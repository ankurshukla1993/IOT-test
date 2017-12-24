package io.reactivex.internal.operators.maybe;

import io.reactivex.MaybeObserver;
import io.reactivex.MaybeSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Predicate;
import io.reactivex.internal.disposables.DisposableHelper;

public final class MaybeOnErrorComplete<T> extends AbstractMaybeWithUpstream<T, T> {
    final Predicate<? super Throwable> predicate;

    static final class OnErrorCompleteMaybeObserver<T> implements MaybeObserver<T>, Disposable {
        final MaybeObserver<? super T> actual;
        Disposable f2645d;
        final Predicate<? super Throwable> predicate;

        OnErrorCompleteMaybeObserver(MaybeObserver<? super T> actual, Predicate<? super Throwable> predicate) {
            this.actual = actual;
            this.predicate = predicate;
        }

        public void onSubscribe(Disposable d) {
            if (DisposableHelper.validate(this.f2645d, d)) {
                this.f2645d = d;
                this.actual.onSubscribe(this);
            }
        }

        public void onSuccess(T value) {
            this.actual.onSuccess(value);
        }

        public void onError(Throwable e) {
            try {
                if (this.predicate.test(e)) {
                    this.actual.onComplete();
                } else {
                    this.actual.onError(e);
                }
            } catch (Throwable ex) {
                Exceptions.throwIfFatal(ex);
                this.actual.onError(new CompositeException(e, ex));
            }
        }

        public void onComplete() {
            this.actual.onComplete();
        }

        public void dispose() {
            this.f2645d.dispose();
        }

        public boolean isDisposed() {
            return this.f2645d.isDisposed();
        }
    }

    public MaybeOnErrorComplete(MaybeSource<T> source, Predicate<? super Throwable> predicate) {
        super(source);
        this.predicate = predicate;
    }

    protected void subscribeActual(MaybeObserver<? super T> observer) {
        this.source.subscribe(new OnErrorCompleteMaybeObserver(observer, this.predicate));
    }
}
