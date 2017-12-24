package com.github.mikephil.charting.renderer;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.RectF;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.buffer.BarBuffer;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.BarDataProvider;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import java.util.List;

public class BarChartRenderer extends DataRenderer {
    protected BarBuffer[] mBarBuffers;
    protected RectF mBarRect = new RectF();
    protected BarDataProvider mChart;
    protected Paint mShadowPaint;

    public BarChartRenderer(BarDataProvider chart, ChartAnimator animator, ViewPortHandler viewPortHandler) {
        super(animator, viewPortHandler);
        this.mChart = chart;
        this.mHighlightPaint = new Paint(1);
        this.mHighlightPaint.setStyle(Style.FILL);
        this.mHighlightPaint.setColor(Color.rgb(0, 0, 0));
        this.mHighlightPaint.setAlpha(120);
        this.mShadowPaint = new Paint(1);
        this.mShadowPaint.setStyle(Style.FILL);
    }

    public void initBuffers() {
        BarData barData = this.mChart.getBarData();
        this.mBarBuffers = new BarBuffer[barData.getDataSetCount()];
        for (int i = 0; i < this.mBarBuffers.length; i++) {
            BarDataSet set = (BarDataSet) barData.getDataSetByIndex(i);
            this.mBarBuffers[i] = new BarBuffer((set.getValueCount() * 4) * set.getStackSize(), barData.getGroupSpace(), barData.getDataSetCount(), set.isStacked());
        }
    }

    public void drawData(Canvas c) {
        BarData barData = this.mChart.getBarData();
        for (int i = 0; i < barData.getDataSetCount(); i++) {
            BarDataSet set = (BarDataSet) barData.getDataSetByIndex(i);
            if (set.isVisible() && set.getEntryCount() > 0) {
                drawDataSet(c, set, i);
            }
        }
    }

    protected void drawDataSet(Canvas c, BarDataSet dataSet, int index) {
        Transformer trans = this.mChart.getTransformer(dataSet.getAxisDependency());
        this.mShadowPaint.setColor(dataSet.getBarShadowColor());
        float phaseX = this.mAnimator.getPhaseX();
        float phaseY = this.mAnimator.getPhaseY();
        List<BarEntry> entries = dataSet.getYVals();
        BarBuffer buffer = this.mBarBuffers[index];
        buffer.setPhases(phaseX, phaseY);
        buffer.setBarSpace(dataSet.getBarSpace());
        buffer.setDataSet(index);
        buffer.setInverted(this.mChart.isInverted(dataSet.getAxisDependency()));
        buffer.feed(entries);
        trans.pointValuesToPixel(buffer.buffer);
        int j;
        if (dataSet.getColors().size() > 1) {
            for (j = 0; j < buffer.size(); j += 4) {
                if (this.mViewPortHandler.isInBoundsLeft(buffer.buffer[j + 2])) {
                    if (this.mViewPortHandler.isInBoundsRight(buffer.buffer[j])) {
                        if (this.mChart.isDrawBarShadowEnabled()) {
                            c.drawRect(buffer.buffer[j], this.mViewPortHandler.contentTop(), buffer.buffer[j + 2], this.mViewPortHandler.contentBottom(), this.mShadowPaint);
                        }
                        this.mRenderPaint.setColor(dataSet.getColor(j / 4));
                        c.drawRect(buffer.buffer[j], buffer.buffer[j + 1], buffer.buffer[j + 2], buffer.buffer[j + 3], this.mRenderPaint);
                    } else {
                        return;
                    }
                }
            }
            return;
        }
        this.mRenderPaint.setColor(dataSet.getColor());
        for (j = 0; j < buffer.size(); j += 4) {
            if (this.mViewPortHandler.isInBoundsLeft(buffer.buffer[j + 2])) {
                if (this.mViewPortHandler.isInBoundsRight(buffer.buffer[j])) {
                    if (this.mChart.isDrawBarShadowEnabled()) {
                        c.drawRect(buffer.buffer[j], this.mViewPortHandler.contentTop(), buffer.buffer[j + 2], this.mViewPortHandler.contentBottom(), this.mShadowPaint);
                    }
                    c.drawRect(buffer.buffer[j], buffer.buffer[j + 1], buffer.buffer[j + 2], buffer.buffer[j + 3], this.mRenderPaint);
                } else {
                    return;
                }
            }
        }
    }

