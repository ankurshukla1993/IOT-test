package com.appeaser.sublimepickerlibrary.utilities;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.StateListDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.ImageView;
import com.appeaser.sublimepickerlibrary.C0563R;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Locale;

public class SUtils {
    private static final int CHANGE_YEAR = 1582;
    public static int COLOR_ACCENT = 0;
    public static int COLOR_BACKGROUND = 0;
    public static int COLOR_BUTTON_NORMAL = 0;
    public static int COLOR_CONTROL_ACTIVATED = 0;
    public static int COLOR_CONTROL_HIGHLIGHT = 0;
    public static int COLOR_PRIMARY = 0;
    public static int COLOR_PRIMARY_DARK = 0;
    public static int COLOR_TEXT_PRIMARY = 0;
    public static int COLOR_TEXT_PRIMARY_INVERSE = 0;
    public static int COLOR_TEXT_SECONDARY = 0;
    public static int COLOR_TEXT_SECONDARY_INVERSE = 0;
    public static final int CORNERS_ALL = 15;
    public static final int CORNER_BOTTOM_LEFT = 8;
    public static final int CORNER_BOTTOM_RIGHT = 4;
    public static int CORNER_RADIUS = 0;
    public static final int CORNER_TOP_LEFT = 1;
    public static final int CORNER_TOP_RIGHT = 2;
    private static final String DATE_FORMAT = "MM/dd/yyyy";
    private static final DateFormat DATE_FORMATTER = new SimpleDateFormat(DATE_FORMAT);
    public static final int STATE_ACTIVATED = 2;
    public static final int STATE_ENABLED = 1;
    public static final int STATE_PRESSED = 4;
    private static final int[][] STATE_SETS = new int[8][];
    private static final String TAG = SUtils.class.getSimpleName();

    static {
        STATE_SETS[0] = new int[]{0};
        STATE_SETS[1] = new int[]{16842910};
        STATE_SETS[2] = new int[]{16843518};
        STATE_SETS[3] = new int[]{16842910, 16843518};
        STATE_SETS[4] = new int[]{16842919};
        STATE_SETS[5] = new int[]{16842910, 16842919};
        STATE_SETS[6] = new int[]{16843518, 16842919};
        STATE_SETS[7] = new int[]{16842910, 16843518, 16842919};
    }

    public static void initializeResources(Context context) {
        TypedArray a = context.obtainStyledAttributes(new int[]{C0563R.attr.colorAccent, C0563R.attr.colorControlHighlight, C0563R.attr.colorControlActivated, C0563R.attr.colorButtonNormal, 16842806, 16842809, C0563R.attr.colorPrimary, C0563R.attr.colorPrimaryDark, 16842808, 16842801, 16842810});
        if (a.hasValue(0)) {
            COLOR_ACCENT = a.getColor(0, 0);
        }
        if (a.hasValue(1)) {
            COLOR_CONTROL_HIGHLIGHT = a.getColor(1, 0);
        }
        if (a.hasValue(2)) {
            COLOR_CONTROL_ACTIVATED = a.getColor(2, 0);
        }
        if (a.hasValue(3)) {
            COLOR_BUTTON_NORMAL = a.getColor(3, 0);
        }
        if (a.hasValue(4)) {
            COLOR_TEXT_PRIMARY = a.getColor(4, 0);
        }
        if (a.hasValue(5)) {
            COLOR_TEXT_PRIMARY_INVERSE = a.getColor(5, 0);
        }
        if (a.hasValue(6)) {
            COLOR_PRIMARY = a.getColor(6, 0);
        }
        if (a.hasValue(7)) {
            COLOR_PRIMARY_DARK = a.getColor(7, 0);
        }
        if (a.hasValue(8)) {
            COLOR_TEXT_SECONDARY = a.getColor(8, 0);
        }
        if (a.hasValue(9)) {
            COLOR_BACKGROUND = a.getColor(9, 0);
        }
        if (a.hasValue(10)) {
            COLOR_TEXT_SECONDARY_INVERSE = a.getColor(10, 0);
        }
        a.recycle();
        CORNER_RADIUS = context.getResources().getDimensionPixelSize(C0563R.dimen.control_corner_material);
    }

    public static boolean isApi_16_OrHigher() {
        return VERSION.SDK_INT >= 16;
    }

    public static boolean isApi_17_OrHigher() {
        return VERSION.SDK_INT >= 17;
    }

    public static boolean isApi_18_OrHigher() {
        return VERSION.SDK_INT >= 18;
    }

