import https from 'https';

/**
 * Perform a native HTTPS request
 * @param {string} url 
 * @param {object} options 
 * @param {string} [postData] 
 * @returns {Promise<{statusCode: number, headers: object, body: string, data: any}>}
 */
export function makeRequest(url, options = {}, postData = null) {
  return new Promise((resolve, reject) => {
    const defaultHeaders = {
      'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36',
    };
    
    const requestOptions = {
      ...options,
      headers: {
        ...defaultHeaders,
        ...(options.headers || {})
      }
    };

    const req = https.request(url, requestOptions, (res) => {
      let body = '';
      res.on('data', (chunk) => {
        body += chunk;
      });
      res.on('end', () => {
        let data = null;
        if (res.headers['content-type'] && res.headers['content-type'].includes('application/json')) {
          try {
            data = JSON.parse(body);
          } catch (e) {
            // ignore JSON parse error, return raw body
          }
        }
        resolve({
          statusCode: res.statusCode,
          headers: res.headers,
          body,
          data
        });
      });
    });

    req.on('error', (err) => {
      reject(err);
    });

    if (postData) {
      req.write(postData);
    }
    req.end();
  });
}

/**
 * Delay execution for ms milliseconds
 * @param {number} ms 
 * @returns {Promise<void>}
 */
export function delay(ms) {
  return new Promise((resolve) => setTimeout(resolve, ms));
}

/**
 * Pad problem frontend ID to 4 characters (e.g. 1 -> "0001")
 * @param {string|number} id 
 * @returns {string}
 */
export function padId(id) {
  const str = String(id).trim();
  if (str.length >= 4) return str;
  return '0'.repeat(4 - str.length) + str;
}

/**
 * Normalize title slug or title into a standard string
 * @param {string} name 
 * @returns {string}
 */
export function normalizeName(name) {
  return name
    .toLowerCase()
    .replace(/\s+/g, '-')
    .replace(/[^a-z0-9_-]/g, '')
    .replace(/-+/g, '-');
}

/**
 * Map LeetCode language designation to extension
 * @param {string} lang 
 * @returns {string}
 */
export function getLanguageExtension(lang) {
  const LANG_TO_EXTENSION = {
    bash: 'sh',
    c: 'c',
    cpp: 'cpp',
    csharp: 'cs',
    dart: 'dart',
    elixir: 'ex',
    erlang: 'erl',
    golang: 'go',
    java: 'java',
    javascript: 'js',
    kotlin: 'kt',
    mssql: 'sql',
    mysql: 'sql',
    oraclesql: 'sql',
    php: 'php',
    python: 'py',
    python3: 'py',
    pythondata: 'py',
    postgresql: 'sql',
    racket: 'rkt',
    ruby: 'rb',
    rust: 'rs',
    scala: 'scala',
    swift: 'swift',
    typescript: 'ts',
  };
  return LANG_TO_EXTENSION[lang.toLowerCase().trim()] || 'txt';
}

/**
 * Generate standard LeetCode authenticated headers
 * @param {string} session 
 * @param {string} csrfToken 
 * @returns {object}
 */
export function graphqlHeaders(session, csrfToken) {
  return {
    'content-type': 'application/json',
    'origin': 'https://leetcode.com',
    'referer': 'https://leetcode.com',
    'cookie': `csrftoken=${csrfToken}; LEETCODE_SESSION=${session};`,
    'x-csrftoken': csrfToken,
  };
}
