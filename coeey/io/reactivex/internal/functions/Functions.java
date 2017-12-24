package io.reactivex.internal.functions;

import io.reactivex.Notification;
import io.reactivex.Scheduler;
import io.reactivex.exceptions.OnErrorNotImplementedException;
import io.reactivex.functions.Action;
import io.reactivex.functions.BiConsumer;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.BooleanSupplier;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Function3;
import io.reactivex.functions.Function4;
import io.reactivex.functions.Function5;
import io.reactivex.functions.Function6;
import io.reactivex.functions.Function7;
import io.reactivex.functions.Function8;
import io.reactivex.functions.Function9;
import io.reactivex.functions.LongConsumer;
import io.reactivex.functions.Predicate;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Timed;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import org.reactivestreams.Subscription;

public final class Functions {
    static final Predicate<Object> ALWAYS_FALSE = new FalsePredicate();
    static final Predicate<Object> ALWAYS_TRUE = new TruePredicate();
    public static final Action EMPTY_ACTION = new EmptyAction();
    static final Consumer<Object> EMPTY_CONSUMER = new EmptyConsumer();
    public static final LongConsumer EMPTY_LONG_CONSUMER = new EmptyLongConsumer();
    public static final Runnable EMPTY_RUNNABLE = new EmptyRunnable();
    public static final Consumer<Throwable> ERROR_CONSUMER = new ErrorConsumer();
    static final Function<Object, Object> IDENTITY = new Identity();
    static final Comparator<Object> NATURAL_COMPARATOR = new NaturalObjectComparator();
    static final Callable<Object> NULL_SUPPLIER = new NullCallable();
    public static final Consumer<Throwable> ON_ERROR_MISSING = new OnErrorMissingConsumer();
    public static final Consumer<Subscription> REQUEST_MAX = new MaxRequestSubscription();

    static final class ActionConsumer<T> implements Consumer<T> {
        final Action action;

        ActionConsumer(Action action) {
            this.action = action;
        }

        public void accept(T t) throws Exception {
            this.action.run();
        }
    }

    static final class Array2Func<T1, T2, R> implements Function<Object[], R> {
        final BiFunction<? super T1, ? super T2, ? extends R> f2472f;

        Array2Func(BiFunction<? super T1, ? super T2, ? extends R> f) {
            this.f2472f = f;
        }

        public R apply(Object[] a) throws Exception {
            if (a.length == 2) {
                return this.f2472f.apply(a[0], a[1]);
            }
            throw new IllegalArgumentException("Array of size 2 expected but got " + a.length);
        }
    }

    static final class Array3Func<T1, T2, T3, R> implements Function<Object[], R> {
        final Function3<T1, T2, T3, R> f2473f;

        Array3Func(Function3<T1, T2, T3, R> f) {
            this.f2473f = f;
        }

        public R apply(Object[] a) throws Exception {
            if (a.length == 3) {
                return this.f2473f.apply(a[0], a[1], a[2]);
            }
            throw new IllegalArgumentException("Array of size 3 expected but got " + a.length);
        }
    }

    static final class Array4Func<T1, T2, T3, T4, R> implements Function<Object[], R> {
        final Function4<T1, T2, T3, T4, R> f2474f;

        Array4Func(Function4<T1, T2, T3, T4, R> f) {
            this.f2474f = f;
        }

        public R apply(Object[] a) throws Exception {
            if (a.length == 4) {
                return this.f2474f.apply(a[0], a[1], a[2], a[3]);
            }
            throw new IllegalArgumentException("Array of size 4 expected but got " + a.length);
        }
    }

    static final class Array5Func<T1, T2, T3, T4, T5, R> implements Function<Object[], R> {
        private final Function5<T1, T2, T3, T4, T5, R> f2475f;

        Array5Func(Function5<T1, T2, T3, T4, T5, R> f) {
            this.f2475f = f;
        }

        public R apply(Object[] a) throws Exception {
            if (a.length == 5) {
                return this.f2475f.apply(a[0], a[1], a[2], a[3], a[4]);
            }
            throw new IllegalArgumentException("Array of size 5 expected but got " + a.length);
        }
    }

