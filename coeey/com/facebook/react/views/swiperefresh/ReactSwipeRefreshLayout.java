package com.facebook.react.views.swiperefresh;

import android.support.v4.widget.SwipeRefreshLayout;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.events.NativeGestureUtil;

public class ReactSwipeRefreshLayout extends SwipeRefreshLayout {
    private static final float DEFAULT_CIRCLE_TARGET = 64.0f;
    private boolean mDidLayout = false;
    private boolean mIntercepted;
    private float mPrevTouchX;
    private float mProgressViewOffset = 0.0f;
    private boolean mRefreshing = false;
    private int mTouchSlop;

    public ReactSwipeRefreshLayout(ReactContext reactContext) {
        super(reactContext);
        this.mTouchSlop = ViewConfiguration.get(reactContext).getScaledTouchSlop();
    }

    public void setRefreshing(boolean refreshing) {
        this.mRefreshing = refreshing;
        if (this.mDidLayout) {
            super.setRefreshing(refreshing);
        }
    }

    public void setProgressViewOffset(float offset) {
        this.mProgressViewOffset = offset;
        if (this.mDidLayout) {
            int diameter = getProgressCircleDiameter();
            setProgressViewOffset(false, Math.round(PixelUtil.toPixelFromDIP(offset)) - diameter, Math.round(PixelUtil.toPixelFromDIP(DEFAULT_CIRCLE_TARGET + offset) - ((float) diameter)));
        }
    }

    public void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (!this.mDidLayout) {
            this.mDidLayout = true;
            setProgressViewOffset(this.mProgressViewOffset);
            setRefreshing(this.mRefreshing);
        }
    }

    public void requestDisallowInterceptTouchEvent(boolean disallowIntercept) {
        if (getParent() != null) {
            getParent().requestDisallowInterceptTouchEvent(disallowIntercept);
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (!shouldInterceptTouchEvent(ev) || !super.onInterceptTouchEvent(ev)) {
            return false;
        }
        NativeGestureUtil.notifyNativeGestureStarted(this, ev);
        return true;
    }

    private boolean shouldInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case 0:
                this.mPrevTouchX = ev.getX();
                this.mIntercepted = false;
                break;
            case 2:
                float xDiff = Math.abs(ev.getX() - this.mPrevTouchX);
                if (this.mIntercepted || xDiff > ((float) this.mTouchSlop)) {
                    this.mIntercepted = true;
                    return false;
                }
        }
        return true;
    }
}
