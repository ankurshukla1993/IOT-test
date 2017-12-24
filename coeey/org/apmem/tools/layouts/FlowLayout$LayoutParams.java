package org.apmem.tools.layouts;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.ViewDebug.ExportedProperty;
import android.view.ViewDebug.IntToString;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;

public class FlowLayout$LayoutParams extends MarginLayoutParams {
    private int gravity = 0;
    private int inlineStartLength;
    private int inlineStartThickness;
    private int length;
    @ExportedProperty(mapping = {@IntToString(from = 0, to = "NONE"), @IntToString(from = 48, to = "TOP"), @IntToString(from = 80, to = "BOTTOM"), @IntToString(from = 3, to = "LEFT"), @IntToString(from = 5, to = "RIGHT"), @IntToString(from = 16, to = "CENTER_VERTICAL"), @IntToString(from = 112, to = "FILL_VERTICAL"), @IntToString(from = 1, to = "CENTER_HORIZONTAL"), @IntToString(from = 7, to = "FILL_HORIZONTAL"), @IntToString(from = 17, to = "CENTER"), @IntToString(from = 119, to = "FILL")})
    private boolean newLine = false;
    private int orientation;
    private int thickness;
    private float weight = FlexItem.FLEX_BASIS_PERCENT_DEFAULT;
    private int f27x;
    private int f28y;

    public FlowLayout$LayoutParams(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        readStyleParameters(context, attributeSet);
    }

    public FlowLayout$LayoutParams(int width, int height) {
        super(width, height);
    }

    public FlowLayout$LayoutParams(LayoutParams layoutParams) {
        super(layoutParams);
    }

    public boolean gravitySpecified() {
        return this.gravity != 0;
    }

    public boolean weightSpecified() {
        return this.weight >= 0.0f;
    }

    private void readStyleParameters(Context context, AttributeSet attributeSet) {
        TypedArray a = context.obtainStyledAttributes(attributeSet, R.styleable.FlowLayout_LayoutParams);
        try {
            this.newLine = a.getBoolean(R.styleable.FlowLayout_LayoutParams_layout_newLine, false);
            this.gravity = a.getInt(R.styleable.FlowLayout_LayoutParams_android_layout_gravity, 0);
            this.weight = a.getFloat(R.styleable.FlowLayout_LayoutParams_layout_weight, FlexItem.FLEX_BASIS_PERCENT_DEFAULT);
        } finally {
            a.recycle();
        }
    }

    void setPosition(int x, int y) {
        this.f27x = x;
        this.f28y = y;
    }

    int getInlineStartLength() {
        return this.inlineStartLength;
    }

    void setInlineStartLength(int inlineStartLength) {
        this.inlineStartLength = inlineStartLength;
    }

    int getLength() {
        return this.length;
    }

    void setLength(int length) {
        this.length = length;
    }

    int getThickness() {
        return this.thickness;
    }

    void setThickness(int thickness) {
        this.thickness = thickness;
    }

    int getInlineStartThickness() {
        return this.inlineStartThickness;
    }

    void setInlineStartThickness(int inlineStartThickness) {
        this.inlineStartThickness = inlineStartThickness;
    }

    int getSpacingLength() {
        if (this.orientation == 0) {
            return this.leftMargin + this.rightMargin;
        }
        return this.topMargin + this.bottomMargin;
    }

    int getSpacingThickness() {
        if (this.orientation == 0) {
            return this.topMargin + this.bottomMargin;
        }
        return this.leftMargin + this.rightMargin;
    }

    public int getX() {
        return this.f27x;
    }

    public int getY() {
        return this.f28y;
    }

    public int getGravity() {
        return this.gravity;
    }

    public void setGravity(int gravity) {
        this.gravity = gravity;
    }

    public float getWeight() {
        return this.weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public boolean isNewLine() {
        return this.newLine;
    }

    public void setNewLine(boolean newLine) {
        this.newLine = newLine;
    }
}
