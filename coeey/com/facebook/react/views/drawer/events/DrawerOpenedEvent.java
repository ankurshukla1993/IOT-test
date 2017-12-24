package com.facebook.react.views.drawer.events;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;

public class DrawerOpenedEvent extends Event<DrawerOpenedEvent> {
    public static final String EVENT_NAME = "topDrawerOpened";

    public DrawerOpenedEvent(int viewId) {
        super(viewId);
    }

    public String getEventName() {
        return EVENT_NAME;
    }

    public short getCoalescingKey() {
        return (short) 0;
    }

    public void dispatch(RCTEventEmitter rctEventEmitter) {
        rctEventEmitter.receiveEvent(getViewTag(), getEventName(), Arguments.createMap());
    }
}
