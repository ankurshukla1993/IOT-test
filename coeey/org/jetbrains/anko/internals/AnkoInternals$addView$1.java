package org.jetbrains.anko.internals;

import android.content.Context;
import android.view.View;
import android.view.ViewManager;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.anko.AnkoContext;

@Metadata(bv = {1, 0, 0}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "Lorg/jetbrains/anko/AnkoContext;", "Landroid/content/Context;", "invoke"}, k = 3, mv = {1, 1, 0})
/* compiled from: Internals.kt */
final class AnkoInternals$addView$1 extends Lambda implements Function1<AnkoContext<Context>, Unit> {
    final /* synthetic */ View $view;

    AnkoInternals$addView$1(View view) {
        this.$view = view;
        super(1);
    }

    public final void invoke(AnkoContext<Context> $receiver) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        AnkoInternals.INSTANCE.addView((ViewManager) $receiver, this.$view);
    }
}
