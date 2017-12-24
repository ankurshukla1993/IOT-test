package com.github.mikephil.charting.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.graphics.PointF;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.support.v4.widget.AutoScrollHelper;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import com.facebook.appevents.AppEventsConstants;
import com.github.mikephil.charting.components.YAxis.AxisDependency;
import io.fabric.sdk.android.services.common.AbstractSpiCall;
import java.util.List;

public abstract class Utils {
    private static final int[] POW_10 = new int[]{1, 10, 100, 1000, AbstractSpiCall.DEFAULT_TIMEOUT, 100000, 1000000, 10000000, 100000000, 1000000000};
    private static int mMaximumFlingVelocity = 8000;
    private static DisplayMetrics mMetrics;
    private static int mMinimumFlingVelocity = 50;

    public static void init(Context context) {
        if (context == null) {
            mMinimumFlingVelocity = ViewConfiguration.getMinimumFlingVelocity();
            mMaximumFlingVelocity = ViewConfiguration.getMaximumFlingVelocity();
            Log.e("MPChartLib-Utils", "Utils.init(...) PROVIDED CONTEXT OBJECT IS NULL");
            return;
        }
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        mMinimumFlingVelocity = viewConfiguration.getScaledMinimumFlingVelocity();
        mMaximumFlingVelocity = viewConfiguration.getScaledMaximumFlingVelocity();
        mMetrics = context.getResources().getDisplayMetrics();
    }

    @Deprecated
    public static void init(Resources res) {
        mMetrics = res.getDisplayMetrics();
        mMinimumFlingVelocity = ViewConfiguration.getMinimumFlingVelocity();
        mMaximumFlingVelocity = ViewConfiguration.getMaximumFlingVelocity();
    }

    public static float convertDpToPixel(float dp) {
        if (mMetrics != null) {
            return dp * (((float) mMetrics.densityDpi) / 160.0f);
        }
        Log.e("MPChartLib-Utils", "Utils NOT INITIALIZED. You need to call Utils.init(...) at least once before calling Utils.convertDpToPixel(...). Otherwise conversion does not take place.");
        return dp;
    }

    public static float convertPixelsToDp(float px) {
        if (mMetrics != null) {
            return px / (((float) mMetrics.densityDpi) / 160.0f);
        }
        Log.e("MPChartLib-Utils", "Utils NOT INITIALIZED. You need to call Utils.init(...) at least once before calling Utils.convertPixelsToDp(...). Otherwise conversion does not take place.");
        return px;
    }

    public static int calcTextWidth(Paint paint, String demoText) {
        return (int) paint.measureText(demoText);
    }

    public static int calcTextHeight(Paint paint, String demoText) {
        Rect r = new Rect();
        paint.getTextBounds(demoText, 0, demoText.length(), r);
        return r.height();
    }

    public static float getLineHeight(Paint paint) {
        FontMetrics metrics = paint.getFontMetrics();
        return metrics.descent - metrics.ascent;
    }

    public static float getLineSpacing(Paint paint) {
        FontMetrics metrics = paint.getFontMetrics();
        return (metrics.ascent - metrics.top) + metrics.bottom;
    }

    public static FSize calcTextSize(Paint paint, String demoText) {
        Rect r = new Rect();
        paint.getTextBounds(demoText, 0, demoText.length(), r);
        return new FSize((float) r.width(), (float) r.height());
    }

    public static String formatNumber(float number, int digitCount, boolean separateThousands) {
        return formatNumber(number, digitCount, separateThousands, '.');
    }

    public static String formatNumber(float number, int digitCount, boolean separateThousands, char separateChar) {
        char[] out = new char[35];
        boolean neg = false;
        if (number == 0.0f) {
            return AppEventsConstants.EVENT_PARAM_VALUE_NO;
        }
        boolean zero = false;
        if (number < FlexItem.FLEX_SHRINK_DEFAULT && number > FlexItem.FLEX_BASIS_PERCENT_DEFAULT) {
            zero = true;
        }
        if (number < 0.0f) {
            neg = true;
            number = -number;
        }
        if (digitCount > POW_10.length) {
            digitCount = POW_10.length - 1;
        }
        long lval = (long) Math.round(number * ((float) POW_10[digitCount]));
        int charCount = 0;
        boolean decimalPointAdded = false;
        int ind = out.length - 1;
        while (true) {
            if (lval == 0 && charCount >= digitCount + 1) {
                break;
            }
            lval /= 10;
            int ind2 = ind - 1;
            out[ind] = (char) (((int) (lval % 10)) + 48);
            charCount++;
            if (charCount == digitCount) {
                ind = ind2 - 1;
                out[ind2] = ',';
                charCount++;
                decimalPointAdded = true;
                ind2 = ind;
            } else if (separateThousands && lval != 0 && charCount > digitCount) {
                if (decimalPointAdded) {
                    if ((charCount - digitCount) % 4 == 0) {
                        ind = ind2 - 1;
                        out[ind2] = separateChar;
                        charCount++;
                        ind2 = ind;
                    }
                } else if ((charCount - digitCount) % 4 == 3) {
                    ind = ind2 - 1;
                    out[ind2] = separateChar;
                    charCount++;
                    ind2 = ind;
                }
            }
            ind = ind2;
        }
        if (zero) {
            ind2 = ind - 1;
            out[ind] = '0';
            charCount++;
            ind = ind2;
        }
        if (neg) {
            ind2 = ind - 1;
            out[ind] = '-';
            charCount++;
        }
        int start = out.length - charCount;
        return String.valueOf(out, start, out.length - start);
    }

