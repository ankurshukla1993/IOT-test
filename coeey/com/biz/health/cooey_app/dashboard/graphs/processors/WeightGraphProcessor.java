package com.biz.health.cooey_app.dashboard.graphs.processors;

import android.content.Context;
import android.graphics.Color;
import com.biz.health.cooey_app.C0644R;
import com.biz.health.cooey_app.utils.DateUtil;
import com.cooey.android.users.old.utils.CTConstants;
import com.cooey.common.stores.StyleStore;
import com.cooey.common.vo.Vital;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class WeightGraphProcessor implements GraphProcessor {
    static DateTimeFormatter builder = DateTimeFormat.forPattern("dd/MM");
    private final Context context;
    private final StyleStore styleStore;

    public WeightGraphProcessor(Context context) {
        this.context = context;
        this.styleStore = new StyleStore(context);
    }

    public void generateGraph(LineChart lineChart, LineData lineData) {
        if (lineData == null) {
            lineChart.clear();
            lineChart.setNoDataTextDescription("Track your BMI and be in control\n");
            lineChart.setNoDataText(" Obesity severely affects hypertension and diabetes!");
            lineChart.setDescriptionTypeface(this.styleStore.getFont("Lato"));
            lineChart.invalidate();
            return;
        }
        lineChart.setMaxVisibleValueCount(5);
        lineChart.setData(lineData);
        lineChart.setTouchEnabled(false);
        lineChart.setOnChartGestureListener(null);
        lineChart.setOnChartValueSelectedListener(null);
        YAxis yAxisRight = lineChart.getAxisRight();
        yAxisRight.setDrawGridLines(false);
        yAxisRight.setDrawAxisLine(false);
        yAxisRight.setDrawLabels(false);
        yAxisRight.setStartAtZero(false);
        YAxis yAxisLeft = lineChart.getAxisLeft();
        yAxisLeft.setDrawAxisLine(false);
        yAxisRight.setDrawGridLines(false);
        yAxisRight.setStartAtZero(false);
        yAxisLeft.setTextColor(Color.argb(255, 90, 90, 90));
        lineChart.setDescriptionTypeface(this.styleStore.getFont("Lato"));
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setDrawAxisLine(false);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawLabels(false);
        lineChart.setDescription("");
        lineChart.getLegend().setTextColor(this.context.getResources().getColor(C0644R.color.textColorPrimary));
        lineChart.setDescriptionTypeface(this.styleStore.getFont("Lato"));
        lineChart.setDrawGridBackground(false);
        lineChart.setGridBackgroundColor(Color.argb(255, 90, 90, 90));
        lineChart.invalidate();
    }

    public void generateDynamicGraph(LineChart lineChart, LineData lineData) {
    }

    public LineData getLineData(List<Vital> weightDataList) {
        if (weightDataList == null) {
            return null;
        }
        try {
            if (weightDataList.size() == 0) {
                return null;
            }
            ArrayList<Entry> YAxisData = new ArrayList();
            List XAxisData = new ArrayList();
            int i = weightDataList.size() - 1;
            int j = 0;
            while (i >= 0) {
                try {
                    String weight = String.valueOf(((Vital) weightDataList.get(i)).getParameterMap().get("weight"));
                    XAxisData.add(j, DateUtil.getReadableStringFromDate(new Date(((Vital) weightDataList.get(i)).getTakenOn())));
                    YAxisData.add(new Entry(Float.valueOf(weight).floatValue(), j));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                i--;
                j++;
            }
            LineDataSet lineDataSet = new LineDataSet(YAxisData, CTConstants.FAB_WEIGHT);
            lineDataSet.setLineWidth(2.0f);
            lineDataSet.setCircleSize(2.0f);
            lineDataSet.setFillAlpha(65);
            lineDataSet.setFillColor(this.context.getResources().getColor(C0644R.color.white));
            lineDataSet.setHighLightColor(Color.rgb(244, 117, 117));
            lineDataSet.setColor(this.context.getResources().getColor(C0644R.color.weight_color));
            lineDataSet.setCircleColor(this.context.getResources().getColor(C0644R.color.white));
            lineDataSet.setDrawValues(false);
            List bloodSugarDataSets = new ArrayList();
            bloodSugarDataSets.add(lineDataSet);
            return new LineData(XAxisData, bloodSugarDataSets);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public LineData getLineData(long startTimestamp, long endTimestamp, int limit) {
        return null;
    }
}
