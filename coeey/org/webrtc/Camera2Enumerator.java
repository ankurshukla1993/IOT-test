package org.webrtc;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.os.Build.VERSION;
import android.os.SystemClock;
import android.util.AndroidException;
import android.util.Range;
import android.util.Size;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.webrtc.CameraEnumerationAndroid.CaptureFormat;
import org.webrtc.CameraEnumerationAndroid.CaptureFormat.FramerateRange;
import org.webrtc.CameraVideoCapturer.CameraEventsHandler;

@TargetApi(21)
public class Camera2Enumerator implements CameraEnumerator {
    private static final double NANO_SECONDS_PER_SECOND = 1.0E9d;
    private static final String TAG = "Camera2Enumerator";
    private static final Map<String, List<CaptureFormat>> cachedSupportedFormats = new HashMap();
    final CameraManager cameraManager;
    final Context context;

    public Camera2Enumerator(Context context) {
        this.context = context;
        this.cameraManager = (CameraManager) context.getSystemService("camera");
    }

    public String[] getDeviceNames() {
        try {
            return this.cameraManager.getCameraIdList();
        } catch (AndroidException e) {
            Logging.m2188e(TAG, "Camera access exception: " + e);
            return new String[0];
        }
    }

    public boolean isFrontFacing(String deviceName) {
        CameraCharacteristics characteristics = getCameraCharacteristics(deviceName);
        return characteristics != null && ((Integer) characteristics.get(CameraCharacteristics.LENS_FACING)).intValue() == 0;
    }

    public boolean isBackFacing(String deviceName) {
        CameraCharacteristics characteristics = getCameraCharacteristics(deviceName);
        return characteristics != null && ((Integer) characteristics.get(CameraCharacteristics.LENS_FACING)).intValue() == 1;
    }

    public List<CaptureFormat> getSupportedFormats(String deviceName) {
        return getSupportedFormats(this.context, deviceName);
    }

    public CameraVideoCapturer createCapturer(String deviceName, CameraEventsHandler eventsHandler) {
        return new Camera2Capturer(this.context, deviceName, eventsHandler);
    }

    private CameraCharacteristics getCameraCharacteristics(String deviceName) {
        try {
            return this.cameraManager.getCameraCharacteristics(deviceName);
        } catch (AndroidException e) {
            Logging.m2188e(TAG, "Camera access exception: " + e);
            return null;
        }
    }

    public static boolean isSupported(Context context) {
        if (VERSION.SDK_INT < 21) {
            return false;
        }
        CameraManager cameraManager = (CameraManager) context.getSystemService("camera");
        try {
            for (String id : cameraManager.getCameraIdList()) {
                if (((Integer) cameraManager.getCameraCharacteristics(id).get(CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL)).intValue() == 2) {
                    return false;
                }
            }
            return true;
        } catch (AndroidException e) {
            Logging.m2188e(TAG, "Camera access exception: " + e);
            return false;
        }
    }

    static int getFpsUnitFactor(Range<Integer>[] fpsRanges) {
        if (fpsRanges.length == 0) {
            return 1000;
        }
        return ((Integer) fpsRanges[0].getUpper()).intValue() < 1000 ? 1000 : 1;
    }

    static List<Size> getSupportedSizes(CameraCharacteristics cameraCharacteristics) {
        StreamConfigurationMap streamMap = (StreamConfigurationMap) cameraCharacteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
        int supportLevel = ((Integer) cameraCharacteristics.get(CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL)).intValue();
        List<Size> sizes = convertSizes(streamMap.getOutputSizes(SurfaceTexture.class));
        if (VERSION.SDK_INT >= 22 || supportLevel != 2) {
            return sizes;
        }
        Rect activeArraySize = (Rect) cameraCharacteristics.get(CameraCharacteristics.SENSOR_INFO_ACTIVE_ARRAY_SIZE);
        List<Size> arrayList = new ArrayList();
        for (Size size : sizes) {
            if (activeArraySize.width() * size.height == activeArraySize.height() * size.width) {
                arrayList.add(size);
            }
        }
        return arrayList;
    }

    static List<CaptureFormat> getSupportedFormats(Context context, String cameraId) {
        return getSupportedFormats((CameraManager) context.getSystemService("camera"), cameraId);
    }

    static List<CaptureFormat> getSupportedFormats(CameraManager cameraManager, String cameraId) {
        synchronized (cachedSupportedFormats) {
            if (cachedSupportedFormats.containsKey(cameraId)) {
                List<CaptureFormat> list = (List) cachedSupportedFormats.get(cameraId);
                return list;
            }
            Logging.m2187d(TAG, "Get supported formats for camera index " + cameraId + ".");
            long startTimeMs = SystemClock.elapsedRealtime();
            try {
                CameraCharacteristics cameraCharacteristics = cameraManager.getCameraCharacteristics(cameraId);
                StreamConfigurationMap streamMap = (StreamConfigurationMap) cameraCharacteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
                Range[] fpsRanges = (Range[]) cameraCharacteristics.get(CameraCharacteristics.CONTROL_AE_AVAILABLE_TARGET_FPS_RANGES);
                List<FramerateRange> framerateRanges = convertFramerates(fpsRanges, getFpsUnitFactor(fpsRanges));
                List<Size> sizes = getSupportedSizes(cameraCharacteristics);
                int defaultMaxFps = 0;
                for (FramerateRange framerateRange : framerateRanges) {
                    defaultMaxFps = Math.max(defaultMaxFps, framerateRange.max);
                }
                List<CaptureFormat> formatList = new ArrayList();
                for (Size size : sizes) {
                    int maxFps;
                    long minFrameDurationNs = 0;
                    try {
                        minFrameDurationNs = streamMap.getOutputMinFrameDuration(SurfaceTexture.class, new Size(size.width, size.height));
                    } catch (Exception e) {
                    }
                    if (minFrameDurationNs == 0) {
                        maxFps = defaultMaxFps;
                    } else {
                        maxFps = ((int) Math.round(NANO_SECONDS_PER_SECOND / ((double) minFrameDurationNs))) * 1000;
                    }
                    formatList.add(new CaptureFormat(size.width, size.height, 0, maxFps));
                    Logging.m2187d(TAG, "Format: " + size.width + "x" + size.height + "@" + maxFps);
                }
                cachedSupportedFormats.put(cameraId, formatList);
                long endTimeMs = SystemClock.elapsedRealtime();
                Logging.m2187d(TAG, "Get supported formats for camera index " + cameraId + " done. Time spent: " + (endTimeMs - startTimeMs) + " ms.");
                return formatList;
            } catch (Exception ex) {
                Logging.m2188e(TAG, "getCameraCharacteristics(): " + ex);
                return new ArrayList();
            }
        }
    }

    private static List<Size> convertSizes(Size[] cameraSizes) {
        List<Size> sizes = new ArrayList();
        for (Size size : cameraSizes) {
            sizes.add(new Size(size.getWidth(), size.getHeight()));
        }
        return sizes;
    }

    static List<FramerateRange> convertFramerates(Range<Integer>[] arrayRanges, int unitFactor) {
        List<FramerateRange> ranges = new ArrayList();
        for (Range<Integer> range : arrayRanges) {
            ranges.add(new FramerateRange(((Integer) range.getLower()).intValue() * unitFactor, ((Integer) range.getUpper()).intValue() * unitFactor));
        }
        return ranges;
    }
}
