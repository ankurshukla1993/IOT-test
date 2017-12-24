package com.github.mikephil.charting.renderer;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint.Style;
import com.facebook.appevents.AppEventsConstants;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.data.BubbleData;
import com.github.mikephil.charting.data.BubbleDataSet;
import com.github.mikephil.charting.data.BubbleEntry;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.BubbleDataProvider;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.google.android.flexbox.FlexItem;
import java.util.List;

public class BubbleChartRenderer extends DataRenderer {
    private float[] _hsvBuffer = new float[3];
    protected BubbleDataProvider mChart;
    private float[] pointBuffer = new float[2];
    private float[] sizeBuffer = new float[4];

    public BubbleChartRenderer(BubbleDataProvider chart, ChartAnimator animator, ViewPortHandler viewPortHandler) {
        super(animator, viewPortHandler);
        this.mChart = chart;
        this.mRenderPaint.setStyle(Style.FILL);
        this.mHighlightPaint.setStyle(Style.STROKE);
        this.mHighlightPaint.setStrokeWidth(Utils.convertDpToPixel(1.5f));
    }

    public void initBuffers() {
    }

    public void drawData(Canvas c) {
        for (BubbleDataSet set : this.mChart.getBubbleData().getDataSets()) {
            if (set.isVisible() && set.getEntryCount() > 0) {
                drawDataSet(c, set);
            }
        }
    }

    protected float getShapeSize(float entrySize, float maxSize, float reference) {
        return reference * (maxSize == 0.0f ? FlexItem.FLEX_SHRINK_DEFAULT : (float) Math.sqrt((double) (entrySize / maxSize)));
    }

    protected void drawDataSet(Canvas c, BubbleDataSet dataSet) {
        Transformer trans = this.mChart.getTransformer(dataSet.getAxisDependency());
        float phaseX = this.mAnimator.getPhaseX();
        float phaseY = this.mAnimator.getPhaseY();
        List<BubbleEntry> entries = dataSet.getYVals();
        Entry entryFrom = dataSet.getEntryForXIndex(this.mMinX);
        Entry entryTo = dataSet.getEntryForXIndex(this.mMaxX);
        int minx = Math.max(dataSet.getEntryPosition(entryFrom), 0);
        int maxx = Math.min(dataSet.getEntryPosition(entryTo) + 1, entries.size());
        this.sizeBuffer[0] = 0.0f;
        this.sizeBuffer[2] = 1.0f;
        trans.pointValuesToPixel(this.sizeBuffer);
        float referenceSize = Math.min(Math.abs(this.mViewPortHandler.contentBottom() - this.mViewPortHandler.contentTop()), Math.abs(this.sizeBuffer[2] - this.sizeBuffer[0]));
        for (int j = minx; j < maxx; j++) {
            BubbleEntry entry = (BubbleEntry) entries.get(j);
            this.pointBuffer[0] = (((float) (entry.getXIndex() - minx)) * phaseX) + ((float) minx);
            this.pointBuffer[1] = entry.getVal() * phaseY;
            trans.pointValuesToPixel(this.pointBuffer);
            float shapeHalf = getShapeSize(entry.getSize(), dataSet.getMaxSize(), referenceSize) / 2.0f;
            if (this.mViewPortHandler.isInBoundsTop(this.pointBuffer[1] + shapeHalf) && this.mViewPortHandler.isInBoundsBottom(this.pointBuffer[1] - shapeHalf) && this.mViewPortHandler.isInBoundsLeft(this.pointBuffer[0] + shapeHalf)) {
                if (this.mViewPortHandler.isInBoundsRight(this.pointBuffer[0] - shapeHalf)) {
                    this.mRenderPaint.setColor(dataSet.getColor(entry.getXIndex()));
                    c.drawCircle(this.pointBuffer[0], this.pointBuffer[1], shapeHalf, this.mRenderPaint);
                } else {
                    return;
                }
            }
        }
    }

