package org.webrtc;

import android.annotation.TargetApi;
import android.media.MediaCodec;
import android.media.MediaCodec.BufferInfo;
import android.media.MediaCodecInfo;
import android.media.MediaCodecInfo.CodecCapabilities;
import android.media.MediaCodecList;
import android.media.MediaFormat;
import android.opengl.GLES20;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.view.Surface;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.webrtc.EglBase14.Context;

@TargetApi(19)
public class MediaCodecVideoEncoder {
    private static final int BITRATE_ADJUSTMENT_FPS = 30;
    private static final double BITRATE_CORRECTION_MAX_SCALE = 4.0d;
    private static final double BITRATE_CORRECTION_SEC = 3.0d;
    private static final int BITRATE_CORRECTION_STEPS = 20;
    private static final int COLOR_QCOM_FORMATYUV420PackedSemiPlanar32m = 2141391876;
    private static final int DEQUEUE_TIMEOUT = 0;
    private static final String[] H264_HW_EXCEPTION_MODELS = new String[]{"SAMSUNG-SGH-I337", "Nexus 7", "Nexus 4"};
    private static final String H264_MIME_TYPE = "video/avc";
    private static final int MAXIMUM_INITIAL_FPS = 30;
    private static final int MEDIA_CODEC_RELEASE_TIMEOUT_MS = 5000;
    private static final long QCOM_VP8_KEY_FRAME_INTERVAL_ANDROID_M_MS = 25000;
    private static final long QCOM_VP8_KEY_FRAME_INTERVAL_ANDROID_N_MS = 15000;
    private static final String TAG = "MediaCodecVideoEncoder";
    private static final int VIDEO_ControlRateConstant = 2;
    private static final String VP8_MIME_TYPE = "video/x-vnd.on2.vp8";
    private static final String VP9_MIME_TYPE = "video/x-vnd.on2.vp9";
    private static int codecErrors = 0;
    private static MediaCodecVideoEncoderErrorCallback errorCallback = null;
    private static final MediaCodecProperties exynosH264HwProperties = new MediaCodecProperties("OMX.Exynos.", 21, BitrateAdjustmentType.FRAMERATE_ADJUSTMENT);
    private static final MediaCodecProperties exynosVp8HwProperties = new MediaCodecProperties("OMX.Exynos.", 23, BitrateAdjustmentType.DYNAMIC_ADJUSTMENT);
    private static final MediaCodecProperties exynosVp9HwProperties = new MediaCodecProperties("OMX.Exynos.", 23, BitrateAdjustmentType.NO_ADJUSTMENT);
    private static final MediaCodecProperties[] h264HwList = new MediaCodecProperties[]{qcomH264HwProperties, exynosH264HwProperties};
    private static Set<String> hwEncoderDisabledTypes = new HashSet();
    private static final MediaCodecProperties intelVp8HwProperties = new MediaCodecProperties("OMX.Intel.", 21, BitrateAdjustmentType.NO_ADJUSTMENT);
    private static final MediaCodecProperties qcomH264HwProperties = new MediaCodecProperties("OMX.qcom.", 19, BitrateAdjustmentType.NO_ADJUSTMENT);
    private static final MediaCodecProperties qcomVp8HwProperties = new MediaCodecProperties("OMX.qcom.", 19, BitrateAdjustmentType.NO_ADJUSTMENT);
    private static final MediaCodecProperties qcomVp9HwProperties = new MediaCodecProperties("OMX.qcom.", 23, BitrateAdjustmentType.NO_ADJUSTMENT);
    private static MediaCodecVideoEncoder runningInstance = null;
    private static final int[] supportedColorList = new int[]{19, 21, 2141391872, COLOR_QCOM_FORMATYUV420PackedSemiPlanar32m};
    private static final int[] supportedSurfaceColorList = new int[]{2130708361};
    private static final MediaCodecProperties[] vp9HwList = new MediaCodecProperties[]{qcomVp9HwProperties, exynosVp9HwProperties};
    private double bitrateAccumulator;
    private double bitrateAccumulatorMax;
    private int bitrateAdjustmentScaleExp;
    private BitrateAdjustmentType bitrateAdjustmentType = BitrateAdjustmentType.NO_ADJUSTMENT;
    private double bitrateObservationTimeMs;
    private int colorFormat;
    private ByteBuffer configData = null;
    private GlRectDrawer drawer;
    private EglBase14 eglBase;
    private long forcedKeyFrameMs;
    private int height;
    private Surface inputSurface;
    private long lastKeyFrameMs;
    private MediaCodec mediaCodec;
    private Thread mediaCodecThread;
    private ByteBuffer[] outputBuffers;
    private int targetBitrateBps;
    private int targetFps;
    private VideoCodecType type;
    private int width;

