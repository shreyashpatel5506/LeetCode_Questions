var compactObject = function(obj) {
    if (Array.isArray(obj))
        return obj.map(compactObject).filter(Boolean);

    if (typeof obj === 'object' && obj !== null) {
        const res = {};
        for (const [k, v] of Object.entries(obj)) {
            const compact = compactObject(v);
            if (compact) res[k] = compact;
        }
        return res;
    }

    return obj; // primitive — caller checks truthiness
};