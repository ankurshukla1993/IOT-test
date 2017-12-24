package com.appeaser.sublimepickerlibrary.recurrencepicker;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.v4.view.ViewCompat;
import android.text.TextUtils;
import android.text.format.Time;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.appeaser.sublimepickerlibrary.C0563R;
import com.appeaser.sublimepickerlibrary.recurrencepicker.RecurrenceOptionCreator.OnRecurrenceSetListener;
import com.appeaser.sublimepickerlibrary.utilities.SUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TimeZone;

public class SublimeRecurrencePicker extends FrameLayout implements OnClickListener {
    LinearLayout llRecurrenceOptionsMenu;
    OnRepeatOptionSetListener mCallback;
    Drawable mCheckmarkDrawable;
    RecurrenceOption mCurrentRecurrenceOption;
    CurrentView mCurrentView;
    long mCurrentlyChosenTime;
    OnRecurrenceSetListener mOnRecurrenceSetListener;
    int mPressedStateColor;
    RecurrenceOptionCreator mRecurrenceOptionCreator;
    String mRecurrenceRule;
    ArrayList<TextView> mRepeatOptionTextViews;
    int mSelectedOptionDrawablePadding;
    int mSelectedStateTextColor;
    int mUnselectedStateTextColor;

    public interface OnRepeatOptionSetListener {
        void onDone();

        void onRepeatOptionSet(RecurrenceOption recurrenceOption, String str);
    }

    class C05952 implements OnRecurrenceSetListener {
        C05952() {
        }

        public void onRecurrenceSet(String rrule) {
            SublimeRecurrencePicker.this.mRecurrenceRule = rrule;
            SublimeRecurrencePicker.this.mCurrentRecurrenceOption = RecurrenceOption.CUSTOM;
            SublimeRecurrencePicker.this.mCurrentView = CurrentView.RECURRENCE_OPTIONS_MENU;
            if (SublimeRecurrencePicker.this.mCallback != null) {
                SublimeRecurrencePicker.this.mCallback.onRepeatOptionSet(RecurrenceOption.CUSTOM, rrule);
            }
        }

        public void onCancelled() {
            SublimeRecurrencePicker.this.mCurrentView = CurrentView.RECURRENCE_OPTIONS_MENU;
            SublimeRecurrencePicker.this.updateView();
        }
    }

    private enum CurrentView {
        RECURRENCE_OPTIONS_MENU,
        RECURRENCE_CREATOR
    }

    public enum RecurrenceOption {
        DOES_NOT_REPEAT("DOES NOT REPEAT"),
        DAILY("DAILY"),
        WEEKLY("WEEKLY"),
        MONTHLY("MONTHLY"),
        YEARLY("YEARLY"),
        CUSTOM("CUSTOM...");
        
        private final String optionName;

        private RecurrenceOption(String name) {
            this.optionName = name;
        }

        public String toString() {
            return this.optionName;
        }
    }

    private static class SavedState extends BaseSavedState {
        public static final Creator<SavedState> CREATOR = new C05971();
        private final RecurrenceOption sCurrentRecurrenceOption;
        private final CurrentView sCurrentView;
        private final String sRecurrenceRule;

        static class C05971 implements Creator<SavedState> {
            C05971() {
            }

            public SavedState createFromParcel(Parcel in) {
                return new SavedState(in);
            }

            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        }

        private SavedState(Parcelable superState, CurrentView currentView, RecurrenceOption currentRecurrenceOption, String recurrenceRule) {
            super(superState);
            this.sCurrentView = currentView;
            this.sCurrentRecurrenceOption = currentRecurrenceOption;
            this.sRecurrenceRule = recurrenceRule;
        }

        private SavedState(Parcel in) {
            super(in);
            this.sCurrentView = CurrentView.valueOf(in.readString());
            this.sCurrentRecurrenceOption = RecurrenceOption.valueOf(in.readString());
            this.sRecurrenceRule = in.readString();
        }