    public enum BitrateAdjustmentType {
        NO_ADJUSTMENT,
        FRAMERATE_ADJUSTMENT,
        DYNAMIC_ADJUSTMENT
    }

    public static class EncoderProperties {
        public final BitrateAdjustmentType bitrateAdjustmentType;
        public final String codecName;
        public final int colorFormat;

        public EncoderProperties(String codecName, int colorFormat, BitrateAdjustmentType bitrateAdjustmentType) {
            this.codecName = codecName;
            this.colorFormat = colorFormat;
            this.bitrateAdjustmentType = bitrateAdjustmentType;
        }
    }

    private static class MediaCodecProperties {
        public final BitrateAdjustmentType bitrateAdjustmentType;
        public final String codecPrefix;
        public final int minSdk;

        MediaCodecProperties(String codecPrefix, int minSdk, BitrateAdjustmentType bitrateAdjustmentType) {
            this.codecPrefix = codecPrefix;
            this.minSdk = minSdk;
            this.bitrateAdjustmentType = bitrateAdjustmentType;
        }
    }

    public interface MediaCodecVideoEncoderErrorCallback {
        void onMediaCodecVideoEncoderCriticalError(int i);
    }

    static class OutputBufferInfo {
        public final ByteBuffer buffer;
        public final int index;
        public final boolean isKeyFrame;
        public final long presentationTimestampUs;

        public OutputBufferInfo(int index, ByteBuffer buffer, boolean isKeyFrame, long presentationTimestampUs) {
            this.index = index;
            this.buffer = buffer;
            this.isKeyFrame = isKeyFrame;
            this.presentationTimestampUs = presentationTimestampUs;
        }
    }

    public enum VideoCodecType {
        VIDEO_CODEC_VP8,
        VIDEO_CODEC_VP9,
        VIDEO_CODEC_H264
    }

    private static MediaCodecProperties[] vp8HwList() {
        ArrayList<MediaCodecProperties> supported_codecs = new ArrayList();
        supported_codecs.add(qcomVp8HwProperties);
        supported_codecs.add(exynosVp8HwProperties);
        if (PeerConnectionFactory.fieldTrialsFindFullName("WebRTC-IntelVP8").equals("Enabled")) {
            supported_codecs.add(intelVp8HwProperties);
        }
        return (MediaCodecProperties[]) supported_codecs.toArray(new MediaCodecProperties[supported_codecs.size()]);
    }

    public static void setErrorCallback(MediaCodecVideoEncoderErrorCallback errorCallback) {
        Logging.m2187d(TAG, "Set error callback");
        errorCallback = errorCallback;
    }

    public static void disableVp8HwCodec() {
        Logging.m2191w(TAG, "VP8 encoding is disabled by application.");
        hwEncoderDisabledTypes.add(VP8_MIME_TYPE);
    }

    public static void disableVp9HwCodec() {
        Logging.m2191w(TAG, "VP9 encoding is disabled by application.");
        hwEncoderDisabledTypes.add(VP9_MIME_TYPE);
    }

    public static void disableH264HwCodec() {
        Logging.m2191w(TAG, "H.264 encoding is disabled by application.");
        hwEncoderDisabledTypes.add(H264_MIME_TYPE);
    }

