package com.google.android.gms.internal;

import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.util.Log;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

final class zzdxh {
    private static List<Object> zze(JSONArray jSONArray) throws JSONException {
        List<Object> arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            Object obj = jSONArray.get(i);
            if (obj instanceof JSONArray) {
                obj = zze((JSONArray) obj);
            } else if (obj instanceof JSONObject) {
                obj = zzz((JSONObject) obj);
            }
            arrayList.add(obj);
        }
        return arrayList;
    }

    @Nullable
    public static Map<String, Object> zzon(String str) {
        Map<String, Object> map = null;
        if (!TextUtils.isEmpty(str)) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject != JSONObject.NULL) {
                    map = zzz(jSONObject);
                }
            } catch (Throwable e) {
                Log.d("RawUserInfoParser", "Failed to parse JSONObject into Map.");
                throw new zzdto(e);
            }
        }
        return map;
    }

    private static Map<String, Object> zzz(JSONObject jSONObject) throws JSONException {
        Map<String, Object> arrayMap = new ArrayMap();
        Iterator keys = jSONObject.keys();
        while (keys.hasNext()) {
            String str = (String) keys.next();
            Object obj = jSONObject.get(str);
            if (obj instanceof JSONArray) {
                obj = zze((JSONArray) obj);
            } else if (obj instanceof JSONObject) {
                obj = zzz((JSONObject) obj);
            }
            arrayMap.put(str, obj);
        }
        return arrayMap;
    }
}
