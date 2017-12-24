package com.cooey.android.medical_reports;

import android.view.View;
import android.view.View.OnClickListener;

class MedicalReportItemViewModel$1 implements OnClickListener {
    final /* synthetic */ MedicalReportItemViewModel this$0;
    final /* synthetic */ MedicalReportPermissionHepler val$medicalReportPermissionHepler;

    MedicalReportItemViewModel$1(MedicalReportItemViewModel this$0, MedicalReportPermissionHepler medicalReportPermissionHepler) {
        this.this$0 = this$0;
        this.val$medicalReportPermissionHepler = medicalReportPermissionHepler;
    }

    public void onClick(View view) {
        this.val$medicalReportPermissionHepler.requestPermission(MedicalReportItemViewModel.access$000(this.this$0));
    }
}
