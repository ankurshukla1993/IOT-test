package com.appeaser.sublimepickerlibrary.datepicker;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.appeaser.sublimepickerlibrary.C0563R;
import com.appeaser.sublimepickerlibrary.datepicker.SelectedDate.Type;
import com.appeaser.sublimepickerlibrary.datepicker.SimpleMonthView.OnDayClickListener;
import com.appeaser.sublimepickerlibrary.utilities.SUtils;
import java.util.Calendar;

class DayPickerPagerAdapter extends PagerAdapter {
    private static final int MONTHS_IN_YEAR = 12;
    private static final String TAG = DayPickerPagerAdapter.class.getSimpleName();
    private ColorStateList mCalendarTextColor;
    private final int mCalendarViewId;
    private int mCount;
    private final ColorStateList mDayHighlightColor;
    private int mDayOfWeekTextAppearance;
    private DaySelectionEventListener mDaySelectionEventListener;
    private ColorStateList mDaySelectorColor;
    private int mDayTextAppearance;
    private int mFirstDayOfWeek;
    private final LayoutInflater mInflater;
    private final SparseArray<ViewHolder> mItems = new SparseArray();
    private final int mLayoutResId;
    private final Calendar mMaxDate = Calendar.getInstance();
    private final Calendar mMinDate = Calendar.getInstance();
    private int mMonthTextAppearance;
    private final OnDayClickListener mOnDayClickListener = new C05691();
    private SelectedDate mSelectedDay = null;
    private final SelectedDate mTempSelectedDay = new SelectedDate(Calendar.getInstance());

    class C05691 implements OnDayClickListener {
        C05691() {
        }

        public void onDayClick(SimpleMonthView view, Calendar day) {
            if (day != null && DayPickerPagerAdapter.this.mDaySelectionEventListener != null) {
                DayPickerPagerAdapter.this.mDaySelectionEventListener.onDaySelected(DayPickerPagerAdapter.this, day);
            }
        }
    }

    public interface DaySelectionEventListener {
        void onDateRangeSelectionEnded(@Nullable SelectedDate selectedDate);

        void onDateRangeSelectionStarted(@NonNull SelectedDate selectedDate);

        void onDateRangeSelectionUpdated(@NonNull SelectedDate selectedDate);

        void onDaySelected(DayPickerPagerAdapter dayPickerPagerAdapter, Calendar calendar);
    }

    private static class ViewHolder {
        public final SimpleMonthView calendar;
        public final View container;
        public final int position;

        public ViewHolder(int position, View container, SimpleMonthView calendar) {
            this.position = position;
            this.container = container;
            this.calendar = calendar;
        }
    }

    public DayPickerPagerAdapter(@NonNull Context context, @LayoutRes int layoutResId, @IdRes int calendarViewId) {
        this.mInflater = LayoutInflater.from(context);
        this.mLayoutResId = layoutResId;
        this.mCalendarViewId = calendarViewId;
        TypedArray ta = context.obtainStyledAttributes(new int[]{C0563R.attr.colorControlHighlight});
        this.mDayHighlightColor = ta.getColorStateList(0);
        ta.recycle();
    }

    public void setRange(@NonNull Calendar min, @NonNull Calendar max) {
        this.mMinDate.setTimeInMillis(min.getTimeInMillis());
        this.mMaxDate.setTimeInMillis(max.getTimeInMillis());
        int diffMonth = this.mMaxDate.get(2) - this.mMinDate.get(2);
        this.mCount = (((this.mMaxDate.get(1) - this.mMinDate.get(1)) * 12) + diffMonth) + 1;
        notifyDataSetChanged();
    }

    public void setFirstDayOfWeek(int weekStart) {
        this.mFirstDayOfWeek = weekStart;
        int count = this.mItems.size();
        for (int i = 0; i < count; i++) {
            ((ViewHolder) this.mItems.valueAt(i)).calendar.setFirstDayOfWeek(weekStart);
        }
    }

