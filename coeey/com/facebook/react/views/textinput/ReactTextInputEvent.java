package com.facebook.react.views.textinput;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.views.text.ReactTextShadowNode;

public class ReactTextInputEvent extends Event<ReactTextInputEvent> {
    public static final String EVENT_NAME = "topTextInput";
    private String mPreviousText;
    private int mRangeEnd;
    private int mRangeStart;
    private String mText;

    public ReactTextInputEvent(int viewId, String text, String previousText, int rangeStart, int rangeEnd) {
        super(viewId);
        this.mText = text;
        this.mPreviousText = previousText;
        this.mRangeStart = rangeStart;
        this.mRangeEnd = rangeEnd;
    }

    public String getEventName() {
        return EVENT_NAME;
    }

    public boolean canCoalesce() {
        return false;
    }

    public void dispatch(RCTEventEmitter rctEventEmitter) {
        rctEventEmitter.receiveEvent(getViewTag(), getEventName(), serializeEventData());
    }

    private WritableMap serializeEventData() {
        WritableMap eventData = Arguments.createMap();
        WritableMap range = Arguments.createMap();
        range.putDouble("start", (double) this.mRangeStart);
        range.putDouble("end", (double) this.mRangeEnd);
        eventData.putString(ReactTextShadowNode.PROP_TEXT, this.mText);
        eventData.putString("previousText", this.mPreviousText);
        eventData.putMap("range", range);
        eventData.putInt("target", getViewTag());
        return eventData;
    }
}
