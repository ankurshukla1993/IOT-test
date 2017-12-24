package com.bumptech.glide;

import java.util.List;

class ListPreloader$1 implements ListPreloader$PreloadModelProvider<T> {
    final /* synthetic */ ListPreloader this$0;

    ListPreloader$1(ListPreloader listPreloader) {
        this.this$0 = listPreloader;
    }

    public List<T> getPreloadItems(int position) {
        return this.this$0.getItems(position, position + 1);
    }

    public GenericRequestBuilder getPreloadRequestBuilder(T item) {
        return this.this$0.getRequestBuilder(item);
    }
}
