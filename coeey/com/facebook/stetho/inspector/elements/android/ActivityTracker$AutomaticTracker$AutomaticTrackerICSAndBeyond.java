package com.facebook.stetho.inspector.elements.android;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.os.Bundle;
import com.facebook.stetho.inspector.elements.android.ActivityTracker.AutomaticTracker;

@TargetApi(14)
class ActivityTracker$AutomaticTracker$AutomaticTrackerICSAndBeyond extends AutomaticTracker {
    private final Application mApplication;
    private final ActivityLifecycleCallbacks mLifecycleCallbacks = new C14401();
    private final ActivityTracker mTracker;

    class C14401 implements ActivityLifecycleCallbacks {
        C14401() {
        }

        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            ActivityTracker$AutomaticTracker$AutomaticTrackerICSAndBeyond.this.mTracker.add(activity);
        }

        public void onActivityStarted(Activity activity) {
        }

        public void onActivityResumed(Activity activity) {
        }

        public void onActivityPaused(Activity activity) {
        }

        public void onActivityStopped(Activity activity) {
        }

        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        }

        public void onActivityDestroyed(Activity activity) {
            ActivityTracker$AutomaticTracker$AutomaticTrackerICSAndBeyond.this.mTracker.remove(activity);
        }
    }

    public ActivityTracker$AutomaticTracker$AutomaticTrackerICSAndBeyond(Application application, ActivityTracker tracker) {
        super(null);
        this.mApplication = application;
        this.mTracker = tracker;
    }

    public void register() {
        this.mApplication.registerActivityLifecycleCallbacks(this.mLifecycleCallbacks);
    }

    public void unregister() {
        this.mApplication.unregisterActivityLifecycleCallbacks(this.mLifecycleCallbacks);
    }
}