    static final class Array6Func<T1, T2, T3, T4, T5, T6, R> implements Function<Object[], R> {
        final Function6<T1, T2, T3, T4, T5, T6, R> f2476f;

        Array6Func(Function6<T1, T2, T3, T4, T5, T6, R> f) {
            this.f2476f = f;
        }

        public R apply(Object[] a) throws Exception {
            if (a.length == 6) {
                return this.f2476f.apply(a[0], a[1], a[2], a[3], a[4], a[5]);
            }
            throw new IllegalArgumentException("Array of size 6 expected but got " + a.length);
        }
    }

    static final class Array7Func<T1, T2, T3, T4, T5, T6, T7, R> implements Function<Object[], R> {
        final Function7<T1, T2, T3, T4, T5, T6, T7, R> f2477f;

        Array7Func(Function7<T1, T2, T3, T4, T5, T6, T7, R> f) {
            this.f2477f = f;
        }

        public R apply(Object[] a) throws Exception {
            if (a.length == 7) {
                return this.f2477f.apply(a[0], a[1], a[2], a[3], a[4], a[5], a[6]);
            }
            throw new IllegalArgumentException("Array of size 7 expected but got " + a.length);
        }
    }

    static final class Array8Func<T1, T2, T3, T4, T5, T6, T7, T8, R> implements Function<Object[], R> {
        final Function8<T1, T2, T3, T4, T5, T6, T7, T8, R> f2478f;

        Array8Func(Function8<T1, T2, T3, T4, T5, T6, T7, T8, R> f) {
            this.f2478f = f;
        }

        public R apply(Object[] a) throws Exception {
            if (a.length == 8) {
                return this.f2478f.apply(a[0], a[1], a[2], a[3], a[4], a[5], a[6], a[7]);
            }
            throw new IllegalArgumentException("Array of size 8 expected but got " + a.length);
        }
    }

    static final class Array9Func<T1, T2, T3, T4, T5, T6, T7, T8, T9, R> implements Function<Object[], R> {
        final Function9<T1, T2, T3, T4, T5, T6, T7, T8, T9, R> f2479f;

        Array9Func(Function9<T1, T2, T3, T4, T5, T6, T7, T8, T9, R> f) {
            this.f2479f = f;
        }

        public R apply(Object[] a) throws Exception {
            if (a.length == 9) {
                return this.f2479f.apply(a[0], a[1], a[2], a[3], a[4], a[5], a[6], a[7], a[8]);
            }
            throw new IllegalArgumentException("Array of size 9 expected but got " + a.length);
        }
    }

    static final class ArrayListCapacityCallable<T> implements Callable<List<T>> {
        final int capacity;

        ArrayListCapacityCallable(int capacity) {
            this.capacity = capacity;
        }

        public List<T> call() throws Exception {
            return new ArrayList(this.capacity);
        }
    }

    static final class BooleanSupplierPredicateReverse<T> implements Predicate<T> {
        final BooleanSupplier supplier;

        BooleanSupplierPredicateReverse(BooleanSupplier supplier) {
            this.supplier = supplier;
        }

        public boolean test(T t) throws Exception {
            return !this.supplier.getAsBoolean();
        }
    }

    static final class CastToClass<T, U> implements Function<T, U> {
        final Class<U> clazz;

        CastToClass(Class<U> clazz) {
            this.clazz = clazz;
        }

        public U apply(T t) throws Exception {
            return this.clazz.cast(t);
        }
    }

    static final class ClassFilter<T, U> implements Predicate<T> {
        final Class<U> clazz;

        ClassFilter(Class<U> clazz) {
            this.clazz = clazz;
        }

        public boolean test(T t) throws Exception {
            return this.clazz.isInstance(t);
        }
    }

    static final class EmptyAction implements Action {
        EmptyAction() {
        }

        public void run() {
        }

        public String toString() {
            return "EmptyAction";
        }
    }

    static final class EmptyConsumer implements Consumer<Object> {
        EmptyConsumer() {
        }

        public void accept(Object v) {
        }

        public String toString() {
            return "EmptyConsumer";
        }
    }

