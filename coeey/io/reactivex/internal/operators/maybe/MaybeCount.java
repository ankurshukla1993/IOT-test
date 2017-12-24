package io.reactivex.internal.operators.maybe;

import io.reactivex.MaybeObserver;
import io.reactivex.MaybeSource;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.fuseable.HasUpstreamMaybeSource;

public final class MaybeCount<T> extends Single<Long> implements HasUpstreamMaybeSource<T> {
    final MaybeSource<T> source;

    static final class CountMaybeObserver implements MaybeObserver<Object>, Disposable {
        final SingleObserver<? super Long> actual;
        Disposable f2624d;

        CountMaybeObserver(SingleObserver<? super Long> actual) {
            this.actual = actual;
        }

        public void onSubscribe(Disposable d) {
            if (DisposableHelper.validate(this.f2624d, d)) {
                this.f2624d = d;
                this.actual.onSubscribe(this);
            }
        }

        public void onSuccess(Object value) {
            this.f2624d = DisposableHelper.DISPOSED;
            this.actual.onSuccess(Long.valueOf(1));
        }

        public void onError(Throwable e) {
            this.f2624d = DisposableHelper.DISPOSED;
            this.actual.onError(e);
        }

        public void onComplete() {
            this.f2624d = DisposableHelper.DISPOSED;
            this.actual.onSuccess(Long.valueOf(0));
        }

        public boolean isDisposed() {
            return this.f2624d.isDisposed();
        }

        public void dispose() {
            this.f2624d.dispose();
            this.f2624d = DisposableHelper.DISPOSED;
        }
    }

    public MaybeCount(MaybeSource<T> source) {
        this.source = source;
    }

    public MaybeSource<T> source() {
        return this.source;
    }

    protected void subscribeActual(SingleObserver<? super Long> observer) {
        this.source.subscribe(new CountMaybeObserver(observer));
    }
}
