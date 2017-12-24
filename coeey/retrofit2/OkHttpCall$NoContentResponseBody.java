package retrofit2;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.BufferedSource;

final class OkHttpCall$NoContentResponseBody extends ResponseBody {
    private final long contentLength;
    private final MediaType contentType;

    OkHttpCall$NoContentResponseBody(MediaType contentType, long contentLength) {
        this.contentType = contentType;
        this.contentLength = contentLength;
    }

    public MediaType contentType() {
        return this.contentType;
    }

    public long contentLength() {
        return this.contentLength;
    }

    public BufferedSource source() {
        throw new IllegalStateException("Cannot read raw response body of a converted body.");
    }
}
