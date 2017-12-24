package com.facebook.imagepipeline.producers;

import android.content.ContentResolver;
import android.database.Cursor;
import android.graphics.Rect;
import android.media.ExifInterface;
import android.net.Uri;
import android.provider.MediaStore.Images.Thumbnails;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.common.logging.FLog;
import com.facebook.common.util.UriUtil;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.memory.BitmapCounterProvider;
import com.facebook.imagepipeline.memory.PooledByteBufferFactory;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imageutils.JfifUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;

public class LocalContentUriThumbnailFetchProducer extends LocalFetchProducer implements ThumbnailProducer<EncodedImage> {
    private static final Rect MICRO_THUMBNAIL_DIMENSIONS = new Rect(0, 0, 96, 96);
    private static final Rect MINI_THUMBNAIL_DIMENSIONS = new Rect(0, 0, 512, BitmapCounterProvider.MAX_BITMAP_COUNT);
    private static final int NO_THUMBNAIL = 0;
    @VisibleForTesting
    static final String PRODUCER_NAME = "LocalContentUriThumbnailFetchProducer";
    private static final String[] PROJECTION = new String[]{"_id", "_data"};
    private static final Class<?> TAG = LocalContentUriThumbnailFetchProducer.class;
    private static final String[] THUMBNAIL_PROJECTION = new String[]{"_data"};
    private final ContentResolver mContentResolver;

    public LocalContentUriThumbnailFetchProducer(Executor executor, PooledByteBufferFactory pooledByteBufferFactory, ContentResolver contentResolver, boolean decodeFileDescriptorEnabled) {
        super(executor, pooledByteBufferFactory, decodeFileDescriptorEnabled);
        this.mContentResolver = contentResolver;
    }

    public boolean canProvideImageForSize(ResizeOptions resizeOptions) {
        return ThumbnailSizeChecker.isImageBigEnough(MINI_THUMBNAIL_DIMENSIONS.width(), MINI_THUMBNAIL_DIMENSIONS.height(), resizeOptions);
    }

    protected EncodedImage getEncodedImage(ImageRequest imageRequest) throws IOException {
        Uri uri = imageRequest.getSourceUri();
        if (UriUtil.isLocalCameraUri(uri)) {
            EncodedImage cameraImage = getCameraImage(uri, imageRequest.getResizeOptions());
            if (cameraImage != null) {
                return cameraImage;
            }
        }
        return null;
    }

    @Nullable
    private EncodedImage getCameraImage(Uri uri, ResizeOptions resizeOptions) throws IOException {
        Cursor cursor = this.mContentResolver.query(uri, PROJECTION, null, null, null);
        if (cursor == null) {
            return null;
        }
        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            String pathname = cursor.getString(cursor.getColumnIndex("_data"));
            if (resizeOptions != null) {
                EncodedImage thumbnail = getThumbnail(resizeOptions, cursor.getInt(cursor.getColumnIndex("_id")));
                if (thumbnail != null) {
                    thumbnail.setRotationAngle(getRotationAngle(pathname));
                    cursor.close();
                    return thumbnail;
                }
            }
            cursor.close();
            return null;
        } finally {
            cursor.close();
        }
    }

    private EncodedImage getThumbnail(ResizeOptions resizeOptions, int imageId) throws IOException {
        EncodedImage encodedImage = null;
        int thumbnailKind = getThumbnailKind(resizeOptions);
        if (thumbnailKind != 0) {
            Cursor thumbnailCursor = null;
            try {
                thumbnailCursor = Thumbnails.queryMiniThumbnail(this.mContentResolver, (long) imageId, thumbnailKind, THUMBNAIL_PROJECTION);
                if (thumbnailCursor != null) {
                    thumbnailCursor.moveToFirst();
                    if (thumbnailCursor.getCount() > 0) {
                        String thumbnailUri = thumbnailCursor.getString(thumbnailCursor.getColumnIndex("_data"));
                        if (new File(thumbnailUri).exists()) {
                            encodedImage = getEncodedImage(new FileInputStream(thumbnailUri), getLength(thumbnailUri));
                            if (thumbnailCursor != null) {
                                thumbnailCursor.close();
                            }
                        }
                    }
                    if (thumbnailCursor != null) {
                        thumbnailCursor.close();
                    }
                } else if (thumbnailCursor != null) {
                    thumbnailCursor.close();
                }
            } catch (Throwable th) {
                if (thumbnailCursor != null) {
                    thumbnailCursor.close();
                }
            }
        }
        return encodedImage;
    }

    private static int getThumbnailKind(ResizeOptions resizeOptions) {
        if (ThumbnailSizeChecker.isImageBigEnough(MICRO_THUMBNAIL_DIMENSIONS.width(), MICRO_THUMBNAIL_DIMENSIONS.height(), resizeOptions)) {
            return 3;
        }
        if (ThumbnailSizeChecker.isImageBigEnough(MINI_THUMBNAIL_DIMENSIONS.width(), MINI_THUMBNAIL_DIMENSIONS.height(), resizeOptions)) {
            return 1;
        }
        return 0;
    }

    private static int getLength(String pathname) {
        return pathname == null ? -1 : (int) new File(pathname).length();
    }

    protected String getProducerName() {
        return PRODUCER_NAME;
    }

    private static int getRotationAngle(String pathname) {
        int i = 0;
        if (pathname != null) {
            try {
                i = JfifUtil.getAutoRotateAngleFromOrientation(new ExifInterface(pathname).getAttributeInt("Orientation", 1));
            } catch (IOException ioe) {
                FLog.e(TAG, ioe, "Unable to retrieve thumbnail rotation for %s", new Object[]{pathname});
            }
        }
        return i;
    }
}
