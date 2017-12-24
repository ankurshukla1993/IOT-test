package com.facebook.common.webp;

import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.os.Build.VERSION;
import android.util.Base64;
import java.io.UnsupportedEncodingException;

public class WebpSupportStatus {
    private static final int EXTENDED_WEBP_HEADER_LENGTH = 21;
    private static final int SIMPLE_WEBP_HEADER_LENGTH = 20;
    private static final String VP8X_WEBP_BASE64 = "UklGRkoAAABXRUJQVlA4WAoAAAAQAAAAAAAAAAAAQUxQSAwAAAARBxAR/Q9ERP8DAABWUDggGAAAABQBAJ0BKgEAAQAAAP4AAA3AAP7mtQAAAA==";
    private static final byte[] WEBP_NAME_BYTES = asciiBytes("WEBP");
    private static final byte[] WEBP_RIFF_BYTES = asciiBytes("RIFF");
    private static final byte[] WEBP_VP8L_BYTES = asciiBytes("VP8L");
    private static final byte[] WEBP_VP8X_BYTES = asciiBytes("VP8X");
    private static final byte[] WEBP_VP8_BYTES = asciiBytes("VP8 ");
    public static final boolean sIsExtendedWebpSupported = isExtendedWebpSupported();
    public static final boolean sIsSimpleWebpSupported;
    public static final boolean sIsWebpSupportRequired;
    public static WebpBitmapFactory sWebpBitmapFactory;
    public static boolean sWebpLibraryPresent;

    static {
        boolean z;
        boolean z2 = true;
        if (VERSION.SDK_INT <= 17) {
            z = true;
        } else {
            z = false;
        }
        sIsWebpSupportRequired = z;
        if (VERSION.SDK_INT < 14) {
            z2 = false;
        }
        sIsSimpleWebpSupported = z2;
        sWebpBitmapFactory = null;
        sWebpLibraryPresent = false;
        try {
            sWebpBitmapFactory = (WebpBitmapFactory) Class.forName("com.facebook.webpsupport.WebpBitmapFactoryImpl").newInstance();
            sWebpLibraryPresent = true;
        } catch (Throwable th) {
            sWebpLibraryPresent = false;
        }
    }

    private static byte[] asciiBytes(String value) {
        try {
            return value.getBytes("ASCII");
        } catch (UnsupportedEncodingException uee) {
            throw new RuntimeException("ASCII not found!", uee);
        }
    }

    private static boolean isExtendedWebpSupported() {
        if (VERSION.SDK_INT < 17) {
            return false;
        }
        if (VERSION.SDK_INT == 17) {
            byte[] decodedBytes = Base64.decode(VP8X_WEBP_BASE64, 0);
            Options opts = new Options();
            opts.inJustDecodeBounds = true;
            BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length, opts);
            if (!(opts.outHeight == 1 && opts.outWidth == 1)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isWebpPlatformSupported(byte[] imageHeaderBytes, int offset, int headerSize) {
        if (isSimpleWebpHeader(imageHeaderBytes, offset)) {
            return sIsSimpleWebpSupported;
        }
        if (isLosslessWebpHeader(imageHeaderBytes, offset)) {
            return sIsExtendedWebpSupported;
        }
        if (!isExtendedWebpHeader(imageHeaderBytes, offset, headerSize) || isAnimatedWebpHeader(imageHeaderBytes, offset)) {
            return false;
        }
        return sIsExtendedWebpSupported;
    }

    public static boolean isAnimatedWebpHeader(byte[] imageHeaderBytes, int offset) {
        boolean isVp8x = matchBytePattern(imageHeaderBytes, offset + 12, WEBP_VP8X_BYTES);
        boolean hasAnimationBit;
        if ((imageHeaderBytes[offset + 20] & 2) == 2) {
            hasAnimationBit = true;
        } else {
            hasAnimationBit = false;
        }
        if (isVp8x && hasAnimationBit) {
            return true;
        }
        return false;
    }

    public static boolean isSimpleWebpHeader(byte[] imageHeaderBytes, int offset) {
        return matchBytePattern(imageHeaderBytes, offset + 12, WEBP_VP8_BYTES);
    }

    public static boolean isLosslessWebpHeader(byte[] imageHeaderBytes, int offset) {
        return matchBytePattern(imageHeaderBytes, offset + 12, WEBP_VP8L_BYTES);
    }

    public static boolean isExtendedWebpHeader(byte[] imageHeaderBytes, int offset, int headerSize) {
        return headerSize >= 21 && matchBytePattern(imageHeaderBytes, offset + 12, WEBP_VP8X_BYTES);
    }

    public static boolean isExtendedWebpHeaderWithAlpha(byte[] imageHeaderBytes, int offset) {
        boolean isVp8x = matchBytePattern(imageHeaderBytes, offset + 12, WEBP_VP8X_BYTES);
        boolean hasAlphaBit;
        if ((imageHeaderBytes[offset + 20] & 16) == 16) {
            hasAlphaBit = true;
        } else {
            hasAlphaBit = false;
        }
        if (isVp8x && hasAlphaBit) {
            return true;
        }
        return false;
    }

    public static boolean isWebpHeader(byte[] imageHeaderBytes, int offset, int headerSize) {
        return headerSize >= 20 && matchBytePattern(imageHeaderBytes, offset, WEBP_RIFF_BYTES) && matchBytePattern(imageHeaderBytes, offset + 8, WEBP_NAME_BYTES);
    }

    private static boolean matchBytePattern(byte[] byteArray, int offset, byte[] pattern) {
        if (pattern == null || byteArray == null || pattern.length + offset > byteArray.length) {
            return false;
        }
        for (int i = 0; i < pattern.length; i++) {
            if (byteArray[i + offset] != pattern[i]) {
                return false;
            }
        }
        return true;
    }
}