    static final class EmptyLongConsumer implements LongConsumer {
        EmptyLongConsumer() {
        }

        public void accept(long v) {
        }
    }

    static final class EmptyRunnable implements Runnable {
        EmptyRunnable() {
        }

        public void run() {
        }

        public String toString() {
            return "EmptyRunnable";
        }
    }

    static final class EqualsPredicate<T> implements Predicate<T> {
        final T value;

        EqualsPredicate(T value) {
            this.value = value;
        }

        public boolean test(T t) throws Exception {
            return ObjectHelper.equals(t, this.value);
        }
    }

    static final class ErrorConsumer implements Consumer<Throwable> {
        ErrorConsumer() {
        }

        public void accept(Throwable error) {
            RxJavaPlugins.onError(error);
        }
    }

    static final class FalsePredicate implements Predicate<Object> {
        FalsePredicate() {
        }

        public boolean test(Object o) {
            return false;
        }
    }

    static final class FutureAction implements Action {
        final Future<?> future;

        FutureAction(Future<?> future) {
            this.future = future;
        }

        public void run() throws Exception {
            this.future.get();
        }
    }

    enum HashSetCallable implements Callable<Set<Object>> {
        INSTANCE;

        public Set<Object> call() throws Exception {
            return new HashSet();
        }
    }

    static final class Identity implements Function<Object, Object> {
        Identity() {
        }

        public Object apply(Object v) {
            return v;
        }

        public String toString() {
            return "IdentityFunction";
        }
    }

    static final class JustValue<T, U> implements Callable<U>, Function<T, U> {
        final U value;

        JustValue(U value) {
            this.value = value;
        }

        public U call() throws Exception {
            return this.value;
        }

        public U apply(T t) throws Exception {
            return this.value;
        }
    }

    static final class ListSorter<T> implements Function<List<T>, List<T>> {
        final Comparator<? super T> comparator;

        ListSorter(Comparator<? super T> comparator) {
            this.comparator = comparator;
        }

        public List<T> apply(List<T> v) {
            Collections.sort(v, this.comparator);
            return v;
        }
    }

    static final class MaxRequestSubscription implements Consumer<Subscription> {
        MaxRequestSubscription() {
        }

        public void accept(Subscription t) throws Exception {
            t.request(Long.MAX_VALUE);
        }
    }

    enum NaturalComparator implements Comparator<Object> {
        INSTANCE;

        public int compare(Object o1, Object o2) {
            return ((Comparable) o1).compareTo(o2);
        }
    }

    static final class NaturalObjectComparator implements Comparator<Object> {
        NaturalObjectComparator() {
        }

        public int compare(Object a, Object b) {
            return ((Comparable) a).compareTo(b);
        }
    }

    static final class NotificationOnComplete<T> implements Action {
        final Consumer<? super Notification<T>> onNotification;

        NotificationOnComplete(Consumer<? super Notification<T>> onNotification) {
            this.onNotification = onNotification;
        }

        public void run() throws Exception {
            this.onNotification.accept(Notification.createOnComplete());
        }
    }

    static final class NotificationOnError<T> implements Consumer<Throwable> {
        final Consumer<? super Notification<T>> onNotification;

        NotificationOnError(Consumer<? super Notification<T>> onNotification) {
            this.onNotification = onNotification;
        }

        public void accept(Throwable v) throws Exception {
            this.onNotification.accept(Notification.createOnError(v));
        }
    }

    static final class NotificationOnNext<T> implements Consumer<T> {
        final Consumer<? super Notification<T>> onNotification;

        NotificationOnNext(Consumer<? super Notification<T>> onNotification) {
            this.onNotification = onNotification;
        }

        public void accept(T v) throws Exception {
            this.onNotification.accept(Notification.createOnNext(v));
        }
    }

    static final class NullCallable implements Callable<Object> {
        NullCallable() {
        }

        public Object call() {
            return null;
        }
    }

    static final class OnErrorMissingConsumer implements Consumer<Throwable> {
        OnErrorMissingConsumer() {
        }

        public void accept(Throwable error) {
            RxJavaPlugins.onError(new OnErrorNotImplementedException(error));
        }
    }

