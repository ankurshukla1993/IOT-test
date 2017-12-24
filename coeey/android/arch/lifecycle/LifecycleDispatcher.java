package android.arch.lifecycle;

import android.app.Activity;
import android.app.Application;
import android.arch.lifecycle.Lifecycle.Event;
import android.arch.lifecycle.Lifecycle.State;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentManager.FragmentLifecycleCallbacks;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicBoolean;

class LifecycleDispatcher {
    private static final String REPORT_FRAGMENT_TAG = "android.arch.lifecycle.LifecycleDispatcher.report_fragment_tag";
    private static AtomicBoolean sInitialized = new AtomicBoolean(false);

    public static class DestructionReportFragment extends Fragment {
        public void onPause() {
            super.onPause();
            dispatch(Event.ON_PAUSE);
        }

        public void onStop() {
            super.onStop();
            dispatch(Event.ON_STOP);
        }

        public void onDestroy() {
            super.onDestroy();
            dispatch(Event.ON_DESTROY);
        }

        protected void dispatch(Event event) {
            LifecycleDispatcher.dispatchIfLifecycleOwner(getParentFragment(), event);
        }
    }

    @VisibleForTesting
    static class DispatcherActivityCallback extends EmptyActivityLifecycleCallbacks {
        private final FragmentCallback mFragmentCallback = new FragmentCallback();

        DispatcherActivityCallback() {
        }

        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            if (activity instanceof FragmentActivity) {
                ((FragmentActivity) activity).getSupportFragmentManager().registerFragmentLifecycleCallbacks(this.mFragmentCallback, true);
            }
            ReportFragment.injectIfNeededIn(activity);
        }

        public void onActivityStopped(Activity activity) {
            if (activity instanceof FragmentActivity) {
                LifecycleDispatcher.markState((FragmentActivity) activity, State.CREATED);
            }
        }

        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
            if (activity instanceof FragmentActivity) {
                LifecycleDispatcher.markState((FragmentActivity) activity, State.CREATED);
            }
        }
    }

    @VisibleForTesting
    static class FragmentCallback extends FragmentLifecycleCallbacks {
        FragmentCallback() {
        }

        public void onFragmentCreated(FragmentManager fm, Fragment f, Bundle savedInstanceState) {
            LifecycleDispatcher.dispatchIfLifecycleOwner(f, Event.ON_CREATE);
            if ((f instanceof LifecycleRegistryOwner) && f.getChildFragmentManager().findFragmentByTag(LifecycleDispatcher.REPORT_FRAGMENT_TAG) == null) {
                f.getChildFragmentManager().beginTransaction().add(new DestructionReportFragment(), LifecycleDispatcher.REPORT_FRAGMENT_TAG).commit();
            }
        }

        public void onFragmentStarted(FragmentManager fm, Fragment f) {
            LifecycleDispatcher.dispatchIfLifecycleOwner(f, Event.ON_START);
        }

        public void onFragmentResumed(FragmentManager fm, Fragment f) {
            LifecycleDispatcher.dispatchIfLifecycleOwner(f, Event.ON_RESUME);
        }
    }

    LifecycleDispatcher() {
    }

    static void init(Context context) {
        if (!sInitialized.getAndSet(true)) {
            ((Application) context.getApplicationContext()).registerActivityLifecycleCallbacks(new DispatcherActivityCallback());
        }
    }

    private static void markState(FragmentManager manager, State state) {
        Collection<Fragment> fragments = manager.getFragments();
        if (fragments != null) {
            for (Fragment fragment : fragments) {
                if (fragment != null) {
                    markStateIn(fragment, state);
                    if (fragment.isAdded()) {
                        markState(fragment.getChildFragmentManager(), state);
                    }
                }
            }
        }
    }

    private static void markStateIn(Object object, State state) {
        if (object instanceof LifecycleRegistryOwner) {
            ((LifecycleRegistryOwner) object).getLifecycle().markState(state);
        }
    }

    private static void markState(FragmentActivity activity, State state) {
        markStateIn(activity, state);
        markState(activity.getSupportFragmentManager(), state);
    }

    private static void dispatchIfLifecycleOwner(Fragment fragment, Event event) {
        if (fragment instanceof LifecycleRegistryOwner) {
            ((LifecycleRegistryOwner) fragment).getLifecycle().handleLifecycleEvent(event);
        }
    }
}
