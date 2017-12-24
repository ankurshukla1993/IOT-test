package com.facebook.imageformat;

public enum ImageFormat {
    WEBP_SIMPLE,
    WEBP_LOSSLESS,
    WEBP_EXTENDED,
    WEBP_EXTENDED_WITH_ALPHA,
    WEBP_ANIMATED,
    JPEG,
    PNG,
    GIF,
    BMP,
    UNKNOWN;

    public static boolean isWebpFormat(ImageFormat imageFormat) {
        return imageFormat == WEBP_SIMPLE || imageFormat == WEBP_LOSSLESS || imageFormat == WEBP_EXTENDED || imageFormat == WEBP_EXTENDED_WITH_ALPHA || imageFormat == WEBP_ANIMATED;
    }

    public static String getFileExtension(ImageFormat imageFormat) throws UnsupportedOperationException {
        switch (imageFormat) {
            case WEBP_SIMPLE:
            case WEBP_LOSSLESS:
            case WEBP_EXTENDED:
            case WEBP_EXTENDED_WITH_ALPHA:
            case WEBP_ANIMATED:
                return "webp";
            case JPEG:
                return "jpeg";
            case PNG:
                return "png";
            case GIF:
                return "gif";
            case BMP:
                return "bmp";
            default:
                throw new UnsupportedOperationException("Unknown image format " + imageFormat.name());
        }
    }
}
