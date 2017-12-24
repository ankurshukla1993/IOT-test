package com.facebook.react.modules.datepicker;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import javax.annotation.Nullable;

@SuppressLint({"ValidFragment"})
public class SupportDatePickerDialogFragment extends DialogFragment {
    @Nullable
    private OnDateSetListener mOnDateSetListener;
    @Nullable
    private OnDismissListener mOnDismissListener;

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return DatePickerDialogFragment.createDialog(getArguments(), getActivity(), this.mOnDateSetListener);
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
