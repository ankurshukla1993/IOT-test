package com.appeaser.sublimepickerlibrary.datepicker;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.LayoutParams;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewConfiguration;
import com.appeaser.sublimepickerlibrary.C0563R;
import com.appeaser.sublimepickerlibrary.utilities.SUtils;
import com.google.common.primitives.Ints;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

class DayPickerViewPager extends ViewPager {
    private static final int NOT_SCROLLING = 0;
    private static final int SCROLLING_LEFT = -1;
    private static final int SCROLLING_RIGHT = 1;
    private static final String TAG = DayPickerViewPager.class.getSimpleName();
    private final int MONTH_SCROLL_THRESHOLD;
    private final int TOUCH_SLOP_SQUARED;
    private boolean mAlreadyTriedAccessingMethod;
    private boolean mCanPickRange;
    private CheckForLongPress mCheckForLongPress;
    private DayPickerPagerAdapter mDayPickerPagerAdapter;
    private float mInitialDownX;
    private float mInitialDownY;
    private boolean mIsLongPressed;
    private final ArrayList<View> mMatchParentChildren;
    private Method mPopulateMethod;
    private ScrollerRunnable mScrollerRunnable;
    private int mScrollingDirection;
    private SelectedDate mTempSelectedDate;

    private class CheckForLongPress implements Runnable {
        private CheckForLongPress() {
        }

        public void run() {
            if (DayPickerViewPager.this.mDayPickerPagerAdapter != null) {
                DayPickerViewPager.this.mTempSelectedDate = DayPickerViewPager.this.mDayPickerPagerAdapter.resolveStartDateForRange((int) DayPickerViewPager.this.mInitialDownX, (int) DayPickerViewPager.this.mInitialDownY, DayPickerViewPager.this.getCurrentItem());
                if (DayPickerViewPager.this.mTempSelectedDate != null) {
                    DayPickerViewPager.this.mIsLongPressed = true;
                    DayPickerViewPager.this.mDayPickerPagerAdapter.onDateRangeSelectionStarted(DayPickerViewPager.this.mTempSelectedDate);
                }
            }
        }
    }

    private class ScrollerRunnable implements Runnable {
        private ScrollerRunnable() {
        }

        public void run() {
            if (DayPickerViewPager.this.mScrollingDirection != 0) {
                DayPickerViewPager.this.setCurrentItem(DayPickerViewPager.this.getCurrentItem() + DayPickerViewPager.this.mScrollingDirection, true);
                DayPickerViewPager.this.postDelayed(this, 1000);
            }
        }
    }

    public DayPickerViewPager(Context context) {
        this(context, null);
    }

