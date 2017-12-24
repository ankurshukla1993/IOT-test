package com.facebook.datasource;

class AbstractDataSource$2 implements Runnable {
    final /* synthetic */ AbstractDataSource this$0;
    final /* synthetic */ DataSubscriber val$subscriber;

    AbstractDataSource$2(AbstractDataSource this$0, DataSubscriber dataSubscriber) {
        this.this$0 = this$0;
        this.val$subscriber = dataSubscriber;
    }

    public void run() {
        this.val$subscriber.onProgressUpdate(this.this$0);
    }
}
