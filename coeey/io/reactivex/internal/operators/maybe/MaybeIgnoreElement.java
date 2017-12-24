package io.reactivex.internal.operators.maybe;

import io.reactivex.MaybeObserver;
import io.reactivex.MaybeSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;

public final class MaybeIgnoreElement<T> extends AbstractMaybeWithUpstream<T, T> {

    static final class IgnoreMaybeObserver<T> implements MaybeObserver<T>, Disposable {
        final MaybeObserver<? super T> actual;
        Disposable f2640d;

        IgnoreMaybeObserver(MaybeObserver<? super T> actual) {
            this.actual = actual;
        }

        public void onSubscribe(Disposable d) {
            if (DisposableHelper.validate(this.f2640d, d)) {
                this.f2640d = d;
                this.actual.onSubscribe(this);
            }
        }

        public void onSuccess(T t) {
            this.f2640d = DisposableHelper.DISPOSED;
            this.actual.onComplete();
        }

        public void onError(Throwable e) {
            this.f2640d = DisposableHelper.DISPOSED;
            this.actual.onError(e);
        }

        public void onComplete() {
            this.f2640d = DisposableHelper.DISPOSED;
            this.actual.onComplete();
        }

        public boolean isDisposed() {
            return this.f2640d.isDisposed();
        }

        public void dispose() {
            this.f2640d.dispose();
            this.f2640d = DisposableHelper.DISPOSED;
        }
    }

    public MaybeIgnoreElement(MaybeSource<T> source) {
        super(source);
    }

    protected void subscribeActual(MaybeObserver<? super T> observer) {
        this.source.subscribe(new IgnoreMaybeObserver(observer));
    }
}
