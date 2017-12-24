package com.facebook.imagepipeline.producers;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract.Contacts;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.common.util.UriUtil;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.memory.PooledByteBufferFactory;
import com.facebook.imagepipeline.request.ImageRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;

public class LocalContentUriFetchProducer extends LocalFetchProducer {
    @VisibleForTesting
    static final String PRODUCER_NAME = "LocalContentUriFetchProducer";
    private static final String[] PROJECTION = new String[]{"_id", "_data"};
    private final ContentResolver mContentResolver;

    public LocalContentUriFetchProducer(Executor executor, PooledByteBufferFactory pooledByteBufferFactory, ContentResolver contentResolver, boolean decodeFileDescriptorEnabled) {
        super(executor, pooledByteBufferFactory, decodeFileDescriptorEnabled);
        this.mContentResolver = contentResolver;
    }

    protected EncodedImage getEncodedImage(ImageRequest imageRequest) throws IOException {
        Uri uri = imageRequest.getSourceUri();
        if (UriUtil.isLocalContactUri(uri)) {
            InputStream inputStream;
            if (uri.toString().endsWith("/photo")) {
                inputStream = this.mContentResolver.openInputStream(uri);
            } else {
                inputStream = Contacts.openContactPhotoInputStream(this.mContentResolver, uri);
                if (inputStream == null) {
                    throw new IOException("Contact photo does not exist: " + uri);
                }
            }
            return getEncodedImage(inputStream, -1);
        }
        if (UriUtil.isLocalCameraUri(uri)) {
            EncodedImage cameraImage = getCameraImage(uri);
            if (cameraImage != null) {
                return cameraImage;
            }
        }
        return getEncodedImage(this.mContentResolver.openInputStream(uri), -1);
    }

    @Nullable
    private EncodedImage getCameraImage(Uri uri) throws IOException {
        EncodedImage encodedImage = null;
        Cursor cursor = this.mContentResolver.query(uri, PROJECTION, null, null, null);
        if (cursor != null) {
            try {
                if (cursor.getCount() != 0) {
                    cursor.moveToFirst();
                    String pathname = cursor.getString(cursor.getColumnIndex("_data"));
                    if (pathname != null) {
                        encodedImage = getEncodedImage(new FileInputStream(pathname), getLength(pathname));
                        cursor.close();
                    } else {
                        cursor.close();
                    }
                }
            } finally {
                cursor.close();
            }
        }
        return encodedImage;
    }

    private static int getLength(String pathname) {
        return pathname == null ? -1 : (int) new File(pathname).length();
    }

    protected String getProducerName() {
        return PRODUCER_NAME;
    }
}
