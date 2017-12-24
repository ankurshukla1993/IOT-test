package io.reactivex.internal.operators.maybe;

import io.reactivex.MaybeObserver;
import io.reactivex.MaybeSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;

public final class MaybeDetach<T> extends AbstractMaybeWithUpstream<T, T> {

    static final class DetachMaybeObserver<T> implements MaybeObserver<T>, Disposable {
        MaybeObserver<? super T> actual;
        Disposable f2627d;

        DetachMaybeObserver(MaybeObserver<? super T> actual) {
            this.actual = actual;
        }

        public void dispose() {
            this.actual = null;
            this.f2627d.dispose();
            this.f2627d = DisposableHelper.DISPOSED;
        }

        public boolean isDisposed() {
            return this.f2627d.isDisposed();
        }

        public void onSubscribe(Disposable d) {
            if (DisposableHelper.validate(this.f2627d, d)) {
                this.f2627d = d;
                this.actual.onSubscribe(this);
            }
        }

        public void onSuccess(T value) {
            this.f2627d = DisposableHelper.DISPOSED;
            MaybeObserver<? super T> a = this.actual;
            if (a != null) {
                a.onSuccess(value);
            }
        }

        public void onError(Throwable e) {
            this.f2627d = DisposableHelper.DISPOSED;
            MaybeObserver<? super T> a = this.actual;
            if (a != null) {
                a.onError(e);
            }
        }

        public void onComplete() {
            this.f2627d = DisposableHelper.DISPOSED;
            MaybeObserver<? super T> a = this.actual;
            if (a != null) {
                a.onComplete();
            }
        }
    }

    public MaybeDetach(MaybeSource<T> source) {
        super(source);
    }

    protected void subscribeActual(MaybeObserver<? super T> observer) {
        this.source.subscribe(new DetachMaybeObserver(observer));
    }
}
