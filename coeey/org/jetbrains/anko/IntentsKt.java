package org.jetbrains.anko;

import android.app.Activity;
import android.app.Fragment;
import android.app.Service;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.facebook.react.views.text.ReactTextShadowNode;
import com.google.common.primitives.Ints;
import com.ihealth.communication.control.AmProfile;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.anko.internals.AnkoInternals;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 0}, d1 = {"\u0000P\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u001a\u001f\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u0001H\b\u001a\u001c\u0010\u0000\u001a\u00020\u0001*\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u0001\u001a#\u0010\u0000\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u00072\u0006\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u0001H\b\u001a\r\u0010\b\u001a\u00020\t*\u00020\tH\b\u001a\r\u0010\n\u001a\u00020\t*\u00020\tH\b\u001a\r\u0010\u000b\u001a\u00020\t*\u00020\tH\b\u001a)\u0010\f\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\f\u001a\u00020\u00042\b\b\u0002\u0010\r\u001a\u00020\u00042\b\b\u0002\u0010\u000e\u001a\u00020\u0004H\b\u001a&\u0010\f\u001a\u00020\u0001*\u00020\u00062\u0006\u0010\f\u001a\u00020\u00042\b\b\u0002\u0010\r\u001a\u00020\u00042\b\b\u0002\u0010\u000e\u001a\u00020\u0004\u001a-\u0010\f\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u00072\u0006\u0010\f\u001a\u00020\u00042\b\b\u0002\u0010\r\u001a\u00020\u00042\b\b\u0002\u0010\u000e\u001a\u00020\u0004H\b\u001a\r\u0010\u000f\u001a\u00020\t*\u00020\tH\b\u001aJ\u0010\u0010\u001a\u00020\t\"\n\b\u0000\u0010\u0011\u0018\u0001*\u00020\u0012*\u00020\u00022*\u0010\u0013\u001a\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00120\u00150\u0014\"\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00120\u0015H\b¢\u0006\u0002\u0010\u0016\u001aJ\u0010\u0010\u001a\u00020\t\"\n\b\u0000\u0010\u0011\u0018\u0001*\u00020\u0012*\u00020\u00062*\u0010\u0013\u001a\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00120\u00150\u0014\"\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00120\u0015H\b¢\u0006\u0002\u0010\u0017\u001aN\u0010\u0010\u001a\u00020\t\"\n\b\u0000\u0010\u0011\u0018\u0001*\u00020\u0012*\u0006\u0012\u0002\b\u00030\u00072*\u0010\u0013\u001a\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00120\u00150\u0014\"\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00120\u0015H\b¢\u0006\u0002\u0010\u0018\u001a\u0015\u0010\u0019\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u0004H\b\u001a\u0012\u0010\u0019\u001a\u00020\u0001*\u00020\u00062\u0006\u0010\u001a\u001a\u00020\u0004\u001a\u0019\u0010\u0019\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u00072\u0006\u0010\u001a\u001a\u00020\u0004H\b\u001a\r\u0010\u001b\u001a\u00020\t*\u00020\tH\b\u001a\r\u0010\u0005\u001a\u00020\t*\u00020\tH\b\u001a\r\u0010\u001c\u001a\u00020\t*\u00020\tH\b\u001a\r\u0010\u001d\u001a\u00020\t*\u00020\tH\b\u001a\u001f\u0010\u001e\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u00042\b\b\u0002\u0010\u000e\u001a\u00020\u0004H\b\u001a\u001c\u0010\u001e\u001a\u00020\u0001*\u00020\u00062\u0006\u0010\u001a\u001a\u00020\u00042\b\b\u0002\u0010\u000e\u001a\u00020\u0004\u001a#\u0010\u001e\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u00072\u0006\u0010\u001a\u001a\u00020\u00042\b\b\u0002\u0010\u000e\u001a\u00020\u0004H\b\u001a\u001f\u0010\u001f\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u00042\b\b\u0002\u0010\r\u001a\u00020\u0004H\b\u001a\u001c\u0010\u001f\u001a\u00020\u0001*\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u00042\b\b\u0002\u0010\r\u001a\u00020\u0004\u001a#\u0010\u001f\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u00072\u0006\u0010\u000e\u001a\u00020\u00042\b\b\u0002\u0010\r\u001a\u00020\u0004H\b\u001a\r\u0010 \u001a\u00020\t*\u00020\tH\b\u001aJ\u0010!\u001a\u00020\"\"\n\b\u0000\u0010\u0011\u0018\u0001*\u00020#*\u00020\u00022*\u0010\u0013\u001a\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00120\u00150\u0014\"\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00120\u0015H\b¢\u0006\u0002\u0010$\u001aJ\u0010!\u001a\u00020\"\"\n\b\u0000\u0010\u0011\u0018\u0001*\u00020#*\u00020\u00062*\u0010\u0013\u001a\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00120\u00150\u0014\"\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00120\u0015H\b¢\u0006\u0002\u0010%\u001aN\u0010!\u001a\u00020\"\"\n\b\u0000\u0010\u0011\u0018\u0001*\u00020#*\u0006\u0012\u0002\b\u00030\u00072*\u0010\u0013\u001a\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00120\u00150\u0014\"\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00120\u0015H\b¢\u0006\u0002\u0010&\u001aR\u0010'\u001a\u00020\"\"\n\b\u0000\u0010\u0011\u0018\u0001*\u00020#*\u00020#2\u0006\u0010(\u001a\u00020)2*\u0010\u0013\u001a\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00120\u00150\u0014\"\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00120\u0015H\b¢\u0006\u0002\u0010*\u001aR\u0010'\u001a\u00020\"\"\n\b\u0000\u0010\u0011\u0018\u0001*\u00020#*\u00020\u00022\u0006\u0010(\u001a\u00020)2*\u0010\u0013\u001a\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00120\u00150\u0014\"\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00120\u0015H\b¢\u0006\u0002\u0010+\u001aJ\u0010,\u001a\u00020\"\"\n\b\u0000\u0010\u0011\u0018\u0001*\u00020-*\u00020\u00022*\u0010\u0013\u001a\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00120\u00150\u0014\"\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00120\u0015H\b¢\u0006\u0002\u0010$\u001aJ\u0010,\u001a\u00020\"\"\n\b\u0000\u0010\u0011\u0018\u0001*\u00020-*\u00020\u00062*\u0010\u0013\u001a\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00120\u00150\u0014\"\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00120\u0015H\b¢\u0006\u0002\u0010%\u001aN\u0010,\u001a\u00020\"\"\n\b\u0000\u0010\u0011\u0018\u0001*\u00020-*\u0006\u0012\u0002\b\u00030\u00072*\u0010\u0013\u001a\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00120\u00150\u0014\"\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00120\u0015H\b¢\u0006\u0002\u0010&¨\u0006."}, d2 = {"browse", "", "Landroid/app/Fragment;", "url", "", "newTask", "Landroid/content/Context;", "Lorg/jetbrains/anko/AnkoContext;", "clearTask", "Landroid/content/Intent;", "clearTop", "clearWhenTaskReset", "email", "subject", "text", "excludeFromRecents", "intentFor", "T", "", "params", "", "Lkotlin/Pair;", "(Landroid/app/Fragment;[Lkotlin/Pair;)Landroid/content/Intent;", "(Landroid/content/Context;[Lkotlin/Pair;)Landroid/content/Intent;", "(Lorg/jetbrains/anko/AnkoContext;[Lkotlin/Pair;)Landroid/content/Intent;", "makeCall", "number", "multipleTask", "noAnimation", "noHistory", "sendSMS", "share", "singleTop", "startActivity", "", "Landroid/app/Activity;", "(Landroid/app/Fragment;[Lkotlin/Pair;)V", "(Landroid/content/Context;[Lkotlin/Pair;)V", "(Lorg/jetbrains/anko/AnkoContext;[Lkotlin/Pair;)V", "startActivityForResult", "requestCode", "", "(Landroid/app/Activity;I[Lkotlin/Pair;)V", "(Landroid/app/Fragment;I[Lkotlin/Pair;)V", "startService", "Landroid/app/Service;", "common-compileReleaseKotlin"}, k = 2, mv = {1, 1, 0})
/* compiled from: Intents.kt */
public final class IntentsKt {
    private static final <T extends Activity> void startActivity(Context $receiver, Pair<String, ? extends Object>... params) {
        Intrinsics.reifiedOperationMarker(4, "T");
        AnkoInternals.internalStartActivity($receiver, Activity.class, params);
    }

