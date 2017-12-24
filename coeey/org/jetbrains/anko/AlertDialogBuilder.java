package org.jetbrains.anko;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewManager;
import android.widget.ListAdapter;
import io.fabric.sdk.android.services.settings.SettingsJsonConstants;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 0}, d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\r\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0013\b\u0016\u0012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003¢\u0006\u0002\u0010\u0004B\r\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J*\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0012\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u00140\u001aJ\"\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0013\u001a\u00020\u001c2\u0012\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u00140\u001aJ!\u0010\u001d\u001a\u00020\u00142\u0019\b\u0002\u0010\u0019\u001a\u0013\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u00140\u001a¢\u0006\u0002\b\u001fJ\u0010\u0010 \u001a\u00020\u00142\b\b\u0002\u0010 \u001a\u00020!J\b\u0010\"\u001a\u00020\u0014H\u0002J\u000e\u0010#\u001a\u00020\u00142\u0006\u0010$\u001a\u00020%J\u001f\u0010#\u001a\u00020\u00142\u0017\u0010&\u001a\u0013\u0012\u0004\u0012\u00020'\u0012\u0004\u0012\u00020\u00140\u001a¢\u0006\u0002\b\u001fJ\u000e\u0010(\u001a\u00020\u00142\u0006\u0010$\u001a\u00020%J\u001f\u0010(\u001a\u00020\u00142\u0017\u0010&\u001a\u0013\u0012\u0004\u0012\u00020'\u0012\u0004\u0012\u00020\u00140\u001a¢\u0006\u0002\b\u001fJ\u0006\u0010)\u001a\u00020\u0014J\u000e\u0010*\u001a\u00020\u00142\u0006\u0010*\u001a\u00020+J\u000e\u0010*\u001a\u00020\u00142\u0006\u0010*\u001a\u00020\u001bJ-\u0010,\u001a\u00020\u00142\f\u0010,\u001a\b\u0012\u0004\u0012\u00020.0-2\u0012\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u00140\u001a¢\u0006\u0002\u0010/J\"\u0010,\u001a\u00020\u00142\u0006\u00100\u001a\u00020\u001b2\u0012\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u00140\u001aJ(\u0010,\u001a\u00020\u00142\f\u0010,\u001a\b\u0012\u0004\u0012\u00020.012\u0012\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u00140\u001aJ\u000e\u00102\u001a\u00020\u00142\u0006\u00102\u001a\u00020.J\u000e\u00102\u001a\u00020\u00142\u0006\u00102\u001a\u00020\u001bJ)\u00103\u001a\u00020\u00142\u0006\u00104\u001a\u00020.2\u0019\b\u0002\u0010\u0019\u001a\u0013\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u00140\u001a¢\u0006\u0002\b\u001fJ)\u00103\u001a\u00020\u00142\u0006\u00104\u001a\u00020\u001b2\u0019\b\u0002\u0010\u0019\u001a\u0013\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u00140\u001a¢\u0006\u0002\b\u001fJ)\u00105\u001a\u00020\u00142\u0006\u00106\u001a\u00020.2\u0019\b\u0002\u0010\u0019\u001a\u0013\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u00140\u001a¢\u0006\u0002\b\u001fJ+\u00105\u001a\u00020\u00142\b\b\u0002\u00106\u001a\u00020\u001b2\u0019\b\u0002\u0010\u0019\u001a\u0013\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u00140\u001a¢\u0006\u0002\b\u001fJ!\u00107\u001a\u00020\u00142\u0019\b\u0002\u0010\u0019\u001a\u0013\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u00140\u001a¢\u0006\u0002\b\u001fJ\u001f\u00108\u001a\u00020\u00142\u0017\u0010\u0019\u001a\u0013\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u00140\u001a¢\u0006\u0002\b\u001fJ\u0014\u00109\u001a\u00020\u00142\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00140:J \u0010;\u001a\u00020\u00142\u0018\u0010\u0019\u001a\u0014\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020=\u0012\u0004\u0012\u00020!0<J'\u0010>\u001a\u00020\u00142\u0006\u0010?\u001a\u00020.2\u0017\u0010\u0019\u001a\u0013\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u00140\u001a¢\u0006\u0002\b\u001fJ'\u0010>\u001a\u00020\u00142\u0006\u0010?\u001a\u00020\u001b2\u0017\u0010\u0019\u001a\u0013\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u00140\u001a¢\u0006\u0002\b\u001fJ\u0006\u0010@\u001a\u00020\u0000J\u000e\u0010A\u001a\u00020\u00142\u0006\u0010A\u001a\u00020.J\u000e\u0010A\u001a\u00020\u00142\u0006\u0010A\u001a\u00020\u001bJ\u001f\u0010B\u001a\u00020\u00142\u0017\u0010\u0019\u001a\u0013\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u00140\u001a¢\u0006\u0002\b\u001fR\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR(\u0010\u000e\u001a\u0004\u0018\u00010\r2\b\u0010\f\u001a\u0004\u0018\u00010\r@BX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012¨\u0006C"}, d2 = {"Lorg/jetbrains/anko/AlertDialogBuilder;", "", "ankoContext", "Lorg/jetbrains/anko/AnkoContext;", "(Lorg/jetbrains/anko/AnkoContext;)V", "ctx", "Landroid/content/Context;", "(Landroid/content/Context;)V", "builder", "Landroid/app/AlertDialog$Builder;", "getCtx", "()Landroid/content/Context;", "<set-?>", "Landroid/app/AlertDialog;", "dialog", "getDialog", "()Landroid/app/AlertDialog;", "setDialog", "(Landroid/app/AlertDialog;)V", "adapter", "", "cursor", "Landroid/database/Cursor;", "labelColumn", "", "callback", "Lkotlin/Function1;", "", "Landroid/widget/ListAdapter;", "cancelButton", "Landroid/content/DialogInterface;", "Lkotlin/ExtensionFunctionType;", "cancellable", "", "checkBuilder", "customTitle", "view", "Landroid/view/View;", "dsl", "Landroid/view/ViewManager;", "customView", "dismiss", "icon", "Landroid/graphics/drawable/Drawable;", "items", "", "", "([Ljava/lang/CharSequence;Lkotlin/jvm/functions/Function1;)V", "itemsId", "", "message", "negativeButton", "negativeText", "neutralButton", "neutralText", "noButton", "okButton", "onCancel", "Lkotlin/Function0;", "onKey", "Lkotlin/Function2;", "Landroid/view/KeyEvent;", "positiveButton", "positiveText", "show", "title", "yesButton", "common-compileReleaseKotlin"}, k = 1, mv = {1, 1, 0})
/* compiled from: AlertDialogBuilder.kt */
public final class AlertDialogBuilder {
    private Builder builder;
    @NotNull
    private final Context ctx;
    @Nullable
    private AlertDialog dialog;

