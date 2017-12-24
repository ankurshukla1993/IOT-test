package com.facebook.datasource;

import com.facebook.common.executors.CallerThreadExecutor;
import com.facebook.common.internal.Supplier;
import java.util.ArrayList;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
class IncreasingQualityDataSourceSupplier$IncreasingQualityDataSource extends AbstractDataSource<T> {
    @GuardedBy("IncreasingQualityDataSource.this")
    @Nullable
    private ArrayList<DataSource<T>> mDataSources;
    @GuardedBy("IncreasingQualityDataSource.this")
    private int mIndexOfDataSourceWithResult;
    final /* synthetic */ IncreasingQualityDataSourceSupplier this$0;

    public IncreasingQualityDataSourceSupplier$IncreasingQualityDataSource(IncreasingQualityDataSourceSupplier increasingQualityDataSourceSupplier) {
        this.this$0 = increasingQualityDataSourceSupplier;
        int n = IncreasingQualityDataSourceSupplier.access$000(increasingQualityDataSourceSupplier).size();
        this.mIndexOfDataSourceWithResult = n;
        this.mDataSources = new ArrayList(n);
        int i = 0;
        while (i < n) {
            DataSource<T> dataSource = (DataSource) ((Supplier) IncreasingQualityDataSourceSupplier.access$000(increasingQualityDataSourceSupplier).get(i)).get();
            this.mDataSources.add(dataSource);
            dataSource.subscribe(new InternalDataSubscriber(this, i), CallerThreadExecutor.getInstance());
            if (!dataSource.hasResult()) {
                i++;
            } else {
                return;
            }
        }
    }

    @Nullable
    private synchronized DataSource<T> getDataSource(int i) {
        DataSource<T> dataSource;
        dataSource = (this.mDataSources == null || i >= this.mDataSources.size()) ? null : (DataSource) this.mDataSources.get(i);
        return dataSource;
    }

    @Nullable
    private synchronized DataSource<T> getAndClearDataSource(int i) {
        DataSource<T> dataSource = null;
        synchronized (this) {
            if (this.mDataSources != null && i < this.mDataSources.size()) {
                dataSource = (DataSource) this.mDataSources.set(i, null);
            }
        }
        return dataSource;
    }

    @Nullable
    private synchronized DataSource<T> getDataSourceWithResult() {
        return getDataSource(this.mIndexOfDataSourceWithResult);
    }

    @Nullable
    public synchronized T getResult() {
        DataSource<T> dataSourceWithResult;
        dataSourceWithResult = getDataSourceWithResult();
        return dataSourceWithResult != null ? dataSourceWithResult.getResult() : null;
    }

    public synchronized boolean hasResult() {
        boolean z;
        DataSource<T> dataSourceWithResult = getDataSourceWithResult();
        z = dataSourceWithResult != null && dataSourceWithResult.hasResult();
        return z;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean close() {
        /*
        r3 = this;
        monitor-enter(r3);
        r2 = super.close();	 Catch:{ all -> 0x0025 }
        if (r2 != 0) goto L_0x000a;
    L_0x0007:
        r2 = 0;
        monitor-exit(r3);	 Catch:{ all -> 0x0025 }
    L_0x0009:
        return r2;
    L_0x000a:
        r0 = r3.mDataSources;	 Catch:{ all -> 0x0025 }
        r2 = 0;
        r3.mDataSources = r2;	 Catch:{ all -> 0x0025 }
        monitor-exit(r3);	 Catch:{ all -> 0x0025 }
        if (r0 == 0) goto L_0x0028;
    L_0x0012:
        r1 = 0;
    L_0x0013:
        r2 = r0.size();
        if (r1 >= r2) goto L_0x0028;
    L_0x0019:
        r2 = r0.get(r1);
        r2 = (com.facebook.datasource.DataSource) r2;
        r3.closeSafely(r2);
        r1 = r1 + 1;
        goto L_0x0013;
    L_0x0025:
        r2 = move-exception;
        monitor-exit(r3);	 Catch:{ all -> 0x0025 }
        throw r2;
    L_0x0028:
        r2 = 1;
        goto L_0x0009;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.datasource.IncreasingQualityDataSourceSupplier$IncreasingQualityDataSource.close():boolean");
    }

    private void onDataSourceNewResult(int index, DataSource<T> dataSource) {
        maybeSetIndexOfDataSourceWithResult(index, dataSource, dataSource.isFinished());
        if (dataSource == getDataSourceWithResult()) {
            boolean z = index == 0 && dataSource.isFinished();
            setResult(null, z);
        }
    }

    private void onDataSourceFailed(int index, DataSource<T> dataSource) {
        closeSafely(tryGetAndClearDataSource(index, dataSource));
        if (index == 0) {
            setFailure(dataSource.getFailureCause());
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void maybeSetIndexOfDataSourceWithResult(int r5, com.facebook.datasource.DataSource<T> r6, boolean r7) {
        /*
        r4 = this;
        monitor-enter(r4);
        r2 = r4.mIndexOfDataSourceWithResult;	 Catch:{ all -> 0x002e }
        r1 = r4.mIndexOfDataSourceWithResult;	 Catch:{ all -> 0x002e }
        r3 = r4.getDataSource(r5);	 Catch:{ all -> 0x002e }
        if (r6 != r3) goto L_0x000f;
    L_0x000b:
        r3 = r4.mIndexOfDataSourceWithResult;	 Catch:{ all -> 0x002e }
        if (r5 != r3) goto L_0x0011;
    L_0x000f:
        monitor-exit(r4);	 Catch:{ all -> 0x002e }
    L_0x0010:
        return;
    L_0x0011:
        r3 = r4.getDataSourceWithResult();	 Catch:{ all -> 0x002e }
        if (r3 == 0) goto L_0x001d;
    L_0x0017:
        if (r7 == 0) goto L_0x0020;
    L_0x0019:
        r3 = r4.mIndexOfDataSourceWithResult;	 Catch:{ all -> 0x002e }
        if (r5 >= r3) goto L_0x0020;
    L_0x001d:
        r1 = r5;
        r4.mIndexOfDataSourceWithResult = r5;	 Catch:{ all -> 0x002e }
    L_0x0020:
        monitor-exit(r4);	 Catch:{ all -> 0x002e }
        r0 = r2;
    L_0x0022:
        if (r0 <= r1) goto L_0x0010;
    L_0x0024:
        r3 = r4.getAndClearDataSource(r0);
        r4.closeSafely(r3);
        r0 = r0 + -1;
        goto L_0x0022;
    L_0x002e:
        r3 = move-exception;
        monitor-exit(r4);	 Catch:{ all -> 0x002e }
        throw r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.datasource.IncreasingQualityDataSourceSupplier$IncreasingQualityDataSource.maybeSetIndexOfDataSourceWithResult(int, com.facebook.datasource.DataSource, boolean):void");
    }

    @Nullable
    private synchronized DataSource<T> tryGetAndClearDataSource(int i, DataSource<T> dataSource) {
        if (dataSource == getDataSourceWithResult()) {
            dataSource = null;
        } else if (dataSource == getDataSource(i)) {
            dataSource = getAndClearDataSource(i);
        }
        return dataSource;
    }

    private void closeSafely(DataSource<T> dataSource) {
        if (dataSource != null) {
            dataSource.close();
        }
    }
}
