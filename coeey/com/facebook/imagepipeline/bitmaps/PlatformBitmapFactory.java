package com.facebook.imagepipeline.bitmaps;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import com.facebook.common.references.CloseableReference;

public abstract class PlatformBitmapFactory {
    public abstract CloseableReference<Bitmap> createBitmap(int i, int i2, Config config);

    public CloseableReference<Bitmap> createBitmap(int width, int height) {
        return createBitmap(width, height, Config.ARGB_8888);
    }
}
