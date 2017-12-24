package com.facebook.imagepipeline.cache;

import com.facebook.cache.common.CacheKey;
import com.facebook.common.internal.Supplier;
import com.facebook.common.memory.MemoryTrimmableRegistry;
import com.facebook.imagepipeline.memory.PooledByteBuffer;

public class EncodedCountingMemoryCacheFactory {

    static class C11551 implements ValueDescriptor<PooledByteBuffer> {
        C11551() {
        }

        public int getSizeInBytes(PooledByteBuffer value) {
            return value.size();
        }
    }

    public static CountingMemoryCache<CacheKey, PooledByteBuffer> get(Supplier<MemoryCacheParams> encodedMemoryCacheParamsSupplier, MemoryTrimmableRegistry memoryTrimmableRegistry) {
        CountingMemoryCache<CacheKey, PooledByteBuffer> countingCache = new CountingMemoryCache(new C11551(), new NativeMemoryCacheTrimStrategy(), encodedMemoryCacheParamsSupplier);
        memoryTrimmableRegistry.registerMemoryTrimmable(countingCache);
        return countingCache;
    }
}
