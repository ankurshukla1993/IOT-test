package com.cooey.common.generators;

import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/cooey/common/generators/TimelineItemTypeSelectionDialogGenerator;", "", "()V", "Companion", "common_release"}, k = 1, mv = {1, 1, 7})
/* compiled from: TimelineItemTypeSelectionDialogGenerator.kt */
public final class TimelineItemTypeSelectionDialogGenerator {
    public static final Companion Companion = new Companion();
    @NotNull
    private static boolean[] checkedFeatures = new boolean[]{true, true, true, true};

    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0018\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\"\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u000f0\u000eR\u001a\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\u0010"}, d2 = {"Lcom/cooey/common/generators/TimelineItemTypeSelectionDialogGenerator$Companion;", "", "()V", "checkedFeatures", "", "getCheckedFeatures", "()[Z", "setCheckedFeatures", "([Z)V", "generate", "Landroid/support/v7/app/AlertDialog;", "context", "Landroid/content/Context;", "filterChangeCallback", "Lkotlin/Function1;", "", "common_release"}, k = 1, mv = {1, 1, 7})
    /* compiled from: TimelineItemTypeSelectionDialogGenerator.kt */
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final boolean[] getCheckedFeatures() {
            return TimelineItemTypeSelectionDialogGenerator.checkedFeatures;
        }

        public final void setCheckedFeatures(@NotNull boolean[] <set-?>) {
            Intrinsics.checkParameterIsNotNull(<set-?>, "<set-?>");
            TimelineItemTypeSelectionDialogGenerator.checkedFeatures = <set-?>;
        }

        @NotNull
        public final AlertDialog generate(@NotNull Context context, @NotNull Function1<? super boolean[], Unit> filterChangeCallback) {
            Intrinsics.checkParameterIsNotNull(context, "context");
            Intrinsics.checkParameterIsNotNull(filterChangeCallback, "filterChangeCallback");
            Builder builder = new Builder(context);
            String[] features = (String[]) new String[]{"Vitals", "Notes", "Actions", "Events"};
            builder.setMultiChoiceItems((CharSequence[]) features, getCheckedFeatures(), (OnMultiChoiceClickListener) new TimelineItemTypeSelectionDialogGenerator$Companion$generate$1(Arrays.asList((String[]) Arrays.copyOf(features, features.length))));
            builder.setCancelable(false);
            builder.setTitle((CharSequence) "Filter By: ");
            builder.setPositiveButton((CharSequence) "OK", (OnClickListener) new TimelineItemTypeSelectionDialogGenerator$Companion$generate$2(filterChangeCallback));
            builder.setNegativeButton((CharSequence) "CANCEL", (OnClickListener) TimelineItemTypeSelectionDialogGenerator$Companion$generate$3.INSTANCE);
            AlertDialog dialog = builder.create();
            Intrinsics.checkExpressionValueIsNotNull(dialog, "dialog");
            return dialog;
        }
    }
}
