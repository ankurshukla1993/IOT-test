package com.facebook.imagepipeline.producers;

import com.facebook.imagepipeline.producers.PostprocessorProducer.PostprocessorConsumer;

class PostprocessorProducer$PostprocessorConsumer$1 extends BaseProducerContextCallbacks {
    final /* synthetic */ PostprocessorConsumer this$1;
    final /* synthetic */ PostprocessorProducer val$this$0;

    PostprocessorProducer$PostprocessorConsumer$1(PostprocessorConsumer this$1, PostprocessorProducer postprocessorProducer) {
        this.this$1 = this$1;
        this.val$this$0 = postprocessorProducer;
    }

    public void onCancellationRequested() {
        PostprocessorConsumer.access$200(this.this$1);
    }
}
