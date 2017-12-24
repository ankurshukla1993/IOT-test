package com.google.common.base;

import java.io.Serializable;
import javax.annotation.Nullable;

public final class Equivalence$Wrapper<T> implements Serializable {
    private static final long serialVersionUID = 0;
    private final Equivalence<? super T> equivalence;
    @Nullable
    private final T reference;

    private Equivalence$Wrapper(Equivalence<? super T> equivalence, @Nullable T reference) {
        this.equivalence = (Equivalence) Preconditions.checkNotNull(equivalence);
        this.reference = reference;
    }

    @Nullable
    public T get() {
        return this.reference;
    }

    public boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Equivalence$Wrapper) {
            Equivalence$Wrapper<?> that = (Equivalence$Wrapper) obj;
            if (this.equivalence.equals(that.equivalence)) {
                return this.equivalence.equivalent(this.reference, that.reference);
            }
        }
        return false;
    }

    public int hashCode() {
        return this.equivalence.hash(this.reference);
    }

    public String toString() {
        String valueOf = String.valueOf(String.valueOf(this.equivalence));
        String valueOf2 = String.valueOf(String.valueOf(this.reference));
        return new StringBuilder((valueOf.length() + 7) + valueOf2.length()).append(valueOf).append(".wrap(").append(valueOf2).append(")").toString();
    }
}
