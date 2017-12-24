package co.ceryle.segmentedbutton;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.ceryle.segmentedbutton.R;
import com.google.common.primitives.Ints;
import java.util.ArrayList;
import java.util.Iterator;

public class SegmentedButtonGroup extends LinearLayout {
    private int animateSelector;
    private int animateSelectorDuration;
    private int backgroundColor;
    private Drawable backgroundDrawable;
    private int borderColor;
    private LayoutParams borderParams;
    private int borderSize;
    private ArrayList<ButtonAttributes> btnAttrs = new ArrayList();
    private float buttonWidth = 0.0f;
    private ArrayList<Button> buttons = new ArrayList();
    private Drawable dividerBackgroundDrawable;
    private int dividerColor;
    private LinearLayout dividerContainer;
    private int dividerPadding;
    private int dividerRadius;
    private int dividerSize;
    private boolean hasDivider;
    private boolean hasRippleColor;
    private boolean hasSelectorImageTint;
    private boolean hasWidth = false;
    private Interpolator interpolatorSelector;
    private int layoutSize = 0;
    private FrameLayout.LayoutParams leftBitmapParams;
    private ImageView leftGroup;
    private LinearLayout mainGroup;
    private int margin;
    private OnClickedButtonPosition onClickedButtonPosition;
    private int position;
    private float radius;
    private FrameLayout.LayoutParams rightBitmapParams;
    private ImageView rightGroup;
    private boolean ripple;
    private int rippleColor;
    private LinearLayout rippleContainer;
    private RoundedCornerLayout roundedLayout;
    private Drawable selectorBackgroundDrawable;
    private int selectorColor;
    private int selectorImageTint;
    private int selectorTextColor;
    private boolean shadow;
    private float shadowElevation;
    private int shadowMargin;
    private int shadowMarginBottom;
    private int shadowMarginLeft;
    private int shadowMarginRight;
    private int shadowMarginTop;
    private boolean sizeChanged = false;

    class C05312 extends ArrayList<Class> {
        C05312() {
            add(FastOutSlowInInterpolator.class);
            add(BounceInterpolator.class);
            add(LinearInterpolator.class);
            add(DecelerateInterpolator.class);
            add(CycleInterpolator.class);
            add(AnticipateInterpolator.class);
            add(AccelerateDecelerateInterpolator.class);
            add(AccelerateInterpolator.class);
            add(AnticipateOvershootInterpolator.class);
            add(FastOutLinearInInterpolator.class);
            add(LinearOutSlowInInterpolator.class);
            add(OvershootInterpolator.class);
        }
    }

    public interface OnClickedButtonPosition {
        void onClickedButtonPosition(int i);
    }

    public SegmentedButtonGroup(Context context) {
        super(context);
        init(null);
    }

