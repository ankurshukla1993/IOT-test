package com.google.common.reflect;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableList$Builder;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;

enum Types$JavaVersion {
    JAVA6 {
        GenericArrayType newArrayType(Type componentType) {
            return new Types$GenericArrayTypeImpl(componentType);
        }

        Type usedInGenericType(Type type) {
            Preconditions.checkNotNull(type);
            if (!(type instanceof Class)) {
                return type;
            }
            Class<?> cls = (Class) type;
            if (cls.isArray()) {
                return new Types$GenericArrayTypeImpl(cls.getComponentType());
            }
            return type;
        }
    },
    JAVA7 {
        Type newArrayType(Type componentType) {
            if (componentType instanceof Class) {
                return Types.getArrayClass((Class) componentType);
            }
            return new Types$GenericArrayTypeImpl(componentType);
        }

        Type usedInGenericType(Type type) {
            return (Type) Preconditions.checkNotNull(type);
        }
    },
    JAVA8 {
        Type newArrayType(Type componentType) {
            return JAVA7.newArrayType(componentType);
        }

        Type usedInGenericType(Type type) {
            return JAVA7.usedInGenericType(type);
        }

        String typeName(Type type) {
            try {
                return (String) Type.class.getMethod("getTypeName", new Class[0]).invoke(type, new Object[0]);
            } catch (NoSuchMethodException e) {
                throw new AssertionError("Type.getTypeName should be available in Java 8");
            } catch (InvocationTargetException e2) {
                throw new RuntimeException(e2);
            } catch (IllegalAccessException e3) {
                throw new RuntimeException(e3);
            }
        }
    };
    
    static final Types$JavaVersion CURRENT = null;

    static class C18194 extends TypeCapture<int[]> {
        C18194() {
        }
    }

    abstract Type newArrayType(Type type);

    abstract Type usedInGenericType(Type type);

    String typeName(Type type) {
        return Types.toString(type);
    }

    final ImmutableList<Type> usedInGenericType(Type[] types) {
        ImmutableList$Builder<Type> builder = ImmutableList.builder();
        for (Type type : types) {
            builder.add(usedInGenericType(type));
        }
        return builder.build();
    }
}
