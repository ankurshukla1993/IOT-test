package com.oney.WebRTCModule;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.WritableMap;
import org.webrtc.SdpObserver;
import org.webrtc.SessionDescription;

class WebRTCModule$1 implements SdpObserver {
    final /* synthetic */ WebRTCModule this$0;
    final /* synthetic */ Callback val$callback;

    WebRTCModule$1(WebRTCModule this$0, Callback callback) {
        this.this$0 = this$0;
        this.val$callback = callback;
    }

    public void onCreateFailure(String s) {
        this.val$callback.invoke(new Object[]{Boolean.valueOf(false), s});
    }

    public void onCreateSuccess(SessionDescription sdp) {
        WritableMap params = Arguments.createMap();
        params.putString("sdp", sdp.description);
        params.putString("type", sdp.type.canonicalForm());
        this.val$callback.invoke(new Object[]{Boolean.valueOf(true), params});
    }

    public void onSetFailure(String s) {
    }

    public void onSetSuccess() {
    }
}