    public SegmentedButtonGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public SegmentedButtonGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    @TargetApi(21)
    public SegmentedButtonGroup(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void init(AttributeSet attrs) {
        getAttributes(attrs);
        inflate(getContext(), R.layout.ceryle_segmented_group, this);
        this.mainGroup = (LinearLayout) findViewById(R.id.main_view);
        this.leftGroup = (ImageView) findViewById(R.id.left_view);
        this.rightGroup = (ImageView) findViewById(R.id.right_view);
        this.roundedLayout = (RoundedCornerLayout) findViewById(R.id.ceryle_test_group_roundedCornerLayout);
        this.rippleContainer = (LinearLayout) findViewById(R.id.rippleContainer);
        this.dividerContainer = (LinearLayout) findViewById(R.id.dividerContainer);
        View borderView = findViewById(R.id.border);
        initInterpolations();
        setShadowAttrs();
        setContainerAttrs();
        setDividerAttrs();
        this.leftBitmapParams = (FrameLayout.LayoutParams) this.leftGroup.getLayoutParams();
        this.rightBitmapParams = (FrameLayout.LayoutParams) this.rightGroup.getLayoutParams();
        this.borderParams = (LayoutParams) borderView.getLayoutParams();
        this.borderParams.setMargins(this.margin - this.borderSize, this.margin - this.borderSize, this.margin - this.borderSize, this.margin - this.borderSize);
        if (this.borderSize > 0) {
            GradientDrawable gd = new GradientDrawable();
            gd.setColor(this.borderColor);
            gd.setCornerRadius(this.radius + 3.0f);
            if (VERSION.SDK_INT >= 16) {
                borderView.setBackground(gd);
            } else {
                borderView.setBackgroundDrawable(gd);
            }
        }
    }

    private void setShadowAttrs() {
        if (this.shadow && VERSION.SDK_INT >= 21) {
            this.roundedLayout.setElevation(this.shadowElevation);
        }
        LayoutParams layoutParams = (LayoutParams) this.roundedLayout.getLayoutParams();
        if (this.shadowMargin != -1) {
            layoutParams.setMargins(this.shadowMargin, this.shadowMargin, this.shadowMargin, this.shadowMargin);
            this.margin = this.shadowMargin;
        } else {
            layoutParams.setMargins(this.shadowMarginLeft, this.shadowMarginTop, this.shadowMarginRight, this.shadowMarginBottom);
            this.margin = this.shadowMarginLeft + this.shadowMarginRight;
        }
        if (this.margin < 1 && this.borderSize > 0) {
            layoutParams.setMargins(this.borderSize, this.borderSize, this.borderSize, this.borderSize);
            this.margin = this.borderSize;
        }
        this.roundedLayout.setRadius(this.radius);
    }

    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (changed) {
            int i;
            float buttonHeight = (float) (getHeight() - (this.margin * 2));
            for (i = 0; i < this.btnAttrs.size(); i++) {
                ((ButtonAttributes) this.btnAttrs.get(i)).getRippleView().getLayoutParams().height = (int) buttonHeight;
            }
            int w1 = 0;
            int w2 = 0;
            if (this.hasWidth) {
                refreshButtonsWidth();
                int pos = this.position + 1;
                for (i = 0; i < pos; i++) {
                    if (i != 0) {
                        w1 += ((ButtonAttributes) this.btnAttrs.get(i - 1)).getWidth();
                    }
                    w2 += ((ButtonAttributes) this.btnAttrs.get(i)).getWidth();
                }
            } else {
                this.buttonWidth = (float) ((int) (((float) (getWidth() - (this.margin * 2))) / ((float) this.buttons.size())));
                w1 = ((int) this.buttonWidth) * this.position;
                w2 = (int) (this.buttonWidth * ((float) (this.position + 1)));
            }
            this.leftBitmapParams.width = w1;
            this.leftBitmapParams.height = (int) buttonHeight;
            this.rightBitmapParams.width = w2;
            this.rightBitmapParams.height = (int) buttonHeight;
            this.borderParams.width = getWidth() + this.borderSize;
            this.borderParams.height = ((int) buttonHeight) + (this.borderSize * 2);
        }
    }

