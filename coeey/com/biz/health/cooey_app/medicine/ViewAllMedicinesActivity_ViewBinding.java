package com.biz.health.cooey_app.medicine;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.biz.health.cooey_app.C0644R;

public class ViewAllMedicinesActivity_ViewBinding implements Unbinder {
    private ViewAllMedicinesActivity target;
    private View view2131361899;

    @UiThread
    public ViewAllMedicinesActivity_ViewBinding(ViewAllMedicinesActivity target) {
        this(target, target.getWindow().getDecorView());
    }

    @UiThread
    public ViewAllMedicinesActivity_ViewBinding(final ViewAllMedicinesActivity target, View source) {
        this.target = target;
        target.revMedicines = (RecyclerView) Utils.findRequiredViewAsType(source, C0644R.id.rev_medicines, "field 'revMedicines'", RecyclerView.class);
        target.toolbar = (Toolbar) Utils.findRequiredViewAsType(source, C0644R.id.toolbar, "field 'toolbar'", Toolbar.class);
        View view = Utils.findRequiredView(source, C0644R.id.btn_add_new, "field 'btnAddNewMedicine' and method 'addNewMedicine'");
        target.btnAddNewMedicine = (FloatingActionButton) Utils.castView(view, C0644R.id.btn_add_new, "field 'btnAddNewMedicine'", FloatingActionButton.class);
        this.view2131361899 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.addNewMedicine();
            }
        });
        target.txtNoMedicineText = (TextView) Utils.findRequiredViewAsType(source, C0644R.id.txt_no_medicine, "field 'txtNoMedicineText'", TextView.class);
        target.progressBar = (ProgressBar) Utils.findRequiredViewAsType(source, C0644R.id.progressBar, "field 'progressBar'", ProgressBar.class);
    }

    @CallSuper
    public void unbind() {
        ViewAllMedicinesActivity target = this.target;
        if (target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target.revMedicines = null;
        target.toolbar = null;
        target.btnAddNewMedicine = null;
        target.txtNoMedicineText = null;
        target.progressBar = null;
        this.view2131361899.setOnClickListener(null);
        this.view2131361899 = null;
    }
}
