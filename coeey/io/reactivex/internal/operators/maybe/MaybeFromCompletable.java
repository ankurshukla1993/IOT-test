package io.reactivex.internal.operators.maybe;

import io.reactivex.CompletableObserver;
import io.reactivex.CompletableSource;
import io.reactivex.Maybe;
import io.reactivex.MaybeObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.fuseable.HasUpstreamCompletableSource;

public final class MaybeFromCompletable<T> extends Maybe<T> implements HasUpstreamCompletableSource {
    final CompletableSource source;

    static final class FromCompletableObserver<T> implements CompletableObserver, Disposable {
        final MaybeObserver<? super T> actual;
        Disposable f2637d;

        FromCompletableObserver(MaybeObserver<? super T> actual) {
            this.actual = actual;
        }

        public void dispose() {
            this.f2637d.dispose();
            this.f2637d = DisposableHelper.DISPOSED;
        }

        public boolean isDisposed() {
            return this.f2637d.isDisposed();
        }

        public void onSubscribe(Disposable d) {
            if (DisposableHelper.validate(this.f2637d, d)) {
                this.f2637d = d;
                this.actual.onSubscribe(this);
            }
        }

        public void onComplete() {
            this.f2637d = DisposableHelper.DISPOSED;
            this.actual.onComplete();
        }

        public void onError(Throwable e) {
            this.f2637d = DisposableHelper.DISPOSED;
            this.actual.onError(e);
        }
    }

    public MaybeFromCompletable(CompletableSource source) {
        this.source = source;
    }

    public CompletableSource source() {
        return this.source;
    }

    protected void subscribeActual(MaybeObserver<? super T> observer) {
        this.source.subscribe(new FromCompletableObserver(observer));
    }
}
