package com.RNFetchBlob;

import android.util.Base64;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule.RCTDeviceEventEmitter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.BufferedSink;
import org.apache.commons.cli.HelpFormatter;

public class RNFetchBlobBody extends RequestBody {
    File bodyCache;
    Boolean chunkedEncoding = Boolean.valueOf(false);
    long contentLength = 0;
    ReadableArray form;
    String mTaskId;
    MediaType mime;
    String rawBody;
    int reported = 0;
    InputStream requestStream;
    RequestType requestType;

    private class FormField {
        public String data;
        public String filename;
        public String mime;
        public String name;

        public FormField(ReadableMap rawData) {
            if (rawData.hasKey("name")) {
                this.name = rawData.getString("name");
            }
            if (rawData.hasKey("filename")) {
                this.filename = rawData.getString("filename");
            }
            if (rawData.hasKey("type")) {
                this.mime = rawData.getString("type");
            } else {
                this.mime = this.filename == null ? "text/plain" : "application/octet-stream";
            }
            if (rawData.hasKey("data")) {
                this.data = rawData.getString("data");
            }
        }
    }

    public RNFetchBlobBody(String taskId) {
        this.mTaskId = taskId;
    }

    RNFetchBlobBody chunkedEncoding(boolean val) {
        this.chunkedEncoding = Boolean.valueOf(val);
        return this;
    }

    RNFetchBlobBody setMIME(MediaType mime) {
        this.mime = mime;
        return this;
    }

