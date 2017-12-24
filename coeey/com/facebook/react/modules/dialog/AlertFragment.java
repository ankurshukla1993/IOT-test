package com.facebook.react.modules.dialog;

import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import com.facebook.react.modules.dialog.DialogModule.AlertFragmentListener;
import javax.annotation.Nullable;

public class AlertFragment extends DialogFragment implements OnClickListener {
    static final String ARG_BUTTON_NEGATIVE = "button_negative";
    static final String ARG_BUTTON_NEUTRAL = "button_neutral";
    static final String ARG_BUTTON_POSITIVE = "button_positive";
    static final String ARG_ITEMS = "items";
    static final String ARG_MESSAGE = "message";
    static final String ARG_TITLE = "title";
    @Nullable
    private final AlertFragmentListener mListener;

    public AlertFragment() {
        this.mListener = null;
    }

    public AlertFragment(@Nullable AlertFragmentListener listener, Bundle arguments) {
        this.mListener = listener;
        setArguments(arguments);
    }

    public static Dialog createDialog(Context activityContext, Bundle arguments, OnClickListener fragment) {
        Builder builder = new Builder(activityContext).setTitle(arguments.getString("title"));
        if (arguments.containsKey(ARG_BUTTON_POSITIVE)) {
            builder.setPositiveButton(arguments.getString(ARG_BUTTON_POSITIVE), fragment);
        }
        if (arguments.containsKey(ARG_BUTTON_NEGATIVE)) {
            builder.setNegativeButton(arguments.getString(ARG_BUTTON_NEGATIVE), fragment);
        }
        if (arguments.containsKey(ARG_BUTTON_NEUTRAL)) {
            builder.setNeutralButton(arguments.getString(ARG_BUTTON_NEUTRAL), fragment);
        }
        if (arguments.containsKey("message")) {
            builder.setMessage(arguments.getString("message"));
        }
        if (arguments.containsKey(ARG_ITEMS)) {
            builder.setItems(arguments.getCharSequenceArray(ARG_ITEMS), fragment);
        }
        return builder.create();
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return createDialog(getActivity(), getArguments(), this);
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
