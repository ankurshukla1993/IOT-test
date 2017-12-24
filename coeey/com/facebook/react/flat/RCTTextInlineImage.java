package com.facebook.react.flat;

import android.text.SpannableStringBuilder;
import com.RNFetchBlob.RNFetchBlobConst;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.views.imagehelper.ImageSource;
import javax.annotation.Nullable;

class RCTTextInlineImage extends FlatTextShadowNode {
    private InlineImageSpanWithPipeline mInlineImageSpan = new InlineImageSpanWithPipeline();

    RCTTextInlineImage() {
    }

    public void setStyleWidth(float width) {
        super.setStyleWidth(width);
        if (this.mInlineImageSpan.getWidth() != width) {
            getMutableSpan().setWidth(width);
            notifyChanged(true);
        }
    }

    public void setStyleHeight(float height) {
        super.setStyleHeight(height);
        if (this.mInlineImageSpan.getHeight() != height) {
            getMutableSpan().setHeight(height);
            notifyChanged(true);
        }
    }

    protected void performCollectText(SpannableStringBuilder builder) {
        builder.append("I");
    }

    protected void performApplySpans(SpannableStringBuilder builder, int begin, int end, boolean isEditable) {
        this.mInlineImageSpan.freeze();
        builder.setSpan(this.mInlineImageSpan, begin, end, 17);
    }

    protected void performCollectAttachDetachListeners(StateBuilder stateBuilder) {
        stateBuilder.addAttachDetachListener(this.mInlineImageSpan);
    }

    @ReactProp(name = "src")
    public void setSource(@Nullable ReadableArray sources) {
        ImageSource imageSource;
        ImageRequest imageRequest = null;
        String source = (sources == null || sources.size() == 0) ? null : sources.getMap(0).getString(RNFetchBlobConst.DATA_ENCODE_URI);
        if (source == null) {
            imageSource = null;
        } else {
            imageSource = new ImageSource(getThemedContext(), source);
        }
        InlineImageSpanWithPipeline mutableSpan = getMutableSpan();
        if (imageSource != null) {
            imageRequest = ImageRequestBuilder.newBuilderWithSource(imageSource.getUri()).build();
        }
        mutableSpan.setImageRequest(imageRequest);
    }

    private InlineImageSpanWithPipeline getMutableSpan() {
        if (this.mInlineImageSpan.isFrozen()) {
            this.mInlineImageSpan = this.mInlineImageSpan.mutableCopy();
        }
        return this.mInlineImageSpan;
    }
}
