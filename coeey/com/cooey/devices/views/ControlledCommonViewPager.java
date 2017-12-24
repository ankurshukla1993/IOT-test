package com.cooey.devices.views;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class ControlledCommonViewPager extends ViewPager {
    public ControlledCommonViewPager(Context context) {
        super(context);
    }

    public ControlledCommonViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public boolean onInterceptTouchEvent(MotionEvent event) {
        return false;
    }

    public boolean onTouchEvent(MotionEvent event) {
        return false;
    }
}
