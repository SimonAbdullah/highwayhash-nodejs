package com.gmail.semon16.highwayhash;

public class Main {
    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }

    public static void main(String[] args) {
        String dataStr = "000102030405060708090a0b0c0d0e0f101112131415161718191a1b1c1d1e1f20";
        byte[] dataBytes = hexStringToByteArray(dataStr);
        HighwayHashWrapper.hash64(dataBytes);
        System.out.println("Done");
    }
}
