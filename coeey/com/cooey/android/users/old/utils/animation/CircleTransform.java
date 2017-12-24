package com.cooey.android.users.old.utils.animation;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader.TileMode;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

public class CircleTransform extends BitmapTransformation {
    public CircleTransform(Context context) {
        super(context);
    }

    protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
        return circleCrop(pool, toTransform);
    }

    private static Bitmap circleCrop(BitmapPool pool, Bitmap source) {
        if (source == null) {
            return null;
        }
        int size = Math.min(source.getWidth(), source.getHeight());
        Bitmap squared = Bitmap.createBitmap(source, (source.getWidth() - size) / 2, (source.getHeight() - size) / 2, size, size);
        Bitmap result = pool.get(size, size, Config.ARGB_8888);
        if (result == null) {
            result = Bitmap.createBitmap(size, size, Config.ARGB_8888);
        }
        Canvas canvas = new Canvas(result);
        Paint paint = new Paint();
        paint.setShader(new BitmapShader(squared, TileMode.CLAMP, TileMode.CLAMP));
        paint.setAntiAlias(true);
        float r = ((float) size) / 2.0f;
        canvas.drawCircle(r, r, r, paint);
        return result;
    }

    public String getId() {
        return getClass().getName();
    }
}
