package com.facebook.imagepipeline.datasource;

import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.AbstractDataSource;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public final class SettableDataSource<T> extends AbstractDataSource<CloseableReference<T>> {
    public static <V> SettableDataSource<V> create() {
        return new SettableDataSource();
    }

    private SettableDataSource() {
    }

    public boolean set(@Nullable CloseableReference<T> valueRef) {
        return super.setResult(CloseableReference.cloneOrNull((CloseableReference) valueRef), true);
    }

    public boolean setException(Throwable throwable) {
        return super.setFailure(throwable);
    }

    public boolean setProgress(float progress) {
        return super.setProgress(progress);
    }

    @Nullable
    public CloseableReference<T> getResult() {
        return CloseableReference.cloneOrNull((CloseableReference) super.getResult());
    }

    protected void closeResult(@Nullable CloseableReference<T> result) {
        CloseableReference.closeSafely((CloseableReference) result);
    }
}
