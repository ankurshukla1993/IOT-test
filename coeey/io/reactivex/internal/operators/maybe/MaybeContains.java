package io.reactivex.internal.operators.maybe;

import io.reactivex.MaybeObserver;
import io.reactivex.MaybeSource;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.fuseable.HasUpstreamMaybeSource;

public final class MaybeContains<T> extends Single<Boolean> implements HasUpstreamMaybeSource<T> {
    final MaybeSource<T> source;
    final Object value;

    static final class ContainsMaybeObserver implements MaybeObserver<Object>, Disposable {
        final SingleObserver<? super Boolean> actual;
        Disposable f2623d;
        final Object value;

        ContainsMaybeObserver(SingleObserver<? super Boolean> actual, Object value) {
            this.actual = actual;
            this.value = value;
        }

        public void dispose() {
            this.f2623d.dispose();
            this.f2623d = DisposableHelper.DISPOSED;
        }

        public boolean isDisposed() {
            return this.f2623d.isDisposed();
        }

        public void onSubscribe(Disposable d) {
            if (DisposableHelper.validate(this.f2623d, d)) {
                this.f2623d = d;
                this.actual.onSubscribe(this);
            }
        }

        public void onSuccess(Object value) {
            this.f2623d = DisposableHelper.DISPOSED;
            this.actual.onSuccess(Boolean.valueOf(ObjectHelper.equals(value, this.value)));
        }

        public void onError(Throwable e) {
            this.f2623d = DisposableHelper.DISPOSED;
            this.actual.onError(e);
        }

        public void onComplete() {
            this.f2623d = DisposableHelper.DISPOSED;
            this.actual.onSuccess(Boolean.valueOf(false));
        }
    }

    public MaybeContains(MaybeSource<T> source, Object value) {
        this.source = source;
        this.value = value;
    }

    public MaybeSource<T> source() {
        return this.source;
    }

    protected void subscribeActual(SingleObserver<? super Boolean> observer) {
        this.source.subscribe(new ContainsMaybeObserver(observer, this.value));
    }
}
