package com.facebook.react.modules.datepicker;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Context;
import android.os.Build.VERSION;
import javax.annotation.Nullable;

public class DismissableDatePickerDialog extends DatePickerDialog {
    public DismissableDatePickerDialog(Context context, @Nullable OnDateSetListener callback, int year, int monthOfYear, int dayOfMonth) {
        super(context, callback, year, monthOfYear, dayOfMonth);
    }

    public DismissableDatePickerDialog(Context context, int theme, @Nullable OnDateSetListener callback, int year, int monthOfYear, int dayOfMonth) {
        super(context, theme, callback, year, monthOfYear, dayOfMonth);
    }

    protected void onStop() {
        if (VERSION.SDK_INT > 19) {
            super.onStop();
        }
    }
}
