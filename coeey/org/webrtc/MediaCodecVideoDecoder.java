package org.webrtc;

import android.media.MediaCodec;
import android.media.MediaCodec.BufferInfo;
import android.media.MediaCodec.CodecException;
import android.media.MediaCodecInfo;
import android.media.MediaCodecInfo.CodecCapabilities;
import android.media.MediaCodecList;
import android.media.MediaFormat;
import android.os.Build.VERSION;
import android.os.SystemClock;
import android.view.Surface;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.webrtc.SurfaceTextureHelper.OnTextureFrameAvailableListener;

public class MediaCodecVideoDecoder {
    private static final int COLOR_QCOM_FORMATYUV420PackedSemiPlanar32m = 2141391876;
    private static final int COLOR_QCOM_FORMATYVU420PackedSemiPlanar16m4ka = 2141391874;
    private static final int COLOR_QCOM_FORMATYVU420PackedSemiPlanar32m4ka = 2141391873;
    private static final int COLOR_QCOM_FORMATYVU420PackedSemiPlanar64x32Tile2m8ka = 2141391875;
    private static final int DEQUEUE_INPUT_TIMEOUT = 500000;
    private static final String H264_MIME_TYPE = "video/avc";
    private static final long MAX_DECODE_TIME_MS = 200;
    private static final int MAX_QUEUED_OUTPUTBUFFERS = 3;
    private static final int MEDIA_CODEC_RELEASE_TIMEOUT_MS = 5000;
    private static final String TAG = "MediaCodecVideoDecoder";
    private static final String VP8_MIME_TYPE = "video/x-vnd.on2.vp8";
    private static final String VP9_MIME_TYPE = "video/x-vnd.on2.vp9";
    private static int codecErrors = 0;
    private static MediaCodecVideoDecoderErrorCallback errorCallback = null;
    private static Set<String> hwDecoderDisabledTypes = new HashSet();
    private static MediaCodecVideoDecoder runningInstance = null;
    private static final List<Integer> supportedColorList = Arrays.asList(new Integer[]{Integer.valueOf(19), Integer.valueOf(21), Integer.valueOf(2141391872), Integer.valueOf(COLOR_QCOM_FORMATYVU420PackedSemiPlanar32m4ka), Integer.valueOf(COLOR_QCOM_FORMATYVU420PackedSemiPlanar16m4ka), Integer.valueOf(COLOR_QCOM_FORMATYVU420PackedSemiPlanar64x32Tile2m8ka), Integer.valueOf(COLOR_QCOM_FORMATYUV420PackedSemiPlanar32m)});
    private static final String[] supportedH264HighProfileHwCodecPrefixes = new String[]{"OMX.qcom."};
    private static final String[] supportedH264HwCodecPrefixes = new String[]{"OMX.qcom.", "OMX.Intel.", "OMX.Exynos."};
    private static final String[] supportedVp8HwCodecPrefixes = new String[]{"OMX.qcom.", "OMX.Nvidia.", "OMX.Exynos.", "OMX.Intel."};
    private static final String[] supportedVp9HwCodecPrefixes = new String[]{"OMX.qcom.", "OMX.Exynos."};
    private int colorFormat;
    private final Queue<TimeStamps> decodeStartTimeMs = new LinkedList();
    private final Queue<DecodedOutputBuffer> dequeuedSurfaceOutputBuffers = new LinkedList();
    private int droppedFrames;
    private boolean hasDecodedFirstFrame;
    private int height;
    private ByteBuffer[] inputBuffers;
    private MediaCodec mediaCodec;
    private Thread mediaCodecThread;
    private ByteBuffer[] outputBuffers;
    private int sliceHeight;
    private int stride;
    private Surface surface = null;
    private TextureListener textureListener;
    private boolean useSurface;
    private int width;

    private static class DecodedOutputBuffer {
        private final long decodeTimeMs;
        private final long endDecodeTimeMs;
        private final int index;
        private final long ntpTimeStampMs;
        private final int offset;
        private final long presentationTimeStampMs;
        private final int size;
        private final long timeStampMs;

        public DecodedOutputBuffer(int index, int offset, int size, long presentationTimeStampMs, long timeStampMs, long ntpTimeStampMs, long decodeTime, long endDecodeTime) {
            this.index = index;
            this.offset = offset;
            this.size = size;
            this.presentationTimeStampMs = presentationTimeStampMs;
            this.timeStampMs = timeStampMs;
            this.ntpTimeStampMs = ntpTimeStampMs;
            this.decodeTimeMs = decodeTime;
            this.endDecodeTimeMs = endDecodeTime;
        }
    }

