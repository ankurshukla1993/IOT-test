package com.facebook.react.views.textinput;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;

class ReactTextInputSelectionEvent extends Event<ReactTextInputSelectionEvent> {
    private static final String EVENT_NAME = "topSelectionChange";
    private int mSelectionEnd;
    private int mSelectionStart;

    public ReactTextInputSelectionEvent(int viewId, int selectionStart, int selectionEnd) {
        super(viewId);
        this.mSelectionStart = selectionStart;
        this.mSelectionEnd = selectionEnd;
    }

    public String getEventName() {
        return EVENT_NAME;
    }

    public void dispatch(RCTEventEmitter rctEventEmitter) {
        rctEventEmitter.receiveEvent(getViewTag(), getEventName(), serializeEventData());
    }

    private WritableMap serializeEventData() {
        WritableMap eventData = Arguments.createMap();
        WritableMap selectionData = Arguments.createMap();
        selectionData.putInt("end", this.mSelectionEnd);
        selectionData.putInt("start", this.mSelectionStart);
        eventData.putMap("selection", selectionData);
        return eventData;
    }
}
