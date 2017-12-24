package com.google.common.util.concurrent;

import java.util.concurrent.Executor;

class Futures$FallbackFuture<V> extends AbstractFuture<V> {
    private volatile ListenableFuture<? extends V> running;

    Futures$FallbackFuture(ListenableFuture<? extends V> input, final FutureFallback<? extends V> fallback, Executor executor) {
        this.running = input;
        Futures.addCallback(this.running, new FutureCallback<V>() {

            class C18601 implements FutureCallback<V> {
                C18601() {
                }

                public void onSuccess(V value) {
                    Futures$FallbackFuture.this.set(value);
                }

                public void onFailure(Throwable t) {
                    if (Futures$FallbackFuture.this.running.isCancelled()) {
                        Futures$FallbackFuture.this.cancel(false);
                    } else {
                        Futures$FallbackFuture.this.setException(t);
                    }
                }
            }

            public void onSuccess(V value) {
                Futures$FallbackFuture.this.set(value);
            }

            public void onFailure(Throwable t) {
                if (!Futures$FallbackFuture.this.isCancelled()) {
                    try {
                        Futures$FallbackFuture.this.running = fallback.create(t);
                        if (Futures$FallbackFuture.this.isCancelled()) {
                            Futures$FallbackFuture.this.running.cancel(Futures$FallbackFuture.this.wasInterrupted());
                        } else {
                            Futures.addCallback(Futures$FallbackFuture.this.running, new C18601(), MoreExecutors.directExecutor());
                        }
                    } catch (Throwable e) {
                        Futures$FallbackFuture.this.setException(e);
                    }
                }
            }
        }, executor);
    }

    public boolean cancel(boolean mayInterruptIfRunning) {
        if (!super.cancel(mayInterruptIfRunning)) {
            return false;
        }
        this.running.cancel(mayInterruptIfRunning);
        return true;
    }
}
