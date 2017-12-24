package com.bumptech.glide;

import java.util.List;

public interface ListPreloader$PreloadModelProvider<U> {
    List<U> getPreloadItems(int i);

    GenericRequestBuilder getPreloadRequestBuilder(U u);
}
