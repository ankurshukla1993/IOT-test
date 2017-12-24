package io.reactivex.internal.operators.single;

import io.reactivex.Flowable;
import io.reactivex.SingleObserver;
import io.reactivex.SingleSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.subscriptions.DeferredScalarSubscription;
import org.reactivestreams.Subscriber;

public final class SingleToFlowable<T> extends Flowable<T> {
    final SingleSource<? extends T> source;

    static final class SingleToFlowableObserver<T> extends DeferredScalarSubscription<T> implements SingleObserver<T> {
        private static final long serialVersionUID = 187782011903685568L;
        Disposable f2797d;

        SingleToFlowableObserver(Subscriber<? super T> actual) {
            super(actual);
        }

        public void onSubscribe(Disposable d) {
            if (DisposableHelper.validate(this.f2797d, d)) {
                this.f2797d = d;
                this.actual.onSubscribe(this);
            }
        }

        public void onSuccess(T value) {
            complete(value);
        }

        public void onError(Throwable e) {
            this.actual.onError(e);
        }

        public void cancel() {
            super.cancel();
            this.f2797d.dispose();
        }
    }

    public SingleToFlowable(SingleSource<? extends T> source) {
        this.source = source;
    }

    public void subscribeActual(Subscriber<? super T> s) {
        this.source.subscribe(new SingleToFlowableObserver(s));
    }
}
