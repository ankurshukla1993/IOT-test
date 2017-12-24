package io.reactivex.internal.operators.maybe;

import io.reactivex.MaybeObserver;
import io.reactivex.MaybeSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Function;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.functions.ObjectHelper;
import java.util.concurrent.atomic.AtomicReference;

public final class MaybeFlatten<T, R> extends AbstractMaybeWithUpstream<T, R> {
    final Function<? super T, ? extends MaybeSource<? extends R>> mapper;

    static final class FlatMapMaybeObserver<T, R> extends AtomicReference<Disposable> implements MaybeObserver<T>, Disposable {
        private static final long serialVersionUID = 4375739915521278546L;
        final MaybeObserver<? super R> actual;
        Disposable f2636d;
        final Function<? super T, ? extends MaybeSource<? extends R>> mapper;

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

        FlatMapMaybeObserver(MaybeObserver<? super R> actual, Function<? super T, ? extends MaybeSource<? extends R>> mapper) {
            this.actual = actual;
            this.mapper = mapper;
        }

        public void dispose() {
            DisposableHelper.dispose(this);
            this.f2636d.dispose();
        }

        public boolean isDisposed() {
            return DisposableHelper.isDisposed((Disposable) get());
        }

        public void onSubscribe(Disposable d) {
            if (DisposableHelper.validate(this.f2636d, d)) {
                this.f2636d = d;
                this.actual.onSubscribe(this);
            }
        }

        public void onSuccess(T value) {
            try {
                MaybeSource<? extends R> source = (MaybeSource) ObjectHelper.requireNonNull(this.mapper.apply(value), "The mapper returned a null MaybeSource");
                if (!isDisposed()) {
                    source.subscribe(new InnerObserver());
                }
            } catch (Exception ex) {
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

    public MaybeFlatten(MaybeSource<T> source, Function<? super T, ? extends MaybeSource<? extends R>> mapper) {
        super(source);
        this.mapper = mapper;
    }

    protected void subscribeActual(MaybeObserver<? super R> observer) {
        this.source.subscribe(new FlatMapMaybeObserver(observer, this.mapper));
    }
}
