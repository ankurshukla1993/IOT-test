package android.databinding.adapters;

import android.databinding.BindingMethod;
import android.databinding.BindingMethods;
import android.widget.ActionMenuView;

@BindingMethods({@BindingMethod(attribute = "android:onMenuItemClick", method = "setOnMenuItemClickListener", type = ActionMenuView.class)})
public class ActionMenuViewBindingAdapter {
}
