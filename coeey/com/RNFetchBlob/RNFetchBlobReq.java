package com.RNFetchBlob;

import android.app.DownloadManager;
import android.app.DownloadManager.Query;
import android.app.DownloadManager.Request;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build.VERSION;
import android.util.Base64;
import com.RNFetchBlob.Response.RNFetchBlobDefaultResp;
import com.RNFetchBlob.Response.RNFetchBlobFileResp;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule.RCTDeviceEventEmitter;
import com.facebook.react.modules.network.TLSSocketFactory;
import com.facebook.react.views.text.ReactTextShadowNode;
import com.google.common.net.HttpHeaders;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import okhttp3.Call;
import okhttp3.ConnectionPool;
import okhttp3.ConnectionSpec;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Interceptor.Chain;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.TlsVersion;

public class RNFetchBlobReq extends BroadcastReceiver implements Runnable {
    static ConnectionPool pool = new ConnectionPool();
    static HashMap<String, RNFetchBlobProgressConfig> progressReport = new HashMap();
    public static HashMap<String, Call> taskTable = new HashMap();
    static HashMap<String, RNFetchBlobProgressConfig> uploadProgressReport = new HashMap();
    Callback callback;
    OkHttpClient client;
    long contentLength;
    ReactApplicationContext ctx;
    String destPath;
    long downloadManagerId;
    ReadableMap headers;
    String method;
    RNFetchBlobConfig options;
    String rawRequestBody;
    ReadableArray rawRequestBodyArray;
    ArrayList<String> redirects = new ArrayList();
    RNFetchBlobBody requestBody;
    RequestType requestType;
    WritableMap respInfo;
    ResponseFormat responseFormat = ResponseFormat.Auto;
    ResponseType responseType;
    String taskId;
    boolean timeout = false;
    String url;

    class C05481 implements Interceptor {
        C05481() {
        }

        public Response intercept(Chain chain) throws IOException {
            RNFetchBlobReq.this.redirects.add(chain.request().url().toString());
            return chain.proceed(chain.request());
        }
    }

    class C05503 implements okhttp3.Callback {
        C05503() {
        }

        public void onFailure(Call call, IOException e) {
            RNFetchBlobReq.cancelTask(RNFetchBlobReq.this.taskId);
            if (RNFetchBlobReq.this.respInfo == null) {
                RNFetchBlobReq.this.respInfo = Arguments.createMap();
            }
            if (e.getClass().equals(SocketTimeoutException.class)) {
                RNFetchBlobReq.this.respInfo.putBoolean("timeout", true);
                RNFetchBlobReq.this.callback.invoke(new Object[]{"request timed out.", null, null});
            } else {
                RNFetchBlobReq.this.callback.invoke(new Object[]{e.getLocalizedMessage(), null, null});
            }
            RNFetchBlobReq.this.releaseTaskResource();
        }

        public void onResponse(Call call, Response response) throws IOException {
            ReadableMap notifyConfig = RNFetchBlobReq.this.options.addAndroidDownloads;
            if (notifyConfig != null) {
                String title = "";
                String desc = "";
                String mime = "text/plain";
                boolean scannable = false;
                boolean notification = false;
                if (notifyConfig.hasKey("title")) {
                    title = RNFetchBlobReq.this.options.addAndroidDownloads.getString("title");
                }
                if (notifyConfig.hasKey("description")) {
                    desc = notifyConfig.getString("description");
                }
                if (notifyConfig.hasKey("mime")) {
                    mime = notifyConfig.getString("mime");
                }
                if (notifyConfig.hasKey("mediaScannable")) {
                    scannable = notifyConfig.getBoolean("mediaScannable");
                }
                if (notifyConfig.hasKey("notification")) {
                    notification = notifyConfig.getBoolean("notification");
                }
                ReactApplicationContext reactApplicationContext = RNFetchBlob.RCTContext;
                ReactApplicationContext reactApplicationContext2 = RNFetchBlob.RCTContext;
                ((DownloadManager) reactApplicationContext.getSystemService("download")).addCompletedDownload(title, desc, scannable, mime, RNFetchBlobReq.this.destPath, RNFetchBlobReq.this.contentLength, notification);
            }
            RNFetchBlobReq.this.done(response);
        }
    }

