package com.github.sundeepk.compactcalendarview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;
import android.widget.OverScroller;
import com.github.sundeepk.compactcalendarview.domain.Event;
import com.google.android.flexbox.FlexItem;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

class CompactCalendarController {
    public static final int ANIMATE_INDICATORS = 3;
    private static final float ANIMATION_SCREEN_SET_DURATION_MILLIS = 700.0f;
    private static final int DAYS_IN_WEEK = 7;
    public static final int EXPAND_COLLAPSE_CALENDAR = 2;
    public static final int EXPOSE_CALENDAR_ANIMATION = 1;
    public static final int IDLE = 0;
    private static final int LAST_FLING_THRESHOLD_MILLIS = 300;
    private static final float SNAP_VELOCITY_DIP_PER_SECOND = 400.0f;
    private static final int VELOCITY_UNIT_PIXELS_PER_SECOND = 1000;
    private PointF accumulatedScrollOffset = new PointF();
    private int animationStatus = 0;
    private Paint background = new Paint();
    private float bigCircleIndicatorRadius;
    private Calendar calendarWithFirstDayOfMonth;
    private int calenderBackgroundColor = -1;
    private int calenderTextColor;
    private Calendar currentCalender;
    private Date currentDate = new Date();
    private int currentDayBackgroundColor;
    private int currentDayIndicatorStyle = 1;
    private int currentDayTextColor;
    private Direction currentDirection = Direction.NONE;
    private int currentSelectedDayBackgroundColor;
    private int currentSelectedDayIndicatorStyle = 1;
    private int currentSelectedDayTextColor;
    private String[] dayColumnNames;
    private Paint dayPaint = new Paint();
    private int densityAdjustedSnapVelocity;
    private boolean displayOtherMonthDays = false;
    private int distanceThresholdForAutoScroll;
    private float distanceX;
    private int eventIndicatorStyle = 3;
    private Calendar eventsCalendar;
    private EventsContainer eventsContainer;
    private int firstDayOfWeekToDraw = 2;
    private float growFactor = 0.0f;
    private float growfactorIndicator;
    private int height;
    private int heightPerDay;
    private boolean isRtl = false;
    private boolean isScrolling;
    private boolean isSmoothScrolling;
    private long lastAutoScrollFromFling;
    private CompactCalendarView$CompactCalendarViewListener listener;
    private Locale locale;
    private int maximumVelocity;
    private int monthsScrolledSoFar;
    private float multiDayIndicatorStrokeWidth;
    private int multiEventIndicatorColor;
    private int otherMonthDaysTextColor;
    private int paddingHeight = 40;
    private int paddingLeft;
    private int paddingRight;
    private int paddingWidth = 40;
    private float screenDensity = FlexItem.FLEX_SHRINK_DEFAULT;
    private OverScroller scroller;
    private boolean shouldDrawDaysHeader = true;
    private boolean shouldDrawIndicatorsBelowSelectedDays = false;
    private boolean shouldSelectFirstDayOfMonthOnScroll = true;
    private float smallIndicatorRadius;
    private int targetHeight;
    private Calendar tempPreviousMonthCalendar;
    private int textHeight;
    private int textSize = 30;
    private Rect textSizeRect;
    private int textWidth;
    private TimeZone timeZone;
    private Calendar todayCalender;
    private boolean useThreeLetterAbbreviation = false;
    private VelocityTracker velocityTracker = null;
    private int width;
    private int widthPerDay;
    private float xIndicatorOffset;

    private enum Direction {
        NONE,
        HORIZONTAL,
        VERTICAL
    }

    CompactCalendarController(Paint dayPaint, OverScroller scroller, Rect textSizeRect, AttributeSet attrs, Context context, int currentDayBackgroundColor, int calenderTextColor, int currentSelectedDayBackgroundColor, VelocityTracker velocityTracker, int multiEventIndicatorColor, EventsContainer eventsContainer, Locale locale, TimeZone timeZone) {
        this.dayPaint = dayPaint;
        this.scroller = scroller;
        this.textSizeRect = textSizeRect;
        this.currentDayBackgroundColor = currentDayBackgroundColor;
        this.calenderTextColor = calenderTextColor;
        this.currentSelectedDayBackgroundColor = currentSelectedDayBackgroundColor;
        this.otherMonthDaysTextColor = calenderTextColor;
        this.velocityTracker = velocityTracker;
        this.multiEventIndicatorColor = multiEventIndicatorColor;
        this.eventsContainer = eventsContainer;
        this.locale = locale;
        this.timeZone = timeZone;
        this.displayOtherMonthDays = false;
        loadAttributes(attrs, context);
        init(context);
    }

