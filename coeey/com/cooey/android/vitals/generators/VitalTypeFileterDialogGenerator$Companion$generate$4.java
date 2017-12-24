package com.cooey.android.vitals.generators;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", "dialog", "Landroid/content/DialogInterface;", "kotlin.jvm.PlatformType", "which", "", "onClick"}, k = 3, mv = {1, 1, 7})
/* compiled from: VitalTypeFileterDialogGenerator.kt */
final class VitalTypeFileterDialogGenerator$Companion$generate$4 implements OnClickListener {
    public static final VitalTypeFileterDialogGenerator$Companion$generate$4 INSTANCE = new VitalTypeFileterDialogGenerator$Companion$generate$4();

    VitalTypeFileterDialogGenerator$Companion$generate$4() {
    }

    public final void onClick(DialogInterface dialog, int which) {
        dialog.dismiss();
    }
}
