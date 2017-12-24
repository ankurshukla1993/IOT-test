package android.databinding.adapters;

import android.databinding.BindingMethod;
import android.databinding.BindingMethods;
import android.widget.Chronometer;

@BindingMethods({@BindingMethod(attribute = "android:onChronometerTick", method = "setOnChronometerTickListener", type = Chronometer.class)})
public class ChronometerBindingAdapter {
}
