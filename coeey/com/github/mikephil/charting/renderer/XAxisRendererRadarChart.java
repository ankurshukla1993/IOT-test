package com.github.mikephil.charting.renderer;

import android.graphics.Canvas;
import android.graphics.PointF;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;

public class XAxisRendererRadarChart extends XAxisRenderer {
    private RadarChart mChart;

    public XAxisRendererRadarChart(ViewPortHandler viewPortHandler, XAxis xAxis, RadarChart chart) {
        super(viewPortHandler, xAxis, null);
        this.mChart = chart;
    }

    public void renderAxisLabels(Canvas c) {
        if (this.mXAxis.isEnabled() && this.mXAxis.isDrawLabelsEnabled()) {
            this.mAxisLabelPaint.setTypeface(this.mXAxis.getTypeface());
            this.mAxisLabelPaint.setTextSize(this.mXAxis.getTextSize());
            this.mAxisLabelPaint.setColor(this.mXAxis.getTextColor());
            float sliceangle = this.mChart.getSliceAngle();
            float factor = this.mChart.getFactor();
            PointF center = this.mChart.getCenterOffsets();
            int mod = this.mXAxis.mAxisLabelModulus;
            for (int i = 0; i < this.mXAxis.getValues().size(); i += mod) {
                String label = (String) this.mXAxis.getValues().get(i);
                PointF p = Utils.getPosition(center, (this.mChart.getYRange() * factor) + (((float) this.mXAxis.mLabelWidth) / 2.0f), ((((float) i) * sliceangle) + this.mChart.getRotationAngle()) % 360.0f);
                drawLabel(c, label, i, p.x, p.y + (((float) this.mXAxis.mLabelHeight) / 2.0f));
            }
        }
    }

    public void renderLimitLines(Canvas c) {
    }
}
