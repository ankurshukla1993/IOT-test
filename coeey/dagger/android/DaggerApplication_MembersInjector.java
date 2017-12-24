package dagger.android;

import android.app.Activity;
import android.app.Fragment;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ContentProvider;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class DaggerApplication_MembersInjector implements MembersInjector<DaggerApplication> {
    static final /* synthetic */ boolean $assertionsDisabled = (!DaggerApplication_MembersInjector.class.desiredAssertionStatus());
    private final Provider<DispatchingAndroidInjector<Activity>> activityInjectorProvider;
    private final Provider<DispatchingAndroidInjector<BroadcastReceiver>> broadcastReceiverInjectorProvider;
    private final Provider<DispatchingAndroidInjector<ContentProvider>> contentProviderInjectorProvider;
    private final Provider<DispatchingAndroidInjector<Fragment>> fragmentInjectorProvider;
    private final Provider<DispatchingAndroidInjector<Service>> serviceInjectorProvider;

    public DaggerApplication_MembersInjector(Provider<DispatchingAndroidInjector<Activity>> activityInjectorProvider, Provider<DispatchingAndroidInjector<BroadcastReceiver>> broadcastReceiverInjectorProvider, Provider<DispatchingAndroidInjector<Fragment>> fragmentInjectorProvider, Provider<DispatchingAndroidInjector<Service>> serviceInjectorProvider, Provider<DispatchingAndroidInjector<ContentProvider>> contentProviderInjectorProvider) {
        if ($assertionsDisabled || activityInjectorProvider != null) {
            this.activityInjectorProvider = activityInjectorProvider;
            if ($assertionsDisabled || broadcastReceiverInjectorProvider != null) {
                this.broadcastReceiverInjectorProvider = broadcastReceiverInjectorProvider;
                if ($assertionsDisabled || fragmentInjectorProvider != null) {
                    this.fragmentInjectorProvider = fragmentInjectorProvider;
                    if ($assertionsDisabled || serviceInjectorProvider != null) {
                        this.serviceInjectorProvider = serviceInjectorProvider;
                        if ($assertionsDisabled || contentProviderInjectorProvider != null) {
                            this.contentProviderInjectorProvider = contentProviderInjectorProvider;
                            return;
                        }
                        throw new AssertionError();
                    }
                    throw new AssertionError();
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<DaggerApplication> create(Provider<DispatchingAndroidInjector<Activity>> activityInjectorProvider, Provider<DispatchingAndroidInjector<BroadcastReceiver>> broadcastReceiverInjectorProvider, Provider<DispatchingAndroidInjector<Fragment>> fragmentInjectorProvider, Provider<DispatchingAndroidInjector<Service>> serviceInjectorProvider, Provider<DispatchingAndroidInjector<ContentProvider>> contentProviderInjectorProvider) {
        return new DaggerApplication_MembersInjector(activityInjectorProvider, broadcastReceiverInjectorProvider, fragmentInjectorProvider, serviceInjectorProvider, contentProviderInjectorProvider);
    }

    public void injectMembers(DaggerApplication instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.activityInjector = (DispatchingAndroidInjector) this.activityInjectorProvider.get();
        instance.broadcastReceiverInjector = (DispatchingAndroidInjector) this.broadcastReceiverInjectorProvider.get();
        instance.fragmentInjector = (DispatchingAndroidInjector) this.fragmentInjectorProvider.get();
        instance.serviceInjector = (DispatchingAndroidInjector) this.serviceInjectorProvider.get();
        instance.contentProviderInjector = (DispatchingAndroidInjector) this.contentProviderInjectorProvider.get();
        instance.setInjected();
    }

    public static void injectActivityInjector(DaggerApplication instance, Provider<DispatchingAndroidInjector<Activity>> activityInjectorProvider) {
        instance.activityInjector = (DispatchingAndroidInjector) activityInjectorProvider.get();
    }

    public static void injectBroadcastReceiverInjector(DaggerApplication instance, Provider<DispatchingAndroidInjector<BroadcastReceiver>> broadcastReceiverInjectorProvider) {
        instance.broadcastReceiverInjector = (DispatchingAndroidInjector) broadcastReceiverInjectorProvider.get();
    }

    public static void injectFragmentInjector(DaggerApplication instance, Provider<DispatchingAndroidInjector<Fragment>> fragmentInjectorProvider) {
        instance.fragmentInjector = (DispatchingAndroidInjector) fragmentInjectorProvider.get();
    }

    public static void injectServiceInjector(DaggerApplication instance, Provider<DispatchingAndroidInjector<Service>> serviceInjectorProvider) {
        instance.serviceInjector = (DispatchingAndroidInjector) serviceInjectorProvider.get();
    }

    public static void injectContentProviderInjector(DaggerApplication instance, Provider<DispatchingAndroidInjector<ContentProvider>> contentProviderInjectorProvider) {
        instance.contentProviderInjector = (DispatchingAndroidInjector) contentProviderInjectorProvider.get();
    }

    public static void injectSetInjected(DaggerApplication instance) {
        instance.setInjected();
    }
}
