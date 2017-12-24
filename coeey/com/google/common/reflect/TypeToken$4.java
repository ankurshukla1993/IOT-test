package com.google.common.reflect;

import com.google.common.collect.ImmutableSet.Builder;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;

class TypeToken$4 extends TypeVisitor {
    final /* synthetic */ Builder val$builder;

    TypeToken$4(Builder builder) {
        this.val$builder = builder;
    }

    void visitTypeVariable(TypeVariable<?> t) {
        visit(t.getBounds());
    }

    void visitWildcardType(WildcardType t) {
        visit(t.getUpperBounds());
    }

    void visitParameterizedType(ParameterizedType t) {
        this.val$builder.add((Class) t.getRawType());
    }

    void visitClass(Class<?> t) {
        this.val$builder.add((Object) t);
    }

    void visitGenericArrayType(GenericArrayType t) {
        this.val$builder.add(Types.getArrayClass(TypeToken.getRawType(t.getGenericComponentType())));
    }
}
