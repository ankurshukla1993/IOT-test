package io.reactivex.internal.operators.observable;

import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Function;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.internal.util.HalfSerializer;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicReferenceArray;

public final class ObservableWithLatestFromMany<T, R> extends AbstractObservableWithUpstream<T, R> {
    @NonNull
    final Function<? super Object[], R> combiner;
    @Nullable
    final ObservableSource<?>[] otherArray;
    @Nullable
    final Iterable<? extends ObservableSource<?>> otherIterable;

    final class SingletonArrayFunc implements Function<T, R> {
        SingletonArrayFunc() {
        }

        public R apply(T t) throws Exception {
            return ObjectHelper.requireNonNull(ObservableWithLatestFromMany.this.combiner.apply(new Object[]{t}), "The combiner returned a null value");
        }
    }

    static final class WithLatestFromObserver<T, R> extends AtomicInteger implements Observer<T>, Disposable {
        private static final long serialVersionUID = 1577321883966341961L;
        final Observer<? super R> actual;
        final Function<? super Object[], R> combiner;
        final AtomicReference<Disposable> f2760d;
        volatile boolean done;
        final AtomicThrowable error;
        final WithLatestInnerObserver[] observers;
        final AtomicReferenceArray<Object> values;

        WithLatestFromObserver(Observer<? super R> actual, Function<? super Object[], R> combiner, int n) {
            this.actual = actual;
            this.combiner = combiner;
            WithLatestInnerObserver[] s = new WithLatestInnerObserver[n];
            for (int i = 0; i < n; i++) {
                s[i] = new WithLatestInnerObserver(this, i);
            }
            this.observers = s;
            this.values = new AtomicReferenceArray(n);
            this.f2760d = new AtomicReference();
            this.error = new AtomicThrowable();
        }

        void subscribe(ObservableSource<?>[] others, int n) {
            WithLatestInnerObserver[] observers = this.observers;
            AtomicReference<Disposable> s = this.f2760d;
            for (int i = 0; i < n && !DisposableHelper.isDisposed((Disposable) s.get()) && !this.done; i++) {
                others[i].subscribe(observers[i]);
            }
        }

        public void onSubscribe(Disposable d) {
            DisposableHelper.setOnce(this.f2760d, d);
        }

        public void onNext(T t) {
            if (!this.done) {
                AtomicReferenceArray<Object> ara = this.values;
                int n = ara.length();
                Object[] objects = new Object[(n + 1)];
                objects[0] = t;
                int i = 0;
                while (i < n) {
                    Object o = ara.get(i);
                    if (o != null) {
                        objects[i + 1] = o;
                        i++;
                    } else {
                        return;
                    }
                }
                try {
                    HalfSerializer.onNext(this.actual, ObjectHelper.requireNonNull(this.combiner.apply(objects), "combiner returned a null value"), (AtomicInteger) this, this.error);
                } catch (Throwable ex) {
                    Exceptions.throwIfFatal(ex);
                    dispose();
                    onError(ex);
                }
            }
        }

        public void onError(Throwable t) {
            if (this.done) {
                RxJavaPlugins.onError(t);
                return;
            }
            this.done = true;
            cancelAllBut(-1);
            HalfSerializer.onError(this.actual, t, (AtomicInteger) this, this.error);
        }

        public void onComplete() {
            if (!this.done) {
                this.done = true;
                cancelAllBut(-1);
                HalfSerializer.onComplete(this.actual, (AtomicInteger) this, this.error);
            }
        }

        public boolean isDisposed() {
            return DisposableHelper.isDisposed((Disposable) this.f2760d.get());
        }

        public void dispose() {
            DisposableHelper.dispose(this.f2760d);
            for (WithLatestInnerObserver s : this.observers) {
                s.dispose();
            }
        }

        void innerNext(int index, Object o) {
            this.values.set(index, o);
        }

        void innerError(int index, Throwable t) {
            this.done = true;
            DisposableHelper.dispose(this.f2760d);
            cancelAllBut(index);
            HalfSerializer.onError(this.actual, t, (AtomicInteger) this, this.error);
        }

        void innerComplete(int index, boolean nonEmpty) {
            if (!nonEmpty) {
                this.done = true;
                cancelAllBut(index);
                HalfSerializer.onComplete(this.actual, (AtomicInteger) this, this.error);
            }
        }

        void cancelAllBut(int index) {
            WithLatestInnerObserver[] observers = this.observers;
            for (int i = 0; i < observers.length; i++) {
                if (i != index) {
                    observers[i].dispose();
                }
            }
        }
    }

    static final class WithLatestInnerObserver extends AtomicReference<Disposable> implements Observer<Object> {
        private static final long serialVersionUID = 3256684027868224024L;
        boolean hasValue;
        final int index;
        final WithLatestFromObserver<?, ?> parent;

        WithLatestInnerObserver(WithLatestFromObserver<?, ?> parent, int index) {
            this.parent = parent;
            this.index = index;
        }

        public void onSubscribe(Disposable d) {
            DisposableHelper.setOnce(this, d);
        }

        public void onNext(Object t) {
            if (!this.hasValue) {
                this.hasValue = true;
            }
            this.parent.innerNext(this.index, t);
        }

        public void onError(Throwable t) {
            this.parent.innerError(this.index, t);
        }

        public void onComplete() {
            this.parent.innerComplete(this.index, this.hasValue);
        }

        public void dispose() {
            DisposableHelper.dispose(this);
        }
    }

    public ObservableWithLatestFromMany(@NonNull ObservableSource<T> source, @NonNull ObservableSource<?>[] otherArray, @NonNull Function<? super Object[], R> combiner) {
        super(source);
        this.otherArray = otherArray;
        this.otherIterable = null;
        this.combiner = combiner;
    }

    public ObservableWithLatestFromMany(@NonNull ObservableSource<T> source, @NonNull Iterable<? extends ObservableSource<?>> otherIterable, @NonNull Function<? super Object[], R> combiner) {
        super(source);
        this.otherArray = null;
        this.otherIterable = otherIterable;
        this.combiner = combiner;
    }

    protected void subscribeActual(Observer<? super R> s) {
        int n;
        Throwable ex;
        ObservableSource<?>[] others = this.otherArray;
        if (others == null) {
            others = new ObservableSource[8];
            try {
                int n2 = 0;
                for (ObservableSource<?> p : this.otherIterable) {
                    try {
                        if (n2 == others.length) {
                            others = (ObservableSource[]) Arrays.copyOf(others, (n2 >> 1) + n2);
                        }
                        n = n2 + 1;
                        others[n2] = p;
                        n2 = n;
                    } catch (Throwable th) {
                        ex = th;
                        n = n2;
                    }
                }
                n = n2;
            } catch (Throwable th2) {
                ex = th2;
            }
        } else {
            n = others.length;
        }
        if (n == 0) {
            new ObservableMap(this.source, new SingletonArrayFunc()).subscribeActual(s);
            return;
        }
        WithLatestFromObserver<T, R> parent = new WithLatestFromObserver(s, this.combiner, n);
        s.onSubscribe(parent);
        parent.subscribe(others, n);
        this.source.subscribe(parent);
        return;
        Exceptions.throwIfFatal(ex);
        EmptyDisposable.error(ex, (Observer) s);
    }
}
