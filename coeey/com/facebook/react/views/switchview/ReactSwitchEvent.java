package com.facebook.react.views.switchview;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;

class ReactSwitchEvent extends Event<ReactSwitchEvent> {
    public static final String EVENT_NAME = "topChange";
    private final boolean mIsChecked;

    public ReactSwitchEvent(int viewId, boolean isChecked) {
        super(viewId);
        this.mIsChecked = isChecked;
    }

    public boolean getIsChecked() {
        return this.mIsChecked;
    }

    public String getEventName() {
        return "topChange";
    }

    public short getCoalescingKey() {
        return (short) 0;
    }

    public void dispatch(RCTEventEmitter rctEventEmitter) {
        rctEventEmitter.receiveEvent(getViewTag(), getEventName(), serializeEventData());
    }

    private WritableMap serializeEventData() {
        WritableMap eventData = Arguments.createMap();
        eventData.putInt("target", getViewTag());
        eventData.putBoolean("value", getIsChecked());
        return eventData;
    }
}
