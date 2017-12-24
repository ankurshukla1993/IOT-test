package com.facebook.imagepipeline.producers;

import com.facebook.common.internal.ImmutableMap;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.image.CloseableStaticBitmap;
import com.facebook.imagepipeline.request.Postprocessor;
import java.util.Map;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

class PostprocessorProducer$PostprocessorConsumer extends DelegatingConsumer<CloseableReference<CloseableImage>, CloseableReference<CloseableImage>> {
    @GuardedBy("PostprocessorConsumer.this")
    private boolean mIsClosed;
    @GuardedBy("PostprocessorConsumer.this")
    private boolean mIsDirty = false;
    @GuardedBy("PostprocessorConsumer.this")
    private boolean mIsLast = false;
    @GuardedBy("PostprocessorConsumer.this")
    private boolean mIsPostProcessingRunning = false;
    private final ProducerListener mListener;
    private final Postprocessor mPostprocessor;
    private final String mRequestId;
    @GuardedBy("PostprocessorConsumer.this")
    @Nullable
    private CloseableReference<CloseableImage> mSourceImageRef = null;
    final /* synthetic */ PostprocessorProducer this$0;

    public PostprocessorProducer$PostprocessorConsumer(PostprocessorProducer postprocessorProducer, Consumer<CloseableReference<CloseableImage>> consumer, ProducerListener listener, String requestId, Postprocessor postprocessor, ProducerContext producerContext) {
        this.this$0 = postprocessorProducer;
        super(consumer);
        this.mListener = listener;
        this.mRequestId = requestId;
        this.mPostprocessor = postprocessor;
        producerContext.addCallbacks(new 1(this, postprocessorProducer));
    }

    protected void onNewResultImpl(CloseableReference<CloseableImage> newResult, boolean isLast) {
        if (CloseableReference.isValid(newResult)) {
            updateSourceImageRef(newResult, isLast);
        } else if (isLast) {
            maybeNotifyOnNewResult(null, true);
        }
    }

    protected void onFailureImpl(Throwable t) {
        maybeNotifyOnFailure(t);
    }

    protected void onCancellationImpl() {
        maybeNotifyOnCancellation();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void updateSourceImageRef(@javax.annotation.Nullable com.facebook.common.references.CloseableReference<com.facebook.imagepipeline.image.CloseableImage> r4, boolean r5) {
        /*
        r3 = this;
        monitor-enter(r3);
        r2 = r3.mIsClosed;	 Catch:{ all -> 0x0022 }
        if (r2 == 0) goto L_0x0007;
    L_0x0005:
        monitor-exit(r3);	 Catch:{ all -> 0x0022 }
    L_0x0006:
        return;
    L_0x0007:
        r0 = r3.mSourceImageRef;	 Catch:{ all -> 0x0022 }
        r2 = com.facebook.common.references.CloseableReference.cloneOrNull(r4);	 Catch:{ all -> 0x0022 }
        r3.mSourceImageRef = r2;	 Catch:{ all -> 0x0022 }
        r3.mIsLast = r5;	 Catch:{ all -> 0x0022 }
        r2 = 1;
        r3.mIsDirty = r2;	 Catch:{ all -> 0x0022 }
        r1 = r3.setRunningIfDirtyAndNotRunning();	 Catch:{ all -> 0x0022 }
        monitor-exit(r3);	 Catch:{ all -> 0x0022 }
        com.facebook.common.references.CloseableReference.closeSafely(r0);
        if (r1 == 0) goto L_0x0006;
    L_0x001e:
        r3.submitPostprocessing();
        goto L_0x0006;
    L_0x0022:
        r2 = move-exception;
        monitor-exit(r3);	 Catch:{ all -> 0x0022 }
        throw r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.producers.PostprocessorProducer$PostprocessorConsumer.updateSourceImageRef(com.facebook.common.references.CloseableReference, boolean):void");
    }

    private void submitPostprocessing() {
        PostprocessorProducer.access$800(this.this$0).execute(new 2(this));
    }

    private void clearRunningAndStartIfDirty() {
        synchronized (this) {
            this.mIsPostProcessingRunning = false;
            boolean shouldExecuteAgain = setRunningIfDirtyAndNotRunning();
        }
        if (shouldExecuteAgain) {
            submitPostprocessing();
        }
    }

    private synchronized boolean setRunningIfDirtyAndNotRunning() {
        boolean z = true;
        synchronized (this) {
            if (this.mIsClosed || !this.mIsDirty || this.mIsPostProcessingRunning || !CloseableReference.isValid(this.mSourceImageRef)) {
                z = false;
            } else {
                this.mIsPostProcessingRunning = true;
            }
        }
        return z;
    }

    private void doPostprocessing(CloseableReference<CloseableImage> sourceImageRef, boolean isLast) {
        Preconditions.checkArgument(CloseableReference.isValid(sourceImageRef));
        if (shouldPostprocess((CloseableImage) sourceImageRef.get())) {
            this.mListener.onProducerStart(this.mRequestId, "PostprocessorProducer");
            CloseableReference destImageRef = null;
            try {
                destImageRef = postprocessInternal((CloseableImage) sourceImageRef.get());
                this.mListener.onProducerFinishWithSuccess(this.mRequestId, "PostprocessorProducer", getExtraMap(this.mListener, this.mRequestId, this.mPostprocessor));
                maybeNotifyOnNewResult(destImageRef, isLast);
            } catch (Exception e) {
                this.mListener.onProducerFinishWithFailure(this.mRequestId, "PostprocessorProducer", e, getExtraMap(this.mListener, this.mRequestId, this.mPostprocessor));
                maybeNotifyOnFailure(e);
            } finally {
                CloseableReference.closeSafely(destImageRef);
            }
        } else {
            maybeNotifyOnNewResult(sourceImageRef, isLast);
        }
    }

    private Map<String, String> getExtraMap(ProducerListener listener, String requestId, Postprocessor postprocessor) {
        if (listener.requiresExtraMap(requestId)) {
            return ImmutableMap.of("Postprocessor", postprocessor.getName());
        }
        return null;
    }

    private boolean shouldPostprocess(CloseableImage sourceImage) {
        return sourceImage instanceof CloseableStaticBitmap;
    }

    private CloseableReference<CloseableImage> postprocessInternal(CloseableImage sourceImage) {
        CloseableStaticBitmap staticBitmap = (CloseableStaticBitmap) sourceImage;
        CloseableReference bitmapRef = this.mPostprocessor.process(staticBitmap.getUnderlyingBitmap(), PostprocessorProducer.access$900(this.this$0));
        try {
            CloseableReference<CloseableImage> of = CloseableReference.of(new CloseableStaticBitmap(bitmapRef, sourceImage.getQualityInfo(), staticBitmap.getRotationAngle()));
            return of;
        } finally {
            CloseableReference.closeSafely(bitmapRef);
        }
    }

    private void maybeNotifyOnNewResult(CloseableReference<CloseableImage> newRef, boolean isLast) {
        if ((!isLast && !isClosed()) || (isLast && close())) {
            getConsumer().onNewResult(newRef, isLast);
        }
    }

    private void maybeNotifyOnFailure(Throwable throwable) {
        if (close()) {
            getConsumer().onFailure(throwable);
        }
    }

    private void maybeNotifyOnCancellation() {
        if (close()) {
            getConsumer().onCancellation();
        }
    }

    private synchronized boolean isClosed() {
        return this.mIsClosed;
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
