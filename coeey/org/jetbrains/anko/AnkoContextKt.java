package org.jetbrains.anko;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.view.View;
import com.ihealth.communication.control.AmProfile;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.anko.internals.AnkoInternals;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 0}, d1 = {"\u00004\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a/\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u00022\u001d\u0010\u0003\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u0001\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0002\b\u0006\u001a7\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00070\u0001*\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u001d\u0010\u0003\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0001\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0002\b\u0006\u001a/\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00070\u0001*\u00020\u00072\u001d\u0010\u0003\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0001\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0002\b\u0006\u001a'\u0010\b\u001a\u00020\n\"\b\b\u0000\u0010\u000b*\u00020\f*\b\u0012\u0004\u0012\u0002H\u000b0\r2\u0006\u0010\u000e\u001a\u0002H\u000b¢\u0006\u0002\u0010\u000f¨\u0006\u0010"}, d2 = {"UI", "Lorg/jetbrains/anko/AnkoContext;", "Landroid/app/Fragment;", "init", "Lkotlin/Function1;", "", "Lkotlin/ExtensionFunctionType;", "Landroid/content/Context;", "setContentView", "", "Landroid/view/View;", "T", "Landroid/app/Activity;", "Lorg/jetbrains/anko/AnkoComponent;", "activity", "(Lorg/jetbrains/anko/AnkoComponent;Landroid/app/Activity;)Landroid/view/View;", "common-compileReleaseKotlin"}, k = 2, mv = {1, 1, 0})
/* compiled from: AnkoContext.kt */
public final class AnkoContextKt {
    @NotNull
    public static final AnkoContext<Context> UI(Context $receiver, boolean setContentView, @NotNull Function1<? super AnkoContext<Context>, Unit> init) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(init, "init");
        return AnkoInternals.INSTANCE.createAnkoContext($receiver, $receiver, init, setContentView);
    }

    @NotNull
    public static final AnkoContext<Context> UI(Context $receiver, @NotNull Function1<? super AnkoContext<Context>, Unit> init) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(init, "init");
        return AnkoInternals.createAnkoContext$default(AnkoInternals.INSTANCE, $receiver, $receiver, init, false, 4, null);
    }

    @NotNull
    public static final AnkoContext<Fragment> UI(Fragment $receiver, @NotNull Function1<? super AnkoContext<Fragment>, Unit> init) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(init, "init");
        AnkoInternals ankoInternals = AnkoInternals.INSTANCE;
        Context activity = $receiver.getActivity();
        Intrinsics.checkExpressionValueIsNotNull(activity, AmProfile.SYNC_ACTIVITY_DATA_AM);
        return AnkoInternals.createAnkoContext$default(ankoInternals, $receiver, activity, init, false, 4, null);
    }

    @NotNull
    public static final <T extends Activity> View setContentView(AnkoComponent<T> $receiver, @NotNull T activity) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(activity, AmProfile.SYNC_ACTIVITY_DATA_AM);
        return $receiver.createView(new AnkoContextImpl((Context) activity, activity, true));
    }
}
