package com.arlib.floatingsearchview.util.adapter;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnItemTouchListener;
import android.view.MotionEvent;

public abstract class OnItemTouchListenerAdapter implements OnItemTouchListener {
    private static final String TAG = "OnItemTouchListenerAdapter";

    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        return false;
    }

    public void onTouchEvent(RecyclerView rv, MotionEvent e) {
    }

    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
    }
}
