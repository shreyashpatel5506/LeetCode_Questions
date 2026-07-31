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
          // Metadata missing or invalid, ignore
        }
      }
    }
  } catch (e) {
    console.error('Error scanning local problems:', e.message);
  }
  return problems;
}

/**
 * Fetch all solved problems metadata from LeetCode GraphQL API
 * @param {string} session 
 * @param {string} csrfToken 
 * @returns {Promise<Array<object>>} List of solved questions
 */
async function fetchSolvedProblems(session, csrfToken) {
  let skip = 0;
  const limit = 100;
  let hasNext = true;
  const solvedProblems = [];
  const url = 'https://leetcode.com/graphql/';
  const headers = graphqlHeaders(session, csrfToken);

  console.log('Fetching list of solved problems from LeetCode GraphQL API...');

  while (hasNext) {
    const query = `
      query problemsetQuestionList($categorySlug: String, $limit: Int, $skip: Int, $filters: QuestionListFilterInput) {
        problemsetQuestionList: questionList(
          categorySlug: $categorySlug
          limit: $limit
          skip: $skip
          filters: $filters
        ) {
          totalNum
          data {
            questionFrontendId
            title
            titleSlug
            difficulty
            topicTags {
              name
              slug
            }
          }
        }
      }
    `;

    const variables = {
      categorySlug: "",
      skip,
      limit,
      filters: { status: "AC" }
    };

    try {
      const res = await makeRequest(url, {
        method: 'POST',
        headers,
      }, JSON.stringify({ query, variables }));

      if (res.statusCode !== 200 || !res.data || !res.data.data || !res.data.data.problemsetQuestionList) {
        throw new Error(`Failed to fetch solved problems list, status: ${res.statusCode}`);
      }

      const list = res.data.data.problemsetQuestionList;
      const questions = list.data;
      if (!questions || questions.length === 0) {
        break;
      }

      for (const q of questions) {
        solvedProblems.push({
          id: q.questionFrontendId,
          title: q.title,
          slug: q.titleSlug,
          difficulty: q.difficulty,
          topicTags: q.topicTags
        });
      }

      skip += limit;
      hasNext = solvedProblems.length < list.totalNum;
      
      await delay(1500);
      console.log(`Fetched ${solvedProblems.length} solved problems so far (total: ${list.totalNum})...`);
    } catch (e) {
      console.error(`Error fetching solved problems at skip ${skip}:`, e.message);
      break;
    }
  }

  return solvedProblems;
}

/**
 * Fetch the latest accepted submission for a specific problem
 * @param {string} questionSlug 
 * @param {string} session 
 * @param {string} csrfToken 
 * @returns {Promise<object|null>} The latest accepted submission info
 */
async function fetchLatestAcceptedSubmission(questionSlug, session, csrfToken) {
  const url = 'https://leetcode.com/graphql/';
  const headers = graphqlHeaders(session, csrfToken);

  const query = `
    query submissionList($offset: Int!, $limit: Int!, $lastKey: String, $questionSlug: String!) {
      submissionList(offset: $offset, limit: $limit, lastKey: $lastKey, questionSlug: $questionSlug) {
        lastKey
        hasNext
        submissions {
          id
          statusDisplay
          lang
          timestamp
          url
        }
      }
    }
  `;

  const variables = {
    offset: 0,
    limit: 20,
    lastKey: null,
    questionSlug
  };

  try {
    const res = await makeRequest(url, {
      method: 'POST',
      headers,
    }, JSON.stringify({ query, variables }));

    if (res.statusCode !== 200 || !res.data || !res.data.data || !res.data.data.submissionList) {
      throw new Error(`Failed to fetch submission list for ${questionSlug}, status: ${res.statusCode}`);
    }

    const submissions = res.data.data.submissionList.submissions;
    if (!submissions) return null;

    for (const sub of submissions) {
      if (sub.statusDisplay === 'Accepted') {
        return {
          id: parseInt(sub.id, 10) || sub.id,
          lang: sub.lang,
          timestamp: parseInt(sub.timestamp, 10),
          url: sub.url
        };
      }
    }
  } catch (e) {
    console.error(`Error fetching submission list for ${questionSlug}:`, e.message);
  }
  return null;
}

/**
 * Fetch detailed submission code from GraphQL
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

  // Fetch all solved problems from LeetCode
  const solvedProblems = await fetchSolvedProblems(session, csrfToken);
  console.log(`Found ${solvedProblems.length} solved problems on LeetCode.`);

  // Filter for new/missing solved problems
  const missingProblems = [];
  for (const prob of solvedProblems) {
    if (!localProblems.has(prob.slug)) {
      missingProblems.push(prob);
    }
  }
  console.log(`Found ${missingProblems.length} missing problems to sync.`);

  const toSync = [];
  for (const prob of missingProblems) {
    console.log(`Retrieving submission details for missing problem: ${prob.title}...`);
    await delay(1500); // Respect rate limits
    const sub = await fetchLatestAcceptedSubmission(prob.slug, session, csrfToken);
    if (sub) {
      toSync.push({
        ...prob,
        submission: sub
      });
    } else {
      console.warn(`Could not find any accepted submission for: ${prob.title}`);
    }
  }

  // Sort chronologically (oldest first) to build the correct contribution graph
  toSync.sort((a, b) => a.submission.timestamp - b.submission.timestamp);
  console.log(`Starting synchronization of ${toSync.length} problems...`);

  let syncedCount = 0;

  for (const item of toSync) {
    console.log(`\nSyncing: ${item.title} (ID: ${item.submission.id})...`);
    
    // Fetch details (code)
    await delay(1500);
    const details = await fetchSubmissionDetails(item.submission.id, session, csrfToken);
    
    if (!details || !details.code) {
      console.error(`Skipping ${item.title}: Could not fetch submission code.`);
      continue;
    }

    const qid = padId(item.id);
    const folderName = `${qid}-${item.slug}`;
    const problemFolder = path.join(PROBLEMS_DIR, folderName);
    
    await fs.mkdir(problemFolder, { recursive: true });

    // Extension mapping
    const ext = getLanguageExtension(item.submission.lang);
    const solutionFile = `solution.${ext}`;
    const solutionPath = path.join(problemFolder, solutionFile);

    // Save solution code
    await fs.writeFile(solutionPath, details.code, 'utf8');

    // Setup metadata
    const isoDate = new Date(item.submission.timestamp * 1000).toISOString();
    const formattedTopics = item.topicTags ? item.topicTags.map(t => t.name) : [];
    
    const metadata = {
      id: qid,
      title: item.title,
      difficulty: item.difficulty || 'Unknown',
      topics: formattedTopics,
      language: item.submission.lang,
      slug: item.slug,
      acceptanceDate: isoDate,
      leetcodeUrl: `https://leetcode.com/problems/${item.slug}/`,
      submissionId: item.submission.id,
      lastUpdated: new Date().toISOString()
    };

    const metadataPath = path.join(problemFolder, 'metadata.json');
    await fs.writeFile(metadataPath, JSON.stringify(metadata, null, 2), 'utf8');

    // Preserve AI Explanation if exists
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
