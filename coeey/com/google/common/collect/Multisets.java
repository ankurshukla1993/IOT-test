package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Multiset.Entry;
import com.google.common.primitives.Ints;
import com.ihealth.communication.control.Bg5Profile;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import javax.annotation.Nullable;

@GwtCompatible
public final class Multisets {
    private static final Ordering<Entry<?>> DECREASING_COUNT_ORDERING = new 5();

    static final class ImmutableEntry<E> extends AbstractEntry<E> implements Serializable {
        private static final long serialVersionUID = 0;
        final int count;
        @Nullable
        final E element;

        ImmutableEntry(@Nullable E element, int count) {
            this.element = element;
            this.count = count;
            CollectPreconditions.checkNonnegative(count, Bg5Profile.HISTORICAL_NUM_BG);
        }

        @Nullable
        public E getElement() {
            return this.element;
        }

        public int getCount() {
            return this.count;
        }
    }

    private Multisets() {
    }

    public static <E> Multiset<E> unmodifiableMultiset(Multiset<? extends E> multiset) {
        if ((multiset instanceof UnmodifiableMultiset) || (multiset instanceof ImmutableMultiset)) {
            return multiset;
        }
        return new UnmodifiableMultiset((Multiset) Preconditions.checkNotNull(multiset));
    }

    @Deprecated
    public static <E> Multiset<E> unmodifiableMultiset(ImmutableMultiset<E> multiset) {
        return (Multiset) Preconditions.checkNotNull(multiset);
    }

    @Beta
    public static <E> SortedMultiset<E> unmodifiableSortedMultiset(SortedMultiset<E> sortedMultiset) {
        return new UnmodifiableSortedMultiset((SortedMultiset) Preconditions.checkNotNull(sortedMultiset));
    }

    public static <E> Entry<E> immutableEntry(@Nullable E e, int n) {
        return new ImmutableEntry(e, n);
    }

    @Beta
    public static <E> Multiset<E> filter(Multiset<E> unfiltered, Predicate<? super E> predicate) {
        if (!(unfiltered instanceof FilteredMultiset)) {
            return new FilteredMultiset(unfiltered, predicate);
        }
        FilteredMultiset<E> filtered = (FilteredMultiset) unfiltered;
        return new FilteredMultiset(filtered.unfiltered, Predicates.and(filtered.predicate, predicate));
    }

    static int inferDistinctElements(Iterable<?> elements) {
        if (elements instanceof Multiset) {
            return ((Multiset) elements).elementSet().size();
        }
        return 11;
    }

    @Beta
    public static <E> Multiset<E> union(Multiset<? extends E> multiset1, Multiset<? extends E> multiset2) {
        Preconditions.checkNotNull(multiset1);
        Preconditions.checkNotNull(multiset2);
        return new 1(multiset1, multiset2);
    }

    public static <E> Multiset<E> intersection(Multiset<E> multiset1, Multiset<?> multiset2) {
        Preconditions.checkNotNull(multiset1);
        Preconditions.checkNotNull(multiset2);
        return new 2(multiset1, multiset2);
    }

    @Beta
    public static <E> Multiset<E> sum(Multiset<? extends E> multiset1, Multiset<? extends E> multiset2) {
        Preconditions.checkNotNull(multiset1);
        Preconditions.checkNotNull(multiset2);
        return new 3(multiset1, multiset2);
    }

    @Beta
    public static <E> Multiset<E> difference(Multiset<E> multiset1, Multiset<?> multiset2) {
        Preconditions.checkNotNull(multiset1);
        Preconditions.checkNotNull(multiset2);
        return new 4(multiset1, multiset2);
    }

    public static boolean containsOccurrences(Multiset<?> superMultiset, Multiset<?> subMultiset) {
        Preconditions.checkNotNull(superMultiset);
        Preconditions.checkNotNull(subMultiset);
        for (Entry<?> entry : subMultiset.entrySet()) {
            if (superMultiset.count(entry.getElement()) < entry.getCount()) {
                return false;
            }
        }
        return true;
    }

    public static boolean retainOccurrences(Multiset<?> multisetToModify, Multiset<?> multisetToRetain) {
        return retainOccurrencesImpl(multisetToModify, multisetToRetain);
    }

    private static <E> boolean retainOccurrencesImpl(Multiset<E> multisetToModify, Multiset<?> occurrencesToRetain) {
        Preconditions.checkNotNull(multisetToModify);
        Preconditions.checkNotNull(occurrencesToRetain);
        Iterator<Entry<E>> entryIterator = multisetToModify.entrySet().iterator();
        boolean changed = false;
        while (entryIterator.hasNext()) {
            Entry<E> entry = (Entry) entryIterator.next();
            int retainCount = occurrencesToRetain.count(entry.getElement());
            if (retainCount == 0) {
                entryIterator.remove();
                changed = true;
            } else if (retainCount < entry.getCount()) {
                multisetToModify.setCount(entry.getElement(), retainCount);
                changed = true;
            }
        }
        return changed;
    }

