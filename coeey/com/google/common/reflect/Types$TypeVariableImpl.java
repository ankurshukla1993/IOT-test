package com.google.common.reflect;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

final class Types$TypeVariableImpl<D extends GenericDeclaration> implements TypeVariable<D> {
    private final ImmutableList<Type> bounds;
    private final D genericDeclaration;
    private final String name;

    Types$TypeVariableImpl(D genericDeclaration, String name, Type[] bounds) {
        Types.access$200(bounds, "bound for type variable");
        this.genericDeclaration = (GenericDeclaration) Preconditions.checkNotNull(genericDeclaration);
        this.name = (String) Preconditions.checkNotNull(name);
        this.bounds = ImmutableList.copyOf(bounds);
    }

    public Type[] getBounds() {
        return Types.access$300(this.bounds);
    }

    public D getGenericDeclaration() {
        return this.genericDeclaration;
    }

    public String getName() {
        return this.name;
    }

    public String toString() {
        return this.name;
    }

    public int hashCode() {
        return this.genericDeclaration.hashCode() ^ this.name.hashCode();
    }

    public boolean equals(Object obj) {
        if (Types$NativeTypeVariableEquals.NATIVE_TYPE_VARIABLE_ONLY) {
            if (!(obj instanceof Types$TypeVariableImpl)) {
                return false;
            }
            Types$TypeVariableImpl<?> that = (Types$TypeVariableImpl) obj;
            if (this.name.equals(that.getName()) && this.genericDeclaration.equals(that.getGenericDeclaration()) && this.bounds.equals(that.bounds)) {
                return true;
            }
            return false;
        } else if (!(obj instanceof TypeVariable)) {
            return false;
        } else {
            TypeVariable<?> that2 = (TypeVariable) obj;
            if (this.name.equals(that2.getName()) && this.genericDeclaration.equals(that2.getGenericDeclaration())) {
                return true;
            }
            return false;
        }
    }
}
