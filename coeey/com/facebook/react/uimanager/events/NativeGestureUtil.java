package com.facebook.react.uimanager.events;

import android.view.MotionEvent;
import android.view.View;
import com.facebook.react.uimanager.RootViewUtil;

public class NativeGestureUtil {
    public static void notifyNativeGestureStarted(View view, MotionEvent event) {
        RootViewUtil.getRootView(view).onChildStartedNativeGesture(event);
    }
}