    public static boolean isVp8HwSupported() {
        return (hwEncoderDisabledTypes.contains(VP8_MIME_TYPE) || findHwEncoder(VP8_MIME_TYPE, vp8HwList(), supportedColorList) == null) ? false : true;
    }

    public static EncoderProperties vp8HwEncoderProperties() {
        if (hwEncoderDisabledTypes.contains(VP8_MIME_TYPE)) {
            return null;
        }
        return findHwEncoder(VP8_MIME_TYPE, vp8HwList(), supportedColorList);
    }

    public static boolean isVp9HwSupported() {
        return (hwEncoderDisabledTypes.contains(VP9_MIME_TYPE) || findHwEncoder(VP9_MIME_TYPE, vp9HwList, supportedColorList) == null) ? false : true;
    }

    public static boolean isH264HwSupported() {
        return (hwEncoderDisabledTypes.contains(H264_MIME_TYPE) || findHwEncoder(H264_MIME_TYPE, h264HwList, supportedColorList) == null) ? false : true;
    }

    public static boolean isVp8HwSupportedUsingTextures() {
        return (hwEncoderDisabledTypes.contains(VP8_MIME_TYPE) || findHwEncoder(VP8_MIME_TYPE, vp8HwList(), supportedSurfaceColorList) == null) ? false : true;
    }

    public static boolean isVp9HwSupportedUsingTextures() {
        return (hwEncoderDisabledTypes.contains(VP9_MIME_TYPE) || findHwEncoder(VP9_MIME_TYPE, vp9HwList, supportedSurfaceColorList) == null) ? false : true;
    }

    public static boolean isH264HwSupportedUsingTextures() {
        return (hwEncoderDisabledTypes.contains(H264_MIME_TYPE) || findHwEncoder(H264_MIME_TYPE, h264HwList, supportedSurfaceColorList) == null) ? false : true;
    }

    private static EncoderProperties findHwEncoder(String mime, MediaCodecProperties[] supportedHwCodecProperties, int[] colorList) {
        if (VERSION.SDK_INT < 19) {
            return null;
        }
        if (mime.equals(H264_MIME_TYPE) && Arrays.asList(H264_HW_EXCEPTION_MODELS).contains(Build.MODEL)) {
            Logging.m2191w(TAG, "Model: " + Build.MODEL + " has black listed H.264 encoder.");
            return null;
        }
        for (int i = 0; i < MediaCodecList.getCodecCount(); i++) {
            MediaCodecInfo info = null;
            try {
                info = MediaCodecList.getCodecInfoAt(i);
            } catch (IllegalArgumentException e) {
                Logging.m2189e(TAG, "Cannot retrieve encoder codec info", e);
            }
            if (info != null && info.isEncoder()) {
                String name = null;
                for (String mimeType : info.getSupportedTypes()) {
                    if (mimeType.equals(mime)) {
                        name = info.getName();
                        break;
                    }
                }
                if (name != null) {
                    CodecCapabilities capabilities;
                    Logging.m2190v(TAG, "Found candidate encoder " + name);
                    boolean supportedCodec = false;
                    BitrateAdjustmentType bitrateAdjustmentType = BitrateAdjustmentType.NO_ADJUSTMENT;
                    for (MediaCodecProperties codecProperties : supportedHwCodecProperties) {
                        if (name.startsWith(codecProperties.codecPrefix)) {
                            if (VERSION.SDK_INT < codecProperties.minSdk) {
                                Logging.m2191w(TAG, "Codec " + name + " is disabled due to SDK version " + VERSION.SDK_INT);
                            } else {
                                if (codecProperties.bitrateAdjustmentType != BitrateAdjustmentType.NO_ADJUSTMENT) {
                                    bitrateAdjustmentType = codecProperties.bitrateAdjustmentType;
                                    Logging.m2191w(TAG, "Codec " + name + " requires bitrate adjustment: " + bitrateAdjustmentType);
                                }
                                supportedCodec = true;
                                if (supportedCodec) {
                                    continue;
                                } else {
                                    try {
                                        capabilities = info.getCapabilitiesForType(mime);
                                        for (int colorFormat : capabilities.colorFormats) {
                                            Logging.m2190v(TAG, "   Color: 0x" + Integer.toHexString(colorFormat));
                                        }
                                        for (int supportedColorFormat : colorList) {
                                            for (int codecColorFormat : capabilities.colorFormats) {
                                                if (codecColorFormat != supportedColorFormat) {
                                                    Logging.m2187d(TAG, "Found target encoder for mime " + mime + " : " + name + ". Color: 0x" + Integer.toHexString(codecColorFormat) + ". Bitrate adjustment: " + bitrateAdjustmentType);
                                                    return new EncoderProperties(name, codecColorFormat, bitrateAdjustmentType);
                                                }
                                            }
                                        }
                                        continue;
                                    } catch (IllegalArgumentException e2) {
                                        Logging.m2189e(TAG, "Cannot retrieve encoder capabilities", e2);
                                    }
                                }
                            }
                        }
                    }
                    if (supportedCodec) {
                        continue;
                    } else {
                        capabilities = info.getCapabilitiesForType(mime);
                        while (r15 < r17) {
                            Logging.m2190v(TAG, "   Color: 0x" + Integer.toHexString(colorFormat));
                        }
                        for (int supportedColorFormat2 : colorList) {
                            while (r15 < r19) {
                                if (codecColorFormat != supportedColorFormat2) {
                                } else {
                                    Logging.m2187d(TAG, "Found target encoder for mime " + mime + " : " + name + ". Color: 0x" + Integer.toHexString(codecColorFormat) + ". Bitrate adjustment: " + bitrateAdjustmentType);
                                    return new EncoderProperties(name, codecColorFormat, bitrateAdjustmentType);
                                }
                            }
                        }
                        continue;
                    }
                } else {
                    continue;
                }
            }
        }
        return null;
    }