    static final class TimestampFunction<T> implements Function<T, Timed<T>> {
        final Scheduler scheduler;
        final TimeUnit unit;

        TimestampFunction(TimeUnit unit, Scheduler scheduler) {
            this.unit = unit;
            this.scheduler = scheduler;
        }

        public Timed<T> apply(T t) throws Exception {
            return new Timed(t, this.scheduler.now(this.unit), this.unit);
        }
    }

    static final class ToMapKeySelector<K, T> implements BiConsumer<Map<K, T>, T> {
        private final Function<? super T, ? extends K> keySelector;

        ToMapKeySelector(Function<? super T, ? extends K> keySelector) {
            this.keySelector = keySelector;
        }

        public void accept(Map<K, T> m, T t) throws Exception {
            m.put(this.keySelector.apply(t), t);
        }
    }

    static final class ToMapKeyValueSelector<K, V, T> implements BiConsumer<Map<K, V>, T> {
        private final Function<? super T, ? extends K> keySelector;
        private final Function<? super T, ? extends V> valueSelector;

        ToMapKeyValueSelector(Function<? super T, ? extends V> valueSelector, Function<? super T, ? extends K> keySelector) {
            this.valueSelector = valueSelector;
            this.keySelector = keySelector;
        }

        public void accept(Map<K, V> m, T t) throws Exception {
            m.put(this.keySelector.apply(t), this.valueSelector.apply(t));
        }
    }

    static final class ToMultimapKeyValueSelector<K, V, T> implements BiConsumer<Map<K, Collection<V>>, T> {
        private final Function<? super K, ? extends Collection<? super V>> collectionFactory;
        private final Function<? super T, ? extends K> keySelector;
        private final Function<? super T, ? extends V> valueSelector;

        ToMultimapKeyValueSelector(Function<? super K, ? extends Collection<? super V>> collectionFactory, Function<? super T, ? extends V> valueSelector, Function<? super T, ? extends K> keySelector) {
            this.collectionFactory = collectionFactory;
            this.valueSelector = valueSelector;
            this.keySelector = keySelector;
        }

        public void accept(Map<K, Collection<V>> m, T t) throws Exception {
            K key = this.keySelector.apply(t);
            Collection<V> coll = (Collection) m.get(key);
            if (coll == null) {
                coll = (Collection) this.collectionFactory.apply(key);
                m.put(key, coll);
            }
            coll.add(this.valueSelector.apply(t));
        }
    }

    static final class TruePredicate implements Predicate<Object> {
        TruePredicate() {
        }

        public boolean test(Object o) {
            return true;
        }
    }

    private Functions() {
        throw new IllegalStateException("No instances!");
    }

    public static <T1, T2, R> Function<Object[], R> toFunction(BiFunction<? super T1, ? super T2, ? extends R> f) {
        ObjectHelper.requireNonNull(f, "f is null");
        return new Array2Func(f);
    }

    public static <T1, T2, T3, R> Function<Object[], R> toFunction(Function3<T1, T2, T3, R> f) {
        ObjectHelper.requireNonNull(f, "f is null");
        return new Array3Func(f);
    }

    public static <T1, T2, T3, T4, R> Function<Object[], R> toFunction(Function4<T1, T2, T3, T4, R> f) {
        ObjectHelper.requireNonNull(f, "f is null");
        return new Array4Func(f);
    }

    public static <T1, T2, T3, T4, T5, R> Function<Object[], R> toFunction(Function5<T1, T2, T3, T4, T5, R> f) {
        ObjectHelper.requireNonNull(f, "f is null");
        return new Array5Func(f);
    }

    public static <T1, T2, T3, T4, T5, T6, R> Function<Object[], R> toFunction(Function6<T1, T2, T3, T4, T5, T6, R> f) {
        ObjectHelper.requireNonNull(f, "f is null");
        return new Array6Func(f);
    }

    public static <T1, T2, T3, T4, T5, T6, T7, R> Function<Object[], R> toFunction(Function7<T1, T2, T3, T4, T5, T6, T7, R> f) {
        ObjectHelper.requireNonNull(f, "f is null");
        return new Array7Func(f);
    }

