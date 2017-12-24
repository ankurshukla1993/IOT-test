package io.reactivex.internal.operators.single;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.SingleSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.BiConsumer;

public final class SingleDoOnEvent<T> extends Single<T> {
    final BiConsumer<? super T, ? super Throwable> onEvent;
    final SingleSource<T> source;

    final class DoOnEvent implements SingleObserver<T> {
        private final SingleObserver<? super T> f2787s;

        DoOnEvent(SingleObserver<? super T> s) {
            this.f2787s = s;
        }

        public void onSubscribe(Disposable d) {
            this.f2787s.onSubscribe(d);
        }

        public void onSuccess(T value) {
            try {
                SingleDoOnEvent.this.onEvent.accept(value, null);
                this.f2787s.onSuccess(value);
            } catch (Throwable ex) {
                Exceptions.throwIfFatal(ex);
                this.f2787s.onError(ex);
            }
        }

        public void onError(Throwable e) {
            try {
                SingleDoOnEvent.this.onEvent.accept(null, e);
            } catch (Throwable ex) {
                Exceptions.throwIfFatal(ex);
                e = new CompositeException(e, ex);
            }
            this.f2787s.onError(e);
        }
    }

    public SingleDoOnEvent(SingleSource<T> source, BiConsumer<? super T, ? super Throwable> onEvent) {
        this.source = source;
        this.onEvent = onEvent;
    }

    protected void subscribeActual(SingleObserver<? super T> s) {
        this.source.subscribe(new DoOnEvent(s));
    }
}
