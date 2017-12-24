package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.collect.ImmutableMap.Builder;
import com.google.common.collect.Multiset.Entry;
import com.google.common.primitives.Ints;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import javax.annotation.Nullable;

@GwtCompatible(emulated = true, serializable = true)
public abstract class ImmutableMultiset<E> extends ImmutableCollection<E> implements Multiset<E> {
    private static final ImmutableMultiset<Object> EMPTY = new RegularImmutableMultiset(ImmutableMap.of(), 0);
    private transient ImmutableSet<Entry<E>> entrySet;

    abstract Entry<E> getEntry(int i);

    public static <E> ImmutableMultiset<E> of() {
        return EMPTY;
    }

    public static <E> ImmutableMultiset<E> of(E element) {
        return copyOfInternal(element);
    }

    public static <E> ImmutableMultiset<E> of(E e1, E e2) {
        return copyOfInternal(e1, e2);
    }

    public static <E> ImmutableMultiset<E> of(E e1, E e2, E e3) {
        return copyOfInternal(e1, e2, e3);
    }

    public static <E> ImmutableMultiset<E> of(E e1, E e2, E e3, E e4) {
        return copyOfInternal(e1, e2, e3, e4);
    }

    public static <E> ImmutableMultiset<E> of(E e1, E e2, E e3, E e4, E e5) {
        return copyOfInternal(e1, e2, e3, e4, e5);
    }

    public static <E> ImmutableMultiset<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E... others) {
        return new Builder().add(e1).add(e2).add(e3).add(e4).add(e5).add(e6).add(others).build();
    }

    public static <E> ImmutableMultiset<E> copyOf(E[] elements) {
        return copyOf(Arrays.asList(elements));
    }

    public static <E> ImmutableMultiset<E> copyOf(Iterable<? extends E> elements) {
        if (elements instanceof ImmutableMultiset) {
            ImmutableMultiset<E> result = (ImmutableMultiset) elements;
            if (!result.isPartialView()) {
                return result;
            }
        }
        return copyOfInternal(elements instanceof Multiset ? Multisets.cast(elements) : LinkedHashMultiset.create(elements));
    }

    private static <E> ImmutableMultiset<E> copyOfInternal(E... elements) {
        return copyOf(Arrays.asList(elements));
    }

    private static <E> ImmutableMultiset<E> copyOfInternal(Multiset<? extends E> multiset) {
        return copyFromEntries(multiset.entrySet());
    }

    static <E> ImmutableMultiset<E> copyFromEntries(Collection<? extends Entry<? extends E>> entries) {
        long size = 0;
        Builder<E, Integer> builder = ImmutableMap.builder();
        for (Entry<? extends E> entry : entries) {
            int count = entry.getCount();
            if (count > 0) {
                builder.put(entry.getElement(), Integer.valueOf(count));
                size += (long) count;
            }
        }
        if (size == 0) {
            return of();
        }
        return new RegularImmutableMultiset(builder.build(), Ints.saturatedCast(size));
    }

    public static <E> ImmutableMultiset<E> copyOf(Iterator<? extends E> elements) {
        Multiset multiset = LinkedHashMultiset.create();
        Iterators.addAll(multiset, elements);
        return copyOfInternal(multiset);
    }

    ImmutableMultiset() {
    }

    public UnmodifiableIterator<E> iterator() {
        return new 1(this, entrySet().iterator());
    }

    public boolean contains(@Nullable Object object) {
        return count(object) > 0;
    }

    public boolean containsAll(Collection<?> targets) {
        return elementSet().containsAll(targets);
    }

    @Deprecated
    public final int add(E e, int occurrences) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final int remove(Object element, int occurrences) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final int setCount(E e, int count) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final boolean setCount(E e, int oldCount, int newCount) {
        throw new UnsupportedOperationException();
    }

    @GwtIncompatible("not present in emulated superclass")
    int copyIntoArray(Object[] dst, int offset) {
        Iterator i$ = entrySet().iterator();
        while (i$.hasNext()) {
            Entry<E> entry = (Entry) i$.next();
            Arrays.fill(dst, offset, entry.getCount() + offset, entry.getElement());
            offset += entry.getCount();
        }
        return offset;
    }

    public boolean equals(@Nullable Object object) {
        return Multisets.equalsImpl(this, object);
    }

    public int hashCode() {
        return Sets.hashCodeImpl(entrySet());
    }

    public String toString() {
        return entrySet().toString();
    }

    public ImmutableSet<Entry<E>> entrySet() {
        ImmutableSet<Entry<E>> immutableSet = this.entrySet;
        if (immutableSet != null) {
            return immutableSet;
        }
        immutableSet = createEntrySet();
        this.entrySet = immutableSet;
        return immutableSet;
    }

    private final ImmutableSet<Entry<E>> createEntrySet() {
        return isEmpty() ? ImmutableSet.of() : new EntrySet(this, null);
    }

    Object writeReplace() {
        return new SerializedForm(this);
    }

    public static <E> Builder<E> builder() {
        return new Builder();
    }
}
