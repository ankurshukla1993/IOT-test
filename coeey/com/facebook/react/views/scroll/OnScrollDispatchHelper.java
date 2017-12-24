package com.facebook.react.views.scroll;

import android.os.SystemClock;

public class OnScrollDispatchHelper {
    private static final int MIN_EVENT_SEPARATION_MS = 10;
    private long mLastScrollEventTimeMs = -11;
    private int mPrevX = Integer.MIN_VALUE;
    private int mPrevY = Integer.MIN_VALUE;

    public boolean onScrollChanged(int x, int y) {
        long eventTime = SystemClock.uptimeMillis();
        boolean shouldDispatch = (eventTime - this.mLastScrollEventTimeMs <= 10 && this.mPrevX == x && this.mPrevY == y) ? false : true;
        this.mLastScrollEventTimeMs = eventTime;
        this.mPrevX = x;
        this.mPrevY = y;
        return shouldDispatch;
    }
}
