package com.bumptech.glide;

import com.bumptech.glide.request.RequestFutureTarget;

class GenericRequestBuilder$1 implements Runnable {
    final /* synthetic */ GenericRequestBuilder this$0;
    final /* synthetic */ RequestFutureTarget val$target;

    GenericRequestBuilder$1(GenericRequestBuilder genericRequestBuilder, RequestFutureTarget requestFutureTarget) {
        this.this$0 = genericRequestBuilder;
        this.val$target = requestFutureTarget;
    }

    public void run() {
        if (!this.val$target.isCancelled()) {
            this.this$0.into(this.val$target);
        }
    }
}
