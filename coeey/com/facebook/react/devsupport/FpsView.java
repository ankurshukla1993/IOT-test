package com.facebook.react.devsupport;

import android.annotation.TargetApi;
import android.view.Choreographer;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.facebook.common.logging.FLog;
import com.facebook.react.C1275R;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.modules.debug.FpsDebugFrameCallback;
import java.util.Locale;

@TargetApi(16)
public class FpsView extends FrameLayout {
    private static final int UPDATE_INTERVAL_MS = 500;
    private final FPSMonitorRunnable mFPSMonitorRunnable;
    private final FpsDebugFrameCallback mFrameCallback;
    private final TextView mTextView = ((TextView) findViewById(C1275R.id.fps_text));

    private class FPSMonitorRunnable implements Runnable {
        private boolean mShouldStop;
        private int mTotal4PlusFrameStutters;
        private int mTotalFramesDropped;

        private FPSMonitorRunnable() {
            this.mShouldStop = false;
            this.mTotalFramesDropped = 0;
            this.mTotal4PlusFrameStutters = 0;
        }

        public void run() {
            if (!this.mShouldStop) {
                this.mTotalFramesDropped += FpsView.this.mFrameCallback.getExpectedNumFrames() - FpsView.this.mFrameCallback.getNumFrames();
                this.mTotal4PlusFrameStutters += FpsView.this.mFrameCallback.get4PlusFrameStutters();
                FpsView.this.setCurrentFPS(FpsView.this.mFrameCallback.getFPS(), FpsView.this.mFrameCallback.getJSFPS(), this.mTotalFramesDropped, this.mTotal4PlusFrameStutters);
                FpsView.this.mFrameCallback.reset();
                FpsView.this.postDelayed(this, 500);
            }
        }

        public void start() {
            this.mShouldStop = false;
            FpsView.this.post(this);
        }

        public void stop() {
            this.mShouldStop = true;
        }
    }

    public FpsView(ReactContext reactContext) {
        super(reactContext);
        inflate(reactContext, C1275R.layout.fps_view, this);
        this.mFrameCallback = new FpsDebugFrameCallback(Choreographer.getInstance(), reactContext);
        this.mFPSMonitorRunnable = new FPSMonitorRunnable();
        setCurrentFPS(0.0d, 0.0d, 0, 0);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mFrameCallback.reset();
        this.mFrameCallback.start();
        this.mFPSMonitorRunnable.start();
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mFrameCallback.stop();
        this.mFPSMonitorRunnable.stop();
    }

    private void setCurrentFPS(double currentFPS, double currentJSFPS, int droppedUIFrames, int total4PlusFrameStutters) {
        String fpsString = String.format(Locale.US, "UI: %.1f fps\n%d dropped so far\n%d stutters (4+) so far\nJS: %.1f fps", new Object[]{Double.valueOf(currentFPS), Integer.valueOf(droppedUIFrames), Integer.valueOf(total4PlusFrameStutters), Double.valueOf(currentJSFPS)});
        this.mTextView.setText(fpsString);
        FLog.m99d(ReactConstants.TAG, fpsString);
    }
}
