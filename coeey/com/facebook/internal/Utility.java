package com.facebook.internal;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.Cursor;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Bundle;
import android.os.Parcel;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import com.facebook.AccessToken;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphRequest.Callback;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.common.util.UriUtil;
import com.facebook.share.internal.ShareConstants;
import com.google.firebase.auth.FacebookAuthProvider;
import java.io.BufferedInputStream;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public final class Utility {
    private static final String APPLICATION_FIELDS = "fields";
    private static final String APP_SETTINGS_PREFS_KEY_FORMAT = "com.facebook.internal.APP_SETTINGS.%s";
    private static final String APP_SETTINGS_PREFS_STORE = "com.facebook.internal.preferences.APP_SETTINGS";
    private static final String APP_SETTING_ANDROID_SDK_ERROR_CATEGORIES = "android_sdk_error_categories";
    private static final String APP_SETTING_DIALOG_CONFIGS = "android_dialog_configs";
    private static final String[] APP_SETTING_FIELDS = new String[]{APP_SETTING_SUPPORTS_IMPLICIT_SDK_LOGGING, APP_SETTING_NUX_CONTENT, APP_SETTING_NUX_ENABLED, APP_SETTING_DIALOG_CONFIGS, APP_SETTING_ANDROID_SDK_ERROR_CATEGORIES};
    private static final String APP_SETTING_NUX_CONTENT = "gdpv4_nux_content";
    private static final String APP_SETTING_NUX_ENABLED = "gdpv4_nux_enabled";
    private static final String APP_SETTING_SUPPORTS_IMPLICIT_SDK_LOGGING = "supports_implicit_sdk_logging";
    public static final int DEFAULT_STREAM_BUFFER_SIZE = 8192;
    private static final String DIALOG_CONFIG_DIALOG_NAME_FEATURE_NAME_SEPARATOR = "\\|";
    private static final String DIALOG_CONFIG_NAME_KEY = "name";
    private static final String DIALOG_CONFIG_URL_KEY = "url";
    private static final String DIALOG_CONFIG_VERSIONS_KEY = "versions";
    private static final String EXTRA_APP_EVENTS_INFO_FORMAT_VERSION = "a1";
    private static final String HASH_ALGORITHM_MD5 = "MD5";
    private static final String HASH_ALGORITHM_SHA1 = "SHA-1";
    static final String LOG_TAG = "FacebookSDK";
    private static final String URL_SCHEME = "https";
    private static final String UTF8 = "UTF-8";
    private static Map<String, FetchedAppSettings> fetchedAppSettings = new ConcurrentHashMap();
    private static AtomicBoolean loadingSettings = new AtomicBoolean(false);

    public interface GraphMeRequestWithCacheCallback {
        void onFailure(FacebookException facebookException);

        void onSuccess(JSONObject jSONObject);
    }

    public static class DialogFeatureConfig {
        private String dialogName;
        private Uri fallbackUrl;
        private String featureName;
        private int[] featureVersionSpec;

        private static DialogFeatureConfig parseDialogConfig(JSONObject dialogConfigJSON) {
            String dialogNameWithFeature = dialogConfigJSON.optString("name");
            if (Utility.isNullOrEmpty(dialogNameWithFeature)) {
                return null;
            }
            String[] components = dialogNameWithFeature.split(Utility.DIALOG_CONFIG_DIALOG_NAME_FEATURE_NAME_SEPARATOR);
            if (components.length != 2) {
                return null;
            }
            String dialogName = components[0];
            String featureName = components[1];
            if (Utility.isNullOrEmpty(dialogName) || Utility.isNullOrEmpty(featureName)) {
                return null;
            }
            String urlString = dialogConfigJSON.optString("url");
            Uri fallbackUri = null;
            if (!Utility.isNullOrEmpty(urlString)) {
                fallbackUri = Uri.parse(urlString);
            }
            return new DialogFeatureConfig(dialogName, featureName, fallbackUri, parseVersionSpec(dialogConfigJSON.optJSONArray(Utility.DIALOG_CONFIG_VERSIONS_KEY)));
        }

        private static int[] parseVersionSpec(JSONArray versionsJSON) {
            int[] versionSpec = null;
            if (versionsJSON != null) {
                int numVersions = versionsJSON.length();
                versionSpec = new int[numVersions];
                for (int i = 0; i < numVersions; i++) {
                    int version = versionsJSON.optInt(i, -1);
                    if (version == -1) {
                        String versionString = versionsJSON.optString(i);
                        if (!Utility.isNullOrEmpty(versionString)) {
                            try {
                                version = Integer.parseInt(versionString);
                            } catch (Exception nfe) {
                                Utility.logd(Utility.LOG_TAG, nfe);
                                version = -1;
                            }
                        }
                    }
                    versionSpec[i] = version;
                }
            }
            return versionSpec;
        }

        private DialogFeatureConfig(String dialogName, String featureName, Uri fallbackUrl, int[] featureVersionSpec) {
            this.dialogName = dialogName;
            this.featureName = featureName;
            this.fallbackUrl = fallbackUrl;
            this.featureVersionSpec = featureVersionSpec;
        }

        public String getDialogName() {
            return this.dialogName;
        }

        public String getFeatureName() {
            return this.featureName;
        }

        public Uri getFallbackUrl() {
            return this.fallbackUrl;
        }

        public int[] getVersionSpec() {
            return this.featureVersionSpec;
        }
    }

    public static class FetchedAppSettings {
        private Map<String, Map<String, DialogFeatureConfig>> dialogConfigMap;
        private FacebookRequestErrorClassification errorClassification;
        private String nuxContent;
        private boolean nuxEnabled;
        private boolean supportsImplicitLogging;

        private FetchedAppSettings(boolean supportsImplicitLogging, String nuxContent, boolean nuxEnabled, Map<String, Map<String, DialogFeatureConfig>> dialogConfigMap, FacebookRequestErrorClassification errorClassification) {
            this.supportsImplicitLogging = supportsImplicitLogging;
            this.nuxContent = nuxContent;
            this.nuxEnabled = nuxEnabled;
            this.dialogConfigMap = dialogConfigMap;
            this.errorClassification = errorClassification;
        }

        public boolean supportsImplicitLogging() {
            return this.supportsImplicitLogging;
        }

        public String getNuxContent() {
            return this.nuxContent;
        }

        public boolean getNuxEnabled() {
            return this.nuxEnabled;
        }

        public Map<String, Map<String, DialogFeatureConfig>> getDialogConfigurations() {
            return this.dialogConfigMap;
        }

        public FacebookRequestErrorClassification getErrorClassification() {
            return this.errorClassification;
        }
    }

    public interface Mapper<T, K> {
        K apply(T t);
    }

    public interface Predicate<T> {
        boolean apply(T t);
    }

    public static int[] intersectRanges(int[] range1, int[] range2) {
        if (range1 == null) {
            return range2;
        }
        if (range2 == null) {
            return range1;
        }
        int[] outputRange = new int[(range1.length + range2.length)];
        int outputIndex = 0;
        int index1 = 0;
        int index2 = 0;
        while (index1 < range1.length && index2 < range2.length) {
            int newRangeLower = Integer.MIN_VALUE;
            int newRangeUpper = Integer.MAX_VALUE;
            int lower1 = range1[index1];
            int upper1 = Integer.MAX_VALUE;
            int lower2 = range2[index2];
            int upper2 = Integer.MAX_VALUE;
            if (index1 < range1.length - 1) {
                upper1 = range1[index1 + 1];
            }
            if (index2 < range2.length - 1) {
                upper2 = range2[index2 + 1];
            }
            if (lower1 < lower2) {
                if (upper1 > lower2) {
                    newRangeLower = lower2;
                    if (upper1 > upper2) {
                        newRangeUpper = upper2;
                        index2 += 2;
                    } else {
                        newRangeUpper = upper1;
                        index1 += 2;
                    }
                } else {
                    index1 += 2;
                }
            } else if (upper2 > lower1) {
                newRangeLower = lower1;
                if (upper2 > upper1) {
                    newRangeUpper = upper1;
                    index1 += 2;
                } else {
                    newRangeUpper = upper2;
                    index2 += 2;
                }
            } else {
                index2 += 2;
            }
            if (newRangeLower != Integer.MIN_VALUE) {
                int outputIndex2 = outputIndex + 1;
                outputRange[outputIndex] = newRangeLower;
                if (newRangeUpper == Integer.MAX_VALUE) {
                    outputIndex = outputIndex2;
                    break;
                }
                outputIndex = outputIndex2 + 1;
                outputRange[outputIndex2] = newRangeUpper;
            }
        }
        return Arrays.copyOf(outputRange, outputIndex);
    }

    public static <T> boolean isSubset(Collection<T> subset, Collection<T> superset) {
        if (superset != null && superset.size() != 0) {
            HashSet<T> hash = new HashSet(superset);
            for (T t : subset) {
                if (!hash.contains(t)) {
                    return false;
                }
            }
            return true;
        } else if (subset == null || subset.size() == 0) {
            return true;
        } else {
            return false;
        }
    }

    public static <T> boolean isNullOrEmpty(Collection<T> c) {
        return c == null || c.size() == 0;
    }

    public static boolean isNullOrEmpty(String s) {
        return s == null || s.length() == 0;
    }

    public static String coerceValueIfNullOrEmpty(String s, String valueIfNullOrEmpty) {
        return isNullOrEmpty(s) ? valueIfNullOrEmpty : s;
    }

    public static <T> Collection<T> unmodifiableCollection(T... ts) {
        return Collections.unmodifiableCollection(Arrays.asList(ts));
    }

    public static <T> ArrayList<T> arrayList(T... ts) {
        ArrayList<T> arrayList = new ArrayList(ts.length);
        for (T t : ts) {
            arrayList.add(t);
        }
        return arrayList;
    }

    public static <T> HashSet<T> hashSet(T... ts) {
        HashSet<T> hashSet = new HashSet(ts.length);
        for (T t : ts) {
            hashSet.add(t);
        }
        return hashSet;
    }

    public static String md5hash(String key) {
        return hashWithAlgorithm("MD5", key);
    }

    public static String sha1hash(String key) {
        return hashWithAlgorithm("SHA-1", key);
    }

    public static String sha1hash(byte[] bytes) {
        return hashWithAlgorithm("SHA-1", bytes);
    }

    private static String hashWithAlgorithm(String algorithm, String key) {
        return hashWithAlgorithm(algorithm, key.getBytes());
    }

    private static String hashWithAlgorithm(String algorithm, byte[] bytes) {
        try {
            return hashBytes(MessageDigest.getInstance(algorithm), bytes);
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    private static String hashBytes(MessageDigest hash, byte[] bytes) {
        hash.update(bytes);
        byte[] digest = hash.digest();
        StringBuilder builder = new StringBuilder();
        for (int b : digest) {
            builder.append(Integer.toHexString((b >> 4) & 15));
            builder.append(Integer.toHexString((b >> 0) & 15));
        }
        return builder.toString();
    }

    public static Uri buildUri(String authority, String path, Bundle parameters) {
        Builder builder = new Builder();
        builder.scheme("https");
        builder.authority(authority);
        builder.path(path);
        if (parameters != null) {
            for (String key : parameters.keySet()) {
                Object parameter = parameters.get(key);
                if (parameter instanceof String) {
                    builder.appendQueryParameter(key, (String) parameter);
                }
            }
        }
        return builder.build();
    }

    public static Bundle parseUrlQueryString(String queryString) {
        Bundle params = new Bundle();
        if (!isNullOrEmpty(queryString)) {
            for (String parameter : queryString.split("&")) {
                String[] keyValuePair = parameter.split("=");
                try {
                    if (keyValuePair.length == 2) {
                        params.putString(URLDecoder.decode(keyValuePair[0], "UTF-8"), URLDecoder.decode(keyValuePair[1], "UTF-8"));
                    } else if (keyValuePair.length == 1) {
                        params.putString(URLDecoder.decode(keyValuePair[0], "UTF-8"), "");
                    }
                } catch (Exception e) {
                    logd(LOG_TAG, e);
                }
            }
        }
        return params;
    }

    public static void putNonEmptyString(Bundle b, String key, String value) {
        if (!isNullOrEmpty(value)) {
            b.putString(key, value);
        }
    }

    public static void putCommaSeparatedStringList(Bundle b, String key, ArrayList<String> list) {
        if (list != null) {
            StringBuilder builder = new StringBuilder();
            Iterator i$ = list.iterator();
            while (i$.hasNext()) {
                builder.append((String) i$.next());
                builder.append(",");
            }
            String commaSeparated = "";
            if (builder.length() > 0) {
                commaSeparated = builder.substring(0, builder.length() - 1);
            }
            b.putString(key, commaSeparated);
        }
    }

    public static void putUri(Bundle b, String key, Uri uri) {
        if (uri != null) {
            putNonEmptyString(b, key, uri.toString());
        }
    }

    public static boolean putJSONValueInBundle(Bundle bundle, String key, Object value) {
        if (value == null) {
            bundle.remove(key);
        } else if (value instanceof Boolean) {
            bundle.putBoolean(key, ((Boolean) value).booleanValue());
        } else if (value instanceof boolean[]) {
            bundle.putBooleanArray(key, (boolean[]) value);
        } else if (value instanceof Double) {
            bundle.putDouble(key, ((Double) value).doubleValue());
        } else if (value instanceof double[]) {
            bundle.putDoubleArray(key, (double[]) value);
        } else if (value instanceof Integer) {
            bundle.putInt(key, ((Integer) value).intValue());
        } else if (value instanceof int[]) {
            bundle.putIntArray(key, (int[]) value);
        } else if (value instanceof Long) {
            bundle.putLong(key, ((Long) value).longValue());
        } else if (value instanceof long[]) {
            bundle.putLongArray(key, (long[]) value);
        } else if (value instanceof String) {
            bundle.putString(key, (String) value);
        } else if (value instanceof JSONArray) {
            bundle.putString(key, ((JSONArray) value).toString());
        } else if (!(value instanceof JSONObject)) {
            return false;
        } else {
            bundle.putString(key, ((JSONObject) value).toString());
        }
        return true;
    }

    public static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
            }
        }
    }

    public static void disconnectQuietly(URLConnection connection) {
        if (connection instanceof HttpURLConnection) {
            ((HttpURLConnection) connection).disconnect();
        }
    }

    public static String getMetadataApplicationId(Context context) {
        Validate.notNull(context, "context");
        FacebookSdk.sdkInitialize(context);
        return FacebookSdk.getApplicationId();
    }

    static Map<String, Object> convertJSONObjectToHashMap(JSONObject jsonObject) {
        HashMap<String, Object> map = new HashMap();
        JSONArray keys = jsonObject.names();
        for (int i = 0; i < keys.length(); i++) {
            try {
                String key = keys.getString(i);
                Object obj = jsonObject.get(key);
                if (obj instanceof JSONObject) {
                    obj = convertJSONObjectToHashMap((JSONObject) obj);
                }
                map.put(key, obj);
            } catch (JSONException e) {
            }
        }
        return map;
    }

    public static Object getStringPropertyAsJSON(JSONObject jsonObject, String key, String nonJSONPropertyKey) throws JSONException {
        JSONObject value = jsonObject.opt(key);
        if (value != null && (value instanceof String)) {
            value = new JSONTokener((String) value).nextValue();
        }
        if (value == null || (value instanceof JSONObject) || (value instanceof JSONArray)) {
            return value;
        }
        if (nonJSONPropertyKey != null) {
            jsonObject = new JSONObject();
            jsonObject.putOpt(nonJSONPropertyKey, value);
            return jsonObject;
        }
        throw new FacebookException("Got an unexpected non-JSON object.");
    }

    public static String readStreamToString(InputStream inputStream) throws IOException {
        Throwable th;
        BufferedInputStream bufferedInputStream = null;
        InputStreamReader reader = null;
        try {
            InputStreamReader reader2;
            BufferedInputStream bufferedInputStream2 = new BufferedInputStream(inputStream);
            try {
                reader2 = new InputStreamReader(bufferedInputStream2);
            } catch (Throwable th2) {
                th = th2;
                bufferedInputStream = bufferedInputStream2;
                closeQuietly(bufferedInputStream);
                closeQuietly(reader);
                throw th;
            }
            try {
                StringBuilder stringBuilder = new StringBuilder();
                char[] buffer = new char[2048];
                while (true) {
                    int n = reader2.read(buffer);
                    if (n != -1) {
                        stringBuilder.append(buffer, 0, n);
                    } else {
                        String stringBuilder2 = stringBuilder.toString();
                        closeQuietly(bufferedInputStream2);
                        closeQuietly(reader2);
                        return stringBuilder2;
                    }
                }
            } catch (Throwable th3) {
                th = th3;
                reader = reader2;
                bufferedInputStream = bufferedInputStream2;
                closeQuietly(bufferedInputStream);
                closeQuietly(reader);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            closeQuietly(bufferedInputStream);
            closeQuietly(reader);
            throw th;
        }
    }

    public static int copyAndCloseInputStream(InputStream inputStream, OutputStream outputStream) throws IOException {
        Throwable th;
        BufferedInputStream bufferedInputStream = null;
        int totalBytes = 0;
        try {
            BufferedInputStream bufferedInputStream2 = new BufferedInputStream(inputStream);
            try {
                byte[] buffer = new byte[8192];
                while (true) {
                    int bytesRead = bufferedInputStream2.read(buffer);
                    if (bytesRead == -1) {
                        break;
                    }
                    outputStream.write(buffer, 0, bytesRead);
                    totalBytes += bytesRead;
                }
                if (bufferedInputStream2 != null) {
                    bufferedInputStream2.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
                return totalBytes;
            } catch (Throwable th2) {
                th = th2;
                bufferedInputStream = bufferedInputStream2;
                if (bufferedInputStream != null) {
                    bufferedInputStream.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            if (bufferedInputStream != null) {
                bufferedInputStream.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
            throw th;
        }
    }

    public static boolean stringsEqualOrEmpty(String a, String b) {
        boolean aEmpty = TextUtils.isEmpty(a);
        boolean bEmpty = TextUtils.isEmpty(b);
        if (aEmpty && bEmpty) {
            return true;
        }
        if (aEmpty || bEmpty) {
            return false;
        }
        return a.equals(b);
    }

    private static void clearCookiesForDomain(Context context, String domain) {
        CookieSyncManager.createInstance(context).sync();
        CookieManager cookieManager = CookieManager.getInstance();
        String cookies = cookieManager.getCookie(domain);
        if (cookies != null) {
            for (String cookie : cookies.split(";")) {
                String[] cookieParts = cookie.split("=");
                if (cookieParts.length > 0) {
                    cookieManager.setCookie(domain, cookieParts[0].trim() + "=;expires=Sat, 1 Jan 2000 00:00:01 UTC;");
                }
            }
            cookieManager.removeExpiredCookie();
        }
    }

    public static void clearFacebookCookies(Context context) {
        clearCookiesForDomain(context, FacebookAuthProvider.PROVIDER_ID);
        clearCookiesForDomain(context, ".facebook.com");
        clearCookiesForDomain(context, "https://facebook.com");
        clearCookiesForDomain(context, "https://.facebook.com");
    }

    public static void logd(String tag, Exception e) {
        if (FacebookSdk.isDebugEnabled() && tag != null && e != null) {
            Log.d(tag, e.getClass().getSimpleName() + ": " + e.getMessage());
        }
    }

    public static void logd(String tag, String msg) {
        if (FacebookSdk.isDebugEnabled() && tag != null && msg != null) {
            Log.d(tag, msg);
        }
    }

    public static void logd(String tag, String msg, Throwable t) {
        if (FacebookSdk.isDebugEnabled() && !isNullOrEmpty(tag)) {
            Log.d(tag, msg, t);
        }
    }

    public static <T> boolean areObjectsEqual(T a, T b) {
        if (a == null) {
            return b == null;
        } else {
            return a.equals(b);
        }
    }

    public static boolean hasSameId(JSONObject a, JSONObject b) {
        if (a == null || b == null || !a.has(ShareConstants.WEB_DIALOG_PARAM_ID) || !b.has(ShareConstants.WEB_DIALOG_PARAM_ID)) {
            return false;
        }
        if (a.equals(b)) {
            return true;
        }
        String idA = a.optString(ShareConstants.WEB_DIALOG_PARAM_ID);
        String idB = b.optString(ShareConstants.WEB_DIALOG_PARAM_ID);
        if (idA == null || idB == null) {
            return false;
        }
        return idA.equals(idB);
    }

    public static void loadAppSettingsAsync(final Context context, final String applicationId) {
        boolean canStartLoading = loadingSettings.compareAndSet(false, true);
        if (!isNullOrEmpty(applicationId) && !fetchedAppSettings.containsKey(applicationId) && canStartLoading) {
            final String settingsKey = String.format(APP_SETTINGS_PREFS_KEY_FORMAT, new Object[]{applicationId});
            FacebookSdk.getExecutor().execute(new Runnable() {
                public void run() {
                    JSONObject resultJSON = Utility.getAppSettingsQueryResponse(applicationId);
                    if (resultJSON != null) {
                        Utility.parseAppSettingsFromJSON(applicationId, resultJSON);
                        context.getSharedPreferences(Utility.APP_SETTINGS_PREFS_STORE, 0).edit().putString(settingsKey, resultJSON.toString()).apply();
                    }
                    Utility.loadingSettings.set(false);
                }
            });
            String settingsJSONString = context.getSharedPreferences(APP_SETTINGS_PREFS_STORE, 0).getString(settingsKey, null);
            if (!isNullOrEmpty(settingsJSONString)) {
                JSONObject settingsJSON = null;
                try {
                    settingsJSON = new JSONObject(settingsJSONString);
                } catch (Exception je) {
                    logd(LOG_TAG, je);
                }
                if (settingsJSON != null) {
                    parseAppSettingsFromJSON(applicationId, settingsJSON);
                }
            }
        }
    }

    public static FetchedAppSettings getAppSettingsWithoutQuery(String applicationId) {
        return applicationId != null ? (FetchedAppSettings) fetchedAppSettings.get(applicationId) : null;
    }

    public static FetchedAppSettings queryAppSettings(String applicationId, boolean forceRequery) {
        if (!forceRequery && fetchedAppSettings.containsKey(applicationId)) {
            return (FetchedAppSettings) fetchedAppSettings.get(applicationId);
        }
        JSONObject response = getAppSettingsQueryResponse(applicationId);
        if (response == null) {
            return null;
        }
        return parseAppSettingsFromJSON(applicationId, response);
    }

    private static FetchedAppSettings parseAppSettingsFromJSON(String applicationId, JSONObject settingsJSON) {
        JSONArray errorClassificationJSON = settingsJSON.optJSONArray(APP_SETTING_ANDROID_SDK_ERROR_CATEGORIES);
        FetchedAppSettings result = new FetchedAppSettings(settingsJSON.optBoolean(APP_SETTING_SUPPORTS_IMPLICIT_SDK_LOGGING, false), settingsJSON.optString(APP_SETTING_NUX_CONTENT, ""), settingsJSON.optBoolean(APP_SETTING_NUX_ENABLED, false), parseDialogConfigurations(settingsJSON.optJSONObject(APP_SETTING_DIALOG_CONFIGS)), errorClassificationJSON == null ? FacebookRequestErrorClassification.getDefaultErrorClassification() : FacebookRequestErrorClassification.createFromJSON(errorClassificationJSON));
        fetchedAppSettings.put(applicationId, result);
        return result;
    }

    private static JSONObject getAppSettingsQueryResponse(String applicationId) {
        Bundle appSettingsParams = new Bundle();
        appSettingsParams.putString(APPLICATION_FIELDS, TextUtils.join(",", APP_SETTING_FIELDS));
        GraphRequest request = GraphRequest.newGraphPathRequest(null, applicationId, null);
        request.setSkipClientToken(true);
        request.setParameters(appSettingsParams);
        return request.executeAndWait().getJSONObject();
    }

    public static DialogFeatureConfig getDialogFeatureConfig(String applicationId, String actionName, String featureName) {
        if (isNullOrEmpty(actionName) || isNullOrEmpty(featureName)) {
            return null;
        }
        FetchedAppSettings settings = (FetchedAppSettings) fetchedAppSettings.get(applicationId);
        if (settings == null) {
            return null;
        }
        Map<String, DialogFeatureConfig> featureMap = (Map) settings.getDialogConfigurations().get(actionName);
        if (featureMap != null) {
            return (DialogFeatureConfig) featureMap.get(featureName);
        }
        return null;
    }

    private static Map<String, Map<String, DialogFeatureConfig>> parseDialogConfigurations(JSONObject dialogConfigResponse) {
        HashMap<String, Map<String, DialogFeatureConfig>> dialogConfigMap = new HashMap();
        if (dialogConfigResponse != null) {
            JSONArray dialogConfigData = dialogConfigResponse.optJSONArray("data");
            if (dialogConfigData != null) {
                for (int i = 0; i < dialogConfigData.length(); i++) {
                    DialogFeatureConfig dialogConfig = DialogFeatureConfig.parseDialogConfig(dialogConfigData.optJSONObject(i));
                    if (dialogConfig != null) {
                        String dialogName = dialogConfig.getDialogName();
                        Map<String, DialogFeatureConfig> featureMap = (Map) dialogConfigMap.get(dialogName);
                        if (featureMap == null) {
                            featureMap = new HashMap();
                            dialogConfigMap.put(dialogName, featureMap);
                        }
                        featureMap.put(dialogConfig.getFeatureName(), dialogConfig);
                    }
                }
            }
        }
        return dialogConfigMap;
    }

    public static String safeGetStringFromResponse(JSONObject response, String propertyName) {
        return response != null ? response.optString(propertyName, "") : "";
    }

    public static JSONObject tryGetJSONObjectFromResponse(JSONObject response, String propertyKey) {
        return response != null ? response.optJSONObject(propertyKey) : null;
    }

    public static JSONArray tryGetJSONArrayFromResponse(JSONObject response, String propertyKey) {
        return response != null ? response.optJSONArray(propertyKey) : null;
    }

    public static void clearCaches(Context context) {
        ImageDownloader.clearCache(context);
    }

    public static void deleteDirectory(File directoryOrFile) {
        if (directoryOrFile.exists()) {
            if (directoryOrFile.isDirectory()) {
                for (File child : directoryOrFile.listFiles()) {
                    deleteDirectory(child);
                }
            }
            directoryOrFile.delete();
        }
    }

    public static <T> List<T> asListNoNulls(T... array) {
        ArrayList<T> result = new ArrayList();
        for (T t : array) {
            if (t != null) {
                result.add(t);
            }
        }
        return result;
    }

    public static List<String> jsonArrayToStringList(JSONArray jsonArray) throws JSONException {
        ArrayList<String> result = new ArrayList();
        for (int i = 0; i < jsonArray.length(); i++) {
            result.add(jsonArray.getString(i));
        }
        return result;
    }

    public static void setAppEventAttributionParameters(JSONObject params, AttributionIdentifiers attributionIdentifiers, String anonymousAppDeviceGUID, boolean limitEventUsage) throws JSONException {
        boolean z = true;
        if (!(attributionIdentifiers == null || attributionIdentifiers.getAttributionId() == null)) {
            params.put("attribution", attributionIdentifiers.getAttributionId());
        }
        if (!(attributionIdentifiers == null || attributionIdentifiers.getAndroidAdvertiserId() == null)) {
            params.put("advertiser_id", attributionIdentifiers.getAndroidAdvertiserId());
            params.put("advertiser_tracking_enabled", !attributionIdentifiers.isTrackingLimited());
        }
        params.put("anon_id", anonymousAppDeviceGUID);
        String str = "application_tracking_enabled";
        if (limitEventUsage) {
            z = false;
        }
        params.put(str, z);
    }

    public static void setAppEventExtendedDeviceInfoParameters(JSONObject params, Context appContext) throws JSONException {
        JSONArray extraInfoArray = new JSONArray();
        extraInfoArray.put(EXTRA_APP_EVENTS_INFO_FORMAT_VERSION);
        String pkgName = appContext.getPackageName();
        int versionCode = -1;
        String versionName = "";
        try {
            PackageInfo pi = appContext.getPackageManager().getPackageInfo(pkgName, 0);
            versionCode = pi.versionCode;
            versionName = pi.versionName;
        } catch (NameNotFoundException e) {
        }
        extraInfoArray.put(pkgName);
        extraInfoArray.put(versionCode);
        extraInfoArray.put(versionName);
        params.put("extinfo", extraInfoArray.toString());
    }

    public static Method getMethodQuietly(Class<?> clazz, String methodName, Class<?>... parameterTypes) {
        try {
            return clazz.getMethod(methodName, parameterTypes);
        } catch (NoSuchMethodException e) {
            return null;
        }
    }

    public static Method getMethodQuietly(String className, String methodName, Class<?>... parameterTypes) {
        try {
            return getMethodQuietly(Class.forName(className), methodName, (Class[]) parameterTypes);
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    public static Object invokeMethodQuietly(Object receiver, Method method, Object... args) {
        Object obj = null;
        try {
            obj = method.invoke(receiver, args);
        } catch (IllegalAccessException e) {
        } catch (InvocationTargetException e2) {
        }
        return obj;
    }

    public static String getActivityName(Context context) {
        if (context == null) {
            return "null";
        }
        if (context == context.getApplicationContext()) {
            return "unknown";
        }
        return context.getClass().getSimpleName();
    }

    public static <T> List<T> filter(List<T> target, Predicate<T> predicate) {
        if (target == null) {
            return null;
        }
        List<T> list = new ArrayList();
        for (T item : target) {
            if (predicate.apply(item)) {
                list.add(item);
            }
        }
        if (list.size() == 0) {
            list = null;
        }
        return list;
    }

    public static <T, K> List<K> map(List<T> target, Mapper<T, K> mapper) {
        if (target == null) {
            return null;
        }
        List<K> list = new ArrayList();
        for (T item : target) {
            K mappedItem = mapper.apply(item);
            if (mappedItem != null) {
                list.add(mappedItem);
            }
        }
        if (list.size() == 0) {
            list = null;
        }
        return list;
    }

    public static String getUriString(Uri uri) {
        return uri == null ? null : uri.toString();
    }

    public static boolean isWebUri(Uri uri) {
        return uri != null && (UriUtil.HTTP_SCHEME.equalsIgnoreCase(uri.getScheme()) || "https".equalsIgnoreCase(uri.getScheme()));
    }

    public static boolean isContentUri(Uri uri) {
        return uri != null && "content".equalsIgnoreCase(uri.getScheme());
    }

    public static boolean isFileUri(Uri uri) {
        return uri != null && UriUtil.LOCAL_FILE_SCHEME.equalsIgnoreCase(uri.getScheme());
    }

    public static long getContentSize(Uri contentUri) {
        Cursor cursor = null;
        try {
            cursor = FacebookSdk.getApplicationContext().getContentResolver().query(contentUri, null, null, null, null);
            int sizeIndex = cursor.getColumnIndex("_size");
            cursor.moveToFirst();
            long j = cursor.getLong(sizeIndex);
            return j;
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    public static Date getBundleLongAsDate(Bundle bundle, String key, Date dateBase) {
        if (bundle == null) {
            return null;
        }
        long secondsFromBase;
        Object secondsObject = bundle.get(key);
        if (secondsObject instanceof Long) {
            secondsFromBase = ((Long) secondsObject).longValue();
        } else if (!(secondsObject instanceof String)) {
            return null;
        } else {
            try {
                secondsFromBase = Long.parseLong((String) secondsObject);
            } catch (NumberFormatException e) {
                return null;
            }
        }
        if (secondsFromBase == 0) {
            return new Date(Long.MAX_VALUE);
        }
        return new Date(dateBase.getTime() + (1000 * secondsFromBase));
    }

    public static void writeStringMapToParcel(Parcel parcel, Map<String, String> map) {
        if (map == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(map.size());
        for (Entry<String, String> entry : map.entrySet()) {
            parcel.writeString((String) entry.getKey());
            parcel.writeString((String) entry.getValue());
        }
    }

    public static Map<String, String> readStringMapFromParcel(Parcel parcel) {
        int size = parcel.readInt();
        if (size < 0) {
            return null;
        }
        Map<String, String> map = new HashMap();
        for (int i = 0; i < size; i++) {
            map.put(parcel.readString(), parcel.readString());
        }
        return map;
    }

    public static boolean isCurrentAccessToken(AccessToken token) {
        return token != null ? token.equals(AccessToken.getCurrentAccessToken()) : false;
    }

    public static void getGraphMeRequestWithCacheAsync(final String accessToken, final GraphMeRequestWithCacheCallback callback) {
        JSONObject cachedValue = ProfileInformationCache.getProfileInformation(accessToken);
        if (cachedValue != null) {
            callback.onSuccess(cachedValue);
            return;
        }
        Callback graphCallback = new Callback() {
            public void onCompleted(GraphResponse response) {
                if (response.getError() != null) {
                    callback.onFailure(response.getError().getException());
                    return;
                }
                ProfileInformationCache.putProfileInformation(accessToken, response.getJSONObject());
                callback.onSuccess(response.getJSONObject());
            }
        };
        GraphRequest graphRequest = getGraphMeRequestWithCache(accessToken);
        graphRequest.setCallback(graphCallback);
        graphRequest.executeAsync();
    }

    public static JSONObject awaitGetGraphMeRequestWithCache(String accessToken) {
        JSONObject cachedValue = ProfileInformationCache.getProfileInformation(accessToken);
        if (cachedValue != null) {
            return cachedValue;
        }
        GraphResponse response = getGraphMeRequestWithCache(accessToken).executeAndWait();
        if (response.getError() != null) {
            return null;
        }
        return response.getJSONObject();
    }

    private static GraphRequest getGraphMeRequestWithCache(String accessToken) {
        Bundle parameters = new Bundle();
        parameters.putString(APPLICATION_FIELDS, "id,name,first_name,middle_name,last_name,link");
        parameters.putString("access_token", accessToken);
        return new GraphRequest(null, "me", parameters, HttpMethod.GET, null);
    }
}
