package com.google.common.reflect;

import com.google.common.base.Joiner;
import java.lang.reflect.Constructor;
import java.lang.reflect.Type;

class TypeToken$2 extends ConstructorInvokable<T> {
    final /* synthetic */ TypeToken this$0;

    TypeToken$2(TypeToken typeToken, Constructor x0) {
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
        String valueOf2 = String.valueOf(String.valueOf(Joiner.on(", ").join(getGenericParameterTypes())));
        return new StringBuilder((valueOf.length() + 2) + valueOf2.length()).append(valueOf).append("(").append(valueOf2).append(")").toString();
    }
}
