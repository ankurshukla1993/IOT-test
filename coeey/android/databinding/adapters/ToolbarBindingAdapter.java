package android.databinding.adapters;

import android.databinding.BindingMethod;
import android.databinding.BindingMethods;
import android.widget.Toolbar;

@BindingMethods({@BindingMethod(attribute = "android:onMenuItemClick", method = "setOnMenuItemClickListener", type = Toolbar.class), @BindingMethod(attribute = "android:onNavigationClick", method = "setNavigationOnClickListener", type = Toolbar.class)})
public class ToolbarBindingAdapter {
}
