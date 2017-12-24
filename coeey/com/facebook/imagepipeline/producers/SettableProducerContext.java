package com.facebook.imagepipeline.producers;

import com.facebook.imagepipeline.common.Priority;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequest$RequestLevel;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public class SettableProducerContext extends BaseProducerContext {
    public SettableProducerContext(ImageRequest imageRequest, String id, ProducerListener producerListener, Object callerContext, ImageRequest$RequestLevel lowestPermittedRequestLevel, boolean isPrefetch, boolean isIntermediateResultExpected, Priority priority) {
        super(imageRequest, id, producerListener, callerContext, lowestPermittedRequestLevel, isPrefetch, isIntermediateResultExpected, priority);
    }

    public void setIsPrefetch(boolean isPrefetch) {
        BaseProducerContext.callOnIsPrefetchChanged(setIsPrefetchNoCallbacks(isPrefetch));
    }

    public void setIsIntermediateResultExpected(boolean isIntermediateResultExpected) {
        BaseProducerContext.callOnIsIntermediateResultExpectedChanged(setIsIntermediateResultExpectedNoCallbacks(isIntermediateResultExpected));
    }

    public void setPriority(Priority priority) {
        BaseProducerContext.callOnPriorityChanged(setPriorityNoCallbacks(priority));
    }
}
