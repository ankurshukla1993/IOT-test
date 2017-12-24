package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.RandomAccess;
import java.util.Set;
import javax.annotation.Nullable;

@GwtCompatible(emulated = true)
public final class Iterables {
    private Iterables() {
    }

    public static <T> Iterable<T> unmodifiableIterable(Iterable<T> iterable) {
        Preconditions.checkNotNull(iterable);
        return ((iterable instanceof UnmodifiableIterable) || (iterable instanceof ImmutableCollection)) ? iterable : new UnmodifiableIterable(iterable, null);
    }

    @Deprecated
    public static <E> Iterable<E> unmodifiableIterable(ImmutableCollection<E> iterable) {
        return (Iterable) Preconditions.checkNotNull(iterable);
    }

    public static int size(Iterable<?> iterable) {
        return iterable instanceof Collection ? ((Collection) iterable).size() : Iterators.size(iterable.iterator());
    }

    public static boolean contains(Iterable<?> iterable, @Nullable Object element) {
        if (iterable instanceof Collection) {
            return Collections2.safeContains((Collection) iterable, element);
        }
        return Iterators.contains(iterable.iterator(), element);
    }

    public static boolean removeAll(Iterable<?> removeFrom, Collection<?> elementsToRemove) {
        return removeFrom instanceof Collection ? ((Collection) removeFrom).removeAll((Collection) Preconditions.checkNotNull(elementsToRemove)) : Iterators.removeAll(removeFrom.iterator(), elementsToRemove);
    }

    public static boolean retainAll(Iterable<?> removeFrom, Collection<?> elementsToRetain) {
        return removeFrom instanceof Collection ? ((Collection) removeFrom).retainAll((Collection) Preconditions.checkNotNull(elementsToRetain)) : Iterators.retainAll(removeFrom.iterator(), elementsToRetain);
    }

    public static <T> boolean removeIf(Iterable<T> removeFrom, Predicate<? super T> predicate) {
        if ((removeFrom instanceof RandomAccess) && (removeFrom instanceof List)) {
            return removeIfFromRandomAccessList((List) removeFrom, (Predicate) Preconditions.checkNotNull(predicate));
        }
        return Iterators.removeIf(removeFrom.iterator(), predicate);
    }

    private static <T> boolean removeIfFromRandomAccessList(List<T> list, Predicate<? super T> predicate) {
        int from = 0;
        int to = 0;
        while (from < list.size()) {
            T element = list.get(from);
            if (!predicate.apply(element)) {
                if (from > to) {
                    try {
                        list.set(to, element);
                    } catch (UnsupportedOperationException e) {
                        slowRemoveIfForRemainingElements(list, predicate, to, from);
                        return true;
                    }
                }
                to++;
            }
            from++;
        }
        list.subList(to, list.size()).clear();
        if (from == to) {
            return false;
        }
        return true;
    }

    private static <T> void slowRemoveIfForRemainingElements(List<T> list, Predicate<? super T> predicate, int to, int from) {
        int n;
        for (n = list.size() - 1; n > from; n--) {
            if (predicate.apply(list.get(n))) {
                list.remove(n);
            }
        }
        for (n = from - 1; n >= to; n--) {
            list.remove(n);
        }
    }

    @Nullable
    static <T> T removeFirstMatching(Iterable<T> removeFrom, Predicate<? super T> predicate) {
        Preconditions.checkNotNull(predicate);
        Iterator<T> iterator = removeFrom.iterator();
        while (iterator.hasNext()) {
            T next = iterator.next();
            if (predicate.apply(next)) {
                iterator.remove();
                return next;
            }
        }
        return null;
    }

    public static boolean elementsEqual(Iterable<?> iterable1, Iterable<?> iterable2) {
        if ((iterable1 instanceof Collection) && (iterable2 instanceof Collection)) {
            if (((Collection) iterable1).size() != ((Collection) iterable2).size()) {
                return false;
            }
        }
        return Iterators.elementsEqual(iterable1.iterator(), iterable2.iterator());
    }

    public static String toString(Iterable<?> iterable) {
        return Iterators.toString(iterable.iterator());
    }

    public static <T> T getOnlyElement(Iterable<T> iterable) {
        return Iterators.getOnlyElement(iterable.iterator());
    }

