package android.arch.lifecycle;

import android.app.Activity;
import android.app.Application;
import android.arch.lifecycle.Lifecycle.Event;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.VisibleForTesting;

public class ProcessLifecycleOwner implements LifecycleOwner {
    @VisibleForTesting
    static final long TIMEOUT_MS = 700;
    private static final ProcessLifecycleOwner sInstance = new ProcessLifecycleOwner();
    private Runnable mDelayedPauseRunnable = new C00121();
    private Handler mHandler;
    private ActivityInitializationListener mInitializationListener = new C00132();
    private boolean mPauseSent = true;
    private final LifecycleRegistry mRegistry = new LifecycleRegistry(this);
    private int mResumedCounter = 0;
    private int mStartedCounter = 0;
    private boolean mStopSent = true;

    class C00121 implements Runnable {
        C00121() {
        }

        public void run() {
            ProcessLifecycleOwner.this.dispatchPauseIfNeeded();
            ProcessLifecycleOwner.this.dispatchStopIfNeeded();
        }
    }

    class C00132 implements ActivityInitializationListener {
        C00132() {
        }

        public void onCreate() {
        }

        public void onStart() {
            ProcessLifecycleOwner.this.activityStarted();
        }

        public void onResume() {
            ProcessLifecycleOwner.this.activityResumed();
        }
    }

    class C00143 extends EmptyActivityLifecycleCallbacks {
        C00143() {
        }

        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            ReportFragment.get(activity).setProcessListener(ProcessLifecycleOwner.this.mInitializationListener);
        }

        public void onActivityPaused(Activity activity) {
            ProcessLifecycleOwner.this.activityPaused();
        }

        public void onActivityStopped(Activity activity) {
            ProcessLifecycleOwner.this.activityStopped();
        }
    }

    public static LifecycleOwner get() {
        return sInstance;
    }

    static void init(Context context) {
        sInstance.attach(context);
    }

    void activityStarted() {
        this.mStartedCounter++;
        if (this.mStartedCounter == 1 && this.mStopSent) {
            this.mRegistry.handleLifecycleEvent(Event.ON_START);
            this.mStopSent = false;
        }
    }

    void activityResumed() {
        this.mResumedCounter++;
        if (this.mResumedCounter != 1) {
            return;
        }
        if (this.mPauseSent) {
            this.mRegistry.handleLifecycleEvent(Event.ON_RESUME);
            this.mPauseSent = false;
            return;
        }
        this.mHandler.removeCallbacks(this.mDelayedPauseRunnable);
    }

    void activityPaused() {
        this.mResumedCounter--;
        if (this.mResumedCounter == 0) {
            this.mHandler.postDelayed(this.mDelayedPauseRunnable, TIMEOUT_MS);
        }
    }

    void activityStopped() {
        this.mStartedCounter--;
        dispatchStopIfNeeded();
    }

    private void dispatchPauseIfNeeded() {
        if (this.mResumedCounter == 0) {
            this.mPauseSent = true;
            this.mRegistry.handleLifecycleEvent(Event.ON_PAUSE);
        }
    }

    private void dispatchStopIfNeeded() {
        if (this.mStartedCounter == 0 && this.mPauseSent) {
            this.mRegistry.handleLifecycleEvent(Event.ON_STOP);
            this.mStopSent = true;
        }
    }

    private ProcessLifecycleOwner() {
    }

    void attach(Context context) {
        this.mHandler = new Handler();
        this.mRegistry.handleLifecycleEvent(Event.ON_CREATE);
        ((Application) context.getApplicationContext()).registerActivityLifecycleCallbacks(new C00143());
    }

    public Lifecycle getLifecycle() {
        return this.mRegistry;
    }
}
