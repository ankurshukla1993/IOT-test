package org.jitsi.meet.sdk;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

class AudioModeModule$4 extends BroadcastReceiver {
    final /* synthetic */ AudioModeModule this$0;

    AudioModeModule$4(AudioModeModule this$0) {
        this.this$0 = this$0;
    }

    public void onReceive(Context context, Intent intent) {
        Log.d("AudioMode", "Wired headset added / removed");
        this.this$0.onAudioDeviceChange();
    }
}
