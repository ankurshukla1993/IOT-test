package com.facebook.react.modules.timepicker;

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

@ReactModule(name = "TimePickerAndroid")
public class TimePickerDialogModule extends ReactContextBaseJavaModule {
    static final String ACTION_DISMISSED = "dismissedAction";
    static final String ACTION_TIME_SET = "timeSetAction";
    static final String ARG_HOUR = "hour";
    static final String ARG_IS24HOUR = "is24Hour";
    static final String ARG_MINUTE = "minute";
    private static final String ERROR_NO_ACTIVITY = "E_NO_ACTIVITY";
    @VisibleForTesting
    public static final String FRAGMENT_TAG = "TimePickerAndroid";

    public TimePickerDialogModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    public String getName() {
        return FRAGMENT_TAG;
    }

    @ReactMethod
    public void open(@Nullable ReadableMap options, Promise promise) {
        Activity activity = getCurrentActivity();
        if (activity == null) {
            promise.reject(ERROR_NO_ACTIVITY, "Tried to open a TimePicker dialog while not attached to an Activity");
        } else if (activity instanceof FragmentActivity) {
            FragmentManager fragmentManager = ((FragmentActivity) activity).getSupportFragmentManager();
            DialogFragment oldFragment = (DialogFragment) fragmentManager.findFragmentByTag(FRAGMENT_TAG);
            if (oldFragment != null) {
                oldFragment.dismiss();
            }
            SupportTimePickerDialogFragment fragment = new SupportTimePickerDialogFragment();
            if (options != null) {
                fragment.setArguments(createFragmentArguments(options));
            }
            listener = new TimePickerDialogListener(this, promise);
            fragment.setOnDismissListener(listener);
            fragment.setOnTimeSetListener(listener);
            fragment.show(fragmentManager, FRAGMENT_TAG);
        } else {
            android.app.FragmentManager fragmentManager2 = activity.getFragmentManager();
            android.app.DialogFragment oldFragment2 = (android.app.DialogFragment) fragmentManager2.findFragmentByTag(FRAGMENT_TAG);
            if (oldFragment2 != null) {
                oldFragment2.dismiss();
            }
            TimePickerDialogFragment fragment2 = new TimePickerDialogFragment();
            if (options != null) {
                fragment2.setArguments(createFragmentArguments(options));
            }
            listener = new TimePickerDialogListener(this, promise);
            fragment2.setOnDismissListener(listener);
            fragment2.setOnTimeSetListener(listener);
            fragment2.show(fragmentManager2, FRAGMENT_TAG);
        }
    }

    private Bundle createFragmentArguments(ReadableMap options) {
        Bundle args = new Bundle();
        if (options.hasKey(ARG_HOUR) && !options.isNull(ARG_HOUR)) {
            args.putInt(ARG_HOUR, options.getInt(ARG_HOUR));
        }
        if (options.hasKey(ARG_MINUTE) && !options.isNull(ARG_MINUTE)) {
            args.putInt(ARG_MINUTE, options.getInt(ARG_MINUTE));
        }
        if (options.hasKey(ARG_IS24HOUR) && !options.isNull(ARG_IS24HOUR)) {
            args.putBoolean(ARG_IS24HOUR, options.getBoolean(ARG_IS24HOUR));
        }
        return args;
    }
}
