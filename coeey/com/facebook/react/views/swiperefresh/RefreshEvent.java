package com.facebook.react.views.swiperefresh;

import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;

public class RefreshEvent extends Event<RefreshEvent> {
    protected RefreshEvent(int viewTag) {
        super(viewTag);
    }

    public String getEventName() {
        return "topRefresh";
    }

    public void dispatch(RCTEventEmitter rctEventEmitter) {
        rctEventEmitter.receiveEvent(getViewTag(), getEventName(), null);
    }
}
