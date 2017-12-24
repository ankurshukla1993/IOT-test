package com.thefinestartist.builders;

import android.os.Bundle;
import java.io.Serializable;

public class BundleBuilder {
    final Bundle bundle = new Bundle();

    public <T extends Serializable> BundleBuilder set(String key, T value) {
        this.bundle.putSerializable(key, value);
        return this;
    }

    public <T> T get(String key) {
        return this.bundle.getSerializable(key);
    }

    public Bundle build() {
        return this.bundle;
    }
}
