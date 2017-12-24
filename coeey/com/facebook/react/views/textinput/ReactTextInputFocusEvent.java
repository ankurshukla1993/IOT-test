package com.facebook.react.views.textinput;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;

class ReactTextInputFocusEvent extends Event<ReactTextInputFocusEvent> {
    private static final String EVENT_NAME = "topFocus";

    public ReactTextInputFocusEvent(int viewId) {
        super(viewId);
    }

    public String getEventName() {
        return EVENT_NAME;
    }

    public boolean canCoalesce() {
        return false;
    }

    public void dispatch(RCTEventEmitter rctEventEmitter) {
        rctEventEmitter.receiveEvent(getViewTag(), getEventName(), serializeEventData());
    }

    private WritableMap serializeEventData() {
        WritableMap eventData = Arguments.createMap();
        eventData.putInt("target", getViewTag());
        return eventData;
    }
}
