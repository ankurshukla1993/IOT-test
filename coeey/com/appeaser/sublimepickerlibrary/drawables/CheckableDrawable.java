package com.appeaser.sublimepickerlibrary.drawables;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.OvershootInterpolator;

public class CheckableDrawable extends Drawable {
    private final int ANIMATION_DURATION_COLLAPSE = 400;
    private final int ANIMATION_DURATION_EXPAND = 500;
    private AnimatorSet asTransition;
    private boolean mChecked;
    private final AnticipateInterpolator mCollapseInterpolator = new AnticipateInterpolator();
    private RectF mCollapsedRect;
    private final OvershootInterpolator mExpandInterpolator = new OvershootInterpolator();
    private RectF mExpandedRect;
    private int mExpandedWidthHeight;
    private int mMaxAlpha;
    private int mMinAlpha;
    private Paint mPaint;
    private boolean mReady;
    private final CRectFEvaluator mRectEvaluator = new CRectFEvaluator();
    private RectF mRectToDraw;

    public interface OnAnimationDone {
        void animationHasBeenCancelled();

        void animationIsDone();
    }

    public CheckableDrawable(int color, boolean checked, int expandedWidthHeight) {
        this.mChecked = checked;
        this.mExpandedWidthHeight = expandedWidthHeight;
        this.mMaxAlpha = Color.alpha(color);
        this.mMinAlpha = 0;
        this.mRectToDraw = new RectF();
        this.mExpandedRect = new RectF();
        this.mCollapsedRect = new RectF();
        this.mPaint = new Paint();
        this.mPaint.setColor(color);
        this.mPaint.setAlpha(this.mMaxAlpha);
        this.mPaint.setAntiAlias(true);
        this.mPaint.setStyle(Style.FILL);
    }

    private void setDimens(int width, int height) {
        this.mReady = true;
        float expandedLeft = ((float) (width - this.mExpandedWidthHeight)) / 2.0f;
        float expandedTop = ((float) (height - this.mExpandedWidthHeight)) / 2.0f;
        float expandedRight = ((float) (this.mExpandedWidthHeight + width)) / 2.0f;
        float expandedBottom = ((float) (this.mExpandedWidthHeight + height)) / 2.0f;
        this.mCollapsedRect = new RectF(((float) width) / 2.0f, ((float) height) / 2.0f, ((float) width) / 2.0f, ((float) height) / 2.0f);
        this.mExpandedRect = new RectF(expandedLeft, expandedTop, expandedRight, expandedBottom);
        reset();
    }

    public void setCheckedOnClick(boolean checked, OnAnimationDone callback) {
        this.mChecked = checked;
        if (this.mReady) {
            reset();
            onClick(callback);
            return;
        }
        invalidateSelf();
    }

    private void onClick(OnAnimationDone callback) {
        animate(this.mChecked, callback);
    }

    private void cancelAnimationInTracks() {
        if (this.asTransition != null && this.asTransition.isRunning()) {
            this.asTransition.cancel();
        }
    }

    public void setChecked(boolean checked) {
        if (this.mChecked != checked) {
            this.mChecked = checked;
            reset();
        }
    }

    private void reset() {
        cancelAnimationInTracks();
        if (this.mChecked) {
            this.mRectToDraw.set(this.mExpandedRect);
        } else {
            this.mRectToDraw.set(this.mCollapsedRect);
        }
        invalidateSelf();
    }

    private void animate(boolean expand, final OnAnimationDone callback) {
        RectF from = expand ? this.mCollapsedRect : this.mExpandedRect;
        RectF to = expand ? this.mExpandedRect : this.mCollapsedRect;
        this.mRectToDraw.set(from);
        ObjectAnimator oaTransition = ObjectAnimator.ofObject(this, "newRectBounds", this.mRectEvaluator, new Object[]{from, to});
        int duration = expand ? 500 : 400;
        oaTransition.setDuration((long) duration);
        oaTransition.setInterpolator(expand ? this.mExpandInterpolator : this.mCollapseInterpolator);
        String str = "alpha";
        int[] iArr = new int[2];
        iArr[0] = expand ? this.mMinAlpha : this.mMaxAlpha;
        iArr[1] = expand ? this.mMaxAlpha : this.mMinAlpha;
        ObjectAnimator.ofInt(this, str, iArr).setDuration((long) duration);
        this.asTransition = new AnimatorSet();
        this.asTransition.playTogether(new Animator[]{oaTransition, oaAlpha});
        this.asTransition.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                if (callback != null) {
                    callback.animationIsDone();
                }
            }

            public void onAnimationCancel(Animator animation) {
                super.onAnimationCancel(animation);
                if (callback != null) {
                    callback.animationHasBeenCancelled();
                }
            }
        });
        this.asTransition.start();
    }

    public void draw(Canvas canvas) {
        if (this.mReady) {
            canvas.drawOval(this.mRectToDraw, this.mPaint);
        } else {
            setDimens(getBounds().width(), getBounds().height());
        }
    }

    public void setAlpha(int alpha) {
        this.mPaint.setAlpha(alpha);
    }

    public void setColorFilter(ColorFilter cf) {
    }

    public int getOpacity() {
        return -3;
    }

    public void setNewRectBounds(RectF newRectBounds) {
        this.mRectToDraw = newRectBounds;
        invalidateSelf();
    }
}
