package com.facebook.imagepipeline.datasource;

import android.graphics.Bitmap;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.BaseDataSubscriber;
import com.facebook.datasource.DataSource;
import com.facebook.imagepipeline.image.CloseableBitmap;
import com.facebook.imagepipeline.image.CloseableImage;
import javax.annotation.Nullable;

public abstract class BaseBitmapDataSubscriber extends BaseDataSubscriber<CloseableReference<CloseableImage>> {
    protected abstract void onNewResultImpl(@Nullable Bitmap bitmap);

    public void onNewResultImpl(DataSource<CloseableReference<CloseableImage>> dataSource) {
        if (dataSource.isFinished()) {
            CloseableReference<CloseableImage> closeableImageRef = (CloseableReference) dataSource.getResult();
            Bitmap bitmap = null;
            if (closeableImageRef != null && (closeableImageRef.get() instanceof CloseableBitmap)) {
                bitmap = ((CloseableBitmap) closeableImageRef.get()).getUnderlyingBitmap();
            }
            try {
                onNewResultImpl(bitmap);
            } finally {
                CloseableReference.closeSafely(closeableImageRef);
            }
        }
    }
}
