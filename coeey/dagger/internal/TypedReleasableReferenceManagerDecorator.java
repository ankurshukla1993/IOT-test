package dagger.internal;

import dagger.releasablereferences.ReleasableReferenceManager;
import dagger.releasablereferences.TypedReleasableReferenceManager;
import java.lang.annotation.Annotation;

@GwtIncompatible
public final class TypedReleasableReferenceManagerDecorator<M extends Annotation> implements TypedReleasableReferenceManager<M> {
    private final ReleasableReferenceManager delegate;
    private final M metadata;

    public TypedReleasableReferenceManagerDecorator(ReleasableReferenceManager delegate, M metadata) {
        this.delegate = (ReleasableReferenceManager) Preconditions.checkNotNull(delegate);
        this.metadata = (Annotation) Preconditions.checkNotNull(metadata);
    }

    public Class<? extends Annotation> scope() {
        return this.delegate.scope();
    }

    public M metadata() {
        return this.metadata;
    }

    public void releaseStrongReferences() {
        this.delegate.releaseStrongReferences();
    }

    public void restoreStrongReferences() {
        this.delegate.restoreStrongReferences();
    }
}
