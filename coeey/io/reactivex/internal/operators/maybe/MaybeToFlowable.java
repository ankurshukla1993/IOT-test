package io.reactivex.internal.operators.maybe;

import io.reactivex.Flowable;
import io.reactivex.MaybeObserver;
import io.reactivex.MaybeSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.fuseable.HasUpstreamMaybeSource;
import io.reactivex.internal.subscriptions.DeferredScalarSubscription;
import org.reactivestreams.Subscriber;

public final class MaybeToFlowable<T> extends Flowable<T> implements HasUpstreamMaybeSource<T> {
    final MaybeSource<T> source;

    static final class MaybeToFlowableSubscriber<T> extends DeferredScalarSubscription<T> implements MaybeObserver<T> {
        private static final long serialVersionUID = 7603343402964826922L;
        Disposable f2649d;

        MaybeToFlowableSubscriber(Subscriber<? super T> actual) {
            super(actual);
        }

        public void onSubscribe(Disposable d) {
            if (DisposableHelper.validate(this.f2649d, d)) {
                this.f2649d = d;
                this.actual.onSubscribe(this);
            }
        }

        public void onSuccess(T value) {
            complete(value);
        }

        public void onError(Throwable e) {
            this.actual.onError(e);
        }

        public void onComplete() {
            this.actual.onComplete();
        }

        public void cancel() {
            super.cancel();
            this.f2649d.dispose();
        }
    }

    public MaybeToFlowable(MaybeSource<T> source) {
        this.source = source;
    }

    public MaybeSource<T> source() {
        return this.source;
    }

    protected void subscribeActual(Subscriber<? super T> s) {
        this.source.subscribe(new MaybeToFlowableSubscriber(s));
    }
}
