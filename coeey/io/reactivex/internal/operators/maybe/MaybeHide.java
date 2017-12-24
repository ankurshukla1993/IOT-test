package io.reactivex.internal.operators.maybe;

import io.reactivex.MaybeObserver;
import io.reactivex.MaybeSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;

public final class MaybeHide<T> extends AbstractMaybeWithUpstream<T, T> {

    static final class HideMaybeObserver<T> implements MaybeObserver<T>, Disposable {
        final MaybeObserver<? super T> actual;
        Disposable f2639d;

        HideMaybeObserver(MaybeObserver<? super T> actual) {
            this.actual = actual;
        }

        public void dispose() {
            this.f2639d.dispose();
            this.f2639d = DisposableHelper.DISPOSED;
        }

        public boolean isDisposed() {
            return this.f2639d.isDisposed();
        }

        public void onSubscribe(Disposable d) {
            if (DisposableHelper.validate(this.f2639d, d)) {
                this.f2639d = d;
                this.actual.onSubscribe(this);
            }
        }

        public void onSuccess(T value) {
            this.actual.onSuccess(value);
        }

        public void onError(Throwable e) {
            this.actual.onError(e);
        }

        public void onComplete() {
            this.actual.onComplete();
        }
    }

    public MaybeHide(MaybeSource<T> source) {
        super(source);
    }

    protected void subscribeActual(MaybeObserver<? super T> observer) {
        this.source.subscribe(new HideMaybeObserver(observer));
    }
}
