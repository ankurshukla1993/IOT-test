package com.appeaser.sublimepickerlibrary.datepicker;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.widget.ExploreByTouchHelper;
import android.text.TextPaint;
import android.text.format.DateFormat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.accessibility.AccessibilityEvent;
import android.widget.TextView;
import com.appeaser.sublimepickerlibrary.C0563R;
import com.appeaser.sublimepickerlibrary.common.DateTimePatternHelper;
import com.appeaser.sublimepickerlibrary.datepicker.SelectedDate.Type;
import com.appeaser.sublimepickerlibrary.utilities.SUtils;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import tw.com.prolific.driver.pl2303.PL2303Driver;

class SimpleMonthView extends View {
    private static final int DAYS_IN_WEEK = 7;
    private static final String DAY_OF_WEEK_FORMAT;
    private static final int DEFAULT_SELECTED_DAY = -1;
    private static final String DEFAULT_TITLE_FORMAT = "MMMMy";
    private static final int DEFAULT_WEEK_START = 1;
    private static final int MAX_WEEKS_IN_MONTH = 6;
    private static final String TAG = SimpleMonthView.class.getSimpleName();
    private final int DRAW_RECT;
    private final int DRAW_RECT_WITH_CURVE_ON_LEFT;
    private final int DRAW_RECT_WITH_CURVE_ON_RIGHT;
    private final ActivatedDays mActivatedDays;
    private final Calendar mCalendar;
    private int mCellWidth;
    private Context mContext;
    private NumberFormat mDayFormatter;
    private int mDayHeight;
    private final Paint mDayHighlightPaint;
    private SimpleDateFormat mDayOfWeekFormatter;
    private int mDayOfWeekHeight;
    private final Calendar mDayOfWeekLabelCalendar;
    private final TextPaint mDayOfWeekPaint;
    private int mDayOfWeekStart;
    private final TextPaint mDayPaint;
    private final Paint mDayRangeSelectorPaint;
    private final Paint mDaySelectorPaint;
    private int mDaySelectorRadius;
    private ColorStateList mDayTextColor;
    private int mDaysInMonth;
    private int mDesiredCellWidth;
    private int mDesiredDayHeight;
    private int mDesiredDayOfWeekHeight;
    private int mDesiredDaySelectorRadius;
    private int mDesiredMonthHeight;
    private int mDownX;
    private int mDownY;
    private int mEnabledDayEnd;
    private int mEnabledDayStart;
    private int mInitialTarget;
    private int mMonth;
    private int mMonthHeight;
    private final TextPaint mMonthPaint;
    private OnDayClickListener mOnDayClickListener;
    private int mPaddedHeight;
    private int mPaddedWidth;
    private float mPaddingRangeIndicator;
    private CheckForTap mPendingCheckForTap;
    private CharSequence mTitle;
    private SimpleDateFormat mTitleFormatter;
    private int mToday;
    private MonthViewTouchHelper mTouchHelper;
    private int mTouchSlopSquared;
    private int mTouchedItem;
    private int mWeekStart;
    private int mYear;

    public interface OnDayClickListener {
        void onDayClick(SimpleMonthView simpleMonthView, Calendar calendar);
    }

    public class ActivatedDays {
        public int endingDay = -1;
        public Type selectedDateType;
        public int startingDay = -1;

        public void reset() {
            this.endingDay = -1;
            this.startingDay = -1;
        }

        public boolean isValid() {
            return (this.startingDay == -1 || this.endingDay == -1) ? false : true;
        }

        public boolean isActivated(int day) {
            return day >= this.startingDay && day <= this.endingDay;
        }

        public boolean isStartingDayOfRange(int day) {
            return day == this.startingDay;
        }

        public boolean isEndingDayOfRange(int day) {
            return day == this.endingDay;
        }

        public boolean isSingleDay() {
            return this.startingDay == this.endingDay;
        }

        public boolean isSelected(int day) {
            return this.selectedDateType == Type.SINGLE && this.startingDay == day && this.endingDay == day;
        }

        public int getSelectedDay() {
            if (this.selectedDateType == Type.SINGLE && this.startingDay == this.endingDay) {
                return this.startingDay;
            }
            return -1;
        }

        public boolean hasSelectedDay() {
            return this.selectedDateType == Type.SINGLE && this.startingDay == this.endingDay && this.startingDay != -1;
        }

