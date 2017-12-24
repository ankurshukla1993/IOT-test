package com.facebook.react.modules.image;

import com.facebook.datasource.BaseDataSubscriber;
import com.facebook.datasource.DataSource;
import com.facebook.react.bridge.Promise;

class ImageLoaderModule$2 extends BaseDataSubscriber<Void> {
    final /* synthetic */ ImageLoaderModule this$0;
    final /* synthetic */ Promise val$promise;
    final /* synthetic */ int val$requestId;

    ImageLoaderModule$2(ImageLoaderModule this$0, int i, Promise promise) {
        this.this$0 = this$0;
        this.val$requestId = i;
        this.val$promise = promise;
    }

    protected void onNewResultImpl(DataSource<Void> dataSource) {
        if (dataSource.isFinished()) {
            try {
                ImageLoaderModule.access$000(this.this$0, this.val$requestId);
                this.val$promise.resolve(Boolean.valueOf(true));
            } finally {
                dataSource.close();
            }
        }
    }

    protected void onFailureImpl(DataSource<Void> dataSource) {
        try {
            ImageLoaderModule.access$000(this.this$0, this.val$requestId);
            this.val$promise.reject("E_PREFETCH_FAILURE", dataSource.getFailureCause());
        } finally {
            dataSource.close();
        }
    }
}
