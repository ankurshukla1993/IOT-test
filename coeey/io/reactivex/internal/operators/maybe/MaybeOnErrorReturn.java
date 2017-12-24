package io.reactivex.internal.operators.maybe;

import io.reactivex.MaybeObserver;
import io.reactivex.MaybeSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Function;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.functions.ObjectHelper;

public final class MaybeOnErrorReturn<T> extends AbstractMaybeWithUpstream<T, T> {
    final Function<? super Throwable, ? extends T> valueSupplier;

    static final class OnErrorReturnMaybeObserver<T> implements MaybeObserver<T>, Disposable {
        final MaybeObserver<? super T> actual;
        Disposable f2647d;
        final Function<? super Throwable, ? extends T> valueSupplier;

        OnErrorReturnMaybeObserver(MaybeObserver<? super T> actual, Function<? super Throwable, ? extends T> valueSupplier) {
            this.actual = actual;
            this.valueSupplier = valueSupplier;
        }

        public void dispose() {
            this.f2647d.dispose();
        }

        public boolean isDisposed() {
            return this.f2647d.isDisposed();
        }

        public void onSubscribe(Disposable d) {
            if (DisposableHelper.validate(this.f2647d, d)) {
                this.f2647d = d;
                this.actual.onSubscribe(this);
            }
        }

        public void onSuccess(T value) {
            this.actual.onSuccess(value);
        }

        public void onError(Throwable e) {
            try {
                this.actual.onSuccess(ObjectHelper.requireNonNull(this.valueSupplier.apply(e), "The valueSupplier returned a null value"));
            } catch (Throwable ex) {
                Exceptions.throwIfFatal(ex);
                this.actual.onError(new CompositeException(e, ex));
            }
        }

        public void onComplete() {
            this.actual.onComplete();
        }
    }

    public MaybeOnErrorReturn(MaybeSource<T> source, Function<? super Throwable, ? extends T> valueSupplier) {
        super(source);
        this.valueSupplier = valueSupplier;
    }

    protected void subscribeActual(MaybeObserver<? super T> observer) {
        this.source.subscribe(new OnErrorReturnMaybeObserver(observer, this.valueSupplier));
    }
}
