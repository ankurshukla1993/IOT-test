package com.facebook.react.views.text;

import android.os.Build.VERSION;
import android.text.BoringLayout;
import android.text.BoringLayout.Metrics;
import android.text.Layout;
import android.text.Layout.Alignment;
import android.text.Spanned;
import android.text.StaticLayout;
import android.text.StaticLayout.Builder;
import android.text.TextPaint;
import com.facebook.infer.annotation.Assertions;
import com.facebook.yoga.YogaConstants;
import com.facebook.yoga.YogaMeasureFunction;
import com.facebook.yoga.YogaMeasureMode;
import com.facebook.yoga.YogaMeasureOutput;
import com.facebook.yoga.YogaNodeAPI;
import com.google.android.flexbox.FlexItem;

class ReactTextShadowNode$1 implements YogaMeasureFunction {
    final /* synthetic */ ReactTextShadowNode this$0;

    ReactTextShadowNode$1(ReactTextShadowNode this$0) {
        this.this$0 = this$0;
    }

    public long measure(YogaNodeAPI node, float width, YogaMeasureMode widthMode, float height, YogaMeasureMode heightMode) {
        Layout layout;
        TextPaint textPaint = ReactTextShadowNode.access$000();
        Spanned text = (Spanned) Assertions.assertNotNull(ReactTextShadowNode.access$100(this.this$0), "Spannable element has not been prepared in onBeforeLayout");
        Metrics boring = BoringLayout.isBoring(text, textPaint);
        float desiredWidth = boring == null ? Layout.getDesiredWidth(text, textPaint) : YogaConstants.UNDEFINED;
        boolean unconstrainedWidth = widthMode == YogaMeasureMode.UNDEFINED || width < 0.0f;
        if (boring == null && (unconstrainedWidth || (!YogaConstants.isUndefined(desiredWidth) && desiredWidth <= width))) {
            int hintWidth = (int) Math.ceil((double) desiredWidth);
            if (VERSION.SDK_INT < 23) {
                layout = new StaticLayout(text, textPaint, hintWidth, Alignment.ALIGN_NORMAL, FlexItem.FLEX_SHRINK_DEFAULT, 0.0f, true);
            } else {
                layout = Builder.obtain(text, 0, text.length(), textPaint, hintWidth).setAlignment(Alignment.ALIGN_NORMAL).setLineSpacing(0.0f, FlexItem.FLEX_SHRINK_DEFAULT).setIncludePad(true).setBreakStrategy(this.this$0.mTextBreakStrategy).setHyphenationFrequency(1).build();
            }
        } else if (boring != null && (unconstrainedWidth || ((float) boring.width) <= width)) {
            layout = BoringLayout.make(text, textPaint, boring.width, Alignment.ALIGN_NORMAL, FlexItem.FLEX_SHRINK_DEFAULT, 0.0f, boring, true);
        } else if (VERSION.SDK_INT < 23) {
            Layout staticLayout = new StaticLayout(text, textPaint, (int) width, Alignment.ALIGN_NORMAL, FlexItem.FLEX_SHRINK_DEFAULT, 0.0f, true);
        } else {
            layout = Builder.obtain(text, 0, text.length(), textPaint, (int) width).setAlignment(Alignment.ALIGN_NORMAL).setLineSpacing(0.0f, FlexItem.FLEX_SHRINK_DEFAULT).setIncludePad(true).setBreakStrategy(this.this$0.mTextBreakStrategy).setHyphenationFrequency(1).build();
        }
        if (this.this$0.mNumberOfLines == -1 || this.this$0.mNumberOfLines >= layout.getLineCount()) {
            return YogaMeasureOutput.make(layout.getWidth(), layout.getHeight());
        }
        return YogaMeasureOutput.make(layout.getWidth(), layout.getLineBottom(this.this$0.mNumberOfLines - 1));
    }
}
