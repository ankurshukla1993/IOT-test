package com.google.common.reflect;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import javax.annotation.Nullable;
import kotlin.text.Typography;

final class Types$ParameterizedTypeImpl implements ParameterizedType, Serializable {
    private static final long serialVersionUID = 0;
    private final ImmutableList<Type> argumentsList;
    private final Type ownerType;
    private final Class<?> rawType;

    Types$ParameterizedTypeImpl(@Nullable Type ownerType, Class<?> rawType, Type[] typeArguments) {
        Preconditions.checkNotNull(rawType);
        Preconditions.checkArgument(typeArguments.length == rawType.getTypeParameters().length);
        Types.access$200(typeArguments, "type parameter");
        this.ownerType = ownerType;
        this.rawType = rawType;
        this.argumentsList = Types$JavaVersion.CURRENT.usedInGenericType(typeArguments);
    }

    public Type[] getActualTypeArguments() {
        return Types.access$300(this.argumentsList);
    }

    public Type getRawType() {
        return this.rawType;
    }

    public Type getOwnerType() {
        return this.ownerType;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        if (this.ownerType != null) {
            builder.append(Types$JavaVersion.CURRENT.typeName(this.ownerType)).append('.');
        }
        builder.append(this.rawType.getName()).append(Typography.less).append(Types.access$500().join(Iterables.transform(this.argumentsList, Types.access$400()))).append(Typography.greater);
        return builder.toString();
    }

    public int hashCode() {
        return ((this.ownerType == null ? 0 : this.ownerType.hashCode()) ^ this.argumentsList.hashCode()) ^ this.rawType.hashCode();
    }

    public boolean equals(Object other) {
        if (!(other instanceof ParameterizedType)) {
            return false;
        }
        ParameterizedType that = (ParameterizedType) other;
        if (getRawType().equals(that.getRawType()) && Objects.equal(getOwnerType(), that.getOwnerType()) && Arrays.equals(getActualTypeArguments(), that.getActualTypeArguments())) {
            return true;
        }
        return false;
    }
}
