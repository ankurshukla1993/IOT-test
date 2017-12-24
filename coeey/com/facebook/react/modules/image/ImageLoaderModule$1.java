package com.facebook.react.modules.image;

import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.BaseDataSubscriber;
import com.facebook.datasource.DataSource;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.WritableMap;

class ImageLoaderModule$1 extends BaseDataSubscriber<CloseableReference<CloseableImage>> {
    final /* synthetic */ ImageLoaderModule this$0;
    final /* synthetic */ Promise val$promise;

    ImageLoaderModule$1(ImageLoaderModule this$0, Promise promise) {
        this.this$0 = this$0;
        this.val$promise = promise;
    }

    protected void onNewResultImpl(DataSource<CloseableReference<CloseableImage>> dataSource) {
        if (dataSource.isFinished()) {
            CloseableReference<CloseableImage> ref = (CloseableReference) dataSource.getResult();
            if (ref != null) {
                try {
                    CloseableImage image = (CloseableImage) ref.get();
                    WritableMap sizes = Arguments.createMap();
                    sizes.putInt("width", image.getWidth());
                    sizes.putInt("height", image.getHeight());
                    this.val$promise.resolve(sizes);
                } catch (Exception e) {
                    this.val$promise.reject("E_GET_SIZE_FAILURE", e);
                } finally {
                    CloseableReference.closeSafely(ref);
                }
            } else {
                this.val$promise.reject("E_GET_SIZE_FAILURE");
            }
        }
    }

    protected void onFailureImpl(DataSource<CloseableReference<CloseableImage>> dataSource) {
        this.val$promise.reject("E_GET_SIZE_FAILURE", dataSource.getFailureCause());
    }
}
