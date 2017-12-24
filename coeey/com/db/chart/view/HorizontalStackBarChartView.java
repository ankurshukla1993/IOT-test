package com.db.chart.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.Region;
import android.util.AttributeSet;
import com.db.chart.model.Bar;
import com.db.chart.model.BarSet;
import com.db.chart.model.ChartSet;
import com.db.chart.view.ChartView.Orientation;
import com.google.android.flexbox.FlexItem;
import java.util.ArrayList;

public class HorizontalStackBarChartView extends BaseStackBarChartView {
    public HorizontalStackBarChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(Orientation.HORIZONTAL);
        setMandatoryBorderSpacing();
    }

    public HorizontalStackBarChartView(Context context) {
        super(context);
        setOrientation(Orientation.HORIZONTAL);
        setMandatoryBorderSpacing();
    }

    public void onDrawChart(Canvas canvas, ArrayList<ChartSet> data) {
        int dataSize = data.size();
        int setSize = ((ChartSet) data.get(0)).size();
        float zeroPosition = getZeroPosition();
        for (int i = 0; i < setSize; i++) {
            if (this.style.hasBarBackground) {
                drawBarBackground(canvas, (float) ((int) getInnerChartLeft()), (float) ((int) (((ChartSet) data.get(0)).getEntry(i).getY() - (this.barWidth / 2.0f))), (float) ((int) getInnerChartRight()), (float) ((int) (((ChartSet) data.get(0)).getEntry(i).getY() + (this.barWidth / 2.0f))));
            }
            float offset = 0.0f;
            float negOffset = 0.0f;
            float currBottom = zeroPosition;
            float negCurrBottom = zeroPosition;
            int bottomSetIndex = BaseStackBarChartView.discoverBottomSet(i, data);
            int topSetIndex = BaseStackBarChartView.discoverTopSet(i, data);
            for (int j = 0; j < dataSize; j++) {
                BarSet barSet = (BarSet) data.get(j);
                Bar bar = (Bar) barSet.getEntry(i);
                float barSize = Math.abs(zeroPosition - bar.getX());
                if (barSet.isVisible() && bar.getValue() != 0.0f && barSize >= 2.0f) {
                    this.style.barPaint.setColor(bar.getColor());
                    applyShadow(this.style.barPaint, barSet.getAlpha(), bar.getShadowDx(), bar.getShadowDy(), bar.getShadowRadius(), bar.getShadowColor());
                    float y0 = bar.getY() - (this.barWidth / 2.0f);
                    float y1 = bar.getY() + (this.barWidth / 2.0f);
                    float x1;
                    if (bar.getValue() > 0.0f) {
                        x1 = zeroPosition + (barSize - offset);
                        if (j == bottomSetIndex) {
                            drawBar(canvas, (float) ((int) currBottom), (float) ((int) y0), (float) ((int) x1), (float) ((int) y1));
                            if (!(bottomSetIndex == topSetIndex || this.style.cornerRadius == 0.0f)) {
                                canvas.drawRect(new Rect((int) (x1 - ((x1 - currBottom) / 2.0f)), (int) y0, (int) x1, (int) y1), this.style.barPaint);
                            }
                        } else if (j == topSetIndex) {
                            drawBar(canvas, (float) ((int) currBottom), (float) ((int) y0), (float) ((int) x1), (float) ((int) y1));
                            canvas.drawRect(new Rect((int) currBottom, (int) y0, (int) (currBottom + ((x1 - currBottom) / 2.0f)), (int) y1), this.style.barPaint);
                        } else {
                            canvas.drawRect(new Rect((int) currBottom, (int) y0, (int) x1, (int) y1), this.style.barPaint);
                        }
                        currBottom = x1;
                        if (barSize != 0.0f) {
                            offset -= barSize - 0.0f;
                        }
                    } else {
                        x1 = zeroPosition - (barSize + negOffset);
                        if (j == bottomSetIndex) {
                            drawBar(canvas, (float) ((int) x1), (float) ((int) y0), (float) ((int) negCurrBottom), (float) ((int) y1));
                            if (!(bottomSetIndex == topSetIndex || this.style.cornerRadius == 0.0f)) {
                                canvas.drawRect(new Rect((int) (negCurrBottom - ((negCurrBottom - x1) / 2.0f)), (int) y0, (int) negCurrBottom, (int) y1), this.style.barPaint);
                            }
                        } else if (j == topSetIndex) {
                            drawBar(canvas, (float) ((int) x1), (float) ((int) y0), (float) ((int) negCurrBottom), (float) ((int) y1));
                            canvas.drawRect(new Rect((int) x1, (int) y0, (int) (x1 + ((negCurrBottom - x1) / 2.0f)), (int) y1), this.style.barPaint);
                        } else {
                            canvas.drawRect(new Rect((int) x1, (int) y0, (int) negCurrBottom, (int) y1), this.style.barPaint);
                        }
                        negCurrBottom = x1;
                        if (barSize != 0.0f) {
                            negOffset += barSize;
                        }
                    }
                }
            }
        }
    }

    public void onPreDrawChart(ArrayList<ChartSet> data) {
        if (((ChartSet) data.get(0)).size() == 1) {
            this.barWidth = (getInnerChartBottom() - getInnerChartTop()) - (getBorderSpacing() * 2.0f);
        } else {
            calculateBarsWidth(-1, ((ChartSet) data.get(0)).getEntry(1).getY(), ((ChartSet) data.get(0)).getEntry(0).getY());
        }
    }

    void defineRegions(ArrayList<ArrayList<Region>> regions, ArrayList<ChartSet> data) {
        int dataSize = data.size();
        int setSize = ((ChartSet) data.get(0)).size();
        float zeroPosition = getZeroPosition();
        for (int i = 0; i < setSize; i++) {
            float offset = 0.0f;
            float negOffset = 0.0f;
            float currBottom = zeroPosition;
            float negCurrBottom = zeroPosition;
            for (int j = 0; j < dataSize; j++) {
                BarSet barSet = (BarSet) data.get(j);
                Bar bar = (Bar) barSet.getEntry(i);
                float barSize = Math.abs(zeroPosition - bar.getX());
                if (barSet.isVisible()) {
                    float x1;
                    if (bar.getValue() > 0.0f) {
                        x1 = zeroPosition + (barSize - offset);
                        ((Region) ((ArrayList) regions.get(j)).get(i)).set((int) currBottom, (int) (bar.getY() - (this.barWidth / 2.0f)), (int) x1, (int) (bar.getY() + (this.barWidth / 2.0f)));
                        currBottom = x1;
                        offset -= barSize - 2.0f;
                    } else if (bar.getValue() < 0.0f) {
                        x1 = zeroPosition - (barSize + negOffset);
                        ((Region) ((ArrayList) regions.get(j)).get(i)).set((int) x1, (int) (bar.getY() - (this.barWidth / 2.0f)), (int) negCurrBottom, (int) (bar.getY() + (this.barWidth / 2.0f)));
                        negCurrBottom = x1;
                        negOffset += barSize;
                    } else {
                        ((Region) ((ArrayList) regions.get(j)).get(i)).set((int) currBottom, (int) (bar.getY() - (this.barWidth / 2.0f)), (int) (zeroPosition + (FlexItem.FLEX_SHRINK_DEFAULT - offset)), (int) (bar.getY() + (this.barWidth / 2.0f)));
                    }
                }
            }
        }
    }
}
