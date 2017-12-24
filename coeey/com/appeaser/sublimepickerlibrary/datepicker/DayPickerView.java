package com.appeaser.sublimepickerlibrary.datepicker;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityManager;
import android.widget.ImageButton;
import com.appeaser.sublimepickerlibrary.C0563R;
import com.appeaser.sublimepickerlibrary.datepicker.DayPickerPagerAdapter.DaySelectionEventListener;
import com.appeaser.sublimepickerlibrary.utilities.SUtils;
import java.util.Calendar;

class DayPickerView extends ViewGroup {
    private static final int[] ATTRS_TEXT_COLOR = new int[]{16842904};
    private static final String TAG = DayPickerView.class.getSimpleName();
    private final AccessibilityManager mAccessibilityManager;
    private final DayPickerPagerAdapter mAdapter;
    private final Calendar mMaxDate;
    private final Calendar mMinDate;
    private final ImageButton mNextButton;
    private final ImageButton mPrevButton;
    private ProxyDaySelectionEventListener mProxyDaySelectionEventListener;
    private SelectedDate mSelectedDay;
    private Calendar mTempCalendar;
    private final DayPickerViewPager mViewPager;

    class C05701 implements OnClickListener {
        C05701() {
        }

        public void onClick(View v) {
            int direction;
            if (v == DayPickerView.this.mPrevButton) {
                direction = -1;
            } else if (v == DayPickerView.this.mNextButton) {
                direction = 1;
            } else {
                return;
            }
            DayPickerView.this.mViewPager.setCurrentItem(DayPickerView.this.mViewPager.getCurrentItem() + direction, !DayPickerView.this.mAccessibilityManager.isEnabled());
        }
    }

    class C05712 implements OnPageChangeListener {
        C05712() {
        }

        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            float alpha = Math.abs(0.5f - positionOffset) * 2.0f;
            DayPickerView.this.mPrevButton.setAlpha(alpha);
            DayPickerView.this.mNextButton.setAlpha(alpha);
        }

        public void onPageScrollStateChanged(int state) {
        }

