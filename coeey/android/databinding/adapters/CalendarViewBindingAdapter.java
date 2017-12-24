package android.databinding.adapters;

import android.databinding.BindingAdapter;
import android.databinding.InverseBindingListener;
import android.databinding.InverseBindingMethod;
import android.databinding.InverseBindingMethods;
import android.widget.CalendarView;
import android.widget.CalendarView.OnDateChangeListener;

@InverseBindingMethods({@InverseBindingMethod(attribute = "android:date", type = CalendarView.class)})
public class CalendarViewBindingAdapter {
    @BindingAdapter({"android:date"})
    public static void setDate(CalendarView view, long date) {
        if (view.getDate() != date) {
            view.setDate(date);
        }
    }

    @BindingAdapter(requireAll = false, value = {"android:onSelectedDayChange", "android:dateAttrChanged"})
    public static void setListeners(CalendarView view, final OnDateChangeListener onDayChange, final InverseBindingListener attrChange) {
        if (attrChange == null) {
            view.setOnDateChangeListener(onDayChange);
        } else {
            view.setOnDateChangeListener(new OnDateChangeListener() {
                public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                    if (onDayChange != null) {
                        onDayChange.onSelectedDayChange(view, year, month, dayOfMonth);
                    }
                    attrChange.onChange();
                }
            });
        }
    }
}