        public void writeToParcel(Parcel dest, int flags) {
            super.writeToParcel(dest, flags);
            dest.writeString(this.sCurrentView.name());
            dest.writeString(this.sCurrentRecurrenceOption.name());
            dest.writeString(this.sRecurrenceRule);
        }

        public CurrentView getCurrentView() {
            return this.sCurrentView;
        }

        public RecurrenceOption getCurrentRepeatOption() {
            return this.sCurrentRecurrenceOption;
        }

        public String getRecurrenceRule() {
            return this.sRecurrenceRule;
        }
    }

    public SublimeRecurrencePicker(Context context) {
        this(context, null);
    }

    public SublimeRecurrencePicker(Context context, AttributeSet attrs) {
        this(context, attrs, C0563R.attr.spRecurrencePickerStyle);
    }

    public SublimeRecurrencePicker(Context context, AttributeSet attrs, int defStyleAttr) {
        super(SUtils.createThemeWrapper(context, C0563R.attr.sublimePickerStyle, C0563R.style.SublimePickerStyleLight, C0563R.attr.spRecurrencePickerStyle, C0563R.style.SublimeRecurrencePickerStyle), attrs, defStyleAttr);
        this.mCurrentView = CurrentView.RECURRENCE_OPTIONS_MENU;
        this.mOnRecurrenceSetListener = new C05952();
        initializeLayout();
    }

    @TargetApi(21)
    public SublimeRecurrencePicker(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(SUtils.createThemeWrapper(context, C0563R.attr.sublimePickerStyle, C0563R.style.SublimePickerStyleLight, C0563R.attr.spRecurrencePickerStyle, C0563R.style.SublimeRecurrencePickerStyle), attrs, defStyleAttr, defStyleRes);
        this.mCurrentView = CurrentView.RECURRENCE_OPTIONS_MENU;
        this.mOnRecurrenceSetListener = new C05952();
        initializeLayout();
    }

    void initializeLayout() {
        Context context = getContext();
        LayoutInflater.from(context).inflate(C0563R.layout.sublime_recurrence_picker, this);
        this.llRecurrenceOptionsMenu = (LinearLayout) findViewById(C0563R.id.llRecurrenceOptionsMenu);
        this.mRecurrenceOptionCreator = (RecurrenceOptionCreator) findViewById(C0563R.id.recurrenceOptionCreator);
        TextView tvRecurrenceHeading = (TextView) findViewById(C0563R.id.tvHeading);
        this.mSelectedOptionDrawablePadding = context.getResources().getDimensionPixelSize(C0563R.dimen.selected_recurrence_option_drawable_padding);
        TypedArray a = context.obtainStyledAttributes(C0563R.styleable.SublimeRecurrencePicker);
        try {
            int headingBgColor = a.getColor(C0563R.styleable.SublimeRecurrencePicker_spHeaderBackground, SUtils.COLOR_ACCENT);
            int pickerBgColor = a.getColor(C0563R.styleable.SublimeRecurrencePicker_spPickerBackground, SUtils.COLOR_BACKGROUND);
            if (pickerBgColor != 0) {
                SUtils.setViewBackground(this, pickerBgColor, 15);
            }
            SUtils.setViewBackground(tvRecurrenceHeading, headingBgColor, 3);
            this.mSelectedStateTextColor = a.getColor(C0563R.styleable.SublimeRecurrencePicker_spSelectedOptionTextColor, SUtils.COLOR_ACCENT);
            this.mUnselectedStateTextColor = a.getColor(C0563R.styleable.SublimeRecurrencePicker_spUnselectedOptionsTextColor, SUtils.COLOR_TEXT_PRIMARY);
            this.mPressedStateColor = a.getColor(C0563R.styleable.SublimeRecurrencePicker_spPressedOptionBgColor, SUtils.COLOR_CONTROL_HIGHLIGHT);
            this.mCheckmarkDrawable = a.getDrawable(C0563R.styleable.SublimeRecurrencePicker_spSelectedOptionDrawable);
            if (this.mCheckmarkDrawable == null) {
                this.mCheckmarkDrawable = context.getResources().getDrawable(C0563R.drawable.checkmark_medium_ff);
            }
            if (this.mCheckmarkDrawable != null) {
                this.mCheckmarkDrawable.setColorFilter(this.mSelectedStateTextColor, Mode.MULTIPLY);
            }
            a.recycle();
            this.mRepeatOptionTextViews = new ArrayList();
            this.mRepeatOptionTextViews.add((TextView) findViewById(C0563R.id.tvChosenCustomOption));
            this.mRepeatOptionTextViews.add((TextView) findViewById(C0563R.id.tvDoesNotRepeat));
            this.mRepeatOptionTextViews.add((TextView) findViewById(C0563R.id.tvDaily));
            this.mRepeatOptionTextViews.add((TextView) findViewById(C0563R.id.tvWeekly));
            this.mRepeatOptionTextViews.add((TextView) findViewById(C0563R.id.tvMonthly));
            this.mRepeatOptionTextViews.add((TextView) findViewById(C0563R.id.tvYearly));
            this.mRepeatOptionTextViews.add((TextView) findViewById(C0563R.id.tvCustom));
            Iterator it = this.mRepeatOptionTextViews.iterator();
            while (it.hasNext()) {
                SUtils.setViewBackground((View) it.next(), createOptionBg(this.mPressedStateColor));
            }
        } catch (Throwable th) {
            a.recycle();
        }
    }

