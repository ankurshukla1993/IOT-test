package com.google.common.io;

import java.io.IOException;
import java.io.InputStream;

class BaseEncoding$2 extends ByteSource {
    final /* synthetic */ BaseEncoding this$0;
    final /* synthetic */ CharSource val$encodedSource;

    BaseEncoding$2(BaseEncoding baseEncoding, CharSource charSource) {
        this.this$0 = baseEncoding;
        this.val$encodedSource = charSource;
    }

    public InputStream openStream() throws IOException {
        return this.this$0.decodingStream(this.val$encodedSource.openStream());
    }
}
