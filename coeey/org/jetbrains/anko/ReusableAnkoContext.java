package org.jetbrains.anko;

import android.content.Context;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 0}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00028\u0000\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\b\u0010\u000e\u001a\u00020\u000fH\u0014R\u0014\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0016\u0010\u0005\u001a\u00028\u0000X\u0004¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/anko/ReusableAnkoContext;", "T", "Lorg/jetbrains/anko/AnkoContextImpl;", "ctx", "Landroid/content/Context;", "owner", "setContentView", "", "(Landroid/content/Context;Ljava/lang/Object;Z)V", "getCtx", "()Landroid/content/Context;", "getOwner", "()Ljava/lang/Object;", "Ljava/lang/Object;", "alreadyHasView", "", "common-compileReleaseKotlin"}, k = 1, mv = {1, 1, 0})
/* compiled from: AnkoContext.kt */
public final class ReusableAnkoContext<T> extends AnkoContextImpl<T> {
    @NotNull
    private final Context ctx;
    private final T owner;
    private final boolean setContentView;

    @NotNull
    public Context getCtx() {
        return this.ctx;
    }

    public T getOwner() {
        return this.owner;
    }

    public ReusableAnkoContext(@NotNull Context ctx, T owner, boolean setContentView) {
        Intrinsics.checkParameterIsNotNull(ctx, "ctx");
        super(ctx, owner, setContentView);
        this.ctx = ctx;
        this.owner = owner;
        this.setContentView = setContentView;
    }

    protected void alreadyHasView() {
    }
}
