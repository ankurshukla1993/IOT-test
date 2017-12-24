package org.jetbrains.anko.internals;

import android.app.Activity;
import android.app.Service;
import android.app.UiModeManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.DisplayMetrics;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewManager;
import com.facebook.internal.AnalyticsEvents;
import com.facebook.internal.NativeProtocol;
import com.ihealth.communication.control.AmProfile;
import java.io.Serializable;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.ClosedRange;
import org.jetbrains.anko.AnkoContext;
import org.jetbrains.anko.AnkoContextImpl;
import org.jetbrains.anko.AnkoContextKt;
import org.jetbrains.anko.AnkoException;
import org.jetbrains.anko.Orientation;
import org.jetbrains.anko.ScreenSize;
import org.jetbrains.anko.UiMode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 0}, d1 = {"\u0000¢\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001QB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J%\u0010\u0003\u001a\u00020\u0004\"\b\b\u0000\u0010\u0005*\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u0002H\u0005¢\u0006\u0002\u0010\nJ%\u0010\u0003\u001a\u00020\u0004\"\b\b\u0000\u0010\u0005*\u00020\u00062\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\t\u001a\u0002H\u0005¢\u0006\u0002\u0010\rJ%\u0010\u0003\u001a\u00020\u0004\"\b\b\u0000\u0010\u0005*\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\t\u001a\u0002H\u0005¢\u0006\u0002\u0010\u0010J\"\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u00062\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00040\u0014JG\u0010\u0015\u001a\u00020\u0016\"\u0004\b\u0000\u0010\u00052\u0006\u0010\u000b\u001a\u00020\f2\u000e\u0010\u0017\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00050\u00182\u001a\u0010\u0019\u001a\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\u00010\u001b0\u001aH\u0007¢\u0006\u0002\u0010\u001dJ1\u0010\u001e\u001a\u00020\u00042\u0006\u0010\u001f\u001a\u00020\u00162\u001a\u0010\u0019\u001a\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\u00010\u001b0\u001aH\u0003¢\u0006\u0002\u0010 J\u000e\u0010!\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u000fJ-\u0010\"\u001a\u0002H\u0005\"\b\b\u0000\u0010\u0005*\u00020\u00062\u0006\u0010\u000b\u001a\u00020\f2\f\u0010#\u001a\b\u0012\u0004\u0012\u0002H\u00050\u0018H\u0007¢\u0006\u0002\u0010$JA\u0010%\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\f2\u000e\u0010\u0007\u001a\n\u0012\u0006\b\u0001\u0012\u00020\b0\u00182\u001a\u0010\u0019\u001a\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\u00010\u001b0\u001aH\u0007¢\u0006\u0002\u0010&JI\u0010'\u001a\u00020\u00042\u0006\u0010(\u001a\u00020\b2\u000e\u0010\u0007\u001a\n\u0012\u0006\b\u0001\u0012\u00020\b0\u00182\u0006\u0010)\u001a\u00020*2\u001a\u0010\u0019\u001a\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\u00010\u001b0\u001aH\u0007¢\u0006\u0002\u0010+JA\u0010,\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\f2\u000e\u0010\u0007\u001a\n\u0012\u0006\b\u0001\u0012\u00020-0\u00182\u001a\u0010\u0019\u001a\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\u00010\u001b0\u001aH\u0007¢\u0006\u0002\u0010&J\u0001\u0010.\u001a\u00020/2\u0006\u0010\u000b\u001a\u00020\f2\b\u00100\u001a\u0004\u0018\u0001012\u000e\u00102\u001a\n\u0012\u0004\u0012\u00020*\u0018\u0001032\b\u00104\u001a\u0004\u0018\u00010\u001c2\b\u00105\u001a\u0004\u0018\u0001062\b\u00107\u001a\u0004\u0018\u00010/2\b\u00108\u001a\u0004\u0018\u00010*2\b\u00109\u001a\u0004\u0018\u00010*2\b\u0010:\u001a\u0004\u0018\u00010;2\b\u0010<\u001a\u0004\u0018\u00010/2\b\u0010=\u001a\u0004\u0018\u00010/2\b\u0010>\u001a\u0004\u0018\u00010*H\u0007¢\u0006\u0002\u0010?J0\u0010@\u001a\u0002H\u0005\"\u0004\b\u0000\u0010\u00052\u0006\u0010A\u001a\u00020B2\u0012\u0010C\u001a\u000e\u0012\u0004\u0012\u00020B\u0012\u0004\u0012\u0002H\u00050\u0014H\b¢\u0006\u0002\u0010DJ0\u0010E\u001a\u0002H\u0005\"\u0004\b\u0000\u0010\u00052\u0006\u0010F\u001a\u00020G2\u0012\u0010C\u001a\u000e\u0012\u0004\u0012\u00020G\u0012\u0004\u0012\u0002H\u00050\u0014H\b¢\u0006\u0002\u0010HJ\u0016\u0010I\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010J\u001a\u00020*JO\u0010K\u001a\b\u0012\u0004\u0012\u0002H\u00050L\"\u0004\b\u0000\u0010\u0005*\u0002H\u00052\u0006\u0010\u000b\u001a\u00020\f2\u001d\u0010M\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00050L\u0012\u0004\u0012\u00020\u00040\u0014¢\u0006\u0002\bN2\b\b\u0002\u0010O\u001a\u00020/H\b¢\u0006\u0002\u0010P¨\u0006R"}, d2 = {"Lorg/jetbrains/anko/internals/AnkoInternals;", "", "()V", "addView", "", "T", "Landroid/view/View;", "activity", "Landroid/app/Activity;", "view", "(Landroid/app/Activity;Landroid/view/View;)V", "ctx", "Landroid/content/Context;", "(Landroid/content/Context;Landroid/view/View;)V", "manager", "Landroid/view/ViewManager;", "(Landroid/view/ViewManager;Landroid/view/View;)V", "applyRecursively", "v", "style", "Lkotlin/Function1;", "createIntent", "Landroid/content/Intent;", "clazz", "Ljava/lang/Class;", "params", "", "Lkotlin/Pair;", "", "(Landroid/content/Context;Ljava/lang/Class;[Lkotlin/Pair;)Landroid/content/Intent;", "fillIntentArguments", "intent", "(Landroid/content/Intent;[Lkotlin/Pair;)V", "getContext", "initiateView", "viewClass", "(Landroid/content/Context;Ljava/lang/Class;)Landroid/view/View;", "internalStartActivity", "(Landroid/content/Context;Ljava/lang/Class;[Lkotlin/Pair;)V", "internalStartActivityForResult", "act", "requestCode", "", "(Landroid/app/Activity;Ljava/lang/Class;I[Lkotlin/Pair;)V", "internalStartService", "Landroid/app/Service;", "testConfiguration", "", "screenSize", "Lorg/jetbrains/anko/ScreenSize;", "density", "Lkotlin/ranges/ClosedRange;", "language", "orientation", "Lorg/jetbrains/anko/Orientation;", "long", "fromSdk", "sdk", "uiMode", "Lorg/jetbrains/anko/UiMode;", "nightMode", "rightToLeft", "smallestWidth", "(Landroid/content/Context;Lorg/jetbrains/anko/ScreenSize;Lkotlin/ranges/ClosedRange;Ljava/lang/String;Lorg/jetbrains/anko/Orientation;Ljava/lang/Boolean;Ljava/lang/Integer;Ljava/lang/Integer;Lorg/jetbrains/anko/UiMode;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Integer;)Z", "useCursor", "cursor", "Landroid/database/Cursor;", "f", "(Landroid/database/Cursor;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "useDatabase", "db", "Landroid/database/sqlite/SQLiteDatabase;", "(Landroid/database/sqlite/SQLiteDatabase;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "wrapContextIfNeeded", "theme", "createAnkoContext", "Lorg/jetbrains/anko/AnkoContext;", "init", "Lkotlin/ExtensionFunctionType;", "setContentView", "(Ljava/lang/Object;Landroid/content/Context;Lkotlin/jvm/functions/Function1;Z)Lorg/jetbrains/anko/AnkoContext;", "InternalConfiguration", "common-compileReleaseKotlin"}, k = 1, mv = {1, 1, 0})
/* compiled from: Internals.kt */
public final class AnkoInternals {
    public static final AnkoInternals INSTANCE = null;

