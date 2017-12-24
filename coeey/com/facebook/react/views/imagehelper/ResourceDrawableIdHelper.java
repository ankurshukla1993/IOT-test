package com.facebook.react.views.imagehelper;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.net.Uri.Builder;
import io.fabric.sdk.android.services.events.EventsFilesManager;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;

public class ResourceDrawableIdHelper {
    private static final String LOCAL_RESOURCE_SCHEME = "res";
    private static ResourceDrawableIdHelper sResourceDrawableIdHelper;
    private Map<String, Integer> mResourceDrawableIdMap = new HashMap();

    private ResourceDrawableIdHelper() {
    }

    public static ResourceDrawableIdHelper getInstance() {
        if (sResourceDrawableIdHelper == null) {
            sResourceDrawableIdHelper = new ResourceDrawableIdHelper();
        }
        return sResourceDrawableIdHelper;
    }

    public void clear() {
        this.mResourceDrawableIdMap.clear();
    }

    public int getResourceDrawableId(Context context, @Nullable String name) {
        if (name == null || name.isEmpty()) {
            return 0;
        }
        name = name.toLowerCase().replace("-", EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR);
        if (this.mResourceDrawableIdMap.containsKey(name)) {
            return ((Integer) this.mResourceDrawableIdMap.get(name)).intValue();
        }
        int id = context.getResources().getIdentifier(name, "drawable", context.getPackageName());
        this.mResourceDrawableIdMap.put(name, Integer.valueOf(id));
        return id;
    }

    @Nullable
    public Drawable getResourceDrawable(Context context, @Nullable String name) {
        int resId = getResourceDrawableId(context, name);
        return resId > 0 ? context.getResources().getDrawable(resId) : null;
    }

    public Uri getResourceDrawableUri(Context context, @Nullable String name) {
        int resId = getResourceDrawableId(context, name);
        return resId > 0 ? new Builder().scheme("res").path(String.valueOf(resId)).build() : Uri.EMPTY;
    }
}
