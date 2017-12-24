package com.facebook.react.modules.debug;

import android.annotation.TargetApi;
import android.view.Choreographer;
import android.view.Choreographer.FrameCallback;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.UIManagerModule;
import java.util.Map.Entry;
import java.util.TreeMap;
import javax.annotation.Nullable;

@TargetApi(16)
public class FpsDebugFrameCallback implements FrameCallback {
    private static final double EXPECTED_FRAME_TIME = 16.9d;
    private int m4PlusFrameStutters = 0;
    private final Choreographer mChoreographer;
    private final DidJSUpdateUiDuringFrameDetector mDidJSUpdateUiDuringFrameDetector;
    private int mExpectedNumFramesPrev = 0;
    private long mFirstFrameTime = -1;
    private boolean mIsRecordingFpsInfoAtEachFrame = false;
    private long mLastFrameTime = -1;
    private int mNumFrameCallbacks = 0;
    private int mNumFrameCallbacksWithBatchDispatches = 0;
    private final ReactContext mReactContext;
    private boolean mShouldStop = false;
    @Nullable
    private TreeMap<Long, FpsInfo> mTimeToFps;
    private final UIManagerModule mUIManagerModule;

    public FpsDebugFrameCallback(Choreographer choreographer, ReactContext reactContext) {
        this.mChoreographer = choreographer;
        this.mReactContext = reactContext;
        this.mUIManagerModule = (UIManagerModule) reactContext.getNativeModule(UIManagerModule.class);
        this.mDidJSUpdateUiDuringFrameDetector = new DidJSUpdateUiDuringFrameDetector();
    }

    public void doFrame(long l) {
        if (!this.mShouldStop) {
            if (this.mFirstFrameTime == -1) {
                this.mFirstFrameTime = l;
            }
            long lastFrameStartTime = this.mLastFrameTime;
            this.mLastFrameTime = l;
            if (this.mDidJSUpdateUiDuringFrameDetector.getDidJSHitFrameAndCleanup(lastFrameStartTime, l)) {
                this.mNumFrameCallbacksWithBatchDispatches++;
            }
            this.mNumFrameCallbacks++;
            int expectedNumFrames = getExpectedNumFrames();
            if ((expectedNumFrames - this.mExpectedNumFramesPrev) - 1 >= 4) {
                this.m4PlusFrameStutters++;
            }
            if (this.mIsRecordingFpsInfoAtEachFrame) {
                Assertions.assertNotNull(this.mTimeToFps);
                this.mTimeToFps.put(Long.valueOf(System.currentTimeMillis()), new FpsInfo(getNumFrames(), getNumJSFrames(), expectedNumFrames, this.m4PlusFrameStutters, getFPS(), getJSFPS(), getTotalTimeMS()));
            }
            this.mExpectedNumFramesPrev = expectedNumFrames;
            this.mChoreographer.postFrameCallback(this);
        }
    }

    public void start() {
        this.mShouldStop = false;
        this.mReactContext.getCatalystInstance().addBridgeIdleDebugListener(this.mDidJSUpdateUiDuringFrameDetector);
        this.mUIManagerModule.setViewHierarchyUpdateDebugListener(this.mDidJSUpdateUiDuringFrameDetector);
        this.mChoreographer.postFrameCallback(this);
    }

    public void startAndRecordFpsAtEachFrame() {
        this.mTimeToFps = new TreeMap();
        this.mIsRecordingFpsInfoAtEachFrame = true;
        start();
    }

    public void stop() {
        this.mShouldStop = true;
        this.mReactContext.getCatalystInstance().removeBridgeIdleDebugListener(this.mDidJSUpdateUiDuringFrameDetector);
        this.mUIManagerModule.setViewHierarchyUpdateDebugListener(null);
    }

    public double getFPS() {
        if (this.mLastFrameTime == this.mFirstFrameTime) {
            return 0.0d;
        }
        return (((double) getNumFrames()) * 1.0E9d) / ((double) (this.mLastFrameTime - this.mFirstFrameTime));
    }

    public double getJSFPS() {
        if (this.mLastFrameTime == this.mFirstFrameTime) {
            return 0.0d;
        }
        return (((double) getNumJSFrames()) * 1.0E9d) / ((double) (this.mLastFrameTime - this.mFirstFrameTime));
    }

    public int getNumFrames() {
        return this.mNumFrameCallbacks - 1;
    }

    public int getNumJSFrames() {
        return this.mNumFrameCallbacksWithBatchDispatches - 1;
    }

    public int getExpectedNumFrames() {
        return (int) ((((double) getTotalTimeMS()) / EXPECTED_FRAME_TIME) + 1.0d);
    }

    public int get4PlusFrameStutters() {
        return this.m4PlusFrameStutters;
    }

    public int getTotalTimeMS() {
        return ((int) (((double) this.mLastFrameTime) - ((double) this.mFirstFrameTime))) / 1000000;
    }

    @Nullable
    public FpsInfo getFpsInfo(long upToTimeMs) {
        Assertions.assertNotNull(this.mTimeToFps, "FPS was not recorded at each frame!");
        Entry<Long, FpsInfo> bestEntry = this.mTimeToFps.floorEntry(Long.valueOf(upToTimeMs));
        if (bestEntry == null) {
            return null;
        }
        return (FpsInfo) bestEntry.getValue();
    }

    public void reset() {
        this.mFirstFrameTime = -1;
        this.mLastFrameTime = -1;
        this.mNumFrameCallbacks = 0;
        this.m4PlusFrameStutters = 0;
        this.mNumFrameCallbacksWithBatchDispatches = 0;
        this.mIsRecordingFpsInfoAtEachFrame = false;
        this.mTimeToFps = null;
    }
}
