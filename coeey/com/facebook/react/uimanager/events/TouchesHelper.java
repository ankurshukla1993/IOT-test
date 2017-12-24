package com.facebook.react.uimanager.events;

import android.view.MotionEvent;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.PixelUtil;

class TouchesHelper {
    private static final String LOCATION_X_KEY = "locationX";
    private static final String LOCATION_Y_KEY = "locationY";
    private static final String PAGE_X_KEY = "pageX";
    private static final String PAGE_Y_KEY = "pageY";
    private static final String POINTER_IDENTIFIER_KEY = "identifier";
    private static final String TARGET_KEY = "target";
    private static final String TIMESTAMP_KEY = "timestamp";

    TouchesHelper() {
    }

    private static WritableArray createsPointersArray(int reactTarget, TouchEvent event) {
        WritableArray touches = Arguments.createArray();
        MotionEvent motionEvent = event.getMotionEvent();
        float targetViewCoordinateX = motionEvent.getX() - event.getViewX();
        float targetViewCoordinateY = motionEvent.getY() - event.getViewY();
        for (int index = 0; index < motionEvent.getPointerCount(); index++) {
            WritableMap touch = Arguments.createMap();
            touch.putDouble(PAGE_X_KEY, (double) PixelUtil.toDIPFromPixel(motionEvent.getX(index)));
            touch.putDouble(PAGE_Y_KEY, (double) PixelUtil.toDIPFromPixel(motionEvent.getY(index)));
            float locationY = motionEvent.getY(index) - targetViewCoordinateY;
            touch.putDouble(LOCATION_X_KEY, (double) PixelUtil.toDIPFromPixel(motionEvent.getX(index) - targetViewCoordinateX));
            touch.putDouble(LOCATION_Y_KEY, (double) PixelUtil.toDIPFromPixel(locationY));
            touch.putInt(TARGET_KEY, reactTarget);
            touch.putDouble("timestamp", (double) event.getTimestampMs());
            touch.putDouble("identifier", (double) motionEvent.getPointerId(index));
            touches.pushMap(touch);
        }
        return touches;
    }

    public static void sendTouchEvent(RCTEventEmitter rctEventEmitter, TouchEventType type, int reactTarget, TouchEvent touchEvent) {
        WritableArray pointers = createsPointersArray(reactTarget, touchEvent);
        MotionEvent motionEvent = touchEvent.getMotionEvent();
        WritableArray changedIndices = Arguments.createArray();
        if (type == TouchEventType.MOVE || type == TouchEventType.CANCEL) {
            for (int i = 0; i < motionEvent.getPointerCount(); i++) {
                changedIndices.pushInt(i);
            }
        } else if (type == TouchEventType.START || type == TouchEventType.END) {
            changedIndices.pushInt(motionEvent.getActionIndex());
        } else {
            throw new RuntimeException("Unknown touch type: " + type);
        }
        rctEventEmitter.receiveTouches(type.getJSEventName(), pointers, changedIndices);
    }
}
