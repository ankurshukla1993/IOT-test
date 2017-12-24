package io.reactivex.internal.operators.maybe;

import io.reactivex.Maybe;
import io.reactivex.MaybeObserver;
import io.reactivex.SingleObserver;
import io.reactivex.SingleSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.fuseable.HasUpstreamSingleSource;

public final class MaybeFromSingle<T> extends Maybe<T> implements HasUpstreamSingleSource<T> {
    final SingleSource<T> source;

    static final class FromSingleObserver<T> implements SingleObserver<T>, Disposable {
        final MaybeObserver<? super T> actual;
        Disposable f2638d;

        FromSingleObserver(MaybeObserver<? super T> actual) {
            this.actual = actual;
        }

        public void dispose() {
            this.f2638d.dispose();
            this.f2638d = DisposableHelper.DISPOSED;
        }

        public boolean isDisposed() {
            return this.f2638d.isDisposed();
        }

        public void onSubscribe(Disposable d) {
            if (DisposableHelper.validate(this.f2638d, d)) {
                this.f2638d = d;
                this.actual.onSubscribe(this);
            }
        }

        public void onSuccess(T value) {
            this.f2638d = DisposableHelper.DISPOSED;
            this.actual.onSuccess(value);
        }

        public void onError(Throwable e) {
            this.f2638d = DisposableHelper.DISPOSED;
            this.actual.onError(e);
        }
    }

    public MaybeFromSingle(SingleSource<T> source) {
        this.source = source;
    }

    public SingleSource<T> source() {
        return this.source;
    }

    protected void subscribeActual(MaybeObserver<? super T> observer) {
        this.source.subscribe(new FromSingleObserver(observer));
    }
}
