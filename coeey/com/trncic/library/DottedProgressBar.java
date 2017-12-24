package com.trncic.library;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.View.MeasureSpec;

public class DottedProgressBar extends View {
    private boolean isActiveDrawable = false;
    private boolean isInProgress;
    private boolean isInactiveDrawable = false;
    private Drawable mActiveDot;
    private int mActiveDotColor;
    private int mActiveDotIndex;
    private final float mDotSize;
    private int mEmptyDotsColor;
    private Handler mHandler;
    private Drawable mInactiveDot;
    private final int mJumpingSpeed;
    private int mNumberOfDots;
    private int mPaddingLeft;
    private Paint mPaint;
    private Runnable mRunnable = new C23421();
    private final float mSpacing;

    class C23421 implements Runnable {
        C23421() {
        }

        public void run() {
            if (DottedProgressBar.this.mNumberOfDots != 0) {
                DottedProgressBar.this.mActiveDotIndex = (DottedProgressBar.this.mActiveDotIndex + 1) % DottedProgressBar.this.mNumberOfDots;
            }
            DottedProgressBar.this.invalidate();
            DottedProgressBar.this.mHandler.postDelayed(DottedProgressBar.this.mRunnable, (long) DottedProgressBar.this.mJumpingSpeed);
        }
    }

    public DottedProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, C2343R.styleable.DottedProgressBar, 0, 0);
        this.isInProgress = false;
        this.mHandler = new Handler();
        TypedValue value = new TypedValue();
        a.getValue(C2343R.styleable.DottedProgressBar_activeDot, value);
        if (value.type < 28 || value.type > 31) {
            try {
                if (value.type == 3) {
                    this.isActiveDrawable = true;
                    this.mActiveDot = getResources().getDrawable(value.resourceId);
                }
            } finally {
                a.recycle();
            }
        } else {
            this.isActiveDrawable = false;
            this.mActiveDotColor = getResources().getColor(value.resourceId);
        }
        a.getValue(C2343R.styleable.DottedProgressBar_inactiveDot, value);
        if (value.type >= 28 && value.type <= 31) {
            this.isInactiveDrawable = false;
            this.mEmptyDotsColor = getResources().getColor(value.resourceId);
        } else if (value.type == 3) {
            this.isInactiveDrawable = true;
            this.mInactiveDot = getResources().getDrawable(value.resourceId);
        }
        this.mDotSize = (float) a.getDimensionPixelSize(C2343R.styleable.DottedProgressBar_dotSize, 5);
        this.mSpacing = (float) a.getDimensionPixelSize(C2343R.styleable.DottedProgressBar_spacing, 10);
        this.mActiveDotIndex = a.getInteger(C2343R.styleable.DottedProgressBar_activeDotIndex, 0);
        this.mJumpingSpeed = a.getInt(C2343R.styleable.DottedProgressBar_jumpingSpeed, 500);
        this.mPaint = new Paint(1);
        this.mPaint.setStyle(Style.FILL);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < this.mNumberOfDots; i++) {
            int x = (int) ((((float) (getPaddingLeft() + this.mPaddingLeft)) + (this.mSpacing / 2.0f)) + (((float) i) * (this.mSpacing + this.mDotSize)));
            if (this.isInactiveDrawable) {
                this.mInactiveDot.setBounds(x, getPaddingTop(), (int) (((float) x) + this.mDotSize), getPaddingTop() + ((int) this.mDotSize));
                this.mInactiveDot.draw(canvas);
            } else {
                this.mPaint.setColor(this.mEmptyDotsColor);
                canvas.drawCircle(((float) x) + (this.mDotSize / 2.0f), ((float) getPaddingTop()) + (this.mDotSize / 2.0f), this.mDotSize / 2.0f, this.mPaint);
            }
        }
        if (this.isInProgress) {
            x = (int) ((((float) (getPaddingLeft() + this.mPaddingLeft)) + (this.mSpacing / 2.0f)) + (((float) this.mActiveDotIndex) * (this.mSpacing + this.mDotSize)));
            if (this.isActiveDrawable) {
                this.mActiveDot.setBounds(x, getPaddingTop(), (int) (((float) x) + this.mDotSize), getPaddingTop() + ((int) this.mDotSize));
                this.mActiveDot.draw(canvas);
                return;
            }
            this.mPaint.setColor(this.mActiveDotColor);
            canvas.drawCircle(((float) x) + (this.mDotSize / 2.0f), ((float) getPaddingTop()) + (this.mDotSize / 2.0f), this.mDotSize / 2.0f, this.mPaint);
        }
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int parentWidth = MeasureSpec.getSize(widthMeasureSpec);
        int widthWithoutPadding = (parentWidth - getPaddingLeft()) - getPaddingRight();
        int heigthWithoutPadding = (MeasureSpec.getSize(heightMeasureSpec) - getPaddingTop()) - getPaddingBottom();
        int calculatedHeight = (getPaddingTop() + getPaddingBottom()) + ((int) this.mDotSize);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(parentWidth, calculatedHeight);
        this.mNumberOfDots = calculateDotsNumber(widthWithoutPadding);
    }

    private int calculateDotsNumber(int width) {
        int number = (int) (((float) width) / (this.mDotSize + this.mSpacing));
        this.mPaddingLeft = (int) ((((float) width) % (this.mDotSize + this.mSpacing)) / 2.0f);
        return number;
    }

    public void startProgress() {
        this.isInProgress = true;
        this.mActiveDotIndex = -1;
        this.mHandler.removeCallbacks(this.mRunnable);
        this.mHandler.post(this.mRunnable);
    }

    public void stopProgress() {
        this.isInProgress = false;
        this.mHandler.removeCallbacks(this.mRunnable);
        invalidate();
    }
}
