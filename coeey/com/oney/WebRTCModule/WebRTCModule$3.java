package com.oney.WebRTCModule;

import com.facebook.react.bridge.Callback;
import org.webrtc.SdpObserver;
import org.webrtc.SessionDescription;

class WebRTCModule$3 implements SdpObserver {
    final /* synthetic */ WebRTCModule this$0;
    final /* synthetic */ Callback val$callback;

    WebRTCModule$3(WebRTCModule this$0, Callback callback) {
        this.this$0 = this$0;
        this.val$callback = callback;
    }

    public void onCreateSuccess(SessionDescription sdp) {
    }

    public void onSetSuccess() {
        this.val$callback.invoke(new Object[]{Boolean.valueOf(true)});
    }

    public void onCreateFailure(String s) {
    }

    public void onSetFailure(String s) {
        this.val$callback.invoke(new Object[]{Boolean.valueOf(false), s});
    }
}
