import fs from 'fs/promises';
import path from 'path';
import { padId } from './utils.js';

const PROBLEMS_DIR = path.join(process.cwd(), 'problems');
const TOPICS_DIR = path.join(process.cwd(), 'topics');

async function checkDirectoryExists(p) {
  try {
    const stat = await fs.stat(p);
    return stat.isDirectory();
  } catch (e) {
    return false;
  }
}

async function isFolderEmpty(p) {
  const files = await fs.readdir(p);
  return files.length === 0;
}

/**
 * Normalizes problem folder names under problems/
 */
async function normalizeProblemFolders() {
  if (!(await checkDirectoryExists(PROBLEMS_DIR))) return;

  const entries = await fs.readdir(PROBLEMS_DIR, { withFileTypes: true });
  for (const entry of entries) {
    if (!entry.isDirectory()) continue;

    const currentPath = path.join(PROBLEMS_DIR, entry.name);
    let targetName = entry.name;

    // Check if name is formatted with leading digits
    const match = entry.name.match(/^(\d+)-(.+)$/);
    if (match) {
      const qid = padId(match[1]);
      targetName = `${qid}-${match[2]}`;
    }

    if (targetName !== entry.name) {
      const targetPath = path.join(PROBLEMS_DIR, targetName);
      console.log(`Renaming folder: "${entry.name}" -> "${targetName}"`);
      try {
        await fs.rename(currentPath, targetPath);
        
        // Also update id in metadata.json if it exists inside
        const metadataPath = path.join(targetPath, 'metadata.json');
        try {
          const content = await fs.readFile(metadataPath, 'utf8');
          const meta = JSON.parse(content);
          const qid = padId(match[1]);
          if (meta.id !== qid) {
            meta.id = qid;
            await fs.writeFile(metadataPath, JSON.stringify(meta, null, 2), 'utf8');
            console.log(`Updated ID in metadata.json for ${targetName}`);
          }
        } catch (e) {
          // No valid metadata inside, ignore
        }
      } catch (e) {
        console.error(`Failed to rename folder "${entry.name}":`, e.message);
      }
    }
  }
}

/**
 * Prunes empty folders under a target directory
 */
async function pruneEmptyFolders(dirPath) {
  if (!(await checkDirectoryExists(dirPath))) return;

  const entries = await fs.readdir(dirPath, { withFileTypes: true });
  for (const entry of entries) {
    if (entry.isDirectory()) {
      const subpath = path.join(dirPath, entry.name);
      await pruneEmptyFolders(subpath);

      if (await isFolderEmpty(subpath)) {
        console.log(`Pruning empty folder: ${path.relative(process.cwd(), subpath)}`);
        await fs.rmdir(subpath);
      }
    }
  }
}

/**
 * Removes obsolete references under topics/
 */
async function pruneObsoleteTopics() {
  if (!(await checkDirectoryExists(TOPICS_DIR))) return;

  const topics = await fs.readdir(TOPICS_DIR, { withFileTypes: true });
  for (const topic of topics) {
    if (!topic.isDirectory()) continue;

    const topicPath = path.join(TOPICS_DIR, topic.name);
    const entries = await fs.readdir(topicPath, { withFileTypes: true });
    
    let activeRefs = 0;

    for (const entry of entries) {
      if (!entry.isDirectory()) continue;

      const refPath = path.join(topicPath, entry.name);
      const targetProblemFolder = path.join(PROBLEMS_DIR, entry.name);

      // Check if target problem exists. If not, this is an obsolete reference.
      if (!(await checkDirectoryExists(targetProblemFolder))) {
        console.log(`Pruning obsolete topic reference: ${topic.name}/${entry.name}`);
        await fs.rm(refPath, { recursive: true, force: true });
      } else {
        activeRefs++;
      }
    }

    // If a topic has no active references remaining, delete its folder
    if (activeRefs === 0) {
      console.log(`Pruning empty topic directory: topics/${topic.name}`);
      await fs.rm(topicPath, { recursive: true, force: true });
    }
  }
}

async function main() {
  console.log('Running codebase cleanup...');

  // 1. Rename problem folders to ensure correct padding format
  await normalizeProblemFolders();

  // 2. Remove obsolete references under topics/
  await pruneObsoleteTopics();

  // 3. Prune any empty directories recursively
  await pruneEmptyFolders(PROBLEMS_DIR);
  await pruneEmptyFolders(TOPICS_DIR);

  console.log('Cleanup completed successfully.');
}

main().catch(err => {
  console.error('Fatal error in cleanup script:', err);
  process.exit(1);
});
