# 🚀 LeetCode Portfolio & Automation Engine v2

Welcome to my LeetCode engineering portfolio! This repository is not just a dump of coding solutions—it is a **fully automated, recruiter-ready software engineering project** designed to catalog, tag, index, and validate my problem-solving journey automatically.

---

## 🛠️ System Architecture & Automation Status

This portfolio is automatically updated by a **custom, native Node.js pipeline** executed via **GitHub Actions**. Every day, the sync engine runs, fetches my latest accepted LeetCode submissions, extracts problem details, builds language-specific files, updates indexes, compiles stats, and commits them.

| Metric | Status / Value |
| :--- | :--- |
| **Sync Engine** | 🟢 Active |
| **Actions Workflows** | 6 Pipelines (Sync, Stats, README, Topics, Validate, Cleanup) |
| **Third-Party Libraries** | None (100% Native Node.js & ES Modules) |
| **Latest Synchronization** | `Jul 31, 2026, 6:38 AM (UTC)` |
| **Workflow Status** | [![Sync](https://github.com/username/repo/actions/workflows/sync.yml/badge.svg)](https://github.com/username/repo/actions/workflows/sync.yml) |

---

## 📊 Repository Statistics

### Difficulty Distribution
We focus on writing high-quality, optimal solutions across all difficulties:

| Difficulty | Solved Count | Percentage | Visualization |
| :--- | :---: | :---: | :--- |
| 🟢 **Easy** | **1** | 50.0% | `█████` |
| 🟡 **Medium** | **1** | 50.0% | `█████` |
| 🔴 **Hard** | **0** | 0.0% | `-` |
| **Total Solved** | **2** | **100%** | |

### Top Languages Used
| Language | Count | Percentage |
| :--- | :---: | :---: |
| `javascript` | 2 | 100.0% |

---

## 🗂️ Topic Explorer

Each problem in this portfolio is linked to one or more conceptual topics. You can explore problems grouped by their algorithmic concept below. Each link leads to a topic index page containing solution files and direct links:

| | | |
| :--- | :--- | :--- |
| 📁 [**Array**](topics/Array/) (`1`) | 📁 [**Hash Table**](topics/Hash%20Table/) (`1`) | 📁 [**Tree**](topics/Tree/) (`1`) |
| 📁 [**BFS**](topics/BFS/) (`1`) | 📁 [**Binary Tree**](topics/Binary%20Tree/) (`1`) |  |

---

## 🕒 Recently Solved Problems

Here are the last 10 problems I solved, synchronized directly from my LeetCode history:

| ID | Title | Difficulty | Language | Acceptance Date | Solution |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0102 | [Binary Tree Level Order Traversal](#) | `Medium` | `javascript` | 2026-07-31 | [Local Code](problems/0102-binary-tree-level-order-traversal/) |
| 0001 | [Two Sum](#) | `Easy` | `javascript` | 2026-07-30 | [Local Code](problems/0001-two-sum/) |

---

## 📁 Repository Structure

The codebase is organized following clean architectural principles:

```
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
```

---

## 🚀 Future Features & AI Integration

The architecture is designed to support **AI-generated extensions** without breaking updates or losing historical history. In the future, the sync process will support adding detailed explanation cards, approach analyses, time/space complexity audits, and mock interview questions inside the `<!-- AI_EXPLANATION -->` segments of each problem's self-contained `README.md`.
