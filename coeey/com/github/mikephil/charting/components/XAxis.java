package com.github.mikephil.charting.components;

import com.github.mikephil.charting.formatter.DefaultXAxisValueFormatter;
import com.github.mikephil.charting.formatter.XAxisValueFormatter;
import java.util.ArrayList;
import java.util.List;

public class XAxis extends AxisBase {
    private boolean mAvoidFirstLastClipping = false;
    public int mAxisLabelModulus = 1;
    private boolean mIsAxisModulusCustom = false;
    public int mLabelHeight = 1;
    public int mLabelWidth = 1;
    private XAxisPosition mPosition = XAxisPosition.TOP;
    private int mSpaceBetweenLabels = 4;
    protected List<String> mValues = new ArrayList();
    protected XAxisValueFormatter mXAxisValueFormatter = new DefaultXAxisValueFormatter();
    public int mYAxisLabelModulus = 1;

    public enum XAxisPosition {
        TOP,
        BOTTOM,
        BOTH_SIDED,
        TOP_INSIDE,
        BOTTOM_INSIDE
    }

    public XAxisPosition getPosition() {
        return this.mPosition;
    }

    public void setPosition(XAxisPosition pos) {
        this.mPosition = pos;
    }

    public void setSpaceBetweenLabels(int spaceCharacters) {
        this.mSpaceBetweenLabels = spaceCharacters;
    }

    public void setLabelsToSkip(int count) {
        if (count < 0) {
            count = 0;
        }
        this.mIsAxisModulusCustom = true;
        this.mAxisLabelModulus = count + 1;
    }

    public void resetLabelsToSkip() {
        this.mIsAxisModulusCustom = false;
    }

    public boolean isAxisModulusCustom() {
        return this.mIsAxisModulusCustom;
    }

    public int getSpaceBetweenLabels() {
        return this.mSpaceBetweenLabels;
    }

    public void setAvoidFirstLastClipping(boolean enabled) {
        this.mAvoidFirstLastClipping = enabled;
    }

    public boolean isAvoidFirstLastClippingEnabled() {
        return this.mAvoidFirstLastClipping;
    }

    public void setValues(List<String> values) {
        this.mValues = values;
    }

    public List<String> getValues() {
        return this.mValues;
    }

    public void setValueFormatter(XAxisValueFormatter formatter) {
        if (formatter == null) {
            this.mXAxisValueFormatter = new DefaultXAxisValueFormatter();
        } else {
            this.mXAxisValueFormatter = formatter;
        }
    }

    public XAxisValueFormatter getValueFormatter() {
        return this.mXAxisValueFormatter;
    }

    public String getLongestLabel() {
        String longest = "";
        for (int i = 0; i < this.mValues.size(); i++) {
            String text = (String) this.mValues.get(i);
            if (longest.length() < text.length()) {
                longest = text;
            }
        }
        return longest;
    }
}
