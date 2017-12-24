package com.cooey.android.users.old.sublimepicker;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.appeaser.sublimepickerlibrary.SublimePicker;
import com.appeaser.sublimepickerlibrary.datepicker.SelectedDate;
import com.appeaser.sublimepickerlibrary.helpers.SublimeListenerAdapter;
import com.appeaser.sublimepickerlibrary.helpers.SublimeOptions;
import com.appeaser.sublimepickerlibrary.recurrencepicker.SublimeRecurrencePicker.RecurrenceOption;
import com.cooey.android.users.C0757R;
import java.text.DateFormat;
import java.util.Locale;
import java.util.TimeZone;

public class SublimePickerFragment extends DialogFragment {
    Callback mCallback;
    DateFormat mDateFormatter = DateFormat.getDateInstance(2, Locale.getDefault());
    SublimeListenerAdapter mListener = new C08001();
    SublimePicker mSublimePicker;
    DateFormat mTimeFormatter = DateFormat.getTimeInstance(3, Locale.getDefault());

    public interface Callback {
        void onCancelled();

        Pair<Boolean, SublimeOptions> onDateTimeRecurrenceSet(int i, int i2, int i3, int i4, int i5, RecurrenceOption recurrenceOption, String str);
    }

    class C08001 extends SublimeListenerAdapter {
        C08001() {
        }

        public void onDateTimeRecurrenceSet(SublimePicker sublimeMaterialPicker, SelectedDate selectedDate, int hourOfDay, int minute, RecurrenceOption recurrenceOption, String recurrenceRule) {
            if (SublimePickerFragment.this.mCallback != null) {
                SublimePickerFragment.this.mCallback.onDateTimeRecurrenceSet(selectedDate.getFirstDate().get(1), selectedDate.getFirstDate().get(2), selectedDate.getFirstDate().get(5), hourOfDay, minute, recurrenceOption, recurrenceRule);
            }
            SublimePickerFragment.this.dismiss();
        }

        public void onCancelled() {
            if (SublimePickerFragment.this.mCallback != null) {
                SublimePickerFragment.this.mCallback.onCancelled();
            }
            SublimePickerFragment.this.dismiss();
        }
    }

    public SublimePickerFragment() {
        this.mTimeFormatter.setTimeZone(TimeZone.getTimeZone("GMT+0"));
    }

    public void setCallback(Callback callback) {
        this.mCallback = callback;
    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.mSublimePicker = (SublimePicker) getActivity().getLayoutInflater().inflate(C0757R.layout.sublime_picker, container);
        Bundle arguments = getArguments();
        SublimeOptions options = null;
        if (arguments != null) {
            options = (SublimeOptions) arguments.getParcelable("SUBLIME_OPTIONS");
        }
        this.mSublimePicker.initializePicker(options, this.mListener);
        return this.mSublimePicker;
    }
}
