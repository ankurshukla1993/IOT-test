package android.databinding.adapters;

import android.databinding.BindingAdapter;
import android.databinding.BindingMethod;
import android.databinding.BindingMethods;
import android.databinding.InverseBindingListener;
import android.databinding.InverseBindingMethod;
import android.databinding.InverseBindingMethods;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

@InverseBindingMethods({@InverseBindingMethod(attribute = "android:selectedItemPosition", type = AdapterView.class)})
@BindingMethods({@BindingMethod(attribute = "android:onItemClick", method = "setOnItemClickListener", type = AdapterView.class), @BindingMethod(attribute = "android:onItemLongClick", method = "setOnItemLongClickListener", type = AdapterView.class)})
public class AdapterViewBindingAdapter {

    public interface OnItemSelected {
        void onItemSelected(AdapterView<?> adapterView, View view, int i, long j);
    }

    public static class OnItemSelectedComponentListener implements OnItemSelectedListener {
        private final InverseBindingListener mAttrChanged;
        private final OnNothingSelected mNothingSelected;
        private final OnItemSelected mSelected;

        public OnItemSelectedComponentListener(OnItemSelected selected, OnNothingSelected nothingSelected, InverseBindingListener attrChanged) {
            this.mSelected = selected;
            this.mNothingSelected = nothingSelected;
            this.mAttrChanged = attrChanged;
        }

        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            if (this.mSelected != null) {
                this.mSelected.onItemSelected(parent, view, position, id);
            }
            if (this.mAttrChanged != null) {
                this.mAttrChanged.onChange();
            }
        }

        public void onNothingSelected(AdapterView<?> parent) {
            if (this.mNothingSelected != null) {
                this.mNothingSelected.onNothingSelected(parent);
            }
            if (this.mAttrChanged != null) {
                this.mAttrChanged.onChange();
            }
        }
    }

    public interface OnNothingSelected {
        void onNothingSelected(AdapterView<?> adapterView);
    }

    @BindingAdapter({"android:selectedItemPosition"})
    public static void setSelectedItemPosition(AdapterView view, int position) {
        if (view.getSelectedItemPosition() != position) {
            view.setSelection(position);
        }
    }

    @BindingAdapter(requireAll = false, value = {"android:onItemSelected", "android:onNothingSelected", "android:selectedItemPositionAttrChanged"})
    public static void setOnItemSelectedListener(AdapterView view, OnItemSelected selected, OnNothingSelected nothingSelected, InverseBindingListener attrChanged) {
        if (selected == null && nothingSelected == null && attrChanged == null) {
            view.setOnItemSelectedListener(null);
        } else {
            view.setOnItemSelectedListener(new OnItemSelectedComponentListener(selected, nothingSelected, attrChanged));
        }
    }
}
