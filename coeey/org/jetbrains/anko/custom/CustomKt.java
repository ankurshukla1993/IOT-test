package org.jetbrains.anko.custom;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewManager;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.anko.internals.AnkoInternals;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 0}, d1 = {"\u00002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u001aQ\u0010\u0000\u001a\u0002H\u0001\"\b\b\u0000\u0010\u0001*\u00020\u0002*\u00020\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u0002H\u00010\u00052\u0006\u0010\u0007\u001a\u00020\b2\u0017\u0010\t\u001a\u0013\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u00020\n0\u0005¢\u0006\u0002\b\u000bH\b¢\u0006\u0002\u0010\f\u001aQ\u0010\u0000\u001a\u0002H\u0001\"\b\b\u0000\u0010\u0001*\u00020\u0002*\u00020\u00062\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u0002H\u00010\u00052\u0006\u0010\u0007\u001a\u00020\b2\u0017\u0010\t\u001a\u0013\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u00020\n0\u0005¢\u0006\u0002\b\u000bH\b¢\u0006\u0002\u0010\r\u001aQ\u0010\u0000\u001a\u0002H\u0001\"\b\b\u0000\u0010\u0001*\u00020\u0002*\u00020\u000e2\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u0002H\u00010\u00052\u0006\u0010\u0007\u001a\u00020\b2\u0017\u0010\t\u001a\u0013\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u00020\n0\u0005¢\u0006\u0002\b\u000bH\b¢\u0006\u0002\u0010\u000f\u001aA\u0010\u0010\u001a\u0002H\u0001\"\n\b\u0000\u0010\u0001\u0018\u0001*\u00020\u0002*\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\u0017\u0010\t\u001a\u0013\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u00020\n0\u0005¢\u0006\u0002\b\u000bH\b¢\u0006\u0002\u0010\u0011\u001aA\u0010\u0010\u001a\u0002H\u0001\"\n\b\u0000\u0010\u0001\u0018\u0001*\u00020\u0002*\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\u0017\u0010\t\u001a\u0013\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u00020\n0\u0005¢\u0006\u0002\b\u000bH\b¢\u0006\u0002\u0010\u0012\u001aA\u0010\u0010\u001a\u0002H\u0001\"\n\b\u0000\u0010\u0001\u0018\u0001*\u00020\u0002*\u00020\u000e2\b\b\u0002\u0010\u0007\u001a\u00020\b2\u0017\u0010\t\u001a\u0013\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u00020\n0\u0005¢\u0006\u0002\b\u000bH\b¢\u0006\u0002\u0010\u0013¨\u0006\u0014"}, d2 = {"ankoView", "T", "Landroid/view/View;", "Landroid/app/Activity;", "factory", "Lkotlin/Function1;", "Landroid/content/Context;", "theme", "", "init", "", "Lkotlin/ExtensionFunctionType;", "(Landroid/app/Activity;Lkotlin/jvm/functions/Function1;ILkotlin/jvm/functions/Function1;)Landroid/view/View;", "(Landroid/content/Context;Lkotlin/jvm/functions/Function1;ILkotlin/jvm/functions/Function1;)Landroid/view/View;", "Landroid/view/ViewManager;", "(Landroid/view/ViewManager;Lkotlin/jvm/functions/Function1;ILkotlin/jvm/functions/Function1;)Landroid/view/View;", "customView", "(Landroid/app/Activity;ILkotlin/jvm/functions/Function1;)Landroid/view/View;", "(Landroid/content/Context;ILkotlin/jvm/functions/Function1;)Landroid/view/View;", "(Landroid/view/ViewManager;ILkotlin/jvm/functions/Function1;)Landroid/view/View;", "common-compileReleaseKotlin"}, k = 2, mv = {1, 1, 0})
/* compiled from: Custom.kt */
public final class CustomKt {
    @NotNull
    public static final <T extends View> T ankoView(ViewManager $receiver, @NotNull Function1<? super Context, ? extends T> factory, int theme, @NotNull Function1<? super T, Unit> init) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(factory, "factory");
        Intrinsics.checkParameterIsNotNull(init, "init");
        View view = (View) factory.invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext($receiver), theme));
        init.invoke(view);
        AnkoInternals.INSTANCE.addView($receiver, view);
        return view;
    }

    private static final <T extends View> T customView(ViewManager $receiver, int theme, Function1<? super T, Unit> init) {
        Context ctx = AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext($receiver), theme);
        Intrinsics.reifiedOperationMarker(4, "T");
        View view$iv = AnkoInternals.initiateView(ctx, View.class);
        init.invoke(view$iv);
        Unit unit = Unit.INSTANCE;
        AnkoInternals.INSTANCE.addView($receiver, view$iv);
        return view$iv;
    }

    @NotNull
    public static final <T extends View> T ankoView(Context $receiver, @NotNull Function1<? super Context, ? extends T> factory, int theme, @NotNull Function1<? super T, Unit> init) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(factory, "factory");
        Intrinsics.checkParameterIsNotNull(init, "init");
        View view = (View) factory.invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded($receiver, theme));
        init.invoke(view);
        AnkoInternals.INSTANCE.addView($receiver, view);
        return view;
    }

    private static final <T extends View> T customView(Context $receiver, int theme, Function1<? super T, Unit> init) {
        Context ctx = AnkoInternals.INSTANCE.wrapContextIfNeeded($receiver, theme);
        Intrinsics.reifiedOperationMarker(4, "T");
        View view$iv = AnkoInternals.initiateView(ctx, View.class);
        init.invoke(view$iv);
        Unit unit = Unit.INSTANCE;
        AnkoInternals.INSTANCE.addView($receiver, view$iv);
        return view$iv;
    }

    @NotNull
    public static final <T extends View> T ankoView(Activity $receiver, @NotNull Function1<? super Context, ? extends T> factory, int theme, @NotNull Function1<? super T, Unit> init) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(factory, "factory");
        Intrinsics.checkParameterIsNotNull(init, "init");
        View view = (View) factory.invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded($receiver, theme));
        init.invoke(view);
        AnkoInternals.INSTANCE.addView($receiver, view);
        return view;
    }

    private static final <T extends View> T customView(Activity $receiver, int theme, Function1<? super T, Unit> init) {
        Context ctx = AnkoInternals.INSTANCE.wrapContextIfNeeded($receiver, theme);
        Intrinsics.reifiedOperationMarker(4, "T");
        View view$iv = AnkoInternals.initiateView(ctx, View.class);
        init.invoke(view$iv);
        Unit unit = Unit.INSTANCE;
        AnkoInternals.INSTANCE.addView($receiver, view$iv);
        return view$iv;
    }
}
