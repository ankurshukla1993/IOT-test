package com.cooey.android.medical_reports;

import android.support.v7.widget.RecyclerView.ViewHolder;
import com.cooey.android.medical_reports.databinding.LayoutMedicalReportItemViewBinding;

public class MedicalReportViewHolder extends ViewHolder {
    MedicalReportItemViewModel medicalReportItemViewModel;

    public MedicalReportViewHolder(LayoutMedicalReportItemViewBinding layoutMedicalReportItemViewBinding) {
        super(layoutMedicalReportItemViewBinding.getRoot());
        this.medicalReportItemViewModel = new MedicalReportItemViewModel(layoutMedicalReportItemViewBinding.getRoot().getContext());
        layoutMedicalReportItemViewBinding.setMedicalReportItemViewModel(this.medicalReportItemViewModel);
    }

    public void bind(MedicalReport medicalReport) {
        this.medicalReportItemViewModel.setMedicalReport(medicalReport);
    }
}
