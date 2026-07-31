import fs from 'fs/promises';
import path from 'path';
import { execSync } from 'child_process';
import { makeRequest, delay, padId, normalizeName, getLanguageExtension, graphqlHeaders } from './utils.js';

const PROBLEMS_DIR = path.join(process.cwd(), 'problems');

/**
 * Fetch all local problems metadata
 * @returns {Promise<Map<string, object>>} Map of slug -> metadata
 */
async function getLocalProblems() {
  const problems = new Map();
  try {
    await fs.mkdir(PROBLEMS_DIR, { recursive: true });
    const entries = await fs.readdir(PROBLEMS_DIR, { withFileTypes: true });
    for (const entry of entries) {
      if (entry.isDirectory()) {
        const metadataPath = path.join(PROBLEMS_DIR, entry.name, 'metadata.json');
        try {
          const content = await fs.readFile(metadataPath, 'utf8');
          const meta = JSON.parse(content);
          if (meta.slug) {
            problems.set(meta.slug, meta);
          }
        } catch (e) {
          // Metadata missing or invalid, ignore or handle in validator/cleanup
        }
      }
    }
  } catch (e) {
    console.error('Error scanning local problems:', e.message);
  }
  return problems;
}

/**
 * Fetch accepted submissions from LeetCode REST API
 * @param {string} session 
 * @param {string} csrfToken 
 * @returns {Promise<Array<object>>}
 */
async function fetchAcceptedSubmissions(session, csrfToken) {
  let offset = 0;
  const limit = 20;
  let hasNext = true;
  const submissions = [];
  const headers = {
    'cookie': `csrftoken=${csrfToken}; LEETCODE_SESSION=${session};`,
    'x-csrftoken': csrfToken,
    'referer': 'https://leetcode.com/submissions/',
  };

  console.log('Fetching submission list from LeetCode API...');

  while (hasNext) {
    const url = `https://leetcode.com/api/submissions/?offset=${offset}&limit=${limit}`;
    try {
      const res = await makeRequest(url, { headers });
      if (res.statusCode !== 200) {
        throw new Error(`Failed to fetch submissions list, status: ${res.statusCode}`);
      }
      
      const data = res.data;
      if (!data || !data.submissions_dump) {
        break;
      }

      const dump = data.submissions_dump;
      for (const sub of dump) {
        if (sub.status_display === 'Accepted') {
          submissions.push({
            id: sub.id,
            title: sub.title,
            slug: sub.title_slug,
            lang: sub.lang,
            timestamp: parseInt(sub.timestamp, 10),
            url: `https://leetcode.com${sub.url}`
          });
        }
      }

      hasNext = data.has_next;
      offset += limit;
      
      // Delay to avoid hitting rate limits
      await delay(1500);
      console.log(`Fetched ${submissions.length} accepted submissions so far (offset: ${offset})...`);
    } catch (e) {
      console.error(`Error fetching submissions list at offset ${offset}:`, e.message);
      break;
    }
  }

  return submissions;
}

/**
 * Fetch detailed submission code and question frontend details from GraphQL
 */
async function fetchSubmissionDetails(submissionId, session, csrfToken) {
  const url = 'https://leetcode.com/graphql/';
  const headers = graphqlHeaders(session, csrfToken);
  
  const query = `
    query submissionDetails($submissionId: Int!) {
      submissionDetails(submissionId: $submissionId) {
        runtimePercentile
        memoryPercentile
        code
        question {
          questionId
        }
      }
    }
  `;

  const variables = { submissionId };
  
  try {
    const res = await makeRequest(url, {
      method: 'POST',
      headers,
    }, JSON.stringify({ query, variables }));

    if (res.statusCode !== 200 || !res.data || !res.data.data) {
      throw new Error(`GraphQL query failed with status ${res.statusCode}`);
    }

    return res.data.data.submissionDetails;
  } catch (e) {
    console.error(`Error fetching submission ${submissionId} details:`, e.message);
    return null;
  }
}

