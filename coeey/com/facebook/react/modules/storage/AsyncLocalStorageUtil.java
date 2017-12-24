package com.facebook.react.modules.storage;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.facebook.react.bridge.ReadableArray;
import java.util.Arrays;
import java.util.Iterator;
import javax.annotation.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

public class AsyncLocalStorageUtil {
    static String buildKeySelection(int selectionCount) {
        String[] list = new String[selectionCount];
        Arrays.fill(list, "?");
        return "key IN (" + TextUtils.join(", ", list) + ")";
    }

    static String[] buildKeySelectionArgs(ReadableArray keys, int start, int count) {
        String[] selectionArgs = new String[count];
        for (int keyIndex = 0; keyIndex < count; keyIndex++) {
            selectionArgs[keyIndex] = keys.getString(start + keyIndex);
        }
        return selectionArgs;
    }

    @Nullable
    public static String getItemImpl(SQLiteDatabase db, String key) {
        String str = null;
        SQLiteDatabase sQLiteDatabase = db;
        Cursor cursor = sQLiteDatabase.query("catalystLocalStorage", new String[]{"value"}, "key=?", new String[]{key}, str, str, str);
        try {
            if (cursor.moveToFirst()) {
                str = cursor.getString(0);
                cursor.close();
            }
            return str;
        } finally {
            cursor.close();
        }
    }

    static boolean setItemImpl(SQLiteDatabase db, String key, String value) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("key", key);
        contentValues.put("value", value);
        return -1 != db.insertWithOnConflict("catalystLocalStorage", null, contentValues, 5);
    }

    static boolean mergeImpl(SQLiteDatabase db, String key, String value) throws JSONException {
        String newValue;
        String oldValue = getItemImpl(db, key);
        if (oldValue == null) {
            newValue = value;
        } else {
            JSONObject oldJSON = new JSONObject(oldValue);
            deepMergeInto(oldJSON, new JSONObject(value));
            newValue = oldJSON.toString();
        }
        return setItemImpl(db, key, newValue);
    }

    private static void deepMergeInto(JSONObject oldJSON, JSONObject newJSON) throws JSONException {
        Iterator<?> keys = newJSON.keys();
        while (keys.hasNext()) {
            String key = (String) keys.next();
            JSONObject newJSONObject = newJSON.optJSONObject(key);
            JSONObject oldJSONObject = oldJSON.optJSONObject(key);
            if (newJSONObject == null || oldJSONObject == null) {
                oldJSON.put(key, newJSON.get(key));
            } else {
                deepMergeInto(oldJSONObject, newJSONObject);
                oldJSON.put(key, oldJSONObject);
            }
        }
    }
}
