package com.biz.health.cooey_app.secondary_vital;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.biz.health.cooey_app.C0644R;

public class MedicalProfileActivity_ViewBinding implements Unbinder {
    private MedicalProfileActivity target;

    @UiThread
    public MedicalProfileActivity_ViewBinding(MedicalProfileActivity target) {
        this(target, target.getWindow().getDecorView());
    }

    @UiThread
    public MedicalProfileActivity_ViewBinding(MedicalProfileActivity target, View source) {
        this.target = target;
        target.revMedicalProfile = (RecyclerView) Utils.findRequiredViewAsType(source, C0644R.id.rev_medical_profile, "field 'revMedicalProfile'", RecyclerView.class);
        target.toolbar = (Toolbar) Utils.findRequiredViewAsType(source, C0644R.id.toolbar, "field 'toolbar'", Toolbar.class);
    }

    @CallSuper
    public void unbind() {
        MedicalProfileActivity target = this.target;
        if (target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target.revMedicalProfile = null;
        target.toolbar = null;
    }
}
