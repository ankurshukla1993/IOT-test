package com.facebook.imagepipeline.bitmaps;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import com.facebook.common.references.CloseableReference;

public class GingerbreadBitmapFactory extends PlatformBitmapFactory {
    public CloseableReference<Bitmap> createBitmap(int width, int height, Config bitmapConfig) {
        return CloseableReference.of(Bitmap.createBitmap(width, height, bitmapConfig), SimpleBitmapReleaser.getInstance());
    }
}
