package com.ihealth.androidbg.audio;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

public final class XXTEA {
    public static ByteBuffer decrypt(ByteBuffer data, ByteBuffer key) {
        byte[] bArr = new byte[(data.limit() - data.position())];
        data.get(bArr);
        return decryptInPlace(ByteBuffer.wrap(bArr), key);
    }

    public static IntBuffer decrypt(IntBuffer data, IntBuffer key) {
        int[] iArr = new int[(data.limit() - data.position())];
        data.get(iArr);
        return decryptInPlace(IntBuffer.wrap(iArr), key);
    }

    public static byte[] decrypt(byte[] data, byte[] key) {
        return decrypt(ByteBuffer.wrap(data), ByteBuffer.wrap(key)).array();
    }

    public static int[] decrypt(int[] data, int[] key) {
        return decrypt(IntBuffer.wrap(data), IntBuffer.wrap(key)).array();
    }

    public static ByteBuffer decryptInPlace(ByteBuffer data, ByteBuffer key) {
        decryptInPlace(data.asIntBuffer(), key.asIntBuffer());
        return data;
    }

    public static IntBuffer decryptInPlace(IntBuffer data, IntBuffer key) {
        if (key.limit() != 4) {
            throw new IllegalArgumentException("XXTEA needs a 128-bits key");
        }
        if (data.limit() >= 2) {
            int i = data.get(0);
            int limit = ((52 / data.limit()) + 6) * -1640531527;
            int limit2 = data.limit();
            do {
                int i2;
                int i3 = (limit >>> 2) & 3;
                int limit3 = data.limit() - 1;
                while (limit3 > 0) {
                    i2 = data.get(limit3 - 1);
                    i = data.get(limit3) - (((i ^ limit) + (i2 ^ key.get((limit3 & 3) ^ i3))) ^ (((i2 >>> 5) ^ (i << 2)) + ((i >>> 3) ^ (i2 << 4))));
                    data.put(limit3, i);
                    limit3--;
                }
                i2 = data.get(limit2 - 1);
                i = data.get(0) - (((i ^ limit) + (key.get((limit3 & 3) ^ i3) ^ i2)) ^ (((i2 >>> 5) ^ (i << 2)) + ((i >>> 3) ^ (i2 << 4))));
                data.put(0, i);
                limit += 1640531527;
            } while (limit != 0);
        }
        return data;
    }

    public static byte[] decryptInPlace(byte[] data, byte[] key) {
        decryptInPlace(ByteBuffer.wrap(data), ByteBuffer.wrap(key));
        return data;
    }

    public static int[] decryptInPlace(int[] data, int[] key) {
        decryptInPlace(IntBuffer.wrap(data), IntBuffer.wrap(key));
        return data;
    }

    public static ByteBuffer encrypt(ByteBuffer data, ByteBuffer key) {
        byte[] bArr = new byte[(data.limit() - data.position())];
        data.get(bArr);
        return encryptInPlace(ByteBuffer.wrap(bArr), key);
    }

    public static IntBuffer encrypt(IntBuffer data, IntBuffer key) {
        int[] iArr = new int[(data.limit() - data.position())];
        data.get(iArr);
        return encryptInPlace(IntBuffer.wrap(iArr), key);
    }

    public static byte[] encrypt(byte[] data, byte[] key) {
        return encrypt(ByteBuffer.wrap(data), ByteBuffer.wrap(key)).array();
    }

    public static int[] encrypt(int[] data, int[] key) {
        return encrypt(IntBuffer.wrap(data), IntBuffer.wrap(key)).array();
    }

    public static ByteBuffer encryptInPlace(ByteBuffer data, ByteBuffer key) {
        encryptInPlace(data.asIntBuffer(), key.asIntBuffer());
        return data;
    }

    public static IntBuffer encryptInPlace(IntBuffer data, IntBuffer key) {
        if (key.limit() != 4) {
            throw new IllegalArgumentException("XXTEA needs a 128-bits key");
        }
        if (data.limit() >= 2) {
            int limit = data.limit();
            int limit2 = (52 / data.limit()) + 6;
            int i = data.get(limit - 1);
            int i2 = limit2;
            limit2 = 0;
            do {
                int i3;
                limit2 -= 1640531527;
                int i4 = (limit2 >>> 2) & 3;
                int i5 = 0;
                while (i5 < limit - 1) {
                    i3 = data.get(i5 + 1);
                    i = (((i ^ key.get((i5 & 3) ^ i4)) + (i3 ^ limit2)) ^ (((i >>> 5) ^ (i3 << 2)) + ((i3 >>> 3) ^ (i << 4)))) + data.get(i5);
                    data.put(i5, i);
                    i5++;
                }
                i3 = data.get(0);
                i = (((i ^ key.get(i4 ^ (i5 & 3))) + (i3 ^ limit2)) ^ (((i >>> 5) ^ (i3 << 2)) + ((i3 >>> 3) ^ (i << 4)))) + data.get(limit - 1);
                data.put(i5, i);
                i2--;
            } while (i2 > 0);
        }
        return data;
    }

    public static byte[] encryptInPlace(byte[] data, byte[] key) {
        encryptInPlace(ByteBuffer.wrap(data), ByteBuffer.wrap(key));
        return data;
    }

    public static int[] encryptInPlace(int[] data, int[] key) {
        encryptInPlace(IntBuffer.wrap(data), IntBuffer.wrap(key));
        return data;
    }

    public boolean BytesEquals(byte[] src, byte[] dst, int len) {
        for (int i = 0; i < len; i++) {
            if (src[i] != dst[i]) {
                return false;
            }
        }
        return true;
    }
}
