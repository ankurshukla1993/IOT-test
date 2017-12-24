package com.facebook.imagepipeline.memory;

import android.graphics.Bitmap;
import android.os.Build.VERSION;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.Throwables;
import com.facebook.common.references.CloseableReference;
import com.facebook.common.references.ResourceReleaser;
import com.facebook.imagepipeline.common.TooManyBitmapsException;
import com.facebook.imagepipeline.nativecode.Bitmaps;
import com.facebook.imageutils.BitmapUtil;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.concurrent.GuardedBy;

public class BitmapCounter {
    @GuardedBy("this")
    private int mCount;
    private final int mMaxCount;
    private final int mMaxSize;
    @GuardedBy("this")
    private long mSize;
    private final ResourceReleaser<Bitmap> mUnpooledBitmapsReleaser;

    class C11691 implements ResourceReleaser<Bitmap> {
        C11691() {
        }

        public void release(Bitmap value) {
            try {
                BitmapCounter.this.decrease(value);
            } finally {
                value.recycle();
            }
        }
    }

    public BitmapCounter(int maxCount, int maxSize) {
        boolean z = true;
        Preconditions.checkArgument(maxCount > 0);
        if (maxSize <= 0) {
            z = false;
        }
        Preconditions.checkArgument(z);
        this.mMaxCount = maxCount;
        this.mMaxSize = maxSize;
        this.mUnpooledBitmapsReleaser = new C11691();
    }

    public synchronized boolean increase(Bitmap bitmap) {
        boolean z;
        int bitmapSize = BitmapUtil.getSizeInBytes(bitmap);
        if (this.mCount >= this.mMaxCount || this.mSize + ((long) bitmapSize) > ((long) this.mMaxSize)) {
            z = false;
        } else {
            this.mCount++;
            this.mSize += (long) bitmapSize;
            z = true;
        }
        return z;
    }

    public synchronized void decrease(Bitmap bitmap) {
        boolean z = true;
        synchronized (this) {
            int bitmapSize = BitmapUtil.getSizeInBytes(bitmap);
            Preconditions.checkArgument(this.mCount > 0, "No bitmaps registered.");
            if (((long) bitmapSize) > this.mSize) {
                z = false;
            }
            Preconditions.checkArgument(z, "Bitmap size bigger than the total registered size: %d, %d", Integer.valueOf(bitmapSize), Long.valueOf(this.mSize));
            this.mSize -= (long) bitmapSize;
            this.mCount--;
        }
    }

    public synchronized int getCount() {
        return this.mCount;
    }

    public synchronized long getSize() {
        return this.mSize;
    }

    public ResourceReleaser<Bitmap> getReleaser() {
        return this.mUnpooledBitmapsReleaser;
    }

    public List<CloseableReference<Bitmap>> associateBitmapsWithBitmapCounter(List<Bitmap> bitmaps) {
        Bitmap bitmap;
        int countedBitmaps = 0;
        while (countedBitmaps < bitmaps.size()) {
            try {
                bitmap = (Bitmap) bitmaps.get(countedBitmaps);
                if (VERSION.SDK_INT < 21) {
                    Bitmaps.pinBitmap(bitmap);
                }
                if (increase(bitmap)) {
                    countedBitmaps++;
                } else {
                    throw new TooManyBitmapsException();
                }
            } catch (Exception exception) {
                if (bitmaps != null) {
                    for (Bitmap bitmap2 : bitmaps) {
                        int countedBitmaps2 = countedBitmaps - 1;
                        if (countedBitmaps > 0) {
                            decrease(bitmap2);
                        }
                        bitmap2.recycle();
                        countedBitmaps = countedBitmaps2;
                    }
                }
                throw Throwables.propagate(exception);
            }
        }
        List<CloseableReference<Bitmap>> ret = new ArrayList();
        for (Bitmap bitmap22 : bitmaps) {
            ret.add(CloseableReference.of(bitmap22, this.mUnpooledBitmapsReleaser));
        }
        return ret;
    }
}