    private static final <T extends Activity> void startActivity(AnkoContext<?> $receiver, Pair<String, ? extends Object>... params) {
        Context ctx = $receiver.getCtx();
        Intrinsics.reifiedOperationMarker(4, "T");
        AnkoInternals.internalStartActivity(ctx, Activity.class, params);
    }

    private static final <T extends Activity> void startActivity(Fragment $receiver, Pair<String, ? extends Object>... params) {
        Context activity = $receiver.getActivity();
        Intrinsics.checkExpressionValueIsNotNull(activity, AmProfile.SYNC_ACTIVITY_DATA_AM);
        Intrinsics.reifiedOperationMarker(4, "T");
        AnkoInternals.internalStartActivity(activity, Activity.class, params);
    }

    private static final <T extends Activity> void startActivityForResult(Activity $receiver, int requestCode, Pair<String, ? extends Object>... params) {
        Intrinsics.reifiedOperationMarker(4, "T");
        AnkoInternals.internalStartActivityForResult($receiver, Activity.class, requestCode, params);
    }

    private static final <T extends Activity> void startActivityForResult(Fragment $receiver, int requestCode, Pair<String, ? extends Object>... params) {
        Context act = ContextUtilsKt.getAct($receiver);
        Intrinsics.reifiedOperationMarker(4, "T");
        $receiver.startActivityForResult(AnkoInternals.createIntent(act, Activity.class, params), requestCode);
    }

