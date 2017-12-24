package com.bumptech.glide;

import com.bumptech.glide.load.model.ModelLoader;
import java.io.InputStream;

public final class RequestManager$ImageModelRequest<T> {
    private final ModelLoader<T, InputStream> loader;
    final /* synthetic */ RequestManager this$0;

    RequestManager$ImageModelRequest(RequestManager requestManager, ModelLoader<T, InputStream> loader) {
        this.this$0 = requestManager;
        this.loader = loader;
    }

    public DrawableTypeRequest<T> from(Class<T> modelClass) {
        return (DrawableTypeRequest) RequestManager.access$500(this.this$0).apply(new DrawableTypeRequest(modelClass, this.loader, null, RequestManager.access$100(this.this$0), RequestManager.access$200(this.this$0), RequestManager.access$300(this.this$0), RequestManager.access$400(this.this$0), RequestManager.access$500(this.this$0)));
    }

    public DrawableTypeRequest<T> load(T model) {
        return (DrawableTypeRequest) from(RequestManager.access$000(model)).load(model);
    }
}
