package com.facebook.react.views.webview.events;

import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;

public class TopLoadingFinishEvent extends Event<TopLoadingFinishEvent> {
    public static final String EVENT_NAME = "topLoadingFinish";
    private WritableMap mEventData;

    public TopLoadingFinishEvent(int viewId, WritableMap eventData) {
        super(viewId);
        this.mEventData = eventData;
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
        rctEventEmitter.receiveEvent(getViewTag(), getEventName(), this.mEventData);
    }
}
