package com.facebook.stetho.websocket;

class MaskingHelper {
    MaskingHelper() {
    }

    public static void unmask(byte[] key, byte[] data, int offset, int count) {
        int index = 0;
        int count2 = count;
        int offset2 = offset;
        while (true) {
            count = count2 - 1;
            if (count2 > 0) {
                offset = offset2 + 1;
                int index2 = index + 1;
                data[offset2] = (byte) (data[offset2] ^ key[index % key.length]);
                index = index2;
                count2 = count;
                offset2 = offset;
            } else {
                return;
            }
        }
    }
}
