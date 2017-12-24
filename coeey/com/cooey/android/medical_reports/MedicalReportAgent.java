package com.cooey.android.medical_reports;

import android.content.Context;
import com.cooey.common.services.ApiClient;
import java.util.List;
import retrofit2.Callback;

public class MedicalReportAgent {
    private ApiClient apiClient;
    private final String authToken;
    private final Context context;
    private MedicalReportsService medicalReportsService = ((MedicalReportsService) this.apiClient.create(MedicalReportsService.class));
    private final String userId;

    public MedicalReportAgent(Context context, String userId, String authToken, String serverUrl) {
        this.context = context;
        this.userId = userId;
        this.authToken = authToken;
        this.apiClient = new ApiClient(context, serverUrl);
    }

    public void create(MedicalReport medicalReport, Callback<MedicalReport> callback) {
        this.medicalReportsService.create(this.authToken, medicalReport).enqueue(callback);
    }

    public void getMedicalReports(Callback<List<MedicalReport>> medicalReports) {
        this.medicalReportsService.getMedicalReports(this.authToken, this.userId).enqueue(medicalReports);
    }
}
