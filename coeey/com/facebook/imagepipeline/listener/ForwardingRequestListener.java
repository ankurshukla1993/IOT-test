package com.facebook.imagepipeline.listener;

import com.facebook.common.logging.FLog;
import com.facebook.imagepipeline.request.ImageRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;

public class ForwardingRequestListener implements RequestListener {
    private static final String TAG = "ForwardingRequestListener";
    private final List<RequestListener> mRequestListeners;

    public ForwardingRequestListener(Set<RequestListener> requestListeners) {
        this.mRequestListeners = new ArrayList(requestListeners.size());
        for (RequestListener requestListener : requestListeners) {
            this.mRequestListeners.add(requestListener);
        }
    }

    public void onRequestStart(ImageRequest request, Object callerContext, String requestId, boolean isPrefetch) {
        int numberOfListeners = this.mRequestListeners.size();
        for (int i = 0; i < numberOfListeners; i++) {
            try {
                ((RequestListener) this.mRequestListeners.get(i)).onRequestStart(request, callerContext, requestId, isPrefetch);
            } catch (Exception exception) {
                onException("InternalListener exception in onRequestStart", exception);
            }
        }
    }

    public void onProducerStart(String requestId, String producerName) {
        int numberOfListeners = this.mRequestListeners.size();
        for (int i = 0; i < numberOfListeners; i++) {
            try {
                ((RequestListener) this.mRequestListeners.get(i)).onProducerStart(requestId, producerName);
            } catch (Exception exception) {
                onException("InternalListener exception in onProducerStart", exception);
            }
        }
    }

    public void onProducerFinishWithSuccess(String requestId, String producerName, @Nullable Map<String, String> extraMap) {
        int numberOfListeners = this.mRequestListeners.size();
        for (int i = 0; i < numberOfListeners; i++) {
            try {
                ((RequestListener) this.mRequestListeners.get(i)).onProducerFinishWithSuccess(requestId, producerName, extraMap);
            } catch (Exception exception) {
                onException("InternalListener exception in onProducerFinishWithSuccess", exception);
            }
        }
    }

    public void onProducerFinishWithFailure(String requestId, String producerName, Throwable t, @Nullable Map<String, String> extraMap) {
        int numberOfListeners = this.mRequestListeners.size();
        for (int i = 0; i < numberOfListeners; i++) {
            try {
                ((RequestListener) this.mRequestListeners.get(i)).onProducerFinishWithFailure(requestId, producerName, t, extraMap);
            } catch (Exception exception) {
                onException("InternalListener exception in onProducerFinishWithFailure", exception);
            }
        }
    }

    public void onProducerFinishWithCancellation(String requestId, String producerName, @Nullable Map<String, String> extraMap) {
        int numberOfListeners = this.mRequestListeners.size();
        for (int i = 0; i < numberOfListeners; i++) {
            try {
                ((RequestListener) this.mRequestListeners.get(i)).onProducerFinishWithCancellation(requestId, producerName, extraMap);
            } catch (Exception exception) {
                onException("InternalListener exception in onProducerFinishWithCancellation", exception);
            }
        }
    }

    public void onProducerEvent(String requestId, String producerName, String producerEventName) {
        int numberOfListeners = this.mRequestListeners.size();
        for (int i = 0; i < numberOfListeners; i++) {
            try {
                ((RequestListener) this.mRequestListeners.get(i)).onProducerEvent(requestId, producerName, producerEventName);
            } catch (Exception exception) {
                onException("InternalListener exception in onIntermediateChunkStart", exception);
            }
        }
    }

    public void onRequestSuccess(ImageRequest request, String requestId, boolean isPrefetch) {
        int numberOfListeners = this.mRequestListeners.size();
        for (int i = 0; i < numberOfListeners; i++) {
            try {
                ((RequestListener) this.mRequestListeners.get(i)).onRequestSuccess(request, requestId, isPrefetch);
            } catch (Exception exception) {
                onException("InternalListener exception in onRequestSuccess", exception);
            }
        }
    }

    public void onRequestFailure(ImageRequest request, String requestId, Throwable throwable, boolean isPrefetch) {
        int numberOfListeners = this.mRequestListeners.size();
        for (int i = 0; i < numberOfListeners; i++) {
            try {
                ((RequestListener) this.mRequestListeners.get(i)).onRequestFailure(request, requestId, throwable, isPrefetch);
            } catch (Exception exception) {
                onException("InternalListener exception in onRequestFailure", exception);
            }
        }
    }

    public void onRequestCancellation(String requestId) {
        int numberOfListeners = this.mRequestListeners.size();
        for (int i = 0; i < numberOfListeners; i++) {
            try {
                ((RequestListener) this.mRequestListeners.get(i)).onRequestCancellation(requestId);
            } catch (Exception exception) {
                onException("InternalListener exception in onRequestCancellation", exception);
            }
        }
    }

    public boolean requiresExtraMap(String id) {
        int numberOfListeners = this.mRequestListeners.size();
        for (int i = 0; i < numberOfListeners; i++) {
            if (((RequestListener) this.mRequestListeners.get(i)).requiresExtraMap(id)) {
                return true;
            }
        }
        return false;
    }

    private void onException(String message, Throwable t) {
        FLog.m112e(TAG, message, t);
    }
}
