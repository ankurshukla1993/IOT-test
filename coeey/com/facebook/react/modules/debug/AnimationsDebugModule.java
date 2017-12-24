package com.facebook.react.modules.debug;

import android.os.Build.VERSION;
import android.view.Choreographer;
import android.widget.Toast;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.JSApplicationCausedNativeException;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.module.annotations.ReactModule;
import java.util.Locale;
import javax.annotation.Nullable;

@ReactModule(name = "AnimationsDebugModule")
public class AnimationsDebugModule extends ReactContextBaseJavaModule {
    protected static final String NAME = "AnimationsDebugModule";
    @Nullable
    private final DeveloperSettings mCatalystSettings;
    @Nullable
    private FpsDebugFrameCallback mFrameCallback;

    public AnimationsDebugModule(ReactApplicationContext reactContext, DeveloperSettings catalystSettings) {
        super(reactContext);
        this.mCatalystSettings = catalystSettings;
    }

    public String getName() {
        return NAME;
    }

    @ReactMethod
    public void startRecordingFps() {
        if (this.mCatalystSettings != null && this.mCatalystSettings.isAnimationFpsDebugEnabled()) {
            if (this.mFrameCallback != null) {
                throw new JSApplicationCausedNativeException("Already recording FPS!");
            }
            checkAPILevel();
            this.mFrameCallback = new FpsDebugFrameCallback(Choreographer.getInstance(), getReactApplicationContext());
            this.mFrameCallback.startAndRecordFpsAtEachFrame();
        }
    }

    @ReactMethod
    public void stopRecordingFps(double animationStopTimeMs) {
        if (this.mFrameCallback != null) {
            checkAPILevel();
            this.mFrameCallback.stop();
            if (this.mFrameCallback.getFpsInfo((long) animationStopTimeMs) == null) {
                Toast.makeText(getReactApplicationContext(), "Unable to get FPS info", 1);
            } else {
                String fpsString = String.format(Locale.US, "FPS: %.2f, %d frames (%d expected)", new Object[]{Double.valueOf(this.mFrameCallback.getFpsInfo((long) animationStopTimeMs).fps), Integer.valueOf(this.mFrameCallback.getFpsInfo((long) animationStopTimeMs).totalFrames), Integer.valueOf(this.mFrameCallback.getFpsInfo((long) animationStopTimeMs).totalExpectedFrames)});
                String debugString = fpsString + "\n" + String.format(Locale.US, "JS FPS: %.2f, %d frames (%d expected)", new Object[]{Double.valueOf(fpsInfo.jsFps), Integer.valueOf(fpsInfo.totalJsFrames), Integer.valueOf(fpsInfo.totalExpectedFrames)}) + "\n" + "Total Time MS: " + String.format(Locale.US, "%d", new Object[]{Integer.valueOf(fpsInfo.totalTimeMs)});
                FLog.d(ReactConstants.TAG, debugString);
                Toast.makeText(getReactApplicationContext(), debugString, 1).show();
            }
            this.mFrameCallback = null;
        }
    }

    public void onCatalystInstanceDestroy() {
        if (this.mFrameCallback != null) {
            this.mFrameCallback.stop();
            this.mFrameCallback = null;
        }
    }

    private static void checkAPILevel() {
        if (VERSION.SDK_INT < 16) {
            throw new JSApplicationCausedNativeException("Animation debugging is not supported in API <16");
        }
    }
}
