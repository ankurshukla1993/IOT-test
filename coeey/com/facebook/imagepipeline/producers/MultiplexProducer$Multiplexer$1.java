package com.facebook.imagepipeline.producers;

import android.util.Pair;
import com.facebook.imagepipeline.producers.MultiplexProducer.Multiplexer;
import java.util.List;

class MultiplexProducer$Multiplexer$1 extends BaseProducerContextCallbacks {
    final /* synthetic */ Multiplexer this$1;
    final /* synthetic */ Pair val$consumerContextPair;

    MultiplexProducer$Multiplexer$1(Multiplexer this$1, Pair pair) {
        this.this$1 = this$1;
        this.val$consumerContextPair = pair;
    }

    public void onCancellationRequested() {
        BaseProducerContext contextToCancel = null;
        List<ProducerContextCallbacks> isPrefetchCallbacks = null;
        List<ProducerContextCallbacks> priorityCallbacks = null;
        List<ProducerContextCallbacks> isIntermediateResultExpectedCallbacks = null;
        synchronized (this.this$1) {
            boolean pairWasRemoved = Multiplexer.access$200(this.this$1).remove(this.val$consumerContextPair);
            if (pairWasRemoved) {
                if (Multiplexer.access$200(this.this$1).isEmpty()) {
                    contextToCancel = Multiplexer.access$300(this.this$1);
                } else {
                    isPrefetchCallbacks = Multiplexer.access$400(this.this$1);
                    priorityCallbacks = Multiplexer.access$500(this.this$1);
                    isIntermediateResultExpectedCallbacks = Multiplexer.access$600(this.this$1);
                }
            }
        }
        BaseProducerContext.callOnIsPrefetchChanged(isPrefetchCallbacks);
        BaseProducerContext.callOnPriorityChanged(priorityCallbacks);
        BaseProducerContext.callOnIsIntermediateResultExpectedChanged(isIntermediateResultExpectedCallbacks);
        if (contextToCancel != null) {
            contextToCancel.cancel();
        }
        if (pairWasRemoved) {
            ((Consumer) this.val$consumerContextPair.first).onCancellation();
        }
    }

    public void onIsPrefetchChanged() {
        BaseProducerContext.callOnIsPrefetchChanged(Multiplexer.access$400(this.this$1));
    }

    public void onIsIntermediateResultExpectedChanged() {
        BaseProducerContext.callOnIsIntermediateResultExpectedChanged(Multiplexer.access$600(this.this$1));
    }

    public void onPriorityChanged() {
        BaseProducerContext.callOnPriorityChanged(Multiplexer.access$500(this.this$1));
    }
}
