package com.RNFetchBlob.Response;

import com.RNFetchBlob.RNFetchBlobConst;
import com.RNFetchBlob.RNFetchBlobProgressConfig;
import com.RNFetchBlob.RNFetchBlobReq;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule.RCTDeviceEventEmitter;
import java.io.IOException;
import java.nio.charset.Charset;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.Okio;
import okio.Source;
import okio.Timeout;

public class RNFetchBlobDefaultResp extends ResponseBody {
    boolean isIncrement = false;
    String mTaskId;
    ResponseBody originalBody;
    ReactApplicationContext rctContext;

    private class ProgressReportingSource implements Source {
        long bytesRead = 0;
        BufferedSource mOriginalSource;

        ProgressReportingSource(BufferedSource originalSource) {
            this.mOriginalSource = originalSource;
        }

        public long read(Buffer sink, long byteCount) throws IOException {
            long j;
            long read = this.mOriginalSource.read(sink, byteCount);
            long j2 = this.bytesRead;
            if (read > 0) {
                j = read;
            } else {
                j = 0;
            }
            this.bytesRead = j + j2;
            RNFetchBlobProgressConfig reportConfig = RNFetchBlobReq.getReportProgress(RNFetchBlobDefaultResp.this.mTaskId);
            long cLen = RNFetchBlobDefaultResp.this.contentLength();
            if (!(reportConfig == null || cLen == 0 || !reportConfig.shouldReport((float) (this.bytesRead / RNFetchBlobDefaultResp.this.contentLength())))) {
                WritableMap args = Arguments.createMap();
                args.putString("taskId", RNFetchBlobDefaultResp.this.mTaskId);
                args.putString("written", String.valueOf(this.bytesRead));
                args.putString("total", String.valueOf(RNFetchBlobDefaultResp.this.contentLength()));
                if (RNFetchBlobDefaultResp.this.isIncrement) {
                    args.putString("chunk", sink.readString(Charset.defaultCharset()));
                } else {
                    args.putString("chunk", "");
                }
                ((RCTDeviceEventEmitter) RNFetchBlobDefaultResp.this.rctContext.getJSModule(RCTDeviceEventEmitter.class)).emit(RNFetchBlobConst.EVENT_PROGRESS, args);
            }
            return read;
        }

        public Timeout timeout() {
            return null;
        }

        public void close() throws IOException {
        }
    }

    public RNFetchBlobDefaultResp(ReactApplicationContext ctx, String taskId, ResponseBody body, boolean isIncrement) {
        this.rctContext = ctx;
        this.mTaskId = taskId;
        this.originalBody = body;
        this.isIncrement = isIncrement;
    }

    public MediaType contentType() {
        return this.originalBody.contentType();
    }

    public long contentLength() {
        return this.originalBody.contentLength();
    }

    public BufferedSource source() {
        return Okio.buffer(new ProgressReportingSource(this.originalBody.source()));
    }
}
