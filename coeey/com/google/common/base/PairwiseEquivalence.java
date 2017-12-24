package com.google.common.base;

import com.google.common.annotations.GwtCompatible;
import java.io.Serializable;
import java.util.Iterator;
import javax.annotation.Nullable;

@GwtCompatible(serializable = true)
final class PairwiseEquivalence<T> extends Equivalence<Iterable<T>> implements Serializable {
    private static final long serialVersionUID = 1;
    final Equivalence<? super T> elementEquivalence;

    PairwiseEquivalence(Equivalence<? super T> elementEquivalence) {
        this.elementEquivalence = (Equivalence) Preconditions.checkNotNull(elementEquivalence);
    }

    protected boolean doEquivalent(Iterable<T> iterableA, Iterable<T> iterableB) {
        Iterator<T> iteratorA = iterableA.iterator();
        Iterator<T> iteratorB = iterableB.iterator();
        while (iteratorA.hasNext() && iteratorB.hasNext()) {
            if (!this.elementEquivalence.equivalent(iteratorA.next(), iteratorB.next())) {
                return false;
            }
        }
        if (iteratorA.hasNext() || iteratorB.hasNext()) {
            return false;
        }
        return true;
    }

    protected int doHash(Iterable<T> iterable) {
        int hash = 78721;
        for (T element : iterable) {
            hash = (hash * 24943) + this.elementEquivalence.hash(element);
        }
        return hash;
    }

    public boolean equals(@Nullable Object object) {
        if (!(object instanceof PairwiseEquivalence)) {
            return false;
        }
        return this.elementEquivalence.equals(((PairwiseEquivalence) object).elementEquivalence);
    }

    public int hashCode() {
        return this.elementEquivalence.hashCode() ^ 1185147655;
    }

    public String toString() {
        String valueOf = String.valueOf(String.valueOf(this.elementEquivalence));
        return new StringBuilder(valueOf.length() + 11).append(valueOf).append(".pairwise()").toString();
    }
}
