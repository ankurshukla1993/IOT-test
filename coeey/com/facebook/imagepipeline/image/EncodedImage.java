package com.facebook.imagepipeline.image;

import android.util.Pair;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.Supplier;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.common.references.CloseableReference;
import com.facebook.common.references.SharedReference;
import com.facebook.imageformat.ImageFormat;
import com.facebook.imageformat.ImageFormatChecker;
import com.facebook.imagepipeline.memory.PooledByteBuffer;
import com.facebook.imagepipeline.memory.PooledByteBufferInputStream;
import com.facebook.imageutils.BitmapUtil;
import com.facebook.imageutils.JfifUtil;
import java.io.Closeable;
import java.io.FileInputStream;
import java.io.InputStream;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

@Immutable
public class EncodedImage implements Closeable {
    public static final int DEFAULT_SAMPLE_SIZE = 1;
    public static final int UNKNOWN_HEIGHT = -1;
    public static final int UNKNOWN_ROTATION_ANGLE = -1;
    public static final int UNKNOWN_STREAM_SIZE = -1;
    public static final int UNKNOWN_WIDTH = -1;
    private int mHeight;
    private ImageFormat mImageFormat;
    @Nullable
    private final Supplier<FileInputStream> mInputStreamSupplier;
    @Nullable
    private final CloseableReference<PooledByteBuffer> mPooledByteBufferRef;
    private int mRotationAngle;
    private int mSampleSize;
    private int mStreamSize;
    private int mWidth;

    public EncodedImage(CloseableReference<PooledByteBuffer> pooledByteBufferRef) {
        this.mImageFormat = ImageFormat.UNKNOWN;
        this.mRotationAngle = -1;
        this.mWidth = -1;
        this.mHeight = -1;
        this.mSampleSize = 1;
        this.mStreamSize = -1;
        Preconditions.checkArgument(CloseableReference.isValid(pooledByteBufferRef));
        this.mPooledByteBufferRef = pooledByteBufferRef.clone();
        this.mInputStreamSupplier = null;
    }

    public EncodedImage(Supplier<FileInputStream> inputStreamSupplier) {
        this.mImageFormat = ImageFormat.UNKNOWN;
        this.mRotationAngle = -1;
        this.mWidth = -1;
        this.mHeight = -1;
        this.mSampleSize = 1;
        this.mStreamSize = -1;
        Preconditions.checkNotNull(inputStreamSupplier);
        this.mPooledByteBufferRef = null;
        this.mInputStreamSupplier = inputStreamSupplier;
    }

    public EncodedImage(Supplier<FileInputStream> inputStreamSupplier, int streamSize) {
        this((Supplier) inputStreamSupplier);
        this.mStreamSize = streamSize;
    }

    public static EncodedImage cloneOrNull(EncodedImage encodedImage) {
        return encodedImage != null ? encodedImage.cloneOrNull() : null;
    }

    public EncodedImage cloneOrNull() {
        EncodedImage encodedImage;
        if (this.mInputStreamSupplier != null) {
            encodedImage = new EncodedImage(this.mInputStreamSupplier, this.mStreamSize);
        } else {
            CloseableReference pooledByteBufferRef = CloseableReference.cloneOrNull(this.mPooledByteBufferRef);
            if (pooledByteBufferRef == null) {
                encodedImage = null;
            } else {
                try {
                    encodedImage = new EncodedImage(pooledByteBufferRef);
                } catch (Throwable th) {
                    CloseableReference.closeSafely(pooledByteBufferRef);
                }
            }
            CloseableReference.closeSafely(pooledByteBufferRef);
        }
        if (encodedImage != null) {
            encodedImage.copyMetaDataFrom(this);
        }
        return encodedImage;
    }

    public void close() {
        CloseableReference.closeSafely(this.mPooledByteBufferRef);
    }

    public synchronized boolean isValid() {
        boolean z;
        z = CloseableReference.isValid(this.mPooledByteBufferRef) || this.mInputStreamSupplier != null;
        return z;
    }

    public CloseableReference<PooledByteBuffer> getByteBufferRef() {
        return CloseableReference.cloneOrNull(this.mPooledByteBufferRef);
    }

