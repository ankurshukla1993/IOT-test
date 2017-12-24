package org.jetbrains.anko;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import com.facebook.internal.NativeProtocol;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.anko.AnkoContext.DefaultImpls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 0}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\r\u0012\u0006\u0010\u0004\u001a\u00028\u0000¢\u0006\u0002\u0010\u0005J\u001c\u0010\u0012\u001a\u00020\u00132\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0016R\u001c\u0010\u0006\u001a\n \b*\u0004\u0018\u00010\u00070\u0007X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0016\u0010\u0004\u001a\u00028\u0000X\u0004¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\u000e\u001a\u00020\u000fX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/anko/DelegatingAnkoContext;", "T", "Landroid/view/ViewGroup;", "Lorg/jetbrains/anko/AnkoContext;", "owner", "(Landroid/view/ViewGroup;)V", "ctx", "Landroid/content/Context;", "kotlin.jvm.PlatformType", "getCtx", "()Landroid/content/Context;", "getOwner", "()Landroid/view/ViewGroup;", "Landroid/view/ViewGroup;", "view", "Landroid/view/View;", "getView", "()Landroid/view/View;", "addView", "", "params", "Landroid/view/ViewGroup$LayoutParams;", "common-compileReleaseKotlin"}, k = 1, mv = {1, 1, 0})
/* compiled from: AnkoContext.kt */
public final class DelegatingAnkoContext<T extends ViewGroup> implements AnkoContext<T> {
    private final Context ctx = getOwner().getContext();
    @NotNull
    private final T owner;
    @NotNull
    private final View view = getOwner();

    public DelegatingAnkoContext(@NotNull T owner) {
        Intrinsics.checkParameterIsNotNull(owner, "owner");
        this.owner = owner;
    }

    @NotNull
    public T getOwner() {
        return this.owner;
    }

    public void removeView(@NotNull View view) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        DefaultImpls.removeView(this, view);
    }

    public void updateViewLayout(@NotNull View view, @NotNull LayoutParams params) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        Intrinsics.checkParameterIsNotNull(params, NativeProtocol.WEB_DIALOG_PARAMS);
        DefaultImpls.updateViewLayout(this, view, params);
    }

    public Context getCtx() {
        return this.ctx;
    }

    @NotNull
    public View getView() {
        return this.view;
    }

    public void addView(@Nullable View view, @Nullable LayoutParams params) {
        if (view != null) {
            if (params == null) {
                getOwner().addView(view);
            } else {
                getOwner().addView(view, params);
            }
        }
    }
}
