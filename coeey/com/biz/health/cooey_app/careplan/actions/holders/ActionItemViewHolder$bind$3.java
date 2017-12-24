package com.biz.health.cooey_app.careplan.actions.holders;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import com.biz.health.cooey_app.PatientApplication;
import com.cooey.android.users.old.utils.CTConstants;
import com.cooey.android.vitals.VitalBlueprint;
import com.cooey.android.vitals.VitalInputActivity;
import com.cooey.android.vitals.VitalsDatabase;
import com.cooey.android.vitals.dao.VitalBlueprintDao;
import com.cooey.common.vo.ActionItem;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 1, 7})
/* compiled from: ActionItemViewHolder.kt */
final class ActionItemViewHolder$bind$3 implements OnClickListener {
    final /* synthetic */ ActionItem $actionItem;
    final /* synthetic */ ActionItemViewHolder this$0;

    ActionItemViewHolder$bind$3(ActionItemViewHolder actionItemViewHolder, ActionItem actionItem) {
        this.this$0 = actionItemViewHolder;
        this.$actionItem = actionItem;
    }

    public final void onClick(View it) {
        if (StringsKt__StringsJVMKt.equals(this.$actionItem.getType(), CTConstants.VITAL_TYPE, true)) {
            VitalBlueprint vitalBlueprint;
            String type = (String) this.$actionItem.getParameterMap().get("type");
            VitalsDatabase vitalsDatabase = PatientApplication.Companion.getVitalsDatabase();
            if (vitalsDatabase != null) {
                VitalBlueprintDao VitalBlueprintDao = vitalsDatabase.VitalBlueprintDao();
                if (VitalBlueprintDao != null) {
                    if (type == null) {
                        Intrinsics.throwNpe();
                    }
                    vitalBlueprint = VitalBlueprintDao.getVitalBlueprintForType(type);
                    VitalInputActivity.Companion.setVitalBlueprint(vitalBlueprint);
                    VitalInputActivity.Companion.setVitalRepository(PatientApplication.Companion.getVitalsRepository());
                    VitalInputActivity.Companion.setUserId(this.$actionItem.getPatientId());
                    VitalInputActivity.Companion.setSession(PatientApplication.Companion.getSession());
                    this.this$0.itemView.getContext().startActivity(new Intent(this.this$0.itemView.getContext(), VitalInputActivity.class));
                    return;
                }
            }
            vitalBlueprint = null;
            VitalInputActivity.Companion.setVitalBlueprint(vitalBlueprint);
            VitalInputActivity.Companion.setVitalRepository(PatientApplication.Companion.getVitalsRepository());
            VitalInputActivity.Companion.setUserId(this.$actionItem.getPatientId());
            VitalInputActivity.Companion.setSession(PatientApplication.Companion.getSession());
            this.this$0.itemView.getContext().startActivity(new Intent(this.this$0.itemView.getContext(), VitalInputActivity.class));
            return;
        }
        StringsKt__StringsJVMKt.equals(this.$actionItem.getType(), "Diet", true);
    }
}
