package com.google.common.reflect;

import com.google.common.base.Predicate;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;

enum TypeToken$TypeFilter implements Predicate<TypeToken<?>> {
    IGNORE_TYPE_VARIABLE_OR_WILDCARD {
        public boolean apply(TypeToken<?> type) {
            return ((TypeToken.access$400(type) instanceof TypeVariable) || (TypeToken.access$400(type) instanceof WildcardType)) ? false : true;
        }
    },
    INTERFACE_ONLY {
        public boolean apply(TypeToken<?> type) {
            return type.getRawType().isInterface();
        }
    }
}
