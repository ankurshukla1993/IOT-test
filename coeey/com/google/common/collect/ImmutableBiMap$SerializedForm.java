package com.google.common.collect;

class ImmutableBiMap$SerializedForm extends ImmutableMap$SerializedForm {
    private static final long serialVersionUID = 0;

    ImmutableBiMap$SerializedForm(ImmutableBiMap<?, ?> bimap) {
        super(bimap);
    }

    Object readResolve() {
        return createMap(new ImmutableBiMap$Builder());
    }
}
