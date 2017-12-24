package com.cooey.android.medical_reports;

import android.view.View;
import android.view.View.OnClickListener;

class MedicalReportActivityViewModel$2 implements OnClickListener {
    final /* synthetic */ MedicalReportActivityViewModel this$0;
    final /* synthetic */ MedicalReportPermissionHepler val$medicalReportPermissionHepler;

    MedicalReportActivityViewModel$2(MedicalReportActivityViewModel this$0, MedicalReportPermissionHepler medicalReportPermissionHepler) {
        this.this$0 = this$0;
        this.val$medicalReportPermissionHepler = medicalReportPermissionHepler;
    }

    public void onClick(View view) {
        this.val$medicalReportPermissionHepler.requestPermission(MedicalReportActivityViewModel.access$000(this.this$0));
    }
}
