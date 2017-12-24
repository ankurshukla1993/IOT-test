package io.reactivex.internal.operators.observable;

import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Function;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.ObserverFullArbiter;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.observers.FullArbiterObserver;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.observers.SerializedObserver;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservableTimeout<T, U, V> extends AbstractObservableWithUpstream<T, T> {
    final ObservableSource<U> firstTimeoutIndicator;
    final Function<? super T, ? extends ObservableSource<V>> itemTimeoutIndicator;
    final ObservableSource<? extends T> other;

    interface OnTimeout {
        void innerError(Throwable th);

        void timeout(long j);
    }

    static final class TimeoutInnerObserver<T, U, V> extends DisposableObserver<Object> {
        boolean done;
        final long index;
        final OnTimeout parent;

        TimeoutInnerObserver(OnTimeout parent, long index) {
            this.parent = parent;
            this.index = index;
        }

        public void onNext(Object t) {
            if (!this.done) {
                this.done = true;
                dispose();
                this.parent.timeout(this.index);
            }
        }

        public void onError(Throwable t) {
            if (this.done) {
                RxJavaPlugins.onError(t);
                return;
            }
            this.done = true;
            this.parent.innerError(t);
        }

        public void onComplete() {
            if (!this.done) {
                this.done = true;
                this.parent.timeout(this.index);
            }
        }
    }

    static final class TimeoutObserver<T, U, V> extends AtomicReference<Disposable> implements Observer<T>, Disposable, OnTimeout {
        private static final long serialVersionUID = 2672739326310051084L;
        final Observer<? super T> actual;
        final ObservableSource<U> firstTimeoutIndicator;
        volatile long index;
        final Function<? super T, ? extends ObservableSource<V>> itemTimeoutIndicator;
        Disposable f2739s;

        TimeoutObserver(Observer<? super T> actual, ObservableSource<U> firstTimeoutIndicator, Function<? super T, ? extends ObservableSource<V>> itemTimeoutIndicator) {
            this.actual = actual;
            this.firstTimeoutIndicator = firstTimeoutIndicator;
            this.itemTimeoutIndicator = itemTimeoutIndicator;
        }

        public void onSubscribe(Disposable s) {
            if (DisposableHelper.validate(this.f2739s, s)) {
                this.f2739s = s;
                Observer<? super T> a = this.actual;
                ObservableSource<U> p = this.firstTimeoutIndicator;
                if (p != null) {
                    TimeoutInnerObserver<T, U, V> tis = new TimeoutInnerObserver(this, 0);
                    if (compareAndSet(null, tis)) {
                        a.onSubscribe(this);
                        p.subscribe(tis);
                        return;
                    }
                    return;
                }
                a.onSubscribe(this);
            }
        }

        public void onNext(T t) {
            long idx = this.index + 1;
            this.index = idx;
            this.actual.onNext(t);
            Disposable d = (Disposable) get();
            if (d != null) {
                d.dispose();
            }
            try {
                ObservableSource<V> p = (ObservableSource) ObjectHelper.requireNonNull(this.itemTimeoutIndicator.apply(t), "The ObservableSource returned is null");
                TimeoutInnerObserver<T, U, V> tis = new TimeoutInnerObserver(this, idx);
                if (compareAndSet(d, tis)) {
                    p.subscribe(tis);
                }
            } catch (Throwable e) {
                Exceptions.throwIfFatal(e);
                dispose();
                this.actual.onError(e);
            }
        }

        public void onError(Throwable t) {
            DisposableHelper.dispose(this);
            this.actual.onError(t);
        }

        public void onComplete() {
            DisposableHelper.dispose(this);
            this.actual.onComplete();
        }

        public void dispose() {
            if (DisposableHelper.dispose(this)) {
                this.f2739s.dispose();
            }
        }

        public boolean isDisposed() {
            return this.f2739s.isDisposed();
        }

        public void timeout(long idx) {
            if (idx == this.index) {
                dispose();
                this.actual.onError(new TimeoutException());
            }
        }

        public void innerError(Throwable e) {
            this.f2739s.dispose();
            this.actual.onError(e);
        }
    }

    static final class TimeoutOtherObserver<T, U, V> extends AtomicReference<Disposable> implements Observer<T>, Disposable, OnTimeout {
        private static final long serialVersionUID = -1957813281749686898L;
        final Observer<? super T> actual;
        final ObserverFullArbiter<T> arbiter;
        boolean done;
        final ObservableSource<U> firstTimeoutIndicator;
        volatile long index;
        final Function<? super T, ? extends ObservableSource<V>> itemTimeoutIndicator;
        final ObservableSource<? extends T> other;
        Disposable f2740s;

        TimeoutOtherObserver(Observer<? super T> actual, ObservableSource<U> firstTimeoutIndicator, Function<? super T, ? extends ObservableSource<V>> itemTimeoutIndicator, ObservableSource<? extends T> other) {
            this.actual = actual;
            this.firstTimeoutIndicator = firstTimeoutIndicator;
            this.itemTimeoutIndicator = itemTimeoutIndicator;
            this.other = other;
            this.arbiter = new ObserverFullArbiter(actual, this, 8);
        }

        public void onSubscribe(Disposable s) {
            if (DisposableHelper.validate(this.f2740s, s)) {
                this.f2740s = s;
                this.arbiter.setDisposable(s);
                Observer<? super T> a = this.actual;
                ObservableSource<U> p = this.firstTimeoutIndicator;
                if (p != null) {
                    TimeoutInnerObserver<T, U, V> tis = new TimeoutInnerObserver(this, 0);
                    if (compareAndSet(null, tis)) {
                        a.onSubscribe(this.arbiter);
                        p.subscribe(tis);
                        return;
                    }
                    return;
                }
                a.onSubscribe(this.arbiter);
            }
        }

        public void onNext(T t) {
            if (!this.done) {
                long idx = this.index + 1;
                this.index = idx;
                if (this.arbiter.onNext(t, this.f2740s)) {
                    Disposable d = (Disposable) get();
                    if (d != null) {
                        d.dispose();
                    }
                    try {
                        ObservableSource<V> p = (ObservableSource) ObjectHelper.requireNonNull(this.itemTimeoutIndicator.apply(t), "The ObservableSource returned is null");
                        TimeoutInnerObserver<T, U, V> tis = new TimeoutInnerObserver(this, idx);
                        if (compareAndSet(d, tis)) {
                            p.subscribe(tis);
                        }
                    } catch (Throwable e) {
                        Exceptions.throwIfFatal(e);
                        this.actual.onError(e);
                    }
                }
            }
        }

        public void onError(Throwable t) {
            if (this.done) {
                RxJavaPlugins.onError(t);
                return;
            }
            this.done = true;
            dispose();
            this.arbiter.onError(t, this.f2740s);
        }

        public void onComplete() {
            if (!this.done) {
                this.done = true;
                dispose();
                this.arbiter.onComplete(this.f2740s);
            }
        }

        public void dispose() {
            if (DisposableHelper.dispose(this)) {
                this.f2740s.dispose();
            }
        }

        public boolean isDisposed() {
            return this.f2740s.isDisposed();
        }

        public void timeout(long idx) {
            if (idx == this.index) {
                dispose();
                this.other.subscribe(new FullArbiterObserver(this.arbiter));
            }
        }

        public void innerError(Throwable e) {
            this.f2740s.dispose();
            this.actual.onError(e);
        }
    }

    public ObservableTimeout(ObservableSource<T> source, ObservableSource<U> firstTimeoutIndicator, Function<? super T, ? extends ObservableSource<V>> itemTimeoutIndicator, ObservableSource<? extends T> other) {
        super(source);
        this.firstTimeoutIndicator = firstTimeoutIndicator;
        this.itemTimeoutIndicator = itemTimeoutIndicator;
        this.other = other;
    }

    public void subscribeActual(Observer<? super T> t) {
        if (this.other == null) {
            this.source.subscribe(new TimeoutObserver(new SerializedObserver(t), this.firstTimeoutIndicator, this.itemTimeoutIndicator));
        } else {
            this.source.subscribe(new TimeoutOtherObserver(t, this.firstTimeoutIndicator, this.itemTimeoutIndicator, this.other));
        }
    }
}