    public static boolean isApi_21_OrHigher() {
        return VERSION.SDK_INT >= 21;
    }

    public static boolean isApi_22_OrHigher() {
        return VERSION.SDK_INT >= 22;
    }

    public static boolean isApi_23_OrHigher() {
        return VERSION.SDK_INT >= 23;
    }

    public static void setViewBackground(View view, Drawable bg) {
        int paddingL = view.getPaddingLeft();
        int paddingT = view.getPaddingTop();
        int paddingR = view.getPaddingRight();
        int paddingB = view.getPaddingBottom();
        if (isApi_16_OrHigher()) {
            view.setBackground(bg);
        } else {
            view.setBackgroundDrawable(bg);
        }
        view.setPadding(paddingL, paddingT, paddingR, paddingB);
    }

    public static Drawable createButtonBg(Context context, int colorButtonNormal, int colorControlHighlight) {
        if (isApi_21_OrHigher()) {
            return createButtonRippleBg(context, colorButtonNormal, colorControlHighlight);
        }
        return createButtonNormalBg(context, colorControlHighlight);
    }

    @TargetApi(21)
    private static Drawable createButtonRippleBg(Context context, int colorButtonNormal, int colorControlHighlight) {
        return new RippleDrawable(ColorStateList.valueOf(colorControlHighlight), null, createButtonShape(context, colorButtonNormal));
    }

    private static Drawable createButtonNormalBg(Context context, int colorControlHighlight) {
        StateListDrawable sld = new StateListDrawable();
        sld.addState(new int[]{16842919}, createButtonShape(context, colorControlHighlight));
        sld.addState(new int[0], new ColorDrawable(0));
        return sld;
    }

    private static Drawable createButtonShape(Context context, int color) {
        int paddingH = context.getResources().getDimensionPixelSize(C0563R.dimen.button_padding_horizontal_material);
        int paddingV = context.getResources().getDimensionPixelSize(C0563R.dimen.button_padding_vertical_material);
        int insetH = context.getResources().getDimensionPixelSize(C0563R.dimen.button_inset_horizontal_material);
        int insetV = context.getResources().getDimensionPixelSize(C0563R.dimen.button_inset_vertical_material);
        float[] outerRadii = new float[8];
        Arrays.fill(outerRadii, (float) CORNER_RADIUS);
        ShapeDrawable shapeDrawable = new ShapeDrawable(new RoundRectShape(outerRadii, null, null));
        shapeDrawable.getPaint().setColor(color);
        shapeDrawable.setPadding(paddingH, paddingV, paddingH, paddingV);
        return new InsetDrawable(shapeDrawable, insetH, insetV, insetH, insetV);
    }

    public static Drawable createImageViewBg(int colorButtonNormal, int colorControlHighlight) {
        if (isApi_21_OrHigher()) {
            return createImageViewRippleBg(colorButtonNormal, colorControlHighlight);
        }
        return createImageViewNormalBg(colorControlHighlight);
    }

    @TargetApi(21)
    private static Drawable createImageViewRippleBg(int colorButtonNormal, int colorControlHighlight) {
        return new RippleDrawable(ColorStateList.valueOf(colorControlHighlight), null, createImageViewShape(colorButtonNormal));
    }

    private static Drawable createImageViewNormalBg(int colorControlHighlight) {
        StateListDrawable sld = new StateListDrawable();
        sld.addState(new int[]{16842919}, createImageViewShape(colorControlHighlight));
        sld.addState(new int[0], new ColorDrawable(0));
        return sld;
    }

    private static Drawable createImageViewShape(int color) {
        ShapeDrawable shapeDrawable = new ShapeDrawable(new OvalShape());
        shapeDrawable.getPaint().setColor(color);
        return shapeDrawable;
    }

    public static int constrain(int amount, int low, int high) {
        if (amount < low) {
            return low;
        }
        return amount > high ? high : amount;
    }

    public static long constrain(long amount, long low, long high) {
        if (amount < low) {
            return low;
        }
        return amount > high ? high : amount;
    }

    public static boolean isLayoutRtlCompat(@NonNull View view) {
        return ViewCompat.getLayoutDirection(view) == 1;
    }

    public static Drawable createBgDrawable(int color, int rTopLeft, int rTopRight, int rBottomRight, int rBottomLeft) {
        ShapeDrawable shapeDrawable = new ShapeDrawable(new RoundRectShape(new float[]{(float) rTopLeft, (float) rTopLeft, (float) rTopRight, (float) rTopRight, (float) rBottomRight, (float) rBottomRight, (float) rBottomLeft, (float) rBottomLeft}, null, null));
        shapeDrawable.getPaint().setColor(color);
        return shapeDrawable;
    }

