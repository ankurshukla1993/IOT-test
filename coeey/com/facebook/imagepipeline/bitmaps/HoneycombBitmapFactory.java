package com.facebook.imagepipeline.bitmaps;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import com.facebook.common.references.CloseableReference;
import com.facebook.imageformat.ImageFormat;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.memory.PooledByteBuffer;
import com.facebook.imagepipeline.platform.PlatformDecoder;
import javax.annotation.concurrent.ThreadSafe;

@TargetApi(11)
@ThreadSafe
public class HoneycombBitmapFactory extends PlatformBitmapFactory {
    private final EmptyJpegGenerator mJpegGenerator;
    private final PlatformDecoder mPurgeableDecoder;

    public HoneycombBitmapFactory(EmptyJpegGenerator jpegGenerator, PlatformDecoder purgeableDecoder) {
        this.mJpegGenerator = jpegGenerator;
        this.mPurgeableDecoder = purgeableDecoder;
    }

    public CloseableReference<Bitmap> createBitmap(int width, int height, Config bitmapConfig) {
        EncodedImage encodedImage;
        CloseableReference<PooledByteBuffer> jpgRef = this.mJpegGenerator.generate((short) width, (short) height);
        try {
            encodedImage = new EncodedImage(jpgRef);
            encodedImage.setImageFormat(ImageFormat.JPEG);
            CloseableReference<Bitmap> bitmapRef = this.mPurgeableDecoder.decodeJPEGFromEncodedImage(encodedImage, bitmapConfig, ((PooledByteBuffer) jpgRef.get()).size());
            ((Bitmap) bitmapRef.get()).eraseColor(0);
            EncodedImage.closeSafely(encodedImage);
            jpgRef.close();
            return bitmapRef;
        } catch (Throwable th) {
            jpgRef.close();
        }
    }
}
