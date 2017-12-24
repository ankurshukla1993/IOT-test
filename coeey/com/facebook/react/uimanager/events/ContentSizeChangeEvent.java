package com.facebook.react.uimanager.events;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.PixelUtil;

public class ContentSizeChangeEvent extends Event<ContentSizeChangeEvent> {
    public static final String EVENT_NAME = "topContentSizeChange";
    private final int mHeight;
    private final int mWidth;

    public ContentSizeChangeEvent(int viewTag, int width, int height) {
        super(viewTag);
        this.mWidth = width;
        this.mHeight = height;
    }

    public String getEventName() {
        return "topContentSizeChange";
    }

    public void dispatch(RCTEventEmitter rctEventEmitter) {
        WritableMap data = Arguments.createMap();
        data.putDouble("width", (double) PixelUtil.toDIPFromPixel((float) this.mWidth));
        data.putDouble("height", (double) PixelUtil.toDIPFromPixel((float) this.mHeight));
        rctEventEmitter.receiveEvent(getViewTag(), "topContentSizeChange", data);
    }
}
