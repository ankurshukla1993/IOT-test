package com.facebook.imageutils;

import java.io.IOException;
import java.io.InputStream;

class StreamProcessor {
    StreamProcessor() {
    }

    public static int readPackedInt(InputStream is, int numBytes, boolean isLittleEndian) throws IOException {
        int value = 0;
        for (int i = 0; i < numBytes; i++) {
            int b = is.read();
            if (b == -1) {
                throw new IOException("no more bytes");
            }
            if (isLittleEndian) {
                value |= (b & 255) << (i * 8);
            } else {
                value = (value << 8) | (b & 255);
            }
        }
        return value;
    }
}
