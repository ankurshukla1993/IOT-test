package com.db.chart.renderer;

import android.graphics.Canvas;
import android.graphics.Paint.Align;
import com.db.chart.renderer.AxisRenderer.LabelPosition;

public class XRenderer extends AxisRenderer {
    public void dispose() {
        super.dispose();
        defineMandatoryBorderSpacing(this.mInnerChartLeft, this.mInnerChartRight);
        defineLabelsPosition(this.mInnerChartLeft, this.mInnerChartRight);
    }

    protected float defineAxisPosition() {
        float result = this.mInnerChartBottom;
        if (this.style.hasXAxis()) {
            return result + (this.style.getAxisThickness() / 2.0f);
        }
        return result;
    }

    protected float defineStaticLabelsPosition(float axisCoordinate, int distanceToAxis) {
        float result = axisCoordinate;
        if (this.style.getXLabelsPositioning() == LabelPosition.INSIDE) {
            result = (result - ((float) distanceToAxis)) - this.style.getLabelsPaint().descent();
            if (this.style.hasXAxis()) {
                return result - (this.style.getAxisThickness() / 2.0f);
            }
            return result;
        } else if (this.style.getXLabelsPositioning() != LabelPosition.OUTSIDE) {
            return result;
        } else {
            result = (result + ((float) distanceToAxis)) + (((float) this.style.getFontMaxHeight()) - this.style.getLabelsPaint().descent());
            if (this.style.hasXAxis()) {
                return result + (this.style.getAxisThickness() / 2.0f);
            }
            return result;
        }
    }

    public void draw(Canvas canvas) {
        if (this.style.hasXAxis()) {
            canvas.drawLine(this.mInnerChartLeft, this.axisPosition, this.mInnerChartRight, this.axisPosition, this.style.getChartPaint());
        }
        if (this.style.getXLabelsPositioning() != LabelPosition.NONE) {
            this.style.getLabelsPaint().setTextAlign(Align.CENTER);
            int nLabels = this.labels.size();
            for (int i = 0; i < nLabels; i++) {
                canvas.drawText((String) this.labels.get(i), ((Float) this.labelsPos.get(i)).floatValue(), this.labelsStaticPos, this.style.getLabelsPaint());
            }
        }
    }

    public float parsePos(int index, double value) {
        if (!this.handleValues) {
            return ((Float) this.labelsPos.get(index)).floatValue();
        }
        return (float) (((((double) this.screenStep) * (value - ((double) this.minLabelValue))) / ((double) (((Float) this.labelsValues.get(1)).floatValue() - this.minLabelValue))) + ((double) this.mInnerChartLeft));
    }

    protected float measureInnerChartLeft(int left) {
        return this.style.getXLabelsPositioning() != LabelPosition.NONE ? this.style.getLabelsPaint().measureText((String) this.labels.get(0)) / 2.0f : (float) left;
    }

    protected float measureInnerChartTop(int top) {
        return (float) top;
    }

    protected float measureInnerChartRight(int right) {
        float lastLabelWidth = 0.0f;
        if (this.labels.size() > 0) {
            lastLabelWidth = this.style.getLabelsPaint().measureText((String) this.labels.get(this.labels.size() - 1));
        }
        float rightBorder = 0.0f;
        if (this.style.getXLabelsPositioning() != LabelPosition.NONE && ((float) this.style.getAxisBorderSpacing()) + this.mandatoryBorderSpacing < lastLabelWidth / 2.0f) {
            rightBorder = (lastLabelWidth / 2.0f) - (((float) this.style.getAxisBorderSpacing()) + this.mandatoryBorderSpacing);
        }
        return ((float) right) - rightBorder;
    }

    protected float measureInnerChartBottom(int bottom) {
        float result = (float) bottom;
        if (this.style.hasXAxis()) {
            result -= this.style.getAxisThickness();
        }
        if (this.style.getXLabelsPositioning() == LabelPosition.OUTSIDE) {
            return result - ((float) (this.style.getFontMaxHeight() + this.style.getAxisLabelsSpacing()));
        }
        return result;
    }
}
