package com.hadiidbouk.charts;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.ProgressBar;

public class Bar extends ProgressBar {
    public Bar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public Bar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Bar(Context context) {
        super(context);
    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(h, w, oldh, oldw);
    }

    protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    protected synchronized void onDraw(Canvas canvas) {
        canvas.rotate(-90.0f);
        canvas.translate((float) (-getHeight()), 0.0f);
        super.onDraw(canvas);
    }
}