    enum RequestType {
        Form,
        SingleFile,
        AsIs,
        WithoutBody,
        Others
    }

    enum ResponseFormat {
        Auto,
        UTF8,
        BASE64
    }

    enum ResponseType {
        KeepInMemory,
        FileStorage
    }

    public RNFetchBlobReq(ReadableMap options, String taskId, String method, String url, ReadableMap headers, String body, ReadableArray arrayBody, OkHttpClient client, Callback callback) {
        this.method = method.toUpperCase();
        this.options = new RNFetchBlobConfig(options);
        this.taskId = taskId;
        this.url = url;
        this.headers = headers;
        this.callback = callback;
        this.rawRequestBody = body;
        this.rawRequestBodyArray = arrayBody;
        this.client = client;
        if (this.options.fileCache.booleanValue() || this.options.path != null) {
            this.responseType = ResponseType.FileStorage;
        } else {
            this.responseType = ResponseType.KeepInMemory;
        }
        if (body != null) {
            this.requestType = RequestType.SingleFile;
        } else if (arrayBody != null) {
            this.requestType = RequestType.Form;
        } else {
            this.requestType = RequestType.WithoutBody;
        }
    }

    public static void cancelTask(String taskId) {
        if (taskTable.containsKey(taskId)) {
            ((Call) taskTable.get(taskId)).cancel();
            taskTable.remove(taskId);
        }
    }

