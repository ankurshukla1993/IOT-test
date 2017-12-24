package io.reactivex.internal.operators.maybe;

import io.reactivex.MaybeObserver;
import io.reactivex.MaybeSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Function;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.functions.ObjectHelper;

public final class MaybeMap<T, R> extends AbstractMaybeWithUpstream<T, R> {
    final Function<? super T, ? extends R> mapper;

    static final class MapMaybeObserver<T, R> implements MaybeObserver<T>, Disposable {
        final MaybeObserver<? super R> actual;
        Disposable f2644d;
        final Function<? super T, ? extends R> mapper;

        MapMaybeObserver(MaybeObserver<? super R> actual, Function<? super T, ? extends R> mapper) {
            this.actual = actual;
            this.mapper = mapper;
        }

        public void dispose() {
            Disposable d = this.f2644d;
            this.f2644d = DisposableHelper.DISPOSED;
            d.dispose();
        }

        public boolean isDisposed() {
            return this.f2644d.isDisposed();
        }

        public void onSubscribe(Disposable d) {
            if (DisposableHelper.validate(this.f2644d, d)) {
                this.f2644d = d;
                this.actual.onSubscribe(this);
            }
        }

        public void onSuccess(T value) {
            try {
                this.actual.onSuccess(ObjectHelper.requireNonNull(this.mapper.apply(value), "The mapper returned a null item"));
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

    public MaybeMap(MaybeSource<T> source, Function<? super T, ? extends R> mapper) {
        super(source);
        this.mapper = mapper;
    }

    protected void subscribeActual(MaybeObserver<? super R> observer) {
        this.source.subscribe(new MapMaybeObserver(observer, this.mapper));
    }
}
