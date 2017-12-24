package com.facebook.react.views.drawer.events;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;

public class DrawerStateChangedEvent extends Event<DrawerStateChangedEvent> {
    public static final String EVENT_NAME = "topDrawerStateChanged";
    private final int mDrawerState;

    public DrawerStateChangedEvent(int viewId, int drawerState) {
        super(viewId);
        this.mDrawerState = drawerState;
    }

    public int getDrawerState() {
        return this.mDrawerState;
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
        eventData.putDouble("drawerState", (double) getDrawerState());
        return eventData;
    }
}
