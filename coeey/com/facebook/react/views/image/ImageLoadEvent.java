package com.facebook.react.views.image;

import com.RNFetchBlob.RNFetchBlobConst;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import javax.annotation.Nullable;

public class ImageLoadEvent extends Event<ImageLoadEvent> {
    public static final int ON_ERROR = 1;
    public static final int ON_LOAD = 2;
    public static final int ON_LOAD_END = 3;
    public static final int ON_LOAD_START = 4;
    public static final int ON_PROGRESS = 5;
    private final int mEventType;
    private final int mHeight;
    @Nullable
    private final String mImageUri;
    private final int mWidth;

    public ImageLoadEvent(int viewId, int eventType) {
        this(viewId, eventType, null);
    }

    public ImageLoadEvent(int viewId, int eventType, String imageUri) {
        this(viewId, eventType, imageUri, 0, 0);
    }

    public ImageLoadEvent(int viewId, int eventType, @Nullable String imageUri, int width, int height) {
        super(viewId);
        this.mEventType = eventType;
        this.mImageUri = imageUri;
        this.mWidth = width;
        this.mHeight = height;
    }

    public static String eventNameForType(int eventType) {
        switch (eventType) {
            case 1:
                return "topError";
            case 2:
                return "topLoad";
            case 3:
                return "topLoadEnd";
            case 4:
                return "topLoadStart";
            case 5:
                return "topProgress";
            default:
                throw new IllegalStateException("Invalid image event: " + Integer.toString(eventType));
        }
    }

    public String getEventName() {
        return eventNameForType(this.mEventType);
    }

    public short getCoalescingKey() {
        return (short) this.mEventType;
    }

    public void dispatch(RCTEventEmitter rctEventEmitter) {
        WritableMap eventData = null;
        if (this.mImageUri != null || this.mEventType == 2) {
            eventData = Arguments.createMap();
            if (this.mImageUri != null) {
                eventData.putString(RNFetchBlobConst.DATA_ENCODE_URI, this.mImageUri);
            }
            if (this.mEventType == 2) {
                WritableMap source = Arguments.createMap();
                source.putDouble("width", (double) this.mWidth);
                source.putDouble("height", (double) this.mHeight);
                if (this.mImageUri != null) {
                    source.putString("url", this.mImageUri);
                }
                eventData.putMap(Param.SOURCE, source);
            }
        }
        rctEventEmitter.receiveEvent(getViewTag(), getEventName(), eventData);
    }
}
