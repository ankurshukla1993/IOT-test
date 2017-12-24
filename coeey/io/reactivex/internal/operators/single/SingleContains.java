package io.reactivex.internal.operators.single;

import io.reactivex.SingleObserver;
import io.reactivex.SingleSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.BiPredicate;

public final class SingleContains<T> extends io.reactivex.Single<Boolean> {
    final BiPredicate<Object, Object> comparer;
    final SingleSource<T> source;
    final Object value;

    final class Single implements SingleObserver<T> {
        private final SingleObserver<? super Boolean> f2778s;

        Single(SingleObserver<? super Boolean> s) {
            this.f2778s = s;
        }

        public void onSubscribe(Disposable d) {
            this.f2778s.onSubscribe(d);
        }

        public void onSuccess(T v) {
            try {
                this.f2778s.onSuccess(Boolean.valueOf(SingleContains.this.comparer.test(v, SingleContains.this.value)));
            } catch (Throwable ex) {
                Exceptions.throwIfFatal(ex);
                this.f2778s.onError(ex);
            }
        }

        public void onError(Throwable e) {
            this.f2778s.onError(e);
        }
    }

    public SingleContains(SingleSource<T> source, Object value, BiPredicate<Object, Object> comparer) {
        this.source = source;
        this.value = value;
        this.comparer = comparer;
    }

    protected void subscribeActual(SingleObserver<? super Boolean> s) {
        this.source.subscribe(new Single(s));
    }
}
