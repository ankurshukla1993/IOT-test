package com.facebook.react.modules.statusbar;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.annotation.TargetApi;
import android.app.Activity;

class StatusBarModule$1 implements Runnable {
    final /* synthetic */ StatusBarModule this$0;
    final /* synthetic */ Activity val$activity;
    final /* synthetic */ boolean val$animated;
    final /* synthetic */ int val$color;

    class C13211 implements AnimatorUpdateListener {
        C13211() {
        }

        public void onAnimationUpdate(ValueAnimator animator) {
            StatusBarModule$1.this.val$activity.getWindow().setStatusBarColor(((Integer) animator.getAnimatedValue()).intValue());
        }
    }

    StatusBarModule$1(StatusBarModule this$0, boolean z, Activity activity, int i) {
        this.this$0 = this$0;
        this.val$animated = z;
        this.val$activity = activity;
        this.val$color = i;
    }

    @TargetApi(21)
    public void run() {
        if (this.val$animated) {
            int curColor = this.val$activity.getWindow().getStatusBarColor();
            ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), new Object[]{Integer.valueOf(curColor), Integer.valueOf(this.val$color)});
            colorAnimation.addUpdateListener(new C13211());
            colorAnimation.setDuration(300).setStartDelay(0);
            colorAnimation.start();
            return;
        }
        this.val$activity.getWindow().setStatusBarColor(this.val$color);
    }
}
