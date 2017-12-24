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

public class StackBarChartView extends BaseStackBarChartView {
    public StackBarChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(Orientation.VERTICAL);
        setMandatoryBorderSpacing();
    }

    public StackBarChartView(Context context) {
        super(context);
        setOrientation(Orientation.VERTICAL);
        setMandatoryBorderSpacing();
    }

    public void onDrawChart(Canvas canvas, ArrayList<ChartSet> data) {
        int dataSize = data.size();
        int setSize = ((ChartSet) data.get(0)).size();
        float zeroPosition = getZeroPosition();
        for (int i = 0; i < setSize; i++) {
            if (this.style.hasBarBackground) {
                drawBarBackground(canvas, (float) ((int) (((ChartSet) data.get(0)).getEntry(i).getX() - (this.barWidth / 2.0f))), (float) ((int) getInnerChartTop()), (float) ((int) (((ChartSet) data.get(0)).getEntry(i).getX() + (this.barWidth / 2.0f))), (float) ((int) getInnerChartBottom()));
            }
            float verticalOffset = 0.0f;
            float negVerticalOffset = 0.0f;
            float currBottomY = zeroPosition;
            float negCurrBottomY = zeroPosition;
            int bottomSetIndex = BaseStackBarChartView.discoverBottomSet(i, data);
            int topSetIndex = BaseStackBarChartView.discoverTopSet(i, data);
            for (int j = 0; j < dataSize; j++) {
                BarSet barSet = (BarSet) data.get(j);
                Bar bar = (Bar) barSet.getEntry(i);
                float barSize = Math.abs(zeroPosition - bar.getY());
                if (barSet.isVisible() && bar.getValue() != 0.0f && barSize >= 2.0f) {
                    this.style.barPaint.setColor(bar.getColor());
                    applyShadow(this.style.barPaint, barSet.getAlpha(), bar.getShadowDx(), bar.getShadowDy(), bar.getShadowRadius(), bar.getShadowColor());
                    float x0 = bar.getX() - (this.barWidth / 2.0f);
                    float x1 = bar.getX() + (this.barWidth / 2.0f);
                    float y1;
                    if (bar.getValue() > 0.0f) {
                        y1 = zeroPosition - (barSize + verticalOffset);
                        if (j == bottomSetIndex) {
                            drawBar(canvas, (float) ((int) x0), (float) ((int) y1), (float) ((int) x1), (float) ((int) currBottomY));
                            if (!(bottomSetIndex == topSetIndex || this.style.cornerRadius == 0.0f)) {
                                canvas.drawRect(new Rect((int) x0, (int) y1, (int) x1, (int) (y1 + ((currBottomY - y1) / 2.0f))), this.style.barPaint);
                            }
                        } else if (j == topSetIndex) {
                            drawBar(canvas, (float) ((int) x0), (float) ((int) y1), (float) ((int) x1), (float) ((int) currBottomY));
                            canvas.drawRect(new Rect((int) x0, (int) (currBottomY - ((currBottomY - y1) / 2.0f)), (int) x1, (int) currBottomY), this.style.barPaint);
                        } else {
                            canvas.drawRect(new Rect((int) x0, (int) y1, (int) x1, (int) currBottomY), this.style.barPaint);
                        }
                        currBottomY = y1;
                        if (barSize != 0.0f) {
                            verticalOffset += 2.0f + barSize;
                        }
                    } else {
                        y1 = zeroPosition + (barSize - negVerticalOffset);
                        if (j == bottomSetIndex) {
                            drawBar(canvas, (float) ((int) x0), (float) ((int) negCurrBottomY), (float) ((int) x1), (float) ((int) y1));
                            if (!(bottomSetIndex == topSetIndex || this.style.cornerRadius == 0.0f)) {
                                canvas.drawRect(new Rect((int) x0, (int) negCurrBottomY, (int) x1, (int) (negCurrBottomY + ((y1 - negCurrBottomY) / 2.0f))), this.style.barPaint);
                            }
                        } else if (j == topSetIndex) {
                            drawBar(canvas, (float) ((int) x0), (float) ((int) negCurrBottomY), (float) ((int) x1), (float) ((int) y1));
                            canvas.drawRect(new Rect((int) x0, (int) (y1 - ((y1 - negCurrBottomY) / 2.0f)), (int) x1, (int) y1), this.style.barPaint);
                        } else {
                            canvas.drawRect(new Rect((int) x0, (int) negCurrBottomY, (int) x1, (int) y1), this.style.barPaint);
                        }
                        negCurrBottomY = y1;
                        if (barSize != 0.0f) {
                            negVerticalOffset -= barSize;
                        }
                    }
                }
            }
        }
    }

    public void onPreDrawChart(ArrayList<ChartSet> data) {
        if (((ChartSet) data.get(0)).size() == 1) {
            this.barWidth = (getInnerChartRight() - getInnerChartLeft()) - (getBorderSpacing() * 2.0f);
        } else {
            calculateBarsWidth(-1, ((ChartSet) data.get(0)).getEntry(0).getX(), ((ChartSet) data.get(0)).getEntry(1).getX());
        }
    }

    void defineRegions(ArrayList<ArrayList<Region>> regions, ArrayList<ChartSet> data) {
        int dataSize = data.size();
        int setSize = ((ChartSet) data.get(0)).size();
        float zeroPosition = getZeroPosition();
        for (int i = 0; i < setSize; i++) {
            float verticalOffset = 0.0f;
            float negVerticalOffset = 0.0f;
            float currBottomY = zeroPosition;
            float negCurrBottomY = zeroPosition;
            for (int j = 0; j < dataSize; j++) {
                BarSet barSet = (BarSet) data.get(j);
                Bar bar = (Bar) barSet.getEntry(i);
                float barSize = Math.abs(zeroPosition - bar.getY());
                if (barSet.isVisible()) {
                    float y1;
                    if (bar.getValue() > 0.0f) {
                        y1 = zeroPosition - (barSize + verticalOffset);
                        ((Region) ((ArrayList) regions.get(j)).get(i)).set((int) (bar.getX() - (this.barWidth / 2.0f)), (int) y1, (int) (bar.getX() + (this.barWidth / 2.0f)), (int) currBottomY);
                        currBottomY = y1;
                        verticalOffset += 2.0f + barSize;
                    } else if (bar.getValue() < 0.0f) {
                        y1 = zeroPosition + (barSize - negVerticalOffset);
                        ((Region) ((ArrayList) regions.get(j)).get(i)).set((int) (bar.getX() - (this.barWidth / 2.0f)), (int) negCurrBottomY, (int) (bar.getX() + (this.barWidth / 2.0f)), (int) y1);
                        negCurrBottomY = y1;
                        negVerticalOffset -= barSize;
                    } else {
                        ((Region) ((ArrayList) regions.get(j)).get(i)).set((int) (bar.getX() - (this.barWidth / 2.0f)), (int) (zeroPosition - (FlexItem.FLEX_SHRINK_DEFAULT + verticalOffset)), (int) (bar.getX() + (this.barWidth / 2.0f)), (int) currBottomY);
                    }
                }
            }
        }
    }
}
