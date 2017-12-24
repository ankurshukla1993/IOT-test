package io.reactivex.internal.operators.maybe;

import io.reactivex.MaybeObserver;
import io.reactivex.MaybeSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.BiConsumer;
import io.reactivex.internal.disposables.DisposableHelper;

public final class MaybeDoOnEvent<T> extends AbstractMaybeWithUpstream<T, T> {
    final BiConsumer<? super T, ? super Throwable> onEvent;

    static final class DoOnEventMaybeObserver<T> implements MaybeObserver<T>, Disposable {
        final MaybeObserver<? super T> actual;
        Disposable f2630d;
        final BiConsumer<? super T, ? super Throwable> onEvent;

        DoOnEventMaybeObserver(MaybeObserver<? super T> actual, BiConsumer<? super T, ? super Throwable> onEvent) {
            this.actual = actual;
            this.onEvent = onEvent;
        }

        public void dispose() {
            this.f2630d.dispose();
            this.f2630d = DisposableHelper.DISPOSED;
        }

        public boolean isDisposed() {
            return this.f2630d.isDisposed();
        }

        public void onSubscribe(Disposable d) {
            if (DisposableHelper.validate(this.f2630d, d)) {
                this.f2630d = d;
                this.actual.onSubscribe(this);
            }
        }

        public void onSuccess(T value) {
            this.f2630d = DisposableHelper.DISPOSED;
            try {
                this.onEvent.accept(value, null);
                this.actual.onSuccess(value);
            } catch (Throwable ex) {
                Exceptions.throwIfFatal(ex);
                this.actual.onError(ex);
            }
        }

        public void onError(Throwable e) {
            this.f2630d = DisposableHelper.DISPOSED;
            try {
                this.onEvent.accept(null, e);
            } catch (Throwable ex) {
                Exceptions.throwIfFatal(ex);
                e = new CompositeException(e, ex);
            }
            this.actual.onError(e);
        }

        public void onComplete() {
            this.f2630d = DisposableHelper.DISPOSED;
            try {
                this.onEvent.accept(null, null);
                this.actual.onComplete();
            } catch (Throwable ex) {
                Exceptions.throwIfFatal(ex);
                this.actual.onError(ex);
            }
        }
    }

    public MaybeDoOnEvent(MaybeSource<T> source, BiConsumer<? super T, ? super Throwable> onEvent) {
        super(source);
        this.onEvent = onEvent;
    }

    protected void subscribeActual(MaybeObserver<? super T> observer) {
        this.source.subscribe(new DoOnEventMaybeObserver(observer, this.onEvent));
    }
}
