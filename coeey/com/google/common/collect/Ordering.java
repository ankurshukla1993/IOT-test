package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import javax.annotation.Nullable;

@GwtCompatible
public abstract class Ordering<T> implements Comparator<T> {
    static final int LEFT_IS_GREATER = 1;
    static final int RIGHT_IS_GREATER = -1;

    public abstract int compare(@Nullable T t, @Nullable T t2);

    @GwtCompatible(serializable = true)
    public static <C extends Comparable> Ordering<C> natural() {
        return NaturalOrdering.INSTANCE;
    }

    @GwtCompatible(serializable = true)
    public static <T> Ordering<T> from(Comparator<T> comparator) {
        return comparator instanceof Ordering ? (Ordering) comparator : new ComparatorOrdering(comparator);
    }

    @GwtCompatible(serializable = true)
    @Deprecated
    public static <T> Ordering<T> from(Ordering<T> ordering) {
        return (Ordering) Preconditions.checkNotNull(ordering);
    }

    @GwtCompatible(serializable = true)
    public static <T> Ordering<T> explicit(List<T> valuesInOrder) {
        return new ExplicitOrdering(valuesInOrder);
    }

    @GwtCompatible(serializable = true)
    public static <T> Ordering<T> explicit(T leastValue, T... remainingValuesInOrder) {
        return explicit(Lists.asList(leastValue, remainingValuesInOrder));
    }

    @GwtCompatible(serializable = true)
    public static Ordering<Object> allEqual() {
        return AllEqualOrdering.INSTANCE;
    }

    @GwtCompatible(serializable = true)
    public static Ordering<Object> usingToString() {
        return UsingToStringOrdering.INSTANCE;
    }

    public static Ordering<Object> arbitrary() {
        return ArbitraryOrderingHolder.ARBITRARY_ORDERING;
    }

    protected Ordering() {
    }

    @GwtCompatible(serializable = true)
    public <S extends T> Ordering<S> reverse() {
        return new ReverseOrdering(this);
    }

    @GwtCompatible(serializable = true)
    public <S extends T> Ordering<S> nullsFirst() {
        return new NullsFirstOrdering(this);
    }

    @GwtCompatible(serializable = true)
    public <S extends T> Ordering<S> nullsLast() {
        return new NullsLastOrdering(this);
    }

    @GwtCompatible(serializable = true)
    public <F> Ordering<F> onResultOf(Function<F, ? extends T> function) {
        return new ByFunctionOrdering(function, this);
    }

    <T2 extends T> Ordering<Entry<T2, ?>> onKeys() {
        return onResultOf(Maps.keyFunction());
    }

    @GwtCompatible(serializable = true)
    public <U extends T> Ordering<U> compound(Comparator<? super U> secondaryComparator) {
        return new CompoundOrdering(this, (Comparator) Preconditions.checkNotNull(secondaryComparator));
    }

    @GwtCompatible(serializable = true)
    public static <T> Ordering<T> compound(Iterable<? extends Comparator<? super T>> comparators) {
        return new CompoundOrdering(comparators);
    }

    @GwtCompatible(serializable = true)
    public <S extends T> Ordering<Iterable<S>> lexicographical() {
        return new LexicographicalOrdering(this);
    }

    public <E extends T> E min(Iterator<E> iterator) {
        E minSoFar = iterator.next();
        while (iterator.hasNext()) {
            minSoFar = min(minSoFar, iterator.next());
        }
        return minSoFar;
    }

    public <E extends T> E min(Iterable<E> iterable) {
        return min(iterable.iterator());
    }

    public <E extends T> E min(@Nullable E a, @Nullable E b) {
        return compare(a, b) <= 0 ? a : b;
    }

    public <E extends T> E min(@Nullable E a, @Nullable E b, @Nullable E c, E... rest) {
        E minSoFar = min(min(a, b), c);
        for (E r : rest) {
            minSoFar = min(minSoFar, r);
        }
        return minSoFar;
    }

    public <E extends T> E max(Iterator<E> iterator) {
        E maxSoFar = iterator.next();
        while (iterator.hasNext()) {
            maxSoFar = max(maxSoFar, iterator.next());
        }
        return maxSoFar;
    }

    public <E extends T> E max(Iterable<E> iterable) {
        return max(iterable.iterator());
    }

    public <E extends T> E max(@Nullable E a, @Nullable E b) {
        return compare(a, b) >= 0 ? a : b;
    }

    public <E extends T> E max(@Nullable E a, @Nullable E b, @Nullable E c, E... rest) {
        E maxSoFar = max(max(a, b), c);
        for (E r : rest) {
            maxSoFar = max(maxSoFar, r);
        }
        return maxSoFar;
    }

