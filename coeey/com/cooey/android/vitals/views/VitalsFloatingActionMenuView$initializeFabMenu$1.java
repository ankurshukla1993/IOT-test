package com.cooey.android.vitals.views;

import android.arch.lifecycle.Observer;
import android.widget.LinearLayout;
import com.cooey.android.vitals.VitalBlueprint;
import java.util.List;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "vitalBlueprints", "", "Lcom/cooey/android/vitals/VitalBlueprint;", "onChanged"}, k = 3, mv = {1, 1, 7})
/* compiled from: VitalsFloatingActionMenuView.kt */
final class VitalsFloatingActionMenuView$initializeFabMenu$1<T> implements Observer<List<VitalBlueprint>> {
    final /* synthetic */ VitalsFloatingActionMenuView this$0;

    VitalsFloatingActionMenuView$initializeFabMenu$1(VitalsFloatingActionMenuView vitalsFloatingActionMenuView) {
        this.this$0 = vitalsFloatingActionMenuView;
    }

    public final void onChanged(@Nullable List<VitalBlueprint> vitalBlueprints) {
        LinearLayout menuContainer = this.this$0.getMenuContainer();
        if (menuContainer != null) {
            menuContainer.removeAllViewsInLayout();
        }
        if (vitalBlueprints != null) {
            for (VitalBlueprint vitalBlueprint : vitalBlueprints) {
                this.this$0.generateMenuItems(vitalBlueprint);
            }
        }
        menuContainer = this.this$0.getMenuContainer();
        if (menuContainer != null) {
            menuContainer.invalidate();
        }
    }
}
