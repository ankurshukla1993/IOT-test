package io.reactivex.internal.operators.maybe;

import io.reactivex.Maybe;
import io.reactivex.MaybeObserver;
import io.reactivex.SingleObserver;
import io.reactivex.SingleSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Predicate;
import io.reactivex.internal.disposables.DisposableHelper;

public final class MaybeFilterSingle<T> extends Maybe<T> {
    final Predicate<? super T> predicate;
    final SingleSource<T> source;

    static final class FilterMaybeObserver<T> implements SingleObserver<T>, Disposable {
        final MaybeObserver<? super T> actual;
        Disposable f2632d;
        final Predicate<? super T> predicate;

        FilterMaybeObserver(MaybeObserver<? super T> actual, Predicate<? super T> predicate) {
            this.actual = actual;
            this.predicate = predicate;
        }

        public void dispose() {
            Disposable d = this.f2632d;
            this.f2632d = DisposableHelper.DISPOSED;
            d.dispose();
        }

        public boolean isDisposed() {
            return this.f2632d.isDisposed();
        }

        public void onSubscribe(Disposable d) {
            if (DisposableHelper.validate(this.f2632d, d)) {
                this.f2632d = d;
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
    }

    public MaybeFilterSingle(SingleSource<T> source, Predicate<? super T> predicate) {
        this.source = source;
        this.predicate = predicate;
    }

    protected void subscribeActual(MaybeObserver<? super T> observer) {
        this.source.subscribe(new FilterMaybeObserver(observer, this.predicate));
    }
}
