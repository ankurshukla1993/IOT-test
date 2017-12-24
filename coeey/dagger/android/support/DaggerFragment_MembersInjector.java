package dagger.android.support;

import android.support.v4.app.Fragment;
import dagger.MembersInjector;
import dagger.android.DispatchingAndroidInjector;
import javax.inject.Provider;

public final class DaggerFragment_MembersInjector implements MembersInjector<DaggerFragment> {
    static final /* synthetic */ boolean $assertionsDisabled = (!DaggerFragment_MembersInjector.class.desiredAssertionStatus());
    private final Provider<DispatchingAndroidInjector<Fragment>> childFragmentInjectorProvider;

    public DaggerFragment_MembersInjector(Provider<DispatchingAndroidInjector<Fragment>> childFragmentInjectorProvider) {
        if ($assertionsDisabled || childFragmentInjectorProvider != null) {
            this.childFragmentInjectorProvider = childFragmentInjectorProvider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<DaggerFragment> create(Provider<DispatchingAndroidInjector<Fragment>> childFragmentInjectorProvider) {
        return new DaggerFragment_MembersInjector(childFragmentInjectorProvider);
    }

    public void injectMembers(DaggerFragment instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.childFragmentInjector = (DispatchingAndroidInjector) this.childFragmentInjectorProvider.get();
    }

    public static void injectChildFragmentInjector(DaggerFragment instance, Provider<DispatchingAndroidInjector<Fragment>> childFragmentInjectorProvider) {
        instance.childFragmentInjector = (DispatchingAndroidInjector) childFragmentInjectorProvider.get();
    }
}
