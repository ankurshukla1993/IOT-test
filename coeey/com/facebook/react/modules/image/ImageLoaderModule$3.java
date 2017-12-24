package com.facebook.react.modules.image;

import android.net.Uri;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.GuardedAsyncTask;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.WritableMap;

class ImageLoaderModule$3 extends GuardedAsyncTask<Void, Void> {
    final /* synthetic */ ImageLoaderModule this$0;
    final /* synthetic */ Promise val$promise;
    final /* synthetic */ ReadableArray val$uris;

    ImageLoaderModule$3(ImageLoaderModule this$0, ReactContext reactContext, ReadableArray readableArray, Promise promise) {
        this.this$0 = this$0;
        this.val$uris = readableArray;
        this.val$promise = promise;
        super(reactContext);
    }

    protected void doInBackgroundGuarded(Void... params) {
        WritableMap result = Arguments.createMap();
        ImagePipeline imagePipeline = Fresco.getImagePipeline();
        for (int i = 0; i < this.val$uris.size(); i++) {
            String uriString = this.val$uris.getString(i);
            Uri uri = Uri.parse(uriString);
            if (imagePipeline.isInBitmapMemoryCache(uri)) {
                result.putString(uriString, "memory");
            } else if (imagePipeline.isInDiskCacheSync(uri)) {
                result.putString(uriString, "disk");
            }
        }
        this.val$promise.resolve(result);
    }
}
