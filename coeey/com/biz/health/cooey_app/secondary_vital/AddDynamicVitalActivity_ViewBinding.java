package com.biz.health.cooey_app.secondary_vital;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.biz.health.cooey_app.C0644R;

public class AddDynamicVitalActivity_ViewBinding implements Unbinder {
    private AddDynamicVitalActivity target;
    private View view2131362201;

    @UiThread
    public AddDynamicVitalActivity_ViewBinding(AddDynamicVitalActivity target) {
        this(target, target.getWindow().getDecorView());
    }

    @UiThread
    public AddDynamicVitalActivity_ViewBinding(final AddDynamicVitalActivity target, View source) {
        this.target = target;
        target.linear = (LinearLayout) Utils.findRequiredViewAsType(source, C0644R.id.linear_layout, "field 'linear'", LinearLayout.class);
        target.toolbar = (Toolbar) Utils.findRequiredViewAsType(source, C0644R.id.toolbar, "field 'toolbar'", Toolbar.class);
        View view = Utils.findRequiredView(source, C0644R.id.imv_save, "method 'saveSecondaryVital'");
        this.view2131362201 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.saveSecondaryVital();
            }
        });
    }

    @CallSuper
    public void unbind() {
        AddDynamicVitalActivity target = this.target;
        if (target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target.linear = null;
        target.toolbar = null;
        this.view2131362201.setOnClickListener(null);
        this.view2131362201 = null;
    }
}
