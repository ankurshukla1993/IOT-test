package com.github.sundeepk.compactcalendarview;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.support.annotation.NonNull;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.OvershootInterpolator;
import com.google.android.flexbox.FlexItem;

class AnimationHandler {
    private static final int HEIGHT_ANIM_DURATION_MILLIS = 650;
    private static final int INDICATOR_ANIM_DURATION_MILLIS = 600;
    private CompactCalendarView$CompactCalendarAnimationListener compactCalendarAnimationListener;
    private CompactCalendarController compactCalendarController;
    private CompactCalendarView compactCalendarView;
    private boolean isAnimating = false;

    class C14992 extends AnimatorListener {
        C14992() {
        }

        public void onAnimationStart(Animator animation) {
            AnimationHandler.this.compactCalendarController.setAnimationStatus(3);
        }

        public void onAnimationEnd(Animator animation) {
            AnimationHandler.this.compactCalendarController.setAnimationStatus(0);
            AnimationHandler.this.onOpen();
            AnimationHandler.this.isAnimating = false;
        }
    }

    class C15014 extends AnimatorListener {
        C15014() {
        }

        public void onAnimationStart(Animator animation) {
            AnimationHandler.this.compactCalendarController.setAnimationStatus(3);
        }

        public void onAnimationEnd(Animator animation) {
        }
    }

    class C15025 implements AnimatorUpdateListener {
        C15025() {
        }

        public void onAnimationUpdate(ValueAnimator animation) {
            AnimationHandler.this.compactCalendarController.setGrowFactorIndicator(((Float) animation.getAnimatedValue()).floatValue());
            AnimationHandler.this.compactCalendarView.invalidate();
        }
    }

    class C15036 extends AnimationListener {
        C15036() {
        }

        public void onAnimationEnd(Animation animation) {
            super.onAnimationEnd(animation);
            AnimationHandler.this.onOpen();
            AnimationHandler.this.isAnimating = false;
        }
    }

    class C15047 extends AnimationListener {
        C15047() {
        }

        public void onAnimationEnd(Animation animation) {
            super.onAnimationEnd(animation);
            AnimationHandler.this.onClose();
            AnimationHandler.this.isAnimating = false;
        }
    }

    AnimationHandler(CompactCalendarController compactCalendarController, CompactCalendarView compactCalendarView) {
        this.compactCalendarController = compactCalendarController;
        this.compactCalendarView = compactCalendarView;
    }

    void setCompactCalendarAnimationListener(CompactCalendarView$CompactCalendarAnimationListener compactCalendarAnimationListener) {
        this.compactCalendarAnimationListener = compactCalendarAnimationListener;
    }

    void openCalendar() {
        if (!this.isAnimating) {
            this.isAnimating = true;
            Animation heightAnim = getCollapsingAnimation(true);
            heightAnim.setDuration(650);
            heightAnim.setInterpolator(new AccelerateDecelerateInterpolator());
            this.compactCalendarController.setAnimationStatus(2);
            setUpAnimationLisForOpen(heightAnim);
            this.compactCalendarView.getLayoutParams().height = 0;
            this.compactCalendarView.requestLayout();
            this.compactCalendarView.startAnimation(heightAnim);
        }
    }

    void closeCalendar() {
        if (!this.isAnimating) {
            this.isAnimating = true;
            Animation heightAnim = getCollapsingAnimation(false);
            heightAnim.setDuration(650);
            heightAnim.setInterpolator(new AccelerateDecelerateInterpolator());
            setUpAnimationLisForClose(heightAnim);
            this.compactCalendarController.setAnimationStatus(2);
            this.compactCalendarView.getLayoutParams().height = this.compactCalendarView.getHeight();
            this.compactCalendarView.requestLayout();
            this.compactCalendarView.startAnimation(heightAnim);
        }
    }

