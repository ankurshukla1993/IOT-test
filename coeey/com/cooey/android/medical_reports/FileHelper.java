package com.cooey.android.medical_reports;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

public class FileHelper {
    public static String getExtensionForFilePath(String path) {
        String extension = "";
        try {
            extension = path.substring(path.lastIndexOf("."));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return extension;
    }

    public static String getFileNameFromContentURI(Context context, Uri uri) {
        Cursor metaCursor = context.getContentResolver().query(uri, new String[]{"_display_name"}, null, null, null);
        if (metaCursor != null) {
            try {
                if (metaCursor.moveToFirst()) {
                    String fileName = metaCursor.getString(0);
                    return fileName;
                }
                metaCursor.close();
            } finally {
                metaCursor.close();
            }
        }
        return null;
    }
}