    public DayPickerViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mMatchParentChildren = new ArrayList(1);
        this.mIsLongPressed = false;
        this.mScrollingDirection = 0;
        this.TOUCH_SLOP_SQUARED = ViewConfiguration.get(context).getScaledTouchSlop() * ViewConfiguration.get(context).getScaledTouchSlop();
        this.MONTH_SCROLL_THRESHOLD = context.getResources().getDimensionPixelSize(C0563R.dimen.sp_month_scroll_threshold);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int i;
        callPopulate();
        int count = getChildCount();
        boolean measureMatchParentChildren = (MeasureSpec.getMode(widthMeasureSpec) == Ints.MAX_POWER_OF_TWO && MeasureSpec.getMode(heightMeasureSpec) == Ints.MAX_POWER_OF_TWO) ? false : true;
        int maxHeight = 0;
        int maxWidth = 0;
        int childState = 0;
        for (i = 0; i < count; i++) {
            View child = getChildAt(i);
            if (child.getVisibility() != 8) {
                measureChild(child, widthMeasureSpec, heightMeasureSpec);
                LayoutParams lp = (LayoutParams) child.getLayoutParams();
                maxWidth = Math.max(maxWidth, child.getMeasuredWidth());
                maxHeight = Math.max(maxHeight, child.getMeasuredHeight());
                childState = combineMeasuredStates(childState, child.getMeasuredState());
                if (measureMatchParentChildren && (lp.width == -1 || lp.height == -1)) {
                    this.mMatchParentChildren.add(child);
                }
            }
        }
        maxWidth += getPaddingLeft() + getPaddingRight();
        maxHeight = Math.max(maxHeight + (getPaddingTop() + getPaddingBottom()), getSuggestedMinimumHeight());
        maxWidth = Math.max(maxWidth, getSuggestedMinimumWidth());
        if (SUtils.isApi_23_OrHigher()) {
            Drawable drawable = getForeground();
            if (drawable != null) {
                maxHeight = Math.max(maxHeight, drawable.getMinimumHeight());
                maxWidth = Math.max(maxWidth, drawable.getMinimumWidth());
            }
        }
        setMeasuredDimension(resolveSizeAndState(maxWidth, widthMeasureSpec, childState), resolveSizeAndState(maxHeight, heightMeasureSpec, childState << 16));
        count = this.mMatchParentChildren.size();
        if (count > 1) {
            for (i = 0; i < count; i++) {
                int childWidthMeasureSpec;
                int childHeightMeasureSpec;
                child = (View) this.mMatchParentChildren.get(i);
                lp = (LayoutParams) child.getLayoutParams();
                if (lp.width == -1) {
                    childWidthMeasureSpec = MeasureSpec.makeMeasureSpec((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight(), Ints.MAX_POWER_OF_TWO);
                } else {
                    childWidthMeasureSpec = getChildMeasureSpec(widthMeasureSpec, getPaddingLeft() + getPaddingRight(), lp.width);
                }
                if (lp.height == -1) {
                    childHeightMeasureSpec = MeasureSpec.makeMeasureSpec((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom(), Ints.MAX_POWER_OF_TWO);
                } else {
                    childHeightMeasureSpec = getChildMeasureSpec(heightMeasureSpec, getPaddingTop() + getPaddingBottom(), lp.height);
                }
                child.measure(childWidthMeasureSpec, childHeightMeasureSpec);
            }
        }
        this.mMatchParentChildren.clear();
    }

    private void initializePopulateMethod() {
        try {
            this.mPopulateMethod = ViewPager.class.getDeclaredMethod("populate", (Class[]) null);
            this.mPopulateMethod.setAccessible(true);
        } catch (NoSuchMethodException nsme) {
            nsme.printStackTrace();
        }
        this.mAlreadyTriedAccessingMethod = true;
    }

    private void callPopulate() {
        if (!this.mAlreadyTriedAccessingMethod) {
            initializePopulateMethod();
        }
        if (this.mPopulateMethod != null) {
            try {
                this.mPopulateMethod.invoke(this, new Object[0]);
                return;
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                return;
            } catch (InvocationTargetException e2) {
                e2.printStackTrace();
                return;
            }
        }
        Log.e(TAG, "Could not call `ViewPager.populate()`");
    }

    protected void setCanPickRange(boolean canPickRange) {
        this.mCanPickRange = canPickRange;
    }

    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (!this.mCanPickRange) {
            return super.onInterceptTouchEvent(ev);
        }
        if (ev.getAction() == 0) {
            this.mInitialDownX = ev.getX();
            this.mInitialDownY = ev.getY();
            if (this.mCheckForLongPress == null) {
                this.mCheckForLongPress = new CheckForLongPress();
            }
            postDelayed(this.mCheckForLongPress, (long) ViewConfiguration.getLongPressTimeout());
        } else if (ev.getAction() == 1 || ev.getAction() == 3) {
            if (this.mCheckForLongPress != null) {
                removeCallbacks(this.mCheckForLongPress);
            }
            this.mIsLongPressed = false;
            this.mInitialDownX = FlexItem.FLEX_BASIS_PERCENT_DEFAULT;
            this.mInitialDownY = FlexItem.FLEX_BASIS_PERCENT_DEFAULT;
        } else if (!(ev.getAction() != 2 || isStillALongPress((int) ev.getX(), (int) ev.getY()) || this.mCheckForLongPress == null)) {
            removeCallbacks(this.mCheckForLongPress);
        }
        if (this.mIsLongPressed || super.onInterceptTouchEvent(ev)) {
            return true;
        }
        return false;
    }

