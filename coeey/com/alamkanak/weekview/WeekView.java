package com.alamkanak.weekview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region.Op;
import android.graphics.Typeface;
import android.os.Build.VERSION;
import android.support.annotation.Nullable;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.support.v7.widget.helper.ItemTouchHelper.Callback;
import android.text.Layout.Alignment;
import android.text.SpannableStringBuilder;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.text.format.DateFormat;
import android.text.style.StyleSpan;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.ScaleGestureDetector.OnScaleGestureListener;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.OverScroller;
import com.alamkanak.weekview.MonthLoader.MonthChangeListener;
import com.facebook.imageutils.JfifUtil;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

public class WeekView extends View {
    @Deprecated
    public static final int LENGTH_LONG = 2;
    @Deprecated
    public static final int LENGTH_SHORT = 1;
    private boolean mAreDimensionsInvalid;
    private int mColumnGap;
    private final Context mContext;
    private Direction mCurrentFlingDirection;
    private PointF mCurrentOrigin;
    private List<? extends WeekViewEvent> mCurrentPeriodEvents;
    private Direction mCurrentScrollDirection;
    private DateTimeInterpreter mDateTimeInterpreter;
    private int mDayBackgroundColor;
    private Paint mDayBackgroundPaint;
    @Deprecated
    private int mDayNameLength;
    private int mDefaultEventColor;
    private int mEffectiveMinHourHeight;
    private EmptyViewClickListener mEmptyViewClickListener;
    private EmptyViewLongPressListener mEmptyViewLongPressListener;
    private Paint mEventBackgroundPaint;
    private EventClickListener mEventClickListener;
    private int mEventCornerRadius;
    private EventLongPressListener mEventLongPressListener;
    private int mEventMarginVertical;
    private int mEventPadding;
    private List<EventRect> mEventRects;
    private int mEventTextColor;
    private TextPaint mEventTextPaint;
    private int mEventTextSize;
    private int mFetchedPeriod;
    private int mFirstDayOfWeek;
    private Calendar mFirstVisibleDay;
    private int mFutureBackgroundColor;
    private Paint mFutureBackgroundPaint;
    private int mFutureWeekendBackgroundColor;
    private Paint mFutureWeekendBackgroundPaint;
    private GestureDetectorCompat mGestureDetector;
    private final SimpleOnGestureListener mGestureListener;
    private Paint mHeaderBackgroundPaint;
    private int mHeaderColumnBackgroundColor;
    private Paint mHeaderColumnBackgroundPaint;
    private int mHeaderColumnPadding;
    private int mHeaderColumnTextColor;
    private float mHeaderColumnWidth;
    private float mHeaderMarginBottom;
    private int mHeaderRowBackgroundColor;
    private int mHeaderRowPadding;
    private float mHeaderTextHeight;
    private Paint mHeaderTextPaint;
    private boolean mHorizontalFlingEnabled;
    private int mHourHeight;
    private int mHourSeparatorColor;
    private int mHourSeparatorHeight;
    private Paint mHourSeparatorPaint;
    private boolean mIsFirstDraw;
    private boolean mIsZooming;
    private Calendar mLastVisibleDay;
    private int mMaxHourHeight;
    private int mMinHourHeight;
    private int mMinimumFlingVelocity;
    private int mNewHourHeight;
    private List<? extends WeekViewEvent> mNextPeriodEvents;
    private int mNowLineColor;
    private Paint mNowLinePaint;
    private int mNowLineThickness;
    private int mNumberOfVisibleDays;
    private int mOverlappingEventGap;
    private int mPastBackgroundColor;
    private Paint mPastBackgroundPaint;
    private int mPastWeekendBackgroundColor;
    private Paint mPastWeekendBackgroundPaint;
    private List<? extends WeekViewEvent> mPreviousPeriodEvents;
    private boolean mRefreshEvents;
    private ScaleGestureDetector mScaleDetector;
    private int mScaledTouchSlop;
    private ScrollListener mScrollListener;
    private Calendar mScrollToDay;
    private double mScrollToHour;
    private OverScroller mScroller;
    private boolean mShowDistinctPastFutureColor;
    private boolean mShowDistinctWeekendColor;
    private boolean mShowNowLine;
    private int mTextSize;
    private float mTimeTextHeight;
    private Paint mTimeTextPaint;
    private float mTimeTextWidth;
    private int mTodayBackgroundColor;
    private Paint mTodayBackgroundPaint;
    private int mTodayHeaderTextColor;
    private Paint mTodayHeaderTextPaint;
    private boolean mVerticalFlingEnabled;
    private WeekViewLoader mWeekViewLoader;
    private float mWidthPerDay;
    private float mXScrollingSpeed;

    class C05561 extends SimpleOnGestureListener {
        C05561() {
        }

