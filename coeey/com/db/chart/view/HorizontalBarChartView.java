package com.db.chart.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Region;
import android.graphics.Shader.TileMode;
import android.util.AttributeSet;
import com.db.chart.model.Bar;
import com.db.chart.model.BarSet;
import com.db.chart.model.ChartSet;
import com.db.chart.view.ChartView.Orientation;
import java.util.ArrayList;

public class HorizontalBarChartView extends BaseBarChartView {
    public HorizontalBarChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(Orientation.HORIZONTAL);
        setMandatoryBorderSpacing();
    }

    public HorizontalBarChartView(Context context) {
        super(context);
        setOrientation(Orientation.HORIZONTAL);
        setMandatoryBorderSpacing();
    }

    public void onDrawChart(Canvas canvas, ArrayList<ChartSet> data) {
        int nSets = data.size();
        int nEntries = ((ChartSet) data.get(0)).size();
        for (int i = 0; i < nEntries; i++) {
            float offset = ((ChartSet) data.get(0)).getEntry(i).getY() - this.drawingOffset;
            for (int j = 0; j < nSets; j++) {
                BarSet barSet = (BarSet) data.get(j);
                Bar bar = (Bar) barSet.getEntry(i);
                if (barSet.isVisible()) {
                    if (bar.hasGradientColor()) {
                        this.style.barPaint.setShader(new LinearGradient(getZeroPosition(), bar.getY(), bar.getX(), bar.getY(), bar.getGradientColors(), bar.getGradientPositions(), TileMode.MIRROR));
                    } else {
                        this.style.barPaint.setColor(bar.getColor());
                    }
                    applyShadow(this.style.barPaint, barSet.getAlpha(), bar.getShadowDx(), bar.getShadowDy(), bar.getShadowRadius(), bar.getShadowColor());
                    if (this.style.hasBarBackground) {
                        drawBarBackground(canvas, getInnerChartLeft(), offset, getInnerChartRight(), offset + this.barWidth);
                    }
                    if (bar.getValue() >= 0.0f) {
                        drawBar(canvas, getZeroPosition(), offset, bar.getX(), offset + this.barWidth);
                    } else {
                        drawBar(canvas, bar.getX(), offset, getZeroPosition(), offset + this.barWidth);
                    }
                    offset += this.barWidth;
                    if (j != nSets - 1) {
                        offset += this.style.setSpacing;
                    }
                }
            }
        }
    }

    protected void onPreDrawChart(ArrayList<ChartSet> data) {
        if (((ChartSet) data.get(0)).size() == 1) {
            this.style.barSpacing = 0.0f;
            calculateBarsWidth(data.size(), 0.0f, (getInnerChartBottom() - getInnerChartTop()) - (getBorderSpacing() * 2.0f));
        } else {
            calculateBarsWidth(data.size(), ((ChartSet) data.get(0)).getEntry(1).getY(), ((ChartSet) data.get(0)).getEntry(0).getY());
        }
        calculatePositionOffset(data.size());
    }

    void defineRegions(ArrayList<ArrayList<Region>> regions, ArrayList<ChartSet> data) {
        int nSets = data.size();
        int nEntries = ((ChartSet) data.get(0)).size();
        for (int i = 0; i < nEntries; i++) {
            float offset = ((ChartSet) data.get(0)).getEntry(i).getY() - this.drawingOffset;
            for (int j = 0; j < nSets; j++) {
                Bar bar = (Bar) ((BarSet) data.get(j)).getEntry(i);
                if (bar.getValue() > 0.0f && ((int) bar.getX()) != ((int) getZeroPosition())) {
                    ((Region) ((ArrayList) regions.get(j)).get(i)).set((int) getZeroPosition(), (int) offset, (int) bar.getX(), (int) (this.barWidth + offset));
                } else if (bar.getValue() >= 0.0f || ((int) bar.getX()) == ((int) getZeroPosition())) {
                    ((Region) ((ArrayList) regions.get(j)).get(i)).set(((int) getZeroPosition()) - 1, (int) offset, (int) getZeroPosition(), (int) (this.barWidth + offset));
                } else {
                    ((Region) ((ArrayList) regions.get(j)).get(i)).set((int) bar.getX(), (int) offset, (int) getZeroPosition(), (int) (this.barWidth + offset));
                }
                if (j != nSets - 1) {
                    offset += this.style.setSpacing;
                }
            }
        }
    }
}
