package com.github.mikephil.charting.renderer;

import android.graphics.Canvas;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.support.v4.view.ViewCompat;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.LimitLine.LimitLabelPosition;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.XAxis.XAxisPosition;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import java.util.List;

public class XAxisRenderer extends AxisRenderer {
    private Path mLimitLinePath = new Path();
    float[] mLimitLineSegmentsBuffer = new float[4];
    protected XAxis mXAxis;

    public XAxisRenderer(ViewPortHandler viewPortHandler, XAxis xAxis, Transformer trans) {
        super(viewPortHandler, trans);
        this.mXAxis = xAxis;
        this.mAxisLabelPaint.setColor(ViewCompat.MEASURED_STATE_MASK);
        this.mAxisLabelPaint.setTextAlign(Align.CENTER);
        this.mAxisLabelPaint.setTextSize(Utils.convertDpToPixel(10.0f));
    }

    public void computeAxis(float xValAverageLength, List<String> xValues) {
        this.mAxisLabelPaint.setTypeface(this.mXAxis.getTypeface());
        this.mAxisLabelPaint.setTextSize(this.mXAxis.getTextSize());
        StringBuffer a = new StringBuffer();
        int max = Math.round(((float) this.mXAxis.getSpaceBetweenLabels()) + xValAverageLength);
        for (int i = 0; i < max; i++) {
            a.append('h');
        }
        this.mXAxis.mLabelWidth = Utils.calcTextWidth(this.mAxisLabelPaint, a.toString());
        this.mXAxis.mLabelHeight = Utils.calcTextHeight(this.mAxisLabelPaint, "Q");
        this.mXAxis.setValues(xValues);
    }

    public void renderAxisLabels(Canvas c) {
        if (this.mXAxis.isEnabled() && this.mXAxis.isDrawLabelsEnabled()) {
            float yoffset = Utils.convertDpToPixel(4.0f);
            this.mAxisLabelPaint.setTypeface(this.mXAxis.getTypeface());
            this.mAxisLabelPaint.setTextSize(this.mXAxis.getTextSize());
            this.mAxisLabelPaint.setColor(this.mXAxis.getTextColor());
            if (this.mXAxis.getPosition() == XAxisPosition.TOP) {
                drawLabels(c, this.mViewPortHandler.offsetTop() - yoffset);
            } else if (this.mXAxis.getPosition() == XAxisPosition.BOTTOM) {
                drawLabels(c, (this.mViewPortHandler.contentBottom() + ((float) this.mXAxis.mLabelHeight)) + (1.5f * yoffset));
            } else if (this.mXAxis.getPosition() == XAxisPosition.BOTTOM_INSIDE) {
                drawLabels(c, this.mViewPortHandler.contentBottom() - yoffset);
            } else if (this.mXAxis.getPosition() == XAxisPosition.TOP_INSIDE) {
                drawLabels(c, (this.mViewPortHandler.offsetTop() + yoffset) + ((float) this.mXAxis.mLabelHeight));
            } else {
                drawLabels(c, this.mViewPortHandler.offsetTop() - yoffset);
                drawLabels(c, (this.mViewPortHandler.contentBottom() + ((float) this.mXAxis.mLabelHeight)) + (1.6f * yoffset));
            }
        }
    }

    public void renderAxisLine(Canvas c) {
        if (this.mXAxis.isDrawAxisLineEnabled() && this.mXAxis.isEnabled()) {
            this.mAxisLinePaint.setColor(this.mXAxis.getAxisLineColor());
            this.mAxisLinePaint.setStrokeWidth(this.mXAxis.getAxisLineWidth());
            if (this.mXAxis.getPosition() == XAxisPosition.TOP || this.mXAxis.getPosition() == XAxisPosition.TOP_INSIDE || this.mXAxis.getPosition() == XAxisPosition.BOTH_SIDED) {
                c.drawLine(this.mViewPortHandler.contentLeft(), this.mViewPortHandler.contentTop(), this.mViewPortHandler.contentRight(), this.mViewPortHandler.contentTop(), this.mAxisLinePaint);
            }
            if (this.mXAxis.getPosition() == XAxisPosition.BOTTOM || this.mXAxis.getPosition() == XAxisPosition.BOTTOM_INSIDE || this.mXAxis.getPosition() == XAxisPosition.BOTH_SIDED) {
                c.drawLine(this.mViewPortHandler.contentLeft(), this.mViewPortHandler.contentBottom(), this.mViewPortHandler.contentRight(), this.mViewPortHandler.contentBottom(), this.mAxisLinePaint);
            }
        }
    }

    protected void drawLabels(Canvas c, float pos) {
        float[] position = new float[]{0.0f, 0.0f};
        int i = this.mMinX;
        while (i <= this.mMaxX) {
            position[0] = (float) i;
            this.mTrans.pointValuesToPixel(position);
            if (this.mViewPortHandler.isInBoundsX(position[0])) {
                String label = (String) this.mXAxis.getValues().get(i);
                if (this.mXAxis.isAvoidFirstLastClippingEnabled()) {
                    if (i == this.mXAxis.getValues().size() - 1 && this.mXAxis.getValues().size() > 1) {
                        float width = (float) Utils.calcTextWidth(this.mAxisLabelPaint, label);
                        if (width > this.mViewPortHandler.offsetRight() * 2.0f && position[0] + width > this.mViewPortHandler.getChartWidth()) {
                            position[0] = position[0] - (width / 2.0f);
                        }
                    } else if (i == 0) {
                        position[0] = position[0] + (((float) Utils.calcTextWidth(this.mAxisLabelPaint, label)) / 2.0f);
                    }
                }
                drawLabel(c, label, i, position[0], pos);
            }
            i += this.mXAxis.mAxisLabelModulus;
        }
    }

