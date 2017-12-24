package io.reactivex.internal.operators.observable;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Function;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.internal.util.HalfSerializer;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservableRepeatWhen<T> extends AbstractObservableWithUpstream<T, T> {
    final Function<? super Observable<Object>, ? extends ObservableSource<?>> handler;

    static final class RepeatWhenObserver<T> extends AtomicInteger implements Observer<T>, Disposable {
        private static final long serialVersionUID = 802743776666017014L;
        volatile boolean active;
        final Observer<? super T> actual;
        final AtomicReference<Disposable> f2714d = new AtomicReference();
        final AtomicThrowable error = new AtomicThrowable();
        final InnerRepeatObserver inner = new InnerRepeatObserver();
        final Subject<Object> signaller;
        final ObservableSource<T> source;
        final AtomicInteger wip = new AtomicInteger();

        final class InnerRepeatObserver extends AtomicReference<Disposable> implements Observer<Object> {
            private static final long serialVersionUID = 3254781284376480842L;

            InnerRepeatObserver() {
            }

            public void onSubscribe(Disposable d) {
                DisposableHelper.setOnce(this, d);
            }

            public void onNext(Object t) {
                RepeatWhenObserver.this.innerNext();
            }

            public void onError(Throwable e) {
                RepeatWhenObserver.this.innerError(e);
            }

            public void onComplete() {
                RepeatWhenObserver.this.innerComplete();
            }
        }

        RepeatWhenObserver(Observer<? super T> actual, Subject<Object> signaller, ObservableSource<T> source) {
            this.actual = actual;
            this.signaller = signaller;
            this.source = source;
        }

        public void onSubscribe(Disposable d) {
            DisposableHelper.replace(this.f2714d, d);
        }

        public void onNext(T t) {
            HalfSerializer.onNext(this.actual, (Object) t, (AtomicInteger) this, this.error);
        }

        public void onError(Throwable e) {
            DisposableHelper.dispose(this.inner);
            HalfSerializer.onError(this.actual, e, (AtomicInteger) this, this.error);
        }

        public void onComplete() {
            this.active = false;
            this.signaller.onNext(Integer.valueOf(0));
        }

        public boolean isDisposed() {
            return DisposableHelper.isDisposed((Disposable) this.f2714d.get());
        }

        public void dispose() {
            DisposableHelper.dispose(this.f2714d);
            DisposableHelper.dispose(this.inner);
        }

        void innerNext() {
            subscribeNext();
        }

        void innerError(Throwable ex) {
            DisposableHelper.dispose(this.f2714d);
            HalfSerializer.onError(this.actual, ex, (AtomicInteger) this, this.error);
        }

        void innerComplete() {
            DisposableHelper.dispose(this.f2714d);
            HalfSerializer.onComplete(this.actual, (AtomicInteger) this, this.error);
        }

        void subscribeNext() {
            if (this.wip.getAndIncrement() == 0) {
                while (!isDisposed()) {
                    if (!this.active) {
                        this.active = true;
                        this.source.subscribe(this);
                    }
                    if (this.wip.decrementAndGet() == 0) {
                        return;
                    }
                }
            }
        }
    }

    public ObservableRepeatWhen(ObservableSource<T> source, Function<? super Observable<Object>, ? extends ObservableSource<?>> handler) {
        super(source);
        this.handler = handler;
    }

    protected void subscribeActual(Observer<? super T> observer) {
        Subject<Object> signaller = PublishSubject.create().toSerialized();
        try {
            ObservableSource<?> other = (ObservableSource) ObjectHelper.requireNonNull(this.handler.apply(signaller), "The handler returned a null ObservableSource");
            RepeatWhenObserver<T> parent = new RepeatWhenObserver(observer, signaller, this.source);
            observer.onSubscribe(parent);
            other.subscribe(parent.inner);
            parent.subscribeNext();
        } catch (Throwable ex) {
            Exceptions.throwIfFatal(ex);
            EmptyDisposable.error(ex, (Observer) observer);
        }
    }
}
