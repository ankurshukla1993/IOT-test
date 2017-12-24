package org.webrtc.voiceengine;

import android.annotation.TargetApi;
import android.content.Context;
import android.media.AudioRecord;
import android.os.Process;
import java.nio.ByteBuffer;
import org.webrtc.Logging;
import org.webrtc.ThreadUtils;

public class WebRtcAudioRecord {
    private static final long AUDIO_RECORD_THREAD_JOIN_TIMEOUT_MS = 2000;
    private static final int BITS_PER_SAMPLE = 16;
    private static final int BUFFERS_PER_SECOND = 100;
    private static final int BUFFER_SIZE_FACTOR = 2;
    private static final int CALLBACK_BUFFER_SIZE_MS = 10;
    private static final boolean DEBUG = false;
    private static final String TAG = "WebRtcAudioRecord";
    private static WebRtcAudioRecordErrorCallback errorCallback = null;
    private static volatile boolean microphoneMute = false;
    private AudioRecord audioRecord = null;
    private AudioRecordThread audioThread = null;
    private ByteBuffer byteBuffer;
    private final Context context;
    private WebRtcAudioEffects effects = null;
    private byte[] emptyBytes;
    private final long nativeAudioRecord;

    private class AudioRecordThread extends Thread {
        private volatile boolean keepAlive = true;

        public AudioRecordThread(String name) {
            super(name);
        }

        public void run() {
            boolean z;
            Process.setThreadPriority(-19);
            Logging.m2187d(WebRtcAudioRecord.TAG, "AudioRecordThread" + WebRtcAudioUtils.getThreadInfo());
            if (WebRtcAudioRecord.this.audioRecord.getRecordingState() == 3) {
                z = true;
            } else {
                z = false;
            }
            WebRtcAudioRecord.assertTrue(z);
            long lastTime = System.nanoTime();
            while (this.keepAlive) {
                int bytesRead = WebRtcAudioRecord.this.audioRecord.read(WebRtcAudioRecord.this.byteBuffer, WebRtcAudioRecord.this.byteBuffer.capacity());
                if (bytesRead == WebRtcAudioRecord.this.byteBuffer.capacity()) {
                    if (WebRtcAudioRecord.microphoneMute) {
                        WebRtcAudioRecord.this.byteBuffer.clear();
                        WebRtcAudioRecord.this.byteBuffer.put(WebRtcAudioRecord.this.emptyBytes);
                    }
                    WebRtcAudioRecord.this.nativeDataIsRecorded(bytesRead, WebRtcAudioRecord.this.nativeAudioRecord);
                } else {
                    String errorMessage = "AudioRecord.read failed: " + bytesRead;
                    Logging.m2188e(WebRtcAudioRecord.TAG, errorMessage);
                    if (bytesRead == -3) {
                        this.keepAlive = false;
                        WebRtcAudioRecord.this.reportWebRtcAudioRecordError(errorMessage);
                    }
                }
            }
            try {
                if (WebRtcAudioRecord.this.audioRecord != null) {
                    WebRtcAudioRecord.this.audioRecord.stop();
                }
            } catch (IllegalStateException e) {
                Logging.m2188e(WebRtcAudioRecord.TAG, "AudioRecord.stop failed: " + e.getMessage());
            }
        }

        public void stopThread() {
            Logging.m2187d(WebRtcAudioRecord.TAG, "stopThread");
            this.keepAlive = false;
        }
    }

    public interface WebRtcAudioRecordErrorCallback {
        void onWebRtcAudioRecordError(String str);

        void onWebRtcAudioRecordInitError(String str);

        void onWebRtcAudioRecordStartError(String str);
    }

    private native void nativeCacheDirectBufferAddress(ByteBuffer byteBuffer, long j);

    private native void nativeDataIsRecorded(int i, long j);

    public static void setErrorCallback(WebRtcAudioRecordErrorCallback errorCallback) {
        Logging.m2187d(TAG, "Set error callback");
        errorCallback = errorCallback;
    }

    WebRtcAudioRecord(Context context, long nativeAudioRecord) {
        Logging.m2187d(TAG, "ctor" + WebRtcAudioUtils.getThreadInfo());
        this.context = context;
        this.nativeAudioRecord = nativeAudioRecord;
        this.effects = WebRtcAudioEffects.create();
    }

    private boolean enableBuiltInAEC(boolean enable) {
        Logging.m2187d(TAG, "enableBuiltInAEC(" + enable + ')');
        if (this.effects != null) {
            return this.effects.setAEC(enable);
        }
        Logging.m2188e(TAG, "Built-in AEC is not supported on this platform");
        return false;
    }

    private boolean enableBuiltInNS(boolean enable) {
        Logging.m2187d(TAG, "enableBuiltInNS(" + enable + ')');
        if (this.effects != null) {
            return this.effects.setNS(enable);
        }
        Logging.m2188e(TAG, "Built-in NS is not supported on this platform");
        return false;
    }

