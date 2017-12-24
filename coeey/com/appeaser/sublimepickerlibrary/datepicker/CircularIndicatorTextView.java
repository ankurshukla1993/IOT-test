package com.appeaser.sublimepickerlibrary.datepicker;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.widget.TextView;
import com.appeaser.sublimepickerlibrary.C0563R;

class CircularIndicatorTextView extends TextView {
    private static final int SELECTED_CIRCLE_ALPHA = 60;
    private int mCircleColor;
    private final Paint mCirclePaint;
    private boolean mDrawIndicator;
    private final String mItemIsSelectedText;

    public CircularIndicatorTextView(Context context) {
        this(context, null);
    }

    public CircularIndicatorTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircularIndicatorTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public CircularIndicatorTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs);
        this.mCirclePaint = new Paint();
        this.mItemIsSelectedText = context.getResources().getString(C0563R.string.item_is_selected);
        init();
    }

    private void init() {
        this.mCirclePaint.setTypeface(Typeface.create(this.mCirclePaint.getTypeface(), 1));
        this.mCirclePaint.setAntiAlias(true);
        this.mCirclePaint.setTextAlign(Align.CENTER);
        this.mCirclePaint.setStyle(Style.FILL);
    }

    public void setCircleColor(int color) {
        if (color != this.mCircleColor) {
            this.mCircleColor = color;
            this.mCirclePaint.setColor(this.mCircleColor);
            this.mCirclePaint.setAlpha(60);
            requestLayout();
        }
    }

    public void setDrawIndicator(boolean drawIndicator) {
        this.mDrawIndicator = drawIndicator;
    }

    public void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        if (this.mDrawIndicator) {
            int width = getWidth();
            int height = getHeight();
            canvas.drawCircle((float) (width / 2), (float) (height / 2), (float) (Math.min(width, height) / 2), this.mCirclePaint);
        }
    }

    public CharSequence getContentDescription() {
        CharSequence itemText = getText();
        if (!this.mDrawIndicator) {
            return itemText;
        }
        return String.format(this.mItemIsSelectedText, new Object[]{itemText});
    }
}
