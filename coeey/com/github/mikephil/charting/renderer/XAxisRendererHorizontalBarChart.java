package com.github.mikephil.charting.renderer;

import android.graphics.Canvas;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.Path;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.LimitLine.LimitLabelPosition;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.XAxis.XAxisPosition;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.google.android.flexbox.FlexItem;
import java.util.List;

public class XAxisRendererHorizontalBarChart extends XAxisRendererBarChart {
    public XAxisRendererHorizontalBarChart(ViewPortHandler viewPortHandler, XAxis xAxis, Transformer trans, BarChart chart) {
        super(viewPortHandler, xAxis, trans, chart);
    }

    public void computeAxis(float xValAverageLength, List<String> xValues) {
        this.mAxisLabelPaint.setTypeface(this.mXAxis.getTypeface());
        this.mAxisLabelPaint.setTextSize(this.mXAxis.getTextSize());
        this.mXAxis.setValues(xValues);
        String longest = this.mXAxis.getLongestLabel();
        this.mXAxis.mLabelWidth = (int) (((float) Utils.calcTextWidth(this.mAxisLabelPaint, longest)) + (this.mXAxis.getXOffset() * 3.5f));
        this.mXAxis.mLabelHeight = Utils.calcTextHeight(this.mAxisLabelPaint, longest);
    }

    public void renderAxisLabels(Canvas c) {
        if (this.mXAxis.isEnabled() && this.mXAxis.isDrawLabelsEnabled()) {
            float xoffset = this.mXAxis.getXOffset();
            this.mAxisLabelPaint.setTypeface(this.mXAxis.getTypeface());
            this.mAxisLabelPaint.setTextSize(this.mXAxis.getTextSize());
            this.mAxisLabelPaint.setColor(this.mXAxis.getTextColor());
            if (this.mXAxis.getPosition() == XAxisPosition.TOP) {
                this.mAxisLabelPaint.setTextAlign(Align.LEFT);
                drawLabels(c, this.mViewPortHandler.contentRight() + xoffset);
            } else if (this.mXAxis.getPosition() == XAxisPosition.BOTTOM) {
                this.mAxisLabelPaint.setTextAlign(Align.RIGHT);
                drawLabels(c, this.mViewPortHandler.contentLeft() - xoffset);
            } else if (this.mXAxis.getPosition() == XAxisPosition.BOTTOM_INSIDE) {
                this.mAxisLabelPaint.setTextAlign(Align.LEFT);
                drawLabels(c, this.mViewPortHandler.contentLeft() + xoffset);
            } else if (this.mXAxis.getPosition() == XAxisPosition.TOP_INSIDE) {
                this.mAxisLabelPaint.setTextAlign(Align.RIGHT);
                drawLabels(c, this.mViewPortHandler.contentRight() - xoffset);
            } else {
                this.mAxisLabelPaint.setTextAlign(Align.RIGHT);
                drawLabels(c, this.mViewPortHandler.contentLeft() - xoffset);
                this.mAxisLabelPaint.setTextAlign(Align.LEFT);
                drawLabels(c, this.mViewPortHandler.contentRight() + xoffset);
            }
        }
    }

    protected void drawLabels(Canvas c, float pos) {
        float[] position = new float[]{0.0f, 0.0f};
        BarData bd = (BarData) this.mChart.getData();
        int step = bd.getDataSetCount();
        int i = this.mMinX;
        while (i <= this.mMaxX) {
            position[1] = (((float) (i * step)) + (((float) i) * bd.getGroupSpace())) + (bd.getGroupSpace() / 2.0f);
            if (step > 1) {
                position[1] = position[1] + ((((float) step) - FlexItem.FLEX_SHRINK_DEFAULT) / 2.0f);
            }
            this.mTrans.pointValuesToPixel(position);
            if (this.mViewPortHandler.isInBoundsY(position[1])) {
                drawLabel(c, (String) this.mXAxis.getValues().get(i), i, pos, position[1] + (((float) this.mXAxis.mLabelHeight) / 2.0f));
            }
            i += this.mXAxis.mAxisLabelModulus;
        }
    }

    public void renderGridLines(Canvas c) {
        if (this.mXAxis.isDrawGridLinesEnabled() && this.mXAxis.isEnabled()) {
            float[] position = new float[]{0.0f, 0.0f};
            this.mGridPaint.setColor(this.mXAxis.getGridColor());
            this.mGridPaint.setStrokeWidth(this.mXAxis.getGridLineWidth());
            BarData bd = (BarData) this.mChart.getData();
            int step = bd.getDataSetCount();
            int i = this.mMinX;
            while (i <= this.mMaxX) {
                position[1] = (((float) (i * step)) + (((float) i) * bd.getGroupSpace())) - 0.5f;
                this.mTrans.pointValuesToPixel(position);
                if (this.mViewPortHandler.isInBoundsY(position[1])) {
                    c.drawLine(this.mViewPortHandler.contentLeft(), position[1], this.mViewPortHandler.contentRight(), position[1], this.mGridPaint);
                }
                i += this.mXAxis.mAxisLabelModulus;
            }
        }
    }

