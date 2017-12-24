package io.reactivex.internal.operators.maybe;

import io.reactivex.Maybe;
import io.reactivex.MaybeObserver;
import io.reactivex.MaybeSource;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.fuseable.FuseToMaybe;
import io.reactivex.internal.fuseable.HasUpstreamMaybeSource;
import io.reactivex.plugins.RxJavaPlugins;

public final class MaybeIsEmptySingle<T> extends Single<Boolean> implements HasUpstreamMaybeSource<T>, FuseToMaybe<Boolean> {
    final MaybeSource<T> source;

    static final class IsEmptyMaybeObserver<T> implements MaybeObserver<T>, Disposable {
        final SingleObserver<? super Boolean> actual;
        Disposable f2643d;

        IsEmptyMaybeObserver(SingleObserver<? super Boolean> actual) {
            this.actual = actual;
        }

        public void dispose() {
            this.f2643d.dispose();
            this.f2643d = DisposableHelper.DISPOSED;
        }

        public boolean isDisposed() {
            return this.f2643d.isDisposed();
        }

        public void onSubscribe(Disposable d) {
            if (DisposableHelper.validate(this.f2643d, d)) {
                this.f2643d = d;
                this.actual.onSubscribe(this);
            }
        }

        public void onSuccess(T t) {
            this.f2643d = DisposableHelper.DISPOSED;
            this.actual.onSuccess(Boolean.valueOf(false));
        }

        public void onError(Throwable e) {
            this.f2643d = DisposableHelper.DISPOSED;
            this.actual.onError(e);
        }

        public void onComplete() {
            this.f2643d = DisposableHelper.DISPOSED;
            this.actual.onSuccess(Boolean.valueOf(true));
        }
    }

    public MaybeIsEmptySingle(MaybeSource<T> source) {
        this.source = source;
    }

    public MaybeSource<T> source() {
        return this.source;
    }

    public Maybe<Boolean> fuseToMaybe() {
        return RxJavaPlugins.onAssembly(new MaybeIsEmpty(this.source));
    }

    protected void subscribeActual(SingleObserver<? super Boolean> observer) {
        this.source.subscribe(new IsEmptyMaybeObserver(observer));
    }
}
