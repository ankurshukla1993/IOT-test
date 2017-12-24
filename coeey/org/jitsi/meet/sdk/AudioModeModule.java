package org.jitsi.meet.sdk;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import java.util.HashMap;
import java.util.Map;

class AudioModeModule extends ReactContextBaseJavaModule {
    private static final String ACTION_HEADSET_PLUG = (VERSION.SDK_INT >= 21 ? "android.intent.action.HEADSET_PLUG" : "android.intent.action.HEADSET_PLUG");
    private static final int AUDIO_CALL = 1;
    private static final int DEFAULT = 0;
    private static final String MODULE_NAME = "AudioMode";
    static final String TAG = "AudioMode";
    private static final int VIDEO_CALL = 2;
    private final AudioManager audioManager;
    private BluetoothHeadsetMonitor bluetoothHeadsetMonitor;
    private final Handler mainThreadHandler = new Handler(Looper.getMainLooper());
    private final Runnable mainThreadRunner = new 1(this);
    private int mode = -1;

    public AudioModeModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.audioManager = (AudioManager) reactContext.getSystemService("audio");
        setupAudioRouteChangeDetection();
    }

    public Map<String, Object> getConstants() {
        Map<String, Object> constants = new HashMap();
        constants.put("AUDIO_CALL", Integer.valueOf(1));
        constants.put("DEFAULT", Integer.valueOf(0));
        constants.put("VIDEO_CALL", Integer.valueOf(2));
        return constants;
    }

    public String getName() {
        return "AudioMode";
    }

    void onAudioDeviceChange() {
        this.mainThreadHandler.post(this.mainThreadRunner);
    }

    private void setBluetoothAudioRoute(boolean enabled) {
        if (enabled) {
            this.audioManager.startBluetoothSco();
            this.audioManager.setBluetoothScoOn(true);
            return;
        }
        this.audioManager.setBluetoothScoOn(false);
        this.audioManager.stopBluetoothSco();
    }

    @ReactMethod
    public void setMode(int mode, Promise promise) {
        if (mode == 0 || mode == 1 || mode == 2) {
            this.mainThreadHandler.post(new 2(this, mode, promise));
            return;
        }
        promise.reject("setMode", "Invalid audio mode " + mode);
    }

    private void setupAudioRouteChangeDetection() {
        if (VERSION.SDK_INT >= 23) {
            setupAudioRouteChangeDetectionM();
        } else {
            setupAudioRouteChangeDetectionPreM();
        }
    }

    @TargetApi(23)
    private void setupAudioRouteChangeDetectionM() {
        this.audioManager.registerAudioDeviceCallback(new 3(this), null);
    }

    private void setupAudioRouteChangeDetectionPreM() {
        Context context = getReactApplicationContext();
        context.registerReceiver(new 4(this), new IntentFilter(ACTION_HEADSET_PLUG));
        this.bluetoothHeadsetMonitor = new BluetoothHeadsetMonitor(this, context);
    }

    private boolean updateAudioRoute(int mode) {
        boolean z = false;
        Log.d("AudioMode", "Update audio route for mode: " + mode);
        if (mode == 0) {
            this.audioManager.setMode(0);
            this.audioManager.abandonAudioFocus(null);
            this.audioManager.setSpeakerphoneOn(false);
            setBluetoothAudioRoute(false);
            return true;
        }
        this.audioManager.setMode(3);
        this.audioManager.setMicrophoneMute(false);
        if (this.audioManager.requestAudioFocus(null, 0, 1) == 0) {
            Log.d("AudioMode", "Audio focus request failed");
            return false;
        }
        boolean useSpeaker;
        if (mode == 2) {
            useSpeaker = true;
        } else {
            useSpeaker = false;
        }
        if (VERSION.SDK_INT < 23) {
            setBluetoothAudioRoute(this.bluetoothHeadsetMonitor.isHeadsetAvailable());
            if (this.bluetoothHeadsetMonitor.isHeadsetAvailable()) {
                useSpeaker = false;
            }
        } else if (this.audioManager.isBluetoothScoAvailableOffCall()) {
            this.audioManager.startBluetoothSco();
        }
        AudioManager audioManager = this.audioManager;
        if (!(!useSpeaker || this.audioManager.isWiredHeadsetOn() || this.audioManager.isBluetoothScoOn())) {
            z = true;
        }
        audioManager.setSpeakerphoneOn(z);
        return true;
    }
}
