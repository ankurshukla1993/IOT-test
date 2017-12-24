package com.biz.health.cooey_app.dashboard.graphs.processors;

import com.cooey.android.vitals.VitalBlueprint;
import com.github.mikephil.charting.charts.LineChart;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\b¨\u0006\t"}, d2 = {"Lcom/biz/health/cooey_app/dashboard/graphs/processors/VitalGraphProcessor;", "", "()V", "generateGraph", "", "lineChart", "Lcom/github/mikephil/charting/charts/LineChart;", "vitalBlueprint", "Lcom/cooey/android/vitals/VitalBlueprint;", "app_release"}, k = 1, mv = {1, 1, 7})
/* compiled from: VitalGraphProcessor.kt */
public final class VitalGraphProcessor {
    public final void generateGraph(@Nullable LineChart lineChart, @NotNull VitalBlueprint vitalBlueprint) {
        Intrinsics.checkParameterIsNotNull(vitalBlueprint, "vitalBlueprint");
    }
}
