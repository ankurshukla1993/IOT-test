package com.cooey.api.client;

import com.cooey.api.client.models.Medicine;
import com.cooey.api.client.models.UserMedicine;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MedicinesApi {
    @POST("v2/medicines")
    Call<UserMedicine> create(@Header("X-Auth-Token") String str, @Body UserMedicine userMedicine);

    @GET("v2/medicines")
    Call<List<UserMedicine>> get(@Header("X-Auth-Token") String str, @Query("userId") String str2);

    @GET("v2/medicines/search")
    Call<List<Medicine>> search(@Header("X-Auth-Token") String str, @Query("query") String str2);

    @PUT("v2/medicines/{medicineId}")
    Call<UserMedicine> update(@Path("medicineId") String str, @Header("X-Auth-Token") String str2, @Body UserMedicine userMedicine);
}
