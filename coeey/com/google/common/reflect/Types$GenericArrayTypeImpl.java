package com.google.common.reflect;

import com.google.common.base.Objects;
import java.io.Serializable;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;

final class Types$GenericArrayTypeImpl implements GenericArrayType, Serializable {
    private static final long serialVersionUID = 0;
    private final Type componentType;

    Types$GenericArrayTypeImpl(Type componentType) {
        this.componentType = Types$JavaVersion.CURRENT.usedInGenericType(componentType);
    }

    public Type getGenericComponentType() {
        return this.componentType;
    }

    public String toString() {
        return String.valueOf(Types.toString(this.componentType)).concat("[]");
    }

    public int hashCode() {
        return this.componentType.hashCode();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof GenericArrayType)) {
            return false;
        }
        return Objects.equal(getGenericComponentType(), ((GenericArrayType) obj).getGenericComponentType());
    }
}