    private void checkOnMediaCodecThread() {
        if (this.mediaCodecThread.getId() != Thread.currentThread().getId()) {
            throw new RuntimeException("MediaCodecVideoEncoder previously operated on " + this.mediaCodecThread + " but is now called on " + Thread.currentThread());
        }
    }

    public static void printStackTrace() {
        if (runningInstance != null && runningInstance.mediaCodecThread != null) {
            StackTraceElement[] mediaCodecStackTraces = runningInstance.mediaCodecThread.getStackTrace();
            if (mediaCodecStackTraces.length > 0) {
                Logging.m2187d(TAG, "MediaCodecVideoEncoder stacks trace:");
                for (StackTraceElement stackTrace : mediaCodecStackTraces) {
                    Logging.m2187d(TAG, stackTrace.toString());
                }
            }
        }
    }

    static MediaCodec createByCodecName(String codecName) {
        try {
            return MediaCodec.createByCodecName(codecName);
        } catch (Exception e) {
            return null;
        }
    }

    boolean initEncode(VideoCodecType type, int width, int height, int kbps, int fps, Context sharedContext) {
        boolean useSurface = sharedContext != null;
        Logging.m2187d(TAG, "Java initEncode: " + type + " : " + width + " x " + height + ". @ " + kbps + " kbps. Fps: " + fps + ". Encode from texture : " + useSurface);
        this.width = width;
        this.height = height;
        if (this.mediaCodecThread != null) {
            throw new RuntimeException("Forgot to release()?");
        }
        EncoderProperties properties = null;
        String mime = null;
        int keyFrameIntervalSec = 0;
        if (type == VideoCodecType.VIDEO_CODEC_VP8) {
            mime = VP8_MIME_TYPE;
            properties = findHwEncoder(VP8_MIME_TYPE, vp8HwList(), useSurface ? supportedSurfaceColorList : supportedColorList);
            keyFrameIntervalSec = 100;
        } else if (type == VideoCodecType.VIDEO_CODEC_VP9) {
            mime = VP9_MIME_TYPE;
            properties = findHwEncoder(VP9_MIME_TYPE, vp9HwList, useSurface ? supportedSurfaceColorList : supportedColorList);
            keyFrameIntervalSec = 100;
        } else if (type == VideoCodecType.VIDEO_CODEC_H264) {
            mime = H264_MIME_TYPE;
            properties = findHwEncoder(H264_MIME_TYPE, h264HwList, useSurface ? supportedSurfaceColorList : supportedColorList);
            keyFrameIntervalSec = 20;
        }
        if (properties == null) {
            throw new RuntimeException("Can not find HW encoder for " + type);
        }
        runningInstance = this;
        this.colorFormat = properties.colorFormat;
        this.bitrateAdjustmentType = properties.bitrateAdjustmentType;
        if (this.bitrateAdjustmentType == BitrateAdjustmentType.FRAMERATE_ADJUSTMENT) {
            fps = 30;
        } else {
            fps = Math.min(fps, 30);
        }
        this.forcedKeyFrameMs = 0;
        this.lastKeyFrameMs = -1;
        if (type == VideoCodecType.VIDEO_CODEC_VP8 && properties.codecName.startsWith(qcomVp8HwProperties.codecPrefix)) {
            if (VERSION.SDK_INT == 23) {
                this.forcedKeyFrameMs = QCOM_VP8_KEY_FRAME_INTERVAL_ANDROID_M_MS;
            } else if (VERSION.SDK_INT > 23) {
                this.forcedKeyFrameMs = QCOM_VP8_KEY_FRAME_INTERVAL_ANDROID_N_MS;
            }
        }
        Logging.m2187d(TAG, "Color format: " + this.colorFormat + ". Bitrate adjustment: " + this.bitrateAdjustmentType + ". Key frame interval: " + this.forcedKeyFrameMs + " . Initial fps: " + fps);
        this.targetBitrateBps = kbps * 1000;
        this.targetFps = fps;
        this.bitrateAccumulatorMax = ((double) this.targetBitrateBps) / 8.0d;
        this.bitrateAccumulator = 0.0d;
        this.bitrateObservationTimeMs = 0.0d;
        this.bitrateAdjustmentScaleExp = 0;
        this.mediaCodecThread = Thread.currentThread();
        try {
            MediaFormat format = MediaFormat.createVideoFormat(mime, width, height);
            format.setInteger("bitrate", this.targetBitrateBps);
            format.setInteger("bitrate-mode", 2);
            format.setInteger("color-format", properties.colorFormat);
            format.setInteger("frame-rate", this.targetFps);
            format.setInteger("i-frame-interval", keyFrameIntervalSec);
            Logging.m2187d(TAG, "  Format: " + format);
            this.mediaCodec = createByCodecName(properties.codecName);
            this.type = type;
            if (this.mediaCodec == null) {
                Logging.m2188e(TAG, "Can not create media encoder");
                return false;
            }
            this.mediaCodec.configure(format, null, null, 1);
            if (useSurface) {
                this.eglBase = new EglBase14(sharedContext, EglBase.CONFIG_RECORDABLE);
                this.inputSurface = this.mediaCodec.createInputSurface();
                this.eglBase.createSurface(this.inputSurface);
                this.drawer = new GlRectDrawer();
            }
            this.mediaCodec.start();
            this.outputBuffers = this.mediaCodec.getOutputBuffers();
            Logging.m2187d(TAG, "Output buffers: " + this.outputBuffers.length);
            return true;
        } catch (IllegalStateException e) {
            Logging.m2189e(TAG, "initEncode failed", e);
            return false;
        }
    }

