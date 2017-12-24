package org.jetbrains.anko;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 0}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u000b\b\b\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u0019\u0012\b\u0010\u0003\u001a\u0004\u0018\u00018\u0000\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\f\u001a\u0004\u0018\u00018\u0000HÆ\u0003¢\u0006\u0002\u0010\nJ\u000b\u0010\r\u001a\u0004\u0018\u00010\u0005HÆ\u0003J,\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00018\u00002\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001¢\u0006\u0002\u0010\u000fR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0015\u0010\u0003\u001a\u0004\u0018\u00018\u0000¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\t\u0010\n¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/anko/AttemptResult;", "T", "", "value", "error", "", "(Ljava/lang/Object;Ljava/lang/Throwable;)V", "getError", "()Ljava/lang/Throwable;", "getValue", "()Ljava/lang/Object;", "Ljava/lang/Object;", "component1", "component2", "copy", "(Ljava/lang/Object;Ljava/lang/Throwable;)Lorg/jetbrains/anko/AttemptResult;", "common-compileReleaseKotlin"}, k = 1, mv = {1, 1, 0})
/* compiled from: Helpers.kt */
public final class AttemptResult<T> {
    @Nullable
    private final Throwable error;
    @Nullable
    private final T value;

    @NotNull
    public static /* bridge */ /* synthetic */ AttemptResult copy$default(AttemptResult attemptResult, Object obj, Throwable th, int i, Object obj2) {
        if (obj2 != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: copy");
        }
        if ((i & 1) != 0) {
            obj = attemptResult.value;
        }
        if ((i & 2) != 0) {
            th = attemptResult.error;
        }
        return attemptResult.copy(obj, th);
    }

    @Nullable
    public final T component1() {
        return this.value;
    }

    @Nullable
    public final Throwable component2() {
        return this.error;
    }

    @NotNull
    public final AttemptResult<T> copy(@Nullable T value, @Nullable Throwable error) {
        return new AttemptResult(value, error);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r3) {
        /*
        r2 = this;
        if (r2 == r3) goto L_0x001c;
    L_0x0002:
        r0 = r3 instanceof org.jetbrains.anko.AttemptResult;
        if (r0 == 0) goto L_0x001e;
    L_0x0006:
        r3 = (org.jetbrains.anko.AttemptResult) r3;
        r0 = r2.value;
        r1 = r3.value;
        r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1);
        if (r0 == 0) goto L_0x001e;
    L_0x0012:
        r0 = r2.error;
        r1 = r3.error;
        r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1);
        if (r0 == 0) goto L_0x001e;
    L_0x001c:
        r0 = 1;
    L_0x001d:
        return r0;
    L_0x001e:
        r0 = 0;
        goto L_0x001d;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jetbrains.anko.AttemptResult.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        int i = 0;
        Object obj = this.value;
        int hashCode = (obj != null ? obj.hashCode() : 0) * 31;
        Throwable th = this.error;
        if (th != null) {
            i = th.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        return "AttemptResult(value=" + this.value + ", error=" + this.error + ")";
    }

    public AttemptResult(@Nullable T value, @Nullable Throwable error) {
        this.value = value;
        this.error = error;
    }

    @Nullable
    public final Throwable getError() {
        return this.error;
    }

    @Nullable
    public final T getValue() {
        return this.value;
    }
}
