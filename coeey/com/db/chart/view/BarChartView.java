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

public class BarChartView extends BaseBarChartView {
    public BarChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(Orientation.VERTICAL);
        setMandatoryBorderSpacing();
    }

    public BarChartView(Context context) {
        super(context);
        setOrientation(Orientation.VERTICAL);
        setMandatoryBorderSpacing();
    }

    public void onDrawChart(Canvas canvas, ArrayList<ChartSet> data) {
        int nSets = data.size();
        int nEntries = ((ChartSet) data.get(0)).size();
        for (int i = 0; i < nEntries; i++) {
            float offset = ((ChartSet) data.get(0)).getEntry(i).getX() - this.drawingOffset;
            for (int j = 0; j < nSets; j++) {
                BarSet barSet = (BarSet) data.get(j);
                Bar bar = (Bar) barSet.getEntry(i);
                if (barSet.isVisible()) {
                    if (bar.hasGradientColor()) {
                        this.style.barPaint.setShader(new LinearGradient(bar.getX(), getZeroPosition(), bar.getX(), bar.getY(), bar.getGradientColors(), bar.getGradientPositions(), TileMode.MIRROR));
                    } else {
                        this.style.barPaint.setColor(bar.getColor());
                    }
                    applyShadow(this.style.barPaint, barSet.getAlpha(), bar.getShadowDx(), bar.getShadowDy(), bar.getShadowRadius(), bar.getShadowColor());
                    if (this.style.hasBarBackground) {
                        drawBarBackground(canvas, offset, getInnerChartTop(), offset + this.barWidth, getInnerChartBottom());
                    }
                    if (bar.getValue() >= 0.0f) {
                        drawBar(canvas, offset, bar.getY(), offset + this.barWidth, getZeroPosition());
                    } else {
                        drawBar(canvas, offset, getZeroPosition(), offset + this.barWidth, bar.getY());
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
            calculateBarsWidth(data.size(), 0.0f, (getInnerChartRight() - getInnerChartLeft()) - (getBorderSpacing() * 2.0f));
        } else {
            calculateBarsWidth(data.size(), ((ChartSet) data.get(0)).getEntry(0).getX(), ((ChartSet) data.get(0)).getEntry(1).getX());
        }
        calculatePositionOffset(data.size());
    }

    void defineRegions(ArrayList<ArrayList<Region>> regions, ArrayList<ChartSet> data) {
        int nSets = data.size();
        int nEntries = ((ChartSet) data.get(0)).size();
        for (int i = 0; i < nEntries; i++) {
            float offset = ((ChartSet) data.get(0)).getEntry(i).getX() - this.drawingOffset;
            for (int j = 0; j < nSets; j++) {
                Bar bar = (Bar) ((BarSet) data.get(j)).getEntry(i);
                if (bar.getValue() > 0.0f && ((int) bar.getY()) != ((int) getZeroPosition())) {
                    offset += this.barWidth;
                    ((Region) ((ArrayList) regions.get(j)).get(i)).set((int) offset, (int) bar.getY(), (int) offset, (int) getZeroPosition());
                } else if (bar.getValue() >= 0.0f || ((int) bar.getY()) == ((int) getZeroPosition())) {
                    offset += this.barWidth;
                    ((Region) ((ArrayList) regions.get(j)).get(i)).set((int) offset, (int) getZeroPosition(), (int) offset, ((int) getZeroPosition()) + 1);
                } else {
                    offset += this.barWidth;
                    ((Region) ((ArrayList) regions.get(j)).get(i)).set((int) offset, (int) getZeroPosition(), (int) offset, (int) bar.getY());
                }
                if (j != nSets - 1) {
                    offset += this.style.setSpacing;
                }
            }
        }
    }
}
