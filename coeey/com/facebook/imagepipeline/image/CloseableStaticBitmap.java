package com.facebook.imagepipeline.image;

import android.graphics.Bitmap;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.references.CloseableReference;
import com.facebook.common.references.ResourceReleaser;
import com.facebook.imageutils.BitmapUtil;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public class CloseableStaticBitmap extends CloseableBitmap {
    private volatile Bitmap mBitmap;
    @GuardedBy("this")
    private CloseableReference<Bitmap> mBitmapReference;
    private final QualityInfo mQualityInfo;
    private final int mRotationAngle;

    public CloseableStaticBitmap(Bitmap bitmap, ResourceReleaser<Bitmap> resourceReleaser, QualityInfo qualityInfo, int rotationAngle) {
        this.mBitmap = (Bitmap) Preconditions.checkNotNull(bitmap);
        this.mBitmapReference = CloseableReference.of(this.mBitmap, (ResourceReleaser) Preconditions.checkNotNull(resourceReleaser));
        this.mQualityInfo = qualityInfo;
        this.mRotationAngle = rotationAngle;
    }

    public CloseableStaticBitmap(CloseableReference<Bitmap> bitmapReference, QualityInfo qualityInfo, int rotationAngle) {
        this.mBitmapReference = (CloseableReference) Preconditions.checkNotNull(bitmapReference.cloneOrNull());
        this.mBitmap = (Bitmap) this.mBitmapReference.get();
        this.mQualityInfo = qualityInfo;
        this.mRotationAngle = rotationAngle;
    }

    public void close() {
        CloseableReference<Bitmap> reference = detachBitmapReference();
        if (reference != null) {
            reference.close();
        }
    }

    private synchronized CloseableReference<Bitmap> detachBitmapReference() {
        CloseableReference<Bitmap> reference;
        reference = this.mBitmapReference;
        this.mBitmapReference = null;
        this.mBitmap = null;
        return reference;
    }

    public synchronized CloseableReference<Bitmap> convertToBitmapReference() {
        Preconditions.checkNotNull(this.mBitmapReference, "Cannot convert a closed static bitmap");
        return detachBitmapReference();
    }

    public synchronized boolean isClosed() {
        return this.mBitmapReference == null;
    }

    public Bitmap getUnderlyingBitmap() {
        return this.mBitmap;
    }

    public int getSizeInBytes() {
        return BitmapUtil.getSizeInBytes(this.mBitmap);
    }

    public int getWidth() {
        Bitmap bitmap = this.mBitmap;
        return bitmap == null ? 0 : bitmap.getWidth();
    }

    public int getHeight() {
        Bitmap bitmap = this.mBitmap;
        return bitmap == null ? 0 : bitmap.getHeight();
    }

    public int getRotationAngle() {
        return this.mRotationAngle;
    }

    public QualityInfo getQualityInfo() {
        return this.mQualityInfo;
    }
}
