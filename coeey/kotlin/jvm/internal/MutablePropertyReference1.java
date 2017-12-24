package kotlin.jvm.internal;

import kotlin.SinceKotlin;
import kotlin.reflect.KCallable;
import kotlin.reflect.KMutableProperty1;
import kotlin.reflect.KMutableProperty1.Setter;
import kotlin.reflect.KProperty1.Getter;

public abstract class MutablePropertyReference1 extends MutablePropertyReference implements KMutableProperty1 {
    @SinceKotlin(version = "1.1")
    public MutablePropertyReference1(Object receiver) {
        super(receiver);
    }

    protected KCallable computeReflected() {
        return Reflection.mutableProperty1(this);
    }

    public Object invoke(Object receiver) {
        return get(receiver);
    }

    public Getter getGetter() {
        return ((KMutableProperty1) getReflected()).getGetter();
    }

    public Setter getSetter() {
        return ((KMutableProperty1) getReflected()).getSetter();
    }

    @SinceKotlin(version = "1.1")
    public Object getDelegate(Object receiver) {
        return ((KMutableProperty1) getReflected()).getDelegate(receiver);
    }
}
