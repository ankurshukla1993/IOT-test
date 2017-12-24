package com.facebook.imagepipeline.producers;

import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imageutils.BitmapUtil;

public final class ThumbnailSizeChecker {
    public static final float ACCEPTABLE_REQUESTED_TO_ACTUAL_SIZE_RATIO = 1.3333334f;
    private static final int ROTATED_90_DEGREES_CLOCKWISE = 90;
    private static final int ROTATED_90_DEGREES_COUNTER_CLOCKWISE = 270;

    public static boolean isImageBigEnough(int width, int height, ResizeOptions resizeOptions) {
        if (resizeOptions == null) {
            if (((float) getAcceptableSize(width)) < BitmapUtil.MAX_BITMAP_SIZE || getAcceptableSize(height) < 2048) {
                return false;
            }
            return true;
        } else if (getAcceptableSize(width) < resizeOptions.width || getAcceptableSize(height) < resizeOptions.height) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean isImageBigEnough(EncodedImage encodedImage, ResizeOptions resizeOptions) {
        if (encodedImage == null) {
            return false;
        }
        switch (encodedImage.getRotationAngle()) {
            case 90:
            case ROTATED_90_DEGREES_COUNTER_CLOCKWISE /*270*/:
                return isImageBigEnough(encodedImage.getHeight(), encodedImage.getWidth(), resizeOptions);
            default:
                return isImageBigEnough(encodedImage.getWidth(), encodedImage.getHeight(), resizeOptions);
        }
    }

    public static int getAcceptableSize(int size) {
        return (int) (((float) size) * ACCEPTABLE_REQUESTED_TO_ACTUAL_SIZE_RATIO);
    }
}
