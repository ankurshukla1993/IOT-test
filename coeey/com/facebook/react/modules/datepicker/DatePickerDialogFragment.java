package com.facebook.react.modules.datepicker;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.widget.DatePicker;
import com.facebook.internal.AnalyticsEvents;
import java.util.Calendar;
import java.util.Locale;
import javax.annotation.Nullable;

@SuppressLint({"ValidFragment"})
public class DatePickerDialogFragment extends DialogFragment {
    private static final long DEFAULT_MIN_DATE = -2208988800001L;
    @Nullable
    private OnDateSetListener mOnDateSetListener;
    @Nullable
    private OnDismissListener mOnDismissListener;

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return createDialog(getArguments(), getActivity(), this.mOnDateSetListener);
    }

    static Dialog createDialog(Bundle args, Context activityContext, @Nullable OnDateSetListener onDateSetListener) {
        Calendar c = Calendar.getInstance();
        if (args != null && args.containsKey("date")) {
            c.setTimeInMillis(args.getLong("date"));
        }
        int year = c.get(1);
        int month = c.get(2);
        int day = c.get(5);
        DatePickerMode mode = DatePickerMode.DEFAULT;
        if (!(args == null || args.getString("mode", null) == null)) {
            mode = DatePickerMode.valueOf(args.getString("mode").toUpperCase(Locale.US));
        }
        DatePickerDialog dialog = null;
        if (VERSION.SDK_INT < 21) {
            DatePickerDialog dismissableDatePickerDialog = new DismissableDatePickerDialog(activityContext, onDateSetListener, year, month, day);
            switch (1.$SwitchMap$com$facebook$react$modules$datepicker$DatePickerMode[mode.ordinal()]) {
                case 1:
                    dismissableDatePickerDialog.getDatePicker().setCalendarViewShown(true);
                    dismissableDatePickerDialog.getDatePicker().setSpinnersShown(false);
                    break;
                case 2:
                    dismissableDatePickerDialog.getDatePicker().setCalendarViewShown(false);
                    break;
                default:
                    break;
            }
        }
        switch (1.$SwitchMap$com$facebook$react$modules$datepicker$DatePickerMode[mode.ordinal()]) {
            case 1:
                dialog = new DismissableDatePickerDialog(activityContext, activityContext.getResources().getIdentifier("CalendarDatePickerDialog", AnalyticsEvents.PARAMETER_LIKE_VIEW_STYLE, activityContext.getPackageName()), onDateSetListener, year, month, day);
                break;
            case 2:
                dialog = new DismissableDatePickerDialog(activityContext, activityContext.getResources().getIdentifier("SpinnerDatePickerDialog", AnalyticsEvents.PARAMETER_LIKE_VIEW_STYLE, activityContext.getPackageName()), onDateSetListener, year, month, day);
                break;
            case 3:
                dismissableDatePickerDialog = new DismissableDatePickerDialog(activityContext, onDateSetListener, year, month, day);
                break;
        }
        DatePicker datePicker = dialog.getDatePicker();
        if (args == null || !args.containsKey("minDate")) {
            datePicker.setMinDate(DEFAULT_MIN_DATE);
        } else {
            c.setTimeInMillis(args.getLong("minDate"));
            c.set(11, 0);
            c.set(12, 0);
            c.set(13, 0);
            c.set(14, 0);
            datePicker.setMinDate(c.getTimeInMillis());
        }
        if (args != null && args.containsKey("maxDate")) {
            c.setTimeInMillis(args.getLong("maxDate"));
            c.set(11, 23);
            c.set(12, 59);
            c.set(13, 59);
            c.set(14, 999);
            datePicker.setMaxDate(c.getTimeInMillis());
        }
        return dialog;
    }

    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        if (this.mOnDismissListener != null) {
            this.mOnDismissListener.onDismiss(dialog);
        }
    }

    void setOnDateSetListener(@Nullable OnDateSetListener onDateSetListener) {
        this.mOnDateSetListener = onDateSetListener;
    }

    void setOnDismissListener(@Nullable OnDismissListener onDismissListener) {
        this.mOnDismissListener = onDismissListener;
    }
}
