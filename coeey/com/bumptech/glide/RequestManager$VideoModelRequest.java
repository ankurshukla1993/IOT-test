package com.bumptech.glide;

import android.os.ParcelFileDescriptor;
import com.bumptech.glide.load.model.ModelLoader;

public final class RequestManager$VideoModelRequest<T> {
    private final ModelLoader<T, ParcelFileDescriptor> loader;
    final /* synthetic */ RequestManager this$0;

    RequestManager$VideoModelRequest(RequestManager requestManager, ModelLoader<T, ParcelFileDescriptor> loader) {
        this.this$0 = requestManager;
        this.loader = loader;
    }

    public DrawableTypeRequest<T> load(T model) {
        return (DrawableTypeRequest) ((DrawableTypeRequest) RequestManager.access$500(this.this$0).apply(new DrawableTypeRequest(RequestManager.access$000(model), null, this.loader, RequestManager.access$100(this.this$0), RequestManager.access$200(this.this$0), RequestManager.access$300(this.this$0), RequestManager.access$400(this.this$0), RequestManager.access$500(this.this$0)))).load(model);
    }
}