    @Metadata(bv = {1, 0, 0}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\r\bÂ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0014\u0010\t\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0006R\u0014\u0010\u000b\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0006R\u0014\u0010\r\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u0006R\u0014\u0010\u000f\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0006¨\u0006\u0011"}, d2 = {"Lorg/jetbrains/anko/internals/AnkoInternals$InternalConfiguration;", "", "()V", "DENSITY_DPI_NONE", "", "getDENSITY_DPI_NONE", "()I", "SCREENLAYOUT_LAYOUTDIR_MASK", "getSCREENLAYOUT_LAYOUTDIR_MASK", "SCREENLAYOUT_LAYOUTDIR_RTL", "getSCREENLAYOUT_LAYOUTDIR_RTL", "SCREENLAYOUT_LAYOUTDIR_SHIFT", "getSCREENLAYOUT_LAYOUTDIR_SHIFT", "UI_MODE_TYPE_APPLIANCE", "getUI_MODE_TYPE_APPLIANCE", "UI_MODE_TYPE_WATCH", "getUI_MODE_TYPE_WATCH", "common-compileReleaseKotlin"}, k = 1, mv = {1, 1, 0})
    /* compiled from: Internals.kt */
    private static final class InternalConfiguration {
        private static final int DENSITY_DPI_NONE = 65535;
        public static final InternalConfiguration INSTANCE = null;
        private static final int SCREENLAYOUT_LAYOUTDIR_MASK = 192;
        private static final int SCREENLAYOUT_LAYOUTDIR_RTL = 128;
        private static final int SCREENLAYOUT_LAYOUTDIR_SHIFT = 6;
        private static final int UI_MODE_TYPE_APPLIANCE = 5;
        private static final int UI_MODE_TYPE_WATCH = 6;

        static {
            InternalConfiguration internalConfiguration = new InternalConfiguration();
        }

        private InternalConfiguration() {
            INSTANCE = this;
            SCREENLAYOUT_LAYOUTDIR_MASK = 192;
            SCREENLAYOUT_LAYOUTDIR_SHIFT = 6;
            SCREENLAYOUT_LAYOUTDIR_RTL = 2 << SCREENLAYOUT_LAYOUTDIR_SHIFT;
            UI_MODE_TYPE_APPLIANCE = 5;
            UI_MODE_TYPE_WATCH = 6;
            DENSITY_DPI_NONE = 65535;
        }

        public final int getSCREENLAYOUT_LAYOUTDIR_MASK() {
            return SCREENLAYOUT_LAYOUTDIR_MASK;
        }

        public final int getSCREENLAYOUT_LAYOUTDIR_SHIFT() {
            return SCREENLAYOUT_LAYOUTDIR_SHIFT;
        }

        public final int getSCREENLAYOUT_LAYOUTDIR_RTL() {
            return SCREENLAYOUT_LAYOUTDIR_RTL;
        }

        public final int getUI_MODE_TYPE_APPLIANCE() {
            return UI_MODE_TYPE_APPLIANCE;
        }

        public final int getUI_MODE_TYPE_WATCH() {
            return UI_MODE_TYPE_WATCH;
        }

        public final int getDENSITY_DPI_NONE() {
            return DENSITY_DPI_NONE;
        }
    }

    @org.jetbrains.annotations.NotNull
    public static /* bridge */ /* synthetic */ org.jetbrains.anko.AnkoContext createAnkoContext$default(org.jetbrains.anko.internals.AnkoInternals r1, java.lang.Object r2, android.content.Context r3, kotlin.jvm.functions.Function1 r4, boolean r5, int r6, java.lang.Object r7) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.DecodeException: Load method exception in method: org.jetbrains.anko.internals.AnkoInternals.createAnkoContext$default(org.jetbrains.anko.internals.AnkoInternals, java.lang.Object, android.content.Context, kotlin.jvm.functions.Function1, boolean, int, java.lang.Object):org.jetbrains.anko.AnkoContext
	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:116)
	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:249)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