    RNFetchBlobBody setRequestType(RequestType type) {
        this.requestType = type;
        return this;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    com.RNFetchBlob.RNFetchBlobBody setBody(java.lang.String r5) {
        /*
        r4 = this;
        r4.rawBody = r5;
        r1 = r4.rawBody;
        if (r1 != 0) goto L_0x000e;
    L_0x0006:
        r1 = "";
        r4.rawBody = r1;
        r1 = com.RNFetchBlob.RNFetchBlobReq.RequestType.AsIs;
        r4.requestType = r1;
    L_0x000e:
        r1 = com.RNFetchBlob.RNFetchBlobBody.C05441.$SwitchMap$com$RNFetchBlob$RNFetchBlobReq$RequestType;	 Catch:{ Exception -> 0x002c }
        r2 = r4.requestType;	 Catch:{ Exception -> 0x002c }
        r2 = r2.ordinal();	 Catch:{ Exception -> 0x002c }
        r1 = r1[r2];	 Catch:{ Exception -> 0x002c }
        switch(r1) {
            case 1: goto L_0x001c;
            case 2: goto L_0x0051;
            default: goto L_0x001b;
        };	 Catch:{ Exception -> 0x002c }
    L_0x001b:
        return r4;
    L_0x001c:
        r1 = r4.getReuqestStream();	 Catch:{ Exception -> 0x002c }
        r4.requestStream = r1;	 Catch:{ Exception -> 0x002c }
        r1 = r4.requestStream;	 Catch:{ Exception -> 0x002c }
        r1 = r1.available();	 Catch:{ Exception -> 0x002c }
        r2 = (long) r1;	 Catch:{ Exception -> 0x002c }
        r4.contentLength = r2;	 Catch:{ Exception -> 0x002c }
        goto L_0x001b;
    L_0x002c:
        r0 = move-exception;
        r0.printStackTrace();
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "RNFetchBlob failed to create single content request body :";
        r1 = r1.append(r2);
        r2 = r0.getLocalizedMessage();
        r1 = r1.append(r2);
        r2 = "\r\n";
        r1 = r1.append(r2);
        r1 = r1.toString();
        com.RNFetchBlob.RNFetchBlobUtils.emitWarningEvent(r1);
        goto L_0x001b;
    L_0x0051:
        r1 = r4.rawBody;	 Catch:{ Exception -> 0x002c }
        r1 = r1.getBytes();	 Catch:{ Exception -> 0x002c }
        r1 = r1.length;	 Catch:{ Exception -> 0x002c }
        r2 = (long) r1;	 Catch:{ Exception -> 0x002c }
        r4.contentLength = r2;	 Catch:{ Exception -> 0x002c }
        r1 = new java.io.ByteArrayInputStream;	 Catch:{ Exception -> 0x002c }
        r2 = r4.rawBody;	 Catch:{ Exception -> 0x002c }
        r2 = r2.getBytes();	 Catch:{ Exception -> 0x002c }
        r1.<init>(r2);	 Catch:{ Exception -> 0x002c }
        r4.requestStream = r1;	 Catch:{ Exception -> 0x002c }
        goto L_0x001b;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.RNFetchBlob.RNFetchBlobBody.setBody(java.lang.String):com.RNFetchBlob.RNFetchBlobBody");
    }

    RNFetchBlobBody setBody(ReadableArray body) {
        this.form = body;
        try {
            this.bodyCache = createMultipartBodyCache();
            this.requestStream = new FileInputStream(this.bodyCache);
            this.contentLength = this.bodyCache.length();
        } catch (Exception ex) {
            ex.printStackTrace();
            RNFetchBlobUtils.emitWarningEvent("RNFetchBlob failed to create request multipart body :" + ex.getLocalizedMessage());
        }
        return this;
    }

    public long contentLength() {
        return this.chunkedEncoding.booleanValue() ? -1 : this.contentLength;
    }

    public MediaType contentType() {
        return this.mime;
    }

    public void writeTo(BufferedSink sink) {
        try {
            pipeStreamToSink(this.requestStream, sink);
        } catch (Exception ex) {
            RNFetchBlobUtils.emitWarningEvent(ex.getLocalizedMessage());
            ex.printStackTrace();
        }
    }

    boolean clearRequestBody() {
        try {
            if (this.bodyCache != null && this.bodyCache.exists()) {
                this.bodyCache.delete();
            }
            return true;
        } catch (Exception e) {
            RNFetchBlobUtils.emitWarningEvent(e.getLocalizedMessage());
            return false;
        }
    }

    private InputStream getReuqestStream() throws Exception {
        InputStream open;
        if (this.rawBody.startsWith(RNFetchBlobConst.FILE_PREFIX)) {
            String orgPath = RNFetchBlobFS.normalizePath(this.rawBody.substring(RNFetchBlobConst.FILE_PREFIX.length()));
            if (RNFetchBlobFS.isAsset(orgPath)) {
                try {
                    open = RNFetchBlob.RCTContext.getAssets().open(orgPath.replace(RNFetchBlobConst.FILE_PREFIX_BUNDLE_ASSET, ""));
                } catch (Exception e) {
                    throw new Exception("error when getting request stream from asset : " + e.getLocalizedMessage());
                }
            }
            File f = new File(RNFetchBlobFS.normalizePath(orgPath));
            try {
                if (!f.exists()) {
                    f.createNewFile();
                }
                open = new FileInputStream(f);
            } catch (Exception e2) {
                throw new Exception("error when getting request stream: " + e2.getLocalizedMessage());
            }
        }
        try {
            open = new ByteArrayInputStream(Base64.decode(this.rawBody, 0));
        } catch (Exception ex) {
            throw new Exception("error when getting request stream: " + ex.getLocalizedMessage());
        }
        return open;
    }

    private File createMultipartBodyCache() throws IOException {
        String boundary = "RNFetchBlob-" + this.mTaskId;
        File outputFile = File.createTempFile("rnfb-form-tmp", "", RNFetchBlob.RCTContext.getCacheDir());
        FileOutputStream fileOutputStream = new FileOutputStream(outputFile);
        ArrayList<FormField> fields = countFormDataLength();
        ReactApplicationContext ctx = RNFetchBlob.RCTContext;
        for (int i = 0; i < fields.size(); i++) {
            FormField field = (FormField) fields.get(i);
            String data = field.data;
            String name = field.name;
            if (!(name == null || data == null)) {
                String header = HelpFormatter.DEFAULT_LONG_OPT_PREFIX + boundary + "\r\n";
                if (field.filename != null) {
                    fileOutputStream.write(((header + "Content-Disposition: form-data; name=\"" + name + "\"; filename=\"" + field.filename + "\"\r\n") + "Content-Type: " + field.mime + "\r\n\r\n").getBytes());
                    if (data.startsWith(RNFetchBlobConst.FILE_PREFIX)) {
                        String orgPath = RNFetchBlobFS.normalizePath(data.substring(RNFetchBlobConst.FILE_PREFIX.length()));
                        if (RNFetchBlobFS.isAsset(orgPath)) {
                            try {
                                pipeStreamToFileStream(ctx.getAssets().open(orgPath.replace(RNFetchBlobConst.FILE_PREFIX_BUNDLE_ASSET, "")), fileOutputStream);
                            } catch (IOException e) {
                                RNFetchBlobUtils.emitWarningEvent("Failed to create form data asset :" + orgPath + ", " + e.getLocalizedMessage());
                            }
                        } else {
                            File file = new File(RNFetchBlobFS.normalizePath(orgPath));
                            if (file.exists()) {
                                pipeStreamToFileStream(new FileInputStream(file), fileOutputStream);
                            } else {
                                RNFetchBlobUtils.emitWarningEvent("Failed to create form data from path :" + orgPath + ", file not exists.");
                            }
                        }
                    } else {
                        fileOutputStream.write(Base64.decode(data, 0));
                    }
                } else {
                    fileOutputStream.write(((header + "Content-Disposition: form-data; name=\"" + name + "\"\r\n") + "Content-Type: " + field.mime + "\r\n\r\n").getBytes());
                    fileOutputStream.write(field.data.getBytes());
                }
                fileOutputStream.write("\r\n".getBytes());
            }
        }
        fileOutputStream.write((HelpFormatter.DEFAULT_LONG_OPT_PREFIX + boundary + "--\r\n").getBytes());
        fileOutputStream.flush();
        fileOutputStream.close();
        return outputFile;
    }

    private void pipeStreamToSink(InputStream stream, BufferedSink sink) throws Exception {
        byte[] chunk = new byte[10240];
        int totalWritten = 0;
        while (true) {
            int read = stream.read(chunk, 0, 10240);
            if (read <= 0) {
                stream.close();
                return;
            } else if (read > 0) {
                sink.write(chunk, 0, read);
                totalWritten += read;
                emitUploadProgress(totalWritten);
            }
        }
    }

    private void pipeStreamToFileStream(InputStream is, FileOutputStream os) throws IOException {
        byte[] buf = new byte[10240];
        while (true) {
            int len = is.read(buf);
            if (len > 0) {
                os.write(buf, 0, len);
            } else {
                is.close();
                return;
            }
        }
    }

    private ArrayList<FormField> countFormDataLength() {
        long total = 0;
        ArrayList<FormField> list = new ArrayList();
        ReactApplicationContext ctx = RNFetchBlob.RCTContext;
        for (int i = 0; i < this.form.size(); i++) {
            FormField field = new FormField(this.form.getMap(i));
            list.add(field);
            String data = field.data;
            if (data == null) {
                RNFetchBlobUtils.emitWarningEvent("RNFetchBlob multipart request builder has found a field without `data` property, the field `" + field.name + "` will be removed implicitly.");
            } else if (field.filename == null) {
                total += field.data != null ? (long) field.data.getBytes().length : 0;
            } else if (data.startsWith(RNFetchBlobConst.FILE_PREFIX)) {
                String orgPath = RNFetchBlobFS.normalizePath(data.substring(RNFetchBlobConst.FILE_PREFIX.length()));
                if (RNFetchBlobFS.isAsset(orgPath)) {
                    try {
                        total += (long) ctx.getAssets().open(orgPath.replace(RNFetchBlobConst.FILE_PREFIX_BUNDLE_ASSET, "")).available();
                    } catch (IOException e) {
                        RNFetchBlobUtils.emitWarningEvent(e.getLocalizedMessage());
                    }
                } else {
                    total += new File(RNFetchBlobFS.normalizePath(orgPath)).length();
                }
            } else {
                total += (long) Base64.decode(data, 0).length;
            }
        }
        this.contentLength = total;
        return list;
    }

    private void emitUploadProgress(int written) {
        RNFetchBlobProgressConfig config = RNFetchBlobReq.getReportUploadProgress(this.mTaskId);
        if (config != null && this.contentLength != 0 && config.shouldReport(((float) written) / ((float) this.contentLength))) {
            WritableMap args = Arguments.createMap();
            args.putString("taskId", this.mTaskId);
            args.putString("written", String.valueOf(written));
            args.putString("total", String.valueOf(this.contentLength));
            ((RCTDeviceEventEmitter) RNFetchBlob.RCTContext.getJSModule(RCTDeviceEventEmitter.class)).emit(RNFetchBlobConst.EVENT_UPLOAD_PROGRESS, args);
        }
    }
}