/**
 * Fallback to query question metadata by titleSlug
 */
async function fetchQuestionMetadata(titleSlug, session, csrfToken) {
  const url = 'https://leetcode.com/graphql/';
  const headers = graphqlHeaders(session, csrfToken);
  
  const query = `
    query questionData($titleSlug: String!) {
      question(titleSlug: $titleSlug) {
        questionFrontendId
        title
        difficulty
        topicTags {
          name
          slug
        }
      }
    }
  `;

  const variables = { titleSlug };
  
  try {
    const res = await makeRequest(url, {
      method: 'POST',
      headers,
    }, JSON.stringify({ query, variables }));

    if (res.statusCode !== 200 || !res.data || !res.data.data) {
      throw new Error(`GraphQL questionData failed with status ${res.statusCode}`);
    }

    return res.data.data.question;
  } catch (e) {
    console.error(`Error fetching question metadata for ${titleSlug}:`, e.message);
    return null;
  }
}

/**
 * Run git commit with custom dates to build contribution history
 */
function gitCommit(message, filePath, isoDate) {
  try {
    execSync(`git add "${filePath}"`, { stdio: 'inherit' });
    
    // Commit with modified author and committer dates
    const env = {
      ...process.env,
      GIT_AUTHOR_DATE: isoDate,
      GIT_COMMITTER_DATE: isoDate
    };
    
    execSync(`git commit -m "${message}"`, { env, stdio: 'inherit' });
    console.log(`Committed: ${message}`);
  } catch (e) {
    console.error(`Git commit failed for ${filePath}:`, e.message);
  }
}

