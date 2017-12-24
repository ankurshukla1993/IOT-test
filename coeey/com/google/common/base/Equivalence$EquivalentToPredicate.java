package com.google.common.base;

import java.io.Serializable;
import javax.annotation.Nullable;

final class Equivalence$EquivalentToPredicate<T> implements Predicate<T>, Serializable {
    private static final long serialVersionUID = 0;
    private final Equivalence<T> equivalence;
    @Nullable
    private final T target;

    Equivalence$EquivalentToPredicate(Equivalence<T> equivalence, @Nullable T target) {
        this.equivalence = (Equivalence) Preconditions.checkNotNull(equivalence);
        this.target = target;
    }

    public boolean apply(@Nullable T input) {
        return this.equivalence.equivalent(input, this.target);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Equivalence$EquivalentToPredicate)) {
            return false;
        }
        Equivalence$EquivalentToPredicate<?> that = (Equivalence$EquivalentToPredicate) obj;
        if (this.equivalence.equals(that.equivalence) && Objects.equal(this.target, that.target)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hashCode(this.equivalence, this.target);
    }

    public String toString() {
        String valueOf = String.valueOf(String.valueOf(this.equivalence));
        String valueOf2 = String.valueOf(String.valueOf(this.target));
        return new StringBuilder((valueOf.length() + 15) + valueOf2.length()).append(valueOf).append(".equivalentTo(").append(valueOf2).append(")").toString();
    }
}
