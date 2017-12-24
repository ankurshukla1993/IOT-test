package com.facebook.imagepipeline.platform;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.support.v4.util.Pools.SynchronizedPool;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.common.references.CloseableReference;
import com.facebook.common.streams.LimitedInputStream;
import com.facebook.common.streams.TailAppendingInputStream;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.memory.BitmapPool;
import com.facebook.imageutils.BitmapUtil;
import java.io.InputStream;
import java.nio.ByteBuffer;
import javax.annotation.concurrent.ThreadSafe;

@TargetApi(21)
@ThreadSafe
public class ArtDecoder implements PlatformDecoder {
    private static final int DECODE_BUFFER_SIZE = 16384;
    private static final byte[] EOI_TAIL = new byte[]{(byte) -1, (byte) -39};
    private final BitmapPool mBitmapPool;
    @VisibleForTesting
    final SynchronizedPool<ByteBuffer> mDecodeBuffers;

    public ArtDecoder(BitmapPool bitmapPool, int maxNumThreads, SynchronizedPool decodeBuffers) {
        this.mBitmapPool = bitmapPool;
        this.mDecodeBuffers = decodeBuffers;
        for (int i = 0; i < maxNumThreads; i++) {
            this.mDecodeBuffers.release(ByteBuffer.allocate(16384));
        }
    }

    public CloseableReference<Bitmap> decodeFromEncodedImage(EncodedImage encodedImage, Config bitmapConfig) {
        Options options = getDecodeOptionsForStream(encodedImage, bitmapConfig);
        boolean retryOnFail = options.inPreferredConfig != Config.ARGB_8888;
        try {
            return decodeStaticImageFromStream(encodedImage.getInputStream(), options);
        } catch (RuntimeException re) {
            if (retryOnFail) {
                return decodeFromEncodedImage(encodedImage, Config.ARGB_8888);
            }
            throw re;
        }
    }

    public CloseableReference<Bitmap> decodeJPEGFromEncodedImage(EncodedImage encodedImage, Config bitmapConfig, int length) {
        InputStream jpegDataStream;
        boolean isJpegComplete = encodedImage.isCompleteAt(length);
        Options options = getDecodeOptionsForStream(encodedImage, bitmapConfig);
        InputStream jpegDataStream2 = encodedImage.getInputStream();
        Preconditions.checkNotNull(jpegDataStream2);
        if (encodedImage.getSize() > length) {
            jpegDataStream = new LimitedInputStream(jpegDataStream2, length);
        } else {
            jpegDataStream = jpegDataStream2;
        }
        if (isJpegComplete) {
            jpegDataStream2 = jpegDataStream;
        } else {
            jpegDataStream2 = new TailAppendingInputStream(jpegDataStream, EOI_TAIL);
        }
        boolean retryOnFail = options.inPreferredConfig != Config.ARGB_8888;
        try {
            return decodeStaticImageFromStream(jpegDataStream2, options);
        } catch (RuntimeException re) {
            if (retryOnFail) {
                return decodeFromEncodedImage(encodedImage, Config.ARGB_8888);
            }
            throw re;
        }
    }

    protected CloseableReference<Bitmap> decodeStaticImageFromStream(InputStream inputStream, Options options) {
        Preconditions.checkNotNull(inputStream);
        Bitmap bitmapToReuse = (Bitmap) this.mBitmapPool.get(BitmapUtil.getSizeInByteForBitmap(options.outWidth, options.outHeight, options.inPreferredConfig));
        if (bitmapToReuse == null) {
            throw new NullPointerException("BitmapPool.get returned null");
        }
        options.inBitmap = bitmapToReuse;
        ByteBuffer byteBuffer = (ByteBuffer) this.mDecodeBuffers.acquire();
        if (byteBuffer == null) {
            byteBuffer = ByteBuffer.allocate(16384);
        }
        try {
            options.inTempStorage = byteBuffer.array();
            Bitmap decodedBitmap = BitmapFactory.decodeStream(inputStream, null, options);
            this.mDecodeBuffers.release(byteBuffer);
            if (bitmapToReuse == decodedBitmap) {
                return CloseableReference.of(decodedBitmap, this.mBitmapPool);
            }
            this.mBitmapPool.release(bitmapToReuse);
            decodedBitmap.recycle();
            throw new IllegalStateException();
        } catch (RuntimeException re) {
            this.mBitmapPool.release(bitmapToReuse);
            throw re;
        } catch (Throwable th) {
            this.mDecodeBuffers.release(byteBuffer);
        }
    }

    private static Options getDecodeOptionsForStream(EncodedImage encodedImage, Config bitmapConfig) {
        Options options = new Options();
        options.inSampleSize = encodedImage.getSampleSize();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(encodedImage.getInputStream(), null, options);
        if (options.outWidth == -1 || options.outHeight == -1) {
            throw new IllegalArgumentException();
        }
        options.inJustDecodeBounds = false;
        options.inDither = true;
        options.inPreferredConfig = bitmapConfig;
        options.inMutable = true;
        return options;
    }
}
