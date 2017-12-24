package com.biz.health.cooey_app.secondary_vital;

import android.support.design.widget.BottomSheetBehavior;
import android.view.View;
import android.view.View.OnClickListener;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "view", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 1, 7})
/* compiled from: SecondaryVitalsActivity.kt */
final class SecondaryVitalsActivity$onCreate$1 implements OnClickListener {
    final /* synthetic */ SecondaryVitalsActivity this$0;

    SecondaryVitalsActivity$onCreate$1(SecondaryVitalsActivity secondaryVitalsActivity) {
        this.this$0 = secondaryVitalsActivity;
    }

    public final void onClick(View view) {
        BottomSheetBehavior access$getBottomSheetBehavior$p = this.this$0.bottomSheetBehavior;
        if (access$getBottomSheetBehavior$p != null) {
            access$getBottomSheetBehavior$p.setState(3);
        }
    }
}