        public void onPageSelected(int position) {
            DayPickerView.this.updateButtonVisibility(position);
        }
    }

    class C05723 implements DaySelectionEventListener {
        C05723() {
        }

        public void onDaySelected(DayPickerPagerAdapter adapter, Calendar day) {
            if (DayPickerView.this.mProxyDaySelectionEventListener != null) {
                DayPickerView.this.mProxyDaySelectionEventListener.onDaySelected(DayPickerView.this, day);
            }
        }

        public void onDateRangeSelectionStarted(@NonNull SelectedDate selectedDate) {
            if (DayPickerView.this.mProxyDaySelectionEventListener != null) {
                DayPickerView.this.mProxyDaySelectionEventListener.onDateRangeSelectionStarted(selectedDate);
            }
        }

        public void onDateRangeSelectionEnded(@Nullable SelectedDate selectedDate) {
            if (DayPickerView.this.mProxyDaySelectionEventListener != null) {
                DayPickerView.this.mProxyDaySelectionEventListener.onDateRangeSelectionEnded(selectedDate);
            }
        }

        public void onDateRangeSelectionUpdated(@NonNull SelectedDate selectedDate) {
            if (DayPickerView.this.mProxyDaySelectionEventListener != null) {
                DayPickerView.this.mProxyDaySelectionEventListener.onDateRangeSelectionUpdated(selectedDate);
            }
        }
    }

    public interface ProxyDaySelectionEventListener {
        void onDateRangeSelectionEnded(@Nullable SelectedDate selectedDate);

        void onDateRangeSelectionStarted(@NonNull SelectedDate selectedDate);

        void onDateRangeSelectionUpdated(@NonNull SelectedDate selectedDate);

        void onDaySelected(DayPickerView dayPickerView, Calendar calendar);
    }

    public DayPickerView(Context context) {
        this(context, null);
    }

    public DayPickerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, C0563R.attr.spDayPickerStyle);
    }

    public DayPickerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        int layoutIdToUse;
        int viewPagerIdToUse;
        super(SUtils.createThemeWrapper(context, C0563R.attr.sublimePickerStyle, C0563R.style.SublimePickerStyleLight, defStyleAttr, C0563R.style.DayPickerViewStyle), attrs);
        this.mSelectedDay = null;
        this.mMinDate = Calendar.getInstance();
        this.mMaxDate = Calendar.getInstance();
        context = getContext();
        this.mAccessibilityManager = (AccessibilityManager) context.getSystemService("accessibility");
        TypedArray a = context.obtainStyledAttributes(attrs, C0563R.styleable.DayPickerView, defStyleAttr, C0563R.style.DayPickerViewStyle);
        int monthTextAppearanceResId = a.getResourceId(C0563R.styleable.DayPickerView_spMonthTextAppearance, C0563R.style.SPMonthLabelTextAppearance);
        int dayOfWeekTextAppearanceResId = a.getResourceId(C0563R.styleable.DayPickerView_spWeekDayTextAppearance, C0563R.style.SPWeekDayLabelTextAppearance);
        int dayTextAppearanceResId = a.getResourceId(C0563R.styleable.DayPickerView_spDateTextAppearance, C0563R.style.SPDayTextAppearance);
        ColorStateList daySelectorColor = a.getColorStateList(C0563R.styleable.DayPickerView_spDaySelectorColor);
        a.recycle();
        this.mAdapter = new DayPickerPagerAdapter(context, C0563R.layout.date_picker_month_item, C0563R.id.month_view);
        this.mAdapter.setMonthTextAppearance(monthTextAppearanceResId);
        this.mAdapter.setDayOfWeekTextAppearance(dayOfWeekTextAppearanceResId);
        this.mAdapter.setDayTextAppearance(dayTextAppearanceResId);
        this.mAdapter.setDaySelectorColor(daySelectorColor);
        LayoutInflater inflater = LayoutInflater.from(context);
        if (getTag() != null && (getTag() instanceof String) && getResources().getString(C0563R.string.recurrence_end_date_picker_tag).equals(getTag())) {
            layoutIdToUse = C0563R.layout.day_picker_content_redp;
            viewPagerIdToUse = C0563R.id.redp_view_pager;
        } else {
            layoutIdToUse = C0563R.layout.day_picker_content_sdp;
            viewPagerIdToUse = C0563R.id.sdp_view_pager;
        }
        inflater.inflate(layoutIdToUse, this, true);
        OnClickListener onClickListener = new C05701();
        this.mPrevButton = (ImageButton) findViewById(C0563R.id.prev);
        this.mPrevButton.setOnClickListener(onClickListener);
        this.mNextButton = (ImageButton) findViewById(C0563R.id.next);
        this.mNextButton.setOnClickListener(onClickListener);
        OnPageChangeListener onPageChangedListener = new C05712();
        this.mViewPager = (DayPickerViewPager) findViewById(viewPagerIdToUse);
        this.mViewPager.setAdapter(this.mAdapter);
        this.mViewPager.addOnPageChangeListener(onPageChangedListener);
        if (monthTextAppearanceResId != 0) {
            TypedArray ta = context.obtainStyledAttributes(null, ATTRS_TEXT_COLOR, 0, monthTextAppearanceResId);
            ColorStateList monthColor = ta.getColorStateList(0);
            if (monthColor != null) {
                SUtils.setImageTintList(this.mPrevButton, monthColor);
                SUtils.setImageTintList(this.mNextButton, monthColor);
            }
            ta.recycle();
        }
        this.mAdapter.setDaySelectionEventListener(new C05723());
    }

    public void setCanPickRange(boolean canPickRange) {
        this.mViewPager.setCanPickRange(canPickRange);
    }

    private void updateButtonVisibility(int position) {
        boolean hasPrev;
        boolean hasNext;
        int i;
        int i2 = 0;
        if (position > 0) {
            hasPrev = true;
        } else {
            hasPrev = false;
        }
        if (position < this.mAdapter.getCount() - 1) {
            hasNext = true;
        } else {
            hasNext = false;
        }
        ImageButton imageButton = this.mPrevButton;
        if (hasPrev) {
            i = 0;
        } else {
            i = 4;
        }
        imageButton.setVisibility(i);
        ImageButton imageButton2 = this.mNextButton;
        if (!hasNext) {
            i2 = 4;
        }
        imageButton2.setVisibility(i2);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        ViewPager viewPager = this.mViewPager;
        measureChild(viewPager, widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(viewPager.getMeasuredWidthAndState(), viewPager.getMeasuredHeightAndState());
        int pagerWidth = viewPager.getMeasuredWidth();
        int pagerHeight = viewPager.getMeasuredHeight();
        int buttonWidthSpec = MeasureSpec.makeMeasureSpec(pagerWidth, Integer.MIN_VALUE);
        int buttonHeightSpec = MeasureSpec.makeMeasureSpec(pagerHeight, Integer.MIN_VALUE);
        this.mPrevButton.measure(buttonWidthSpec, buttonHeightSpec);
        this.mNextButton.measure(buttonWidthSpec, buttonHeightSpec);
    }

    public void onRtlPropertiesChanged(int layoutDirection) {
        super.onRtlPropertiesChanged(layoutDirection);
        requestLayout();
    }

    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        ImageButton leftButton;
        ImageButton rightButton;
        if (SUtils.isLayoutRtlCompat(this)) {
            leftButton = this.mNextButton;
            rightButton = this.mPrevButton;
        } else {
            leftButton = this.mPrevButton;
            rightButton = this.mNextButton;
        }
        int width = right - left;
        this.mViewPager.layout(0, 0, width, bottom - top);
        SimpleMonthView monthView = (SimpleMonthView) this.mViewPager.getChildAt(0).findViewById(C0563R.id.month_view);
        int monthHeight = monthView.getMonthHeight();
        int cellWidth = monthView.getCellWidth();
        int leftDW = leftButton.getMeasuredWidth();
        int leftDH = leftButton.getMeasuredHeight();
        int leftIconTop = monthView.getPaddingTop() + ((monthHeight - leftDH) / 2);
        int leftIconLeft = monthView.getPaddingLeft() + ((cellWidth - leftDW) / 2);
        leftButton.layout(leftIconLeft, leftIconTop, leftIconLeft + leftDW, leftIconTop + leftDH);
        int rightDW = rightButton.getMeasuredWidth();
        int rightDH = rightButton.getMeasuredHeight();
        int rightIconTop = monthView.getPaddingTop() + ((monthHeight - rightDH) / 2);
        int rightIconRight = (width - monthView.getPaddingRight()) - ((cellWidth - rightDW) / 2);
        rightButton.layout(rightIconRight - rightDW, rightIconTop, rightIconRight, rightIconTop + rightDH);
    }

    public void setDayOfWeekTextAppearance(int resId) {
        this.mAdapter.setDayOfWeekTextAppearance(resId);
    }

    public int getDayOfWeekTextAppearance() {
        return this.mAdapter.getDayOfWeekTextAppearance();
    }

    public void setDayTextAppearance(int resId) {
        this.mAdapter.setDayTextAppearance(resId);
    }

    public int getDayTextAppearance() {
        return this.mAdapter.getDayTextAppearance();
    }

    public void setDate(SelectedDate date) {
        setDate(date, false);
    }

    public void setDate(SelectedDate date, boolean animate) {
        setDate(date, animate, true, true);
    }

    public void setDate(SelectedDate date, boolean animate, boolean goToPosition) {
        setDate(date, animate, true, goToPosition);
    }

    private void setDate(SelectedDate date, boolean animate, boolean setSelected, boolean goToPosition) {
        long timeInMillis;
        if (setSelected) {
            this.mSelectedDay = date;
        }
        if (this.mSelectedDay == null) {
            timeInMillis = Calendar.getInstance().getTimeInMillis();
        } else {
            timeInMillis = this.mSelectedDay.getStartDate().getTimeInMillis();
        }
        int position = getPositionFromDay(timeInMillis);
        if (goToPosition && position != this.mViewPager.getCurrentItem()) {
            this.mViewPager.setCurrentItem(position, animate);
        }
        this.mAdapter.setSelectedDay(new SelectedDate(this.mSelectedDay));
    }

    public SelectedDate getDate() {
        return this.mSelectedDay;
    }

    public void setFirstDayOfWeek(int firstDayOfWeek) {
        this.mAdapter.setFirstDayOfWeek(firstDayOfWeek);
    }

    public int getFirstDayOfWeek() {
        return this.mAdapter.getFirstDayOfWeek();
    }

    public void setMinDate(long timeInMillis) {
        this.mMinDate.setTimeInMillis(timeInMillis);
        onRangeChanged();
    }

    public long getMinDate() {
        return this.mMinDate.getTimeInMillis();
    }

    public void setMaxDate(long timeInMillis) {
        this.mMaxDate.setTimeInMillis(timeInMillis);
        onRangeChanged();
    }

    public long getMaxDate() {
        return this.mMaxDate.getTimeInMillis();
    }

    private void onRangeChanged() {
        this.mAdapter.setRange(this.mMinDate, this.mMaxDate);
        setDate(this.mSelectedDay, false, false, true);
        updateButtonVisibility(this.mViewPager.getCurrentItem());
    }

    public void setProxyDaySelectionEventListener(ProxyDaySelectionEventListener listener) {
        this.mProxyDaySelectionEventListener = listener;
    }

    private int getDiffMonths(Calendar start, Calendar end) {
        return (end.get(2) - start.get(2)) + ((end.get(1) - start.get(1)) * 12);
    }

    private int getPositionFromDay(long timeInMillis) {
        return SUtils.constrain(getDiffMonths(this.mMinDate, getTempCalendarForTime(timeInMillis)), 0, getDiffMonths(this.mMinDate, this.mMaxDate));
    }

    private Calendar getTempCalendarForTime(long timeInMillis) {
        if (this.mTempCalendar == null) {
            this.mTempCalendar = Calendar.getInstance();
        }
        this.mTempCalendar.setTimeInMillis(timeInMillis);
        return this.mTempCalendar;
    }

    public int getMostVisiblePosition() {
        return this.mViewPager.getCurrentItem();
    }

    public void setPosition(int position) {
        this.mViewPager.setCurrentItem(position, false);
    }
}
