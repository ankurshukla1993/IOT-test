package com.google.common.collect;

import com.google.common.base.Converter;
import com.google.common.base.Preconditions;
import java.io.Serializable;
import javax.annotation.Nullable;

final class Maps$BiMapConverter<A, B> extends Converter<A, B> implements Serializable {
    private static final long serialVersionUID = 0;
    private final BiMap<A, B> bimap;

    Maps$BiMapConverter(BiMap<A, B> bimap) {
        this.bimap = (BiMap) Preconditions.checkNotNull(bimap);
    }

    protected B doForward(A a) {
        return convert(this.bimap, a);
    }

    protected A doBackward(B b) {
        return convert(this.bimap.inverse(), b);
    }

    private static <X, Y> Y convert(BiMap<X, Y> bimap, X input) {
        boolean z;
        Y output = bimap.get(input);
        if (output != null) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.checkArgument(z, "No non-null mapping present for input: %s", input);
        return output;
    }

    public boolean equals(@Nullable Object object) {
        if (!(object instanceof Maps$BiMapConverter)) {
            return false;
        }
        return this.bimap.equals(((Maps$BiMapConverter) object).bimap);
    }

    public int hashCode() {
        return this.bimap.hashCode();
    }

    public String toString() {
        String valueOf = String.valueOf(String.valueOf(this.bimap));
        return new StringBuilder(valueOf.length() + 18).append("Maps.asConverter(").append(valueOf).append(")").toString();
    }
}
