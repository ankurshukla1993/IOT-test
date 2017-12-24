package com.google.common.base;

import javax.annotation.Nullable;

enum Functions$IdentityFunction implements Function<Object, Object> {
    INSTANCE;

    @Nullable
    public Object apply(@Nullable Object o) {
        return o;
    }

    public String toString() {
        return InterpolationAnimatedNode.EXTRAPOLATE_TYPE_IDENTITY;
    }
}
