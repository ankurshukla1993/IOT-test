package com.facebook.react.views.viewpager;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;

class PageScrollEvent extends Event<PageScrollEvent> {
    public static final String EVENT_NAME = "topPageScroll";
    private final float mOffset;
    private final int mPosition;

    protected PageScrollEvent(int viewTag, int position, float offset) {
        super(viewTag);
        this.mPosition = position;
        if (Float.isInfinite(offset) || Float.isNaN(offset)) {
            offset = 0.0f;
        }
        this.mOffset = offset;
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
        eventData.putDouble("offset", (double) this.mOffset);
        return eventData;
    }
}
