package com.google.common.util.concurrent;

import com.google.common.base.Preconditions;

class Futures$NonCancellationPropagatingFuture<V> extends AbstractFuture<V> {
    Futures$NonCancellationPropagatingFuture(final ListenableFuture<V> delegate) {
        Preconditions.checkNotNull(delegate);
        Futures.addCallback(delegate, new FutureCallback<V>() {
            public void onSuccess(V result) {
                Futures$NonCancellationPropagatingFuture.this.set(result);
            }

            public void onFailure(Throwable t) {
                if (delegate.isCancelled()) {
                    Futures$NonCancellationPropagatingFuture.this.cancel(false);
                } else {
                    Futures$NonCancellationPropagatingFuture.this.setException(t);
                }
            }
        }, MoreExecutors.directExecutor());
    }
}
