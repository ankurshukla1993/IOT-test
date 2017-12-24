package com.RNFetchBlob;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.Intent;
import android.net.Uri;
import com.RNFetchBlob.RNFetchBlobProgressConfig.ReportType;
import com.facebook.react.bridge.ActivityEventListener;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.network.CookieJarContainer;
import com.facebook.react.modules.network.ForwardingCookieHandler;
import com.facebook.react.modules.network.OkHttpClientProvider;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;

public class RNFetchBlob extends ReactContextBaseJavaModule {
    public static boolean ActionViewVisible = false;
    static ReactApplicationContext RCTContext;
    static LinkedBlockingQueue<Runnable> fsTaskQueue = new LinkedBlockingQueue();
    static ThreadPoolExecutor fsThreadPool = new ThreadPoolExecutor(2, 10, 5000, TimeUnit.MILLISECONDS, taskQueue);
    static HashMap<Integer, Promise> promiseTable = new HashMap();
    static LinkedBlockingQueue<Runnable> taskQueue = new LinkedBlockingQueue();
    static ThreadPoolExecutor threadPool = new ThreadPoolExecutor(5, 10, 5000, TimeUnit.MILLISECONDS, taskQueue);
    private final OkHttpClient mClient = OkHttpClientProvider.getOkHttpClient();
    private final ForwardingCookieHandler mCookieHandler;
    private final CookieJarContainer mCookieJarContainer;

    class C05351 implements ActivityEventListener {
        C05351() {
        }

        public void onActivityResult(Activity activity, int requestCode, int resultCode, Intent data) {
            if (requestCode == RNFetchBlobConst.GET_CONTENT_INTENT.intValue() && resultCode == -1) {
                ((Promise) RNFetchBlob.promiseTable.get(RNFetchBlobConst.GET_CONTENT_INTENT)).resolve(data.getData().toString());
                RNFetchBlob.promiseTable.remove(RNFetchBlobConst.GET_CONTENT_INTENT);
            }
        }

        public void onNewIntent(Intent intent) {
        }
    }

    public RNFetchBlob(ReactApplicationContext reactContext) {
        super(reactContext);
        this.mCookieHandler = new ForwardingCookieHandler(reactContext);
        this.mCookieJarContainer = (CookieJarContainer) this.mClient.cookieJar();
        this.mCookieJarContainer.setCookieJar(new JavaNetCookieJar(this.mCookieHandler));
        RCTContext = reactContext;
        reactContext.addActivityEventListener(new C05351());
    }

    public String getName() {
        return "RNFetchBlob";
    }

    public Map<String, Object> getConstants() {
        return RNFetchBlobFS.getSystemfolders(getReactApplicationContext());
    }

    @ReactMethod
    public void createFile(String path, String content, String encode, Callback callback) {
        final String str = path;
        final String str2 = content;
        final String str3 = encode;
        final Callback callback2 = callback;
        threadPool.execute(new Runnable() {
            public void run() {
                RNFetchBlobFS.createFile(str, str2, str3, callback2);
            }
        });
    }

    @ReactMethod
    public void actionViewIntent(String path, String mime, final Promise promise) {
        try {
            Intent intent = new Intent("android.intent.action.VIEW").setDataAndType(Uri.parse("file://" + path), mime);
            intent.setFlags(268435456);
            getReactApplicationContext().startActivity(intent);
            ActionViewVisible = true;
            RCTContext.addLifecycleEventListener(new LifecycleEventListener() {
                public void onHostResume() {
                    if (RNFetchBlob.ActionViewVisible) {
                        promise.resolve(null);
                    }
                    RNFetchBlob.RCTContext.removeLifecycleEventListener(this);
                }

                public void onHostPause() {
                }

                public void onHostDestroy() {
                }
            });
        } catch (Exception ex) {
            promise.reject(ex.getLocalizedMessage());
        }
    }

    @ReactMethod
    public void createFileASCII(final String path, final ReadableArray dataArray, final Callback callback) {
        threadPool.execute(new Runnable() {
            public void run() {
                RNFetchBlobFS.createFileASCII(path, dataArray, callback);
            }
        });
    }

