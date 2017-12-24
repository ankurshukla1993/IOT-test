package dagger.android;

import dagger.internal.Factory;
import dagger.internal.GwtIncompatible;
import dagger.releasablereferences.TypedReleasableReferenceManager;
import java.util.Set;
import javax.inject.Provider;

@GwtIncompatible
public final class AndroidMemorySensitiveReferenceManager_Factory implements Factory<AndroidMemorySensitiveReferenceManager> {
    static final /* synthetic */ boolean $assertionsDisabled = (!AndroidMemorySensitiveReferenceManager_Factory.class.desiredAssertionStatus());
    private final Provider<Set<TypedReleasableReferenceManager<ReleaseReferencesAt>>> managersProvider;

    public AndroidMemorySensitiveReferenceManager_Factory(Provider<Set<TypedReleasableReferenceManager<ReleaseReferencesAt>>> managersProvider) {
        if ($assertionsDisabled || managersProvider != null) {
            this.managersProvider = managersProvider;
            return;
        }
        throw new AssertionError();
    }

    public AndroidMemorySensitiveReferenceManager get() {
        return new AndroidMemorySensitiveReferenceManager((Set) this.managersProvider.get());
    }

    public static Factory<AndroidMemorySensitiveReferenceManager> create(Provider<Set<TypedReleasableReferenceManager<ReleaseReferencesAt>>> managersProvider) {
        return new AndroidMemorySensitiveReferenceManager_Factory(managersProvider);
    }

    public static AndroidMemorySensitiveReferenceManager newAndroidMemorySensitiveReferenceManager(Set<TypedReleasableReferenceManager<ReleaseReferencesAt>> managers) {
        return new AndroidMemorySensitiveReferenceManager(managers);
    }
}
