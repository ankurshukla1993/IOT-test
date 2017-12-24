package com.bumptech.glide;

import com.bumptech.glide.load.model.ModelLoader;

public final class RequestManager$GenericModelRequest<A, T> {
    private final Class<T> dataClass;
    private final ModelLoader<A, T> modelLoader;
    final /* synthetic */ RequestManager this$0;

    public final class GenericTypeRequest {
        private final A model;
        private final Class<A> modelClass;
        private final boolean providedModel;

        GenericTypeRequest(A model) {
            this.providedModel = true;
            this.model = model;
            this.modelClass = RequestManager.access$000(model);
        }

        GenericTypeRequest(Class<A> modelClass) {
            this.providedModel = false;
            this.model = null;
            this.modelClass = modelClass;
        }

        public <Z> GenericTranscodeRequest<A, T, Z> as(Class<Z> resourceClass) {
            GenericTranscodeRequest<A, T, Z> result = (GenericTranscodeRequest) RequestManager.access$500(RequestManager$GenericModelRequest.this.this$0).apply(new GenericTranscodeRequest(RequestManager.access$100(RequestManager$GenericModelRequest.this.this$0), RequestManager.access$200(RequestManager$GenericModelRequest.this.this$0), this.modelClass, RequestManager$GenericModelRequest.this.modelLoader, RequestManager$GenericModelRequest.this.dataClass, resourceClass, RequestManager.access$300(RequestManager$GenericModelRequest.this.this$0), RequestManager.access$400(RequestManager$GenericModelRequest.this.this$0), RequestManager.access$500(RequestManager$GenericModelRequest.this.this$0)));
            if (this.providedModel) {
                result.load(this.model);
            }
            return result;
        }
    }

    RequestManager$GenericModelRequest(RequestManager requestManager, ModelLoader<A, T> modelLoader, Class<T> dataClass) {
        this.this$0 = requestManager;
        this.modelLoader = modelLoader;
        this.dataClass = dataClass;
    }

    public GenericTypeRequest from(Class<A> modelClass) {
        return new GenericTypeRequest((Class) modelClass);
    }

    public GenericTypeRequest load(A model) {
        return new GenericTypeRequest((Object) model);
    }
}
