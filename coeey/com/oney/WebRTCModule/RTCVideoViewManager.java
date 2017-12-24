package com.oney.WebRTCModule;

import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import org.webrtc.MediaStream;

public class RTCVideoViewManager extends SimpleViewManager<WebRTCView> {
    private static final String REACT_CLASS = "RTCVideoView";
    private ThemedReactContext mContext;

    public String getName() {
        return REACT_CLASS;
    }

    public WebRTCView createViewInstance(ThemedReactContext context) {
        this.mContext = context;
        return new WebRTCView(context);
    }

    @ReactProp(name = "mirror")
    public void setMirror(WebRTCView view, boolean mirror) {
        view.setMirror(mirror);
    }

    @ReactProp(name = "objectFit")
    public void setObjectFit(WebRTCView view, String objectFit) {
        view.setObjectFit(objectFit);
    }

    @ReactProp(name = "streamURL")
    public void setStreamURL(WebRTCView view, String streamURL) {
        MediaStream mediaStream;
        if (streamURL == null) {
            mediaStream = null;
        } else {
            mediaStream = ((WebRTCModule) this.mContext.getNativeModule(WebRTCModule.class)).getStreamForReactTag(streamURL);
        }
        view.setStream(mediaStream);
    }

    @ReactProp(name = "zOrder")
    public void setZOrder(WebRTCView view, int zOrder) {
        view.setZOrder(zOrder);
    }
}
