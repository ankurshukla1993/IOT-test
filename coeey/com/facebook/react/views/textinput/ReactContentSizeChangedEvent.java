package com.facebook.react.views.textinput;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;

public class ReactContentSizeChangedEvent extends Event<ReactTextChangedEvent> {
    public static final String EVENT_NAME = "topContentSizeChange";
    private float mContentHeight;
    private float mContentWidth;

    public ReactContentSizeChangedEvent(int viewId, float contentSizeWidth, float contentSizeHeight) {
        super(viewId);
        this.mContentWidth = contentSizeWidth;
        this.mContentHeight = contentSizeHeight;
    }

    public String getEventName() {
        return "topContentSizeChange";
    }

    public void dispatch(RCTEventEmitter rctEventEmitter) {
        rctEventEmitter.receiveEvent(getViewTag(), getEventName(), serializeEventData());
    }

    private WritableMap serializeEventData() {
        WritableMap eventData = Arguments.createMap();
        WritableMap contentSize = Arguments.createMap();
        contentSize.putDouble("width", (double) this.mContentWidth);
        contentSize.putDouble("height", (double) this.mContentHeight);
        eventData.putMap("contentSize", contentSize);
        eventData.putInt("target", getViewTag());
        return eventData;
    }
}