    private static class DecodedTextureBuffer {
        private final long decodeTimeMs;
        private final long frameDelayMs;
        private final long ntpTimeStampMs;
        private final long presentationTimeStampMs;
        private final int textureID;
        private final long timeStampMs;
        private final float[] transformMatrix;

        public DecodedTextureBuffer(int textureID, float[] transformMatrix, long presentationTimeStampMs, long timeStampMs, long ntpTimeStampMs, long decodeTimeMs, long frameDelay) {
            this.textureID = textureID;
            this.transformMatrix = transformMatrix;
            this.presentationTimeStampMs = presentationTimeStampMs;
            this.timeStampMs = timeStampMs;
            this.ntpTimeStampMs = ntpTimeStampMs;
            this.decodeTimeMs = decodeTimeMs;
            this.frameDelayMs = frameDelay;
        }
    }

    private static class DecoderProperties {
        public final String codecName;
        public final int colorFormat;

        public DecoderProperties(String codecName, int colorFormat) {
            this.codecName = codecName;
            this.colorFormat = colorFormat;
        }
    }

    public interface MediaCodecVideoDecoderErrorCallback {
        void onMediaCodecVideoDecoderCriticalError(int i);
    }

    private static class TextureListener implements OnTextureFrameAvailableListener {
        private DecodedOutputBuffer bufferToRender;
        private final Object newFrameLock = new Object();
        private DecodedTextureBuffer renderedBuffer;
        private final SurfaceTextureHelper surfaceTextureHelper;

        public TextureListener(SurfaceTextureHelper surfaceTextureHelper) {
            this.surfaceTextureHelper = surfaceTextureHelper;
            surfaceTextureHelper.startListening(this);
        }

        public void addBufferToRender(DecodedOutputBuffer buffer) {
            if (this.bufferToRender != null) {
                Logging.m2188e(MediaCodecVideoDecoder.TAG, "Unexpected addBufferToRender() called while waiting for a texture.");
                throw new IllegalStateException("Waiting for a texture.");
            } else {
                this.bufferToRender = buffer;
            }
        }

        public boolean isWaitingForTexture() {
            boolean z;
            synchronized (this.newFrameLock) {
                z = this.bufferToRender != null;
            }
            return z;
        }

        public void onTextureFrameAvailable(int oesTextureId, float[] transformMatrix, long timestampNs) {
            synchronized (this.newFrameLock) {
                if (this.renderedBuffer != null) {
                    Logging.m2188e(MediaCodecVideoDecoder.TAG, "Unexpected onTextureFrameAvailable() called while already holding a texture.");
                    throw new IllegalStateException("Already holding a texture.");
                }
                this.renderedBuffer = new DecodedTextureBuffer(oesTextureId, transformMatrix, this.bufferToRender.presentationTimeStampMs, this.bufferToRender.timeStampMs, this.bufferToRender.ntpTimeStampMs, this.bufferToRender.decodeTimeMs, SystemClock.elapsedRealtime() - this.bufferToRender.endDecodeTimeMs);
                this.bufferToRender = null;
                this.newFrameLock.notifyAll();
            }
        }

