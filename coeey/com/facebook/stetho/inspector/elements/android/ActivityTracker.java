package com.facebook.stetho.inspector.elements.android;

import android.app.Activity;
import android.app.Application;
import android.os.Build.VERSION;
import android.os.Looper;
import com.facebook.stetho.common.Util;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public final class ActivityTracker {
    private static final ActivityTracker sInstance = new ActivityTracker();
    @GuardedBy("Looper.getMainLooper()")
    private final ArrayList<WeakReference<Activity>> mActivities = new ArrayList();
    private final List<WeakReference<Activity>> mActivitiesUnmodifiable = Collections.unmodifiableList(this.mActivities);
    @Nullable
    private AutomaticTracker mAutomaticTracker;
    private final List<Listener> mListeners = new CopyOnWriteArrayList();

    private static abstract class AutomaticTracker {
        public abstract void register();

        public abstract void unregister();

        private AutomaticTracker() {
        }

        @Nullable
        public static AutomaticTracker newInstanceIfPossible(Application application, ActivityTracker tracker) {
            if (VERSION.SDK_INT >= 14) {
                return new AutomaticTrackerICSAndBeyond(application, tracker);
            }
            return null;
        }
    }

    public static ActivityTracker get() {
        return sInstance;
    }

    public void registerListener(Listener listener) {
        this.mListeners.add(listener);
    }

    public void unregisterListener(Listener listener) {
        this.mListeners.remove(listener);
    }

    public boolean beginTrackingIfPossible(Application application) {
        if (this.mAutomaticTracker == null) {
            AutomaticTracker automaticTracker = AutomaticTracker.newInstanceIfPossible(application, this);
            if (automaticTracker != null) {
                automaticTracker.register();
                this.mAutomaticTracker = automaticTracker;
                return true;
            }
        }
        return false;
    }

    public boolean endTracking() {
        if (this.mAutomaticTracker == null) {
            return false;
        }
        this.mAutomaticTracker.unregister();
        this.mAutomaticTracker = null;
        return true;
    }

    public void add(Activity activity) {
        Util.throwIfNull(activity);
        Util.throwIfNot(Looper.myLooper() == Looper.getMainLooper());
        this.mActivities.add(new WeakReference(activity));
        for (Listener listener : this.mListeners) {
            listener.onActivityAdded(activity);
        }
    }

    public void remove(Activity activity) {
        Util.throwIfNull(activity);
        Util.throwIfNot(Looper.myLooper() == Looper.getMainLooper());
        if (removeFromWeakList(this.mActivities, activity)) {
            for (Listener listener : this.mListeners) {
                listener.onActivityRemoved(activity);
            }
        }
    }

    private static <T> boolean removeFromWeakList(ArrayList<WeakReference<T>> haystack, T needle) {
        int N = haystack.size();
        for (int i = 0; i < N; i++) {
            if (((WeakReference) haystack.get(i)).get() == needle) {
                haystack.remove(i);
                return true;
            }
        }
        return false;
    }

    public List<WeakReference<Activity>> getActivitiesView() {
        return this.mActivitiesUnmodifiable;
    }

    @Nullable
    public Activity tryGetTopActivity() {
        if (this.mActivitiesUnmodifiable.isEmpty()) {
            return null;
        }
        for (int i = this.mActivitiesUnmodifiable.size() - 1; i >= 0; i--) {
            Activity activity = (Activity) ((WeakReference) this.mActivitiesUnmodifiable.get(i)).get();
            if (activity != null) {
                return activity;
            }
        }
        return null;
    }
}