    @Nullable
    public static <T> T getOnlyElement(Iterable<? extends T> iterable, @Nullable T defaultValue) {
        return Iterators.getOnlyElement(iterable.iterator(), defaultValue);
    }

    @GwtIncompatible("Array.newInstance(Class, int)")
    public static <T> T[] toArray(Iterable<? extends T> iterable, Class<T> type) {
        Collection<? extends T> collection = toCollection(iterable);
        return collection.toArray(ObjectArrays.newArray(type, collection.size()));
    }

    static Object[] toArray(Iterable<?> iterable) {
        return toCollection(iterable).toArray();
    }

    private static <E> Collection<E> toCollection(Iterable<E> iterable) {
        return iterable instanceof Collection ? (Collection) iterable : Lists.newArrayList(iterable.iterator());
    }

    public static <T> boolean addAll(Collection<T> addTo, Iterable<? extends T> elementsToAdd) {
        if (elementsToAdd instanceof Collection) {
            return addTo.addAll(Collections2.cast(elementsToAdd));
        }
        return Iterators.addAll(addTo, ((Iterable) Preconditions.checkNotNull(elementsToAdd)).iterator());
    }

    public static int frequency(Iterable<?> iterable, @Nullable Object element) {
        if (iterable instanceof Multiset) {
            return ((Multiset) iterable).count(element);
        }
        if (iterable instanceof Set) {
            return ((Set) iterable).contains(element) ? 1 : 0;
        } else {
            return Iterators.frequency(iterable.iterator(), element);
        }
    }

    public static <T> Iterable<T> cycle(Iterable<T> iterable) {
        Preconditions.checkNotNull(iterable);
        return new 1(iterable);
    }

    public static <T> Iterable<T> cycle(T... elements) {
        return cycle(Lists.newArrayList(elements));
    }

    public static <T> Iterable<T> concat(Iterable<? extends T> a, Iterable<? extends T> b) {
        return concat(ImmutableList.of(a, b));
    }

    public static <T> Iterable<T> concat(Iterable<? extends T> a, Iterable<? extends T> b, Iterable<? extends T> c) {
        return concat(ImmutableList.of(a, b, c));
    }

    public static <T> Iterable<T> concat(Iterable<? extends T> a, Iterable<? extends T> b, Iterable<? extends T> c, Iterable<? extends T> d) {
        return concat(ImmutableList.of(a, b, c, d));
    }

    public static <T> Iterable<T> concat(Iterable<? extends T>... inputs) {
        return concat(ImmutableList.copyOf((Object[]) inputs));
    }

    public static <T> Iterable<T> concat(Iterable<? extends Iterable<? extends T>> inputs) {
        Preconditions.checkNotNull(inputs);
        return new 2(inputs);
    }

    private static <T> Iterator<Iterator<? extends T>> iterators(Iterable<? extends Iterable<? extends T>> iterables) {
        return new 3(iterables.iterator());
    }

    public static <T> Iterable<List<T>> partition(Iterable<T> iterable, int size) {
        Preconditions.checkNotNull(iterable);
        Preconditions.checkArgument(size > 0);
        return new 4(iterable, size);
    }

    public static <T> Iterable<List<T>> paddedPartition(Iterable<T> iterable, int size) {
        Preconditions.checkNotNull(iterable);
        Preconditions.checkArgument(size > 0);
        return new 5(iterable, size);
    }

    public static <T> Iterable<T> filter(Iterable<T> unfiltered, Predicate<? super T> predicate) {
        Preconditions.checkNotNull(unfiltered);
        Preconditions.checkNotNull(predicate);
        return new 6(unfiltered, predicate);
    }

    @GwtIncompatible("Class.isInstance")
    public static <T> Iterable<T> filter(Iterable<?> unfiltered, Class<T> type) {
        Preconditions.checkNotNull(unfiltered);
        Preconditions.checkNotNull(type);
        return new 7(unfiltered, type);
    }

    public static <T> boolean any(Iterable<T> iterable, Predicate<? super T> predicate) {
        return Iterators.any(iterable.iterator(), predicate);
    }

    public static <T> boolean all(Iterable<T> iterable, Predicate<? super T> predicate) {
        return Iterators.all(iterable.iterator(), predicate);
    }

    public static <T> T find(Iterable<T> iterable, Predicate<? super T> predicate) {
        return Iterators.find(iterable.iterator(), predicate);
    }

