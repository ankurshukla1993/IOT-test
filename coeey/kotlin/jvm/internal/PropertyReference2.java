package kotlin.jvm.internal;

import kotlin.SinceKotlin;
import kotlin.reflect.KCallable;
import kotlin.reflect.KProperty2;
import kotlin.reflect.KProperty2.Getter;

public abstract class PropertyReference2 extends PropertyReference implements KProperty2 {
    protected KCallable computeReflected() {
        return Reflection.property2(this);
    }

    public Object invoke(Object receiver1, Object receiver2) {
        return get(receiver1, receiver2);
    }

    public Getter getGetter() {
        return ((KProperty2) getReflected()).getGetter();
    }

    @SinceKotlin(version = "1.1")
    public Object getDelegate(Object receiver1, Object receiver2) {
        return ((KProperty2) getReflected()).getDelegate(receiver1, receiver2);
    }
}
