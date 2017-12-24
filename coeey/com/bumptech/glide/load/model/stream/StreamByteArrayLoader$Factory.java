package com.bumptech.glide.load.model.stream;

import android.content.Context;
import com.bumptech.glide.load.model.GenericLoaderFactory;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.model.ModelLoaderFactory;
import java.io.InputStream;

public class StreamByteArrayLoader$Factory implements ModelLoaderFactory<byte[], InputStream> {
    public ModelLoader<byte[], InputStream> build(Context context, GenericLoaderFactory factories) {
        return new StreamByteArrayLoader();
    }

    public void teardown() {
    }
}