        public DecodedTextureBuffer dequeueTextureBuffer(int timeoutMs) {
            DecodedTextureBuffer returnedBuffer;
            synchronized (this.newFrameLock) {
                if (this.renderedBuffer == null && timeoutMs > 0 && isWaitingForTexture()) {
                    try {
                        this.newFrameLock.wait((long) timeoutMs);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                returnedBuffer = this.renderedBuffer;
                this.renderedBuffer = null;
            }
            return returnedBuffer;
        }

        public void release() {
            this.surfaceTextureHelper.stopListening();
            synchronized (this.newFrameLock) {
                if (this.renderedBuffer != null) {
                    this.surfaceTextureHelper.returnTextureFrame();
                    this.renderedBuffer = null;
                }
            }
        }
    }

    private static class TimeStamps {
        private final long decodeStartTimeMs;
        private final long ntpTimeStampMs;
        private final long timeStampMs;

        public TimeStamps(long decodeStartTimeMs, long timeStampMs, long ntpTimeStampMs) {
            this.decodeStartTimeMs = decodeStartTimeMs;
            this.timeStampMs = timeStampMs;
            this.ntpTimeStampMs = ntpTimeStampMs;
        }
    }

    public enum VideoCodecType {
        VIDEO_CODEC_VP8,
        VIDEO_CODEC_VP9,
        VIDEO_CODEC_H264
    }

    public static void setErrorCallback(MediaCodecVideoDecoderErrorCallback errorCallback) {
        Logging.m2187d(TAG, "Set error callback");
        errorCallback = errorCallback;
    }

    public static void disableVp8HwCodec() {
        Logging.m2191w(TAG, "VP8 decoding is disabled by application.");
        hwDecoderDisabledTypes.add(VP8_MIME_TYPE);
    }

    public static void disableVp9HwCodec() {
        Logging.m2191w(TAG, "VP9 decoding is disabled by application.");
        hwDecoderDisabledTypes.add(VP9_MIME_TYPE);
    }

    public static void disableH264HwCodec() {
        Logging.m2191w(TAG, "H.264 decoding is disabled by application.");
        hwDecoderDisabledTypes.add(H264_MIME_TYPE);
    }

    public static boolean isVp8HwSupported() {
        return (hwDecoderDisabledTypes.contains(VP8_MIME_TYPE) || findDecoder(VP8_MIME_TYPE, supportedVp8HwCodecPrefixes) == null) ? false : true;
    }

    public static boolean isVp9HwSupported() {
        return (hwDecoderDisabledTypes.contains(VP9_MIME_TYPE) || findDecoder(VP9_MIME_TYPE, supportedVp9HwCodecPrefixes) == null) ? false : true;
    }

    public static boolean isH264HwSupported() {
        return (hwDecoderDisabledTypes.contains(H264_MIME_TYPE) || findDecoder(H264_MIME_TYPE, supportedH264HwCodecPrefixes) == null) ? false : true;
    }

    public static boolean isH264HighProfileHwSupported() {
        return (VERSION.SDK_INT < 21 || hwDecoderDisabledTypes.contains(H264_MIME_TYPE) || findDecoder(H264_MIME_TYPE, supportedH264HighProfileHwCodecPrefixes) == null) ? false : true;
    }

    public static void printStackTrace() {
        if (runningInstance != null && runningInstance.mediaCodecThread != null) {
            StackTraceElement[] mediaCodecStackTraces = runningInstance.mediaCodecThread.getStackTrace();
            if (mediaCodecStackTraces.length > 0) {
                Logging.m2187d(TAG, "MediaCodecVideoDecoder stacks trace:");
                for (StackTraceElement stackTrace : mediaCodecStackTraces) {
                    Logging.m2187d(TAG, stackTrace.toString());
                }
            }
        }
    }

    private static DecoderProperties findDecoder(String mime, String[] supportedCodecPrefixes) {
        if (VERSION.SDK_INT < 19) {
            return null;
        }
        Logging.m2187d(TAG, "Trying to find HW decoder for mime " + mime);
        for (int i = 0; i < MediaCodecList.getCodecCount(); i++) {
            MediaCodecInfo info = null;
            try {
                info = MediaCodecList.getCodecInfoAt(i);
            } catch (IllegalArgumentException e) {
                Logging.m2189e(TAG, "Cannot retrieve decoder codec info", e);
            }
            if (!(info == null || info.isEncoder())) {
                String name = null;
                for (String mimeType : info.getSupportedTypes()) {
                    if (mimeType.equals(mime)) {
                        name = info.getName();
                        break;
                    }
                }
                if (name != null) {
                    Logging.m2187d(TAG, "Found candidate decoder " + name);
                    boolean supportedCodec = false;
                    for (String codecPrefix : supportedCodecPrefixes) {
                        if (name.startsWith(codecPrefix)) {
                            supportedCodec = true;
                            break;
                        }
                    }
                    if (supportedCodec) {
                        try {
                            CodecCapabilities capabilities = info.getCapabilitiesForType(mime);
                            for (int colorFormat : capabilities.colorFormats) {
                                Logging.m2190v(TAG, "   Color: 0x" + Integer.toHexString(colorFormat));
                            }
                            for (Integer intValue : supportedColorList) {
                                int supportedColorFormat = intValue.intValue();
                                for (int codecColorFormat : capabilities.colorFormats) {
                                    if (codecColorFormat == supportedColorFormat) {
                                        Logging.m2187d(TAG, "Found target decoder " + name + ". Color: 0x" + Integer.toHexString(codecColorFormat));
                                        return new DecoderProperties(name, codecColorFormat);
                                    }
                                }
                            }
                            continue;
                        } catch (IllegalArgumentException e2) {
                            Logging.m2189e(TAG, "Cannot retrieve decoder capabilities", e2);
                        }
                    } else {
                        continue;
                    }
                } else {
                    continue;
                }
            }
        }
        Logging.m2187d(TAG, "No HW decoder found for mime " + mime);
        return null;
    }

    private void checkOnMediaCodecThread() throws IllegalStateException {
        if (this.mediaCodecThread.getId() != Thread.currentThread().getId()) {
            throw new IllegalStateException("MediaCodecVideoDecoder previously operated on " + this.mediaCodecThread + " but is now called on " + Thread.currentThread());
        }
    }

    private boolean initDecode(VideoCodecType type, int width, int height, SurfaceTextureHelper surfaceTextureHelper) {
        if (this.mediaCodecThread != null) {
            throw new RuntimeException("initDecode: Forgot to release()?");
        }
        boolean z;
        String mime;
        String[] supportedCodecPrefixes;
        if (surfaceTextureHelper != null) {
            z = true;
        } else {
            z = false;
        }
        this.useSurface = z;
        if (type == VideoCodecType.VIDEO_CODEC_VP8) {
            mime = VP8_MIME_TYPE;
            supportedCodecPrefixes = supportedVp8HwCodecPrefixes;
        } else if (type == VideoCodecType.VIDEO_CODEC_VP9) {
            mime = VP9_MIME_TYPE;
            supportedCodecPrefixes = supportedVp9HwCodecPrefixes;
        } else if (type == VideoCodecType.VIDEO_CODEC_H264) {
            mime = H264_MIME_TYPE;
            supportedCodecPrefixes = supportedH264HwCodecPrefixes;
        } else {
            throw new RuntimeException("initDecode: Non-supported codec " + type);
        }
        DecoderProperties properties = findDecoder(mime, supportedCodecPrefixes);
        if (properties == null) {
            throw new RuntimeException("Cannot find HW decoder for " + type);
        }
        Logging.m2187d(TAG, "Java initDecode: " + type + " : " + width + " x " + height + ". Color: 0x" + Integer.toHexString(properties.colorFormat) + ". Use Surface: " + this.useSurface);
        runningInstance = this;
        this.mediaCodecThread = Thread.currentThread();
        try {
            this.width = width;
            this.height = height;
            this.stride = width;
            this.sliceHeight = height;
            if (this.useSurface) {
                this.textureListener = new TextureListener(surfaceTextureHelper);
                this.surface = new Surface(surfaceTextureHelper.getSurfaceTexture());
            }
            MediaFormat format = MediaFormat.createVideoFormat(mime, width, height);
            if (!this.useSurface) {
                format.setInteger("color-format", properties.colorFormat);
            }
            Logging.m2187d(TAG, "  Format: " + format);
            this.mediaCodec = MediaCodecVideoEncoder.createByCodecName(properties.codecName);
            if (this.mediaCodec == null) {
                Logging.m2188e(TAG, "Can not create media decoder");
                return false;
            }
            this.mediaCodec.configure(format, this.surface, null, 0);
            this.mediaCodec.start();
            this.colorFormat = properties.colorFormat;
            this.outputBuffers = this.mediaCodec.getOutputBuffers();
            this.inputBuffers = this.mediaCodec.getInputBuffers();
            this.decodeStartTimeMs.clear();
            this.hasDecodedFirstFrame = false;
            this.dequeuedSurfaceOutputBuffers.clear();
            this.droppedFrames = 0;
            Logging.m2187d(TAG, "Input buffers: " + this.inputBuffers.length + ". Output buffers: " + this.outputBuffers.length);
            return true;
        } catch (IllegalStateException e) {
            Logging.m2189e(TAG, "initDecode failed", e);
            return false;
        }
    }

    private void reset(int width, int height) {
        if (this.mediaCodecThread == null || this.mediaCodec == null) {
            throw new RuntimeException("Incorrect reset call for non-initialized decoder.");
        }
        Logging.m2187d(TAG, "Java reset: " + width + " x " + height);
        this.mediaCodec.flush();
        this.width = width;
        this.height = height;
        this.decodeStartTimeMs.clear();
        this.dequeuedSurfaceOutputBuffers.clear();
        this.hasDecodedFirstFrame = false;
        this.droppedFrames = 0;
    }

    private void release() {
        Logging.m2187d(TAG, "Java releaseDecoder. Total number of dropped frames: " + this.droppedFrames);
        checkOnMediaCodecThread();
        final CountDownLatch releaseDone = new CountDownLatch(1);
        new Thread(new Runnable() {
            public void run() {
                try {
                    Logging.m2187d(MediaCodecVideoDecoder.TAG, "Java releaseDecoder on release thread");
                    MediaCodecVideoDecoder.this.mediaCodec.stop();
                    MediaCodecVideoDecoder.this.mediaCodec.release();
                    Logging.m2187d(MediaCodecVideoDecoder.TAG, "Java releaseDecoder on release thread done");
                } catch (Exception e) {
                    Logging.m2189e(MediaCodecVideoDecoder.TAG, "Media decoder release failed", e);
                }
                releaseDone.countDown();
            }
        }).start();
        if (!ThreadUtils.awaitUninterruptibly(releaseDone, 5000)) {
            Logging.m2188e(TAG, "Media decoder release timeout");
            codecErrors++;
            if (errorCallback != null) {
                Logging.m2188e(TAG, "Invoke codec error callback. Errors: " + codecErrors);
                errorCallback.onMediaCodecVideoDecoderCriticalError(codecErrors);
            }
        }
        this.mediaCodec = null;
        this.mediaCodecThread = null;
        runningInstance = null;
        if (this.useSurface) {
            this.surface.release();
            this.surface = null;
            this.textureListener.release();
        }
        Logging.m2187d(TAG, "Java releaseDecoder done");
    }

    private int dequeueInputBuffer() {
        checkOnMediaCodecThread();
        try {
            return this.mediaCodec.dequeueInputBuffer(500000);
        } catch (IllegalStateException e) {
            Logging.m2189e(TAG, "dequeueIntputBuffer failed", e);
            return -2;
        }
    }

    private boolean queueInputBuffer(int inputBufferIndex, int size, long presentationTimeStamUs, long timeStampMs, long ntpTimeStamp) {
        checkOnMediaCodecThread();
        try {
            this.inputBuffers[inputBufferIndex].position(0);
            this.inputBuffers[inputBufferIndex].limit(size);
            this.decodeStartTimeMs.add(new TimeStamps(SystemClock.elapsedRealtime(), timeStampMs, ntpTimeStamp));
            this.mediaCodec.queueInputBuffer(inputBufferIndex, 0, size, presentationTimeStamUs, 0);
            return true;
        } catch (IllegalStateException e) {
            Logging.m2189e(TAG, "decode failed", e);
            return false;
        }
    }

    private DecodedOutputBuffer dequeueOutputBuffer(int dequeueTimeoutMs) {
        checkOnMediaCodecThread();
        if (this.decodeStartTimeMs.isEmpty()) {
            return null;
        }
        BufferInfo info = new BufferInfo();
        while (true) {
            int result = this.mediaCodec.dequeueOutputBuffer(info, TimeUnit.MILLISECONDS.toMicros((long) dequeueTimeoutMs));
            switch (result) {
                case -3:
                    this.outputBuffers = this.mediaCodec.getOutputBuffers();
                    Logging.m2187d(TAG, "Decoder output buffers changed: " + this.outputBuffers.length);
                    if (!this.hasDecodedFirstFrame) {
                        break;
                    }
                    throw new RuntimeException("Unexpected output buffer change event.");
                case -2:
                    MediaFormat format = this.mediaCodec.getOutputFormat();
                    Logging.m2187d(TAG, "Decoder format changed: " + format.toString());
                    int new_width = format.getInteger("width");
                    int new_height = format.getInteger("height");
                    if (!this.hasDecodedFirstFrame || (new_width == this.width && new_height == this.height)) {
                        this.width = format.getInteger("width");
                        this.height = format.getInteger("height");
                        if (!this.useSurface) {
                            if (format.containsKey("color-format")) {
                                this.colorFormat = format.getInteger("color-format");
                                Logging.m2187d(TAG, "Color: 0x" + Integer.toHexString(this.colorFormat));
                                if (!supportedColorList.contains(Integer.valueOf(this.colorFormat))) {
                                    throw new IllegalStateException("Non supported color format: " + this.colorFormat);
                                }
                            }
                        }
                        if (format.containsKey("stride")) {
                            this.stride = format.getInteger("stride");
                        }
                        if (format.containsKey("slice-height")) {
                            this.sliceHeight = format.getInteger("slice-height");
                        }
                        Logging.m2187d(TAG, "Frame stride and slice height: " + this.stride + " x " + this.sliceHeight);
                        this.stride = Math.max(this.width, this.stride);
                        this.sliceHeight = Math.max(this.height, this.sliceHeight);
                        break;
                    }
                    throw new RuntimeException("Unexpected size change. Configured " + this.width + "*" + this.height + ". New " + new_width + "*" + new_height);
                    break;
                case -1:
                    return null;
                default:
                    this.hasDecodedFirstFrame = true;
                    TimeStamps timeStamps = (TimeStamps) this.decodeStartTimeMs.remove();
                    long decodeTimeMs = SystemClock.elapsedRealtime() - timeStamps.decodeStartTimeMs;
                    if (decodeTimeMs > MAX_DECODE_TIME_MS) {
                        Logging.m2188e(TAG, "Very high decode time: " + decodeTimeMs + "ms. Q size: " + this.decodeStartTimeMs.size() + ". Might be caused by resuming H264 decoding after a pause.");
                        decodeTimeMs = MAX_DECODE_TIME_MS;
                    }
                    return new DecodedOutputBuffer(result, info.offset, info.size, TimeUnit.MICROSECONDS.toMillis(info.presentationTimeUs), timeStamps.timeStampMs, timeStamps.ntpTimeStampMs, decodeTimeMs, SystemClock.elapsedRealtime());
            }
        }
    }

    private DecodedTextureBuffer dequeueTextureBuffer(int dequeueTimeoutMs) {
        checkOnMediaCodecThread();
        if (this.useSurface) {
            DecodedOutputBuffer outputBuffer = dequeueOutputBuffer(dequeueTimeoutMs);
            if (outputBuffer != null) {
                this.dequeuedSurfaceOutputBuffers.add(outputBuffer);
            }
            MaybeRenderDecodedTextureBuffer();
            DecodedTextureBuffer renderedBuffer = this.textureListener.dequeueTextureBuffer(dequeueTimeoutMs);
            if (renderedBuffer != null) {
                MaybeRenderDecodedTextureBuffer();
                return renderedBuffer;
            } else if (this.dequeuedSurfaceOutputBuffers.size() < Math.min(3, this.outputBuffers.length) && (dequeueTimeoutMs <= 0 || this.dequeuedSurfaceOutputBuffers.isEmpty())) {
                return null;
            } else {
                this.droppedFrames++;
                DecodedOutputBuffer droppedFrame = (DecodedOutputBuffer) this.dequeuedSurfaceOutputBuffers.remove();
                if (dequeueTimeoutMs > 0) {
                    Logging.m2191w(TAG, "Draining decoder. Dropping frame with TS: " + droppedFrame.presentationTimeStampMs + ". Total number of dropped frames: " + this.droppedFrames);
                } else {
                    Logging.m2191w(TAG, "Too many output buffers " + this.dequeuedSurfaceOutputBuffers.size() + ". Dropping frame with TS: " + droppedFrame.presentationTimeStampMs + ". Total number of dropped frames: " + this.droppedFrames);
                }
                this.mediaCodec.releaseOutputBuffer(droppedFrame.index, false);
                return new DecodedTextureBuffer(0, null, droppedFrame.presentationTimeStampMs, droppedFrame.timeStampMs, droppedFrame.ntpTimeStampMs, droppedFrame.decodeTimeMs, SystemClock.elapsedRealtime() - droppedFrame.endDecodeTimeMs);
            }
        }
        throw new IllegalStateException("dequeueTexture() called for byte buffer decoding.");
    }

    private void MaybeRenderDecodedTextureBuffer() {
        if (!this.dequeuedSurfaceOutputBuffers.isEmpty() && !this.textureListener.isWaitingForTexture()) {
            DecodedOutputBuffer buffer = (DecodedOutputBuffer) this.dequeuedSurfaceOutputBuffers.remove();
            this.textureListener.addBufferToRender(buffer);
            this.mediaCodec.releaseOutputBuffer(buffer.index, true);
        }
    }

    private void returnDecodedOutputBuffer(int index) throws IllegalStateException, CodecException {
        checkOnMediaCodecThread();
        if (this.useSurface) {
            throw new IllegalStateException("returnDecodedOutputBuffer() called for surface decoding.");
        }
        this.mediaCodec.releaseOutputBuffer(index, false);
    }
}
