package com.arlib.floatingsearchview.util.adapter;

import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;

public abstract class GestureDetectorListenerAdapter implements OnGestureListener {
    private static final String TAG = "GestureDetectorListenerAdapter";

    public boolean onDown(MotionEvent e) {
        return false;
    }

    public void onShowPress(MotionEvent e) {
    }

    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    public void onLongPress(MotionEvent e) {
    }

    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }
}
