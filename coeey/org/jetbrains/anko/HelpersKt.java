package org.jetbrains.anko;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Build.VERSION;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.ClosedRange;
import org.jetbrains.anko.internals.AnkoInternals;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 0}, d1 = {"\u0000f\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a \u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\b0\u0007\"\u0004\b\u0000\u0010\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\b0\n\u001a\u001f\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\f0\nH\b\u001a\u001f\u0010\u000e\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\f0\nH\b\u001a¶\u0001\u0010\u000f\u001a\u0004\u0018\u0001H\b\"\b\b\u0000\u0010\b*\u00020\u0010*\u00020\u00112\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0010\b\u0002\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u00152\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00172\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00192\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\u001b2\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\u001b2\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u00012\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\b0\nH\b¢\u0006\u0002\u0010#\u001a¶\u0001\u0010\u000f\u001a\u0004\u0018\u0001H\b\"\b\b\u0000\u0010\b*\u00020\u0010*\u00020$2\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0010\b\u0002\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u00152\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00172\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00192\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\u001b2\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\u001b2\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u00012\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\b0\nH\b¢\u0006\u0002\u0010%\u001a¶\u0001\u0010\u000f\u001a\u0004\u0018\u0001H\b\"\b\b\u0000\u0010\b*\u00020\u0010*\u00020&2\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0010\b\u0002\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u00152\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00172\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00192\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\u001b2\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\u001b2\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u00012\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\b0\nH\b¢\u0006\u0002\u0010'\u001aº\u0001\u0010\u000f\u001a\u0004\u0018\u0001H\b\"\b\b\u0000\u0010\b*\u00020\u0010*\u0006\u0012\u0002\b\u00030(2\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0010\b\u0002\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u00152\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00172\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00192\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\u001b2\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\u001b2\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u00012\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\b0\nH\b¢\u0006\u0002\u0010)\u001a\u0012\u0010*\u001a\u00020\u0001*\u00020\u00012\u0006\u0010+\u001a\u00020\u0001\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00018F¢\u0006\u0006\u001a\u0004\b\u0002\u0010\u0003\"\u0015\u0010\u0004\u001a\u00020\u0001*\u00020\u00018F¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0003¨\u0006,"}, d2 = {"gray", "", "getGray", "(I)I", "opaque", "getOpaque", "attempt", "Lorg/jetbrains/anko/AttemptResult;", "T", "f", "Lkotlin/Function0;", "doFromSdk", "", "version", "doIfSdk", "configuration", "", "Landroid/app/Activity;", "screenSize", "Lorg/jetbrains/anko/ScreenSize;", "density", "Lkotlin/ranges/ClosedRange;", "language", "", "orientation", "Lorg/jetbrains/anko/Orientation;", "long", "", "fromSdk", "sdk", "uiMode", "Lorg/jetbrains/anko/UiMode;", "nightMode", "rightToLeft", "smallestWidth", "(Landroid/app/Activity;Lorg/jetbrains/anko/ScreenSize;Lkotlin/ranges/ClosedRange;Ljava/lang/String;Lorg/jetbrains/anko/Orientation;Ljava/lang/Boolean;Ljava/lang/Integer;Ljava/lang/Integer;Lorg/jetbrains/anko/UiMode;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Integer;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "Landroid/app/Fragment;", "(Landroid/app/Fragment;Lorg/jetbrains/anko/ScreenSize;Lkotlin/ranges/ClosedRange;Ljava/lang/String;Lorg/jetbrains/anko/Orientation;Ljava/lang/Boolean;Ljava/lang/Integer;Ljava/lang/Integer;Lorg/jetbrains/anko/UiMode;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Integer;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "Landroid/content/Context;", "(Landroid/content/Context;Lorg/jetbrains/anko/ScreenSize;Lkotlin/ranges/ClosedRange;Ljava/lang/String;Lorg/jetbrains/anko/Orientation;Ljava/lang/Boolean;Ljava/lang/Integer;Ljava/lang/Integer;Lorg/jetbrains/anko/UiMode;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Integer;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "Lorg/jetbrains/anko/AnkoContext;", "(Lorg/jetbrains/anko/AnkoContext;Lorg/jetbrains/anko/ScreenSize;Lkotlin/ranges/ClosedRange;Ljava/lang/String;Lorg/jetbrains/anko/Orientation;Ljava/lang/Boolean;Ljava/lang/Integer;Ljava/lang/Integer;Lorg/jetbrains/anko/UiMode;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Integer;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "withAlpha", "alpha", "common-compileReleaseKotlin"}, k = 2, mv = {1, 1, 0})
/* compiled from: Helpers.kt */
public final class HelpersKt {
    public static final int withAlpha(int r1, int r2) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.DecodeException: Load method exception in method: org.jetbrains.anko.HelpersKt.withAlpha(int, int):int
	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:116)
	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:249)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
