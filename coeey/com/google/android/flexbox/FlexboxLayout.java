package com.google.android.flexbox;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.SparseIntArray;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import com.google.common.primitives.Ints;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

public class FlexboxLayout extends ViewGroup implements FlexContainer {
    public static final int SHOW_DIVIDER_BEGINNING = 1;
    public static final int SHOW_DIVIDER_END = 4;
    public static final int SHOW_DIVIDER_MIDDLE = 2;
    public static final int SHOW_DIVIDER_NONE = 0;
    private int mAlignContent;
    private int mAlignItems;
    @Nullable
    private Drawable mDividerDrawableHorizontal;
    @Nullable
    private Drawable mDividerDrawableVertical;
    private int mDividerHorizontalHeight;
    private int mDividerVerticalWidth;
    private int mFlexDirection;
    private List<FlexLine> mFlexLines;
    private FlexLinesResult mFlexLinesResult;
    private int mFlexWrap;
    private FlexboxHelper mFlexboxHelper;
    private int mJustifyContent;
    private SparseIntArray mOrderCache;
    private int[] mReorderedIndices;
    private int mShowDividerHorizontal;
    private int mShowDividerVertical;

    @Retention(RetentionPolicy.SOURCE)
    public @interface DividerMode {
    }

    public static class LayoutParams extends MarginLayoutParams implements FlexItem {
        public static final Creator<LayoutParams> CREATOR = new 1();
        private int mAlignSelf = -1;
        private float mFlexBasisPercent = FlexItem.FLEX_BASIS_PERCENT_DEFAULT;
        private float mFlexGrow = 0.0f;
        private float mFlexShrink = FlexItem.FLEX_SHRINK_DEFAULT;
        private int mMaxHeight = 16777215;
        private int mMaxWidth = 16777215;
        private int mMinHeight;
        private int mMinWidth;
        private int mOrder = 1;
        private boolean mWrapBefore;

        public LayoutParams(Context context, AttributeSet attrs) {
            super(context, attrs);
            TypedArray a = context.obtainStyledAttributes(attrs, C0657R.styleable.FlexboxLayout_Layout);
            this.mOrder = a.getInt(C0657R.styleable.FlexboxLayout_Layout_layout_order, 1);
            this.mFlexGrow = a.getFloat(C0657R.styleable.FlexboxLayout_Layout_layout_flexGrow, 0.0f);
            this.mFlexShrink = a.getFloat(C0657R.styleable.FlexboxLayout_Layout_layout_flexShrink, FlexItem.FLEX_SHRINK_DEFAULT);
            this.mAlignSelf = a.getInt(C0657R.styleable.FlexboxLayout_Layout_layout_alignSelf, -1);
            this.mFlexBasisPercent = a.getFraction(C0657R.styleable.FlexboxLayout_Layout_layout_flexBasisPercent, 1, 1, FlexItem.FLEX_BASIS_PERCENT_DEFAULT);
            this.mMinWidth = a.getDimensionPixelSize(C0657R.styleable.FlexboxLayout_Layout_layout_minWidth, 0);
            this.mMinHeight = a.getDimensionPixelSize(C0657R.styleable.FlexboxLayout_Layout_layout_minHeight, 0);
            this.mMaxWidth = a.getDimensionPixelSize(C0657R.styleable.FlexboxLayout_Layout_layout_maxWidth, 16777215);
            this.mMaxHeight = a.getDimensionPixelSize(C0657R.styleable.FlexboxLayout_Layout_layout_maxHeight, 16777215);
            this.mWrapBefore = a.getBoolean(C0657R.styleable.FlexboxLayout_Layout_layout_wrapBefore, false);
            a.recycle();
        }

        public LayoutParams(LayoutParams source) {
            super(source);
            this.mOrder = source.mOrder;
            this.mFlexGrow = source.mFlexGrow;
            this.mFlexShrink = source.mFlexShrink;
            this.mAlignSelf = source.mAlignSelf;
            this.mFlexBasisPercent = source.mFlexBasisPercent;
            this.mMinWidth = source.mMinWidth;
            this.mMinHeight = source.mMinHeight;
            this.mMaxWidth = source.mMaxWidth;
            this.mMaxHeight = source.mMaxHeight;
            this.mWrapBefore = source.mWrapBefore;
        }

        public LayoutParams(android.view.ViewGroup.LayoutParams source) {
            super(source);
        }

        public LayoutParams(int width, int height) {
            super(new android.view.ViewGroup.LayoutParams(width, height));
        }

        public LayoutParams(MarginLayoutParams source) {
            super(source);
        }