    ByteBuffer[] getInputBuffers() {
        ByteBuffer[] inputBuffers = this.mediaCodec.getInputBuffers();
        Logging.m2187d(TAG, "Input buffers: " + inputBuffers.length);
        return inputBuffers;
    }

    void checkKeyFrameRequired(boolean requestedKeyFrame, long presentationTimestampUs) {
        long presentationTimestampMs = (500 + presentationTimestampUs) / 1000;
        if (this.lastKeyFrameMs < 0) {
            this.lastKeyFrameMs = presentationTimestampMs;
        }
        boolean forcedKeyFrame = false;
        if (!requestedKeyFrame && this.forcedKeyFrameMs > 0 && presentationTimestampMs > this.lastKeyFrameMs + this.forcedKeyFrameMs) {
            forcedKeyFrame = true;
        }
        if (requestedKeyFrame || forcedKeyFrame) {
            if (requestedKeyFrame) {
                Logging.m2187d(TAG, "Sync frame request");
            } else {
                Logging.m2187d(TAG, "Sync frame forced");
            }
            Bundle b = new Bundle();
            b.putInt("request-sync", 0);
            this.mediaCodec.setParameters(b);
            this.lastKeyFrameMs = presentationTimestampMs;
        }
    }

    boolean encodeBuffer(boolean isKeyframe, int inputBuffer, int size, long presentationTimestampUs) {
        checkOnMediaCodecThread();
        try {
            checkKeyFrameRequired(isKeyframe, presentationTimestampUs);
            this.mediaCodec.queueInputBuffer(inputBuffer, 0, size, presentationTimestampUs, 0);
            return true;
        } catch (IllegalStateException e) {
            Logging.m2189e(TAG, "encodeBuffer failed", e);
            return false;
        }
    }

