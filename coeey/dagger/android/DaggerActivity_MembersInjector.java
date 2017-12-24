package dagger.android;

import android.app.Fragment;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class DaggerActivity_MembersInjector implements MembersInjector<DaggerActivity> {
    static final /* synthetic */ boolean $assertionsDisabled = (!DaggerActivity_MembersInjector.class.desiredAssertionStatus());
    private final Provider<DispatchingAndroidInjector<Fragment>> fragmentInjectorProvider;

    public DaggerActivity_MembersInjector(Provider<DispatchingAndroidInjector<Fragment>> fragmentInjectorProvider) {
        if ($assertionsDisabled || fragmentInjectorProvider != null) {
            this.fragmentInjectorProvider = fragmentInjectorProvider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<DaggerActivity> create(Provider<DispatchingAndroidInjector<Fragment>> fragmentInjectorProvider) {
        return new DaggerActivity_MembersInjector(fragmentInjectorProvider);
    }

    public void injectMembers(DaggerActivity instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.fragmentInjector = (DispatchingAndroidInjector) this.fragmentInjectorProvider.get();
    }

    public static void injectFragmentInjector(DaggerActivity instance, Provider<DispatchingAndroidInjector<Fragment>> fragmentInjectorProvider) {
        instance.fragmentInjector = (DispatchingAndroidInjector) fragmentInjectorProvider.get();
    }
}
