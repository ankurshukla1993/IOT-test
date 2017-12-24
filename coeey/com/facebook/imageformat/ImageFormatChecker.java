package com.facebook.imageformat;

import com.facebook.common.internal.ByteStreams;
import com.facebook.common.internal.Closeables;
import com.facebook.common.internal.Ints;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.Throwables;
import com.facebook.common.webp.WebpSupportStatus;
import com.google.common.base.Ascii;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

public class ImageFormatChecker {
    private static final byte[] BMP_HEADER = asciiBytes("BM");
    private static final int EXTENDED_WEBP_HEADER_LENGTH = 21;
    private static final byte[] GIF_HEADER_87A = asciiBytes("GIF87a");
    private static final byte[] GIF_HEADER_89A = asciiBytes("GIF89a");
    private static final int GIF_HEADER_LENGTH = 6;
    private static final byte[] JPEG_HEADER = new byte[]{(byte) -1, (byte) -40, (byte) -1};
    private static final int MAX_HEADER_LENGTH = Ints.max(21, 20, JPEG_HEADER.length, PNG_HEADER.length, 6, BMP_HEADER.length);
    private static final byte[] PNG_HEADER = new byte[]{(byte) -119, (byte) 80, (byte) 78, (byte) 71, (byte) 13, (byte) 10, Ascii.SUB, (byte) 10};
    private static final int SIMPLE_WEBP_HEADER_LENGTH = 20;

    private ImageFormatChecker() {
    }

    private static ImageFormat doGetImageFormat(byte[] imageHeaderBytes, int headerSize) {
        Preconditions.checkNotNull(imageHeaderBytes);
        if (WebpSupportStatus.isWebpHeader(imageHeaderBytes, 0, headerSize)) {
            return getWebpFormat(imageHeaderBytes, headerSize);
        }
        if (isJpegHeader(imageHeaderBytes, headerSize)) {
            return ImageFormat.JPEG;
        }
        if (isPngHeader(imageHeaderBytes, headerSize)) {
            return ImageFormat.PNG;
        }
        if (isGifHeader(imageHeaderBytes, headerSize)) {
            return ImageFormat.GIF;
        }
        if (isBmpHeader(imageHeaderBytes, headerSize)) {
            return ImageFormat.BMP;
        }
        return ImageFormat.UNKNOWN;
    }

    private static int readHeaderFromStream(InputStream is, byte[] imageHeaderBytes) throws IOException {
        Preconditions.checkNotNull(is);
        Preconditions.checkNotNull(imageHeaderBytes);
        Preconditions.checkArgument(imageHeaderBytes.length >= MAX_HEADER_LENGTH);
        if (!is.markSupported()) {
            return ByteStreams.read(is, imageHeaderBytes, 0, MAX_HEADER_LENGTH);
        }
        try {
            is.mark(MAX_HEADER_LENGTH);
            int read = ByteStreams.read(is, imageHeaderBytes, 0, MAX_HEADER_LENGTH);
            return read;
        } finally {
            is.reset();
        }
    }

    public static ImageFormat getImageFormat(InputStream is) throws IOException {
        Preconditions.checkNotNull(is);
        byte[] imageHeaderBytes = new byte[MAX_HEADER_LENGTH];
        return doGetImageFormat(imageHeaderBytes, readHeaderFromStream(is, imageHeaderBytes));
    }

    public static ImageFormat getImageFormat_WrapIOException(InputStream is) {
        try {
            return getImageFormat(is);
        } catch (IOException ioe) {
            throw Throwables.propagate(ioe);
        }
    }

    public static ImageFormat getImageFormat(String filename) {
        ImageFormat imageFormat;
        Throwable th;
        InputStream fileInputStream = null;
        try {
            InputStream fileInputStream2 = new FileInputStream(filename);
            try {
                imageFormat = getImageFormat(fileInputStream2);
                Closeables.closeQuietly(fileInputStream2);
                fileInputStream = fileInputStream2;
            } catch (IOException e) {
                fileInputStream = fileInputStream2;
                try {
                    imageFormat = ImageFormat.UNKNOWN;
                    Closeables.closeQuietly(fileInputStream);
                    return imageFormat;
                } catch (Throwable th2) {
                    th = th2;
                    Closeables.closeQuietly(fileInputStream);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                fileInputStream = fileInputStream2;
                Closeables.closeQuietly(fileInputStream);
                throw th;
            }
        } catch (IOException e2) {
            imageFormat = ImageFormat.UNKNOWN;
            Closeables.closeQuietly(fileInputStream);
            return imageFormat;
        }
        return imageFormat;
    }

    private static boolean matchBytePattern(byte[] byteArray, int offset, byte[] pattern) {
        Preconditions.checkNotNull(byteArray);
        Preconditions.checkNotNull(pattern);
        Preconditions.checkArgument(offset >= 0);
        if (pattern.length + offset > byteArray.length) {
            return false;
        }
        for (int i = 0; i < pattern.length; i++) {
            if (byteArray[i + offset] != pattern[i]) {
                return false;
            }
        }
        return true;
    }

    private static byte[] asciiBytes(String value) {
        Preconditions.checkNotNull(value);
        try {
            return value.getBytes("ASCII");
        } catch (UnsupportedEncodingException uee) {
            throw new RuntimeException("ASCII not found!", uee);
        }
    }

    private static ImageFormat getWebpFormat(byte[] imageHeaderBytes, int headerSize) {
        Preconditions.checkArgument(WebpSupportStatus.isWebpHeader(imageHeaderBytes, 0, headerSize));
        if (WebpSupportStatus.isSimpleWebpHeader(imageHeaderBytes, 0)) {
            return ImageFormat.WEBP_SIMPLE;
        }
        if (WebpSupportStatus.isLosslessWebpHeader(imageHeaderBytes, 0)) {
            return ImageFormat.WEBP_LOSSLESS;
        }
        if (!WebpSupportStatus.isExtendedWebpHeader(imageHeaderBytes, 0, headerSize)) {
            return ImageFormat.UNKNOWN;
        }
        if (WebpSupportStatus.isAnimatedWebpHeader(imageHeaderBytes, 0)) {
            return ImageFormat.WEBP_ANIMATED;
        }
        if (WebpSupportStatus.isExtendedWebpHeaderWithAlpha(imageHeaderBytes, 0)) {
            return ImageFormat.WEBP_EXTENDED_WITH_ALPHA;
        }
        return ImageFormat.WEBP_EXTENDED;
    }

    private static boolean isJpegHeader(byte[] imageHeaderBytes, int headerSize) {
        return headerSize >= JPEG_HEADER.length && matchBytePattern(imageHeaderBytes, 0, JPEG_HEADER);
    }

    private static boolean isPngHeader(byte[] imageHeaderBytes, int headerSize) {
        return headerSize >= PNG_HEADER.length && matchBytePattern(imageHeaderBytes, 0, PNG_HEADER);
    }

    private static boolean isGifHeader(byte[] imageHeaderBytes, int headerSize) {
        if (headerSize < 6) {
            return false;
        }
        if (matchBytePattern(imageHeaderBytes, 0, GIF_HEADER_87A) || matchBytePattern(imageHeaderBytes, 0, GIF_HEADER_89A)) {
            return true;
        }
        return false;
    }

    private static boolean isBmpHeader(byte[] imageHeaderBytes, int headerSize) {
        if (headerSize < BMP_HEADER.length) {
            return false;
        }
        return matchBytePattern(imageHeaderBytes, 0, BMP_HEADER);
    }
}
