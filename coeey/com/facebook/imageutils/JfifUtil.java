package com.facebook.imageutils;

import com.facebook.common.internal.Preconditions;
import com.ihealth.communication.manager.iHealthDevicesManager.ScanDevice;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class JfifUtil {
    public static final int APP1_EXIF_MAGIC = 1165519206;
    public static final int MARKER_APP1 = 225;
    public static final int MARKER_EOI = 217;
    public static final int MARKER_ESCAPE_BYTE = 0;
    public static final int MARKER_FIRST_BYTE = 255;
    public static final int MARKER_RST0 = 208;
    public static final int MARKER_RST7 = 215;
    public static final int MARKER_SOFn = 192;
    public static final int MARKER_SOI = 216;
    public static final int MARKER_SOS = 218;
    public static final int MARKER_TEM = 1;

    private JfifUtil() {
    }

    public static int getAutoRotateAngleFromOrientation(int orientation) {
        return TiffUtil.getAutoRotateAngleFromOrientation(orientation);
    }

    public static int getOrientation(byte[] jpeg) {
        return getOrientation(new ByteArrayInputStream(jpeg));
    }

    public static int getOrientation(InputStream is) {
        int i = 0;
        try {
            int length = moveToAPP1EXIF(is);
            if (length != 0) {
                i = TiffUtil.readOrientationFromTIFF(is, length);
            }
        } catch (IOException e) {
        }
        return i;
    }

    public static boolean moveToMarker(InputStream is, int markerToFind) throws IOException {
        Preconditions.checkNotNull(is);
        while (StreamProcessor.readPackedInt(is, 1, false) == 255) {
            int marker = 255;
            while (marker == 255) {
                marker = StreamProcessor.readPackedInt(is, 1, false);
            }
            if ((markerToFind == MARKER_SOFn && isSOFn(marker)) || marker == markerToFind) {
                return true;
            }
            if (!(marker == MARKER_SOI || marker == 1)) {
                if (marker == MARKER_EOI || marker == MARKER_SOS) {
                    return false;
                }
                is.skip((long) (StreamProcessor.readPackedInt(is, 2, false) - 2));
            }
        }
        return false;
    }

    private static boolean isSOFn(int marker) {
        switch (marker) {
            case MARKER_SOFn /*192*/:
            case 193:
            case 194:
            case 195:
            case 197:
            case 198:
            case 199:
            case 201:
            case 202:
            case ScanDevice.LINK_WIFI /*203*/:
            case ScanDevice.LINK_AU /*205*/:
            case 206:
            case 207:
                return true;
            default:
                return false;
        }
    }

    private static int moveToAPP1EXIF(InputStream is) throws IOException {
        if (moveToMarker(is, MARKER_APP1)) {
            int length = StreamProcessor.readPackedInt(is, 2, false) - 2;
            if (length > 6) {
                int magic = StreamProcessor.readPackedInt(is, 4, false);
                length -= 4;
                int zero = StreamProcessor.readPackedInt(is, 2, false);
                length -= 2;
                if (magic == APP1_EXIF_MAGIC && zero == 0) {
                    return length;
                }
            }
        }
        return 0;
    }
}
