package com.appeaser.sublimepickerlibrary.datepicker;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Layout.Alignment;
import android.text.SpannableString;
import android.text.format.DateFormat;
import android.text.format.DateUtils;
import android.text.style.AlignmentSpan.Standard;
import android.text.style.RelativeSizeSpan;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewAnimator;
import com.appeaser.sublimepickerlibrary.C0563R;
import com.appeaser.sublimepickerlibrary.common.DateTimePatternHelper;
import com.appeaser.sublimepickerlibrary.datepicker.DayPickerView.ProxyDaySelectionEventListener;
import com.appeaser.sublimepickerlibrary.datepicker.SelectedDate.Type;
import com.appeaser.sublimepickerlibrary.datepicker.YearPickerView.OnYearSelectedListener;
import com.appeaser.sublimepickerlibrary.utilities.AccessibilityUtils;
import com.appeaser.sublimepickerlibrary.utilities.SUtils;
import com.appeaser.sublimepickerlibrary.utilities.TextColorHelper;
import com.flipboard.bottomsheet.BaseViewTransformer;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class SublimeDatePicker extends FrameLayout {
    private static final int DEFAULT_END_YEAR = 2100;
    private static final int DEFAULT_START_YEAR = 1900;
    private static final int RANGE_ACTIVATED_END = 2;
    private static final int RANGE_ACTIVATED_NONE = 0;
    private static final int RANGE_ACTIVATED_START = 1;
    private static final String TAG = SublimeDatePicker.class.getSimpleName();
    private static final int UNINITIALIZED = -1;
    private static final int VIEW_MONTH_DAY = 0;
    private static final int VIEW_YEAR = 1;
    private ImageView ivHeaderDateReset;
    private LinearLayout llHeaderDateRangeCont;
    private LinearLayout llHeaderDateSingleCont;
    private ViewAnimator mAnimator;
    private ViewGroup mContainer;
    private Context mContext;
    private SelectedDate mCurrentDate;
    private Locale mCurrentLocale;
    private int mCurrentView;
    private int mCurrentlyActivatedRangeItem;
    private OnDateChangedListener mDateChangedListener;
    private DayPickerView mDayPickerView;
    private int mFirstDayOfWeek;
    private TextView mHeaderMonthDay;
    private TextView mHeaderYear;
    private boolean mIsInLandscapeMode;
    private Calendar mMaxDate;
    private Calendar mMinDate;
    private SimpleDateFormat mMonthDayFormat;
    private final OnClickListener mOnHeaderClickListener;
    private final OnYearSelectedListener mOnYearSelectedListener;
    private final ProxyDaySelectionEventListener mProxyDaySelectionEventListener;
    private String mSelectDay;
    private String mSelectYear;
    private Calendar mTempDate;
    private DatePickerValidationCallback mValidationCallback;
    private SimpleDateFormat mYearFormat;
    private YearPickerView mYearPickerView;
    private TextView tvHeaderDateEnd;
    private TextView tvHeaderDateStart;

    public interface OnDateChangedListener {
        void onDateChanged(SublimeDatePicker sublimeDatePicker, SelectedDate selectedDate);
    }

    public interface DatePickerValidationCallback {
        void onDatePickerValidationChanged(boolean z);
    }

    class C05781 implements ProxyDaySelectionEventListener {
        C05781() {
        }

        public void onDaySelected(DayPickerView view, Calendar day) {
            boolean goToPosition = true;
            if (SublimeDatePicker.this.llHeaderDateRangeCont.getVisibility() != 0) {
                SublimeDatePicker.this.mCurrentDate = new SelectedDate(day);
            } else if (SublimeDatePicker.this.tvHeaderDateStart.isActivated()) {
                if (SelectedDate.compareDates(day, SublimeDatePicker.this.mCurrentDate.getEndDate()) > 0) {
                    SublimeDatePicker.this.mCurrentDate = new SelectedDate(day);
                } else {
                    goToPosition = false;
                    SublimeDatePicker.this.mCurrentDate = new SelectedDate(day, SublimeDatePicker.this.mCurrentDate.getEndDate());
                }
            } else if (SublimeDatePicker.this.tvHeaderDateEnd.isActivated()) {
                if (SelectedDate.compareDates(day, SublimeDatePicker.this.mCurrentDate.getStartDate()) < 0) {
                    SublimeDatePicker.this.mCurrentDate = new SelectedDate(day);
                } else {
                    goToPosition = false;
                    SublimeDatePicker.this.mCurrentDate = new SelectedDate(SublimeDatePicker.this.mCurrentDate.getStartDate(), day);
                }
            }
            SublimeDatePicker.this.onDateChanged(true, false, goToPosition);
        }

        public void onDateRangeSelectionStarted(@NonNull SelectedDate selectedDate) {
            SublimeDatePicker.this.mCurrentDate = new SelectedDate(selectedDate);
            SublimeDatePicker.this.onDateChanged(false, false, false);
        }

        public void onDateRangeSelectionEnded(@Nullable SelectedDate selectedDate) {
            if (selectedDate != null) {
                SublimeDatePicker.this.mCurrentDate = new SelectedDate(selectedDate);
                SublimeDatePicker.this.onDateChanged(false, false, false);
            }
        }

        public void onDateRangeSelectionUpdated(@NonNull SelectedDate selectedDate) {
            SublimeDatePicker.this.mCurrentDate = new SelectedDate(selectedDate);
            SublimeDatePicker.this.onDateChanged(false, false, false);
        }
    }

    class C05792 implements OnYearSelectedListener {
        C05792() {
        }

        public void onYearChanged(YearPickerView view, int year) {
            int day = SublimeDatePicker.this.mCurrentDate.getStartDate().get(5);
            int daysInMonth = SUtils.getDaysInMonth(SublimeDatePicker.this.mCurrentDate.getStartDate().get(2), year);
            if (day > daysInMonth) {
                SublimeDatePicker.this.mCurrentDate.set(5, daysInMonth);
            }
            SublimeDatePicker.this.mCurrentDate.set(1, year);
            SublimeDatePicker.this.onDateChanged(true, true, true);
            SublimeDatePicker.this.setCurrentView(0);
        }
    }

    class C05803 implements OnClickListener {
        C05803() {
        }

        public void onClick(View v) {
            SUtils.vibrateForDatePicker(SublimeDatePicker.this);
            if (v.getId() == C0563R.id.date_picker_header_year) {
                SublimeDatePicker.this.setCurrentView(1);
            } else if (v.getId() == C0563R.id.date_picker_header_date) {
                SublimeDatePicker.this.setCurrentView(0);
            } else if (v.getId() == C0563R.id.tv_header_date_start) {
                SublimeDatePicker.this.mCurrentlyActivatedRangeItem = 1;
                SublimeDatePicker.this.tvHeaderDateStart.setActivated(true);
                SublimeDatePicker.this.tvHeaderDateEnd.setActivated(false);
            } else if (v.getId() == C0563R.id.tv_header_date_end) {
                SublimeDatePicker.this.mCurrentlyActivatedRangeItem = 2;
                SublimeDatePicker.this.tvHeaderDateStart.setActivated(false);
                SublimeDatePicker.this.tvHeaderDateEnd.setActivated(true);
            } else if (v.getId() == C0563R.id.iv_header_date_reset) {
                SublimeDatePicker.this.mCurrentDate = new SelectedDate(SublimeDatePicker.this.mCurrentDate.getStartDate());
                SublimeDatePicker.this.onDateChanged(true, false, true);
            }
        }
    }

    private static class SavedState extends BaseSavedState {
        public static final Creator<SavedState> CREATOR = new C05811();
        private final int mCurrentView;
        private final int mListPosition;
        private final int mListPositionOffset;
        private final long mMaxDate;
        private final long mMinDate;
        private final int mSelectedDayEnd;
        private final int mSelectedDayStart;
        private final int mSelectedMonthEnd;
        private final int mSelectedMonthStart;
        private final int mSelectedYearEnd;
        private final int mSelectedYearStart;
        private final int ssCurrentlyActivatedRangeItem;

        static class C05811 implements Creator<SavedState> {
            C05811() {
            }

            public SavedState createFromParcel(Parcel in) {
                return new SavedState(in);
            }

            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        }

        private SavedState(Parcelable superState, SelectedDate selectedDate, long minDate, long maxDate, int currentView, int listPosition, int listPositionOffset, int currentlyActivatedRangeItem) {
            super(superState);
            this.mSelectedYearStart = selectedDate.getStartDate().get(1);
            this.mSelectedMonthStart = selectedDate.getStartDate().get(2);
            this.mSelectedDayStart = selectedDate.getStartDate().get(5);
            this.mSelectedYearEnd = selectedDate.getEndDate().get(1);
            this.mSelectedMonthEnd = selectedDate.getEndDate().get(2);
            this.mSelectedDayEnd = selectedDate.getEndDate().get(5);
            this.mMinDate = minDate;
            this.mMaxDate = maxDate;
            this.mCurrentView = currentView;
            this.mListPosition = listPosition;
            this.mListPositionOffset = listPositionOffset;
            this.ssCurrentlyActivatedRangeItem = currentlyActivatedRangeItem;
        }

        private SavedState(Parcel in) {
            super(in);
            this.mSelectedYearStart = in.readInt();
            this.mSelectedMonthStart = in.readInt();
            this.mSelectedDayStart = in.readInt();
            this.mSelectedYearEnd = in.readInt();
            this.mSelectedMonthEnd = in.readInt();
            this.mSelectedDayEnd = in.readInt();
            this.mMinDate = in.readLong();
            this.mMaxDate = in.readLong();
            this.mCurrentView = in.readInt();
            this.mListPosition = in.readInt();
            this.mListPositionOffset = in.readInt();
            this.ssCurrentlyActivatedRangeItem = in.readInt();
        }

        public void writeToParcel(Parcel dest, int flags) {
            super.writeToParcel(dest, flags);
            dest.writeInt(this.mSelectedYearStart);
            dest.writeInt(this.mSelectedMonthStart);
            dest.writeInt(this.mSelectedDayStart);
            dest.writeInt(this.mSelectedYearEnd);
            dest.writeInt(this.mSelectedMonthEnd);
            dest.writeInt(this.mSelectedDayEnd);
            dest.writeLong(this.mMinDate);
            dest.writeLong(this.mMaxDate);
            dest.writeInt(this.mCurrentView);
            dest.writeInt(this.mListPosition);
            dest.writeInt(this.mListPositionOffset);
            dest.writeInt(this.ssCurrentlyActivatedRangeItem);
        }

        public int getSelectedDayStart() {
            return this.mSelectedDayStart;
        }

        public int getSelectedMonthStart() {
            return this.mSelectedMonthStart;
        }

        public int getSelectedYearStart() {
            return this.mSelectedYearStart;
        }

        public int getSelectedDayEnd() {
            return this.mSelectedDayEnd;
        }

        public int getSelectedMonthEnd() {
            return this.mSelectedMonthEnd;
        }

        public int getSelectedYearEnd() {
            return this.mSelectedYearEnd;
        }

        public long getMinDate() {
            return this.mMinDate;
        }

        public long getMaxDate() {
            return this.mMaxDate;
        }

        public int getCurrentView() {
            return this.mCurrentView;
        }

        public int getListPosition() {
            return this.mListPosition;
        }

        public int getListPositionOffset() {
            return this.mListPositionOffset;
        }

        public int getCurrentlyActivatedRangeItem() {
            return this.ssCurrentlyActivatedRangeItem;
        }
    }

    public SublimeDatePicker(Context context) {
        this(context, null);
    }

    public SublimeDatePicker(Context context, AttributeSet attrs) {
        this(context, attrs, C0563R.attr.spDatePickerStyle);
    }

    public SublimeDatePicker(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mCurrentView = -1;
        this.mCurrentlyActivatedRangeItem = 0;
        this.mProxyDaySelectionEventListener = new C05781();
        this.mOnYearSelectedListener = new C05792();
        this.mOnHeaderClickListener = new C05803();
        initializeLayout(attrs, defStyleAttr, C0563R.style.SublimeDatePickerStyle);
    }

    @TargetApi(21)
    public SublimeDatePicker(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.mCurrentView = -1;
        this.mCurrentlyActivatedRangeItem = 0;
        this.mProxyDaySelectionEventListener = new C05781();
        this.mOnYearSelectedListener = new C05792();
        this.mOnHeaderClickListener = new C05803();
        initializeLayout(attrs, defStyleAttr, defStyleRes);
    }

    private void initializeLayout(AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        this.mContext = getContext();
        this.mIsInLandscapeMode = this.mContext.getResources().getConfiguration().orientation == 2;
        setCurrentLocale(Locale.getDefault());
        this.mCurrentDate = new SelectedDate(Calendar.getInstance(this.mCurrentLocale));
        this.mTempDate = Calendar.getInstance(this.mCurrentLocale);
        this.mMinDate = Calendar.getInstance(this.mCurrentLocale);
        this.mMaxDate = Calendar.getInstance(this.mCurrentLocale);
        this.mMinDate.set(DEFAULT_START_YEAR, 0, 1);
        this.mMaxDate.set(DEFAULT_END_YEAR, 11, 31);
        Resources res = getResources();
        TypedArray a = this.mContext.obtainStyledAttributes(attrs, C0563R.styleable.SublimeDatePicker, defStyleAttr, defStyleRes);
        try {
            this.mContainer = (ViewGroup) ((LayoutInflater) this.mContext.getSystemService("layout_inflater")).inflate(C0563R.layout.date_picker_layout, this, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        addView(this.mContainer);
        ViewGroup header = (ViewGroup) this.mContainer.findViewById(C0563R.id.date_picker_header);
        this.llHeaderDateSingleCont = (LinearLayout) header.findViewById(C0563R.id.ll_header_date_single_cont);
        this.mHeaderYear = (TextView) header.findViewById(C0563R.id.date_picker_header_year);
        this.mHeaderYear.setOnClickListener(this.mOnHeaderClickListener);
        this.mHeaderMonthDay = (TextView) header.findViewById(C0563R.id.date_picker_header_date);
        this.mHeaderMonthDay.setOnClickListener(this.mOnHeaderClickListener);
        this.llHeaderDateRangeCont = (LinearLayout) header.findViewById(C0563R.id.ll_header_date_range_cont);
        this.tvHeaderDateStart = (TextView) header.findViewById(C0563R.id.tv_header_date_start);
        this.tvHeaderDateStart.setOnClickListener(this.mOnHeaderClickListener);
        this.tvHeaderDateEnd = (TextView) header.findViewById(C0563R.id.tv_header_date_end);
        this.tvHeaderDateEnd.setOnClickListener(this.mOnHeaderClickListener);
        this.ivHeaderDateReset = (ImageView) header.findViewById(C0563R.id.iv_header_date_reset);
        this.ivHeaderDateReset.setOnClickListener(this.mOnHeaderClickListener);
        TypedArray typedArray = getContext().obtainStyledAttributes(C0563R.styleable.SublimePicker);
        try {
            int iconColor = typedArray.getColor(C0563R.styleable.SublimePicker_spOverflowIconColor, SUtils.COLOR_TEXT_PRIMARY_INVERSE);
            int pressedStateBgColor = typedArray.getColor(C0563R.styleable.SublimePicker_spOverflowIconPressedBgColor, SUtils.COLOR_TEXT_PRIMARY);
            SUtils.setImageTintList(this.ivHeaderDateReset, ColorStateList.valueOf(iconColor));
            SUtils.setViewBackground(this.ivHeaderDateReset, SUtils.createOverflowButtonBg(pressedStateBgColor));
            ColorStateList headerTextColor = a.getColorStateList(C0563R.styleable.SublimeDatePicker_spHeaderTextColor);
            if (headerTextColor == null) {
                headerTextColor = TextColorHelper.resolveMaterialHeaderTextColor();
            }
            if (headerTextColor != null) {
                this.mHeaderYear.setTextColor(headerTextColor);
                this.mHeaderMonthDay.setTextColor(headerTextColor);
            }
            if (SUtils.isApi_22_OrHigher()) {
                if (a.hasValueOrEmpty(C0563R.styleable.SublimeDatePicker_spHeaderBackground)) {
                    SUtils.setViewBackground(header, a.getDrawable(C0563R.styleable.SublimeDatePicker_spHeaderBackground));
                }
            } else if (a.hasValue(C0563R.styleable.SublimeDatePicker_spHeaderBackground)) {
                SUtils.setViewBackground(header, a.getDrawable(C0563R.styleable.SublimeDatePicker_spHeaderBackground));
            }
            int firstDayOfWeek = a.getInt(C0563R.styleable.SublimeDatePicker_spFirstDayOfWeek, this.mCurrentDate.getFirstDate().getFirstDayOfWeek());
            String minDate = a.getString(C0563R.styleable.SublimeDatePicker_spMinDate);
            String maxDate = a.getString(C0563R.styleable.SublimeDatePicker_spMaxDate);
            Calendar tempDate = Calendar.getInstance();
            if (!SUtils.parseDate(minDate, tempDate)) {
                tempDate.set(DEFAULT_START_YEAR, 0, 1);
            }
            long minDateMillis = tempDate.getTimeInMillis();
            if (!SUtils.parseDate(maxDate, tempDate)) {
                tempDate.set(DEFAULT_END_YEAR, 11, 31);
            }
            long maxDateMillis = tempDate.getTimeInMillis();
            if (maxDateMillis < minDateMillis) {
                throw new IllegalArgumentException("maxDate must be >= minDate");
            }
            long setDateMillis = SUtils.constrain(System.currentTimeMillis(), minDateMillis, maxDateMillis);
            this.mMinDate.setTimeInMillis(minDateMillis);
            this.mMaxDate.setTimeInMillis(maxDateMillis);
            this.mCurrentDate.setTimeInMillis(setDateMillis);
            a.recycle();
            this.mAnimator = (ViewAnimator) this.mContainer.findViewById(C0563R.id.animator);
            this.mDayPickerView = (DayPickerView) this.mAnimator.findViewById(C0563R.id.date_picker_day_picker);
            setFirstDayOfWeek(firstDayOfWeek);
            this.mDayPickerView.setMinDate(this.mMinDate.getTimeInMillis());
            this.mDayPickerView.setMaxDate(this.mMaxDate.getTimeInMillis());
            this.mDayPickerView.setDate(this.mCurrentDate);
            this.mDayPickerView.setProxyDaySelectionEventListener(this.mProxyDaySelectionEventListener);
            this.mYearPickerView = (YearPickerView) this.mAnimator.findViewById(C0563R.id.date_picker_year_picker);
            this.mYearPickerView.setRange(this.mMinDate, this.mMaxDate);
            this.mYearPickerView.setOnYearSelectedListener(this.mOnYearSelectedListener);
            this.mSelectDay = res.getString(C0563R.string.select_day);
            this.mSelectYear = res.getString(C0563R.string.select_year);
            onLocaleChanged(this.mCurrentLocale);
            setCurrentView(0);
        } finally {
            typedArray.recycle();
        }
    }

    private void onLocaleChanged(Locale locale) {
        if (this.mHeaderYear != null) {
            String datePattern;
            if (SUtils.isApi_18_OrHigher()) {
                datePattern = DateFormat.getBestDateTimePattern(locale, "EMMMd");
            } else {
                datePattern = DateTimePatternHelper.getBestDateTimePattern(locale, 0);
            }
            this.mMonthDayFormat = new SimpleDateFormat(datePattern, locale);
            this.mYearFormat = new SimpleDateFormat("y", locale);
            onCurrentDateChanged(false);
        }
    }

    private void onCurrentDateChanged(boolean announce) {
        if (this.mHeaderYear != null) {
            this.mHeaderYear.setText(this.mYearFormat.format(this.mCurrentDate.getStartDate().getTime()));
            this.mHeaderMonthDay.setText(this.mMonthDayFormat.format(this.mCurrentDate.getStartDate().getTime()));
            String yearStrStart = this.mYearFormat.format(this.mCurrentDate.getStartDate().getTime());
            String dateStrStart = yearStrStart + "\n" + this.mMonthDayFormat.format(this.mCurrentDate.getStartDate().getTime());
            String yearStrEnd = this.mYearFormat.format(this.mCurrentDate.getEndDate().getTime());
            String dateStrEnd = yearStrEnd + "\n" + this.mMonthDayFormat.format(this.mCurrentDate.getEndDate().getTime());
            SpannableString spDateStart = new SpannableString(dateStrStart);
            spDateStart.setSpan(new RelativeSizeSpan(BaseViewTransformer.MAX_DIM_ALPHA), 0, yearStrStart.length(), 33);
            SpannableString spDateEnd = new SpannableString(dateStrEnd);
            spDateEnd.setSpan(new RelativeSizeSpan(BaseViewTransformer.MAX_DIM_ALPHA), 0, yearStrEnd.length(), 33);
            if (!(this.mIsInLandscapeMode || SUtils.isApi_17_OrHigher())) {
                spDateEnd.setSpan(new Standard(Alignment.ALIGN_OPPOSITE), 0, dateStrEnd.length(), 33);
            }
            this.tvHeaderDateStart.setText(spDateStart);
            this.tvHeaderDateEnd.setText(spDateEnd);
            if (announce) {
                AccessibilityUtils.makeAnnouncement(this.mAnimator, DateUtils.formatDateTime(this.mContext, this.mCurrentDate.getStartDate().getTimeInMillis(), 20));
            }
        }
    }

    private void setCurrentView(int viewIndex) {
        switch (viewIndex) {
            case 0:
                this.mDayPickerView.setDate(this.mCurrentDate);
                if (this.mCurrentDate.getType() == Type.SINGLE) {
                    switchToSingleDateView();
                } else if (this.mCurrentDate.getType() == Type.RANGE) {
                    switchToDateRangeView();
                }
                if (this.mCurrentView != viewIndex) {
                    if (this.mAnimator.getDisplayedChild() != 0) {
                        this.mAnimator.setDisplayedChild(0);
                    }
                    this.mCurrentView = viewIndex;
                }
                AccessibilityUtils.makeAnnouncement(this.mAnimator, this.mSelectDay);
                return;
            case 1:
                if (this.mCurrentView != viewIndex) {
                    this.mHeaderMonthDay.setActivated(false);
                    this.mHeaderYear.setActivated(true);
                    this.mAnimator.setDisplayedChild(1);
                    this.mCurrentView = viewIndex;
                }
                AccessibilityUtils.makeAnnouncement(this.mAnimator, this.mSelectYear);
                return;
            default:
                return;
        }
    }

    public void init(SelectedDate selectedDate, boolean canPickRange, OnDateChangedListener callback) {
        this.mCurrentDate = new SelectedDate(selectedDate);
        this.mDayPickerView.setCanPickRange(canPickRange);
        this.mDateChangedListener = callback;
        onDateChanged(false, false, true);
    }

    public void updateDate(int year, int month, int dayOfMonth) {
        this.mCurrentDate.set(1, year);
        this.mCurrentDate.set(2, month);
        this.mCurrentDate.set(5, dayOfMonth);
        onDateChanged(false, true, true);
    }

    private void onDateChanged(boolean fromUser, boolean callbackToClient, boolean goToPosition) {
        int year = this.mCurrentDate.getStartDate().get(1);
        if (callbackToClient && this.mDateChangedListener != null) {
            this.mDateChangedListener.onDateChanged(this, this.mCurrentDate);
        }
        updateHeaderViews();
        this.mDayPickerView.setDate(new SelectedDate(this.mCurrentDate), false, goToPosition);
        if (this.mCurrentDate.getType() == Type.SINGLE) {
            this.mYearPickerView.setYear(year);
        }
        onCurrentDateChanged(fromUser);
        if (fromUser) {
            SUtils.vibrateForDatePicker(this);
        }
    }

    private void updateHeaderViews() {
        if (this.mCurrentDate.getType() == Type.SINGLE) {
            switchToSingleDateView();
        } else if (this.mCurrentDate.getType() == Type.RANGE) {
            switchToDateRangeView();
        }
    }

    private void switchToSingleDateView() {
        this.mCurrentlyActivatedRangeItem = 0;
        this.ivHeaderDateReset.setVisibility(8);
        this.llHeaderDateRangeCont.setVisibility(4);
        this.llHeaderDateSingleCont.setVisibility(0);
        this.mHeaderMonthDay.setActivated(true);
        this.mHeaderYear.setActivated(false);
    }

    private void switchToDateRangeView() {
        boolean z;
        boolean z2 = true;
        if (this.mCurrentlyActivatedRangeItem == 0) {
            this.mCurrentlyActivatedRangeItem = 1;
        }
        this.llHeaderDateSingleCont.setVisibility(4);
        this.ivHeaderDateReset.setVisibility(0);
        this.llHeaderDateRangeCont.setVisibility(0);
        TextView textView = this.tvHeaderDateStart;
        if (this.mCurrentlyActivatedRangeItem == 1) {
            z = true;
        } else {
            z = false;
        }
        textView.setActivated(z);
        TextView textView2 = this.tvHeaderDateEnd;
        if (this.mCurrentlyActivatedRangeItem != 2) {
            z2 = false;
        }
        textView2.setActivated(z2);
    }

    public SelectedDate getSelectedDate() {
        return new SelectedDate(this.mCurrentDate);
    }

    public long getSelectedDateInMillis() {
        return this.mCurrentDate.getStartDate().getTimeInMillis();
    }

    public void setMinDate(long minDate) {
        this.mTempDate.setTimeInMillis(minDate);
        if (this.mTempDate.get(1) != this.mMinDate.get(1) || this.mTempDate.get(6) == this.mMinDate.get(6)) {
            if (this.mCurrentDate.getStartDate().before(this.mTempDate)) {
                this.mCurrentDate.getStartDate().setTimeInMillis(minDate);
                onDateChanged(false, true, true);
            }
            this.mMinDate.setTimeInMillis(minDate);
            this.mDayPickerView.setMinDate(minDate);
            this.mYearPickerView.setRange(this.mMinDate, this.mMaxDate);
        }
    }

    public Calendar getMinDate() {
        return this.mMinDate;
    }

    public void setMaxDate(long maxDate) {
        this.mTempDate.setTimeInMillis(maxDate);
        if (this.mTempDate.get(1) != this.mMaxDate.get(1) || this.mTempDate.get(6) == this.mMaxDate.get(6)) {
            if (this.mCurrentDate.getEndDate().after(this.mTempDate)) {
                this.mCurrentDate.getEndDate().setTimeInMillis(maxDate);
                onDateChanged(false, true, true);
            }
            this.mMaxDate.setTimeInMillis(maxDate);
            this.mDayPickerView.setMaxDate(maxDate);
            this.mYearPickerView.setRange(this.mMinDate, this.mMaxDate);
        }
    }

    public Calendar getMaxDate() {
        return this.mMaxDate;
    }

    public void setFirstDayOfWeek(int firstDayOfWeek) {
        if (firstDayOfWeek < 1 || firstDayOfWeek > 7) {
            firstDayOfWeek = this.mCurrentDate.getFirstDate().getFirstDayOfWeek();
        }
        this.mFirstDayOfWeek = firstDayOfWeek;
        this.mDayPickerView.setFirstDayOfWeek(firstDayOfWeek);
    }

    public int getFirstDayOfWeek() {
        return this.mFirstDayOfWeek;
    }

    public void setEnabled(boolean enabled) {
        if (isEnabled() != enabled) {
            this.mContainer.setEnabled(enabled);
            this.mDayPickerView.setEnabled(enabled);
            this.mYearPickerView.setEnabled(enabled);
            this.mHeaderYear.setEnabled(enabled);
            this.mHeaderMonthDay.setEnabled(enabled);
        }
    }

    public boolean isEnabled() {
        return this.mContainer.isEnabled();
    }

    public void onConfigurationChanged(Configuration newConfig) {
        setCurrentLocale(newConfig.locale);
    }

    private void setCurrentLocale(Locale locale) {
        if (!locale.equals(this.mCurrentLocale)) {
            this.mCurrentLocale = locale;
            onLocaleChanged(locale);
        }
    }

    public Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();
        int listPosition = -1;
        int listPositionOffset = -1;
        if (this.mCurrentView == 0) {
            listPosition = this.mDayPickerView.getMostVisiblePosition();
        } else if (this.mCurrentView == 1) {
            listPosition = this.mYearPickerView.getFirstVisiblePosition();
            listPositionOffset = this.mYearPickerView.getFirstPositionOffset();
        }
        return new SavedState(superState, this.mCurrentDate, this.mMinDate.getTimeInMillis(), this.mMaxDate.getTimeInMillis(), this.mCurrentView, listPosition, listPositionOffset, this.mCurrentlyActivatedRangeItem);
    }

    @SuppressLint({"NewApi"})
    public void onRestoreInstanceState(Parcelable state) {
        BaseSavedState bss = (BaseSavedState) state;
        super.onRestoreInstanceState(bss.getSuperState());
        SavedState ss = (SavedState) bss;
        Calendar startDate = Calendar.getInstance(this.mCurrentLocale);
        Calendar endDate = Calendar.getInstance(this.mCurrentLocale);
        startDate.set(ss.getSelectedYearStart(), ss.getSelectedMonthStart(), ss.getSelectedDayStart());
        endDate.set(ss.getSelectedYearEnd(), ss.getSelectedMonthEnd(), ss.getSelectedDayEnd());
        this.mCurrentDate.setFirstDate(startDate);
        this.mCurrentDate.setSecondDate(endDate);
        int currentView = ss.getCurrentView();
        this.mMinDate.setTimeInMillis(ss.getMinDate());
        this.mMaxDate.setTimeInMillis(ss.getMaxDate());
        this.mCurrentlyActivatedRangeItem = ss.getCurrentlyActivatedRangeItem();
        onCurrentDateChanged(false);
        setCurrentView(currentView);
        int listPosition = ss.getListPosition();
        if (listPosition == -1) {
            return;
        }
        if (currentView == 0) {
            this.mDayPickerView.setPosition(listPosition);
        } else if (currentView == 1) {
            this.mYearPickerView.setSelectionFromTop(listPosition, ss.getListPositionOffset());
        }
    }

    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent event) {
        onPopulateAccessibilityEvent(event);
        return true;
    }

    public void onPopulateAccessibilityEvent(AccessibilityEvent event) {
        super.onPopulateAccessibilityEvent(event);
        event.getText().add(this.mCurrentDate.getStartDate().getTime().toString());
    }

    public CharSequence getAccessibilityClassName() {
        return SublimeDatePicker.class.getName();
    }

    public void setValidationCallback(DatePickerValidationCallback callback) {
        this.mValidationCallback = callback;
    }

    protected void onValidationChanged(boolean valid) {
        if (this.mValidationCallback != null) {
            this.mValidationCallback.onDatePickerValidationChanged(valid);
        }
    }
}
