package android.databinding.adapters;

import android.databinding.BindingMethod;
import android.databinding.BindingMethods;
import android.widget.LinearLayout;

@BindingMethods({@BindingMethod(attribute = "android:divider", method = "setDividerDrawable", type = LinearLayout.class), @BindingMethod(attribute = "android:measureWithLargestChild", method = "setMeasureWithLargestChildEnabled", type = LinearLayout.class)})
public class LinearLayoutBindingAdapter {
}
