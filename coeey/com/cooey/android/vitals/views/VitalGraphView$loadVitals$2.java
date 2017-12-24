package com.cooey.android.vitals.views;

import android.arch.lifecycle.Observer;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.cooey.android.vitals.R;
import com.cooey.android.vitals.Vital;
import com.db.chart.view.LineChartView;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref.ObjectRef;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "vitals", "", "Lcom/cooey/android/vitals/Vital;", "onChanged"}, k = 3, mv = {1, 1, 7})
/* compiled from: VitalGraphView.kt */
final class VitalGraphView$loadVitals$2<T> implements Observer<List<? extends Vital>> {
    final /* synthetic */ ObjectRef $fieldKeys;
    final /* synthetic */ VitalGraphView this$0;

    VitalGraphView$loadVitals$2(VitalGraphView vitalGraphView, ObjectRef objectRef) {
        this.this$0 = vitalGraphView;
        this.$fieldKeys = objectRef;
    }

    public final void onChanged(@Nullable List<Vital> vitals) {
        Integer num = null;
        this.this$0.removeAllViewsInLayout();
        VitalGraphView vitalGraphView = this.this$0;
        View inflate = LayoutInflater.from(this.this$0.getContext()).inflate(R.layout.vital_graph_layout, this.this$0, false);
        if (inflate == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.RelativeLayout");
        }
        TextView placeholderText;
        LineChartView lineChartView;
        Integer valueOf;
        vitalGraphView.fieldGraphView = (RelativeLayout) inflate;
        RelativeLayout access$getFieldGraphView$p = this.this$0.fieldGraphView;
        if (access$getFieldGraphView$p != null) {
            placeholderText = (TextView) access$getFieldGraphView$p.findViewById(R.id.placeholder_text);
        } else {
            placeholderText = null;
        }
        vitalGraphView = this.this$0;
        access$getFieldGraphView$p = this.this$0.fieldGraphView;
        if (access$getFieldGraphView$p != null) {
            lineChartView = (LineChartView) access$getFieldGraphView$p.findViewById(R.id.chart);
        } else {
            lineChartView = null;
        }
        vitalGraphView.chart = lineChartView;
        if (vitals != null) {
            valueOf = Integer.valueOf(vitals.size());
        } else {
            valueOf = null;
        }
        if (valueOf == null) {
            Intrinsics.throwNpe();
        }
        if (valueOf.intValue() > 0) {
            lineChartView = this.this$0.chart;
            if (lineChartView != null) {
                lineChartView.setVisibility(0);
            }
            if (placeholderText != null) {
                placeholderText.setVisibility(8);
            }
            if (vitals != null) {
                num = Integer.valueOf(vitals.size());
            }
            if (num == null) {
                Intrinsics.throwNpe();
            }
            VitalGraphView vitalGraphView2;
            List asReversed;
            if (num.intValue() > 7) {
                vitalGraphView2 = this.this$0;
                asReversed = CollectionsKt__ReversedViewsKt.asReversed(vitals.subList(0, 7));
                if (asReversed == null) {
                    Intrinsics.throwNpe();
                }
                vitalGraphView2.setupChart(asReversed, (ArrayList) this.$fieldKeys.element);
            } else {
                vitalGraphView2 = this.this$0;
                asReversed = CollectionsKt__ReversedViewsKt.asReversed(vitals);
                if (asReversed == null) {
                    Intrinsics.throwNpe();
                }
                vitalGraphView2.setupChart(asReversed, (ArrayList) this.$fieldKeys.element);
            }
        } else {
            lineChartView = this.this$0.chart;
            if (lineChartView != null) {
                lineChartView.setVisibility(8);
            }
            if (placeholderText != null) {
                placeholderText.setVisibility(0);
            }
        }
        this.this$0.addView(this.this$0.fieldGraphView);
        this.this$0.invalidate();
    }
}
