package com.biz.health.cooey_app.medicine;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.biz.health.cooey_app.C0644R;

public class AddMedicineFullScreenDialog_ViewBinding implements Unbinder {
    private AddMedicineFullScreenDialog target;

    @UiThread
    public AddMedicineFullScreenDialog_ViewBinding(AddMedicineFullScreenDialog target, View source) {
        this.target = target;
        target.txtMedicineName = (TextView) Utils.findRequiredViewAsType(source, C0644R.id.txt_view_medicine_name, "field 'txtMedicineName'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        AddMedicineFullScreenDialog target = this.target;
        if (target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target.txtMedicineName = null;
    }
}
