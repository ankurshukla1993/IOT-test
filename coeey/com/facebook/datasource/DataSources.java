package com.facebook.datasource;

import com.facebook.common.internal.Supplier;

public class DataSources {
    private DataSources() {
    }

    public static <T> DataSource<T> immediateFailedDataSource(Throwable failure) {
        SimpleDataSource<T> simpleDataSource = SimpleDataSource.create();
        simpleDataSource.setFailure(failure);
        return simpleDataSource;
    }

    public static <T> DataSource<T> immediateDataSource(T result) {
        SimpleDataSource<T> simpleDataSource = SimpleDataSource.create();
        simpleDataSource.setResult(result);
        return simpleDataSource;
    }

    public static <T> Supplier<DataSource<T>> getFailedDataSourceSupplier(final Throwable failure) {
        return new Supplier<DataSource<T>>() {
            public DataSource<T> get() {
                return DataSources.immediateFailedDataSource(failure);
            }
        };
    }
}
