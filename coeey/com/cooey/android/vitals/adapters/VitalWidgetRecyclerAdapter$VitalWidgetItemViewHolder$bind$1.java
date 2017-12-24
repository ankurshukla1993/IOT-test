package com.cooey.android.vitals.adapters;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import com.cooey.android.vitals.VitalBlueprint;
import com.cooey.android.vitals.VitalListActivity;
import com.cooey.android.vitals.adapters.VitalWidgetRecyclerAdapter.VitalWidgetItemViewHolder;
import com.cooey.android.vitals.repositories.VitalRepository;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 1, 7})
/* compiled from: VitalWidgetRecyclerAdapter.kt */
final class VitalWidgetRecyclerAdapter$VitalWidgetItemViewHolder$bind$1 implements OnClickListener {
    final /* synthetic */ String $userId;
    final /* synthetic */ VitalBlueprint $vitalBlueprint;
    final /* synthetic */ VitalRepository $vitalRepository;
    final /* synthetic */ VitalWidgetItemViewHolder this$0;

    VitalWidgetRecyclerAdapter$VitalWidgetItemViewHolder$bind$1(VitalWidgetItemViewHolder vitalWidgetItemViewHolder, String str, VitalBlueprint vitalBlueprint, VitalRepository vitalRepository) {
        this.this$0 = vitalWidgetItemViewHolder;
        this.$userId = str;
        this.$vitalBlueprint = vitalBlueprint;
        this.$vitalRepository = vitalRepository;
    }

    public final void onClick(View it) {
        VitalListActivity.Companion.setUserId(this.$userId);
        VitalListActivity.Companion.setVitalBlueprint(this.$vitalBlueprint);
        VitalListActivity.Companion.setVitalRepository(this.$vitalRepository);
        VitalListActivity.Companion.setCommonDatabase(this.this$0.this$0.getCommonDatabase());
        this.this$0.this$0.getContext().startActivity(new Intent(this.this$0.this$0.getContext(), VitalListActivity.class));
    }
}
