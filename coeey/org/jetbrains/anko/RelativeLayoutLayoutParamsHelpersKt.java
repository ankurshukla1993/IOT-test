package org.jetbrains.anko;

import android.view.View;
import android.widget.RelativeLayout.LayoutParams;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 0}, d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0015\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\b\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\b\u001a\u0015\u0010\u0007\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\b\u001a\r\u0010\b\u001a\u00020\u0001*\u00020\u0002H\b\u001a\r\u0010\t\u001a\u00020\u0001*\u00020\u0002H\b\u001a\r\u0010\n\u001a\u00020\u0001*\u00020\u0002H\b\u001a\r\u0010\u000b\u001a\u00020\u0001*\u00020\u0002H\b\u001a\r\u0010\f\u001a\u00020\u0001*\u00020\u0002H\b\u001a\r\u0010\r\u001a\u00020\u0001*\u00020\u0002H\b\u001a\u0015\u0010\u000e\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\b\u001a\u0015\u0010\u000f\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\b\u001a\u0015\u0010\u000f\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\b\u001a\u0015\u0010\u0010\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\b\u001a\u0015\u0010\u0010\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\b\u001a\r\u0010\u0011\u001a\u00020\u0001*\u00020\u0002H\b\u001a\r\u0010\u0012\u001a\u00020\u0001*\u00020\u0002H\b\u001a\r\u0010\u0013\u001a\u00020\u0001*\u00020\u0002H\b\u001a\u0015\u0010\u0014\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\b\u001a\u0015\u0010\u0014\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\b\u001a\u0015\u0010\u0015\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\b\u001a\u0015\u0010\u0015\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\b\u001a\u0015\u0010\u0016\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\b\u001a\u0015\u0010\u0016\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\b\u001a\u0015\u0010\u0017\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\b\u001a\u0015\u0010\u0017\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\b\u001a\u0015\u0010\u0018\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\b\u001a\u0015\u0010\u0018\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\b\u001a\u0015\u0010\u0019\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\b\u001a\u0015\u0010\u0019\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\b\u001a\u0015\u0010\u001a\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\b\u001a\u0015\u0010\u001a\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\b¨\u0006\u001b"}, d2 = {"above", "", "Landroid/widget/RelativeLayout$LayoutParams;", "view", "Landroid/view/View;", "id", "", "alignEnd", "alignParentBottom", "alignParentEnd", "alignParentLeft", "alignParentRight", "alignParentStart", "alignParentTop", "alignStart", "below", "bottomOf", "centerHorizontally", "centerInParent", "centerVertically", "leftOf", "rightOf", "sameBottom", "sameLeft", "sameRight", "sameTop", "topOf", "common-compileReleaseKotlin"}, k = 2, mv = {1, 1, 0})
/* compiled from: RelativeLayoutLayoutParamsHelpers.kt */
public final class RelativeLayoutLayoutParamsHelpersKt {
    public static final void topOf(LayoutParams $receiver, @NotNull View view) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(view, "view");
        $receiver.addRule(2, view.getId());
    }

    public static final void above(LayoutParams $receiver, @NotNull View view) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(view, "view");
        $receiver.addRule(2, view.getId());
    }

    public static final void bottomOf(LayoutParams $receiver, @NotNull View view) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(view, "view");
        $receiver.addRule(3, view.getId());
    }

    public static final void below(LayoutParams $receiver, @NotNull View view) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(view, "view");
        $receiver.addRule(3, view.getId());
    }

    public static final void leftOf(LayoutParams $receiver, @NotNull View view) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(view, "view");
        $receiver.addRule(0, view.getId());
    }

    public static final void rightOf(LayoutParams $receiver, @NotNull View view) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(view, "view");
        $receiver.addRule(1, view.getId());
    }

    public static final void sameLeft(LayoutParams $receiver, @NotNull View view) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(view, "view");
        $receiver.addRule(5, view.getId());
    }

    public static final void sameTop(LayoutParams $receiver, @NotNull View view) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(view, "view");
        $receiver.addRule(6, view.getId());
    }

    public static final void sameRight(LayoutParams $receiver, @NotNull View view) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(view, "view");
        $receiver.addRule(7, view.getId());
    }

    public static final void sameBottom(LayoutParams $receiver, @NotNull View view) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(view, "view");
        $receiver.addRule(8, view.getId());
    }

    public static final void topOf(LayoutParams $receiver, int id) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        $receiver.addRule(2, id);
    }

    public static final void above(LayoutParams $receiver, int id) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        $receiver.addRule(2, id);
    }

    public static final void below(LayoutParams $receiver, int id) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        $receiver.addRule(3, id);
    }

    public static final void bottomOf(LayoutParams $receiver, int id) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        $receiver.addRule(3, id);
    }

    public static final void leftOf(LayoutParams $receiver, int id) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        $receiver.addRule(0, id);
    }

    public static final void rightOf(LayoutParams $receiver, int id) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        $receiver.addRule(1, id);
    }

    public static final void sameLeft(LayoutParams $receiver, int id) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        $receiver.addRule(5, id);
    }

    public static final void sameTop(LayoutParams $receiver, int id) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        $receiver.addRule(6, id);
    }

    public static final void sameRight(LayoutParams $receiver, int id) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        $receiver.addRule(7, id);
    }

    public static final void sameBottom(LayoutParams $receiver, int id) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        $receiver.addRule(8, id);
    }

    public static final void alignStart(LayoutParams $receiver, int id) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        $receiver.addRule(18, id);
    }

    public static final void alignEnd(LayoutParams $receiver, int id) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        $receiver.addRule(19, id);
    }

    public static final void alignParentTop(LayoutParams $receiver) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        $receiver.addRule(10);
    }

    public static final void alignParentRight(LayoutParams $receiver) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        $receiver.addRule(11);
    }

    public static final void alignParentBottom(LayoutParams $receiver) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        $receiver.addRule(12);
    }

    public static final void alignParentLeft(LayoutParams $receiver) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        $receiver.addRule(9);
    }

    public static final void centerHorizontally(LayoutParams $receiver) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        $receiver.addRule(14);
    }

    public static final void centerVertically(LayoutParams $receiver) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        $receiver.addRule(15);
    }

    public static final void centerInParent(LayoutParams $receiver) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        $receiver.addRule(13);
    }

    public static final void alignParentStart(LayoutParams $receiver) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        $receiver.addRule(20);
    }

    public static final void alignParentEnd(LayoutParams $receiver) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        $receiver.addRule(21);
    }
}
