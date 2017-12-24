package com.facebook.react.modules.network;

import java.io.IOException;
import okio.Buffer;
import okio.ForwardingSource;
import okio.Source;

class ProgressResponseBody$1 extends ForwardingSource {
    final /* synthetic */ ProgressResponseBody this$0;

    ProgressResponseBody$1(ProgressResponseBody this$0, Source x0) {
        this.this$0 = this$0;
        super(x0);
    }

    public long read(Buffer sink, long byteCount) throws IOException {
        long bytesRead = super.read(sink, byteCount);
        ProgressResponseBody.access$002(this.this$0, (bytesRead != -1 ? bytesRead : 0) + ProgressResponseBody.access$000(this.this$0));
        ProgressResponseBody.access$200(this.this$0).onProgress(ProgressResponseBody.access$000(this.this$0), ProgressResponseBody.access$100(this.this$0).contentLength(), bytesRead == -1);
        return bytesRead;
    }
}
