package com.facebook.drawee.controller;

import com.facebook.datasource.BaseDataSubscriber;
import com.facebook.datasource.DataSource;

class AbstractDraweeController$1 extends BaseDataSubscriber<T> {
    final /* synthetic */ AbstractDraweeController this$0;
    final /* synthetic */ String val$id;
    final /* synthetic */ boolean val$wasImmediate;

    AbstractDraweeController$1(AbstractDraweeController this$0, String str, boolean z) {
        this.this$0 = this$0;
        this.val$id = str;
        this.val$wasImmediate = z;
    }

    public void onNewResultImpl(DataSource<T> dataSource) {
        boolean isFinished = dataSource.isFinished();
        float progress = dataSource.getProgress();
        T image = dataSource.getResult();
        if (image != null) {
            AbstractDraweeController.access$000(this.this$0, this.val$id, dataSource, image, progress, isFinished, this.val$wasImmediate);
        } else if (isFinished) {
            AbstractDraweeController.access$100(this.this$0, this.val$id, dataSource, new NullPointerException(), true);
        }
    }

    public void onFailureImpl(DataSource<T> dataSource) {
        AbstractDraweeController.access$100(this.this$0, this.val$id, dataSource, dataSource.getFailureCause(), true);
    }

    public void onProgressUpdate(DataSource<T> dataSource) {
        boolean isFinished = dataSource.isFinished();
        AbstractDraweeController.access$200(this.this$0, this.val$id, dataSource, dataSource.getProgress(), isFinished);
    }
}