    private int initRecording(int sampleRate, int channels) {
        Logging.m2187d(TAG, "initRecording(sampleRate=" + sampleRate + ", channels=" + channels + ")");
        if (!WebRtcAudioUtils.hasPermission(this.context, "android.permission.RECORD_AUDIO")) {
            reportWebRtcAudioRecordInitError("RECORD_AUDIO permission is missing");
            return -1;
        } else if (this.audioRecord != null) {
            reportWebRtcAudioRecordInitError("InitRecording called twice without StopRecording.");
            return -1;
        } else {
            int framesPerBuffer = sampleRate / 100;
            this.byteBuffer = ByteBuffer.allocateDirect((channels * 2) * framesPerBuffer);
            Logging.m2187d(TAG, "byteBuffer.capacity: " + this.byteBuffer.capacity());
            this.emptyBytes = new byte[this.byteBuffer.capacity()];
            nativeCacheDirectBufferAddress(this.byteBuffer, this.nativeAudioRecord);
            int channelConfig = channelCountToConfiguration(channels);
            int minBufferSize = AudioRecord.getMinBufferSize(sampleRate, channelConfig, 2);
            if (minBufferSize == -1 || minBufferSize == -2) {
                reportWebRtcAudioRecordInitError("AudioRecord.getMinBufferSize failed: " + minBufferSize);
                return -1;
            }
            Logging.m2187d(TAG, "AudioRecord.getMinBufferSize: " + minBufferSize);
            int bufferSizeInBytes = Math.max(minBufferSize * 2, this.byteBuffer.capacity());
            Logging.m2187d(TAG, "bufferSizeInBytes: " + bufferSizeInBytes);
            try {
                this.audioRecord = new AudioRecord(7, sampleRate, channelConfig, 2, bufferSizeInBytes);
                if (this.audioRecord == null || this.audioRecord.getState() != 1) {
                    reportWebRtcAudioRecordInitError("Failed to create a new AudioRecord instance");
                    releaseAudioResources();
                    return -1;
                }
                if (this.effects != null) {
                    this.effects.enable(this.audioRecord.getAudioSessionId());
                }
                logMainParameters();
                logMainParametersExtended();
                return framesPerBuffer;
            } catch (IllegalArgumentException e) {
                reportWebRtcAudioRecordInitError("AudioRecord ctor error: " + e.getMessage());
                releaseAudioResources();
                return -1;
            }
        }
    }

    private boolean startRecording() {
        boolean z;
        Logging.m2187d(TAG, "startRecording");
        assertTrue(this.audioRecord != null);
        if (this.audioThread == null) {
            z = true;
        } else {
            z = false;
        }
        assertTrue(z);
        try {
            this.audioRecord.startRecording();
            if (this.audioRecord.getRecordingState() != 3) {
                reportWebRtcAudioRecordStartError("AudioRecord.startRecording failed - incorrect state :" + this.audioRecord.getRecordingState());
                return false;
            }
            this.audioThread = new AudioRecordThread("AudioRecordJavaThread");
            this.audioThread.start();
            return true;
        } catch (IllegalStateException e) {
            reportWebRtcAudioRecordStartError("AudioRecord.startRecording failed: " + e.getMessage());
            return false;
        }
    }

    private boolean stopRecording() {
        Logging.m2187d(TAG, "stopRecording");
        assertTrue(this.audioThread != null);
        this.audioThread.stopThread();
        if (!ThreadUtils.joinUninterruptibly(this.audioThread, AUDIO_RECORD_THREAD_JOIN_TIMEOUT_MS)) {
            Logging.m2188e(TAG, "Join of AudioRecordJavaThread timed out");
        }
        this.audioThread = null;
        if (this.effects != null) {
            this.effects.release();
        }
        releaseAudioResources();
        return true;
    }

    private void logMainParameters() {
        Logging.m2187d(TAG, "AudioRecord: session ID: " + this.audioRecord.getAudioSessionId() + ", channels: " + this.audioRecord.getChannelCount() + ", sample rate: " + this.audioRecord.getSampleRate());
    }

    @TargetApi(23)
    private void logMainParametersExtended() {
        if (WebRtcAudioUtils.runningOnMarshmallowOrHigher()) {
            Logging.m2187d(TAG, "AudioRecord: buffer size in frames: " + this.audioRecord.getBufferSizeInFrames());
        }
    }

    private static void assertTrue(boolean condition) {
        if (!condition) {
            throw new AssertionError("Expected condition to be true");
        }
    }

    private int channelCountToConfiguration(int channels) {
        return channels == 1 ? 16 : 12;
    }

    public static void setMicrophoneMute(boolean mute) {
        Logging.m2191w(TAG, "setMicrophoneMute(" + mute + ")");
        microphoneMute = mute;
    }

    private void releaseAudioResources() {
        if (this.audioRecord != null) {
            this.audioRecord.release();
            this.audioRecord = null;
        }
    }

    private void reportWebRtcAudioRecordInitError(String errorMessage) {
        Logging.m2188e(TAG, "Init recording error: " + errorMessage);
        if (errorCallback != null) {
            errorCallback.onWebRtcAudioRecordInitError(errorMessage);
        }
    }

    private void reportWebRtcAudioRecordStartError(String errorMessage) {
        Logging.m2188e(TAG, "Start recording error: " + errorMessage);
        if (errorCallback != null) {
            errorCallback.onWebRtcAudioRecordStartError(errorMessage);
        }
    }

    private void reportWebRtcAudioRecordError(String errorMessage) {
        Logging.m2188e(TAG, "Run-time recording error: " + errorMessage);
        if (errorCallback != null) {
            errorCallback.onWebRtcAudioRecordError(errorMessage);
        }
    }
}
