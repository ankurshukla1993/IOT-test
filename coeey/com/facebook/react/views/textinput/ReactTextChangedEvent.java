package com.facebook.react.views.textinput;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.views.text.ReactTextShadowNode;

public class ReactTextChangedEvent extends Event<ReactTextChangedEvent> {
    public static final String EVENT_NAME = "topChange";
    private float mContentHeight;
    private float mContentWidth;
    private int mEventCount;
    private String mText;

    public ReactTextChangedEvent(int viewId, String text, float contentSizeWidth, float contentSizeHeight, int eventCount) {
        super(viewId);
        this.mText = text;
        this.mContentWidth = contentSizeWidth;
        this.mContentHeight = contentSizeHeight;
        this.mEventCount = eventCount;
    }

    public String getEventName() {
        return "topChange";
    }

    public void dispatch(RCTEventEmitter rctEventEmitter) {
        rctEventEmitter.receiveEvent(getViewTag(), getEventName(), serializeEventData());
    }

    private WritableMap serializeEventData() {
        WritableMap eventData = Arguments.createMap();
        eventData.putString(ReactTextShadowNode.PROP_TEXT, this.mText);
        WritableMap contentSize = Arguments.createMap();
        contentSize.putDouble("width", (double) this.mContentWidth);
        contentSize.putDouble("height", (double) this.mContentHeight);
        eventData.putMap("contentSize", contentSize);
        eventData.putInt("eventCount", this.mEventCount);
        eventData.putInt("target", getViewTag());
        return eventData;
    }
}
