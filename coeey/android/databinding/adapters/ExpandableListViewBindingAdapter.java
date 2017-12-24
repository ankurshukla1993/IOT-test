package android.databinding.adapters;

import android.databinding.BindingMethod;
import android.databinding.BindingMethods;
import android.widget.ExpandableListView;

@BindingMethods({@BindingMethod(attribute = "android:onChildClick", method = "setOnChildClickListener", type = ExpandableListView.class), @BindingMethod(attribute = "android:onGroupClick", method = "setOnGroupClickListener", type = ExpandableListView.class), @BindingMethod(attribute = "android:onGroupCollapse", method = "setOnGroupCollapseListener", type = ExpandableListView.class), @BindingMethod(attribute = "android:onGroupExpand", method = "setOnGroupExpandListener", type = ExpandableListView.class)})
public class ExpandableListViewBindingAdapter {
}
