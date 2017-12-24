package com.bumptech.glide.load.model;

import android.net.Uri;
import com.facebook.common.util.UriUtil;

final class AssetUriParser {
    private static final String ASSET_PATH_SEGMENT = "android_asset";
    private static final String ASSET_PREFIX = "file:///android_asset/";
    private static final int ASSET_PREFIX_LENGTH = ASSET_PREFIX.length();

    private AssetUriParser() {
    }

    public static boolean isAssetUri(Uri uri) {
        return UriUtil.LOCAL_FILE_SCHEME.equals(uri.getScheme()) && !uri.getPathSegments().isEmpty() && ASSET_PATH_SEGMENT.equals(uri.getPathSegments().get(0));
    }

    public static String toAssetPath(Uri uri) {
        return uri.toString().substring(ASSET_PREFIX_LENGTH);
    }
}
