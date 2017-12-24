package com.facebook.react.views.scroll;

import android.support.v4.util.Pools.SynchronizedPool;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import javax.annotation.Nullable;

public class ScrollEvent extends Event<ScrollEvent> {
    private static final SynchronizedPool<ScrollEvent> EVENTS_POOL = new SynchronizedPool(3);
    private int mContentHeight;
    private int mContentWidth;
    @Nullable
    private ScrollEventType mScrollEventType;
    private int mScrollViewHeight;
    private int mScrollViewWidth;
    private int mScrollX;
    private int mScrollY;

    public static ScrollEvent obtain(int viewTag, ScrollEventType scrollEventType, int scrollX, int scrollY, int contentWidth, int contentHeight, int scrollViewWidth, int scrollViewHeight) {
        ScrollEvent event = (ScrollEvent) EVENTS_POOL.acquire();
        if (event == null) {
            event = new ScrollEvent();
        }
        event.init(viewTag, scrollEventType, scrollX, scrollY, contentWidth, contentHeight, scrollViewWidth, scrollViewHeight);
        return event;
    }

    public void onDispose() {
        EVENTS_POOL.release(this);
    }

    private ScrollEvent() {
    }

    private void init(int viewTag, ScrollEventType scrollEventType, int scrollX, int scrollY, int contentWidth, int contentHeight, int scrollViewWidth, int scrollViewHeight) {
        super.init(viewTag);
        this.mScrollEventType = scrollEventType;
        this.mScrollX = scrollX;
        this.mScrollY = scrollY;
        this.mContentWidth = contentWidth;
        this.mContentHeight = contentHeight;
        this.mScrollViewWidth = scrollViewWidth;
        this.mScrollViewHeight = scrollViewHeight;
    }

    public String getEventName() {
        return ((ScrollEventType) Assertions.assertNotNull(this.mScrollEventType)).getJSEventName();
    }

    public short getCoalescingKey() {
        return (short) 0;
    }

    public boolean canCoalesce() {
        if (this.mScrollEventType == ScrollEventType.SCROLL) {
            return true;
        }
        return false;
    }

    public void dispatch(RCTEventEmitter rctEventEmitter) {
        rctEventEmitter.receiveEvent(getViewTag(), getEventName(), serializeEventData());
    }

    private WritableMap serializeEventData() {
        WritableMap contentInset = Arguments.createMap();
        contentInset.putDouble(ViewProps.TOP, 0.0d);
        contentInset.putDouble(ViewProps.BOTTOM, 0.0d);
        contentInset.putDouble(ViewProps.LEFT, 0.0d);
        contentInset.putDouble(ViewProps.RIGHT, 0.0d);
        WritableMap contentOffset = Arguments.createMap();
        contentOffset.putDouble("x", (double) PixelUtil.toDIPFromPixel((float) this.mScrollX));
        contentOffset.putDouble("y", (double) PixelUtil.toDIPFromPixel((float) this.mScrollY));
        WritableMap contentSize = Arguments.createMap();
        contentSize.putDouble("width", (double) PixelUtil.toDIPFromPixel((float) this.mContentWidth));
        contentSize.putDouble("height", (double) PixelUtil.toDIPFromPixel((float) this.mContentHeight));
        WritableMap layoutMeasurement = Arguments.createMap();
        layoutMeasurement.putDouble("width", (double) PixelUtil.toDIPFromPixel((float) this.mScrollViewWidth));
        layoutMeasurement.putDouble("height", (double) PixelUtil.toDIPFromPixel((float) this.mScrollViewHeight));
        WritableMap event = Arguments.createMap();
        event.putMap("contentInset", contentInset);
        event.putMap("contentOffset", contentOffset);
        event.putMap("contentSize", contentSize);
        event.putMap("layoutMeasurement", layoutMeasurement);
        event.putInt("target", getViewTag());
        event.putBoolean("responderIgnoreScroll", true);
        return event;
    }
}
