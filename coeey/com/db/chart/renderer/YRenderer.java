package com.db.chart.renderer;

import android.graphics.Canvas;
import android.graphics.Paint.Align;
import com.db.chart.renderer.AxisRenderer.LabelPosition;
import java.util.Collections;
import java.util.Iterator;

public class YRenderer extends AxisRenderer {
    public void dispose() {
        super.dispose();
        defineMandatoryBorderSpacing(this.mInnerChartTop, this.mInnerChartBottom);
        defineLabelsPosition(this.mInnerChartTop, this.mInnerChartBottom);
    }

    protected float defineAxisPosition() {
        float result = this.mInnerChartLeft;
        if (this.style.hasYAxis()) {
            return result - (this.style.getAxisThickness() / 2.0f);
        }
        return result;
    }

    protected float defineStaticLabelsPosition(float axisCoordinate, int distanceToAxis) {
        float result = axisCoordinate;
        if (this.style.getYLabelsPositioning() == LabelPosition.INSIDE) {
            result += (float) distanceToAxis;
            if (this.style.hasYAxis()) {
                return result + (this.style.getAxisThickness() / 2.0f);
            }
            return result;
        } else if (this.style.getYLabelsPositioning() != LabelPosition.OUTSIDE) {
            return result;
        } else {
            result -= (float) distanceToAxis;
            if (this.style.hasYAxis()) {
                return result - (this.style.getAxisThickness() / 2.0f);
            }
            return result;
        }
    }

    public void draw(Canvas canvas) {
        if (this.style.hasYAxis()) {
            float bottom = this.mInnerChartBottom;
            if (this.style.hasXAxis()) {
                bottom += this.style.getAxisThickness();
            }
            canvas.drawLine(this.axisPosition, this.mInnerChartTop, this.axisPosition, bottom, this.style.getChartPaint());
        }
        if (this.style.getYLabelsPositioning() != LabelPosition.NONE) {
            this.style.getLabelsPaint().setTextAlign(this.style.getYLabelsPositioning() == LabelPosition.OUTSIDE ? Align.RIGHT : Align.LEFT);
            int nLabels = this.labels.size();
            for (int i = 0; i < nLabels; i++) {
                canvas.drawText((String) this.labels.get(i), this.labelsStaticPos, ((float) (this.style.getLabelHeight((String) this.labels.get(i)) / 2)) + ((Float) this.labelsPos.get(i)).floatValue(), this.style.getLabelsPaint());
            }
        }
    }

    void defineLabelsPosition(float innerStart, float innerEnd) {
        super.defineLabelsPosition(innerStart, innerEnd);
        Collections.reverse(this.labelsPos);
    }

    public float parsePos(int index, double value) {
        if (!this.handleValues) {
            return ((Float) this.labelsPos.get(index)).floatValue();
        }
        return (float) (((double) this.mInnerChartBottom) - ((((double) this.screenStep) * (value - ((double) this.minLabelValue))) / ((double) (((Float) this.labelsValues.get(1)).floatValue() - this.minLabelValue))));
    }

    protected float measureInnerChartLeft(int left) {
        float result = (float) left;
        if (this.style.hasYAxis()) {
            result += this.style.getAxisThickness();
        }
        if (this.style.getYLabelsPositioning() != LabelPosition.OUTSIDE) {
            return result;
        }
        float maxLabelLength = 0.0f;
        Iterator it = this.labels.iterator();
        while (it.hasNext()) {
            float aux = this.style.getLabelsPaint().measureText((String) it.next());
            if (aux > maxLabelLength) {
                maxLabelLength = aux;
            }
        }
        return result + (((float) this.style.getAxisLabelsSpacing()) + maxLabelLength);
    }

    protected float measureInnerChartTop(int top) {
        return (float) top;
    }

    protected float measureInnerChartRight(int right) {
        return (float) right;
    }

    protected float measureInnerChartBottom(int bottom) {
        if (this.style.getYLabelsPositioning() == LabelPosition.NONE || this.style.getAxisBorderSpacing() >= this.style.getFontMaxHeight() / 2) {
            return (float) bottom;
        }
        return (float) (bottom - (this.style.getFontMaxHeight() / 2));
    }
}
