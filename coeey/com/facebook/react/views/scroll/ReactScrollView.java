package com.facebook.react.views.scroll;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnLayoutChangeListener;
import android.view.ViewGroup.OnHierarchyChangeListener;
import android.widget.OverScroller;
import android.widget.ScrollView;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.uimanager.MeasureSpecAssertions;
import com.facebook.react.uimanager.ReactClippingViewGroup;
import com.facebook.react.uimanager.ReactClippingViewGroupHelper;
import com.facebook.react.uimanager.events.NativeGestureUtil;
import java.lang.reflect.Field;
import javax.annotation.Nullable;

public class ReactScrollView extends ScrollView implements ReactClippingViewGroup, OnHierarchyChangeListener, OnLayoutChangeListener {
    private static Field sScrollerField;
    private static boolean sTriedToGetScrollerField = false;
    @Nullable
    private Rect mClippingRect;
    private View mContentView;
    private boolean mDoneFlinging;
    private boolean mDragging;
    @Nullable
    private Drawable mEndBackground;
    private int mEndFillColor;
    private boolean mFlinging;
    @Nullable
    private FpsListener mFpsListener;
    private final OnScrollDispatchHelper mOnScrollDispatchHelper;
    private boolean mRemoveClippedSubviews;
    private boolean mScrollEnabled;
    @Nullable
    private String mScrollPerfTag;
    private final OverScroller mScroller;
    private boolean mSendMomentumEvents;

    public ReactScrollView(ReactContext context) {
        this(context, null);
    }

    public ReactScrollView(ReactContext context, @Nullable FpsListener fpsListener) {
        super(context);
        this.mOnScrollDispatchHelper = new OnScrollDispatchHelper();
        this.mScrollEnabled = true;
        this.mFpsListener = null;
        this.mEndFillColor = 0;
        this.mFpsListener = fpsListener;
        if (!sTriedToGetScrollerField) {
            sTriedToGetScrollerField = true;
            try {
                sScrollerField = ScrollView.class.getDeclaredField("mScroller");
                sScrollerField.setAccessible(true);
            } catch (NoSuchFieldException e) {
                Log.w(ReactConstants.TAG, "Failed to get mScroller field for ScrollView! This app will exhibit the bounce-back scrolling bug :(");
            }
        }
        if (sScrollerField != null) {
            try {
                Object scroller = sScrollerField.get(this);
                if (scroller instanceof OverScroller) {
                    this.mScroller = (OverScroller) scroller;
                } else {
                    Log.w(ReactConstants.TAG, "Failed to cast mScroller field in ScrollView (probably due to OEM changes to AOSP)! This app will exhibit the bounce-back scrolling bug :(");
                    this.mScroller = null;
                }
            } catch (IllegalAccessException e2) {
                throw new RuntimeException("Failed to get mScroller from ScrollView!", e2);
            }
        }
        this.mScroller = null;
        setOnHierarchyChangeListener(this);
        setScrollBarStyle(33554432);
    }

    public void setSendMomentumEvents(boolean sendMomentumEvents) {
        this.mSendMomentumEvents = sendMomentumEvents;
    }

    public void setScrollPerfTag(String scrollPerfTag) {
        this.mScrollPerfTag = scrollPerfTag;
    }

