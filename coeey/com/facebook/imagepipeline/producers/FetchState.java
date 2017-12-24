package com.facebook.imagepipeline.producers;

import android.net.Uri;
import com.facebook.imagepipeline.image.EncodedImage;

public class FetchState {
    private final Consumer<EncodedImage> mConsumer;
    private final ProducerContext mContext;
    private long mLastIntermediateResultTimeMs = 0;

    public FetchState(Consumer<EncodedImage> consumer, ProducerContext context) {
        this.mConsumer = consumer;
        this.mContext = context;
    }

    public Consumer<EncodedImage> getConsumer() {
        return this.mConsumer;
    }

    public ProducerContext getContext() {
        return this.mContext;
    }

    public String getId() {
        return this.mContext.getId();
    }

    public ProducerListener getListener() {
        return this.mContext.getListener();
    }

    public Uri getUri() {
        return this.mContext.getImageRequest().getSourceUri();
    }

    public long getLastIntermediateResultTimeMs() {
        return this.mLastIntermediateResultTimeMs;
    }

    public void setLastIntermediateResultTimeMs(long lastIntermediateResultTimeMs) {
        this.mLastIntermediateResultTimeMs = lastIntermediateResultTimeMs;
    }
}
