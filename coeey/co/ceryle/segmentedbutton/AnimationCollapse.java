package co.ceryle.segmentedbutton;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.view.View;
import android.view.animation.Interpolator;

class AnimationCollapse {
    AnimationCollapse() {
    }

    static void expand(final View v, Interpolator interpolator, int duration, int targetWidth) {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(new int[]{v.getWidth(), targetWidth});
        valueAnimator.addUpdateListener(new AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                v.getLayoutParams().width = ((Integer) animation.getAnimatedValue()).intValue();
                v.requestLayout();
            }
        });
        valueAnimator.setInterpolator(interpolator);
        valueAnimator.setDuration((long) duration);
        valueAnimator.start();
    }
}