    private static final <T extends Service> void startService(Context $receiver, Pair<String, ? extends Object>... params) {
        Intrinsics.reifiedOperationMarker(4, "T");
        AnkoInternals.internalStartService($receiver, Service.class, params);
    }

    private static final <T extends Service> void startService(AnkoContext<?> $receiver, Pair<String, ? extends Object>... params) {
        Context ctx = $receiver.getCtx();
        Intrinsics.reifiedOperationMarker(4, "T");
        AnkoInternals.internalStartService(ctx, Service.class, params);
    }

    private static final <T extends Service> void startService(Fragment $receiver, Pair<String, ? extends Object>... params) {
        Context activity = $receiver.getActivity();
        Intrinsics.checkExpressionValueIsNotNull(activity, AmProfile.SYNC_ACTIVITY_DATA_AM);
        Intrinsics.reifiedOperationMarker(4, "T");
        AnkoInternals.internalStartService(activity, Service.class, params);
    }

    private static final <T> Intent intentFor(Context $receiver, Pair<String, ? extends Object>... params) {
        Intrinsics.reifiedOperationMarker(4, "T");
        return AnkoInternals.createIntent($receiver, Object.class, params);
    }

    private static final <T> Intent intentFor(AnkoContext<?> $receiver, Pair<String, ? extends Object>... params) {
        Context ctx = $receiver.getCtx();
        Intrinsics.reifiedOperationMarker(4, "T");
        return AnkoInternals.createIntent(ctx, Object.class, params);
    }

