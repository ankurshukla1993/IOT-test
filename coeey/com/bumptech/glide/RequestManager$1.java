package com.bumptech.glide;

import com.bumptech.glide.manager.Lifecycle;

class RequestManager$1 implements Runnable {
    final /* synthetic */ RequestManager this$0;
    final /* synthetic */ Lifecycle val$lifecycle;

    RequestManager$1(RequestManager requestManager, Lifecycle lifecycle) {
        this.this$0 = requestManager;
        this.val$lifecycle = lifecycle;
    }

    public void run() {
        this.val$lifecycle.addListener(this.this$0);
    }
}
