package co.ceryle.segmentedbutton;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ScaleDrawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.Button;
import com.ceryle.segmentedbutton.R;

public class SegmentedButton extends Button {
    private float buttonImageScale;
    private float buttonWeight;
    private int buttonWidth;
    private Rect drawableBounds = new Rect();
    private boolean hasButtonImageTint;
    private boolean hasButtonWeight;
    private boolean hasButtonWidth;
    private boolean hasRippleColor;
    private boolean hasSelectedImageTint;
    private boolean hasSelectedTextColor;
    private int imageTint;
    private int rippleColor;
    private int selectedImageTint;
    private int selectedTextColor;
    private Rect textBounds = new Rect();

    public SegmentedButton(Context context) {
        super(context);
        init(null);
    }

    public SegmentedButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public SegmentedButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    @TargetApi(21)
    public SegmentedButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        getAttributes(attrs);
        if (this.hasButtonImageTint) {
            setImageTint(this.imageTint);
        }
        if (this.buttonImageScale != FlexItem.FLEX_SHRINK_DEFAULT) {
            scaleButtonDrawables((double) this.buttonImageScale);
        }
        setTransformationMethod(null);
    }

    private void getAttributes(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.SegmentedButton);
        this.imageTint = typedArray.getColor(R.styleable.SegmentedButton_sb_imageTint, -1);
        this.hasButtonImageTint = typedArray.hasValue(R.styleable.SegmentedButton_sb_imageTint);
        this.buttonImageScale = typedArray.getFloat(R.styleable.SegmentedButton_sb_imageScale, FlexItem.FLEX_SHRINK_DEFAULT);
        this.selectedImageTint = typedArray.getColor(R.styleable.SegmentedButton_sb_selectedImageTint, 0);
        this.hasSelectedImageTint = typedArray.hasValue(R.styleable.SegmentedButton_sb_selectedImageTint);
        this.selectedTextColor = typedArray.getColor(R.styleable.SegmentedButton_sb_selectedTextColor, 0);
        this.hasSelectedTextColor = typedArray.hasValue(R.styleable.SegmentedButton_sb_selectedTextColor);
        this.rippleColor = typedArray.getColor(R.styleable.SegmentedButton_sb_rippleColor, 0);
        this.hasRippleColor = typedArray.hasValue(R.styleable.SegmentedButton_sb_rippleColor);
        try {
            this.hasButtonWeight = typedArray.hasValue(R.styleable.SegmentedButton_android_layout_weight);
            this.buttonWeight = typedArray.getFloat(R.styleable.SegmentedButton_android_layout_weight, FlexItem.FLEX_BASIS_PERCENT_DEFAULT);
            this.buttonWidth = typedArray.getDimensionPixelSize(R.styleable.SegmentedButton_android_layout_width, 0);
            this.hasButtonWidth = typedArray.hasValue(R.styleable.SegmentedButton_android_layout_width);
        } catch (Exception ignored) {
            Log.d("SegmentedButton", ignored.toString());
        }
        typedArray.recycle();
    }

    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (changed) {
            if (this.buttonImageScale != FlexItem.FLEX_SHRINK_DEFAULT) {
                scaleButtonDrawables((double) this.buttonImageScale);
            }
            drawButton();
        }
    }

    private void drawButton() {
        CharSequence text = getText();
        if (TextUtils.isEmpty(text)) {
            this.textBounds.setEmpty();
        } else {
            getPaint().getTextBounds(text.toString(), 0, text.length(), this.textBounds);
        }
        int width = getWidth() - (getPaddingLeft() + getPaddingRight());
        int height = getHeight() - (getPaddingTop() + getPaddingBottom());
        Drawable[] drawables = getCompoundDrawables();
        int offSet = 0;
        for (int i = 0; i < drawables.length; i++) {
            if (drawables[i] != null) {
                drawables[i].copyBounds(this.drawableBounds);
                switch (i) {
                    case 0:
                        offSet = (((width - (this.textBounds.width() + this.drawableBounds.width())) + getRightPaddingOffset()) / 2) - getCompoundDrawablePadding();
                        break;
                    case 1:
                        offSet = (((height - (this.textBounds.height() + this.drawableBounds.height())) + getBottomPaddingOffset()) / 2) - getCompoundDrawablePadding();
                        break;
                    case 2:
                        offSet = ((((this.textBounds.width() + this.drawableBounds.width()) - width) + getLeftPaddingOffset()) / 2) + getCompoundDrawablePadding();
                        break;
                    case 3:
                        offSet = ((((this.textBounds.height() + this.drawableBounds.height()) - height) + getTopPaddingOffset()) / 2) + getCompoundDrawablePadding();
                        break;
                }
                if (i % 2 == 0) {
                    this.drawableBounds.offset(offSet, 0);
                } else {
                    this.drawableBounds.offset(0, offSet);
                }
                drawables[i].setBounds(this.drawableBounds);
            }
        }
    }

    public void scaleButtonDrawables(double fitFactor) {
        Drawable[] drawables = getCompoundDrawables();
        for (int i = 0; i < drawables.length; i++) {
            if (drawables[i] != null) {
                if (drawables[i] instanceof ScaleDrawable) {
                    drawables[i].setLevel(1);
                }
                ScaleDrawable sd = new ScaleDrawable(drawables[i], 0, (float) drawables[i].getIntrinsicWidth(), (float) drawables[i].getIntrinsicHeight());
                drawables[i].setBounds(0, 0, (int) (((double) drawables[i].getIntrinsicWidth()) * fitFactor), (int) (((double) drawables[i].getIntrinsicHeight()) * fitFactor));
                if (i == 0) {
                    setCompoundDrawables(drawables[i], drawables[1], drawables[2], drawables[3]);
                } else if (i == 1) {
                    setCompoundDrawables(drawables[0], sd.getDrawable(), drawables[2], drawables[3]);
                } else if (i == 2) {
                    setCompoundDrawables(drawables[0], drawables[1], sd.getDrawable(), drawables[3]);
                } else {
                    setCompoundDrawables(drawables[0], drawables[1], drawables[2], sd.getDrawable());
                }
            }
        }
    }

    public void removeImageTint() {
        for (int i = 0; i < getCompoundDrawables().length; i++) {
            if (getCompoundDrawables()[i] != null) {
                getCompoundDrawables()[i].clearColorFilter();
            }
        }
    }

    public void setImageTint(int color) {
        int pos = 0;
        Drawable drawable = null;
        if (getCompoundDrawables().length > 0) {
            for (int i = 0; i < getCompoundDrawables().length; i++) {
                if (getCompoundDrawables()[i] != null) {
                    pos = i;
                    drawable = getCompoundDrawables()[i];
                }
            }
            if (drawable != null) {
                drawable.setColorFilter(new PorterDuffColorFilter(color, Mode.SRC_IN));
                if (pos == 0) {
                    setCompoundDrawables(drawable, null, null, null);
                } else if (pos == 1) {
                    setCompoundDrawables(null, drawable, null, null);
                } else if (pos == 2) {
                    setCompoundDrawables(null, null, drawable, null);
                } else {
                    setCompoundDrawables(null, null, null, drawable);
                }
            }
        }
    }

    public int getSelectedImageTint() {
        return this.selectedImageTint;
    }

    public int getImageTint() {
        return this.imageTint;
    }

    public float getButtonImageScale() {
        return this.buttonImageScale;
    }

    public boolean hasButtonImageTint() {
        return this.hasButtonImageTint;
    }

    public void setDrawableTop(int drawableId) {
        setCompoundDrawablesWithIntrinsicBounds(0, drawableId, 0, 0);
        setImageTint(this.imageTint);
    }

    public void setDrawableBottom(int drawableId) {
        setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, drawableId);
        setImageTint(this.imageTint);
    }

    public void setDrawableLeft(int drawableId) {
        setCompoundDrawablesWithIntrinsicBounds(drawableId, 0, 0, 0);
        setImageTint(this.imageTint);
    }

    public void setDrawableRight(int drawableId) {
        setCompoundDrawablesWithIntrinsicBounds(0, 0, drawableId, 0);
        setImageTint(this.imageTint);
    }

    public boolean hasImageTint() {
        return this.hasButtonImageTint;
    }

    public boolean hasSelectorTint() {
        return this.hasSelectedImageTint;
    }

    public boolean hasButtonWidth() {
        return this.hasButtonWidth;
    }

    public void setHasButtonWidth(boolean hasButtonWidth) {
        this.hasButtonWidth = hasButtonWidth;
    }

    public boolean hasButtonWeight() {
        return this.hasButtonWeight;
    }

    public void setHasButtonWeight(boolean hasButtonWeight) {
        this.hasButtonWeight = hasButtonWeight;
    }

    public float getButtonWeight() {
        return this.buttonWeight;
    }

    public void setButtonWeight(float buttonWeight) {
        this.buttonWeight = buttonWeight;
    }

    public int getButtonWidth() {
        return this.buttonWidth;
    }

    public void setButtonWidth(int buttonWidth) {
        this.buttonWidth = buttonWidth;
    }

    public int getRippleColor() {
        return this.rippleColor;
    }

    public boolean hasRippleColor() {
        return this.hasRippleColor;
    }

    public int getSelectedTextColor() {
        return this.selectedTextColor;
    }

    public void setSelectedTextColor(int selectedTextColor) {
        this.selectedTextColor = selectedTextColor;
    }

    public boolean hasSelectedTextColor() {
        return this.hasSelectedTextColor;
    }

    public void setHasSelectedTextColor(boolean hasSelectedTextColor) {
        this.hasSelectedTextColor = hasSelectedTextColor;
    }
}
