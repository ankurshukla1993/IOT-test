package kotlin.jvm.internal;

import kotlin.SinceKotlin;
import kotlin.reflect.KCallable;
import kotlin.reflect.KFunction;

public class FunctionReference extends CallableReference implements FunctionBase, KFunction {
    private final int arity;

    public FunctionReference(int arity) {
        this.arity = arity;
    }

    @SinceKotlin(version = "1.1")
    public FunctionReference(int arity, Object receiver) {
        super(receiver);
        this.arity = arity;
    }

    public int getArity() {
        return this.arity;
    }

    @SinceKotlin(version = "1.1")
    protected KFunction getReflected() {
        return (KFunction) super.getReflected();
    }

    @SinceKotlin(version = "1.1")
    protected KCallable computeReflected() {
        return Reflection.function(this);
    }

    @SinceKotlin(version = "1.1")
    public boolean isInline() {
        return getReflected().isInline();
    }

    @SinceKotlin(version = "1.1")
    public boolean isExternal() {
        return getReflected().isExternal();
    }

    @SinceKotlin(version = "1.1")
    public boolean isOperator() {
        return getReflected().isOperator();
    }

    @SinceKotlin(version = "1.1")
    public boolean isInfix() {
        return getReflected().isInfix();
    }

    @SinceKotlin(version = "1.1")
    public boolean isSuspend() {
        return getReflected().isSuspend();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r6) {
        /*
        r5 = this;
        r1 = 1;
        r2 = 0;
        if (r6 != r5) goto L_0x0005;
    L_0x0004:
        return r1;
    L_0x0005:
        r3 = r6 instanceof kotlin.jvm.internal.FunctionReference;
        if (r3 == 0) goto L_0x0053;
    L_0x0009:
        r0 = r6;
        r0 = (kotlin.jvm.internal.FunctionReference) r0;
        r3 = r5.getOwner();
        if (r3 != 0) goto L_0x0044;
    L_0x0012:
        r3 = r0.getOwner();
        if (r3 != 0) goto L_0x0042;
    L_0x0018:
        r3 = r5.getName();
        r4 = r0.getName();
        r3 = r3.equals(r4);
        if (r3 == 0) goto L_0x0042;
    L_0x0026:
        r3 = r5.getSignature();
        r4 = r0.getSignature();
        r3 = r3.equals(r4);
        if (r3 == 0) goto L_0x0042;
    L_0x0034:
        r3 = r5.getBoundReceiver();
        r4 = r0.getBoundReceiver();
        r3 = kotlin.jvm.internal.Intrinsics.areEqual(r3, r4);
        if (r3 != 0) goto L_0x0004;
    L_0x0042:
        r1 = r2;
        goto L_0x0004;
    L_0x0044:
        r3 = r5.getOwner();
        r4 = r0.getOwner();
        r3 = r3.equals(r4);
        if (r3 == 0) goto L_0x0042;
    L_0x0052:
        goto L_0x0018;
    L_0x0053:
        r1 = r6 instanceof kotlin.reflect.KFunction;
        if (r1 == 0) goto L_0x0060;
    L_0x0057:
        r1 = r5.compute();
        r1 = r6.equals(r1);
        goto L_0x0004;
    L_0x0060:
        r1 = r2;
        goto L_0x0004;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.jvm.internal.FunctionReference.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        return (((getOwner() == null ? 0 : getOwner().hashCode() * 31) + getName().hashCode()) * 31) + getSignature().hashCode();
    }

    public String toString() {
        FunctionReference reflected = compute();
        if (reflected != this) {
            return reflected.toString();
        }
        return "<init>".equals(getName()) ? "constructor (Kotlin reflection is not available)" : "function " + getName() + " (Kotlin reflection is not available)";
    }
}
