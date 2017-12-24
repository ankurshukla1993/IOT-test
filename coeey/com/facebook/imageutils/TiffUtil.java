package com.facebook.imageutils;

import com.facebook.common.logging.FLog;
import java.io.IOException;
import java.io.InputStream;

class TiffUtil {
    private static final Class<?> TAG = TiffUtil.class;
    public static final int TIFF_BYTE_ORDER_BIG_END = 1296891946;
    public static final int TIFF_BYTE_ORDER_LITTLE_END = 1229531648;
    public static final int TIFF_TAG_ORIENTATION = 274;
    public static final int TIFF_TYPE_SHORT = 3;

    private static class TiffHeader {
        int byteOrder;
        int firstIfdOffset;
        boolean isLittleEndian;

        private TiffHeader() {
        }
    }

    TiffUtil() {
    }

    public static int getAutoRotateAngleFromOrientation(int orientation) {
        switch (orientation) {
            case 1:
                return 0;
            case 3:
                return 180;
            case 6:
                return 90;
            case 8:
                return 270;
            default:
                FLog.m115i(TAG, "Unsupported orientation");
                return 0;
        }
    }

    public static int readOrientationFromTIFF(InputStream is, int length) throws IOException {
        TiffHeader tiffHeader = new TiffHeader();
        length = readTiffHeader(is, length, tiffHeader);
        int toSkip = tiffHeader.firstIfdOffset - 8;
        if (length == 0 || toSkip > length) {
            return 0;
        }
        is.skip((long) toSkip);
        return getOrientationFromTiffEntry(is, moveToTiffEntryWithTag(is, length - toSkip, tiffHeader.isLittleEndian, TIFF_TAG_ORIENTATION), tiffHeader.isLittleEndian);
    }

    private static int readTiffHeader(InputStream is, int length, TiffHeader tiffHeader) throws IOException {
        if (length <= 8) {
            return 0;
        }
        tiffHeader.byteOrder = StreamProcessor.readPackedInt(is, 4, false);
        length -= 4;
        if (tiffHeader.byteOrder == TIFF_BYTE_ORDER_LITTLE_END || tiffHeader.byteOrder == TIFF_BYTE_ORDER_BIG_END) {
            tiffHeader.isLittleEndian = tiffHeader.byteOrder == TIFF_BYTE_ORDER_LITTLE_END;
            tiffHeader.firstIfdOffset = StreamProcessor.readPackedInt(is, 4, tiffHeader.isLittleEndian);
            length -= 4;
            if (tiffHeader.firstIfdOffset >= 8 && tiffHeader.firstIfdOffset - 8 <= length) {
                return length;
            }
            FLog.m107e(TAG, "Invalid offset");
            return 0;
        }
        FLog.m107e(TAG, "Invalid TIFF header");
        return 0;
    }

    private static int moveToTiffEntryWithTag(InputStream is, int length, boolean isLittleEndian, int tagToFind) throws IOException {
        if (length < 14) {
            return 0;
        }
        length -= 2;
        int numEntries = StreamProcessor.readPackedInt(is, 2, isLittleEndian);
        while (true) {
            int numEntries2 = numEntries - 1;
            if (numEntries <= 0 || length < 12) {
                return 0;
            }
            length -= 2;
            if (StreamProcessor.readPackedInt(is, 2, isLittleEndian) == tagToFind) {
                return length;
            }
            is.skip(10);
            length -= 10;
            numEntries = numEntries2;
        }
    }

    private static int getOrientationFromTiffEntry(InputStream is, int length, boolean isLittleEndian) throws IOException {
        if (length < 10 || StreamProcessor.readPackedInt(is, 2, isLittleEndian) != 3 || StreamProcessor.readPackedInt(is, 4, isLittleEndian) != 1) {
            return 0;
        }
        int value = StreamProcessor.readPackedInt(is, 2, isLittleEndian);
        int padding = StreamProcessor.readPackedInt(is, 2, isLittleEndian);
        return value;
    }
}
