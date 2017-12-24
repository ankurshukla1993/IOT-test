package android.databinding.adapters;

import android.databinding.BindingMethod;
import android.databinding.BindingMethods;
import android.widget.CheckedTextView;

@BindingMethods({@BindingMethod(attribute = "android:checkMark", method = "setCheckMarkDrawable", type = CheckedTextView.class), @BindingMethod(attribute = "android:checkMarkTint", method = "setCheckMarkTintList", type = CheckedTextView.class)})
public class CheckedTextViewBindingAdapter {
}
