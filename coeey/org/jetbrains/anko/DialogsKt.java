package org.jetbrains.anko;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 0}, d1 = {"\u0000B\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\u001aC\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u001b\b\n\u0010\u0006\u001a\u0015\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007¢\u0006\u0002\b\tH\b¢\u0006\u0002\u0010\n\u001a>\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u000b2\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u000b2\u001b\b\n\u0010\u0006\u001a\u0015\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007¢\u0006\u0002\b\tH\b\u001a(\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0019\b\b\u0010\u0006\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0002\b\tH\b\u001a@\u0010\u0000\u001a\u00020\u0001*\u00020\f2\u0006\u0010\u0003\u001a\u00020\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u001b\b\u0002\u0010\u0006\u001a\u0015\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007¢\u0006\u0002\b\t¢\u0006\u0002\u0010\r\u001a;\u0010\u0000\u001a\u00020\u0001*\u00020\f2\u0006\u0010\u0003\u001a\u00020\u000b2\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u000b2\u001b\b\u0002\u0010\u0006\u001a\u0015\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007¢\u0006\u0002\b\t\u001a#\u0010\u0000\u001a\u00020\u0001*\u00020\f2\u0017\u0010\u0006\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0002\b\t\u001aG\u0010\u0000\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u000e2\u0006\u0010\u0003\u001a\u00020\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u001b\b\n\u0010\u0006\u001a\u0015\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007¢\u0006\u0002\b\tH\b¢\u0006\u0002\u0010\u000f\u001aB\u0010\u0000\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u000e2\u0006\u0010\u0003\u001a\u00020\u000b2\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u000b2\u001b\b\n\u0010\u0006\u001a\u0015\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007¢\u0006\u0002\b\tH\b\u001a,\u0010\u0000\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u000e2\u0019\b\b\u0010\u0006\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0002\b\tH\b\u001aG\u0010\u0010\u001a\u00020\u0011*\u00020\u00022\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u001b\b\n\u0010\u0006\u001a\u0015\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007¢\u0006\u0002\b\tH\b¢\u0006\u0002\u0010\u0012\u001aB\u0010\u0010\u001a\u00020\u0011*\u00020\u00022\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u000b2\u001b\b\n\u0010\u0006\u001a\u0015\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007¢\u0006\u0002\b\tH\b\u001aD\u0010\u0010\u001a\u00020\u0011*\u00020\f2\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u001b\b\u0002\u0010\u0006\u001a\u0015\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007¢\u0006\u0002\b\t¢\u0006\u0002\u0010\u0013\u001a?\u0010\u0010\u001a\u00020\u0011*\u00020\f2\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u000b2\u001b\b\u0002\u0010\u0006\u001a\u0015\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007¢\u0006\u0002\b\t\u001aK\u0010\u0010\u001a\u00020\u0011*\u0006\u0012\u0002\b\u00030\u000e2\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u001b\b\n\u0010\u0006\u001a\u0015\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007¢\u0006\u0002\b\tH\b¢\u0006\u0002\u0010\u0014\u001aF\u0010\u0010\u001a\u00020\u0011*\u0006\u0012\u0002\b\u00030\u000e2\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u000b2\u001b\b\n\u0010\u0006\u001a\u0015\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007¢\u0006\u0002\b\tH\b\u001aG\u0010\u0015\u001a\u00020\u0011*\u00020\u00022\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u001b\b\n\u0010\u0006\u001a\u0015\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007¢\u0006\u0002\b\tH\b¢\u0006\u0002\u0010\u0012\u001aB\u0010\u0015\u001a\u00020\u0011*\u00020\u00022\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u000b2\u001b\b\n\u0010\u0006\u001a\u0015\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007¢\u0006\u0002\b\tH\b\u001aI\u0010\u0015\u001a\u00020\u0011*\u00020\f2\u0006\u0010\u0016\u001a\u00020\u00172\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u000b2\u001b\b\u0002\u0010\u0006\u001a\u0015\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007¢\u0006\u0002\b\tH\u0002\u001aD\u0010\u0015\u001a\u00020\u0011*\u00020\f2\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u001b\b\u0002\u0010\u0006\u001a\u0015\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007¢\u0006\u0002\b\t¢\u0006\u0002\u0010\u0013\u001a?\u0010\u0015\u001a\u00020\u0011*\u00020\f2\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u000b2\u001b\b\u0002\u0010\u0006\u001a\u0015\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007¢\u0006\u0002\b\t\u001aK\u0010\u0015\u001a\u00020\u0011*\u0006\u0012\u0002\b\u00030\u000e2\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u001b\b\n\u0010\u0006\u001a\u0015\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007¢\u0006\u0002\b\tH\b¢\u0006\u0002\u0010\u0014\u001aF\u0010\u0015\u001a\u00020\u0011*\u0006\u0012\u0002\b\u00030\u000e2\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u000b2\u001b\b\n\u0010\u0006\u001a\u0015\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007¢\u0006\u0002\b\tH\b¨\u0006\u0018"}, d2 = {"alert", "Lorg/jetbrains/anko/AlertDialogBuilder;", "Landroid/app/Fragment;", "message", "", "title", "init", "Lkotlin/Function1;", "", "Lkotlin/ExtensionFunctionType;", "(Landroid/app/Fragment;ILjava/lang/Integer;Lkotlin/jvm/functions/Function1;)Lorg/jetbrains/anko/AlertDialogBuilder;", "", "Landroid/content/Context;", "(Landroid/content/Context;ILjava/lang/Integer;Lkotlin/jvm/functions/Function1;)Lorg/jetbrains/anko/AlertDialogBuilder;", "Lorg/jetbrains/anko/AnkoContext;", "(Lorg/jetbrains/anko/AnkoContext;ILjava/lang/Integer;Lkotlin/jvm/functions/Function1;)Lorg/jetbrains/anko/AlertDialogBuilder;", "indeterminateProgressDialog", "Landroid/app/ProgressDialog;", "(Landroid/app/Fragment;Ljava/lang/Integer;Ljava/lang/Integer;Lkotlin/jvm/functions/Function1;)Landroid/app/ProgressDialog;", "(Landroid/content/Context;Ljava/lang/Integer;Ljava/lang/Integer;Lkotlin/jvm/functions/Function1;)Landroid/app/ProgressDialog;", "(Lorg/jetbrains/anko/AnkoContext;Ljava/lang/Integer;Ljava/lang/Integer;Lkotlin/jvm/functions/Function1;)Landroid/app/ProgressDialog;", "progressDialog", "indeterminate", "", "common-compileReleaseKotlin"}, k = 2, mv = {1, 1, 0})
/* compiled from: Dialogs.kt */
public final class DialogsKt {
    @NotNull
    public static final AlertDialogBuilder alert(AnkoContext<?> $receiver, @NotNull String message, @Nullable String title, @Nullable Function1<? super AlertDialogBuilder, Unit> init) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(message, "message");
        return alert($receiver.getCtx(), message, title, (Function1) init);
    }

    @NotNull
    public static final AlertDialogBuilder alert(Fragment $receiver, @NotNull String message, @Nullable String title, @Nullable Function1<? super AlertDialogBuilder, Unit> init) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(message, "message");
        return alert((Context) $receiver.getActivity(), message, title, (Function1) init);
    }

    @NotNull
    public static final AlertDialogBuilder alert(Context $receiver, @NotNull String message, @Nullable String title, @Nullable Function1<? super AlertDialogBuilder, Unit> init) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(message, "message");
        AlertDialogBuilder alertDialogBuilder = new AlertDialogBuilder($receiver);
        AlertDialogBuilder $receiver2 = alertDialogBuilder;
        if (title != null) {
            $receiver2.title((CharSequence) title);
        }
        $receiver2.message((CharSequence) message);
        if (init != null) {
            init.invoke($receiver2);
        }
        Unit unit = Unit.INSTANCE;
        return alertDialogBuilder;
    }

    @NotNull
    public static final AlertDialogBuilder alert(AnkoContext<?> $receiver, int message, @Nullable Integer title, @Nullable Function1<? super AlertDialogBuilder, Unit> init) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        return alert($receiver.getCtx(), message, title, (Function1) init);
    }

    @NotNull
    public static final AlertDialogBuilder alert(Fragment $receiver, int message, @Nullable Integer title, @Nullable Function1<? super AlertDialogBuilder, Unit> init) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        return alert((Context) $receiver.getActivity(), message, title, (Function1) init);
    }

    @NotNull
    public static final AlertDialogBuilder alert(Context $receiver, int message, @Nullable Integer title, @Nullable Function1<? super AlertDialogBuilder, Unit> init) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        AlertDialogBuilder alertDialogBuilder = new AlertDialogBuilder($receiver);
        AlertDialogBuilder $receiver2 = alertDialogBuilder;
        if (title != null) {
            $receiver2.title(title.intValue());
        }
        $receiver2.message(message);
        if (init != null) {
            init.invoke($receiver2);
        }
        Unit unit = Unit.INSTANCE;
        return alertDialogBuilder;
    }

    @NotNull
    public static final AlertDialogBuilder alert(AnkoContext<?> $receiver, @NotNull Function1<? super AlertDialogBuilder, Unit> init) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(init, "init");
        return alert($receiver.getCtx(), (Function1) init);
    }

    @NotNull
    public static final AlertDialogBuilder alert(Fragment $receiver, @NotNull Function1<? super AlertDialogBuilder, Unit> init) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(init, "init");
        return alert((Context) $receiver.getActivity(), (Function1) init);
    }

    @NotNull
    public static final AlertDialogBuilder alert(Context $receiver, @NotNull Function1<? super AlertDialogBuilder, Unit> init) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(init, "init");
        AlertDialogBuilder alertDialogBuilder = new AlertDialogBuilder($receiver);
        init.invoke(alertDialogBuilder);
        Unit unit = Unit.INSTANCE;
        return alertDialogBuilder;
    }

    @NotNull
    public static final ProgressDialog progressDialog(AnkoContext<?> $receiver, @Nullable Integer message, @Nullable Integer title, @Nullable Function1<? super ProgressDialog, Unit> init) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        return progressDialog($receiver.getCtx(), message, title, (Function1) init);
    }

    @NotNull
    public static final ProgressDialog progressDialog(Fragment $receiver, @Nullable Integer message, @Nullable Integer title, @Nullable Function1<? super ProgressDialog, Unit> init) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        return progressDialog((Context) $receiver.getActivity(), message, title, (Function1) init);
    }

    @NotNull
    public static final ProgressDialog progressDialog(Context $receiver, @Nullable Integer message, @Nullable Integer title, @Nullable Function1<? super ProgressDialog, Unit> init) {
        String string;
        Context $receiver2;
        String string2;
        String str;
        boolean z;
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        if (message != null) {
            string = $receiver.getString(message.intValue());
            $receiver2 = $receiver;
        } else {
            string = null;
            $receiver2 = $receiver;
        }
        if (title != null) {
            string2 = $receiver.getString(title.intValue());
            str = string;
            z = false;
        } else {
            string2 = null;
            str = string;
            z = false;
        }
        return progressDialog($receiver2, z, str, string2, init);
    }

    @NotNull
    public static final ProgressDialog indeterminateProgressDialog(AnkoContext<?> $receiver, @Nullable Integer message, @Nullable Integer title, @Nullable Function1<? super ProgressDialog, Unit> init) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        return indeterminateProgressDialog($receiver.getCtx(), message, title, (Function1) init);
    }

    @NotNull
    public static final ProgressDialog indeterminateProgressDialog(Fragment $receiver, @Nullable Integer message, @Nullable Integer title, @Nullable Function1<? super ProgressDialog, Unit> init) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        return progressDialog((Context) $receiver.getActivity(), message, title, (Function1) init);
    }

    @NotNull
    public static final ProgressDialog indeterminateProgressDialog(Context $receiver, @Nullable Integer message, @Nullable Integer title, @Nullable Function1<? super ProgressDialog, Unit> init) {
        String string;
        Context $receiver2;
        String string2;
        String str;
        boolean z;
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        if (message != null) {
            string = $receiver.getString(message.intValue());
            $receiver2 = $receiver;
        } else {
            string = null;
            $receiver2 = $receiver;
        }
        if (title != null) {
            string2 = $receiver.getString(title.intValue());
            str = string;
            z = true;
        } else {
            string2 = null;
            str = string;
            z = true;
        }
        return progressDialog($receiver2, z, str, string2, init);
    }

    @NotNull
    public static final ProgressDialog progressDialog(AnkoContext<?> $receiver, @Nullable String message, @Nullable String title, @Nullable Function1<? super ProgressDialog, Unit> init) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        return progressDialog($receiver.getCtx(), message, title, (Function1) init);
    }

    @NotNull
    public static final ProgressDialog progressDialog(Fragment $receiver, @Nullable String message, @Nullable String title, @Nullable Function1<? super ProgressDialog, Unit> init) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        return progressDialog((Context) $receiver.getActivity(), message, title, (Function1) init);
    }

    @NotNull
    public static final ProgressDialog progressDialog(Context $receiver, @Nullable String message, @Nullable String title, @Nullable Function1<? super ProgressDialog, Unit> init) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        return progressDialog($receiver, false, message, title, init);
    }

    @NotNull
    public static final ProgressDialog indeterminateProgressDialog(AnkoContext<?> $receiver, @Nullable String message, @Nullable String title, @Nullable Function1<? super ProgressDialog, Unit> init) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        return indeterminateProgressDialog($receiver.getCtx(), message, title, (Function1) init);
    }

    @NotNull
    public static final ProgressDialog indeterminateProgressDialog(Fragment $receiver, @Nullable String message, @Nullable String title, @Nullable Function1<? super ProgressDialog, Unit> init) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        return indeterminateProgressDialog((Context) $receiver.getActivity(), message, title, (Function1) init);
    }

    @NotNull
    public static final ProgressDialog indeterminateProgressDialog(Context $receiver, @Nullable String message, @Nullable String title, @Nullable Function1<? super ProgressDialog, Unit> init) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        return progressDialog($receiver, true, message, title, init);
    }

    static /* bridge */ /* synthetic */ ProgressDialog progressDialog$default(Context context, boolean z, String str, String str2, Function1 function1, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: progressDialog");
        }
        String str3;
        if ((i & 2) != 0) {
            str = (String) null;
        }
        if ((i & 4) != 0) {
            str3 = (String) null;
        } else {
            str3 = str2;
        }
        return progressDialog(context, z, str, str3, (i & 8) != 0 ? (Function1) null : function1);
    }

    private static final ProgressDialog progressDialog(Context $receiver, boolean indeterminate, String message, String title, Function1<? super ProgressDialog, Unit> init) {
        ProgressDialog progressDialog = new ProgressDialog($receiver);
        ProgressDialog $receiver2 = progressDialog;
        $receiver2.setIndeterminate(indeterminate);
        if (!indeterminate) {
            $receiver2.setProgressStyle(1);
        }
        if (message != null) {
            $receiver2.setMessage(message);
        }
        if (title != null) {
            $receiver2.setTitle(title);
        }
        if (init != null) {
            init.invoke($receiver2);
        }
        $receiver2.show();
        Unit unit = Unit.INSTANCE;
        return progressDialog;
    }
}