    private static final <T> Intent intentFor(Fragment $receiver, Pair<String, ? extends Object>... params) {
        Context activity = $receiver.getActivity();
        Intrinsics.checkExpressionValueIsNotNull(activity, AmProfile.SYNC_ACTIVITY_DATA_AM);
        Intrinsics.reifiedOperationMarker(4, "T");
        return AnkoInternals.createIntent(activity, Object.class, params);
    }

    @NotNull
    public static final Intent clearTask(Intent $receiver) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        $receiver.addFlags(32768);
        Unit unit = Unit.INSTANCE;
        return $receiver;
    }

    @NotNull
    public static final Intent clearTop(Intent $receiver) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        $receiver.addFlags(67108864);
        Unit unit = Unit.INSTANCE;
        return $receiver;
    }

    @NotNull
    public static final Intent clearWhenTaskReset(Intent $receiver) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        $receiver.addFlags(524288);
        Unit unit = Unit.INSTANCE;
        return $receiver;
    }

    @NotNull
    public static final Intent excludeFromRecents(Intent $receiver) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        $receiver.addFlags(8388608);
        Unit unit = Unit.INSTANCE;
        return $receiver;
    }

    @NotNull
    public static final Intent multipleTask(Intent $receiver) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        $receiver.addFlags(134217728);
        Unit unit = Unit.INSTANCE;
        return $receiver;
    }

    @NotNull
    public static final Intent newTask(Intent $receiver) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        $receiver.addFlags(268435456);
        Unit unit = Unit.INSTANCE;
        return $receiver;
    }

    @NotNull
    public static final Intent noAnimation(Intent $receiver) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        $receiver.addFlags(65536);
        Unit unit = Unit.INSTANCE;
        return $receiver;
    }

    @NotNull
    public static final Intent noHistory(Intent $receiver) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        $receiver.addFlags(Ints.MAX_POWER_OF_TWO);
        Unit unit = Unit.INSTANCE;
        return $receiver;
    }

    @NotNull
    public static final Intent singleTop(Intent $receiver) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        $receiver.addFlags(536870912);
        Unit unit = Unit.INSTANCE;
        return $receiver;
    }

    public static final boolean browse(AnkoContext<?> $receiver, @NotNull String url, boolean newTask) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(url, "url");
        return browse($receiver.getCtx(), url, newTask);
    }

    public static final boolean browse(Fragment $receiver, @NotNull String url, boolean newTask) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(url, "url");
        return browse((Context) $receiver.getActivity(), url, newTask);
    }

    public static final boolean browse(Context $receiver, @NotNull String url, boolean newTask) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(url, "url");
        try {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setData(Uri.parse(url));
            if (newTask) {
                intent.addFlags(268435456);
            }
            $receiver.startActivity(intent);
            return true;
        } catch (ActivityNotFoundException e) {
            Throwable th = e;
            if (th == null) {
                throw new TypeCastException("null cannot be cast to non-null type java.lang.Throwable");
            }
            th.printStackTrace();
            return false;
        }
    }

    public static final boolean share(AnkoContext<?> $receiver, @NotNull String text, @NotNull String subject) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(text, ReactTextShadowNode.PROP_TEXT);
        Intrinsics.checkParameterIsNotNull(subject, "subject");
        return share($receiver.getCtx(), text, subject);
    }

    public static final boolean share(Fragment $receiver, @NotNull String text, @NotNull String subject) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(text, ReactTextShadowNode.PROP_TEXT);
        Intrinsics.checkParameterIsNotNull(subject, "subject");
        return share((Context) $receiver.getActivity(), text, subject);
    }

    public static final boolean share(Context $receiver, @NotNull String text, @NotNull String subject) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(text, ReactTextShadowNode.PROP_TEXT);
        Intrinsics.checkParameterIsNotNull(subject, "subject");
        try {
            Intent intent = new Intent("android.intent.action.SEND");
            intent.setType("text/plain");
            intent.putExtra("android.intent.extra.SUBJECT", subject);
            intent.putExtra("android.intent.extra.TEXT", text);
            $receiver.startActivity(Intent.createChooser(intent, (CharSequence) null));
            return true;
        } catch (ActivityNotFoundException e) {
            Throwable th = e;
            if (th == null) {
                throw new TypeCastException("null cannot be cast to non-null type java.lang.Throwable");
            }
            th.printStackTrace();
            return false;
        }
    }

    public static final boolean email(AnkoContext<?> $receiver, @NotNull String email, @NotNull String subject, @NotNull String text) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(email, "email");
        Intrinsics.checkParameterIsNotNull(subject, "subject");
        Intrinsics.checkParameterIsNotNull(text, ReactTextShadowNode.PROP_TEXT);
        return email($receiver.getCtx(), email, subject, text);
    }

    public static final boolean email(Fragment $receiver, @NotNull String email, @NotNull String subject, @NotNull String text) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(email, "email");
        Intrinsics.checkParameterIsNotNull(subject, "subject");
        Intrinsics.checkParameterIsNotNull(text, ReactTextShadowNode.PROP_TEXT);
        return email((Context) $receiver.getActivity(), email, subject, text);
    }

    public static final boolean email(Context $receiver, @NotNull String email, @NotNull String subject, @NotNull String text) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(email, "email");
        Intrinsics.checkParameterIsNotNull(subject, "subject");
        Intrinsics.checkParameterIsNotNull(text, ReactTextShadowNode.PROP_TEXT);
        Intent intent = new Intent("android.intent.action.SENDTO");
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra("android.intent.extra.EMAIL", (String[]) ((Object[]) new String[]{email}));
        if (subject.length() > 0) {
            intent.putExtra("android.intent.extra.SUBJECT", subject);
        }
        if (text.length() > 0) {
            intent.putExtra("android.intent.extra.TEXT", text);
        }
        if (intent.resolveActivity($receiver.getPackageManager()) == null) {
            return false;
        }
        $receiver.startActivity(intent);
        return true;
    }

    public static final boolean makeCall(AnkoContext<?> $receiver, @NotNull String number) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(number, "number");
        return makeCall($receiver.getCtx(), number);
    }

    public static final boolean makeCall(Fragment $receiver, @NotNull String number) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(number, "number");
        return makeCall((Context) $receiver.getActivity(), number);
    }

    public static final boolean makeCall(Context $receiver, @NotNull String number) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(number, "number");
        try {
            $receiver.startActivity(new Intent("android.intent.action.CALL", Uri.parse("tel:" + number)));
            return true;
        } catch (Exception e) {
            Throwable th = e;
            if (th == null) {
                throw new TypeCastException("null cannot be cast to non-null type java.lang.Throwable");
            }
            th.printStackTrace();
            return false;
        }
    }

    public static final boolean sendSMS(AnkoContext<?> $receiver, @NotNull String number, @NotNull String text) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(number, "number");
        Intrinsics.checkParameterIsNotNull(text, ReactTextShadowNode.PROP_TEXT);
        return sendSMS($receiver.getCtx(), number, text);
    }

    public static final boolean sendSMS(Fragment $receiver, @NotNull String number, @NotNull String text) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(number, "number");
        Intrinsics.checkParameterIsNotNull(text, ReactTextShadowNode.PROP_TEXT);
        return sendSMS((Context) $receiver.getActivity(), number, text);
    }

    public static final boolean sendSMS(Context $receiver, @NotNull String number, @NotNull String text) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(number, "number");
        Intrinsics.checkParameterIsNotNull(text, ReactTextShadowNode.PROP_TEXT);
        try {
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("sms:" + number));
            intent.putExtra("sms_body", text);
            $receiver.startActivity(intent);
            return true;
        } catch (Exception e) {
            Throwable th = e;
            if (th == null) {
                throw new TypeCastException("null cannot be cast to non-null type java.lang.Throwable");
            }
            th.printStackTrace();
            return false;
        }
    }
}
