package dagger.android.support;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.util.Log;
import dagger.android.AndroidInjector;
import dagger.internal.Preconditions;

public final class AndroidSupportInjection {
    private static final String TAG = "dagger.android.support";

    public static void inject(Fragment fragment) {
        Preconditions.checkNotNull(fragment, "fragment");
        HasSupportFragmentInjector hasSupportFragmentInjector = findHasFragmentInjector(fragment);
        Log.d(TAG, String.format("An injector for %s was found in %s", new Object[]{fragment.getClass().getCanonicalName(), hasSupportFragmentInjector.getClass().getCanonicalName()}));
        AndroidInjector<Fragment> fragmentInjector = hasSupportFragmentInjector.supportFragmentInjector();
        Preconditions.checkNotNull(fragmentInjector, "%s.supportFragmentInjector() returned null", hasSupportFragmentInjector.getClass().getCanonicalName());
        fragmentInjector.inject(fragment);
    }

    private static HasSupportFragmentInjector findHasFragmentInjector(Fragment fragment) {
        Fragment parentFragment = fragment;
        do {
            parentFragment = parentFragment.getParentFragment();
            if (parentFragment == null) {
                Activity activity = fragment.getActivity();
                if (activity instanceof HasSupportFragmentInjector) {
                    return (HasSupportFragmentInjector) activity;
                }
                if (activity.getApplication() instanceof HasSupportFragmentInjector) {
                    return (HasSupportFragmentInjector) activity.getApplication();
                }
                throw new IllegalArgumentException(String.format("No injector was found for %s", new Object[]{fragment.getClass().getCanonicalName()}));
            }
        } while (!(parentFragment instanceof HasSupportFragmentInjector));
        return (HasSupportFragmentInjector) parentFragment;
    }

    private AndroidSupportInjection() {
    }
}
