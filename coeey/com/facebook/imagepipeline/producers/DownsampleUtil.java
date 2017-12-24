package com.facebook.imagepipeline.producers;

import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.common.logging.FLog;
import com.facebook.imageformat.ImageFormat;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.request.ImageRequest;
import com.google.android.flexbox.FlexItem;

public class DownsampleUtil {
    private static final int DEFAULT_SAMPLE_SIZE = 1;
    private static final float INTERVAL_ROUNDING = 0.33333334f;
    private static final float MAX_BITMAP_SIZE = 2048.0f;

    private DownsampleUtil() {
    }

    public static int determineSampleSize(ImageRequest imageRequest, EncodedImage encodedImage) {
        if (!EncodedImage.isMetaDataAvailable(encodedImage)) {
            return 1;
        }
        int sampleSize;
        float ratio = determineDownsampleRatio(imageRequest, encodedImage);
        if (encodedImage.getImageFormat() == ImageFormat.JPEG) {
            sampleSize = ratioToSampleSizeJPEG(ratio);
        } else {
            sampleSize = ratioToSampleSize(ratio);
        }
        int maxDimension = Math.max(encodedImage.getHeight(), encodedImage.getWidth());
        while (((float) (maxDimension / sampleSize)) > 2048.0f) {
            if (encodedImage.getImageFormat() == ImageFormat.JPEG) {
                sampleSize *= 2;
            } else {
                sampleSize++;
            }
        }
        return sampleSize;
    }

    @VisibleForTesting
    static float determineDownsampleRatio(ImageRequest imageRequest, EncodedImage encodedImage) {
        Preconditions.checkArgument(EncodedImage.isMetaDataAvailable(encodedImage));
        ResizeOptions resizeOptions = imageRequest.getResizeOptions();
        if (resizeOptions == null || resizeOptions.height <= 0 || resizeOptions.width <= 0 || encodedImage.getWidth() == 0 || encodedImage.getHeight() == 0) {
            return FlexItem.FLEX_SHRINK_DEFAULT;
        }
        boolean swapDimensions;
        int rotationAngle = getRotationAngle(imageRequest, encodedImage);
        if (rotationAngle == 90 || rotationAngle == 270) {
            swapDimensions = true;
        } else {
            swapDimensions = false;
        }
        FLog.m145v("DownsampleUtil", "Downsample - Specified size: %dx%d, image size: %dx%d ratio: %.1f x %.1f, ratio: %.3f for %s", Integer.valueOf(resizeOptions.width), Integer.valueOf(resizeOptions.height), Integer.valueOf(swapDimensions ? encodedImage.getHeight() : encodedImage.getWidth()), Integer.valueOf(swapDimensions ? encodedImage.getWidth() : encodedImage.getHeight()), Float.valueOf(((float) resizeOptions.width) / ((float) (swapDimensions ? encodedImage.getHeight() : encodedImage.getWidth()))), Float.valueOf(((float) resizeOptions.height) / ((float) (swapDimensions ? encodedImage.getWidth() : encodedImage.getHeight()))), Float.valueOf(Math.max(((float) resizeOptions.width) / ((float) (swapDimensions ? encodedImage.getHeight() : encodedImage.getWidth())), ((float) resizeOptions.height) / ((float) (swapDimensions ? encodedImage.getWidth() : encodedImage.getHeight())))), imageRequest.getSourceUri().toString());
        return Math.max(((float) resizeOptions.width) / ((float) (swapDimensions ? encodedImage.getHeight() : encodedImage.getWidth())), ((float) resizeOptions.height) / ((float) (swapDimensions ? encodedImage.getWidth() : encodedImage.getHeight())));
    }

    @VisibleForTesting
    static int ratioToSampleSize(float ratio) {
        if (ratio > 0.6666667f) {
            return 1;
        }
        int sampleSize = 2;
        while (true) {
            if ((1.0d / ((double) sampleSize)) + (0.3333333432674408d * (1.0d / (Math.pow((double) sampleSize, 2.0d) - ((double) sampleSize)))) <= ((double) ratio)) {
                return sampleSize - 1;
            }
            sampleSize++;
        }
    }

    @VisibleForTesting
    static int ratioToSampleSizeJPEG(float ratio) {
        if (ratio > 0.6666667f) {
            return 1;
        }
        int sampleSize = 2;
        while (true) {
            if ((1.0d / ((double) (sampleSize * 2))) + (0.3333333432674408d * (1.0d / ((double) (sampleSize * 2)))) <= ((double) ratio)) {
                return sampleSize;
            }
            sampleSize *= 2;
        }
    }

    private static int getRotationAngle(ImageRequest imageRequest, EncodedImage encodedImage) {
        boolean z = false;
        if (!imageRequest.getAutoRotateEnabled()) {
            return 0;
        }
        int rotationAngle = encodedImage.getRotationAngle();
        if (rotationAngle == 0 || rotationAngle == 90 || rotationAngle == 180 || rotationAngle == 270) {
            z = true;
        }
        Preconditions.checkArgument(z);
        return rotationAngle;
    }

    @VisibleForTesting
    static int roundToPowerOfTwo(int sampleSize) {
        int compare = 1;
        while (compare < sampleSize) {
            compare *= 2;
        }
        return compare;
    }
}
