package com.cooey.android.vitals;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "view", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 1, 7})
/* compiled from: VitalListActivity.kt */
final class VitalListActivity$onCreate$1 implements OnClickListener {
    final /* synthetic */ VitalListActivity this$0;

    VitalListActivity$onCreate$1(VitalListActivity vitalListActivity) {
        this.this$0 = vitalListActivity;
    }

    public final void onClick(View view) {
        VitalInputActivity.Companion.setVitalBlueprint(VitalListActivity.Companion.getVitalBlueprint());
        VitalInputActivity.Companion.setVitalRepository(VitalListActivity.Companion.getVitalRepository());
        VitalInputActivity.Companion.setUserId(VitalListActivity.Companion.getUserId());
        VitalInputActivity.Companion.setSession(VitalListActivity.Companion.getSession());
        this.this$0.startActivity(new Intent(this.this$0, VitalInputActivity.class));
    }
}
