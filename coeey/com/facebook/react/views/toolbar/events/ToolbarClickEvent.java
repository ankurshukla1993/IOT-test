package com.facebook.react.views.toolbar.events;

import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;

public class ToolbarClickEvent extends Event<ToolbarClickEvent> {
    private static final String EVENT_NAME = "topSelect";
    private final int position;

    public ToolbarClickEvent(int viewId, int position) {
        super(viewId);
        this.position = position;
    }

    public int getPosition() {
        return this.position;
    }

    public String getEventName() {
        return "topSelect";
    }

    public boolean canCoalesce() {
        return false;
    }

    public void dispatch(RCTEventEmitter rctEventEmitter) {
        WritableMap event = new WritableNativeMap();
        event.putInt("position", getPosition());
        rctEventEmitter.receiveEvent(getViewTag(), getEventName(), event);
    }
}
