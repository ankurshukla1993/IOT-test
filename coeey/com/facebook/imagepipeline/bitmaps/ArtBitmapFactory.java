package com.facebook.imagepipeline.bitmaps;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.memory.BitmapPool;
import com.facebook.imagepipeline.nativecode.Bitmaps;
import com.facebook.imageutils.BitmapUtil;
import javax.annotation.concurrent.ThreadSafe;

@TargetApi(21)
@ThreadSafe
public class ArtBitmapFactory extends PlatformBitmapFactory {
    private final BitmapPool mBitmapPool;

    public ArtBitmapFactory(BitmapPool bitmapPool) {
        this.mBitmapPool = bitmapPool;
    }

    public CloseableReference<Bitmap> createBitmap(int width, int height, Config bitmapConfig) {
        Bitmap bitmap = (Bitmap) this.mBitmapPool.get(BitmapUtil.getSizeInByteForBitmap(width, height, bitmapConfig));
        Bitmaps.reconfigureBitmap(bitmap, width, height, bitmapConfig);
        return CloseableReference.of(bitmap, this.mBitmapPool);
    }
}
