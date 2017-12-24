package com.facebook.imagepipeline.platform;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory.Options;
import android.os.Build.VERSION;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.Throwables;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.common.TooManyBitmapsException;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.memory.BitmapCounter;
import com.facebook.imagepipeline.memory.BitmapCounterProvider;
import com.facebook.imagepipeline.memory.PooledByteBuffer;
import com.facebook.imagepipeline.nativecode.Bitmaps;

abstract class DalvikPurgeableDecoder implements PlatformDecoder {
    protected static final byte[] EOI = new byte[]{(byte) -1, (byte) -39};
    private final BitmapCounter mUnpooledBitmapsCounter = BitmapCounterProvider.get();

    abstract Bitmap decodeByteArrayAsPurgeable(CloseableReference<PooledByteBuffer> closeableReference, Options options);

    abstract Bitmap decodeJPEGByteArrayAsPurgeable(CloseableReference<PooledByteBuffer> closeableReference, int i, Options options);

    DalvikPurgeableDecoder() {
    }

    public CloseableReference<Bitmap> decodeFromEncodedImage(EncodedImage encodedImage, Config bitmapConfig) {
        Options options = getBitmapFactoryOptions(encodedImage.getSampleSize(), bitmapConfig);
        CloseableReference<PooledByteBuffer> bytesRef = encodedImage.getByteBufferRef();
        Preconditions.checkNotNull(bytesRef);
        try {
            CloseableReference<Bitmap> pinBitmap = pinBitmap(decodeByteArrayAsPurgeable(bytesRef, options));
            return pinBitmap;
        } finally {
            CloseableReference.closeSafely(bytesRef);
        }
    }

    public CloseableReference<Bitmap> decodeJPEGFromEncodedImage(EncodedImage encodedImage, Config bitmapConfig, int length) {
        Options options = getBitmapFactoryOptions(encodedImage.getSampleSize(), bitmapConfig);
        CloseableReference<PooledByteBuffer> bytesRef = encodedImage.getByteBufferRef();
        Preconditions.checkNotNull(bytesRef);
        try {
            CloseableReference<Bitmap> pinBitmap = pinBitmap(decodeJPEGByteArrayAsPurgeable(bytesRef, length, options));
            return pinBitmap;
        } finally {
            CloseableReference.closeSafely(bytesRef);
        }
    }

    private static Options getBitmapFactoryOptions(int sampleSize, Config bitmapConfig) {
        Options options = new Options();
        options.inDither = true;
        options.inPreferredConfig = bitmapConfig;
        options.inPurgeable = true;
        options.inInputShareable = true;
        options.inSampleSize = sampleSize;
        if (VERSION.SDK_INT >= 11) {
            options.inMutable = true;
        }
        return options;
    }

    protected static boolean endsWithEOI(CloseableReference<PooledByteBuffer> bytesRef, int length) {
        PooledByteBuffer buffer = (PooledByteBuffer) bytesRef.get();
        return length >= 2 && buffer.read(length - 2) == (byte) -1 && buffer.read(length - 1) == (byte) -39;
    }

    public CloseableReference<Bitmap> pinBitmap(Bitmap bitmap) {
        try {
            Bitmaps.pinBitmap(bitmap);
            if (this.mUnpooledBitmapsCounter.increase(bitmap)) {
                return CloseableReference.of(bitmap, this.mUnpooledBitmapsCounter.getReleaser());
            }
            bitmap.recycle();
            throw new TooManyBitmapsException();
        } catch (Exception e) {
            bitmap.recycle();
            throw Throwables.propagate(e);
        }
    }
}
