package com.facebook.imagepipeline.listener;

import com.facebook.imagepipeline.request.ImageRequest;
import java.util.Map;
import javax.annotation.Nullable;

public class BaseRequestListener implements RequestListener {
    public void onRequestStart(ImageRequest request, Object callerContext, String requestId, boolean isPrefetch) {
    }

    public void onRequestSuccess(ImageRequest request, String requestId, boolean isPrefetch) {
    }

    public void onRequestFailure(ImageRequest request, String requestId, Throwable throwable, boolean isPrefetch) {
    }

    public void onRequestCancellation(String requestId) {
    }

    public void onProducerStart(String requestId, String producerName) {
    }

    public void onProducerEvent(String requestId, String producerName, String eventName) {
    }

    public void onProducerFinishWithSuccess(String requestId, String producerName, @Nullable Map<String, String> map) {
    }

    public void onProducerFinishWithFailure(String requestId, String producerName, Throwable t, @Nullable Map<String, String> map) {
    }

    public void onProducerFinishWithCancellation(String requestId, String producerName, @Nullable Map<String, String> map) {
    }

    public boolean requiresExtraMap(String requestId) {
        return false;
    }
}
