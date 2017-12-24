package com.facebook.datasource;

import com.facebook.datasource.FirstAvailableDataSourceSupplier.FirstAvailableDataSource;

class C1130xc47c469b implements DataSubscriber<T> {
    final /* synthetic */ FirstAvailableDataSource this$1;

    private C1130xc47c469b(FirstAvailableDataSource firstAvailableDataSource) {
        this.this$1 = firstAvailableDataSource;
    }

    public void onFailure(DataSource<T> dataSource) {
        FirstAvailableDataSource.access$200(this.this$1, dataSource);
    }

    public void onCancellation(DataSource<T> dataSource) {
    }

    public void onNewResult(DataSource<T> dataSource) {
        if (dataSource.hasResult()) {
            FirstAvailableDataSource.access$300(this.this$1, dataSource);
        } else if (dataSource.isFinished()) {
            FirstAvailableDataSource.access$200(this.this$1, dataSource);
        }
    }

    public void onProgressUpdate(DataSource<T> dataSource) {
        this.this$1.setProgress(Math.max(this.this$1.getProgress(), dataSource.getProgress()));
    }
}
