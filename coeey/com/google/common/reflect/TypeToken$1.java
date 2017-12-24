package com.google.common.reflect;

import java.lang.reflect.Method;
import java.lang.reflect.Type;

class TypeToken$1 extends MethodInvokable<T> {
    final /* synthetic */ TypeToken this$0;

    TypeToken$1(TypeToken typeToken, Method x0) {
        this.this$0 = typeToken;
        super(x0);
    }

    Type getGenericReturnType() {
        return this.this$0.resolveType(super.getGenericReturnType()).getType();
    }

    Type[] getGenericParameterTypes() {
        return TypeToken.access$000(this.this$0, super.getGenericParameterTypes());
    }

    Type[] getGenericExceptionTypes() {
        return TypeToken.access$000(this.this$0, super.getGenericExceptionTypes());
    }

    public TypeToken<T> getOwnerType() {
        return this.this$0;
    }

    public String toString() {
        String valueOf = String.valueOf(String.valueOf(getOwnerType()));
        String valueOf2 = String.valueOf(String.valueOf(super.toString()));
        return new StringBuilder((valueOf.length() + 1) + valueOf2.length()).append(valueOf).append(".").append(valueOf2).toString();
    }
}
