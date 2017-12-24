package com.facebook.react.modules.camera;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.provider.MediaStore.Images.Media;
import android.text.TextUtils;
import com.RNFetchBlob.RNFetchBlobConst;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.GuardedAsyncTask;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeArray;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.module.annotations.ReactModule;
import com.google.android.gms.measurement.AppMeasurement$Param;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;

@ReactModule(name = "CameraRollManager")
public class CameraRollManager extends ReactContextBaseJavaModule {
    private static final String ERROR_UNABLE_TO_LOAD = "E_UNABLE_TO_LOAD";
    private static final String ERROR_UNABLE_TO_LOAD_PERMISSION = "E_UNABLE_TO_LOAD_PERMISSION";
    private static final String ERROR_UNABLE_TO_SAVE = "E_UNABLE_TO_SAVE";
    public static final boolean IS_JELLY_BEAN_OR_LATER = (VERSION.SDK_INT >= 16);
    protected static final String NAME = "CameraRollManager";
    private static final String[] PROJECTION;
    private static final String SELECTION_BUCKET = "bucket_display_name = ?";
    private static final String SELECTION_DATE_TAKEN = "datetaken < ?";

    private static class GetPhotosTask extends GuardedAsyncTask<Void, Void> {
        @Nullable
        private final String mAfter;
        private final Context mContext;
        private final int mFirst;
        @Nullable
        private final String mGroupName;
        @Nullable
        private final ReadableArray mMimeTypes;
        private final Promise mPromise;

        private GetPhotosTask(ReactContext context, int first, @Nullable String after, @Nullable String groupName, @Nullable ReadableArray mimeTypes, Promise promise) {
            super(context);
            this.mContext = context;
            this.mFirst = first;
            this.mAfter = after;
            this.mGroupName = groupName;
            this.mMimeTypes = mimeTypes;
            this.mPromise = promise;
        }

        protected void doInBackgroundGuarded(Void... params) {
            StringBuilder selection = new StringBuilder(AppEventsConstants.EVENT_PARAM_VALUE_YES);
            List<String> selectionArgs = new ArrayList();
            if (!TextUtils.isEmpty(this.mAfter)) {
                selection.append(" AND datetaken < ?");
                selectionArgs.add(this.mAfter);
            }
            if (!TextUtils.isEmpty(this.mGroupName)) {
                selection.append(" AND bucket_display_name = ?");
                selectionArgs.add(this.mGroupName);
            }
            if (this.mMimeTypes != null && this.mMimeTypes.size() > 0) {
                selection.append(" AND mime_type IN (");
                for (int i = 0; i < this.mMimeTypes.size(); i++) {
                    selection.append("?,");
                    selectionArgs.add(this.mMimeTypes.getString(i));
                }
                selection.replace(selection.length() - 1, selection.length(), ")");
            }
            WritableMap response = new WritableNativeMap();
            ContentResolver resolver = this.mContext.getContentResolver();
            Cursor photos;
            try {
                photos = resolver.query(Media.EXTERNAL_CONTENT_URI, CameraRollManager.PROJECTION, selection.toString(), (String[]) selectionArgs.toArray(new String[selectionArgs.size()]), "datetaken DESC, date_modified DESC LIMIT " + (this.mFirst + 1));
                if (photos == null) {
                    this.mPromise.reject(CameraRollManager.ERROR_UNABLE_TO_LOAD, "Could not get photos");
                    return;
                }
                CameraRollManager.putEdges(resolver, photos, response, this.mFirst);
                CameraRollManager.putPageInfo(photos, response, this.mFirst);
                photos.close();
                this.mPromise.resolve(response);
            } catch (SecurityException e) {
                this.mPromise.reject(CameraRollManager.ERROR_UNABLE_TO_LOAD_PERMISSION, "Could not get photos: need READ_EXTERNAL_STORAGE permission", e);
            } catch (Throwable th) {
                photos.close();
                this.mPromise.resolve(response);
            }
        }
    }

    static {
        if (IS_JELLY_BEAN_OR_LATER) {
            PROJECTION = new String[]{"_id", "mime_type", "bucket_display_name", "datetaken", "width", "height", "longitude", "latitude"};
        } else {
            PROJECTION = new String[]{"_id", "mime_type", "bucket_display_name", "datetaken", "longitude", "latitude"};
        }
    }

