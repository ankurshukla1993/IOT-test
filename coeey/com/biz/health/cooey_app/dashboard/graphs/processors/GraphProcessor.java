package com.biz.health.cooey_app.dashboard.graphs.processors;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.LineData;

public interface GraphProcessor {
    void generateDynamicGraph(LineChart lineChart, LineData lineData);

    void generateGraph(LineChart lineChart, LineData lineData);

    LineData getLineData(long j, long j2, int i);
}