        public boolean onDown(MotionEvent e) {
            WeekView.this.goToNearestOrigin();
            return true;
        }

        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            if (!WeekView.this.mIsZooming) {
                switch (WeekView.this.mCurrentScrollDirection) {
                    case NONE:
                        if (Math.abs(distanceX) > Math.abs(distanceY)) {
                            if (distanceX <= 0.0f) {
                                WeekView.this.mCurrentScrollDirection = Direction.RIGHT;
                                break;
                            }
                            WeekView.this.mCurrentScrollDirection = Direction.LEFT;
                            break;
                        }
                        WeekView.this.mCurrentScrollDirection = Direction.VERTICAL;
                        break;
                    case LEFT:
                        if (Math.abs(distanceX) > Math.abs(distanceY) && distanceX < ((float) (-WeekView.this.mScaledTouchSlop))) {
                            WeekView.this.mCurrentScrollDirection = Direction.RIGHT;
                            break;
                        }
                    case RIGHT:
                        if (Math.abs(distanceX) > Math.abs(distanceY) && distanceX > ((float) WeekView.this.mScaledTouchSlop)) {
                            WeekView.this.mCurrentScrollDirection = Direction.LEFT;
                            break;
                        }
                }
                PointF access$400;
                switch (WeekView.this.mCurrentScrollDirection) {
                    case LEFT:
                    case RIGHT:
                        access$400 = WeekView.this.mCurrentOrigin;
                        access$400.x -= WeekView.this.mXScrollingSpeed * distanceX;
                        ViewCompat.postInvalidateOnAnimation(WeekView.this);
                        break;
                    case VERTICAL:
                        access$400 = WeekView.this.mCurrentOrigin;
                        access$400.y -= distanceY;
                        ViewCompat.postInvalidateOnAnimation(WeekView.this);
                        break;
                    default:
                        break;
                }
            }
            return true;
        }

        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if (WeekView.this.mIsZooming) {
                return true;
            }
            if ((WeekView.this.mCurrentFlingDirection == Direction.LEFT && !WeekView.this.mHorizontalFlingEnabled) || ((WeekView.this.mCurrentFlingDirection == Direction.RIGHT && !WeekView.this.mHorizontalFlingEnabled) || (WeekView.this.mCurrentFlingDirection == Direction.VERTICAL && !WeekView.this.mVerticalFlingEnabled))) {
                return true;
            }
            WeekView.this.mScroller.forceFinished(true);
            WeekView.this.mCurrentFlingDirection = WeekView.this.mCurrentScrollDirection;
            switch (WeekView.this.mCurrentFlingDirection) {
                case LEFT:
                case RIGHT:
                    WeekView.this.mScroller.fling((int) WeekView.this.mCurrentOrigin.x, (int) WeekView.this.mCurrentOrigin.y, (int) (WeekView.this.mXScrollingSpeed * velocityX), 0, Integer.MIN_VALUE, Integer.MAX_VALUE, (int) (-(((((((float) (WeekView.this.mHourHeight * 24)) + WeekView.this.mHeaderTextHeight) + ((float) (WeekView.this.mHeaderRowPadding * 2))) + WeekView.this.mHeaderMarginBottom) + (WeekView.this.mTimeTextHeight / 2.0f)) - ((float) WeekView.this.getHeight()))), 0);
                    break;
                case VERTICAL:
                    WeekView.this.mScroller.fling((int) WeekView.this.mCurrentOrigin.x, (int) WeekView.this.mCurrentOrigin.y, 0, (int) velocityY, Integer.MIN_VALUE, Integer.MAX_VALUE, (int) (-(((((((float) (WeekView.this.mHourHeight * 24)) + WeekView.this.mHeaderTextHeight) + ((float) (WeekView.this.mHeaderRowPadding * 2))) + WeekView.this.mHeaderMarginBottom) + (WeekView.this.mTimeTextHeight / 2.0f)) - ((float) WeekView.this.getHeight()))), 0);
                    break;
            }
            ViewCompat.postInvalidateOnAnimation(WeekView.this);
            return true;
        }

        public boolean onSingleTapConfirmed(MotionEvent e) {
            if (!(WeekView.this.mEventRects == null || WeekView.this.mEventClickListener == null)) {
                List<EventRect> reversedEventRects = WeekView.this.mEventRects;
                Collections.reverse(reversedEventRects);
                for (EventRect event : reversedEventRects) {
                    if (event.rectF != null && e.getX() > event.rectF.left && e.getX() < event.rectF.right && e.getY() > event.rectF.top && e.getY() < event.rectF.bottom) {
                        WeekView.this.mEventClickListener.onEventClick(event.originalEvent, event.rectF);
                        WeekView.this.playSoundEffect(0);
                        return super.onSingleTapConfirmed(e);
                    }
                }
            }
            if (WeekView.this.mEmptyViewClickListener != null && e.getX() > WeekView.this.mHeaderColumnWidth && e.getY() > (WeekView.this.mHeaderTextHeight + ((float) (WeekView.this.mHeaderRowPadding * 2))) + WeekView.this.mHeaderMarginBottom) {
                Calendar selectedTime = WeekView.this.getTimeFromPoint(e.getX(), e.getY());
                if (selectedTime != null) {
                    WeekView.this.playSoundEffect(0);
                    WeekView.this.mEmptyViewClickListener.onEmptyViewClicked(selectedTime);
                }
            }
            return super.onSingleTapConfirmed(e);
        }

        public void onLongPress(MotionEvent e) {
            super.onLongPress(e);
            if (!(WeekView.this.mEventLongPressListener == null || WeekView.this.mEventRects == null)) {
                List<EventRect> reversedEventRects = WeekView.this.mEventRects;
                Collections.reverse(reversedEventRects);
                for (EventRect event : reversedEventRects) {
                    if (event.rectF != null && e.getX() > event.rectF.left && e.getX() < event.rectF.right && e.getY() > event.rectF.top && e.getY() < event.rectF.bottom) {
                        WeekView.this.mEventLongPressListener.onEventLongPress(event.originalEvent, event.rectF);
                        WeekView.this.performHapticFeedback(0);
                        return;
                    }
                }
            }
            if (WeekView.this.mEmptyViewLongPressListener != null && e.getX() > WeekView.this.mHeaderColumnWidth && e.getY() > (WeekView.this.mHeaderTextHeight + ((float) (WeekView.this.mHeaderRowPadding * 2))) + WeekView.this.mHeaderMarginBottom) {
                Calendar selectedTime = WeekView.this.getTimeFromPoint(e.getX(), e.getY());
                if (selectedTime != null) {
                    WeekView.this.performHapticFeedback(0);
                    WeekView.this.mEmptyViewLongPressListener.onEmptyViewLongPress(selectedTime);
                }
            }
        }
    }

    class C05572 implements OnScaleGestureListener {
        C05572() {
        }

        public void onScaleEnd(ScaleGestureDetector detector) {
            WeekView.this.mIsZooming = false;
        }

        public boolean onScaleBegin(ScaleGestureDetector detector) {
            WeekView.this.mIsZooming = true;
            WeekView.this.goToNearestOrigin();
            return true;
        }

        public boolean onScale(ScaleGestureDetector detector) {
            WeekView.this.mNewHourHeight = Math.round(((float) WeekView.this.mHourHeight) * detector.getScaleFactor());
            WeekView.this.invalidate();
            return true;
        }
    }

    class C05583 implements Comparator<WeekViewEvent> {
        C05583() {
        }

        public int compare(WeekViewEvent event1, WeekViewEvent event2) {
            long start1 = event1.getStartTime().getTimeInMillis();
            long start2 = event2.getStartTime().getTimeInMillis();
            int comparator = start1 > start2 ? 1 : start1 < start2 ? -1 : 0;
            if (comparator != 0) {
                return comparator;
            }
            long end1 = event1.getEndTime().getTimeInMillis();
            long end2 = event2.getEndTime().getTimeInMillis();
            if (end1 > end2) {
                return 1;
            }
            return end1 < end2 ? -1 : 0;
        }
    }

    class C05594 implements DateTimeInterpreter {
        C05594() {
        }

        public String interpretDate(Calendar date) {
            try {
                return (WeekView.this.mDayNameLength == 1 ? new SimpleDateFormat("EEEEE M/dd", Locale.getDefault()) : new SimpleDateFormat("EEE M/dd", Locale.getDefault())).format(date.getTime()).toUpperCase();
            } catch (Exception e) {
                e.printStackTrace();
                return "";
            }
        }

        public String interpretTime(int hour) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(11, hour);
            calendar.set(12, 0);
            try {
                return (DateFormat.is24HourFormat(WeekView.this.getContext()) ? new SimpleDateFormat("HH:mm", Locale.getDefault()) : new SimpleDateFormat("hh a", Locale.getDefault())).format(calendar.getTime());
            } catch (Exception e) {
                e.printStackTrace();
                return "";
            }
        }
    }

    private enum Direction {
        NONE,
        LEFT,
        RIGHT,
        VERTICAL
    }

    public interface EmptyViewClickListener {
        void onEmptyViewClicked(Calendar calendar);
    }

    public interface EmptyViewLongPressListener {
        void onEmptyViewLongPress(Calendar calendar);
    }

    public interface EventClickListener {
        void onEventClick(WeekViewEvent weekViewEvent, RectF rectF);
    }

    public interface EventLongPressListener {
        void onEventLongPress(WeekViewEvent weekViewEvent, RectF rectF);
    }

    private class EventRect {
        public float bottom;
        public WeekViewEvent event;
        public float left;
        public WeekViewEvent originalEvent;
        public RectF rectF;
        public float top;
        public float width;

        public EventRect(WeekViewEvent event, WeekViewEvent originalEvent, RectF rectF) {
            this.event = event;
            this.rectF = rectF;
            this.originalEvent = originalEvent;
        }
    }

    public interface ScrollListener {
        void onFirstVisibleDayChanged(Calendar calendar, Calendar calendar2);
    }

    public WeekView(Context context) {
        this(context, null);
    }

    public WeekView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WeekView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mCurrentOrigin = new PointF(0.0f, 0.0f);
        this.mCurrentScrollDirection = Direction.NONE;
        this.mFetchedPeriod = -1;
        this.mRefreshEvents = false;
        this.mCurrentFlingDirection = Direction.NONE;
        this.mMinimumFlingVelocity = 0;
        this.mScaledTouchSlop = 0;
        this.mHourHeight = 50;
        this.mNewHourHeight = -1;
        this.mMinHourHeight = 0;
        this.mEffectiveMinHourHeight = this.mMinHourHeight;
        this.mMaxHourHeight = Callback.DEFAULT_SWIPE_ANIMATION_DURATION;
        this.mColumnGap = 10;
        this.mFirstDayOfWeek = 2;
        this.mTextSize = 12;
        this.mHeaderColumnPadding = 10;
        this.mHeaderColumnTextColor = ViewCompat.MEASURED_STATE_MASK;
        this.mNumberOfVisibleDays = 3;
        this.mHeaderRowPadding = 10;
        this.mHeaderRowBackgroundColor = -1;
        this.mDayBackgroundColor = Color.rgb(245, 245, 245);
        this.mPastBackgroundColor = Color.rgb(227, 227, 227);
        this.mFutureBackgroundColor = Color.rgb(245, 245, 245);
        this.mPastWeekendBackgroundColor = 0;
        this.mFutureWeekendBackgroundColor = 0;
        this.mNowLineColor = Color.rgb(102, 102, 102);
        this.mNowLineThickness = 5;
        this.mHourSeparatorColor = Color.rgb(230, 230, 230);
        this.mTodayBackgroundColor = Color.rgb(239, 247, 254);
        this.mHourSeparatorHeight = 2;
        this.mTodayHeaderTextColor = Color.rgb(39, 137, 228);
        this.mEventTextSize = 12;
        this.mEventTextColor = ViewCompat.MEASURED_STATE_MASK;
        this.mEventPadding = 8;
        this.mHeaderColumnBackgroundColor = -1;
        this.mIsFirstDraw = true;
        this.mAreDimensionsInvalid = true;
        this.mDayNameLength = 2;
        this.mOverlappingEventGap = 0;
        this.mEventMarginVertical = 0;
        this.mXScrollingSpeed = FlexItem.FLEX_SHRINK_DEFAULT;
        this.mScrollToDay = null;
        this.mScrollToHour = -1.0d;
        this.mEventCornerRadius = 0;
        this.mShowDistinctWeekendColor = false;
        this.mShowNowLine = false;
        this.mShowDistinctPastFutureColor = false;
        this.mHorizontalFlingEnabled = true;
        this.mVerticalFlingEnabled = true;
        this.mGestureListener = new C05561();
        this.mContext = context;
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, C0555R.styleable.WeekView, 0, 0);
        try {
            this.mFirstDayOfWeek = a.getInteger(C0555R.styleable.WeekView_firstDayOfWeek, this.mFirstDayOfWeek);
            this.mHourHeight = a.getDimensionPixelSize(C0555R.styleable.WeekView_hourHeight, this.mHourHeight);
            this.mMinHourHeight = a.getDimensionPixelSize(C0555R.styleable.WeekView_minHourHeight, this.mMinHourHeight);
            this.mEffectiveMinHourHeight = this.mMinHourHeight;
            this.mMaxHourHeight = a.getDimensionPixelSize(C0555R.styleable.WeekView_maxHourHeight, this.mMaxHourHeight);
            this.mTextSize = a.getDimensionPixelSize(C0555R.styleable.WeekView_textSize, (int) TypedValue.applyDimension(2, (float) this.mTextSize, context.getResources().getDisplayMetrics()));
            this.mHeaderColumnPadding = a.getDimensionPixelSize(C0555R.styleable.WeekView_headerColumnPadding, this.mHeaderColumnPadding);
            this.mColumnGap = a.getDimensionPixelSize(C0555R.styleable.WeekView_columnGap, this.mColumnGap);
            this.mHeaderColumnTextColor = a.getColor(C0555R.styleable.WeekView_headerColumnTextColor, this.mHeaderColumnTextColor);
            this.mNumberOfVisibleDays = a.getInteger(C0555R.styleable.WeekView_noOfVisibleDays, this.mNumberOfVisibleDays);
            this.mHeaderRowPadding = a.getDimensionPixelSize(C0555R.styleable.WeekView_headerRowPadding, this.mHeaderRowPadding);
            this.mHeaderRowBackgroundColor = a.getColor(C0555R.styleable.WeekView_headerRowBackgroundColor, this.mHeaderRowBackgroundColor);
            this.mDayBackgroundColor = a.getColor(C0555R.styleable.WeekView_dayBackgroundColor, this.mDayBackgroundColor);
            this.mFutureBackgroundColor = a.getColor(C0555R.styleable.WeekView_futureBackgroundColor, this.mFutureBackgroundColor);
            this.mPastBackgroundColor = a.getColor(C0555R.styleable.WeekView_pastBackgroundColor, this.mPastBackgroundColor);
            this.mFutureWeekendBackgroundColor = a.getColor(C0555R.styleable.WeekView_futureWeekendBackgroundColor, this.mFutureBackgroundColor);
            this.mPastWeekendBackgroundColor = a.getColor(C0555R.styleable.WeekView_pastWeekendBackgroundColor, this.mPastBackgroundColor);
            this.mNowLineColor = a.getColor(C0555R.styleable.WeekView_nowLineColor, this.mNowLineColor);
            this.mNowLineThickness = a.getDimensionPixelSize(C0555R.styleable.WeekView_nowLineThickness, this.mNowLineThickness);
            this.mHourSeparatorColor = a.getColor(C0555R.styleable.WeekView_hourSeparatorColor, this.mHourSeparatorColor);
            this.mTodayBackgroundColor = a.getColor(C0555R.styleable.WeekView_todayBackgroundColor, this.mTodayBackgroundColor);
            this.mHourSeparatorHeight = a.getDimensionPixelSize(C0555R.styleable.WeekView_hourSeparatorHeight, this.mHourSeparatorHeight);
            this.mTodayHeaderTextColor = a.getColor(C0555R.styleable.WeekView_todayHeaderTextColor, this.mTodayHeaderTextColor);
            this.mEventTextSize = a.getDimensionPixelSize(C0555R.styleable.WeekView_eventTextSize, (int) TypedValue.applyDimension(2, (float) this.mEventTextSize, context.getResources().getDisplayMetrics()));
            this.mEventTextColor = a.getColor(C0555R.styleable.WeekView_eventTextColor, this.mEventTextColor);
            this.mEventPadding = a.getDimensionPixelSize(C0555R.styleable.WeekView_eventPadding, this.mEventPadding);
            this.mHeaderColumnBackgroundColor = a.getColor(C0555R.styleable.WeekView_headerColumnBackground, this.mHeaderColumnBackgroundColor);
            this.mDayNameLength = a.getInteger(C0555R.styleable.WeekView_dayNameLength, this.mDayNameLength);
            this.mOverlappingEventGap = a.getDimensionPixelSize(C0555R.styleable.WeekView_overlappingEventGap, this.mOverlappingEventGap);
            this.mEventMarginVertical = a.getDimensionPixelSize(C0555R.styleable.WeekView_eventMarginVertical, this.mEventMarginVertical);
            this.mXScrollingSpeed = a.getFloat(C0555R.styleable.WeekView_xScrollingSpeed, this.mXScrollingSpeed);
            this.mEventCornerRadius = a.getDimensionPixelSize(C0555R.styleable.WeekView_eventCornerRadius, this.mEventCornerRadius);
            this.mShowDistinctPastFutureColor = a.getBoolean(C0555R.styleable.WeekView_showDistinctPastFutureColor, this.mShowDistinctPastFutureColor);
            this.mShowDistinctWeekendColor = a.getBoolean(C0555R.styleable.WeekView_showDistinctWeekendColor, this.mShowDistinctWeekendColor);
            this.mShowNowLine = a.getBoolean(C0555R.styleable.WeekView_showNowLine, this.mShowNowLine);
            this.mHorizontalFlingEnabled = a.getBoolean(C0555R.styleable.WeekView_horizontalFlingEnabled, this.mHorizontalFlingEnabled);
            this.mVerticalFlingEnabled = a.getBoolean(C0555R.styleable.WeekView_verticalFlingEnabled, this.mVerticalFlingEnabled);
            init();
        } finally {
            a.recycle();
        }
    }

    private void init() {
        this.mGestureDetector = new GestureDetectorCompat(this.mContext, this.mGestureListener);
        this.mScroller = new OverScroller(this.mContext, new FastOutLinearInInterpolator());
        this.mMinimumFlingVelocity = ViewConfiguration.get(this.mContext).getScaledMinimumFlingVelocity();
        this.mScaledTouchSlop = ViewConfiguration.get(this.mContext).getScaledTouchSlop();
        this.mTimeTextPaint = new Paint(1);
        this.mTimeTextPaint.setTextAlign(Align.RIGHT);
        this.mTimeTextPaint.setTextSize((float) this.mTextSize);
        this.mTimeTextPaint.setColor(this.mHeaderColumnTextColor);
        Rect rect = new Rect();
        this.mTimeTextPaint.getTextBounds("00 PM", 0, "00 PM".length(), rect);
        this.mTimeTextHeight = (float) rect.height();
        this.mHeaderMarginBottom = this.mTimeTextHeight / 2.0f;
        initTextTimeWidth();
        this.mHeaderTextPaint = new Paint(1);
        this.mHeaderTextPaint.setColor(this.mHeaderColumnTextColor);
        this.mHeaderTextPaint.setTextAlign(Align.CENTER);
        this.mHeaderTextPaint.setTextSize((float) this.mTextSize);
        this.mHeaderTextPaint.getTextBounds("00 PM", 0, "00 PM".length(), rect);
        this.mHeaderTextHeight = (float) rect.height();
        this.mHeaderTextPaint.setTypeface(Typeface.DEFAULT_BOLD);
        this.mHeaderBackgroundPaint = new Paint();
        this.mHeaderBackgroundPaint.setColor(this.mHeaderRowBackgroundColor);
        this.mDayBackgroundPaint = new Paint();
        this.mDayBackgroundPaint.setColor(this.mDayBackgroundColor);
        this.mFutureBackgroundPaint = new Paint();
        this.mFutureBackgroundPaint.setColor(this.mFutureBackgroundColor);
        this.mPastBackgroundPaint = new Paint();
        this.mPastBackgroundPaint.setColor(this.mPastBackgroundColor);
        this.mFutureWeekendBackgroundPaint = new Paint();
        this.mFutureWeekendBackgroundPaint.setColor(this.mFutureWeekendBackgroundColor);
        this.mPastWeekendBackgroundPaint = new Paint();
        this.mPastWeekendBackgroundPaint.setColor(this.mPastWeekendBackgroundColor);
        this.mHourSeparatorPaint = new Paint();
        this.mHourSeparatorPaint.setStyle(Style.STROKE);
        this.mHourSeparatorPaint.setStrokeWidth((float) this.mHourSeparatorHeight);
        this.mHourSeparatorPaint.setColor(this.mHourSeparatorColor);
        this.mNowLinePaint = new Paint();
        this.mNowLinePaint.setStrokeWidth((float) this.mNowLineThickness);
        this.mNowLinePaint.setColor(this.mNowLineColor);
        this.mTodayBackgroundPaint = new Paint();
        this.mTodayBackgroundPaint.setColor(this.mTodayBackgroundColor);
        this.mTodayHeaderTextPaint = new Paint(1);
        this.mTodayHeaderTextPaint.setTextAlign(Align.CENTER);
        this.mTodayHeaderTextPaint.setTextSize((float) this.mTextSize);
        this.mTodayHeaderTextPaint.setTypeface(Typeface.DEFAULT_BOLD);
        this.mTodayHeaderTextPaint.setColor(this.mTodayHeaderTextColor);
        this.mEventBackgroundPaint = new Paint();
        this.mEventBackgroundPaint.setColor(Color.rgb(174, JfifUtil.MARKER_RST0, 238));
        this.mHeaderColumnBackgroundPaint = new Paint();
        this.mHeaderColumnBackgroundPaint.setColor(this.mHeaderColumnBackgroundColor);
        this.mEventTextPaint = new TextPaint(65);
        this.mEventTextPaint.setStyle(Style.FILL);
        this.mEventTextPaint.setColor(this.mEventTextColor);
        this.mEventTextPaint.setTextSize((float) this.mEventTextSize);
        this.mDefaultEventColor = Color.parseColor("#9fc6e7");
        this.mScaleDetector = new ScaleGestureDetector(this.mContext, new C05572());
    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.mAreDimensionsInvalid = true;
    }

    private void initTextTimeWidth() {
        this.mTimeTextWidth = 0.0f;
        for (int i = 0; i < 24; i++) {
            String time = getDateTimeInterpreter().interpretTime(i);
            if (time == null) {
                throw new IllegalStateException("A DateTimeInterpreter must not return null time");
            }
            this.mTimeTextWidth = Math.max(this.mTimeTextWidth, this.mTimeTextPaint.measureText(time));
        }
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(0.0f, 0.0f, this.mTimeTextWidth + ((float) (this.mHeaderColumnPadding * 2)), this.mHeaderTextHeight + ((float) (this.mHeaderRowPadding * 2)), this.mHeaderBackgroundPaint);
        drawHeaderRowAndEvents(canvas);
        drawTimeColumnAndAxes(canvas);
    }

    private void drawTimeColumnAndAxes(Canvas canvas) {
        Canvas canvas2 = canvas;
        canvas2.drawRect(0.0f, ((float) (this.mHeaderRowPadding * 2)) + this.mHeaderTextHeight, this.mHeaderColumnWidth, (float) getHeight(), this.mHeaderColumnBackgroundPaint);
        canvas2 = canvas;
        canvas2.clipRect(0.0f, ((float) (this.mHeaderRowPadding * 2)) + this.mHeaderTextHeight, this.mHeaderColumnWidth, (float) getHeight(), Op.REPLACE);
        for (int i = 0; i < 24; i++) {
            float top = (((this.mHeaderTextHeight + ((float) (this.mHeaderRowPadding * 2))) + this.mCurrentOrigin.y) + ((float) (this.mHourHeight * i))) + this.mHeaderMarginBottom;
            String time = getDateTimeInterpreter().interpretTime(i);
            if (time == null) {
                throw new IllegalStateException("A DateTimeInterpreter must not return null time");
            }
            if (top < ((float) getHeight())) {
                canvas.drawText(time, this.mTimeTextWidth + ((float) this.mHeaderColumnPadding), this.mTimeTextHeight + top, this.mTimeTextPaint);
            }
        }
    }

    private void drawHeaderRowAndEvents(Canvas canvas) {
        this.mHeaderColumnWidth = this.mTimeTextWidth + ((float) (this.mHeaderColumnPadding * 2));
        this.mWidthPerDay = (((float) getWidth()) - this.mHeaderColumnWidth) - ((float) (this.mColumnGap * (this.mNumberOfVisibleDays - 1)));
        this.mWidthPerDay /= (float) this.mNumberOfVisibleDays;
        Calendar today = today();
        if (this.mAreDimensionsInvalid) {
            this.mEffectiveMinHourHeight = Math.max(this.mMinHourHeight, (int) ((((((float) getHeight()) - this.mHeaderTextHeight) - ((float) (this.mHeaderRowPadding * 2))) - this.mHeaderMarginBottom) / 24.0f));
            this.mAreDimensionsInvalid = false;
            if (this.mScrollToDay != null) {
                goToDate(this.mScrollToDay);
            }
            this.mAreDimensionsInvalid = false;
            if (this.mScrollToHour >= 0.0d) {
                goToHour(this.mScrollToHour);
            }
            this.mScrollToDay = null;
            this.mScrollToHour = -1.0d;
            this.mAreDimensionsInvalid = false;
        }
        if (this.mIsFirstDraw) {
            this.mIsFirstDraw = false;
            if (this.mNumberOfVisibleDays >= 7 && today.get(7) != this.mFirstDayOfWeek) {
                int difference = (today.get(7) - this.mFirstDayOfWeek) + 7;
                PointF pointF = this.mCurrentOrigin;
                pointF.x += (this.mWidthPerDay + ((float) this.mColumnGap)) * ((float) difference);
            }
        }
        if (this.mNewHourHeight > 0) {
            if (this.mNewHourHeight < this.mEffectiveMinHourHeight) {
                this.mNewHourHeight = this.mEffectiveMinHourHeight;
            } else if (this.mNewHourHeight > this.mMaxHourHeight) {
                this.mNewHourHeight = this.mMaxHourHeight;
            }
            this.mCurrentOrigin.y = (this.mCurrentOrigin.y / ((float) this.mHourHeight)) * ((float) this.mNewHourHeight);
            this.mHourHeight = this.mNewHourHeight;
            this.mNewHourHeight = -1;
        }
        if (this.mCurrentOrigin.y < (((((float) (getHeight() - (this.mHourHeight * 24))) - this.mHeaderTextHeight) - ((float) (this.mHeaderRowPadding * 2))) - this.mHeaderMarginBottom) - (this.mTimeTextHeight / 2.0f)) {
            this.mCurrentOrigin.y = (((((float) (getHeight() - (this.mHourHeight * 24))) - this.mHeaderTextHeight) - ((float) (this.mHeaderRowPadding * 2))) - this.mHeaderMarginBottom) - (this.mTimeTextHeight / 2.0f);
        }
        if (this.mCurrentOrigin.y > 0.0f) {
            this.mCurrentOrigin.y = 0.0f;
        }
        int leftDaysWithGaps = (int) (-Math.ceil((double) (this.mCurrentOrigin.x / (this.mWidthPerDay + ((float) this.mColumnGap)))));
        float startFromPixel = (this.mCurrentOrigin.x + ((this.mWidthPerDay + ((float) this.mColumnGap)) * ((float) leftDaysWithGaps))) + this.mHeaderColumnWidth;
        float startPixel = startFromPixel;
        ((Calendar) today.clone()).add(10, 6);
        float[] hourLines = new float[(((((int) ((((((float) getHeight()) - this.mHeaderTextHeight) - ((float) (this.mHeaderRowPadding * 2))) - this.mHeaderMarginBottom) / ((float) this.mHourHeight))) + 1) * (this.mNumberOfVisibleDays + 1)) * 4)];
        if (this.mEventRects != null) {
            for (EventRect eventRect : this.mEventRects) {
                eventRect.rectF = null;
            }
        }
        canvas.clipRect(this.mHeaderColumnWidth, ((this.mHeaderTextHeight + ((float) (this.mHeaderRowPadding * 2))) + this.mHeaderMarginBottom) + (this.mTimeTextHeight / 2.0f), (float) getWidth(), (float) getHeight(), Op.REPLACE);
        Calendar oldFirstVisibleDay = this.mFirstVisibleDay;
        this.mFirstVisibleDay = (Calendar) today.clone();
        this.mFirstVisibleDay.add(5, -Math.round(this.mCurrentOrigin.x / (this.mWidthPerDay + ((float) this.mColumnGap))));
        if (!(this.mFirstVisibleDay.equals(oldFirstVisibleDay) || this.mScrollListener == null)) {
            this.mScrollListener.onFirstVisibleDayChanged(this.mFirstVisibleDay, oldFirstVisibleDay);
        }
        int dayNumber = leftDaysWithGaps + 1;
        while (dayNumber <= (this.mNumberOfVisibleDays + leftDaysWithGaps) + 1) {
            float start;
            float startY;
            Calendar day = (Calendar) today.clone();
            this.mLastVisibleDay = (Calendar) day.clone();
            day.add(5, dayNumber - 1);
            this.mLastVisibleDay.add(5, dayNumber - 2);
            boolean sameDay = isSameDay(day, today);
            if (this.mEventRects == null || this.mRefreshEvents || (dayNumber == leftDaysWithGaps + 1 && this.mFetchedPeriod != ((int) this.mWeekViewLoader.toWeekViewPeriodIndex(day)) && Math.abs(((double) this.mFetchedPeriod) - this.mWeekViewLoader.toWeekViewPeriodIndex(day)) > 0.5d)) {
                getMoreEvents(day);
                this.mRefreshEvents = false;
            }
            if (startPixel < this.mHeaderColumnWidth) {
                start = this.mHeaderColumnWidth;
            } else {
                start = startPixel;
            }
            if ((this.mWidthPerDay + startPixel) - start > 0.0f) {
                if (this.mShowDistinctPastFutureColor) {
                    boolean isWeekend = day.get(7) == 7 || day.get(7) == 1;
                    Paint pastPaint = (isWeekend && this.mShowDistinctWeekendColor) ? this.mPastWeekendBackgroundPaint : this.mPastBackgroundPaint;
                    Paint futurePaint = (isWeekend && this.mShowDistinctWeekendColor) ? this.mFutureWeekendBackgroundPaint : this.mFutureBackgroundPaint;
                    startY = (((this.mHeaderTextHeight + ((float) (this.mHeaderRowPadding * 2))) + (this.mTimeTextHeight / 2.0f)) + this.mHeaderMarginBottom) + this.mCurrentOrigin.y;
                    if (sameDay) {
                        Calendar now = Calendar.getInstance();
                        float beforeNow = (((float) now.get(11)) + (((float) now.get(12)) / 60.0f)) * ((float) this.mHourHeight);
                        canvas.drawRect(start, startY, startPixel + this.mWidthPerDay, startY + beforeNow, pastPaint);
                        canvas.drawRect(start, startY + beforeNow, startPixel + this.mWidthPerDay, (float) getHeight(), futurePaint);
                    } else if (day.before(today)) {
                        canvas.drawRect(start, startY, startPixel + this.mWidthPerDay, (float) getHeight(), pastPaint);
                    } else {
                        canvas.drawRect(start, startY, startPixel + this.mWidthPerDay, (float) getHeight(), futurePaint);
                    }
                } else {
                    canvas.drawRect(start, ((this.mHeaderTextHeight + ((float) (this.mHeaderRowPadding * 2))) + (this.mTimeTextHeight / 2.0f)) + this.mHeaderMarginBottom, startPixel + this.mWidthPerDay, (float) getHeight(), sameDay ? this.mTodayBackgroundPaint : this.mDayBackgroundPaint);
                }
            }
            int i = 0;
            for (int hourNumber = 0; hourNumber < 24; hourNumber++) {
                float top = ((((this.mHeaderTextHeight + ((float) (this.mHeaderRowPadding * 2))) + this.mCurrentOrigin.y) + ((float) (this.mHourHeight * hourNumber))) + (this.mTimeTextHeight / 2.0f)) + this.mHeaderMarginBottom;
                if (top > (((this.mHeaderTextHeight + ((float) (this.mHeaderRowPadding * 2))) + (this.mTimeTextHeight / 2.0f)) + this.mHeaderMarginBottom) - ((float) this.mHourSeparatorHeight) && top < ((float) getHeight()) && (this.mWidthPerDay + startPixel) - start > 0.0f) {
                    hourLines[i * 4] = start;
                    hourLines[(i * 4) + 1] = top;
                    hourLines[(i * 4) + 2] = this.mWidthPerDay + startPixel;
                    hourLines[(i * 4) + 3] = top;
                    i++;
                }
            }
            canvas.drawLines(hourLines, this.mHourSeparatorPaint);
            drawEvents(day, startPixel, canvas);
            if (this.mShowNowLine && sameDay) {
                startY = (((this.mHeaderTextHeight + ((float) (this.mHeaderRowPadding * 2))) + (this.mTimeTextHeight / 2.0f)) + this.mHeaderMarginBottom) + this.mCurrentOrigin.y;
                now = Calendar.getInstance();
                beforeNow = (((float) now.get(11)) + (((float) now.get(12)) / 60.0f)) * ((float) this.mHourHeight);
                canvas.drawLine(start, startY + beforeNow, startPixel + this.mWidthPerDay, startY + beforeNow, this.mNowLinePaint);
            }
            startPixel += this.mWidthPerDay + ((float) this.mColumnGap);
            dayNumber++;
        }
        canvas.clipRect(this.mHeaderColumnWidth, 0.0f, (float) getWidth(), this.mHeaderTextHeight + ((float) (this.mHeaderRowPadding * 2)), Op.REPLACE);
        canvas.drawRect(0.0f, 0.0f, (float) getWidth(), this.mHeaderTextHeight + ((float) (this.mHeaderRowPadding * 2)), this.mHeaderBackgroundPaint);
        startPixel = startFromPixel;
        for (dayNumber = leftDaysWithGaps + 1; dayNumber <= (this.mNumberOfVisibleDays + leftDaysWithGaps) + 1; dayNumber++) {
            day = (Calendar) today.clone();
            day.add(5, dayNumber - 1);
            sameDay = isSameDay(day, today);
            String dayLabel = getDateTimeInterpreter().interpretDate(day);
            if (dayLabel == null) {
                throw new IllegalStateException("A DateTimeInterpreter must not return null date");
            }
            canvas.drawText(dayLabel, startPixel + (this.mWidthPerDay / 2.0f), ((float) this.mHeaderRowPadding) + this.mHeaderTextHeight, sameDay ? this.mTodayHeaderTextPaint : this.mHeaderTextPaint);
            startPixel += this.mWidthPerDay + ((float) this.mColumnGap);
        }
    }

    private Calendar getTimeFromPoint(float x, float y) {
        int leftDaysWithGaps = (int) (-Math.ceil((double) (this.mCurrentOrigin.x / (this.mWidthPerDay + ((float) this.mColumnGap)))));
        float startPixel = (this.mCurrentOrigin.x + ((this.mWidthPerDay + ((float) this.mColumnGap)) * ((float) leftDaysWithGaps))) + this.mHeaderColumnWidth;
        int dayNumber = leftDaysWithGaps + 1;
        while (dayNumber <= (this.mNumberOfVisibleDays + leftDaysWithGaps) + 1) {
            float start = startPixel < this.mHeaderColumnWidth ? this.mHeaderColumnWidth : startPixel;
            if ((this.mWidthPerDay + startPixel) - start <= 0.0f || x <= start || x >= this.mWidthPerDay + startPixel) {
                startPixel += this.mWidthPerDay + ((float) this.mColumnGap);
                dayNumber++;
            } else {
                Calendar day = today();
                day.add(5, dayNumber - 1);
                float pixelsFromZero = ((((y - this.mCurrentOrigin.y) - this.mHeaderTextHeight) - ((float) (this.mHeaderRowPadding * 2))) - (this.mTimeTextHeight / 2.0f)) - this.mHeaderMarginBottom;
                int hour = (int) (pixelsFromZero / ((float) this.mHourHeight));
                int minute = (int) ((60.0f * (pixelsFromZero - ((float) (this.mHourHeight * hour)))) / ((float) this.mHourHeight));
                day.add(10, hour);
                day.set(12, minute);
                return day;
            }
        }
        return null;
    }

    private void drawEvents(Calendar date, float startFromPixel, Canvas canvas) {
        if (this.mEventRects != null && this.mEventRects.size() > 0) {
            for (int i = 0; i < this.mEventRects.size(); i++) {
                if (isSameDay(((EventRect) this.mEventRects.get(i)).event.getStartTime(), date)) {
                    float top = (((((((((EventRect) this.mEventRects.get(i)).top * ((float) (this.mHourHeight * 24))) / 1440.0f) + this.mCurrentOrigin.y) + this.mHeaderTextHeight) + ((float) (this.mHeaderRowPadding * 2))) + this.mHeaderMarginBottom) + (this.mTimeTextHeight / 2.0f)) + ((float) this.mEventMarginVertical);
                    float bottom = (((((((((float) (this.mHourHeight * 24)) * ((EventRect) this.mEventRects.get(i)).bottom) / 1440.0f) + this.mCurrentOrigin.y) + this.mHeaderTextHeight) + ((float) (this.mHeaderRowPadding * 2))) + this.mHeaderMarginBottom) + (this.mTimeTextHeight / 2.0f)) - ((float) this.mEventMarginVertical);
                    float left = startFromPixel + (((EventRect) this.mEventRects.get(i)).left * this.mWidthPerDay);
                    if (left < startFromPixel) {
                        left += (float) this.mOverlappingEventGap;
                    }
                    float right = left + (((EventRect) this.mEventRects.get(i)).width * this.mWidthPerDay);
                    if (right < this.mWidthPerDay + startFromPixel) {
                        right -= (float) this.mOverlappingEventGap;
                    }
                    if (left >= right || left >= ((float) getWidth()) || top >= ((float) getHeight()) || right <= this.mHeaderColumnWidth || bottom <= ((this.mHeaderTextHeight + ((float) (this.mHeaderRowPadding * 2))) + (this.mTimeTextHeight / 2.0f)) + this.mHeaderMarginBottom) {
                        ((EventRect) this.mEventRects.get(i)).rectF = null;
                    } else {
                        ((EventRect) this.mEventRects.get(i)).rectF = new RectF(left, top, right, bottom);
                        this.mEventBackgroundPaint.setColor(((EventRect) this.mEventRects.get(i)).event.getColor() == 0 ? this.mDefaultEventColor : ((EventRect) this.mEventRects.get(i)).event.getColor());
                        canvas.drawRoundRect(((EventRect) this.mEventRects.get(i)).rectF, (float) this.mEventCornerRadius, (float) this.mEventCornerRadius, this.mEventBackgroundPaint);
                        drawEventTitle(((EventRect) this.mEventRects.get(i)).event, ((EventRect) this.mEventRects.get(i)).rectF, canvas, top, left);
                    }
                }
            }
        }
    }

    private void drawEventTitle(WeekViewEvent event, RectF rect, Canvas canvas, float originalTop, float originalLeft) {
        if ((rect.right - rect.left) - ((float) (this.mEventPadding * 2)) >= 0.0f && (rect.bottom - rect.top) - ((float) (this.mEventPadding * 2)) >= 0.0f) {
            SpannableStringBuilder bob = new SpannableStringBuilder();
            if (event.getName() != null) {
                bob.append(event.getName());
                bob.setSpan(new StyleSpan(1), 0, bob.length(), 0);
                bob.append(' ');
            }
            if (event.getLocation() != null) {
                bob.append(event.getLocation());
            }
            int availableHeight = (int) ((rect.bottom - originalTop) - ((float) (this.mEventPadding * 2)));
            int availableWidth = (int) ((rect.right - originalLeft) - ((float) (this.mEventPadding * 2)));
            StaticLayout textLayout = new StaticLayout(bob, this.mEventTextPaint, availableWidth, Alignment.ALIGN_NORMAL, FlexItem.FLEX_SHRINK_DEFAULT, 0.0f, false);
            int lineHeight = textLayout.getHeight() / textLayout.getLineCount();
            if (availableHeight >= lineHeight) {
                StaticLayout staticLayout;
                int availableLineCount = availableHeight / lineHeight;
                do {
                    staticLayout = new StaticLayout(TextUtils.ellipsize(bob, this.mEventTextPaint, (float) (availableLineCount * availableWidth), TruncateAt.END), this.mEventTextPaint, (int) ((rect.right - originalLeft) - ((float) (this.mEventPadding * 2))), Alignment.ALIGN_NORMAL, FlexItem.FLEX_SHRINK_DEFAULT, 0.0f, false);
                    availableLineCount--;
                } while (staticLayout.getHeight() > availableHeight);
                canvas.save();
                canvas.translate(((float) this.mEventPadding) + originalLeft, ((float) this.mEventPadding) + originalTop);
                staticLayout.draw(canvas);
                canvas.restore();
            }
        }
    }

    private void getMoreEvents(Calendar day) {
        if (this.mEventRects == null) {
            this.mEventRects = new ArrayList();
        }
        if (this.mWeekViewLoader != null || isInEditMode()) {
            if (this.mRefreshEvents) {
                this.mEventRects.clear();
                this.mPreviousPeriodEvents = null;
                this.mCurrentPeriodEvents = null;
                this.mNextPeriodEvents = null;
                this.mFetchedPeriod = -1;
            }
            if (this.mWeekViewLoader != null) {
                int periodToFetch = (int) this.mWeekViewLoader.toWeekViewPeriodIndex(day);
                if (!isInEditMode() && (this.mFetchedPeriod < 0 || this.mFetchedPeriod != periodToFetch || this.mRefreshEvents)) {
                    List<? extends WeekViewEvent> previousPeriodEvents = null;
                    List<? extends WeekViewEvent> currentPeriodEvents = null;
                    List<? extends WeekViewEvent> nextPeriodEvents = null;
                    if (!(this.mPreviousPeriodEvents == null || this.mCurrentPeriodEvents == null || this.mNextPeriodEvents == null)) {
                        if (periodToFetch == this.mFetchedPeriod - 1) {
                            currentPeriodEvents = this.mPreviousPeriodEvents;
                            nextPeriodEvents = this.mCurrentPeriodEvents;
                        } else if (periodToFetch == this.mFetchedPeriod) {
                            previousPeriodEvents = this.mPreviousPeriodEvents;
                            currentPeriodEvents = this.mCurrentPeriodEvents;
                            nextPeriodEvents = this.mNextPeriodEvents;
                        } else if (periodToFetch == this.mFetchedPeriod + 1) {
                            previousPeriodEvents = this.mCurrentPeriodEvents;
                            currentPeriodEvents = this.mNextPeriodEvents;
                        }
                    }
                    if (currentPeriodEvents == null) {
                        currentPeriodEvents = this.mWeekViewLoader.onLoad(periodToFetch);
                    }
                    if (previousPeriodEvents == null) {
                        previousPeriodEvents = this.mWeekViewLoader.onLoad(periodToFetch - 1);
                    }
                    if (nextPeriodEvents == null) {
                        nextPeriodEvents = this.mWeekViewLoader.onLoad(periodToFetch + 1);
                    }
                    this.mEventRects.clear();
                    sortAndCacheEvents(previousPeriodEvents);
                    sortAndCacheEvents(currentPeriodEvents);
                    sortAndCacheEvents(nextPeriodEvents);
                    this.mPreviousPeriodEvents = previousPeriodEvents;
                    this.mCurrentPeriodEvents = currentPeriodEvents;
                    this.mNextPeriodEvents = nextPeriodEvents;
                    this.mFetchedPeriod = periodToFetch;
                }
            }
            List<EventRect> tempEvents = this.mEventRects;
            this.mEventRects = new ArrayList();
            while (tempEvents.size() > 0) {
                ArrayList<EventRect> eventRects = new ArrayList(tempEvents.size());
                EventRect eventRect1 = (EventRect) tempEvents.remove(0);
                eventRects.add(eventRect1);
                int i = 0;
                while (i < tempEvents.size()) {
                    EventRect eventRect2 = (EventRect) tempEvents.get(i);
                    if (isSameDay(eventRect1.event.getStartTime(), eventRect2.event.getStartTime())) {
                        tempEvents.remove(i);
                        eventRects.add(eventRect2);
                    } else {
                        i++;
                    }
                }
                computePositionOfEvents(eventRects);
            }
            return;
        }
        throw new IllegalStateException("You must provide a MonthChangeListener");
    }

    private void cacheEvent(WeekViewEvent event) {
        if (event.getStartTime().compareTo(event.getEndTime()) < 0) {
            if (isSameDay(event.getStartTime(), event.getEndTime())) {
                this.mEventRects.add(new EventRect(event, event, null));
                return;
            }
            Calendar endTime = (Calendar) event.getStartTime().clone();
            endTime.set(11, 23);
            endTime.set(12, 59);
            WeekViewEvent event1 = new WeekViewEvent(event.getId(), event.getName(), event.getLocation(), event.getStartTime(), endTime);
            event1.setColor(event.getColor());
            this.mEventRects.add(new EventRect(event1, event, null));
            Calendar otherDay = (Calendar) event.getStartTime().clone();
            otherDay.add(5, 1);
            while (!isSameDay(otherDay, event.getEndTime())) {
                Calendar overDay = (Calendar) otherDay.clone();
                overDay.set(11, 0);
                overDay.set(12, 0);
                Calendar endOfOverDay = (Calendar) overDay.clone();
                endOfOverDay.set(11, 23);
                endOfOverDay.set(12, 59);
                WeekViewEvent eventMore = new WeekViewEvent(event.getId(), event.getName(), overDay, endOfOverDay);
                eventMore.setColor(event.getColor());
                this.mEventRects.add(new EventRect(eventMore, event, null));
                otherDay.add(5, 1);
            }
            Calendar startTime = (Calendar) event.getEndTime().clone();
            startTime.set(11, 0);
            startTime.set(12, 0);
            WeekViewEvent event2 = new WeekViewEvent(event.getId(), event.getName(), event.getLocation(), startTime, event.getEndTime());
            event2.setColor(event.getColor());
            this.mEventRects.add(new EventRect(event2, event, null));
        }
    }

    private void sortAndCacheEvents(List<? extends WeekViewEvent> events) {
        sortEvents(events);
        for (WeekViewEvent event : events) {
            cacheEvent(event);
        }
    }

    private void sortEvents(List<? extends WeekViewEvent> events) {
        Collections.sort(events, new C05583());
    }

    private void computePositionOfEvents(List<EventRect> eventRects) {
        List<List<EventRect>> collisionGroups = new ArrayList();
        for (EventRect eventRect : eventRects) {
            boolean isPlaced = false;
            for (List<EventRect> collisionGroup : collisionGroups) {
                for (EventRect groupEvent : collisionGroup) {
                    if (isEventsCollide(groupEvent.event, eventRect.event)) {
                        collisionGroup.add(eventRect);
                        isPlaced = true;
                        break;
                    }
                }
            }
            if (!isPlaced) {
                List<EventRect> newGroup = new ArrayList();
                newGroup.add(eventRect);
                collisionGroups.add(newGroup);
            }
        }
        for (List<EventRect> collisionGroup2 : collisionGroups) {
            expandEventsToMaxWidth(collisionGroup2);
        }
    }

    private void expandEventsToMaxWidth(List<EventRect> collisionGroup) {
        List<List<EventRect>> columns = new ArrayList();
        columns.add(new ArrayList());
        for (EventRect eventRect : collisionGroup) {
            boolean isPlaced = false;
            for (List<EventRect> column : columns) {
                EventRect eventRect2;
                if (column.size() == 0) {
                    column.add(eventRect2);
                    isPlaced = true;
                } else if (!isEventsCollide(eventRect2.event, ((EventRect) column.get(column.size() - 1)).event)) {
                    column.add(eventRect2);
                    isPlaced = true;
                    break;
                }
            }
            if (!isPlaced) {
                List<EventRect> newColumn = new ArrayList();
                newColumn.add(eventRect2);
                columns.add(newColumn);
            }
        }
        int maxRowCount = 0;
        for (List<EventRect> column2 : columns) {
            maxRowCount = Math.max(maxRowCount, column2.size());
        }
        for (int i = 0; i < maxRowCount; i++) {
            float j = 0.0f;
            for (List<EventRect> column22 : columns) {
                if (column22.size() >= i + 1) {
                    eventRect2 = (EventRect) column22.get(i);
                    eventRect2.width = FlexItem.FLEX_SHRINK_DEFAULT / ((float) columns.size());
                    eventRect2.left = j / ((float) columns.size());
                    eventRect2.top = (float) ((eventRect2.event.getStartTime().get(11) * 60) + eventRect2.event.getStartTime().get(12));
                    eventRect2.bottom = (float) ((eventRect2.event.getEndTime().get(11) * 60) + eventRect2.event.getEndTime().get(12));
                    this.mEventRects.add(eventRect2);
                }
                j += FlexItem.FLEX_SHRINK_DEFAULT;
            }
        }
    }

    private boolean isEventsCollide(WeekViewEvent event1, WeekViewEvent event2) {
        return event1.getStartTime().getTimeInMillis() < event2.getEndTime().getTimeInMillis() && event1.getEndTime().getTimeInMillis() > event2.getStartTime().getTimeInMillis();
    }

    private boolean isTimeAfterOrEquals(Calendar time1, Calendar time2) {
        return (time1 == null || time2 == null || time1.getTimeInMillis() < time2.getTimeInMillis()) ? false : true;
    }

    public void invalidate() {
        super.invalidate();
        this.mAreDimensionsInvalid = true;
    }

    public void setOnEventClickListener(EventClickListener listener) {
        this.mEventClickListener = listener;
    }

    public EventClickListener getEventClickListener() {
        return this.mEventClickListener;
    }

    @Nullable
    public MonthChangeListener getMonthChangeListener() {
        if (this.mWeekViewLoader instanceof MonthLoader) {
            return ((MonthLoader) this.mWeekViewLoader).getOnMonthChangeListener();
        }
        return null;
    }

    public void setMonthChangeListener(MonthChangeListener monthChangeListener) {
        this.mWeekViewLoader = new MonthLoader(monthChangeListener);
    }

    public WeekViewLoader getWeekViewLoader() {
        return this.mWeekViewLoader;
    }

    public void setWeekViewLoader(WeekViewLoader loader) {
        this.mWeekViewLoader = loader;
    }

    public EventLongPressListener getEventLongPressListener() {
        return this.mEventLongPressListener;
    }

    public void setEventLongPressListener(EventLongPressListener eventLongPressListener) {
        this.mEventLongPressListener = eventLongPressListener;
    }

    public void setEmptyViewClickListener(EmptyViewClickListener emptyViewClickListener) {
        this.mEmptyViewClickListener = emptyViewClickListener;
    }

    public EmptyViewClickListener getEmptyViewClickListener() {
        return this.mEmptyViewClickListener;
    }

    public void setEmptyViewLongPressListener(EmptyViewLongPressListener emptyViewLongPressListener) {
        this.mEmptyViewLongPressListener = emptyViewLongPressListener;
    }

    public EmptyViewLongPressListener getEmptyViewLongPressListener() {
        return this.mEmptyViewLongPressListener;
    }

    public void setScrollListener(ScrollListener scrolledListener) {
        this.mScrollListener = scrolledListener;
    }

    public ScrollListener getScrollListener() {
        return this.mScrollListener;
    }

    public DateTimeInterpreter getDateTimeInterpreter() {
        if (this.mDateTimeInterpreter == null) {
            this.mDateTimeInterpreter = new C05594();
        }
        return this.mDateTimeInterpreter;
    }

    public void setDateTimeInterpreter(DateTimeInterpreter dateTimeInterpreter) {
        this.mDateTimeInterpreter = dateTimeInterpreter;
        initTextTimeWidth();
    }

    public int getNumberOfVisibleDays() {
        return this.mNumberOfVisibleDays;
    }

    public void setNumberOfVisibleDays(int numberOfVisibleDays) {
        this.mNumberOfVisibleDays = numberOfVisibleDays;
        this.mCurrentOrigin.x = 0.0f;
        this.mCurrentOrigin.y = 0.0f;
        invalidate();
    }

    public int getHourHeight() {
        return this.mHourHeight;
    }

    public void setHourHeight(int hourHeight) {
        this.mNewHourHeight = hourHeight;
        invalidate();
    }

    public int getColumnGap() {
        return this.mColumnGap;
    }

    public void setColumnGap(int columnGap) {
        this.mColumnGap = columnGap;
        invalidate();
    }

    public int getFirstDayOfWeek() {
        return this.mFirstDayOfWeek;
    }

    public void setFirstDayOfWeek(int firstDayOfWeek) {
        this.mFirstDayOfWeek = firstDayOfWeek;
        invalidate();
    }

    public int getTextSize() {
        return this.mTextSize;
    }

    public void setTextSize(int textSize) {
        this.mTextSize = textSize;
        this.mTodayHeaderTextPaint.setTextSize((float) this.mTextSize);
        this.mHeaderTextPaint.setTextSize((float) this.mTextSize);
        this.mTimeTextPaint.setTextSize((float) this.mTextSize);
        invalidate();
    }

    public int getHeaderColumnPadding() {
        return this.mHeaderColumnPadding;
    }

    public void setHeaderColumnPadding(int headerColumnPadding) {
        this.mHeaderColumnPadding = headerColumnPadding;
        invalidate();
    }

    public int getHeaderColumnTextColor() {
        return this.mHeaderColumnTextColor;
    }

    public void setHeaderColumnTextColor(int headerColumnTextColor) {
        this.mHeaderColumnTextColor = headerColumnTextColor;
        this.mHeaderTextPaint.setColor(this.mHeaderColumnTextColor);
        this.mTimeTextPaint.setColor(this.mHeaderColumnTextColor);
        invalidate();
    }

    public int getHeaderRowPadding() {
        return this.mHeaderRowPadding;
    }

    public void setHeaderRowPadding(int headerRowPadding) {
        this.mHeaderRowPadding = headerRowPadding;
        invalidate();
    }

    public int getHeaderRowBackgroundColor() {
        return this.mHeaderRowBackgroundColor;
    }

    public void setHeaderRowBackgroundColor(int headerRowBackgroundColor) {
        this.mHeaderRowBackgroundColor = headerRowBackgroundColor;
        this.mHeaderBackgroundPaint.setColor(this.mHeaderRowBackgroundColor);
        invalidate();
    }

    public int getDayBackgroundColor() {
        return this.mDayBackgroundColor;
    }

    public void setDayBackgroundColor(int dayBackgroundColor) {
        this.mDayBackgroundColor = dayBackgroundColor;
        this.mDayBackgroundPaint.setColor(this.mDayBackgroundColor);
        invalidate();
    }

    public int getHourSeparatorColor() {
        return this.mHourSeparatorColor;
    }

    public void setHourSeparatorColor(int hourSeparatorColor) {
        this.mHourSeparatorColor = hourSeparatorColor;
        this.mHourSeparatorPaint.setColor(this.mHourSeparatorColor);
        invalidate();
    }

    public int getTodayBackgroundColor() {
        return this.mTodayBackgroundColor;
    }

    public void setTodayBackgroundColor(int todayBackgroundColor) {
        this.mTodayBackgroundColor = todayBackgroundColor;
        this.mTodayBackgroundPaint.setColor(this.mTodayBackgroundColor);
        invalidate();
    }

    public int getHourSeparatorHeight() {
        return this.mHourSeparatorHeight;
    }

    public void setHourSeparatorHeight(int hourSeparatorHeight) {
        this.mHourSeparatorHeight = hourSeparatorHeight;
        this.mHourSeparatorPaint.setStrokeWidth((float) this.mHourSeparatorHeight);
        invalidate();
    }

    public int getTodayHeaderTextColor() {
        return this.mTodayHeaderTextColor;
    }

    public void setTodayHeaderTextColor(int todayHeaderTextColor) {
        this.mTodayHeaderTextColor = todayHeaderTextColor;
        this.mTodayHeaderTextPaint.setColor(this.mTodayHeaderTextColor);
        invalidate();
    }

    public int getEventTextSize() {
        return this.mEventTextSize;
    }

    public void setEventTextSize(int eventTextSize) {
        this.mEventTextSize = eventTextSize;
        this.mEventTextPaint.setTextSize((float) this.mEventTextSize);
        invalidate();
    }

    public int getEventTextColor() {
        return this.mEventTextColor;
    }

    public void setEventTextColor(int eventTextColor) {
        this.mEventTextColor = eventTextColor;
        this.mEventTextPaint.setColor(this.mEventTextColor);
        invalidate();
    }

    public int getEventPadding() {
        return this.mEventPadding;
    }

    public void setEventPadding(int eventPadding) {
        this.mEventPadding = eventPadding;
        invalidate();
    }

    public int getHeaderColumnBackgroundColor() {
        return this.mHeaderColumnBackgroundColor;
    }

    public void setHeaderColumnBackgroundColor(int headerColumnBackgroundColor) {
        this.mHeaderColumnBackgroundColor = headerColumnBackgroundColor;
        this.mHeaderColumnBackgroundPaint.setColor(this.mHeaderColumnBackgroundColor);
        invalidate();
    }

    public int getDefaultEventColor() {
        return this.mDefaultEventColor;
    }

    public void setDefaultEventColor(int defaultEventColor) {
        this.mDefaultEventColor = defaultEventColor;
        invalidate();
    }

    @Deprecated
    public int getDayNameLength() {
        return this.mDayNameLength;
    }

    @Deprecated
    public void setDayNameLength(int length) {
        if (length == 2 || length == 1) {
            this.mDayNameLength = length;
            return;
        }
        throw new IllegalArgumentException("length parameter must be either LENGTH_LONG or LENGTH_SHORT");
    }

    public int getOverlappingEventGap() {
        return this.mOverlappingEventGap;
    }

    public void setOverlappingEventGap(int overlappingEventGap) {
        this.mOverlappingEventGap = overlappingEventGap;
        invalidate();
    }

    public int getEventCornerRadius() {
        return this.mEventCornerRadius;
    }

    public void setEventCornerRadius(int eventCornerRadius) {
        this.mEventCornerRadius = eventCornerRadius;
    }

    public int getEventMarginVertical() {
        return this.mEventMarginVertical;
    }

    public void setEventMarginVertical(int eventMarginVertical) {
        this.mEventMarginVertical = eventMarginVertical;
        invalidate();
    }

    public Calendar getFirstVisibleDay() {
        return this.mFirstVisibleDay;
    }

    public Calendar getLastVisibleDay() {
        return this.mLastVisibleDay;
    }

    public float getXScrollingSpeed() {
        return this.mXScrollingSpeed;
    }

    public void setXScrollingSpeed(float xScrollingSpeed) {
        this.mXScrollingSpeed = xScrollingSpeed;
    }

    public boolean isShowDistinctWeekendColor() {
        return this.mShowDistinctWeekendColor;
    }

    public void setShowDistinctWeekendColor(boolean showDistinctWeekendColor) {
        this.mShowDistinctWeekendColor = showDistinctWeekendColor;
        invalidate();
    }

    public boolean isShowDistinctPastFutureColor() {
        return this.mShowDistinctPastFutureColor;
    }

    public void setShowDistinctPastFutureColor(boolean showDistinctPastFutureColor) {
        this.mShowDistinctPastFutureColor = showDistinctPastFutureColor;
        invalidate();
    }

    public boolean isShowNowLine() {
        return this.mShowNowLine;
    }

    public void setShowNowLine(boolean showNowLine) {
        this.mShowNowLine = showNowLine;
        invalidate();
    }

    public int getNowLineColor() {
        return this.mNowLineColor;
    }

    public void setNowLineColor(int nowLineColor) {
        this.mNowLineColor = nowLineColor;
        invalidate();
    }

    public int getNowLineThickness() {
        return this.mNowLineThickness;
    }

    public void setNowLineThickness(int nowLineThickness) {
        this.mNowLineThickness = nowLineThickness;
        invalidate();
    }

    public boolean isHorizontalFlingEnabled() {
        return this.mHorizontalFlingEnabled;
    }

    public void setHorizontalFlingEnabled(boolean enabled) {
        this.mHorizontalFlingEnabled = enabled;
    }

    public boolean isVerticalFlingEnabled() {
        return this.mVerticalFlingEnabled;
    }

    public void setVerticalFlingEnabled(boolean enabled) {
        this.mVerticalFlingEnabled = enabled;
    }

    public boolean onTouchEvent(MotionEvent event) {
        this.mScaleDetector.onTouchEvent(event);
        boolean val = this.mGestureDetector.onTouchEvent(event);
        if (event.getAction() == 1 && !this.mIsZooming && this.mCurrentFlingDirection == Direction.NONE) {
            if (this.mCurrentScrollDirection == Direction.RIGHT || this.mCurrentScrollDirection == Direction.LEFT) {
                goToNearestOrigin();
            }
            this.mCurrentScrollDirection = Direction.NONE;
        }
        return val;
    }

    private void goToNearestOrigin() {
        double leftDays = (double) (this.mCurrentOrigin.x / (this.mWidthPerDay + ((float) this.mColumnGap)));
        if (this.mCurrentFlingDirection != Direction.NONE) {
            leftDays = (double) Math.round(leftDays);
        } else if (this.mCurrentScrollDirection == Direction.LEFT) {
            leftDays = Math.floor(leftDays);
        } else if (this.mCurrentScrollDirection == Direction.RIGHT) {
            leftDays = Math.ceil(leftDays);
        } else {
            leftDays = (double) Math.round(leftDays);
        }
        int nearestOrigin = (int) (((double) this.mCurrentOrigin.x) - (((double) (this.mWidthPerDay + ((float) this.mColumnGap))) * leftDays));
        if (nearestOrigin != 0) {
            this.mScroller.forceFinished(true);
            this.mScroller.startScroll((int) this.mCurrentOrigin.x, (int) this.mCurrentOrigin.y, -nearestOrigin, 0, (int) ((((float) Math.abs(nearestOrigin)) / this.mWidthPerDay) * 500.0f));
            ViewCompat.postInvalidateOnAnimation(this);
        }
        Direction direction = Direction.NONE;
        this.mCurrentFlingDirection = direction;
        this.mCurrentScrollDirection = direction;
    }

    public void computeScroll() {
        super.computeScroll();
        if (this.mScroller.isFinished()) {
            if (this.mCurrentFlingDirection != Direction.NONE) {
                goToNearestOrigin();
            }
        } else if (this.mCurrentFlingDirection != Direction.NONE && forceFinishScroll()) {
            goToNearestOrigin();
        } else if (this.mScroller.computeScrollOffset()) {
            this.mCurrentOrigin.y = (float) this.mScroller.getCurrY();
            this.mCurrentOrigin.x = (float) this.mScroller.getCurrX();
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    private boolean forceFinishScroll() {
        if (VERSION.SDK_INT < 14 || this.mScroller.getCurrVelocity() > ((float) this.mMinimumFlingVelocity)) {
            return false;
        }
        return true;
    }

    public void goToToday() {
        goToDate(Calendar.getInstance());
    }

    public void goToDate(Calendar date) {
        this.mScroller.forceFinished(true);
        Direction direction = Direction.NONE;
        this.mCurrentFlingDirection = direction;
        this.mCurrentScrollDirection = direction;
        date.set(11, 0);
        date.set(12, 0);
        date.set(13, 0);
        date.set(14, 0);
        if (this.mAreDimensionsInvalid) {
            this.mScrollToDay = date;
            return;
        }
        this.mRefreshEvents = true;
        Calendar today = Calendar.getInstance();
        today.set(11, 0);
        today.set(12, 0);
        today.set(13, 0);
        today.set(14, 0);
        long dateDifference = ((date.getTimeInMillis() + ((long) date.getTimeZone().getOffset(date.getTimeInMillis()))) / 86400000) - ((today.getTimeInMillis() + ((long) today.getTimeZone().getOffset(today.getTimeInMillis()))) / 86400000);
        this.mCurrentOrigin.x = ((float) (-dateDifference)) * (this.mWidthPerDay + ((float) this.mColumnGap));
        invalidate();
    }

    public void notifyDatasetChanged() {
        this.mRefreshEvents = true;
        invalidate();
    }

    public void goToHour(double hour) {
        if (this.mAreDimensionsInvalid) {
            this.mScrollToHour = hour;
            return;
        }
        int verticalOffset = 0;
        if (hour > 24.0d) {
            verticalOffset = this.mHourHeight * 24;
        } else if (hour > 0.0d) {
            verticalOffset = (int) (((double) this.mHourHeight) * hour);
        }
        if (((float) verticalOffset) > ((((float) ((this.mHourHeight * 24) - getHeight())) + this.mHeaderTextHeight) + ((float) (this.mHeaderRowPadding * 2))) + this.mHeaderMarginBottom) {
            verticalOffset = (int) (((((float) ((this.mHourHeight * 24) - getHeight())) + this.mHeaderTextHeight) + ((float) (this.mHeaderRowPadding * 2))) + this.mHeaderMarginBottom);
        }
        this.mCurrentOrigin.y = (float) (-verticalOffset);
        invalidate();
    }

    public double getFirstVisibleHour() {
        return (double) ((-this.mCurrentOrigin.y) / ((float) this.mHourHeight));
    }

    private boolean isSameDay(Calendar dayOne, Calendar dayTwo) {
        return dayOne.get(1) == dayTwo.get(1) && dayOne.get(6) == dayTwo.get(6);
    }

    private Calendar today() {
        Calendar today = Calendar.getInstance();
        today.set(11, 0);
        today.set(12, 0);
        today.set(13, 0);
        today.set(14, 0);
        return today;
    }
}