    public <E extends T> List<E> leastOf(Iterable<E> iterable, int k) {
        if (iterable instanceof Collection) {
            Collection<E> collection = (Collection) iterable;
            if (((long) collection.size()) <= 2 * ((long) k)) {
                E[] array = collection.toArray();
                Arrays.sort(array, this);
                if (array.length > k) {
                    array = ObjectArrays.arraysCopyOf(array, k);
                }
                return Collections.unmodifiableList(Arrays.asList(array));
            }
        }
        return leastOf(iterable.iterator(), k);
    }

    public <E extends T> List<E> leastOf(Iterator<E> elements, int k) {
        Preconditions.checkNotNull(elements);
        CollectPreconditions.checkNonnegative(k, "k");
        if (k == 0 || !elements.hasNext()) {
            return ImmutableList.of();
        }
        if (k >= 1073741823) {
            ArrayList<E> list = Lists.newArrayList(elements);
            Collections.sort(list, this);
            if (list.size() > k) {
                list.subList(k, list.size()).clear();
            }
            list.trimToSize();
            return Collections.unmodifiableList(list);
        }
        int bufferCap = k * 2;
        Object[] buffer = (Object[]) new Object[bufferCap];
        E threshold = elements.next();
        buffer[0] = threshold;
        int bufferSize = 1;
        while (bufferSize < k && elements.hasNext()) {
            E e = elements.next();
            int bufferSize2 = bufferSize + 1;
            buffer[bufferSize] = e;
            threshold = max(threshold, e);
            bufferSize = bufferSize2;
        }
        while (true) {
            bufferSize2 = bufferSize;
            while (elements.hasNext()) {
                e = elements.next();
                if (compare(e, threshold) < 0) {
                    bufferSize = bufferSize2 + 1;
                    buffer[bufferSize2] = e;
                    if (bufferSize == bufferCap) {
                        int left = 0;
                        int right = bufferCap - 1;
                        int minThresholdPosition = 0;
                        while (left < right) {
                            int pivotNewIndex = partition(buffer, left, right, ((left + right) + 1) >>> 1);
                            if (pivotNewIndex <= k) {
                                if (pivotNewIndex >= k) {
                                    break;
                                }
                                left = Math.max(pivotNewIndex, left + 1);
                                minThresholdPosition = pivotNewIndex;
                            } else {
                                right = pivotNewIndex - 1;
                            }
                        }
                        bufferSize2 = k;
                        threshold = buffer[minThresholdPosition];
                        for (int i = minThresholdPosition + 1; i < bufferSize2; i++) {
                            threshold = max(threshold, buffer[i]);
                        }
                    }
                }
            }
            Arrays.sort(buffer, 0, bufferSize2, this);
            return Collections.unmodifiableList(Arrays.asList(ObjectArrays.arraysCopyOf(buffer, Math.min(bufferSize2, k))));
        }
    }

    private <E extends T> int partition(E[] values, int left, int right, int pivotIndex) {
        E pivotValue = values[pivotIndex];
        values[pivotIndex] = values[right];
        values[right] = pivotValue;
        int storeIndex = left;
        for (int i = left; i < right; i++) {
            if (compare(values[i], pivotValue) < 0) {
                ObjectArrays.swap(values, storeIndex, i);
                storeIndex++;
            }
        }
        ObjectArrays.swap(values, right, storeIndex);
        return storeIndex;
    }

    public <E extends T> List<E> greatestOf(Iterable<E> iterable, int k) {
        return reverse().leastOf((Iterable) iterable, k);
    }

    public <E extends T> List<E> greatestOf(Iterator<E> iterator, int k) {
        return reverse().leastOf((Iterator) iterator, k);
    }

    public <E extends T> List<E> sortedCopy(Iterable<E> elements) {
        Object[] array = Iterables.toArray(elements);
        Arrays.sort(array, this);
        return Lists.newArrayList(Arrays.asList(array));
    }

    public <E extends T> ImmutableList<E> immutableSortedCopy(Iterable<E> elements) {
        Object[] array = Iterables.toArray(elements);
        for (E e : array) {
            Preconditions.checkNotNull(e);
        }
        Arrays.sort(array, this);
        return ImmutableList.asImmutableList(array);
    }

    public boolean isOrdered(Iterable<? extends T> iterable) {
        Iterator<? extends T> it = iterable.iterator();
        if (it.hasNext()) {
            T prev = it.next();
            while (it.hasNext()) {
                T next = it.next();
                if (compare(prev, next) > 0) {
                    return false;
                }
                prev = next;
            }
        }
        return true;
    }

    public boolean isStrictlyOrdered(Iterable<? extends T> iterable) {
        Iterator<? extends T> it = iterable.iterator();
        if (it.hasNext()) {
            T prev = it.next();
            while (it.hasNext()) {
                T next = it.next();
                if (compare(prev, next) >= 0) {
                    return false;
                }
                prev = next;
            }
        }
        return true;
    }

    public int binarySearch(List<? extends T> sortedList, @Nullable T key) {
        return Collections.binarySearch(sortedList, key, this);
    }
}
