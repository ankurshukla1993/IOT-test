package com.github.sundeepk.compactcalendarview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v4.view.GestureDetectorCompat;
import android.util.AttributeSet;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.OverScroller;
import com.github.sundeepk.compactcalendarview.domain.Event;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class CompactCalendarView extends View {
    public static final int FILL_LARGE_INDICATOR = 1;
    public static final int NO_FILL_LARGE_INDICATOR = 2;
    public static final int SMALL_INDICATOR = 3;
    private final AnimationHandler animationHandler;
    private CompactCalendarController compactCalendarController;
    private GestureDetectorCompat gestureDetector;
    private final SimpleOnGestureListener gestureListener;
    private boolean shouldScroll;

    public CompactCalendarView(Context context) {
        this(context, null);
    }

    public CompactCalendarView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CompactCalendarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.shouldScroll = true;
        this.gestureListener = new 1(this);
        this.compactCalendarController = new CompactCalendarController(new Paint(), new OverScroller(getContext()), new Rect(), attrs, getContext(), Color.argb(255, 233, 84, 81), Color.argb(255, 64, 64, 64), Color.argb(255, 219, 219, 219), VelocityTracker.obtain(), Color.argb(255, 100, 68, 65), new EventsContainer(Calendar.getInstance()), Locale.getDefault(), TimeZone.getDefault());
        this.gestureDetector = new GestureDetectorCompat(getContext(), this.gestureListener);
        this.animationHandler = new AnimationHandler(this.compactCalendarController, this);
    }

    public void setAnimationListener(CompactCalendarAnimationListener compactCalendarAnimationListener) {
        this.animationHandler.setCompactCalendarAnimationListener(compactCalendarAnimationListener);
    }

    public void setLocale(TimeZone timeZone, Locale locale) {
        this.compactCalendarController.setLocale(timeZone, locale);
        invalidate();
    }

    public void setUseThreeLetterAbbreviation(boolean useThreeLetterAbbreviation) {
        this.compactCalendarController.setUseWeekDayAbbreviation(useThreeLetterAbbreviation);
        invalidate();
    }

    public void setCalendarBackgroundColor(int calenderBackgroundColor) {
        this.compactCalendarController.setCalenderBackgroundColor(calenderBackgroundColor);
        invalidate();
    }

    public void setDayColumnNames(String[] dayColumnNames) {
        this.compactCalendarController.setDayColumnNames(dayColumnNames);
    }

    public void setFirstDayOfWeek(int dayOfWeek) {
        this.compactCalendarController.setFirstDayOfWeek(dayOfWeek);
        invalidate();
    }

    public void setCurrentSelectedDayBackgroundColor(int currentSelectedDayBackgroundColor) {
        this.compactCalendarController.setCurrentSelectedDayBackgroundColor(currentSelectedDayBackgroundColor);
        invalidate();
    }

    public void setCurrentDayBackgroundColor(int currentDayBackgroundColor) {
        this.compactCalendarController.setCurrentDayBackgroundColor(currentDayBackgroundColor);
        invalidate();
    }

    public int getHeightPerDay() {
        return this.compactCalendarController.getHeightPerDay();
    }

    public void setListener(CompactCalendarViewListener listener) {
        this.compactCalendarController.setListener(listener);
    }

    public Date getFirstDayOfCurrentMonth() {
        return this.compactCalendarController.getFirstDayOfCurrentMonth();
    }

    public void shouldDrawIndicatorsBelowSelectedDays(boolean shouldDrawIndicatorsBelowSelectedDays) {
        this.compactCalendarController.shouldDrawIndicatorsBelowSelectedDays(shouldDrawIndicatorsBelowSelectedDays);
    }

    public void setCurrentDate(Date dateTimeMonth) {
        this.compactCalendarController.setCurrentDate(dateTimeMonth);
        invalidate();
    }

    public int getWeekNumberForCurrentMonth() {
        return this.compactCalendarController.getWeekNumberForCurrentMonth();
    }

    public void setShouldDrawDaysHeader(boolean shouldDrawDaysHeader) {
        this.compactCalendarController.setShouldDrawDaysHeader(shouldDrawDaysHeader);
    }

    public void setCurrentSelectedDayTextColor(int currentSelectedDayTextColor) {
        this.compactCalendarController.setCurrentSelectedDayTextColor(currentSelectedDayTextColor);
    }

    public void setCurrentDayTextColor(int currentDayTextColor) {
        this.compactCalendarController.setCurrentDayTextColor(currentDayTextColor);
    }

    @Deprecated
    public void addEvent(Event event) {
        addEvent(event, false);
    }

    public void addEvent(Event event, boolean shouldInvalidate) {
        this.compactCalendarController.addEvent(event);
        if (shouldInvalidate) {
            invalidate();
        }
    }

    public void addEvents(List<Event> events) {
        this.compactCalendarController.addEvents(events);
        invalidate();
    }

    public List<Event> getEvents(Date date) {
        return this.compactCalendarController.getCalendarEventsFor(date.getTime());
    }

    public List<Event> getEvents(long epochMillis) {
        return this.compactCalendarController.getCalendarEventsFor(epochMillis);
    }

    public List<Event> getEventsForMonth(long epochMillis) {
        return this.compactCalendarController.getCalendarEventsForMonth(epochMillis);
    }

    public List<Event> getEventsForMonth(Date date) {
        return this.compactCalendarController.getCalendarEventsForMonth(date.getTime());
    }

    public void removeEvents(Date date) {
        this.compactCalendarController.removeEventsFor(date.getTime());
    }

    public void removeEvents(long epochMillis) {
        this.compactCalendarController.removeEventsFor(epochMillis);
    }

    @Deprecated
    public void removeEvent(Event event) {
        removeEvent(event, false);
    }

    public void removeEvent(Event event, boolean shouldInvalidate) {
        this.compactCalendarController.removeEvent(event);
        if (shouldInvalidate) {
            invalidate();
        }
    }

    public void removeEvents(List<Event> events) {
        this.compactCalendarController.removeEvents(events);
        invalidate();
    }

    public void removeAllEvents() {
        this.compactCalendarController.removeAllEvents();
        invalidate();
    }

    public void setIsRtl(boolean isRtl) {
        this.compactCalendarController.setIsRtl(isRtl);
    }

    public void shouldSelectFirstDayOfMonthOnScroll(boolean shouldSelectFirstDayOfMonthOnScroll) {
        this.compactCalendarController.setShouldSelectFirstDayOfMonthOnScroll(shouldSelectFirstDayOfMonthOnScroll);
    }

    public void setCurrentSelectedDayIndicatorStyle(int currentSelectedDayIndicatorStyle) {
        this.compactCalendarController.setCurrentSelectedDayIndicatorStyle(currentSelectedDayIndicatorStyle);
        invalidate();
    }

    public void setCurrentDayIndicatorStyle(int currentDayIndicatorStyle) {
        this.compactCalendarController.setCurrentDayIndicatorStyle(currentDayIndicatorStyle);
        invalidate();
    }

    public void setEventIndicatorStyle(int eventIndicatorStyle) {
        this.compactCalendarController.setEventIndicatorStyle(eventIndicatorStyle);
        invalidate();
    }

    private void checkTargetHeight() {
        if (this.compactCalendarController.getTargetHeight() <= 0) {
            throw new IllegalStateException("Target height must be set in xml properties in order to expand/collapse CompactCalendar.");
        }
    }

    public void displayOtherMonthDays(boolean displayOtherMonthDays) {
        this.compactCalendarController.setDisplayOtherMonthDays(displayOtherMonthDays);
        invalidate();
    }

    public void setTargetHeight(int targetHeight) {
        this.compactCalendarController.setTargetHeight(targetHeight);
        checkTargetHeight();
    }

    public void showCalendar() {
        checkTargetHeight();
        this.animationHandler.openCalendar();
    }

    public void hideCalendar() {
        checkTargetHeight();
        this.animationHandler.closeCalendar();
    }

    public void showCalendarWithAnimation() {
        checkTargetHeight();
        this.animationHandler.openCalendarWithAnimation();
    }

    public void hideCalendarWithAnimation() {
        checkTargetHeight();
        this.animationHandler.closeCalendarWithAnimation();
    }

    public void showNextMonth() {
        this.compactCalendarController.showNextMonth();
        invalidate();
    }

    public void showPreviousMonth() {
        this.compactCalendarController.showPreviousMonth();
        invalidate();
    }

    public boolean isAnimating() {
        return this.animationHandler.isAnimating();
    }

    protected void onMeasure(int parentWidth, int parentHeight) {
        super.onMeasure(parentWidth, parentHeight);
        int width = MeasureSpec.getSize(parentWidth);
        int height = MeasureSpec.getSize(parentHeight);
        if (width > 0 && height > 0) {
            this.compactCalendarController.onMeasure(width, height, getPaddingRight(), getPaddingLeft());
        }
        setMeasuredDimension(width, height);
    }

    protected void onDraw(Canvas canvas) {
        this.compactCalendarController.onDraw(canvas);
    }

    public void computeScroll() {
        super.computeScroll();
        if (this.compactCalendarController.computeScroll()) {
            invalidate();
        }
    }

    public void shouldScrollMonth(boolean shouldDisableScroll) {
        this.shouldScroll = shouldDisableScroll;
    }

    public boolean onTouchEvent(MotionEvent event) {
        if (this.shouldScroll) {
            this.compactCalendarController.onTouch(event);
            invalidate();
        }
        if ((event.getAction() == 3 || event.getAction() == 1) && this.shouldScroll) {
            getParent().requestDisallowInterceptTouchEvent(false);
        }
        return this.gestureDetector.onTouchEvent(event);
    }

    public boolean canScrollHorizontally(int direction) {
        return true;
    }
}
