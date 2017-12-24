package com.appeaser.sublimepickerlibrary.common;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.PorterDuff.Mode;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import com.appeaser.sublimepickerlibrary.C0563R;
import com.appeaser.sublimepickerlibrary.SublimePicker;
import com.appeaser.sublimepickerlibrary.helpers.SublimeOptions.Picker;
import com.appeaser.sublimepickerlibrary.utilities.SUtils;

public class ButtonHandler implements OnClickListener {
    int mButtonBarBgColor;
    Callback mCallback;
    int mDisabledAlpha;
    int mIconOverlayColor;
    private boolean mIsInLandscapeMode;
    View mNegativeButtonDP;
    View mNegativeButtonTP;
    private ButtonLayout mPortraitButtonHandler;
    View mPositiveButtonDP;
    View mPositiveButtonTP;
    Button mSwitcherButtonDP;
    Button mSwitcherButtonTP;

    public interface Callback {
        void onCancel();

        void onOkay();

        void onSwitch();
    }

    public ButtonHandler(@NonNull SublimePicker sublimePicker) {
        this.mIsInLandscapeMode = sublimePicker.getContext().getResources().getConfiguration().orientation == 2;
        if (this.mIsInLandscapeMode) {
            initializeForLandscape(sublimePicker);
        } else {
            this.mPortraitButtonHandler = (ButtonLayout) sublimePicker.findViewById(C0563R.id.button_layout);
        }
    }

    private void initializeForLandscape(SublimePicker sublimeMaterialPicker) {
        Context context = SUtils.createThemeWrapper(sublimeMaterialPicker.getContext(), C0563R.attr.sublimePickerStyle, C0563R.style.SublimePickerStyleLight, C0563R.attr.spButtonLayoutStyle, C0563R.style.ButtonLayoutStyle);
        Resources res = context.getResources();
        TypedArray a = context.obtainStyledAttributes(C0563R.styleable.ButtonLayout);
        this.mSwitcherButtonDP = (Button) sublimeMaterialPicker.findViewById(C0563R.id.buttonSwitcherDP);
        this.mSwitcherButtonTP = (Button) sublimeMaterialPicker.findViewById(C0563R.id.buttonSwitcherTP);
        Button bPositiveDP = (Button) sublimeMaterialPicker.findViewById(C0563R.id.buttonPositiveDP);
        Button bPositiveTP = (Button) sublimeMaterialPicker.findViewById(C0563R.id.buttonPositiveTP);
        Button bNegativeDP = (Button) sublimeMaterialPicker.findViewById(C0563R.id.buttonNegativeDP);
        Button bNegativeTP = (Button) sublimeMaterialPicker.findViewById(C0563R.id.buttonNegativeTP);
        ImageView ivPositiveDP = (ImageView) sublimeMaterialPicker.findViewById(C0563R.id.imageViewPositiveDP);
        View ivPositiveTP = (ImageView) sublimeMaterialPicker.findViewById(C0563R.id.imageViewPositiveTP);
        ImageView ivNegativeDP = (ImageView) sublimeMaterialPicker.findViewById(C0563R.id.imageViewNegativeDP);
        ImageView ivNegativeTP = (ImageView) sublimeMaterialPicker.findViewById(C0563R.id.imageViewNegativeTP);
        try {
            TypedValue typedValueDisabledAlpha = new TypedValue();
            context.getTheme().resolveAttribute(16842803, typedValueDisabledAlpha, true);
            this.mDisabledAlpha = typedValueDisabledAlpha.type == 4 ? (int) (typedValueDisabledAlpha.getFloat() * 255.0f) : 122;
            int presentation = a.getInt(C0563R.styleable.ButtonLayout_spPresentation, 0);
            int bgColor = a.getColor(C0563R.styleable.ButtonLayout_spButtonBgColor, SUtils.COLOR_BUTTON_NORMAL);
            int pressedBgColor = a.getColor(C0563R.styleable.ButtonLayout_spButtonPressedBgColor, SUtils.COLOR_CONTROL_HIGHLIGHT);
            this.mButtonBarBgColor = a.getColor(C0563R.styleable.ButtonLayout_spButtonBarBgColor, 0);
            int buttonInvertedBgColor = a.getColor(C0563R.styleable.ButtonLayout_spButtonInvertedBgColor, SUtils.COLOR_ACCENT);
            int buttonPressedInvertedBgColor = a.getColor(C0563R.styleable.ButtonLayout_spButtonPressedInvertedBgColor, ContextCompat.getColor(context, C0563R.color.sp_ripple_material_dark));
            SUtils.setViewBackground(this.mSwitcherButtonDP, SUtils.createButtonBg(context, buttonInvertedBgColor, buttonPressedInvertedBgColor));
            SUtils.setViewBackground(this.mSwitcherButtonTP, SUtils.createButtonBg(context, buttonInvertedBgColor, buttonPressedInvertedBgColor));
            if (presentation == 0) {
                bPositiveDP.setVisibility(0);
                bPositiveTP.setVisibility(0);
                bNegativeDP.setVisibility(0);
                bNegativeTP.setVisibility(0);
                bPositiveDP.setText(res.getString(C0563R.string.ok));
                bPositiveTP.setText(res.getString(C0563R.string.ok));
                bNegativeDP.setText(res.getString(C0563R.string.cancel));
                bNegativeTP.setText(res.getString(C0563R.string.cancel));
                SUtils.setViewBackground(bPositiveDP, SUtils.createButtonBg(context, bgColor, pressedBgColor));
                SUtils.setViewBackground(bPositiveTP, SUtils.createButtonBg(context, bgColor, pressedBgColor));
                SUtils.setViewBackground(bNegativeDP, SUtils.createButtonBg(context, bgColor, pressedBgColor));
                SUtils.setViewBackground(bNegativeTP, SUtils.createButtonBg(context, bgColor, pressedBgColor));
                this.mPositiveButtonDP = bPositiveDP;
                this.mPositiveButtonTP = bPositiveTP;
                this.mNegativeButtonDP = bNegativeDP;
                this.mNegativeButtonTP = bNegativeTP;
            } else {
                ivPositiveDP.setVisibility(0);
                ivPositiveTP.setVisibility(0);
                ivNegativeDP.setVisibility(0);
                ivNegativeTP.setVisibility(0);
                this.mIconOverlayColor = a.getColor(C0563R.styleable.ButtonLayout_spIconColor, SUtils.COLOR_ACCENT);
                ivPositiveDP.setColorFilter(this.mIconOverlayColor, Mode.MULTIPLY);
                ivPositiveTP.setColorFilter(this.mIconOverlayColor, Mode.MULTIPLY);
                ivNegativeDP.setColorFilter(this.mIconOverlayColor, Mode.MULTIPLY);
                ivNegativeTP.setColorFilter(this.mIconOverlayColor, Mode.MULTIPLY);
                SUtils.setViewBackground(ivPositiveDP, SUtils.createImageViewBg(bgColor, pressedBgColor));
                SUtils.setViewBackground(ivPositiveTP, SUtils.createImageViewBg(bgColor, pressedBgColor));
                SUtils.setViewBackground(ivNegativeDP, SUtils.createImageViewBg(bgColor, pressedBgColor));
                SUtils.setViewBackground(ivNegativeTP, SUtils.createImageViewBg(bgColor, pressedBgColor));
                this.mPositiveButtonDP = ivPositiveDP;
                this.mPositiveButtonTP = ivPositiveTP;
                this.mNegativeButtonDP = ivNegativeDP;
                this.mNegativeButtonTP = ivNegativeTP;
            }
            a.recycle();
            this.mPositiveButtonDP.setOnClickListener(this);
            this.mPositiveButtonTP.setOnClickListener(this);
            this.mNegativeButtonDP.setOnClickListener(this);
            this.mNegativeButtonTP.setOnClickListener(this);
            this.mSwitcherButtonDP.setOnClickListener(this);
            this.mSwitcherButtonTP.setOnClickListener(this);
        } catch (Throwable th) {
            a.recycle();
        }
    }