    public static float roundToNextSignificant(double number) {
        double d;
        if (number < 0.0d) {
            d = -number;
        } else {
            d = number;
        }
        float magnitude = (float) Math.pow(10.0d, (double) (1 - ((int) ((float) Math.ceil((double) ((float) Math.log10(d)))))));
        return ((float) Math.round(((double) magnitude) * number)) / magnitude;
    }

    public static int getDecimals(float number) {
        return ((int) Math.ceil(-Math.log10((double) roundToNextSignificant((double) number)))) + 2;
    }

    public static int[] convertIntegers(List<Integer> integers) {
        int[] ret = new int[integers.size()];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = ((Integer) integers.get(i)).intValue();
        }
        return ret;
    }

    public static String[] convertStrings(List<String> strings) {
        String[] ret = new String[strings.size()];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = (String) strings.get(i);
        }
        return ret;
    }

    public static double nextUp(double d) {
        if (d == Double.POSITIVE_INFINITY) {
            return d;
        }
        d += 0.0d;
        return Double.longBitsToDouble((d >= 0.0d ? 1 : -1) + Double.doubleToRawLongBits(d));
    }

    public static int getClosestDataSetIndex(List<SelectionDetail> valsAtIndex, float val, AxisDependency axis) {
        int index = -2147483647;
        float distance = AutoScrollHelper.NO_MAX;
        for (int i = 0; i < valsAtIndex.size(); i++) {
            SelectionDetail sel = (SelectionDetail) valsAtIndex.get(i);
            if (axis == null || sel.dataSet.getAxisDependency() == axis) {
                float cdistance = Math.abs(sel.val - val);
                if (cdistance < distance) {
                    index = ((SelectionDetail) valsAtIndex.get(i)).dataSetIndex;
                    distance = cdistance;
                }
            }
        }
        return index;
    }

    public static float getMinimumDistance(List<SelectionDetail> valsAtIndex, float val, AxisDependency axis) {
        float distance = AutoScrollHelper.NO_MAX;
        for (int i = 0; i < valsAtIndex.size(); i++) {
            SelectionDetail sel = (SelectionDetail) valsAtIndex.get(i);
            if (sel.dataSet.getAxisDependency() == axis) {
                float cdistance = Math.abs(sel.val - val);
                if (cdistance < distance) {
                    distance = cdistance;
                }
            }
        }
        return distance;
    }

    public static PointF getPosition(PointF center, float dist, float angle) {
        return new PointF((float) (((double) center.x) + (((double) dist) * Math.cos(Math.toRadians((double) angle)))), (float) (((double) center.y) + (((double) dist) * Math.sin(Math.toRadians((double) angle)))));
    }

    public static void velocityTrackerPointerUpCleanUpIfNecessary(MotionEvent ev, VelocityTracker tracker) {
        tracker.computeCurrentVelocity(1000, (float) mMaximumFlingVelocity);
        int upIndex = ev.getActionIndex();
        int id1 = ev.getPointerId(upIndex);
        float x1 = tracker.getXVelocity(id1);
        float y1 = tracker.getYVelocity(id1);
        int count = ev.getPointerCount();
        for (int i = 0; i < count; i++) {
            if (i != upIndex) {
                int id2 = ev.getPointerId(i);
                if ((x1 * tracker.getXVelocity(id2)) + (y1 * tracker.getYVelocity(id2)) < 0.0f) {
                    tracker.clear();
                    return;
                }
            }
        }
    }

    @SuppressLint({"NewApi"})
    public static void postInvalidateOnAnimation(View view) {
        if (VERSION.SDK_INT >= 16) {
            view.postInvalidateOnAnimation();
        } else {
            view.postInvalidateDelayed(10);
        }
    }

    public static int getMinimumFlingVelocity() {
        return mMinimumFlingVelocity;
    }

    public static int getMaximumFlingVelocity() {
        return mMaximumFlingVelocity;
    }

    public static float getNormalizedAngle(float angle) {
        while (angle < 0.0f) {
            angle += 360.0f;
        }
        return angle % 360.0f;
    }
}
