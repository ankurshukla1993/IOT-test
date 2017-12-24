package com.google.common.reflect;

import com.google.common.collect.ImmutableList;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.Arrays;
import java.util.Iterator;

final class Types$WildcardTypeImpl implements WildcardType, Serializable {
    private static final long serialVersionUID = 0;
    private final ImmutableList<Type> lowerBounds;
    private final ImmutableList<Type> upperBounds;

    Types$WildcardTypeImpl(Type[] lowerBounds, Type[] upperBounds) {
        Types.access$200(lowerBounds, "lower bound for wildcard");
        Types.access$200(upperBounds, "upper bound for wildcard");
        this.lowerBounds = Types$JavaVersion.CURRENT.usedInGenericType(lowerBounds);
        this.upperBounds = Types$JavaVersion.CURRENT.usedInGenericType(upperBounds);
    }

    public Type[] getLowerBounds() {
        return Types.access$300(this.lowerBounds);
    }

    public Type[] getUpperBounds() {
        return Types.access$300(this.upperBounds);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof WildcardType)) {
            return false;
        }
        WildcardType that = (WildcardType) obj;
        if (this.lowerBounds.equals(Arrays.asList(that.getLowerBounds())) && this.upperBounds.equals(Arrays.asList(that.getUpperBounds()))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return this.lowerBounds.hashCode() ^ this.upperBounds.hashCode();
    }

    public String toString() {
        StringBuilder builder = new StringBuilder("?");
        Iterator i$ = this.lowerBounds.iterator();
        while (i$.hasNext()) {
            builder.append(" super ").append(Types$JavaVersion.CURRENT.typeName((Type) i$.next()));
        }
        for (Type upperBound : Types.access$600(this.upperBounds)) {
            builder.append(" extends ").append(Types$JavaVersion.CURRENT.typeName(upperBound));
        }
        return builder.toString();
    }
}
