package com.facebook.imagepipeline.producers;

import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.request.RepeatedPostprocessor;
import com.facebook.imagepipeline.request.RepeatedPostprocessorRunner;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

class PostprocessorProducer$RepeatedPostprocessorConsumer extends DelegatingConsumer<CloseableReference<CloseableImage>, CloseableReference<CloseableImage>> implements RepeatedPostprocessorRunner {
    @GuardedBy("RepeatedPostprocessorConsumer.this")
    private boolean mIsClosed;
    @GuardedBy("RepeatedPostprocessorConsumer.this")
    @Nullable
    private CloseableReference<CloseableImage> mSourceImageRef;
    final /* synthetic */ PostprocessorProducer this$0;

    private PostprocessorProducer$RepeatedPostprocessorConsumer(PostprocessorProducer this$0, PostprocessorProducer$PostprocessorConsumer postprocessorConsumer, RepeatedPostprocessor repeatedPostprocessor, ProducerContext context) {
        this.this$0 = this$0;
        super(postprocessorConsumer);
        this.mIsClosed = false;
        this.mSourceImageRef = null;
        repeatedPostprocessor.setCallback(this);
        context.addCallbacks(new 1(this, this$0));
    }

    protected void onNewResultImpl(CloseableReference<CloseableImage> newResult, boolean isLast) {
        if (isLast) {
            setSourceImageRef(newResult);
            updateInternal();
        }
    }

    protected void onFailureImpl(Throwable throwable) {
        if (close()) {
            getConsumer().onFailure(throwable);
        }
    }

    protected void onCancellationImpl() {
        if (close()) {
            getConsumer().onCancellation();
        }
    }

    public synchronized void update() {
        updateInternal();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void updateInternal() {
        /*
        r3 = this;
        monitor-enter(r3);
        r1 = r3.mIsClosed;	 Catch:{ all -> 0x001a }
        if (r1 == 0) goto L_0x0007;
    L_0x0005:
        monitor-exit(r3);	 Catch:{ all -> 0x001a }
    L_0x0006:
        return;
    L_0x0007:
        r1 = r3.mSourceImageRef;	 Catch:{ all -> 0x001a }
        r0 = com.facebook.common.references.CloseableReference.cloneOrNull(r1);	 Catch:{ all -> 0x001a }
        monitor-exit(r3);	 Catch:{ all -> 0x001a }
        r1 = r3.getConsumer();	 Catch:{ all -> 0x001d }
        r2 = 0;
        r1.onNewResult(r0, r2);	 Catch:{ all -> 0x001d }
        com.facebook.common.references.CloseableReference.closeSafely(r0);
        goto L_0x0006;
    L_0x001a:
        r1 = move-exception;
        monitor-exit(r3);	 Catch:{ all -> 0x001a }
        throw r1;
    L_0x001d:
        r1 = move-exception;
        com.facebook.common.references.CloseableReference.closeSafely(r0);
        throw r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.producers.PostprocessorProducer$RepeatedPostprocessorConsumer.updateInternal():void");
    }

    private void setSourceImageRef(CloseableReference<CloseableImage> sourceImageRef) {
        synchronized (this) {
            if (this.mIsClosed) {
                return;
            }
            CloseableReference oldSourceImageRef = this.mSourceImageRef;
            this.mSourceImageRef = CloseableReference.cloneOrNull((CloseableReference) sourceImageRef);
            CloseableReference.closeSafely(oldSourceImageRef);
        }
    }

    private boolean close() {
        boolean z = true;
        synchronized (this) {
            if (this.mIsClosed) {
                z = false;
            } else {
                CloseableReference oldSourceImageRef = this.mSourceImageRef;
                this.mSourceImageRef = null;
                this.mIsClosed = true;
                CloseableReference.closeSafely(oldSourceImageRef);
            }
        }
        return z;
    }
}
