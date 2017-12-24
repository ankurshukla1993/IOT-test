package org.jetbrains.anko;

import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.support.v4.app.NotificationCompat;
import android.view.KeyEvent;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 0}, d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u000e\u0010\u0007\u001a\n \u0004*\u0004\u0018\u00010\b0\bH\n¢\u0006\u0002\b\t"}, d2 = {"<anonymous>", "", "dialog", "Landroid/content/DialogInterface;", "kotlin.jvm.PlatformType", "keyCode", "", "event", "Landroid/view/KeyEvent;", "onKey"}, k = 3, mv = {1, 1, 0})
/* compiled from: AlertDialogBuilder.kt */
final class AlertDialogBuilder$onKey$1 implements OnKeyListener {
    final /* synthetic */ Function2 $callback;

    AlertDialogBuilder$onKey$1(Function2 function2) {
        this.$callback = function2;
    }

    public final boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
        Function2 function2 = this.$callback;
        Integer valueOf = Integer.valueOf(keyCode);
        Intrinsics.checkExpressionValueIsNotNull(event, NotificationCompat.CATEGORY_EVENT);
        return ((Boolean) function2.invoke(valueOf, event)).booleanValue();
    }
}
