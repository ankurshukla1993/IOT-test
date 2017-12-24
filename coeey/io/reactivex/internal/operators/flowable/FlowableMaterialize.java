package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.Notification;
import io.reactivex.internal.subscribers.SinglePostCompleteSubscriber;
import io.reactivex.plugins.RxJavaPlugins;
import org.reactivestreams.Subscriber;

public final class FlowableMaterialize<T> extends AbstractFlowableWithUpstream<T, Notification<T>> {

    static final class MaterializeSubscriber<T> extends SinglePostCompleteSubscriber<T, Notification<T>> {
        private static final long serialVersionUID = -3740826063558713822L;

        MaterializeSubscriber(Subscriber<? super Notification<T>> actual) {
            super(actual);
        }

        public void onNext(T t) {
            this.produced++;
            this.actual.onNext(Notification.createOnNext(t));
        }

        public void onError(Throwable t) {
            complete(Notification.createOnError(t));
        }

        public void onComplete() {
            complete(Notification.createOnComplete());
        }

        protected void onDrop(Notification<T> n) {
            if (n.isOnError()) {
                RxJavaPlugins.onError(n.getError());
            }
        }
    }

    public FlowableMaterialize(Flowable<T> source) {
        super(source);
    }

    protected void subscribeActual(Subscriber<? super Notification<T>> s) {
        this.source.subscribe(new MaterializeSubscriber(s));
    }
}
