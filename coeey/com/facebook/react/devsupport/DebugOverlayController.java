package com.facebook.react.devsupport;

import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.FrameLayout;
import com.facebook.react.bridge.ReactContext;
import javax.annotation.Nullable;

class DebugOverlayController {
    @Nullable
    private FrameLayout mFPSDebugViewContainer;
    private final ReactContext mReactContext;
    private final WindowManager mWindowManager;

    public DebugOverlayController(ReactContext reactContext) {
        this.mReactContext = reactContext;
        this.mWindowManager = (WindowManager) reactContext.getSystemService("window");
    }

    public void setFpsDebugViewVisible(boolean fpsDebugViewVisible) {
        if (fpsDebugViewVisible && this.mFPSDebugViewContainer == null) {
            this.mFPSDebugViewContainer = new FpsView(this.mReactContext);
            this.mWindowManager.addView(this.mFPSDebugViewContainer, new LayoutParams(-1, -1, 2006, 24, -3));
        } else if (!fpsDebugViewVisible && this.mFPSDebugViewContainer != null) {
            this.mFPSDebugViewContainer.removeAllViews();
            this.mWindowManager.removeView(this.mFPSDebugViewContainer);
            this.mFPSDebugViewContainer = null;
        }
    }
}