    public void renderAxisLine(Canvas c) {
        if (this.mXAxis.isDrawAxisLineEnabled() && this.mXAxis.isEnabled()) {
            this.mAxisLinePaint.setColor(this.mXAxis.getAxisLineColor());
            this.mAxisLinePaint.setStrokeWidth(this.mXAxis.getAxisLineWidth());
            if (this.mXAxis.getPosition() == XAxisPosition.TOP || this.mXAxis.getPosition() == XAxisPosition.TOP_INSIDE || this.mXAxis.getPosition() == XAxisPosition.BOTH_SIDED) {
                c.drawLine(this.mViewPortHandler.contentRight(), this.mViewPortHandler.contentTop(), this.mViewPortHandler.contentRight(), this.mViewPortHandler.contentBottom(), this.mAxisLinePaint);
            }
            if (this.mXAxis.getPosition() == XAxisPosition.BOTTOM || this.mXAxis.getPosition() == XAxisPosition.BOTTOM_INSIDE || this.mXAxis.getPosition() == XAxisPosition.BOTH_SIDED) {
                c.drawLine(this.mViewPortHandler.contentLeft(), this.mViewPortHandler.contentTop(), this.mViewPortHandler.contentLeft(), this.mViewPortHandler.contentBottom(), this.mAxisLinePaint);
            }
        }
    }

    public void renderLimitLines(Canvas c) {
        List<LimitLine> limitLines = this.mXAxis.getLimitLines();
        if (limitLines != null && limitLines.size() > 0) {
            float[] pts = new float[2];
            Path limitLinePath = new Path();
            for (int i = 0; i < limitLines.size(); i++) {
                LimitLine l = (LimitLine) limitLines.get(i);
                if (l.isEnabled()) {
                    this.mLimitLinePaint.setStyle(Style.STROKE);
                    this.mLimitLinePaint.setColor(l.getLineColor());
                    this.mLimitLinePaint.setStrokeWidth(l.getLineWidth());
                    this.mLimitLinePaint.setPathEffect(l.getDashPathEffect());
                    pts[1] = l.getLimit();
                    this.mTrans.pointValuesToPixel(pts);
                    limitLinePath.moveTo(this.mViewPortHandler.contentLeft(), pts[1]);
                    limitLinePath.lineTo(this.mViewPortHandler.contentRight(), pts[1]);
                    c.drawPath(limitLinePath, this.mLimitLinePaint);
                    limitLinePath.reset();
                    String label = l.getLabel();
                    if (!(label == null || label.equals(""))) {
                        this.mLimitLinePaint.setStyle(l.getTextStyle());
                        this.mLimitLinePaint.setPathEffect(null);
                        this.mLimitLinePaint.setColor(l.getTextColor());
                        this.mLimitLinePaint.setStrokeWidth(0.5f);
                        this.mLimitLinePaint.setTextSize(l.getTextSize());
                        float labelLineHeight = (float) Utils.calcTextHeight(this.mLimitLinePaint, label);
                        float xOffset = Utils.convertDpToPixel(4.0f) + l.getXOffset();
                        float yOffset = (l.getLineWidth() + labelLineHeight) + l.getYOffset();
                        LimitLabelPosition position = l.getLabelPosition();
                        if (position == LimitLabelPosition.RIGHT_TOP) {
                            this.mLimitLinePaint.setTextAlign(Align.RIGHT);
                            c.drawText(label, this.mViewPortHandler.contentRight() - xOffset, (pts[1] - yOffset) + labelLineHeight, this.mLimitLinePaint);
                        } else if (position == LimitLabelPosition.RIGHT_BOTTOM) {
                            this.mLimitLinePaint.setTextAlign(Align.RIGHT);
                            c.drawText(label, this.mViewPortHandler.contentRight() - xOffset, pts[1] + yOffset, this.mLimitLinePaint);
                        } else if (position == LimitLabelPosition.LEFT_TOP) {
                            this.mLimitLinePaint.setTextAlign(Align.LEFT);
                            c.drawText(label, this.mViewPortHandler.contentLeft() + xOffset, (pts[1] - yOffset) + labelLineHeight, this.mLimitLinePaint);
                        } else {
                            this.mLimitLinePaint.setTextAlign(Align.LEFT);
                            c.drawText(label, this.mViewPortHandler.offsetLeft() + xOffset, pts[1] + yOffset, this.mLimitLinePaint);
                        }
                    }
                }
            }
        }
    }
}
