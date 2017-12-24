package io.reactivex.internal.operators.observable;

import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.observers.SerializedObserver;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservableSampleWithObservable<T> extends AbstractObservableWithUpstream<T, T> {
    final boolean emitLast;
    final ObservableSource<?> other;

    static abstract class SampleMainObserver<T> extends AtomicReference<T> implements Observer<T>, Disposable {
        private static final long serialVersionUID = -3517602651313910099L;
        final Observer<? super T> actual;
        final AtomicReference<Disposable> other = new AtomicReference();
        Disposable f2717s;
        final ObservableSource<?> sampler;

        abstract void completeMain();

        abstract void completeOther();

        abstract void run();

        SampleMainObserver(Observer<? super T> actual, ObservableSource<?> other) {
            this.actual = actual;
            this.sampler = other;
        }

        public void onSubscribe(Disposable s) {
            if (DisposableHelper.validate(this.f2717s, s)) {
                this.f2717s = s;
                this.actual.onSubscribe(this);
                if (this.other.get() == null) {
                    this.sampler.subscribe(new SamplerObserver(this));
                }
            }
        }

        public void onNext(T t) {
            lazySet(t);
        }

        public void onError(Throwable t) {
            DisposableHelper.dispose(this.other);
            this.actual.onError(t);
        }

        public void onComplete() {
            DisposableHelper.dispose(this.other);
            completeMain();
        }

        boolean setOther(Disposable o) {
            return DisposableHelper.setOnce(this.other, o);
        }

        public void dispose() {
            DisposableHelper.dispose(this.other);
            this.f2717s.dispose();
        }

        public boolean isDisposed() {
            return this.other.get() == DisposableHelper.DISPOSED;
        }

        public void error(Throwable e) {
            this.f2717s.dispose();
            this.actual.onError(e);
        }

        public void complete() {
            this.f2717s.dispose();
            completeOther();
        }

        void emit() {
            T value = getAndSet(null);
            if (value != null) {
                this.actual.onNext(value);
            }
        }
    }

    static final class SampleMainEmitLast<T> extends SampleMainObserver<T> {
        private static final long serialVersionUID = -3029755663834015785L;
        volatile boolean done;
        final AtomicInteger wip = new AtomicInteger();

        SampleMainEmitLast(Observer<? super T> actual, ObservableSource<?> other) {
            super(actual, other);
        }

        void completeMain() {
            this.done = true;
            if (this.wip.getAndIncrement() == 0) {
                emit();
                this.actual.onComplete();
            }
        }

        void completeOther() {
            this.done = true;
            if (this.wip.getAndIncrement() == 0) {
                emit();
                this.actual.onComplete();
            }
        }

        void run() {
            if (this.wip.getAndIncrement() == 0) {
                do {
                    boolean d = this.done;
                    emit();
                    if (d) {
                        this.actual.onComplete();
                        return;
                    }
                } while (this.wip.decrementAndGet() != 0);
            }
        }
    }

    static final class SampleMainNoLast<T> extends SampleMainObserver<T> {
        private static final long serialVersionUID = -3029755663834015785L;

        SampleMainNoLast(Observer<? super T> actual, ObservableSource<?> other) {
            super(actual, other);
        }

        void completeMain() {
            this.actual.onComplete();
        }

        void completeOther() {
            this.actual.onComplete();
        }

        void run() {
            emit();
        }
    }

    static final class SamplerObserver<T> implements Observer<Object> {
        final SampleMainObserver<T> parent;

        SamplerObserver(SampleMainObserver<T> parent) {
            this.parent = parent;
        }

        public void onSubscribe(Disposable s) {
            this.parent.setOther(s);
        }

        public void onNext(Object t) {
            this.parent.run();
        }

        public void onError(Throwable t) {
            this.parent.error(t);
        }

        public void onComplete() {
            this.parent.complete();
        }
    }

    public ObservableSampleWithObservable(ObservableSource<T> source, ObservableSource<?> other, boolean emitLast) {
        super(source);
        this.other = other;
        this.emitLast = emitLast;
    }

    public void subscribeActual(Observer<? super T> t) {
        SerializedObserver<T> serial = new SerializedObserver(t);
        if (this.emitLast) {
            this.source.subscribe(new SampleMainEmitLast(serial, this.other));
        } else {
            this.source.subscribe(new SampleMainNoLast(serial, this.other));
        }
    }
}
