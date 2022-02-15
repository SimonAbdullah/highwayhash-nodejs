package com.gmail.semon16.highwayhash;

import com.google.highwayhash.HighwayHash;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class HighwayHashWrapper {
    private static long[] KEY = new long[]{0x0706050403020100L, 0x0F0E0D0C0B0A0908L, 0x1716151413121110L, 0x1F1E1D1C1B1A1918L};

    private HighwayHashWrapper() {
    }

    public static void resetKey(byte[] key) throws Exception {
        if(key.length != 32) throw new Exception("Key must be 32-byte length.");

        ByteBuffer keyBuf = ByteBuffer.wrap(key);
        long key0 = keyBuf.getLong();
        long key1 = keyBuf.getLong();
        long key2 = keyBuf.getLong();
        long key3 = keyBuf.getLong();

        KEY = new long[] {key0, key1, key2, key3};
    }

    public static byte[] hash64(byte[] buffer) {
        HighwayHash h = calcHash(buffer);
        return hashToByteArray(new long[] { h.finalize64() });
    }

    public static byte[] hash128(byte[] buffer) {
        HighwayHash h = calcHash(buffer);
        return hashToByteArray(h.finalize128());
    }

    public static byte[] hash256(byte[] buffer) {
        HighwayHash h = calcHash(buffer);
        return hashToByteArray(h.finalize256());
    }

    private static HighwayHash calcHash(byte[] arrayBuf) {
        HighwayHash h = new HighwayHash(KEY);
        int length = arrayBuf.length;
        int offset = 0;
        byte[] data = new byte[32];

        int i;
        for(i = 0; i + 32 <= length; i += 32) {
            h.updatePacket(Arrays.copyOfRange(arrayBuf, offset + i, offset + i + 32), 0);
        }

        if ((length & 31) != 0) {
            data = Arrays.copyOfRange(arrayBuf, offset + i, offset + i + (length & 31));
            h.updateRemainder(data, 0, length & 31);
        }

        return h;
    }

    private static byte[] hashToByteArray(long[] hash){
        ByteBuffer hashBuf = ByteBuffer.allocate(hash.length * 8);
        for (int i = 0; i< hash.length; i++)
            hashBuf.putLong(hash[i]);
        return hashBuf.array();
    }
}
