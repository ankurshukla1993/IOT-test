package com.google.common.io;

import java.io.IOException;
import java.io.OutputStream;

class BaseEncoding$1 extends ByteSink {
    final /* synthetic */ BaseEncoding this$0;
    final /* synthetic */ CharSink val$encodedSink;

    BaseEncoding$1(BaseEncoding baseEncoding, CharSink charSink) {
        this.this$0 = baseEncoding;
        this.val$encodedSink = charSink;
    }

    public OutputStream openStream() throws IOException {
        return this.this$0.encodingStream(this.val$encodedSink.openStream());
    }
}