    @ReactMethod
    public void writeArrayChunk(String streamId, ReadableArray dataArray, Callback callback) {
        RNFetchBlobFS.writeArrayChunk(streamId, dataArray, callback);
    }

    @ReactMethod
    public void unlink(String path, Callback callback) {
        RNFetchBlobFS.unlink(path, callback);
    }

    @ReactMethod
    public void mkdir(String path, Callback callback) {
        RNFetchBlobFS.mkdir(path, callback);
    }

    @ReactMethod
    public void exists(String path, Callback callback) {
        RNFetchBlobFS.exists(path, callback);
    }

    @ReactMethod
    public void cp(final String path, final String dest, final Callback callback) {
        threadPool.execute(new Runnable() {
            public void run() {
                RNFetchBlobFS.cp(path, dest, callback);
            }
        });
    }

    @ReactMethod
    public void mv(String path, String dest, Callback callback) {
        RNFetchBlobFS.mv(path, dest, callback);
    }

    @ReactMethod
    public void ls(String path, Callback callback) {
        RNFetchBlobFS.ls(path, callback);
    }

    @ReactMethod
    public void writeStream(String path, String encode, boolean append, Callback callback) {
        new RNFetchBlobFS(getReactApplicationContext()).writeStream(path, encode, append, callback);
    }

    @ReactMethod
    public void writeChunk(String streamId, String data, Callback callback) {
        RNFetchBlobFS.writeChunk(streamId, data, callback);
    }

    @ReactMethod
    public void closeStream(String streamId, Callback callback) {
        RNFetchBlobFS.closeStream(streamId, callback);
    }

    @ReactMethod
    public void removeSession(ReadableArray paths, Callback callback) {
        RNFetchBlobFS.removeSession(paths, callback);
    }

    @ReactMethod
    public void readFile(final String path, final String encoding, final Promise promise) {
        threadPool.execute(new Runnable() {
            public void run() {
                RNFetchBlobFS.readFile(path, encoding, promise);
            }
        });
    }

    @ReactMethod
    public void writeFileArray(String path, ReadableArray data, boolean append, Promise promise) {
        final String str = path;
        final ReadableArray readableArray = data;
        final boolean z = append;
        final Promise promise2 = promise;
        threadPool.execute(new Runnable() {
            public void run() {
                RNFetchBlobFS.writeFile(str, readableArray, z, promise2);
            }
        });
    }

    @ReactMethod
    public void writeFile(String path, String encoding, String data, boolean append, Promise promise) {
        final String str = path;
        final String str2 = encoding;
        final String str3 = data;
        final boolean z = append;
        final Promise promise2 = promise;
        threadPool.execute(new Runnable() {
            public void run() {
                RNFetchBlobFS.writeFile(str, str2, str3, z, promise2);
            }
        });
    }

    @ReactMethod
    public void lstat(String path, Callback callback) {
        RNFetchBlobFS.lstat(path, callback);
    }

    @ReactMethod
    public void stat(String path, Callback callback) {
        RNFetchBlobFS.stat(path, callback);
    }

    @ReactMethod
    public void scanFile(final ReadableArray pairs, final Callback callback) {
        final ReactApplicationContext ctx = getReactApplicationContext();
        threadPool.execute(new Runnable() {
            public void run() {
                int size = pairs.size();
                String[] p = new String[size];
                String[] m = new String[size];
                for (int i = 0; i < size; i++) {
                    ReadableMap pair = pairs.getMap(i);
                    if (pair.hasKey(RNFetchBlobConst.RNFB_RESPONSE_PATH)) {
                        p[i] = pair.getString(RNFetchBlobConst.RNFB_RESPONSE_PATH);
                        if (pair.hasKey("mime")) {
                            m[i] = pair.getString("mime");
                        } else {
                            m[i] = null;
                        }
                    }
                }
                new RNFetchBlobFS(ctx).scanFile(p, m, callback);
            }
        });
    }

