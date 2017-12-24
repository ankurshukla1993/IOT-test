package io.reactivex.internal.operators.completable;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.CompletableSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;

public final class CompletableHide extends Completable {
    final CompletableSource source;

    static final class HideCompletableObserver implements CompletableObserver, Disposable {
        final CompletableObserver actual;
        Disposable f2497d;

        HideCompletableObserver(CompletableObserver actual) {
            this.actual = actual;
        }

        public void dispose() {
            this.f2497d.dispose();
            this.f2497d = DisposableHelper.DISPOSED;
        }

        public boolean isDisposed() {
            return this.f2497d.isDisposed();
        }

        public void onSubscribe(Disposable d) {
            if (DisposableHelper.validate(this.f2497d, d)) {
                this.f2497d = d;
                this.actual.onSubscribe(this);
            }
        }

        public void onError(Throwable e) {
            this.actual.onError(e);
        }

        public void onComplete() {
            this.actual.onComplete();
        }
    }

    public CompletableHide(CompletableSource source) {
        this.source = source;
    }

    protected void subscribeActual(CompletableObserver observer) {
        this.source.subscribe(new HideCompletableObserver(observer));
    }
}