    public void drawValues(Canvas c) {
        BubbleData bubbleData = this.mChart.getBubbleData();
        if (bubbleData != null && bubbleData.getYValCount() < ((int) Math.ceil((double) (((float) this.mChart.getMaxVisibleCount()) * this.mViewPortHandler.getScaleX())))) {
            List<BubbleDataSet> dataSets = bubbleData.getDataSets();
            float lineHeight = (float) Utils.calcTextHeight(this.mValuePaint, AppEventsConstants.EVENT_PARAM_VALUE_YES);
            for (int i = 0; i < dataSets.size(); i++) {
                DataSet dataSet = (BubbleDataSet) dataSets.get(i);
                if (dataSet.isDrawValuesEnabled() && dataSet.getEntryCount() != 0) {
                    float alpha;
                    applyValueTextStyle(dataSet);
                    float phaseX = this.mAnimator.getPhaseX();
                    float phaseY = this.mAnimator.getPhaseY();
                    if (phaseX == FlexItem.FLEX_SHRINK_DEFAULT) {
                        alpha = phaseY;
                    } else {
                        alpha = phaseX;
                    }
                    int valueTextColor = dataSet.getValueTextColor();
                    this.mValuePaint.setColor(Color.argb(Math.round(255.0f * alpha), Color.red(valueTextColor), Color.green(valueTextColor), Color.blue(valueTextColor)));
                    List<BubbleEntry> entries = dataSet.getYVals();
                    Entry entryFrom = dataSet.getEntryForXIndex(this.mMinX);
                    Entry entryTo = dataSet.getEntryForXIndex(this.mMaxX);
                    int minx = dataSet.getEntryPosition(entryFrom);
                    float[] positions = this.mChart.getTransformer(dataSet.getAxisDependency()).generateTransformedValuesBubble(entries, phaseX, phaseY, minx, Math.min(dataSet.getEntryPosition(entryTo) + 1, dataSet.getEntryCount()));
                    for (int j = 0; j < positions.length; j += 2) {
                        float x = positions[j];
                        float y = positions[j + 1];
                        if (!this.mViewPortHandler.isInBoundsRight(x)) {
                            break;
                        }
                        if (this.mViewPortHandler.isInBoundsLeft(x) && this.mViewPortHandler.isInBoundsY(y)) {
                            BubbleEntry entry = (BubbleEntry) entries.get((j / 2) + minx);
                            drawValue(c, dataSet.getValueFormatter(), entry.getSize(), entry, i, x, y + (0.5f * lineHeight));
                        }
                    }
                }
            }
        }
    }

    public void drawExtras(Canvas c) {
    }

    public void drawHighlighted(Canvas c, Highlight[] indices) {
        BubbleData bubbleData = this.mChart.getBubbleData();
        float phaseX = this.mAnimator.getPhaseX();
        float phaseY = this.mAnimator.getPhaseY();
        for (Highlight indice : indices) {
            BubbleDataSet dataSet = (BubbleDataSet) bubbleData.getDataSetByIndex(indice.getDataSetIndex());
            if (dataSet != null && dataSet.isHighlightEnabled()) {
                Entry entryFrom = dataSet.getEntryForXIndex(this.mMinX);
                Entry entryTo = dataSet.getEntryForXIndex(this.mMaxX);
                int minx = dataSet.getEntryPosition(entryFrom);
                int maxx = Math.min(dataSet.getEntryPosition(entryTo) + 1, dataSet.getEntryCount());
                BubbleEntry entry = (BubbleEntry) bubbleData.getEntryForHighlight(indice);
                if (entry != null && entry.getXIndex() == indice.getXIndex()) {
                    Transformer trans = this.mChart.getTransformer(dataSet.getAxisDependency());
                    this.sizeBuffer[0] = 0.0f;
                    this.sizeBuffer[2] = 1.0f;
                    trans.pointValuesToPixel(this.sizeBuffer);
                    float referenceSize = Math.min(Math.abs(this.mViewPortHandler.contentBottom() - this.mViewPortHandler.contentTop()), Math.abs(this.sizeBuffer[2] - this.sizeBuffer[0]));
                    this.pointBuffer[0] = (((float) (entry.getXIndex() - minx)) * phaseX) + ((float) minx);
                    this.pointBuffer[1] = entry.getVal() * phaseY;
                    trans.pointValuesToPixel(this.pointBuffer);
                    float shapeHalf = getShapeSize(entry.getSize(), dataSet.getMaxSize(), referenceSize) / 2.0f;
                    if (this.mViewPortHandler.isInBoundsTop(this.pointBuffer[1] + shapeHalf) && this.mViewPortHandler.isInBoundsBottom(this.pointBuffer[1] - shapeHalf) && this.mViewPortHandler.isInBoundsLeft(this.pointBuffer[0] + shapeHalf)) {
                        if (!this.mViewPortHandler.isInBoundsRight(this.pointBuffer[0] - shapeHalf)) {
                            return;
                        }
                        if (indice.getXIndex() >= minx && indice.getXIndex() < maxx) {
                            int originalColor = dataSet.getColor(entry.getXIndex());
                            Color.RGBToHSV(Color.red(originalColor), Color.green(originalColor), Color.blue(originalColor), this._hsvBuffer);
                            float[] fArr = this._hsvBuffer;
                            fArr[2] = fArr[2] * 0.5f;
                            this.mHighlightPaint.setColor(Color.HSVToColor(Color.alpha(originalColor), this._hsvBuffer));
                            this.mHighlightPaint.setStrokeWidth(dataSet.getHighlightCircleWidth());
                            c.drawCircle(this.pointBuffer[0], this.pointBuffer[1], shapeHalf, this.mHighlightPaint);
                        }
                    }
                }
            }
        }
    }
}
