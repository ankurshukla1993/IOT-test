package com.biz.health.cooey_app.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.databinding.ViewDataBinding.IncludedLayouts;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.CardView;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.biz.health.cooey_app.C0644R;
import com.biz.health.cooey_app.dashboard.widgets.holders.CareplanWidgetViewModel;
import com.github.lzyzsd.circleprogress.ArcProgress;

public class CareplanWidgetBinding extends ViewDataBinding {
    @Nullable
    private static final IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    @NonNull
    public final ArcProgress activityProgress;
    @NonNull
    public final CardView carePlanCard;
    @NonNull
    public final TextView careplanDate;
    @NonNull
    public final TextView careplanDuration;
    @NonNull
    public final TextView careplanHeader;
    @NonNull
    public final TextView careplanName;
    @NonNull
    public final ProgressBar careplanProgressBar;
    @NonNull
    public final ArcProgress dietProgress;
    @NonNull
    public final ConstraintLayout lowerContainerLayout;
    @Nullable
    private CareplanWidgetViewModel mCareplanWidgetModel;
    private long mDirtyFlags = -1;
    @NonNull
    private final FrameLayout mboundView0;
    @NonNull
    public final ArcProgress todoProgress;
    @NonNull
    public final ConstraintLayout upperConstraintLayout;
    @NonNull
    public final ArcProgress vitalProgress;

    static {
        sViewsWithIds.put(C0644R.id.carePlanCard, 1);
        sViewsWithIds.put(C0644R.id.careplan_progress_bar, 2);
        sViewsWithIds.put(C0644R.id.careplan_header, 3);
        sViewsWithIds.put(C0644R.id.careplan_name, 4);
        sViewsWithIds.put(C0644R.id.careplan_duration, 5);
        sViewsWithIds.put(C0644R.id.careplan_date, 6);
        sViewsWithIds.put(C0644R.id.upper_constraint_layout, 7);
        sViewsWithIds.put(C0644R.id.diet_progress, 8);
        sViewsWithIds.put(C0644R.id.vital_progress, 9);
        sViewsWithIds.put(C0644R.id.lower_container_layout, 10);
        sViewsWithIds.put(C0644R.id.todo_progress, 11);
        sViewsWithIds.put(C0644R.id.activity_progress, 12);
    }

    public CareplanWidgetBinding(@NonNull DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = mapBindings(bindingComponent, root, 13, sIncludes, sViewsWithIds);
        this.activityProgress = (ArcProgress) bindings[12];
        this.carePlanCard = (CardView) bindings[1];
        this.careplanDate = (TextView) bindings[6];
        this.careplanDuration = (TextView) bindings[5];
        this.careplanHeader = (TextView) bindings[3];
        this.careplanName = (TextView) bindings[4];
        this.careplanProgressBar = (ProgressBar) bindings[2];
        this.dietProgress = (ArcProgress) bindings[8];
        this.lowerContainerLayout = (ConstraintLayout) bindings[10];
        this.mboundView0 = (FrameLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.todoProgress = (ArcProgress) bindings[11];
        this.upperConstraintLayout = (ConstraintLayout) bindings[7];
        this.vitalProgress = (ArcProgress) bindings[9];
        setRootTag(root);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 2;
        }
        requestRebind();
    }

    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.mDirtyFlags != 0) {
                return true;
            }
            return false;
        }
    }

    public boolean setVariable(int variableId, @Nullable Object variable) {
        if (9 != variableId) {
            return false;
        }
        setCareplanWidgetModel((CareplanWidgetViewModel) variable);
        return true;
    }

    public void setCareplanWidgetModel(@Nullable CareplanWidgetViewModel CareplanWidgetModel) {
        this.mCareplanWidgetModel = CareplanWidgetModel;
    }

    @Nullable
    public CareplanWidgetViewModel getCareplanWidgetModel() {
        return this.mCareplanWidgetModel;
    }

    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    protected void executeBindings() {
        synchronized (this) {
            long dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
    }

    @NonNull
    public static CareplanWidgetBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static CareplanWidgetBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent bindingComponent) {
        return (CareplanWidgetBinding) DataBindingUtil.inflate(inflater, C0644R.layout.careplan_widget, root, attachToRoot, bindingComponent);
    }

    @NonNull
    public static CareplanWidgetBinding inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static CareplanWidgetBinding inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(C0644R.layout.careplan_widget, null, false), bindingComponent);
    }

    @NonNull
    public static CareplanWidgetBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static CareplanWidgetBinding bind(@NonNull View view, @Nullable DataBindingComponent bindingComponent) {
        if ("layout/careplan_widget_0".equals(view.getTag())) {
            return new CareplanWidgetBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
