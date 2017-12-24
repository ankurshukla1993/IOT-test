package dagger.android;

import dagger.internal.GwtIncompatible;
import dagger.releasablereferences.TypedReleasableReferenceManager;
import java.util.Set;
import javax.inject.Inject;

@GwtIncompatible
public final class AndroidMemorySensitiveReferenceManager {
    private final Set<TypedReleasableReferenceManager<ReleaseReferencesAt>> managers;

    @Inject
    AndroidMemorySensitiveReferenceManager(Set<TypedReleasableReferenceManager<ReleaseReferencesAt>> managers) {
        this.managers = managers;
    }

    public void onTrimMemory(int level) {
        for (TypedReleasableReferenceManager<ReleaseReferencesAt> manager : this.managers) {
            if (level >= ((ReleaseReferencesAt) manager.metadata()).value()) {
                manager.releaseStrongReferences();
            } else {
                manager.restoreStrongReferences();
            }
        }
    }
}
