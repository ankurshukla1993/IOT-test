package com.google.common.collect;

import com.google.common.base.Objects;
import com.google.common.collect.Multiset.Entry;
import javax.annotation.Nullable;

abstract class Multisets$AbstractEntry<E> implements Entry<E> {
    Multisets$AbstractEntry() {
    }

    public boolean equals(@Nullable Object object) {
        if (!(object instanceof Entry)) {
            return false;
        }
        Entry<?> that = (Entry) object;
        if (getCount() == that.getCount() && Objects.equal(getElement(), that.getElement())) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        E e = getElement();
        return (e == null ? 0 : e.hashCode()) ^ getCount();
    }

    public String toString() {
        String text = String.valueOf(getElement());
        int n = getCount();
        if (n == 1) {
            return text;
        }
        String valueOf = String.valueOf(String.valueOf(text));
        return new StringBuilder(valueOf.length() + 14).append(valueOf).append(" x ").append(n).toString();
    }
}