    boolean encodeTexture(boolean isKeyframe, int oesTextureId, float[] transformationMatrix, long presentationTimestampUs) {
        checkOnMediaCodecThread();
        try {
            checkKeyFrameRequired(isKeyframe, presentationTimestampUs);
            this.eglBase.makeCurrent();
            GLES20.glClear(16384);
            this.drawer.drawOes(oesTextureId, transformationMatrix, this.width, this.height, 0, 0, this.width, this.height);
            this.eglBase.swapBuffers(TimeUnit.MICROSECONDS.toNanos(presentationTimestampUs));
            return true;
        } catch (RuntimeException e) {
            Logging.m2189e(TAG, "encodeTexture failed", e);
            return false;
        }
    }

    void release() {
        Logging.m2187d(TAG, "Java releaseEncoder");
        checkOnMediaCodecThread();
        final CountDownLatch releaseDone = new CountDownLatch(1);
        new Thread(new Runnable() {
            public void run() {
                try {
                    Logging.m2187d(MediaCodecVideoEncoder.TAG, "Java releaseEncoder on release thread");
                    MediaCodecVideoEncoder.this.mediaCodec.stop();
                    MediaCodecVideoEncoder.this.mediaCodec.release();
                    Logging.m2187d(MediaCodecVideoEncoder.TAG, "Java releaseEncoder on release thread done");
                } catch (Exception e) {
                    Logging.m2189e(MediaCodecVideoEncoder.TAG, "Media encoder release failed", e);
                }
                releaseDone.countDown();
            }
        }).start();
        if (!ThreadUtils.awaitUninterruptibly(releaseDone, 5000)) {
            Logging.m2188e(TAG, "Media encoder release timeout");
            codecErrors++;
            if (errorCallback != null) {
                Logging.m2188e(TAG, "Invoke codec error callback. Errors: " + codecErrors);
                errorCallback.onMediaCodecVideoEncoderCriticalError(codecErrors);
            }
        }
        this.mediaCodec = null;
        this.mediaCodecThread = null;
        if (this.drawer != null) {
            this.drawer.release();
            this.drawer = null;
        }
        if (this.eglBase != null) {
            this.eglBase.release();
            this.eglBase = null;
        }
        if (this.inputSurface != null) {
            this.inputSurface.release();
            this.inputSurface = null;
        }
        runningInstance = null;
        Logging.m2187d(TAG, "Java releaseEncoder done");
    }

