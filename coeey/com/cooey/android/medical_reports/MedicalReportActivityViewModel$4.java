package com.cooey.android.medical_reports;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class MedicalReportActivityViewModel$4 implements Callback<List<MedicalReport>> {
    final /* synthetic */ MedicalReportActivityViewModel this$0;

    MedicalReportActivityViewModel$4(MedicalReportActivityViewModel this$0) {
        this.this$0 = this$0;
    }

    public void onResponse(Call<List<MedicalReport>> call, Response<List<MedicalReport>> response) {
        MedicalReportActivityViewModel.access$102(this.this$0, (List) response.body());
        MedicalReportActivityViewModel.access$200(this.this$0).setMedicalReports(MedicalReportActivityViewModel.access$100(this.this$0));
        this.this$0.setProgess(false);
    }

    public void onFailure(Call<List<MedicalReport>> call, Throwable t) {
        this.this$0.setProgess(false);
    }
}
