package dagger.android.support;

import android.app.Activity;
import android.app.Fragment;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ContentProvider;
import dagger.MembersInjector;
import dagger.android.DispatchingAndroidInjector;
import javax.inject.Provider;

public final class DaggerApplication_MembersInjector implements MembersInjector<DaggerApplication> {
    static final /* synthetic */ boolean $assertionsDisabled = (!DaggerApplication_MembersInjector.class.desiredAssertionStatus());
    private final Provider<DispatchingAndroidInjector<Activity>> activityInjectorProvider;
    private final Provider<DispatchingAndroidInjector<BroadcastReceiver>> broadcastReceiverInjectorProvider;
    private final Provider<DispatchingAndroidInjector<ContentProvider>> contentProviderInjectorProvider;
    private final Provider<DispatchingAndroidInjector<Fragment>> fragmentInjectorProvider;
    private final Provider<DispatchingAndroidInjector<Service>> serviceInjectorProvider;
    private final Provider<DispatchingAndroidInjector<android.support.v4.app.Fragment>> supportFragmentInjectorProvider;

    public DaggerApplication_MembersInjector(Provider<DispatchingAndroidInjector<Activity>> activityInjectorProvider, Provider<DispatchingAndroidInjector<BroadcastReceiver>> broadcastReceiverInjectorProvider, Provider<DispatchingAndroidInjector<Fragment>> fragmentInjectorProvider, Provider<DispatchingAndroidInjector<Service>> serviceInjectorProvider, Provider<DispatchingAndroidInjector<ContentProvider>> contentProviderInjectorProvider, Provider<DispatchingAndroidInjector<android.support.v4.app.Fragment>> supportFragmentInjectorProvider) {
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
                            if ($assertionsDisabled || supportFragmentInjectorProvider != null) {
                                this.supportFragmentInjectorProvider = supportFragmentInjectorProvider;
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
        throw new AssertionError();
    }

    public static MembersInjector<DaggerApplication> create(Provider<DispatchingAndroidInjector<Activity>> activityInjectorProvider, Provider<DispatchingAndroidInjector<BroadcastReceiver>> broadcastReceiverInjectorProvider, Provider<DispatchingAndroidInjector<Fragment>> fragmentInjectorProvider, Provider<DispatchingAndroidInjector<Service>> serviceInjectorProvider, Provider<DispatchingAndroidInjector<ContentProvider>> contentProviderInjectorProvider, Provider<DispatchingAndroidInjector<android.support.v4.app.Fragment>> supportFragmentInjectorProvider) {
        return new DaggerApplication_MembersInjector(activityInjectorProvider, broadcastReceiverInjectorProvider, fragmentInjectorProvider, serviceInjectorProvider, contentProviderInjectorProvider, supportFragmentInjectorProvider);
    }

    public void injectMembers(DaggerApplication instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        dagger.android.DaggerApplication_MembersInjector.injectActivityInjector(instance, this.activityInjectorProvider);
        dagger.android.DaggerApplication_MembersInjector.injectBroadcastReceiverInjector(instance, this.broadcastReceiverInjectorProvider);
        dagger.android.DaggerApplication_MembersInjector.injectFragmentInjector(instance, this.fragmentInjectorProvider);
        dagger.android.DaggerApplication_MembersInjector.injectServiceInjector(instance, this.serviceInjectorProvider);
        dagger.android.DaggerApplication_MembersInjector.injectContentProviderInjector(instance, this.contentProviderInjectorProvider);
        dagger.android.DaggerApplication_MembersInjector.injectSetInjected(instance);
        instance.supportFragmentInjector = (DispatchingAndroidInjector) this.supportFragmentInjectorProvider.get();
    }

    public static void injectSupportFragmentInjector(DaggerApplication instance, Provider<DispatchingAndroidInjector<android.support.v4.app.Fragment>> supportFragmentInjectorProvider) {
        instance.supportFragmentInjector = (DispatchingAndroidInjector) supportFragmentInjectorProvider.get();
    }
}