        public int getWidth() {
            return this.width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getHeight() {
            return this.height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public int getOrder() {
            return this.mOrder;
        }

        public void setOrder(int order) {
            this.mOrder = order;
        }

        public float getFlexGrow() {
            return this.mFlexGrow;
        }

        public void setFlexGrow(float flexGrow) {
            this.mFlexGrow = flexGrow;
        }

        public float getFlexShrink() {
            return this.mFlexShrink;
        }

        public void setFlexShrink(float flexShrink) {
            this.mFlexShrink = flexShrink;
        }

        public int getAlignSelf() {
            return this.mAlignSelf;
        }

        public void setAlignSelf(int alignSelf) {
            this.mAlignSelf = alignSelf;
        }

        public int getMinWidth() {
            return this.mMinWidth;
        }

        public void setMinWidth(int minWidth) {
            this.mMinWidth = minWidth;
        }

        public int getMinHeight() {
            return this.mMinHeight;
        }

        public void setMinHeight(int minHeight) {
            this.mMinHeight = minHeight;
        }

        public int getMaxWidth() {
            return this.mMaxWidth;
        }

        public void setMaxWidth(int maxWidth) {
            this.mMaxWidth = maxWidth;
        }

        public int getMaxHeight() {
            return this.mMaxHeight;
        }

        public void setMaxHeight(int maxHeight) {
            this.mMaxHeight = maxHeight;
        }

        public boolean isWrapBefore() {
            return this.mWrapBefore;
        }

        public void setWrapBefore(boolean wrapBefore) {
            this.mWrapBefore = wrapBefore;
        }

        public float getFlexBasisPercent() {
            return this.mFlexBasisPercent;
        }

        public void setFlexBasisPercent(float flexBasisPercent) {
            this.mFlexBasisPercent = flexBasisPercent;
        }

        public int getMarginLeft() {
            return this.leftMargin;
        }

        public int getMarginTop() {
            return this.topMargin;
        }

        public int getMarginRight() {
            return this.rightMargin;
        }

        public int getMarginBottom() {
            return this.bottomMargin;
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.mOrder);
            dest.writeFloat(this.mFlexGrow);
            dest.writeFloat(this.mFlexShrink);
            dest.writeInt(this.mAlignSelf);
            dest.writeFloat(this.mFlexBasisPercent);
            dest.writeInt(this.mMinWidth);
            dest.writeInt(this.mMinHeight);
            dest.writeInt(this.mMaxWidth);
            dest.writeInt(this.mMaxHeight);
            dest.writeByte(this.mWrapBefore ? (byte) 1 : (byte) 0);
            dest.writeInt(this.bottomMargin);
            dest.writeInt(this.leftMargin);
            dest.writeInt(this.rightMargin);
            dest.writeInt(this.topMargin);
            dest.writeInt(this.height);
            dest.writeInt(this.width);
        }

        protected LayoutParams(Parcel in) {
            boolean z = true;
            super(0, 0);
            this.mOrder = in.readInt();
            this.mFlexGrow = in.readFloat();
            this.mFlexShrink = in.readFloat();
            this.mAlignSelf = in.readInt();
            this.mFlexBasisPercent = in.readFloat();
            this.mMinWidth = in.readInt();
            this.mMinHeight = in.readInt();
            this.mMaxWidth = in.readInt();
            this.mMaxHeight = in.readInt();
            if (in.readByte() == (byte) 0) {
                z = false;
            }
            this.mWrapBefore = z;
            this.bottomMargin = in.readInt();
            this.leftMargin = in.readInt();
            this.rightMargin = in.readInt();
            this.topMargin = in.readInt();
            this.height = in.readInt();
            this.width = in.readInt();
        }
    }

    public FlexboxLayout(Context context) {
        this(context, null);
    }

    public FlexboxLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FlexboxLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mFlexboxHelper = new FlexboxHelper(this);
        this.mFlexLines = new ArrayList();
        this.mFlexLinesResult = new FlexLinesResult();
        TypedArray a = context.obtainStyledAttributes(attrs, C0657R.styleable.FlexboxLayout, defStyleAttr, 0);
        this.mFlexDirection = a.getInt(C0657R.styleable.FlexboxLayout_flexDirection, 0);
        this.mFlexWrap = a.getInt(C0657R.styleable.FlexboxLayout_flexWrap, 0);
        this.mJustifyContent = a.getInt(C0657R.styleable.FlexboxLayout_justifyContent, 0);
        this.mAlignItems = a.getInt(C0657R.styleable.FlexboxLayout_alignItems, 4);
        this.mAlignContent = a.getInt(C0657R.styleable.FlexboxLayout_alignContent, 5);
        Drawable drawable = a.getDrawable(C0657R.styleable.FlexboxLayout_dividerDrawable);
        if (drawable != null) {
            setDividerDrawableHorizontal(drawable);
            setDividerDrawableVertical(drawable);
        }
        Drawable drawableHorizontal = a.getDrawable(C0657R.styleable.FlexboxLayout_dividerDrawableHorizontal);
        if (drawableHorizontal != null) {
            setDividerDrawableHorizontal(drawableHorizontal);
        }
        Drawable drawableVertical = a.getDrawable(C0657R.styleable.FlexboxLayout_dividerDrawableVertical);
        if (drawableVertical != null) {
            setDividerDrawableVertical(drawableVertical);
        }
        int dividerMode = a.getInt(C0657R.styleable.FlexboxLayout_showDivider, 0);
        if (dividerMode != 0) {
            this.mShowDividerVertical = dividerMode;
            this.mShowDividerHorizontal = dividerMode;
        }
        int dividerModeVertical = a.getInt(C0657R.styleable.FlexboxLayout_showDividerVertical, 0);
        if (dividerModeVertical != 0) {
            this.mShowDividerVertical = dividerModeVertical;
        }
        int dividerModeHorizontal = a.getInt(C0657R.styleable.FlexboxLayout_showDividerHorizontal, 0);
        if (dividerModeHorizontal != 0) {
            this.mShowDividerHorizontal = dividerModeHorizontal;
        }
        a.recycle();
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (this.mOrderCache == null) {
            this.mOrderCache = new SparseIntArray(getChildCount());
        }
        if (this.mFlexboxHelper.isOrderChangedFromLastMeasurement(this.mOrderCache)) {
            this.mReorderedIndices = this.mFlexboxHelper.createReorderedIndices(this.mOrderCache);
        }
        switch (this.mFlexDirection) {
            case 0:
            case 1:
                measureHorizontal(widthMeasureSpec, heightMeasureSpec);
                return;
            case 2:
            case 3:
                measureVertical(widthMeasureSpec, heightMeasureSpec);
                return;
            default:
                throw new IllegalStateException("Invalid value for the flex direction is set: " + this.mFlexDirection);
        }
    }

    public int getFlexItemCount() {
        return getChildCount();
    }

    public View getFlexItemAt(int index) {
        return getChildAt(index);
    }

    public View getReorderedChildAt(int index) {
        if (index < 0 || index >= this.mReorderedIndices.length) {
            return null;
        }
        return getChildAt(this.mReorderedIndices[index]);
    }

    public View getReorderedFlexItemAt(int index) {
        return getReorderedChildAt(index);
    }

