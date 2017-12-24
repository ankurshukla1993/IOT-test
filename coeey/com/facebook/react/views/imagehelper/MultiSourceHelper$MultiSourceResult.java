package com.facebook.react.views.imagehelper;

import javax.annotation.Nullable;

public class MultiSourceHelper$MultiSourceResult {
    @Nullable
    private final ImageSource bestResult;
    @Nullable
    private final ImageSource bestResultInCache;

    private MultiSourceHelper$MultiSourceResult(@Nullable ImageSource bestResult, @Nullable ImageSource bestResultInCache) {
        this.bestResult = bestResult;
        this.bestResultInCache = bestResultInCache;
    }

    @Nullable
    public ImageSource getBestResult() {
        return this.bestResult;
    }

    @Nullable
    public ImageSource getBestResultInCache() {
        return this.bestResultInCache;
    }
}
