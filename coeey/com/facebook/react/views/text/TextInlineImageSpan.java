package com.facebook.react.views.text;

import android.graphics.drawable.Drawable;
import android.text.Spannable;
import android.text.style.ReplacementSpan;
import android.widget.TextView;
import javax.annotation.Nullable;

public abstract class TextInlineImageSpan extends ReplacementSpan {
    @Nullable
    public abstract Drawable getDrawable();

    public abstract int getHeight();

    public abstract int getWidth();

    public abstract void onAttachedToWindow();

    public abstract void onDetachedFromWindow();

    public abstract void onFinishTemporaryDetach();

    public abstract void onStartTemporaryDetach();

    public abstract void setTextView(TextView textView);

    public static void possiblyUpdateInlineImageSpans(Spannable spannable, TextView view) {
        int i = 0;
        TextInlineImageSpan[] spans = (TextInlineImageSpan[]) spannable.getSpans(0, spannable.length(), TextInlineImageSpan.class);
        int length = spans.length;
        while (i < length) {
            TextInlineImageSpan span = spans[i];
            span.onAttachedToWindow();
            span.setTextView(view);
            i++;
        }
    }
}