    public void addView(View child, int index, android.view.ViewGroup.LayoutParams params) {
        if (this.mOrderCache == null) {
            this.mOrderCache = new SparseIntArray(getChildCount());
        }
        this.mReorderedIndices = this.mFlexboxHelper.createReorderedIndices(child, index, params, this.mOrderCache);
        super.addView(child, index, params);
    }

    private void measureHorizontal(int widthMeasureSpec, int heightMeasureSpec) {
        this.mFlexLines.clear();
        this.mFlexLinesResult.reset();
        this.mFlexboxHelper.calculateHorizontalFlexLines(this.mFlexLinesResult, widthMeasureSpec, heightMeasureSpec);
        this.mFlexLines = this.mFlexLinesResult.mFlexLines;
        this.mFlexboxHelper.determineMainSize(widthMeasureSpec, heightMeasureSpec);
        if (this.mAlignItems == 3) {
            for (FlexLine flexLine : this.mFlexLines) {
                int largestHeightInLine = Integer.MIN_VALUE;
                for (int i = 0; i < flexLine.mItemCount; i++) {
                    View child = getReorderedChildAt(flexLine.mFirstIndex + i);
                    if (!(child == null || child.getVisibility() == 8)) {
                        LayoutParams lp = (LayoutParams) child.getLayoutParams();
                        if (this.mFlexWrap != 2) {
                            largestHeightInLine = Math.max(largestHeightInLine, (child.getMeasuredHeight() + Math.max(flexLine.mMaxBaseline - child.getBaseline(), lp.topMargin)) + lp.bottomMargin);
                        } else {
                            largestHeightInLine = Math.max(largestHeightInLine, (child.getMeasuredHeight() + lp.topMargin) + Math.max((flexLine.mMaxBaseline - child.getMeasuredHeight()) + child.getBaseline(), lp.bottomMargin));
                        }
                    }
                }
                flexLine.mCrossSize = largestHeightInLine;
            }
        }
        this.mFlexboxHelper.determineCrossSize(widthMeasureSpec, heightMeasureSpec, getPaddingTop() + getPaddingBottom());
        this.mFlexboxHelper.stretchViews();
        setMeasuredDimensionForFlex(this.mFlexDirection, widthMeasureSpec, heightMeasureSpec, this.mFlexLinesResult.mChildState);
    }

    private void measureVertical(int widthMeasureSpec, int heightMeasureSpec) {
        this.mFlexLines.clear();
        this.mFlexLinesResult.reset();
        this.mFlexboxHelper.calculateVerticalFlexLines(this.mFlexLinesResult, widthMeasureSpec, heightMeasureSpec);
        this.mFlexLines = this.mFlexLinesResult.mFlexLines;
        this.mFlexboxHelper.determineMainSize(widthMeasureSpec, heightMeasureSpec);
        this.mFlexboxHelper.determineCrossSize(widthMeasureSpec, heightMeasureSpec, getPaddingLeft() + getPaddingRight());
        this.mFlexboxHelper.stretchViews();
        setMeasuredDimensionForFlex(this.mFlexDirection, widthMeasureSpec, heightMeasureSpec, this.mFlexLinesResult.mChildState);
    }

    private void setMeasuredDimensionForFlex(int flexDirection, int widthMeasureSpec, int heightMeasureSpec, int childState) {
        int calculatedMaxHeight;
        int calculatedMaxWidth;
        int widthSizeAndState;
        int heightSizeAndState;
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        switch (flexDirection) {
            case 0:
            case 1:
                calculatedMaxHeight = (getSumOfCrossSize() + getPaddingTop()) + getPaddingBottom();
                calculatedMaxWidth = getLargestMainSize();
                break;
            case 2:
            case 3:
                calculatedMaxHeight = getLargestMainSize();
                calculatedMaxWidth = (getSumOfCrossSize() + getPaddingLeft()) + getPaddingRight();
                break;
            default:
                throw new IllegalArgumentException("Invalid flex direction: " + flexDirection);
        }
        switch (widthMode) {
            case Integer.MIN_VALUE:
                if (widthSize < calculatedMaxWidth) {
                    childState = View.combineMeasuredStates(childState, 16777216);
                } else {
                    widthSize = calculatedMaxWidth;
                }
                widthSizeAndState = View.resolveSizeAndState(widthSize, widthMeasureSpec, childState);
                break;
            case 0:
                widthSizeAndState = View.resolveSizeAndState(calculatedMaxWidth, widthMeasureSpec, childState);
                break;
            case Ints.MAX_POWER_OF_TWO /*1073741824*/:
                if (widthSize < calculatedMaxWidth) {
                    childState = View.combineMeasuredStates(childState, 16777216);
                }
                widthSizeAndState = View.resolveSizeAndState(widthSize, widthMeasureSpec, childState);
                break;
            default:
                throw new IllegalStateException("Unknown width mode is set: " + widthMode);
        }
        switch (heightMode) {
            case Integer.MIN_VALUE:
                if (heightSize < calculatedMaxHeight) {
                    childState = View.combineMeasuredStates(childState, 256);
                } else {
                    heightSize = calculatedMaxHeight;
                }
                heightSizeAndState = View.resolveSizeAndState(heightSize, heightMeasureSpec, childState);
                break;
            case 0:
                heightSizeAndState = View.resolveSizeAndState(calculatedMaxHeight, heightMeasureSpec, childState);
                break;
            case Ints.MAX_POWER_OF_TWO /*1073741824*/:
                if (heightSize < calculatedMaxHeight) {
                    childState = View.combineMeasuredStates(childState, 256);
                }
                heightSizeAndState = View.resolveSizeAndState(heightSize, heightMeasureSpec, childState);
                break;
            default:
                throw new IllegalStateException("Unknown height mode is set: " + heightMode);
        }
        setMeasuredDimension(widthSizeAndState, heightSizeAndState);
    }

