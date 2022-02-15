
const java = require("java");
const path = require("path");

const jarPath = process.env.HIGHWAY_HASH_WRAPPER_JAR_PATH || path.join(process.cwd(), "java", "HighwayHashWrapper", "out", "artifacts", "HighwayHashWrapper", "HighwayHashWrapper.jar");
java.classpath.push(jarPath);

const resetKey = (key) => {
  const keyByteArray = java.newArray(
    "byte",
    [...key].map((b) => {
      return java.newByte(b);
    })
  );

  java.callStaticMethodSync(
    "com.gmail.semon16.highwayhash.HighwayHashWrapper",
    "resetKey",
    keyByteArray
  );
};

const hash64 = (buf) => {
  const bufByteArray = java.newArray(
    "byte",
    [...buf].map((b) => {
      return java.newByte(b);
    })
  );

  const hash = java.callStaticMethodSync(
    "com.gmail.semon16.highwayhash.HighwayHashWrapper",
    "hash64",
    bufByteArray
  );

  return hash;
};

const hash128 = (buf) => {
  const bufByteArray = java.newArray(
    "byte",
    [...buf].map((b) => {
      return java.newByte(b);
    })
  );

  const hash = java.callStaticMethodSync(
    "com.gmail.semon16.highwayhash.HighwayHashWrapper",
    "hash128",
    bufByteArray
  );

  return hash;
};

const hash256 = (buf) => {
  const bufByteArray = java.newArray(
    "byte",
    [...buf].map((b) => {
      return java.newByte(b);
    })
  );

  const hash = java.callStaticMethodSync(
    "com.gmail.semon16.highwayhash.HighwayHashWrapper",
    "hash256",
    bufByteArray
  );

  return hash;
};

module.exports = { resetKey, hash64, hash128, hash256 }
