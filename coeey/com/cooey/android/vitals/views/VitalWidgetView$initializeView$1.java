package com.cooey.android.vitals.views;

import android.arch.lifecycle.Observer;
import com.cooey.android.vitals.VitalBlueprint;
import com.cooey.android.vitals.adapters.VitalWidgetRecyclerAdapter;
import java.util.List;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "vitalBlueprints", "", "Lcom/cooey/android/vitals/VitalBlueprint;", "onChanged"}, k = 3, mv = {1, 1, 7})
/* compiled from: VitalWidgetView.kt */
final class VitalWidgetView$initializeView$1<T> implements Observer<List<VitalBlueprint>> {
    final /* synthetic */ VitalWidgetView this$0;

    VitalWidgetView$initializeView$1(VitalWidgetView vitalWidgetView) {
        this.this$0 = vitalWidgetView;
    }

    public final void onChanged(@Nullable List<VitalBlueprint> vitalBlueprints) {
        VitalWidgetRecyclerAdapter vitalWidgetRecyclerAdapter = this.this$0.getVitalWidgetRecyclerAdapter();
        if (vitalWidgetRecyclerAdapter != null) {
            vitalWidgetRecyclerAdapter.setVitalBlueprints(vitalBlueprints);
        }
        vitalWidgetRecyclerAdapter = this.this$0.getVitalWidgetRecyclerAdapter();
        if (vitalWidgetRecyclerAdapter != null) {
            vitalWidgetRecyclerAdapter.notifyDataSetChanged();
        }
    }
}
