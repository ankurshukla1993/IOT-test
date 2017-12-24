package com.bumptech.glide;

class RequestManager$OptionsApplier {
    final /* synthetic */ RequestManager this$0;

    RequestManager$OptionsApplier(RequestManager requestManager) {
        this.this$0 = requestManager;
    }

    public <A, X extends GenericRequestBuilder<A, ?, ?, ?>> X apply(X builder) {
        if (RequestManager.access$800(this.this$0) != null) {
            RequestManager.access$800(this.this$0).apply(builder);
        }
        return builder;
    }
}