    protected void prepareBarHighlight(float x, float y1, float y2, float barspaceHalf, Transformer trans) {
        float top = y1;
        this.mBarRect.set((x - 0.5f) + barspaceHalf, top, (x + 0.5f) - barspaceHalf, y2);
        trans.rectValueToPixel(this.mBarRect, this.mAnimator.getPhaseY());
    }

    public void drawValues(Canvas c) {
        if (passesCheck()) {
            List<BarDataSet> dataSets = this.mChart.getBarData().getDataSets();
            float valueOffsetPlus = Utils.convertDpToPixel(4.5f);
            boolean drawValueAboveBar = this.mChart.isDrawValueAboveBarEnabled();
            for (int i = 0; i < this.mChart.getBarData().getDataSetCount(); i++) {
                DataSet dataSet = (BarDataSet) dataSets.get(i);
                if (dataSet.isDrawValuesEnabled() && dataSet.getEntryCount() != 0) {
                    float negOffset;
                    applyValueTextStyle(dataSet);
                    boolean isInverted = this.mChart.isInverted(dataSet.getAxisDependency());
                    float valueTextHeight = (float) Utils.calcTextHeight(this.mValuePaint, "8");
                    float posOffset = drawValueAboveBar ? -valueOffsetPlus : valueTextHeight + valueOffsetPlus;
                    if (drawValueAboveBar) {
                        negOffset = valueTextHeight + valueOffsetPlus;
                    } else {
                        negOffset = -valueOffsetPlus;
                    }
                    if (isInverted) {
                        posOffset = (-posOffset) - valueTextHeight;
                        negOffset = (-negOffset) - valueTextHeight;
                    }
                    Transformer trans = this.mChart.getTransformer(dataSet.getAxisDependency());
                    List<BarEntry> entries = dataSet.getYVals();
                    float[] valuePoints = getTransformedValues(trans, entries, i);
                    int j;
                    if (dataSet.isStacked()) {
                        j = 0;
                        while (((float) j) < ((float) (valuePoints.length - 1)) * this.mAnimator.getPhaseX()) {
                            Entry entry = (BarEntry) entries.get(j / 2);
                            float[] vals = entry.getVals();
                            if (vals == null) {
                                if (!this.mViewPortHandler.isInBoundsRight(valuePoints[j])) {
                                    break;
                                } else if (this.mViewPortHandler.isInBoundsY(valuePoints[j + 1]) && this.mViewPortHandler.isInBoundsLeft(valuePoints[j])) {
                                    drawValue(c, dataSet.getValueFormatter(), entry.getVal(), entry, i, valuePoints[j], valuePoints[j + 1] + (entry.getVal() >= 0.0f ? posOffset : negOffset));
                                }
                            } else {
                                float y;
                                float[] transformed = new float[(vals.length * 2)];
                                float posY = 0.0f;
                                float negY = -entry.getNegativeSum();
                                int k = 0;
                                int idx = 0;
                                while (k < transformed.length) {
                                    float value = vals[idx];
                                    if (value >= 0.0f) {
                                        posY += value;
                                        y = posY;
                                    } else {
                                        y = negY;
                                        negY -= value;
                                    }
                                    transformed[k + 1] = this.mAnimator.getPhaseY() * y;
                                    k += 2;
                                    idx++;
                                }
                                trans.pointValuesToPixel(transformed);
                                for (k = 0; k < transformed.length; k += 2) {
                                    float f;
                                    float x = valuePoints[j];
                                    float f2 = transformed[k + 1];
                                    if (vals[k / 2] >= 0.0f) {
                                        f = posOffset;
                                    } else {
                                        f = negOffset;
                                    }
                                    y = f2 + f;
                                    if (!this.mViewPortHandler.isInBoundsRight(x)) {
                                        break;
                                    }
                                    if (this.mViewPortHandler.isInBoundsY(y) && this.mViewPortHandler.isInBoundsLeft(x)) {
                                        drawValue(c, dataSet.getValueFormatter(), vals[k / 2], entry, i, x, y);
                                    }
                                }
                            }
                            j += 2;
                        }
                    } else {
                        j = 0;
                        while (((float) j) < ((float) valuePoints.length) * this.mAnimator.getPhaseX() && this.mViewPortHandler.isInBoundsRight(valuePoints[j])) {
                            if (this.mViewPortHandler.isInBoundsY(valuePoints[j + 1]) && this.mViewPortHandler.isInBoundsLeft(valuePoints[j])) {
                                BarEntry entry2 = (BarEntry) entries.get(j / 2);
                                float val = entry2.getVal();
                                drawValue(c, dataSet.getValueFormatter(), val, entry2, i, valuePoints[j], valuePoints[j + 1] + (val >= 0.0f ? posOffset : negOffset));
                            }
                            j += 2;
                        }
                    }
                }
            }
        }
    }