    public void run() {
        ReadableMapKeySetIterator it;
        if (this.options.addAndroidDownloads != null && this.options.addAndroidDownloads.hasKey("useDownloadManager") && this.options.addAndroidDownloads.getBoolean("useDownloadManager")) {
            Request req = new Request(Uri.parse(this.url));
            req.setNotificationVisibility(1);
            if (this.options.addAndroidDownloads.hasKey("title")) {
                req.setTitle(this.options.addAndroidDownloads.getString("title"));
            }
            if (this.options.addAndroidDownloads.hasKey("description")) {
                req.setDescription(this.options.addAndroidDownloads.getString("description"));
            }
            if (this.options.addAndroidDownloads.hasKey(RNFetchBlobConst.RNFB_RESPONSE_PATH)) {
                req.setDestinationUri(Uri.parse("file://" + this.options.addAndroidDownloads.getString(RNFetchBlobConst.RNFB_RESPONSE_PATH)));
            }
            if (this.options.addAndroidDownloads.hasKey("mime")) {
                req.setMimeType(this.options.addAndroidDownloads.getString("mime"));
            }
            it = this.headers.keySetIterator();
            if (this.options.addAndroidDownloads.hasKey("mediaScannable") && this.options.addAndroidDownloads.hasKey("mediaScannable")) {
                req.allowScanningByMediaScanner();
            }
            while (it.hasNextKey()) {
                String key = it.nextKey();
                req.addRequestHeader(key, this.headers.getString(key));
            }
            Context appCtx = RNFetchBlob.RCTContext.getApplicationContext();
            this.downloadManagerId = ((DownloadManager) appCtx.getSystemService("download")).enqueue(req);
            appCtx.registerReceiver(this, new IntentFilter("android.intent.action.DOWNLOAD_COMPLETE"));
            return;
        }
        String ext;
        String cacheKey = this.taskId;
        if (this.options.appendExt.isEmpty()) {
            ext = "";
        } else {
            ext = "." + this.options.appendExt;
        }
        if (this.options.key != null) {
            cacheKey = RNFetchBlobUtils.getMD5(this.options.key);
            if (cacheKey == null) {
                cacheKey = this.taskId;
            }
            if (new File(RNFetchBlobFS.getTmpPath(RNFetchBlob.RCTContext, cacheKey) + ext).exists()) {
                this.callback.invoke(new Object[]{null, RNFetchBlobConst.RNFB_RESPONSE_PATH, r0.getAbsolutePath()});
                return;
            }
        }
        if (this.options.path != null) {
            this.destPath = this.options.path;
        } else if (this.options.fileCache.booleanValue()) {
            this.destPath = RNFetchBlobFS.getTmpPath(RNFetchBlob.RCTContext, cacheKey) + ext;
        }
        try {
            Builder clientBuilder;
            if (this.options.trusty.booleanValue()) {
                clientBuilder = RNFetchBlobUtils.getUnsafeOkHttpClient(this.client);
            } else {
                clientBuilder = this.client.newBuilder();
            }
            okhttp3.Request.Builder builder = new okhttp3.Request.Builder();
            try {
                builder.url(new URL(this.url));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            HashMap<String, String> mheaders = new HashMap();
            if (this.headers != null) {
                it = this.headers.keySetIterator();
                while (it.hasNextKey()) {
                    key = it.nextKey();
                    String value = this.headers.getString(key);
                    if (!key.equalsIgnoreCase("RNFB-Response")) {
                        builder.header(key.toLowerCase(), value);
                        mheaders.put(key.toLowerCase(), value);
                    } else if (value.equalsIgnoreCase(RNFetchBlobConst.RNFB_RESPONSE_BASE64)) {
                        this.responseFormat = ResponseFormat.BASE64;
                    } else if (value.equalsIgnoreCase(RNFetchBlobConst.RNFB_RESPONSE_UTF8)) {
                        this.responseFormat = ResponseFormat.UTF8;
                    }
                }
            }
            if (this.method.equalsIgnoreCase("post") || this.method.equalsIgnoreCase("put") || this.method.equalsIgnoreCase("patch")) {
                String cType = getHeaderIgnoreCases((HashMap) mheaders, "Content-Type").toLowerCase();
                if (this.rawRequestBodyArray != null) {
                    this.requestType = RequestType.Form;
                } else if (cType.isEmpty()) {
                    builder.header("Content-Type", "application/octet-stream");
                    this.requestType = RequestType.SingleFile;
                }
                if (this.rawRequestBody != null) {
                    if (this.rawRequestBody.startsWith(RNFetchBlobConst.FILE_PREFIX)) {
                        this.requestType = RequestType.SingleFile;
                    } else if (cType.toLowerCase().contains(";base64") || cType.toLowerCase().startsWith("application/octet")) {
                        cType = cType.replace(";base64", "").replace(";BASE64", "");
                        if (mheaders.containsKey("content-type")) {
                            mheaders.put("content-type", cType);
                        }
                        if (mheaders.containsKey("Content-Type")) {
                            mheaders.put("Content-Type", cType);
                        }
                        this.requestType = RequestType.SingleFile;
                    } else {
                        this.requestType = RequestType.AsIs;
                    }
                }
            } else {
                this.requestType = RequestType.WithoutBody;
            }
            boolean isChunkedRequest = getHeaderIgnoreCases((HashMap) mheaders, HttpHeaders.TRANSFER_ENCODING).equalsIgnoreCase("chunked");
            switch (this.requestType) {
                case SingleFile:
                    this.requestBody = new RNFetchBlobBody(this.taskId).chunkedEncoding(isChunkedRequest).setRequestType(this.requestType).setBody(this.rawRequestBody).setMIME(MediaType.parse(getHeaderIgnoreCases((HashMap) mheaders, "content-type")));
                    builder.method(this.method, this.requestBody);
                    break;
                case AsIs:
                    this.requestBody = new RNFetchBlobBody(this.taskId).chunkedEncoding(isChunkedRequest).setRequestType(this.requestType).setBody(this.rawRequestBody).setMIME(MediaType.parse(getHeaderIgnoreCases((HashMap) mheaders, "content-type")));
                    builder.method(this.method, this.requestBody);
                    break;
                case Form:
                    this.requestBody = new RNFetchBlobBody(this.taskId).chunkedEncoding(isChunkedRequest).setRequestType(this.requestType).setBody(this.rawRequestBodyArray).setMIME(MediaType.parse("multipart/form-data; boundary=" + ("RNFetchBlob-" + this.taskId)));
                    builder.method(this.method, this.requestBody);
                    break;
                case WithoutBody:
                    if (!this.method.equalsIgnoreCase("post") && !this.method.equalsIgnoreCase("put") && !this.method.equalsIgnoreCase("patch")) {
                        builder.method(this.method, null);
                        break;
                    }
                    builder.method(this.method, RequestBody.create(null, new byte[0]));
                    break;
                    break;
            }
            okhttp3.Request req2 = builder.build();
            clientBuilder.addNetworkInterceptor(new C05481());
            final okhttp3.Request request = req2;
            clientBuilder.addInterceptor(new Interceptor() {
                public Response intercept(Chain chain) throws IOException {
                    try {
                        ResponseBody extended;
                        Response originalResponse = chain.proceed(request);
                        switch (RNFetchBlobReq.this.responseType) {
                            case KeepInMemory:
                                extended = new RNFetchBlobDefaultResp(RNFetchBlob.RCTContext, RNFetchBlobReq.this.taskId, originalResponse.body(), RNFetchBlobReq.this.options.increment.booleanValue());
                                break;
                            case FileStorage:
                                extended = new RNFetchBlobFileResp(RNFetchBlob.RCTContext, RNFetchBlobReq.this.taskId, originalResponse.body(), RNFetchBlobReq.this.destPath, RNFetchBlobReq.this.options.overwrite.booleanValue());
                                break;
                            default:
                                extended = new RNFetchBlobDefaultResp(RNFetchBlob.RCTContext, RNFetchBlobReq.this.taskId, originalResponse.body(), RNFetchBlobReq.this.options.increment.booleanValue());
                                break;
                        }
                        return originalResponse.newBuilder().body(extended).build();
                    } catch (SocketException e) {
                        RNFetchBlobReq.this.timeout = true;
                        return chain.proceed(chain.request());
                    } catch (SocketTimeoutException e2) {
                        RNFetchBlobReq.this.timeout = true;
                        RNFetchBlobUtils.emitWarningEvent("RNFetchBlob error when sending request : " + e2.getLocalizedMessage());
                        return chain.proceed(chain.request());
                    } catch (Exception e3) {
                        return chain.proceed(chain.request());
                    }
                }
            });
            if (this.options.timeout >= 0) {
                clientBuilder.connectTimeout(this.options.timeout, TimeUnit.MILLISECONDS);
                clientBuilder.readTimeout(this.options.timeout, TimeUnit.MILLISECONDS);
            }
            clientBuilder.connectionPool(pool);
            clientBuilder.retryOnConnectionFailure(false);
            clientBuilder.followRedirects(this.options.followRedirect.booleanValue());
            clientBuilder.followSslRedirects(this.options.followRedirect.booleanValue());
            clientBuilder.retryOnConnectionFailure(true);
            Call call = enableTls12OnPreLollipop(clientBuilder).build().newCall(req2);
            taskTable.put(this.taskId, call);
            call.enqueue(new C05503());
        } catch (Exception error) {
            error.printStackTrace();
            releaseTaskResource();
            this.callback.invoke(new Object[]{"RNFetchBlob request error: " + error.getMessage() + error.getCause()});
        }
    }

    private void releaseTaskResource() {
        if (taskTable.containsKey(this.taskId)) {
            taskTable.remove(this.taskId);
        }
        if (uploadProgressReport.containsKey(this.taskId)) {
            uploadProgressReport.remove(this.taskId);
        }
        if (progressReport.containsKey(this.taskId)) {
            progressReport.remove(this.taskId);
        }
        if (this.requestBody != null) {
            this.requestBody.clearRequestBody();
        }
    }

    private void done(Response resp) {
        boolean isBlobResp = isBlobResponse(resp);
        emitStateEvent(getResponseInfo(resp, isBlobResp));
        switch (this.responseType) {
            case KeepInMemory:
                if (isBlobResp) {
                    try {
                        if (this.options.auto.booleanValue()) {
                            String dest = RNFetchBlobFS.getTmpPath(this.ctx, this.taskId);
                            InputStream ins = resp.body().byteStream();
                            FileOutputStream os = new FileOutputStream(new File(dest));
                            byte[] buffer = new byte[10240];
                            while (true) {
                                int read = ins.read(buffer);
                                if (read == -1) {
                                    ins.close();
                                    os.flush();
                                    os.close();
                                    this.callback.invoke(new Object[]{null, RNFetchBlobConst.RNFB_RESPONSE_PATH, dest});
                                    break;
                                }
                                os.write(buffer, 0, read);
                            }
                        }
                    } catch (IOException e) {
                        this.callback.invoke(new Object[]{"RNFetchBlob failed to encode response data to BASE64 string.", null});
                        break;
                    }
                }
                byte[] b = resp.body().bytes();
                CharsetEncoder encoder = Charset.forName("UTF-8").newEncoder();
                if (this.responseFormat != ResponseFormat.BASE64) {
                    try {
                        encoder.encode(ByteBuffer.wrap(b).asCharBuffer());
                        String utf8 = new String(b);
                        this.callback.invoke(new Object[]{null, RNFetchBlobConst.RNFB_RESPONSE_UTF8, utf8});
                        break;
                    } catch (CharacterCodingException e2) {
                        if (this.responseFormat != ResponseFormat.UTF8) {
                            this.callback.invoke(new Object[]{null, RNFetchBlobConst.RNFB_RESPONSE_BASE64, Base64.encodeToString(b, 2)});
                            break;
                        }
                        this.callback.invoke(new Object[]{null, RNFetchBlobConst.RNFB_RESPONSE_UTF8, ""});
                        break;
                    }
                }
                this.callback.invoke(new Object[]{null, RNFetchBlobConst.RNFB_RESPONSE_BASE64, Base64.encodeToString(b, 2)});
                return;
            case FileStorage:
                try {
                    resp.body().bytes();
                } catch (Exception e3) {
                }
                this.destPath = this.destPath.replace("?append=true", "");
                this.callback.invoke(new Object[]{null, RNFetchBlobConst.RNFB_RESPONSE_PATH, this.destPath});
                break;
            default:
                try {
                    this.callback.invoke(new Object[]{null, RNFetchBlobConst.RNFB_RESPONSE_UTF8, new String(resp.body().bytes(), "UTF-8")});
                    break;
                } catch (IOException e4) {
                    this.callback.invoke(new Object[]{"RNFetchBlob failed to encode response data to UTF8 string.", null});
                    break;
                }
        }
        resp.body().close();
        releaseTaskResource();
    }

    public static RNFetchBlobProgressConfig getReportProgress(String taskId) {
        if (progressReport.containsKey(taskId)) {
            return (RNFetchBlobProgressConfig) progressReport.get(taskId);
        }
        return null;
    }

    public static RNFetchBlobProgressConfig getReportUploadProgress(String taskId) {
        if (uploadProgressReport.containsKey(taskId)) {
            return (RNFetchBlobProgressConfig) uploadProgressReport.get(taskId);
        }
        return null;
    }

    private WritableMap getResponseInfo(Response resp, boolean isBlobResp) {
        WritableMap info = Arguments.createMap();
        info.putInt("status", resp.code());
        info.putString("state", "2");
        info.putString("taskId", this.taskId);
        info.putBoolean("timeout", this.timeout);
        WritableMap headers = Arguments.createMap();
        for (int i = 0; i < resp.headers().size(); i++) {
            headers.putString(resp.headers().name(i), resp.headers().value(i));
        }
        WritableArray redirectList = Arguments.createArray();
        Iterator it = this.redirects.iterator();
        while (it.hasNext()) {
            redirectList.pushString((String) it.next());
        }
        info.putArray("redirects", redirectList);
        info.putMap("headers", headers);
        Headers h = resp.headers();
        if (isBlobResp) {
            info.putString("respType", "blob");
        } else if (getHeaderIgnoreCases(h, "content-type").equalsIgnoreCase("text/")) {
            info.putString("respType", ReactTextShadowNode.PROP_TEXT);
        } else if (getHeaderIgnoreCases(h, "content-type").contains("application/json")) {
            info.putString("respType", "json");
        } else {
            info.putString("respType", "");
        }
        return info;
    }

    private boolean isBlobResponse(Response resp) {
        boolean isJSON;
        String ctype = getHeaderIgnoreCases(resp.headers(), "Content-Type");
        boolean isText;
        if (ctype.equalsIgnoreCase("text/")) {
            isText = false;
        } else {
            isText = true;
        }
        if (ctype.equalsIgnoreCase("application/json")) {
            isJSON = false;
        } else {
            isJSON = true;
        }
        boolean isCustomBinary = false;
        if (this.options.binaryContentTypes != null) {
            for (int i = 0; i < this.options.binaryContentTypes.size(); i++) {
                if (ctype.toLowerCase().contains(this.options.binaryContentTypes.getString(i).toLowerCase())) {
                    isCustomBinary = true;
                    break;
                }
            }
        }
        if ((isJSON || isText) && !isCustomBinary) {
            return false;
        }
        return true;
    }

    private String getHeaderIgnoreCases(Headers headers, String field) {
        String val = headers.get(field);
        if (val != null) {
            return val;
        }
        return headers.get(field.toLowerCase()) == null ? "" : headers.get(field.toLowerCase());
    }

    private String getHeaderIgnoreCases(HashMap<String, String> headers, String field) {
        String val = (String) headers.get(field);
        if (val != null) {
            return val;
        }
        String lowerCasedValue = (String) headers.get(field.toLowerCase());
        if (lowerCasedValue == null) {
            lowerCasedValue = "";
        }
        return lowerCasedValue;
    }

    private void emitStateEvent(WritableMap args) {
        ((RCTDeviceEventEmitter) RNFetchBlob.RCTContext.getJSModule(RCTDeviceEventEmitter.class)).emit(RNFetchBlobConst.EVENT_HTTP_STATE, args);
    }

    public void onReceive(Context context, Intent intent) {
        if ("android.intent.action.DOWNLOAD_COMPLETE".equals(intent.getAction())) {
            Context appCtx = RNFetchBlob.RCTContext.getApplicationContext();
            if (intent.getExtras().getLong("extra_download_id") == this.downloadManagerId) {
                Query query = new Query();
                query.setFilterById(new long[]{this.downloadManagerId});
                DownloadManager dm = (DownloadManager) appCtx.getSystemService("download");
                dm.query(query);
                Cursor c = dm.query(query);
                String filePath = null;
                if (c.moveToFirst()) {
                    if (c.getInt(c.getColumnIndex("status")) == 16) {
                        this.callback.invoke(new Object[]{"Download manager failed to download from  " + this.url + ". Statu Code = " + statusCode, null, null});
                        return;
                    }
                    String contentUri = c.getString(c.getColumnIndex("local_uri"));
                    if (contentUri != null && this.options.addAndroidDownloads.hasKey("mime") && this.options.addAndroidDownloads.getString("mime").contains("image")) {
                        Uri uri = Uri.parse(contentUri);
                        Cursor cursor = appCtx.getContentResolver().query(uri, new String[]{"_data"}, null, null, null);
                        if (cursor != null) {
                            cursor.moveToFirst();
                            filePath = cursor.getString(0);
                        }
                    }
                }
                if (this.options.addAndroidDownloads.hasKey(RNFetchBlobConst.RNFB_RESPONSE_PATH)) {
                    try {
                        if (new File(this.options.addAndroidDownloads.getString(RNFetchBlobConst.RNFB_RESPONSE_PATH)).exists()) {
                            this.callback.invoke(new Object[]{null, RNFetchBlobConst.RNFB_RESPONSE_PATH, customDest});
                            return;
                        }
                        throw new Exception("Download manager download failed, the file does not downloaded to destination.");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        this.callback.invoke(new Object[]{ex.getLocalizedMessage(), null});
                    }
                } else if (filePath == null) {
                    this.callback.invoke(new Object[]{"Download manager could not resolve downloaded file path.", RNFetchBlobConst.RNFB_RESPONSE_PATH, null});
                } else {
                    this.callback.invoke(new Object[]{null, RNFetchBlobConst.RNFB_RESPONSE_PATH, filePath});
                }
            }
        }
    }

    public static Builder enableTls12OnPreLollipop(Builder client) {
        if (VERSION.SDK_INT >= 16 && VERSION.SDK_INT <= 19) {
            try {
                client.sslSocketFactory(new TLSSocketFactory());
                ConnectionSpec cs = new ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS).tlsVersions(new TlsVersion[]{TlsVersion.TLS_1_2}).build();
                List<ConnectionSpec> specs = new ArrayList();
                specs.add(cs);
                specs.add(ConnectionSpec.COMPATIBLE_TLS);
                specs.add(ConnectionSpec.CLEARTEXT);
                client.connectionSpecs(specs);
            } catch (Exception exc) {
                FLog.e("OkHttpClientProvider", "Error while enabling TLS 1.2", exc);
            }
        }
        return client;
    }
}
