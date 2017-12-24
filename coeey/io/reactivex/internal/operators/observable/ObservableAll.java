package io.reactivex.internal.operators.observable;

import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Predicate;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.plugins.RxJavaPlugins;

public final class ObservableAll<T> extends AbstractObservableWithUpstream<T, Boolean> {
    final Predicate<? super T> predicate;

    static final class AllObserver<T> implements Observer<T>, Disposable {
        final Observer<? super Boolean> actual;
        boolean done;
        final Predicate<? super T> predicate;
        Disposable f2655s;

        AllObserver(Observer<? super Boolean> actual, Predicate<? super T> predicate) {
            this.actual = actual;
            this.predicate = predicate;
        }

        public void onSubscribe(Disposable s) {
            if (DisposableHelper.validate(this.f2655s, s)) {
                this.f2655s = s;
                this.actual.onSubscribe(this);
            }
        }

        public void onNext(T t) {
            if (!this.done) {
                try {
                    if (!this.predicate.test(t)) {
                        this.done = true;
                        this.f2655s.dispose();
                        this.actual.onNext(Boolean.valueOf(false));
                        this.actual.onComplete();
                    }
                } catch (Throwable e) {
                    Exceptions.throwIfFatal(e);
                    this.f2655s.dispose();
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
            this.actual.onError(t);
        }

        public void onComplete() {
            if (!this.done) {
                this.done = true;
                this.actual.onNext(Boolean.valueOf(true));
                this.actual.onComplete();
            }
        }

        public void dispose() {
            this.f2655s.dispose();
        }

        public boolean isDisposed() {
            return this.f2655s.isDisposed();
        }
    }

    public ObservableAll(ObservableSource<T> source, Predicate<? super T> predicate) {
        super(source);
        this.predicate = predicate;
    }

    protected void subscribeActual(Observer<? super Boolean> t) {
        this.source.subscribe(new AllObserver(t, this.predicate));
    }
}
