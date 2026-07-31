import fs from 'fs/promises';
import path from 'path';
import { getLanguageExtension } from './utils.js';

const PROBLEMS_DIR = path.join(process.cwd(), 'problems');
const TOPICS_DIR = path.join(process.cwd(), 'topics');

async function main() {
  console.log('Generating topic indexes...');

  // Scan all problems
  const problems = [];
  try {
    const entries = await fs.readdir(PROBLEMS_DIR, { withFileTypes: true });
    for (const entry of entries) {
      if (entry.isDirectory()) {
        const metadataPath = path.join(PROBLEMS_DIR, entry.name, 'metadata.json');
        try {
          const content = await fs.readFile(metadataPath, 'utf8');
          const meta = JSON.parse(content);
          problems.push({
            ...meta,
            folderName: entry.name
          });
        } catch (e) {
          // Skip folders with invalid metadata
        }
      }
    }
  } catch (e) {
    console.warn('No problems found. Skipping topic page generation.');
    return;
  }

  // Re-create the topics directory from scratch
  try {
    await fs.rm(TOPICS_DIR, { recursive: true, force: true });
  } catch (e) {
    // Ignore error if it doesn't exist
  }
  await fs.mkdir(TOPICS_DIR, { recursive: true });

  // Map topics to their problems
  const topicsMap = new Map();
  for (const prob of problems) {
    const topicTags = prob.topics || [];
    for (const topic of topicTags) {
      const trimmedTopic = topic.trim();
      if (!trimmedTopic) continue;

      if (!topicsMap.has(trimmedTopic)) {
        topicsMap.set(trimmedTopic, []);
      }
      topicsMap.get(trimmedTopic).push(prob);
    }
  }

  // Generate directory and files for each topic
  for (const [topicName, probList] of topicsMap.entries()) {
    // Sort problems alphabetically by title
    probList.sort((a, b) => a.title.localeCompare(b.title));

    const topicFolder = path.join(TOPICS_DIR, topicName);
    await fs.mkdir(topicFolder, { recursive: true });

    // 1. Generate individual problem reference folders inside topic folder
    for (const prob of probList) {
      const refFolder = path.join(topicFolder, prob.folderName);
      await fs.mkdir(refFolder, { recursive: true });

      const ext = getLanguageExtension(prob.language);
      const relativeProblemPath = `../../../problems/${prob.folderName}`;
      const relativeSolutionPath = `${relativeProblemPath}/solution.${ext}`;

      const refReadmeContent = `# ${prob.title}

This problem belongs to the topic **${topicName}**.

To view the problem description, solution files, and metadata, please check the main folder:
- [Problem Folder](${relativeProblemPath}/)
- [Solution File](${relativeSolutionPath})
`;
      await fs.writeFile(path.join(refFolder, 'README.md'), refReadmeContent, 'utf8');
    }

    // 2. Generate topics/<Topic Name>/README.md with a table of problems
    let readmeContent = `# Topic: ${topicName}

This page contains all problems categorized under the **${topicName}** topic.

## Solved Problems (${probList.length})

| Problem Name | Difficulty | Language | LeetCode Link | Solution Link |
| :--- | :--- | :--- | :--- | :--- |
`;

    for (const prob of probList) {
      const ext = getLanguageExtension(prob.language);
      const relativeProblemPath = `../../problems/${prob.folderName}`;
      const relativeSolutionPath = `${relativeProblemPath}/solution.${ext}`;
      
      readmeContent += `| **${prob.title}** | \`${prob.difficulty}\` | \`${prob.language}\` | [LeetCode](${prob.leetcodeUrl}) | [Local Solution](${relativeProblemPath}/) |\n`;
    }

    await fs.writeFile(path.join(topicFolder, 'README.md'), readmeContent, 'utf8');
  }

  console.log(`Topic indexes generated. Successfully indexed ${topicsMap.size} topics.`);
}

main().catch(err => {
  console.error('Fatal error in topics script:', err);
  process.exit(1);
});