    public CameraRollManager(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    public String getName() {
        return NAME;
    }

    @ReactMethod
    public void saveToCameraRoll(String uri, String type, Promise promise) {
        new SaveToCameraRoll(getReactApplicationContext(), Uri.parse(uri), promise).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    @ReactMethod
    public void getPhotos(ReadableMap params, Promise promise) {
        String after;
        String groupName;
        ReadableArray mimeTypes;
        int first = params.getInt("first");
        if (params.hasKey("after")) {
            after = params.getString("after");
        } else {
            after = null;
        }
        if (params.hasKey("groupName")) {
            groupName = params.getString("groupName");
        } else {
            groupName = null;
        }
        if (params.hasKey("mimeTypes")) {
            mimeTypes = params.getArray("mimeTypes");
        } else {
            mimeTypes = null;
        }
        if (params.hasKey("groupTypes")) {
            throw new JSApplicationIllegalArgumentException("groupTypes is not supported on Android");
        }
        new GetPhotosTask(getReactApplicationContext(), first, after, groupName, mimeTypes, promise).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    private static void putPageInfo(Cursor photos, WritableMap response, int limit) {
        WritableMap pageInfo = new WritableNativeMap();
        pageInfo.putBoolean("has_next_page", limit < photos.getCount());
        if (limit < photos.getCount()) {
            photos.moveToPosition(limit - 1);
            pageInfo.putString("end_cursor", photos.getString(photos.getColumnIndex("datetaken")));
        }
        response.putMap("page_info", pageInfo);
    }

    private static void putEdges(ContentResolver resolver, Cursor photos, WritableMap response, int limit) {
        int widthIndex;
        int heightIndex;
        WritableArray edges = new WritableNativeArray();
        photos.moveToFirst();
        int idIndex = photos.getColumnIndex("_id");
        int mimeTypeIndex = photos.getColumnIndex("mime_type");
        int groupNameIndex = photos.getColumnIndex("bucket_display_name");
        int dateTakenIndex = photos.getColumnIndex("datetaken");
        if (IS_JELLY_BEAN_OR_LATER) {
            widthIndex = photos.getColumnIndex("width");
        } else {
            widthIndex = -1;
        }
        if (IS_JELLY_BEAN_OR_LATER) {
            heightIndex = photos.getColumnIndex("height");
        } else {
            heightIndex = -1;
        }
        int longitudeIndex = photos.getColumnIndex("longitude");
        int latitudeIndex = photos.getColumnIndex("latitude");
        int i = 0;
        while (i < limit && !photos.isAfterLast()) {
            WritableMap edge = new WritableNativeMap();
            WritableMap node = new WritableNativeMap();
            if (putImageInfo(resolver, photos, node, idIndex, widthIndex, heightIndex)) {
                putBasicNodeInfo(photos, node, mimeTypeIndex, groupNameIndex, dateTakenIndex);
                putLocationInfo(photos, node, longitudeIndex, latitudeIndex);
                edge.putMap("node", node);
                edges.pushMap(edge);
            } else {
                i--;
            }
            photos.moveToNext();
            i++;
        }
        response.putArray("edges", edges);
    }

    private static void putBasicNodeInfo(Cursor photos, WritableMap node, int mimeTypeIndex, int groupNameIndex, int dateTakenIndex) {
        node.putString("type", photos.getString(mimeTypeIndex));
        node.putString("group_name", photos.getString(groupNameIndex));
        node.putDouble(AppMeasurement$Param.TIMESTAMP, ((double) photos.getLong(dateTakenIndex)) / 1000.0d);
    }

    private static boolean putImageInfo(ContentResolver resolver, Cursor photos, WritableMap node, int idIndex, int widthIndex, int heightIndex) {
        WritableMap image = new WritableNativeMap();
        Uri photoUri = Uri.withAppendedPath(Media.EXTERNAL_CONTENT_URI, photos.getString(idIndex));
        image.putString(RNFetchBlobConst.DATA_ENCODE_URI, photoUri.toString());
        float width = FlexItem.FLEX_BASIS_PERCENT_DEFAULT;
        float height = FlexItem.FLEX_BASIS_PERCENT_DEFAULT;
        if (IS_JELLY_BEAN_OR_LATER) {
            width = (float) photos.getInt(widthIndex);
            height = (float) photos.getInt(heightIndex);
        }
        if (width <= 0.0f || height <= 0.0f) {
            try {
                AssetFileDescriptor photoDescriptor = resolver.openAssetFileDescriptor(photoUri, "r");
                Options options = new Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeFileDescriptor(photoDescriptor.getFileDescriptor(), null, options);
                photoDescriptor.close();
                width = (float) options.outWidth;
                height = (float) options.outHeight;
            } catch (IOException e) {
                FLog.e(ReactConstants.TAG, "Could not get width/height for " + photoUri.toString(), e);
                return false;
            }
        }
        image.putDouble("width", (double) width);
        image.putDouble("height", (double) height);
        node.putMap("image", image);
        return true;
    }

    private static void putLocationInfo(Cursor photos, WritableMap node, int longitudeIndex, int latitudeIndex) {
        double longitude = photos.getDouble(longitudeIndex);
        double latitude = photos.getDouble(latitudeIndex);
        if (longitude > 0.0d || latitude > 0.0d) {
            WritableMap location = new WritableNativeMap();
            location.putDouble("longitude", longitude);
            location.putDouble("latitude", latitude);
            node.putMap(Param.LOCATION, location);
        }
    }
}
