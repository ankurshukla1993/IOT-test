package com.facebook.common.media;

import android.webkit.MimeTypeMap;
import com.facebook.common.internal.ImmutableMap;
import java.util.Locale;
import java.util.Map;
import javax.annotation.Nullable;

public class MediaUtils {
    public static final Map<String, String> ADDITIONAL_ALLOWED_MIME_TYPES = ImmutableMap.of("mkv", "video/x-matroska");

    public static boolean isPhoto(@Nullable String mimeType) {
        return mimeType != null && mimeType.startsWith("image/");
    }

    public static boolean isVideo(@Nullable String mimeType) {
        return mimeType != null && mimeType.startsWith("video/");
    }

    @Nullable
    public static String extractMime(String path) {
        String extension = extractExtension(path);
        if (extension == null) {
            return null;
        }
        extension = extension.toLowerCase(Locale.US);
        String mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
        if (mimeType == null) {
            return (String) ADDITIONAL_ALLOWED_MIME_TYPES.get(extension);
        }
        return mimeType;
    }

    @Nullable
    private static String extractExtension(String path) {
        int pos = path.lastIndexOf(46);
        if (pos < 0 || pos == path.length() - 1) {
            return null;
        }
        return path.substring(pos + 1);
    }

    public static boolean isNonNativeSupportedMimeType(String mimeType) {
        return ADDITIONAL_ALLOWED_MIME_TYPES.containsValue(mimeType);
    }
}
