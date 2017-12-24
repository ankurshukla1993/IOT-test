package com.facebook.imagepipeline.producers;

import android.util.Pair;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.Sets;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.imagepipeline.common.Priority;
import com.facebook.imagepipeline.producers.MultiplexProducer$Multiplexer.ForwardingConsumer;
import java.io.Closeable;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

@VisibleForTesting
class MultiplexProducer$Multiplexer {
    private final CopyOnWriteArraySet<Pair<Consumer<T>, ProducerContext>> mConsumerContextPairs = Sets.newCopyOnWriteArraySet();
    @GuardedBy("Multiplexer.this")
    @Nullable
    private ForwardingConsumer mForwardingConsumer;
    private final K mKey;
    @GuardedBy("Multiplexer.this")
    @Nullable
    private T mLastIntermediateResult;
    @GuardedBy("Multiplexer.this")
    private float mLastProgress;
    @GuardedBy("Multiplexer.this")
    @Nullable
    private BaseProducerContext mMultiplexProducerContext;
    final /* synthetic */ MultiplexProducer this$0;

    public MultiplexProducer$Multiplexer(MultiplexProducer this$0, K key) {
        this.this$0 = this$0;
        this.mKey = key;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean addNewConsumer(com.facebook.imagepipeline.producers.Consumer<T> r10, com.facebook.imagepipeline.producers.ProducerContext r11) {
        /*
        r9 = this;
        r6 = 0;
        r0 = android.util.Pair.create(r10, r11);
        monitor-enter(r9);
        r7 = r9.this$0;	 Catch:{ all -> 0x0050 }
        r8 = r9.mKey;	 Catch:{ all -> 0x0050 }
        r7 = com.facebook.imagepipeline.producers.MultiplexProducer.access$100(r7, r8);	 Catch:{ all -> 0x0050 }
        if (r7 == r9) goto L_0x0012;
    L_0x0010:
        monitor-exit(r9);	 Catch:{ all -> 0x0050 }
    L_0x0011:
        return r6;
    L_0x0012:
        r6 = r9.mConsumerContextPairs;	 Catch:{ all -> 0x0050 }
        r6.add(r0);	 Catch:{ all -> 0x0050 }
        r4 = r9.updateIsPrefetch();	 Catch:{ all -> 0x0050 }
        r5 = r9.updatePriority();	 Catch:{ all -> 0x0050 }
        r1 = r9.updateIsIntermediateResultExpected();	 Catch:{ all -> 0x0050 }
        r2 = r9.mLastIntermediateResult;	 Catch:{ all -> 0x0050 }
        r3 = r9.mLastProgress;	 Catch:{ all -> 0x0050 }
        monitor-exit(r9);	 Catch:{ all -> 0x0050 }
        com.facebook.imagepipeline.producers.BaseProducerContext.callOnIsPrefetchChanged(r4);
        com.facebook.imagepipeline.producers.BaseProducerContext.callOnPriorityChanged(r5);
        com.facebook.imagepipeline.producers.BaseProducerContext.callOnIsIntermediateResultExpectedChanged(r1);
        monitor-enter(r0);
        monitor-enter(r9);	 Catch:{ all -> 0x005f }
        r6 = r9.mLastIntermediateResult;	 Catch:{ all -> 0x005c }
        if (r2 == r6) goto L_0x0053;
    L_0x0037:
        r2 = 0;
    L_0x0038:
        monitor-exit(r9);	 Catch:{ all -> 0x005c }
        if (r2 == 0) goto L_0x004a;
    L_0x003b:
        r6 = 0;
        r6 = (r3 > r6 ? 1 : (r3 == r6 ? 0 : -1));
        if (r6 <= 0) goto L_0x0043;
    L_0x0040:
        r10.onProgressUpdate(r3);	 Catch:{ all -> 0x005f }
    L_0x0043:
        r6 = 0;
        r10.onNewResult(r2, r6);	 Catch:{ all -> 0x005f }
        r9.closeSafely(r2);	 Catch:{ all -> 0x005f }
    L_0x004a:
        monitor-exit(r0);	 Catch:{ all -> 0x005f }
        r9.addCallbacks(r0, r11);
        r6 = 1;
        goto L_0x0011;
    L_0x0050:
        r6 = move-exception;
        monitor-exit(r9);	 Catch:{ all -> 0x0050 }
        throw r6;
    L_0x0053:
        if (r2 == 0) goto L_0x0038;
    L_0x0055:
        r6 = r9.this$0;	 Catch:{ all -> 0x005c }
        r2 = r6.cloneOrNull(r2);	 Catch:{ all -> 0x005c }
        goto L_0x0038;
    L_0x005c:
        r6 = move-exception;
        monitor-exit(r9);	 Catch:{ all -> 0x005c }
        throw r6;	 Catch:{ all -> 0x005f }
    L_0x005f:
        r6 = move-exception;
        monitor-exit(r0);	 Catch:{ all -> 0x005f }
        throw r6;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.producers.MultiplexProducer$Multiplexer.addNewConsumer(com.facebook.imagepipeline.producers.Consumer, com.facebook.imagepipeline.producers.ProducerContext):boolean");
    }

    private void addCallbacks(Pair<Consumer<T>, ProducerContext> consumerContextPair, ProducerContext producerContext) {
        producerContext.addCallbacks(new 1(this, consumerContextPair));
    }

    private void startInputProducerIfHasAttachedConsumers() {
        boolean z = true;
        synchronized (this) {
            Preconditions.checkArgument(this.mMultiplexProducerContext == null);
            if (this.mForwardingConsumer != null) {
                z = false;
            }
            Preconditions.checkArgument(z);
            if (this.mConsumerContextPairs.isEmpty()) {
                MultiplexProducer.access$700(this.this$0, this.mKey, this);
                return;
            }
            ProducerContext producerContext = ((Pair) this.mConsumerContextPairs.iterator().next()).second;
            this.mMultiplexProducerContext = new BaseProducerContext(producerContext.getImageRequest(), producerContext.getId(), producerContext.getListener(), producerContext.getCallerContext(), producerContext.getLowestPermittedRequestLevel(), computeIsPrefetch(), computeIsIntermediateResultExpected(), computePriority());
            this.mForwardingConsumer = new ForwardingConsumer(this, null);
            BaseProducerContext multiplexProducerContext = this.mMultiplexProducerContext;
            ForwardingConsumer forwardingConsumer = this.mForwardingConsumer;
            MultiplexProducer.access$900(this.this$0).produceResults(forwardingConsumer, multiplexProducerContext);
        }
    }

    @Nullable
    private synchronized List<ProducerContextCallbacks> updateIsPrefetch() {
        List<ProducerContextCallbacks> list;
        if (this.mMultiplexProducerContext == null) {
            list = null;
        } else {
            list = this.mMultiplexProducerContext.setIsPrefetchNoCallbacks(computeIsPrefetch());
        }
        return list;
    }

    private synchronized boolean computeIsPrefetch() {
        boolean z;
        Iterator it = this.mConsumerContextPairs.iterator();
        while (it.hasNext()) {
            if (!((ProducerContext) ((Pair) it.next()).second).isPrefetch()) {
                z = false;
                break;
            }
        }
        z = true;
        return z;
    }

    @Nullable
    private synchronized List<ProducerContextCallbacks> updateIsIntermediateResultExpected() {
        List<ProducerContextCallbacks> list;
        if (this.mMultiplexProducerContext == null) {
            list = null;
        } else {
            list = this.mMultiplexProducerContext.setIsIntermediateResultExpectedNoCallbacks(computeIsIntermediateResultExpected());
        }
        return list;
    }

    private synchronized boolean computeIsIntermediateResultExpected() {
        boolean z;
        Iterator it = this.mConsumerContextPairs.iterator();
        while (it.hasNext()) {
            if (((ProducerContext) ((Pair) it.next()).second).isIntermediateResultExpected()) {
                z = true;
                break;
            }
        }
        z = false;
        return z;
    }

    @Nullable
    private synchronized List<ProducerContextCallbacks> updatePriority() {
        List<ProducerContextCallbacks> list;
        if (this.mMultiplexProducerContext == null) {
            list = null;
        } else {
            list = this.mMultiplexProducerContext.setPriorityNoCallbacks(computePriority());
        }
        return list;
    }

    private synchronized Priority computePriority() {
        Priority priority;
        priority = Priority.LOW;
        Iterator it = this.mConsumerContextPairs.iterator();
        while (it.hasNext()) {
            priority = Priority.getHigherPriority(priority, ((ProducerContext) ((Pair) it.next()).second).getPriority());
        }
        return priority;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onFailure(com.facebook.imagepipeline.producers.MultiplexProducer$Multiplexer.ForwardingConsumer r5, java.lang.Throwable r6) {
        /*
        r4 = this;
        monitor-enter(r4);
        r2 = r4.mForwardingConsumer;	 Catch:{ all -> 0x003b }
        if (r2 == r5) goto L_0x0007;
    L_0x0005:
        monitor-exit(r4);	 Catch:{ all -> 0x003b }
    L_0x0006:
        return;
    L_0x0007:
        r2 = r4.mConsumerContextPairs;	 Catch:{ all -> 0x003b }
        r0 = r2.iterator();	 Catch:{ all -> 0x003b }
        r2 = r4.mConsumerContextPairs;	 Catch:{ all -> 0x003b }
        r2.clear();	 Catch:{ all -> 0x003b }
        r2 = r4.this$0;	 Catch:{ all -> 0x003b }
        r3 = r4.mKey;	 Catch:{ all -> 0x003b }
        com.facebook.imagepipeline.producers.MultiplexProducer.access$700(r2, r3, r4);	 Catch:{ all -> 0x003b }
        r2 = r4.mLastIntermediateResult;	 Catch:{ all -> 0x003b }
        r4.closeSafely(r2);	 Catch:{ all -> 0x003b }
        r2 = 0;
        r4.mLastIntermediateResult = r2;	 Catch:{ all -> 0x003b }
        monitor-exit(r4);	 Catch:{ all -> 0x003b }
    L_0x0022:
        r2 = r0.hasNext();
        if (r2 == 0) goto L_0x0006;
    L_0x0028:
        r1 = r0.next();
        r1 = (android.util.Pair) r1;
        monitor-enter(r1);
        r2 = r1.first;	 Catch:{ all -> 0x0038 }
        r2 = (com.facebook.imagepipeline.producers.Consumer) r2;	 Catch:{ all -> 0x0038 }
        r2.onFailure(r6);	 Catch:{ all -> 0x0038 }
        monitor-exit(r1);	 Catch:{ all -> 0x0038 }
        goto L_0x0022;
    L_0x0038:
        r2 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x0038 }
        throw r2;
    L_0x003b:
        r2 = move-exception;
        monitor-exit(r4);	 Catch:{ all -> 0x003b }
        throw r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.producers.MultiplexProducer$Multiplexer.onFailure(com.facebook.imagepipeline.producers.MultiplexProducer$Multiplexer$ForwardingConsumer, java.lang.Throwable):void");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onNextResult(com.facebook.imagepipeline.producers.MultiplexProducer$Multiplexer.ForwardingConsumer r5, T r6, boolean r7) {
        /*
        r4 = this;
        monitor-enter(r4);
        r2 = r4.mForwardingConsumer;	 Catch:{ all -> 0x0046 }
        if (r2 == r5) goto L_0x0007;
    L_0x0005:
        monitor-exit(r4);	 Catch:{ all -> 0x0046 }
    L_0x0006:
        return;
    L_0x0007:
        r2 = r4.mLastIntermediateResult;	 Catch:{ all -> 0x0046 }
        r4.closeSafely(r2);	 Catch:{ all -> 0x0046 }
        r2 = 0;
        r4.mLastIntermediateResult = r2;	 Catch:{ all -> 0x0046 }
        r2 = r4.mConsumerContextPairs;	 Catch:{ all -> 0x0046 }
        r0 = r2.iterator();	 Catch:{ all -> 0x0046 }
        if (r7 != 0) goto L_0x0039;
    L_0x0017:
        r2 = r4.this$0;	 Catch:{ all -> 0x0046 }
        r2 = r2.cloneOrNull(r6);	 Catch:{ all -> 0x0046 }
        r4.mLastIntermediateResult = r2;	 Catch:{ all -> 0x0046 }
    L_0x001f:
        monitor-exit(r4);	 Catch:{ all -> 0x0046 }
    L_0x0020:
        r2 = r0.hasNext();
        if (r2 == 0) goto L_0x0006;
    L_0x0026:
        r1 = r0.next();
        r1 = (android.util.Pair) r1;
        monitor-enter(r1);
        r2 = r1.first;	 Catch:{ all -> 0x0036 }
        r2 = (com.facebook.imagepipeline.producers.Consumer) r2;	 Catch:{ all -> 0x0036 }
        r2.onNewResult(r6, r7);	 Catch:{ all -> 0x0036 }
        monitor-exit(r1);	 Catch:{ all -> 0x0036 }
        goto L_0x0020;
    L_0x0036:
        r2 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x0036 }
        throw r2;
    L_0x0039:
        r2 = r4.mConsumerContextPairs;	 Catch:{ all -> 0x0046 }
        r2.clear();	 Catch:{ all -> 0x0046 }
        r2 = r4.this$0;	 Catch:{ all -> 0x0046 }
        r3 = r4.mKey;	 Catch:{ all -> 0x0046 }
        com.facebook.imagepipeline.producers.MultiplexProducer.access$700(r2, r3, r4);	 Catch:{ all -> 0x0046 }
        goto L_0x001f;
    L_0x0046:
        r2 = move-exception;
        monitor-exit(r4);	 Catch:{ all -> 0x0046 }
        throw r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.producers.MultiplexProducer$Multiplexer.onNextResult(com.facebook.imagepipeline.producers.MultiplexProducer$Multiplexer$ForwardingConsumer, java.io.Closeable, boolean):void");
    }

    public void onCancelled(ForwardingConsumer forwardingConsumer) {
        synchronized (this) {
            if (this.mForwardingConsumer != forwardingConsumer) {
                return;
            }
            this.mForwardingConsumer = null;
            this.mMultiplexProducerContext = null;
            closeSafely(this.mLastIntermediateResult);
            this.mLastIntermediateResult = null;
            startInputProducerIfHasAttachedConsumers();
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onProgressUpdate(com.facebook.imagepipeline.producers.MultiplexProducer$Multiplexer.ForwardingConsumer r4, float r5) {
        /*
        r3 = this;
        monitor-enter(r3);
        r2 = r3.mForwardingConsumer;	 Catch:{ all -> 0x0029 }
        if (r2 == r4) goto L_0x0007;
    L_0x0005:
        monitor-exit(r3);	 Catch:{ all -> 0x0029 }
    L_0x0006:
        return;
    L_0x0007:
        r3.mLastProgress = r5;	 Catch:{ all -> 0x0029 }
        r2 = r3.mConsumerContextPairs;	 Catch:{ all -> 0x0029 }
        r0 = r2.iterator();	 Catch:{ all -> 0x0029 }
        monitor-exit(r3);	 Catch:{ all -> 0x0029 }
    L_0x0010:
        r2 = r0.hasNext();
        if (r2 == 0) goto L_0x0006;
    L_0x0016:
        r1 = r0.next();
        r1 = (android.util.Pair) r1;
        monitor-enter(r1);
        r2 = r1.first;	 Catch:{ all -> 0x0026 }
        r2 = (com.facebook.imagepipeline.producers.Consumer) r2;	 Catch:{ all -> 0x0026 }
        r2.onProgressUpdate(r5);	 Catch:{ all -> 0x0026 }
        monitor-exit(r1);	 Catch:{ all -> 0x0026 }
        goto L_0x0010;
    L_0x0026:
        r2 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x0026 }
        throw r2;
    L_0x0029:
        r2 = move-exception;
        monitor-exit(r3);	 Catch:{ all -> 0x0029 }
        throw r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.producers.MultiplexProducer$Multiplexer.onProgressUpdate(com.facebook.imagepipeline.producers.MultiplexProducer$Multiplexer$ForwardingConsumer, float):void");
    }

    private void closeSafely(Closeable obj) {
        if (obj != null) {
            try {
                obj.close();
            } catch (IOException ioe) {
                throw new RuntimeException(ioe);
            }
        }
    }
}
