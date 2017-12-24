package kotlin;

import java.io.Serializable;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0002\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\u00060\u0003j\u0002`\u0004B\u001f\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\tJ\b\u0010\u000e\u001a\u00020\u000fH\u0016J\b\u0010\u0010\u001a\u00020\u0011H\u0016J\b\u0010\u0012\u001a\u00020\bH\u0002R\u0014\u0010\n\u001a\u0004\u0018\u00010\b8\u0002@\u0002X\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0006X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\u00028\u00008VX\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\r¨\u0006\u0013"}, d2 = {"Lkotlin/SynchronizedLazyImpl;", "T", "Lkotlin/Lazy;", "Ljava/io/Serializable;", "Lkotlin/io/Serializable;", "initializer", "Lkotlin/Function0;", "lock", "", "(Lkotlin/jvm/functions/Function0;Ljava/lang/Object;)V", "_value", "value", "getValue", "()Ljava/lang/Object;", "isInitialized", "", "toString", "", "writeReplace", "kotlin-stdlib"}, k = 1, mv = {1, 1, 7})
/* compiled from: Lazy.kt */
final class SynchronizedLazyImpl<T> implements Lazy<T>, Serializable {
    private volatile Object _value;
    private Function0<? extends T> initializer;
    private final Object lock;

    public T getValue() {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.DecodeException: Load method exception in method: kotlin.SynchronizedLazyImpl.getValue():T
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
        r0 = r5._value;
        r3 = kotlin.UNINITIALIZED_VALUE.INSTANCE;
        if (r0 == r3) goto L_0x0007;
    L_0x0006:
        return r0;
    L_0x0007:
        r4 = r5.lock;
        monitor-enter(r4);
        r1 = r5._value;	 Catch:{ all -> 0x0029 }
        r3 = kotlin.UNINITIALIZED_VALUE.INSTANCE;	 Catch:{ all -> 0x0029 }
        if (r1 == r3) goto L_0x0015;
    L_0x0012:
        monitor-exit(r4);
        r0 = r1;
        goto L_0x0006;
    L_0x0015:
        r3 = r5.initializer;	 Catch:{ all -> 0x0029 }
        if (r3 != 0) goto L_0x001c;	 Catch:{ all -> 0x0029 }
    L_0x0019:
        kotlin.jvm.internal.Intrinsics.throwNpe();	 Catch:{ all -> 0x0029 }
    L_0x001c:
        r2 = r3.invoke();	 Catch:{ all -> 0x0029 }
        r5._value = r2;	 Catch:{ all -> 0x0029 }
        r3 = 0;	 Catch:{ all -> 0x0029 }
        r3 = (kotlin.jvm.functions.Function0) r3;	 Catch:{ all -> 0x0029 }
        r5.initializer = r3;	 Catch:{ all -> 0x0029 }
        r1 = r2;
        goto L_0x0011;
    L_0x0029:
        r3 = move-exception;
        monitor-exit(r4);
        throw r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.SynchronizedLazyImpl.getValue():T");
    }

    public SynchronizedLazyImpl(@NotNull Function0<? extends T> initializer, @Nullable Object lock) {
        Intrinsics.checkParameterIsNotNull(initializer, "initializer");
        this.initializer = initializer;
        this._value = UNINITIALIZED_VALUE.INSTANCE;
        if (lock == null) {
            SynchronizedLazyImpl lock2 = this;
        }
        this.lock = lock;
    }

    public /* synthetic */ SynchronizedLazyImpl(Function0 function0, Object obj, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 2) != 0) {
            obj = null;
        }
        this(function0, obj);
    }

    public boolean isInitialized() {
        return this._value != UNINITIALIZED_VALUE.INSTANCE;
    }

    @NotNull
    public String toString() {
        return isInitialized() ? String.valueOf(getValue()) : "Lazy value not initialized yet.";
    }

    private final Object writeReplace() {
        return new InitializedLazyImpl(getValue());
    }
}