    private boolean isStillALongPress(int x, int y) {
        return ((((float) x) - this.mInitialDownX) * (((float) x) - this.mInitialDownX)) + ((((float) y) - this.mInitialDownY) * (((float) y) - this.mInitialDownY)) <= ((float) this.TOUCH_SLOP_SQUARED);
    }

    public boolean onTouchEvent(MotionEvent ev) {
        if (!this.mCanPickRange) {
            return super.onTouchEvent(ev);
        }
        if (this.mCheckForLongPress != null) {
            removeCallbacks(this.mCheckForLongPress);
        }
        if ((this.mIsLongPressed && ev.getAction() == 1) || ev.getAction() == 3) {
            if (ev.getAction() == 1 && this.mDayPickerPagerAdapter != null) {
                this.mTempSelectedDate = this.mDayPickerPagerAdapter.resolveEndDateForRange((int) ev.getX(), (int) ev.getY(), getCurrentItem(), false);
                this.mDayPickerPagerAdapter.onDateRangeSelectionEnded(this.mTempSelectedDate);
            }
            this.mIsLongPressed = false;
            this.mInitialDownX = FlexItem.FLEX_BASIS_PERCENT_DEFAULT;
            this.mInitialDownY = FlexItem.FLEX_BASIS_PERCENT_DEFAULT;
            this.mScrollingDirection = 0;
            if (this.mScrollerRunnable != null) {
                removeCallbacks(this.mScrollerRunnable);
            }
        } else if (this.mIsLongPressed && ev.getAction() == 0) {
            this.mScrollingDirection = 0;
        } else if (this.mIsLongPressed && ev.getAction() == 2) {
            boolean directionChanged;
            int direction = resolveDirectionForScroll(ev.getX());
            if (this.mScrollingDirection != direction) {
                directionChanged = true;
            } else {
                directionChanged = false;
            }
            if (directionChanged && this.mScrollerRunnable != null) {
                removeCallbacks(this.mScrollerRunnable);
            }
            if (this.mScrollerRunnable == null) {
                this.mScrollerRunnable = new ScrollerRunnable();
            }
            this.mScrollingDirection = direction;
            if (this.mScrollingDirection == 0) {
                if (this.mDayPickerPagerAdapter != null) {
                    this.mTempSelectedDate = this.mDayPickerPagerAdapter.resolveEndDateForRange((int) ev.getX(), (int) ev.getY(), getCurrentItem(), true);
                    if (this.mTempSelectedDate != null) {
                        this.mDayPickerPagerAdapter.onDateRangeSelectionUpdated(this.mTempSelectedDate);
                    }
                }
            } else if (directionChanged) {
                post(this.mScrollerRunnable);
            }
        }
        if (this.mIsLongPressed || super.onTouchEvent(ev)) {
            return true;
        }
        return false;
    }

    private int resolveDirectionForScroll(float x) {
        if (x - ((float) getLeft()) < ((float) this.MONTH_SCROLL_THRESHOLD)) {
            return -1;
        }
        if (((float) getRight()) - x < ((float) this.MONTH_SCROLL_THRESHOLD)) {
            return 1;
        }
        return 0;
    }

    public void setAdapter(PagerAdapter adapter) {
        super.setAdapter(adapter);
        if (adapter instanceof DayPickerPagerAdapter) {
            this.mDayPickerPagerAdapter = (DayPickerPagerAdapter) adapter;
        }
    }

    public void onRestoreInstanceState(Parcelable state) {
        super.onRestoreInstanceState(state);
    }
}
