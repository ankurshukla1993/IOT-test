package io.reactivex.internal.operators.maybe;

import io.reactivex.MaybeObserver;
import io.reactivex.MaybeSource;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.fuseable.HasUpstreamMaybeSource;
import java.util.NoSuchElementException;

public final class MaybeToSingle<T> extends Single<T> implements HasUpstreamMaybeSource<T> {
    final T defaultValue;
    final MaybeSource<T> source;

    static final class ToSingleMaybeSubscriber<T> implements MaybeObserver<T>, Disposable {
        final SingleObserver<? super T> actual;
        Disposable f2651d;
        final T defaultValue;

        ToSingleMaybeSubscriber(SingleObserver<? super T> actual, T defaultValue) {
            this.actual = actual;
            this.defaultValue = defaultValue;
        }

        public void dispose() {
            this.f2651d.dispose();
            this.f2651d = DisposableHelper.DISPOSED;
        }

        public boolean isDisposed() {
            return this.f2651d.isDisposed();
        }

        public void onSubscribe(Disposable d) {
            if (DisposableHelper.validate(this.f2651d, d)) {
                this.f2651d = d;
                this.actual.onSubscribe(this);
            }
        }

        public void onSuccess(T value) {
            this.f2651d = DisposableHelper.DISPOSED;
            this.actual.onSuccess(value);
        }

        public void onError(Throwable e) {
            this.f2651d = DisposableHelper.DISPOSED;
            this.actual.onError(e);
        }

        public void onComplete() {
            this.f2651d = DisposableHelper.DISPOSED;
            if (this.defaultValue != null) {
                this.actual.onSuccess(this.defaultValue);
            } else {
                this.actual.onError(new NoSuchElementException("The MaybeSource is empty"));
            }
        }
    }

    public MaybeToSingle(MaybeSource<T> source, T defaultValue) {
        this.source = source;
        this.defaultValue = defaultValue;
    }

    public MaybeSource<T> source() {
        return this.source;
    }

    protected void subscribeActual(SingleObserver<? super T> observer) {
        this.source.subscribe(new ToSingleMaybeSubscriber(observer, this.defaultValue));
    }
}