        public boolean isStartOfMonth() {
            return this.startingDay == 1;
        }
    }

    private final class CheckForTap implements Runnable {
        private CheckForTap() {
        }

        public void run() {
            SimpleMonthView.this.mTouchedItem = SimpleMonthView.this.getDayAtLocation(SimpleMonthView.this.mDownX, SimpleMonthView.this.mDownY);
            SimpleMonthView.this.invalidate();
        }
    }

    private class MonthViewTouchHelper extends ExploreByTouchHelper {
        private static final String DATE_FORMAT = "dd MMMM yyyy";
        private final Calendar mTempCalendar = Calendar.getInstance();
        private final Rect mTempRect = new Rect();

        public MonthViewTouchHelper(View forView) {
            super(forView);
        }

        protected int getVirtualViewAt(float x, float y) {
            int day = SimpleMonthView.this.getDayAtLocation((int) (x + 0.5f), (int) (0.5f + y));
            return day != -1 ? day : Integer.MIN_VALUE;
        }

        protected void getVisibleVirtualViews(List<Integer> virtualViewIds) {
            for (int day = 1; day <= SimpleMonthView.this.mDaysInMonth; day++) {
                virtualViewIds.add(Integer.valueOf(day));
            }
        }

        protected void onPopulateEventForVirtualView(int virtualViewId, AccessibilityEvent event) {
            event.setContentDescription(getDayDescription(virtualViewId));
        }

        protected void onPopulateNodeForVirtualView(int virtualViewId, AccessibilityNodeInfoCompat node) {
            if (SimpleMonthView.this.getBoundsForDay(virtualViewId, this.mTempRect)) {
                node.setText(getDayText(virtualViewId));
                node.setContentDescription(getDayDescription(virtualViewId));
                node.setBoundsInParent(this.mTempRect);
                boolean isDayEnabled = SimpleMonthView.this.isDayEnabled(virtualViewId);
                if (isDayEnabled) {
                    node.addAction(16);
                }
                node.setEnabled(isDayEnabled);
                if (SimpleMonthView.this.mActivatedDays.isValid() && SimpleMonthView.this.mActivatedDays.isActivated(virtualViewId)) {
                    node.setChecked(true);
                    return;
                }
                return;
            }
            this.mTempRect.setEmpty();
            node.setContentDescription("");
            node.setBoundsInParent(this.mTempRect);
            node.setVisibleToUser(false);
        }

        protected boolean onPerformActionForVirtualView(int virtualViewId, int action, Bundle arguments) {
            switch (action) {
                case 16:
                    return SimpleMonthView.this.onDayClicked(virtualViewId);
                default:
                    return false;
            }
        }

        private CharSequence getDayDescription(int id) {
            if (!SimpleMonthView.this.isValidDayOfMonth(id)) {
                return "";
            }
            this.mTempCalendar.set(SimpleMonthView.this.mYear, SimpleMonthView.this.mMonth, id);
            return DateFormat.format(DATE_FORMAT, this.mTempCalendar.getTimeInMillis());
        }

        private CharSequence getDayText(int id) {
            if (SimpleMonthView.this.isValidDayOfMonth(id)) {
                return SimpleMonthView.this.mDayFormatter.format((long) id);
            }
            return null;
        }
    }

    static {
        if (SUtils.isApi_18_OrHigher()) {
            DAY_OF_WEEK_FORMAT = "EEEEE";
        } else {
            DAY_OF_WEEK_FORMAT = "E";
        }
    }

    public SimpleMonthView(Context context) {
        this(context, null);
    }

    public SimpleMonthView(Context context, AttributeSet attrs) {
        this(context, attrs, C0563R.attr.spMonthViewStyle);
    }