    public static Drawable createOverflowButtonBg(int pressedStateColor) {
        if (isApi_21_OrHigher()) {
            return createOverflowButtonBgLollipop(pressedStateColor);
        }
        return createOverflowButtonBgBC(pressedStateColor);
    }

    @TargetApi(21)
    private static Drawable createOverflowButtonBgLollipop(int pressedStateColor) {
        return new RippleDrawable(ColorStateList.valueOf(pressedStateColor), null, null);
    }

    private static Drawable createOverflowButtonBgBC(int pressedStateColor) {
        StateListDrawable sld = new StateListDrawable();
        sld.addState(new int[]{16842919}, createBgDrawable(pressedStateColor, 0, CORNER_RADIUS, 0, 0));
        sld.addState(new int[0], new ColorDrawable(0));
        return sld;
    }

    public static Calendar getCalendarForLocale(Calendar oldCalendar, Locale locale) {
        if (oldCalendar == null) {
            return Calendar.getInstance(locale);
        }
        long currentTimeMillis = oldCalendar.getTimeInMillis();
        Calendar newCalendar = Calendar.getInstance(locale);
        newCalendar.setTimeInMillis(currentTimeMillis);
        return newCalendar;
    }

    public static ContextThemeWrapper createThemeWrapper(Context context, int parentStyleAttr, int parentDefaultStyle, int childStyleAttr, int childDefaultStyle) {
        TypedArray forParent = context.obtainStyledAttributes(new int[]{parentStyleAttr});
        int parentStyle = forParent.getResourceId(0, parentDefaultStyle);
        forParent.recycle();
        TypedArray forChild = context.obtainStyledAttributes(parentStyle, new int[]{childStyleAttr});
        int childStyleId = forChild.getResourceId(0, childDefaultStyle);
        forChild.recycle();
        return new ContextThemeWrapper(context, childStyleId);
    }

    public static void setViewBackground(View view, int bgColor, int corners) {
        int i = 0;
        if (isApi_21_OrHigher()) {
            view.setBackgroundColor(bgColor);
            return;
        }
        int i2 = (corners & 1) != 0 ? CORNER_RADIUS : 0;
        int i3 = (corners & 2) != 0 ? CORNER_RADIUS : 0;
        int i4 = (corners & 4) != 0 ? CORNER_RADIUS : 0;
        if ((corners & 8) != 0) {
            i = CORNER_RADIUS;
        }
        setViewBackground(view, createBgDrawable(bgColor, i2, i3, i4, i));
    }

    public static void setImageTintList(ImageView imageView, ColorStateList colorStateList) {
        if (isApi_21_OrHigher()) {
            imageView.setImageTintList(colorStateList);
            return;
        }
        Drawable drawable = imageView.getDrawable();
        if (drawable != null) {
            Drawable wrapped = DrawableCompat.wrap(drawable);
            DrawableCompat.setTintList(wrapped, colorStateList);
            imageView.setImageDrawable(wrapped);
        }
    }

    public static int[] resolveStateSet(int mask) {
        return STATE_SETS[mask];
    }

    public static boolean parseDate(String date, Calendar outDate) {
        if (date == null || date.isEmpty()) {
            return false;
        }
        try {
            outDate.setTime(DATE_FORMATTER.parse(date));
            return true;
        } catch (ParseException e) {
            Log.w(TAG, "Date: " + date + " not in format: " + DATE_FORMAT);
            return false;
        }
    }

    private static boolean isLeapYear(int year) {
        if (year > CHANGE_YEAR) {
            if (year % 4 != 0 || (year % 100 == 0 && year % 400 != 0)) {
                return false;
            }
            return true;
        } else if (year % 4 != 0) {
            return false;
        } else {
            return true;
        }
    }

    public static int getDaysInMonth(int month, int year) {
        switch (month) {
            case 0:
            case 2:
            case 4:
            case 6:
            case 7:
            case 9:
            case 11:
                return 31;
            case 1:
                return isLeapYear(year) ? 29 : 28;
            case 3:
            case 5:
            case 8:
            case 10:
                return 30;
            default:
                throw new IllegalArgumentException("Invalid Month");
        }
    }

    @TargetApi(21)
    public static void vibrateForDatePicker(View view) {
        view.performHapticFeedback(1);
    }

    @TargetApi(21)
    public static void vibrateForTimePicker(View view) {
        view.performHapticFeedback(isApi_21_OrHigher() ? 4 : 1);
    }
}
