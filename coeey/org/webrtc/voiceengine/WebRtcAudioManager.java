package org.webrtc.voiceengine;

import android.annotation.TargetApi;
import android.content.Context;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.AudioTrack;
import android.os.Build;
import com.evernote.android.job.JobRequest;
import java.util.Timer;
import java.util.TimerTask;
import org.webrtc.Logging;

public class WebRtcAudioManager {
    private static final String[] AUDIO_MODES = new String[]{"MODE_NORMAL", "MODE_RINGTONE", "MODE_IN_CALL", "MODE_IN_COMMUNICATION"};
    private static final int BITS_PER_SAMPLE = 16;
    private static final boolean DEBUG = false;
    private static final int DEFAULT_FRAME_PER_BUFFER = 256;
    private static final String TAG = "WebRtcAudioManager";
    private static boolean blacklistDeviceForOpenSLESUsage = false;
    private static boolean blacklistDeviceForOpenSLESUsageIsOverridden = false;
    private static boolean useStereoInput = false;
    private static boolean useStereoOutput = false;
    private final AudioManager audioManager;
    private final Context context;
    private boolean hardwareAEC;
    private boolean hardwareAGC;
    private boolean hardwareNS;
    private boolean initialized = false;
    private int inputBufferSize;
    private int inputChannels;
    private boolean lowLatencyInput;
    private boolean lowLatencyOutput;
    private final long nativeAudioManager;
    private int nativeChannels;
    private int nativeSampleRate;
    private int outputBufferSize;
    private int outputChannels;
    private boolean proAudio;
    private int sampleRate;
    private final VolumeLogger volumeLogger;

    private static class VolumeLogger {
        private static final String THREAD_NAME = "WebRtcVolumeLevelLoggerThread";
        private static final int TIMER_PERIOD_IN_SECONDS = 30;
        private final AudioManager audioManager;
        private Timer timer;

        private class LogVolumeTask extends TimerTask {
            private final int maxRingVolume;
            private final int maxVoiceCallVolume;

            LogVolumeTask(int maxRingVolume, int maxVoiceCallVolume) {
                this.maxRingVolume = maxRingVolume;
                this.maxVoiceCallVolume = maxVoiceCallVolume;
            }

            public void run() {
                int mode = VolumeLogger.this.audioManager.getMode();
                if (mode == 1) {
                    Logging.m2187d(WebRtcAudioManager.TAG, "STREAM_RING stream volume: " + VolumeLogger.this.audioManager.getStreamVolume(2) + " (max=" + this.maxRingVolume + ")");
                } else if (mode == 3) {
                    Logging.m2187d(WebRtcAudioManager.TAG, "VOICE_CALL stream volume: " + VolumeLogger.this.audioManager.getStreamVolume(0) + " (max=" + this.maxVoiceCallVolume + ")");
                }
            }
        }

        public VolumeLogger(AudioManager audioManager) {
            this.audioManager = audioManager;
        }

        public void start() {
            this.timer = new Timer(THREAD_NAME);
            this.timer.schedule(new LogVolumeTask(this.audioManager.getStreamMaxVolume(2), this.audioManager.getStreamMaxVolume(0)), 0, JobRequest.DEFAULT_BACKOFF_MS);
        }

        private void stop() {
            if (this.timer != null) {
                this.timer.cancel();
                this.timer = null;
            }
        }
    }

    private native void nativeCacheAudioParameters(int i, int i2, int i3, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, int i4, int i5, long j);

    public static synchronized void setBlacklistDeviceForOpenSLESUsage(boolean enable) {
        synchronized (WebRtcAudioManager.class) {
            blacklistDeviceForOpenSLESUsageIsOverridden = true;
            blacklistDeviceForOpenSLESUsage = enable;
        }
    }

    public static synchronized void setStereoOutput(boolean enable) {
        synchronized (WebRtcAudioManager.class) {
            Logging.m2191w(TAG, "Overriding default output behavior: setStereoOutput(" + enable + ')');
            useStereoOutput = enable;
        }
    }

