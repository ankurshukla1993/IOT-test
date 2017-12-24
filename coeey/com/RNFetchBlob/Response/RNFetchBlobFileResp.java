package com.RNFetchBlob.Response;

import com.RNFetchBlob.RNFetchBlobConst;
import com.RNFetchBlob.RNFetchBlobProgressConfig;
import com.RNFetchBlob.RNFetchBlobReq;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule.RCTDeviceEventEmitter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.Okio;
import okio.Source;
import okio.Timeout;

public class RNFetchBlobFileResp extends ResponseBody {
    static final /* synthetic */ boolean $assertionsDisabled = (!RNFetchBlobFileResp.class.desiredAssertionStatus());
    long bytesDownloaded = 0;
    String mPath;
    String mTaskId;
    FileOutputStream ofStream;
    ResponseBody originalBody;
    ReactApplicationContext rctContext;

    private class ProgressReportingSource implements Source {
        private ProgressReportingSource() {
        }

        public long read(Buffer sink, long byteCount) throws IOException {
            try {
                byte[] bytes = new byte[((int) byteCount)];
                long read = (long) RNFetchBlobFileResp.this.originalBody.byteStream().read(bytes, 0, (int) byteCount);
                RNFetchBlobFileResp rNFetchBlobFileResp = RNFetchBlobFileResp.this;
                rNFetchBlobFileResp.bytesDownloaded = (read > 0 ? read : 0) + rNFetchBlobFileResp.bytesDownloaded;
                if (read > 0) {
                    RNFetchBlobFileResp.this.ofStream.write(bytes, 0, (int) read);
                }
                RNFetchBlobProgressConfig reportConfig = RNFetchBlobReq.getReportProgress(RNFetchBlobFileResp.this.mTaskId);
                if (reportConfig == null || RNFetchBlobFileResp.this.contentLength() == 0 || !reportConfig.shouldReport((float) (RNFetchBlobFileResp.this.bytesDownloaded / RNFetchBlobFileResp.this.contentLength()))) {
                    return read;
                }
                WritableMap args = Arguments.createMap();
                args.putString("taskId", RNFetchBlobFileResp.this.mTaskId);
                args.putString("written", String.valueOf(RNFetchBlobFileResp.this.bytesDownloaded));
                args.putString("total", String.valueOf(RNFetchBlobFileResp.this.contentLength()));
                ((RCTDeviceEventEmitter) RNFetchBlobFileResp.this.rctContext.getJSModule(RCTDeviceEventEmitter.class)).emit(RNFetchBlobConst.EVENT_PROGRESS, args);
                return read;
            } catch (Exception e) {
                return -1;
            }
        }

        public Timeout timeout() {
            return null;
        }

        public void close() throws IOException {
            RNFetchBlobFileResp.this.ofStream.close();
        }
    }

    public RNFetchBlobFileResp(ReactApplicationContext ctx, String taskId, ResponseBody body, String path, boolean overwrite) throws IOException {
        this.rctContext = ctx;
        this.mTaskId = taskId;
        this.originalBody = body;
        if ($assertionsDisabled || path != null) {
            this.mPath = path;
            if (path != null) {
                boolean appendToExistingFile = !overwrite;
                path = path.replace("?append=true", "");
                this.mPath = path;
                File f = new File(path);
                File parent = f.getParentFile();
                if (parent.exists() || parent.mkdirs()) {
                    if (!f.exists()) {
                        f.createNewFile();
                    }
                    this.ofStream = new FileOutputStream(new File(path), appendToExistingFile);
                    return;
                }
                throw new IllegalStateException("Couldn't create dir: " + parent);
            }
            return;
        }
        throw new AssertionError();
    }

    public MediaType contentType() {
        return this.originalBody.contentType();
    }

    public long contentLength() {
        return this.originalBody.contentLength();
    }

    public BufferedSource source() {
        return Okio.buffer(new ProgressReportingSource());
    }
}
