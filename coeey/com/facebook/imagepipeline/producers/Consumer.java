package com.facebook.imagepipeline.producers;

public interface Consumer<T> {
    void onCancellation();

    void onFailure(Throwable th);

    void onNewResult(T t, boolean z);

    void onProgressUpdate(float f);
}
