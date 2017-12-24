package android.databinding.adapters;

import android.databinding.BindingAdapter;
import android.databinding.InverseBindingListener;
import android.databinding.InverseBindingMethod;
import android.databinding.InverseBindingMethods;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import com.android.databinding.library.baseAdapters.C0562R;

@InverseBindingMethods({@InverseBindingMethod(attribute = "android:year", type = DatePicker.class), @InverseBindingMethod(attribute = "android:month", type = DatePicker.class), @InverseBindingMethod(attribute = "android:day", method = "getDayOfMonth", type = DatePicker.class)})
public class DatePickerBindingAdapter {

    private static class DateChangedListener implements OnDateChangedListener {
        InverseBindingListener mDayChanged;
        OnDateChangedListener mListener;
        InverseBindingListener mMonthChanged;
        InverseBindingListener mYearChanged;

        private DateChangedListener() {
        }

        public void setListeners(OnDateChangedListener listener, InverseBindingListener yearChanged, InverseBindingListener monthChanged, InverseBindingListener dayChanged) {
            this.mListener = listener;
            this.mYearChanged = yearChanged;
            this.mMonthChanged = monthChanged;
            this.mDayChanged = dayChanged;
        }

        public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            if (this.mListener != null) {
                this.mListener.onDateChanged(view, year, monthOfYear, dayOfMonth);
            }
            if (this.mYearChanged != null) {
                this.mYearChanged.onChange();
            }
            if (this.mMonthChanged != null) {
                this.mMonthChanged.onChange();
            }
            if (this.mDayChanged != null) {
                this.mDayChanged.onChange();
            }
        }
    }

    @BindingAdapter(requireAll = false, value = {"android:year", "android:month", "android:day", "android:onDateChanged", "android:yearAttrChanged", "android:monthAttrChanged", "android:dayAttrChanged"})
    public static void setListeners(DatePicker view, int year, int month, int day, OnDateChangedListener listener, InverseBindingListener yearChanged, InverseBindingListener monthChanged, InverseBindingListener dayChanged) {
        if (year == 0) {
            year = view.getYear();
        }
        if (day == 0) {
            day = view.getDayOfMonth();
        }
        if (yearChanged == null && monthChanged == null && dayChanged == null) {
            view.init(year, month, day, listener);
            return;
        }
        DateChangedListener oldListener = (DateChangedListener) ListenerUtil.getListener(view, C0562R.id.onDateChanged);
        if (oldListener == null) {
            oldListener = new DateChangedListener();
            ListenerUtil.trackListener(view, oldListener, C0562R.id.onDateChanged);
        }
        oldListener.setListeners(listener, yearChanged, monthChanged, dayChanged);
        view.init(year, month, day, oldListener);
    }
}
