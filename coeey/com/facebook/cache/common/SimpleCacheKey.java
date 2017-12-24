package com.facebook.cache.common;

import android.net.Uri;
import com.facebook.common.internal.Preconditions;

public class SimpleCacheKey implements CacheKey {
    final String mKey;

    public SimpleCacheKey(String key) {
        this.mKey = (String) Preconditions.checkNotNull(key);
    }

    public String toString() {
        return this.mKey;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof SimpleCacheKey)) {
            return false;
        }
        return this.mKey.equals(((SimpleCacheKey) o).mKey);
    }

    public int hashCode() {
        return this.mKey.hashCode();
    }

    public boolean containsUri(Uri uri) {
        return this.mKey.contains(uri.toString());
    }
}
