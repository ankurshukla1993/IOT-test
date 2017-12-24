package com.facebook.imagepipeline.decoder;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import com.facebook.common.internal.Closeables;
import com.facebook.common.references.CloseableReference;
import com.facebook.imageformat.GifFormatChecker;
import com.facebook.imageformat.ImageFormat;
import com.facebook.imageformat.ImageFormatChecker;
import com.facebook.imagepipeline.animated.factory.AnimatedImageFactory;
import com.facebook.imagepipeline.common.ImageDecodeOptions;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.image.CloseableStaticBitmap;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.image.ImmutableQualityInfo;
import com.facebook.imagepipeline.image.QualityInfo;
import com.facebook.imagepipeline.platform.PlatformDecoder;
import java.io.InputStream;

public class ImageDecoder {
    private final AnimatedImageFactory mAnimatedImageFactory;
    private final Config mBitmapConfig;
    private final PlatformDecoder mPlatformDecoder;

    public ImageDecoder(AnimatedImageFactory animatedImageFactory, PlatformDecoder platformDecoder, Config bitmapConfig) {
        this.mAnimatedImageFactory = animatedImageFactory;
        this.mBitmapConfig = bitmapConfig;
        this.mPlatformDecoder = platformDecoder;
    }

    public CloseableImage decodeImage(EncodedImage encodedImage, int length, QualityInfo qualityInfo, ImageDecodeOptions options) {
        ImageFormat imageFormat = encodedImage.getImageFormat();
        if (imageFormat == null || imageFormat == ImageFormat.UNKNOWN) {
            imageFormat = ImageFormatChecker.getImageFormat_WrapIOException(encodedImage.getInputStream());
        }
        switch (imageFormat) {
            case UNKNOWN:
                throw new IllegalArgumentException("unknown image format");
            case JPEG:
                return decodeJpeg(encodedImage, length, qualityInfo);
            case GIF:
                return decodeGif(encodedImage, options);
            case WEBP_ANIMATED:
                return decodeAnimatedWebp(encodedImage, options);
            default:
                return decodeStaticImage(encodedImage);
        }
    }

    public CloseableImage decodeGif(EncodedImage encodedImage, ImageDecodeOptions options) {
        InputStream is = encodedImage.getInputStream();
        if (is == null) {
            return null;
        }
        try {
            CloseableImage decodeStaticImage;
            if (options.forceStaticImage || !GifFormatChecker.isAnimated(is)) {
                decodeStaticImage = decodeStaticImage(encodedImage);
                Closeables.closeQuietly(is);
                return decodeStaticImage;
            }
            decodeStaticImage = this.mAnimatedImageFactory.decodeGif(encodedImage, options, this.mBitmapConfig);
            return decodeStaticImage;
        } finally {
            Closeables.closeQuietly(is);
        }
    }

    public CloseableStaticBitmap decodeStaticImage(EncodedImage encodedImage) {
        CloseableReference<Bitmap> bitmapReference = this.mPlatformDecoder.decodeFromEncodedImage(encodedImage, this.mBitmapConfig);
        try {
            CloseableStaticBitmap closeableStaticBitmap = new CloseableStaticBitmap(bitmapReference, ImmutableQualityInfo.FULL_QUALITY, encodedImage.getRotationAngle());
            return closeableStaticBitmap;
        } finally {
            bitmapReference.close();
        }
    }

    public CloseableStaticBitmap decodeJpeg(EncodedImage encodedImage, int length, QualityInfo qualityInfo) {
        CloseableReference<Bitmap> bitmapReference = this.mPlatformDecoder.decodeJPEGFromEncodedImage(encodedImage, this.mBitmapConfig, length);
        try {
            CloseableStaticBitmap closeableStaticBitmap = new CloseableStaticBitmap(bitmapReference, qualityInfo, encodedImage.getRotationAngle());
            return closeableStaticBitmap;
        } finally {
            bitmapReference.close();
        }
    }

    public CloseableImage decodeAnimatedWebp(EncodedImage encodedImage, ImageDecodeOptions options) {
        return this.mAnimatedImageFactory.decodeWebP(encodedImage, options, this.mBitmapConfig);
    }
}