    public void setScrollEnabled(boolean scrollEnabled) {
        this.mScrollEnabled = scrollEnabled;
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        MeasureSpecAssertions.assertExplicitMeasureSpec(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), MeasureSpec.getSize(heightMeasureSpec));
    }

    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        scrollTo(getScrollX(), getScrollY());
    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (this.mRemoveClippedSubviews) {
            updateClippingRect();
        }
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.mRemoveClippedSubviews) {
            updateClippingRect();
        }
    }

    protected void onScrollChanged(int x, int y, int oldX, int oldY) {
        super.onScrollChanged(x, y, oldX, oldY);
        if (this.mOnScrollDispatchHelper.onScrollChanged(x, y)) {
            if (this.mRemoveClippedSubviews) {
                updateClippingRect();
            }
            if (this.mFlinging) {
                this.mDoneFlinging = false;
            }
            ReactScrollViewHelper.emitScrollEvent(this);
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (!this.mScrollEnabled || !super.onInterceptTouchEvent(ev)) {
            return false;
        }
        NativeGestureUtil.notifyNativeGestureStarted(this, ev);
        ReactScrollViewHelper.emitScrollBeginDragEvent(this);
        this.mDragging = true;
        enableFpsListener();
        return true;
    }

    public boolean onTouchEvent(MotionEvent ev) {
        if (!this.mScrollEnabled) {
            return false;
        }
        if ((ev.getAction() & 255) == 1 && this.mDragging) {
            ReactScrollViewHelper.emitScrollEndDragEvent(this);
            this.mDragging = false;
            disableFpsListener();
        }
        return super.onTouchEvent(ev);
    }

    public void setRemoveClippedSubviews(boolean removeClippedSubviews) {
        if (removeClippedSubviews && this.mClippingRect == null) {
            this.mClippingRect = new Rect();
        }
        this.mRemoveClippedSubviews = removeClippedSubviews;
        updateClippingRect();
    }

    public boolean getRemoveClippedSubviews() {
        return this.mRemoveClippedSubviews;
    }

    public void updateClippingRect() {
        if (this.mRemoveClippedSubviews) {
            Assertions.assertNotNull(this.mClippingRect);
            ReactClippingViewGroupHelper.calculateClippingRect(this, this.mClippingRect);
            View contentView = getChildAt(0);
            if (contentView instanceof ReactClippingViewGroup) {
                ((ReactClippingViewGroup) contentView).updateClippingRect();
            }
        }
    }

    public void getClippingRect(Rect outClippingRect) {
        outClippingRect.set((Rect) Assertions.assertNotNull(this.mClippingRect));
    }

    public void fling(int velocityY) {
        if (this.mScroller != null) {
            int i = velocityY;
            int i2 = 0;
            int i3 = 0;
            int i4 = 0;
            int i5 = 0;
            this.mScroller.fling(getScrollX(), getScrollY(), 0, i, i2, i3, i4, Integer.MAX_VALUE, i5, ((getHeight() - getPaddingBottom()) - getPaddingTop()) / 2);
            postInvalidateOnAnimation();
        } else {
            super.fling(velocityY);
        }
        if (this.mSendMomentumEvents || isScrollPerfLoggingEnabled()) {
            this.mFlinging = true;
            enableFpsListener();
            ReactScrollViewHelper.emitScrollMomentumBeginEvent(this);
            postOnAnimationDelayed(new 1(this), 20);
        }
    }

    private void enableFpsListener() {
        if (isScrollPerfLoggingEnabled()) {
            Assertions.assertNotNull(this.mFpsListener);
            Assertions.assertNotNull(this.mScrollPerfTag);
            this.mFpsListener.enable(this.mScrollPerfTag);
        }
    }

    private void disableFpsListener() {
        if (isScrollPerfLoggingEnabled()) {
            Assertions.assertNotNull(this.mFpsListener);
            Assertions.assertNotNull(this.mScrollPerfTag);
            this.mFpsListener.disable(this.mScrollPerfTag);
        }
    }

    private boolean isScrollPerfLoggingEnabled() {
        return (this.mFpsListener == null || this.mScrollPerfTag == null || this.mScrollPerfTag.isEmpty()) ? false : true;
    }

    private int getMaxScrollY() {
        return Math.max(0, this.mContentView.getHeight() - ((getHeight() - getPaddingBottom()) - getPaddingTop()));
    }

    public void draw(Canvas canvas) {
        if (this.mEndFillColor != 0) {
            View content = getChildAt(0);
            if (!(this.mEndBackground == null || content == null || content.getBottom() >= getHeight())) {
                this.mEndBackground.setBounds(0, content.getBottom(), getWidth(), getHeight());
                this.mEndBackground.draw(canvas);
            }
        }
        super.draw(canvas);
    }

    public void setEndFillColor(int color) {
        if (color != this.mEndFillColor) {
            this.mEndFillColor = color;
            this.mEndBackground = new ColorDrawable(this.mEndFillColor);
        }
    }

    protected void onOverScrolled(int scrollX, int scrollY, boolean clampedX, boolean clampedY) {
        if (!(this.mScroller == null || this.mScroller.isFinished() || this.mScroller.getCurrY() == this.mScroller.getFinalY())) {
            int scrollRange = getMaxScrollY();
            if (scrollY >= scrollRange) {
                this.mScroller.abortAnimation();
                scrollY = scrollRange;
            }
        }
        super.onOverScrolled(scrollX, scrollY, clampedX, clampedY);
    }

    public void onChildViewAdded(View parent, View child) {
        this.mContentView = child;
        this.mContentView.addOnLayoutChangeListener(this);
    }

    public void onChildViewRemoved(View parent, View child) {
        this.mContentView.removeOnLayoutChangeListener(this);
        this.mContentView = null;
    }

    public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
        if (this.mContentView != null) {
            int currentScrollY = getScrollY();
            int maxScrollY = getMaxScrollY();
            if (currentScrollY > maxScrollY) {
                scrollTo(getScrollX(), maxScrollY);
            }
        }
    }
}
