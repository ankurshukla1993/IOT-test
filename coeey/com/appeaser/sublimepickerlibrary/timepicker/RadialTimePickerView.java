package com.appeaser.sublimepickerlibrary.timepicker;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.Rect;
import android.graphics.Region.Op;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.widget.ExploreByTouchHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import com.appeaser.sublimepickerlibrary.C0563R;
import com.appeaser.sublimepickerlibrary.utilities.SUtils;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class RadialTimePickerView extends View {
    private static final int ALPHA_OPAQUE = 255;
    private static final int ALPHA_TRANSPARENT = 0;
    private static final int AM = 0;
    private static final float[] COS_30 = new float[12];
    private static final int DEGREES_FOR_ONE_HOUR = 30;
    private static final int DEGREES_FOR_ONE_MINUTE = 6;
    private static final int FADE_IN_DURATION = 500;
    private static final int FADE_OUT_DURATION = 500;
    private static final int HOURS = 0;
    private static final int HOURS_INNER = 2;
    private static final int HOURS_IN_CIRCLE = 12;
    private static final int[] HOURS_NUMBERS = new int[]{12, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
    private static final int[] HOURS_NUMBERS_24 = new int[]{0, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23};
    private static final int MINUTES = 1;
    private static final int MINUTES_IN_CIRCLE = 60;
    private static final int[] MINUTES_NUMBERS = new int[]{0, 5, 10, 15, 20, 25, 30, 35, 40, 45, 50, 55};
    private static final int NUM_POSITIONS = 12;
    private static final int PM = 1;
    private static final int SELECTOR_CIRCLE = 0;
    private static final int SELECTOR_DOT = 1;
    private static final int SELECTOR_LINE = 2;
    private static final float[] SIN_30 = new float[12];
    private static final int[] SNAP_PREFER_30S_MAP = new int[361];
    private static final String TAG = RadialTimePickerView.class.getSimpleName();
    private final IntHolder[] mAlpha;
    private int mAmOrPm;
    private int mCenterDotRadius;
    private boolean mChangedDuringTouch;
    private int mCircleRadius;
    private float mDisabledAlpha;
    private int mHalfwayDist;
    private final String[] mHours12Texts;
    private final ArrayList<Animator> mHoursToMinutesAnims;
    private final String[] mInnerHours24Texts;
    private String[] mInnerTextHours;
    private final float[] mInnerTextX;
    private final float[] mInnerTextY;
    private boolean mInputEnabled;
    private final InvalidateUpdateListener mInvalidateUpdateListener;
    private boolean mIs24HourMode;
    private boolean mIsOnInnerCircle;
    private OnValueSelectedListener mListener;
    private int mMaxDistForOuterNumber;
    private int mMinDistForInnerNumber;
    private final ArrayList<Animator> mMinuteToHoursAnims;
    private String[] mMinutesText;
    private final String[] mMinutesTexts;
    private final String[] mOuterHours24Texts;
    private String[] mOuterTextHours;
    private final float[][] mOuterTextX;
    private final float[][] mOuterTextY;
    private final Paint[] mPaint;
    private final Paint mPaintBackground;
    private final Paint mPaintCenter;
    private final Paint[][] mPaintSelector;
    private final int[] mSelectionDegrees;
    private int mSelectorColor;
    private int mSelectorDotColor;
    private int mSelectorDotRadius;
    private final Path mSelectorPath;
    private int mSelectorRadius;
    private int mSelectorStroke;
    private boolean mShowHours;
    private final ColorStateList[] mTextColor;
    private final int[] mTextInset;
    private final int[] mTextSize;
    private RadialPickerTouchHelper mTouchHelper;
    private AnimatorSet mTransition;
    private Typeface mTypeface;
    private int mXCenter;
    private int mYCenter;

    private static class IntHolder {
        private int mValue;

        public IntHolder(int value) {
            this.mValue = value;
        }

        public void setValue(int value) {
            this.mValue = value;
        }

        public int getValue() {
            return this.mValue;
        }
    }

    private class InvalidateUpdateListener implements AnimatorUpdateListener {
        private InvalidateUpdateListener() {
        }

        public void onAnimationUpdate(ValueAnimator animation) {
            RadialTimePickerView.this.invalidate();
        }
    }

    public interface OnValueSelectedListener {
        void onValueSelected(int i, int i2, boolean z);
    }

    private class RadialPickerTouchHelper extends ExploreByTouchHelper {
        private final int MASK_TYPE = 15;
        private final int MASK_VALUE = 255;
        private final int MINUTE_INCREMENT = 5;
        private final int SHIFT_TYPE = 0;
        private final int SHIFT_VALUE = 8;
        private final int TYPE_HOUR = 1;
        private final int TYPE_MINUTE = 2;
        private final Rect mTempRect = new Rect();

        public RadialPickerTouchHelper() {
            super(RadialTimePickerView.this);
        }

        protected void getVisibleVirtualViews(List<Integer> virtualViewIds) {
            int i;
            if (RadialTimePickerView.this.mShowHours) {
                int min;
                if (RadialTimePickerView.this.mIs24HourMode) {
                    min = 0;
                } else {
                    min = 1;
                }
                int max = RadialTimePickerView.this.mIs24HourMode ? 23 : 12;
                for (i = min; i <= max; i++) {
                    virtualViewIds.add(Integer.valueOf(makeId(1, i)));
                }
                return;
            }
            int current = RadialTimePickerView.this.getCurrentMinute();
            i = 0;
            while (i < 60) {
                virtualViewIds.add(Integer.valueOf(makeId(2, i)));
                if (current > i && current < i + 5) {
                    virtualViewIds.add(Integer.valueOf(makeId(2, current)));
                }
                i += 5;
            }
        }

        protected void onPopulateEventForVirtualView(int virtualViewId, AccessibilityEvent event) {
            event.setClassName(getClass().getName());
            event.setContentDescription(getVirtualViewDescription(getTypeFromId(virtualViewId), getValueFromId(virtualViewId)));
        }

        protected void onPopulateNodeForVirtualView(int virtualViewId, AccessibilityNodeInfoCompat node) {
            node.setClassName(getClass().getName());
            node.addAction(16);
            int type = getTypeFromId(virtualViewId);
            int value = getValueFromId(virtualViewId);
            node.setContentDescription(getVirtualViewDescription(type, value));
            getBoundsForVirtualView(virtualViewId, this.mTempRect);
            node.setBoundsInParent(this.mTempRect);
            node.setSelected(isVirtualViewSelected(type, value));
            int nextId = getVirtualViewIdAfter(type, value);
            if (nextId != Integer.MIN_VALUE) {
                node.setTraversalBefore(RadialTimePickerView.this, nextId);
            }
        }

        protected boolean onPerformActionForVirtualView(int virtualViewId, int action, Bundle arguments) {
            if (action == 16) {
                int type = getTypeFromId(virtualViewId);
                int value = getValueFromId(virtualViewId);
                if (type == 1) {
                    RadialTimePickerView.this.setCurrentHour(RadialTimePickerView.this.mIs24HourMode ? value : hour12To24(value, RadialTimePickerView.this.mAmOrPm));
                    return true;
                } else if (type == 2) {
                    RadialTimePickerView.this.setCurrentMinute(value);
                    return true;
                }
            }
            return false;
        }

        public void onInitializeAccessibilityNodeInfo(View host, AccessibilityNodeInfoCompat info) {
            super.onInitializeAccessibilityNodeInfo(host, info);
            info.addAction(4096);
            info.addAction(8192);
        }

        public boolean performAccessibilityAction(View host, int action, Bundle arguments) {
            if (super.performAccessibilityAction(host, action, arguments)) {
                return true;
            }
            switch (action) {
                case 4096:
                    adjustPicker(1);
                    return true;
                case 8192:
                    adjustPicker(-1);
                    return true;
                default:
                    return false;
            }
        }

        private void adjustPicker(int step) {
            int stepSize;
            int initialStep;
            int minValue;
            int maxValue;
            if (RadialTimePickerView.this.mShowHours) {
                stepSize = 1;
                int currentHour24 = RadialTimePickerView.this.getCurrentHour();
                if (RadialTimePickerView.this.mIs24HourMode) {
                    initialStep = currentHour24;
                    minValue = 0;
                    maxValue = 23;
                } else {
                    initialStep = hour24To12(currentHour24);
                    minValue = 1;
                    maxValue = 12;
                }
            } else {
                stepSize = 5;
                initialStep = RadialTimePickerView.this.getCurrentMinute() / 5;
                minValue = 0;
                maxValue = 55;
            }
            int clampedValue = SUtils.constrain((initialStep + step) * stepSize, minValue, maxValue);
            if (RadialTimePickerView.this.mShowHours) {
                RadialTimePickerView.this.setCurrentHour(clampedValue);
            } else {
                RadialTimePickerView.this.setCurrentMinute(clampedValue);
            }
        }

        protected int getVirtualViewAt(float x, float y) {
            int degrees = RadialTimePickerView.this.getDegreesFromXY(x, y, true);
            if (degrees == -1) {
                return Integer.MIN_VALUE;
            }
            int snapDegrees = RadialTimePickerView.snapOnly30s(degrees, 0) % 360;
            if (RadialTimePickerView.this.mShowHours) {
                int hour24 = RadialTimePickerView.this.getHourForDegrees(snapDegrees, RadialTimePickerView.this.getInnerCircleFromXY(x, y));
                return makeId(1, RadialTimePickerView.this.mIs24HourMode ? hour24 : hour24To12(hour24));
            }
            int minute;
            int current = RadialTimePickerView.this.getCurrentMinute();
            int touched = RadialTimePickerView.this.getMinuteForDegrees(degrees);
            int snapped = RadialTimePickerView.this.getMinuteForDegrees(snapDegrees);
            if (getCircularDiff(current, touched, 60) < getCircularDiff(snapped, touched, 60)) {
                minute = current;
            } else {
                minute = snapped;
            }
            return makeId(2, minute);
        }

        private int getCircularDiff(int first, int second, int max) {
            int diff = Math.abs(first - second);
            return diff > max / 2 ? max - diff : diff;
        }

        private int getVirtualViewIdAfter(int type, int value) {
            int nextValue;
            if (type == 1) {
                nextValue = value + 1;
                if (nextValue <= (RadialTimePickerView.this.mIs24HourMode ? 23 : 12)) {
                    return makeId(type, nextValue);
                }
            } else if (type == 2) {
                int current = RadialTimePickerView.this.getCurrentMinute();
                nextValue = (value - (value % 5)) + 5;
                if (value < current && nextValue > current) {
                    return makeId(type, current);
                }
                if (nextValue < 60) {
                    return makeId(type, nextValue);
                }
            }
            return Integer.MIN_VALUE;
        }

        private int hour12To24(int hour12, int amOrPm) {
            int hour24 = hour12;
            if (hour12 == 12) {
                if (amOrPm == 0) {
                    return 0;
                }
                return hour24;
            } else if (amOrPm == 1) {
                return hour24 + 12;
            } else {
                return hour24;
            }
        }

        private int hour24To12(int hour24) {
            if (hour24 == 0) {
                return 12;
            }
            if (hour24 > 12) {
                return hour24 - 12;
            }
            return hour24;
        }

        private void getBoundsForVirtualView(int virtualViewId, Rect bounds) {
            float centerRadius;
            float radius;
            float degrees;
            int type = getTypeFromId(virtualViewId);
            int value = getValueFromId(virtualViewId);
            if (type == 1) {
                if (RadialTimePickerView.this.getInnerCircleForHour(value)) {
                    centerRadius = (float) (RadialTimePickerView.this.mCircleRadius - RadialTimePickerView.this.mTextInset[2]);
                    radius = (float) RadialTimePickerView.this.mSelectorRadius;
                } else {
                    centerRadius = (float) (RadialTimePickerView.this.mCircleRadius - RadialTimePickerView.this.mTextInset[0]);
                    radius = (float) RadialTimePickerView.this.mSelectorRadius;
                }
                degrees = (float) RadialTimePickerView.this.getDegreesForHour(value);
            } else if (type == 2) {
                centerRadius = (float) (RadialTimePickerView.this.mCircleRadius - RadialTimePickerView.this.mTextInset[1]);
                degrees = (float) RadialTimePickerView.this.getDegreesForMinute(value);
                radius = (float) RadialTimePickerView.this.mSelectorRadius;
            } else {
                centerRadius = 0.0f;
                degrees = 0.0f;
                radius = 0.0f;
            }
            double radians = Math.toRadians((double) degrees);
            float xCenter = ((float) RadialTimePickerView.this.mXCenter) + (((float) Math.sin(radians)) * centerRadius);
            float yCenter = ((float) RadialTimePickerView.this.mYCenter) - (((float) Math.cos(radians)) * centerRadius);
            bounds.set((int) (xCenter - radius), (int) (yCenter - radius), (int) (xCenter + radius), (int) (yCenter + radius));
        }

        private CharSequence getVirtualViewDescription(int type, int value) {
            if (type == 1 || type == 2) {
                return Integer.toString(value);
            }
            return null;
        }

        private boolean isVirtualViewSelected(int type, int value) {
            if (type == 1) {
                if (RadialTimePickerView.this.getCurrentHour() == value) {
                    return true;
                }
                return false;
            } else if (type == 2 && RadialTimePickerView.this.getCurrentMinute() == value) {
                return true;
            } else {
                return false;
            }
        }

        private int makeId(int type, int value) {
            return (type << 0) | (value << 8);
        }

        private int getTypeFromId(int id) {
            return (id >>> 0) & 15;
        }

        private int getValueFromId(int id) {
            return (id >>> 8) & 255;
        }
    }

    static {
        preparePrefer30sMap();
        double angle = 1.5707963267948966d;
        for (int i = 0; i < 12; i++) {
            COS_30[i] = (float) Math.cos(angle);
            SIN_30[i] = (float) Math.sin(angle);
            angle += 0.5235987755982988d;
        }
    }

    private static void preparePrefer30sMap() {
        int snappedOutputDegrees = 0;
        int count = 1;
        int expectedCount = 8;
        for (int degrees = 0; degrees < 361; degrees++) {
            SNAP_PREFER_30S_MAP[degrees] = snappedOutputDegrees;
            if (count == expectedCount) {
                snappedOutputDegrees += 6;
                if (snappedOutputDegrees == 360) {
                    expectedCount = 7;
                } else if (snappedOutputDegrees % 30 == 0) {
                    expectedCount = 14;
                } else {
                    expectedCount = 4;
                }
                count = 1;
            } else {
                count++;
            }
        }
    }

    private static int snapPrefer30s(int degrees) {
        if (SNAP_PREFER_30S_MAP == null) {
            return -1;
        }
        return SNAP_PREFER_30S_MAP[degrees];
    }

    private static int snapOnly30s(int degrees, int forceHigherOrLower) {
        int floor = (degrees / 30) * 30;
        int ceiling = floor + 30;
        if (forceHigherOrLower == 1) {
            return ceiling;
        }
        if (forceHigherOrLower == -1) {
            if (degrees == floor) {
                floor -= 30;
            }
            return floor;
        } else if (degrees - floor < ceiling - degrees) {
            return floor;
        } else {
            return ceiling;
        }
    }

    public RadialTimePickerView(Context context) {
        this(context, null);
    }

    public RadialTimePickerView(Context context, AttributeSet attrs) {
        this(context, attrs, C0563R.attr.spRadialTimePickerStyle);
    }

    public RadialTimePickerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mInvalidateUpdateListener = new InvalidateUpdateListener();
        this.mHours12Texts = new String[12];
        this.mOuterHours24Texts = new String[12];
        this.mInnerHours24Texts = new String[12];
        this.mMinutesTexts = new String[12];
        this.mPaint = new Paint[2];
        this.mAlpha = new IntHolder[2];
        this.mPaintCenter = new Paint();
        this.mPaintSelector = (Paint[][]) Array.newInstance(Paint.class, new int[]{2, 3});
        this.mPaintBackground = new Paint();
        this.mTextColor = new ColorStateList[3];
        this.mTextSize = new int[3];
        this.mTextInset = new int[3];
        this.mOuterTextX = (float[][]) Array.newInstance(Float.TYPE, new int[]{2, 12});
        this.mOuterTextY = (float[][]) Array.newInstance(Float.TYPE, new int[]{2, 12});
        this.mInnerTextX = new float[12];
        this.mInnerTextY = new float[12];
        this.mSelectionDegrees = new int[2];
        this.mHoursToMinutesAnims = new ArrayList();
        this.mMinuteToHoursAnims = new ArrayList();
        this.mSelectorPath = new Path();
        this.mInputEnabled = true;
        this.mChangedDuringTouch = false;
        init(attrs, defStyleAttr, C0563R.style.RadialTimePickerViewStyle);
    }

    @TargetApi(21)
    public RadialTimePickerView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs);
        this.mInvalidateUpdateListener = new InvalidateUpdateListener();
        this.mHours12Texts = new String[12];
        this.mOuterHours24Texts = new String[12];
        this.mInnerHours24Texts = new String[12];
        this.mMinutesTexts = new String[12];
        this.mPaint = new Paint[2];
        this.mAlpha = new IntHolder[2];
        this.mPaintCenter = new Paint();
        this.mPaintSelector = (Paint[][]) Array.newInstance(Paint.class, new int[]{2, 3});
        this.mPaintBackground = new Paint();
        this.mTextColor = new ColorStateList[3];
        this.mTextSize = new int[3];
        this.mTextInset = new int[3];
        this.mOuterTextX = (float[][]) Array.newInstance(Float.TYPE, new int[]{2, 12});
        this.mOuterTextY = (float[][]) Array.newInstance(Float.TYPE, new int[]{2, 12});
        this.mInnerTextX = new float[12];
        this.mInnerTextY = new float[12];
        this.mSelectionDegrees = new int[2];
        this.mHoursToMinutesAnims = new ArrayList();
        this.mMinuteToHoursAnims = new ArrayList();
        this.mSelectorPath = new Path();
        this.mInputEnabled = true;
        this.mChangedDuringTouch = false;
        init(attrs, defStyleAttr, defStyleRes);
    }

    private void init(AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        Context context = getContext();
        TypedValue outValue = new TypedValue();
        context.getTheme().resolveAttribute(16842803, outValue, true);
        this.mDisabledAlpha = outValue.getFloat();
        Resources res = getResources();
        TypedArray a = context.obtainStyledAttributes(attrs, C0563R.styleable.RadialTimePickerView, defStyleAttr, defStyleRes);
        this.mTypeface = Typeface.create("sans-serif", 0);
        for (int i = 0; i < this.mAlpha.length; i++) {
            this.mAlpha[i] = new IntHolder(255);
        }
        this.mTextColor[0] = a.getColorStateList(C0563R.styleable.RadialTimePickerView_spNumbersTextColor);
        this.mTextColor[2] = a.getColorStateList(C0563R.styleable.RadialTimePickerView_spNumbersInnerTextColor);
        this.mTextColor[1] = this.mTextColor[0];
        this.mPaint[0] = new Paint();
        this.mPaint[0].setAntiAlias(true);
        this.mPaint[0].setTextAlign(Align.CENTER);
        this.mPaint[1] = new Paint();
        this.mPaint[1].setAntiAlias(true);
        this.mPaint[1].setTextAlign(Align.CENTER);
        ColorStateList selectorColors = a.getColorStateList(C0563R.styleable.RadialTimePickerView_spNumbersSelectorColor);
        int selectorActivatedColor = ViewCompat.MEASURED_STATE_MASK;
        if (selectorColors != null) {
            selectorActivatedColor = selectorColors.getColorForState(SUtils.resolveStateSet(3), 0);
        }
        this.mPaintCenter.setColor(selectorActivatedColor);
        this.mPaintCenter.setAntiAlias(true);
        int[] activatedStateSet = SUtils.resolveStateSet(3);
        this.mSelectorColor = selectorActivatedColor;
        this.mSelectorDotColor = this.mTextColor[0].getColorForState(activatedStateSet, 0);
        this.mPaintSelector[0][0] = new Paint();
        this.mPaintSelector[0][0].setAntiAlias(true);
        this.mPaintSelector[0][1] = new Paint();
        this.mPaintSelector[0][1].setAntiAlias(true);
        this.mPaintSelector[0][2] = new Paint();
        this.mPaintSelector[0][2].setAntiAlias(true);
        this.mPaintSelector[0][2].setStrokeWidth(2.0f);
        this.mPaintSelector[1][0] = new Paint();
        this.mPaintSelector[1][0].setAntiAlias(true);
        this.mPaintSelector[1][1] = new Paint();
        this.mPaintSelector[1][1].setAntiAlias(true);
        this.mPaintSelector[1][2] = new Paint();
        this.mPaintSelector[1][2].setAntiAlias(true);
        this.mPaintSelector[1][2].setStrokeWidth(2.0f);
        this.mPaintBackground.setColor(a.getColor(C0563R.styleable.RadialTimePickerView_spNumbersBackgroundColor, ContextCompat.getColor(context, C0563R.color.timepicker_default_numbers_background_color_material)));
        this.mPaintBackground.setAntiAlias(true);
        this.mSelectorRadius = res.getDimensionPixelSize(C0563R.dimen.sp_timepicker_selector_radius);
        this.mSelectorStroke = res.getDimensionPixelSize(C0563R.dimen.sp_timepicker_selector_stroke);
        this.mSelectorDotRadius = res.getDimensionPixelSize(C0563R.dimen.sp_timepicker_selector_dot_radius);
        this.mCenterDotRadius = res.getDimensionPixelSize(C0563R.dimen.sp_timepicker_center_dot_radius);
        this.mTextSize[0] = res.getDimensionPixelSize(C0563R.dimen.sp_timepicker_text_size_normal);
        this.mTextSize[1] = res.getDimensionPixelSize(C0563R.dimen.sp_timepicker_text_size_normal);
        this.mTextSize[2] = res.getDimensionPixelSize(C0563R.dimen.sp_timepicker_text_size_inner);
        this.mTextInset[0] = res.getDimensionPixelSize(C0563R.dimen.sp_timepicker_text_inset_normal);
        this.mTextInset[1] = res.getDimensionPixelSize(C0563R.dimen.sp_timepicker_text_inset_normal);
        this.mTextInset[2] = res.getDimensionPixelSize(C0563R.dimen.sp_timepicker_text_inset_inner);
        this.mShowHours = true;
        this.mIs24HourMode = false;
        this.mAmOrPm = 0;
        this.mTouchHelper = new RadialPickerTouchHelper();
        ViewCompat.setAccessibilityDelegate(this, this.mTouchHelper);
        if (ViewCompat.getImportantForAccessibility(this) == 0) {
            ViewCompat.setImportantForAccessibility(this, 1);
        }
        initHoursAndMinutesText();
        initData();
        a.recycle();
        Calendar calendar = Calendar.getInstance(Locale.getDefault());
        int currentHour = calendar.get(11);
        int currentMinute = calendar.get(12);
        setCurrentHourInternal(currentHour, false, false);
        setCurrentMinuteInternal(currentMinute, false);
        setHapticFeedbackEnabled(true);
    }

    public void initialize(int hour, int minute, boolean is24HourMode) {
        if (this.mIs24HourMode != is24HourMode) {
            this.mIs24HourMode = is24HourMode;
            initData();
        }
        setCurrentHourInternal(hour, false, false);
        setCurrentMinuteInternal(minute, false);
    }

    public void setCurrentItemShowing(int item, boolean animate) {
        switch (item) {
            case 0:
                showHours(animate);
                return;
            case 1:
                showMinutes(animate);
                return;
            default:
                Log.e(TAG, "ClockView does not support showing item " + item);
                return;
        }
    }

    public int getCurrentItemShowing() {
        return this.mShowHours ? 0 : 1;
    }

    public void setOnValueSelectedListener(OnValueSelectedListener listener) {
        this.mListener = listener;
    }

    public void setCurrentHour(int hour) {
        setCurrentHourInternal(hour, true, false);
    }

    private void setCurrentHourInternal(int hour, boolean callback, boolean autoAdvance) {
        this.mSelectionDegrees[0] = (hour % 12) * 30;
        int amOrPm = (hour == 0 || hour % 24 < 12) ? 0 : 1;
        boolean isOnInnerCircle = getInnerCircleForHour(hour);
        if (!(this.mAmOrPm == amOrPm && this.mIsOnInnerCircle == isOnInnerCircle)) {
            this.mAmOrPm = amOrPm;
            this.mIsOnInnerCircle = isOnInnerCircle;
            initData();
            this.mTouchHelper.invalidateRoot();
        }
        invalidate();
        if (callback && this.mListener != null) {
            this.mListener.onValueSelected(0, hour, autoAdvance);
        }
    }

    public int getCurrentHour() {
        return getHourForDegrees(this.mSelectionDegrees[0], this.mIsOnInnerCircle);
    }

    private int getHourForDegrees(int degrees, boolean innerCircle) {
        int hour = (degrees / 30) % 12;
        if (this.mIs24HourMode) {
            if (!innerCircle && hour == 0) {
                return 12;
            }
            if (!innerCircle || hour == 0) {
                return hour;
            }
            return hour + 12;
        } else if (this.mAmOrPm == 1) {
            return hour + 12;
        } else {
            return hour;
        }
    }

    private int getDegreesForHour(int hour) {
        if (this.mIs24HourMode) {
            if (hour >= 12) {
                hour -= 12;
            }
        } else if (hour == 12) {
            hour = 0;
        }
        return hour * 30;
    }

    private boolean getInnerCircleForHour(int hour) {
        return this.mIs24HourMode && (hour == 0 || hour > 12);
    }

    public void setCurrentMinute(int minute) {
        setCurrentMinuteInternal(minute, true);
    }

    private void setCurrentMinuteInternal(int minute, boolean callback) {
        this.mSelectionDegrees[1] = (minute % 60) * 6;
        invalidate();
        if (callback && this.mListener != null) {
            this.mListener.onValueSelected(1, minute, false);
        }
    }

    public int getCurrentMinute() {
        return getMinuteForDegrees(this.mSelectionDegrees[1]);
    }

    private int getMinuteForDegrees(int degrees) {
        return degrees / 6;
    }

    private int getDegreesForMinute(int minute) {
        return minute * 6;
    }

    public void setAmOrPm(int val) {
        this.mAmOrPm = val % 2;
        invalidate();
        this.mTouchHelper.invalidateRoot();
    }

    public int getAmOrPm() {
        return this.mAmOrPm;
    }

    private void showHours(boolean animate) {
        if (!this.mShowHours) {
            this.mShowHours = true;
            if (animate) {
                startMinutesToHoursAnimation();
            }
            initData();
            invalidate();
            this.mTouchHelper.invalidateRoot();
        }
    }

    private void showMinutes(boolean animate) {
        if (this.mShowHours) {
            this.mShowHours = false;
            if (animate) {
                startHoursToMinutesAnimation();
            }
            initData();
            invalidate();
            this.mTouchHelper.invalidateRoot();
        }
    }

    private void initHoursAndMinutesText() {
        for (int i = 0; i < 12; i++) {
            this.mHours12Texts[i] = String.format("%d", new Object[]{Integer.valueOf(HOURS_NUMBERS[i])});
            this.mInnerHours24Texts[i] = String.format("%02d", new Object[]{Integer.valueOf(HOURS_NUMBERS_24[i])});
            this.mOuterHours24Texts[i] = String.format("%d", new Object[]{Integer.valueOf(HOURS_NUMBERS[i])});
            this.mMinutesTexts[i] = String.format("%02d", new Object[]{Integer.valueOf(MINUTES_NUMBERS[i])});
        }
    }

    private void initData() {
        int hoursAlpha;
        int minutesAlpha;
        if (this.mIs24HourMode) {
            this.mOuterTextHours = this.mOuterHours24Texts;
            this.mInnerTextHours = this.mInnerHours24Texts;
        } else {
            this.mOuterTextHours = this.mHours12Texts;
            this.mInnerTextHours = this.mHours12Texts;
        }
        this.mMinutesText = this.mMinutesTexts;
        if (this.mShowHours) {
            hoursAlpha = 255;
        } else {
            hoursAlpha = 0;
        }
        this.mAlpha[0].setValue(hoursAlpha);
        if (this.mShowHours) {
            minutesAlpha = 0;
        } else {
            minutesAlpha = 255;
        }
        this.mAlpha[1].setValue(minutesAlpha);
    }

    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        if (changed) {
            this.mXCenter = getWidth() / 2;
            this.mYCenter = getHeight() / 2;
            this.mCircleRadius = Math.min(this.mXCenter, this.mYCenter);
            this.mMinDistForInnerNumber = (this.mCircleRadius - this.mTextInset[2]) - this.mSelectorRadius;
            this.mMaxDistForOuterNumber = (this.mCircleRadius - this.mTextInset[0]) + this.mSelectorRadius;
            this.mHalfwayDist = this.mCircleRadius - ((this.mTextInset[0] + this.mTextInset[2]) / 2);
            calculatePositionsHours();
            calculatePositionsMinutes();
            this.mTouchHelper.invalidateRoot();
        }
    }

    public void onDraw(Canvas canvas) {
        float alphaMod = this.mInputEnabled ? FlexItem.FLEX_SHRINK_DEFAULT : this.mDisabledAlpha;
        drawCircleBackground(canvas);
        drawHours(canvas, alphaMod);
        drawMinutes(canvas, alphaMod);
        drawCenter(canvas, alphaMod);
    }

    private void drawCircleBackground(Canvas canvas) {
        canvas.drawCircle((float) this.mXCenter, (float) this.mYCenter, (float) this.mCircleRadius, this.mPaintBackground);
    }

    private void drawHours(Canvas canvas, float alphaMod) {
        int hoursAlpha = (int) ((((float) this.mAlpha[0].getValue()) * alphaMod) + 0.5f);
        if (hoursAlpha > 0) {
            drawSelector(canvas, this.mIsOnInnerCircle ? 2 : 0, null, alphaMod);
            drawTextElements(canvas, (float) this.mTextSize[0], this.mTypeface, this.mTextColor[0], this.mOuterTextHours, this.mOuterTextX[0], this.mOuterTextY[0], this.mPaint[0], hoursAlpha, !this.mIsOnInnerCircle, this.mSelectionDegrees[0], false);
            if (this.mIs24HourMode && this.mInnerTextHours != null) {
                drawTextElements(canvas, (float) this.mTextSize[2], this.mTypeface, this.mTextColor[2], this.mInnerTextHours, this.mInnerTextX, this.mInnerTextY, this.mPaint[0], hoursAlpha, this.mIsOnInnerCircle, this.mSelectionDegrees[0], false);
            }
        }
    }

    private void drawMinutes(Canvas canvas, float alphaMod) {
        int minutesAlpha = (int) ((((float) this.mAlpha[1].getValue()) * alphaMod) + 0.5f);
        if (minutesAlpha > 0) {
            drawSelector(canvas, 1, this.mSelectorPath, alphaMod);
            canvas.save(2);
            canvas.clipPath(this.mSelectorPath, Op.DIFFERENCE);
            drawTextElements(canvas, (float) this.mTextSize[1], this.mTypeface, this.mTextColor[1], this.mMinutesText, this.mOuterTextX[1], this.mOuterTextY[1], this.mPaint[1], minutesAlpha, false, 0, false);
            canvas.restore();
            canvas.save(2);
            canvas.clipPath(this.mSelectorPath, Op.INTERSECT);
            drawTextElements(canvas, (float) this.mTextSize[1], this.mTypeface, this.mTextColor[1], this.mMinutesText, this.mOuterTextX[1], this.mOuterTextY[1], this.mPaint[1], minutesAlpha, true, this.mSelectionDegrees[1], true);
            canvas.restore();
        }
    }

    private void drawCenter(Canvas canvas, float alphaMod) {
        this.mPaintCenter.setAlpha((int) ((255.0f * alphaMod) + 0.5f));
        canvas.drawCircle((float) this.mXCenter, (float) this.mYCenter, (float) this.mCenterDotRadius, this.mPaintCenter);
    }

    private int applyAlpha(int argb, int alpha) {
        return (16777215 & argb) | (((int) ((((double) ((argb >> 24) & 255)) * (((double) alpha) / 255.0d)) + 0.5d)) << 24);
    }

    private int getMultipliedAlpha(int argb, int alpha) {
        return (int) ((((double) Color.alpha(argb)) * (((double) alpha) / 255.0d)) + 0.5d);
    }

    private void drawSelector(Canvas canvas, int index, Path selectorPath, float alphaMod) {
        int color = applyAlpha(this.mSelectorColor, (int) ((((float) this.mAlpha[index % 2].getValue()) * alphaMod) + 0.5f));
        int selRadius = this.mSelectorRadius;
        int selLength = this.mCircleRadius - this.mTextInset[index];
        double selAngleRad = Math.toRadians((double) this.mSelectionDegrees[index % 2]);
        float selCenterX = ((float) this.mXCenter) + (((float) selLength) * ((float) Math.sin(selAngleRad)));
        float selCenterY = ((float) this.mYCenter) - (((float) selLength) * ((float) Math.cos(selAngleRad)));
        Paint paint = this.mPaintSelector[index % 2][0];
        paint.setColor(color);
        canvas.drawCircle(selCenterX, selCenterY, (float) selRadius, paint);
        if (selectorPath != null) {
            selectorPath.reset();
            selectorPath.addCircle(selCenterX, selCenterY, (float) selRadius, Direction.CCW);
        }
        if (this.mSelectionDegrees[index % 2] % 30 != 0) {
            Paint dotPaint = this.mPaintSelector[index % 2][1];
            dotPaint.setColor(this.mSelectorDotColor);
            canvas.drawCircle(selCenterX, selCenterY, (float) this.mSelectorDotRadius, dotPaint);
        }
        double sin = Math.sin(selAngleRad);
        double cos = Math.cos(selAngleRad);
        int lineLength = selLength - selRadius;
        float linePointX = (float) (((int) (((double) lineLength) * sin)) + (this.mXCenter + ((int) (((double) this.mCenterDotRadius) * sin))));
        float linePointY = (float) ((this.mYCenter - ((int) (((double) this.mCenterDotRadius) * cos))) - ((int) (((double) lineLength) * cos)));
        Paint linePaint = this.mPaintSelector[index % 2][2];
        linePaint.setColor(color);
        linePaint.setStrokeWidth((float) this.mSelectorStroke);
        canvas.drawLine((float) this.mXCenter, (float) this.mYCenter, linePointX, linePointY, linePaint);
    }

    private void calculatePositionsHours() {
        calculatePositions(this.mPaint[0], (float) (this.mCircleRadius - this.mTextInset[0]), (float) this.mXCenter, (float) this.mYCenter, (float) this.mTextSize[0], this.mOuterTextX[0], this.mOuterTextY[0]);
        if (this.mIs24HourMode) {
            calculatePositions(this.mPaint[0], (float) (this.mCircleRadius - this.mTextInset[2]), (float) this.mXCenter, (float) this.mYCenter, (float) this.mTextSize[2], this.mInnerTextX, this.mInnerTextY);
        }
    }

    private void calculatePositionsMinutes() {
        calculatePositions(this.mPaint[1], (float) (this.mCircleRadius - this.mTextInset[1]), (float) this.mXCenter, (float) this.mYCenter, (float) this.mTextSize[1], this.mOuterTextX[1], this.mOuterTextY[1]);
    }

    private static void calculatePositions(Paint paint, float radius, float xCenter, float yCenter, float textSize, float[] x, float[] y) {
        paint.setTextSize(textSize);
        yCenter -= (paint.descent() + paint.ascent()) / 2.0f;
        for (int i = 0; i < 12; i++) {
            x[i] = xCenter - (COS_30[i] * radius);
            y[i] = yCenter - (SIN_30[i] * radius);
        }
    }

    private void drawTextElements(Canvas canvas, float textSize, Typeface typeface, ColorStateList textColor, String[] texts, float[] textX, float[] textY, Paint paint, int alpha, boolean showActivated, int activatedDegrees, boolean activatedOnly) {
        paint.setTextSize(textSize);
        paint.setTypeface(typeface);
        float activatedIndex = ((float) activatedDegrees) / 30.0f;
        int activatedFloor = (int) activatedIndex;
        int activatedCeil = ((int) Math.ceil((double) activatedIndex)) % 12;
        int i = 0;
        while (i < 12) {
            boolean activated = activatedFloor == i || activatedCeil == i;
            if (!activatedOnly || activated) {
                int i2 = (showActivated && activated) ? 2 : 0;
                int color = textColor.getColorForState(SUtils.resolveStateSet(i2 | 1), 0);
                paint.setColor(color);
                paint.setAlpha(getMultipliedAlpha(color, alpha));
                canvas.drawText(texts[i], textX[i], textY[i], paint);
            }
            i++;
        }
    }

    private static ObjectAnimator getFadeOutAnimator(IntHolder target, int startAlpha, int endAlpha, InvalidateUpdateListener updateListener) {
        ObjectAnimator animator = ObjectAnimator.ofInt(target, "value", new int[]{startAlpha, endAlpha});
        animator.setDuration(500);
        animator.addUpdateListener(updateListener);
        return animator;
    }

    private static ObjectAnimator getFadeInAnimator(IntHolder target, int startAlpha, int endAlpha, InvalidateUpdateListener updateListener) {
        Keyframe kf0 = Keyframe.ofInt(0.0f, startAlpha);
        Keyframe kf1 = Keyframe.ofInt(0.2f, startAlpha);
        Keyframe kf2 = Keyframe.ofInt(FlexItem.FLEX_SHRINK_DEFAULT, endAlpha);
        PropertyValuesHolder fadeIn = PropertyValuesHolder.ofKeyframe("value", new Keyframe[]{kf0, kf1, kf2});
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(target, new PropertyValuesHolder[]{fadeIn});
        animator.setDuration(625);
        animator.addUpdateListener(updateListener);
        return animator;
    }

    private void startHoursToMinutesAnimation() {
        if (this.mHoursToMinutesAnims.size() == 0) {
            this.mHoursToMinutesAnims.add(getFadeOutAnimator(this.mAlpha[0], 255, 0, this.mInvalidateUpdateListener));
            this.mHoursToMinutesAnims.add(getFadeInAnimator(this.mAlpha[1], 0, 255, this.mInvalidateUpdateListener));
        }
        if (this.mTransition != null && this.mTransition.isRunning()) {
            this.mTransition.end();
        }
        this.mTransition = new AnimatorSet();
        this.mTransition.playTogether(this.mHoursToMinutesAnims);
        this.mTransition.start();
    }

    private void startMinutesToHoursAnimation() {
        if (this.mMinuteToHoursAnims.size() == 0) {
            this.mMinuteToHoursAnims.add(getFadeOutAnimator(this.mAlpha[1], 255, 0, this.mInvalidateUpdateListener));
            this.mMinuteToHoursAnims.add(getFadeInAnimator(this.mAlpha[0], 0, 255, this.mInvalidateUpdateListener));
        }
        if (this.mTransition != null && this.mTransition.isRunning()) {
            this.mTransition.end();
        }
        this.mTransition = new AnimatorSet();
        this.mTransition.playTogether(this.mMinuteToHoursAnims);
        this.mTransition.start();
    }

    private int getDegreesFromXY(float x, float y, boolean constrainOutside) {
        int innerBound;
        int outerBound;
        if (this.mIs24HourMode && this.mShowHours) {
            innerBound = this.mMinDistForInnerNumber;
            outerBound = this.mMaxDistForOuterNumber;
        } else {
            int center = this.mCircleRadius - this.mTextInset[this.mShowHours ? 0 : 1];
            innerBound = center - this.mSelectorRadius;
            outerBound = center + this.mSelectorRadius;
        }
        double dX = (double) (x - ((float) this.mXCenter));
        double dY = (double) (y - ((float) this.mYCenter));
        double distFromCenter = Math.sqrt((dX * dX) + (dY * dY));
        if (distFromCenter < ((double) innerBound) || (constrainOutside && distFromCenter > ((double) outerBound))) {
            return -1;
        }
        int degrees = (int) (Math.toDegrees(Math.atan2(dY, dX) + 1.5707963267948966d) + 0.5d);
        if (degrees < 0) {
            return degrees + 360;
        }
        return degrees;
    }

    private boolean getInnerCircleFromXY(float x, float y) {
        if (!this.mIs24HourMode || !this.mShowHours) {
            return false;
        }
        double dX = (double) (x - ((float) this.mXCenter));
        double dY = (double) (y - ((float) this.mYCenter));
        if (Math.sqrt((dX * dX) + (dY * dY)) <= ((double) this.mHalfwayDist)) {
            return true;
        }
        return false;
    }

    public boolean onTouchEvent(MotionEvent event) {
        if (this.mInputEnabled) {
            int action = event.getActionMasked();
            if (action == 2 || action == 1 || action == 0) {
                boolean forceSelection = false;
                boolean autoAdvance = false;
                if (action == 0) {
                    this.mChangedDuringTouch = false;
                } else if (action == 1) {
                    autoAdvance = true;
                    if (!this.mChangedDuringTouch) {
                        forceSelection = true;
                    }
                }
                this.mChangedDuringTouch |= handleTouchInput(event.getX(), event.getY(), forceSelection, autoAdvance);
            }
        }
        return true;
    }

    private boolean handleTouchInput(float x, float y, boolean forceSelection, boolean autoAdvance) {
        boolean isOnInnerCircle = getInnerCircleFromXY(x, y);
        int degrees = getDegreesFromXY(x, y, false);
        if (degrees == -1) {
            return false;
        }
        boolean valueChanged;
        int type;
        int newValue;
        int snapDegrees;
        if (this.mShowHours) {
            snapDegrees = snapOnly30s(degrees, 0) % 360;
            if (this.mIsOnInnerCircle == isOnInnerCircle && this.mSelectionDegrees[0] == snapDegrees) {
                valueChanged = false;
            } else {
                valueChanged = true;
            }
            this.mIsOnInnerCircle = isOnInnerCircle;
            this.mSelectionDegrees[0] = snapDegrees;
            type = 0;
            newValue = getCurrentHour();
        } else {
            snapDegrees = snapPrefer30s(degrees) % 360;
            if (this.mSelectionDegrees[1] != snapDegrees) {
                valueChanged = true;
            } else {
                valueChanged = false;
            }
            this.mSelectionDegrees[1] = snapDegrees;
            type = 1;
            newValue = getCurrentMinute();
        }
        if (!valueChanged && !forceSelection && !autoAdvance) {
            return false;
        }
        if (this.mListener != null) {
            this.mListener.onValueSelected(type, newValue, autoAdvance);
        }
        if (valueChanged || forceSelection) {
            SUtils.vibrateForTimePicker(this);
            invalidate();
        }
        return true;
    }

    public boolean dispatchHoverEvent(MotionEvent event) {
        return this.mTouchHelper.dispatchHoverEvent(event) || super.dispatchHoverEvent(event);
    }

    public void setInputEnabled(boolean inputEnabled) {
        this.mInputEnabled = inputEnabled;
        invalidate();
    }
}
