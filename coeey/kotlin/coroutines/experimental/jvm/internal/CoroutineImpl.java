package kotlin.coroutines.experimental.jvm.internal;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.experimental.Continuation;
import kotlin.coroutines.experimental.CoroutineContext;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u0003\n\u0002\b\u0003\b&\u0018\u00002\u00020\u00012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0002B\u001f\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0010\u0010\u0006\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0018\u00010\u0002¢\u0006\u0002\u0010\u0007J$\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\u00022\b\u0010\u0014\u001a\u0004\u0018\u00010\u00032\n\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u0002H\u0016J\u001a\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\u00022\n\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u0002H\u0016J\u001e\u0010\u0015\u001a\u0004\u0018\u00010\u00032\b\u0010\u0016\u001a\u0004\u0018\u00010\u00032\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H$J\u0012\u0010\u0019\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0003H\u0016J\u0010\u0010\u001a\u001a\u00020\u00132\u0006\u0010\u0017\u001a\u00020\u0018H\u0016R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\n\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0018\u00010\u0002X\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u0006\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0018\u00010\u00028\u0004@\u0004X\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\u00020\t8VX\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0019\u0010\u000e\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u00028F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0012\u0010\u0011\u001a\u00020\u00058\u0004@\u0004X\u000e¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lkotlin/coroutines/experimental/jvm/internal/CoroutineImpl;", "Lkotlin/jvm/internal/Lambda;", "Lkotlin/coroutines/experimental/Continuation;", "", "arity", "", "completion", "(ILkotlin/coroutines/experimental/Continuation;)V", "_context", "Lkotlin/coroutines/experimental/CoroutineContext;", "_facade", "context", "getContext", "()Lkotlin/coroutines/experimental/CoroutineContext;", "facade", "getFacade", "()Lkotlin/coroutines/experimental/Continuation;", "label", "create", "", "value", "doResume", "data", "exception", "", "resume", "resumeWithException", "kotlin-stdlib"}, k = 1, mv = {1, 1, 7})
/* compiled from: CoroutineImpl.kt */
public abstract class CoroutineImpl extends Lambda implements Continuation<Object> {
    private final CoroutineContext _context;
    private Continuation<Object> _facade;
    @Nullable
    @JvmField
    protected Continuation<Object> completion;
    @JvmField
    protected int label;

    @Nullable
    protected abstract Object doResume(@Nullable Object obj, @Nullable Throwable th);

    public void resume(@org.jetbrains.annotations.Nullable java.lang.Object r1) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.DecodeException: Load method exception in method: kotlin.coroutines.experimental.jvm.internal.CoroutineImpl.resume(java.lang.Object):void
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
        r0 = this;
        r1 = r3.completion;
        if (r1 != 0) goto L_0x0007;
    L_0x0004:
        kotlin.jvm.internal.Intrinsics.throwNpe();
        r0 = 0;
        r0 = r3.doResume(r4, r0);	 Catch:{ Throwable -> 0x001d }
        r2 = kotlin.coroutines.experimental.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED();	 Catch:{ Throwable -> 0x001d }
        if (r0 == r2) goto L_0x0021;	 Catch:{ Throwable -> 0x001d }
        if (r1 != 0) goto L_0x0022;	 Catch:{ Throwable -> 0x001d }
        r0 = new kotlin.TypeCastException;	 Catch:{ Throwable -> 0x001d }
        r2 = "null cannot be cast to non-null type kotlin.coroutines.experimental.Continuation<kotlin.Any?>";	 Catch:{ Throwable -> 0x001d }
        r0.<init>(r2);	 Catch:{ Throwable -> 0x001d }
        throw r0;	 Catch:{ Throwable -> 0x001d }
    L_0x001d:
        r0 = move-exception;
        r1.resumeWithException(r0);
        return;
        r1.resume(r0);	 Catch:{ Throwable -> 0x001d }
        goto L_0x0021;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.coroutines.experimental.jvm.internal.CoroutineImpl.resume(java.lang.Object):void");
    }

    public void resumeWithException(@org.jetbrains.annotations.NotNull java.lang.Throwable r1) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.DecodeException: Load method exception in method: kotlin.coroutines.experimental.jvm.internal.CoroutineImpl.resumeWithException(java.lang.Throwable):void
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
        r0 = this;
        r0 = "exception";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r4, r0);
        r1 = r3.completion;
        if (r1 != 0) goto L_0x000c;
    L_0x0009:
        kotlin.jvm.internal.Intrinsics.throwNpe();
        r0 = 0;
        r0 = r3.doResume(r0, r4);	 Catch:{ Throwable -> 0x0022 }
        r2 = kotlin.coroutines.experimental.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED();	 Catch:{ Throwable -> 0x0022 }
        if (r0 == r2) goto L_0x0026;	 Catch:{ Throwable -> 0x0022 }
        if (r1 != 0) goto L_0x0027;	 Catch:{ Throwable -> 0x0022 }
        r0 = new kotlin.TypeCastException;	 Catch:{ Throwable -> 0x0022 }
        r2 = "null cannot be cast to non-null type kotlin.coroutines.experimental.Continuation<kotlin.Any?>";	 Catch:{ Throwable -> 0x0022 }
        r0.<init>(r2);	 Catch:{ Throwable -> 0x0022 }
        throw r0;	 Catch:{ Throwable -> 0x0022 }
    L_0x0022:
        r0 = move-exception;
        r1.resumeWithException(r0);
        return;
        r1.resume(r0);	 Catch:{ Throwable -> 0x0022 }
        goto L_0x0026;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.coroutines.experimental.jvm.internal.CoroutineImpl.resumeWithException(java.lang.Throwable):void");
    }

    public CoroutineImpl(int arity, @Nullable Continuation<Object> completion) {
        super(arity);
        this.completion = completion;
        this.label = this.completion != null ? 0 : -1;
        Continuation continuation = this.completion;
        this._context = continuation != null ? continuation.getContext() : null;
    }

    @NotNull
    public CoroutineContext getContext() {
        CoroutineContext coroutineContext = this._context;
        if (coroutineContext == null) {
            Intrinsics.throwNpe();
        }
        return coroutineContext;
    }

    @NotNull
    public final Continuation<Object> getFacade() {
        if (this._facade == null) {
            CoroutineContext coroutineContext = this._context;
            if (coroutineContext == null) {
                Intrinsics.throwNpe();
            }
            this._facade = CoroutineIntrinsics.interceptContinuationIfNeeded(coroutineContext, this);
        }
        Continuation<Object> continuation = this._facade;
        if (continuation == null) {
            Intrinsics.throwNpe();
        }
        return continuation;
    }

    @NotNull
    public Continuation<Unit> create(@NotNull Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        throw new IllegalStateException("create(Continuation) has not been overridden");
    }

    @NotNull
    public Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        throw new IllegalStateException("create(Any?;Continuation) has not been overridden");
    }
}