    public int getFirstDayOfWeek() {
        return this.mFirstDayOfWeek;
    }

    public void setSelectedDay(@Nullable SelectedDate day) {
        int i;
        int[] oldPosition = getPositionsForDay(this.mSelectedDay);
        int[] newPosition = getPositionsForDay(day);
        if (oldPosition != null) {
            for (i = oldPosition[0]; i <= oldPosition[oldPosition.length - 1]; i++) {
                ViewHolder oldMonthView = (ViewHolder) this.mItems.get(i, null);
                if (oldMonthView != null) {
                    oldMonthView.calendar.setSelectedDays(-1, -1, Type.SINGLE);
                }
            }
        }
        if (newPosition != null) {
            ViewHolder newMonthView;
            if (newPosition.length == 1) {
                newMonthView = (ViewHolder) this.mItems.get(newPosition[0], null);
                if (newMonthView != null) {
                    int dayOfMonth = day.getFirstDate().get(5);
                    newMonthView.calendar.setSelectedDays(dayOfMonth, dayOfMonth, Type.SINGLE);
                }
            } else if (newPosition.length == 2) {
                if (newPosition[0] == newPosition[1]) {
                    newMonthView = (ViewHolder) this.mItems.get(newPosition[0], null);
                    if (newMonthView != null) {
                        newMonthView.calendar.setSelectedDays(day.getFirstDate().get(5), day.getSecondDate().get(5), Type.RANGE);
                    }
                } else {
                    ViewHolder newMonthViewStart = (ViewHolder) this.mItems.get(newPosition[0], null);
                    if (newMonthViewStart != null) {
                        newMonthViewStart.calendar.setSelectedDays(day.getFirstDate().get(5), day.getFirstDate().getActualMaximum(5), Type.RANGE);
                    }
                    for (i = newPosition[0] + 1; i < newPosition[1]; i++) {
                        newMonthView = (ViewHolder) this.mItems.get(i, null);
                        if (newMonthView != null) {
                            newMonthView.calendar.selectAllDays();
                        }
                    }
                    ViewHolder newMonthViewEnd = (ViewHolder) this.mItems.get(newPosition[1], null);
                    if (newMonthViewEnd != null) {
                        newMonthViewEnd.calendar.setSelectedDays(day.getSecondDate().getMinimum(5), day.getSecondDate().get(5), Type.RANGE);
                    }
                }
            }
        }
        this.mSelectedDay = day;
    }

    public void setDaySelectionEventListener(DaySelectionEventListener listener) {
        this.mDaySelectionEventListener = listener;
    }

    void setCalendarTextColor(ColorStateList calendarTextColor) {
        this.mCalendarTextColor = calendarTextColor;
    }

    void setDaySelectorColor(ColorStateList selectorColor) {
        this.mDaySelectorColor = selectorColor;
    }

    void setMonthTextAppearance(int resId) {
        this.mMonthTextAppearance = resId;
    }

    void setDayOfWeekTextAppearance(int resId) {
        this.mDayOfWeekTextAppearance = resId;
    }

    int getDayOfWeekTextAppearance() {
        return this.mDayOfWeekTextAppearance;
    }

    void setDayTextAppearance(int resId) {
        this.mDayTextAppearance = resId;
    }

    int getDayTextAppearance() {
        return this.mDayTextAppearance;
    }

    public int getCount() {
        return this.mCount;
    }

    public boolean isViewFromObject(View view, Object object) {
        return view == ((ViewHolder) object).container;
    }

    private int getMonthForPosition(int position) {
        return (this.mMinDate.get(2) + position) % 12;
    }

    private int getYearForPosition(int position) {
        return this.mMinDate.get(1) + ((this.mMinDate.get(2) + position) / 12);
    }

