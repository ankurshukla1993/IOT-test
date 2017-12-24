package android.databinding.adapters;

import android.databinding.BindingAdapter;
import android.databinding.InverseBindingListener;
import android.databinding.InverseBindingMethod;
import android.databinding.InverseBindingMethods;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

@InverseBindingMethods({@InverseBindingMethod(attribute = "android:checkedButton", method = "getCheckedRadioButtonId", type = RadioGroup.class)})
public class RadioGroupBindingAdapter {
    @BindingAdapter({"android:checkedButton"})
    public static void setCheckedButton(RadioGroup view, int id) {
        if (id != view.getCheckedRadioButtonId()) {
            view.check(id);
        }
    }

    @BindingAdapter(requireAll = false, value = {"android:onCheckedChanged", "android:checkedButtonAttrChanged"})
    public static void setListeners(RadioGroup view, final OnCheckedChangeListener listener, final InverseBindingListener attrChange) {
        if (attrChange == null) {
            view.setOnCheckedChangeListener(listener);
        } else {
            view.setOnCheckedChangeListener(new OnCheckedChangeListener() {
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    if (listener != null) {
                        listener.onCheckedChanged(group, checkedId);
                    }
                    attrChange.onChange();
                }
            });
        }
    }
}
