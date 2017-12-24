package com.facebook.react.modules.timepicker;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.os.Bundle;
import android.text.format.DateFormat;
import java.util.Calendar;
import javax.annotation.Nullable;

public class TimePickerDialogFragment extends DialogFragment {
    @Nullable
    private OnDismissListener mOnDismissListener;
    @Nullable
    private OnTimeSetListener mOnTimeSetListener;

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return createDialog(getArguments(), getActivity(), this.mOnTimeSetListener);
    }

    static Dialog createDialog(Bundle args, Context activityContext, @Nullable OnTimeSetListener onTimeSetListener) {
        Calendar now = Calendar.getInstance();
        int hour = now.get(11);
        int minute = now.get(12);
        boolean is24hour = DateFormat.is24HourFormat(activityContext);
        if (args != null) {
            hour = args.getInt("hour", now.get(11));
            minute = args.getInt("minute", now.get(12));
            is24hour = args.getBoolean("is24Hour", DateFormat.is24HourFormat(activityContext));
        }
        return new DismissableTimePickerDialog(activityContext, onTimeSetListener, hour, minute, is24hour);
    }

    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        if (this.mOnDismissListener != null) {
            this.mOnDismissListener.onDismiss(dialog);
        }
    }

    public void setOnDismissListener(@Nullable OnDismissListener onDismissListener) {
        this.mOnDismissListener = onDismissListener;
    }

    public void setOnTimeSetListener(@Nullable OnTimeSetListener onTimeSetListener) {
        this.mOnTimeSetListener = onTimeSetListener;
    }
}
