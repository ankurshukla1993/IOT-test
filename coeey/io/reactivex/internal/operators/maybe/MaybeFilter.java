package io.reactivex.internal.operators.maybe;

import io.reactivex.MaybeObserver;
import io.reactivex.MaybeSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Predicate;
import io.reactivex.internal.disposables.DisposableHelper;

public final class MaybeFilter<T> extends AbstractMaybeWithUpstream<T, T> {
    final Predicate<? super T> predicate;

    static final class FilterMaybeObserver<T> implements MaybeObserver<T>, Disposable {
        final MaybeObserver<? super T> actual;
        Disposable f2631d;
        final Predicate<? super T> predicate;

        FilterMaybeObserver(MaybeObserver<? super T> actual, Predicate<? super T> predicate) {
            this.actual = actual;
            this.predicate = predicate;
        }

        public void dispose() {
            Disposable d = this.f2631d;
            this.f2631d = DisposableHelper.DISPOSED;
            d.dispose();
        }

        public boolean isDisposed() {
            return this.f2631d.isDisposed();
        }

        public void onSubscribe(Disposable d) {
            if (DisposableHelper.validate(this.f2631d, d)) {
                this.f2631d = d;
                this.actual.onSubscribe(this);
            }
        }

        public void onSuccess(T value) {
            try {
                if (this.predicate.test(value)) {
                    this.actual.onSuccess(value);
                } else {
                    this.actual.onComplete();
                }
            } catch (Throwable ex) {
                Exceptions.throwIfFatal(ex);
                this.actual.onError(ex);
            }
        }

        public void onError(Throwable e) {
            this.actual.onError(e);
        }

        public void onComplete() {
            this.actual.onComplete();
        }
    }

    public MaybeFilter(MaybeSource<T> source, Predicate<? super T> predicate) {
        super(source);
        this.predicate = predicate;
    }

    protected void subscribeActual(MaybeObserver<? super T> observer) {
        this.source.subscribe(new FilterMaybeObserver(observer, this.predicate));
    }
}
