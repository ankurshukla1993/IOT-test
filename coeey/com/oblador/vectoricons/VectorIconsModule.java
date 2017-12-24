package com.oblador.vectoricons;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.views.text.ReactFontManager;
import io.fabric.sdk.android.services.events.EventsFilesManager;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class VectorIconsModule extends ReactContextBaseJavaModule {
    public static final String REACT_CLASS = "RNVectorIconsModule";
    private static final Map<String, Typeface> sTypefaceCache = new HashMap();

    public String getName() {
        return REACT_CLASS;
    }

    public VectorIconsModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @ReactMethod
    public void getImageForFont(String fontFamily, String glyph, Integer fontSize, Integer color, Callback callback) {
        IOException e;
        FileNotFoundException e2;
        OutputStream fos;
        Throwable th;
        Context context = getReactApplicationContext();
        String cacheFolderPath = context.getCacheDir().getAbsolutePath() + "/";
        float scale = context.getResources().getDisplayMetrics().density;
        String scaleSuffix = "@" + (scale == ((float) ((int) scale)) ? Integer.toString((int) scale) : Float.toString(scale)) + "x";
        int size = Math.round(((float) fontSize.intValue()) * scale);
        String cacheFilePath = cacheFolderPath + Integer.toString((fontFamily + ":" + glyph + ":" + color).hashCode(), 32) + EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR + Integer.toString(fontSize.intValue()) + scaleSuffix + ".png";
        String cacheFileUrl = "file://" + cacheFilePath;
        File cacheFile = new File(cacheFilePath);
        if (cacheFile.exists()) {
            callback.invoke(new Object[]{null, cacheFileUrl});
            return;
        }
        FileOutputStream fileOutputStream = null;
        Typeface typeface = ReactFontManager.getInstance().getTypeface(fontFamily, 0, context.getAssets());
        Paint paint = new Paint();
        paint.setTypeface(typeface);
        paint.setColor(color.intValue());
        paint.setTextSize((float) size);
        paint.setAntiAlias(true);
        Rect textBounds = new Rect();
        paint.getTextBounds(glyph, 0, glyph.length(), textBounds);
        Bitmap bitmap = Bitmap.createBitmap(textBounds.width(), textBounds.height(), Config.ARGB_8888);
        new Canvas(bitmap).drawText(glyph, (float) (-textBounds.left), (float) (-textBounds.top), paint);
        try {
            OutputStream fileOutputStream2 = new FileOutputStream(cacheFile);
            try {
                bitmap.compress(CompressFormat.PNG, 100, fileOutputStream2);
                fileOutputStream2.flush();
                fileOutputStream2.close();
                fileOutputStream = null;
                callback.invoke(new Object[]{null, cacheFileUrl});
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                }
            } catch (FileNotFoundException e4) {
                e2 = e4;
                fos = fileOutputStream2;
                try {
                    callback.invoke(new Object[]{e2.getMessage()});
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (IOException e32) {
                            e32.printStackTrace();
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (IOException e322) {
                            e322.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (IOException e5) {
                e322 = e5;
                fos = fileOutputStream2;
                callback.invoke(new Object[]{e322.getMessage()});
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException e3222) {
                        e3222.printStackTrace();
                    }
                }
            } catch (Throwable th3) {
                th = th3;
                fos = fileOutputStream2;
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                throw th;
            }
        } catch (FileNotFoundException e6) {
            e2 = e6;
            callback.invoke(new Object[]{e2.getMessage()});
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
        } catch (IOException e7) {
            e3222 = e7;
            callback.invoke(new Object[]{e3222.getMessage()});
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
        }
    }
}
