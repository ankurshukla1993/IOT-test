package com.biz.health.cooey_app.secondary_vital;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.biz.health.cooey_app.C0644R;

public class SecondaryVitalViewHolder_ViewBinding implements Unbinder {
    private SecondaryVitalViewHolder target;
    private View view2131362421;

    @UiThread
    public SecondaryVitalViewHolder_ViewBinding(final SecondaryVitalViewHolder target, View source) {
        this.target = target;
        target.txtViewSecondaryVital = (TextView) Utils.findRequiredViewAsType(source, C0644R.id.txt_view_secondary_vital_header, "field 'txtViewSecondaryVital'", TextView.class);
        View view = Utils.findRequiredView(source, C0644R.id.rel_secondary_vital, "method 'addDynamicVital'");
        this.view2131362421 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.addDynamicVital();
            }
        });
    }

    @CallSuper
    public void unbind() {
        SecondaryVitalViewHolder target = this.target;
        if (target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target.txtViewSecondaryVital = null;
        this.view2131362421.setOnClickListener(null);
        this.view2131362421 = null;
    }
}
