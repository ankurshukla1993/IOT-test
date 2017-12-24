package com.facebook.imagepipeline.datasource;

import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSource;
import com.facebook.imagepipeline.listener.RequestListener;
import com.facebook.imagepipeline.producers.Producer;
import com.facebook.imagepipeline.producers.SettableProducerContext;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public class CloseableProducerToDataSourceAdapter<T> extends AbstractProducerToDataSourceAdapter<CloseableReference<T>> {
    public static <T> DataSource<CloseableReference<T>> create(Producer<CloseableReference<T>> producer, SettableProducerContext settableProducerContext, RequestListener listener) {
        return new CloseableProducerToDataSourceAdapter(producer, settableProducerContext, listener);
    }

    private CloseableProducerToDataSourceAdapter(Producer<CloseableReference<T>> producer, SettableProducerContext settableProducerContext, RequestListener listener) {
        super(producer, settableProducerContext, listener);
    }

    @Nullable
    public CloseableReference<T> getResult() {
        return CloseableReference.cloneOrNull((CloseableReference) super.getResult());
    }

    protected void closeResult(CloseableReference<T> result) {
        CloseableReference.closeSafely((CloseableReference) result);
    }

    protected void onNewResultImpl(CloseableReference<T> result, boolean isLast) {
        super.onNewResultImpl(CloseableReference.cloneOrNull((CloseableReference) result), isLast);
    }
}