Caused by: java.lang.NullPointerException
*/
        /*
        if (r9 == 0) goto L_0x000a;
    L_0x0002:
        r1 = new java.lang.UnsupportedOperationException;
        r2 = "Super calls with default arguments not supported in this target, function: createAnkoContext";
        r1.<init>(r2);
        throw r1;
    L_0x000a:
        r1 = r8 & 4;
        if (r1 == 0) goto L_0x000f;
    L_0x000e:
        r7 = 0;
        r0 = new org.jetbrains.anko.AnkoContextImpl;
        r0.<init>(r5, r4, r7);
        r6.invoke(r0);
        r0 = (org.jetbrains.anko.AnkoContext) r0;
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jetbrains.anko.internals.AnkoInternals.createAnkoContext$default(org.jetbrains.anko.internals.AnkoInternals, java.lang.Object, android.content.Context, kotlin.jvm.functions.Function1, boolean, int, java.lang.Object):org.jetbrains.anko.AnkoContext");
    }

    static {
        AnkoInternals ankoInternals = new AnkoInternals();
    }

    private AnkoInternals() {
        INSTANCE = this;
    }

    public final <T extends View> void addView(@NotNull ViewManager manager, @NotNull T view) {
        Intrinsics.checkParameterIsNotNull(manager, "manager");
        Intrinsics.checkParameterIsNotNull(view, "view");
        if (manager instanceof ViewGroup) {
            ((ViewGroup) manager).addView(view);
        } else if (manager instanceof AnkoContext) {
            manager.addView(view, (LayoutParams) null);
        } else {
            throw new AnkoException(manager + " is the wrong parent");
        }
    }

    public final <T extends View> void addView(@NotNull Context ctx, @NotNull T view) {
        Intrinsics.checkParameterIsNotNull(ctx, "ctx");
        Intrinsics.checkParameterIsNotNull(view, "view");
        AnkoContextKt.UI(ctx, (Function1) new AnkoInternals$addView$1(view));
    }

    public final <T extends View> void addView(@NotNull Activity activity, @NotNull T view) {
        Intrinsics.checkParameterIsNotNull(activity, AmProfile.SYNC_ACTIVITY_DATA_AM);
        Intrinsics.checkParameterIsNotNull(view, "view");
        AnkoContextImpl dsl$iv = new AnkoContextImpl(activity, this, true);
        INSTANCE.addView((ViewManager) dsl$iv, (View) view);
        Unit unit = Unit.INSTANCE;
    }

    @NotNull
    public final Context wrapContextIfNeeded(@NotNull Context ctx, int theme) {
        Intrinsics.checkParameterIsNotNull(ctx, "ctx");
        if (theme == 0 || ((ctx instanceof ContextThemeWrapper) && ((ContextThemeWrapper) ctx).getThemeResId() == theme)) {
            return ctx;
        }
        return new ContextThemeWrapper(ctx, theme);
    }

    public final void applyRecursively(@NotNull View v, @NotNull Function1<? super View, Unit> style) {
        Intrinsics.checkParameterIsNotNull(v, "v");
        Intrinsics.checkParameterIsNotNull(style, AnalyticsEvents.PARAMETER_LIKE_VIEW_STYLE);
        style.invoke(v);
        if (v instanceof ViewGroup) {
            int maxIndex = ((ViewGroup) v).getChildCount() - 1;
            int i = 0;
            if (0 <= maxIndex) {
                while (true) {
                    View it = ((ViewGroup) v).getChildAt(i);
                    if (it != null) {
                        INSTANCE.applyRecursively(it, style);
                        Unit unit = Unit.INSTANCE;
                    }
                    if (i != maxIndex) {
                        i++;
                    } else {
                        return;
                    }
                }
            }
        }
    }

    @NotNull
    public final Context getContext(@NotNull ViewManager manager) {
        Intrinsics.checkParameterIsNotNull(manager, "manager");
        if (manager instanceof ViewGroup) {
            Context context = ((ViewGroup) manager).getContext();
            Intrinsics.checkExpressionValueIsNotNull(context, "manager.context");
            return context;
        } else if (manager instanceof AnkoContext) {
            return ((AnkoContext) manager).getCtx();
        } else {
            throw new AnkoException(manager + " is the wrong parent");
        }
    }

    @NotNull
    public final <T> AnkoContext<T> createAnkoContext(T $receiver, @NotNull Context ctx, @NotNull Function1<? super AnkoContext<T>, Unit> init, boolean setContentView) {
        Intrinsics.checkParameterIsNotNull(ctx, "ctx");
        Intrinsics.checkParameterIsNotNull(init, "init");
        AnkoContextImpl dsl = new AnkoContextImpl(ctx, $receiver, setContentView);
        init.invoke(dsl);
        return dsl;
    }

    @JvmStatic
    @NotNull
    public static final <T> Intent createIntent(@NotNull Context ctx, @NotNull Class<? extends T> clazz, @NotNull Pair<String, ? extends Object>[] params) {
        Object obj;
        Intrinsics.checkParameterIsNotNull(ctx, "ctx");
        Intrinsics.checkParameterIsNotNull(clazz, "clazz");
        Intrinsics.checkParameterIsNotNull(params, NativeProtocol.WEB_DIALOG_PARAMS);
        Intent intent = new Intent(ctx, clazz);
        if (((Object[]) params).length == 0) {
            obj = 1;
        } else {
            obj = null;
        }
        if (obj == null) {
            obj = 1;
        } else {
            obj = null;
        }
        if (obj != null) {
            fillIntentArguments(intent, params);
        }
        return intent;
    }

    @JvmStatic
    public static final void internalStartActivity(@NotNull Context ctx, @NotNull Class<? extends Activity> activity, @NotNull Pair<String, ? extends Object>[] params) {
        Intrinsics.checkParameterIsNotNull(ctx, "ctx");
        Intrinsics.checkParameterIsNotNull(activity, AmProfile.SYNC_ACTIVITY_DATA_AM);
        Intrinsics.checkParameterIsNotNull(params, NativeProtocol.WEB_DIALOG_PARAMS);
        ctx.startActivity(createIntent(ctx, activity, params));
    }

    @JvmStatic
    public static final void internalStartActivityForResult(@NotNull Activity act, @NotNull Class<? extends Activity> activity, int requestCode, @NotNull Pair<String, ? extends Object>[] params) {
        Intrinsics.checkParameterIsNotNull(act, "act");
        Intrinsics.checkParameterIsNotNull(activity, AmProfile.SYNC_ACTIVITY_DATA_AM);
        Intrinsics.checkParameterIsNotNull(params, NativeProtocol.WEB_DIALOG_PARAMS);
        act.startActivityForResult(createIntent(act, activity, params), requestCode);
    }

    @JvmStatic
    public static final void internalStartService(@NotNull Context ctx, @NotNull Class<? extends Service> activity, @NotNull Pair<String, ? extends Object>[] params) {
        Intrinsics.checkParameterIsNotNull(ctx, "ctx");
        Intrinsics.checkParameterIsNotNull(activity, AmProfile.SYNC_ACTIVITY_DATA_AM);
        Intrinsics.checkParameterIsNotNull(params, NativeProtocol.WEB_DIALOG_PARAMS);
        ctx.startService(createIntent(ctx, activity, params));
    }

    @JvmStatic
    private static final void fillIntentArguments(Intent intent, Pair<String, ? extends Object>[] params) {
        Object[] objArr = (Object[]) params;
        for (Pair it : objArr) {
            Object value = it.getSecond();
            if (value instanceof Integer) {
                intent.putExtra((String) it.getFirst(), ((Number) value).intValue());
            } else if (value instanceof Long) {
                intent.putExtra((String) it.getFirst(), ((Number) value).longValue());
            } else if (value instanceof CharSequence) {
                intent.putExtra((String) it.getFirst(), (CharSequence) value);
            } else if (value instanceof String) {
                intent.putExtra((String) it.getFirst(), (String) value);
            } else if (value instanceof Float) {
                intent.putExtra((String) it.getFirst(), ((Number) value).floatValue());
            } else if (value instanceof Double) {
                intent.putExtra((String) it.getFirst(), ((Number) value).doubleValue());
            } else if (value instanceof Character) {
                intent.putExtra((String) it.getFirst(), ((Character) value).charValue());
            } else if (value instanceof Short) {
                intent.putExtra((String) it.getFirst(), ((Number) value).shortValue());
            } else if (value instanceof Boolean) {
                intent.putExtra((String) it.getFirst(), ((Boolean) value).booleanValue());
            } else if (value instanceof Serializable) {
                intent.putExtra((String) it.getFirst(), (Serializable) value);
            } else if (value instanceof Bundle) {
                intent.putExtra((String) it.getFirst(), (Bundle) value);
            } else if (value instanceof Parcelable) {
                intent.putExtra((String) it.getFirst(), (Parcelable) value);
            } else if (value instanceof Object[]) {
                if (((Object[]) value) instanceof CharSequence[]) {
                    intent.putExtra((String) it.getFirst(), (Serializable) value);
                } else if (((Object[]) value) instanceof String[]) {
                    intent.putExtra((String) it.getFirst(), (Serializable) value);
                } else if (((Object[]) value) instanceof Parcelable[]) {
                    intent.putExtra((String) it.getFirst(), (Serializable) value);
                } else {
                    throw new AnkoException("Intent extra " + ((String) it.getFirst()) + " has wrong type " + ((Object[]) value).getClass().getName());
                }
            } else if (value instanceof int[]) {
                intent.putExtra((String) it.getFirst(), (int[]) value);
            } else if (value instanceof long[]) {
                intent.putExtra((String) it.getFirst(), (long[]) value);
            } else if (value instanceof float[]) {
                intent.putExtra((String) it.getFirst(), (float[]) value);
            } else if (value instanceof double[]) {
                intent.putExtra((String) it.getFirst(), (double[]) value);
            } else if (value instanceof char[]) {
                intent.putExtra((String) it.getFirst(), (char[]) value);
            } else if (value instanceof short[]) {
                intent.putExtra((String) it.getFirst(), (short[]) value);
            } else if (value instanceof boolean[]) {
                intent.putExtra((String) it.getFirst(), (boolean[]) value);
            } else {
                throw new AnkoException("Intent extra " + ((String) it.getFirst()) + " has wrong type " + value.getClass().getName());
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    @JvmStatic
    public static final <T> T useDatabase(@NotNull SQLiteDatabase db, @NotNull Function1<? super SQLiteDatabase, ? extends T> f) {
        Intrinsics.checkParameterIsNotNull(db, "db");
        Intrinsics.checkParameterIsNotNull(f, "f");
        try {
            T invoke = f.invoke(db);
            try {
                db.close();
            } catch (Exception e) {
            }
            InlineMarker.finallyEnd(1);
            return invoke;
        } finally {
            InlineMarker.finallyStart(1);
            try {
                db.close();
            } catch (Exception e2) {
            }
            InlineMarker.finallyEnd(1);
        }
    }

    @JvmStatic
    public static final <T> T useCursor(@NotNull Cursor cursor, @NotNull Function1<? super Cursor, ? extends T> f) {
        Intrinsics.checkParameterIsNotNull(cursor, "cursor");
        Intrinsics.checkParameterIsNotNull(f, "f");
        try {
            T invoke = f.invoke(cursor);
            try {
                cursor.close();
            } catch (Exception e) {
            }
            InlineMarker.finallyEnd(1);
            return invoke;
        } finally {
            InlineMarker.finallyStart(1);
            try {
                cursor.close();
            } catch (Exception e2) {
            }
            InlineMarker.finallyEnd(1);
        }
    }

    @JvmStatic
    @NotNull
    public static final <T extends View> T initiateView(@NotNull Context ctx, @NotNull Class<T> viewClass) {
        View view;
        Intrinsics.checkParameterIsNotNull(ctx, "ctx");
        Intrinsics.checkParameterIsNotNull(viewClass, "viewClass");
        AnkoInternals$initiateView$1 getConstructor1$ = new AnkoInternals$initiateView$1(viewClass);
        AnkoInternals$initiateView$2 getConstructor2$ = new AnkoInternals$initiateView$2(viewClass);
        try {
            view = (View) getConstructor1$.invoke().newInstance(new Object[]{ctx});
            Intrinsics.checkExpressionValueIsNotNull(view, "getConstructor1().newInstance(ctx)");
            return view;
        } catch (NoSuchMethodException e) {
            try {
                view = (View) getConstructor2$.invoke().newInstance(new Object[]{ctx, null});
                Intrinsics.checkExpressionValueIsNotNull(view, "getConstructor2().newInstance(ctx, null)");
                return view;
            } catch (NoSuchMethodException e2) {
                throw new AnkoException("Can't initiate View of class " + viewClass.getName() + ": can't find proper constructor");
            }
        }
    }

    @JvmStatic
    public static final boolean testConfiguration(@NotNull Context ctx, @Nullable ScreenSize screenSize, @Nullable ClosedRange<Integer> density, @Nullable String language, @Nullable Orientation orientation, @Nullable Boolean long_, @Nullable Integer fromSdk, @Nullable Integer sdk, @Nullable UiMode uiMode, @Nullable Boolean nightMode, @Nullable Boolean rightToLeft, @Nullable Integer smallestWidth) {
        Intrinsics.checkParameterIsNotNull(ctx, "ctx");
        Resources resources = ctx.getResources();
        Configuration config = resources != null ? resources.getConfiguration() : null;
        if (screenSize != null) {
            if (config != null) {
                switch (config.screenLayout & 15) {
                    case 1:
                        if ((Intrinsics.areEqual((Object) screenSize, ScreenSize.SMALL) ^ 1) != 0) {
                            return false;
                        }
                        break;
                    case 2:
                        if ((Intrinsics.areEqual((Object) screenSize, ScreenSize.NORMAL) ^ 1) != 0) {
                            return false;
                        }
                        break;
                    case 3:
                        if ((Intrinsics.areEqual((Object) screenSize, ScreenSize.LARGE) ^ 1) != 0) {
                            return false;
                        }
                        break;
                    case 4:
                        if ((Intrinsics.areEqual((Object) screenSize, ScreenSize.XLARGE) ^ 1) != 0) {
                            return false;
                        }
                        break;
                }
            }
            return false;
        }
        if (density != null) {
            resources = ctx.getResources();
            if (resources != null) {
                DisplayMetrics displayMetrics = resources.getDisplayMetrics();
                if (displayMetrics != null) {
                    int currentDensityDpi = displayMetrics.densityDpi;
                    if ((density.contains(Integer.valueOf(currentDensityDpi)) ^ 1) != 0 || currentDensityDpi == ((Number) density.getEndInclusive()).intValue()) {
                        return false;
                    }
                }
            }
            return false;
        }
        if (language != null) {
            Locale locale = Locale.getDefault();
            if ((Intrinsics.areEqual(StringsKt__StringsKt.indexOf$default((CharSequence) language, '_', 0, false, 6, null) >= 0 ? locale.toString() : locale.getLanguage(), (Object) language) ^ 1) != 0) {
                return false;
            }
        }
        if (orientation != null) {
            if (config != null) {
                switch (config.orientation) {
                    case 1:
                        if ((Intrinsics.areEqual((Object) orientation, Orientation.PORTRAIT) ^ 1) != 0) {
                            return false;
                        }
                        break;
                    case 2:
                        if ((Intrinsics.areEqual((Object) orientation, Orientation.LANDSCAPE) ^ 1) != 0) {
                            return false;
                        }
                        break;
                    case 3:
                        if ((Intrinsics.areEqual((Object) orientation, Orientation.SQUARE) ^ 1) != 0) {
                            return false;
                        }
                        break;
                }
            }
            return false;
        }
        if (long_ != null) {
            if (config == null) {
                return false;
            }
            int currentLong = config.screenLayout & 48;
            if (currentLong == 32 && !long_.booleanValue()) {
                return false;
            }
            if (currentLong == 16 && long_.booleanValue()) {
                return false;
            }
        }
        if (fromSdk != null && Intrinsics.compare(VERSION.SDK_INT, fromSdk.intValue()) < 0) {
            return false;
        }
        if (sdk != null && (Intrinsics.areEqual(Integer.valueOf(VERSION.SDK_INT), (Object) sdk) ^ 1) != 0) {
            return false;
        }
        if (uiMode != null) {
            if (config == null) {
                return false;
            }
            int i = config.uiMode & 15;
            if (i == 1) {
                if ((Intrinsics.areEqual((Object) uiMode, UiMode.NORMAL) ^ 1) != 0) {
                    return false;
                }
            } else if (i == 2) {
                if ((Intrinsics.areEqual((Object) uiMode, UiMode.DESK) ^ 1) != 0) {
                    return false;
                }
            } else if (i == 3) {
                if ((Intrinsics.areEqual((Object) uiMode, UiMode.CAR) ^ 1) != 0) {
                    return false;
                }
            } else if (i == 4) {
                if ((Intrinsics.areEqual((Object) uiMode, UiMode.TELEVISION) ^ 1) != 0) {
                    return false;
                }
            } else if (i == InternalConfiguration.INSTANCE.getUI_MODE_TYPE_APPLIANCE()) {
                if ((Intrinsics.areEqual((Object) uiMode, UiMode.APPLIANCE) ^ 1) != 0) {
                    return false;
                }
            } else if (i == InternalConfiguration.INSTANCE.getUI_MODE_TYPE_WATCH()) {
                if ((Intrinsics.areEqual((Object) uiMode, UiMode.WATCH) ^ 1) != 0) {
                    return false;
                }
            }
        }
        if (nightMode != null) {
            UiModeManager uiModeManager = ctx.getSystemService("uimode");
            if (!(uiModeManager instanceof UiModeManager)) {
                uiModeManager = null;
            }
            uiModeManager = uiModeManager;
            if (uiModeManager == null) {
                return false;
            }
            int currentMode = uiModeManager.getNightMode();
            if (currentMode == 2 && !nightMode.booleanValue()) {
                return false;
            }
            if (currentMode == 1 && nightMode.booleanValue()) {
                return false;
            }
        }
        if (rightToLeft != null) {
            if (config == null) {
                return false;
            }
            if ((Intrinsics.areEqual(Boolean.valueOf((config.screenLayout & InternalConfiguration.INSTANCE.getSCREENLAYOUT_LAYOUTDIR_MASK()) == InternalConfiguration.INSTANCE.getSCREENLAYOUT_LAYOUTDIR_RTL()), (Object) rightToLeft) ^ 1) != 0) {
                return false;
            }
        }
        if (smallestWidth != null) {
            if (config == null) {
                return false;
            }
            if (config.smallestScreenWidthDp == 0) {
                if ((Intrinsics.areEqual((Object) smallestWidth, Integer.valueOf(0)) ^ 1) != 0) {
                    return false;
                }
            } else if (Intrinsics.compare(config.smallestScreenWidthDp, smallestWidth.intValue()) < 0) {
                return false;
            }
        }
        return true;
    }
}
