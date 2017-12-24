package com.biz.health.cooey_app.dashboard.graphs.processors;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import com.biz.health.cooey_app.C0644R;
import com.biz.health.cooey_app.vitals.BPActivity;
import com.cooey.common.stores.StyleStore;
import com.cooey.common.vo.Vital;
import com.cooey.devices.helpers.CooeySQLHelper;
import com.facebook.appevents.AppEventsConstants;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.XAxis.XAxisPosition;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import java.util.ArrayList;
import java.util.List;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class BloodPressureGraphProcessor implements GraphProcessor {
    static DateTimeFormatter builder = DateTimeFormat.forPattern("dd/MM");
    final Context context;
    private final StyleStore styleStore;

    class C06811 implements OnClickListener {
        C06811() {
        }

        public void onClick(View v) {
            BloodPressureGraphProcessor.this.context.startActivity(new Intent(BloodPressureGraphProcessor.this.context, BPActivity.class));
        }
    }

    public BloodPressureGraphProcessor(Context context) {
        this.context = context;
        this.styleStore = new StyleStore(context);
    }

    public void generateGraph(LineChart lineChart, LineData lineData) {
        String data = "";
        if (lineData == null) {
            lineChart.setNoDataTextDescription("Why not you? \n");
            lineChart.setNoDataText("72% had it under control with regular BP checks.");
            lineChart.setDescriptionTypeface(this.styleStore.getFont("Lato"));
            YAxis yAxisRight = lineChart.getAxisRight();
            yAxisRight.setDrawGridLines(false);
            yAxisRight.setDrawAxisLine(false);
            XAxis xAxis = lineChart.getXAxis();
            xAxis.setDrawAxisLine(false);
            xAxis.setDrawGridLines(false);
            xAxis.setDrawLabels(true);
            xAxis.setPosition(XAxisPosition.BOTTOM);
            return;
        }
        lineChart.setMaxVisibleValueCount(5);
        lineChart.setData(lineData);
        lineChart.setTouchEnabled(false);
        lineChart.setOnClickListener(new C06811());
        lineChart.setOnChartGestureListener(null);
        lineChart.setOnChartValueSelectedListener(null);
        yAxisRight = lineChart.getAxisRight();
        yAxisRight.setDrawGridLines(false);
        yAxisRight.setDrawAxisLine(false);
        yAxisRight.setDrawLabels(false);
        yAxisRight.setStartAtZero(false);
        YAxis yAxisLeft = lineChart.getAxisLeft();
        yAxisLeft.setDrawAxisLine(false);
        yAxisRight.setDrawGridLines(false);
        yAxisRight.setStartAtZero(false);
        yAxisLeft.setTextColor(Color.argb(255, 90, 90, 90));
        yAxisLeft.setTypeface(this.styleStore.getFont("Lato"));
        xAxis = lineChart.getXAxis();
        xAxis.setDrawAxisLine(false);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawLabels(true);
        xAxis.setPosition(XAxisPosition.BOTTOM);
        lineChart.setDescription("");
        lineChart.getLegend().setTextColor(this.context.getResources().getColor(C0644R.color.textColorPrimary));
        lineChart.getLegend().setTypeface(this.styleStore.getFont("Lato"));
        lineChart.setDrawGridBackground(false);
        lineChart.setGridBackgroundColor(Color.argb(255, 90, 90, 90));
        lineChart.invalidate();
    }

    public void generateDynamicGraph(LineChart lineChart, LineData lineData) {
    }

    public LineData getLineData(List<Vital> bpDataList) {
        if (bpDataList == null) {
            return null;
        }
        if (bpDataList.size() == 0) {
            return null;
        }
        ArrayList<Entry> systolicEntries = new ArrayList();
        ArrayList<Entry> diastolicEntries = new ArrayList();
        List bpValues = new ArrayList();
        int i = bpDataList.size() - 1;
        int j = 0;
        while (i >= 0) {
            String systolic = String.valueOf(((Vital) bpDataList.get(i)).getParameterMap().get(CooeySQLHelper.COL_SYS));
            String diastolic = String.valueOf(((Vital) bpDataList.get(i)).getParameterMap().get(CooeySQLHelper.COL_DIA));
            if (systolic == null) {
                systolic = AppEventsConstants.EVENT_PARAM_VALUE_NO;
            }
            if (diastolic == null) {
                diastolic = AppEventsConstants.EVENT_PARAM_VALUE_NO;
            }
            try {
                systolicEntries.add(new BarEntry(Float.valueOf(systolic).floatValue(), j));
                diastolicEntries.add(new BarEntry(Float.valueOf(diastolic).floatValue(), j));
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (((Vital) bpDataList.get(i)).getTakenOn() > 0) {
                    bpValues.add(j, new DateTime().toString(builder));
                } else {
                    bpValues.add(j, new DateTime(Long.valueOf(((Vital) bpDataList.get(i)).getTakenOn())).toString(builder));
                }
                i--;
                j++;
            } catch (Exception ex) {
                ex.printStackTrace();
                Log.e("Graph", "Exception", ex);
                return null;
            }
        }
        LineDataSet set1 = new LineDataSet(systolicEntries, "(Sys)");
        set1.setLineWidth(2.0f);
        set1.setCircleSize(2.0f);
        set1.setFillAlpha(65);
        set1.setFillColor(ColorTemplate.getHoloBlue());
        set1.setDrawValues(true);
        set1.setColor(this.context.getResources().getColor(C0644R.color.blood_pressure_color));
        set1.setCircleColor(this.context.getResources().getColor(C0644R.color.blood_pressure_color));
        LineDataSet set2 = new LineDataSet(diastolicEntries, "(Dia)");
        set2.setColor(ColorTemplate.getHoloBlue());
        set2.setCircleColor(ColorTemplate.getHoloBlue());
        set2.setLineWidth(2.0f);
        set2.setCircleSize(2.0f);
        set2.setFillAlpha(65);
        set2.setFillColor(ColorTemplate.getHoloBlue());
        set2.setDrawValues(true);
        set2.setColor(this.context.getResources().getColor(C0644R.color.colorAccent));
        set2.setCircleColor(this.context.getResources().getColor(C0644R.color.colorAccent));
        List bpdataSet = new ArrayList();
        bpdataSet.add(set1);
        bpdataSet.add(set2);
        return new LineData(bpValues, bpdataSet);
    }

    public LineData getLineData(long startTimestamp, long endTimestamp, int limit) {
        return null;
    }
}