    public void initializeData(OnRepeatOptionSetListener callback, RecurrenceOption initialOption, String recurrenceRule, long currentlyChosenTime) {
        this.mCallback = callback;
        this.mRecurrenceRule = recurrenceRule;
        this.mCurrentlyChosenTime = currentlyChosenTime;
        this.mCurrentRecurrenceOption = initialOption;
        this.mRecurrenceOptionCreator.initializeData(this.mCurrentlyChosenTime, null, this.mRecurrenceRule, this.mOnRecurrenceSetListener);
    }

    public void updateView() {
        if (this.mCurrentView == CurrentView.RECURRENCE_OPTIONS_MENU) {
            this.mRecurrenceOptionCreator.setVisibility(8);
            this.llRecurrenceOptionsMenu.setVisibility(0);
            updateFlowLayout(this.mCurrentRecurrenceOption);
            final ScrollView scrollView = (ScrollView) this.llRecurrenceOptionsMenu.findViewById(C0563R.id.svRecurrenceOptionsMenu);
            this.llRecurrenceOptionsMenu.post(new Runnable() {
                public void run() {
                    if (scrollView.getScrollY() != 0) {
                        scrollView.fullScroll(33);
                    }
                }
            });
        } else if (this.mCurrentView == CurrentView.RECURRENCE_CREATOR) {
            this.llRecurrenceOptionsMenu.setVisibility(8);
            this.mRecurrenceOptionCreator.setVisibility(0);
        }
    }

    void updateFlowLayout(RecurrenceOption recurrenceOption) {
        int viewIdToSelect;
        switch (recurrenceOption) {
            case DOES_NOT_REPEAT:
                viewIdToSelect = C0563R.id.tvDoesNotRepeat;
                break;
            case DAILY:
                viewIdToSelect = C0563R.id.tvDaily;
                break;
            case WEEKLY:
                viewIdToSelect = C0563R.id.tvWeekly;
                break;
            case MONTHLY:
                viewIdToSelect = C0563R.id.tvMonthly;
                break;
            case YEARLY:
                viewIdToSelect = C0563R.id.tvYearly;
                break;
            case CUSTOM:
                viewIdToSelect = C0563R.id.tvChosenCustomOption;
                break;
            default:
                viewIdToSelect = C0563R.id.tvDoesNotRepeat;
                break;
        }
        Iterator it = this.mRepeatOptionTextViews.iterator();
        while (it.hasNext()) {
            TextView tv = (TextView) it.next();
            tv.setOnClickListener(this);
            if (tv.getId() == C0563R.id.tvChosenCustomOption) {
                if (TextUtils.isEmpty(this.mRecurrenceRule)) {
                    tv.setVisibility(8);
                } else {
                    EventRecurrence eventRecurrence = new EventRecurrence();
                    eventRecurrence.parse(this.mRecurrenceRule);
                    Time startDate = new Time(TimeZone.getDefault().getID());
                    startDate.set(this.mCurrentlyChosenTime);
                    eventRecurrence.setStartDate(startDate);
                    tv.setVisibility(0);
                    tv.setText(EventRecurrenceFormatter.getRepeatString(getContext(), getContext().getResources(), eventRecurrence, true));
                }
            }
            if (tv.getId() == viewIdToSelect) {
                tv.setCompoundDrawablesWithIntrinsicBounds(null, null, this.mCheckmarkDrawable, null);
                tv.setCompoundDrawablePadding(this.mSelectedOptionDrawablePadding);
                tv.setTextColor(this.mSelectedStateTextColor);
            } else {
                tv.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
                tv.setTextColor(this.mUnselectedStateTextColor);
            }
        }
    }

