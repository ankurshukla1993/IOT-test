package com.facebook.react.flat;

import android.text.SpannableStringBuilder;
import com.facebook.react.uimanager.ReactShadowNode;

abstract class FlatTextShadowNode extends FlatShadowNode {
    private int mTextBegin;
    private int mTextEnd;

    protected abstract void performApplySpans(SpannableStringBuilder spannableStringBuilder, int i, int i2, boolean z);

    protected abstract void performCollectAttachDetachListeners(StateBuilder stateBuilder);

    protected abstract void performCollectText(SpannableStringBuilder spannableStringBuilder);

    FlatTextShadowNode() {
    }

    protected void notifyChanged(boolean shouldRemeasure) {
        ReactShadowNode parent = getParent();
        if (parent instanceof FlatTextShadowNode) {
            ((FlatTextShadowNode) parent).notifyChanged(shouldRemeasure);
        }
    }

    public boolean isVirtual() {
        return true;
    }

    final void collectText(SpannableStringBuilder builder) {
        this.mTextBegin = builder.length();
        performCollectText(builder);
        this.mTextEnd = builder.length();
    }

    boolean shouldAllowEmptySpans() {
        return false;
    }

    boolean isEditable() {
        return false;
    }

    final void applySpans(SpannableStringBuilder builder, boolean isEditable) {
        if (this.mTextBegin != this.mTextEnd || shouldAllowEmptySpans()) {
            performApplySpans(builder, this.mTextBegin, this.mTextEnd, isEditable);
        }
    }
}
