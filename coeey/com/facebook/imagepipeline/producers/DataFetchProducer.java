package com.facebook.imagepipeline.producers;

import android.net.Uri;
import android.util.Base64;
import com.RNFetchBlob.RNFetchBlobConst;
import com.facebook.common.executors.CallerThreadExecutor;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.memory.PooledByteBufferFactory;
import com.facebook.imagepipeline.request.ImageRequest;
import java.io.ByteArrayInputStream;
import java.io.IOException;

public class DataFetchProducer extends LocalFetchProducer {
    private static final String PRODUCER_NAME = "DataFetchProducer";

    public DataFetchProducer(PooledByteBufferFactory pooledByteBufferFactory, boolean fileDescriptorEnabled) {
        super(CallerThreadExecutor.getInstance(), pooledByteBufferFactory, fileDescriptorEnabled);
    }

    protected EncodedImage getEncodedImage(ImageRequest imageRequest) throws IOException {
        byte[] data = getData(imageRequest.getSourceUri().toString());
        return getByteBufferBackedEncodedImage(new ByteArrayInputStream(data), data.length);
    }

    protected String getProducerName() {
        return PRODUCER_NAME;
    }

    @VisibleForTesting
    static byte[] getData(String uri) {
        Preconditions.checkArgument(uri.substring(0, 5).equals("data:"));
        int commaPos = uri.indexOf(44);
        String dataStr = uri.substring(commaPos + 1, uri.length());
        if (isBase64(uri.substring(0, commaPos))) {
            return Base64.decode(dataStr, 0);
        }
        return Uri.decode(dataStr).getBytes();
    }

    @VisibleForTesting
    static boolean isBase64(String prefix) {
        if (!prefix.contains(";")) {
            return false;
        }
        String[] parameters = prefix.split(";");
        return parameters[parameters.length - 1].equals(RNFetchBlobConst.RNFB_RESPONSE_BASE64);
    }
}
