package com.facebook.imagepipeline.request;

import android.graphics.Bitmap;
import com.facebook.cache.common.CacheKey;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.bitmaps.PlatformBitmapFactory;
import com.facebook.imagepipeline.nativecode.Bitmaps;
import javax.annotation.Nullable;

public abstract class BasePostprocessor implements Postprocessor {
    public String getName() {
        return "Unknown postprocessor";
    }

    public CloseableReference<Bitmap> process(Bitmap sourceBitmap, PlatformBitmapFactory bitmapFactory) {
        CloseableReference destBitmapRef = bitmapFactory.createBitmap(sourceBitmap.getWidth(), sourceBitmap.getHeight(), sourceBitmap.getConfig());
        try {
            process((Bitmap) destBitmapRef.get(), sourceBitmap);
            CloseableReference<Bitmap> cloneOrNull = CloseableReference.cloneOrNull(destBitmapRef);
            return cloneOrNull;
        } finally {
            CloseableReference.closeSafely(destBitmapRef);
        }
    }

    public void process(Bitmap destBitmap, Bitmap sourceBitmap) {
        Bitmaps.copyBitmap(destBitmap, sourceBitmap);
        process(destBitmap);
    }

    public void process(Bitmap bitmap) {
    }

    @Nullable
    public CacheKey getPostprocessorCacheKey() {
        return null;
    }
}
