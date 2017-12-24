package com.facebook.datasource;

import com.facebook.common.internal.Preconditions;

public class SimpleDataSource<T> extends AbstractDataSource<T> {
    private SimpleDataSource() {
    }

    public static <T> SimpleDataSource<T> create() {
        return new SimpleDataSource();
    }

    public boolean setResult(T value, boolean isLast) {
        return super.setResult(Preconditions.checkNotNull(value), isLast);
    }

    public boolean setResult(T value) {
        return super.setResult(Preconditions.checkNotNull(value), true);
    }

    public boolean setFailure(Throwable throwable) {
        return super.setFailure((Throwable) Preconditions.checkNotNull(throwable));
    }

    public boolean setProgress(float progress) {
        return super.setProgress(progress);
    }
}
