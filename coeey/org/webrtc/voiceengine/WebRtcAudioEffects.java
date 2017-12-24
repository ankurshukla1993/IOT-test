package org.webrtc.voiceengine;

import android.annotation.TargetApi;
import android.media.audiofx.AcousticEchoCanceler;
import android.media.audiofx.AudioEffect;
import android.media.audiofx.AudioEffect.Descriptor;
import android.media.audiofx.NoiseSuppressor;
import android.os.Build;
import com.facebook.react.uimanager.ViewProps;
import java.util.UUID;
import org.webrtc.Logging;

class WebRtcAudioEffects {
    private static final UUID AOSP_ACOUSTIC_ECHO_CANCELER = UUID.fromString("bb392ec0-8d4d-11e0-a896-0002a5d5c51b");
    private static final UUID AOSP_NOISE_SUPPRESSOR = UUID.fromString("c06c8400-8e06-11e0-9cb6-0002a5d5c51b");
    private static final boolean DEBUG = false;
    private static final String TAG = "WebRtcAudioEffects";
    private static Descriptor[] cachedEffects = null;
    private AcousticEchoCanceler aec = null;
    private NoiseSuppressor ns = null;
    private boolean shouldEnableAec = false;
    private boolean shouldEnableNs = false;

    public static boolean isAcousticEchoCancelerSupported() {
        return WebRtcAudioUtils.runningOnJellyBeanOrHigher() && isAcousticEchoCancelerEffectAvailable();
    }

    public static boolean isNoiseSuppressorSupported() {
        return WebRtcAudioUtils.runningOnJellyBeanOrHigher() && isNoiseSuppressorEffectAvailable();
    }

    public static boolean isAcousticEchoCancelerBlacklisted() {
        boolean isBlacklisted = WebRtcAudioUtils.getBlackListedModelsForAecUsage().contains(Build.MODEL);
        if (isBlacklisted) {
            Logging.m2191w(TAG, Build.MODEL + " is blacklisted for HW AEC usage!");
        }
        return isBlacklisted;
    }

    public static boolean isNoiseSuppressorBlacklisted() {
        boolean isBlacklisted = WebRtcAudioUtils.getBlackListedModelsForNsUsage().contains(Build.MODEL);
        if (isBlacklisted) {
            Logging.m2191w(TAG, Build.MODEL + " is blacklisted for HW NS usage!");
        }
        return isBlacklisted;
    }

    @TargetApi(18)
    private static boolean isAcousticEchoCancelerExcludedByUUID() {
        for (Descriptor d : getAvailableEffects()) {
            if (d.type.equals(AudioEffect.EFFECT_TYPE_AEC) && d.uuid.equals(AOSP_ACOUSTIC_ECHO_CANCELER)) {
                return true;
            }
        }
        return false;
    }

    @TargetApi(18)
    private static boolean isNoiseSuppressorExcludedByUUID() {
        for (Descriptor d : getAvailableEffects()) {
            if (d.type.equals(AudioEffect.EFFECT_TYPE_NS) && d.uuid.equals(AOSP_NOISE_SUPPRESSOR)) {
                return true;
            }
        }
        return false;
    }

    @TargetApi(18)
    private static boolean isAcousticEchoCancelerEffectAvailable() {
        return isEffectTypeAvailable(AudioEffect.EFFECT_TYPE_AEC);
    }

    @TargetApi(18)
    private static boolean isNoiseSuppressorEffectAvailable() {
        return isEffectTypeAvailable(AudioEffect.EFFECT_TYPE_NS);
    }

    public static boolean canUseAcousticEchoCanceler() {
        boolean canUseAcousticEchoCanceler = (!isAcousticEchoCancelerSupported() || WebRtcAudioUtils.useWebRtcBasedAcousticEchoCanceler() || isAcousticEchoCancelerBlacklisted() || isAcousticEchoCancelerExcludedByUUID()) ? false : true;
        Logging.m2187d(TAG, "canUseAcousticEchoCanceler: " + canUseAcousticEchoCanceler);
        return canUseAcousticEchoCanceler;
    }

    public static boolean canUseNoiseSuppressor() {
        boolean canUseNoiseSuppressor = (!isNoiseSuppressorSupported() || WebRtcAudioUtils.useWebRtcBasedNoiseSuppressor() || isNoiseSuppressorBlacklisted() || isNoiseSuppressorExcludedByUUID()) ? false : true;
        Logging.m2187d(TAG, "canUseNoiseSuppressor: " + canUseNoiseSuppressor);
        return canUseNoiseSuppressor;
    }

    static WebRtcAudioEffects create() {
        if (WebRtcAudioUtils.runningOnJellyBeanOrHigher()) {
            return new WebRtcAudioEffects();
        }
        Logging.m2191w(TAG, "API level 16 or higher is required!");
        return null;
    }

