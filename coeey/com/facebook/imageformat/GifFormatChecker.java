package com.facebook.imageformat;

import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.VisibleForTesting;
import java.io.IOException;
import java.io.InputStream;

public final class GifFormatChecker {
    private static final byte[] FRAME_HEADER_END_1 = new byte[]{(byte) 0, (byte) 44};
    private static final byte[] FRAME_HEADER_END_2 = new byte[]{(byte) 0, (byte) 33};
    private static final int FRAME_HEADER_SIZE = 10;
    private static final byte[] FRAME_HEADER_START = new byte[]{(byte) 0, (byte) 33, (byte) -7, (byte) 4};

    private GifFormatChecker() {
    }

    public static boolean isAnimated(InputStream source) {
        byte[] buffer = new byte[10];
        try {
            source.read(buffer, 0, 10);
            int offset = 0;
            int frameHeaders = 0;
            while (source.read(buffer, offset, 1) > 0) {
                if (circularBufferMatchesBytePattern(buffer, offset + 1, FRAME_HEADER_START) && (circularBufferMatchesBytePattern(buffer, offset + 9, FRAME_HEADER_END_1) || circularBufferMatchesBytePattern(buffer, offset + 9, FRAME_HEADER_END_2))) {
                    frameHeaders++;
                    if (frameHeaders > 1) {
                        return true;
                    }
                }
                offset = (offset + 1) % buffer.length;
            }
            return false;
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }

    @VisibleForTesting
    static boolean circularBufferMatchesBytePattern(byte[] byteArray, int offset, byte[] pattern) {
        Preconditions.checkNotNull(byteArray);
        Preconditions.checkNotNull(pattern);
        Preconditions.checkArgument(offset >= 0);
        if (pattern.length > byteArray.length) {
            return false;
        }
        for (int i = 0; i < pattern.length; i++) {
            if (byteArray[(i + offset) % byteArray.length] != pattern[i]) {
                return false;
            }
        }
        return true;
    }
}
