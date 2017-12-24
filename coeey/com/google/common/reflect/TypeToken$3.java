package com.google.common.reflect;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;

class TypeToken$3 extends TypeVisitor {
    final /* synthetic */ TypeToken this$0;

    TypeToken$3(TypeToken typeToken) {
        this.this$0 = typeToken;
    }

    void visitTypeVariable(TypeVariable<?> typeVariable) {
        String valueOf = String.valueOf(String.valueOf(TypeToken.access$400(this.this$0)));
        throw new IllegalArgumentException(new StringBuilder(valueOf.length() + 58).append(valueOf).append("contains a type variable and is not safe for the operation").toString());
    }

    void visitWildcardType(WildcardType type) {
        visit(type.getLowerBounds());
        visit(type.getUpperBounds());
    }

    void visitParameterizedType(ParameterizedType type) {
        visit(type.getActualTypeArguments());
        visit(type.getOwnerType());
    }

    void visitGenericArrayType(GenericArrayType type) {
        visit(type.getGenericComponentType());
    }
}
