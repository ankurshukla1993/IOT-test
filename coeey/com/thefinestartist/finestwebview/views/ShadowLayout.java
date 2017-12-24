package com.thefinestartist.finestwebview.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build.VERSION;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import com.thefinestartist.finestwebview.C2332R;

public class ShadowLayout extends FrameLayout {
    private float cornerRadius;
    private float dx;
    private float dy;
    private int shadowColor;
    private float shadowSize;

    public ShadowLayout(Context context) {
        super(context);
        setWillNotDraw(false);
        initAttributes(null);
        setPadding();
    }

    public ShadowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);
        initAttributes(attrs);
        setPadding();
    }

    public ShadowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setWillNotDraw(false);
        initAttributes(attrs);
        setPadding();
    }

    private void initAttributes(AttributeSet attrs) {
        TypedArray attr = getContext().obtainStyledAttributes(attrs, C2332R.styleable.ShadowLayout, 0, 0);
        if (attr != null) {
            try {
                this.cornerRadius = attr.getDimension(C2332R.styleable.ShadowLayout_slCornerRadius, getResources().getDimension(C2332R.dimen.defaultMenuDropShadowCornerRadius));
                this.shadowSize = attr.getDimension(C2332R.styleable.ShadowLayout_slShadowSize, getResources().getDimension(C2332R.dimen.defaultMenuDropShadowSize));
                this.dx = attr.getDimension(C2332R.styleable.ShadowLayout_slDx, 0.0f);
                this.dy = attr.getDimension(C2332R.styleable.ShadowLayout_slDy, 0.0f);
                this.shadowColor = attr.getColor(C2332R.styleable.ShadowLayout_slShadowColor, ContextCompat.getColor(getContext(), C2332R.color.finestBlack10));
            } finally {
                attr.recycle();
            }
        }
    }

    private void setPadding() {
        int xPadding = (int) (this.shadowSize + Math.abs(this.dx));
        int yPadding = (int) (this.shadowSize + Math.abs(this.dy));
        setPadding(xPadding, yPadding, xPadding, yPadding);
    }

    public void setShadowColor(int shadowColor) {
        this.shadowColor = shadowColor;
        invalidate();
    }

    public void setShadowSize(float shadowSize) {
        this.shadowSize = shadowSize;
        setPadding();
    }

    public void setCornerRadius(float cornerRadius) {
        this.cornerRadius = cornerRadius;
        invalidate();
    }

    public void setDx(float dx) {
        this.dx = dx;
        setPadding();
    }

    public void setDy(float dy) {
        this.dy = dy;
        setPadding();
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setBackgroundCompat(canvas.getWidth(), canvas.getHeight());
    }

    private void setBackgroundCompat(int w, int h) {
        BitmapDrawable drawable = new BitmapDrawable(getResources(), createShadowBitmap(w, h, this.cornerRadius, this.shadowSize, this.dx, this.dy, this.shadowColor, 0));
        if (VERSION.SDK_INT <= 16) {
            setBackgroundDrawable(drawable);
        } else {
            setBackground(drawable);
        }
    }

    private Bitmap createShadowBitmap(int shadowWidth, int shadowHeight, float cornerRadius, float shadowSize, float dx, float dy, int shadowColor, int fillColor) {
        Bitmap output = Bitmap.createBitmap(shadowWidth, shadowHeight, Config.ALPHA_8);
        Canvas canvas = new Canvas(output);
        RectF shadowRect = new RectF(shadowSize, shadowSize, ((float) shadowWidth) - shadowSize, ((float) shadowHeight) - shadowSize);
        if (dy > 0.0f) {
            shadowRect.top += dy;
            shadowRect.bottom -= dy;
        } else if (dy < 0.0f) {
            shadowRect.top += Math.abs(dy);
            shadowRect.bottom -= Math.abs(dy);
        }
        if (dx > 0.0f) {
            shadowRect.left += dx;
            shadowRect.right -= dx;
        } else if (dx < 0.0f) {
            shadowRect.left += Math.abs(dx);
            shadowRect.right -= Math.abs(dx);
        }
        Paint shadowPaint = new Paint();
        shadowPaint.setAntiAlias(true);
        shadowPaint.setColor(fillColor);
        shadowPaint.setStyle(Style.FILL);
        shadowPaint.setShadowLayer(shadowSize, dx, dy, shadowColor);
        canvas.drawRoundRect(shadowRect, cornerRadius, cornerRadius, shadowPaint);
        return output;
    }

    protected int getSuggestedMinimumWidth() {
        return 0;
    }

    protected int getSuggestedMinimumHeight() {
        return 0;
    }
}
