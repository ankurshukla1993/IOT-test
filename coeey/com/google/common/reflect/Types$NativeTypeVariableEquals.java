package com.google.common.reflect;

import java.lang.reflect.Type;

final class Types$NativeTypeVariableEquals<X> {
    static final boolean NATIVE_TYPE_VARIABLE_ONLY;

    Types$NativeTypeVariableEquals() {
    }

    static {
        boolean z = false;
        if (!Types$NativeTypeVariableEquals.class.getTypeParameters()[0].equals(Types.newArtificialTypeVariable(Types$NativeTypeVariableEquals.class, "X", new Type[0]))) {
            z = true;
        }
        NATIVE_TYPE_VARIABLE_ONLY = z;
    }
}
