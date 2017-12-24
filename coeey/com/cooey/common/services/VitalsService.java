package com.cooey.common.services;

import com.cooey.common.vo.Vital;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface VitalsService {
    @POST("v2/vitals")
    @Headers({"Accept: application/json"})
    Call<Vital> addVitalForUser(@Header("X-Auth-Token") String str, @Body Vital vital);

    @GET("v2/vitals/{vitalId}")
    @Headers({"Accept: application/json"})
    Call<Vital> getVital(@Header("X-Auth-Token") String str, @Path("vitalId") String str2);

    @GET("v2/vitals")
    @Headers({"Accept: application/json"})
    Call<List<Vital>> getVitalsForUser(@Header("X-Auth-Token") String str, @Query("offset") int i, @Query("value") int i2, @Query("type") String str2, @Query("userId") String str3);
}