    public SimpleMonthView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.DRAW_RECT = 0;
        this.DRAW_RECT_WITH_CURVE_ON_LEFT = 1;
        this.DRAW_RECT_WITH_CURVE_ON_RIGHT = 2;
        this.mMonthPaint = new TextPaint();
        this.mDayOfWeekPaint = new TextPaint();
        this.mDayPaint = new TextPaint();
        this.mDaySelectorPaint = new Paint();
        this.mDayHighlightPaint = new Paint();
        this.mDayRangeSelectorPaint = new Paint();
        this.mCalendar = Calendar.getInstance();
        this.mDayOfWeekLabelCalendar = Calendar.getInstance();
        this.mActivatedDays = new ActivatedDays();
        this.mToday = -1;
        this.mWeekStart = 1;
        this.mEnabledDayStart = 1;
        this.mEnabledDayEnd = 31;
        this.mTouchedItem = -1;
        this.mInitialTarget = -1;
        init();
    }

    @TargetApi(21)
    public SimpleMonthView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.DRAW_RECT = 0;
        this.DRAW_RECT_WITH_CURVE_ON_LEFT = 1;
        this.DRAW_RECT_WITH_CURVE_ON_RIGHT = 2;
        this.mMonthPaint = new TextPaint();
        this.mDayOfWeekPaint = new TextPaint();
        this.mDayPaint = new TextPaint();
        this.mDaySelectorPaint = new Paint();
        this.mDayHighlightPaint = new Paint();
        this.mDayRangeSelectorPaint = new Paint();
        this.mCalendar = Calendar.getInstance();
        this.mDayOfWeekLabelCalendar = Calendar.getInstance();
        this.mActivatedDays = new ActivatedDays();
        this.mToday = -1;
        this.mWeekStart = 1;
        this.mEnabledDayStart = 1;
        this.mEnabledDayEnd = 31;
        this.mTouchedItem = -1;
        this.mInitialTarget = -1;
        init();
    }

    private void init() {
        String titleFormat;
        this.mContext = getContext();
        this.mTouchSlopSquared = ViewConfiguration.get(this.mContext).getScaledTouchSlop() * ViewConfiguration.get(this.mContext).getScaledTouchSlop();
        Resources res = this.mContext.getResources();
        this.mDesiredMonthHeight = res.getDimensionPixelSize(C0563R.dimen.sp_date_picker_month_height);
        this.mDesiredDayOfWeekHeight = res.getDimensionPixelSize(C0563R.dimen.sp_date_picker_day_of_week_height);
        this.mDesiredDayHeight = res.getDimensionPixelSize(C0563R.dimen.sp_date_picker_day_height);
        this.mDesiredCellWidth = res.getDimensionPixelSize(C0563R.dimen.sp_date_picker_day_width);
        this.mDesiredDaySelectorRadius = res.getDimensionPixelSize(C0563R.dimen.sp_date_picker_day_selector_radius);
        this.mPaddingRangeIndicator = (float) res.getDimensionPixelSize(C0563R.dimen.sp_month_view_range_padding);
        this.mTouchHelper = new MonthViewTouchHelper(this);
        ViewCompat.setAccessibilityDelegate(this, this.mTouchHelper);
        ViewCompat.setImportantForAccessibility(this, 1);
        Locale locale = res.getConfiguration().locale;
        if (SUtils.isApi_18_OrHigher()) {
            titleFormat = DateFormat.getBestDateTimePattern(locale, DEFAULT_TITLE_FORMAT);
        } else {
            titleFormat = DateTimePatternHelper.getBestDateTimePattern(locale, 1);
        }
        this.mTitleFormatter = new SimpleDateFormat(titleFormat, locale);
        this.mDayOfWeekFormatter = new SimpleDateFormat(DAY_OF_WEEK_FORMAT, locale);
        this.mDayFormatter = NumberFormat.getIntegerInstance(locale);
        initPaints(res);
    }

    private ColorStateList applyTextAppearance(Paint p, int resId) {
        TextView tv = new TextView(this.mContext);
        if (SUtils.isApi_23_OrHigher()) {
            tv.setTextAppearance(resId);
        } else {
            tv.setTextAppearance(this.mContext, resId);
        }
        p.setTypeface(tv.getTypeface());
        p.setTextSize(tv.getTextSize());
        ColorStateList textColor = tv.getTextColors();
        if (textColor != null) {
            p.setColor(textColor.getColorForState(ENABLED_STATE_SET, 0));
        }
        return textColor;
    }

    public int getMonthHeight() {
        return this.mMonthHeight;
    }

    public int getCellWidth() {
        return this.mCellWidth;
    }

    public void setMonthTextAppearance(int resId) {
        applyTextAppearance(this.mMonthPaint, resId);
        invalidate();
    }

    public void setDayOfWeekTextAppearance(int resId) {
        applyTextAppearance(this.mDayOfWeekPaint, resId);
        invalidate();
    }

    public void setDayTextAppearance(int resId) {
        ColorStateList textColor = applyTextAppearance(this.mDayPaint, resId);
        if (textColor != null) {
            this.mDayTextColor = textColor;
        }
        invalidate();
    }

    public CharSequence getTitle() {
        if (this.mTitle == null) {
            this.mTitle = this.mTitleFormatter.format(this.mCalendar.getTime());
        }
        return this.mTitle;
    }

    private void initPaints(Resources res) {
        String monthTypeface = res.getString(C0563R.string.sp_date_picker_month_typeface);
        String dayOfWeekTypeface = res.getString(C0563R.string.sp_date_picker_day_of_week_typeface);
        String dayTypeface = res.getString(C0563R.string.sp_date_picker_day_typeface);
        int monthTextSize = res.getDimensionPixelSize(C0563R.dimen.sp_date_picker_month_text_size);
        int dayOfWeekTextSize = res.getDimensionPixelSize(C0563R.dimen.sp_date_picker_day_of_week_text_size);
        int dayTextSize = res.getDimensionPixelSize(C0563R.dimen.sp_date_picker_day_text_size);
        this.mMonthPaint.setAntiAlias(true);
        this.mMonthPaint.setTextSize((float) monthTextSize);
        this.mMonthPaint.setTypeface(Typeface.create(monthTypeface, 0));
        this.mMonthPaint.setTextAlign(Align.CENTER);
        this.mMonthPaint.setStyle(Style.FILL);
        this.mDayOfWeekPaint.setAntiAlias(true);
        this.mDayOfWeekPaint.setTextSize((float) dayOfWeekTextSize);
        this.mDayOfWeekPaint.setTypeface(Typeface.create(dayOfWeekTypeface, 0));
        this.mDayOfWeekPaint.setTextAlign(Align.CENTER);
        this.mDayOfWeekPaint.setStyle(Style.FILL);
        this.mDaySelectorPaint.setAntiAlias(true);
        this.mDaySelectorPaint.setStyle(Style.FILL);
        this.mDayHighlightPaint.setAntiAlias(true);
        this.mDayHighlightPaint.setStyle(Style.FILL);
        this.mDayRangeSelectorPaint.setAntiAlias(true);
        this.mDayRangeSelectorPaint.setStyle(Style.FILL);
        this.mDayPaint.setAntiAlias(true);
        this.mDayPaint.setTextSize((float) dayTextSize);
        this.mDayPaint.setTypeface(Typeface.create(dayTypeface, 0));
        this.mDayPaint.setTextAlign(Align.CENTER);
        this.mDayPaint.setStyle(Style.FILL);
    }

    void setMonthTextColor(ColorStateList monthTextColor) {
        this.mMonthPaint.setColor(monthTextColor.getColorForState(ENABLED_STATE_SET, 0));
        invalidate();
    }

    void setDayOfWeekTextColor(ColorStateList dayOfWeekTextColor) {
        this.mDayOfWeekPaint.setColor(dayOfWeekTextColor.getColorForState(ENABLED_STATE_SET, 0));
        invalidate();
    }

    void setDayTextColor(ColorStateList dayTextColor) {
        this.mDayTextColor = dayTextColor;
        invalidate();
    }

    void setDaySelectorColor(ColorStateList dayBackgroundColor) {
        int activatedColor = dayBackgroundColor.getColorForState(SUtils.resolveStateSet(3), 0);
        this.mDaySelectorPaint.setColor(activatedColor);
        this.mDayRangeSelectorPaint.setColor(activatedColor);
        this.mDayRangeSelectorPaint.setAlpha(PL2303Driver.BAUD150);
        invalidate();
    }

    void setDayHighlightColor(ColorStateList dayHighlightColor) {
        this.mDayHighlightPaint.setColor(dayHighlightColor.getColorForState(SUtils.resolveStateSet(5), 0));
        invalidate();
    }

    public void setOnDayClickListener(OnDayClickListener listener) {
        this.mOnDayClickListener = listener;
    }

    public boolean dispatchHoverEvent(MotionEvent event) {
        return this.mTouchHelper.dispatchHoverEvent(event) || super.dispatchHoverEvent(event);
    }

    private boolean isStillAClick(int x, int y) {
        return ((x - this.mDownX) * (x - this.mDownX)) + ((y - this.mDownY) * (y - this.mDownY)) <= this.mTouchSlopSquared;
    }

    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) (event.getX() + 0.5f);
        int y = (int) (event.getY() + 0.5f);
        switch (event.getAction()) {
            case 0:
                this.mDownX = x;
                this.mDownY = y;
                this.mInitialTarget = getDayAtLocation(this.mDownX, this.mDownY);
                if (this.mInitialTarget >= 0) {
                    if (this.mPendingCheckForTap == null) {
                        this.mPendingCheckForTap = new CheckForTap();
                    }
                    postDelayed(this.mPendingCheckForTap, (long) ViewConfiguration.getTapTimeout());
                    break;
                }
                return false;
            case 1:
                onDayClicked(this.mInitialTarget);
                break;
            case 2:
                if (!isStillAClick(x, y)) {
                    if (this.mPendingCheckForTap != null) {
                        removeCallbacks(this.mPendingCheckForTap);
                    }
                    this.mInitialTarget = -1;
                    if (this.mTouchedItem >= 0) {
                        this.mTouchedItem = -1;
                        invalidate();
                        break;
                    }
                }
                break;
            case 3:
                break;
        }
        if (this.mPendingCheckForTap != null) {
            removeCallbacks(this.mPendingCheckForTap);
        }
        this.mTouchedItem = -1;
        this.mInitialTarget = -1;
        invalidate();
        return true;
    }

    protected void onDraw(Canvas canvas) {
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        canvas.translate((float) paddingLeft, (float) paddingTop);
        drawMonth(canvas);
        drawDaysOfWeek(canvas);
        drawDays(canvas);
        canvas.translate((float) (-paddingLeft), (float) (-paddingTop));
    }

    private void drawMonth(Canvas canvas) {
        canvas.drawText(getTitle().toString(), ((float) this.mPaddedWidth) / 2.0f, (((float) this.mMonthHeight) - (this.mMonthPaint.ascent() + this.mMonthPaint.descent())) / 2.0f, this.mMonthPaint);
    }

    private void drawDaysOfWeek(Canvas canvas) {
        TextPaint p = this.mDayOfWeekPaint;
        int headerHeight = this.mMonthHeight;
        int rowHeight = this.mDayOfWeekHeight;
        int colWidth = this.mCellWidth;
        float halfLineHeight = (p.ascent() + p.descent()) / 2.0f;
        int rowCenter = headerHeight + (rowHeight / 2);
        for (int col = 0; col < 7; col++) {
            int colCenterRtl;
            int colCenter = (colWidth * col) + (colWidth / 2);
            if (SUtils.isLayoutRtlCompat(this)) {
                colCenterRtl = this.mPaddedWidth - colCenter;
            } else {
                colCenterRtl = colCenter;
            }
            canvas.drawText(getDayOfWeekLabel((this.mWeekStart + col) % 7), (float) colCenterRtl, ((float) rowCenter) - halfLineHeight, p);
        }
    }

    private String getDayOfWeekLabel(int dayOfWeek) {
        this.mDayOfWeekLabelCalendar.set(7, dayOfWeek);
        return this.mDayOfWeekFormatter.format(this.mDayOfWeekLabelCalendar.getTime());
    }

    private void drawDays(Canvas canvas) {
        Paint p = this.mDayPaint;
        float rowHeight = (float) this.mDayHeight;
        float colWidth = (float) this.mCellWidth;
        float halfLineHeight = (p.ascent() + p.descent()) / 2.0f;
        float rowCenter = ((float) (this.mMonthHeight + this.mDayOfWeekHeight)) + (rowHeight / 2.0f);
        int day = 1;
        int col = findDayOffset();
        while (day <= this.mDaysInMonth) {
            float colCenterRtl;
            int dayTextColor;
            float colCenter = (((float) col) * colWidth) + (colWidth / 2.0f);
            if (SUtils.isLayoutRtlCompat(this)) {
                colCenterRtl = ((float) this.mPaddedWidth) - colCenter;
            } else {
                colCenterRtl = colCenter;
            }
            int stateMask = 0;
            boolean isDayEnabled = isDayEnabled(day);
            if (isDayEnabled) {
                stateMask = 0 | 1;
            }
            boolean isDayInActivatedRange = this.mActivatedDays.isValid() && this.mActivatedDays.isActivated(day);
            if (!this.mActivatedDays.isSelected(day)) {
                if (isDayInActivatedRange) {
                    stateMask |= 2;
                    int bgShape = 0;
                    if (this.mActivatedDays.isSingleDay()) {
                        if (this.mActivatedDays.isStartOfMonth()) {
                            bgShape = 2;
                        } else {
                            bgShape = 1;
                        }
                    } else if (this.mActivatedDays.isStartingDayOfRange(day)) {
                        bgShape = 1;
                    } else if (this.mActivatedDays.isEndingDayOfRange(day)) {
                        bgShape = 2;
                    }
                    float horDistFromCenter = (colWidth > (rowHeight - (2.0f * this.mPaddingRangeIndicator)) ? 1 : (colWidth == (rowHeight - (2.0f * this.mPaddingRangeIndicator)) ? 0 : -1)) > 0 ? (rowHeight / 2.0f) - this.mPaddingRangeIndicator : colWidth / 2.0f;
                    switch (bgShape) {
                        case 1:
                            int leftRectArcRight;
                            int leftRectArcLeft = ((int) (colCenterRtl - horDistFromCenter)) % 2 == 1 ? ((int) (colCenterRtl - horDistFromCenter)) + 1 : (int) (colCenterRtl - horDistFromCenter);
                            if (((int) (colCenterRtl + horDistFromCenter)) % 2 == 1) {
                                leftRectArcRight = ((int) (colCenterRtl + horDistFromCenter)) + 1;
                            } else {
                                leftRectArcRight = (int) (colCenterRtl + horDistFromCenter);
                            }
                            RectF leftArcRect = new RectF((float) leftRectArcLeft, (rowCenter - (rowHeight / 2.0f)) + this.mPaddingRangeIndicator, (float) leftRectArcRight, ((rowHeight / 2.0f) + rowCenter) - this.mPaddingRangeIndicator);
                            canvas.drawArc(leftArcRect, 90.0f, 180.0f, true, this.mDayRangeSelectorPaint);
                            canvas.drawRect(new RectF(leftArcRect.centerX(), (rowCenter - (rowHeight / 2.0f)) + this.mPaddingRangeIndicator, (colWidth / 2.0f) + colCenterRtl, ((rowHeight / 2.0f) + rowCenter) - this.mPaddingRangeIndicator), this.mDayRangeSelectorPaint);
                            break;
                        case 2:
                            int rightRectArcRight;
                            int rightRectArcLeft = ((int) (colCenterRtl - horDistFromCenter)) % 2 == 1 ? ((int) (colCenterRtl - horDistFromCenter)) + 1 : (int) (colCenterRtl - horDistFromCenter);
                            if (((int) (colCenterRtl + horDistFromCenter)) % 2 == 1) {
                                rightRectArcRight = ((int) (colCenterRtl + horDistFromCenter)) + 1;
                            } else {
                                rightRectArcRight = (int) (colCenterRtl + horDistFromCenter);
                            }
                            RectF rightArcRect = new RectF((float) rightRectArcLeft, (rowCenter - (rowHeight / 2.0f)) + this.mPaddingRangeIndicator, (float) rightRectArcRight, ((rowHeight / 2.0f) + rowCenter) - this.mPaddingRangeIndicator);
                            canvas.drawArc(rightArcRect, 270.0f, 180.0f, true, this.mDayRangeSelectorPaint);
                            canvas.drawRect(new RectF(colCenterRtl - (colWidth / 2.0f), (rowCenter - (rowHeight / 2.0f)) + this.mPaddingRangeIndicator, rightArcRect.centerX(), ((rowHeight / 2.0f) + rowCenter) - this.mPaddingRangeIndicator), this.mDayRangeSelectorPaint);
                            break;
                        default:
                            canvas.drawRect(new RectF(colCenterRtl - (colWidth / 2.0f), (rowCenter - (rowHeight / 2.0f)) + this.mPaddingRangeIndicator, (colWidth / 2.0f) + colCenterRtl, ((rowHeight / 2.0f) + rowCenter) - this.mPaddingRangeIndicator), this.mDayRangeSelectorPaint);
                            break;
                    }
                }
            }
            stateMask |= 2;
            canvas.drawCircle(colCenterRtl, rowCenter, (float) this.mDaySelectorRadius, this.mDaySelectorPaint);
            if (this.mTouchedItem == day) {
                stateMask |= 4;
                if (isDayEnabled) {
                    canvas.drawCircle(colCenterRtl, rowCenter, (float) this.mDaySelectorRadius, this.mDayHighlightPaint);
                }
            }
            if (!(this.mToday == day) || isDayInActivatedRange) {
                dayTextColor = this.mDayTextColor.getColorForState(SUtils.resolveStateSet(stateMask), 0);
            } else {
                dayTextColor = this.mDaySelectorPaint.getColor();
            }
            p.setColor(dayTextColor);
            canvas.drawText(this.mDayFormatter.format((long) day), colCenterRtl, rowCenter - halfLineHeight, p);
            col++;
            if (col == 7) {
                col = 0;
                rowCenter += rowHeight;
            }
            day++;
        }
    }

    private boolean isDayEnabled(int day) {
        return day >= this.mEnabledDayStart && day <= this.mEnabledDayEnd;
    }

    private boolean isValidDayOfMonth(int day) {
        return day >= 1 && day <= this.mDaysInMonth;
    }

    private static boolean isValidDayOfWeek(int day) {
        return day >= 1 && day <= 7;
    }

    private static boolean isValidMonth(int month) {
        return month >= 0 && month <= 11;
    }

    public void selectAllDays() {
        setSelectedDays(1, SUtils.getDaysInMonth(this.mMonth, this.mYear), Type.RANGE);
    }

    public void setSelectedDays(int selectedDayStart, int selectedDayEnd, Type selectedDateType) {
        this.mActivatedDays.startingDay = selectedDayStart;
        this.mActivatedDays.endingDay = selectedDayEnd;
        this.mActivatedDays.selectedDateType = selectedDateType;
        this.mTouchHelper.invalidateRoot();
        invalidate();
    }

    public void setFirstDayOfWeek(int weekStart) {
        if (isValidDayOfWeek(weekStart)) {
            this.mWeekStart = weekStart;
        } else {
            this.mWeekStart = this.mCalendar.getFirstDayOfWeek();
        }
        this.mTouchHelper.invalidateRoot();
        invalidate();
    }

    void setMonthParams(int month, int year, int weekStart, int enabledDayStart, int enabledDayEnd, int selectedDayStart, int selectedDayEnd, Type selectedDateType) {
        if (isValidMonth(month)) {
            this.mMonth = month;
        }
        this.mYear = year;
        this.mCalendar.set(2, this.mMonth);
        this.mCalendar.set(1, this.mYear);
        this.mCalendar.set(5, 1);
        this.mDayOfWeekStart = this.mCalendar.get(7);
        if (isValidDayOfWeek(weekStart)) {
            this.mWeekStart = weekStart;
        } else {
            this.mWeekStart = this.mCalendar.getFirstDayOfWeek();
        }
        Calendar today = Calendar.getInstance();
        this.mToday = -1;
        this.mDaysInMonth = SUtils.getDaysInMonth(this.mMonth, this.mYear);
        for (int i = 0; i < this.mDaysInMonth; i++) {
            int day = i + 1;
            if (sameDay(day, today)) {
                this.mToday = day;
            }
        }
        this.mEnabledDayStart = SUtils.constrain(enabledDayStart, 1, this.mDaysInMonth);
        this.mEnabledDayEnd = SUtils.constrain(enabledDayEnd, this.mEnabledDayStart, this.mDaysInMonth);
        this.mTitle = null;
        this.mActivatedDays.startingDay = selectedDayStart;
        this.mActivatedDays.endingDay = selectedDayEnd;
        this.mActivatedDays.selectedDateType = selectedDateType;
        this.mTouchHelper.invalidateRoot();
    }

    private boolean sameDay(int day, Calendar today) {
        if (this.mYear == today.get(1) && this.mMonth == today.get(2) && day == today.get(5)) {
            return true;
        }
        return false;
    }

    @TargetApi(17)
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(resolveSize(((this.mDesiredCellWidth * 7) + (SUtils.isApi_17_OrHigher() ? getPaddingStart() : getPaddingLeft())) + (SUtils.isApi_17_OrHigher() ? getPaddingEnd() : getPaddingRight()), widthMeasureSpec), resolveSize(((((this.mDesiredDayHeight * 6) + this.mDesiredDayOfWeekHeight) + this.mDesiredMonthHeight) + getPaddingTop()) + getPaddingBottom(), heightMeasureSpec));
    }

    public void onRtlPropertiesChanged(int layoutDirection) {
        super.onRtlPropertiesChanged(layoutDirection);
        requestLayout();
    }

    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        if (changed) {
            int w = right - left;
            int h = bottom - top;
            int paddingLeft = getPaddingLeft();
            int paddingTop = getPaddingTop();
            int paddingRight = getPaddingRight();
            int paddingBottom = getPaddingBottom();
            int paddedWidth = (w - paddingRight) - paddingLeft;
            int paddedHeight = (h - paddingBottom) - paddingTop;
            if (paddedWidth != this.mPaddedWidth && paddedHeight != this.mPaddedHeight) {
                this.mPaddedWidth = paddedWidth;
                this.mPaddedHeight = paddedHeight;
                float scaleH = ((float) paddedHeight) / ((float) ((getMeasuredHeight() - paddingTop) - paddingBottom));
                int cellWidth = this.mPaddedWidth / 7;
                this.mMonthHeight = (int) (((float) this.mDesiredMonthHeight) * scaleH);
                this.mDayOfWeekHeight = (int) (((float) this.mDesiredDayOfWeekHeight) * scaleH);
                this.mDayHeight = (int) (((float) this.mDesiredDayHeight) * scaleH);
                this.mCellWidth = cellWidth;
                this.mDaySelectorRadius = Math.min(this.mDesiredDaySelectorRadius, Math.min((cellWidth / 2) + Math.min(paddingLeft, paddingRight), (this.mDayHeight / 2) + paddingBottom));
                this.mTouchHelper.invalidateRoot();
            }
        }
    }

    private int findDayOffset() {
        int offset = this.mDayOfWeekStart - this.mWeekStart;
        if (this.mDayOfWeekStart < this.mWeekStart) {
            return offset + 7;
        }
        return offset;
    }

    public int getDayAtLocation(int x, int y) {
        int paddedX = x - getPaddingLeft();
        if (paddedX < 0 || paddedX >= this.mPaddedWidth) {
            return -1;
        }
        int headerHeight = this.mMonthHeight + this.mDayOfWeekHeight;
        int paddedY = y - getPaddingTop();
        if (paddedY < headerHeight || paddedY >= this.mPaddedHeight) {
            return -1;
        }
        int paddedXRtl;
        if (SUtils.isLayoutRtlCompat(this)) {
            paddedXRtl = this.mPaddedWidth - paddedX;
        } else {
            paddedXRtl = paddedX;
        }
        int day = ((((paddedXRtl * 7) / this.mPaddedWidth) + (((paddedY - headerHeight) / this.mDayHeight) * 7)) + 1) - findDayOffset();
        if (isValidDayOfMonth(day)) {
            return day;
        }
        return -1;
    }

    private boolean getBoundsForDay(int id, Rect outBounds) {
        if (!isValidDayOfMonth(id)) {
            return false;
        }
        int left;
        int index = (id - 1) + findDayOffset();
        int col = index % 7;
        int colWidth = this.mCellWidth;
        if (SUtils.isLayoutRtlCompat(this)) {
            left = (getWidth() - getPaddingRight()) - ((col + 1) * colWidth);
        } else {
            left = getPaddingLeft() + (col * colWidth);
        }
        int row = index / 7;
        int rowHeight = this.mDayHeight;
        int top = (getPaddingTop() + (this.mMonthHeight + this.mDayOfWeekHeight)) + (row * rowHeight);
        outBounds.set(left, top, left + colWidth, top + rowHeight);
        return true;
    }

    private boolean onDayClicked(int day) {
        if (!isValidDayOfMonth(day) || !isDayEnabled(day)) {
            return false;
        }
        if (this.mOnDayClickListener != null) {
            Calendar date = Calendar.getInstance();
            date.set(this.mYear, this.mMonth, day);
            this.mOnDayClickListener.onDayClick(this, date);
        }
        this.mTouchHelper.sendEventForVirtualView(day, 1);
        return true;
    }

    public Calendar composeDate(int day) {
        if (!isValidDayOfMonth(day) || !isDayEnabled(day)) {
            return null;
        }
        Calendar date = Calendar.getInstance();
        date.set(this.mYear, this.mMonth, day);
        return date;
    }
}
