package com.facebook.drawee.gestures;

import android.content.Context;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import com.facebook.common.internal.VisibleForTesting;

public class GestureDetector {
    @VisibleForTesting
    long mActionDownTime;
    @VisibleForTesting
    float mActionDownX;
    @VisibleForTesting
    float mActionDownY;
    @VisibleForTesting
    ClickListener mClickListener;
    @VisibleForTesting
    boolean mIsCapturingGesture;
    @VisibleForTesting
    boolean mIsClickCandidate;
    @VisibleForTesting
    final float mSingleTapSlopPx;

    public interface ClickListener {
        boolean onClick();
    }

    public GestureDetector(Context context) {
        this.mSingleTapSlopPx = (float) ViewConfiguration.get(context).getScaledTouchSlop();
        init();
    }

    public static GestureDetector newInstance(Context context) {
        return new GestureDetector(context);
    }

    public void init() {
        this.mClickListener = null;
        reset();
    }

    public void reset() {
        this.mIsCapturingGesture = false;
        this.mIsClickCandidate = false;
    }

    public void setClickListener(ClickListener clickListener) {
        this.mClickListener = clickListener;
    }

    public boolean isCapturingGesture() {
        return this.mIsCapturingGesture;
    }

    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case 0:
                this.mIsCapturingGesture = true;
                this.mIsClickCandidate = true;
                this.mActionDownTime = event.getEventTime();
                this.mActionDownX = event.getX();
                this.mActionDownY = event.getY();
                break;
            case 1:
                this.mIsCapturingGesture = false;
                if (Math.abs(event.getX() - this.mActionDownX) > this.mSingleTapSlopPx || Math.abs(event.getY() - this.mActionDownY) > this.mSingleTapSlopPx) {
                    this.mIsClickCandidate = false;
                }
                if (this.mIsClickCandidate && event.getEventTime() - this.mActionDownTime <= ((long) ViewConfiguration.getLongPressTimeout()) && this.mClickListener != null) {
                    this.mClickListener.onClick();
                }
                this.mIsClickCandidate = false;
                break;
            case 2:
                if (Math.abs(event.getX() - this.mActionDownX) > this.mSingleTapSlopPx || Math.abs(event.getY() - this.mActionDownY) > this.mSingleTapSlopPx) {
                    this.mIsClickCandidate = false;
                    break;
                }
            case 3:
                this.mIsCapturingGesture = false;
                this.mIsClickCandidate = false;
                break;
        }
        return true;
    }
}
