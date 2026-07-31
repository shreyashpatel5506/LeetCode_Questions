import fs from 'fs/promises';
import path from 'path';
import { execSync } from 'child_process';

const PROBLEMS_DIR = path.join(process.cwd(), 'problems');
const STATS_DIR = path.join(process.cwd(), 'stats');
const TOPICS_DIR = path.join(process.cwd(), 'topics');

const mockProblems = [
  {
    id: '0001',
    title: 'Two Sum',
    difficulty: 'Easy',
    topics: ['Array', 'Hash Table'],
    language: 'javascript',
    slug: 'two-sum',
    acceptanceDate: '2026-07-30T10:00:00Z',
    leetcodeUrl: 'https://leetcode.com/problems/two-sum/',
    submissionId: 100000001,
    lastUpdated: '2026-07-30T10:00:00Z',
    code: 'var twoSum = function(nums, target) {\n    const map = new Map();\n    for (let i = 0; i < nums.length; i++) {\n        const diff = target - nums[i];\n        if (map.has(diff)) return [map.get(diff), i];\n        map.set(nums[i], i);\n    }\n};'
  },
  {
    id: '0102',
    title: 'Binary Tree Level Order Traversal',
    difficulty: 'Medium',
    topics: ['Tree', 'BFS', 'Binary Tree'],
    language: 'javascript',
    slug: 'binary-tree-level-order-traversal',
    acceptanceDate: '2026-07-31T11:00:00Z',
    leetcodeUrl: 'https://leetcode.com/problems/binary-tree-level-order-traversal/',
    submissionId: 100000002,
    lastUpdated: '2026-07-31T11:00:00Z',
    code: 'var levelOrder = function(root) {\n    if (!root) return [];\n    const result = [];\n    const queue = [root];\n    while (queue.length) {\n        const levelSize = queue.length;\n        const currentLevel = [];\n        for (let i = 0; i < levelSize; i++) {\n            const node = queue.shift();\n            currentLevel.push(node.val);\n            if (node.left) queue.push(node.left);\n            if (node.right) queue.push(node.right);\n        }\n        result.push(currentLevel);\n    }\n    return result;\n};'
  }
];

async function setupMockProblems() {
  console.log('Setting up mock problems for verification...');
  await fs.mkdir(PROBLEMS_DIR, { recursive: true });

  for (const prob of mockProblems) {
    const folderName = `${prob.id}-${prob.slug}`;
    const folderPath = path.join(PROBLEMS_DIR, folderName);
    await fs.mkdir(folderPath, { recursive: true });

    // Write solution code
    await fs.writeFile(path.join(folderPath, 'solution.js'), prob.code, 'utf8');

    // Write metadata
    const metadata = { ...prob };
    delete metadata.code; // exclude code from metadata.json
    await fs.writeFile(
      path.join(folderPath, 'metadata.json'), 
      JSON.stringify(metadata, null, 2), 
      'utf8'
    );

    // Write a base README
    const readmeContent = `# ${prob.title}

## Details
- **Problem ID:** ${prob.id}
- **Difficulty:** ${prob.difficulty}
- **Topics:** ${prob.topics.join(', ')}
- **Language:** ${prob.language}
- **LeetCode Link:** [LeetCode Link](${prob.leetcodeUrl})

## Solution Link
- [solution.js](./solution.js)

<!-- AI_EXPLANATION_START -->
*No explanation added yet. AI explanations can be generated here in the future.*
<!-- AI_EXPLANATION_END -->
`;
    await fs.writeFile(path.join(folderPath, 'README.md'), readmeContent, 'utf8');
  }
}

async function runPipeline() {
  console.log('\nRunning Stats, Topics, README, Validate, and Cleanup scripts...');
  
  execSync('node scripts/stats.js', { stdio: 'inherit' });
  execSync('node scripts/topics.js', { stdio: 'inherit' });
  execSync('node scripts/readme.js', { stdio: 'inherit' });
  execSync('node scripts/validate.js', { stdio: 'inherit' });
  execSync('node scripts/cleanup.js', { stdio: 'inherit' });
}

async function verifyOutputs() {
  console.log('\nVerifying output artifacts...');
  let testsFailed = false;

  const assertFileExists = async (filePath) => {
    try {
      await fs.access(filePath);
      console.log(`✅ File exists: ${path.relative(process.cwd(), filePath)}`);
    } catch (e) {
      console.error(`❌ Missing expected file: ${path.relative(process.cwd(), filePath)}`);
      testsFailed = true;
    }
  };

  // Check stats files
  await assertFileExists(path.join(STATS_DIR, 'difficulty.json'));
  await assertFileExists(path.join(STATS_DIR, 'topics.json'));
  await assertFileExists(path.join(STATS_DIR, 'languages.json'));
  await assertFileExists(path.join(STATS_DIR, 'recent.json'));
  await assertFileExists(path.join(STATS_DIR, 'activity.json'));
  await assertFileExists(path.join(STATS_DIR, 'metadata.json'));

  // Check topic references
  await assertFileExists(path.join(TOPICS_DIR, 'Array', 'README.md'));
  await assertFileExists(path.join(TOPICS_DIR, 'Array', '0001-two-sum', 'README.md'));
  await assertFileExists(path.join(TOPICS_DIR, 'BFS', '0102-binary-tree-level-order-traversal', 'README.md'));

  // Check main README
  await assertFileExists(path.join(process.cwd(), 'README.md'));

  // Check stats contents
  try {
    const diffContent = await fs.readFile(path.join(STATS_DIR, 'difficulty.json'), 'utf8');
    const difficulty = JSON.parse(diffContent);
    if (difficulty.Easy === 1 && difficulty.Medium === 1) {
      console.log('✅ stats/difficulty.json parsed correctly.');
    } else {
      console.error('❌ stats/difficulty.json has incorrect counts:', difficulty);
      testsFailed = true;
    }
  } catch (e) {
    console.error('❌ Failed to parse difficulty.json:', e.message);
    testsFailed = true;
  }

  if (testsFailed) {
    console.error('\n❌ Verification pipeline failed.');
    process.exit(1);
  } else {
    console.log('\n🌟 Verification pipeline successfully completed with no errors!');
  }
}

async function main() {
  await setupMockProblems();
  await runPipeline();
  await verifyOutputs();
}

main().catch(err => {
  console.error('Fatal error in test script:', err);
  process.exit(1);
});
