package com.google.common.util.concurrent;

import com.google.common.base.Preconditions;
import java.util.concurrent.Executor;

public abstract class ForwardingListenableFuture<V> extends ForwardingFuture<V> implements ListenableFuture<V> {

    public static abstract class SimpleForwardingListenableFuture<V> extends ForwardingListenableFuture<V> {
        private final ListenableFuture<V> delegate;

        protected SimpleForwardingListenableFuture(ListenableFuture<V> delegate) {
            this.delegate = (ListenableFuture) Preconditions.checkNotNull(delegate);
        }

        protected final ListenableFuture<V> delegate() {
            return this.delegate;
        }
    }

    protected abstract ListenableFuture<V> delegate();

    protected ForwardingListenableFuture() {
    }

    public void addListener(Runnable listener, Executor exec) {
        delegate().addListener(listener, exec);
    }
}
