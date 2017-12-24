package com.facebook.react.views.slider;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;

public class ReactSliderEvent extends Event<ReactSliderEvent> {
    public static final String EVENT_NAME = "topChange";
    private final boolean mFromUser;
    private final double mValue;

    public ReactSliderEvent(int viewId, double value, boolean fromUser) {
        super(viewId);
        this.mValue = value;
        this.mFromUser = fromUser;
    }

    public double getValue() {
        return this.mValue;
    }

    public boolean isFromUser() {
        return this.mFromUser;
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
        eventData.putDouble("value", getValue());
        eventData.putBoolean("fromUser", isFromUser());
        return eventData;
    }
}
