package com.facebook.react.modules.datepicker;

import android.app.DatePickerDialog.OnDateSetListener;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.widget.DatePicker;
import com.facebook.internal.NativeProtocol;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeMap;

class DatePickerDialogModule$DatePickerDialogListener implements OnDateSetListener, OnDismissListener {
    private final Promise mPromise;
    private boolean mPromiseResolved = false;
    final /* synthetic */ DatePickerDialogModule this$0;

    public DatePickerDialogModule$DatePickerDialogListener(DatePickerDialogModule datePickerDialogModule, Promise promise) {
        this.this$0 = datePickerDialogModule;
        this.mPromise = promise;
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        if (!this.mPromiseResolved && DatePickerDialogModule.access$000(this.this$0).hasActiveCatalystInstance()) {
            WritableMap result = new WritableNativeMap();
            result.putString(NativeProtocol.WEB_DIALOG_ACTION, "dateSetAction");
            result.putInt("year", year);
            result.putInt("month", month);
            result.putInt("day", day);
            this.mPromise.resolve(result);
            this.mPromiseResolved = true;
        }
    }

    public void onDismiss(DialogInterface dialog) {
        if (!this.mPromiseResolved && DatePickerDialogModule.access$100(this.this$0).hasActiveCatalystInstance()) {
            WritableMap result = new WritableNativeMap();
            result.putString(NativeProtocol.WEB_DIALOG_ACTION, "dismissedAction");
            this.mPromise.resolve(result);
            this.mPromiseResolved = true;
        }
    }
}
