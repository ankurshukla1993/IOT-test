package com.github.mikephil.charting.data;

import android.content.Context;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import com.github.mikephil.charting.formatter.DefaultFillFormatter;
import com.github.mikephil.charting.formatter.FillFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.Utils;
import com.google.android.flexbox.FlexItem;
import java.util.ArrayList;
import java.util.List;

public class LineDataSet extends LineRadarDataSet<Entry> {
    private int mCircleColorHole;
    private List<Integer> mCircleColors;
    private float mCircleSize;
    private float mCubicIntensity;
    private DashPathEffect mDashPathEffect;
    private boolean mDrawCircleHole;
    private boolean mDrawCircles;
    private boolean mDrawCubic;
    private FillFormatter mFillFormatter;

    public LineDataSet(List<Entry> yVals, String label) {
        super(yVals, label);
        this.mCircleColors = null;
        this.mCircleColorHole = -1;
        this.mCircleSize = 8.0f;
        this.mCubicIntensity = 0.2f;
        this.mDashPathEffect = null;
        this.mFillFormatter = new DefaultFillFormatter();
        this.mDrawCircles = true;
        this.mDrawCubic = false;
        this.mDrawCircleHole = true;
        this.mCircleColors = new ArrayList();
        this.mCircleColors.add(Integer.valueOf(Color.rgb(140, 234, 255)));
    }

    public DataSet<Entry> copy() {
        List<Entry> yVals = new ArrayList();
        for (int i = 0; i < this.mYVals.size(); i++) {
            yVals.add(((Entry) this.mYVals.get(i)).copy());
        }
        LineDataSet copied = new LineDataSet(yVals, getLabel());
        copied.mColors = this.mColors;
        copied.mCircleSize = this.mCircleSize;
        copied.mCircleColors = this.mCircleColors;
        copied.mDashPathEffect = this.mDashPathEffect;
        copied.mDrawCircles = this.mDrawCircles;
        copied.mDrawCubic = this.mDrawCubic;
        copied.mHighLightColor = this.mHighLightColor;
        return copied;
    }

    public void setCubicIntensity(float intensity) {
        if (intensity > FlexItem.FLEX_SHRINK_DEFAULT) {
            intensity = FlexItem.FLEX_SHRINK_DEFAULT;
        }
        if (intensity < 0.05f) {
            intensity = 0.05f;
        }
        this.mCubicIntensity = intensity;
    }

    public float getCubicIntensity() {
        return this.mCubicIntensity;
    }

    public void setCircleSize(float size) {
        this.mCircleSize = Utils.convertDpToPixel(size);
    }

    public float getCircleSize() {
        return this.mCircleSize;
    }

    public void enableDashedLine(float lineLength, float spaceLength, float phase) {
        this.mDashPathEffect = new DashPathEffect(new float[]{lineLength, spaceLength}, phase);
    }

    public void disableDashedLine() {
        this.mDashPathEffect = null;
    }

    public boolean isDashedLineEnabled() {
        return this.mDashPathEffect != null;
    }

    public DashPathEffect getDashPathEffect() {
        return this.mDashPathEffect;
    }

    public void setDrawCircles(boolean enabled) {
        this.mDrawCircles = enabled;
    }

    public boolean isDrawCirclesEnabled() {
        return this.mDrawCircles;
    }

    public void setDrawCubic(boolean enabled) {
        this.mDrawCubic = enabled;
    }

    public boolean isDrawCubicEnabled() {
        return this.mDrawCubic;
    }

    public List<Integer> getCircleColors() {
        return this.mCircleColors;
    }

    public int getCircleColor(int index) {
        return ((Integer) this.mCircleColors.get(index % this.mCircleColors.size())).intValue();
    }

    public void setCircleColors(List<Integer> colors) {
        this.mCircleColors = colors;
    }

    public void setCircleColors(int[] colors) {
        this.mCircleColors = ColorTemplate.createColors(colors);
    }

    public void setCircleColors(int[] colors, Context c) {
        List<Integer> clrs = new ArrayList();
        for (int color : colors) {
            clrs.add(Integer.valueOf(c.getResources().getColor(color)));
        }
        this.mCircleColors = clrs;
    }

    public void setCircleColor(int color) {
        resetCircleColors();
        this.mCircleColors.add(Integer.valueOf(color));
    }

    public void resetCircleColors() {
        this.mCircleColors = new ArrayList();
    }

    public void setCircleColorHole(int color) {
        this.mCircleColorHole = color;
    }

    public int getCircleHoleColor() {
        return this.mCircleColorHole;
    }

    public void setDrawCircleHole(boolean enabled) {
        this.mDrawCircleHole = enabled;
    }

    public boolean isDrawCircleHoleEnabled() {
        return this.mDrawCircleHole;
    }

    public void setFillFormatter(FillFormatter formatter) {
        if (formatter == null) {
            this.mFillFormatter = new DefaultFillFormatter();
        } else {
            this.mFillFormatter = formatter;
        }
    }

    public FillFormatter getFillFormatter() {
        return this.mFillFormatter;
    }
}
