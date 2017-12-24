package com.cooey.android.medical_reports;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface MedicalReportsService {
    @POST("v2/medical-reports")
    Call<MedicalReport> create(@Header("X-Auth-Token") String str, @Body MedicalReport medicalReport);

    @GET("v2/medical-reports/users/{userId}")
    Call<List<MedicalReport>> getMedicalReports(@Header("X-Auth-Token") String str, @Path("userId") String str2);
}