    private WebRtcAudioEffects() {
        Logging.m2187d(TAG, "ctor" + WebRtcAudioUtils.getThreadInfo());
    }

    public boolean setAEC(boolean enable) {
        Logging.m2187d(TAG, "setAEC(" + enable + ")");
        if (!canUseAcousticEchoCanceler()) {
            Logging.m2191w(TAG, "Platform AEC is not supported");
            this.shouldEnableAec = false;
            return false;
        } else if (this.aec == null || enable == this.shouldEnableAec) {
            this.shouldEnableAec = enable;
            return true;
        } else {
            Logging.m2188e(TAG, "Platform AEC state can't be modified while recording");
            return false;
        }
    }

    public boolean setNS(boolean enable) {
        Logging.m2187d(TAG, "setNS(" + enable + ")");
        if (!canUseNoiseSuppressor()) {
            Logging.m2191w(TAG, "Platform NS is not supported");
            this.shouldEnableNs = false;
            return false;
        } else if (this.ns == null || enable == this.shouldEnableNs) {
            this.shouldEnableNs = enable;
            return true;
        } else {
            Logging.m2188e(TAG, "Platform NS state can't be modified while recording");
            return false;
        }
    }

    public void enable(int audioSession) {
        boolean z;
        boolean enable;
        Logging.m2187d(TAG, "enable(audioSession=" + audioSession + ")");
        if (this.aec == null) {
            z = true;
        } else {
            z = false;
        }
        assertTrue(z);
        if (this.ns == null) {
            z = true;
        } else {
            z = false;
        }
        assertTrue(z);
        for (Descriptor d : AudioEffect.queryEffects()) {
            if (effectTypeIsVoIP(d.type)) {
                Logging.m2187d(TAG, "name: " + d.name + ", mode: " + d.connectMode + ", implementor: " + d.implementor + ", UUID: " + d.uuid);
            }
        }
        if (isAcousticEchoCancelerSupported()) {
            this.aec = AcousticEchoCanceler.create(audioSession);
            if (this.aec != null) {
                boolean enabled = this.aec.getEnabled();
                if (this.shouldEnableAec && canUseAcousticEchoCanceler()) {
                    enable = true;
                } else {
                    enable = false;
                }
                if (this.aec.setEnabled(enable) != 0) {
                    Logging.m2188e(TAG, "Failed to set the AcousticEchoCanceler state");
                }
                Logging.m2187d(TAG, "AcousticEchoCanceler: was " + (enabled ? ViewProps.ENABLED : "disabled") + ", enable: " + enable + ", is now: " + (this.aec.getEnabled() ? ViewProps.ENABLED : "disabled"));
            } else {
                Logging.m2188e(TAG, "Failed to create the AcousticEchoCanceler instance");
            }
        }
        if (isNoiseSuppressorSupported()) {
            this.ns = NoiseSuppressor.create(audioSession);
            if (this.ns != null) {
                enabled = this.ns.getEnabled();
                if (this.shouldEnableNs && canUseNoiseSuppressor()) {
                    enable = true;
                } else {
                    enable = false;
                }
                if (this.ns.setEnabled(enable) != 0) {
                    Logging.m2188e(TAG, "Failed to set the NoiseSuppressor state");
                }
                Logging.m2187d(TAG, "NoiseSuppressor: was " + (enabled ? ViewProps.ENABLED : "disabled") + ", enable: " + enable + ", is now: " + (this.ns.getEnabled() ? ViewProps.ENABLED : "disabled"));
                return;
            }
            Logging.m2188e(TAG, "Failed to create the NoiseSuppressor instance");
        }
    }

    public void release() {
        Logging.m2187d(TAG, "release");
        if (this.aec != null) {
            this.aec.release();
            this.aec = null;
        }
        if (this.ns != null) {
            this.ns.release();
            this.ns = null;
        }
    }

    @TargetApi(18)
    private boolean effectTypeIsVoIP(UUID type) {
        if (!WebRtcAudioUtils.runningOnJellyBeanMR2OrHigher()) {
            return false;
        }
        if ((AudioEffect.EFFECT_TYPE_AEC.equals(type) && isAcousticEchoCancelerSupported()) || (AudioEffect.EFFECT_TYPE_NS.equals(type) && isNoiseSuppressorSupported())) {
            return true;
        }
        return false;
    }

    private static void assertTrue(boolean condition) {
        if (!condition) {
            throw new AssertionError("Expected condition to be true");
        }
    }

    private static Descriptor[] getAvailableEffects() {
        if (cachedEffects != null) {
            return cachedEffects;
        }
        cachedEffects = AudioEffect.queryEffects();
        return cachedEffects;
    }

    private static boolean isEffectTypeAvailable(UUID effectType) {
        Descriptor[] effects = getAvailableEffects();
        if (effects == null) {
            return false;
        }
        for (Descriptor d : effects) {
            if (d.type.equals(effectType)) {
                return true;
            }
        }
        return false;
    }
}
