package io.reactivex.internal.operators.observable;

import io.reactivex.Emitter;
import io.reactivex.Notification;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.SingleSource;
import io.reactivex.functions.Action;
import io.reactivex.functions.BiConsumer;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.internal.functions.Functions;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.operators.single.SingleToObservable;
import io.reactivex.observables.ConnectableObservable;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public final class ObservableInternalHelper {

    static final class BufferedReplayCallable<T> implements Callable<ConnectableObservable<T>> {
        private final int bufferSize;
        private final Observable<T> parent;

        BufferedReplayCallable(Observable<T> parent, int bufferSize) {
            this.parent = parent;
            this.bufferSize = bufferSize;
        }

        public ConnectableObservable<T> call() {
            return this.parent.replay(this.bufferSize);
        }
    }

    static final class BufferedTimedReplayCallable<T> implements Callable<ConnectableObservable<T>> {
        private final int bufferSize;
        private final Observable<T> parent;
        private final Scheduler scheduler;
        private final long time;
        private final TimeUnit unit;

        BufferedTimedReplayCallable(Observable<T> parent, int bufferSize, long time, TimeUnit unit, Scheduler scheduler) {
            this.parent = parent;
            this.bufferSize = bufferSize;
            this.time = time;
            this.unit = unit;
            this.scheduler = scheduler;
        }

        public ConnectableObservable<T> call() {
            return this.parent.replay(this.bufferSize, this.time, this.unit, this.scheduler);
        }
    }

    enum ErrorMapperFilter implements Function<Notification<Object>, Throwable>, Predicate<Notification<Object>> {
        INSTANCE;

        public Throwable apply(Notification<Object> t) throws Exception {
            return t.getError();
        }

        public boolean test(Notification<Object> t) throws Exception {
            return t.isOnError();
        }
    }

    static final class FlatMapIntoIterable<T, U> implements Function<T, ObservableSource<U>> {
        private final Function<? super T, ? extends Iterable<? extends U>> mapper;

        FlatMapIntoIterable(Function<? super T, ? extends Iterable<? extends U>> mapper) {
            this.mapper = mapper;
        }

        public ObservableSource<U> apply(T t) throws Exception {
            return new ObservableFromIterable((Iterable) ObjectHelper.requireNonNull(this.mapper.apply(t), "The mapper returned a null Iterable"));
        }
    }

    static final class FlatMapWithCombinerInner<U, R, T> implements Function<U, R> {
        private final BiFunction<? super T, ? super U, ? extends R> combiner;
        private final T f2703t;

        FlatMapWithCombinerInner(BiFunction<? super T, ? super U, ? extends R> combiner, T t) {
            this.combiner = combiner;
            this.f2703t = t;
        }

        public R apply(U w) throws Exception {
            return this.combiner.apply(this.f2703t, w);
        }
    }

    static final class FlatMapWithCombinerOuter<T, R, U> implements Function<T, ObservableSource<R>> {
        private final BiFunction<? super T, ? super U, ? extends R> combiner;
        private final Function<? super T, ? extends ObservableSource<? extends U>> mapper;

        FlatMapWithCombinerOuter(BiFunction<? super T, ? super U, ? extends R> combiner, Function<? super T, ? extends ObservableSource<? extends U>> mapper) {
            this.combiner = combiner;
            this.mapper = mapper;
        }

        public ObservableSource<R> apply(T t) throws Exception {
            return new ObservableMap((ObservableSource) ObjectHelper.requireNonNull(this.mapper.apply(t), "The mapper returned a null ObservableSource"), new FlatMapWithCombinerInner(this.combiner, t));
        }
    }

    static final class ItemDelayFunction<T, U> implements Function<T, ObservableSource<T>> {
        final Function<? super T, ? extends ObservableSource<U>> itemDelay;

        ItemDelayFunction(Function<? super T, ? extends ObservableSource<U>> itemDelay) {
            this.itemDelay = itemDelay;
        }

        public ObservableSource<T> apply(T v) throws Exception {
            return new ObservableTake((ObservableSource) ObjectHelper.requireNonNull(this.itemDelay.apply(v), "The itemDelay returned a null ObservableSource"), 1).map(Functions.justFunction(v)).defaultIfEmpty(v);
        }
    }

    enum MapToInt implements Function<Object, Object> {
        INSTANCE;

        public Object apply(Object t) throws Exception {
            return Integer.valueOf(0);
        }
    }

    static final class ObservableMapper<T, R> implements Function<T, Observable<R>> {
        final Function<? super T, ? extends SingleSource<? extends R>> mapper;

        ObservableMapper(Function<? super T, ? extends SingleSource<? extends R>> mapper) {
            this.mapper = mapper;
        }

        public Observable<R> apply(T t) throws Exception {
            return RxJavaPlugins.onAssembly(new SingleToObservable((SingleSource) ObjectHelper.requireNonNull(this.mapper.apply(t), "The mapper returned a null SingleSource")));
        }
    }

    static final class ObserverOnComplete<T> implements Action {
        final Observer<T> observer;

        ObserverOnComplete(Observer<T> observer) {
            this.observer = observer;
        }

        public void run() throws Exception {
            this.observer.onComplete();
        }
    }

    static final class ObserverOnError<T> implements Consumer<Throwable> {
        final Observer<T> observer;

        ObserverOnError(Observer<T> observer) {
            this.observer = observer;
        }

        public void accept(Throwable v) throws Exception {
            this.observer.onError(v);
        }
    }

    static final class ObserverOnNext<T> implements Consumer<T> {
        final Observer<T> observer;

        ObserverOnNext(Observer<T> observer) {
            this.observer = observer;
        }

        public void accept(T v) throws Exception {
            this.observer.onNext(v);
        }
    }

    static final class RepeatWhenOuterHandler implements Function<Observable<Notification<Object>>, ObservableSource<?>> {
        private final Function<? super Observable<Object>, ? extends ObservableSource<?>> handler;

        RepeatWhenOuterHandler(Function<? super Observable<Object>, ? extends ObservableSource<?>> handler) {
            this.handler = handler;
        }

        public ObservableSource<?> apply(Observable<Notification<Object>> no) throws Exception {
            return (ObservableSource) this.handler.apply(no.map(MapToInt.INSTANCE));
        }
    }

    static final class ReplayCallable<T> implements Callable<ConnectableObservable<T>> {
        private final Observable<T> parent;

        ReplayCallable(Observable<T> parent) {
            this.parent = parent;
        }

        public ConnectableObservable<T> call() {
            return this.parent.replay();
        }
    }

    static final class ReplayFunction<T, R> implements Function<Observable<T>, ObservableSource<R>> {
        private final Scheduler scheduler;
        private final Function<? super Observable<T>, ? extends ObservableSource<R>> selector;

        ReplayFunction(Function<? super Observable<T>, ? extends ObservableSource<R>> selector, Scheduler scheduler) {
            this.selector = selector;
            this.scheduler = scheduler;
        }

        public ObservableSource<R> apply(Observable<T> t) throws Exception {
            return Observable.wrap((ObservableSource) ObjectHelper.requireNonNull(this.selector.apply(t), "The selector returned a null ObservableSource")).observeOn(this.scheduler);
        }
    }

    static final class RetryWhenInner implements Function<Observable<Notification<Object>>, ObservableSource<?>> {
        private final Function<? super Observable<Throwable>, ? extends ObservableSource<?>> handler;

        RetryWhenInner(Function<? super Observable<Throwable>, ? extends ObservableSource<?>> handler) {
            this.handler = handler;
        }

        public ObservableSource<?> apply(Observable<Notification<Object>> no) throws Exception {
            return (ObservableSource) this.handler.apply(no.takeWhile(ErrorMapperFilter.INSTANCE).map(ErrorMapperFilter.INSTANCE));
        }
    }

    static final class SimpleBiGenerator<T, S> implements BiFunction<S, Emitter<T>, S> {
        final BiConsumer<S, Emitter<T>> consumer;

        SimpleBiGenerator(BiConsumer<S, Emitter<T>> consumer) {
            this.consumer = consumer;
        }

        public S apply(S t1, Emitter<T> t2) throws Exception {
            this.consumer.accept(t1, t2);
            return t1;
        }
    }

    static final class SimpleGenerator<T, S> implements BiFunction<S, Emitter<T>, S> {
        final Consumer<Emitter<T>> consumer;

        SimpleGenerator(Consumer<Emitter<T>> consumer) {
            this.consumer = consumer;
        }

        public S apply(S t1, Emitter<T> t2) throws Exception {
            this.consumer.accept(t2);
            return t1;
        }
    }

    static final class TimedReplayCallable<T> implements Callable<ConnectableObservable<T>> {
        private final Observable<T> parent;
        private final Scheduler scheduler;
        private final long time;
        private final TimeUnit unit;

        TimedReplayCallable(Observable<T> parent, long time, TimeUnit unit, Scheduler scheduler) {
            this.parent = parent;
            this.time = time;
            this.unit = unit;
            this.scheduler = scheduler;
        }

        public ConnectableObservable<T> call() {
            return this.parent.replay(this.time, this.unit, this.scheduler);
        }
    }

    static final class ZipIterableFunction<T, R> implements Function<List<ObservableSource<? extends T>>, ObservableSource<? extends R>> {
        private final Function<? super Object[], ? extends R> zipper;

        ZipIterableFunction(Function<? super Object[], ? extends R> zipper) {
            this.zipper = zipper;
        }

        public ObservableSource<? extends R> apply(List<ObservableSource<? extends T>> list) {
            return Observable.zipIterable(list, this.zipper, false, Observable.bufferSize());
        }
    }

    private ObservableInternalHelper() {
        throw new IllegalStateException("No instances!");
    }

    public static <T, S> BiFunction<S, Emitter<T>, S> simpleGenerator(Consumer<Emitter<T>> consumer) {
        return new SimpleGenerator(consumer);
    }

    public static <T, S> BiFunction<S, Emitter<T>, S> simpleBiGenerator(BiConsumer<S, Emitter<T>> consumer) {
        return new SimpleBiGenerator(consumer);
    }

    public static <T, U> Function<T, ObservableSource<T>> itemDelay(Function<? super T, ? extends ObservableSource<U>> itemDelay) {
        return new ItemDelayFunction(itemDelay);
    }

    public static <T> Consumer<T> observerOnNext(Observer<T> observer) {
        return new ObserverOnNext(observer);
    }

    public static <T> Consumer<Throwable> observerOnError(Observer<T> observer) {
        return new ObserverOnError(observer);
    }

    public static <T> Action observerOnComplete(Observer<T> observer) {
        return new ObserverOnComplete(observer);
    }

    public static <T, U, R> Function<T, ObservableSource<R>> flatMapWithCombiner(Function<? super T, ? extends ObservableSource<? extends U>> mapper, BiFunction<? super T, ? super U, ? extends R> combiner) {
        return new FlatMapWithCombinerOuter(combiner, mapper);
    }

    public static <T, U> Function<T, ObservableSource<U>> flatMapIntoIterable(Function<? super T, ? extends Iterable<? extends U>> mapper) {
        return new FlatMapIntoIterable(mapper);
    }

    public static Function<Observable<Notification<Object>>, ObservableSource<?>> repeatWhenHandler(Function<? super Observable<Object>, ? extends ObservableSource<?>> handler) {
        return new RepeatWhenOuterHandler(handler);
    }

    public static <T> Callable<ConnectableObservable<T>> replayCallable(Observable<T> parent) {
        return new ReplayCallable(parent);
    }

    public static <T> Callable<ConnectableObservable<T>> replayCallable(Observable<T> parent, int bufferSize) {
        return new BufferedReplayCallable(parent, bufferSize);
    }

    public static <T> Callable<ConnectableObservable<T>> replayCallable(Observable<T> parent, int bufferSize, long time, TimeUnit unit, Scheduler scheduler) {
        return new BufferedTimedReplayCallable(parent, bufferSize, time, unit, scheduler);
    }

    public static <T> Callable<ConnectableObservable<T>> replayCallable(Observable<T> parent, long time, TimeUnit unit, Scheduler scheduler) {
        return new TimedReplayCallable(parent, time, unit, scheduler);
    }

    public static <T, R> Function<Observable<T>, ObservableSource<R>> replayFunction(Function<? super Observable<T>, ? extends ObservableSource<R>> selector, Scheduler scheduler) {
        return new ReplayFunction(selector, scheduler);
    }

    public static <T> Function<Observable<Notification<Object>>, ObservableSource<?>> retryWhenHandler(Function<? super Observable<Throwable>, ? extends ObservableSource<?>> handler) {
        return new RetryWhenInner(handler);
    }

    public static <T, R> Function<List<ObservableSource<? extends T>>, ObservableSource<? extends R>> zipIterable(Function<? super Object[], ? extends R> zipper) {
        return new ZipIterableFunction(zipper);
    }

    public static <T, R> Observable<R> switchMapSingle(Observable<T> source, Function<? super T, ? extends SingleSource<? extends R>> mapper) {
        return source.switchMap(convertSingleMapperToObservableMapper(mapper), 1);
    }

    public static <T, R> Observable<R> switchMapSingleDelayError(Observable<T> source, Function<? super T, ? extends SingleSource<? extends R>> mapper) {
        return source.switchMapDelayError(convertSingleMapperToObservableMapper(mapper), 1);
    }

    private static <T, R> Function<T, Observable<R>> convertSingleMapperToObservableMapper(Function<? super T, ? extends SingleSource<? extends R>> mapper) {
        ObjectHelper.requireNonNull(mapper, "mapper is null");
        return new ObservableMapper(mapper);
    }
}
