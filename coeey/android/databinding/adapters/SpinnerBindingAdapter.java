package android.databinding.adapters;

import android.databinding.BindingMethod;
import android.databinding.BindingMethods;
import android.widget.Spinner;

@BindingMethods({@BindingMethod(attribute = "android:popupBackground", method = "setPopupBackgroundDrawable", type = Spinner.class)})
public class SpinnerBindingAdapter {
}
