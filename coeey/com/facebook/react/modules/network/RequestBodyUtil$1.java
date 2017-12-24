package com.facebook.react.modules.network;

import java.io.IOException;
import java.io.InputStream;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.internal.Util;
import okio.BufferedSink;
import okio.Okio;
import okio.Source;

class RequestBodyUtil$1 extends RequestBody {
    final /* synthetic */ InputStream val$inputStream;
    final /* synthetic */ MediaType val$mediaType;

    RequestBodyUtil$1(MediaType mediaType, InputStream inputStream) {
        this.val$mediaType = mediaType;
        this.val$inputStream = inputStream;
    }

    public MediaType contentType() {
        return this.val$mediaType;
    }

    public long contentLength() {
        try {
            return (long) this.val$inputStream.available();
        } catch (IOException e) {
            return 0;
        }
    }

    public void writeTo(BufferedSink sink) throws IOException {
        Source source = null;
        try {
            source = Okio.source(this.val$inputStream);
            sink.writeAll(source);
        } finally {
            Util.closeQuietly(source);
        }
    }
}
