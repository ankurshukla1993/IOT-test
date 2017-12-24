package com.facebook.react.modules.fresco;

import android.util.Pair;
import com.facebook.imagepipeline.listener.RequestListener;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.systrace.Systrace;
import com.facebook.systrace.Systrace.EventScope;
import io.fabric.sdk.android.services.events.EventsFilesManager;
import java.util.HashMap;
import java.util.Map;

public class SystraceRequestListener implements RequestListener {
    int mCurrentID = 0;
    Map<String, Pair<Integer, String>> mProducerID = new HashMap();
    Map<String, Pair<Integer, String>> mRequestsID = new HashMap();

    public void onProducerStart(String requestId, String producerName) {
        if (Systrace.isTracing(0)) {
            StringBuilder entryName = new StringBuilder();
            entryName.append("FRESCO_PRODUCER_");
            entryName.append(producerName.replace(':', '_'));
            Pair<Integer, String> requestPair = Pair.create(Integer.valueOf(this.mCurrentID), entryName.toString());
            Systrace.beginAsyncSection(0, (String) requestPair.second, this.mCurrentID);
            this.mProducerID.put(requestId, requestPair);
            this.mCurrentID++;
        }
    }

    public void onProducerFinishWithSuccess(String requestId, String producerName, Map<String, String> map) {
        if (Systrace.isTracing(0) && this.mProducerID.containsKey(requestId)) {
            Pair<Integer, String> entry = (Pair) this.mProducerID.get(requestId);
            Systrace.endAsyncSection(0, (String) entry.second, ((Integer) entry.first).intValue());
            this.mProducerID.remove(requestId);
        }
    }

    public void onProducerFinishWithFailure(String requestId, String producerName, Throwable throwable, Map<String, String> map) {
        if (Systrace.isTracing(0) && this.mProducerID.containsKey(requestId)) {
            Pair<Integer, String> entry = (Pair) this.mProducerID.get(requestId);
            Systrace.endAsyncSection(0, (String) entry.second, ((Integer) entry.first).intValue());
            this.mProducerID.remove(requestId);
        }
    }

    public void onProducerFinishWithCancellation(String requestId, String producerName, Map<String, String> map) {
        if (Systrace.isTracing(0) && this.mProducerID.containsKey(requestId)) {
            Pair<Integer, String> entry = (Pair) this.mProducerID.get(requestId);
            Systrace.endAsyncSection(0, (String) entry.second, ((Integer) entry.first).intValue());
            this.mProducerID.remove(requestId);
        }
    }

    public void onProducerEvent(String requestId, String producerName, String producerEventName) {
        if (Systrace.isTracing(0)) {
            StringBuilder entryName = new StringBuilder();
            entryName.append("FRESCO_PRODUCER_EVENT_");
            entryName.append(requestId.replace(':', '_'));
            entryName.append(EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR);
            entryName.append(producerName.replace(':', '_'));
            entryName.append(EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR);
            entryName.append(producerEventName.replace(':', '_'));
            Systrace.traceInstant(0, entryName.toString(), EventScope.THREAD);
        }
    }

    public void onRequestStart(ImageRequest request, Object callerContext, String requestId, boolean isPrefetch) {
        if (Systrace.isTracing(0)) {
            StringBuilder entryName = new StringBuilder();
            entryName.append("FRESCO_REQUEST_");
            entryName.append(request.getSourceUri().toString().replace(':', '_'));
            Pair<Integer, String> requestPair = Pair.create(Integer.valueOf(this.mCurrentID), entryName.toString());
            Systrace.beginAsyncSection(0, (String) requestPair.second, this.mCurrentID);
            this.mRequestsID.put(requestId, requestPair);
            this.mCurrentID++;
        }
    }

    public void onRequestSuccess(ImageRequest request, String requestId, boolean isPrefetch) {
        if (Systrace.isTracing(0) && this.mRequestsID.containsKey(requestId)) {
            Pair<Integer, String> entry = (Pair) this.mRequestsID.get(requestId);
            Systrace.endAsyncSection(0, (String) entry.second, ((Integer) entry.first).intValue());
            this.mRequestsID.remove(requestId);
        }
    }

    public void onRequestFailure(ImageRequest request, String requestId, Throwable throwable, boolean isPrefetch) {
        if (Systrace.isTracing(0) && this.mRequestsID.containsKey(requestId)) {
            Pair<Integer, String> entry = (Pair) this.mRequestsID.get(requestId);
            Systrace.endAsyncSection(0, (String) entry.second, ((Integer) entry.first).intValue());
            this.mRequestsID.remove(requestId);
        }
    }

    public void onRequestCancellation(String requestId) {
        if (Systrace.isTracing(0) && this.mRequestsID.containsKey(requestId)) {
            Pair<Integer, String> entry = (Pair) this.mRequestsID.get(requestId);
            Systrace.endAsyncSection(0, (String) entry.second, ((Integer) entry.first).intValue());
            this.mRequestsID.remove(requestId);
        }
    }

    public boolean requiresExtraMap(String id) {
        return false;
    }
}
