package io.reactivex.internal.operators.observable;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Predicate;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.fuseable.FuseToObservable;
import io.reactivex.plugins.RxJavaPlugins;

public final class ObservableAnySingle<T> extends Single<Boolean> implements FuseToObservable<Boolean> {
    final Predicate<? super T> predicate;
    final ObservableSource<T> source;

    static final class AnyObserver<T> implements Observer<T>, Disposable {
        final SingleObserver<? super Boolean> actual;
        boolean done;
        final Predicate<? super T> predicate;
        Disposable f2658s;

        AnyObserver(SingleObserver<? super Boolean> actual, Predicate<? super T> predicate) {
            this.actual = actual;
            this.predicate = predicate;
        }

        public void onSubscribe(Disposable s) {
            if (DisposableHelper.validate(this.f2658s, s)) {
                this.f2658s = s;
                this.actual.onSubscribe(this);
            }
        }

        public void onNext(T t) {
            if (!this.done) {
                try {
                    if (this.predicate.test(t)) {
                        this.done = true;
                        this.f2658s.dispose();
                        this.actual.onSuccess(Boolean.valueOf(true));
                    }
                } catch (Throwable e) {
                    Exceptions.throwIfFatal(e);
                    this.f2658s.dispose();
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
                this.actual.onSuccess(Boolean.valueOf(false));
            }
        }

        public void dispose() {
            this.f2658s.dispose();
        }

        public boolean isDisposed() {
            return this.f2658s.isDisposed();
        }
    }

    public ObservableAnySingle(ObservableSource<T> source, Predicate<? super T> predicate) {
        this.source = source;
        this.predicate = predicate;
    }

    protected void subscribeActual(SingleObserver<? super Boolean> t) {
        this.source.subscribe(new AnyObserver(t, this.predicate));
    }

    public Observable<Boolean> fuseToObservable() {
        return RxJavaPlugins.onAssembly(new ObservableAny(this.source, this.predicate));
    }
}
