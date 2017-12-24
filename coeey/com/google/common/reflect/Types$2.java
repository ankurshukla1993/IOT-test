package com.google.common.reflect;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.concurrent.atomic.AtomicReference;

class Types$2 extends TypeVisitor {
    final /* synthetic */ AtomicReference val$result;

    Types$2(AtomicReference atomicReference) {
        this.val$result = atomicReference;
    }

    void visitTypeVariable(TypeVariable<?> t) {
        this.val$result.set(Types.access$100(t.getBounds()));
    }

    void visitWildcardType(WildcardType t) {
        this.val$result.set(Types.access$100(t.getUpperBounds()));
    }

    void visitGenericArrayType(GenericArrayType t) {
        this.val$result.set(t.getGenericComponentType());
    }

    void visitClass(Class<?> t) {
        this.val$result.set(t.getComponentType());
    }
}
