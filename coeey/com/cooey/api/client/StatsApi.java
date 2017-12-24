package com.cooey.api.client;

import com.cooey.api.client.models.Alert;
import com.cooey.api.client.models.Careplan;
import com.cooey.api.client.models.DashboardStats;
import com.cooey.api.client.models.PatientVitalStatsResponse;
import com.cooey.api.client.models.TaskStatus;
import com.cooey.api.client.models.User;
import java.util.List;
import java.util.Map;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface StatsApi {
    @GET("v2/stats/patients/careplan")
    Call<Map<String, Careplan>> getCarePlan(@Header("X-Auth-Token") String str, @Query("userType") String str2);

    @GET("v2/stats/alerts")
    Call<Map<String, List<Alert>>> getCaretakerAlerts(@Header("X-Auth-Token") String str, @Query("limit") Integer num, @Query("offset") Integer num2, @Query("notifierId") String str2, @Query("userType") String str3);

    @GET("v2/stats/users/task")
    Call<Map<String, TaskStatus>> getCaretakerTask(@Header("X-Auth-Token") String str, @Query("userType") String str2);

    @GET("v2/stats/caretakers/residents")
    Call<Map<String, TaskStatus>> getCaretakerVital(@Header("X-Auth-Token") String str);

    @GET("v2/stats/caretakers/residents/vitals")
    Call<Map<String, TaskStatus>> getCaretakerVitalCount(@Header("X-Auth-Token") String str);

    @GET("v2/stats/patients/caretakers")
    Call<Map<String, List<User>>> getCaretakers(@Header("X-Auth-Token") String str, @Query("userType") String str2);

    @GET("v2/stats/dashboard/stats")
    Call<DashboardStats> getDashBoard(@Header("X-Auth-Token") String str);

    @GET("v2/stats/users/{userId}/vitals")
    Call<PatientVitalStatsResponse> getPatientStats(@Path("userId") String str, @Header("X-Auth-Token") String str2);
}
