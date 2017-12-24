package com.bartoszlipinski.viewpropertyobjectanimator;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.Animator.AnimatorPauseListener;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.annotation.SuppressLint;
import android.os.Build.VERSION;
import android.support.v4.util.ArrayMap;
import android.util.Property;
import android.view.View;
import android.view.animation.Interpolator;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ViewPropertyObjectAnimator {
    private DimensionChangeListener mDimensionListener;
    private long mDuration = -1;
    private Interpolator mInterpolator;
    private List<AnimatorListener> mListeners = new ArrayList();
    private MarginChangeListener mMarginListener;
    private PaddingChangeListener mPaddingListener;
    private List<AnimatorPauseListener> mPauseListeners = new ArrayList();
    private ArrayMap<Property<View, Float>, PropertyValuesHolder> mPropertyHoldersMap = new ArrayMap();
    private ScrollChangeListener mScrollListener;
    private long mStartDelay = -1;
    private List<AnimatorUpdateListener> mUpdateListeners = new ArrayList();
    private final WeakReference<View> mView;
    private boolean mWithLayer = false;

    class C06323 extends AnimatorListenerAdapter {
        C06323() {
        }

        public void onAnimationStart(Animator animation) {
            if (ViewPropertyObjectAnimator.this.hasView()) {
                ((View) ViewPropertyObjectAnimator.this.mView.get()).setLayerType(2, null);
            }
        }

        public void onAnimationEnd(Animator animation) {
            if (ViewPropertyObjectAnimator.this.hasView()) {
                ((View) ViewPropertyObjectAnimator.this.mView.get()).setLayerType(0, null);
            }
        }
    }

    private ViewPropertyObjectAnimator(View view) {
        this.mView = new WeakReference(view);
    }

    public static ViewPropertyObjectAnimator animate(View view) {
        return new ViewPropertyObjectAnimator(view);
    }

    private void animateProperty(Property<View, Float> property, float toValue) {
        if (hasView()) {
            animatePropertyBetween(property, ((Float) property.get(this.mView.get())).floatValue(), toValue);
        }
    }

    private void animatePropertyBy(Property<View, Float> property, float byValue) {
        if (hasView()) {
            float fromValue = ((Float) property.get(this.mView.get())).floatValue();
            animatePropertyBetween(property, fromValue, fromValue + byValue);
        }
    }

    private void animatePropertyBetween(Property<View, Float> property, float fromValue, float toValue) {
        this.mPropertyHoldersMap.remove(property);
        this.mPropertyHoldersMap.put(property, PropertyValuesHolder.ofFloat(property, new float[]{fromValue, toValue}));
    }

    public ViewPropertyObjectAnimator scaleX(float scaleX) {
        animateProperty(View.SCALE_X, scaleX);
        return this;
    }

    public ViewPropertyObjectAnimator scaleXBy(float scaleXBy) {
        animatePropertyBy(View.SCALE_X, scaleXBy);
        return this;
    }

    public ViewPropertyObjectAnimator scaleY(float scaleY) {
        animateProperty(View.SCALE_Y, scaleY);
        return this;
    }

    public ViewPropertyObjectAnimator scaleYBy(float scaleYBy) {
        animatePropertyBy(View.SCALE_Y, scaleYBy);
        return this;
    }

    public ViewPropertyObjectAnimator scales(float scales) {
        scaleY(scales);
        scaleX(scales);
        return this;
    }

    public ViewPropertyObjectAnimator scalesBy(float scalesBy) {
        scaleYBy(scalesBy);
        scaleXBy(scalesBy);
        return this;
    }

    public ViewPropertyObjectAnimator translationX(float translationX) {
        animateProperty(View.TRANSLATION_X, translationX);
        return this;
    }

    public ViewPropertyObjectAnimator translationXBy(float translationXBy) {
        animatePropertyBy(View.TRANSLATION_X, translationXBy);
        return this;
    }

    public ViewPropertyObjectAnimator translationY(float translationY) {
        animateProperty(View.TRANSLATION_Y, translationY);
        return this;
    }

    public ViewPropertyObjectAnimator translationYBy(float translationYBy) {
        animatePropertyBy(View.TRANSLATION_Y, translationYBy);
        return this;
    }

    @SuppressLint({"NewApi"})
    public ViewPropertyObjectAnimator translationZ(float translationZ) {
        if (VERSION.SDK_INT >= 21) {
            animateProperty(View.TRANSLATION_Z, translationZ);
        }
        return this;
    }

    @SuppressLint({"NewApi"})
    public ViewPropertyObjectAnimator translationZBy(float translationZBy) {
        if (VERSION.SDK_INT >= 21) {
            animatePropertyBy(View.TRANSLATION_Z, translationZBy);
        }
        return this;
    }

    public ViewPropertyObjectAnimator alpha(float alpha) {
        animateProperty(View.ALPHA, alpha);
        return this;
    }

    public ViewPropertyObjectAnimator alphaBy(float alphaBy) {
        animatePropertyBy(View.ALPHA, alphaBy);
        return this;
    }

    public ViewPropertyObjectAnimator rotation(float rotation) {
        animateProperty(View.ROTATION, rotation);
        return this;
    }

    public ViewPropertyObjectAnimator rotationBy(float rotationBy) {
        animatePropertyBy(View.ROTATION, rotationBy);
        return this;
    }

    public ViewPropertyObjectAnimator rotationX(float rotationX) {
        animateProperty(View.ROTATION_X, rotationX);
        return this;
    }

    public ViewPropertyObjectAnimator rotationXBy(float rotationXBy) {
        animatePropertyBy(View.ROTATION_X, rotationXBy);
        return this;
    }

    public ViewPropertyObjectAnimator rotationY(float rotationY) {
        animateProperty(View.ROTATION_Y, rotationY);
        return this;
    }

    public ViewPropertyObjectAnimator rotationYBy(float rotationYBy) {
        animatePropertyBy(View.ROTATION_Y, rotationYBy);
        return this;
    }

    public ViewPropertyObjectAnimator m3x(float x) {
        animateProperty(View.X, x);
        return this;
    }

    public ViewPropertyObjectAnimator xBy(float xBy) {
        animatePropertyBy(View.X, xBy);
        return this;
    }

    public ViewPropertyObjectAnimator m4y(float y) {
        animateProperty(View.Y, y);
        return this;
    }

    public ViewPropertyObjectAnimator yBy(float yBy) {
        animatePropertyBy(View.Y, yBy);
        return this;
    }

    @SuppressLint({"NewApi"})
    public ViewPropertyObjectAnimator m5z(float z) {
        if (VERSION.SDK_INT >= 21) {
            animateProperty(View.Z, z);
        }
        return this;
    }

    @SuppressLint({"NewApi"})
    public ViewPropertyObjectAnimator zBy(float zBy) {
        if (VERSION.SDK_INT >= 21) {
            animatePropertyBy(View.Z, zBy);
        }
        return this;
    }

    public ViewPropertyObjectAnimator leftMargin(int leftMargin) {
        if (initMarginListener()) {
            this.mMarginListener.leftMargin(leftMargin);
        }
        return this;
    }

    public ViewPropertyObjectAnimator leftMarginBy(int leftMarginBy) {
        if (initMarginListener()) {
            this.mMarginListener.leftMarginBy(leftMarginBy);
        }
        return this;
    }

    public ViewPropertyObjectAnimator topMargin(int topMargin) {
        if (initMarginListener()) {
            this.mMarginListener.topMargin(topMargin);
        }
        return this;
    }

    public ViewPropertyObjectAnimator topMarginBy(int topMarginBy) {
        if (initMarginListener()) {
            this.mMarginListener.topMarginBy(topMarginBy);
        }
        return this;
    }

    public ViewPropertyObjectAnimator rightMargin(int rightMargin) {
        if (initMarginListener()) {
            this.mMarginListener.rightMargin(rightMargin);
        }
        return this;
    }

    public ViewPropertyObjectAnimator rightMarginBy(int rightMarginBy) {
        if (initMarginListener()) {
            this.mMarginListener.rightMarginBy(rightMarginBy);
        }
        return this;
    }

    public ViewPropertyObjectAnimator bottomMargin(int bottomMargin) {
        if (initMarginListener()) {
            this.mMarginListener.bottomMargin(bottomMargin);
        }
        return this;
    }

    public ViewPropertyObjectAnimator bottomMarginBy(int bottomMarginBy) {
        if (initMarginListener()) {
            this.mMarginListener.bottomMarginBy(bottomMarginBy);
        }
        return this;
    }

    public ViewPropertyObjectAnimator horizontalMargin(int horizontalMargin) {
        if (initMarginListener()) {
            this.mMarginListener.horizontalMargin(horizontalMargin);
        }
        return this;
    }

    public ViewPropertyObjectAnimator horizontalMarginBy(int horizontalMarginBy) {
        if (initMarginListener()) {
            this.mMarginListener.horizontalMarginBy(horizontalMarginBy);
        }
        return this;
    }

    public ViewPropertyObjectAnimator verticalMargin(int verticalMargin) {
        if (initMarginListener()) {
            this.mMarginListener.verticalMargin(verticalMargin);
        }
        return this;
    }

    public ViewPropertyObjectAnimator verticalMarginBy(int verticalMarginBy) {
        if (initMarginListener()) {
            this.mMarginListener.verticalMarginBy(verticalMarginBy);
        }
        return this;
    }

    public ViewPropertyObjectAnimator margin(int margin) {
        if (initMarginListener()) {
            this.mMarginListener.margin(margin);
        }
        return this;
    }

    public ViewPropertyObjectAnimator marginBy(int marginBy) {
        if (initMarginListener()) {
            this.mMarginListener.marginBy(marginBy);
        }
        return this;
    }

    public ViewPropertyObjectAnimator width(int width) {
        if (initDimensionListener()) {
            this.mDimensionListener.width(width);
        }
        return this;
    }

    public ViewPropertyObjectAnimator widthBy(int widthBy) {
        if (initDimensionListener()) {
            this.mDimensionListener.widthBy(widthBy);
        }
        return this;
    }

    public ViewPropertyObjectAnimator height(int height) {
        if (initDimensionListener()) {
            this.mDimensionListener.height(height);
        }
        return this;
    }

    public ViewPropertyObjectAnimator heightBy(int heightBy) {
        if (initDimensionListener()) {
            this.mDimensionListener.heightBy(heightBy);
        }
        return this;
    }

    public ViewPropertyObjectAnimator size(int size) {
        if (initDimensionListener()) {
            this.mDimensionListener.size(size);
        }
        return this;
    }

    public ViewPropertyObjectAnimator sizeBy(int sizeBy) {
        if (initDimensionListener()) {
            this.mDimensionListener.sizeBy(sizeBy);
        }
        return this;
    }

    public ViewPropertyObjectAnimator leftPadding(int leftPadding) {
        if (initPaddingListener()) {
            this.mPaddingListener.leftPadding(leftPadding);
        }
        return this;
    }

    public ViewPropertyObjectAnimator leftPaddingBy(int leftPaddingBy) {
        if (initPaddingListener()) {
            this.mPaddingListener.leftPaddingBy(leftPaddingBy);
        }
        return this;
    }

    public ViewPropertyObjectAnimator topPadding(int topPadding) {
        if (initPaddingListener()) {
            this.mPaddingListener.topPadding(topPadding);
        }
        return this;
    }

    public ViewPropertyObjectAnimator topPaddingBy(int topPaddingBy) {
        if (initPaddingListener()) {
            this.mPaddingListener.topPaddingBy(topPaddingBy);
        }
        return this;
    }

    public ViewPropertyObjectAnimator rightPadding(int rightPadding) {
        if (initPaddingListener()) {
            this.mPaddingListener.rightPadding(rightPadding);
        }
        return this;
    }

    public ViewPropertyObjectAnimator rightPaddingBy(int rightPaddingBy) {
        if (initPaddingListener()) {
            this.mPaddingListener.rightPaddingBy(rightPaddingBy);
        }
        return this;
    }

    public ViewPropertyObjectAnimator bottomPadding(int bottomPadding) {
        if (initPaddingListener()) {
            this.mPaddingListener.bottomPadding(bottomPadding);
        }
        return this;
    }

    public ViewPropertyObjectAnimator bottomPaddingBy(int bottomPaddingBy) {
        if (initPaddingListener()) {
            this.mPaddingListener.bottomPaddingBy(bottomPaddingBy);
        }
        return this;
    }

    public ViewPropertyObjectAnimator horizontalPadding(int horizontalPadding) {
        if (initPaddingListener()) {
            this.mPaddingListener.horizontalPadding(horizontalPadding);
        }
        return this;
    }

    public ViewPropertyObjectAnimator horizontalPaddingBy(int horizontalPaddingBy) {
        if (initPaddingListener()) {
            this.mPaddingListener.horizontalPaddingBy(horizontalPaddingBy);
        }
        return this;
    }

    public ViewPropertyObjectAnimator verticalPadding(int verticalPadding) {
        if (initPaddingListener()) {
            this.mPaddingListener.verticalPadding(verticalPadding);
        }
        return this;
    }

    public ViewPropertyObjectAnimator verticalPaddingBy(int verticalPaddingBy) {
        if (initPaddingListener()) {
            this.mPaddingListener.verticalPaddingBy(verticalPaddingBy);
        }
        return this;
    }

    public ViewPropertyObjectAnimator padding(int padding) {
        if (initPaddingListener()) {
            this.mPaddingListener.padding(padding);
        }
        return this;
    }

    public ViewPropertyObjectAnimator paddingBy(int paddingBy) {
        if (initPaddingListener()) {
            this.mPaddingListener.paddingBy(paddingBy);
        }
        return this;
    }

    public ViewPropertyObjectAnimator scrollX(int scrollX) {
        if (initScrollListener()) {
            this.mScrollListener.scrollX(scrollX);
        }
        return this;
    }

    public ViewPropertyObjectAnimator scrollXBy(int scrollXBy) {
        if (initScrollListener()) {
            this.mScrollListener.scrollXBy(scrollXBy);
        }
        return this;
    }

    public ViewPropertyObjectAnimator scrollY(int scrollY) {
        if (initScrollListener()) {
            this.mScrollListener.scrollY(scrollY);
        }
        return this;
    }

    public ViewPropertyObjectAnimator scrollYBy(int scrollYBy) {
        if (initScrollListener()) {
            this.mScrollListener.scrollYBy(scrollYBy);
        }
        return this;
    }

    private boolean initMarginListener() {
        if (this.mMarginListener == null) {
            if (!hasView()) {
                return false;
            }
            this.mMarginListener = new MarginChangeListener((View) this.mView.get());
        }
        return true;
    }

    private boolean initDimensionListener() {
        if (this.mDimensionListener == null) {
            if (!hasView()) {
                return false;
            }
            this.mDimensionListener = new DimensionChangeListener((View) this.mView.get());
        }
        return true;
    }

    private boolean initPaddingListener() {
        if (this.mPaddingListener == null) {
            if (!hasView()) {
                return false;
            }
            this.mPaddingListener = new PaddingChangeListener((View) this.mView.get());
        }
        return true;
    }

    private boolean initScrollListener() {
        if (this.mScrollListener == null) {
            if (!hasView()) {
                return false;
            }
            this.mScrollListener = new ScrollChangeListener((View) this.mView.get());
        }
        return true;
    }

    public ViewPropertyObjectAnimator withLayer() {
        this.mWithLayer = true;
        return this;
    }

    public ViewPropertyObjectAnimator setStartDelay(long startDelay) {
        if (startDelay < 0) {
            throw new IllegalArgumentException("startDelay cannot be < 0");
        }
        this.mStartDelay = startDelay;
        return this;
    }

    public ViewPropertyObjectAnimator setDuration(long duration) {
        if (duration < 0) {
            throw new IllegalArgumentException("duration cannot be < 0");
        }
        this.mDuration = duration;
        return this;
    }

    public ViewPropertyObjectAnimator setInterpolator(Interpolator interpolator) {
        this.mInterpolator = interpolator;
        return this;
    }

    public ViewPropertyObjectAnimator addListener(AnimatorListener listener) {
        this.mListeners.add(listener);
        return this;
    }

    public ViewPropertyObjectAnimator removeListener(AnimatorListener listener) {
        this.mListeners.remove(listener);
        return this;
    }

    public ViewPropertyObjectAnimator removeAllListeners() {
        this.mListeners.clear();
        return this;
    }

    public ViewPropertyObjectAnimator addUpdateListener(AnimatorUpdateListener listener) {
        this.mUpdateListeners.add(listener);
        return this;
    }

    public ViewPropertyObjectAnimator removeUpdateListener(AnimatorUpdateListener listener) {
        this.mUpdateListeners.remove(listener);
        return this;
    }

    public ViewPropertyObjectAnimator removeAllUpdateListeners() {
        this.mUpdateListeners.clear();
        return this;
    }

    public ViewPropertyObjectAnimator addPauseListener(AnimatorPauseListener listener) {
        if (VERSION.SDK_INT >= 19) {
            this.mPauseListeners.add(listener);
        }
        return this;
    }

    public ViewPropertyObjectAnimator removePauseListener(AnimatorPauseListener listener) {
        if (VERSION.SDK_INT >= 19) {
            this.mPauseListeners.remove(listener);
        }
        return this;
    }

    public ViewPropertyObjectAnimator removeAllPauseListeners() {
        if (VERSION.SDK_INT >= 19) {
            this.mPauseListeners.clear();
        }
        return this;
    }

    public ViewPropertyObjectAnimator withStartAction(final Runnable runnable) {
        return addListener(new AnimatorListenerAdapter() {
            public void onAnimationStart(Animator animation) {
                runnable.run();
                ViewPropertyObjectAnimator.this.removeListener(this);
            }
        });
    }

    public ViewPropertyObjectAnimator withEndAction(final Runnable runnable) {
        return addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animation) {
                runnable.run();
                ViewPropertyObjectAnimator.this.removeListener(this);
            }
        });
    }

    private boolean hasView() {
        return this.mView.get() != null;
    }

    @SuppressLint({"NewApi"})
    public ObjectAnimator get() {
        if (!hasView()) {
            return ObjectAnimator.ofFloat(null, View.ALPHA, new float[]{FlexItem.FLEX_SHRINK_DEFAULT, FlexItem.FLEX_SHRINK_DEFAULT});
        }
        Collection<PropertyValuesHolder> holders = this.mPropertyHoldersMap.values();
        ObjectAnimator ofPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(this.mView.get(), (PropertyValuesHolder[]) holders.toArray(new PropertyValuesHolder[holders.size()]));
        if (this.mWithLayer) {
            ofPropertyValuesHolder.addListener(new C06323());
        }
        if (this.mStartDelay != -1) {
            ofPropertyValuesHolder.setStartDelay(this.mStartDelay);
        }
        if (this.mDuration != -1) {
            ofPropertyValuesHolder.setDuration(this.mDuration);
        }
        if (this.mInterpolator != null) {
            ofPropertyValuesHolder.setInterpolator(this.mInterpolator);
        }
        for (AnimatorListener listener : this.mListeners) {
            ofPropertyValuesHolder.addListener(listener);
        }
        if (this.mMarginListener != null) {
            ofPropertyValuesHolder.addUpdateListener(this.mMarginListener);
        }
        if (this.mDimensionListener != null) {
            ofPropertyValuesHolder.addUpdateListener(this.mDimensionListener);
        }
        if (this.mPaddingListener != null) {
            ofPropertyValuesHolder.addUpdateListener(this.mPaddingListener);
        }
        if (this.mScrollListener != null) {
            ofPropertyValuesHolder.addUpdateListener(this.mScrollListener);
        }
        for (AnimatorUpdateListener listener2 : this.mUpdateListeners) {
            ofPropertyValuesHolder.addUpdateListener(listener2);
        }
        if (VERSION.SDK_INT < 19) {
            return ofPropertyValuesHolder;
        }
        for (AnimatorPauseListener listener3 : this.mPauseListeners) {
            ofPropertyValuesHolder.addPauseListener(listener3);
        }
        return ofPropertyValuesHolder;
    }

    public void start() {
        get().start();
    }
}
