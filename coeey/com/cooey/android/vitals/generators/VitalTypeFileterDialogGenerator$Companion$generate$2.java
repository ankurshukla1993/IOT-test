package com.cooey.android.vitals.generators;

import android.content.DialogInterface;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import com.cooey.common.generators.TimelineItemTypeSelectionDialogGenerator;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\n¢\u0006\u0002\b\t"}, d2 = {"<anonymous>", "", "dialog", "Landroid/content/DialogInterface;", "kotlin.jvm.PlatformType", "which", "", "isChecked", "", "onClick"}, k = 3, mv = {1, 1, 7})
/* compiled from: VitalTypeFileterDialogGenerator.kt */
final class VitalTypeFileterDialogGenerator$Companion$generate$2 implements OnMultiChoiceClickListener {
    public static final VitalTypeFileterDialogGenerator$Companion$generate$2 INSTANCE = new VitalTypeFileterDialogGenerator$Companion$generate$2();

    VitalTypeFileterDialogGenerator$Companion$generate$2() {
    }

    public final void onClick(DialogInterface dialog, int which, boolean isChecked) {
        TimelineItemTypeSelectionDialogGenerator.Companion.getCheckedFeatures()[which] = isChecked;
        VitalTypeFileterDialogGenerator.Companion.getSelectedTypes().add((String) VitalTypeFileterDialogGenerator.Companion.getOptions().get(which));
    }
}
