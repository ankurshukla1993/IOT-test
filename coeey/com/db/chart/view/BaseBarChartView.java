package com.db.chart.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.ColorInt;
import android.support.annotation.FloatRange;
import android.util.AttributeSet;
import com.db.chart.model.ChartSet;
import com.db.williamchart.C1047R;
import java.util.ArrayList;

public abstract class BaseBarChartView extends ChartView {
    float barWidth;
    float drawingOffset;
    final Style style;

    public class Style {
        private static final int DEFAULT_COLOR = -16777216;
        Paint barBackgroundPaint;
        Paint barPaint;
        float barSpacing;
        float cornerRadius;
        boolean hasBarBackground;
        private int mBarBackgroundColor;
        float setSpacing;

        Style() {
            this.mBarBackgroundColor = -16777216;
            this.hasBarBackground = false;
            this.barSpacing = BaseBarChartView.this.getResources().getDimension(C1047R.dimen.bar_spacing);
            this.setSpacing = BaseBarChartView.this.getResources().getDimension(C1047R.dimen.set_spacing);
            this.cornerRadius = BaseBarChartView.this.getResources().getDimension(C1047R.dimen.corner_radius);
        }

        Style(TypedArray attrs) {
            this.mBarBackgroundColor = attrs.getColor(C1047R.styleable.BarChartAttrs_chart_barBackgroundColor, -1);
            this.hasBarBackground = this.mBarBackgroundColor != -1;
            this.barSpacing = attrs.getDimension(C1047R.styleable.BarChartAttrs_chart_barSpacing, BaseBarChartView.this.getResources().getDimension(C1047R.dimen.bar_spacing));
            this.setSpacing = attrs.getDimension(C1047R.styleable.BarChartAttrs_chart_setSpacing, BaseBarChartView.this.getResources().getDimension(C1047R.dimen.set_spacing));
            this.cornerRadius = attrs.getDimension(C1047R.styleable.BarChartAttrs_chart_cornerRadius, BaseBarChartView.this.getResources().getDimension(C1047R.dimen.corner_radius));
        }

        private void init() {
            this.barPaint = new Paint();
            this.barPaint.setStyle(android.graphics.Paint.Style.FILL);
            this.barBackgroundPaint = new Paint();
            this.barBackgroundPaint.setColor(this.mBarBackgroundColor);
            this.barBackgroundPaint.setStyle(android.graphics.Paint.Style.FILL);
        }

        private void clean() {
            this.barPaint = null;
            this.barBackgroundPaint = null;
        }
    }

    public BaseBarChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.style = new Style(context.getTheme().obtainStyledAttributes(attrs, C1047R.styleable.BarChartAttrs, 0, 0));
    }

    public BaseBarChartView(Context context) {
        super(context);
        this.style = new Style();
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.style.init();
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.style.clean();
    }

    protected void onDrawChart(Canvas canvas, ArrayList<ChartSet> arrayList) {
    }

    public void reset() {
        super.reset();
        setMandatoryBorderSpacing();
    }

    void drawBar(Canvas canvas, float left, float top, float right, float bottom) {
        canvas.drawRoundRect(new RectF((float) Math.round(left), (float) Math.round(top), (float) Math.round(right), (float) Math.round(bottom)), this.style.cornerRadius, this.style.cornerRadius, this.style.barPaint);
    }

    void drawBarBackground(Canvas canvas, float left, float top, float right, float bottom) {
        canvas.drawRoundRect(new RectF((float) Math.round(left), (float) Math.round(top), (float) Math.round(right), (float) Math.round(bottom)), this.style.cornerRadius, this.style.cornerRadius, this.style.barBackgroundPaint);
    }

    void calculateBarsWidth(int nSets, float x0, float x1) {
        this.barWidth = (((x1 - x0) - this.style.barSpacing) - (this.style.setSpacing * ((float) (nSets - 1)))) / ((float) nSets);
    }

    void calculatePositionOffset(int size) {
        if (size % 2 == 0) {
            this.drawingOffset = ((((float) size) * this.barWidth) / 2.0f) + (((float) (size - 1)) * (this.style.setSpacing / 2.0f));
        } else {
            this.drawingOffset = ((((float) size) * this.barWidth) / 2.0f) + (((float) ((size - 1) / 2)) * this.style.setSpacing);
        }
    }

    public void setBarSpacing(float spacing) {
        this.style.barSpacing = spacing;
    }

    public void setSetSpacing(float spacing) {
        this.style.setSpacing = spacing;
    }

    public void setBarBackgroundColor(@ColorInt int color) {
        this.style.hasBarBackground = true;
        this.style.mBarBackgroundColor = color;
    }

    public void setRoundCorners(@FloatRange(from = 0.0d) float radius) {
        this.style.cornerRadius = radius;
    }
}