    public InputStream getInputStream() {
        if (this.mInputStreamSupplier != null) {
            return (InputStream) this.mInputStreamSupplier.get();
        }
        CloseableReference pooledByteBufferRef = CloseableReference.cloneOrNull(this.mPooledByteBufferRef);
        if (pooledByteBufferRef == null) {
            return null;
        }
        try {
            InputStream pooledByteBufferInputStream = new PooledByteBufferInputStream((PooledByteBuffer) pooledByteBufferRef.get());
            return pooledByteBufferInputStream;
        } finally {
            CloseableReference.closeSafely(pooledByteBufferRef);
        }
    }

    public void setImageFormat(ImageFormat imageFormat) {
        this.mImageFormat = imageFormat;
    }

    public void setHeight(int height) {
        this.mHeight = height;
    }

    public void setWidth(int width) {
        this.mWidth = width;
    }

    public void setRotationAngle(int rotationAngle) {
        this.mRotationAngle = rotationAngle;
    }

    public void setSampleSize(int sampleSize) {
        this.mSampleSize = sampleSize;
    }

    public void setStreamSize(int streamSize) {
        this.mStreamSize = streamSize;
    }

    public ImageFormat getImageFormat() {
        return this.mImageFormat;
    }

    public int getRotationAngle() {
        return this.mRotationAngle;
    }

    public int getWidth() {
        return this.mWidth;
    }

    public int getHeight() {
        return this.mHeight;
    }

    public int getSampleSize() {
        return this.mSampleSize;
    }

    public boolean isCompleteAt(int length) {
        if (this.mImageFormat != ImageFormat.JPEG || this.mInputStreamSupplier != null) {
            return true;
        }
        Preconditions.checkNotNull(this.mPooledByteBufferRef);
        PooledByteBuffer buf = (PooledByteBuffer) this.mPooledByteBufferRef.get();
        if (buf.read(length - 2) == (byte) -1 && buf.read(length - 1) == (byte) -39) {
            return true;
        }
        return false;
    }

    public int getSize() {
        if (this.mPooledByteBufferRef == null || this.mPooledByteBufferRef.get() == null) {
            return this.mStreamSize;
        }
        return ((PooledByteBuffer) this.mPooledByteBufferRef.get()).size();
    }

    public void parseMetaData() {
        ImageFormat imageFormat = ImageFormatChecker.getImageFormat_WrapIOException(getInputStream());
        this.mImageFormat = imageFormat;
        if (!ImageFormat.isWebpFormat(imageFormat)) {
            Pair<Integer, Integer> dimensions = BitmapUtil.decodeDimensions(getInputStream());
            if (dimensions != null) {
                this.mWidth = ((Integer) dimensions.first).intValue();
                this.mHeight = ((Integer) dimensions.second).intValue();
                if (imageFormat != ImageFormat.JPEG) {
                    this.mRotationAngle = 0;
                } else if (this.mRotationAngle == -1) {
                    this.mRotationAngle = JfifUtil.getAutoRotateAngleFromOrientation(JfifUtil.getOrientation(getInputStream()));
                }
            }
        }
    }

    public void copyMetaDataFrom(EncodedImage encodedImage) {
        this.mImageFormat = encodedImage.getImageFormat();
        this.mWidth = encodedImage.getWidth();
        this.mHeight = encodedImage.getHeight();
        this.mRotationAngle = encodedImage.getRotationAngle();
        this.mSampleSize = encodedImage.getSampleSize();
        this.mStreamSize = encodedImage.getSize();
    }

    public static boolean isMetaDataAvailable(EncodedImage encodedImage) {
        return encodedImage.mRotationAngle >= 0 && encodedImage.mWidth >= 0 && encodedImage.mHeight >= 0;
    }

    public static void closeSafely(@Nullable EncodedImage encodedImage) {
        if (encodedImage != null) {
            encodedImage.close();
        }
    }

    public static boolean isValid(@Nullable EncodedImage encodedImage) {
        return encodedImage != null && encodedImage.isValid();
    }

    @VisibleForTesting
    public synchronized SharedReference<PooledByteBuffer> getUnderlyingReferenceTestOnly() {
        return this.mPooledByteBufferRef != null ? this.mPooledByteBufferRef.getUnderlyingReferenceTestOnly() : null;
    }
}
