package com.cooey.android.medical_reports.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.databinding.ViewDataBinding.IncludedLayouts;
import android.databinding.adapters.TextViewBindingAdapter;
import android.databinding.generated.callback.OnClickListener.Listener;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.Guideline;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.biz.health.cooey_app.C0644R;
import com.cooey.android.medical_reports.MedicalReport;
import com.cooey.android.medical_reports.MedicalReportItemViewModel;

public class LayoutMedicalReportItemViewBinding extends ViewDataBinding implements Listener {
    @Nullable
    private static final IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    @NonNull
    public final Guideline guideline;
    @NonNull
    public final ImageView imageView;
    @Nullable
    private final OnClickListener mCallback3;
    private long mDirtyFlags = -1;
    @Nullable
    private MedicalReportItemViewModel mMedicalReportItemViewModel;
    @NonNull
    private final ConstraintLayout mboundView0;
    @NonNull
    public final ImageButton reportDownloadButton;
    @NonNull
    public final TextView reportName;
    @NonNull
    public final TextView reportTime;

    static {
        sViewsWithIds.put(C0644R.id.imageView, 4);
        sViewsWithIds.put(C0644R.id.guideline, 5);
    }

    public LayoutMedicalReportItemViewBinding(@NonNull DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 1);
        Object[] bindings = mapBindings(bindingComponent, root, 6, sIncludes, sViewsWithIds);
        this.guideline = (Guideline) bindings[5];
        this.imageView = (ImageView) bindings[4];
        this.mboundView0 = (ConstraintLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.reportDownloadButton = (ImageButton) bindings[3];
        this.reportDownloadButton.setTag(null);
        this.reportName = (TextView) bindings[2];
        this.reportName.setTag(null);
        this.reportTime = (TextView) bindings[1];
        this.reportTime.setTag(null);
        setRootTag(root);
        this.mCallback3 = new android.databinding.generated.callback.OnClickListener(this, 1);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 8;
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
        if (29 != variableId) {
            return false;
        }
        setMedicalReportItemViewModel((MedicalReportItemViewModel) variable);
        return true;
    }

    public void setMedicalReportItemViewModel(@Nullable MedicalReportItemViewModel MedicalReportItemViewModel) {
        updateRegistration(0, MedicalReportItemViewModel);
        this.mMedicalReportItemViewModel = MedicalReportItemViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(29);
        super.requestRebind();
    }

    @Nullable
    public MedicalReportItemViewModel getMedicalReportItemViewModel() {
        return this.mMedicalReportItemViewModel;
    }

    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeMedicalReportItemViewModel((MedicalReportItemViewModel) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeMedicalReportItemViewModel(MedicalReportItemViewModel MedicalReportItemViewModel, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        } else if (fieldId == 48) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        } else if (fieldId != 27) {
            return false;
        } else {
            synchronized (this) {
                this.mDirtyFlags |= 4;
            }
            return true;
        }
    }

    protected void executeBindings() {
        synchronized (this) {
            long dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        MedicalReport medicalReportItemViewModelMedicalReport = null;
        String medicalReportItemViewModelMedicalReportName = null;
        String medicalReportItemViewModelTimeString = null;
        MedicalReportItemViewModel medicalReportItemViewModel = this.mMedicalReportItemViewModel;
        if ((15 & dirtyFlags) != 0) {
            if ((dirtyFlags & 13) != 0) {
                if (medicalReportItemViewModel != null) {
                    medicalReportItemViewModelMedicalReport = medicalReportItemViewModel.getMedicalReport();
                }
                if (medicalReportItemViewModelMedicalReport != null) {
                    medicalReportItemViewModelMedicalReportName = medicalReportItemViewModelMedicalReport.getName();
                }
            }
            if (!((dirtyFlags & 11) == 0 || medicalReportItemViewModel == null)) {
                medicalReportItemViewModelTimeString = medicalReportItemViewModel.getTimeString();
            }
        }
        if ((8 & dirtyFlags) != 0) {
            this.reportDownloadButton.setOnClickListener(this.mCallback3);
        }
        if ((dirtyFlags & 13) != 0) {
            TextViewBindingAdapter.setText(this.reportName, medicalReportItemViewModelMedicalReportName);
        }
        if ((dirtyFlags & 11) != 0) {
            TextViewBindingAdapter.setText(this.reportTime, medicalReportItemViewModelTimeString);
        }
    }

    public final void _internalCallbackOnClick(int sourceId, View callbackArg_0) {
        MedicalReportItemViewModel medicalReportItemViewModel = this.mMedicalReportItemViewModel;
        if (medicalReportItemViewModel != null) {
            medicalReportItemViewModel.downloadReport();
        }
    }

    @NonNull
    public static LayoutMedicalReportItemViewBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static LayoutMedicalReportItemViewBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent bindingComponent) {
        return (LayoutMedicalReportItemViewBinding) DataBindingUtil.inflate(inflater, C0644R.layout.layout_medical_report_item_view, root, attachToRoot, bindingComponent);
    }

    @NonNull
    public static LayoutMedicalReportItemViewBinding inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static LayoutMedicalReportItemViewBinding inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(C0644R.layout.layout_medical_report_item_view, null, false), bindingComponent);
    }

    @NonNull
    public static LayoutMedicalReportItemViewBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static LayoutMedicalReportItemViewBinding bind(@NonNull View view, @Nullable DataBindingComponent bindingComponent) {
        if ("layout/layout_medical_report_item_view_0".equals(view.getTag())) {
            return new LayoutMedicalReportItemViewBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
