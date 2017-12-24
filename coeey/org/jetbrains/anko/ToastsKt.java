package org.jetbrains.anko;

import android.app.Fragment;
import android.content.Context;
import android.widget.Toast;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 0}, d1 = {"\u0000 \n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\b\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0005H\b\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0005\u001a\u0019\u0010\u0000\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u00072\u0006\u0010\u0003\u001a\u00020\u0004H\b\u001a\u0019\u0010\u0000\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u00072\u0006\u0010\u0003\u001a\u00020\u0005H\b\u001a\u0015\u0010\b\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\b\u001a\u0015\u0010\b\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0005H\b\u001a\u0012\u0010\b\u001a\u00020\u0001*\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0012\u0010\b\u001a\u00020\u0001*\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0005\u001a\u0019\u0010\b\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u00072\u0006\u0010\u0003\u001a\u00020\u0004H\b\u001a\u0019\u0010\b\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u00072\u0006\u0010\u0003\u001a\u00020\u0005H\b¨\u0006\t"}, d2 = {"longToast", "", "Landroid/app/Fragment;", "message", "", "", "Landroid/content/Context;", "Lorg/jetbrains/anko/AnkoContext;", "toast", "common-compileReleaseKotlin"}, k = 2, mv = {1, 1, 0})
/* compiled from: Toasts.kt */
public final class ToastsKt {
    public static final void toast(AnkoContext<?> $receiver, int message) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        toast($receiver.getCtx(), message);
    }

    public static final void toast(Fragment $receiver, int message) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        toast((Context) $receiver.getActivity(), message);
    }

    public static final void toast(Context $receiver, int message) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Toast.makeText($receiver, message, 0).show();
    }

    public static final void toast(AnkoContext<?> $receiver, @NotNull CharSequence message) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(message, "message");
        toast($receiver.getCtx(), message);
    }

    public static final void toast(Fragment $receiver, @NotNull CharSequence message) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(message, "message");
        toast((Context) $receiver.getActivity(), message);
    }

    public static final void toast(Context $receiver, @NotNull CharSequence message) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(message, "message");
        Toast.makeText($receiver, message, 0).show();
    }

    public static final void longToast(AnkoContext<?> $receiver, int message) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        longToast($receiver.getCtx(), message);
    }

    public static final void longToast(Fragment $receiver, int message) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        longToast((Context) $receiver.getActivity(), message);
    }

    public static final void longToast(Context $receiver, int message) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Toast.makeText($receiver, message, 1).show();
    }

    public static final void longToast(AnkoContext<?> $receiver, @NotNull CharSequence message) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(message, "message");
        longToast($receiver.getCtx(), message);
    }

    public static final void longToast(Fragment $receiver, @NotNull CharSequence message) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(message, "message");
        longToast((Context) $receiver.getActivity(), message);
    }

    public static final void longToast(Context $receiver, @NotNull CharSequence message) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(message, "message");
        Toast.makeText($receiver, message, 1).show();
    }
}