    public void onClick(View v) {
        if (v.getId() == C0563R.id.tvChosenCustomOption) {
            this.mCurrentRecurrenceOption = RecurrenceOption.CUSTOM;
            if (this.mCallback != null) {
                this.mCallback.onRepeatOptionSet(RecurrenceOption.CUSTOM, this.mRecurrenceRule);
                return;
            }
            return;
        }
        if (v.getId() == C0563R.id.tvDoesNotRepeat) {
            this.mCurrentRecurrenceOption = RecurrenceOption.DOES_NOT_REPEAT;
        } else if (v.getId() == C0563R.id.tvDaily) {
            this.mCurrentRecurrenceOption = RecurrenceOption.DAILY;
        } else if (v.getId() == C0563R.id.tvWeekly) {
            this.mCurrentRecurrenceOption = RecurrenceOption.WEEKLY;
        } else if (v.getId() == C0563R.id.tvMonthly) {
            this.mCurrentRecurrenceOption = RecurrenceOption.MONTHLY;
        } else if (v.getId() == C0563R.id.tvYearly) {
            this.mCurrentRecurrenceOption = RecurrenceOption.YEARLY;
        } else if (v.getId() == C0563R.id.tvCustom) {
            this.mCurrentView = CurrentView.RECURRENCE_CREATOR;
            updateView();
            return;
        } else {
            this.mCurrentRecurrenceOption = RecurrenceOption.DOES_NOT_REPEAT;
        }
        if (this.mCallback != null) {
            this.mCallback.onRepeatOptionSet(this.mCurrentRecurrenceOption, null);
        }
    }

    Drawable createOptionBg(int pressedBgColor) {
        if (SUtils.isApi_21_OrHigher()) {
            return createRippleDrawableForOption(pressedBgColor);
        }
        return createStateListDrawableForOption(pressedBgColor);
    }

    private Drawable createStateListDrawableForOption(int pressedBgColor) {
        StateListDrawable sld = new StateListDrawable();
        sld.addState(new int[]{16842919}, new ColorDrawable(pressedBgColor));
        sld.addState(new int[0], new ColorDrawable(0));
        return sld;
    }

    @TargetApi(21)
    private Drawable createRippleDrawableForOption(int pressedBgColor) {
        return new RippleDrawable(ColorStateList.valueOf(pressedBgColor), null, new ColorDrawable(ViewCompat.MEASURED_STATE_MASK));
    }

    protected Parcelable onSaveInstanceState() {
        return new SavedState(super.onSaveInstanceState(), this.mCurrentView, this.mCurrentRecurrenceOption, this.mRecurrenceRule);
    }

    protected void onRestoreInstanceState(Parcelable state) {
        BaseSavedState bss = (BaseSavedState) state;
        super.onRestoreInstanceState(bss.getSuperState());
        SavedState ss = (SavedState) bss;
        this.mCurrentView = ss.getCurrentView();
        this.mCurrentRecurrenceOption = ss.getCurrentRepeatOption();
        this.mRecurrenceRule = ss.getRecurrenceRule();
        updateView();
    }
}
