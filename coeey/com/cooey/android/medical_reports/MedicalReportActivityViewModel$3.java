package com.cooey.android.medical_reports;

import android.widget.Toast;
import java.util.ArrayList;

class MedicalReportActivityViewModel$3 implements MedicalReporttUploadCallback {
    final /* synthetic */ MedicalReportActivityViewModel this$0;

    MedicalReportActivityViewModel$3(MedicalReportActivityViewModel this$0) {
        this.this$0 = this$0;
    }

    public void onMedicalReportFileUploadComplete(MedicalReport medicalReport) {
        Toast.makeText(MedicalReportActivityViewModel.access$000(this.this$0), "Report Uploaded!", 0).show();
        if (MedicalReportActivityViewModel.access$100(this.this$0) == null) {
            MedicalReportActivityViewModel.access$102(this.this$0, new ArrayList());
        }
        MedicalReportActivityViewModel.access$100(this.this$0).add(medicalReport);
        MedicalReportActivityViewModel.access$200(this.this$0).setMedicalReports(MedicalReportActivityViewModel.access$100(this.this$0));
    }

    public void onUploadError() {
    }
}
