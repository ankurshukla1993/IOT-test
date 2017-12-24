package com.thefinestartist.utils.etc;

import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v4.util.SimpleArrayMap;
import android.widget.TextView;
import com.thefinestartist.Base;

public class TypefaceUtil {
    private static final SimpleArrayMap<String, Typeface> cache = new SimpleArrayMap();

    private TypefaceUtil() {
    }

    public static Typeface get(@NonNull String path) {
        synchronized (cache) {
            if (cache.containsKey(path)) {
                Typeface typeface = (Typeface) cache.get(path);
                return typeface;
            }
            try {
                Typeface typeface2 = Typeface.createFromAsset(Base.getContext().getAssets(), path);
                cache.put(path, typeface2);
                return typeface2;
            } catch (RuntimeException e) {
                return null;
            }
        }
    }

    public static void setTypeface(@NonNull String path, TextView... textViews) {
        if (textViews != null) {
            for (TextView textView : textViews) {
                if (textView != null) {
                    textView.setTypeface(get(path));
                }
            }
        }
    }

    public static void setTypeface(@NonNull String path, boolean includeFontPadding, TextView... textViews) {
        if (textViews != null) {
            for (TextView textView : textViews) {
                if (textView != null) {
                    textView.setTypeface(get(path));
                    textView.setIncludeFontPadding(includeFontPadding);
                }
            }
        }
    }
}
