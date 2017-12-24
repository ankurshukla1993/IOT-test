package com.facebook.react.views.drawer.events;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;

public class DrawerSlideEvent extends Event<DrawerSlideEvent> {
    public static final String EVENT_NAME = "topDrawerSlide";
    private final float mOffset;

    public DrawerSlideEvent(int viewId, float offset) {
        super(viewId);
        this.mOffset = offset;
    }

    public float getOffset() {
        return this.mOffset;
    }

    public String getEventName() {
        return EVENT_NAME;
    }

    public short getCoalescingKey() {
        return (short) 0;
    }

    public void dispatch(RCTEventEmitter rctEventEmitter) {
        rctEventEmitter.receiveEvent(getViewTag(), getEventName(), serializeEventData());
    }

    private WritableMap serializeEventData() {
        WritableMap eventData = Arguments.createMap();
        eventData.putDouble("offset", (double) getOffset());
        return eventData;
    }
}
