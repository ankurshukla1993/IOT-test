package com.nex3z.flowlayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import java.util.ArrayList;
import java.util.List;

public class FlowLayout extends ViewGroup {
    private static final int DEFAULT_CHILD_SPACING = 0;
    private static final int DEFAULT_CHILD_SPACING_FOR_LAST_ROW = -65538;
    private static final boolean DEFAULT_FLOW = true;
    private static final int DEFAULT_MAX_ROWS = Integer.MAX_VALUE;
    private static final float DEFAULT_ROW_SPACING = 0.0f;
    private static final boolean DEFAULT_RTL = false;
    private static final String LOG_TAG = FlowLayout.class.getSimpleName();
    public static final int SPACING_ALIGN = -65537;
    public static final int SPACING_AUTO = -65536;
    private static final int SPACING_UNDEFINED = -65538;
    private float mAdjustedRowSpacing;
    private List<Integer> mChildNumForRow;
    private int mChildSpacing;
    private int mChildSpacingForLastRow;
    private boolean mFlow;
    private List<Integer> mHeightForRow;
    private List<Float> mHorizontalSpacingForRow;
    private int mMaxRows;
    private float mRowSpacing;
    private boolean mRtl;

    public FlowLayout(Context context) {
        this(context, null);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mFlow = true;
        this.mChildSpacing = 0;
        this.mChildSpacingForLastRow = -65538;
        this.mRowSpacing = 0.0f;
        this.mAdjustedRowSpacing = 0.0f;
        this.mRtl = false;
        this.mMaxRows = Integer.MAX_VALUE;
        this.mHorizontalSpacingForRow = new ArrayList();
        this.mHeightForRow = new ArrayList();
        this.mChildNumForRow = new ArrayList();
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, C2264R.styleable.FlowLayout, 0, 0);
        this.mFlow = a.getBoolean(C2264R.styleable.FlowLayout_flFlow, true);
        try {
            this.mChildSpacing = a.getInt(C2264R.styleable.FlowLayout_flChildSpacing, 0);
        } catch (NumberFormatException e) {
            this.mChildSpacing = a.getDimensionPixelSize(C2264R.styleable.FlowLayout_flChildSpacing, (int) dpToPx(0.0f));
        } catch (Throwable th) {
            a.recycle();
        }
        try {
            this.mChildSpacingForLastRow = a.getInt(C2264R.styleable.FlowLayout_flChildSpacingForLastRow, -65538);
        } catch (NumberFormatException e2) {
            this.mChildSpacingForLastRow = a.getDimensionPixelSize(C2264R.styleable.FlowLayout_flChildSpacingForLastRow, (int) dpToPx(0.0f));
        }
        try {
            this.mRowSpacing = (float) a.getInt(C2264R.styleable.FlowLayout_flRowSpacing, 0);
        } catch (NumberFormatException e3) {
            this.mRowSpacing = a.getDimension(C2264R.styleable.FlowLayout_flRowSpacing, dpToPx(0.0f));
        }
        this.mMaxRows = a.getInt(C2264R.styleable.FlowLayout_flMaxRows, Integer.MAX_VALUE);
        this.mRtl = a.getBoolean(C2264R.styleable.FlowLayout_flRtl, false);
        a.recycle();
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        this.mHorizontalSpacingForRow.clear();
        this.mChildNumForRow.clear();
        this.mHeightForRow.clear();
        int measuredHeight = 0;
        int measuredWidth = 0;
        int childCount = getChildCount();
        int rowWidth = 0;
        int maxChildHeightInRow = 0;
        int childNumInRow = 0;
        int rowSize = (widthSize - getPaddingLeft()) - getPaddingRight();
        boolean allowFlow = widthMode != 0 && this.mFlow;
        int childSpacing = (this.mChildSpacing == -65536 && widthMode == 0) ? 0 : this.mChildSpacing;
        float tmpSpacing = childSpacing == -65536 ? 0.0f : (float) childSpacing;
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            if (child.getVisibility() != 8) {
                LayoutParams childParams = child.getLayoutParams();
                int horizontalMargin = 0;
                int verticalMargin = 0;
                if (childParams instanceof MarginLayoutParams) {
                    measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, measuredHeight);
                    MarginLayoutParams marginParams = (MarginLayoutParams) childParams;
                    horizontalMargin = marginParams.leftMargin + marginParams.rightMargin;
                    verticalMargin = marginParams.topMargin + marginParams.bottomMargin;
                } else {
                    measureChild(child, widthMeasureSpec, heightMeasureSpec);
                }
                int childWidth = child.getMeasuredWidth() + horizontalMargin;
                int childHeight = child.getMeasuredHeight() + verticalMargin;
                if (!allowFlow || rowWidth + childWidth <= rowSize) {
                    childNumInRow++;
                    rowWidth = (int) (((float) rowWidth) + (((float) childWidth) + tmpSpacing));
                    maxChildHeightInRow = Math.max(maxChildHeightInRow, childHeight);
                } else {
                    this.mHorizontalSpacingForRow.add(Float.valueOf(getSpacingForRow(childSpacing, rowSize, rowWidth, childNumInRow)));
                    this.mChildNumForRow.add(Integer.valueOf(childNumInRow));
                    this.mHeightForRow.add(Integer.valueOf(maxChildHeightInRow));
                    if (this.mHorizontalSpacingForRow.size() <= this.mMaxRows) {
                        measuredHeight += maxChildHeightInRow;
                    }
                    measuredWidth = Math.max(measuredWidth, rowWidth);
                    childNumInRow = 1;
                    rowWidth = childWidth + ((int) tmpSpacing);
                    maxChildHeightInRow = childHeight;
                }
            }
        }
        if (this.mChildSpacingForLastRow == SPACING_ALIGN) {
            if (this.mHorizontalSpacingForRow.size() >= 1) {
                this.mHorizontalSpacingForRow.add(this.mHorizontalSpacingForRow.get(this.mHorizontalSpacingForRow.size() - 1));
            } else {
                this.mHorizontalSpacingForRow.add(Float.valueOf(getSpacingForRow(childSpacing, rowSize, rowWidth, childNumInRow)));
            }
        } else if (this.mChildSpacingForLastRow != -65538) {
            this.mHorizontalSpacingForRow.add(Float.valueOf(getSpacingForRow(this.mChildSpacingForLastRow, rowSize, rowWidth, childNumInRow)));
        } else {
            this.mHorizontalSpacingForRow.add(Float.valueOf(getSpacingForRow(childSpacing, rowSize, rowWidth, childNumInRow)));
        }
        this.mChildNumForRow.add(Integer.valueOf(childNumInRow));
        this.mHeightForRow.add(Integer.valueOf(maxChildHeightInRow));
        if (this.mHorizontalSpacingForRow.size() <= this.mMaxRows) {
            measuredHeight += maxChildHeightInRow;
        }
        measuredWidth = Math.max(measuredWidth, rowWidth);
        if (childSpacing == -65536) {
            measuredWidth = widthSize;
        } else if (widthMode == 0) {
            measuredWidth = (getPaddingLeft() + measuredWidth) + getPaddingRight();
        } else {
            measuredWidth = Math.min((getPaddingLeft() + measuredWidth) + getPaddingRight(), widthSize);
        }
        measuredHeight += getPaddingTop() + getPaddingBottom();
        int rowNum = Math.min(this.mHorizontalSpacingForRow.size(), this.mMaxRows);
        float rowSpacing = (this.mRowSpacing == -65536.0f && heightMode == 0) ? 0.0f : this.mRowSpacing;
        if (rowSpacing == -65536.0f) {
            if (rowNum > 1) {
                this.mAdjustedRowSpacing = (float) ((heightSize - measuredHeight) / (rowNum - 1));
            } else {
                this.mAdjustedRowSpacing = 0.0f;
            }
            measuredHeight = heightSize;
        } else {
            this.mAdjustedRowSpacing = rowSpacing;
            if (rowNum > 1) {
                if (heightMode == 0) {
                    measuredHeight = (int) (((float) measuredHeight) + (this.mAdjustedRowSpacing * ((float) (rowNum - 1))));
                } else {
                    measuredHeight = Math.min((int) (((float) measuredHeight) + (this.mAdjustedRowSpacing * ((float) (rowNum - 1)))), heightSize);
                }
            }
        }
        if (widthMode == 1073741824) {
            measuredWidth = widthSize;
        }
        if (heightMode == 1073741824) {
            measuredHeight = heightSize;
        }
        setMeasuredDimension(measuredWidth, measuredHeight);
    }

    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int x;
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        if (this.mRtl) {
            x = getWidth() - paddingRight;
        } else {
            x = paddingLeft;
        }
        int y = paddingTop;
        int rowCount = this.mChildNumForRow.size();
        int childIdx = 0;
        int row = 0;
        while (row < rowCount) {
            int childNum = ((Integer) this.mChildNumForRow.get(row)).intValue();
            int rowHeight = ((Integer) this.mHeightForRow.get(row)).intValue();
            float spacing = ((Float) this.mHorizontalSpacingForRow.get(row)).floatValue();
            int i = 0;
            int childIdx2 = childIdx;
            while (i < childNum && childIdx2 < getChildCount()) {
                childIdx = childIdx2 + 1;
                View child = getChildAt(childIdx2);
                if (child.getVisibility() == 8) {
                    childIdx2 = childIdx;
                } else {
                    i++;
                    LayoutParams childParams = child.getLayoutParams();
                    int marginLeft = 0;
                    int marginTop = 0;
                    int marginRight = 0;
                    if (childParams instanceof MarginLayoutParams) {
                        MarginLayoutParams marginParams = (MarginLayoutParams) childParams;
                        marginLeft = marginParams.leftMargin;
                        marginRight = marginParams.rightMargin;
                        marginTop = marginParams.topMargin;
                    }
                    int childWidth = child.getMeasuredWidth();
                    int childHeight = child.getMeasuredHeight();
                    if (this.mRtl) {
                        child.layout((x - marginRight) - childWidth, y + marginTop, x - marginRight, (y + marginTop) + childHeight);
                        x = (int) (((float) x) - (((((float) childWidth) + spacing) + ((float) marginLeft)) + ((float) marginRight)));
                    } else {
                        child.layout(x + marginLeft, y + marginTop, (x + marginLeft) + childWidth, (y + marginTop) + childHeight);
                        x = (int) (((float) x) + (((((float) childWidth) + spacing) + ((float) marginLeft)) + ((float) marginRight)));
                    }
                    childIdx2 = childIdx;
                }
            }
            if (this.mRtl) {
                x = getWidth() - paddingRight;
            } else {
                x = paddingLeft;
            }
            y = (int) (((float) y) + (((float) rowHeight) + this.mAdjustedRowSpacing));
            row++;
            childIdx = childIdx2;
        }
    }

    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new MarginLayoutParams(p);
    }

    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    public boolean isFlow() {
        return this.mFlow;
    }

    public void setFlow(boolean flow) {
        this.mFlow = flow;
        requestLayout();
    }

    public int getChildSpacing() {
        return this.mChildSpacing;
    }

    public void setChildSpacing(int childSpacing) {
        this.mChildSpacing = childSpacing;
        requestLayout();
    }

    public int getChildSpacingForLastRow() {
        return this.mChildSpacingForLastRow;
    }

    public void setChildSpacingForLastRow(int childSpacingForLastRow) {
        this.mChildSpacingForLastRow = childSpacingForLastRow;
        requestLayout();
    }

    public float getRowSpacing() {
        return this.mRowSpacing;
    }

    public void setRowSpacing(float rowSpacing) {
        this.mRowSpacing = rowSpacing;
        requestLayout();
    }

    public int getMaxRows() {
        return this.mMaxRows;
    }

    public void setMaxRows(int maxRows) {
        this.mMaxRows = maxRows;
        requestLayout();
    }

    private float getSpacingForRow(int spacingAttribute, int rowSize, int usedSize, int childNum) {
        if (spacingAttribute != -65536) {
            return (float) spacingAttribute;
        }
        if (childNum > 1) {
            return (float) ((rowSize - usedSize) / (childNum - 1));
        }
        return 0.0f;
    }

    private float dpToPx(float dp) {
        return TypedValue.applyDimension(1, dp, getResources().getDisplayMetrics());
    }
}
