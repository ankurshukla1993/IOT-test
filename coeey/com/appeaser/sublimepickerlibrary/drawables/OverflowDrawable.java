package com.appeaser.sublimepickerlibrary.drawables;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import com.appeaser.sublimepickerlibrary.C0563R;

public class OverflowDrawable extends Drawable {
    PointF center1;
    PointF center2;
    PointF center3;
    Paint mPaintCircle = new Paint();
    float mRadius;
    int mWidthHeight;

    public OverflowDrawable(Context context, int color) {
        Resources res = context.getResources();
        this.mWidthHeight = res.getDimensionPixelSize(C0563R.dimen.options_size);
        float density = ((float) res.getDisplayMetrics().densityDpi) / 160.0f;
        this.mRadius = 2.0f * density;
        float centerXY = ((float) this.mWidthHeight) / 2.0f;
        this.center1 = new PointF(centerXY, centerXY - (6.0f * density));
        this.center2 = new PointF(centerXY, centerXY);
        this.center3 = new PointF(centerXY, (6.0f * density) + centerXY);
        this.mPaintCircle.setColor(color);
        this.mPaintCircle.setAntiAlias(true);
    }

    public void draw(Canvas canvas) {
        canvas.drawCircle(this.center1.x, this.center1.y, this.mRadius, this.mPaintCircle);
        canvas.drawCircle(this.center2.x, this.center2.y, this.mRadius, this.mPaintCircle);
        canvas.drawCircle(this.center3.x, this.center3.y, this.mRadius, this.mPaintCircle);
    }

    public int getMinimumHeight() {
        return this.mWidthHeight;
    }

    public int getMinimumWidth() {
        return this.mWidthHeight;
    }

    public int getIntrinsicHeight() {
        return this.mWidthHeight;
    }

    public int getIntrinsicWidth() {
        return this.mWidthHeight;
    }

    public void setAlpha(int alpha) {
        this.mPaintCircle.setAlpha(alpha);
        invalidateSelf();
    }

    public void setColorFilter(ColorFilter cf) {
        this.mPaintCircle.setColorFilter(cf);
        invalidateSelf();
    }

    public int getOpacity() {
        return -1;
    }
}