    void openCalendarWithAnimation() {
        if (!this.isAnimating) {
            this.isAnimating = true;
            Animator indicatorAnim = getIndicatorAnimator(FlexItem.FLEX_SHRINK_DEFAULT, this.compactCalendarController.getDayIndicatorRadius());
            Animation heightAnim = getExposeCollapsingAnimation(true);
            this.compactCalendarView.getLayoutParams().height = 0;
            this.compactCalendarView.requestLayout();
            setUpAnimationLisForExposeOpen(indicatorAnim, heightAnim);
            this.compactCalendarView.startAnimation(heightAnim);
        }
    }

    void closeCalendarWithAnimation() {
        if (!this.isAnimating) {
            this.isAnimating = true;
            Animator indicatorAnim = getIndicatorAnimator(this.compactCalendarController.getDayIndicatorRadius(), FlexItem.FLEX_SHRINK_DEFAULT);
            Animation heightAnim = getExposeCollapsingAnimation(false);
            this.compactCalendarView.getLayoutParams().height = this.compactCalendarView.getHeight();
            this.compactCalendarView.requestLayout();
            setUpAnimationLisForExposeClose(indicatorAnim, heightAnim);
            this.compactCalendarView.startAnimation(heightAnim);
        }
    }

    private void setUpAnimationLisForExposeOpen(final Animator indicatorAnim, Animation heightAnim) {
        heightAnim.setAnimationListener(new AnimationListener() {
            public void onAnimationStart(Animation animation) {
                AnimationHandler.this.compactCalendarController.setAnimationStatus(1);
            }

            public void onAnimationEnd(Animation animation) {
                indicatorAnim.start();
            }
        });
        indicatorAnim.addListener(new C14992());
    }

    private void setUpAnimationLisForExposeClose(final Animator indicatorAnim, Animation heightAnim) {
        heightAnim.setAnimationListener(new AnimationListener() {
            public void onAnimationStart(Animation animation) {
                AnimationHandler.this.compactCalendarController.setAnimationStatus(1);
                indicatorAnim.start();
            }

            public void onAnimationEnd(Animation animation) {
                AnimationHandler.this.compactCalendarController.setAnimationStatus(0);
                AnimationHandler.this.onClose();
                AnimationHandler.this.isAnimating = false;
            }
        });
        indicatorAnim.addListener(new C15014());
    }

    @NonNull
    private Animation getExposeCollapsingAnimation(boolean isCollapsing) {
        Animation heightAnim = getCollapsingAnimation(isCollapsing);
        heightAnim.setDuration(650);
        heightAnim.setInterpolator(new AccelerateDecelerateInterpolator());
        return heightAnim;
    }

    @NonNull
    private Animation getCollapsingAnimation(boolean isCollapsing) {
        return new CollapsingAnimation(this.compactCalendarView, this.compactCalendarController, this.compactCalendarController.getTargetHeight(), getTargetGrowRadius(), isCollapsing);
    }

    @NonNull
    private Animator getIndicatorAnimator(float from, float to) {
        ValueAnimator animIndicator = ValueAnimator.ofFloat(new float[]{from, to});
        animIndicator.setDuration(600);
        animIndicator.setInterpolator(new OvershootInterpolator());
        animIndicator.addUpdateListener(new C15025());
        return animIndicator;
    }

    private int getTargetGrowRadius() {
        return (int) (0.5d * Math.sqrt((double) ((this.compactCalendarController.getTargetHeight() * this.compactCalendarController.getTargetHeight()) + (this.compactCalendarController.getWidth() * this.compactCalendarController.getWidth()))));
    }

    private void onOpen() {
        if (this.compactCalendarAnimationListener != null) {
            this.compactCalendarAnimationListener.onOpened();
        }
    }

    private void onClose() {
        if (this.compactCalendarAnimationListener != null) {
            this.compactCalendarAnimationListener.onClosed();
        }
    }

    private void setUpAnimationLisForOpen(Animation openAnimation) {
        openAnimation.setAnimationListener(new C15036());
    }

    private void setUpAnimationLisForClose(Animation openAnimation) {
        openAnimation.setAnimationListener(new C15047());
    }

    public boolean isAnimating() {
        return this.isAnimating;
    }
}
