package android.databinding.adapters;

import android.databinding.BindingMethod;
import android.databinding.BindingMethods;
import android.widget.AbsSeekBar;

@BindingMethods({@BindingMethod(attribute = "android:thumbTint", method = "setThumbTintList", type = AbsSeekBar.class)})
public class AbsSeekBarBindingAdapter {
}
