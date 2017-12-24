package io.reactivex.processors;

import io.reactivex.internal.subscriptions.DeferredScalarSubscription;
import io.reactivex.plugins.RxJavaPlugins;
import org.reactivestreams.Subscriber;

final class AsyncProcessor$AsyncSubscription<T> extends DeferredScalarSubscription<T> {
    private static final long serialVersionUID = 5629876084736248016L;
    final AsyncProcessor<T> parent;

    AsyncProcessor$AsyncSubscription(Subscriber<? super T> actual, AsyncProcessor<T> parent) {
        super(actual);
        this.parent = parent;
    }

    public void cancel() {
        if (super.tryCancel()) {
            this.parent.remove(this);
        }
    }

    void onComplete() {
        if (!isCancelled()) {
            this.actual.onComplete();
        }
    }

    void onError(Throwable t) {
        if (isCancelled()) {
            RxJavaPlugins.onError(t);
        } else {
            this.actual.onError(t);
        }
    }
}
