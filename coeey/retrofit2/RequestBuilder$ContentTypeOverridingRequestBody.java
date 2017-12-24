package retrofit2;

import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.BufferedSink;

class RequestBuilder$ContentTypeOverridingRequestBody extends RequestBody {
    private final MediaType contentType;
    private final RequestBody delegate;

    RequestBuilder$ContentTypeOverridingRequestBody(RequestBody delegate, MediaType contentType) {
        this.delegate = delegate;
        this.contentType = contentType;
    }

    public MediaType contentType() {
        return this.contentType;
    }

    public long contentLength() throws IOException {
        return this.delegate.contentLength();
    }

    public void writeTo(BufferedSink sink) throws IOException {
        this.delegate.writeTo(sink);
    }
}
