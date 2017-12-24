package com.facebook.imagepipeline.producers;

import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.producers.PostprocessorProducer.PostprocessorConsumer;

class PostprocessorProducer$PostprocessorConsumer$2 implements Runnable {
    final /* synthetic */ PostprocessorConsumer this$1;

    PostprocessorProducer$PostprocessorConsumer$2(PostprocessorConsumer this$1) {
        this.this$1 = this$1;
    }

    public void run() {
        synchronized (this.this$1) {
            CloseableReference<CloseableImage> closeableImageRef = PostprocessorConsumer.access$300(this.this$1);
            boolean isLast = PostprocessorConsumer.access$400(this.this$1);
            PostprocessorConsumer.access$302(this.this$1, null);
            PostprocessorConsumer.access$502(this.this$1, false);
        }
        if (CloseableReference.isValid(closeableImageRef)) {
            try {
                PostprocessorConsumer.access$600(this.this$1, closeableImageRef, isLast);
            } finally {
                CloseableReference.closeSafely(closeableImageRef);
            }
        }
        PostprocessorConsumer.access$700(this.this$1);
    }
}
