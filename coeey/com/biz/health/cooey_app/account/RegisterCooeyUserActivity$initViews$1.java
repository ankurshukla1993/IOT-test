package com.biz.health.cooey_app.account;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.util.Pair;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;
import com.biz.health.cooey_app.vitals.SublimePickerFragment;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 1, 7})
/* compiled from: RegisterCooeyUserActivity.kt */
final class RegisterCooeyUserActivity$initViews$1 implements OnClickListener {
    final /* synthetic */ RegisterCooeyUserActivity this$0;

    RegisterCooeyUserActivity$initViews$1(RegisterCooeyUserActivity registerCooeyUserActivity) {
        this.this$0 = registerCooeyUserActivity;
    }

    public final void onClick(View it) {
        SublimePickerFragment pickerFrag = new SublimePickerFragment();
        pickerFrag.setCallback(this.this$0);
        Pair optionsPair = this.this$0.getOptions$app_release();
        if (((Boolean) optionsPair.first).booleanValue()) {
            Bundle bundle = new Bundle();
            bundle.putParcelable("SUBLIME_OPTIONS", (Parcelable) optionsPair.second);
            pickerFrag.setArguments(bundle);
            pickerFrag.setStyle(1, 0);
            pickerFrag.show(this.this$0.getSupportFragmentManager(), "SUBLIME_PICKER");
            return;
        }
        Toast.makeText(this.this$0.getApplicationContext(), "No pickers activated", 0).show();
    }
}
