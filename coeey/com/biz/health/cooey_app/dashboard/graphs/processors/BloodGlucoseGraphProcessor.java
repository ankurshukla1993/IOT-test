package com.biz.health.cooey_app.dashboard.graphs.processors;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.view.View.OnClickListener;
import com.biz.health.cooey_app.C0644R;
import com.biz.health.cooey_app.vitals.BPActivity;
import com.cooey.common.stores.StyleStore;
import com.cooey.common.vo.Vital;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.XAxis.XAxisPosition;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import java.util.ArrayList;
import java.util.List;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class BloodGlucoseGraphProcessor implements GraphProcessor {
    private static final String PREFERENCE_KEY = "Preference";
    private static final String PREFERENCE_NAME1 = "EXTRA VITALS";
    static DateTimeFormatter builder = DateTimeFormat.forPattern("dd/MM");
    private final Context context;
    StyleStore styleStore;

    class C06801 implements OnClickListener {
        C06801() {
        }

        public void onClick(View v) {
            BloodGlucoseGraphProcessor.this.context.startActivity(new Intent(BloodGlucoseGraphProcessor.this.context, BPActivity.class));
        }
    }

    public BloodGlucoseGraphProcessor(Context context) {
        this.context = context;
        this.styleStore = new StyleStore(context);
    }

    public void generateGraph(LineChart lineChart, LineData lineData) {
        if (lineData == null) {
            lineChart.setNoDataTextDescription("Also prevents hypo &  hyperglycaemia. Start today!");
            lineChart.setNoDataText("Monitoring helps reduce HbA1c.");
            lineChart.setDescriptionTypeface(this.styleStore.getFont("Lato"));
            lineChart.clear();
            lineChart.invalidate();
            return;
        }
        lineChart.setMaxVisibleValueCount(5);
        lineChart.setData(lineData);
        lineChart.setTouchEnabled(false);
        lineChart.setOnChartGestureListener(null);
        lineChart.setOnChartValueSelectedListener(null);
        lineChart.setOnClickListener(new C06801());
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
        XAxis xAxis = lineChart.getXAxis();
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

    public LineData getLineData(List<Vital> bloodGlucoseDataList) {
        try {
            ArrayList<Entry> randomBloodSugarEntries = new ArrayList();
            List glucoseValues = new ArrayList();
            int i = bloodGlucoseDataList.size() - 1;
            int j = 0;
            while (i >= 0) {
                randomBloodSugarEntries.add(new Entry(Float.valueOf((String) ((Vital) bloodGlucoseDataList.get(i)).getParameterMap().get("glucose")).floatValue(), j));
                if (((Vital) bloodGlucoseDataList.get(i)).getTakenOn() > 0) {
                    glucoseValues.add(j, new DateTime(((Vital) bloodGlucoseDataList.get(i)).getTakenOn()).toString(builder));
                } else {
                    glucoseValues.add(j, new DateTime().toString(builder));
                }
                i--;
                j++;
            }
            LineDataSet set4 = new LineDataSet(randomBloodSugarEntries, "(Random)");
            set4.setColor(ColorTemplate.getHoloBlue());
            set4.setCircleColor(ColorTemplate.getHoloBlue());
            set4.setLineWidth(2.0f);
            set4.setCircleSize(2.0f);
            set4.setFillAlpha(65);
            set4.setFillColor(ColorTemplate.getHoloBlue());
            set4.setHighLightColor(Color.rgb(244, 117, 117));
            set4.setColor(this.context.getResources().getColor(C0644R.color.colorAccent));
            set4.setCircleColor(this.context.getResources().getColor(C0644R.color.colorAccent));
            set4.setDrawValues(false);
            List bpdataSet = new ArrayList();
            bpdataSet.add(set4);
            return new LineData(glucoseValues, bpdataSet);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public LineData getLineData(long startTimestamp, long endTimestamp, int limit) {
        return null;
    }
}
