package io.reactivex.internal.operators.maybe;

import io.reactivex.MaybeObserver;
import io.reactivex.MaybeSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicReference;

public final class MaybeTakeUntilMaybe<T, U> extends AbstractMaybeWithUpstream<T, T> {
    final MaybeSource<U> other;

    static final class TakeUntilMainMaybeObserver<T, U> extends AtomicReference<Disposable> implements MaybeObserver<T>, Disposable {
        private static final long serialVersionUID = -2187421758664251153L;
        final MaybeObserver<? super T> actual;
        final TakeUntilOtherMaybeObserver<U> other = new TakeUntilOtherMaybeObserver(this);

        static final class TakeUntilOtherMaybeObserver<U> extends AtomicReference<Disposable> implements MaybeObserver<U> {
            private static final long serialVersionUID = -1266041316834525931L;
            final TakeUntilMainMaybeObserver<?, U> parent;

            TakeUntilOtherMaybeObserver(TakeUntilMainMaybeObserver<?, U> parent) {
                this.parent = parent;
            }

            public void onSubscribe(Disposable d) {
                DisposableHelper.setOnce(this, d);
            }

            public void onSuccess(Object value) {
                this.parent.otherComplete();
            }

            public void onError(Throwable e) {
                this.parent.otherError(e);
            }

            public void onComplete() {
                this.parent.otherComplete();
            }
        }

        TakeUntilMainMaybeObserver(MaybeObserver<? super T> actual) {
            this.actual = actual;
        }

        public void dispose() {
            DisposableHelper.dispose(this);
            DisposableHelper.dispose(this.other);
        }

        public boolean isDisposed() {
            return DisposableHelper.isDisposed((Disposable) get());
        }

        public void onSubscribe(Disposable d) {
            DisposableHelper.setOnce(this, d);
        }

        public void onSuccess(T value) {
            DisposableHelper.dispose(this.other);
            if (getAndSet(DisposableHelper.DISPOSED) != DisposableHelper.DISPOSED) {
                this.actual.onSuccess(value);
            }
        }

        public void onError(Throwable e) {
            DisposableHelper.dispose(this.other);
            if (getAndSet(DisposableHelper.DISPOSED) != DisposableHelper.DISPOSED) {
                this.actual.onError(e);
            } else {
                RxJavaPlugins.onError(e);
            }
        }

        public void onComplete() {
            DisposableHelper.dispose(this.other);
            if (getAndSet(DisposableHelper.DISPOSED) != DisposableHelper.DISPOSED) {
                this.actual.onComplete();
            }
        }

        void otherError(Throwable e) {
            if (DisposableHelper.dispose(this)) {
                this.actual.onError(e);
            } else {
                RxJavaPlugins.onError(e);
            }
        }

        void otherComplete() {
            if (DisposableHelper.dispose(this)) {
                this.actual.onComplete();
            }
        }
    }

    public MaybeTakeUntilMaybe(MaybeSource<T> source, MaybeSource<U> other) {
        super(source);
        this.other = other;
    }

    protected void subscribeActual(MaybeObserver<? super T> observer) {
        TakeUntilMainMaybeObserver<T, U> parent = new TakeUntilMainMaybeObserver(observer);
        observer.onSubscribe(parent);
        this.other.subscribe(parent.other);
        this.source.subscribe(parent);
    }
}