    public static synchronized void setStereoInput(boolean enable) {
        synchronized (WebRtcAudioManager.class) {
            Logging.m2191w(TAG, "Overriding default input behavior: setStereoInput(" + enable + ')');
            useStereoInput = enable;
        }
    }

    public static synchronized boolean getStereoOutput() {
        boolean z;
        synchronized (WebRtcAudioManager.class) {
            z = useStereoOutput;
        }
        return z;
    }

    public static synchronized boolean getStereoInput() {
        boolean z;
        synchronized (WebRtcAudioManager.class) {
            z = useStereoInput;
        }
        return z;
    }

    WebRtcAudioManager(Context context, long nativeAudioManager) {
        Logging.m2187d(TAG, "ctor" + WebRtcAudioUtils.getThreadInfo());
        this.context = context;
        this.nativeAudioManager = nativeAudioManager;
        this.audioManager = (AudioManager) context.getSystemService("audio");
        this.volumeLogger = new VolumeLogger(this.audioManager);
        storeAudioParameters();
        nativeCacheAudioParameters(this.sampleRate, this.outputChannels, this.inputChannels, this.hardwareAEC, this.hardwareAGC, this.hardwareNS, this.lowLatencyOutput, this.lowLatencyInput, this.proAudio, this.outputBufferSize, this.inputBufferSize, nativeAudioManager);
    }

    private boolean init() {
        Logging.m2187d(TAG, "init" + WebRtcAudioUtils.getThreadInfo());
        if (!this.initialized) {
            Logging.m2187d(TAG, "audio mode is: " + AUDIO_MODES[this.audioManager.getMode()]);
            this.initialized = true;
            this.volumeLogger.start();
        }
        return true;
    }

    private void dispose() {
        Logging.m2187d(TAG, "dispose" + WebRtcAudioUtils.getThreadInfo());
        if (this.initialized) {
            this.volumeLogger.stop();
        }
    }

    private boolean isCommunicationModeEnabled() {
        return this.audioManager.getMode() == 3;
    }

    private boolean isDeviceBlacklistedForOpenSLESUsage() {
        boolean blacklisted;
        if (blacklistDeviceForOpenSLESUsageIsOverridden) {
            blacklisted = blacklistDeviceForOpenSLESUsage;
        } else {
            blacklisted = WebRtcAudioUtils.deviceIsBlacklistedForOpenSLESUsage();
        }
        if (blacklisted) {
            Logging.m2188e(TAG, Build.MODEL + " is blacklisted for OpenSL ES usage!");
        }
        return blacklisted;
    }

    private void storeAudioParameters() {
        int lowLatencyOutputFramesPerBuffer;
        int i = 2;
        this.outputChannels = getStereoOutput() ? 2 : 1;
        if (!getStereoInput()) {
            i = 1;
        }
        this.inputChannels = i;
        this.sampleRate = getNativeOutputSampleRate();
        this.hardwareAEC = isAcousticEchoCancelerSupported();
        this.hardwareAGC = false;
        this.hardwareNS = isNoiseSuppressorSupported();
        this.lowLatencyOutput = isLowLatencyOutputSupported();
        this.lowLatencyInput = isLowLatencyInputSupported();
        this.proAudio = isProAudioSupported();
        if (this.lowLatencyOutput) {
            lowLatencyOutputFramesPerBuffer = getLowLatencyOutputFramesPerBuffer();
        } else {
            lowLatencyOutputFramesPerBuffer = getMinOutputFrameSize(this.sampleRate, this.outputChannels);
        }
        this.outputBufferSize = lowLatencyOutputFramesPerBuffer;
        if (this.lowLatencyInput) {
            lowLatencyOutputFramesPerBuffer = getLowLatencyInputFramesPerBuffer();
        } else {
            lowLatencyOutputFramesPerBuffer = getMinInputFrameSize(this.sampleRate, this.inputChannels);
        }
        this.inputBufferSize = lowLatencyOutputFramesPerBuffer;
    }

