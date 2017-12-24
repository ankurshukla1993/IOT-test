package com.cooey.common.services;

import com.cooey.common.vo.Medicine;
import com.cooey.common.vo.MedicineSearch;
import io.reactivex.Observable;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface MedicinesService {
    @POST("v2/medicines")
    @Headers({"Accept: application/json"})
    Call<Medicine> addMedicineReminder(@Header("X-Auth-Token") String str, @Body Medicine medicine);

    @GET("v2/medicines")
    @Headers({"Accept: application/json"})
    Call<List<Medicine>> getMedicineReminderForPatient(@Header("X-Auth-Token") String str, @Query("userId") String str2);

    @GET("v2/medicines")
    @Headers({"Accept: application/json"})
    Observable<List<Medicine>> getMedicineReminderForPatientWithObservable(@Header("X-Auth-Token") String str, @Query("userId") String str2);

    @GET("v2/medicines/search")
    @Headers({"Accept: application/json"})
    Call<List<MedicineSearch>> medicineSearch(@Header("X-Auth-Token") String str, @Query("query") String str2);
}
