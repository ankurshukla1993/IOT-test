package com.facebook.react;

import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.WindowManager;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule.RCTDeviceEventEmitter;
import com.facebook.react.uimanager.DisplayMetricsHolder;
import com.facebook.react.uimanager.PixelUtil;
import javax.annotation.Nullable;

class ReactRootView$CustomGlobalLayoutListener implements OnGlobalLayoutListener {
    private int mDeviceRotation = 0;
    private int mKeyboardHeight = 0;
    private final int mMinKeyboardHeightDetected = ((int) PixelUtil.toPixelFromDIP(60.0f));
    private final Rect mVisibleViewArea = new Rect();
    final /* synthetic */ ReactRootView this$0;

    ReactRootView$CustomGlobalLayoutListener(ReactRootView reactRootView) {
        this.this$0 = reactRootView;
    }

    public void onGlobalLayout() {
        if (ReactRootView.access$100(this.this$0) != null && ReactRootView.access$200(this.this$0) && ReactRootView.access$100(this.this$0).getCurrentReactContext() != null) {
            checkForKeyboardEvents();
            checkForDeviceOrientationChanges();
        }
    }

    private void checkForKeyboardEvents() {
        this.this$0.getRootView().getWindowVisibleDisplayFrame(this.mVisibleViewArea);
        int heightDiff = DisplayMetricsHolder.getWindowDisplayMetrics().heightPixels - this.mVisibleViewArea.bottom;
        if (this.mKeyboardHeight != heightDiff && heightDiff > this.mMinKeyboardHeightDetected) {
            this.mKeyboardHeight = heightDiff;
            WritableMap params = Arguments.createMap();
            WritableMap coordinates = Arguments.createMap();
            coordinates.putDouble("screenY", (double) PixelUtil.toDIPFromPixel((float) this.mVisibleViewArea.bottom));
            coordinates.putDouble("screenX", (double) PixelUtil.toDIPFromPixel((float) this.mVisibleViewArea.left));
            coordinates.putDouble("width", (double) PixelUtil.toDIPFromPixel((float) this.mVisibleViewArea.width()));
            coordinates.putDouble("height", (double) PixelUtil.toDIPFromPixel((float) this.mKeyboardHeight));
            params.putMap("endCoordinates", coordinates);
            sendEvent("keyboardDidShow", params);
        } else if (this.mKeyboardHeight != 0 && heightDiff <= this.mMinKeyboardHeightDetected) {
            this.mKeyboardHeight = 0;
            sendEvent("keyboardDidHide", null);
        }
    }

    private void checkForDeviceOrientationChanges() {
        int rotation = ((WindowManager) this.this$0.getContext().getSystemService("window")).getDefaultDisplay().getRotation();
        if (this.mDeviceRotation != rotation) {
            this.mDeviceRotation = rotation;
            DisplayMetricsHolder.initDisplayMetrics(this.this$0.getContext());
            emitUpdateDimensionsEvent();
            emitOrientationChanged(rotation);
        }
    }

    private void emitOrientationChanged(int newRotation) {
        String name;
        double rotationDegrees;
        boolean isLandscape = false;
        switch (newRotation) {
            case 0:
                name = "portrait-primary";
                rotationDegrees = 0.0d;
                break;
            case 1:
                name = "landscape-primary";
                rotationDegrees = -90.0d;
                isLandscape = true;
                break;
            case 2:
                name = "portrait-secondary";
                rotationDegrees = 180.0d;
                break;
            case 3:
                name = "landscape-secondary";
                rotationDegrees = 90.0d;
                isLandscape = true;
                break;
            default:
                return;
        }
        WritableMap map = Arguments.createMap();
        map.putString("name", name);
        map.putDouble("rotationDegrees", rotationDegrees);
        map.putBoolean("isLandscape", isLandscape);
        sendEvent("namedOrientationDidChange", map);
    }

    private void emitUpdateDimensionsEvent() {
        DisplayMetrics windowDisplayMetrics = DisplayMetricsHolder.getWindowDisplayMetrics();
        DisplayMetrics screenDisplayMetrics = DisplayMetricsHolder.getScreenDisplayMetrics();
        WritableMap windowDisplayMetricsMap = Arguments.createMap();
        windowDisplayMetricsMap.putInt("width", windowDisplayMetrics.widthPixels);
        windowDisplayMetricsMap.putInt("height", windowDisplayMetrics.heightPixels);
        windowDisplayMetricsMap.putDouble("scale", (double) windowDisplayMetrics.density);
        windowDisplayMetricsMap.putDouble("fontScale", (double) windowDisplayMetrics.scaledDensity);
        windowDisplayMetricsMap.putDouble("densityDpi", (double) windowDisplayMetrics.densityDpi);
        WritableMap screenDisplayMetricsMap = Arguments.createMap();
        screenDisplayMetricsMap.putInt("width", screenDisplayMetrics.widthPixels);
        screenDisplayMetricsMap.putInt("height", screenDisplayMetrics.heightPixels);
        screenDisplayMetricsMap.putDouble("scale", (double) screenDisplayMetrics.density);
        screenDisplayMetricsMap.putDouble("fontScale", (double) screenDisplayMetrics.scaledDensity);
        screenDisplayMetricsMap.putDouble("densityDpi", (double) screenDisplayMetrics.densityDpi);
        WritableMap dimensionsMap = Arguments.createMap();
        dimensionsMap.putMap("windowPhysicalPixels", windowDisplayMetricsMap);
        dimensionsMap.putMap("screenPhysicalPixels", screenDisplayMetricsMap);
        sendEvent("didUpdateDimensions", dimensionsMap);
    }

    private void sendEvent(String eventName, @Nullable WritableMap params) {
        if (ReactRootView.access$100(this.this$0) != null) {
            ((RCTDeviceEventEmitter) ReactRootView.access$100(this.this$0).getCurrentReactContext().getJSModule(RCTDeviceEventEmitter.class)).emit(eventName, params);
        }
    }
}