    public AlertDialogBuilder(@NotNull Context ctx) {
        Intrinsics.checkParameterIsNotNull(ctx, "ctx");
        this.ctx = ctx;
        this.builder = new Builder(this.ctx);
    }

    @NotNull
    public final Context getCtx() {
        return this.ctx;
    }

    private final void setDialog(AlertDialog <set-?>) {
        this.dialog = <set-?>;
    }

    @Nullable
    public final AlertDialog getDialog() {
        return this.dialog;
    }

    public AlertDialogBuilder(@NotNull AnkoContext<?> ankoContext) {
        Intrinsics.checkParameterIsNotNull(ankoContext, "ankoContext");
        this(ankoContext.getCtx());
    }

    public final void dismiss() {
        AlertDialog alertDialog = this.dialog;
        if (alertDialog != null) {
            alertDialog.dismiss();
            Unit unit = Unit.INSTANCE;
        }
    }

    private final void checkBuilder() {
        if (this.builder == null) {
            throw new IllegalStateException("show() was already called for this AlertDialogBuilder");
        }
    }

    @NotNull
    public final AlertDialogBuilder show() {
        checkBuilder();
        Builder builder = this.builder;
        if (builder == null) {
            Intrinsics.throwNpe();
        }
        this.dialog = builder.create();
        this.builder = (Builder) null;
        AlertDialog alertDialog = this.dialog;
        if (alertDialog == null) {
            Intrinsics.throwNpe();
        }
        alertDialog.show();
        return this;
    }

    public final void title(@NotNull CharSequence title) {
        Intrinsics.checkParameterIsNotNull(title, "title");
        checkBuilder();
        Builder builder = this.builder;
        if (builder == null) {
            Intrinsics.throwNpe();
        }
        builder.setTitle(title);
    }

    public final void title(int title) {
        checkBuilder();
        Builder builder = this.builder;
        if (builder == null) {
            Intrinsics.throwNpe();
        }
        builder.setTitle(title);
    }

    public final void message(@NotNull CharSequence message) {
        Intrinsics.checkParameterIsNotNull(message, "message");
        checkBuilder();
        Builder builder = this.builder;
        if (builder == null) {
            Intrinsics.throwNpe();
        }
        builder.setMessage(message);
    }

    public final void message(int message) {
        checkBuilder();
        Builder builder = this.builder;
        if (builder == null) {
            Intrinsics.throwNpe();
        }
        builder.setMessage(message);
    }

