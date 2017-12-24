package com.facebook.react.modules.timepicker;

import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.widget.TimePicker;
import com.facebook.internal.NativeProtocol;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeMap;

class TimePickerDialogModule$TimePickerDialogListener implements OnTimeSetListener, OnDismissListener {
    private final Promise mPromise;
    private boolean mPromiseResolved = false;
    final /* synthetic */ TimePickerDialogModule this$0;

    public TimePickerDialogModule$TimePickerDialogListener(TimePickerDialogModule timePickerDialogModule, Promise promise) {
        this.this$0 = timePickerDialogModule;
        this.mPromise = promise;
    }

    public void onTimeSet(TimePicker view, int hour, int minute) {
        if (!this.mPromiseResolved && TimePickerDialogModule.access$000(this.this$0).hasActiveCatalystInstance()) {
            WritableMap result = new WritableNativeMap();
            result.putString(NativeProtocol.WEB_DIALOG_ACTION, "timeSetAction");
            result.putInt("hour", hour);
            result.putInt("minute", minute);
            this.mPromise.resolve(result);
            this.mPromiseResolved = true;
        }
    }

    public void onDismiss(DialogInterface dialog) {
        if (!this.mPromiseResolved && TimePickerDialogModule.access$100(this.this$0).hasActiveCatalystInstance()) {
            WritableMap result = new WritableNativeMap();
            result.putString(NativeProtocol.WEB_DIALOG_ACTION, "dismissedAction");
            this.mPromise.resolve(result);
            this.mPromiseResolved = true;
        }
    }
}
