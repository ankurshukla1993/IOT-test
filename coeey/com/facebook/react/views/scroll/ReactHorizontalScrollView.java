package com.facebook.react.views.scroll;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.HorizontalScrollView;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.uimanager.MeasureSpecAssertions;
import com.facebook.react.uimanager.ReactClippingViewGroup;
import com.facebook.react.uimanager.ReactClippingViewGroupHelper;
import com.facebook.react.uimanager.events.NativeGestureUtil;
import javax.annotation.Nullable;

public class ReactHorizontalScrollView extends HorizontalScrollView implements ReactClippingViewGroup {
    private boolean mActivelyScrolling;
    @Nullable
    private Rect mClippingRect;
    private boolean mDragging;
    @Nullable
    private Drawable mEndBackground;
    private int mEndFillColor;
    @Nullable
    private FpsListener mFpsListener;
    private final OnScrollDispatchHelper mOnScrollDispatchHelper;
    private boolean mPagingEnabled;
    @Nullable
    private Runnable mPostTouchRunnable;
    private boolean mRemoveClippedSubviews;
    private boolean mScrollEnabled;
    @Nullable
    private String mScrollPerfTag;
    private boolean mSendMomentumEvents;

    public ReactHorizontalScrollView(Context context) {
        this(context, null);
    }

    public ReactHorizontalScrollView(Context context, @Nullable FpsListener fpsListener) {
        super(context);
        this.mOnScrollDispatchHelper = new OnScrollDispatchHelper();
        this.mPagingEnabled = false;
        this.mScrollEnabled = true;
        this.mFpsListener = null;
        this.mEndFillColor = 0;
        this.mFpsListener = fpsListener;
    }

    public void setScrollPerfTag(@Nullable String scrollPerfTag) {
        this.mScrollPerfTag = scrollPerfTag;
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

    public void setSendMomentumEvents(boolean sendMomentumEvents) {
        this.mSendMomentumEvents = sendMomentumEvents;
    }

    public void setScrollEnabled(boolean scrollEnabled) {
        this.mScrollEnabled = scrollEnabled;
    }

    public void setPagingEnabled(boolean pagingEnabled) {
        this.mPagingEnabled = pagingEnabled;
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        MeasureSpecAssertions.assertExplicitMeasureSpec(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), MeasureSpec.getSize(heightMeasureSpec));
    }

    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        scrollTo(getScrollX(), getScrollY());
    }

    protected void onScrollChanged(int x, int y, int oldX, int oldY) {
        super.onScrollChanged(x, y, oldX, oldY);
        if (this.mOnScrollDispatchHelper.onScrollChanged(x, y)) {
            if (this.mRemoveClippedSubviews) {
                updateClippingRect();
            }
            this.mActivelyScrolling = true;
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
            handlePostTouchScrolling();
        }
        return super.onTouchEvent(ev);
    }

    public void fling(int velocityX) {
        if (this.mPagingEnabled) {
            smoothScrollToPage(velocityX);
        } else {
            super.fling(velocityX);
        }
        handlePostTouchScrolling();
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

    public void setEndFillColor(int color) {
        if (color != this.mEndFillColor) {
            this.mEndFillColor = color;
            this.mEndBackground = new ColorDrawable(this.mEndFillColor);
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

    public void draw(Canvas canvas) {
        if (this.mEndFillColor != 0) {
            View content = getChildAt(0);
            if (!(this.mEndBackground == null || content == null || content.getRight() >= getWidth())) {
                this.mEndBackground.setBounds(content.getRight(), 0, getWidth(), getHeight());
                this.mEndBackground.draw(canvas);
            }
        }
        super.draw(canvas);
    }

    @TargetApi(16)
    private void handlePostTouchScrolling() {
        if ((this.mSendMomentumEvents || this.mPagingEnabled || isScrollPerfLoggingEnabled()) && this.mPostTouchRunnable == null) {
            if (this.mSendMomentumEvents) {
                ReactScrollViewHelper.emitScrollMomentumBeginEvent(this);
            }
            this.mActivelyScrolling = false;
            this.mPostTouchRunnable = new 1(this);
            postOnAnimationDelayed(this.mPostTouchRunnable, 20);
        }
    }

    private void smoothScrollToPage(int velocity) {
        int width = getWidth();
        int currentX = getScrollX();
        int page = currentX / width;
        if (currentX + velocity > (page * width) + (width / 2)) {
            page++;
        }
        smoothScrollTo(page * width, getScrollY());
    }
}
