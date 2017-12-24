package org.apmem.tools.layouts;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v4.view.InputDeviceCompat;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import com.google.common.primitives.Ints;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class FlowLayout extends ViewGroup {
    public static final int HORIZONTAL = 0;
    public static final int LAYOUT_DIRECTION_LTR = 0;
    public static final int LAYOUT_DIRECTION_RTL = 1;
    public static final int VERTICAL = 1;
    private final LayoutConfiguration config;
    List<LineDefinition> lines = new ArrayList();

    public FlowLayout(Context context) {
        super(context);
        this.config = new LayoutConfiguration(context, null);
    }

    public FlowLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.config = new LayoutConfiguration(context, attributeSet);
    }

    public FlowLayout(Context context, AttributeSet attributeSet, int defStyle) {
        super(context, attributeSet, defStyle);
        this.config = new LayoutConfiguration(context, attributeSet);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int controlMaxLength;
        int controlMaxThickness;
        int modeLength;
        int i;
        int sizeWidth = (MeasureSpec.getSize(widthMeasureSpec) - getPaddingRight()) - getPaddingLeft();
        int sizeHeight = (MeasureSpec.getSize(heightMeasureSpec) - getPaddingTop()) - getPaddingBottom();
        int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
        int modeHeight = MeasureSpec.getMode(heightMeasureSpec);
        if (this.config.getOrientation() == 0) {
            controlMaxLength = sizeWidth;
        } else {
            controlMaxLength = sizeHeight;
        }
        if (this.config.getOrientation() == 0) {
            controlMaxThickness = sizeHeight;
        } else {
            controlMaxThickness = sizeWidth;
        }
        if (this.config.getOrientation() == 0) {
            modeLength = modeWidth;
        } else {
            modeLength = modeHeight;
        }
        int modeThickness;
        if (this.config.getOrientation() == 0) {
            modeThickness = modeHeight;
        } else {
            modeThickness = modeWidth;
        }
        this.lines.clear();
        LineDefinition currentLine = new LineDefinition(controlMaxLength);
        this.lines.add(currentLine);
        int count = getChildCount();
        for (i = 0; i < count; i++) {
            View child = getChildAt(i);
            if (child.getVisibility() != 8) {
                LayoutParams lp = (LayoutParams) child.getLayoutParams();
                child.measure(getChildMeasureSpec(widthMeasureSpec, getPaddingLeft() + getPaddingRight(), lp.width), getChildMeasureSpec(heightMeasureSpec, getPaddingTop() + getPaddingBottom(), lp.height));
                LayoutParams.access$002(lp, this.config.getOrientation());
                if (this.config.getOrientation() == 0) {
                    lp.setLength(child.getMeasuredWidth());
                    lp.setThickness(child.getMeasuredHeight());
                } else {
                    lp.setLength(child.getMeasuredHeight());
                    lp.setThickness(child.getMeasuredWidth());
                }
                boolean newLine = lp.isNewLine() || !(modeLength == 0 || currentLine.canFit(child));
                if (newLine) {
                    currentLine = new LineDefinition(controlMaxLength);
                    if (this.config.getOrientation() == 1 && this.config.getLayoutDirection() == 1) {
                        this.lines.add(0, currentLine);
                    } else {
                        this.lines.add(currentLine);
                    }
                }
                if (this.config.getOrientation() == 0 && this.config.getLayoutDirection() == 1) {
                    currentLine.addView(0, child);
                } else {
                    currentLine.addView(child);
                }
            }
        }
        calculateLinesAndChildPosition(this.lines);
        int contentLength = 0;
        int linesCount = this.lines.size();
        for (i = 0; i < linesCount; i++) {
            contentLength = Math.max(contentLength, ((LineDefinition) this.lines.get(i)).getLineLength());
        }
        int contentThickness = currentLine.getLineStartThickness() + currentLine.getLineThickness();
        applyGravityToLines(this.lines, findSize(modeLength, controlMaxLength, contentLength), findSize(modeHeight, controlMaxThickness, contentThickness));
        for (i = 0; i < linesCount; i++) {
            LineDefinition line = (LineDefinition) this.lines.get(i);
            applyGravityToLine(line);
            applyPositionsToViews(line);
        }
        int totalControlWidth = getPaddingLeft() + getPaddingRight();
        int totalControlHeight = getPaddingBottom() + getPaddingTop();
        if (this.config.getOrientation() == 0) {
            totalControlWidth += contentLength;
            totalControlHeight += contentThickness;
        } else {
            totalControlWidth += contentThickness;
            totalControlHeight += contentLength;
        }
        setMeasuredDimension(resolveSize(totalControlWidth, widthMeasureSpec), resolveSize(totalControlHeight, heightMeasureSpec));
    }

    private int findSize(int modeSize, int controlMaxSize, int contentSize) {
        switch (modeSize) {
            case Integer.MIN_VALUE:
                return Math.min(contentSize, controlMaxSize);
            case 0:
                return contentSize;
            case Ints.MAX_POWER_OF_TWO /*1073741824*/:
                return controlMaxSize;
            default:
                return contentSize;
        }
    }

    private void calculateLinesAndChildPosition(List<LineDefinition> lines) {
        int prevLinesThickness = 0;
        int linesCount = lines.size();
        for (int i = 0; i < linesCount; i++) {
            LineDefinition line = (LineDefinition) lines.get(i);
            line.setLineStartThickness(prevLinesThickness);
            prevLinesThickness += line.getLineThickness();
            int prevChildThickness = 0;
            List<View> childViews = line.getViews();
            int childCount = childViews.size();
            for (int j = 0; j < childCount; j++) {
                LayoutParams lp = (LayoutParams) ((View) childViews.get(j)).getLayoutParams();
                lp.setInlineStartLength(prevChildThickness);
                prevChildThickness += lp.getLength() + lp.getSpacingLength();
            }
        }
    }

    private void applyPositionsToViews(LineDefinition line) {
        List<View> childViews = line.getViews();
        int childCount = childViews.size();
        for (int i = 0; i < childCount; i++) {
            View child = (View) childViews.get(i);
            LayoutParams layoutParams = (LayoutParams) child.getLayoutParams();
            if (this.config.getOrientation() == 0) {
                layoutParams.setPosition((getPaddingLeft() + line.getLineStartLength()) + layoutParams.getInlineStartLength(), (getPaddingTop() + line.getLineStartThickness()) + layoutParams.getInlineStartThickness());
                child.measure(MeasureSpec.makeMeasureSpec(layoutParams.getLength(), Ints.MAX_POWER_OF_TWO), MeasureSpec.makeMeasureSpec(layoutParams.getThickness(), Ints.MAX_POWER_OF_TWO));
            } else {
                layoutParams.setPosition((getPaddingLeft() + line.getLineStartThickness()) + layoutParams.getInlineStartThickness(), (getPaddingTop() + line.getLineStartLength()) + layoutParams.getInlineStartLength());
                child.measure(MeasureSpec.makeMeasureSpec(layoutParams.getThickness(), Ints.MAX_POWER_OF_TWO), MeasureSpec.makeMeasureSpec(layoutParams.getLength(), Ints.MAX_POWER_OF_TWO));
            }
        }
    }

    private void applyGravityToLines(List<LineDefinition> lines, int realControlLength, int realControlThickness) {
        int linesCount = lines.size();
        if (linesCount > 0) {
            int totalWeight = linesCount;
            LineDefinition lastLine = (LineDefinition) lines.get(linesCount - 1);
            int excessThickness = realControlThickness - (lastLine.getLineThickness() + lastLine.getLineStartThickness());
            if (excessThickness < 0) {
                excessThickness = 0;
            }
            int excessOffset = 0;
            for (int i = 0; i < linesCount; i++) {
                LineDefinition child = (LineDefinition) lines.get(i);
                int gravity = getGravity(null);
                int extraThickness = Math.round((float) ((excessThickness * 1) / totalWeight));
                int childLength = child.getLineLength();
                int childThickness = child.getLineThickness();
                Rect container = new Rect();
                container.top = excessOffset;
                container.left = 0;
                container.right = realControlLength;
                container.bottom = (childThickness + extraThickness) + excessOffset;
                Rect result = new Rect();
                Gravity.apply(gravity, childLength, childThickness, container, result);
                excessOffset += extraThickness;
                child.setLineStartLength(child.getLineStartLength() + result.left);
                child.setLineStartThickness(child.getLineStartThickness() + result.top);
                child.setLength(result.width());
                child.setThickness(result.height());
            }
        }
    }

    private void applyGravityToLine(LineDefinition line) {
        List<View> views = line.getViews();
        int viewCount = views.size();
        if (viewCount > 0) {
            int i;
            float totalWeight = 0.0f;
            for (i = 0; i < viewCount; i++) {
                totalWeight += getWeight((LayoutParams) ((View) views.get(i)).getLayoutParams());
            }
            LayoutParams lastChildLayoutParams = (LayoutParams) ((View) views.get(viewCount - 1)).getLayoutParams();
            int excessLength = line.getLineLength() - ((lastChildLayoutParams.getLength() + lastChildLayoutParams.getSpacingLength()) + lastChildLayoutParams.getInlineStartLength());
            int excessOffset = 0;
            for (i = 0; i < viewCount; i++) {
                int extraLength;
                LayoutParams layoutParams = (LayoutParams) ((View) views.get(i)).getLayoutParams();
                float weight = getWeight(layoutParams);
                int gravity = getGravity(layoutParams);
                if (totalWeight == 0.0f) {
                    extraLength = excessLength / viewCount;
                } else {
                    extraLength = Math.round((((float) excessLength) * weight) / totalWeight);
                }
                int childLength = layoutParams.getLength() + layoutParams.getSpacingLength();
                int childThickness = layoutParams.getThickness() + layoutParams.getSpacingThickness();
                Rect container = new Rect();
                container.top = 0;
                container.left = excessOffset;
                container.right = (childLength + extraLength) + excessOffset;
                container.bottom = line.getLineThickness();
                Rect result = new Rect();
                Gravity.apply(gravity, childLength, childThickness, container, result);
                excessOffset += extraLength;
                layoutParams.setInlineStartLength(result.left + layoutParams.getInlineStartLength());
                layoutParams.setInlineStartThickness(result.top);
                layoutParams.setLength(result.width() - layoutParams.getSpacingLength());
                layoutParams.setThickness(result.height() - layoutParams.getSpacingThickness());
            }
        }
    }

    private int getGravity(LayoutParams lp) {
        int childGravity;
        int parentGravity = this.config.getGravity();
        if (lp == null || !lp.gravitySpecified()) {
            childGravity = parentGravity;
        } else {
            childGravity = lp.getGravity();
        }
        childGravity = getGravityFromRelative(childGravity);
        parentGravity = getGravityFromRelative(parentGravity);
        if ((childGravity & 7) == 0) {
            childGravity |= parentGravity & 7;
        }
        if ((childGravity & 112) == 0) {
            childGravity |= parentGravity & 112;
        }
        if ((childGravity & 7) == 0) {
            childGravity |= 3;
        }
        if ((childGravity & 112) == 0) {
            return childGravity | 48;
        }
        return childGravity;
    }

    private int getGravityFromRelative(int childGravity) {
        int i = 3;
        if (this.config.getOrientation() == 1 && (childGravity & 8388608) == 0) {
            int horizontalGravity = childGravity;
            childGravity = (0 | (((horizontalGravity & 7) >> 0) << 4)) | (((horizontalGravity & 112) >> 4) << 0);
        }
        if (this.config.getLayoutDirection() != 1 || (childGravity & 8388608) == 0) {
            return childGravity;
        }
        int i2;
        int ltrGravity = childGravity;
        if ((ltrGravity & 3) == 3) {
            i2 = 5;
        } else {
            i2 = 0;
        }
        childGravity = 0 | i2;
        if ((ltrGravity & 5) != 5) {
            i = 0;
        }
        return childGravity | i;
    }

    private float getWeight(LayoutParams lp) {
        return lp.weightSpecified() ? lp.getWeight() : this.config.getWeightDefault();
    }

    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            LayoutParams lp = (LayoutParams) child.getLayoutParams();
            child.layout(LayoutParams.access$100(lp) + lp.leftMargin, LayoutParams.access$200(lp) + lp.topMargin, (LayoutParams.access$100(lp) + lp.leftMargin) + child.getMeasuredWidth(), (LayoutParams.access$200(lp) + lp.topMargin) + child.getMeasuredHeight());
        }
    }

    protected boolean drawChild(Canvas canvas, View child, long drawingTime) {
        boolean more = super.drawChild(canvas, child, drawingTime);
        drawDebugInfo(canvas, child);
        return more;
    }

    protected boolean checkLayoutParams(LayoutParams p) {
        return p instanceof LayoutParams;
    }

    protected LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-2, -2);
    }

    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new LayoutParams(p);
    }

    private void drawDebugInfo(Canvas canvas, View child) {
        if (isDebugDraw()) {
            float x;
            float y;
            Paint childPaint = createPaint(InputDeviceCompat.SOURCE_ANY);
            Paint newLinePaint = createPaint(-65536);
            LayoutParams lp = (LayoutParams) child.getLayoutParams();
            if (lp.rightMargin > 0) {
                x = (float) child.getRight();
                y = ((float) child.getTop()) + (((float) child.getHeight()) / 2.0f);
                canvas.drawLine(x, y, x + ((float) lp.rightMargin), y, childPaint);
                canvas.drawLine((((float) lp.rightMargin) + x) - 4.0f, y - 4.0f, x + ((float) lp.rightMargin), y, childPaint);
                canvas.drawLine((((float) lp.rightMargin) + x) - 4.0f, y + 4.0f, x + ((float) lp.rightMargin), y, childPaint);
            }
            if (lp.leftMargin > 0) {
                x = (float) child.getLeft();
                y = ((float) child.getTop()) + (((float) child.getHeight()) / 2.0f);
                canvas.drawLine(x, y, x - ((float) lp.leftMargin), y, childPaint);
                canvas.drawLine((x - ((float) lp.leftMargin)) + 4.0f, y - 4.0f, x - ((float) lp.leftMargin), y, childPaint);
                canvas.drawLine((x - ((float) lp.leftMargin)) + 4.0f, y + 4.0f, x - ((float) lp.leftMargin), y, childPaint);
            }
            if (lp.bottomMargin > 0) {
                x = ((float) child.getLeft()) + (((float) child.getWidth()) / 2.0f);
                y = (float) child.getBottom();
                canvas.drawLine(x, y, x, y + ((float) lp.bottomMargin), childPaint);
                canvas.drawLine(x - 4.0f, (((float) lp.bottomMargin) + y) - 4.0f, x, y + ((float) lp.bottomMargin), childPaint);
                canvas.drawLine(x + 4.0f, (((float) lp.bottomMargin) + y) - 4.0f, x, y + ((float) lp.bottomMargin), childPaint);
            }
            if (lp.topMargin > 0) {
                x = ((float) child.getLeft()) + (((float) child.getWidth()) / 2.0f);
                y = (float) child.getTop();
                canvas.drawLine(x, y, x, y - ((float) lp.topMargin), childPaint);
                canvas.drawLine(x - 4.0f, (y - ((float) lp.topMargin)) + 4.0f, x, y - ((float) lp.topMargin), childPaint);
                canvas.drawLine(x + 4.0f, (y - ((float) lp.topMargin)) + 4.0f, x, y - ((float) lp.topMargin), childPaint);
            }
            if (!lp.isNewLine()) {
                return;
            }
            if (this.config.getOrientation() == 0) {
                x = (float) child.getLeft();
                y = ((float) child.getTop()) + (((float) child.getHeight()) / 2.0f);
                canvas.drawLine(x, y - 6.0f, x, y + 6.0f, newLinePaint);
                return;
            }
            x = ((float) child.getLeft()) + (((float) child.getWidth()) / 2.0f);
            y = (float) child.getTop();
            canvas.drawLine(x - 6.0f, y, x + 6.0f, y, newLinePaint);
        }
    }

    private Paint createPaint(int color) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(color);
        paint.setStrokeWidth(2.0f);
        return paint;
    }

    public int getOrientation() {
        return this.config.getOrientation();
    }

    public void setOrientation(int orientation) {
        this.config.setOrientation(orientation);
        requestLayout();
    }

    public boolean isDebugDraw() {
        return this.config.isDebugDraw() || debugDraw();
    }

    public void setDebugDraw(boolean debugDraw) {
        this.config.setDebugDraw(debugDraw);
        invalidate();
    }

    private boolean debugDraw() {
        try {
            Method m = ViewGroup.class.getDeclaredMethod("debugDraw", (Class[]) null);
            m.setAccessible(true);
            return ((Boolean) m.invoke(this, new Object[]{null})).booleanValue();
        } catch (Exception e) {
            return false;
        }
    }

    public float getWeightDefault() {
        return this.config.getWeightDefault();
    }

    public void setWeightDefault(float weightDefault) {
        this.config.setWeightDefault(weightDefault);
        requestLayout();
    }

    public int getGravity() {
        return this.config.getGravity();
    }

    public void setGravity(int gravity) {
        this.config.setGravity(gravity);
        requestLayout();
    }

    public int getLayoutDirection() {
        if (this.config == null) {
            return 0;
        }
        return this.config.getLayoutDirection();
    }

    public void setLayoutDirection(int layoutDirection) {
        this.config.setLayoutDirection(layoutDirection);
        requestLayout();
    }
}
