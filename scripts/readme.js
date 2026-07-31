import fs from 'fs/promises';
import path from 'path';

const STATS_DIR = path.join(process.cwd(), 'stats');
const README_PATH = path.join(process.cwd(), 'README.md');

/**
 * Load stats file or return default value if error
 */
async function loadStatsFile(filename, defaultValue = {}) {
  try {
    const content = await fs.readFile(path.join(STATS_DIR, filename), 'utf8');
    return JSON.parse(content);
  } catch (e) {
    return defaultValue;
  }
}

async function main() {
  console.log('Generating main repository README.md...');

  // Load stats
  const difficulty = await loadStatsFile('difficulty.json', { Easy: 0, Medium: 0, Hard: 0, Unknown: 0 });
  const topics = await loadStatsFile('topics.json', {});
  const languages = await loadStatsFile('languages.json', {});
  const recent = await loadStatsFile('recent.json', []);
  const metadata = await loadStatsFile('metadata.json', { totalSolved: 0, lastSync: new Date().toISOString() });

  const total = metadata.totalSolved || 0;
  const lastSyncDate = metadata.lastSync ? new Date(metadata.lastSync).toLocaleString('en-US', {
    dateStyle: 'medium',
    timeStyle: 'short',
    timeZone: 'UTC'
  }) + ' (UTC)' : 'N/A';

  // Sort topics by problem counts desc
  const sortedTopics = Object.entries(topics)
    .sort((a, b) => b[1] - a[1]);

  // Sort languages by counts desc
  const sortedLanguages = Object.entries(languages)
    .sort((a, b) => b[1] - a[1]);

  // Design beautiful README
  let readme = `# 🚀 LeetCode Portfolio & Automation Engine v2

Welcome to my LeetCode engineering portfolio! This repository is not just a dump of coding solutions—it is a **fully automated, recruiter-ready software engineering project** designed to catalog, tag, index, and validate my problem-solving journey automatically.

---

## 🛠️ System Architecture & Automation Status

This portfolio is automatically updated by a **custom, native Node.js pipeline** executed via **GitHub Actions**. Every day, the sync engine runs, fetches my latest accepted LeetCode submissions, extracts problem details, builds language-specific files, updates indexes, compiles stats, and commits them.

| Metric | Status / Value |
| :--- | :--- |
| **Sync Engine** | 🟢 Active |
| **Actions Workflows** | 6 Pipelines (Sync, Stats, README, Topics, Validate, Cleanup) |
| **Third-Party Libraries** | None (100% Native Node.js & ES Modules) |
| **Latest Synchronization** | \`${lastSyncDate}\` |
| **Workflow Status** | [![Sync](https://github.com/${process.env.GITHUB_REPOSITORY || 'username/repo'}/actions/workflows/sync.yml/badge.svg)](https://github.com/${process.env.GITHUB_REPOSITORY || 'username/repo'}/actions/workflows/sync.yml) |

---

## 📊 Repository Statistics

### Difficulty Distribution
We focus on writing high-quality, optimal solutions across all difficulties:

| Difficulty | Solved Count | Percentage | Visualization |
| :--- | :---: | :---: | :--- |
| 🟢 **Easy** | **${difficulty.Easy || 0}** | ${total ? ((difficulty.Easy / total) * 100).toFixed(1) : 0}% | \`${'█'.repeat(Math.round(((difficulty.Easy || 0) / (total || 1)) * 10)) || '-'}\` |
| 🟡 **Medium** | **${difficulty.Medium || 0}** | ${total ? ((difficulty.Medium / total) * 100).toFixed(1) : 0}% | \`${'█'.repeat(Math.round(((difficulty.Medium || 0) / (total || 1)) * 10)) || '-'}\` |
| 🔴 **Hard** | **${difficulty.Hard || 0}** | ${total ? ((difficulty.Hard / total) * 100).toFixed(1) : 0}% | \`${'█'.repeat(Math.round(((difficulty.Hard || 0) / (total || 1)) * 10)) || '-'}\` |
| **Total Solved** | **${total}** | **100%** | |

### Top Languages Used
| Language | Count | Percentage |
| :--- | :---: | :---: |
`;

  for (const [lang, count] of sortedLanguages) {
    readme += `| \`${lang}\` | ${count} | ${total ? ((count / total) * 100).toFixed(1) : 0}% |\n`;
  }

  readme += `
---

## 🗂️ Topic Explorer

Each problem in this portfolio is linked to one or more conceptual topics. You can explore problems grouped by their algorithmic concept below. Each link leads to a topic index page containing solution files and direct links:

`;

  // Display top topics in a nice column table
  const columns = 3;
  let topicTable = '| | | |\n| :--- | :--- | :--- |\n';
  let cellCount = 0;
  let rowCells = [];

  for (const [topic, count] of sortedTopics) {
    const encodedTopic = encodeURIComponent(topic);
    rowCells.push(`📁 [**${topic}**](topics/${encodedTopic}/) (\`${count}\`)`);
    cellCount++;

    if (cellCount % columns === 0) {
      topicTable += `| ${rowCells.join(' | ')} |\n`;
      rowCells = [];
    }
  }
  if (rowCells.length > 0) {
    while (rowCells.length < columns) {
      rowCells.push('');
    }
    topicTable += `| ${rowCells.join(' | ')} |\n`;
  }

  readme += topicTable + `
---

## 🕒 Recently Solved Problems

Here are the last 10 problems I solved, synchronized directly from my LeetCode history:

| ID | Title | Difficulty | Language | Acceptance Date | Solution |
| :--- | :--- | :--- | :--- | :--- | :--- |
`;

  for (const prob of recent) {
    const localPath = `problems/${prob.id}-${prob.slug}`;
    const dateStr = prob.acceptanceDate ? prob.acceptanceDate.split('T')[0] : 'N/A';
    readme += `| ${prob.id} | [${prob.title}](${prob.leetcodeUrl || '#'}) | \`${prob.difficulty}\` | \`${prob.language}\` | ${dateStr} | [Local Code](${localPath}/) |\n`;
  }

  readme += `
---

## 📁 Repository Structure

The codebase is organized following clean architectural principles:

\`\`\`
LeetCode_Questions/
├── .github/
│   └── workflows/          # GitHub Action automation workflows
│       ├── sync.yml        # Synchronizes new LeetCode solutions daily
│       ├── stats.yml       # Refreshes stats JSON files
│       ├── readme.yml      # Updates this main portfolio README
│       ├── topics.yml      # Regroups and links problems under topics/
│       ├── validate.yml    # Runs integrity verification checks
│       └── cleanup.yml     # Removes obsolete files & formats names
├── problems/               # One source of truth for problem folders
│   └── [ID]-[slug]/        # Unique problem folder
│       ├── README.md       # Self-contained problem README
│       ├── solution.[ext]  # Executable solution source code
│       └── metadata.json   # Machine-readable metadata file
├── topics/                 # Auto-generated conceptual indexes (references)
│   └── [concept]/          # Algorithmic topic folder (e.g., Array)
│       ├── README.md       # Directory listing for this topic
│       └── [ID]-[slug]/    # Topic references containing links to problems/
├── stats/                  # Auto-generated statistics databases
│       ├── topics.json
│       ├── difficulty.json
│       ├── languages.json
│       └── metadata.json
├── scripts/                # Modular automation script execution engine
│       ├── sync.js         # Core submission fetcher logic
│       ├── stats.js        # Compiles metadata statistics databases
│       ├── readme.js       # Generates this portfolio file
│       ├── topics.js       # Groups files into topic categories
│       ├── validate.js     # Verifies data integrity
│       └── cleanup.js      # Prunes invalid files
└── package.json            # Script definitions and type specifications
\`\`\`

---

## 🚀 Future Features & AI Integration

The architecture is designed to support **AI-generated extensions** without breaking updates or losing historical history. In the future, the sync process will support adding detailed explanation cards, approach analyses, time/space complexity audits, and mock interview questions inside the \`<!-- AI_EXPLANATION -->\` segments of each problem's self-contained \`README.md\`.
`;

  await fs.writeFile(README_PATH, readme, 'utf8');
  console.log('Main README.md dashboard successfully generated!');
}

main().catch(err => {
  console.error('Fatal error in README generator script:', err);
  process.exit(1);
});