    private int getPositionForDay(@Nullable Calendar day) {
        if (day == null) {
            return -1;
        }
        return ((day.get(1) - this.mMinDate.get(1)) * 12) + (day.get(2) - this.mMinDate.get(2));
    }

    private int[] getPositionsForDay(@Nullable SelectedDate day) {
        if (day == null) {
            return null;
        }
        Type typeOfDay = day.getType();
        int[] positions;
        if (typeOfDay == Type.SINGLE) {
            positions = new int[1];
            int monthOffset = day.getFirstDate().get(2) - this.mMinDate.get(2);
            positions[0] = ((day.getFirstDate().get(1) - this.mMinDate.get(1)) * 12) + monthOffset;
            return positions;
        } else if (typeOfDay != Type.RANGE) {
            return null;
        } else {
            positions = new int[2];
            int monthOffsetFirstDate = day.getFirstDate().get(2) - this.mMinDate.get(2);
            positions[0] = ((day.getFirstDate().get(1) - this.mMinDate.get(1)) * 12) + monthOffsetFirstDate;
            int monthOffsetSecondDate = day.getSecondDate().get(2) - this.mMinDate.get(2);
            positions[1] = ((day.getSecondDate().get(1) - this.mMinDate.get(1)) * 12) + monthOffsetSecondDate;
            return positions;
        }
    }

