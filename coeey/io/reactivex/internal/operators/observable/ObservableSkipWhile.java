package io.reactivex.internal.operators.observable;

import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Predicate;
import io.reactivex.internal.disposables.DisposableHelper;

public final class ObservableSkipWhile<T> extends AbstractObservableWithUpstream<T, T> {
    final Predicate<? super T> predicate;

    static final class SkipWhileObserver<T> implements Observer<T>, Disposable {
        final Observer<? super T> actual;
        boolean notSkipping;
        final Predicate<? super T> predicate;
        Disposable f2728s;

        SkipWhileObserver(Observer<? super T> actual, Predicate<? super T> predicate) {
            this.actual = actual;
            this.predicate = predicate;
        }

        public void onSubscribe(Disposable s) {
            if (DisposableHelper.validate(this.f2728s, s)) {
                this.f2728s = s;
                this.actual.onSubscribe(this);
            }
        }

        public void dispose() {
            this.f2728s.dispose();
        }

        public boolean isDisposed() {
            return this.f2728s.isDisposed();
        }

        public void onNext(T t) {
            if (this.notSkipping) {
                this.actual.onNext(t);
                return;
            }
            try {
                if (!this.predicate.test(t)) {
                    this.notSkipping = true;
                    this.actual.onNext(t);
                }
            } catch (Throwable e) {
                Exceptions.throwIfFatal(e);
                this.f2728s.dispose();
                this.actual.onError(e);
            }
        }

        public void onError(Throwable t) {
            this.actual.onError(t);
        }

        public void onComplete() {
            this.actual.onComplete();
        }
    }

    public ObservableSkipWhile(ObservableSource<T> source, Predicate<? super T> predicate) {
        super(source);
        this.predicate = predicate;
    }

    public void subscribeActual(Observer<? super T> s) {
        this.source.subscribe(new SkipWhileObserver(s, this.predicate));
    }
}
