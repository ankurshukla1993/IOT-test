package android.databinding.adapters;

import android.databinding.BindingAdapter;
import android.databinding.BindingMethod;
import android.databinding.BindingMethods;
import android.databinding.adapters.AdapterViewBindingAdapter.OnItemSelected;
import android.databinding.adapters.AdapterViewBindingAdapter.OnItemSelectedComponentListener;
import android.databinding.adapters.AdapterViewBindingAdapter.OnNothingSelected;
import android.widget.AutoCompleteTextView;
import android.widget.AutoCompleteTextView.Validator;

@BindingMethods({@BindingMethod(attribute = "android:completionThreshold", method = "setThreshold", type = AutoCompleteTextView.class), @BindingMethod(attribute = "android:popupBackground", method = "setDropDownBackgroundDrawable", type = AutoCompleteTextView.class), @BindingMethod(attribute = "android:onDismiss", method = "setOnDismissListener", type = AutoCompleteTextView.class), @BindingMethod(attribute = "android:onItemClick", method = "setOnItemClickListener", type = AutoCompleteTextView.class)})
public class AutoCompleteTextViewBindingAdapter {

    public interface FixText {
        CharSequence fixText(CharSequence charSequence);
    }

    public interface IsValid {
        boolean isValid(CharSequence charSequence);
    }

    @BindingAdapter(requireAll = false, value = {"android:fixText", "android:isValid"})
    public static void setValidator(AutoCompleteTextView view, final FixText fixText, final IsValid isValid) {
        if (fixText == null && isValid == null) {
            view.setValidator(null);
        } else {
            view.setValidator(new Validator() {
                public boolean isValid(CharSequence text) {
                    if (isValid != null) {
                        return isValid.isValid(text);
                    }
                    return true;
                }

                public CharSequence fixText(CharSequence invalidText) {
                    if (fixText != null) {
                        return fixText.fixText(invalidText);
                    }
                    return invalidText;
                }
            });
        }
    }

    @BindingAdapter(requireAll = false, value = {"android:onItemSelected", "android:onNothingSelected"})
    public static void setOnItemSelectedListener(AutoCompleteTextView view, OnItemSelected selected, OnNothingSelected nothingSelected) {
        if (selected == null && nothingSelected == null) {
            view.setOnItemSelectedListener(null);
        } else {
            view.setOnItemSelectedListener(new OnItemSelectedComponentListener(selected, nothingSelected, null));
        }
    }
}
