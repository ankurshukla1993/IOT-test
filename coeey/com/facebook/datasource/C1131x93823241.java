package com.facebook.datasource;

import com.facebook.datasource.IncreasingQualityDataSourceSupplier.IncreasingQualityDataSource;

class C1131x93823241 implements DataSubscriber<T> {
    private int mIndex;
    final /* synthetic */ IncreasingQualityDataSource this$1;

    public C1131x93823241(IncreasingQualityDataSource increasingQualityDataSource, int index) {
        this.this$1 = increasingQualityDataSource;
        this.mIndex = index;
    }

    public void onNewResult(DataSource<T> dataSource) {
        if (dataSource.hasResult()) {
            IncreasingQualityDataSource.access$100(this.this$1, this.mIndex, dataSource);
        } else if (dataSource.isFinished()) {
            IncreasingQualityDataSource.access$200(this.this$1, this.mIndex, dataSource);
        }
    }

    public void onFailure(DataSource<T> dataSource) {
        IncreasingQualityDataSource.access$200(this.this$1, this.mIndex, dataSource);
    }

    public void onCancellation(DataSource<T> dataSource) {
    }

    public void onProgressUpdate(DataSource<T> dataSource) {
        if (this.mIndex == 0) {
            this.this$1.setProgress(dataSource.getProgress());
        }
    }
}