    public void drawHighlighted(Canvas c, Highlight[] indices) {
        int setCount = this.mChart.getBarData().getDataSetCount();
        for (Highlight h : indices) {
            int index = h.getXIndex();
            int dataSetIndex = h.getDataSetIndex();
            BarDataSet set = (BarDataSet) this.mChart.getBarData().getDataSetByIndex(dataSetIndex);
            if (set != null && set.isHighlightEnabled()) {
                float barspaceHalf = set.getBarSpace() / 2.0f;
                Transformer trans = this.mChart.getTransformer(set.getAxisDependency());
                this.mHighlightPaint.setColor(set.getHighLightColor());
                this.mHighlightPaint.setAlpha(set.getHighLightAlpha());
                if (index >= 0 && ((float) index) < (this.mChart.getXChartMax() * this.mAnimator.getPhaseX()) / ((float) setCount)) {
                    BarEntry e = (BarEntry) set.getEntryForXIndex(index);
                    if (e != null && e.getXIndex() == index) {
                        float y1;
                        float y2;
                        float groupspace = this.mChart.getBarData().getGroupSpace();
                        float x = (((float) ((index * setCount) + dataSetIndex)) + (groupspace / 2.0f)) + (((float) index) * groupspace);
                        if (h.getStackIndex() >= 0) {
                            y1 = h.getRange().from;
                            y2 = h.getRange().to;
                        } else {
                            y1 = e.getVal();
                            y2 = 0.0f;
                        }
                        prepareBarHighlight(x, y1, y2, barspaceHalf, trans);
                        c.drawRect(this.mBarRect, this.mHighlightPaint);
                        if (this.mChart.isDrawHighlightArrowEnabled()) {
                            this.mHighlightPaint.setAlpha(255);
                            float offsetY = this.mAnimator.getPhaseY() * 0.07f;
                            float[] values = new float[9];
                            trans.getPixelToValueMatrix().getValues(values);
                            float arrowWidth = set.getBarSpace() / 2.0f;
                            float arrowHeight = arrowWidth * Math.abs(values[4] / values[0]);
                            float yArrow;
                            Path arrow;
                            if (y1 > (-y2)) {
                                yArrow = y1 * this.mAnimator.getPhaseY();
                                arrow = new Path();
                                arrow.moveTo(0.4f + x, yArrow + offsetY);
                                arrow.lineTo((0.4f + x) + arrowWidth, (yArrow + offsetY) - arrowHeight);
                                arrow.lineTo((0.4f + x) + arrowWidth, (yArrow + offsetY) + arrowHeight);
                                trans.pathValueToPixel(arrow);
                                c.drawPath(arrow, this.mHighlightPaint);
                            } else {
                                yArrow = y1 * this.mAnimator.getPhaseY();
                                arrow = new Path();
                                arrow.moveTo(0.4f + x, yArrow + offsetY);
                                arrow.lineTo((0.4f + x) + arrowWidth, (yArrow + offsetY) - arrowHeight);
                                arrow.lineTo((0.4f + x) + arrowWidth, (yArrow + offsetY) + arrowHeight);
                                trans.pathValueToPixel(arrow);
                                c.drawPath(arrow, this.mHighlightPaint);
                            }
                        }
                    }
                }
            }
        }
    }

    public float[] getTransformedValues(Transformer trans, List<BarEntry> entries, int dataSetIndex) {
        return trans.generateTransformedValuesBarChart(entries, dataSetIndex, this.mChart.getBarData(), this.mAnimator.getPhaseY());
    }

    protected boolean passesCheck() {
        return ((float) this.mChart.getBarData().getYValCount()) < ((float) this.mChart.getMaxVisibleCount()) * this.mViewPortHandler.getScaleX();
    }

    public void drawExtras(Canvas c) {
    }
}