    private boolean setRates(int kbps, int frameRate) {
        checkOnMediaCodecThread();
        int codecBitrateBps = kbps * 1000;
        if (this.bitrateAdjustmentType == BitrateAdjustmentType.DYNAMIC_ADJUSTMENT) {
            this.bitrateAccumulatorMax = ((double) codecBitrateBps) / 8.0d;
            if (this.targetBitrateBps > 0 && codecBitrateBps < this.targetBitrateBps) {
                this.bitrateAccumulator = (this.bitrateAccumulator * ((double) codecBitrateBps)) / ((double) this.targetBitrateBps);
            }
        }
        this.targetBitrateBps = codecBitrateBps;
        this.targetFps = frameRate;
        if (this.bitrateAdjustmentType == BitrateAdjustmentType.FRAMERATE_ADJUSTMENT && this.targetFps > 0) {
            codecBitrateBps = (this.targetBitrateBps * 30) / this.targetFps;
            Logging.m2190v(TAG, "setRates: " + kbps + " -> " + (codecBitrateBps / 1000) + " kbps. Fps: " + this.targetFps);
        } else if (this.bitrateAdjustmentType == BitrateAdjustmentType.DYNAMIC_ADJUSTMENT) {
            Logging.m2190v(TAG, "setRates: " + kbps + " kbps. Fps: " + this.targetFps + ". ExpScale: " + this.bitrateAdjustmentScaleExp);
            if (this.bitrateAdjustmentScaleExp != 0) {
                codecBitrateBps = (int) (((double) codecBitrateBps) * getBitrateScale(this.bitrateAdjustmentScaleExp));
            }
        } else {
            Logging.m2190v(TAG, "setRates: " + kbps + " kbps. Fps: " + this.targetFps);
        }
        try {
            Bundle params = new Bundle();
            params.putInt("video-bitrate", codecBitrateBps);
            this.mediaCodec.setParameters(params);
            return true;
        } catch (IllegalStateException e) {
            Logging.m2189e(TAG, "setRates failed", e);
            return false;
        }
    }

    int dequeueInputBuffer() {
        checkOnMediaCodecThread();
        try {
            return this.mediaCodec.dequeueInputBuffer(0);
        } catch (IllegalStateException e) {
            Logging.m2189e(TAG, "dequeueIntputBuffer failed", e);
            return -2;
        }
    }

    OutputBufferInfo dequeueOutputBuffer() {
        checkOnMediaCodecThread();
        try {
            BufferInfo info = new BufferInfo();
            int result = this.mediaCodec.dequeueOutputBuffer(info, 0);
            if (result >= 0) {
                if ((info.flags & 2) != 0) {
                    Logging.m2187d(TAG, "Config frame generated. Offset: " + info.offset + ". Size: " + info.size);
                    this.configData = ByteBuffer.allocateDirect(info.size);
                    this.outputBuffers[result].position(info.offset);
                    this.outputBuffers[result].limit(info.offset + info.size);
                    this.configData.put(this.outputBuffers[result]);
                    this.mediaCodec.releaseOutputBuffer(result, false);
                    result = this.mediaCodec.dequeueOutputBuffer(info, 0);
                }
            }
            if (result >= 0) {
                ByteBuffer outputBuffer = this.outputBuffers[result].duplicate();
                outputBuffer.position(info.offset);
                outputBuffer.limit(info.offset + info.size);
                reportEncodedFrame(info.size);
                boolean isKeyFrame = (info.flags & 1) != 0;
                if (isKeyFrame) {
                    Logging.m2187d(TAG, "Sync frame generated");
                }
                if (isKeyFrame && this.type == VideoCodecType.VIDEO_CODEC_H264) {
                    Logging.m2187d(TAG, "Appending config frame of size " + this.configData.capacity() + " to output buffer with offset " + info.offset + ", size " + info.size);
                    ByteBuffer keyFrameBuffer = ByteBuffer.allocateDirect(this.configData.capacity() + info.size);
                    this.configData.rewind();
                    keyFrameBuffer.put(this.configData);
                    keyFrameBuffer.put(outputBuffer);
                    keyFrameBuffer.position(0);
                    return new OutputBufferInfo(result, keyFrameBuffer, isKeyFrame, info.presentationTimeUs);
                }
                return new OutputBufferInfo(result, outputBuffer.slice(), isKeyFrame, info.presentationTimeUs);
            } else if (result == -3) {
                this.outputBuffers = this.mediaCodec.getOutputBuffers();
                return dequeueOutputBuffer();
            } else if (result == -2) {
                return dequeueOutputBuffer();
            } else {
                if (result == -1) {
                    return null;
                }
                throw new RuntimeException("dequeueOutputBuffer: " + result);
            }
        } catch (IllegalStateException e) {
            Logging.m2189e(TAG, "dequeueOutputBuffer failed", e);
            return new OutputBufferInfo(-1, null, false, -1);
        }
    }

