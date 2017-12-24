package com.biz.health.cooey_app.medicine;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.arlib.floatingsearchview.FloatingSearchView;
import com.biz.health.cooey_app.C0644R;

public class MedicineSearchActivity_ViewBinding implements Unbinder {
    private MedicineSearchActivity target;

    @UiThread
    public MedicineSearchActivity_ViewBinding(MedicineSearchActivity target) {
        this(target, target.getWindow().getDecorView());
    }

    @UiThread
    public MedicineSearchActivity_ViewBinding(MedicineSearchActivity target, View source) {
        this.target = target;
        target.floatingSearchView = (FloatingSearchView) Utils.findRequiredViewAsType(source, C0644R.id.floating_search_view, "field 'floatingSearchView'", FloatingSearchView.class);
    }

    @CallSuper
    public void unbind() {
        MedicineSearchActivity target = this.target;
        if (target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target.floatingSearchView = null;
    }
}
