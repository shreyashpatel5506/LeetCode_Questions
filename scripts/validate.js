import fs from 'fs/promises';
import path from 'path';
import { getLanguageExtension } from './utils.js';

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

async function checkFileExists(p) {
  try {
    const stat = await fs.stat(p);
    return stat.isFile();
  } catch (e) {
    return false;
  }
}

async function main() {
  console.log('Running codebase validation checks...');
  let hasErrors = false;

  // 1. Validate Problems
  const problemIds = new Set();
  const problemSlugs = new Set();

  if (!(await checkDirectoryExists(PROBLEMS_DIR))) {
    console.log('Problems directory does not exist yet. Skipping problems validation.');
  } else {
    const folders = await fs.readdir(PROBLEMS_DIR, { withFileTypes: true });
    
    for (const folder of folders) {
      if (!folder.isDirectory()) continue;
      
      const folderPath = path.join(PROBLEMS_DIR, folder.name);
      
      // Check folder name format (0000-slug)
      if (!/^\d{4}-.+/.test(folder.name)) {
        console.error(`[Error] Invalid problem folder name format: "${folder.name}". Expected "0000-slug".`);
        hasErrors = true;
      }

      // Check metadata.json
      const metadataPath = path.join(folderPath, 'metadata.json');
      if (!(await checkFileExists(metadataPath))) {
        console.error(`[Error] Missing metadata.json in "${folder.name}"`);
        hasErrors = true;
        continue;
      }

      let meta;
      try {
        const content = await fs.readFile(metadataPath, 'utf8');
        meta = JSON.parse(content);
      } catch (e) {
        console.error(`[Error] Invalid JSON format in "${folder.name}/metadata.json": ${e.message}`);
        hasErrors = true;
        continue;
      }

      // Check required metadata fields
      const requiredFields = [
        'id', 'title', 'difficulty', 'topics', 'language', 'slug', 
        'acceptanceDate', 'leetcodeUrl', 'submissionId', 'lastUpdated'
      ];
      
      for (const field of requiredFields) {
        if (meta[field] === undefined || meta[field] === null || meta[field] === '') {
          console.error(`[Error] Missing or empty field "${field}" in "${folder.name}/metadata.json"`);
          hasErrors = true;
        }
      }

      // Check for duplicate IDs
      if (meta.id) {
        if (problemIds.has(meta.id)) {
          console.error(`[Error] Duplicate Problem ID detected: "${meta.id}" in folder "${folder.name}"`);
          hasErrors = true;
        } else {
          problemIds.add(meta.id);
        }
      }

      // Check for duplicate Slugs
      if (meta.slug) {
        if (problemSlugs.has(meta.slug)) {
          console.error(`[Error] Duplicate Problem Slug detected: "${meta.slug}" in folder "${folder.name}"`);
          hasErrors = true;
        } else {
          problemSlugs.add(meta.slug);
        }
      }

      // Check for solution file matching metadata language
      if (meta.language) {
        const ext = getLanguageExtension(meta.language);
        const solutionPath = path.join(folderPath, `solution.${ext}`);
        if (!(await checkFileExists(solutionPath))) {
          console.error(`[Error] Missing expected solution file "solution.${ext}" in "${folder.name}"`);
          hasErrors = true;
        }
      }

      // Check README.md
      const readmePath = path.join(folderPath, 'README.md');
      if (!(await checkFileExists(readmePath))) {
        console.error(`[Error] Missing README.md in "${folder.name}"`);
        hasErrors = true;
      }
    }
  }

  // 2. Validate Topics references
  if (!(await checkDirectoryExists(TOPICS_DIR))) {
    console.log('Topics directory does not exist yet. Skipping topics validation.');
  } else {
    const topics = await fs.readdir(TOPICS_DIR, { withFileTypes: true });
    
    for (const topic of topics) {
      if (!topic.isDirectory()) continue;
      
      const topicPath = path.join(TOPICS_DIR, topic.name);
      
      // Check topic README
      const topicReadmePath = path.join(topicPath, 'README.md');
      if (!(await checkFileExists(topicReadmePath))) {
        console.error(`[Error] Missing README.md in topic folder "${topic.name}"`);
        hasErrors = true;
      }

      // Check subfolder references
      const refFolders = await fs.readdir(topicPath, { withFileTypes: true });
      for (const refFolder of refFolders) {
        if (!refFolder.isDirectory()) continue;
        
        const refFolderPath = path.join(topicPath, refFolder.name);
        const refReadmePath = path.join(refFolderPath, 'README.md');
        
        if (!(await checkFileExists(refReadmePath))) {
          console.error(`[Error] Missing README.md reference in "${topic.name}/${refFolder.name}"`);
          hasErrors = true;
          continue;
        }

        // Verify reference path actually points to an existing problem
        const targetProblemFolder = path.join(PROBLEMS_DIR, refFolder.name);
        if (!(await checkDirectoryExists(targetProblemFolder))) {
          console.error(`[Error] Broken reference: topic folder "${topic.name}/${refFolder.name}" does not match any folder in problems/`);
          hasErrors = true;
        }
      }
    }
  }

  if (hasErrors) {
    console.error('\n❌ Validation checks failed. Please fix the errors listed above.');
    process.exit(1);
  } else {
    console.log('\n✅ Validation checks completed successfully. All constraints met!');
  }
}

main().catch(err => {
  console.error('Fatal error in validation script:', err);
  process.exit(1);
});
