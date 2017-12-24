package dagger.internal;

import java.lang.ref.WeakReference;
import javax.inject.Provider;

@GwtIncompatible
public final class ReferenceReleasingProvider<T> implements Provider<T> {
    static final /* synthetic */ boolean $assertionsDisabled = (!ReferenceReleasingProvider.class.desiredAssertionStatus());
    private static final Object NULL = new Object();
    private final Provider<T> provider;
    private volatile Object strongReference;
    private volatile WeakReference<T> weakReference;

    private ReferenceReleasingProvider(Provider<T> provider) {
        if ($assertionsDisabled || provider != null) {
            this.provider = provider;
            return;
        }
        throw new AssertionError();
    }

    public void releaseStrongReference() {
        T value = this.strongReference;
        if (value != null && value != NULL) {
            synchronized (this) {
                this.weakReference = new WeakReference(value);
                this.strongReference = null;
            }
        }
    }

    public void restoreStrongReference() {
        Object value = this.strongReference;
        if (this.weakReference != null && value == null) {
            synchronized (this) {
                value = this.strongReference;
                if (this.weakReference != null && value == null) {
                    value = this.weakReference.get();
                    if (value != null) {
                        this.strongReference = value;
                        this.weakReference = null;
                    }
                }
            }
        }
    }

    public T get() {
        Object value = currentValue();
        if (value == null) {
            synchronized (this) {
                value = currentValue();
                if (value == null) {
                    value = this.provider.get();
                    if (value == null) {
                        value = NULL;
                    }
                    this.strongReference = value;
                }
            }
        }
        return value == NULL ? null : value;
    }

    private Object currentValue() {
        Object value = this.strongReference;
        if (value != null) {
            return value;
        }
        if (this.weakReference != null) {
            return this.weakReference.get();
        }
        return null;
    }

    public static <T> ReferenceReleasingProvider<T> create(Provider<T> delegate, ReferenceReleasingProviderManager references) {
        ReferenceReleasingProvider<T> provider = new ReferenceReleasingProvider((Provider) Preconditions.checkNotNull(delegate));
        references.addProvider(provider);
        return provider;
    }
}
