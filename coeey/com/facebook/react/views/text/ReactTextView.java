package com.facebook.react.views.text;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build.VERSION;
import android.support.v4.view.GravityCompat;
import android.text.Layout;
import android.text.Spanned;
import android.text.TextUtils.TruncateAt;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;
import com.facebook.react.uimanager.ReactCompoundView;
import com.facebook.react.views.view.ReactViewBackgroundDrawable;
import com.facebook.yoga.YogaConstants;
import javax.annotation.Nullable;

public class ReactTextView extends TextView implements ReactCompoundView {
    private static final LayoutParams EMPTY_LAYOUT_PARAMS = new LayoutParams(0, 0);
    private boolean mContainsImages;
    private int mDefaultGravityHorizontal = (getGravity() & GravityCompat.RELATIVE_HORIZONTAL_GRAVITY_MASK);
    private int mDefaultGravityVertical = (getGravity() & 112);
    private TruncateAt mEllipsizeLocation = TruncateAt.END;
    private float mLineHeight = YogaConstants.UNDEFINED;
    private int mNumberOfLines = Integer.MAX_VALUE;
    private ReactViewBackgroundDrawable mReactBackgroundDrawable;
    private int mTextAlign = 0;
    private boolean mTextIsSelectable;

    public ReactTextView(Context context) {
        super(context);
    }

    public void setText(ReactTextUpdate update) {
        this.mContainsImages = update.containsImages();
        if (getLayoutParams() == null) {
            setLayoutParams(EMPTY_LAYOUT_PARAMS);
        }
        setText(update.getText());
        setPadding((int) Math.floor((double) update.getPaddingLeft()), (int) Math.floor((double) update.getPaddingTop()), (int) Math.floor((double) update.getPaddingRight()), (int) Math.floor((double) update.getPaddingBottom()));
        int nextTextAlign = update.getTextAlign();
        if (this.mTextAlign != nextTextAlign) {
            this.mTextAlign = nextTextAlign;
        }
        setGravityHorizontal(this.mTextAlign);
        if (VERSION.SDK_INT >= 23 && getBreakStrategy() != update.getTextBreakStrategy()) {
            setBreakStrategy(update.getTextBreakStrategy());
        }
    }

    public int reactTagForTouch(float touchX, float touchY) {
        Spanned text = (Spanned) getText();
        int target = getId();
        int x = (int) touchX;
        int y = (int) touchY;
        Layout layout = getLayout();
        if (layout == null) {
            return target;
        }
        int line = layout.getLineForVertical(y);
        int lineEndX = (int) layout.getLineRight(line);
        if (x >= ((int) layout.getLineLeft(line)) && x <= lineEndX) {
            int index = layout.getOffsetForHorizontal(line, (float) x);
            ReactTagSpan[] spans = (ReactTagSpan[]) text.getSpans(index, index, ReactTagSpan.class);
            if (spans != null) {
                int targetSpanTextLength = text.length();
                for (int i = 0; i < spans.length; i++) {
                    int spanStart = text.getSpanStart(spans[i]);
                    int spanEnd = text.getSpanEnd(spans[i]);
                    if (spanEnd > index && spanEnd - spanStart <= targetSpanTextLength) {
                        target = spans[i].getReactTag();
                        targetSpanTextLength = spanEnd - spanStart;
                    }
                }
            }
        }
        return target;
    }

    public void setTextIsSelectable(boolean selectable) {
        this.mTextIsSelectable = selectable;
        super.setTextIsSelectable(selectable);
    }

    protected boolean verifyDrawable(Drawable drawable) {
        int i = 0;
        if (this.mContainsImages && (getText() instanceof Spanned)) {
            Spanned text = (Spanned) getText();
            TextInlineImageSpan[] spans = (TextInlineImageSpan[]) text.getSpans(0, text.length(), TextInlineImageSpan.class);
            int length = spans.length;
            while (i < length) {
                if (spans[i].getDrawable() == drawable) {
                    return true;
                }
                i++;
            }
        }
        return super.verifyDrawable(drawable);
    }

    public void invalidateDrawable(Drawable drawable) {
        int i = 0;
        if (this.mContainsImages && (getText() instanceof Spanned)) {
            Spanned text = (Spanned) getText();
            TextInlineImageSpan[] spans = (TextInlineImageSpan[]) text.getSpans(0, text.length(), TextInlineImageSpan.class);
            int length = spans.length;
            while (i < length) {
                if (spans[i].getDrawable() == drawable) {
                    invalidate();
                }
                i++;
            }
        }
        super.invalidateDrawable(drawable);
    }

