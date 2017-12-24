package org.apmem.tools.layouts;

import android.view.View;
import java.util.ArrayList;
import java.util.List;
import org.apmem.tools.layouts.FlowLayout.LayoutParams;

class LineDefinition {
    private int lineLength;
    private int lineStartLength = 0;
    private int lineStartThickness = 0;
    private int lineThickness;
    private final int maxLength;
    private final List<View> views = new ArrayList();

    public LineDefinition(int maxLength) {
        this.maxLength = maxLength;
    }

    public void addView(View child) {
        addView(this.views.size(), child);
    }

    public void addView(int i, View child) {
        LayoutParams lp = (LayoutParams) child.getLayoutParams();
        this.views.add(i, child);
        this.lineLength = (this.lineLength + lp.getLength()) + lp.getSpacingLength();
        this.lineThickness = Math.max(this.lineThickness, lp.getThickness() + lp.getSpacingThickness());
    }

    public boolean canFit(View child) {
        LayoutParams lp = (LayoutParams) child.getLayoutParams();
        return (this.lineLength + lp.getLength()) + lp.getSpacingLength() <= this.maxLength;
    }

    public int getLineStartThickness() {
        return this.lineStartThickness;
    }

    public void setLineStartThickness(int lineStartThickness) {
        this.lineStartThickness = lineStartThickness;
    }

    public int getLineThickness() {
        return this.lineThickness;
    }

    public int getLineLength() {
        return this.lineLength;
    }

    public int getLineStartLength() {
        return this.lineStartLength;
    }

    public void setLineStartLength(int lineStartLength) {
        this.lineStartLength = lineStartLength;
    }

    public List<View> getViews() {
        return this.views;
    }

    public void setThickness(int thickness) {
        this.lineThickness = thickness;
    }

    public void setLength(int length) {
        this.lineLength = length;
    }
}
