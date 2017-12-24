package com.facebook.react.views.textinput;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.views.text.ReactTextShadowNode;

class ReactTextInputEndEditingEvent extends Event<ReactTextInputEndEditingEvent> {
    private static final String EVENT_NAME = "topEndEditing";
    private String mText;

    public ReactTextInputEndEditingEvent(int viewId, String text) {
        super(viewId);
        this.mText = text;
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
        eventData.putString(ReactTextShadowNode.PROP_TEXT, this.mText);
        return eventData;
    }
}
