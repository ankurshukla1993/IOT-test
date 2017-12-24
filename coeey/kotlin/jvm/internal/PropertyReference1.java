package kotlin.jvm.internal;

import kotlin.SinceKotlin;
import kotlin.reflect.KCallable;
import kotlin.reflect.KProperty1;
import kotlin.reflect.KProperty1.Getter;

public abstract class PropertyReference1 extends PropertyReference implements KProperty1 {
    @SinceKotlin(version = "1.1")
    public PropertyReference1(Object receiver) {
        super(receiver);
    }

    protected KCallable computeReflected() {
        return Reflection.property1(this);
    }

    public Object invoke(Object receiver) {
        return get(receiver);
    }

    public Getter getGetter() {
        return ((KProperty1) getReflected()).getGetter();
    }

    @SinceKotlin(version = "1.1")
    public Object getDelegate(Object receiver) {
        return ((KProperty1) getReflected()).getDelegate(receiver);
    }
}