    public void onDetachedFromWindow() {
        int i = 0;
        super.onDetachedFromWindow();
        if (this.mContainsImages && (getText() instanceof Spanned)) {
            Spanned text = (Spanned) getText();
            TextInlineImageSpan[] spans = (TextInlineImageSpan[]) text.getSpans(0, text.length(), TextInlineImageSpan.class);
            int length = spans.length;
            while (i < length) {
                spans[i].onDetachedFromWindow();
                i++;
            }
        }
    }

    public void onStartTemporaryDetach() {
        int i = 0;
        super.onStartTemporaryDetach();
        if (this.mContainsImages && (getText() instanceof Spanned)) {
            Spanned text = (Spanned) getText();
            TextInlineImageSpan[] spans = (TextInlineImageSpan[]) text.getSpans(0, text.length(), TextInlineImageSpan.class);
            int length = spans.length;
            while (i < length) {
                spans[i].onStartTemporaryDetach();
                i++;
            }
        }
    }

    public void onAttachedToWindow() {
        int i = 0;
        super.onAttachedToWindow();
        if (this.mContainsImages && (getText() instanceof Spanned)) {
            Spanned text = (Spanned) getText();
            TextInlineImageSpan[] spans = (TextInlineImageSpan[]) text.getSpans(0, text.length(), TextInlineImageSpan.class);
            int length = spans.length;
            while (i < length) {
                spans[i].onAttachedToWindow();
                i++;
            }
        }
    }

    public void onFinishTemporaryDetach() {
        int i = 0;
        super.onFinishTemporaryDetach();
        if (this.mContainsImages && (getText() instanceof Spanned)) {
            Spanned text = (Spanned) getText();
            TextInlineImageSpan[] spans = (TextInlineImageSpan[]) text.getSpans(0, text.length(), TextInlineImageSpan.class);
            int length = spans.length;
            while (i < length) {
                spans[i].onFinishTemporaryDetach();
                i++;
            }
        }
    }

    public void setBackgroundColor(int color) {
        if (color != 0 || this.mReactBackgroundDrawable != null) {
            getOrCreateReactViewBackground().setColor(color);
        }
    }

    void setGravityHorizontal(int gravityHorizontal) {
        if (gravityHorizontal == 0) {
            gravityHorizontal = this.mDefaultGravityHorizontal;
        }
        setGravity(((getGravity() & -8) & -8388616) | gravityHorizontal);
    }

    void setGravityVertical(int gravityVertical) {
        if (gravityVertical == 0) {
            gravityVertical = this.mDefaultGravityVertical;
        }
        setGravity((getGravity() & -113) | gravityVertical);
    }

    public void setNumberOfLines(int numberOfLines) {
        if (numberOfLines == 0) {
            numberOfLines = Integer.MAX_VALUE;
        }
        this.mNumberOfLines = numberOfLines;
        setMaxLines(this.mNumberOfLines);
    }

    public void setEllipsizeLocation(TruncateAt ellipsizeLocation) {
        this.mEllipsizeLocation = ellipsizeLocation;
    }

    public void updateView() {
        setEllipsize(this.mNumberOfLines == Integer.MAX_VALUE ? null : this.mEllipsizeLocation);
    }

    public void setBorderWidth(int position, float width) {
        getOrCreateReactViewBackground().setBorderWidth(position, width);
    }

    public void setBorderColor(int position, float color, float alpha) {
        getOrCreateReactViewBackground().setBorderColor(position, color, alpha);
    }

    public void setBorderRadius(float borderRadius) {
        getOrCreateReactViewBackground().setRadius(borderRadius);
    }

    public void setBorderRadius(float borderRadius, int position) {
        getOrCreateReactViewBackground().setRadius(borderRadius, position);
    }

    public void setBorderStyle(@Nullable String style) {
        getOrCreateReactViewBackground().setBorderStyle(style);
    }

    private ReactViewBackgroundDrawable getOrCreateReactViewBackground() {
        if (this.mReactBackgroundDrawable == null) {
            this.mReactBackgroundDrawable = new ReactViewBackgroundDrawable();
            Drawable backgroundDrawable = getBackground();
            super.setBackground(null);
            if (backgroundDrawable == null) {
                super.setBackground(this.mReactBackgroundDrawable);
            } else {
                super.setBackground(new LayerDrawable(new Drawable[]{this.mReactBackgroundDrawable, backgroundDrawable}));
            }
        }
        return this.mReactBackgroundDrawable;
    }
}