    private void refreshButtonsWidth() {
        for (int i = 0; i < this.btnAttrs.size(); i++) {
            ((ButtonAttributes) this.btnAttrs.get(i)).setWidth(((SegmentedButton) this.buttons.get(i)).getWidth());
        }
    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.sizeChanged = true;
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (this.layoutSize == 0) {
            Iterator it = this.btnAttrs.iterator();
            while (it.hasNext()) {
                ButtonAttributes b = (ButtonAttributes) it.next();
                if (b.hasWidth()) {
                    this.layoutSize += b.getWidth();
                    getLayoutParams().width = this.layoutSize;
                }
                if (b.hasWeight()) {
                    getLayoutParams().width = -1;
                    return;
                }
            }
        }
    }

    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (!isInEditMode() && this.sizeChanged) {
            updateViews();
            this.sizeChanged = false;
        }
    }

    private void setBackgroundGradient(View v) {
        GradientDrawable gd = new GradientDrawable(Orientation.LEFT_RIGHT, new int[]{-1, ViewCompat.MEASURED_STATE_MASK});
        gd.setCornerRadius(0.0f);
        if (VERSION.SDK_INT >= 16) {
            v.setBackground(gd);
        } else {
            v.setBackgroundDrawable(gd);
        }
    }

    private void setBackgroundColor(View v, Drawable d, int c) {
        if (d == null) {
            v.setBackgroundColor(c);
        } else if (VERSION.SDK_INT >= 16) {
            v.setBackground(d);
        } else {
            v.setBackgroundDrawable(d);
        }
    }

    private void setDividerAttrs() {
        if (this.hasDivider) {
            this.dividerContainer.setShowDividers(2);
            RoundHelper.makeDividerRound(this.dividerContainer, this.dividerColor, this.dividerRadius, this.dividerSize, this.dividerBackgroundDrawable);
            if (VERSION.SDK_INT >= 14) {
                this.dividerContainer.setDividerPadding(this.dividerPadding);
            }
        }
    }

    public void updateViews() {
        ArrayList<ButtonAttributes> buttonAttributes = new ArrayList();
        setBackgroundColor(this.mainGroup, this.backgroundDrawable, this.backgroundColor);
        this.leftGroup.setImageBitmap(getViewBitmap(this.mainGroup));
        Iterator it = this.buttons.iterator();
        while (it.hasNext()) {
            SegmentedButton sButton;
            Button button = (Button) it.next();
            ButtonAttributes btnAttr = new ButtonAttributes();
            btnAttr.setTextColor(button.getCurrentTextColor());
            button.setTextColor(this.selectorTextColor);
            if (button instanceof SegmentedButton) {
                sButton = (SegmentedButton) button;
                btnAttr.setTintColor(sButton.getImageTint());
                btnAttr.setTintColor(sButton.hasImageTint());
                if (this.hasSelectorImageTint) {
                    sButton.setImageTint(this.selectorImageTint);
                } else if (sButton.hasSelectorTint()) {
                    sButton.setImageTint(sButton.getSelectedImageTint());
                }
                if (sButton.hasSelectedTextColor()) {
                    sButton.setTextColor(sButton.getSelectedTextColor());
                }
            }
            buttonAttributes.add(btnAttr);
        }
        setBackgroundColor(this.mainGroup, this.selectorBackgroundDrawable, this.selectorColor);
        this.rightGroup.setImageBitmap(getViewBitmap(this.mainGroup));
        for (int i = 0; i < this.buttons.size(); i++) {
            button = (Button) this.buttons.get(i);
            button.setTextColor(((ButtonAttributes) buttonAttributes.get(i)).getTextColor());
            if (button instanceof SegmentedButton) {
                sButton = (SegmentedButton) button;
                if (((ButtonAttributes) buttonAttributes.get(i)).hasTintColor()) {
                    sButton.setImageTint(((ButtonAttributes) buttonAttributes.get(i)).getTintColor());
                } else {
                    sButton.removeImageTint();
                }
            }
        }
        setBackgroundColor(this.mainGroup, this.backgroundDrawable, this.backgroundColor);
    }

    private void toggle(int position, int duration) {
        int w1 = 0;
        int w2 = 0;
        if (this.hasWidth) {
            int pos = position + 1;
            for (int i = 0; i < pos; i++) {
                if (i != 0) {
                    w1 += ((ButtonAttributes) this.btnAttrs.get(i - 1)).getWidth();
                }
                w2 += ((ButtonAttributes) this.btnAttrs.get(i)).getWidth();
            }
            w2 -= this.dividerSize;
        } else {
            w1 = (int) (this.buttonWidth * ((float) position));
            w2 = (int) (this.buttonWidth * ((float) (position + 1)));
        }
        AnimationCollapse.expand(this.leftGroup, this.interpolatorSelector, duration, Math.max(0, w1));
        AnimationCollapse.expand(this.rightGroup, this.interpolatorSelector, duration, Math.max(0, w2));
        if (this.onClickedButtonPosition != null) {
            this.onClickedButtonPosition.onClickedButtonPosition(position);
        }
        this.position = position;
    }

    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        if (this.mainGroup == null) {
            super.addView(child, index, params);
            return;
        }
        child.setClickable(false);
        child.setFocusable(false);
        this.mainGroup.addView(child, index, params);
        ButtonAttributes buttonAttributes = new ButtonAttributes();
        if (child instanceof SegmentedButton) {
            SegmentedButton s = (SegmentedButton) child;
            if (s.hasButtonWeight()) {
                buttonAttributes.setHasWeight(true);
                buttonAttributes.setWeight(s.getButtonWeight());
                this.hasWidth = true;
            } else if (s.getButtonWidth() > 0) {
                buttonAttributes.setHasWidth(true);
                buttonAttributes.setWidth(s.getButtonWidth());
                this.hasWidth = true;
            } else {
                child.setLayoutParams(new LinearLayout.LayoutParams(0, -1, FlexItem.FLEX_SHRINK_DEFAULT));
                buttonAttributes.setHasWeight(true);
                buttonAttributes.setWeight(FlexItem.FLEX_SHRINK_DEFAULT);
            }
            this.buttons.add((SegmentedButton) child);
        } else {
            child.setLayoutParams(new LinearLayout.LayoutParams(0, -1, FlexItem.FLEX_SHRINK_DEFAULT));
            this.buttons.add((Button) child);
            buttonAttributes.setHasWeight(true);
            buttonAttributes.setWeight(FlexItem.FLEX_SHRINK_DEFAULT);
        }
        this.btnAttrs.add(buttonAttributes);
        child.setBackgroundColor(ContextCompat.getColor(getContext(), 17170445));
        initForeground(this.buttons.size() - 1);
    }

    private void initForeground(final int pos) {
        ButtonAttributes attrs = (ButtonAttributes) this.btnAttrs.get(pos);
        View rippleView = new View(getContext());
        ((ButtonAttributes) this.btnAttrs.get(pos)).setRippleView(rippleView);
        rippleView.setLayoutParams(new LinearLayout.LayoutParams(attrs.getWidth(), 0, attrs.getWeight()));
        this.rippleContainer.addView(rippleView);
        rippleView.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                SegmentedButtonGroup.this.toggle(pos, SegmentedButtonGroup.this.animateSelectorDuration);
            }
        });
        if (this.hasRippleColor) {
            RippleHelper.setRipple(rippleView, this.rippleColor);
        } else if (this.ripple) {
            RippleHelper.setSelectableItemBackground(getContext(), rippleView);
        } else {
            Iterator it = this.buttons.iterator();
            while (it.hasNext()) {
                Button button = (Button) it.next();
                if ((button instanceof SegmentedButton) && ((SegmentedButton) button).hasRippleColor()) {
                    RippleHelper.setRipple(rippleView, ((SegmentedButton) button).getRippleColor());
                }
            }
        }
        if (this.hasDivider) {
            View dividerView = new View(getContext());
            ((ButtonAttributes) this.btnAttrs.get(pos)).setDividerView(dividerView);
            dividerView.setLayoutParams(new LinearLayout.LayoutParams(attrs.getWidth() - this.dividerSize, 0, attrs.getWeight()));
            this.dividerContainer.addView(dividerView);
        }
    }

    private void setContainerAttrs() {
        if (isInEditMode()) {
            this.mainGroup.setBackgroundColor(this.backgroundColor);
        }
    }

    private void getAttributes(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.SegmentedButtonGroup);
        this.hasDivider = typedArray.hasValue(R.styleable.SegmentedButtonGroup_sbg_dividerSize);
        this.dividerSize = (int) typedArray.getDimension(R.styleable.SegmentedButtonGroup_sbg_dividerSize, 0.0f);
        this.dividerColor = typedArray.getColor(R.styleable.SegmentedButtonGroup_sbg_dividerColor, -1);
        this.dividerPadding = (int) typedArray.getDimension(R.styleable.SegmentedButtonGroup_sbg_dividerPadding, 0.0f);
        this.dividerRadius = (int) typedArray.getDimension(R.styleable.SegmentedButtonGroup_sbg_dividerRadius, 0.0f);
        this.selectorTextColor = typedArray.getColor(R.styleable.SegmentedButtonGroup_sbg_selectorTextColor, -7829368);
        this.selectorImageTint = typedArray.getColor(R.styleable.SegmentedButtonGroup_sbg_selectorImageTint, -7829368);
        this.hasSelectorImageTint = typedArray.hasValue(R.styleable.SegmentedButtonGroup_sbg_selectorImageTint);
        this.selectorColor = typedArray.getColor(R.styleable.SegmentedButtonGroup_sbg_selectorColor, -7829368);
        this.animateSelector = typedArray.getInt(R.styleable.SegmentedButtonGroup_sbg_animateSelector, 0);
        this.animateSelectorDuration = typedArray.getInt(R.styleable.SegmentedButtonGroup_sbg_animateSelectorDuration, 500);
        this.shadow = typedArray.getBoolean(R.styleable.SegmentedButtonGroup_sbg_shadow, false);
        this.shadowElevation = typedArray.getDimension(R.styleable.SegmentedButtonGroup_sbg_shadowElevation, 0.0f);
        this.shadowMargin = (int) typedArray.getDimension(R.styleable.SegmentedButtonGroup_sbg_shadowMargin, FlexItem.FLEX_BASIS_PERCENT_DEFAULT);
        this.shadowMarginTop = (int) typedArray.getDimension(R.styleable.SegmentedButtonGroup_sbg_shadowMarginTop, 0.0f);
        this.shadowMarginBottom = (int) typedArray.getDimension(R.styleable.SegmentedButtonGroup_sbg_shadowMarginBottom, 0.0f);
        this.shadowMarginLeft = (int) typedArray.getDimension(R.styleable.SegmentedButtonGroup_sbg_shadowMarginLeft, 0.0f);
        this.shadowMarginRight = (int) typedArray.getDimension(R.styleable.SegmentedButtonGroup_sbg_shadowMarginRight, 0.0f);
        this.radius = typedArray.getDimension(R.styleable.SegmentedButtonGroup_sbg_radius, 0.0f);
        this.position = typedArray.getInt(R.styleable.SegmentedButtonGroup_sbg_position, 0);
        this.backgroundColor = typedArray.getColor(R.styleable.SegmentedButtonGroup_sbg_backgroundColor, -1);
        this.ripple = typedArray.getBoolean(R.styleable.SegmentedButtonGroup_sbg_ripple, false);
        this.hasRippleColor = typedArray.hasValue(R.styleable.SegmentedButtonGroup_sbg_rippleColor);
        this.rippleColor = typedArray.getColor(R.styleable.SegmentedButtonGroup_sbg_rippleColor, -7829368);
        this.borderSize = (int) typedArray.getDimension(R.styleable.SegmentedButtonGroup_sbg_borderSize, 0.0f);
        this.borderColor = typedArray.getColor(R.styleable.SegmentedButtonGroup_sbg_borderColor, ViewCompat.MEASURED_STATE_MASK);
        this.backgroundDrawable = typedArray.getDrawable(R.styleable.SegmentedButtonGroup_sbg_backgroundDrawable);
        this.selectorBackgroundDrawable = typedArray.getDrawable(R.styleable.SegmentedButtonGroup_sbg_selectorBackgroundDrawable);
        this.dividerBackgroundDrawable = typedArray.getDrawable(R.styleable.SegmentedButtonGroup_sbg_dividerBackgroundDrawable);
        typedArray.recycle();
    }

    private void initInterpolations() {
        try {
            this.interpolatorSelector = (Interpolator) ((Class) new C05312().get(this.animateSelector)).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setOnClickedButtonPosition(OnClickedButtonPosition onClickedButtonPosition) {
        this.onClickedButtonPosition = onClickedButtonPosition;
    }

    private Bitmap getViewBitmap(View view) {
        int width = view.getMeasuredWidth();
        int height = view.getMeasuredHeight();
        view.measure(MeasureSpec.makeMeasureSpec(width, Ints.MAX_POWER_OF_TWO), MeasureSpec.makeMeasureSpec(height, Ints.MAX_POWER_OF_TWO));
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        Bitmap b = Bitmap.createBitmap(width, height, Config.ARGB_8888);
        view.draw(new Canvas(b));
        return b;
    }

    public void setPosition(final int position, final int duration) {
        this.position = position;
        post(new Runnable() {
            public void run() {
                SegmentedButtonGroup.this.toggle(position, duration);
            }
        });
    }

    public void setPosition(final int position, final boolean withAnimation) {
        this.position = position;
        post(new Runnable() {
            public void run() {
                if (withAnimation) {
                    SegmentedButtonGroup.this.toggle(position, SegmentedButtonGroup.this.animateSelectorDuration);
                } else {
                    SegmentedButtonGroup.this.toggle(position, 0);
                }
            }
        });
    }

    public void setSelectorColor(int selectorColor) {
        this.selectorColor = selectorColor;
    }

    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public void setSelectorImageTint(int selectorImageTint) {
        this.selectorImageTint = selectorImageTint;
    }

    public void setSelectorTextColor(int selectorTextColor) {
        this.selectorTextColor = selectorTextColor;
    }

    public void setRippleColor(int rippleColor) {
        this.rippleColor = rippleColor;
    }

    public void setShadowElevation(float shadowElevation) {
        this.shadowElevation = shadowElevation;
    }

    public void setShadowMargin(int shadowMargin) {
        this.shadowMargin = shadowMargin;
    }

    public void setShadowMarginTop(int shadowMarginTop) {
        this.shadowMarginTop = shadowMarginTop;
    }

    public void setShadowMarginBottom(int shadowMarginBottom) {
        this.shadowMarginBottom = shadowMarginBottom;
    }

    public void setShadowMarginLeft(int shadowMarginLeft) {
        this.shadowMarginLeft = shadowMarginLeft;
    }

    public void setShadowMarginRight(int shadowMarginRight) {
        this.shadowMarginRight = shadowMarginRight;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public void setDividerPadding(int dividerPadding) {
        this.dividerPadding = dividerPadding;
    }

    public void setShadow(boolean shadow) {
        this.shadow = shadow;
    }

    public void setHasDivider(boolean hasDivider) {
        this.hasDivider = hasDivider;
    }

    public void setHasRippleColor(boolean hasRippleColor) {
        this.hasRippleColor = hasRippleColor;
    }

    public void setRipple(boolean ripple) {
        this.ripple = ripple;
    }

    public void setMargin(int margin) {
        this.margin = margin;
    }

    public void setSelectorAnimationDuration(int animateSelectorDuration) {
        this.animateSelectorDuration = animateSelectorDuration;
    }

    public void setSelectorAnimation(int animateSelector) {
        this.animateSelector = animateSelector;
    }

    public void setInterpolatorSelector(Interpolator interpolatorSelector) {
        this.interpolatorSelector = interpolatorSelector;
    }

    public void setDividerColor(int dividerColor) {
        this.dividerColor = dividerColor;
        RoundHelper.makeDividerRound(this.dividerContainer, dividerColor, this.dividerRadius, this.dividerSize, this.dividerBackgroundDrawable);
    }

    public void setDividerSize(int dividerSize) {
        this.dividerSize = dividerSize;
        RoundHelper.makeDividerRound(this.dividerContainer, this.dividerColor, this.dividerRadius, dividerSize, this.dividerBackgroundDrawable);
    }

    public void setDividerRadius(int dividerRadius) {
        this.dividerRadius = dividerRadius;
        RoundHelper.makeDividerRound(this.dividerContainer, this.dividerColor, dividerRadius, this.dividerSize, this.dividerBackgroundDrawable);
    }

    public float getShadowMarginTop() {
        return (float) this.shadowMarginTop;
    }

    public int getSelectorTextColor() {
        return this.selectorTextColor;
    }

    public float getShadowElevation() {
        return this.shadowElevation;
    }

    public float getShadowMargin() {
        return (float) this.shadowMargin;
    }

    public float getShadowMarginBottom() {
        return (float) this.shadowMarginBottom;
    }

    public int getDividerSize() {
        return this.dividerSize;
    }

    public int getRippleColor() {
        return this.rippleColor;
    }

    public int getSelectorColor() {
        return this.selectorColor;
    }

    public int getSelectorAnimation() {
        return this.animateSelector;
    }

    public int getSelectorAnimationDuration() {
        return this.animateSelectorDuration;
    }

    public int getPosition() {
        return this.position;
    }

    public int getBackgroundColor() {
        return this.backgroundColor;
    }

    public int getDividerColor() {
        return this.dividerColor;
    }

    public int getSelectorImageTint() {
        return this.selectorImageTint;
    }

    public float getShadowMarginLeft() {
        return (float) this.shadowMarginLeft;
    }

    public float getShadowMarginRight() {
        return (float) this.shadowMarginRight;
    }

    public float getRadius() {
        return this.radius;
    }

    public int getDividerPadding() {
        return this.dividerPadding;
    }

    public float getDividerRadius() {
        return (float) this.dividerRadius;
    }

    public boolean isShadow() {
        return this.shadow;
    }

    public boolean isHasDivider() {
        return this.hasDivider;
    }

    public boolean isHasRippleColor() {
        return this.hasRippleColor;
    }

    public boolean isRipple() {
        return this.ripple;
    }

    public Interpolator getInterpolatorSelector() {
        return this.interpolatorSelector;
    }

    public int getMargin() {
        return this.margin;
    }

    public Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("state", super.onSaveInstanceState());
        bundle.putInt("position", this.position);
        return bundle;
    }

    public void onRestoreInstanceState(Parcelable state) {
        if (state instanceof Bundle) {
            Bundle bundle = (Bundle) state;
            this.position = bundle.getInt("position");
            state = bundle.getParcelable("state");
        }
        super.onRestoreInstanceState(state);
    }
}
