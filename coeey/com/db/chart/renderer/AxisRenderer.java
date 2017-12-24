package com.db.chart.renderer;

import android.graphics.Canvas;
import com.db.chart.model.ChartEntry;
import com.db.chart.model.ChartSet;
import com.db.chart.view.ChartView.Style;
import com.google.android.flexbox.FlexItem;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;

public abstract class AxisRenderer {
    private static final float DEFAULT_STEPS_NUMBER = 3.0f;
    float axisPosition;
    boolean handleValues;
    ArrayList<String> labels;
    ArrayList<Float> labelsPos;
    float labelsStaticPos;
    ArrayList<Float> labelsValues;
    float mInnerChartBottom;
    float mInnerChartLeft;
    float mInnerChartRight;
    float mInnerChartTop;
    float mandatoryBorderSpacing;
    private float maxLabelValue;
    float minLabelValue;
    float screenStep;
    private float step;
    Style style;

    public enum LabelPosition {
        NONE,
        OUTSIDE,
        INSIDE
    }

    protected abstract float defineAxisPosition();

    protected abstract float defineStaticLabelsPosition(float f, int i);

    protected abstract void draw(Canvas canvas);

    protected abstract float measureInnerChartBottom(int i);

    protected abstract float measureInnerChartLeft(int i);

    protected abstract float measureInnerChartRight(int i);

    protected abstract float measureInnerChartTop(int i);

    public abstract float parsePos(int i, double d);

    AxisRenderer() {
        reset();
    }

    public void init(ArrayList<ChartSet> data, Style style) {
        if (this.handleValues) {
            if (this.minLabelValue == 0.0f && this.maxLabelValue == 0.0f) {
                float[] borders;
                if (hasStep()) {
                    borders = findBorders(data, this.step);
                } else {
                    borders = findBorders(data);
                }
                this.minLabelValue = borders[0];
                this.maxLabelValue = borders[1];
            }
            if (!hasStep()) {
                setBorderValues(this.minLabelValue, this.maxLabelValue);
            }
            this.labelsValues = calculateValues(this.minLabelValue, this.maxLabelValue, this.step);
            this.labels = convertToLabelsFormat(this.labelsValues, style.getLabelsFormat());
        } else {
            this.labels = extractLabels(data);
        }
        this.style = style;
    }

    void dispose() {
        this.axisPosition = defineAxisPosition();
        this.labelsStaticPos = defineStaticLabelsPosition(this.axisPosition, this.style.getAxisLabelsSpacing());
    }

    public void measure(int left, int top, int right, int bottom) {
        this.mInnerChartLeft = measureInnerChartLeft(left);
        this.mInnerChartTop = measureInnerChartTop(top);
        this.mInnerChartRight = measureInnerChartRight(right);
        this.mInnerChartBottom = measureInnerChartBottom(bottom);
    }

    public void reset() {
        this.mandatoryBorderSpacing = 0.0f;
        this.step = FlexItem.FLEX_BASIS_PERCENT_DEFAULT;
        this.labelsStaticPos = 0.0f;
        this.axisPosition = 0.0f;
        this.minLabelValue = 0.0f;
        this.maxLabelValue = 0.0f;
        this.handleValues = false;
    }

    void defineMandatoryBorderSpacing(float innerStart, float innerEnd) {
        if (this.mandatoryBorderSpacing == FlexItem.FLEX_SHRINK_DEFAULT) {
            this.mandatoryBorderSpacing = (((innerEnd - innerStart) - ((float) (this.style.getAxisBorderSpacing() * 2))) / ((float) this.labels.size())) / 2.0f;
        }
    }

    void defineLabelsPosition(float innerStart, float innerEnd) {
        int nLabels = this.labels.size();
        this.screenStep = ((((innerEnd - innerStart) - ((float) this.style.getAxisTopSpacing())) - ((float) (this.style.getAxisBorderSpacing() * 2))) - (this.mandatoryBorderSpacing * 2.0f)) / ((float) (nLabels - 1));
        this.labelsPos = new ArrayList(nLabels);
        float currPos = (((float) this.style.getAxisBorderSpacing()) + innerStart) + this.mandatoryBorderSpacing;
        for (int i = 0; i < nLabels; i++) {
            this.labelsPos.add(Float.valueOf(currPos));
            currPos += this.screenStep;
        }
    }

