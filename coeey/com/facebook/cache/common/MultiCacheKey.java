package com.facebook.cache.common;

import android.net.Uri;
import com.facebook.common.internal.Preconditions;
import java.util.List;

public class MultiCacheKey implements CacheKey {
    final List<CacheKey> mCacheKeys;

    public MultiCacheKey(List<CacheKey> cacheKeys) {
        this.mCacheKeys = (List) Preconditions.checkNotNull(cacheKeys);
    }

    public List<CacheKey> getCacheKeys() {
        return this.mCacheKeys;
    }

    public String toString() {
        return "MultiCacheKey:" + this.mCacheKeys.toString();
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof MultiCacheKey)) {
            return false;
        }
        return this.mCacheKeys.equals(((MultiCacheKey) o).mCacheKeys);
    }

    public int hashCode() {
        return this.mCacheKeys.hashCode();
    }

    public boolean containsUri(Uri uri) {
        for (int i = 0; i < this.mCacheKeys.size(); i++) {
            if (((CacheKey) this.mCacheKeys.get(i)).containsUri(uri)) {
                return true;
            }
        }
        return false;
    }
}