async function main() {
  const session = process.env.LEETCODE_SESSION;
  const csrfToken = process.env.LEETCODE_CSRF_TOKEN;
  const dryRun = process.env.DRY_RUN === 'true';

  if (!session || !csrfToken) {
    console.warn('LEETCODE_SESSION or LEETCODE_CSRF_TOKEN environment variable is missing.');
    console.warn('Running in Dry-Run/Offline validation mode.');
    return;
  }

  // Get locally synced problems
  const localProblems = await getLocalProblems();
  console.log(`Found ${localProblems.size} problems locally.`);

  // Fetch accepted submissions from LeetCode
  const submissions = await fetchAcceptedSubmissions(session, csrfToken);
  console.log(`Fetched ${submissions.length} accepted submissions from LeetCode.`);

  // Group submissions by title slug, keeping the latest accepted one
  const latestSubmissions = new Map();
  for (const sub of submissions) {
    if (!latestSubmissions.has(sub.slug)) {
      latestSubmissions.set(sub.slug, sub);
    } else {
      const existing = latestSubmissions.get(sub.slug);
      if (sub.timestamp > existing.timestamp) {
        latestSubmissions.set(sub.slug, sub);
      }
    }
  }
  console.log(`Identified ${latestSubmissions.size} unique solved problems.`);

  // Filter for new/outdated submissions
  const pendingUpdates = [];
  for (const [slug, sub] of latestSubmissions.entries()) {
    const local = localProblems.get(slug);
    if (!local || sub.id > local.submissionId) {
      pendingUpdates.push(sub);
    }
  }

  // Sort chronologically (oldest first) to build the correct contribution graph
  pendingUpdates.sort((a, b) => a.timestamp - b.timestamp);
  console.log(`Found ${pendingUpdates.length} submissions to sync.`);

  let syncedCount = 0;

  for (const sub of pendingUpdates) {
    console.log(`\nSyncing: ${sub.title} (ID: ${sub.id})...`);
    
    // Fetch details
    await delay(1000); // Wait between requests to prevent rate limiting
    const details = await fetchSubmissionDetails(sub.id, session, csrfToken);
    
    if (!details || !details.code) {
      console.error(`Skipping ${sub.title}: Could not fetch submission details.`);
      continue;
    }

    let questionInfo = details.question;
    // Fallback to fetch question details if topicTags or frontend ID is missing
    if (!questionInfo || !questionInfo.questionFrontendId || !questionInfo.topicTags) {
      await delay(1000);
      const fallback = await fetchQuestionMetadata(sub.slug, session, csrfToken);
      if (fallback) {
        questionInfo = { ...(questionInfo || {}), ...fallback };
      }
    }

    if (!questionInfo || !questionInfo.questionFrontendId) {
      console.error(`Skipping ${sub.title}: Could not retrieve question metadata.`);
      continue;
    }

    const qid = padId(questionInfo.questionFrontendId);
    const folderName = `${qid}-${sub.slug}`;
    const problemFolder = path.join(PROBLEMS_DIR, folderName);
    
    await fs.mkdir(problemFolder, { recursive: true });

    // Extension mapping
    const ext = getLanguageExtension(sub.lang);
    const solutionFile = `solution.${ext}`;
    const solutionPath = path.join(problemFolder, solutionFile);

    // Save solution code
    await fs.writeFile(solutionPath, details.code, 'utf8');

    // Setup metadata
    const isoDate = new Date(sub.timestamp * 1000).toISOString();
    const formattedTopics = questionInfo.topicTags ? questionInfo.topicTags.map(t => t.name) : [];
    
    const metadata = {
      id: qid,
      title: questionInfo.title || sub.title,
      difficulty: questionInfo.difficulty || 'Unknown',
      topics: formattedTopics,
      language: sub.lang,
      slug: sub.slug,
      acceptanceDate: isoDate,
      leetcodeUrl: `https://leetcode.com/problems/${sub.slug}/`,
      submissionId: sub.id,
      lastUpdated: new Date().toISOString()
    };

    const metadataPath = path.join(problemFolder, 'metadata.json');
    await fs.writeFile(metadataPath, JSON.stringify(metadata, null, 2), 'utf8');

    // Preserve AI Expanation if exists
    const readmePath = path.join(problemFolder, 'README.md');
    let customAiContent = '';
    try {
      const existingReadme = await fs.readFile(readmePath, 'utf8');
      const match = existingReadme.match(/<!-- AI_EXPLANATION_START -->([\s\S]*?)<!-- AI_EXPLANATION_END -->/);
      if (match) {
        customAiContent = match[1].trim();
      }
    } catch (e) {
      // file doesn't exist yet, ignore
    }

    // Generate Problem README
    const readmeContent = `# ${metadata.title}

## Details
- **Problem ID:** ${metadata.id}
- **Difficulty:** ${metadata.difficulty}
- **Topics:** ${metadata.topics.join(', ') || 'None'}
- **Language:** ${metadata.language}
- **LeetCode Link:** [LeetCode - ${metadata.title}](${metadata.leetcodeUrl})
- **Last Updated:** ${metadata.acceptanceDate.split('T')[0]}

## Folder Contents
- [Solution File](./${solutionFile})

<!-- AI_EXPLANATION_START -->
${customAiContent || '*No explanation added yet. AI explanations can be generated here in the future.*'}
<!-- AI_EXPLANATION_END -->
`;

    await fs.writeFile(readmePath, readmeContent, 'utf8');
    
    // Commit if not in dryRun
    if (!dryRun) {
      const commitMessage = `feat(leetcode): add ${qid} ${metadata.title}`;
      // Add all files in the problem folder
      execSync(`git add "${problemFolder}"`);
      gitCommit(commitMessage, problemFolder, isoDate);
    } else {
      console.log(`[Dry Run] Saved files for: ${metadata.title}`);
    }

    syncedCount++;
  }

  console.log(`\nSync finished. Successfully processed ${syncedCount} new/updated submissions.`);
}

main().catch(err => {
  console.error('Fatal error in sync script:', err);
  process.exit(1);
});
