package io.reactivex.internal.operators.maybe;

import io.reactivex.MaybeObserver;
import io.reactivex.MaybeSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;

public final class MaybeIsEmpty<T> extends AbstractMaybeWithUpstream<T, Boolean> {

    static final class IsEmptyMaybeObserver<T> implements MaybeObserver<T>, Disposable {
        final MaybeObserver<? super Boolean> actual;
        Disposable f2642d;

        IsEmptyMaybeObserver(MaybeObserver<? super Boolean> actual) {
            this.actual = actual;
        }

        public void dispose() {
            this.f2642d.dispose();
        }

        public boolean isDisposed() {
            return this.f2642d.isDisposed();
        }

        public void onSubscribe(Disposable d) {
            if (DisposableHelper.validate(this.f2642d, d)) {
                this.f2642d = d;
                this.actual.onSubscribe(this);
            }
        }

        public void onSuccess(T t) {
            this.actual.onSuccess(Boolean.valueOf(false));
        }

        public void onError(Throwable e) {
            this.actual.onError(e);
        }

        public void onComplete() {
            this.actual.onSuccess(Boolean.valueOf(true));
        }
    }

    public MaybeIsEmpty(MaybeSource<T> source) {
        super(source);
    }

    protected void subscribeActual(MaybeObserver<? super Boolean> observer) {
        this.source.subscribe(new IsEmptyMaybeObserver(observer));
    }
}
