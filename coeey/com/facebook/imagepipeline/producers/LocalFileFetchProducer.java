package com.facebook.imagepipeline.producers;

import com.facebook.common.internal.VisibleForTesting;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.memory.PooledByteBufferFactory;
import com.facebook.imagepipeline.request.ImageRequest;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.Executor;

public class LocalFileFetchProducer extends LocalFetchProducer {
    @VisibleForTesting
    static final String PRODUCER_NAME = "LocalFileFetchProducer";

    public LocalFileFetchProducer(Executor executor, PooledByteBufferFactory pooledByteBufferFactory, boolean decodeFileDescriptorEnabled) {
        super(executor, pooledByteBufferFactory, decodeFileDescriptorEnabled);
    }

    protected EncodedImage getEncodedImage(ImageRequest imageRequest) throws IOException {
        return getEncodedImage(new FileInputStream(imageRequest.getSourceFile().toString()), (int) imageRequest.getSourceFile().length());
    }

    protected String getProducerName() {
        return PRODUCER_NAME;
    }
}
