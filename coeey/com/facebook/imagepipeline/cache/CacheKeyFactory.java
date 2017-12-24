package com.facebook.imagepipeline.cache;

import com.facebook.cache.common.CacheKey;
import com.facebook.imagepipeline.request.ImageRequest;
import javax.annotation.Nullable;

public interface CacheKeyFactory {
    CacheKey getBitmapCacheKey(ImageRequest imageRequest, Object obj);

    CacheKey getEncodedCacheKey(ImageRequest imageRequest, @Nullable Object obj);

    CacheKey getPostprocessedBitmapCacheKey(ImageRequest imageRequest, Object obj);
}
