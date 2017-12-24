package com.facebook.imagepipeline.datasource;

import com.facebook.common.executors.CallerThreadExecutor;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.AbstractDataSource;
import com.facebook.datasource.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CancellationException;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

public class ListDataSource<T> extends AbstractDataSource<List<CloseableReference<T>>> {
    private final DataSource<CloseableReference<T>>[] mDataSources;
    @GuardedBy("this")
    private int mFinishedDataSources = 0;

    protected ListDataSource(DataSource<CloseableReference<T>>[] dataSources) {
        this.mDataSources = dataSources;
    }

    public static <T> ListDataSource<T> create(DataSource<CloseableReference<T>>... dataSources) {
        boolean z;
        int i = 0;
        Preconditions.checkNotNull(dataSources);
        if (dataSources.length > 0) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.checkState(z);
        ListDataSource<T> listDataSource = new ListDataSource(dataSources);
        int length = dataSources.length;
        while (i < length) {
            DataSource<CloseableReference<T>> dataSource = dataSources[i];
            if (dataSource != null) {
                listDataSource.getClass();
                dataSource.subscribe(new InternalDataSubscriber(listDataSource, null), CallerThreadExecutor.getInstance());
            }
            i++;
        }
        return listDataSource;
    }

    @Nullable
    public synchronized List<CloseableReference<T>> getResult() {
        List<CloseableReference<T>> results;
        if (hasResult()) {
            results = new ArrayList(this.mDataSources.length);
            for (DataSource<CloseableReference<T>> dataSource : this.mDataSources) {
                results.add(dataSource.getResult());
            }
        } else {
            results = null;
        }
        return results;
    }

    public synchronized boolean hasResult() {
        boolean z;
        z = !isClosed() && this.mFinishedDataSources == this.mDataSources.length;
        return z;
    }

    public boolean close() {
        int i = 0;
        if (!super.close()) {
            return false;
        }
        DataSource[] dataSourceArr = this.mDataSources;
        int length = dataSourceArr.length;
        while (i < length) {
            dataSourceArr[i].close();
            i++;
        }
        return true;
    }

    private void onDataSourceFinished() {
        if (increaseAndCheckIfLast()) {
            setResult(null, true);
        }
    }

    private synchronized boolean increaseAndCheckIfLast() {
        int i;
        i = this.mFinishedDataSources + 1;
        this.mFinishedDataSources = i;
        return i == this.mDataSources.length;
    }

    private void onDataSourceFailed(DataSource<CloseableReference<T>> dataSource) {
        setFailure(dataSource.getFailureCause());
    }

    private void onDataSourceCancelled() {
        setFailure(new CancellationException());
    }

    private void onDataSourceProgress() {
        float progress = 0.0f;
        for (DataSource<?> dataSource : this.mDataSources) {
            progress += dataSource.getProgress();
        }
        setProgress(progress / ((float) this.mDataSources.length));
    }
}
