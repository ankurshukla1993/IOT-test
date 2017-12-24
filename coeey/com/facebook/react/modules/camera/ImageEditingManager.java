package com.facebook.react.modules.camera;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.AsyncTask;
import android.text.TextUtils;
import com.RNFetchBlob.RNFetchBlobConst;
import com.facebook.common.logging.FLog;
import com.facebook.common.util.UriUtil;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.module.annotations.ReactModule;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;

@ReactModule(name = "ImageEditingManager")
public class ImageEditingManager extends ReactContextBaseJavaModule {
    private static final int COMPRESS_QUALITY = 90;
    @SuppressLint({"InlinedApi"})
    private static final String[] EXIF_ATTRIBUTES = new String[]{"FNumber", "DateTime", "DateTimeDigitized", "ExposureTime", "Flash", "FocalLength", "GPSAltitude", "GPSAltitudeRef", "GPSDateStamp", "GPSLatitude", "GPSLatitudeRef", "GPSLongitude", "GPSLongitudeRef", "GPSProcessingMethod", "GPSTimeStamp", "ImageLength", "ImageWidth", "ISOSpeedRatings", "Make", "Model", "Orientation", "SubSecTime", "SubSecTimeDigitized", "SubSecTimeOriginal", "WhiteBalance"};
    private static final List<String> LOCAL_URI_PREFIXES = Arrays.asList(new String[]{"file://", RNFetchBlobConst.FILE_PREFIX_CONTENT});
    protected static final String NAME = "ImageEditingManager";
    private static final String TEMP_FILE_PREFIX = "ReactNative_cropped_image_";

    public ImageEditingManager(ReactApplicationContext reactContext) {
        super(reactContext);
        new CleanTask(getReactApplicationContext(), null).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    public String getName() {
        return NAME;
    }

    public Map<String, Object> getConstants() {
        return Collections.emptyMap();
    }

    public void onCatalystInstanceDestroy() {
        new CleanTask(getReactApplicationContext(), null).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    @ReactMethod
    public void cropImage(String uri, ReadableMap options, Callback success, Callback error) {
        ReadableMap offset = options.hasKey("offset") ? options.getMap("offset") : null;
        ReadableMap size = options.hasKey("size") ? options.getMap("size") : null;
        if (offset == null || size == null || !offset.hasKey("x") || !offset.hasKey("y") || !size.hasKey("width") || !size.hasKey("height")) {
            throw new JSApplicationIllegalArgumentException("Please specify offset and size");
        } else if (uri == null || uri.isEmpty()) {
            throw new JSApplicationIllegalArgumentException("Please specify a URI");
        } else {
            CropTask cropTask = new CropTask(getReactApplicationContext(), uri, (int) offset.getDouble("x"), (int) offset.getDouble("y"), (int) size.getDouble("width"), (int) size.getDouble("height"), success, error, null);
            if (options.hasKey("displaySize")) {
                ReadableMap targetSize = options.getMap("displaySize");
                cropTask.setTargetSize(targetSize.getInt("width"), targetSize.getInt("height"));
            }
            cropTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        }
    }

    private static void copyExif(Context context, Uri oldImage, File newFile) throws IOException {
        File oldFile = getFileFromUri(context, oldImage);
        if (oldFile == null) {
            FLog.w(ReactConstants.TAG, "Couldn't get real path for uri: " + oldImage);
            return;
        }
        ExifInterface oldExif = new ExifInterface(oldFile.getAbsolutePath());
        ExifInterface newExif = new ExifInterface(newFile.getAbsolutePath());
        for (String attribute : EXIF_ATTRIBUTES) {
            String value = oldExif.getAttribute(attribute);
            if (value != null) {
                newExif.setAttribute(attribute, value);
            }
        }
        newExif.saveAttributes();
    }

    @Nullable
    private static File getFileFromUri(Context context, Uri uri) {
        File file = null;
        if (uri.getScheme().equals(UriUtil.LOCAL_FILE_SCHEME)) {
            return new File(uri.getPath());
        }
        if (!uri.getScheme().equals("content")) {
            return null;
        }
        Cursor cursor = context.getContentResolver().query(uri, new String[]{"_data"}, null, null, null);
        if (cursor == null) {
            return null;
        }
        try {
            if (cursor.moveToFirst()) {
                String path = cursor.getString(0);
                if (!TextUtils.isEmpty(path)) {
                    file = new File(path);
                    return file;
                }
            }
            cursor.close();
            return null;
        } finally {
            cursor.close();
        }
    }

    private static boolean isLocalUri(String uri) {
        for (String localPrefix : LOCAL_URI_PREFIXES) {
            if (uri.startsWith(localPrefix)) {
                return true;
            }
        }
        return false;
    }

    private static String getFileExtensionForType(@Nullable String mimeType) {
        if ("image/png".equals(mimeType)) {
            return ".png";
        }
        if ("image/webp".equals(mimeType)) {
            return ".webp";
        }
        return ".jpg";
    }

    private static CompressFormat getCompressFormatForType(String type) {
        if ("image/png".equals(type)) {
            return CompressFormat.PNG;
        }
        if ("image/webp".equals(type)) {
            return CompressFormat.WEBP;
        }
        return CompressFormat.JPEG;
    }

    private static void writeCompressedBitmapToFile(Bitmap cropped, String mimeType, File tempFile) throws IOException {
        OutputStream out = new FileOutputStream(tempFile);
        try {
            cropped.compress(getCompressFormatForType(mimeType), 90, out);
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    private static File createTempFile(Context context, @Nullable String mimeType) throws IOException {
        File externalCacheDir = context.getExternalCacheDir();
        File internalCacheDir = context.getCacheDir();
        if (externalCacheDir == null && internalCacheDir == null) {
            throw new IOException("No cache directory available");
        }
        File cacheDir;
        if (externalCacheDir == null) {
            cacheDir = internalCacheDir;
        } else if (internalCacheDir == null) {
            cacheDir = externalCacheDir;
        } else {
            cacheDir = externalCacheDir.getFreeSpace() > internalCacheDir.getFreeSpace() ? externalCacheDir : internalCacheDir;
        }
        return File.createTempFile(TEMP_FILE_PREFIX, getFileExtensionForType(mimeType), cacheDir);
    }

    private static int getDecodeSampleSize(int width, int height, int targetWidth, int targetHeight) {
        int inSampleSize = 1;
        if (height > targetWidth || width > targetHeight) {
            int halfHeight = height / 2;
            int halfWidth = width / 2;
            while (halfWidth / inSampleSize >= targetWidth && halfHeight / inSampleSize >= targetHeight) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }
}
