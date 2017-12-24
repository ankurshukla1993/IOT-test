package com.facebook.imagepipeline.cache;

import com.facebook.cache.common.CacheKey;
import com.facebook.common.internal.Supplier;
import com.facebook.common.memory.MemoryTrimmableRegistry;
import com.facebook.imagepipeline.image.CloseableImage;

public class BitmapCountingMemoryCacheFactory {

    static class C11461 implements ValueDescriptor<CloseableImage> {
        C11461() {
        }

        public int getSizeInBytes(CloseableImage value) {
            return value.getSizeInBytes();
        }
    }

    public static CountingMemoryCache<CacheKey, CloseableImage> get(Supplier<MemoryCacheParams> bitmapMemoryCacheParamsSupplier, MemoryTrimmableRegistry memoryTrimmableRegistry) {
        CountingMemoryCache<CacheKey, CloseableImage> countingCache = new CountingMemoryCache(new C11461(), new BitmapMemoryCacheTrimStrategy(), bitmapMemoryCacheParamsSupplier);
        memoryTrimmableRegistry.registerMemoryTrimmable(countingCache);
        return countingCache;
    }
}
