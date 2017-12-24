package org.jitsi.meet.sdk;

import android.util.Log;
import com.facebook.react.bridge.Promise;

class AudioModeModule$2 implements Runnable {
    final /* synthetic */ AudioModeModule this$0;
    final /* synthetic */ int val$mode;
    final /* synthetic */ Promise val$promise;

    AudioModeModule$2(AudioModeModule this$0, int i, Promise promise) {
        this.this$0 = this$0;
        this.val$mode = i;
        this.val$promise = promise;
    }

    public void run() {
        boolean success;
        try {
            success = AudioModeModule.access$100(this.this$0, this.val$mode);
        } catch (Throwable e) {
            success = false;
            Log.e("AudioMode", "Failed to update audio route for mode: " + this.val$mode, e);
        }
        if (success) {
            AudioModeModule.access$002(this.this$0, this.val$mode);
            this.val$promise.resolve(null);
            return;
        }
        this.val$promise.reject("setMode", "Failed to set audio mode to " + this.val$mode);
    }
}
