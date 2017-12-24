package io.reactivex.internal.operators.observable;

import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Function;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.observers.SerializedObserver;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservableDebounce<T, U> extends AbstractObservableWithUpstream<T, T> {
    final Function<? super T, ? extends ObservableSource<U>> debounceSelector;

    static final class DebounceObserver<T, U> implements Observer<T>, Disposable {
        final Observer<? super T> actual;
        final Function<? super T, ? extends ObservableSource<U>> debounceSelector;
        final AtomicReference<Disposable> debouncer = new AtomicReference();
        boolean done;
        volatile long index;
        Disposable f2680s;

        static final class DebounceInnerObserver<T, U> extends DisposableObserver<U> {
            boolean done;
            final long index;
            final AtomicBoolean once = new AtomicBoolean();
            final DebounceObserver<T, U> parent;
            final T value;

            DebounceInnerObserver(DebounceObserver<T, U> parent, long index, T value) {
                this.parent = parent;
                this.index = index;
                this.value = value;
            }

            public void onNext(U u) {
                if (!this.done) {
                    this.done = true;
                    dispose();
                    emit();
                }
            }

            void emit() {
                if (this.once.compareAndSet(false, true)) {
                    this.parent.emit(this.index, this.value);
                }
            }

            public void onError(Throwable t) {
                if (this.done) {
                    RxJavaPlugins.onError(t);
                    return;
                }
                this.done = true;
                this.parent.onError(t);
            }

            public void onComplete() {
                if (!this.done) {
                    this.done = true;
                    emit();
                }
            }
        }

        DebounceObserver(Observer<? super T> actual, Function<? super T, ? extends ObservableSource<U>> debounceSelector) {
            this.actual = actual;
            this.debounceSelector = debounceSelector;
        }

        public void onSubscribe(Disposable s) {
            if (DisposableHelper.validate(this.f2680s, s)) {
                this.f2680s = s;
                this.actual.onSubscribe(this);
            }
        }

        public void onNext(T t) {
            if (!this.done) {
                long idx = this.index + 1;
                this.index = idx;
                Disposable d = (Disposable) this.debouncer.get();
                if (d != null) {
                    d.dispose();
                }
                try {
                    ObservableSource<U> p = (ObservableSource) ObjectHelper.requireNonNull(this.debounceSelector.apply(t), "The ObservableSource supplied is null");
                    DebounceInnerObserver<T, U> dis = new DebounceInnerObserver(this, idx, t);
                    if (this.debouncer.compareAndSet(d, dis)) {
                        p.subscribe(dis);
                    }
                } catch (Throwable e) {
                    Exceptions.throwIfFatal(e);
                    dispose();
                    this.actual.onError(e);
                }
            }
        }

        public void onError(Throwable t) {
            DisposableHelper.dispose(this.debouncer);
            this.actual.onError(t);
        }

        public void onComplete() {
            if (!this.done) {
                this.done = true;
                Disposable d = (Disposable) this.debouncer.get();
                if (d != DisposableHelper.DISPOSED) {
                    ((DebounceInnerObserver) d).emit();
                    DisposableHelper.dispose(this.debouncer);
                    this.actual.onComplete();
                }
            }
        }

        public void dispose() {
            this.f2680s.dispose();
            DisposableHelper.dispose(this.debouncer);
        }

        public boolean isDisposed() {
            return this.f2680s.isDisposed();
        }

        void emit(long idx, T value) {
            if (idx == this.index) {
                this.actual.onNext(value);
            }
        }
    }

    public ObservableDebounce(ObservableSource<T> source, Function<? super T, ? extends ObservableSource<U>> debounceSelector) {
        super(source);
        this.debounceSelector = debounceSelector;
    }

    public void subscribeActual(Observer<? super T> t) {
        this.source.subscribe(new DebounceObserver(new SerializedObserver(t), this.debounceSelector));
    }
}
