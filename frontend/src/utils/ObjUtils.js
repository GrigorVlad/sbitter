export const objUtils = {
    nvl,
    getValue
}

function nvl(value, defaultValue) {
    if (value) return value;
    return defaultValue;
}

function getValue(obj, value) {
    if (obj && value && obj[value]) return obj[value];
    return null;
}