    public static <T1, T2, T3, T4, T5, T6, T7, T8, R> Function<Object[], R> toFunction(Function8<T1, T2, T3, T4, T5, T6, T7, T8, R> f) {
        ObjectHelper.requireNonNull(f, "f is null");
        return new Array8Func(f);
    }

    public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, R> Function<Object[], R> toFunction(Function9<T1, T2, T3, T4, T5, T6, T7, T8, T9, R> f) {
        ObjectHelper.requireNonNull(f, "f is null");
        return new Array9Func(f);
    }

    public static <T> Function<T, T> identity() {
        return IDENTITY;
    }

    public static <T> Consumer<T> emptyConsumer() {
        return EMPTY_CONSUMER;
    }

    public static <T> Predicate<T> alwaysTrue() {
        return ALWAYS_TRUE;
    }

    public static <T> Predicate<T> alwaysFalse() {
        return ALWAYS_FALSE;
    }

    public static <T> Callable<T> nullSupplier() {
        return NULL_SUPPLIER;
    }

    public static <T> Comparator<T> naturalOrder() {
        return NATURAL_COMPARATOR;
    }

    public static Action futureAction(Future<?> future) {
        return new FutureAction(future);
    }

    public static <T> Callable<T> justCallable(T value) {
        return new JustValue(value);
    }

    public static <T, U> Function<T, U> justFunction(U value) {
        return new JustValue(value);
    }

    public static <T, U> Function<T, U> castFunction(Class<U> target) {
        return new CastToClass(target);
    }

    public static <T> Callable<List<T>> createArrayList(int capacity) {
        return new ArrayListCapacityCallable(capacity);
    }

    public static <T> Predicate<T> equalsWith(T value) {
        return new EqualsPredicate(value);
    }

    public static <T> Callable<Set<T>> createHashSet() {
        return HashSetCallable.INSTANCE;
    }

    public static <T> Consumer<T> notificationOnNext(Consumer<? super Notification<T>> onNotification) {
        return new NotificationOnNext(onNotification);
    }

    public static <T> Consumer<Throwable> notificationOnError(Consumer<? super Notification<T>> onNotification) {
        return new NotificationOnError(onNotification);
    }

    public static <T> Action notificationOnComplete(Consumer<? super Notification<T>> onNotification) {
        return new NotificationOnComplete(onNotification);
    }

    public static <T> Consumer<T> actionConsumer(Action action) {
        return new ActionConsumer(action);
    }

    public static <T, U> Predicate<T> isInstanceOf(Class<U> clazz) {
        return new ClassFilter(clazz);
    }

    public static <T> Predicate<T> predicateReverseFor(BooleanSupplier supplier) {
        return new BooleanSupplierPredicateReverse(supplier);
    }

    public static <T> Function<T, Timed<T>> timestampWith(TimeUnit unit, Scheduler scheduler) {
        return new TimestampFunction(unit, scheduler);
    }

    public static <T, K> BiConsumer<Map<K, T>, T> toMapKeySelector(Function<? super T, ? extends K> keySelector) {
        return new ToMapKeySelector(keySelector);
    }

    public static <T, K, V> BiConsumer<Map<K, V>, T> toMapKeyValueSelector(Function<? super T, ? extends K> keySelector, Function<? super T, ? extends V> valueSelector) {
        return new ToMapKeyValueSelector(valueSelector, keySelector);
    }

    public static <T, K, V> BiConsumer<Map<K, Collection<V>>, T> toMultimapKeyValueSelector(Function<? super T, ? extends K> keySelector, Function<? super T, ? extends V> valueSelector, Function<? super K, ? extends Collection<? super V>> collectionFactory) {
        return new ToMultimapKeyValueSelector(collectionFactory, valueSelector, keySelector);
    }

    public static <T> Comparator<T> naturalComparator() {
        return NaturalComparator.INSTANCE;
    }

    public static <T> Function<List<T>, List<T>> listSorter(Comparator<? super T> comparator) {
        return new ListSorter(comparator);
    }
}
