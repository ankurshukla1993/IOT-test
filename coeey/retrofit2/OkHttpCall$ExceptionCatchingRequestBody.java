package retrofit2;

import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;

final class OkHttpCall$ExceptionCatchingRequestBody extends ResponseBody {
    private final ResponseBody delegate;
    IOException thrownException;

    OkHttpCall$ExceptionCatchingRequestBody(ResponseBody delegate) {
        this.delegate = delegate;
    }

    public MediaType contentType() {
        return this.delegate.contentType();
    }

    public long contentLength() {
        return this.delegate.contentLength();
    }

    public BufferedSource source() {
        return Okio.buffer(new ForwardingSource(this.delegate.source()) {
            public long read(Buffer sink, long byteCount) throws IOException {
                try {
                    return super.read(sink, byteCount);
                } catch (IOException e) {
                    OkHttpCall$ExceptionCatchingRequestBody.this.thrownException = e;
                    throw e;
                }
            }
        });
    }

    public void close() {
        this.delegate.close();
    }

    void throwIfCaught() throws IOException {
        if (this.thrownException != null) {
            throw this.thrownException;
        }
    }
}