Caused by: java.lang.NullPointerException
*/
        /*
        if (r4 < 0) goto L_0x0019;
    L_0x0002:
        r1 = 255; // 0xff float:3.57E-43 double:1.26E-321;
        if (r4 > r1) goto L_0x0019;
    L_0x0006:
        r1 = 1;
        if (r1 != 0) goto L_0x001b;
    L_0x000b:
        r0 = "Failed requirement";
        r1 = new java.lang.IllegalArgumentException;
        r2 = r0.toString();
        r1.<init>(r2);
        r1 = (java.lang.Throwable) r1;
        throw r1;
    L_0x0019:
        r1 = 0;
        goto L_0x0007;
    L_0x001b:
        r1 = 16777215; // 0xffffff float:2.3509886E-38 double:8.2890456E-317;
        r1 = r1 & r3;
        r2 = r4 << 24;
        r1 = r1 | r2;
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jetbrains.anko.HelpersKt.withAlpha(int, int):int");
    }

    public static final int getGray(int $receiver) {
        return (($receiver << 8) | $receiver) | ($receiver << 16);
    }

    public static final int getOpaque(int $receiver) {
        return ((int) 4278190080L) | $receiver;
    }

    @Nullable
    public static final <T> T configuration(Context $receiver, @Nullable ScreenSize screenSize, @Nullable ClosedRange<Integer> density, @Nullable String language, @Nullable Orientation orientation, @Nullable Boolean long_, @Nullable Integer fromSdk, @Nullable Integer sdk, @Nullable UiMode uiMode, @Nullable Boolean nightMode, @Nullable Boolean rightToLeft, @Nullable Integer smallestWidth, @NotNull Function0<? extends T> f) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(f, "f");
        return AnkoInternals.testConfiguration($receiver, screenSize, density, language, orientation, long_, fromSdk, sdk, uiMode, nightMode, rightToLeft, smallestWidth) ? f.invoke() : null;
    }

    @Nullable
    public static final <T> T configuration(Activity $receiver, @Nullable ScreenSize screenSize, @Nullable ClosedRange<Integer> density, @Nullable String language, @Nullable Orientation orientation, @Nullable Boolean long_, @Nullable Integer fromSdk, @Nullable Integer sdk, @Nullable UiMode uiMode, @Nullable Boolean nightMode, @Nullable Boolean rightToLeft, @Nullable Integer smallestWidth, @NotNull Function0<? extends T> f) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(f, "f");
        return AnkoInternals.testConfiguration((Context) $receiver, screenSize, density, language, orientation, long_, fromSdk, sdk, uiMode, nightMode, rightToLeft, smallestWidth) ? f.invoke() : null;
    }

    @Nullable
    public static final <T> T configuration(AnkoContext<?> $receiver, @Nullable ScreenSize screenSize, @Nullable ClosedRange<Integer> density, @Nullable String language, @Nullable Orientation orientation, @Nullable Boolean long_, @Nullable Integer fromSdk, @Nullable Integer sdk, @Nullable UiMode uiMode, @Nullable Boolean nightMode, @Nullable Boolean rightToLeft, @Nullable Integer smallestWidth, @NotNull Function0<? extends T> f) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(f, "f");
        return AnkoInternals.testConfiguration($receiver.getCtx(), screenSize, density, language, orientation, long_, fromSdk, sdk, uiMode, nightMode, rightToLeft, smallestWidth) ? f.invoke() : null;
    }

    @Nullable
    public static final <T> T configuration(Fragment $receiver, @Nullable ScreenSize screenSize, @Nullable ClosedRange<Integer> density, @Nullable String language, @Nullable Orientation orientation, @Nullable Boolean long_, @Nullable Integer fromSdk, @Nullable Integer sdk, @Nullable UiMode uiMode, @Nullable Boolean nightMode, @Nullable Boolean rightToLeft, @Nullable Integer smallestWidth, @NotNull Function0<? extends T> f) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(f, "f");
        Activity act = $receiver.getActivity();
        if (act != null) {
            return AnkoInternals.testConfiguration((Context) act, screenSize, density, language, orientation, long_, fromSdk, sdk, uiMode, nightMode, rightToLeft, smallestWidth) ? f.invoke() : null;
        } else {
            return null;
        }
    }

    public static final void doFromSdk(int version, @NotNull Function0<Unit> f) {
        Intrinsics.checkParameterIsNotNull(f, "f");
        if (VERSION.SDK_INT >= version) {
            f.invoke();
        }
    }

    public static final void doIfSdk(int version, @NotNull Function0<Unit> f) {
        Intrinsics.checkParameterIsNotNull(f, "f");
        if (VERSION.SDK_INT == version) {
            f.invoke();
        }
    }

    @NotNull
    public static final <T> AttemptResult<T> attempt(@NotNull Function0<? extends T> f) {
        Intrinsics.checkParameterIsNotNull(f, "f");
        Object value = null;
        Throwable error = null;
        try {
            value = f.invoke();
        } catch (Throwable t) {
            error = t;
        }
        return new AttemptResult(value, error);
    }
}