    private void loadAttributes(AttributeSet attrs, Context context) {
        if (attrs != null && context != null) {
            TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, C1505R.styleable.CompactCalendarView, 0, 0);
            try {
                this.currentDayBackgroundColor = typedArray.getColor(C1505R.styleable.CompactCalendarView_compactCalendarCurrentDayBackgroundColor, this.currentDayBackgroundColor);
                this.calenderTextColor = typedArray.getColor(C1505R.styleable.CompactCalendarView_compactCalendarTextColor, this.calenderTextColor);
                this.currentDayTextColor = typedArray.getColor(C1505R.styleable.CompactCalendarView_compactCalendarCurrentDayTextColor, this.calenderTextColor);
                this.otherMonthDaysTextColor = typedArray.getColor(C1505R.styleable.CompactCalendarView_compactCalendarOtherMonthDaysTextColor, this.otherMonthDaysTextColor);
                this.currentSelectedDayBackgroundColor = typedArray.getColor(C1505R.styleable.f189x265b7e5, this.currentSelectedDayBackgroundColor);
                this.currentSelectedDayTextColor = typedArray.getColor(C1505R.styleable.CompactCalendarView_compactCalendarCurrentSelectedDayTextColor, this.calenderTextColor);
                this.calenderBackgroundColor = typedArray.getColor(C1505R.styleable.CompactCalendarView_compactCalendarBackgroundColor, this.calenderBackgroundColor);
                this.multiEventIndicatorColor = typedArray.getColor(C1505R.styleable.CompactCalendarView_compactCalendarMultiEventIndicatorColor, this.multiEventIndicatorColor);
                this.textSize = typedArray.getDimensionPixelSize(C1505R.styleable.CompactCalendarView_compactCalendarTextSize, (int) TypedValue.applyDimension(2, (float) this.textSize, context.getResources().getDisplayMetrics()));
                this.targetHeight = typedArray.getDimensionPixelSize(C1505R.styleable.CompactCalendarView_compactCalendarTargetHeight, (int) TypedValue.applyDimension(1, (float) this.targetHeight, context.getResources().getDisplayMetrics()));
                this.eventIndicatorStyle = typedArray.getInt(C1505R.styleable.CompactCalendarView_compactCalendarEventIndicatorStyle, 3);
                this.currentDayIndicatorStyle = typedArray.getInt(C1505R.styleable.CompactCalendarView_compactCalendarCurrentDayIndicatorStyle, 1);
                this.currentSelectedDayIndicatorStyle = typedArray.getInt(C1505R.styleable.f190x6f05bdd2, 1);
                this.displayOtherMonthDays = typedArray.getBoolean(C1505R.styleable.CompactCalendarView_compactCalendarDisplayOtherMonthDays, this.displayOtherMonthDays);
                this.shouldSelectFirstDayOfMonthOnScroll = typedArray.getBoolean(C1505R.styleable.f191xb40ea192, this.shouldSelectFirstDayOfMonthOnScroll);
            } finally {
                typedArray.recycle();
            }
        }
    }

    private void init(Context context) {
        this.currentCalender = Calendar.getInstance(this.timeZone, this.locale);
        this.todayCalender = Calendar.getInstance(this.timeZone, this.locale);
        this.calendarWithFirstDayOfMonth = Calendar.getInstance(this.timeZone, this.locale);
        this.eventsCalendar = Calendar.getInstance(this.timeZone, this.locale);
        this.tempPreviousMonthCalendar = Calendar.getInstance(this.timeZone, this.locale);
        this.eventsCalendar.setMinimalDaysInFirstWeek(1);
        this.calendarWithFirstDayOfMonth.setMinimalDaysInFirstWeek(1);
        this.todayCalender.setMinimalDaysInFirstWeek(1);
        this.currentCalender.setMinimalDaysInFirstWeek(1);
        this.tempPreviousMonthCalendar.setMinimalDaysInFirstWeek(1);
        setFirstDayOfWeek(this.firstDayOfWeekToDraw);
        setUseWeekDayAbbreviation(false);
        this.dayPaint.setTextAlign(Align.CENTER);
        this.dayPaint.setStyle(Style.STROKE);
        this.dayPaint.setFlags(1);
        this.dayPaint.setTypeface(Typeface.SANS_SERIF);
        this.dayPaint.setTextSize((float) this.textSize);
        this.dayPaint.setColor(this.calenderTextColor);
        this.dayPaint.getTextBounds("31", 0, "31".length(), this.textSizeRect);
        this.textHeight = this.textSizeRect.height() * 3;
        this.textWidth = this.textSizeRect.width() * 2;
        this.todayCalender.setTime(new Date());
        setToMidnight(this.todayCalender);
        this.currentCalender.setTime(this.currentDate);
        setCalenderToFirstDayOfMonth(this.calendarWithFirstDayOfMonth, this.currentDate, -this.monthsScrolledSoFar, 0);
        initScreenDensityRelatedValues(context);
        this.xIndicatorOffset = 3.5f * this.screenDensity;
        this.smallIndicatorRadius = 2.5f * this.screenDensity;
        this.growFactor = 2.14748365E9f;
    }

    private void initScreenDensityRelatedValues(Context context) {
        if (context != null) {
            this.screenDensity = context.getResources().getDisplayMetrics().density;
            ViewConfiguration configuration = ViewConfiguration.get(context);
            this.densityAdjustedSnapVelocity = (int) (this.screenDensity * SNAP_VELOCITY_DIP_PER_SECOND);
            this.maximumVelocity = configuration.getScaledMaximumFlingVelocity();
            this.multiDayIndicatorStrokeWidth = TypedValue.applyDimension(1, FlexItem.FLEX_SHRINK_DEFAULT, context.getResources().getDisplayMetrics());
        }
    }

    private void setCalenderToFirstDayOfMonth(Calendar calendarWithFirstDayOfMonth, Date currentDate, int scrollOffset, int monthOffset) {
        setMonthOffset(calendarWithFirstDayOfMonth, currentDate, scrollOffset, monthOffset);
        calendarWithFirstDayOfMonth.set(5, 1);
    }

    private void setMonthOffset(Calendar calendarWithFirstDayOfMonth, Date currentDate, int scrollOffset, int monthOffset) {
        calendarWithFirstDayOfMonth.setTime(currentDate);
        calendarWithFirstDayOfMonth.add(2, scrollOffset + monthOffset);
        calendarWithFirstDayOfMonth.set(11, 0);
        calendarWithFirstDayOfMonth.set(12, 0);
        calendarWithFirstDayOfMonth.set(13, 0);
        calendarWithFirstDayOfMonth.set(14, 0);
    }

    void setIsRtl(boolean isRtl) {
        this.isRtl = isRtl;
    }

    void setShouldSelectFirstDayOfMonthOnScroll(boolean shouldSelectFirstDayOfMonthOnScroll) {
        this.shouldSelectFirstDayOfMonthOnScroll = shouldSelectFirstDayOfMonthOnScroll;
    }

    void setDisplayOtherMonthDays(boolean displayOtherMonthDays) {
        this.displayOtherMonthDays = displayOtherMonthDays;
    }

    void shouldDrawIndicatorsBelowSelectedDays(boolean shouldDrawIndicatorsBelowSelectedDays) {
        this.shouldDrawIndicatorsBelowSelectedDays = shouldDrawIndicatorsBelowSelectedDays;
    }

    void setCurrentDayIndicatorStyle(int currentDayIndicatorStyle) {
        this.currentDayIndicatorStyle = currentDayIndicatorStyle;
    }

    void setEventIndicatorStyle(int eventIndicatorStyle) {
        this.eventIndicatorStyle = eventIndicatorStyle;
    }

    void setCurrentSelectedDayIndicatorStyle(int currentSelectedDayIndicatorStyle) {
        this.currentSelectedDayIndicatorStyle = currentSelectedDayIndicatorStyle;
    }

    void setTargetHeight(int targetHeight) {
        this.targetHeight = targetHeight;
    }

    float getScreenDensity() {
        return this.screenDensity;
    }

    float getDayIndicatorRadius() {
        return this.bigCircleIndicatorRadius;
    }

    void setGrowFactorIndicator(float growfactorIndicator) {
        this.growfactorIndicator = growfactorIndicator;
    }

    float getGrowFactorIndicator() {
        return this.growfactorIndicator;
    }

    void setAnimationStatus(int animationStatus) {
        this.animationStatus = animationStatus;
    }

    int getTargetHeight() {
        return this.targetHeight;
    }

    int getWidth() {
        return this.width;
    }

    void setListener(CompactCalendarView$CompactCalendarViewListener listener) {
        this.listener = listener;
    }

    void removeAllEvents() {
        this.eventsContainer.removeAllEvents();
    }

    void setFirstDayOfWeek(int day) {
        if (day < 1 || day > 7) {
            throw new IllegalArgumentException("Day must be an int between 1 and 7 or DAY_OF_WEEK from Java Calendar class. For more information please see Calendar.DAY_OF_WEEK.");
        }
        this.firstDayOfWeekToDraw = day;
        setUseWeekDayAbbreviation(this.useThreeLetterAbbreviation);
        this.eventsCalendar.setFirstDayOfWeek(day);
        this.calendarWithFirstDayOfMonth.setFirstDayOfWeek(day);
        this.todayCalender.setFirstDayOfWeek(day);
        this.currentCalender.setFirstDayOfWeek(day);
        this.tempPreviousMonthCalendar.setFirstDayOfWeek(day);
    }

    void setCurrentSelectedDayBackgroundColor(int currentSelectedDayBackgroundColor) {
        this.currentSelectedDayBackgroundColor = currentSelectedDayBackgroundColor;
    }

    void setCurrentSelectedDayTextColor(int currentSelectedDayTextColor) {
        this.currentSelectedDayTextColor = currentSelectedDayTextColor;
    }

    void setCalenderBackgroundColor(int calenderBackgroundColor) {
        this.calenderBackgroundColor = calenderBackgroundColor;
    }

    void setCurrentDayBackgroundColor(int currentDayBackgroundColor) {
        this.currentDayBackgroundColor = currentDayBackgroundColor;
    }

    void setCurrentDayTextColor(int currentDayTextColor) {
        this.currentDayTextColor = currentDayTextColor;
    }

    void showNextMonth() {
        if (this.isRtl) {
            scrollPrev();
        } else {
            scrollNext();
        }
    }

    void showPreviousMonth() {
        if (this.isRtl) {
            scrollNext();
        } else {
            scrollPrev();
        }
    }

    private void scrollNext() {
        this.monthsScrolledSoFar--;
        this.accumulatedScrollOffset.x = (float) (this.monthsScrolledSoFar * this.width);
        if (this.shouldSelectFirstDayOfMonthOnScroll) {
            setCalenderToFirstDayOfMonth(this.calendarWithFirstDayOfMonth, this.currentCalender.getTime(), 0, 1);
            setCurrentDate(this.calendarWithFirstDayOfMonth.getTime());
        }
        performMonthScrollCallback();
    }

    private void scrollPrev() {
        this.monthsScrolledSoFar++;
        this.accumulatedScrollOffset.x = (float) (this.monthsScrolledSoFar * this.width);
        if (this.shouldSelectFirstDayOfMonthOnScroll) {
            setCalenderToFirstDayOfMonth(this.calendarWithFirstDayOfMonth, this.currentCalender.getTime(), 0, -1);
            setCurrentDate(this.calendarWithFirstDayOfMonth.getTime());
        }
        performMonthScrollCallback();
    }

    void setLocale(TimeZone timeZone, Locale locale) {
        if (locale == null) {
            throw new IllegalArgumentException("Locale cannot be null.");
        } else if (timeZone == null) {
            throw new IllegalArgumentException("TimeZone cannot be null.");
        } else {
            this.locale = locale;
            this.timeZone = timeZone;
            this.eventsContainer = new EventsContainer(Calendar.getInstance(this.timeZone, this.locale));
            init(null);
        }
    }

    void setUseWeekDayAbbreviation(boolean useThreeLetterAbbreviation) {
        this.useThreeLetterAbbreviation = useThreeLetterAbbreviation;
        this.dayColumnNames = WeekUtils.getWeekdayNames(this.locale, this.firstDayOfWeekToDraw, this.useThreeLetterAbbreviation);
    }

    void setDayColumnNames(String[] dayColumnNames) {
        if (dayColumnNames == null || dayColumnNames.length != 7) {
            throw new IllegalArgumentException("Column names cannot be null and must contain a value for each day of the week");
        }
        this.dayColumnNames = dayColumnNames;
    }

    void setShouldDrawDaysHeader(boolean shouldDrawDaysHeader) {
        this.shouldDrawDaysHeader = shouldDrawDaysHeader;
    }

    void onMeasure(int width, int height, int paddingRight, int paddingLeft) {
        this.widthPerDay = width / 7;
        this.heightPerDay = this.targetHeight > 0 ? this.targetHeight / 7 : height / 7;
        this.width = width;
        this.distanceThresholdForAutoScroll = (int) (((double) width) * 0.5d);
        this.height = height;
        this.paddingRight = paddingRight;
        this.paddingLeft = paddingLeft;
        this.bigCircleIndicatorRadius = getInterpolatedBigCircleIndicator();
        float f = (this.shouldDrawIndicatorsBelowSelectedDays && this.eventIndicatorStyle == 3) ? this.bigCircleIndicatorRadius * 0.85f : this.bigCircleIndicatorRadius;
        this.bigCircleIndicatorRadius = f;
    }

    private float getInterpolatedBigCircleIndicator() {
        float x0 = (float) this.textSizeRect.height();
        float x1 = (float) this.heightPerDay;
        double y0 = 0.5d * Math.sqrt((double) ((x0 * x0) + (x0 * x0)));
        return (float) ((((0.5d * Math.sqrt((double) ((x1 * x1) + (x1 * x1)))) - y0) * ((double) ((((((float) this.textSizeRect.height()) + x1) / 2.0f) - x0) / (x1 - x0)))) + y0);
    }

    void onDraw(Canvas canvas) {
        this.paddingWidth = this.widthPerDay / 2;
        this.paddingHeight = this.heightPerDay / 2;
        calculateXPositionOffset();
        if (this.animationStatus == 1) {
            drawCalendarWhileAnimating(canvas);
        } else if (this.animationStatus == 3) {
            drawCalendarWhileAnimatingIndicators(canvas);
        } else {
            drawCalenderBackground(canvas);
            drawScrollableCalender(canvas);
        }
    }

    private void drawCalendarWhileAnimatingIndicators(Canvas canvas) {
        this.dayPaint.setColor(this.calenderBackgroundColor);
        this.dayPaint.setStyle(Style.FILL);
        canvas.drawCircle(0.0f, 0.0f, this.growFactor, this.dayPaint);
        this.dayPaint.setStyle(Style.STROKE);
        this.dayPaint.setColor(-1);
        drawScrollableCalender(canvas);
    }

    private void drawCalendarWhileAnimating(Canvas canvas) {
        this.background.setColor(this.calenderBackgroundColor);
        this.background.setStyle(Style.FILL);
        canvas.drawCircle(0.0f, 0.0f, this.growFactor, this.background);
        this.dayPaint.setStyle(Style.STROKE);
        this.dayPaint.setColor(-1);
        drawScrollableCalender(canvas);
    }

    void onSingleTapUp(MotionEvent e) {
        if (!isScrolling()) {
            int dayColumn = Math.round((((((float) this.paddingLeft) + e.getX()) - ((float) this.paddingWidth)) - ((float) this.paddingRight)) / ((float) this.widthPerDay));
            int dayRow = Math.round((e.getY() - ((float) this.paddingHeight)) / ((float) this.heightPerDay));
            setCalenderToFirstDayOfMonth(this.calendarWithFirstDayOfMonth, this.currentDate, this.isRtl ? this.monthsScrolledSoFar : -this.monthsScrolledSoFar, 0);
            int dayOfMonth = ((dayRow - 1) * 7) - getDayOfWeek(this.calendarWithFirstDayOfMonth);
            if (this.isRtl) {
                dayOfMonth += 6 - dayColumn;
            } else {
                dayOfMonth += dayColumn;
            }
            if (dayOfMonth < this.calendarWithFirstDayOfMonth.getActualMaximum(5) && dayOfMonth >= 0) {
                this.calendarWithFirstDayOfMonth.add(5, dayOfMonth);
                this.currentCalender.setTimeInMillis(this.calendarWithFirstDayOfMonth.getTimeInMillis());
                performOnDayClickCallback(this.currentCalender.getTime());
            }
        }
    }

    private boolean isScrolling() {
        float scrolledX = Math.abs(this.accumulatedScrollOffset.x);
        int expectedScrollX = Math.abs(this.width * this.monthsScrolledSoFar);
        return scrolledX < ((float) (expectedScrollX + -5)) || scrolledX > ((float) (expectedScrollX + 5));
    }

    private void performOnDayClickCallback(Date date) {
        if (this.listener != null) {
            this.listener.onDayClick(date);
        }
    }

    boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        if (!this.isSmoothScrolling) {
            if (this.currentDirection == Direction.NONE) {
                if (Math.abs(distanceX) > Math.abs(distanceY)) {
                    this.currentDirection = Direction.HORIZONTAL;
                } else {
                    this.currentDirection = Direction.VERTICAL;
                }
            }
            this.isScrolling = true;
            this.distanceX = distanceX;
        }
        return true;
    }

    boolean onTouch(MotionEvent event) {
        if (this.velocityTracker == null) {
            this.velocityTracker = VelocityTracker.obtain();
        }
        this.velocityTracker.addMovement(event);
        if (event.getAction() == 0) {
            if (!this.scroller.isFinished()) {
                this.scroller.abortAnimation();
            }
            this.isSmoothScrolling = false;
        } else if (event.getAction() == 2) {
            this.velocityTracker.addMovement(event);
            this.velocityTracker.computeCurrentVelocity(500);
        } else if (event.getAction() == 1) {
            handleHorizontalScrolling();
            this.velocityTracker.recycle();
            this.velocityTracker.clear();
            this.velocityTracker = null;
            this.isScrolling = false;
        }
        return false;
    }

    private void snapBackScroller() {
        this.scroller.startScroll((int) this.accumulatedScrollOffset.x, 0, (int) (-(this.accumulatedScrollOffset.x - ((float) (this.monthsScrolledSoFar * this.width)))), 0);
    }

    private void handleHorizontalScrolling() {
        handleSmoothScrolling(computeVelocity());
        this.currentDirection = Direction.NONE;
        setCalenderToFirstDayOfMonth(this.calendarWithFirstDayOfMonth, this.currentDate, this.isRtl ? this.monthsScrolledSoFar : -this.monthsScrolledSoFar, 0);
        if (this.calendarWithFirstDayOfMonth.get(2) != this.currentCalender.get(2) && this.shouldSelectFirstDayOfMonthOnScroll) {
            setCalenderToFirstDayOfMonth(this.currentCalender, this.currentDate, this.isRtl ? this.monthsScrolledSoFar : -this.monthsScrolledSoFar, 0);
        }
    }

    private int computeVelocity() {
        this.velocityTracker.computeCurrentVelocity(1000, (float) this.maximumVelocity);
        return (int) this.velocityTracker.getXVelocity();
    }

    private void handleSmoothScrolling(int velocityX) {
        int distanceScrolled = (int) (this.accumulatedScrollOffset.x - ((float) (this.width * this.monthsScrolledSoFar)));
        boolean isEnoughTimeElapsedSinceLastSmoothScroll;
        if (System.currentTimeMillis() - this.lastAutoScrollFromFling > 300) {
            isEnoughTimeElapsedSinceLastSmoothScroll = true;
        } else {
            isEnoughTimeElapsedSinceLastSmoothScroll = false;
        }
        if (velocityX > this.densityAdjustedSnapVelocity && isEnoughTimeElapsedSinceLastSmoothScroll) {
            scrollPreviousMonth();
        } else if (velocityX < (-this.densityAdjustedSnapVelocity) && isEnoughTimeElapsedSinceLastSmoothScroll) {
            scrollNextMonth();
        } else if (this.isScrolling && distanceScrolled > this.distanceThresholdForAutoScroll) {
            scrollPreviousMonth();
        } else if (!this.isScrolling || distanceScrolled >= (-this.distanceThresholdForAutoScroll)) {
            this.isSmoothScrolling = false;
            snapBackScroller();
        } else {
            scrollNextMonth();
        }
    }

    private void scrollNextMonth() {
        this.lastAutoScrollFromFling = System.currentTimeMillis();
        this.monthsScrolledSoFar--;
        performScroll();
        this.isSmoothScrolling = true;
        performMonthScrollCallback();
    }

    private void scrollPreviousMonth() {
        this.lastAutoScrollFromFling = System.currentTimeMillis();
        this.monthsScrolledSoFar++;
        performScroll();
        this.isSmoothScrolling = true;
        performMonthScrollCallback();
    }

    private void performMonthScrollCallback() {
        if (this.listener != null) {
            this.listener.onMonthScroll(getFirstDayOfCurrentMonth());
        }
    }

    private void performScroll() {
        float remainingScrollAfterFingerLifted = ((float) (this.monthsScrolledSoFar * this.width)) - this.accumulatedScrollOffset.x;
        this.scroller.startScroll((int) this.accumulatedScrollOffset.x, 0, (int) remainingScrollAfterFingerLifted, 0, (int) ((((float) Math.abs((int) remainingScrollAfterFingerLifted)) / ((float) this.width)) * ANIMATION_SCREEN_SET_DURATION_MILLIS));
    }

    int getHeightPerDay() {
        return this.heightPerDay;
    }

    int getWeekNumberForCurrentMonth() {
        Calendar calendar = Calendar.getInstance(this.timeZone, this.locale);
        calendar.setTime(this.currentDate);
        return calendar.get(4);
    }

    Date getFirstDayOfCurrentMonth() {
        Calendar calendar = Calendar.getInstance(this.timeZone, this.locale);
        calendar.setTime(this.currentDate);
        calendar.add(2, this.isRtl ? this.monthsScrolledSoFar : -this.monthsScrolledSoFar);
        calendar.set(5, 1);
        setToMidnight(calendar);
        return calendar.getTime();
    }

    void setCurrentDate(Date dateTimeMonth) {
        this.distanceX = 0.0f;
        this.monthsScrolledSoFar = 0;
        this.accumulatedScrollOffset.x = 0.0f;
        this.scroller.startScroll(0, 0, 0, 0);
        this.currentDate = new Date(dateTimeMonth.getTime());
        this.currentCalender.setTime(this.currentDate);
        this.todayCalender = Calendar.getInstance(this.timeZone, this.locale);
        setToMidnight(this.currentCalender);
    }

    private void setToMidnight(Calendar calendar) {
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
    }

    void addEvent(Event event) {
        this.eventsContainer.addEvent(event);
    }

    void addEvents(List<Event> events) {
        this.eventsContainer.addEvents(events);
    }

    List<Event> getCalendarEventsFor(long epochMillis) {
        return this.eventsContainer.getEventsFor(epochMillis);
    }

    List<Event> getCalendarEventsForMonth(long epochMillis) {
        return this.eventsContainer.getEventsForMonth(epochMillis);
    }

    void removeEventsFor(long epochMillis) {
        this.eventsContainer.removeEventByEpochMillis(epochMillis);
    }

    void removeEvent(Event event) {
        this.eventsContainer.removeEvent(event);
    }

    void removeEvents(List<Event> events) {
        this.eventsContainer.removeEvents(events);
    }

    void setGrowProgress(float grow) {
        this.growFactor = grow;
    }

    float getGrowFactor() {
        return this.growFactor;
    }

    boolean onDown(MotionEvent e) {
        this.scroller.forceFinished(true);
        return true;
    }

    boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        this.scroller.forceFinished(true);
        return true;
    }

    boolean computeScroll() {
        if (!this.scroller.computeScrollOffset()) {
            return false;
        }
        this.accumulatedScrollOffset.x = (float) this.scroller.getCurrX();
        return true;
    }

    private void drawScrollableCalender(Canvas canvas) {
        if (this.isRtl) {
            drawNextMonth(canvas, -1);
            drawCurrentMonth(canvas);
            drawPreviousMonth(canvas, 1);
            return;
        }
        drawPreviousMonth(canvas, -1);
        drawCurrentMonth(canvas);
        drawNextMonth(canvas, 1);
    }

    private void drawNextMonth(Canvas canvas, int offset) {
        setCalenderToFirstDayOfMonth(this.calendarWithFirstDayOfMonth, this.currentDate, -this.monthsScrolledSoFar, offset);
        drawMonth(canvas, this.calendarWithFirstDayOfMonth, this.width * ((-this.monthsScrolledSoFar) + 1));
    }

    private void drawCurrentMonth(Canvas canvas) {
        setCalenderToFirstDayOfMonth(this.calendarWithFirstDayOfMonth, this.currentDate, this.isRtl ? this.monthsScrolledSoFar : -this.monthsScrolledSoFar, 0);
        drawMonth(canvas, this.calendarWithFirstDayOfMonth, this.width * (-this.monthsScrolledSoFar));
    }

    private void drawPreviousMonth(Canvas canvas, int offset) {
        setCalenderToFirstDayOfMonth(this.calendarWithFirstDayOfMonth, this.currentDate, -this.monthsScrolledSoFar, offset);
        drawMonth(canvas, this.calendarWithFirstDayOfMonth, this.width * ((-this.monthsScrolledSoFar) - 1));
    }

    private void calculateXPositionOffset() {
        if (this.currentDirection == Direction.HORIZONTAL) {
            PointF pointF = this.accumulatedScrollOffset;
            pointF.x -= this.distanceX;
        }
    }

    private void drawCalenderBackground(Canvas canvas) {
        this.dayPaint.setColor(this.calenderBackgroundColor);
        this.dayPaint.setStyle(Style.FILL);
        canvas.drawRect(0.0f, 0.0f, (float) this.width, (float) this.height, this.dayPaint);
        this.dayPaint.setStyle(Style.STROKE);
        this.dayPaint.setColor(this.calenderTextColor);
    }

    void drawEvents(Canvas canvas, Calendar currentMonthToDrawCalender, int offset) {
        int currentMonth = currentMonthToDrawCalender.get(2);
        List<Events> uniqEvents = this.eventsContainer.getEventsForMonthAndYear(currentMonth, currentMonthToDrawCalender.get(1));
        boolean shouldDrawCurrentDayCircle = currentMonth == this.todayCalender.get(2);
        boolean shouldDrawSelectedDayCircle = currentMonth == this.currentCalender.get(2);
        int todayDayOfMonth = this.todayCalender.get(5);
        int currentYear = this.todayCalender.get(1);
        int selectedDayOfMonth = this.currentCalender.get(5);
        float indicatorOffset = this.bigCircleIndicatorRadius / 2.0f;
        if (uniqEvents != null) {
            for (int i = 0; i < uniqEvents.size(); i++) {
                Events events = (Events) uniqEvents.get(i);
                this.eventsCalendar.setTimeInMillis(events.getTimeInMillis());
                int dayOfWeek = getDayOfWeek(this.eventsCalendar);
                if (this.isRtl) {
                    dayOfWeek = 6 - dayOfWeek;
                }
                float xPosition = ((((float) (((this.widthPerDay * dayOfWeek) + this.paddingWidth) + this.paddingLeft)) + this.accumulatedScrollOffset.x) + ((float) offset)) - ((float) this.paddingRight);
                float yPosition = (float) ((this.heightPerDay * this.eventsCalendar.get(4)) + this.paddingHeight);
                if ((!(this.animationStatus == 1 || this.animationStatus == 3) || xPosition < this.growFactor) && yPosition < this.growFactor && ((this.animationStatus != 2 || yPosition < this.growFactor) && !(this.animationStatus == 1 && (this.eventIndicatorStyle == 1 || this.eventIndicatorStyle == 2)))) {
                    List<Event> eventsList = events.getEvents();
                    int dayOfMonth = this.eventsCalendar.get(5);
                    boolean isSameDayAsCurrentDay = shouldDrawCurrentDayCircle && todayDayOfMonth == dayOfMonth && this.eventsCalendar.get(1) == currentYear;
                    boolean isCurrentSelectedDay = shouldDrawSelectedDayCircle && selectedDayOfMonth == dayOfMonth;
                    if (this.shouldDrawIndicatorsBelowSelectedDays || (!(this.shouldDrawIndicatorsBelowSelectedDays || isSameDayAsCurrentDay || isCurrentSelectedDay) || this.animationStatus == 1)) {
                        if (this.eventIndicatorStyle == 1 || this.eventIndicatorStyle == 2) {
                            drawEventIndicatorCircle(canvas, xPosition, yPosition, ((Event) eventsList.get(0)).getColor());
                        } else {
                            yPosition += indicatorOffset;
                            if (this.shouldDrawIndicatorsBelowSelectedDays && (isSameDayAsCurrentDay || isCurrentSelectedDay)) {
                                yPosition += indicatorOffset;
                            }
                            if (eventsList.size() >= 3) {
                                drawEventsWithPlus(canvas, xPosition, yPosition, eventsList);
                            } else if (eventsList.size() == 2) {
                                drawTwoEvents(canvas, xPosition, yPosition, eventsList);
                            } else if (eventsList.size() == 1) {
                                drawSingleEvent(canvas, xPosition, yPosition, eventsList);
                            }
                        }
                    }
                }
            }
        }
    }

    private void drawSingleEvent(Canvas canvas, float xPosition, float yPosition, List<Event> eventsList) {
        drawEventIndicatorCircle(canvas, xPosition, yPosition, ((Event) eventsList.get(0)).getColor());
    }

    private void drawTwoEvents(Canvas canvas, float xPosition, float yPosition, List<Event> eventsList) {
        drawEventIndicatorCircle(canvas, xPosition + (this.xIndicatorOffset * FlexItem.FLEX_BASIS_PERCENT_DEFAULT), yPosition, ((Event) eventsList.get(0)).getColor());
        drawEventIndicatorCircle(canvas, xPosition + (this.xIndicatorOffset * FlexItem.FLEX_SHRINK_DEFAULT), yPosition, ((Event) eventsList.get(1)).getColor());
    }

    private void drawEventsWithPlus(Canvas canvas, float xPosition, float yPosition, List<Event> eventsList) {
        int j = 0;
        int k = -2;
        while (j < 3) {
            Event event = (Event) eventsList.get(j);
            float xStartPosition = xPosition + (this.xIndicatorOffset * ((float) k));
            if (j == 2) {
                this.dayPaint.setColor(this.multiEventIndicatorColor);
                this.dayPaint.setStrokeWidth(this.multiDayIndicatorStrokeWidth);
                canvas.drawLine(xStartPosition - this.smallIndicatorRadius, yPosition, xStartPosition + this.smallIndicatorRadius, yPosition, this.dayPaint);
                canvas.drawLine(xStartPosition, yPosition - this.smallIndicatorRadius, xStartPosition, yPosition + this.smallIndicatorRadius, this.dayPaint);
                this.dayPaint.setStrokeWidth(0.0f);
            } else {
                drawEventIndicatorCircle(canvas, xStartPosition, yPosition, event.getColor());
            }
            j++;
            k += 2;
        }
    }

    int getDayOfWeek(Calendar calendar) {
        int dayOfWeek = calendar.get(7) - this.firstDayOfWeekToDraw;
        if (dayOfWeek < 0) {
            return dayOfWeek + 7;
        }
        return dayOfWeek;
    }

    void drawMonth(Canvas canvas, Calendar monthToDrawCalender, int offset) {
        drawEvents(canvas, monthToDrawCalender, offset);
        int firstDayOfMonth = getDayOfWeek(monthToDrawCalender);
        boolean isSameMonthAsToday = monthToDrawCalender.get(2) == this.todayCalender.get(2);
        boolean isSameYearAsToday = monthToDrawCalender.get(1) == this.todayCalender.get(1);
        boolean isSameMonthAsCurrentCalendar = monthToDrawCalender.get(2) == this.currentCalender.get(2) && monthToDrawCalender.get(1) == this.currentCalender.get(1);
        int todayDayOfMonth = this.todayCalender.get(5);
        boolean isAnimatingWithExpose = this.animationStatus == 1;
        int maximumMonthDay = monthToDrawCalender.getActualMaximum(5);
        this.tempPreviousMonthCalendar.setTimeInMillis(monthToDrawCalender.getTimeInMillis());
        this.tempPreviousMonthCalendar.add(2, -1);
        int maximumPreviousMonthDay = this.tempPreviousMonthCalendar.getActualMaximum(5);
        int dayColumn = 0;
        int colDirection = this.isRtl ? 6 : 0;
        int dayRow = 0;
        while (dayColumn <= 6) {
            if (dayRow == 7) {
                if (this.isRtl) {
                    colDirection--;
                } else {
                    colDirection++;
                }
                dayRow = 0;
                if (dayColumn <= 6) {
                    dayColumn++;
                }
            }
            if (dayColumn != this.dayColumnNames.length) {
                float xPosition = ((((float) (((this.widthPerDay * dayColumn) + this.paddingWidth) + this.paddingLeft)) + this.accumulatedScrollOffset.x) + ((float) offset)) - ((float) this.paddingRight);
                float yPosition = (float) ((this.heightPerDay * dayRow) + this.paddingHeight);
                if ((xPosition < this.growFactor || !(isAnimatingWithExpose || this.animationStatus == 3)) && yPosition < this.growFactor) {
                    if (dayRow != 0) {
                        int day = ((((dayRow - 1) * 7) + colDirection) + 1) - firstDayOfMonth;
                        int defaultCalenderTextColorToUse = this.calenderTextColor;
                        if (this.currentCalender.get(5) == day && isSameMonthAsCurrentCalendar && !isAnimatingWithExpose) {
                            drawDayCircleIndicator(this.currentSelectedDayIndicatorStyle, canvas, xPosition, yPosition, this.currentSelectedDayBackgroundColor);
                            defaultCalenderTextColorToUse = this.currentSelectedDayTextColor;
                        } else if (isSameYearAsToday && isSameMonthAsToday && todayDayOfMonth == day && !isAnimatingWithExpose) {
                            drawDayCircleIndicator(this.currentDayIndicatorStyle, canvas, xPosition, yPosition, this.currentDayBackgroundColor);
                            defaultCalenderTextColorToUse = this.currentDayTextColor;
                        }
                        if (day <= 0) {
                            if (this.displayOtherMonthDays) {
                                this.dayPaint.setStyle(Style.FILL);
                                this.dayPaint.setColor(this.otherMonthDaysTextColor);
                                canvas.drawText(String.valueOf(maximumPreviousMonthDay + day), xPosition, yPosition, this.dayPaint);
                            }
                        } else if (day <= maximumMonthDay) {
                            this.dayPaint.setStyle(Style.FILL);
                            this.dayPaint.setColor(defaultCalenderTextColorToUse);
                            canvas.drawText(String.valueOf(day), xPosition, yPosition, this.dayPaint);
                        } else if (this.displayOtherMonthDays) {
                            this.dayPaint.setStyle(Style.FILL);
                            this.dayPaint.setColor(this.otherMonthDaysTextColor);
                            canvas.drawText(String.valueOf(day - maximumMonthDay), xPosition, yPosition, this.dayPaint);
                        }
                    } else if (this.shouldDrawDaysHeader) {
                        this.dayPaint.setColor(this.calenderTextColor);
                        this.dayPaint.setTypeface(Typeface.DEFAULT_BOLD);
                        this.dayPaint.setStyle(Style.FILL);
                        this.dayPaint.setColor(this.calenderTextColor);
                        canvas.drawText(this.dayColumnNames[colDirection], xPosition, (float) this.paddingHeight, this.dayPaint);
                        this.dayPaint.setTypeface(Typeface.DEFAULT);
                    }
                }
                dayRow++;
            } else {
                return;
            }
        }
    }

    private void drawDayCircleIndicator(int indicatorStyle, Canvas canvas, float x, float y, int color) {
        drawDayCircleIndicator(indicatorStyle, canvas, x, y, color, FlexItem.FLEX_SHRINK_DEFAULT);
    }

    private void drawDayCircleIndicator(int indicatorStyle, Canvas canvas, float x, float y, int color, float circleScale) {
        float strokeWidth = this.dayPaint.getStrokeWidth();
        if (indicatorStyle == 2) {
            this.dayPaint.setStrokeWidth(2.0f * this.screenDensity);
            this.dayPaint.setStyle(Style.STROKE);
        } else {
            this.dayPaint.setStyle(Style.FILL);
        }
        drawCircle(canvas, x, y, color, circleScale);
        this.dayPaint.setStrokeWidth(strokeWidth);
        this.dayPaint.setStyle(Style.FILL);
    }

    private void drawCircle(Canvas canvas, float x, float y, int color, float circleScale) {
        this.dayPaint.setColor(color);
        if (this.animationStatus == 3) {
            float maxRadius = (this.bigCircleIndicatorRadius * circleScale) * 1.4f;
            if (this.growfactorIndicator <= maxRadius) {
                maxRadius = this.growfactorIndicator;
            }
            drawCircle(canvas, maxRadius, x, y - ((float) (this.textHeight / 6)));
            return;
        }
        drawCircle(canvas, this.bigCircleIndicatorRadius * circleScale, x, y - ((float) (this.textHeight / 6)));
    }

    private void drawEventIndicatorCircle(Canvas canvas, float x, float y, int color) {
        this.dayPaint.setColor(color);
        if (this.eventIndicatorStyle == 3) {
            this.dayPaint.setStyle(Style.FILL);
            drawCircle(canvas, this.smallIndicatorRadius, x, y);
        } else if (this.eventIndicatorStyle == 2) {
            this.dayPaint.setStyle(Style.STROKE);
            drawDayCircleIndicator(2, canvas, x, y, color);
        } else if (this.eventIndicatorStyle == 1) {
            drawDayCircleIndicator(1, canvas, x, y, color);
        }
    }

    private void drawCircle(Canvas canvas, float radius, float x, float y) {
        canvas.drawCircle(x, y, radius, this.dayPaint);
    }
}
