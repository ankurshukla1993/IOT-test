package io.reactivex.internal.operators.maybe;

import io.reactivex.MaybeObserver;
import io.reactivex.MaybeSource;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.fuseable.HasUpstreamMaybeSource;
import io.reactivex.internal.observers.DeferredScalarDisposable;

public final class MaybeToObservable<T> extends Observable<T> implements HasUpstreamMaybeSource<T> {
    final MaybeSource<T> source;

    static final class MaybeToFlowableSubscriber<T> extends DeferredScalarDisposable<T> implements MaybeObserver<T> {
        private static final long serialVersionUID = 7603343402964826922L;
        Disposable f2650d;

        MaybeToFlowableSubscriber(Observer<? super T> actual) {
            super(actual);
        }

        public void onSubscribe(Disposable d) {
            if (DisposableHelper.validate(this.f2650d, d)) {
                this.f2650d = d;
                this.actual.onSubscribe(this);
            }
        }

        public void onSuccess(T value) {
            complete(value);
        }

        public void onError(Throwable e) {
            error(e);
        }

        public void onComplete() {
            complete();
        }

        public void dispose() {
            super.dispose();
            this.f2650d.dispose();
        }
    }

    public MaybeToObservable(MaybeSource<T> source) {
        this.source = source;
    }

    public MaybeSource<T> source() {
        return this.source;
    }

    protected void subscribeActual(Observer<? super T> s) {
        this.source.subscribe(new MaybeToFlowableSubscriber(s));
    }
}