    private boolean hasEarpiece() {
        return this.context.getPackageManager().hasSystemFeature("android.hardware.telephony");
    }

    private boolean isLowLatencyOutputSupported() {
        return isOpenSLESSupported() && this.context.getPackageManager().hasSystemFeature("android.hardware.audio.low_latency");
    }

    public boolean isLowLatencyInputSupported() {
        return WebRtcAudioUtils.runningOnLollipopOrHigher() && isLowLatencyOutputSupported();
    }

    @TargetApi(23)
    private boolean isProAudioSupported() {
        return WebRtcAudioUtils.runningOnMarshmallowOrHigher() && this.context.getPackageManager().hasSystemFeature("android.hardware.audio.pro");
    }

    private int getNativeOutputSampleRate() {
        if (WebRtcAudioUtils.runningOnEmulator()) {
            Logging.m2187d(TAG, "Running emulator, overriding sample rate to 8 kHz.");
            return 8000;
        } else if (WebRtcAudioUtils.isDefaultSampleRateOverridden()) {
            Logging.m2187d(TAG, "Default sample rate is overriden to " + WebRtcAudioUtils.getDefaultSampleRateHz() + " Hz");
            return WebRtcAudioUtils.getDefaultSampleRateHz();
        } else {
            int sampleRateHz;
            if (WebRtcAudioUtils.runningOnJellyBeanMR1OrHigher()) {
                sampleRateHz = getSampleRateOnJellyBeanMR10OrHigher();
            } else {
                sampleRateHz = WebRtcAudioUtils.getDefaultSampleRateHz();
            }
            Logging.m2187d(TAG, "Sample rate is set to " + sampleRateHz + " Hz");
            return sampleRateHz;
        }
    }

    @TargetApi(17)
    private int getSampleRateOnJellyBeanMR10OrHigher() {
        String sampleRateString = this.audioManager.getProperty("android.media.property.OUTPUT_SAMPLE_RATE");
        if (sampleRateString == null) {
            return WebRtcAudioUtils.getDefaultSampleRateHz();
        }
        return Integer.parseInt(sampleRateString);
    }

    @TargetApi(17)
    private int getLowLatencyOutputFramesPerBuffer() {
        assertTrue(isLowLatencyOutputSupported());
        if (!WebRtcAudioUtils.runningOnJellyBeanMR1OrHigher()) {
            return 256;
        }
        String framesPerBuffer = this.audioManager.getProperty("android.media.property.OUTPUT_FRAMES_PER_BUFFER");
        if (framesPerBuffer != null) {
            return Integer.parseInt(framesPerBuffer);
        }
        return 256;
    }

    private static boolean isAcousticEchoCancelerSupported() {
        return WebRtcAudioEffects.canUseAcousticEchoCanceler();
    }

    private static boolean isNoiseSuppressorSupported() {
        return WebRtcAudioEffects.canUseNoiseSuppressor();
    }

    private static int getMinOutputFrameSize(int sampleRateInHz, int numChannels) {
        return AudioTrack.getMinBufferSize(sampleRateInHz, numChannels == 1 ? 4 : 12, 2) / (numChannels * 2);
    }

    private int getLowLatencyInputFramesPerBuffer() {
        assertTrue(isLowLatencyInputSupported());
        return getLowLatencyOutputFramesPerBuffer();
    }

    private static int getMinInputFrameSize(int sampleRateInHz, int numChannels) {
        return AudioRecord.getMinBufferSize(sampleRateInHz, numChannels == 1 ? 16 : 12, 2) / (numChannels * 2);
    }

    private static boolean isOpenSLESSupported() {
        return WebRtcAudioUtils.runningOnGingerBreadOrHigher();
    }

    private static void assertTrue(boolean condition) {
        if (!condition) {
            throw new AssertionError("Expected condition to be true");
        }
    }
}
