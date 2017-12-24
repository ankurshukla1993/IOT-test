package org.webrtc.voiceengine;

import android.annotation.TargetApi;
import android.content.Context;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.os.Process;
import java.nio.ByteBuffer;
import org.webrtc.Logging;

public class WebRtcAudioTrack {
    private static final int BITS_PER_SAMPLE = 16;
    private static final int BUFFERS_PER_SECOND = 100;
    private static final int CALLBACK_BUFFER_SIZE_MS = 10;
    private static final boolean DEBUG = false;
    private static final String TAG = "WebRtcAudioTrack";
    private static volatile boolean speakerMute = false;
    private final AudioManager audioManager;
    private AudioTrackThread audioThread = null;
    private AudioTrack audioTrack = null;
    private ByteBuffer byteBuffer;
    private final Context context;
    private byte[] emptyBytes;
    private final long nativeAudioTrack;

    private class AudioTrackThread extends Thread {
        private volatile boolean keepAlive = true;

        public AudioTrackThread(String name) {
            super(name);
        }

        public void run() {
            boolean z = true;
            Process.setThreadPriority(-19);
            Logging.m2187d(WebRtcAudioTrack.TAG, "AudioTrackThread" + WebRtcAudioUtils.getThreadInfo());
            try {
                boolean z2;
                WebRtcAudioTrack.this.audioTrack.play();
                if (WebRtcAudioTrack.this.audioTrack.getPlayState() == 3) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                WebRtcAudioTrack.assertTrue(z2);
                int sizeInBytes = WebRtcAudioTrack.this.byteBuffer.capacity();
                while (this.keepAlive) {
                    int bytesWritten;
                    WebRtcAudioTrack.this.nativeGetPlayoutData(sizeInBytes, WebRtcAudioTrack.this.nativeAudioTrack);
                    if (sizeInBytes <= WebRtcAudioTrack.this.byteBuffer.remaining()) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    WebRtcAudioTrack.assertTrue(z2);
                    if (WebRtcAudioTrack.speakerMute) {
                        WebRtcAudioTrack.this.byteBuffer.clear();
                        WebRtcAudioTrack.this.byteBuffer.put(WebRtcAudioTrack.this.emptyBytes);
                        WebRtcAudioTrack.this.byteBuffer.position(0);
                    }
                    if (WebRtcAudioUtils.runningOnLollipopOrHigher()) {
                        bytesWritten = writeOnLollipop(WebRtcAudioTrack.this.audioTrack, WebRtcAudioTrack.this.byteBuffer, sizeInBytes);
                    } else {
                        bytesWritten = writePreLollipop(WebRtcAudioTrack.this.audioTrack, WebRtcAudioTrack.this.byteBuffer, sizeInBytes);
                    }
                    if (bytesWritten != sizeInBytes) {
                        Logging.m2188e(WebRtcAudioTrack.TAG, "AudioTrack.write failed: " + bytesWritten);
                        if (bytesWritten == -3) {
                            this.keepAlive = false;
                        }
                    }
                    WebRtcAudioTrack.this.byteBuffer.rewind();
                }
                try {
                    WebRtcAudioTrack.this.audioTrack.stop();
                } catch (IllegalStateException e) {
                    Logging.m2188e(WebRtcAudioTrack.TAG, "AudioTrack.stop failed: " + e.getMessage());
                }
                if (WebRtcAudioTrack.this.audioTrack.getPlayState() != 1) {
                    z = false;
                }
                WebRtcAudioTrack.assertTrue(z);
                WebRtcAudioTrack.this.audioTrack.flush();
            } catch (IllegalStateException e2) {
                Logging.m2188e(WebRtcAudioTrack.TAG, "AudioTrack.play failed: " + e2.getMessage());
                WebRtcAudioTrack.this.releaseAudioResources();
            }
        }

        @TargetApi(21)
        private int writeOnLollipop(AudioTrack audioTrack, ByteBuffer byteBuffer, int sizeInBytes) {
            return audioTrack.write(byteBuffer, sizeInBytes, 0);
        }

        private int writePreLollipop(AudioTrack audioTrack, ByteBuffer byteBuffer, int sizeInBytes) {
            return audioTrack.write(byteBuffer.array(), byteBuffer.arrayOffset(), sizeInBytes);
        }

        public void joinThread() {
            this.keepAlive = false;
            while (isAlive()) {
                try {
                    join();
                } catch (InterruptedException e) {
                }
            }
        }
    }

    private native void nativeCacheDirectBufferAddress(ByteBuffer byteBuffer, long j);

    private native void nativeGetPlayoutData(int i, long j);

    WebRtcAudioTrack(Context context, long nativeAudioTrack) {
        Logging.m2187d(TAG, "ctor" + WebRtcAudioUtils.getThreadInfo());
        this.context = context;
        this.nativeAudioTrack = nativeAudioTrack;
        this.audioManager = (AudioManager) context.getSystemService("audio");
    }

