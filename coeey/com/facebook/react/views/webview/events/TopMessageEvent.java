package com.facebook.react.views.webview.events;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;

public class TopMessageEvent extends Event<TopMessageEvent> {
    public static final String EVENT_NAME = "topMessage";
    private final String mData;

    public TopMessageEvent(int viewId, String data) {
        super(viewId);
        this.mData = data;
    }

    public String getEventName() {
        return EVENT_NAME;
    }

    public boolean canCoalesce() {
        return false;
    }

    public short getCoalescingKey() {
        return (short) 0;
    }

    public void dispatch(RCTEventEmitter rctEventEmitter) {
        WritableMap data = Arguments.createMap();
        data.putString("data", this.mData);
        rctEventEmitter.receiveEvent(getViewTag(), EVENT_NAME, data);
    }
}
