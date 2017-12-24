package com.facebook.imagepipeline.memory;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.memory.MemoryTrimmableRegistry;
import javax.annotation.concurrent.ThreadSafe;

@TargetApi(21)
@ThreadSafe
public class BitmapPool extends BasePool<Bitmap> {
    public BitmapPool(MemoryTrimmableRegistry memoryTrimmableRegistry, PoolParams poolParams, PoolStatsTracker poolStatsTracker) {
        super(memoryTrimmableRegistry, poolParams, poolStatsTracker);
        initialize();
    }

    protected Bitmap alloc(int size) {
        return Bitmap.createBitmap(1, (int) Math.ceil(((double) size) / 2.0d), Config.RGB_565);
    }

    protected void free(Bitmap value) {
        Preconditions.checkNotNull(value);
        value.recycle();
    }

    protected int getBucketedSize(int requestSize) {
        return requestSize;
    }

    protected int getBucketedSizeForValue(Bitmap value) {
        Preconditions.checkNotNull(value);
        return value.getAllocationByteCount();
    }

    protected int getSizeInBytes(int bucketedSize) {
        return bucketedSize;
    }

    protected boolean isReusable(Bitmap value) {
        Preconditions.checkNotNull(value);
        return !value.isRecycled() && value.isMutable();
    }
}
