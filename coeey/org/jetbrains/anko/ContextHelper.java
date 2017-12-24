package org.jetbrains.anko;

import android.os.Handler;
import android.os.Looper;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 0}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\bÂ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0019\u0010\u0007\u001a\n \t*\u0004\u0018\u00010\b0\b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lorg/jetbrains/anko/ContextHelper;", "", "()V", "handler", "Landroid/os/Handler;", "getHandler", "()Landroid/os/Handler;", "mainThread", "Ljava/lang/Thread;", "kotlin.jvm.PlatformType", "getMainThread", "()Ljava/lang/Thread;", "common-compileReleaseKotlin"}, k = 1, mv = {1, 1, 0})
/* compiled from: Async.kt */
final class ContextHelper {
    public static final ContextHelper INSTANCE = null;
    @NotNull
    private static final Handler handler = null;
    private static final Thread mainThread = null;

    static {
        ContextHelper contextHelper = new ContextHelper();
    }

    private ContextHelper() {
        INSTANCE = this;
        handler = new Handler(Looper.getMainLooper());
        mainThread = Looper.getMainLooper().getThread();
    }

    @NotNull
    public final Handler getHandler() {
        return handler;
    }

    public final Thread getMainThread() {
        return mainThread;
    }
}