    public int getLargestMainSize() {
        int largestSize = Integer.MIN_VALUE;
        for (FlexLine flexLine : this.mFlexLines) {
            largestSize = Math.max(largestSize, flexLine.mMainSize);
        }
        return largestSize;
    }

    public int getSumOfCrossSize() {
        int sum = 0;
        int size = this.mFlexLines.size();
        for (int i = 0; i < size; i++) {
            FlexLine flexLine = (FlexLine) this.mFlexLines.get(i);
            if (hasDividerBeforeFlexLine(i)) {
                if (isMainAxisDirectionHorizontal()) {
                    sum += this.mDividerHorizontalHeight;
                } else {
                    sum += this.mDividerVerticalWidth;
                }
            }
            if (hasEndDividerAfterFlexLine(i)) {
                if (isMainAxisDirectionHorizontal()) {
                    sum += this.mDividerHorizontalHeight;
                } else {
                    sum += this.mDividerVerticalWidth;
                }
            }
            sum += flexLine.mCrossSize;
        }
        return sum;
    }

    public boolean isMainAxisDirectionHorizontal() {
        return this.mFlexDirection == 0 || this.mFlexDirection == 1;
    }

    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        int layoutDirection = ViewCompat.getLayoutDirection(this);
        boolean isRtl;
        switch (this.mFlexDirection) {
            case 0:
                if (layoutDirection == 1) {
                    isRtl = true;
                } else {
                    isRtl = false;
                }
                layoutHorizontal(isRtl, left, top, right, bottom);
                return;
            case 1:
                if (layoutDirection != 1) {
                    isRtl = true;
                } else {
                    isRtl = false;
                }
                layoutHorizontal(isRtl, left, top, right, bottom);
                return;
            case 2:
                if (layoutDirection == 1) {
                    isRtl = true;
                } else {
                    isRtl = false;
                }
                if (this.mFlexWrap == 2) {
                    if (isRtl) {
                        isRtl = false;
                    } else {
                        isRtl = true;
                    }
                }
                layoutVertical(isRtl, false, left, top, right, bottom);
                return;
            case 3:
                if (layoutDirection == 1) {
                    isRtl = true;
                } else {
                    isRtl = false;
                }
                if (this.mFlexWrap == 2) {
                    if (isRtl) {
                        isRtl = false;
                    } else {
                        isRtl = true;
                    }
                }
                layoutVertical(isRtl, true, left, top, right, bottom);
                return;
            default:
                throw new IllegalStateException("Invalid flex direction is set: " + this.mFlexDirection);
        }
    }

    private void layoutHorizontal(boolean isRtl, int left, int top, int right, int bottom) {
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int width = right - left;
        int childBottom = (bottom - top) - getPaddingBottom();
        int childTop = getPaddingTop();
        int size = this.mFlexLines.size();
        for (int i = 0; i < size; i++) {
            float childLeft;
            float childRight;
            FlexLine flexLine = (FlexLine) this.mFlexLines.get(i);
            if (hasDividerBeforeFlexLine(i)) {
                childBottom -= this.mDividerHorizontalHeight;
                childTop += this.mDividerHorizontalHeight;
            }
            float spaceBetweenItem = 0.0f;
            switch (this.mJustifyContent) {
                case 0:
                    childLeft = (float) paddingLeft;
                    childRight = (float) (width - paddingRight);
                    break;
                case 1:
                    childLeft = (float) ((width - flexLine.mMainSize) + paddingRight);
                    childRight = (float) (flexLine.mMainSize - paddingLeft);
                    break;
                case 2:
                    childLeft = ((float) paddingLeft) + (((float) (width - flexLine.mMainSize)) / 2.0f);
                    childRight = ((float) (width - paddingRight)) - (((float) (width - flexLine.mMainSize)) / 2.0f);
                    break;
                case 3:
                    childLeft = (float) paddingLeft;
                    int visibleItem = flexLine.getItemCountNotGone();
                    spaceBetweenItem = ((float) (width - flexLine.mMainSize)) / (visibleItem != 1 ? (float) (visibleItem - 1) : FlexItem.FLEX_SHRINK_DEFAULT);
                    childRight = (float) (width - paddingRight);
                    break;
                case 4:
                    int visibleCount = flexLine.getItemCountNotGone();
                    if (visibleCount != 0) {
                        spaceBetweenItem = ((float) (width - flexLine.mMainSize)) / ((float) visibleCount);
                    }
                    childLeft = ((float) paddingLeft) + (spaceBetweenItem / 2.0f);
                    childRight = ((float) (width - paddingRight)) - (spaceBetweenItem / 2.0f);
                    break;
                default:
                    throw new IllegalStateException("Invalid justifyContent is set: " + this.mJustifyContent);
            }
            spaceBetweenItem = Math.max(spaceBetweenItem, 0.0f);
            for (int j = 0; j < flexLine.mItemCount; j++) {
                int index = flexLine.mFirstIndex + j;
                View child = getReorderedChildAt(index);
                if (!(child == null || child.getVisibility() == 8)) {
                    LayoutParams lp = (LayoutParams) child.getLayoutParams();
                    childLeft += (float) lp.leftMargin;
                    childRight -= (float) lp.rightMargin;
                    int beforeDividerLength = 0;
                    int endDividerLength = 0;
                    if (hasDividerBeforeChildAtAlongMainAxis(index, j)) {
                        beforeDividerLength = this.mDividerVerticalWidth;
                        childLeft += (float) beforeDividerLength;
                        childRight -= (float) beforeDividerLength;
                    }
                    if (j == flexLine.mItemCount - 1 && (this.mShowDividerVertical & 4) > 0) {
                        endDividerLength = this.mDividerVerticalWidth;
                    }
                    if (this.mFlexWrap == 2) {
                        if (isRtl) {
                            this.mFlexboxHelper.layoutSingleChildHorizontal(child, flexLine, Math.round(childRight) - child.getMeasuredWidth(), childBottom - child.getMeasuredHeight(), Math.round(childRight), childBottom);
                        } else {
                            this.mFlexboxHelper.layoutSingleChildHorizontal(child, flexLine, Math.round(childLeft), childBottom - child.getMeasuredHeight(), Math.round(childLeft) + child.getMeasuredWidth(), childBottom);
                        }
                    } else if (isRtl) {
                        this.mFlexboxHelper.layoutSingleChildHorizontal(child, flexLine, Math.round(childRight) - child.getMeasuredWidth(), childTop, Math.round(childRight), childTop + child.getMeasuredHeight());
                    } else {
                        this.mFlexboxHelper.layoutSingleChildHorizontal(child, flexLine, Math.round(childLeft), childTop, Math.round(childLeft) + child.getMeasuredWidth(), childTop + child.getMeasuredHeight());
                    }
                    childLeft += (((float) child.getMeasuredWidth()) + spaceBetweenItem) + ((float) lp.rightMargin);
                    childRight -= (((float) child.getMeasuredWidth()) + spaceBetweenItem) + ((float) lp.leftMargin);
                    if (isRtl) {
                        flexLine.updatePositionFromView(child, endDividerLength, 0, beforeDividerLength, 0);
                    } else {
                        flexLine.updatePositionFromView(child, beforeDividerLength, 0, endDividerLength, 0);
                    }
                }
            }
            childTop += flexLine.mCrossSize;
            childBottom -= flexLine.mCrossSize;
        }
    }

    private void layoutVertical(boolean isRtl, boolean fromBottomToTop, int left, int top, int right, int bottom) {
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int paddingRight = getPaddingRight();
        int childLeft = getPaddingLeft();
        int height = bottom - top;
        int childRight = (right - left) - paddingRight;
        int size = this.mFlexLines.size();
        for (int i = 0; i < size; i++) {
            float childTop;
            float childBottom;
            FlexLine flexLine = (FlexLine) this.mFlexLines.get(i);
            if (hasDividerBeforeFlexLine(i)) {
                childLeft += this.mDividerVerticalWidth;
                childRight -= this.mDividerVerticalWidth;
            }
            float spaceBetweenItem = 0.0f;
            switch (this.mJustifyContent) {
                case 0:
                    childTop = (float) paddingTop;
                    childBottom = (float) (height - paddingBottom);
                    break;
                case 1:
                    childTop = (float) ((height - flexLine.mMainSize) + paddingBottom);
                    childBottom = (float) (flexLine.mMainSize - paddingTop);
                    break;
                case 2:
                    childTop = ((float) paddingTop) + (((float) (height - flexLine.mMainSize)) / 2.0f);
                    childBottom = ((float) (height - paddingBottom)) - (((float) (height - flexLine.mMainSize)) / 2.0f);
                    break;
                case 3:
                    childTop = (float) paddingTop;
                    int visibleItem = flexLine.getItemCountNotGone();
                    spaceBetweenItem = ((float) (height - flexLine.mMainSize)) / (visibleItem != 1 ? (float) (visibleItem - 1) : FlexItem.FLEX_SHRINK_DEFAULT);
                    childBottom = (float) (height - paddingBottom);
                    break;
                case 4:
                    int visibleCount = flexLine.getItemCountNotGone();
                    if (visibleCount != 0) {
                        spaceBetweenItem = ((float) (height - flexLine.mMainSize)) / ((float) visibleCount);
                    }
                    childTop = ((float) paddingTop) + (spaceBetweenItem / 2.0f);
                    childBottom = ((float) (height - paddingBottom)) - (spaceBetweenItem / 2.0f);
                    break;
                default:
                    throw new IllegalStateException("Invalid justifyContent is set: " + this.mJustifyContent);
            }
            spaceBetweenItem = Math.max(spaceBetweenItem, 0.0f);
            for (int j = 0; j < flexLine.mItemCount; j++) {
                int index = flexLine.mFirstIndex + j;
                View child = getReorderedChildAt(index);
                if (!(child == null || child.getVisibility() == 8)) {
                    LayoutParams lp = (LayoutParams) child.getLayoutParams();
                    childTop += (float) lp.topMargin;
                    childBottom -= (float) lp.bottomMargin;
                    int beforeDividerLength = 0;
                    int endDividerLength = 0;
                    if (hasDividerBeforeChildAtAlongMainAxis(index, j)) {
                        beforeDividerLength = this.mDividerHorizontalHeight;
                        childTop += (float) beforeDividerLength;
                        childBottom -= (float) beforeDividerLength;
                    }
                    if (j == flexLine.mItemCount - 1 && (this.mShowDividerHorizontal & 4) > 0) {
                        endDividerLength = this.mDividerHorizontalHeight;
                    }
                    if (isRtl) {
                        if (fromBottomToTop) {
                            this.mFlexboxHelper.layoutSingleChildVertical(child, flexLine, true, childRight - child.getMeasuredWidth(), Math.round(childBottom) - child.getMeasuredHeight(), childRight, Math.round(childBottom));
                        } else {
                            this.mFlexboxHelper.layoutSingleChildVertical(child, flexLine, true, childRight - child.getMeasuredWidth(), Math.round(childTop), childRight, Math.round(childTop) + child.getMeasuredHeight());
                        }
                    } else if (fromBottomToTop) {
                        this.mFlexboxHelper.layoutSingleChildVertical(child, flexLine, false, childLeft, Math.round(childBottom) - child.getMeasuredHeight(), childLeft + child.getMeasuredWidth(), Math.round(childBottom));
                    } else {
                        this.mFlexboxHelper.layoutSingleChildVertical(child, flexLine, false, childLeft, Math.round(childTop), childLeft + child.getMeasuredWidth(), Math.round(childTop) + child.getMeasuredHeight());
                    }
                    childTop += (((float) child.getMeasuredHeight()) + spaceBetweenItem) + ((float) lp.bottomMargin);
                    childBottom -= (((float) child.getMeasuredHeight()) + spaceBetweenItem) + ((float) lp.topMargin);
                    if (fromBottomToTop) {
                        flexLine.updatePositionFromView(child, 0, endDividerLength, 0, beforeDividerLength);
                    } else {
                        flexLine.updatePositionFromView(child, 0, beforeDividerLength, 0, endDividerLength);
                    }
                }
            }
            childLeft += flexLine.mCrossSize;
            childRight -= flexLine.mCrossSize;
        }
    }

    protected void onDraw(Canvas canvas) {
        if (this.mDividerDrawableVertical != null || this.mDividerDrawableHorizontal != null) {
            if (this.mShowDividerHorizontal != 0 || this.mShowDividerVertical != 0) {
                int layoutDirection = ViewCompat.getLayoutDirection(this);
                boolean fromBottomToTop = false;
                boolean isRtl;
                switch (this.mFlexDirection) {
                    case 0:
                        if (layoutDirection == 1) {
                            isRtl = true;
                        } else {
                            isRtl = false;
                        }
                        if (this.mFlexWrap == 2) {
                            fromBottomToTop = true;
                        }
                        drawDividersHorizontal(canvas, isRtl, fromBottomToTop);
                        return;
                    case 1:
                        if (layoutDirection != 1) {
                            isRtl = true;
                        } else {
                            isRtl = false;
                        }
                        if (this.mFlexWrap == 2) {
                            fromBottomToTop = true;
                        }
                        drawDividersHorizontal(canvas, isRtl, fromBottomToTop);
                        return;
                    case 2:
                        if (layoutDirection == 1) {
                            isRtl = true;
                        } else {
                            isRtl = false;
                        }
                        if (this.mFlexWrap == 2) {
                            if (isRtl) {
                                isRtl = false;
                            } else {
                                isRtl = true;
                            }
                        }
                        drawDividersVertical(canvas, isRtl, false);
                        return;
                    case 3:
                        if (layoutDirection == 1) {
                            isRtl = true;
                        } else {
                            isRtl = false;
                        }
                        if (this.mFlexWrap == 2) {
                            if (isRtl) {
                                isRtl = false;
                            } else {
                                isRtl = true;
                            }
                        }
                        drawDividersVertical(canvas, isRtl, true);
                        return;
                    default:
                        return;
                }
            }
        }
    }

    private void drawDividersHorizontal(Canvas canvas, boolean isRtl, boolean fromBottomToTop) {
        int paddingLeft = getPaddingLeft();
        int horizontalDividerLength = Math.max(0, (getWidth() - getPaddingRight()) - paddingLeft);
        int size = this.mFlexLines.size();
        for (int i = 0; i < size; i++) {
            int horizontalDividerTop;
            FlexLine flexLine = (FlexLine) this.mFlexLines.get(i);
            for (int j = 0; j < flexLine.mItemCount; j++) {
                int viewIndex = flexLine.mFirstIndex + j;
                View view = getReorderedChildAt(viewIndex);
                if (!(view == null || view.getVisibility() == 8)) {
                    int dividerLeft;
                    LayoutParams lp = (LayoutParams) view.getLayoutParams();
                    if (hasDividerBeforeChildAtAlongMainAxis(viewIndex, j)) {
                        if (isRtl) {
                            dividerLeft = view.getRight() + lp.rightMargin;
                        } else {
                            dividerLeft = (view.getLeft() - lp.leftMargin) - this.mDividerVerticalWidth;
                        }
                        drawVerticalDivider(canvas, dividerLeft, flexLine.mTop, flexLine.mCrossSize);
                    }
                    if (j == flexLine.mItemCount - 1 && (this.mShowDividerVertical & 4) > 0) {
                        if (isRtl) {
                            dividerLeft = (view.getLeft() - lp.leftMargin) - this.mDividerVerticalWidth;
                        } else {
                            dividerLeft = view.getRight() + lp.rightMargin;
                        }
                        drawVerticalDivider(canvas, dividerLeft, flexLine.mTop, flexLine.mCrossSize);
                    }
                }
            }
            if (hasDividerBeforeFlexLine(i)) {
                if (fromBottomToTop) {
                    horizontalDividerTop = flexLine.mBottom;
                } else {
                    horizontalDividerTop = flexLine.mTop - this.mDividerHorizontalHeight;
                }
                drawHorizontalDivider(canvas, paddingLeft, horizontalDividerTop, horizontalDividerLength);
            }
            if (hasEndDividerAfterFlexLine(i) && (this.mShowDividerHorizontal & 4) > 0) {
                if (fromBottomToTop) {
                    horizontalDividerTop = flexLine.mTop - this.mDividerHorizontalHeight;
                } else {
                    horizontalDividerTop = flexLine.mBottom;
                }
                drawHorizontalDivider(canvas, paddingLeft, horizontalDividerTop, horizontalDividerLength);
            }
        }
    }

    private void drawDividersVertical(Canvas canvas, boolean isRtl, boolean fromBottomToTop) {
        int paddingTop = getPaddingTop();
        int verticalDividerLength = Math.max(0, (getHeight() - getPaddingBottom()) - paddingTop);
        int size = this.mFlexLines.size();
        for (int i = 0; i < size; i++) {
            int verticalDividerLeft;
            FlexLine flexLine = (FlexLine) this.mFlexLines.get(i);
            for (int j = 0; j < flexLine.mItemCount; j++) {
                int viewIndex = flexLine.mFirstIndex + j;
                View view = getReorderedChildAt(viewIndex);
                if (!(view == null || view.getVisibility() == 8)) {
                    int dividerTop;
                    LayoutParams lp = (LayoutParams) view.getLayoutParams();
                    if (hasDividerBeforeChildAtAlongMainAxis(viewIndex, j)) {
                        if (fromBottomToTop) {
                            dividerTop = view.getBottom() + lp.bottomMargin;
                        } else {
                            dividerTop = (view.getTop() - lp.topMargin) - this.mDividerHorizontalHeight;
                        }
                        drawHorizontalDivider(canvas, flexLine.mLeft, dividerTop, flexLine.mCrossSize);
                    }
                    if (j == flexLine.mItemCount - 1 && (this.mShowDividerHorizontal & 4) > 0) {
                        if (fromBottomToTop) {
                            dividerTop = (view.getTop() - lp.topMargin) - this.mDividerHorizontalHeight;
                        } else {
                            dividerTop = view.getBottom() + lp.bottomMargin;
                        }
                        drawHorizontalDivider(canvas, flexLine.mLeft, dividerTop, flexLine.mCrossSize);
                    }
                }
            }
            if (hasDividerBeforeFlexLine(i)) {
                if (isRtl) {
                    verticalDividerLeft = flexLine.mRight;
                } else {
                    verticalDividerLeft = flexLine.mLeft - this.mDividerVerticalWidth;
                }
                drawVerticalDivider(canvas, verticalDividerLeft, paddingTop, verticalDividerLength);
            }
            if (hasEndDividerAfterFlexLine(i) && (this.mShowDividerVertical & 4) > 0) {
                if (isRtl) {
                    verticalDividerLeft = flexLine.mLeft - this.mDividerVerticalWidth;
                } else {
                    verticalDividerLeft = flexLine.mRight;
                }
                drawVerticalDivider(canvas, verticalDividerLeft, paddingTop, verticalDividerLength);
            }
        }
    }

    private void drawVerticalDivider(Canvas canvas, int left, int top, int length) {
        if (this.mDividerDrawableVertical != null) {
            this.mDividerDrawableVertical.setBounds(left, top, this.mDividerVerticalWidth + left, top + length);
            this.mDividerDrawableVertical.draw(canvas);
        }
    }

    private void drawHorizontalDivider(Canvas canvas, int left, int top, int length) {
        if (this.mDividerDrawableHorizontal != null) {
            this.mDividerDrawableHorizontal.setBounds(left, top, left + length, this.mDividerHorizontalHeight + top);
            this.mDividerDrawableHorizontal.draw(canvas);
        }
    }

    protected boolean checkLayoutParams(android.view.ViewGroup.LayoutParams p) {
        return p instanceof LayoutParams;
    }

    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(getContext(), attrs);
    }

    protected android.view.ViewGroup.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams lp) {
        if (lp instanceof LayoutParams) {
            return new LayoutParams((LayoutParams) lp);
        }
        if (lp instanceof MarginLayoutParams) {
            return new LayoutParams((MarginLayoutParams) lp);
        }
        return new LayoutParams(lp);
    }

    public int getFlexDirection() {
        return this.mFlexDirection;
    }

    public void setFlexDirection(int flexDirection) {
        if (this.mFlexDirection != flexDirection) {
            this.mFlexDirection = flexDirection;
            requestLayout();
        }
    }

    public int getFlexWrap() {
        return this.mFlexWrap;
    }

    public void setFlexWrap(int flexWrap) {
        if (this.mFlexWrap != flexWrap) {
            this.mFlexWrap = flexWrap;
            requestLayout();
        }
    }

    public int getJustifyContent() {
        return this.mJustifyContent;
    }

    public void setJustifyContent(int justifyContent) {
        if (this.mJustifyContent != justifyContent) {
            this.mJustifyContent = justifyContent;
            requestLayout();
        }
    }

    public int getAlignItems() {
        return this.mAlignItems;
    }

    public void setAlignItems(int alignItems) {
        if (this.mAlignItems != alignItems) {
            this.mAlignItems = alignItems;
            requestLayout();
        }
    }

    public int getAlignContent() {
        return this.mAlignContent;
    }

    public void setAlignContent(int alignContent) {
        if (this.mAlignContent != alignContent) {
            this.mAlignContent = alignContent;
            requestLayout();
        }
    }

    public List<FlexLine> getFlexLines() {
        List<FlexLine> result = new ArrayList(this.mFlexLines.size());
        for (FlexLine flexLine : this.mFlexLines) {
            if (flexLine.getItemCountNotGone() != 0) {
                result.add(flexLine);
            }
        }
        return result;
    }

    public int getDecorationLengthMainAxis(View view, int index, int indexInFlexLine) {
        int decorationLength = 0;
        if (isMainAxisDirectionHorizontal()) {
            if (hasDividerBeforeChildAtAlongMainAxis(index, indexInFlexLine)) {
                decorationLength = 0 + this.mDividerVerticalWidth;
            }
            if ((this.mShowDividerVertical & 4) > 0) {
                return decorationLength + this.mDividerVerticalWidth;
            }
            return decorationLength;
        }
        if (hasDividerBeforeChildAtAlongMainAxis(index, indexInFlexLine)) {
            decorationLength = 0 + this.mDividerHorizontalHeight;
        }
        if ((this.mShowDividerHorizontal & 4) > 0) {
            return decorationLength + this.mDividerHorizontalHeight;
        }
        return decorationLength;
    }

    public int getDecorationLengthCrossAxis(View view) {
        return 0;
    }

    public void onNewFlexLineAdded(FlexLine flexLine) {
        if (isMainAxisDirectionHorizontal()) {
            if ((this.mShowDividerVertical & 4) > 0) {
                flexLine.mMainSize += this.mDividerVerticalWidth;
                flexLine.mDividerLengthInMainSize += this.mDividerVerticalWidth;
            }
        } else if ((this.mShowDividerHorizontal & 4) > 0) {
            flexLine.mMainSize += this.mDividerHorizontalHeight;
            flexLine.mDividerLengthInMainSize += this.mDividerHorizontalHeight;
        }
    }

    public int getChildWidthMeasureSpec(int widthSpec, int padding, int childDimension) {
        return getChildMeasureSpec(widthSpec, padding, childDimension);
    }

    public int getChildHeightMeasureSpec(int heightSpec, int padding, int childDimension) {
        return getChildMeasureSpec(heightSpec, padding, childDimension);
    }

    public void onNewFlexItemAdded(View view, int index, int indexInFlexLine, FlexLine flexLine) {
        if (!hasDividerBeforeChildAtAlongMainAxis(index, indexInFlexLine)) {
            return;
        }
        if (isMainAxisDirectionHorizontal()) {
            flexLine.mMainSize += this.mDividerVerticalWidth;
            flexLine.mDividerLengthInMainSize += this.mDividerVerticalWidth;
            return;
        }
        flexLine.mMainSize += this.mDividerHorizontalHeight;
        flexLine.mDividerLengthInMainSize += this.mDividerHorizontalHeight;
    }

    public void setFlexLines(List<FlexLine> flexLines) {
        this.mFlexLines = flexLines;
    }

    public List<FlexLine> getFlexLinesInternal() {
        return this.mFlexLines;
    }

    public void updateViewCache(int position, View view) {
    }

    @Nullable
    public Drawable getDividerDrawableHorizontal() {
        return this.mDividerDrawableHorizontal;
    }

    @Nullable
    public Drawable getDividerDrawableVertical() {
        return this.mDividerDrawableVertical;
    }

    public void setDividerDrawable(Drawable divider) {
        setDividerDrawableHorizontal(divider);
        setDividerDrawableVertical(divider);
    }

    public void setDividerDrawableHorizontal(@Nullable Drawable divider) {
        if (divider != this.mDividerDrawableHorizontal) {
            this.mDividerDrawableHorizontal = divider;
            if (divider != null) {
                this.mDividerHorizontalHeight = divider.getIntrinsicHeight();
            } else {
                this.mDividerHorizontalHeight = 0;
            }
            setWillNotDrawFlag();
            requestLayout();
        }
    }

    public void setDividerDrawableVertical(@Nullable Drawable divider) {
        if (divider != this.mDividerDrawableVertical) {
            this.mDividerDrawableVertical = divider;
            if (divider != null) {
                this.mDividerVerticalWidth = divider.getIntrinsicWidth();
            } else {
                this.mDividerVerticalWidth = 0;
            }
            setWillNotDrawFlag();
            requestLayout();
        }
    }

    public int getShowDividerVertical() {
        return this.mShowDividerVertical;
    }

    public int getShowDividerHorizontal() {
        return this.mShowDividerHorizontal;
    }

    public void setShowDivider(int dividerMode) {
        setShowDividerVertical(dividerMode);
        setShowDividerHorizontal(dividerMode);
    }

    public void setShowDividerVertical(int dividerMode) {
        if (dividerMode != this.mShowDividerVertical) {
            this.mShowDividerVertical = dividerMode;
            requestLayout();
        }
    }

    public void setShowDividerHorizontal(int dividerMode) {
        if (dividerMode != this.mShowDividerHorizontal) {
            this.mShowDividerHorizontal = dividerMode;
            requestLayout();
        }
    }

    private void setWillNotDrawFlag() {
        if (this.mDividerDrawableHorizontal == null && this.mDividerDrawableVertical == null) {
            setWillNotDraw(true);
        } else {
            setWillNotDraw(false);
        }
    }

    private boolean hasDividerBeforeChildAtAlongMainAxis(int index, int indexInFlexLine) {
        if (allViewsAreGoneBefore(index, indexInFlexLine)) {
            if (isMainAxisDirectionHorizontal()) {
                if ((this.mShowDividerVertical & 1) != 0) {
                    return true;
                }
                return false;
            } else if ((this.mShowDividerHorizontal & 1) == 0) {
                return false;
            } else {
                return true;
            }
        } else if (isMainAxisDirectionHorizontal()) {
            if ((this.mShowDividerVertical & 2) == 0) {
                return false;
            }
            return true;
        } else if ((this.mShowDividerHorizontal & 2) == 0) {
            return false;
        } else {
            return true;
        }
    }

    private boolean allViewsAreGoneBefore(int index, int indexInFlexLine) {
        for (int i = 1; i <= indexInFlexLine; i++) {
            View view = getReorderedChildAt(index - i);
            if (view != null && view.getVisibility() != 8) {
                return false;
            }
        }
        return true;
    }

    private boolean hasDividerBeforeFlexLine(int flexLineIndex) {
        if (flexLineIndex < 0 || flexLineIndex >= this.mFlexLines.size()) {
            return false;
        }
        if (allFlexLinesAreDummyBefore(flexLineIndex)) {
            if (isMainAxisDirectionHorizontal()) {
                if ((this.mShowDividerHorizontal & 1) == 0) {
                    return false;
                }
                return true;
            } else if ((this.mShowDividerVertical & 1) == 0) {
                return false;
            } else {
                return true;
            }
        } else if (isMainAxisDirectionHorizontal()) {
            if ((this.mShowDividerHorizontal & 2) == 0) {
                return false;
            }
            return true;
        } else if ((this.mShowDividerVertical & 2) == 0) {
            return false;
        } else {
            return true;
        }
    }

    private boolean allFlexLinesAreDummyBefore(int flexLineIndex) {
        for (int i = 0; i < flexLineIndex; i++) {
            if (((FlexLine) this.mFlexLines.get(i)).getItemCountNotGone() > 0) {
                return false;
            }
        }
        return true;
    }

    private boolean hasEndDividerAfterFlexLine(int flexLineIndex) {
        boolean z = true;
        if (flexLineIndex < 0 || flexLineIndex >= this.mFlexLines.size()) {
            return false;
        }
        for (int i = flexLineIndex + 1; i < this.mFlexLines.size(); i++) {
            if (((FlexLine) this.mFlexLines.get(i)).getItemCountNotGone() > 0) {
                return false;
            }
        }
        if (isMainAxisDirectionHorizontal()) {
            return (this.mShowDividerHorizontal & 4) != 0;
        }
        if ((this.mShowDividerVertical & 4) == 0) {
            z = false;
        }
        return z;
    }
}
