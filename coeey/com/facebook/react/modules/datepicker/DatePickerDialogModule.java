package com.facebook.react.modules.datepicker;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.common.annotations.VisibleForTesting;
import com.facebook.react.module.annotations.ReactModule;
import javax.annotation.Nullable;

@ReactModule(name = "DatePickerAndroid")
public class DatePickerDialogModule extends ReactContextBaseJavaModule {
    static final String ACTION_DATE_SET = "dateSetAction";
    static final String ACTION_DISMISSED = "dismissedAction";
    static final String ARG_DATE = "date";
    static final String ARG_MAXDATE = "maxDate";
    static final String ARG_MINDATE = "minDate";
    static final String ARG_MODE = "mode";
    private static final String ERROR_NO_ACTIVITY = "E_NO_ACTIVITY";
    @VisibleForTesting
    public static final String FRAGMENT_TAG = "DatePickerAndroid";

    public DatePickerDialogModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    public String getName() {
        return FRAGMENT_TAG;
    }

    @ReactMethod
    public void open(@Nullable ReadableMap options, Promise promise) {
        Activity activity = getCurrentActivity();
        if (activity == null) {
            promise.reject(ERROR_NO_ACTIVITY, "Tried to open a DatePicker dialog while not attached to an Activity");
        } else if (activity instanceof FragmentActivity) {
            FragmentManager fragmentManager = ((FragmentActivity) activity).getSupportFragmentManager();
            DialogFragment oldFragment = (DialogFragment) fragmentManager.findFragmentByTag(FRAGMENT_TAG);
            if (oldFragment != null) {
                oldFragment.dismiss();
            }
            SupportDatePickerDialogFragment fragment = new SupportDatePickerDialogFragment();
            if (options != null) {
                fragment.setArguments(createFragmentArguments(options));
            }
            listener = new DatePickerDialogListener(this, promise);
            fragment.setOnDismissListener(listener);
            fragment.setOnDateSetListener(listener);
            fragment.show(fragmentManager, FRAGMENT_TAG);
        } else {
            android.app.FragmentManager fragmentManager2 = activity.getFragmentManager();
            android.app.DialogFragment oldFragment2 = (android.app.DialogFragment) fragmentManager2.findFragmentByTag(FRAGMENT_TAG);
            if (oldFragment2 != null) {
                oldFragment2.dismiss();
            }
            DatePickerDialogFragment fragment2 = new DatePickerDialogFragment();
            if (options != null) {
                fragment2.setArguments(createFragmentArguments(options));
            }
            listener = new DatePickerDialogListener(this, promise);
            fragment2.setOnDismissListener(listener);
            fragment2.setOnDateSetListener(listener);
            fragment2.show(fragmentManager2, FRAGMENT_TAG);
        }
    }

    private Bundle createFragmentArguments(ReadableMap options) {
        Bundle args = new Bundle();
        if (options.hasKey("date") && !options.isNull("date")) {
            args.putLong("date", (long) options.getDouble("date"));
        }
        if (options.hasKey(ARG_MINDATE) && !options.isNull(ARG_MINDATE)) {
            args.putLong(ARG_MINDATE, (long) options.getDouble(ARG_MINDATE));
        }
        if (options.hasKey(ARG_MAXDATE) && !options.isNull(ARG_MAXDATE)) {
            args.putLong(ARG_MAXDATE, (long) options.getDouble(ARG_MAXDATE));
        }
        if (options.hasKey(ARG_MODE) && !options.isNull(ARG_MODE)) {
            args.putString(ARG_MODE, options.getString(ARG_MODE));
        }
        return args;
    }
}
