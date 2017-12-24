package com.facebook.react.flat;

import android.text.SpannableStringBuilder;
import com.facebook.react.uimanager.annotations.ReactProp;
import javax.annotation.Nullable;

final class RCTRawText extends FlatTextShadowNode {
    @Nullable
    private String mText;

    RCTRawText() {
    }

    protected void performCollectText(SpannableStringBuilder builder) {
        if (this.mText != null) {
            builder.append(this.mText);
        }
    }

    protected void performApplySpans(SpannableStringBuilder builder, int begin, int end, boolean isEditable) {
        builder.setSpan(this, begin, end, 17);
    }

    protected void performCollectAttachDetachListeners(StateBuilder stateBuilder) {
    }

    @ReactProp(name = "text")
    public void setText(@Nullable String text) {
        this.mText = text;
        notifyChanged(true);
    }
}