    public final void icon(int icon) {
        checkBuilder();
        Builder builder = this.builder;
        if (builder == null) {
            Intrinsics.throwNpe();
        }
        builder.setIcon(icon);
    }

    public final void icon(@NotNull Drawable icon) {
        Intrinsics.checkParameterIsNotNull(icon, SettingsJsonConstants.APP_ICON_KEY);
        checkBuilder();
        Builder builder = this.builder;
        if (builder == null) {
            Intrinsics.throwNpe();
        }
        builder.setIcon(icon);
    }

    public final void customTitle(@NotNull View view) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        checkBuilder();
        Builder builder = this.builder;
        if (builder == null) {
            Intrinsics.throwNpe();
        }
        builder.setCustomTitle(view);
    }

    public final void customTitle(@NotNull Function1<? super ViewManager, Unit> dsl) {
        Intrinsics.checkParameterIsNotNull(dsl, "dsl");
        checkBuilder();
        View view = AnkoContextKt.UI(this.ctx, (Function1) dsl).getView();
        Builder builder = this.builder;
        if (builder == null) {
            Intrinsics.throwNpe();
        }
        builder.setCustomTitle(view);
    }

    public final void customView(@NotNull View view) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        checkBuilder();
        Builder builder = this.builder;
        if (builder == null) {
            Intrinsics.throwNpe();
        }
        builder.setView(view);
    }

    public final void customView(@NotNull Function1<? super ViewManager, Unit> dsl) {
        Intrinsics.checkParameterIsNotNull(dsl, "dsl");
        checkBuilder();
        View view = AnkoContextKt.UI(this.ctx, (Function1) dsl).getView();
        Builder builder = this.builder;
        if (builder == null) {
            Intrinsics.throwNpe();
        }
        builder.setView(view);
    }

    public static /* bridge */ /* synthetic */ void cancellable$default(AlertDialogBuilder alertDialogBuilder, boolean z, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: cancellable");
        }
        if ((i & 1) != 0) {
            z = true;
        }
        alertDialogBuilder.cancellable(z);
    }

    public final void cancellable(boolean cancellable) {
        checkBuilder();
        Builder builder = this.builder;
        if (builder == null) {
            Intrinsics.throwNpe();
        }
        builder.setCancelable(cancellable);
    }

    public final void onCancel(@NotNull Function0<Unit> callback) {
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        checkBuilder();
        Builder builder = this.builder;
        if (builder == null) {
            Intrinsics.throwNpe();
        }
        builder.setOnCancelListener(new AlertDialogBuilder$onCancel$1(callback));
    }

    public final void onKey(@NotNull Function2<? super Integer, ? super KeyEvent, Boolean> callback) {
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        checkBuilder();
        Builder builder = this.builder;
        if (builder == null) {
            Intrinsics.throwNpe();
        }
        builder.setOnKeyListener(new AlertDialogBuilder$onKey$1(callback));
    }

    public final void neutralButton(int neutralText, @NotNull Function1<? super DialogInterface, Unit> callback) {
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        CharSequence string = this.ctx.getString(neutralText);
        Intrinsics.checkExpressionValueIsNotNull(string, "ctx.getString(neutralText)");
        neutralButton(string, (Function1) callback);
    }

    public final void neutralButton(@NotNull CharSequence neutralText, @NotNull Function1<? super DialogInterface, Unit> callback) {
        Intrinsics.checkParameterIsNotNull(neutralText, "neutralText");
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        checkBuilder();
        Builder builder = this.builder;
        if (builder == null) {
            Intrinsics.throwNpe();
        }
        builder.setNeutralButton(neutralText, new AlertDialogBuilder$neutralButton$3(callback));
    }

    public final void positiveButton(int positiveText, @NotNull Function1<? super DialogInterface, Unit> callback) {
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        CharSequence string = this.ctx.getString(positiveText);
        Intrinsics.checkExpressionValueIsNotNull(string, "ctx.getString(positiveText)");
        positiveButton(string, (Function1) callback);
    }

    public final void okButton(@NotNull Function1<? super DialogInterface, Unit> callback) {
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        CharSequence string = this.ctx.getString(17039370);
        Intrinsics.checkExpressionValueIsNotNull(string, "ctx.getString(R.string.ok)");
        positiveButton(string, (Function1) callback);
    }

    public final void yesButton(@NotNull Function1<? super DialogInterface, Unit> callback) {
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        CharSequence string = this.ctx.getString(17039379);
        Intrinsics.checkExpressionValueIsNotNull(string, "ctx.getString(R.string.yes)");
        positiveButton(string, (Function1) callback);
    }

    public final void positiveButton(@NotNull CharSequence positiveText, @NotNull Function1<? super DialogInterface, Unit> callback) {
        Intrinsics.checkParameterIsNotNull(positiveText, "positiveText");
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        checkBuilder();
        Builder builder = this.builder;
        if (builder == null) {
            Intrinsics.throwNpe();
        }
        builder.setPositiveButton(positiveText, new AlertDialogBuilder$positiveButton$1(callback));
    }

    public final void negativeButton(int negativeText, @NotNull Function1<? super DialogInterface, Unit> callback) {
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        CharSequence string = this.ctx.getString(negativeText);
        Intrinsics.checkExpressionValueIsNotNull(string, "ctx.getString(negativeText)");
        negativeButton(string, (Function1) callback);
    }

    public static /* bridge */ /* synthetic */ void cancelButton$default(AlertDialogBuilder alertDialogBuilder, Function1 function1, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: cancelButton");
        }
        alertDialogBuilder.cancelButton((i & 1) != 0 ? AlertDialogBuilder$cancelButton$1.INSTANCE : function1);
    }

    public final void cancelButton(@NotNull Function1<? super DialogInterface, Unit> callback) {
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        CharSequence string = this.ctx.getString(17039360);
        Intrinsics.checkExpressionValueIsNotNull(string, "ctx.getString(R.string.cancel)");
        negativeButton(string, (Function1) callback);
    }

    public static /* bridge */ /* synthetic */ void noButton$default(AlertDialogBuilder alertDialogBuilder, Function1 function1, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: noButton");
        }
        alertDialogBuilder.noButton((i & 1) != 0 ? AlertDialogBuilder$noButton$1.INSTANCE : function1);
    }

    public final void noButton(@NotNull Function1<? super DialogInterface, Unit> callback) {
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        CharSequence string = this.ctx.getString(17039369);
        Intrinsics.checkExpressionValueIsNotNull(string, "ctx.getString(R.string.no)");
        negativeButton(string, (Function1) callback);
    }

    public final void negativeButton(@NotNull CharSequence negativeText, @NotNull Function1<? super DialogInterface, Unit> callback) {
        Intrinsics.checkParameterIsNotNull(negativeText, "negativeText");
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        checkBuilder();
        Builder builder = this.builder;
        if (builder == null) {
            Intrinsics.throwNpe();
        }
        builder.setNegativeButton(negativeText, new AlertDialogBuilder$negativeButton$3(callback));
    }

    public final void items(int itemsId, @NotNull Function1<? super Integer, Unit> callback) {
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        Resources resources = this.ctx.getResources();
        if (resources == null) {
            Intrinsics.throwNpe();
        }
        CharSequence[] textArray = resources.getTextArray(itemsId);
        Intrinsics.checkExpressionValueIsNotNull(textArray, "ctx.resources!!.getTextArray(itemsId)");
        items(textArray, (Function1) callback);
    }

    public final void items(@NotNull List<? extends CharSequence> items, @NotNull Function1<? super Integer, Unit> callback) {
        Intrinsics.checkParameterIsNotNull(items, "items");
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        Collection collection = items;
        if (collection == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.util.Collection<T>");
        }
        Collection thisCollection$iv = collection;
        Object[] toArray = thisCollection$iv.toArray(new CharSequence[thisCollection$iv.size()]);
        if (toArray == null) {
            throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
        }
        items((CharSequence[]) toArray, (Function1) callback);
    }

    public final void items(@NotNull CharSequence[] items, @NotNull Function1<? super Integer, Unit> callback) {
        Intrinsics.checkParameterIsNotNull(items, "items");
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        checkBuilder();
        Builder builder = this.builder;
        if (builder == null) {
            Intrinsics.throwNpe();
        }
        builder.setItems(items, new AlertDialogBuilder$items$1(callback));
    }

    public final void adapter(@NotNull ListAdapter adapter, @NotNull Function1<? super Integer, Unit> callback) {
        Intrinsics.checkParameterIsNotNull(adapter, "adapter");
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        checkBuilder();
        Builder builder = this.builder;
        if (builder == null) {
            Intrinsics.throwNpe();
        }
        builder.setAdapter(adapter, new AlertDialogBuilder$adapter$1(callback));
    }

    public final void adapter(@NotNull Cursor cursor, @NotNull String labelColumn, @NotNull Function1<? super Integer, Unit> callback) {
        Intrinsics.checkParameterIsNotNull(cursor, "cursor");
        Intrinsics.checkParameterIsNotNull(labelColumn, "labelColumn");
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        checkBuilder();
        Builder builder = this.builder;
        if (builder == null) {
            Intrinsics.throwNpe();
        }
        builder.setCursor(cursor, new AlertDialogBuilder$adapter$2(callback), labelColumn);
    }
}
