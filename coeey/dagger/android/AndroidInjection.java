package dagger.android;

import android.app.Activity;
import android.app.Application;
import android.app.Fragment;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ContentProvider;
import android.content.Context;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import com.ihealth.communication.control.AmProfile;
import dagger.internal.Preconditions;

public final class AndroidInjection {
    private static final String TAG = "dagger.android";

    public static void inject(Activity activity) {
        Preconditions.checkNotNull(activity, AmProfile.SYNC_ACTIVITY_DATA_AM);
        Application application = activity.getApplication();
        if (application instanceof HasActivityInjector) {
            AndroidInjector<Activity> activityInjector = ((HasActivityInjector) application).activityInjector();
            Preconditions.checkNotNull(activityInjector, "%s.activityInjector() returned null", application.getClass().getCanonicalName());
            activityInjector.inject(activity);
            return;
        }
        throw new RuntimeException(String.format("%s does not implement %s", new Object[]{application.getClass().getCanonicalName(), HasActivityInjector.class.getCanonicalName()}));
    }

    public static void inject(Fragment fragment) {
        Preconditions.checkNotNull(fragment, "fragment");
        HasFragmentInjector hasFragmentInjector = findHasFragmentInjector(fragment);
        Log.d(TAG, String.format("An injector for %s was found in %s", new Object[]{fragment.getClass().getCanonicalName(), hasFragmentInjector.getClass().getCanonicalName()}));
        AndroidInjector<Fragment> fragmentInjector = hasFragmentInjector.fragmentInjector();
        Preconditions.checkNotNull(fragmentInjector, "%s.fragmentInjector() returned null", hasFragmentInjector.getClass().getCanonicalName());
        fragmentInjector.inject(fragment);
    }

    private static HasFragmentInjector findHasFragmentInjector(Fragment fragment) {
        Fragment parentFragment = fragment;
        do {
            parentFragment = parentFragment.getParentFragment();
            if (parentFragment == null) {
                Activity activity = fragment.getActivity();
                if (activity instanceof HasFragmentInjector) {
                    return (HasFragmentInjector) activity;
                }
                if (activity.getApplication() instanceof HasFragmentInjector) {
                    return (HasFragmentInjector) activity.getApplication();
                }
                throw new IllegalArgumentException(String.format("No injector was found for %s", new Object[]{fragment.getClass().getCanonicalName()}));
            }
        } while (!(parentFragment instanceof HasFragmentInjector));
        return (HasFragmentInjector) parentFragment;
    }

    public static void inject(Service service) {
        Preconditions.checkNotNull(service, NotificationCompat.CATEGORY_SERVICE);
        Application application = service.getApplication();
        if (application instanceof HasServiceInjector) {
            AndroidInjector<Service> serviceInjector = ((HasServiceInjector) application).serviceInjector();
            Preconditions.checkNotNull(serviceInjector, "%s.serviceInjector() returned null", application.getClass().getCanonicalName());
            serviceInjector.inject(service);
            return;
        }
        throw new RuntimeException(String.format("%s does not implement %s", new Object[]{application.getClass().getCanonicalName(), HasServiceInjector.class.getCanonicalName()}));
    }

    public static void inject(BroadcastReceiver broadcastReceiver, Context context) {
        Preconditions.checkNotNull(broadcastReceiver, "broadcastReceiver");
        Preconditions.checkNotNull(context, "context");
        Application application = (Application) context.getApplicationContext();
        if (application instanceof HasBroadcastReceiverInjector) {
            AndroidInjector<BroadcastReceiver> broadcastReceiverInjector = ((HasBroadcastReceiverInjector) application).broadcastReceiverInjector();
            Preconditions.checkNotNull(broadcastReceiverInjector, "%s.broadcastReceiverInjector() returned null", application.getClass().getCanonicalName());
            broadcastReceiverInjector.inject(broadcastReceiver);
            return;
        }
        throw new RuntimeException(String.format("%s does not implement %s", new Object[]{application.getClass().getCanonicalName(), HasBroadcastReceiverInjector.class.getCanonicalName()}));
    }

    public static void inject(ContentProvider contentProvider) {
        Preconditions.checkNotNull(contentProvider, "contentProvider");
        Application application = (Application) contentProvider.getContext().getApplicationContext();
        if (application instanceof HasContentProviderInjector) {
            AndroidInjector<ContentProvider> contentProviderInjector = ((HasContentProviderInjector) application).contentProviderInjector();
            Preconditions.checkNotNull(contentProviderInjector, "%s.contentProviderInjector() returned null", application.getClass().getCanonicalName());
            contentProviderInjector.inject(contentProvider);
            return;
        }
        throw new RuntimeException(String.format("%s does not implement %s", new Object[]{application.getClass().getCanonicalName(), HasContentProviderInjector.class.getCanonicalName()}));
    }

    private AndroidInjection() {
    }
}
