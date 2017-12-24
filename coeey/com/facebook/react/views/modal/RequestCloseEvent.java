package com.facebook.react.views.modal;

import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;

class RequestCloseEvent extends Event<RequestCloseEvent> {
    public static final String EVENT_NAME = "topRequestClose";

    protected RequestCloseEvent(int viewTag) {
        super(viewTag);
    }

    public String getEventName() {
        return EVENT_NAME;
    }

    public void dispatch(RCTEventEmitter rctEventEmitter) {
        rctEventEmitter.receiveEvent(getViewTag(), getEventName(), null);
    }
}
