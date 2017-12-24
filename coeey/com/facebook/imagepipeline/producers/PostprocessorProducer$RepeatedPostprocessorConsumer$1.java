package com.facebook.imagepipeline.producers;

import com.facebook.imagepipeline.producers.PostprocessorProducer.RepeatedPostprocessorConsumer;

class PostprocessorProducer$RepeatedPostprocessorConsumer$1 extends BaseProducerContextCallbacks {
    final /* synthetic */ RepeatedPostprocessorConsumer this$1;
    final /* synthetic */ PostprocessorProducer val$this$0;

    PostprocessorProducer$RepeatedPostprocessorConsumer$1(RepeatedPostprocessorConsumer this$1, PostprocessorProducer postprocessorProducer) {
        this.this$1 = this$1;
        this.val$this$0 = postprocessorProducer;
    }

    public void onCancellationRequested() {
        if (RepeatedPostprocessorConsumer.access$1000(this.this$1)) {
            this.this$1.getConsumer().onCancellation();
        }
    }
}