    @ReactMethod
    public void readStream(String path, String encoding, int bufferSize, int tick, String streamId) {
        final ReactApplicationContext ctx = getReactApplicationContext();
        final String str = path;
        final String str2 = encoding;
        final int i = bufferSize;
        final int i2 = tick;
        final String str3 = streamId;
        fsThreadPool.execute(new Runnable() {
            public void run() {
                new RNFetchBlobFS(ctx).readStream(str, str2, i, i2, str3);
            }
        });
    }

    @ReactMethod
    public void cancelRequest(String taskId, Callback callback) {
        try {
            RNFetchBlobReq.cancelTask(taskId);
            callback.invoke(new Object[]{null, taskId});
        } catch (Exception ex) {
            callback.invoke(new Object[]{ex.getLocalizedMessage(), null});
        }
    }

    @ReactMethod
    public void slice(String src, String dest, int start, int end, Promise promise) {
        RNFetchBlobFS.slice(src, dest, start, end, "", promise);
    }

    @ReactMethod
    public void enableProgressReport(String taskId, int interval, int count) {
        RNFetchBlobReq.progressReport.put(taskId, new RNFetchBlobProgressConfig(true, interval, count, ReportType.Download));
    }

    @ReactMethod
    public void df(final Callback callback) {
        fsThreadPool.execute(new Runnable() {
            public void run() {
                RNFetchBlobFS.df(callback);
            }
        });
    }

    @ReactMethod
    public void enableUploadProgressReport(String taskId, int interval, int count) {
        RNFetchBlobReq.uploadProgressReport.put(taskId, new RNFetchBlobProgressConfig(true, interval, count, ReportType.Upload));
    }

    @ReactMethod
    public void fetchBlob(ReadableMap options, String taskId, String method, String url, ReadableMap headers, String body, Callback callback) {
        new RNFetchBlobReq(options, taskId, method, url, headers, body, null, this.mClient, callback).run();
    }

    @ReactMethod
    public void fetchBlobForm(ReadableMap options, String taskId, String method, String url, ReadableMap headers, ReadableArray body, Callback callback) {
        new RNFetchBlobReq(options, taskId, method, url, headers, null, body, this.mClient, callback).run();
    }

    @ReactMethod
    public void getContentIntent(String mime, Promise promise) {
        Intent i = new Intent("android.intent.action.GET_CONTENT");
        if (mime != null) {
            i.setType(mime);
        } else {
            i.setType("*/*");
        }
        promiseTable.put(RNFetchBlobConst.GET_CONTENT_INTENT, promise);
        getReactApplicationContext().startActivityForResult(i, RNFetchBlobConst.GET_CONTENT_INTENT.intValue(), null);
    }

    @ReactMethod
    public void addCompleteDownload(ReadableMap config, Promise promise) {
        String str = null;
        boolean z = true;
        ReactApplicationContext reactApplicationContext = RCTContext;
        ReactApplicationContext reactApplicationContext2 = RCTContext;
        DownloadManager dm = (DownloadManager) reactApplicationContext.getSystemService("download");
        String path = RNFetchBlobFS.normalizePath(config.getString(RNFetchBlobConst.RNFB_RESPONSE_PATH));
        if (path == null) {
            promise.reject("RNFetchblob.addCompleteDownload can not resolve URI:" + config.getString(RNFetchBlobConst.RNFB_RESPONSE_PATH), "RNFetchblob.addCompleteDownload can not resolve URI:" + path);
            return;
        }
        try {
            WritableMap stat = RNFetchBlobFS.statFile(path);
            String string = config.hasKey("title") ? config.getString("title") : "";
            String string2 = config.hasKey("description") ? config.getString("description") : "";
            if (config.hasKey("mime")) {
                str = config.getString("mime");
            }
            long longValue = Long.valueOf(stat.getString("size")).longValue();
            if (!(config.hasKey("showNotification") && config.getBoolean("showNotification"))) {
                z = false;
            }
            dm.addCompletedDownload(string, string2, true, str, path, longValue, z);
            promise.resolve(null);
        } catch (Exception ex) {
            promise.reject("RNFetchblob.addCompleteDownload failed", ex.getStackTrace().toString());
        }
    }
}
