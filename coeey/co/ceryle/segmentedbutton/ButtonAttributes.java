package co.ceryle.segmentedbutton;

import android.view.View;
import android.widget.LinearLayout.LayoutParams;

class ButtonAttributes {
    private View dividerView;
    private boolean hasRippleColor;
    private boolean hasTextColor;
    private boolean hasTintColor;
    private boolean hasWeight;
    private boolean hasWidth;
    private int rippleColor;
    private View rippleView;
    private int textColor;
    private int tintColor;
    private float weight = 0.0f;
    private int width = 0;

    ButtonAttributes() {
    }

    View getRippleView() {
        return this.rippleView;
    }

    LayoutParams getRippleViewParams() {
        return (LayoutParams) this.rippleView.getLayoutParams();
    }

    void setRippleView(View rippleView) {
        this.rippleView = rippleView;
    }

    View getDividerView() {
        return this.dividerView;
    }

    LayoutParams getDividerViewParams() {
        return (LayoutParams) this.dividerView.getLayoutParams();
    }

    void setDividerView(View dividerView) {
        this.dividerView = dividerView;
    }

    float getWeight() {
        return this.weight;
    }

    void setWeight(float weight) {
        this.weight = weight;
    }

    int getWidth() {
        return this.width;
    }

    boolean hasWidth() {
        return this.hasWidth;
    }

    void setHasWidth(boolean hasWidth) {
        this.hasWidth = hasWidth;
    }

    boolean hasWeight() {
        return this.hasWeight;
    }

    void setHasWeight(boolean hasWeight) {
        this.hasWeight = hasWeight;
    }

    void setWidth(int width) {
        this.width = width;
    }

    int getTintColor() {
        return this.tintColor;
    }

    void setTintColor(int tintColor) {
        this.tintColor = tintColor;
    }

    int getTextColor() {
        return this.textColor;
    }

    void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    boolean hasTintColor() {
        return this.hasTintColor;
    }

    void setTintColor(boolean hasTintColor) {
        this.hasTintColor = hasTintColor;
    }

    boolean hasTextColor() {
        return this.hasTextColor;
    }

    void setTextColor(boolean hasTextColor) {
        this.hasTextColor = hasTextColor;
    }

    boolean hasRippleColor() {
        return this.hasRippleColor;
    }

    void setRippleColor(boolean hasRippleColor) {
        this.hasRippleColor = hasRippleColor;
    }

    int getRippleColor() {
        return this.rippleColor;
    }

    void setRippleColor(int rippleColor) {
        this.rippleColor = rippleColor;
    }
}
