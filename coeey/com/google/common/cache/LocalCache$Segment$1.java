package com.google.common.cache;

import com.google.common.cache.LocalCache.Segment;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.logging.Level;

class LocalCache$Segment$1 implements Runnable {
    final /* synthetic */ Segment this$0;
    final /* synthetic */ int val$hash;
    final /* synthetic */ Object val$key;
    final /* synthetic */ ListenableFuture val$loadingFuture;
    final /* synthetic */ LocalCache$LoadingValueReference val$loadingValueReference;

    LocalCache$Segment$1(Segment segment, Object obj, int i, LocalCache$LoadingValueReference localCache$LoadingValueReference, ListenableFuture listenableFuture) {
        this.this$0 = segment;
        this.val$key = obj;
        this.val$hash = i;
        this.val$loadingValueReference = localCache$LoadingValueReference;
        this.val$loadingFuture = listenableFuture;
    }

    public void run() {
        try {
            this.this$0.getAndRecordStats(this.val$key, this.val$hash, this.val$loadingValueReference, this.val$loadingFuture);
        } catch (Throwable t) {
            LocalCache.logger.log(Level.WARNING, "Exception thrown during refresh", t);
            this.val$loadingValueReference.setException(t);
        }
    }
}
