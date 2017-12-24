package com.facebook.imagepipeline.producers;

import java.io.IOException;
import java.io.InputStream;

class NetworkFetchProducer$1 implements NetworkFetcher$Callback {
    final /* synthetic */ NetworkFetchProducer this$0;
    final /* synthetic */ FetchState val$fetchState;

    NetworkFetchProducer$1(NetworkFetchProducer this$0, FetchState fetchState) {
        this.this$0 = this$0;
        this.val$fetchState = fetchState;
    }

    public void onResponse(InputStream response, int responseLength) throws IOException {
        NetworkFetchProducer.access$000(this.this$0, this.val$fetchState, response, responseLength);
    }

    public void onFailure(Throwable throwable) {
        NetworkFetchProducer.access$100(this.this$0, this.val$fetchState, throwable);
    }

    public void onCancellation() {
        NetworkFetchProducer.access$200(this.this$0, this.val$fetchState);
    }
}