    protected void drawLabel(Canvas c, String label, int xIndex, float x, float y) {
        c.drawText(this.mXAxis.getValueFormatter().getXValue(label, xIndex, this.mViewPortHandler), x, y, this.mAxisLabelPaint);
    }

    public void renderGridLines(Canvas c) {
        if (this.mXAxis.isDrawGridLinesEnabled() && this.mXAxis.isEnabled()) {
            float[] position = new float[]{0.0f, 0.0f};
            this.mGridPaint.setColor(this.mXAxis.getGridColor());
            this.mGridPaint.setStrokeWidth(this.mXAxis.getGridLineWidth());
            this.mGridPaint.setPathEffect(this.mXAxis.getGridDashPathEffect());
            Path gridLinePath = new Path();
            int i = this.mMinX;
            while (i <= this.mMaxX) {
                position[0] = (float) i;
                this.mTrans.pointValuesToPixel(position);
                if (position[0] >= this.mViewPortHandler.offsetLeft() && position[0] <= this.mViewPortHandler.getChartWidth()) {
                    gridLinePath.moveTo(position[0], this.mViewPortHandler.contentBottom());
                    gridLinePath.lineTo(position[0], this.mViewPortHandler.contentTop());
                    c.drawPath(gridLinePath, this.mGridPaint);
                }
                gridLinePath.reset();
                i += this.mXAxis.mAxisLabelModulus;
            }
        }
    }

    public void renderLimitLines(Canvas c) {
        List<LimitLine> limitLines = this.mXAxis.getLimitLines();
        if (limitLines != null && limitLines.size() > 0) {
            float[] position = new float[2];
            for (int i = 0; i < limitLines.size(); i++) {
                LimitLine l = (LimitLine) limitLines.get(i);
                if (l.isEnabled()) {
                    position[0] = l.getLimit();
                    position[1] = 0.0f;
                    this.mTrans.pointValuesToPixel(position);
                    renderLimitLineLine(c, l, position);
                    renderLimitLineLabel(c, l, position, 2.0f + l.getYOffset());
                }
            }
        }
    }

    public void renderLimitLineLine(Canvas c, LimitLine limitLine, float[] position) {
        this.mLimitLineSegmentsBuffer[0] = position[0];
        this.mLimitLineSegmentsBuffer[1] = this.mViewPortHandler.contentTop();
        this.mLimitLineSegmentsBuffer[2] = position[0];
        this.mLimitLineSegmentsBuffer[3] = this.mViewPortHandler.contentBottom();
        this.mLimitLinePath.reset();
        this.mLimitLinePath.moveTo(this.mLimitLineSegmentsBuffer[0], this.mLimitLineSegmentsBuffer[1]);
        this.mLimitLinePath.lineTo(this.mLimitLineSegmentsBuffer[2], this.mLimitLineSegmentsBuffer[3]);
        this.mLimitLinePaint.setStyle(Style.STROKE);
        this.mLimitLinePaint.setColor(limitLine.getLineColor());
        this.mLimitLinePaint.setStrokeWidth(limitLine.getLineWidth());
        this.mLimitLinePaint.setPathEffect(limitLine.getDashPathEffect());
        c.drawPath(this.mLimitLinePath, this.mLimitLinePaint);
    }

    public void renderLimitLineLabel(Canvas c, LimitLine limitLine, float[] position, float yOffset) {
        String label = limitLine.getLabel();
        if (label != null && !label.equals("")) {
            this.mLimitLinePaint.setStyle(limitLine.getTextStyle());
            this.mLimitLinePaint.setPathEffect(null);
            this.mLimitLinePaint.setColor(limitLine.getTextColor());
            this.mLimitLinePaint.setStrokeWidth(0.5f);
            this.mLimitLinePaint.setTextSize(limitLine.getTextSize());
            float xOffset = limitLine.getLineWidth() + limitLine.getXOffset();
            LimitLabelPosition labelPosition = limitLine.getLabelPosition();
            if (labelPosition == LimitLabelPosition.RIGHT_TOP) {
                float labelLineHeight = (float) Utils.calcTextHeight(this.mLimitLinePaint, label);
                this.mLimitLinePaint.setTextAlign(Align.LEFT);
                c.drawText(label, position[0] + xOffset, (this.mViewPortHandler.contentTop() + yOffset) + labelLineHeight, this.mLimitLinePaint);
            } else if (labelPosition == LimitLabelPosition.RIGHT_BOTTOM) {
                this.mLimitLinePaint.setTextAlign(Align.LEFT);
                c.drawText(label, position[0] + xOffset, this.mViewPortHandler.contentBottom() - yOffset, this.mLimitLinePaint);
            } else if (labelPosition == LimitLabelPosition.LEFT_TOP) {
                this.mLimitLinePaint.setTextAlign(Align.RIGHT);
                c.drawText(label, position[0] - xOffset, (this.mViewPortHandler.contentTop() + yOffset) + ((float) Utils.calcTextHeight(this.mLimitLinePaint, label)), this.mLimitLinePaint);
            } else {
                this.mLimitLinePaint.setTextAlign(Align.RIGHT);
                c.drawText(label, position[0] - xOffset, this.mViewPortHandler.contentBottom() - yOffset, this.mLimitLinePaint);
            }
        }
    }
}
