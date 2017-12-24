package com.cooey.android.vitals.views;

import android.view.View;
import android.view.View.OnClickListener;
import com.cooey.android.vitals.VitalBlueprint;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 1, 7})
/* compiled from: VitalBlueprintSelectionView.kt */
final class VitalBlueprintSelectionView$generateActionItems$1 implements OnClickListener {
    final /* synthetic */ VitalBlueprint $vitalBlueprint;
    final /* synthetic */ VitalBlueprintSelectionView this$0;

    VitalBlueprintSelectionView$generateActionItems$1(VitalBlueprintSelectionView vitalBlueprintSelectionView, VitalBlueprint vitalBlueprint) {
        this.this$0 = vitalBlueprintSelectionView;
        this.$vitalBlueprint = vitalBlueprint;
    }

    public final void onClick(View it) {
        this.this$0.showVitalInputDialog(this.$vitalBlueprint);
    }
}
