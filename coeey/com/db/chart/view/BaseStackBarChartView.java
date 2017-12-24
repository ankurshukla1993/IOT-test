package com.db.chart.view;

import android.content.Context;
import android.util.AttributeSet;
import com.db.chart.model.Bar;
import com.db.chart.model.BarSet;
import com.db.chart.model.ChartSet;
import com.google.android.flexbox.FlexItem;
import java.util.ArrayList;

public abstract class BaseStackBarChartView extends BaseBarChartView {
    private boolean mCalcMaxValue = true;

    public BaseStackBarChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseStackBarChartView(Context context) {
        super(context);
    }

    static int discoverBottomSet(int entryIndex, ArrayList<ChartSet> data) {
        int index;
        int dataSize = data.size();
        boolean hasNegativeValues = false;
        for (index = 0; index < dataSize; index++) {
            if (((ChartSet) data.get(index)).getEntry(entryIndex).getValue() < 0.0f) {
                hasNegativeValues = true;
                break;
            }
        }
        if (hasNegativeValues) {
            index = dataSize - 1;
            while (index >= 0 && ((ChartSet) data.get(index)).getEntry(entryIndex).getValue() >= 0.0f) {
                index--;
            }
        } else {
            index = 0;
            while (index < dataSize && ((ChartSet) data.get(index)).getEntry(entryIndex).getValue() == 0.0f) {
                index++;
            }
        }
        return index;
    }

    static int discoverTopSet(int entryIndex, ArrayList<ChartSet> data) {
        int index;
        int dataSize = data.size();
        boolean hasPositiveValues = false;
        for (index = 0; index < dataSize; index++) {
            if (((ChartSet) data.get(index)).getEntry(entryIndex).getValue() > 0.0f) {
                hasPositiveValues = true;
                break;
            }
        }
        if (hasPositiveValues) {
            index = dataSize - 1;
            while (index >= 0 && ((ChartSet) data.get(index)).getEntry(entryIndex).getValue() <= 0.0f) {
                index--;
            }
        } else {
            index = 0;
            while (index < dataSize && ((ChartSet) data.get(index)).getEntry(entryIndex).getValue() == 0.0f) {
                index++;
            }
        }
        return index;
    }

    void calculateBarsWidth(int nSets, float x0, float x1) {
        this.barWidth = (x1 - x0) - this.style.barSpacing;
    }

    private void calculateMaxStackBarValue() {
        int maxStackValue = 0;
        int minStackValue = 0;
        int dataSize = this.data.size();
        int setSize = ((ChartSet) this.data.get(0)).size();
        for (int i = 0; i < setSize; i++) {
            float positiveStackValue = 0.0f;
            float negativeStackValue = 0.0f;
            for (int j = 0; j < dataSize; j++) {
                Bar bar = (Bar) ((BarSet) this.data.get(j)).getEntry(i);
                if (bar.getValue() >= 0.0f) {
                    positiveStackValue += bar.getValue();
                } else {
                    negativeStackValue += bar.getValue();
                }
            }
            if (maxStackValue < ((int) Math.ceil((double) positiveStackValue))) {
                maxStackValue = (int) Math.ceil((double) positiveStackValue);
            }
            if (minStackValue > ((int) Math.ceil((double) (negativeStackValue * FlexItem.FLEX_BASIS_PERCENT_DEFAULT))) * -1) {
                minStackValue = ((int) Math.ceil((double) (negativeStackValue * FlexItem.FLEX_BASIS_PERCENT_DEFAULT))) * -1;
            }
        }
        super.setAxisBorderValues((float) minStackValue, (float) maxStackValue, getStep());
    }

    public void show() {
        if (this.mCalcMaxValue) {
            calculateMaxStackBarValue();
        }
        super.show();
    }

    public ChartView setAxisBorderValues(float minValue, float maxValue, float step) {
        this.mCalcMaxValue = false;
        return super.setAxisBorderValues(minValue, maxValue, step);
    }
}
