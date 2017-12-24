package com.facebook.common.references;

import java.lang.ref.SoftReference;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class OOMSoftReference<T> {
    SoftReference<T> softRef1 = null;
    SoftReference<T> softRef2 = null;
    SoftReference<T> softRef3 = null;

    public void set(@Nonnull T hardReference) {
        this.softRef1 = new SoftReference(hardReference);
        this.softRef2 = new SoftReference(hardReference);
        this.softRef3 = new SoftReference(hardReference);
    }

    @Nullable
    public T get() {
        return this.softRef1 == null ? null : this.softRef1.get();
    }

    public void clear() {
        if (this.softRef1 != null) {
            this.softRef1.clear();
            this.softRef1 = null;
        }
        if (this.softRef2 != null) {
            this.softRef2.clear();
            this.softRef2 = null;
        }
        if (this.softRef3 != null) {
            this.softRef3.clear();
            this.softRef3 = null;
        }
    }
}
