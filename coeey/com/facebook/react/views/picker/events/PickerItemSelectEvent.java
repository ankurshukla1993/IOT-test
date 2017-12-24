package com.facebook.react.views.picker.events;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;

public class PickerItemSelectEvent extends Event<PickerItemSelectEvent> {
    public static final String EVENT_NAME = "topSelect";
    private final int mPosition;

    public PickerItemSelectEvent(int id, int position) {
        super(id);
        this.mPosition = position;
    }

    public String getEventName() {
        return EVENT_NAME;
    }

    public void dispatch(RCTEventEmitter rctEventEmitter) {
        rctEventEmitter.receiveEvent(getViewTag(), getEventName(), serializeEventData());
    }

    private WritableMap serializeEventData() {
        WritableMap eventData = Arguments.createMap();
        eventData.putInt("position", this.mPosition);
        return eventData;
    }
}
