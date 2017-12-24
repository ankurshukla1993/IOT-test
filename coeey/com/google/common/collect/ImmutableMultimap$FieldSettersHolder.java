package com.google.common.collect;

import com.google.common.annotations.GwtIncompatible;

@GwtIncompatible("java serialization is not supported")
class ImmutableMultimap$FieldSettersHolder {
    static final FieldSetter<ImmutableSetMultimap> EMPTY_SET_FIELD_SETTER = Serialization.getFieldSetter(ImmutableSetMultimap.class, "emptySet");
    static final FieldSetter<ImmutableMultimap> MAP_FIELD_SETTER = Serialization.getFieldSetter(ImmutableMultimap.class, "map");
    static final FieldSetter<ImmutableMultimap> SIZE_FIELD_SETTER = Serialization.getFieldSetter(ImmutableMultimap.class, "size");

    ImmutableMultimap$FieldSettersHolder() {
    }
}
