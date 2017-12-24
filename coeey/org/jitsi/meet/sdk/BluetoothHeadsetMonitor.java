package org.jitsi.meet.sdk;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothHeadset;
import android.bluetooth.BluetoothProfile;
import android.bluetooth.BluetoothProfile.ServiceListener;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

class BluetoothHeadsetMonitor {
    private final AudioModeModule audioModeModule;
    private final Context context;
    private BluetoothHeadset headset;
    private boolean headsetAvailable = false;
    private final Handler mainThreadHandler = new Handler(Looper.getMainLooper());
    private final Runnable updateDevicesRunnable = new C25491();

    class C25491 implements Runnable {
        C25491() {
        }

        public void run() {
            BluetoothHeadsetMonitor bluetoothHeadsetMonitor = BluetoothHeadsetMonitor.this;
            boolean z = (BluetoothHeadsetMonitor.this.headset == null || BluetoothHeadsetMonitor.this.headset.getConnectedDevices().isEmpty()) ? false : true;
            bluetoothHeadsetMonitor.headsetAvailable = z;
            BluetoothHeadsetMonitor.this.audioModeModule.onAudioDeviceChange();
        }
    }

    class C25502 implements ServiceListener {
        C25502() {
        }

        public void onServiceConnected(int profile, BluetoothProfile proxy) {
            if (profile == 1) {
                BluetoothHeadsetMonitor.this.headset = (BluetoothHeadset) proxy;
                BluetoothHeadsetMonitor.this.updateDevices();
            }
        }

        public void onServiceDisconnected(int profile) {
            onServiceConnected(profile, null);
        }
    }

    class C25513 extends BroadcastReceiver {
        C25513() {
        }

        public void onReceive(Context context, Intent intent) {
            BluetoothHeadsetMonitor.this.onBluetoothReceiverReceive(context, intent);
        }
    }

    public BluetoothHeadsetMonitor(AudioModeModule audioModeModule, Context context) {
        this.audioModeModule = audioModeModule;
        this.context = context;
        if (!((AudioManager) context.getSystemService("audio")).isBluetoothScoAvailableOffCall()) {
            Log.w("AudioMode", "Bluetooth SCO is not available");
        } else if (getBluetoothHeadsetProfileProxy()) {
            registerBluetoothReceiver();
            updateDevices();
        }
    }

    private boolean getBluetoothHeadsetProfileProxy() {
        BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
        if (adapter == null) {
            Log.w("AudioMode", "Device doesn't support Bluetooth");
            return false;
        }
        return adapter.getProfileProxy(this.context, new C25502(), 1);
    }

    public boolean isHeadsetAvailable() {
        return this.headsetAvailable;
    }

    private void onBluetoothReceiverReceive(Context context, Intent intent) {
        String action = intent.getAction();
        int state;
        if (action.equals("android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED")) {
            state = intent.getIntExtra("android.bluetooth.profile.extra.STATE", -99);
            switch (state) {
                case 0:
                case 2:
                    Log.d("AudioMode", "BT headset connection state changed: " + state);
                    updateDevices();
                    return;
                default:
                    return;
            }
        } else if (action.equals("android.media.ACTION_SCO_AUDIO_STATE_UPDATED")) {
            state = intent.getIntExtra("android.media.extra.SCO_AUDIO_STATE", -99);
            switch (state) {
                case 0:
                case 1:
                    Log.d("AudioMode", "BT SCO connection state changed: " + state);
                    updateDevices();
                    return;
                default:
                    return;
            }
        }
    }

    private void registerBluetoothReceiver() {
        BroadcastReceiver receiver = new C25513();
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.media.ACTION_SCO_AUDIO_STATE_UPDATED");
        filter.addAction("android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED");
        this.context.registerReceiver(receiver, filter);
    }

    private void updateDevices() {
        this.mainThreadHandler.post(this.updateDevicesRunnable);
    }
}
