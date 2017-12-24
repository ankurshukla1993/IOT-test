package com.RNFetchBlob;

import android.content.res.AssetFileDescriptor;
import android.media.MediaScannerConnection;
import android.media.MediaScannerConnection.OnScanCompletedListener;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.StatFs;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.util.Base64;
import com.RNFetchBlob.Utils.PathResolver;
import com.facebook.common.util.UriUtil;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule.RCTDeviceEventEmitter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class RNFetchBlobFS {
    static HashMap<String, RNFetchBlobFS> fileStreams = new HashMap();
    boolean append = false;
    RCTDeviceEventEmitter emitter;
    String encoding = RNFetchBlobConst.RNFB_RESPONSE_BASE64;
    ReactApplicationContext mCtx;
    OutputStream writeStreamInstance = null;

    RNFetchBlobFS(ReactApplicationContext ctx) {
        this.mCtx = ctx;
        this.emitter = (RCTDeviceEventEmitter) ctx.getJSModule(RCTDeviceEventEmitter.class);
    }

    static String getExternalFilePath(ReactApplicationContext ctx, String taskId, RNFetchBlobConfig config) {
        if (config.path != null) {
            return config.path;
        }
        if (!config.fileCache.booleanValue() || config.appendExt == null) {
            return getTmpPath(ctx, taskId);
        }
        return getTmpPath(ctx, taskId) + "." + config.appendExt;
    }

    public static void writeFile(String path, String encoding, String data, boolean append, Promise promise) {
        try {
            int written;
            File f = new File(path);
            File dir = f.getParentFile();
            if (!dir.exists()) {
                dir.mkdirs();
            }
            FileOutputStream fout = new FileOutputStream(f, append);
            if (encoding.equalsIgnoreCase(RNFetchBlobConst.DATA_ENCODE_URI)) {
                data = normalizePath(data);
                File src = new File(data);
                if (src.exists()) {
                    FileInputStream fin = new FileInputStream(src);
                    byte[] buffer = new byte[10240];
                    written = 0;
                    while (true) {
                        int read = fin.read(buffer);
                        if (read <= 0) {
                            break;
                        }
                        fout.write(buffer, 0, read);
                        written += read;
                    }
                    fin.close();
                } else {
                    promise.reject("RNfetchBlob writeFileError", "source file : " + data + "not exists");
                    fout.close();
                    return;
                }
            }
            byte[] bytes = stringToBytes(data, encoding);
            fout.write(bytes);
            written = bytes.length;
            fout.close();
            promise.resolve(Integer.valueOf(written));
        } catch (Exception e) {
            promise.reject("RNFetchBlob writeFileError", e.getLocalizedMessage());
        }
    }

    public static void writeFile(String path, ReadableArray data, boolean append, Promise promise) {
        try {
            File f = new File(path);
            File dir = f.getParentFile();
            if (!dir.exists()) {
                dir.mkdirs();
            }
            FileOutputStream os = new FileOutputStream(f, append);
            byte[] bytes = new byte[data.size()];
            for (int i = 0; i < data.size(); i++) {
                bytes[i] = (byte) data.getInt(i);
            }
            os.write(bytes);
            os.close();
            promise.resolve(Integer.valueOf(data.size()));
        } catch (Exception e) {
            promise.reject("RNFetchBlob writeFileError", e.getLocalizedMessage());
        }
    }

    public static void readFile(String path, String encoding, Promise promise) {
        byte[] bytes;
        String toLowerCase;
        Object obj;
        WritableArray asciiResult;
        String resolved = normalizePath(path);
        if (resolved != null) {
            path = resolved;
        }
        if (resolved != null) {
            try {
                if (resolved.startsWith(RNFetchBlobConst.FILE_PREFIX_BUNDLE_ASSET)) {
                    String assetName = path.replace(RNFetchBlobConst.FILE_PREFIX_BUNDLE_ASSET, "");
                    long length = RNFetchBlob.RCTContext.getAssets().openFd(assetName).getLength();
                    bytes = new byte[((int) length)];
                    InputStream in = RNFetchBlob.RCTContext.getAssets().open(assetName);
                    in.read(bytes, 0, (int) length);
                    in.close();
                    toLowerCase = encoding.toLowerCase();
                    obj = -1;
                    switch (toLowerCase.hashCode()) {
                        case -1396204209:
                            if (toLowerCase.equals(RNFetchBlobConst.RNFB_RESPONSE_BASE64)) {
                                obj = null;
                                break;
                            }
                            break;
                        case 3600241:
                            if (toLowerCase.equals(RNFetchBlobConst.RNFB_RESPONSE_UTF8)) {
                                obj = 2;
                                break;
                            }
                            break;
                        case 93106001:
                            if (toLowerCase.equals("ascii")) {
                                obj = 1;
                                break;
                            }
                            break;
                    }
                    switch (obj) {
                        case null:
                            promise.resolve(Base64.encodeToString(bytes, 2));
                            return;
                        case 1:
                            asciiResult = Arguments.createArray();
                            for (byte b : bytes) {
                                asciiResult.pushInt(b);
                            }
                            promise.resolve(asciiResult);
                            return;
                        case 2:
                            promise.resolve(new String(bytes));
                            return;
                        default:
                            promise.resolve(new String(bytes));
                            return;
                    }
                }
            } catch (Exception err) {
                promise.reject("ReadFile Error", err.getLocalizedMessage());
                return;
            }
        }
        if (resolved == null) {
            in = RNFetchBlob.RCTContext.getContentResolver().openInputStream(Uri.parse(path));
            bytes = new byte[in.available()];
            in.read(bytes);
            in.close();
        } else {
            File f = new File(path);
            bytes = new byte[((int) f.length())];
            FileInputStream in2 = new FileInputStream(f);
            in2.read(bytes);
            in2.close();
        }
        toLowerCase = encoding.toLowerCase();
        obj = -1;
        switch (toLowerCase.hashCode()) {
            case -1396204209:
                if (toLowerCase.equals(RNFetchBlobConst.RNFB_RESPONSE_BASE64)) {
                    obj = null;
                    break;
                }
                break;
            case 3600241:
                if (toLowerCase.equals(RNFetchBlobConst.RNFB_RESPONSE_UTF8)) {
                    obj = 2;
                    break;
                }
                break;
            case 93106001:
                if (toLowerCase.equals("ascii")) {
                    obj = 1;
                    break;
                }
                break;
        }
        switch (obj) {
            case null:
                promise.resolve(Base64.encodeToString(bytes, 2));
                return;
            case 1:
                asciiResult = Arguments.createArray();
                while (r10 < r11) {
                    asciiResult.pushInt(b);
                }
                promise.resolve(asciiResult);
                return;
            case 2:
                promise.resolve(new String(bytes));
                return;
            default:
                promise.resolve(new String(bytes));
                return;
        }
    }

    public static Map<String, Object> getSystemfolders(ReactApplicationContext ctx) {
        Map<String, Object> res = new HashMap();
        res.put("DocumentDir", ctx.getFilesDir().getAbsolutePath());
        res.put("CacheDir", ctx.getCacheDir().getAbsolutePath());
        res.put("DCIMDir", Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath());
        res.put("PictureDir", Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath());
        res.put("MusicDir", Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC).getAbsolutePath());
        res.put("DownloadDir", Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath());
        res.put("MovieDir", Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES).getAbsolutePath());
        res.put("RingtoneDir", Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_RINGTONES).getAbsolutePath());
        if (Environment.getExternalStorageState().equals("mounted")) {
            res.put("SDCardDir", Environment.getExternalStorageDirectory().getAbsolutePath());
            res.put("SDCardApplicationDir", ctx.getExternalFilesDir(null).getParentFile().getAbsolutePath());
        }
        res.put("MainBundleDir", ctx.getApplicationInfo().dataDir);
        return res;
    }

    public static String getTmpPath(ReactApplicationContext ctx, String taskId) {
        return RNFetchBlob.RCTContext.getFilesDir() + "/RNFetchBlobTmp_" + taskId;
    }

    public void readStream(String path, String encoding, int bufferSize, int tick, String streamId) {
        String resolved = normalizePath(path);
        if (resolved != null) {
            path = resolved;
        }
        try {
            InputStream fs;
            byte[] buffer;
            boolean error;
            CharsetEncoder encoder;
            int cursor;
            WritableArray chunk;
            int i;
            byte[] copy;
            int chunkSize = encoding.equalsIgnoreCase(RNFetchBlobConst.RNFB_RESPONSE_BASE64) ? 4095 : 4096;
            if (bufferSize > 0) {
                chunkSize = bufferSize;
            }
            if (resolved != null) {
                if (path.startsWith(RNFetchBlobConst.FILE_PREFIX_BUNDLE_ASSET)) {
                    fs = RNFetchBlob.RCTContext.getAssets().open(path.replace(RNFetchBlobConst.FILE_PREFIX_BUNDLE_ASSET, ""));
                    buffer = new byte[chunkSize];
                    error = false;
                    if (encoding.equalsIgnoreCase(RNFetchBlobConst.RNFB_RESPONSE_UTF8)) {
                        encoder = Charset.forName("UTF-8").newEncoder();
                        while (true) {
                            cursor = fs.read(buffer);
                            if (cursor != -1) {
                                break;
                            }
                            encoder.encode(ByteBuffer.wrap(buffer).asCharBuffer());
                            emitStreamEvent(streamId, "data", new String(buffer, 0, cursor));
                            if (tick > 0) {
                                SystemClock.sleep((long) tick);
                            }
                        }
                    } else {
                        if (encoding.equalsIgnoreCase("ascii")) {
                            while (true) {
                                cursor = fs.read(buffer);
                                if (cursor != -1) {
                                    break;
                                }
                                chunk = Arguments.createArray();
                                for (i = 0; i < cursor; i++) {
                                    chunk.pushInt(buffer[i]);
                                }
                                emitStreamEvent(streamId, "data", chunk);
                                if (tick > 0) {
                                    SystemClock.sleep((long) tick);
                                }
                            }
                        } else {
                            if (encoding.equalsIgnoreCase(RNFetchBlobConst.RNFB_RESPONSE_BASE64)) {
                                while (true) {
                                    cursor = fs.read(buffer);
                                    if (cursor != -1) {
                                        break;
                                    }
                                    if (cursor >= chunkSize) {
                                        copy = new byte[cursor];
                                        for (i = 0; i < cursor; i++) {
                                            copy[i] = buffer[i];
                                        }
                                        emitStreamEvent(streamId, "data", Base64.encodeToString(copy, 2));
                                    } else {
                                        emitStreamEvent(streamId, "data", Base64.encodeToString(buffer, 2));
                                    }
                                    if (tick > 0) {
                                        SystemClock.sleep((long) tick);
                                    }
                                }
                            } else {
                                emitStreamEvent(streamId, "error", "unrecognized encoding `" + encoding + "`");
                                error = true;
                            }
                        }
                    }
                    if (!error) {
                        emitStreamEvent(streamId, "end", "");
                    }
                    fs.close();
                }
            }
            if (resolved == null) {
                fs = RNFetchBlob.RCTContext.getContentResolver().openInputStream(Uri.parse(path));
            } else {
                fs = new FileInputStream(new File(path));
            }
            buffer = new byte[chunkSize];
            error = false;
            if (encoding.equalsIgnoreCase(RNFetchBlobConst.RNFB_RESPONSE_UTF8)) {
                encoder = Charset.forName("UTF-8").newEncoder();
                while (true) {
                    cursor = fs.read(buffer);
                    if (cursor != -1) {
                        break;
                    }
                    encoder.encode(ByteBuffer.wrap(buffer).asCharBuffer());
                    emitStreamEvent(streamId, "data", new String(buffer, 0, cursor));
                    if (tick > 0) {
                        SystemClock.sleep((long) tick);
                    }
                }
            } else {
                if (encoding.equalsIgnoreCase("ascii")) {
                    while (true) {
                        cursor = fs.read(buffer);
                        if (cursor != -1) {
                            break;
                        }
                        chunk = Arguments.createArray();
                        for (i = 0; i < cursor; i++) {
                            chunk.pushInt(buffer[i]);
                        }
                        emitStreamEvent(streamId, "data", chunk);
                        if (tick > 0) {
                            SystemClock.sleep((long) tick);
                        }
                    }
                } else {
                    if (encoding.equalsIgnoreCase(RNFetchBlobConst.RNFB_RESPONSE_BASE64)) {
                        while (true) {
                            cursor = fs.read(buffer);
                            if (cursor != -1) {
                                break;
                            }
                            if (cursor >= chunkSize) {
                                emitStreamEvent(streamId, "data", Base64.encodeToString(buffer, 2));
                            } else {
                                copy = new byte[cursor];
                                for (i = 0; i < cursor; i++) {
                                    copy[i] = buffer[i];
                                }
                                emitStreamEvent(streamId, "data", Base64.encodeToString(copy, 2));
                            }
                            if (tick > 0) {
                                SystemClock.sleep((long) tick);
                            }
                        }
                    } else {
                        emitStreamEvent(streamId, "error", "unrecognized encoding `" + encoding + "`");
                        error = true;
                    }
                }
            }
            if (error) {
                emitStreamEvent(streamId, "end", "");
            }
            fs.close();
        } catch (Exception err) {
            emitStreamEvent(streamId, "warn", "Failed to convert data to " + encoding + " encoded string, this might due to the source data is not able to convert using this encoding.");
            err.printStackTrace();
        }
    }

    public void writeStream(String path, String encoding, boolean append, Callback callback) {
        File dest = new File(path);
        if (!dest.exists() || dest.isDirectory()) {
            callback.invoke(new Object[]{"write stream error: target path `" + path + "` may not exists or it's a folder"});
            return;
        }
        try {
            OutputStream fs = new FileOutputStream(path, append);
            this.encoding = encoding;
            this.append = append;
            fileStreams.put(UUID.randomUUID().toString(), this);
            this.writeStreamInstance = fs;
            callback.invoke(new Object[]{null, streamId});
        } catch (Exception err) {
            callback.invoke(new Object[]{"write stream error: failed to create write stream at path `" + path + "` " + err.getLocalizedMessage()});
        }
    }

    static void writeChunk(String streamId, String data, Callback callback) {
        RNFetchBlobFS fs = (RNFetchBlobFS) fileStreams.get(streamId);
        try {
            fs.writeStreamInstance.write(stringToBytes(data, fs.encoding));
            callback.invoke(new Object[0]);
        } catch (Exception e) {
            callback.invoke(new Object[]{e.getLocalizedMessage()});
        }
    }

    static void writeArrayChunk(String streamId, ReadableArray data, Callback callback) {
        try {
            OutputStream stream = ((RNFetchBlobFS) fileStreams.get(streamId)).writeStreamInstance;
            byte[] chunk = new byte[data.size()];
            for (int i = 0; i < data.size(); i++) {
                chunk[i] = (byte) data.getInt(i);
            }
            stream.write(chunk);
            callback.invoke(new Object[0]);
        } catch (Exception e) {
            callback.invoke(new Object[]{e.getLocalizedMessage()});
        }
    }

    static void closeStream(String streamId, Callback callback) {
        try {
            OutputStream stream = ((RNFetchBlobFS) fileStreams.get(streamId)).writeStreamInstance;
            fileStreams.remove(streamId);
            stream.close();
            callback.invoke(new Object[0]);
        } catch (Exception err) {
            callback.invoke(new Object[]{err.getLocalizedMessage()});
        }
    }

    static void unlink(String path, Callback callback) {
        try {
            deleteRecursive(new File(path));
            callback.invoke(new Object[]{null, Boolean.valueOf(true)});
        } catch (Exception err) {
            if (err != null) {
                callback.invoke(new Object[]{err.getLocalizedMessage(), Boolean.valueOf(false)});
            }
        }
    }

    static void deleteRecursive(File fileOrDirectory) {
        if (fileOrDirectory.isDirectory()) {
            for (File child : fileOrDirectory.listFiles()) {
                deleteRecursive(child);
            }
        }
        fileOrDirectory.delete();
    }

    static void mkdir(String path, Callback callback) {
        File dest = new File(path);
        if (dest.exists()) {
            callback.invoke(new Object[]{"mkdir error: failed to create folder at `" + path + "` folder already exists"});
            return;
        }
        dest.mkdirs();
        callback.invoke(new Object[0]);
    }

    static void cp(String path, String dest, Callback callback) {
        Exception err;
        Throwable th;
        path = normalizePath(path);
        InputStream in = null;
        OutputStream outputStream = null;
        try {
            if (isPathExists(path)) {
                if (!new File(dest).exists()) {
                    new File(dest).createNewFile();
                }
                in = inputStreamFromPath(path);
                OutputStream out = new FileOutputStream(dest);
                try {
                    byte[] buf = new byte[10240];
                    while (true) {
                        int len = in.read(buf);
                        if (len <= 0) {
                            break;
                        }
                        out.write(buf, 0, len);
                    }
                    if (in != null) {
                        try {
                            in.close();
                        } catch (Exception e) {
                            callback.invoke(new Object[]{e.getLocalizedMessage()});
                            outputStream = out;
                            return;
                        }
                    }
                    if (out != null) {
                        out.close();
                    }
                    callback.invoke(new Object[0]);
                    outputStream = out;
                    return;
                } catch (Exception e2) {
                    err = e2;
                    outputStream = out;
                    try {
                        callback.invoke(new Object[]{err.getLocalizedMessage()});
                        if (in != null) {
                            try {
                                in.close();
                            } catch (Exception e3) {
                                callback.invoke(new Object[]{e3.getLocalizedMessage()});
                                return;
                            }
                        }
                        if (outputStream != null) {
                            outputStream.close();
                        }
                        callback.invoke(new Object[0]);
                    } catch (Throwable th2) {
                        th = th2;
                        if (in != null) {
                            try {
                                in.close();
                            } catch (Exception e32) {
                                callback.invoke(new Object[]{e32.getLocalizedMessage()});
                                throw th;
                            }
                        }
                        if (outputStream != null) {
                            outputStream.close();
                        }
                        callback.invoke(new Object[0]);
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    outputStream = out;
                    if (in != null) {
                        in.close();
                    }
                    if (outputStream != null) {
                        outputStream.close();
                    }
                    callback.invoke(new Object[0]);
                    throw th;
                }
            }
            callback.invoke(new Object[]{"cp error: source file at path`" + path + "` not exists"});
            if (in != null) {
                try {
                    in.close();
                } catch (Exception e322) {
                    callback.invoke(new Object[]{e322.getLocalizedMessage()});
                    return;
                }
            }
            if (outputStream != null) {
                outputStream.close();
            }
            callback.invoke(new Object[0]);
        } catch (Exception e4) {
            err = e4;
            callback.invoke(new Object[]{err.getLocalizedMessage()});
            if (in != null) {
                in.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
            callback.invoke(new Object[0]);
        }
    }

    static void mv(String path, String dest, Callback callback) {
        File src = new File(path);
        if (src.exists()) {
            src.renameTo(new File(dest));
            callback.invoke(new Object[0]);
            return;
        }
        callback.invoke(new Object[]{"mv error: source file at path `" + path + "` does not exists"});
    }

    static void exists(String path, Callback callback) {
        if (isAsset(path)) {
            try {
                AssetFileDescriptor fd = RNFetchBlob.RCTContext.getAssets().openFd(path.replace(RNFetchBlobConst.FILE_PREFIX_BUNDLE_ASSET, ""));
                callback.invoke(new Object[]{Boolean.valueOf(true), Boolean.valueOf(false)});
                return;
            } catch (IOException e) {
                callback.invoke(new Object[]{Boolean.valueOf(false), Boolean.valueOf(false)});
                return;
            }
        }
        path = normalizePath(path);
        boolean exist = new File(path).exists();
        boolean isDir = new File(path).isDirectory();
        callback.invoke(new Object[]{Boolean.valueOf(exist), Boolean.valueOf(isDir)});
    }

    static void ls(String path, Callback callback) {
        path = normalizePath(path);
        File src = new File(path);
        if (src.exists() && src.isDirectory()) {
            String[] files = new File(path).list();
            WritableArray arg = Arguments.createArray();
            for (String i : files) {
                arg.pushString(i);
            }
            callback.invoke(new Object[]{null, arg});
            return;
        }
        callback.invoke(new Object[]{"ls error: failed to list path `" + path + "` for it is not exist or it is not a folder"});
    }

    public static void slice(String src, String dest, int start, int end, String encode, Promise promise) {
        try {
            src = normalizePath(src);
            File file = new File(src);
            if (file.exists()) {
                long expected = Math.min(file.length(), (long) end) - ((long) start);
                long now = 0;
                FileInputStream in = new FileInputStream(new File(src));
                FileOutputStream out = new FileOutputStream(new File(dest));
                in.skip((long) start);
                byte[] buffer = new byte[10240];
                while (now < expected) {
                    long read = (long) in.read(buffer, 0, 10240);
                    long remain = expected - now;
                    if (read <= 0) {
                        break;
                    }
                    out.write(buffer, 0, (int) Math.min(remain, read));
                    now += read;
                }
                in.close();
                out.flush();
                out.close();
                promise.resolve(dest);
                return;
            }
            promise.reject("RNFetchBlob.slice error", "source file : " + src + " not exists");
        } catch (Exception e) {
            e.printStackTrace();
            promise.reject(e.getLocalizedMessage());
        }
    }

    static void lstat(String path, final Callback callback) {
        path = normalizePath(path);
        new AsyncTask<String, Integer, Integer>() {
            protected Integer doInBackground(String... args) {
                WritableArray res = Arguments.createArray();
                if (args[0] == null) {
                    callback.invoke(new Object[]{"lstat error: the path specified for lstat is either `null` or `undefined`."});
                    return Integer.valueOf(0);
                }
                File src = new File(args[0]);
                if (src.exists()) {
                    if (src.isDirectory()) {
                        for (String p : src.list()) {
                            res.pushMap(RNFetchBlobFS.statFile(src.getPath() + "/" + p));
                        }
                    } else {
                        res.pushMap(RNFetchBlobFS.statFile(src.getAbsolutePath()));
                    }
                    callback.invoke(new Object[]{null, res});
                    return Integer.valueOf(0);
                }
                callback.invoke(new Object[]{"lstat error: failed to list path `" + args[0] + "` for it is not exist or it is not a folder"});
                return Integer.valueOf(0);
            }
        }.execute(new String[]{path});
    }

    static void stat(String path, Callback callback) {
        try {
            if (statFile(normalizePath(path)) == null) {
                callback.invoke(new Object[]{"stat error: failed to list path `" + normalizePath(path) + "` for it is not exist or it is not a folder", null});
                return;
            }
            callback.invoke(new Object[]{null, statFile(normalizePath(path))});
        } catch (Exception err) {
            callback.invoke(new Object[]{err.getLocalizedMessage()});
        }
    }

    static WritableMap statFile(String path) {
        try {
            path = normalizePath(path);
            WritableMap stat = Arguments.createMap();
            if (isAsset(path)) {
                String name = path.replace(RNFetchBlobConst.FILE_PREFIX_BUNDLE_ASSET, "");
                AssetFileDescriptor fd = RNFetchBlob.RCTContext.getAssets().openFd(name);
                stat.putString("filename", name);
                stat.putString(RNFetchBlobConst.RNFB_RESPONSE_PATH, path);
                stat.putString("type", UriUtil.LOCAL_ASSET_SCHEME);
                stat.putString("size", String.valueOf(fd.getLength()));
                stat.putInt("lastModified", 0);
                return stat;
            }
            File target = new File(path);
            if (!target.exists()) {
                return null;
            }
            stat.putString("filename", target.getName());
            stat.putString(RNFetchBlobConst.RNFB_RESPONSE_PATH, target.getPath());
            stat.putString("type", target.isDirectory() ? "directory" : UriUtil.LOCAL_FILE_SCHEME);
            stat.putString("size", String.valueOf(target.length()));
            stat.putString("lastModified", String.valueOf(target.lastModified()));
            return stat;
        } catch (Exception e) {
            return null;
        }
    }

    void scanFile(String[] path, String[] mimes, final Callback callback) {
        try {
            MediaScannerConnection.scanFile(this.mCtx, path, mimes, new OnScanCompletedListener() {
                public void onScanCompleted(String s, Uri uri) {
                    callback.invoke(new Object[]{null, Boolean.valueOf(true)});
                }
            });
        } catch (Exception err) {
            callback.invoke(new Object[]{err.getLocalizedMessage(), null});
        }
    }

    static void createFile(String path, String data, String encoding, Callback callback) {
        try {
            File dest = new File(path);
            boolean created = dest.createNewFile();
            if (encoding.equals(RNFetchBlobConst.DATA_ENCODE_URI)) {
                File src = new File(data.replace(RNFetchBlobConst.FILE_PREFIX, ""));
                if (src.exists()) {
                    FileInputStream fin = new FileInputStream(src);
                    OutputStream ostream = new FileOutputStream(dest);
                    byte[] buffer = new byte[10240];
                    for (int read = fin.read(buffer); read > 0; read = fin.read(buffer)) {
                        ostream.write(buffer, 0, read);
                    }
                    fin.close();
                    ostream.close();
                } else {
                    callback.invoke(new Object[]{"RNfetchBlob writeFileError", "source file : " + data + "not exists"});
                    return;
                }
            } else if (created) {
                new FileOutputStream(dest).write(stringToBytes(data, encoding));
            } else {
                callback.invoke(new Object[]{"create file error: failed to create file at path `" + path + "` for its parent path may not exists, or the file already exists. If you intended to overwrite the existing file use fs.writeFile instead."});
                return;
            }
            callback.invoke(new Object[]{null, path});
        } catch (Exception err) {
            callback.invoke(new Object[]{err.getLocalizedMessage()});
        }
    }

    static void createFileASCII(String path, ReadableArray data, Callback callback) {
        try {
            File dest = new File(path);
            if (dest.exists()) {
                callback.invoke(new Object[]{"create file error: failed to create file at path `" + path + "`, file already exists."});
            } else if (dest.createNewFile()) {
                OutputStream ostream = new FileOutputStream(dest);
                byte[] chunk = new byte[data.size()];
                for (int i = 0; i < data.size(); i++) {
                    chunk[i] = (byte) data.getInt(i);
                }
                ostream.write(chunk);
                callback.invoke(new Object[]{null, path});
            } else {
                callback.invoke(new Object[]{"create file error: failed to create file at path `" + path + "` for its parent path may not exists"});
            }
        } catch (Exception err) {
            callback.invoke(new Object[]{err.getLocalizedMessage()});
        }
    }

    static void df(Callback callback) {
        StatFs stat = new StatFs(Environment.getDataDirectory().getPath());
        WritableMap args = Arguments.createMap();
        if (VERSION.SDK_INT >= 18) {
            args.putString("internal_free", String.valueOf(stat.getFreeBytes()));
            args.putString("internal_total", String.valueOf(stat.getTotalBytes()));
            StatFs statEx = new StatFs(Environment.getExternalStorageDirectory().getPath());
            args.putString("external_free", String.valueOf(statEx.getFreeBytes()));
            args.putString("external_total", String.valueOf(statEx.getTotalBytes()));
        }
        callback.invoke(new Object[]{null, args});
    }

    static void removeSession(ReadableArray paths, final Callback callback) {
        new AsyncTask<ReadableArray, Integer, Integer>() {
            protected Integer doInBackground(ReadableArray... paths) {
                int i = 0;
                while (i < paths[0].size()) {
                    try {
                        File f = new File(paths[0].getString(i));
                        if (f.exists()) {
                            f.delete();
                        }
                        i++;
                    } catch (Exception err) {
                        callback.invoke(new Object[]{err.getLocalizedMessage()});
                    }
                }
                callback.invoke(new Object[]{null, Boolean.valueOf(true)});
                return Integer.valueOf(paths[0].size());
            }
        }.execute(new ReadableArray[]{paths});
    }

    private static byte[] stringToBytes(String data, String encoding) {
        if (encoding.equalsIgnoreCase("ascii")) {
            return data.getBytes(Charset.forName("US-ASCII"));
        }
        if (encoding.toLowerCase().contains(RNFetchBlobConst.RNFB_RESPONSE_BASE64)) {
            return Base64.decode(data, 2);
        }
        if (encoding.equalsIgnoreCase(RNFetchBlobConst.RNFB_RESPONSE_UTF8)) {
            return data.getBytes(Charset.forName("UTF-8"));
        }
        return data.getBytes(Charset.forName("US-ASCII"));
    }

    private void emitStreamEvent(String streamName, String event, String data) {
        WritableMap eventData = Arguments.createMap();
        eventData.putString(NotificationCompat.CATEGORY_EVENT, event);
        eventData.putString("detail", data);
        this.emitter.emit(streamName, eventData);
    }

    private void emitStreamEvent(String streamName, String event, WritableArray data) {
        WritableMap eventData = Arguments.createMap();
        eventData.putString(NotificationCompat.CATEGORY_EVENT, event);
        eventData.putArray("detail", data);
        this.emitter.emit(streamName, eventData);
    }

    void emitFSData(String taskId, String event, String data) {
        WritableMap eventData = Arguments.createMap();
        eventData.putString(NotificationCompat.CATEGORY_EVENT, event);
        eventData.putString("detail", data);
        this.emitter.emit("RNFetchBlobStream" + taskId, eventData);
    }

    static InputStream inputStreamFromPath(String path) throws IOException {
        if (path.startsWith(RNFetchBlobConst.FILE_PREFIX_BUNDLE_ASSET)) {
            return RNFetchBlob.RCTContext.getAssets().open(path.replace(RNFetchBlobConst.FILE_PREFIX_BUNDLE_ASSET, ""));
        }
        return new FileInputStream(new File(path));
    }

    static boolean isPathExists(String path) {
        if (!path.startsWith(RNFetchBlobConst.FILE_PREFIX_BUNDLE_ASSET)) {
            return new File(path).exists();
        }
        try {
            RNFetchBlob.RCTContext.getAssets().open(path.replace(RNFetchBlobConst.FILE_PREFIX_BUNDLE_ASSET, ""));
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    static boolean isAsset(String path) {
        if (path != null) {
            return path.startsWith(RNFetchBlobConst.FILE_PREFIX_BUNDLE_ASSET);
        }
        return false;
    }

    static String normalizePath(String path) {
        if (path == null) {
            return null;
        }
        if (!path.matches("\\w+\\:.*")) {
            return path;
        }
        if (path.startsWith("file://")) {
            return path.replace("file://", "");
        }
        return !path.startsWith(RNFetchBlobConst.FILE_PREFIX_BUNDLE_ASSET) ? PathResolver.getRealPathFromURI(RNFetchBlob.RCTContext, Uri.parse(path)) : path;
    }
}
