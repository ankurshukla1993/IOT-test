package org.jetbrains.anko;

import android.app.Fragment;
import android.content.Context;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 0}, d1 = {"\u0000*\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a=\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u00062\u0014\b\b\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00010\bH\b\u001a8\u0010\u0000\u001a\u00020\u0001*\u00020\n2\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u00062\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00010\b\u001aA\u0010\u0000\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u000b2\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u00062\u0014\b\b\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00010\bH\b¨\u0006\f"}, d2 = {"selector", "", "Landroid/app/Fragment;", "title", "", "items", "", "onClick", "Lkotlin/Function1;", "", "Landroid/content/Context;", "Lorg/jetbrains/anko/AnkoContext;", "common-compileReleaseKotlin"}, k = 2, mv = {1, 1, 0})
/* compiled from: Selectors.kt */
public final class SelectorsKt {
    public static final void selector(AnkoContext<?> $receiver, @Nullable CharSequence title, @NotNull List<? extends CharSequence> items, @NotNull Function1<? super Integer, Unit> onClick) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(items, "items");
        Intrinsics.checkParameterIsNotNull(onClick, "onClick");
        selector($receiver.getCtx(), title, (List) items, (Function1) onClick);
    }

    public static final void selector(Fragment $receiver, @Nullable CharSequence title, @NotNull List<? extends CharSequence> items, @NotNull Function1<? super Integer, Unit> onClick) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(items, "items");
        Intrinsics.checkParameterIsNotNull(onClick, "onClick");
        selector((Context) $receiver.getActivity(), title, (List) items, (Function1) onClick);
    }

    public static final void selector(Context $receiver, @Nullable CharSequence title, @NotNull List<? extends CharSequence> items, @NotNull Function1<? super Integer, Unit> onClick) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(items, "items");
        Intrinsics.checkParameterIsNotNull(onClick, "onClick");
        AlertDialogBuilder $receiver2 = new AlertDialogBuilder($receiver);
        if (title != null) {
            $receiver2.title(title);
        }
        $receiver2.items((List) items, (Function1) onClick);
        $receiver2.show();
    }
}
