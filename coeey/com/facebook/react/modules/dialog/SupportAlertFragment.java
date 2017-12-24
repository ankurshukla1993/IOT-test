package com.facebook.react.modules.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import com.facebook.react.modules.dialog.DialogModule.AlertFragmentListener;
import javax.annotation.Nullable;

public class SupportAlertFragment extends DialogFragment implements OnClickListener {
    @Nullable
    private final AlertFragmentListener mListener;

    public SupportAlertFragment() {
        this.mListener = null;
    }

    public SupportAlertFragment(@Nullable AlertFragmentListener listener, Bundle arguments) {
        this.mListener = listener;
        setArguments(arguments);
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return AlertFragment.createDialog(getActivity(), getArguments(), this);
    }

    public void onClick(DialogInterface dialog, int which) {
        if (this.mListener != null) {
            this.mListener.onClick(dialog, which);
        }
    }

    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        if (this.mListener != null) {
            this.mListener.onDismiss(dialog);
        }
    }
}
