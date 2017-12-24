package com.facebook.imagepipeline.common;

public class ImageDecodeOptionsBuilder {
    private int mBackgroundColor = -1;
    private boolean mDecodeAllFrames;
    private boolean mDecodePreviewFrame;
    private boolean mForceOldAnimationCode;
    private boolean mForceStaticImage;
    private int mMinDecodeIntervalMs = 100;
    private boolean mUseLastFrameForPreview;

    public ImageDecodeOptionsBuilder setFrom(ImageDecodeOptions options) {
        this.mBackgroundColor = options.backgroundColor;
        this.mForceOldAnimationCode = options.forceOldAnimationCode;
        this.mDecodePreviewFrame = options.decodePreviewFrame;
        this.mUseLastFrameForPreview = options.useLastFrameForPreview;
        this.mDecodeAllFrames = options.decodeAllFrames;
        this.mForceStaticImage = options.forceStaticImage;
        return this;
    }

    public ImageDecodeOptionsBuilder setMinDecodeIntervalMs(int intervalMs) {
        this.mMinDecodeIntervalMs = intervalMs;
        return this;
    }

    public int getMinDecodeIntervalMs() {
        return this.mMinDecodeIntervalMs;
    }

    public ImageDecodeOptionsBuilder setBackgroundColor(int backgroundColor) {
        this.mBackgroundColor = backgroundColor;
        return this;
    }

    public int getBackgroundColor() {
        return this.mBackgroundColor;
    }

    public ImageDecodeOptionsBuilder setForceOldAnimationCode(boolean forceOldAnimationCode) {
        this.mForceOldAnimationCode = forceOldAnimationCode;
        return this;
    }

    public boolean getForceOldAnimationCode() {
        return this.mForceOldAnimationCode;
    }

    public ImageDecodeOptionsBuilder setDecodePreviewFrame(boolean decodePreviewFrame) {
        this.mDecodePreviewFrame = decodePreviewFrame;
        return this;
    }

    public boolean getDecodePreviewFrame() {
        return this.mDecodePreviewFrame;
    }

    public boolean getUseLastFrameForPreview() {
        return this.mUseLastFrameForPreview;
    }

    public ImageDecodeOptionsBuilder setUseLastFrameForPreview(boolean useLastFrameForPreview) {
        this.mUseLastFrameForPreview = useLastFrameForPreview;
        return this;
    }

    public boolean getDecodeAllFrames() {
        return this.mDecodeAllFrames;
    }

    public ImageDecodeOptionsBuilder setDecodeAllFrames(boolean decodeAllFrames) {
        this.mDecodeAllFrames = decodeAllFrames;
        return this;
    }

    public ImageDecodeOptionsBuilder setForceStaticImage(boolean forceStaticImage) {
        this.mForceStaticImage = forceStaticImage;
        return this;
    }

    public boolean getForceStaticImage() {
        return this.mForceStaticImage;
    }

    public ImageDecodeOptions build() {
        return new ImageDecodeOptions(this);
    }
}
