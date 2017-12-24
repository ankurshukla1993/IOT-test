package com.facebook.react.flat;

import android.text.Layout;
import android.text.Spanned;
import javax.annotation.Nullable;

final class TextNodeRegion extends NodeRegion {
    @Nullable
    private Layout mLayout;

    TextNodeRegion(float left, float top, float right, float bottom, int tag, boolean isVirtual, @Nullable Layout layout) {
        super(left, top, right, bottom, tag, isVirtual);
        this.mLayout = layout;
    }

    public void setLayout(Layout layout) {
        this.mLayout = layout;
    }

    @Nullable
    Layout getLayout() {
        return this.mLayout;
    }

    int getReactTag(float touchX, float touchY) {
        if (this.mLayout != null) {
            CharSequence text = this.mLayout.getText();
            if (text instanceof Spanned) {
                int y = Math.round(touchY - getTop());
                if (y >= this.mLayout.getLineTop(0) && y < this.mLayout.getLineBottom(this.mLayout.getLineCount() - 1)) {
                    float x = (float) Math.round(touchX - getLeft());
                    int line = this.mLayout.getLineForVertical(y);
                    if (this.mLayout.getLineLeft(line) <= x && x <= this.mLayout.getLineRight(line)) {
                        int off = this.mLayout.getOffsetForHorizontal(line, x);
                        RCTRawText[] link = (RCTRawText[]) ((Spanned) text).getSpans(off, off, RCTRawText.class);
                        if (link.length != 0) {
                            return link[0].getReactTag();
                        }
                    }
                }
            }
        }
        return super.getReactTag(touchX, touchY);
    }

    boolean matchesTag(int tag) {
        if (super.matchesTag(tag)) {
            return true;
        }
        if (this.mLayout != null) {
            Spanned text = (Spanned) this.mLayout.getText();
            for (RCTRawText span : (RCTRawText[]) text.getSpans(0, text.length(), RCTRawText.class)) {
                if (span.getReactTag() == tag) {
                    return true;
                }
            }
        }
        return false;
    }
}
