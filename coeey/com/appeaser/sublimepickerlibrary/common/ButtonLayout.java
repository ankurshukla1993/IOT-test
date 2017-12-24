package com.appeaser.sublimepickerlibrary.common;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.PorterDuff.Mode;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.appeaser.sublimepickerlibrary.C0563R;
import com.appeaser.sublimepickerlibrary.common.ButtonHandler.Callback;
import com.appeaser.sublimepickerlibrary.utilities.SUtils;

public class ButtonLayout extends LinearLayout implements OnClickListener {
    int mButtonBarBgColor;
    Callback mCallback;
    int mDisabledAlpha;
    int mIconOverlayColor;
    View mNegativeButton;
    View mPositiveButton;
    Button mSwitcherButton;

    public ButtonLayout(Context context) {
        this(context, null);
    }

    public ButtonLayout(Context context, AttributeSet attrs) {
        this(context, attrs, C0563R.attr.spButtonLayoutStyle);
    }

    public ButtonLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(SUtils.createThemeWrapper(context, C0563R.attr.sublimePickerStyle, C0563R.style.SublimePickerStyleLight, C0563R.attr.spButtonLayoutStyle, C0563R.style.ButtonLayoutStyle), attrs, defStyleAttr);
        initialize();
    }

    @TargetApi(21)
    public ButtonLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(SUtils.createThemeWrapper(context, C0563R.attr.sublimePickerStyle, C0563R.style.SublimePickerStyleLight, C0563R.attr.spButtonLayoutStyle, C0563R.style.ButtonLayoutStyle), attrs, defStyleAttr, defStyleRes);
        initialize();
    }

    void initialize() {
        Context context = getContext();
        Resources res = getResources();
        TypedArray a = context.obtainStyledAttributes(C0563R.styleable.ButtonLayout);
        if (SUtils.isApi_17_OrHigher()) {
            setLayoutDirection(3);
        }
        setOrientation(0);
        setGravity(80);
        setPadding(res.getDimensionPixelSize(C0563R.dimen.sp_button_bar_padding_start), res.getDimensionPixelSize(C0563R.dimen.sp_button_bar_padding_top), res.getDimensionPixelSize(C0563R.dimen.sp_button_bar_padding_end), res.getDimensionPixelSize(C0563R.dimen.sp_button_bar_padding_bottom));
        ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(C0563R.layout.sublime_button_panel_layout, this, true);
        this.mSwitcherButton = (Button) findViewById(C0563R.id.buttonSwitcher);
        Button bPositive = (Button) findViewById(C0563R.id.buttonPositive);
        Button bNegative = (Button) findViewById(C0563R.id.buttonNegative);
        ImageView ivPositive = (ImageView) findViewById(C0563R.id.imageViewPositive);
        ImageView ivNegative = (ImageView) findViewById(C0563R.id.imageViewNegative);
        try {
            TypedValue typedValueDisabledAlpha = new TypedValue();
            getContext().getTheme().resolveAttribute(16842803, typedValueDisabledAlpha, true);
            this.mDisabledAlpha = typedValueDisabledAlpha.type == 4 ? (int) (typedValueDisabledAlpha.getFloat() * 255.0f) : 122;
            int presentation = a.getInt(C0563R.styleable.ButtonLayout_spPresentation, 0);
            int bgColor = a.getColor(C0563R.styleable.ButtonLayout_spButtonBgColor, SUtils.COLOR_BUTTON_NORMAL);
            int pressedBgColor = a.getColor(C0563R.styleable.ButtonLayout_spButtonPressedBgColor, SUtils.COLOR_CONTROL_HIGHLIGHT);
            this.mButtonBarBgColor = a.getColor(C0563R.styleable.ButtonLayout_spButtonBarBgColor, 0);
            SUtils.setViewBackground(this.mSwitcherButton, SUtils.createButtonBg(context, bgColor, pressedBgColor));
            setBackgroundColor(this.mButtonBarBgColor);
            if (presentation == 0) {
                bPositive.setVisibility(0);
                bNegative.setVisibility(0);
                bPositive.setText(res.getString(C0563R.string.ok));
                bNegative.setText(res.getString(C0563R.string.cancel));
                SUtils.setViewBackground(bPositive, SUtils.createButtonBg(context, bgColor, pressedBgColor));
                SUtils.setViewBackground(bNegative, SUtils.createButtonBg(context, bgColor, pressedBgColor));
                this.mPositiveButton = bPositive;
                this.mNegativeButton = bNegative;
            } else {
                ivPositive.setVisibility(0);
                ivNegative.setVisibility(0);
                this.mIconOverlayColor = a.getColor(C0563R.styleable.ButtonLayout_spIconColor, SUtils.COLOR_ACCENT);
                ivPositive.setColorFilter(this.mIconOverlayColor, Mode.MULTIPLY);
                ivNegative.setColorFilter(this.mIconOverlayColor, Mode.MULTIPLY);
                SUtils.setViewBackground(ivPositive, SUtils.createImageViewBg(bgColor, pressedBgColor));
                SUtils.setViewBackground(ivNegative, SUtils.createImageViewBg(bgColor, pressedBgColor));
                this.mPositiveButton = ivPositive;
                this.mNegativeButton = ivNegative;
            }
            a.recycle();
            this.mPositiveButton.setOnClickListener(this);
            this.mNegativeButton.setOnClickListener(this);
            this.mSwitcherButton.setOnClickListener(this);
        } catch (Throwable th) {
            a.recycle();
        }
    }

    public void applyOptions(boolean switcherRequired, @NonNull Callback callback) {
        this.mSwitcherButton.setVisibility(switcherRequired ? 0 : 8);
        this.mCallback = callback;
    }

    public boolean isSwitcherButtonEnabled() {
        return this.mSwitcherButton.getVisibility() == 0;
    }

    public void updateSwitcherText(CharSequence text) {
        this.mSwitcherButton.setText(text);
    }

    public void updateValidity(boolean valid) {
        this.mPositiveButton.setEnabled(valid);
        if (this.mPositiveButton instanceof ImageView) {
            int color = this.mIconOverlayColor;
            if (!valid) {
                color = (this.mDisabledAlpha << 24) | (this.mIconOverlayColor & 16777215);
            }
            ((ImageView) this.mPositiveButton).setColorFilter(color, Mode.MULTIPLY);
        }
    }

    public void onClick(View v) {
        if (v == this.mPositiveButton) {
            this.mCallback.onOkay();
        } else if (v == this.mNegativeButton) {
            this.mCallback.onCancel();
        } else if (v == this.mSwitcherButton) {
            this.mCallback.onSwitch();
        }
    }
}
