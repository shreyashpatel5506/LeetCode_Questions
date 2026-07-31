import fs from 'fs/promises';
import path from 'path';

const PROBLEMS_DIR = path.join(process.cwd(), 'problems');
const STATS_DIR = path.join(process.cwd(), 'stats');

async function main() {
  console.log('Generating repository statistics...');

  // Create stats directory if it doesn't exist
  await fs.mkdir(STATS_DIR, { recursive: true });

  const problems = [];
  try {
    const entries = await fs.readdir(PROBLEMS_DIR, { withFileTypes: true });
    for (const entry of entries) {
      if (entry.isDirectory()) {
        const metadataPath = path.join(PROBLEMS_DIR, entry.name, 'metadata.json');
        try {
          const content = await fs.readFile(metadataPath, 'utf8');
          const meta = JSON.parse(content);
          problems.push(meta);
        } catch (e) {
          // Skip folders with missing or invalid metadata
        }
      }
    }
  } catch (e) {
    console.warn('Could not read problems directory. Starting with empty statistics.');
  }

  // 1. Difficulty distribution
  const difficulty = { Easy: 0, Medium: 0, Hard: 0, Unknown: 0 };
  
  // 2. Topic distribution
  const topics = {};

  // 3. Language distribution
  const languages = {};

  // 4. Activity (grouped by date)
  const activity = {};

  // Parse all problems
  for (const meta of problems) {
    // Difficulty
    const diff = meta.difficulty || 'Unknown';
    difficulty[diff] = (difficulty[diff] || 0) + 1;

    // Topics
    if (meta.topics && Array.isArray(meta.topics)) {
      for (const topic of meta.topics) {
        topics[topic] = (topics[topic] || 0) + 1;
      }
    }

    // Language
    const lang = meta.language || 'unknown';
    languages[lang] = (languages[lang] || 0) + 1;

    // Activity date (from acceptanceDate, YYYY-MM-DD format)
    if (meta.acceptanceDate) {
      const dateStr = meta.acceptanceDate.split('T')[0];
      activity[dateStr] = (activity[dateStr] || 0) + 1;
    }
  }

  // 5. Recent problems (sorted by acceptanceDate desc, max 10)
  const recent = [...problems]
    .filter(meta => meta.acceptanceDate)
    .sort((a, b) => new Date(b.acceptanceDate) - new Date(a.acceptanceDate))
    .slice(0, 10)
    .map(meta => ({
      id: meta.id,
      title: meta.title,
      difficulty: meta.difficulty,
      language: meta.language,
      acceptanceDate: meta.acceptanceDate,
      slug: meta.slug
    }));

  // 6. Global Stats Metadata
  const repoMeta = {
    totalSolved: problems.length,
    lastSync: new Date().toISOString(),
    difficultyDistribution: {
      Easy: difficulty.Easy || 0,
      Medium: difficulty.Medium || 0,
      Hard: difficulty.Hard || 0
    },
    topLanguages: Object.entries(languages)
      .sort((a, b) => b[1] - a[1])
      .reduce((obj, [key, val]) => {
        obj[key] = val;
        return obj;
      }, {})
  };

  // Write all stats JSON files
  await fs.writeFile(path.join(STATS_DIR, 'difficulty.json'), JSON.stringify(difficulty, null, 2), 'utf8');
  await fs.writeFile(path.join(STATS_DIR, 'topics.json'), JSON.stringify(topics, null, 2), 'utf8');
  await fs.writeFile(path.join(STATS_DIR, 'languages.json'), JSON.stringify(languages, null, 2), 'utf8');
  await fs.writeFile(path.join(STATS_DIR, 'activity.json'), JSON.stringify(activity, null, 2), 'utf8');
  await fs.writeFile(path.join(STATS_DIR, 'recent.json'), JSON.stringify(recent, null, 2), 'utf8');
  await fs.writeFile(path.join(STATS_DIR, 'metadata.json'), JSON.stringify(repoMeta, null, 2), 'utf8');

  console.log(`Statistics successfully generated. Total problems parsed: ${problems.length}`);
}

main().catch(err => {
  console.error('Fatal error in stats script:', err);
  process.exit(1);
});