    ArrayList<String> convertToLabelsFormat(ArrayList<Float> values, DecimalFormat format) {
        int size = values.size();
        ArrayList<String> result = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            result.add(format.format(values.get(i)));
        }
        return result;
    }

    ArrayList<String> extractLabels(ArrayList<ChartSet> sets) {
        int size = ((ChartSet) sets.get(0)).size();
        ArrayList<String> result = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            result.add(((ChartSet) sets.get(0)).getLabel(i));
        }
        return result;
    }

    float[] findBorders(ArrayList<ChartSet> sets) {
        float max = -2.14748365E9f;
        float min = 2.14748365E9f;
        Iterator it = sets.iterator();
        while (it.hasNext()) {
            Iterator it2 = ((ChartSet) it.next()).getEntries().iterator();
            while (it2.hasNext()) {
                ChartEntry e = (ChartEntry) it2.next();
                if (e.getValue() >= max) {
                    max = e.getValue();
                }
                if (e.getValue() <= min) {
                    min = e.getValue();
                }
            }
        }
        if (max < 0.0f) {
            max = 0.0f;
        }
        if (min > 0.0f) {
            min = 0.0f;
        }
        if (min == max) {
            max += FlexItem.FLEX_SHRINK_DEFAULT;
        }
        return new float[]{min, max};
    }

    float[] findBorders(ArrayList<ChartSet> sets, float step) {
        float[] borders = findBorders(sets);
        while ((borders[1] - borders[0]) % step != 0.0f) {
            borders[1] = borders[1] + FlexItem.FLEX_SHRINK_DEFAULT;
        }
        return borders;
    }

    ArrayList<Float> calculateValues(float min, float max, float step) {
        ArrayList<Float> result = new ArrayList();
        float pos = min;
        while (pos <= max) {
            result.add(Float.valueOf(pos));
            pos += step;
        }
        if (((Float) result.get(result.size() - 1)).floatValue() < max) {
            result.add(Float.valueOf(pos));
        }
        return result;
    }

    public float getInnerChartLeft() {
        return this.mInnerChartLeft;
    }

    public float getInnerChartTop() {
        return this.mInnerChartTop;
    }

    public float getInnerChartRight() {
        return this.mInnerChartRight;
    }

    public float getInnerChartBottom() {
        return this.mInnerChartBottom;
    }

    public float[] getInnerChartBounds() {
        return new float[]{this.mInnerChartLeft, this.mInnerChartTop, this.mInnerChartRight, this.mInnerChartBottom};
    }

    public float getStep() {
        return this.step;
    }

    public void setStep(int step) {
        this.step = (float) step;
    }

    public float getBorderMaximumValue() {
        return this.maxLabelValue;
    }

    public float getBorderMinimumValue() {
        return this.minLabelValue;
    }

    public boolean hasMandatoryBorderSpacing() {
        return this.mandatoryBorderSpacing == FlexItem.FLEX_SHRINK_DEFAULT;
    }

    boolean hasStep() {
        return this.step != FlexItem.FLEX_BASIS_PERCENT_DEFAULT;
    }

    public void setHandleValues(boolean bool) {
        this.handleValues = bool;
    }

    public void setMandatoryBorderSpacing(boolean bool) {
        this.mandatoryBorderSpacing = bool ? FlexItem.FLEX_SHRINK_DEFAULT : 0.0f;
    }

    public void setInnerChartBounds(float left, float top, float right, float bottom) {
        this.mInnerChartLeft = left;
        this.mInnerChartTop = top;
        this.mInnerChartRight = right;
        this.mInnerChartBottom = bottom;
    }

    public void setBorderValues(float min, float max, float step) {
        if (min >= max) {
            throw new IllegalArgumentException("Minimum border value must be greater than maximum values");
        }
        this.step = step;
        this.maxLabelValue = max;
        this.minLabelValue = min;
    }

    public void setBorderValues(float min, float max) {
        if (!hasStep()) {
            this.step = (max - min) / DEFAULT_STEPS_NUMBER;
        }
        setBorderValues(min, max, this.step);
    }
}
