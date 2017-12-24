package dagger.android.support;

import android.app.Fragment;
import dagger.MembersInjector;
import dagger.android.DispatchingAndroidInjector;
import javax.inject.Provider;

public final class DaggerAppCompatActivity_MembersInjector implements MembersInjector<DaggerAppCompatActivity> {
    static final /* synthetic */ boolean $assertionsDisabled = (!DaggerAppCompatActivity_MembersInjector.class.desiredAssertionStatus());
    private final Provider<DispatchingAndroidInjector<Fragment>> frameworkFragmentInjectorProvider;
    private final Provider<DispatchingAndroidInjector<android.support.v4.app.Fragment>> supportFragmentInjectorProvider;

    public DaggerAppCompatActivity_MembersInjector(Provider<DispatchingAndroidInjector<android.support.v4.app.Fragment>> supportFragmentInjectorProvider, Provider<DispatchingAndroidInjector<Fragment>> frameworkFragmentInjectorProvider) {
        if ($assertionsDisabled || supportFragmentInjectorProvider != null) {
            this.supportFragmentInjectorProvider = supportFragmentInjectorProvider;
            if ($assertionsDisabled || frameworkFragmentInjectorProvider != null) {
                this.frameworkFragmentInjectorProvider = frameworkFragmentInjectorProvider;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<DaggerAppCompatActivity> create(Provider<DispatchingAndroidInjector<android.support.v4.app.Fragment>> supportFragmentInjectorProvider, Provider<DispatchingAndroidInjector<Fragment>> frameworkFragmentInjectorProvider) {
        return new DaggerAppCompatActivity_MembersInjector(supportFragmentInjectorProvider, frameworkFragmentInjectorProvider);
    }

    public void injectMembers(DaggerAppCompatActivity instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.supportFragmentInjector = (DispatchingAndroidInjector) this.supportFragmentInjectorProvider.get();
        instance.frameworkFragmentInjector = (DispatchingAndroidInjector) this.frameworkFragmentInjectorProvider.get();
    }

    public static void injectSupportFragmentInjector(DaggerAppCompatActivity instance, Provider<DispatchingAndroidInjector<android.support.v4.app.Fragment>> supportFragmentInjectorProvider) {
        instance.supportFragmentInjector = (DispatchingAndroidInjector) supportFragmentInjectorProvider.get();
    }

    public static void injectFrameworkFragmentInjector(DaggerAppCompatActivity instance, Provider<DispatchingAndroidInjector<Fragment>> frameworkFragmentInjectorProvider) {
        instance.frameworkFragmentInjector = (DispatchingAndroidInjector) frameworkFragmentInjectorProvider.get();
    }
}
