package com.facebook.react.modules.network;

import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.ForwardingSink;
import okio.Okio;
import okio.Sink;

public class ProgressRequestBody extends RequestBody {
    private BufferedSink mBufferedSink;
    private final ProgressListener mProgressListener;
    private final RequestBody mRequestBody;

    public ProgressRequestBody(RequestBody requestBody, ProgressListener progressListener) {
        this.mRequestBody = requestBody;
        this.mProgressListener = progressListener;
    }

    public MediaType contentType() {
        return this.mRequestBody.contentType();
    }

    public long contentLength() throws IOException {
        return this.mRequestBody.contentLength();
    }

    public void writeTo(BufferedSink sink) throws IOException {
        if (this.mBufferedSink == null) {
            this.mBufferedSink = Okio.buffer(sink(sink));
        }
        this.mRequestBody.writeTo(this.mBufferedSink);
        this.mBufferedSink.flush();
    }

    private Sink sink(Sink sink) {
        return new ForwardingSink(sink) {
            long bytesWritten = 0;
            long contentLength = 0;

            public void write(Buffer source, long byteCount) throws IOException {
                super.write(source, byteCount);
                if (this.contentLength == 0) {
                    this.contentLength = ProgressRequestBody.this.contentLength();
                }
                this.bytesWritten += byteCount;
                ProgressRequestBody.this.mProgressListener.onProgress(this.bytesWritten, this.contentLength, this.bytesWritten == this.contentLength);
            }
        };
    }
}
