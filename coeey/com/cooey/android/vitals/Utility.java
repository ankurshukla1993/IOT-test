package com.cooey.android.vitals;

import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0016\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/cooey/android/vitals/Utility;", "", "()V", "Companion", "vitals_release"}, k = 1, mv = {1, 1, 7})
/* compiled from: Utility.kt */
public class Utility {
    public static final Companion Companion = new Companion();

    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b¨\u0006\t"}, d2 = {"Lcom/cooey/android/vitals/Utility$Companion;", "", "()V", "showErrorAlert", "", "context", "Landroid/content/Context;", "message", "", "vitals_release"}, k = 1, mv = {1, 1, 7})
    /* compiled from: Utility.kt */
    public static final class Companion {
        private Companion() {
        }

        public final void showErrorAlert(@NotNull Context context, @NotNull String message) {
            Intrinsics.checkParameterIsNotNull(context, "context");
            Intrinsics.checkParameterIsNotNull(message, "message");
            AlertDialog simpleAlert = new Builder(context).create();
            simpleAlert.setTitle("Error");
            simpleAlert.setMessage(message);
            simpleAlert.setButton(-1, (CharSequence) "OK", (OnClickListener) Utility$Companion$showErrorAlert$1.INSTANCE);
            simpleAlert.show();
        }
    }
}