    public static boolean removeOccurrences(Multiset<?> multisetToModify, Iterable<?> occurrencesToRemove) {
        if (occurrencesToRemove instanceof Multiset) {
            return removeOccurrencesImpl((Multiset) multisetToModify, (Multiset) occurrencesToRemove);
        }
        return removeOccurrencesImpl((Multiset) multisetToModify, (Iterable) occurrencesToRemove);
    }

    private static boolean removeOccurrencesImpl(Multiset<?> multisetToModify, Iterable<?> occurrencesToRemove) {
        Preconditions.checkNotNull(multisetToModify);
        Preconditions.checkNotNull(occurrencesToRemove);
        boolean changed = false;
        for (Object o : occurrencesToRemove) {
            changed |= multisetToModify.remove(o);
        }
        return changed;
    }

    private static <E> boolean removeOccurrencesImpl(Multiset<E> multisetToModify, Multiset<?> occurrencesToRemove) {
        Preconditions.checkNotNull(multisetToModify);
        Preconditions.checkNotNull(occurrencesToRemove);
        boolean changed = false;
        Iterator<Entry<E>> entryIterator = multisetToModify.entrySet().iterator();
        while (entryIterator.hasNext()) {
            Entry<E> entry = (Entry) entryIterator.next();
            int removeCount = occurrencesToRemove.count(entry.getElement());
            if (removeCount >= entry.getCount()) {
                entryIterator.remove();
                changed = true;
            } else if (removeCount > 0) {
                multisetToModify.remove(entry.getElement(), removeCount);
                changed = true;
            }
        }
        return changed;
    }

    static boolean equalsImpl(Multiset<?> multiset, @Nullable Object object) {
        if (object == multiset) {
            return true;
        }
        if (!(object instanceof Multiset)) {
            return false;
        }
        Multiset<?> that = (Multiset) object;
        if (multiset.size() != that.size() || multiset.entrySet().size() != that.entrySet().size()) {
            return false;
        }
        for (Entry<?> entry : that.entrySet()) {
            if (multiset.count(entry.getElement()) != entry.getCount()) {
                return false;
            }
        }
        return true;
    }

    static <E> boolean addAllImpl(Multiset<E> self, Collection<? extends E> elements) {
        if (elements.isEmpty()) {
            return false;
        }
        if (elements instanceof Multiset) {
            for (Entry<? extends E> entry : cast(elements).entrySet()) {
                self.add(entry.getElement(), entry.getCount());
            }
        } else {
            Iterators.addAll(self, elements.iterator());
        }
        return true;
    }

    static boolean removeAllImpl(Multiset<?> self, Collection<?> elementsToRemove) {
        Collection<?> collection;
        if (elementsToRemove instanceof Multiset) {
            collection = ((Multiset) elementsToRemove).elementSet();
        } else {
            collection = elementsToRemove;
        }
        return self.elementSet().removeAll(collection);
    }

    static boolean retainAllImpl(Multiset<?> self, Collection<?> elementsToRetain) {
        Collection<?> collection;
        Preconditions.checkNotNull(elementsToRetain);
        if (elementsToRetain instanceof Multiset) {
            collection = ((Multiset) elementsToRetain).elementSet();
        } else {
            collection = elementsToRetain;
        }
        return self.elementSet().retainAll(collection);
    }

    static <E> int setCountImpl(Multiset<E> self, E element, int count) {
        CollectPreconditions.checkNonnegative(count, Bg5Profile.HISTORICAL_NUM_BG);
        int oldCount = self.count(element);
        int delta = count - oldCount;
        if (delta > 0) {
            self.add(element, delta);
        } else if (delta < 0) {
            self.remove(element, -delta);
        }
        return oldCount;
    }

    static <E> boolean setCountImpl(Multiset<E> self, E element, int oldCount, int newCount) {
        CollectPreconditions.checkNonnegative(oldCount, "oldCount");
        CollectPreconditions.checkNonnegative(newCount, "newCount");
        if (self.count(element) != oldCount) {
            return false;
        }
        self.setCount(element, newCount);
        return true;
    }

    static <E> Iterator<E> iteratorImpl(Multiset<E> multiset) {
        return new MultisetIteratorImpl(multiset, multiset.entrySet().iterator());
    }

    static int sizeImpl(Multiset<?> multiset) {
        long size = 0;
        for (Entry<?> entry : multiset.entrySet()) {
            size += (long) entry.getCount();
        }
        return Ints.saturatedCast(size);
    }

    static <T> Multiset<T> cast(Iterable<T> iterable) {
        return (Multiset) iterable;
    }

    @Beta
    public static <E> ImmutableMultiset<E> copyHighestCountFirst(Multiset<E> multiset) {
        return ImmutableMultiset.copyFromEntries(DECREASING_COUNT_ORDERING.immutableSortedCopy(multiset.entrySet()));
    }
}
