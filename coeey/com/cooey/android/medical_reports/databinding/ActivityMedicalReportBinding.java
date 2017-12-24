package com.cooey.android.medical_reports.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.databinding.ViewDataBinding.IncludedLayouts;
import android.databinding.generated.callback.OnClickListener.Listener;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.biz.health.cooey_app.C0644R;
import com.cooey.android.medical_reports.MedicalReportActivityViewModel;
import com.cooey.android.medical_reports.MedicalReportRecyclerViewAdapter;
import com.cooey.common.BindingAdapters;

public class ActivityMedicalReportBinding extends ViewDataBinding implements Listener {
    @Nullable
    private static final IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    @NonNull
    public final FloatingActionButton addM;
    @NonNull
    public final AppBarLayout appbar;
    @Nullable
    private final OnClickListener mCallback5;
    private long mDirtyFlags = -1;
    @Nullable
    private MedicalReportActivityViewModel mMedicalReportActivityViewModel;
    @NonNull
    private final RelativeLayout mboundView0;
    @NonNull
    public final RecyclerView medicalReportsRecyclerView;
    @NonNull
    public final Toolbar toolbar;

    static {
        sViewsWithIds.put(C0644R.id.appbar, 3);
        sViewsWithIds.put(C0644R.id.toolbar, 4);
    }

    public ActivityMedicalReportBinding(@NonNull DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 1);
        Object[] bindings = mapBindings(bindingComponent, root, 5, sIncludes, sViewsWithIds);
        this.addM = (FloatingActionButton) bindings[2];
        this.addM.setTag(null);
        this.appbar = (AppBarLayout) bindings[3];
        this.mboundView0 = (RelativeLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.medicalReportsRecyclerView = (RecyclerView) bindings[1];
        this.medicalReportsRecyclerView.setTag(null);
        this.toolbar = (Toolbar) bindings[4];
        setRootTag(root);
        this.mCallback5 = new android.databinding.generated.callback.OnClickListener(this, 1);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 4;
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
        if (28 != variableId) {
            return false;
        }
        setMedicalReportActivityViewModel((MedicalReportActivityViewModel) variable);
        return true;
    }

    public void setMedicalReportActivityViewModel(@Nullable MedicalReportActivityViewModel MedicalReportActivityViewModel) {
        updateRegistration(0, MedicalReportActivityViewModel);
        this.mMedicalReportActivityViewModel = MedicalReportActivityViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(28);
        super.requestRebind();
    }

    @Nullable
    public MedicalReportActivityViewModel getMedicalReportActivityViewModel() {
        return this.mMedicalReportActivityViewModel;
    }

    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeMedicalReportActivityViewModel((MedicalReportActivityViewModel) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeMedicalReportActivityViewModel(MedicalReportActivityViewModel MedicalReportActivityViewModel, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        } else if (fieldId != 30) {
            return false;
        } else {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        }
    }

    protected void executeBindings() {
        synchronized (this) {
            long dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        MedicalReportActivityViewModel medicalReportActivityViewModel = this.mMedicalReportActivityViewModel;
        MedicalReportRecyclerViewAdapter medicalReportActivityViewModelMedicalReportRecyclerViewAdapter = null;
        if (!((dirtyFlags & 7) == 0 || medicalReportActivityViewModel == null)) {
            medicalReportActivityViewModelMedicalReportRecyclerViewAdapter = medicalReportActivityViewModel.getMedicalReportRecyclerViewAdapter();
        }
        if ((4 & dirtyFlags) != 0) {
            this.addM.setOnClickListener(this.mCallback5);
            BindingAdapters.onSetLayoutManager(this.medicalReportsRecyclerView, "LINEAR");
        }
        if ((dirtyFlags & 7) != 0) {
            BindingAdapters.onSetRecyclerViewAdapter(this.medicalReportsRecyclerView, medicalReportActivityViewModelMedicalReportRecyclerViewAdapter);
        }
    }

    public final void _internalCallbackOnClick(int sourceId, View callbackArg_0) {
        MedicalReportActivityViewModel medicalReportActivityViewModel = this.mMedicalReportActivityViewModel;
        if (medicalReportActivityViewModel != null) {
            medicalReportActivityViewModel.showFileSelector();
        }
    }

    @NonNull
    public static ActivityMedicalReportBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityMedicalReportBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent bindingComponent) {
        return (ActivityMedicalReportBinding) DataBindingUtil.inflate(inflater, C0644R.layout.activity_medical_report, root, attachToRoot, bindingComponent);
    }

    @NonNull
    public static ActivityMedicalReportBinding inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityMedicalReportBinding inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(C0644R.layout.activity_medical_report, null, false), bindingComponent);
    }

    @NonNull
    public static ActivityMedicalReportBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityMedicalReportBinding bind(@NonNull View view, @Nullable DataBindingComponent bindingComponent) {
        if ("layout/activity_medical_report_0".equals(view.getTag())) {
            return new ActivityMedicalReportBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