    @Nullable
    public static <T> T find(Iterable<? extends T> iterable, Predicate<? super T> predicate, @Nullable T defaultValue) {
        return Iterators.find(iterable.iterator(), predicate, defaultValue);
    }

    public static <T> Optional<T> tryFind(Iterable<T> iterable, Predicate<? super T> predicate) {
        return Iterators.tryFind(iterable.iterator(), predicate);
    }

    public static <T> int indexOf(Iterable<T> iterable, Predicate<? super T> predicate) {
        return Iterators.indexOf(iterable.iterator(), predicate);
    }

    public static <F, T> Iterable<T> transform(Iterable<F> fromIterable, Function<? super F, ? extends T> function) {
        Preconditions.checkNotNull(fromIterable);
        Preconditions.checkNotNull(function);
        return new 8(fromIterable, function);
    }

    public static <T> T get(Iterable<T> iterable, int position) {
        Preconditions.checkNotNull(iterable);
        return iterable instanceof List ? ((List) iterable).get(position) : Iterators.get(iterable.iterator(), position);
    }

    @Nullable
    public static <T> T get(Iterable<? extends T> iterable, int position, @Nullable T defaultValue) {
        Preconditions.checkNotNull(iterable);
        Iterators.checkNonnegative(position);
        if (iterable instanceof List) {
            List<? extends T> list = Lists.cast(iterable);
            if (position < list.size()) {
                return list.get(position);
            }
            return defaultValue;
        }
        Iterator<? extends T> iterator = iterable.iterator();
        Iterators.advance(iterator, position);
        return Iterators.getNext(iterator, defaultValue);
    }

    @Nullable
    public static <T> T getFirst(Iterable<? extends T> iterable, @Nullable T defaultValue) {
        return Iterators.getNext(iterable.iterator(), defaultValue);
    }

    public static <T> T getLast(Iterable<T> iterable) {
        if (!(iterable instanceof List)) {
            return Iterators.getLast(iterable.iterator());
        }
        List<T> list = (List) iterable;
        if (!list.isEmpty()) {
            return getLastInNonemptyList(list);
        }
        throw new NoSuchElementException();
    }

    @Nullable
    public static <T> T getLast(Iterable<? extends T> iterable, @Nullable T defaultValue) {
        if (iterable instanceof Collection) {
            if (Collections2.cast(iterable).isEmpty()) {
                return defaultValue;
            }
            if (iterable instanceof List) {
                return getLastInNonemptyList(Lists.cast(iterable));
            }
        }
        return Iterators.getLast(iterable.iterator(), defaultValue);
    }

    private static <T> T getLastInNonemptyList(List<T> list) {
        return list.get(list.size() - 1);
    }

    public static <T> Iterable<T> skip(Iterable<T> iterable, int numberToSkip) {
        Preconditions.checkNotNull(iterable);
        Preconditions.checkArgument(numberToSkip >= 0, "number to skip cannot be negative");
        if (iterable instanceof List) {
            return new 9((List) iterable, numberToSkip);
        }
        return new 10(iterable, numberToSkip);
    }

    public static <T> Iterable<T> limit(Iterable<T> iterable, int limitSize) {
        Preconditions.checkNotNull(iterable);
        Preconditions.checkArgument(limitSize >= 0, "limit is negative");
        return new 11(iterable, limitSize);
    }

    public static <T> Iterable<T> consumingIterable(Iterable<T> iterable) {
        if (iterable instanceof Queue) {
            return new 12(iterable);
        }
        Preconditions.checkNotNull(iterable);
        return new 13(iterable);
    }

    public static boolean isEmpty(Iterable<?> iterable) {
        if (iterable instanceof Collection) {
            return ((Collection) iterable).isEmpty();
        }
        return !iterable.iterator().hasNext();
    }

    @Beta
    public static <T> Iterable<T> mergeSorted(Iterable<? extends Iterable<? extends T>> iterables, Comparator<? super T> comparator) {
        Preconditions.checkNotNull(iterables, "iterables");
        Preconditions.checkNotNull(comparator, "comparator");
        return new UnmodifiableIterable(new 14(iterables, comparator), null);
    }

    private static <T> Function<Iterable<? extends T>, Iterator<? extends T>> toIterator() {
        return new 15();
    }
}
