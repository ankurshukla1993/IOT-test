package io.reactivex.internal.operators.maybe;

import io.reactivex.MaybeObserver;
import io.reactivex.MaybeSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import java.util.concurrent.atomic.AtomicReference;

public final class MaybeSwitchIfEmpty<T> extends AbstractMaybeWithUpstream<T, T> {
    final MaybeSource<? extends T> other;

    static final class SwitchIfEmptyMaybeObserver<T> extends AtomicReference<Disposable> implements MaybeObserver<T>, Disposable {
        private static final long serialVersionUID = -2223459372976438024L;
        final MaybeObserver<? super T> actual;
        final MaybeSource<? extends T> other;

        static final class OtherMaybeObserver<T> implements MaybeObserver<T> {
            final MaybeObserver<? super T> actual;
            final AtomicReference<Disposable> parent;

            OtherMaybeObserver(MaybeObserver<? super T> actual, AtomicReference<Disposable> parent) {
                this.actual = actual;
                this.parent = parent;
            }

            public void onSubscribe(Disposable d) {
                DisposableHelper.setOnce(this.parent, d);
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

        SwitchIfEmptyMaybeObserver(MaybeObserver<? super T> actual, MaybeSource<? extends T> other) {
            this.actual = actual;
            this.other = other;
        }

        public void dispose() {
            DisposableHelper.dispose(this);
        }

        public boolean isDisposed() {
            return DisposableHelper.isDisposed((Disposable) get());
        }

        public void onSubscribe(Disposable d) {
            if (DisposableHelper.setOnce(this, d)) {
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
            Disposable d = (Disposable) get();
            if (d != DisposableHelper.DISPOSED && compareAndSet(d, null)) {
                this.other.subscribe(new OtherMaybeObserver(this.actual, this));
            }
        }
    }

    public MaybeSwitchIfEmpty(MaybeSource<T> source, MaybeSource<? extends T> other) {
        super(source);
        this.other = other;
    }

    protected void subscribeActual(MaybeObserver<? super T> observer) {
        this.source.subscribe(new SwitchIfEmptyMaybeObserver(observer, this.other));
    }
}