    private boolean initPlayout(int sampleRate, int channels) {
        Logging.m2187d(TAG, "initPlayout(sampleRate=" + sampleRate + ", channels=" + channels + ")");
        int bytesPerFrame = channels * 2;
        ByteBuffer byteBuffer = this.byteBuffer;
        this.byteBuffer = ByteBuffer.allocateDirect((sampleRate / 100) * bytesPerFrame);
        Logging.m2187d(TAG, "byteBuffer.capacity: " + this.byteBuffer.capacity());
        this.emptyBytes = new byte[this.byteBuffer.capacity()];
        nativeCacheDirectBufferAddress(this.byteBuffer, this.nativeAudioTrack);
        int channelConfig = channelCountToConfiguration(channels);
        int minBufferSizeInBytes = AudioTrack.getMinBufferSize(sampleRate, channelConfig, 2);
        Logging.m2187d(TAG, "AudioTrack.getMinBufferSize: " + minBufferSizeInBytes);
        if (minBufferSizeInBytes < this.byteBuffer.capacity()) {
            Logging.m2188e(TAG, "AudioTrack.getMinBufferSize returns an invalid value.");
            return false;
        } else if (this.audioTrack != null) {
            Logging.m2188e(TAG, "Conflict with existing AudioTrack.");
            return false;
        } else {
            try {
                this.audioTrack = new AudioTrack(0, sampleRate, channelConfig, 2, minBufferSizeInBytes, 1);
                if (this.audioTrack == null || this.audioTrack.getState() != 1) {
                    Logging.m2188e(TAG, "Initialization of audio track failed.");
                    releaseAudioResources();
                    return false;
                }
                logMainParameters();
                logMainParametersExtended();
                return true;
            } catch (IllegalArgumentException e) {
                Logging.m2187d(TAG, e.getMessage());
                releaseAudioResources();
                return false;
            }
        }
    }

    private boolean startPlayout() {
        boolean z;
        Logging.m2187d(TAG, "startPlayout");
        assertTrue(this.audioTrack != null);
        if (this.audioThread == null) {
            z = true;
        } else {
            z = false;
        }
        assertTrue(z);
        if (this.audioTrack.getState() != 1) {
            Logging.m2188e(TAG, "AudioTrack instance is not successfully initialized.");
            return false;
        }
        this.audioThread = new AudioTrackThread("AudioTrackJavaThread");
        this.audioThread.start();
        return true;
    }

    private boolean stopPlayout() {
        Logging.m2187d(TAG, "stopPlayout");
        assertTrue(this.audioThread != null);
        logUnderrunCount();
        this.audioThread.joinThread();
        this.audioThread = null;
        releaseAudioResources();
        return true;
    }

    private int getStreamMaxVolume() {
        boolean z;
        Logging.m2187d(TAG, "getStreamMaxVolume");
        if (this.audioManager != null) {
            z = true;
        } else {
            z = false;
        }
        assertTrue(z);
        return this.audioManager.getStreamMaxVolume(0);
    }

    private boolean setStreamVolume(int volume) {
        Logging.m2187d(TAG, "setStreamVolume(" + volume + ")");
        assertTrue(this.audioManager != null);
        if (isVolumeFixed()) {
            Logging.m2188e(TAG, "The device implements a fixed volume policy.");
            return false;
        }
        this.audioManager.setStreamVolume(0, volume, 0);
        return true;
    }

    private boolean isVolumeFixed() {
        if (WebRtcAudioUtils.runningOnLollipopOrHigher()) {
            return this.audioManager.isVolumeFixed();
        }
        return false;
    }

    private int getStreamVolume() {
        boolean z;
        Logging.m2187d(TAG, "getStreamVolume");
        if (this.audioManager != null) {
            z = true;
        } else {
            z = false;
        }
        assertTrue(z);
        return this.audioManager.getStreamVolume(0);
    }

    private void logMainParameters() {
        String str = TAG;
        StringBuilder append = new StringBuilder().append("AudioTrack: session ID: ").append(this.audioTrack.getAudioSessionId()).append(", channels: ").append(this.audioTrack.getChannelCount()).append(", sample rate: ").append(this.audioTrack.getSampleRate()).append(", max gain: ");
        AudioTrack audioTrack = this.audioTrack;
        Logging.m2187d(str, append.append(AudioTrack.getMaxVolume()).toString());
    }

    @TargetApi(24)
    private void logMainParametersExtended() {
        if (WebRtcAudioUtils.runningOnMarshmallowOrHigher()) {
            Logging.m2187d(TAG, "AudioTrack: buffer size in frames: " + this.audioTrack.getBufferSizeInFrames());
        }
        if (WebRtcAudioUtils.runningOnNougatOrHigher()) {
            Logging.m2187d(TAG, "AudioTrack: buffer capacity in frames: " + this.audioTrack.getBufferCapacityInFrames());
        }
    }

    @TargetApi(24)
    private void logUnderrunCount() {
        if (WebRtcAudioUtils.runningOnNougatOrHigher()) {
            Logging.m2187d(TAG, "underrun count: " + this.audioTrack.getUnderrunCount());
        }
    }

    private static void assertTrue(boolean condition) {
        if (!condition) {
            throw new AssertionError("Expected condition to be true");
        }
    }

    private int channelCountToConfiguration(int channels) {
        return channels == 1 ? 4 : 12;
    }

    public static void setSpeakerMute(boolean mute) {
        Logging.m2191w(TAG, "setSpeakerMute(" + mute + ")");
        speakerMute = mute;
    }

    private void releaseAudioResources() {
        if (this.audioTrack != null) {
            this.audioTrack.release();
            this.audioTrack = null;
        }
    }
}