    public Object instantiateItem(ViewGroup container, int position) {
        int enabledDayRangeStart;
        int enabledDayRangeEnd;
        View itemView = this.mInflater.inflate(this.mLayoutResId, container, false);
        SimpleMonthView v = (SimpleMonthView) itemView.findViewById(this.mCalendarViewId);
        v.setOnDayClickListener(this.mOnDayClickListener);
        v.setMonthTextAppearance(this.mMonthTextAppearance);
        v.setDayOfWeekTextAppearance(this.mDayOfWeekTextAppearance);
        v.setDayTextAppearance(this.mDayTextAppearance);
        if (this.mDaySelectorColor != null) {
            v.setDaySelectorColor(this.mDaySelectorColor);
        }
        if (this.mDayHighlightColor != null) {
            v.setDayHighlightColor(this.mDayHighlightColor);
        }
        if (this.mCalendarTextColor != null) {
            v.setMonthTextColor(this.mCalendarTextColor);
            v.setDayOfWeekTextColor(this.mCalendarTextColor);
            v.setDayTextColor(this.mCalendarTextColor);
        }
        int month = getMonthForPosition(position);
        int year = getYearForPosition(position);
        int[] selectedDay = resolveSelectedDayBasedOnType(month, year);
        if (this.mMinDate.get(2) == month && this.mMinDate.get(1) == year) {
            enabledDayRangeStart = this.mMinDate.get(5);
        } else {
            enabledDayRangeStart = 1;
        }
        if (this.mMaxDate.get(2) == month && this.mMaxDate.get(1) == year) {
            enabledDayRangeEnd = this.mMaxDate.get(5);
        } else {
            enabledDayRangeEnd = 31;
        }
        v.setMonthParams(month, year, this.mFirstDayOfWeek, enabledDayRangeStart, enabledDayRangeEnd, selectedDay[0], selectedDay[1], this.mSelectedDay != null ? this.mSelectedDay.getType() : null);
        ViewHolder holder = new ViewHolder(position, itemView, v);
        this.mItems.put(position, holder);
        container.addView(itemView);
        return holder;
    }

    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(((ViewHolder) object).container);
        this.mItems.remove(position);
    }

    public int getItemPosition(Object object) {
        return ((ViewHolder) object).position;
    }

    public CharSequence getPageTitle(int position) {
        SimpleMonthView v = ((ViewHolder) this.mItems.get(position)).calendar;
        if (v != null) {
            return v.getTitle();
        }
        return null;
    }

    public SelectedDate resolveStartDateForRange(int x, int y, int position) {
        if (position < 0) {
            return null;
        }
        ViewHolder newMonthView = (ViewHolder) this.mItems.get(position, null);
        if (newMonthView == null) {
            return null;
        }
        Calendar selectedDayStart = newMonthView.calendar.composeDate(newMonthView.calendar.getDayAtLocation(x, y));
        if (selectedDayStart == null) {
            return null;
        }
        this.mTempSelectedDay.setDate(selectedDayStart);
        return this.mTempSelectedDay;
    }

    public SelectedDate resolveEndDateForRange(int x, int y, int position, boolean updateIfNecessary) {
        if (position < 0) {
            return null;
        }
        ViewHolder newMonthView = (ViewHolder) this.mItems.get(position, null);
        if (newMonthView == null) {
            return null;
        }
        Calendar selectedDayEnd = newMonthView.calendar.composeDate(newMonthView.calendar.getDayAtLocation(x, y));
        if (selectedDayEnd == null) {
            return null;
        }
        if (updateIfNecessary && this.mSelectedDay.getSecondDate().getTimeInMillis() == selectedDayEnd.getTimeInMillis()) {
            return null;
        }
        this.mTempSelectedDay.setSecondDate(selectedDayEnd);
        return this.mTempSelectedDay;
    }

    private int[] resolveSelectedDayBasedOnType(int month, int year) {
        if (this.mSelectedDay == null) {
            return new int[]{-1, -1};
        }
        if (this.mSelectedDay.getType() == Type.SINGLE) {
            return resolveSelectedDayForTypeSingle(month, year);
        }
        if (this.mSelectedDay.getType() == Type.RANGE) {
            return resolveSelectedDayForTypeRange(month, year);
        }
        return new int[]{-1, -1};
    }

    private int[] resolveSelectedDayForTypeSingle(int month, int year) {
        if (this.mSelectedDay.getFirstDate().get(2) != month || this.mSelectedDay.getFirstDate().get(1) != year) {
            return new int[]{-1, -1};
        }
        int resolvedDay = this.mSelectedDay.getFirstDate().get(5);
        return new int[]{resolvedDay, resolvedDay};
    }

    private int[] resolveSelectedDayForTypeRange(int month, int year) {
        float startDateQuan = ((float) this.mSelectedDay.getStartDate().get(1)) + (((float) (this.mSelectedDay.getStartDate().get(2) + 1)) / 100.0f);
        float endDateQuan = ((float) this.mSelectedDay.getEndDate().get(1)) + (((float) (this.mSelectedDay.getEndDate().get(2) + 1)) / 100.0f);
        float dateQuan = ((float) year) + (((float) (month + 1)) / 100.0f);
        if (dateQuan < startDateQuan || dateQuan > endDateQuan) {
            return new int[]{-1, -1};
        }
        int startDay;
        int endDay;
        if (dateQuan == startDateQuan) {
            startDay = this.mSelectedDay.getStartDate().get(5);
        } else {
            startDay = 1;
        }
        if (dateQuan == endDateQuan) {
            endDay = this.mSelectedDay.getEndDate().get(5);
        } else {
            endDay = SUtils.getDaysInMonth(month, year);
        }
        return new int[]{startDay, endDay};
    }

    public void onDateRangeSelectionStarted(SelectedDate selectedDate) {
        if (this.mDaySelectionEventListener != null) {
            this.mDaySelectionEventListener.onDateRangeSelectionStarted(selectedDate);
        }
    }

    public void onDateRangeSelectionEnded(SelectedDate selectedDate) {
        if (this.mDaySelectionEventListener != null) {
            this.mDaySelectionEventListener.onDateRangeSelectionEnded(selectedDate);
        }
    }

    public void onDateRangeSelectionUpdated(SelectedDate selectedDate) {
        if (this.mDaySelectionEventListener != null) {
            this.mDaySelectionEventListener.onDateRangeSelectionUpdated(selectedDate);
        }
    }
}