    public void applyOptions(boolean switcherRequired, @NonNull Callback callback) {
        int i = 0;
        this.mCallback = callback;
        if (this.mIsInLandscapeMode) {
            this.mSwitcherButtonDP.setVisibility(switcherRequired ? 0 : 8);
            Button button = this.mSwitcherButtonTP;
            if (!switcherRequired) {
                i = 8;
            }
            button.setVisibility(i);
            return;
        }
        this.mPortraitButtonHandler.applyOptions(switcherRequired, callback);
    }

    public boolean isSwitcherButtonEnabled() {
        if (this.mIsInLandscapeMode) {
            return this.mSwitcherButtonDP.getVisibility() == 0 || this.mSwitcherButtonTP.getVisibility() == 0;
        } else {
            return this.mPortraitButtonHandler.isSwitcherButtonEnabled();
        }
    }

    public void updateSwitcherText(@NonNull Picker displayedPicker, CharSequence text) {
        if (!this.mIsInLandscapeMode) {
            this.mPortraitButtonHandler.updateSwitcherText(text);
        } else if (displayedPicker == Picker.DATE_PICKER) {
            this.mSwitcherButtonDP.setText(text);
        } else if (displayedPicker == Picker.TIME_PICKER) {
            this.mSwitcherButtonTP.setText(text);
        }
    }

    public void updateValidity(boolean valid) {
        if (this.mIsInLandscapeMode) {
            this.mPositiveButtonDP.setEnabled(valid);
            this.mPositiveButtonTP.setEnabled(valid);
            if (this.mPositiveButtonDP instanceof ImageView) {
                int color = this.mIconOverlayColor;
                if (!valid) {
                    color = (this.mDisabledAlpha << 24) | (this.mIconOverlayColor & 16777215);
                }
                ((ImageView) this.mPositiveButtonDP).setColorFilter(color, Mode.MULTIPLY);
                ((ImageView) this.mPositiveButtonTP).setColorFilter(color, Mode.MULTIPLY);
                return;
            }
            return;
        }
        this.mPortraitButtonHandler.updateValidity(valid);
    }

    public void onClick(View v) {
        if (v == this.mPositiveButtonDP || v == this.mPositiveButtonTP) {
            this.mCallback.onOkay();
        } else if (v == this.mNegativeButtonDP || v == this.mNegativeButtonTP) {
            this.mCallback.onCancel();
        } else if (v == this.mSwitcherButtonDP || v == this.mSwitcherButtonTP) {
            this.mCallback.onSwitch();
        }
    }
}
