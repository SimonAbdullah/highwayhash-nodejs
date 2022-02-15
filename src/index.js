const dotenv = require("dotenv");
dotenv.config();

const { resetKey, hash64, hash128, hash256 } = require("./highway-hash");

module.exports.resetKey = (key) => resetKey(key);
module.exports.hash64 = (buf) => hash64(buf);
module.exports.hash128 = (buf) => hash128(buf);
module.exports.hash256 = (buf) => hash256(buf);