    private double getBitrateScale(int bitrateAdjustmentScaleExp) {
        return Math.pow(BITRATE_CORRECTION_MAX_SCALE, ((double) bitrateAdjustmentScaleExp) / 20.0d);
    }

    private void reportEncodedFrame(int size) {
        if (this.targetFps != 0 && this.bitrateAdjustmentType == BitrateAdjustmentType.DYNAMIC_ADJUSTMENT) {
            this.bitrateAccumulator += ((double) size) - (((double) this.targetBitrateBps) / (8.0d * ((double) this.targetFps)));
            this.bitrateObservationTimeMs += 1000.0d / ((double) this.targetFps);
            double bitrateAccumulatorCap = BITRATE_CORRECTION_SEC * this.bitrateAccumulatorMax;
            this.bitrateAccumulator = Math.min(this.bitrateAccumulator, bitrateAccumulatorCap);
            this.bitrateAccumulator = Math.max(this.bitrateAccumulator, -bitrateAccumulatorCap);
            if (this.bitrateObservationTimeMs > 3000.0d) {
                Logging.m2187d(TAG, "Acc: " + ((int) this.bitrateAccumulator) + ". Max: " + ((int) this.bitrateAccumulatorMax) + ". ExpScale: " + this.bitrateAdjustmentScaleExp);
                boolean bitrateAdjustmentScaleChanged = false;
                if (this.bitrateAccumulator > this.bitrateAccumulatorMax) {
                    this.bitrateAdjustmentScaleExp -= (int) ((this.bitrateAccumulator / this.bitrateAccumulatorMax) + 0.5d);
                    this.bitrateAccumulator = this.bitrateAccumulatorMax;
                    bitrateAdjustmentScaleChanged = true;
                } else if (this.bitrateAccumulator < (-this.bitrateAccumulatorMax)) {
                    this.bitrateAdjustmentScaleExp += (int) (((-this.bitrateAccumulator) / this.bitrateAccumulatorMax) + 0.5d);
                    this.bitrateAccumulator = -this.bitrateAccumulatorMax;
                    bitrateAdjustmentScaleChanged = true;
                }
                if (bitrateAdjustmentScaleChanged) {
                    this.bitrateAdjustmentScaleExp = Math.min(this.bitrateAdjustmentScaleExp, 20);
                    this.bitrateAdjustmentScaleExp = Math.max(this.bitrateAdjustmentScaleExp, -20);
                    Logging.m2187d(TAG, "Adjusting bitrate scale to " + this.bitrateAdjustmentScaleExp + ". Value: " + getBitrateScale(this.bitrateAdjustmentScaleExp));
                    setRates(this.targetBitrateBps / 1000, this.targetFps);
                }
                this.bitrateObservationTimeMs = 0.0d;
            }
        }
    }

    boolean releaseOutputBuffer(int index) {
        checkOnMediaCodecThread();
        try {
            this.mediaCodec.releaseOutputBuffer(index, false);
            return true;
        } catch (IllegalStateException e) {
            Logging.m2189e(TAG, "releaseOutputBuffer failed", e);
            return false;
        }
    }
}
