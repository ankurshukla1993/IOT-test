package com.facebook.imagepipeline.producers;

import android.content.ContentResolver;
import android.media.ExifInterface;
import android.net.Uri;
import android.util.Pair;
import com.facebook.common.internal.ImmutableMap;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.common.references.CloseableReference;
import com.facebook.imageformat.ImageFormat;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.memory.PooledByteBuffer;
import com.facebook.imagepipeline.memory.PooledByteBufferFactory;
import com.facebook.imagepipeline.memory.PooledByteBufferInputStream;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imageutils.BitmapUtil;
import com.facebook.imageutils.JfifUtil;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.Executor;

public class LocalExifThumbnailProducer implements ThumbnailProducer<EncodedImage> {
    private static final int COMMON_EXIF_THUMBNAIL_MAX_DIMENSION = 512;
    @VisibleForTesting
    static final String CREATED_THUMBNAIL = "createdThumbnail";
    @VisibleForTesting
    static final String PRODUCER_NAME = "LocalExifThumbnailProducer";
    private final ContentResolver mContentResolver;
    private final Executor mExecutor;
    private final PooledByteBufferFactory mPooledByteBufferFactory;

    private java.lang.String getRealPathFromUri(android.net.Uri r10) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find block by offset: 0x0027 in list [B:9:0x0024]
	at jadx.core.utils.BlockUtils.getBlockByOffset(BlockUtils.java:42)
	at jadx.core.dex.instructions.IfNode.initBlocks(IfNode.java:60)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.initBlocksInIfNodes(BlockFinish.java:48)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.visit(BlockFinish.java:33)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
*/
        /*
        r9 = this;
        r8 = 0;
        r0 = com.facebook.common.util.UriUtil.isLocalContentUri(r10);
        if (r0 == 0) goto L_0x002f;
    L_0x0007:
        r6 = 0;
        r0 = r9.mContentResolver;	 Catch:{ all -> 0x0028 }
        r2 = 0;	 Catch:{ all -> 0x0028 }
        r3 = 0;	 Catch:{ all -> 0x0028 }
        r4 = 0;	 Catch:{ all -> 0x0028 }
        r5 = 0;	 Catch:{ all -> 0x0028 }
        r1 = r10;	 Catch:{ all -> 0x0028 }
        r6 = r0.query(r1, r2, r3, r4, r5);	 Catch:{ all -> 0x0028 }
        if (r6 == 0) goto L_0x0022;	 Catch:{ all -> 0x0028 }
    L_0x0015:
        r6.moveToFirst();	 Catch:{ all -> 0x0028 }
        r0 = "_data";	 Catch:{ all -> 0x0028 }
        r7 = r6.getColumnIndex(r0);	 Catch:{ all -> 0x0028 }
        r8 = r6.getString(r7);	 Catch:{ all -> 0x0028 }
    L_0x0022:
        if (r6 == 0) goto L_0x0027;
    L_0x0024:
        r6.close();
    L_0x0027:
        return r8;
    L_0x0028:
        r0 = move-exception;
        if (r6 == 0) goto L_0x002e;
    L_0x002b:
        r6.close();
    L_0x002e:
        throw r0;
    L_0x002f:
        r0 = com.facebook.common.util.UriUtil.isLocalFileUri(r10);
        if (r0 == 0) goto L_0x0027;
    L_0x0035:
        r8 = r10.getPath();
        goto L_0x0027;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.producers.LocalExifThumbnailProducer.getRealPathFromUri(android.net.Uri):java.lang.String");
    }

    public LocalExifThumbnailProducer(Executor executor, PooledByteBufferFactory pooledByteBufferFactory, ContentResolver contentResolver) {
        this.mExecutor = executor;
        this.mPooledByteBufferFactory = pooledByteBufferFactory;
        this.mContentResolver = contentResolver;
    }

    public boolean canProvideImageForSize(ResizeOptions resizeOptions) {
        return ThumbnailSizeChecker.isImageBigEnough(512, 512, resizeOptions);
    }

    public void produceResults(Consumer<EncodedImage> consumer, ProducerContext producerContext) {
        ProducerListener listener = producerContext.getListener();
        String requestId = producerContext.getId();
        final ImageRequest imageRequest = producerContext.getImageRequest();
        final StatefulProducerRunnable cancellableProducerRunnable = new StatefulProducerRunnable<EncodedImage>(consumer, listener, PRODUCER_NAME, requestId) {
            protected EncodedImage getResult() throws Exception {
                ExifInterface exifInterface = LocalExifThumbnailProducer.this.getExifInterface(imageRequest.getSourceUri());
                if (exifInterface == null || !exifInterface.hasThumbnail()) {
                    return null;
                }
                return LocalExifThumbnailProducer.this.buildEncodedImage(LocalExifThumbnailProducer.this.mPooledByteBufferFactory.newByteBuffer(exifInterface.getThumbnail()), exifInterface);
            }

            protected void disposeResult(EncodedImage result) {
                EncodedImage.closeSafely(result);
            }

            protected Map<String, String> getExtraMapOnSuccess(EncodedImage result) {
                return ImmutableMap.of(LocalExifThumbnailProducer.CREATED_THUMBNAIL, Boolean.toString(result != null));
            }
        };
        producerContext.addCallbacks(new BaseProducerContextCallbacks() {
            public void onCancellationRequested() {
                cancellableProducerRunnable.cancel();
            }
        });
        this.mExecutor.execute(cancellableProducerRunnable);
    }

    @VisibleForTesting
    ExifInterface getExifInterface(Uri uri) throws IOException {
        String realPath = getRealPathFromUri(uri);
        if (canReadAsFile(realPath)) {
            return new ExifInterface(realPath);
        }
        return null;
    }

    private EncodedImage buildEncodedImage(PooledByteBuffer imageBytes, ExifInterface exifInterface) {
        int width;
        int height = -1;
        Pair<Integer, Integer> dimensions = BitmapUtil.decodeDimensions(new PooledByteBufferInputStream(imageBytes));
        int rotationAngle = getRotationAngle(exifInterface);
        if (dimensions != null) {
            width = ((Integer) dimensions.first).intValue();
        } else {
            width = -1;
        }
        if (dimensions != null) {
            height = ((Integer) dimensions.second).intValue();
        }
        CloseableReference<PooledByteBuffer> closeableByteBuffer = CloseableReference.of(imageBytes);
        try {
            EncodedImage encodedImage = new EncodedImage(closeableByteBuffer);
            encodedImage.setImageFormat(ImageFormat.JPEG);
            encodedImage.setRotationAngle(rotationAngle);
            encodedImage.setWidth(width);
            encodedImage.setHeight(height);
            return encodedImage;
        } finally {
            CloseableReference.closeSafely(closeableByteBuffer);
        }
    }

    private int getRotationAngle(ExifInterface exifInterface) {
        return JfifUtil.getAutoRotateAngleFromOrientation(Integer.parseInt(exifInterface.getAttribute("Orientation")));
    }

    @VisibleForTesting
    boolean canReadAsFile(String realPath) throws IOException {
        if (realPath == null) {
            return false;
        }
        File file = new File(realPath);
        if (file.exists() && file.canRead()) {
            return true;
        }
        return false;
    }
}
