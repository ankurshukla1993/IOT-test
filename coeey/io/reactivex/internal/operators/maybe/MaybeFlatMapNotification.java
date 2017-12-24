package io.reactivex.internal.operators.maybe;

import io.reactivex.MaybeObserver;
import io.reactivex.MaybeSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Function;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.functions.ObjectHelper;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicReference;

public final class MaybeFlatMapNotification<T, R> extends AbstractMaybeWithUpstream<T, R> {
    final Callable<? extends MaybeSource<? extends R>> onCompleteSupplier;
    final Function<? super Throwable, ? extends MaybeSource<? extends R>> onErrorMapper;
    final Function<? super T, ? extends MaybeSource<? extends R>> onSuccessMapper;

    static final class FlatMapMaybeObserver<T, R> extends AtomicReference<Disposable> implements MaybeObserver<T>, Disposable {
        private static final long serialVersionUID = 4375739915521278546L;
        final MaybeObserver<? super R> actual;
        Disposable f2635d;
        final Callable<? extends MaybeSource<? extends R>> onCompleteSupplier;
        final Function<? super Throwable, ? extends MaybeSource<? extends R>> onErrorMapper;
        final Function<? super T, ? extends MaybeSource<? extends R>> onSuccessMapper;

        final class InnerObserver implements MaybeObserver<R> {
            InnerObserver() {
            }

            public void onSubscribe(Disposable d) {
                DisposableHelper.setOnce(FlatMapMaybeObserver.this, d);
            }

            public void onSuccess(R value) {
                FlatMapMaybeObserver.this.actual.onSuccess(value);
            }

            public void onError(Throwable e) {
                FlatMapMaybeObserver.this.actual.onError(e);
            }

            public void onComplete() {
                FlatMapMaybeObserver.this.actual.onComplete();
            }
        }

        FlatMapMaybeObserver(MaybeObserver<? super R> actual, Function<? super T, ? extends MaybeSource<? extends R>> onSuccessMapper, Function<? super Throwable, ? extends MaybeSource<? extends R>> onErrorMapper, Callable<? extends MaybeSource<? extends R>> onCompleteSupplier) {
            this.actual = actual;
            this.onSuccessMapper = onSuccessMapper;
            this.onErrorMapper = onErrorMapper;
            this.onCompleteSupplier = onCompleteSupplier;
        }

        public void dispose() {
            DisposableHelper.dispose(this);
            this.f2635d.dispose();
        }

        public boolean isDisposed() {
            return DisposableHelper.isDisposed((Disposable) get());
        }

        public void onSubscribe(Disposable d) {
            if (DisposableHelper.validate(this.f2635d, d)) {
                this.f2635d = d;
                this.actual.onSubscribe(this);
            }
        }

        public void onSuccess(T value) {
            try {
                ((MaybeSource) ObjectHelper.requireNonNull(this.onSuccessMapper.apply(value), "The onSuccessMapper returned a null MaybeSource")).subscribe(new InnerObserver());
            } catch (Exception ex) {
                Exceptions.throwIfFatal(ex);
                this.actual.onError(ex);
            }
        }

        public void onError(Throwable e) {
            try {
                ((MaybeSource) ObjectHelper.requireNonNull(this.onErrorMapper.apply(e), "The onErrorMapper returned a null MaybeSource")).subscribe(new InnerObserver());
            } catch (Exception ex) {
                Exceptions.throwIfFatal(ex);
                this.actual.onError(new CompositeException(e, ex));
            }
        }

        public void onComplete() {
            try {
                ((MaybeSource) ObjectHelper.requireNonNull(this.onCompleteSupplier.call(), "The onCompleteSupplier returned a null MaybeSource")).subscribe(new InnerObserver());
            } catch (Exception ex) {
                Exceptions.throwIfFatal(ex);
                this.actual.onError(ex);
            }
        }
    }

    public MaybeFlatMapNotification(MaybeSource<T> source, Function<? super T, ? extends MaybeSource<? extends R>> onSuccessMapper, Function<? super Throwable, ? extends MaybeSource<? extends R>> onErrorMapper, Callable<? extends MaybeSource<? extends R>> onCompleteSupplier) {
        super(source);
        this.onSuccessMapper = onSuccessMapper;
        this.onErrorMapper = onErrorMapper;
        this.onCompleteSupplier = onCompleteSupplier;
    }

    protected void subscribeActual(MaybeObserver<? super R> observer) {
        this.source.subscribe(new FlatMapMaybeObserver(observer, this.onSuccessMapper, this.onErrorMapper, this.onCompleteSupplier));
    }
}
