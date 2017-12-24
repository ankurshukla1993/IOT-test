package com.facebook.drawee.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.annotation.TargetApi;
import android.graphics.Rect;
import android.transition.ChangeBounds;
import android.transition.Transition;
import android.transition.TransitionSet;
import android.transition.TransitionValues;
import android.view.ViewGroup;
import com.facebook.drawee.drawable.ScalingUtils$ScaleType;
import com.facebook.drawee.drawable.ScalingUtils.InterpolatingScaleType;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.google.android.flexbox.FlexItem;

@TargetApi(19)
public class DraweeTransition extends Transition {
    private static final String PROPNAME_BOUNDS = "draweeTransition:bounds";
    private final ScalingUtils$ScaleType mFromScale;
    private final ScalingUtils$ScaleType mToScale;

    public static TransitionSet createTransitionSet(ScalingUtils$ScaleType fromScale, ScalingUtils$ScaleType toScale) {
        TransitionSet transitionSet = new TransitionSet();
        transitionSet.addTransition(new ChangeBounds());
        transitionSet.addTransition(new DraweeTransition(fromScale, toScale));
        return transitionSet;
    }

    public DraweeTransition(ScalingUtils$ScaleType fromScale, ScalingUtils$ScaleType toScale) {
        this.mFromScale = fromScale;
        this.mToScale = toScale;
    }

    public void captureStartValues(TransitionValues transitionValues) {
        captureValues(transitionValues);
    }

    public void captureEndValues(TransitionValues transitionValues) {
        captureValues(transitionValues);
    }

    public Animator createAnimator(ViewGroup sceneRoot, TransitionValues startValues, TransitionValues endValues) {
        if (startValues == null || endValues == null) {
            return null;
        }
        Rect startBounds = (Rect) startValues.values.get(PROPNAME_BOUNDS);
        Rect endBounds = (Rect) endValues.values.get(PROPNAME_BOUNDS);
        if (startBounds == null || endBounds == null || this.mFromScale == this.mToScale) {
            return null;
        }
        final GenericDraweeView draweeView = startValues.view;
        final InterpolatingScaleType scaleType = new InterpolatingScaleType(this.mFromScale, this.mToScale, startBounds, endBounds);
        ((GenericDraweeHierarchy) draweeView.getHierarchy()).setActualImageScaleType(scaleType);
        Animator animator = ValueAnimator.ofFloat(new float[]{0.0f, FlexItem.FLEX_SHRINK_DEFAULT});
        animator.addUpdateListener(new AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                scaleType.setValue(((Float) animation.getAnimatedValue()).floatValue());
            }
        });
        animator.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animation) {
                ((GenericDraweeHierarchy) draweeView.getHierarchy()).setActualImageScaleType(DraweeTransition.this.mToScale);
            }
        });
        return animator;
    }

    private void captureValues(TransitionValues transitionValues) {
        if (transitionValues.view instanceof GenericDraweeView) {
            transitionValues.values.put(PROPNAME_BOUNDS, new Rect(0, 0, transitionValues.view.getWidth(), transitionValues.view.getHeight()));
        }
    }
}
