package com.bumptech.glide;

class ListPreloader$2 implements ListPreloader$PreloadSizeProvider<T> {
    final /* synthetic */ ListPreloader this$0;

    ListPreloader$2(ListPreloader listPreloader) {
        this.this$0 = listPreloader;
    }

    public int[] getPreloadSize(T item, int adapterPosition, int perItemPosition) {
        return this.this$0.getDimensions(item);
    }
}
