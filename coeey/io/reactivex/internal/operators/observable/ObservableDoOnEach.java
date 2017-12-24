package io.reactivex.internal.operators.observable;

import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.plugins.RxJavaPlugins;

public final class ObservableDoOnEach<T> extends AbstractObservableWithUpstream<T, T> {
    final Action onAfterTerminate;
    final Action onComplete;
    final Consumer<? super Throwable> onError;
    final Consumer<? super T> onNext;

    static final class DoOnEachObserver<T> implements Observer<T>, Disposable {
        final Observer<? super T> actual;
        boolean done;
        final Action onAfterTerminate;
        final Action onComplete;
        final Consumer<? super Throwable> onError;
        final Consumer<? super T> onNext;
        Disposable f2688s;

        DoOnEachObserver(Observer<? super T> actual, Consumer<? super T> onNext, Consumer<? super Throwable> onError, Action onComplete, Action onAfterTerminate) {
            this.actual = actual;
            this.onNext = onNext;
            this.onError = onError;
            this.onComplete = onComplete;
            this.onAfterTerminate = onAfterTerminate;
        }

        public void onSubscribe(Disposable s) {
            if (DisposableHelper.validate(this.f2688s, s)) {
                this.f2688s = s;
                this.actual.onSubscribe(this);
            }
        }

        public void dispose() {
            this.f2688s.dispose();
        }

        public boolean isDisposed() {
            return this.f2688s.isDisposed();
        }

        public void onNext(T t) {
            if (!this.done) {
                try {
                    this.onNext.accept(t);
                    this.actual.onNext(t);
                } catch (Throwable e) {
                    Exceptions.throwIfFatal(e);
                    this.f2688s.dispose();
                    onError(e);
                }
            }
        }

        public void onError(Throwable t) {
            if (this.done) {
                RxJavaPlugins.onError(t);
                return;
            }
            this.done = true;
            try {
                this.onError.accept(t);
            } catch (Throwable e) {
                Exceptions.throwIfFatal(e);
                t = new CompositeException(t, e);
            }
            this.actual.onError(t);
            try {
                this.onAfterTerminate.run();
            } catch (Throwable e2) {
                Exceptions.throwIfFatal(e2);
                RxJavaPlugins.onError(e2);
            }
        }

        public void onComplete() {
            if (!this.done) {
                try {
                    this.onComplete.run();
                    this.done = true;
                    this.actual.onComplete();
                    try {
                        this.onAfterTerminate.run();
                    } catch (Throwable e) {
                        Exceptions.throwIfFatal(e);
                        RxJavaPlugins.onError(e);
                    }
                } catch (Throwable e2) {
                    Exceptions.throwIfFatal(e2);
                    onError(e2);
                }
            }
        }
    }

    public ObservableDoOnEach(ObservableSource<T> source, Consumer<? super T> onNext, Consumer<? super Throwable> onError, Action onComplete, Action onAfterTerminate) {
        super(source);
        this.onNext = onNext;
        this.onError = onError;
        this.onComplete = onComplete;
        this.onAfterTerminate = onAfterTerminate;
    }

    public void subscribeActual(Observer<? super T> t) {
        this.source.subscribe(new DoOnEachObserver(t, this.onNext, this.onError, this.onComplete, this.onAfterTerminate));
    }
}
