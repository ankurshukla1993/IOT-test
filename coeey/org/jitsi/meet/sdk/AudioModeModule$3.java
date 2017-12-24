package org.jitsi.meet.sdk;

import android.media.AudioDeviceCallback;
import android.media.AudioDeviceInfo;
import android.util.Log;

class AudioModeModule$3 extends AudioDeviceCallback {
    final /* synthetic */ AudioModeModule this$0;

    AudioModeModule$3(AudioModeModule this$0) {
        this.this$0 = this$0;
    }

    public void onAudioDevicesAdded(AudioDeviceInfo[] addedDevices) {
        Log.d("AudioMode", "Audio devices added");
        this.this$0.onAudioDeviceChange();
    }

    public void onAudioDevicesRemoved(AudioDeviceInfo[] removedDevices) {
        Log.d("AudioMode", "Audio devices removed");
        this.this$0.onAudioDeviceChange();
    }
}
