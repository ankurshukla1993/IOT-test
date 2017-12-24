package com.cooey.android.medical_reports;

public interface MedicalReporttUploadCallback {
    void onMedicalReportFileUploadComplete(MedicalReport medicalReport);

    void onUploadError();
}
