package com.cooey.api.client;

import com.cooey.api.client.models.CreateVitalRequest;
import com.cooey.api.client.models.Vital;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface VitalsApi {
    @POST("v2/vitals")
    Call<Vital> create(@Header("x-Auth-token") String str, @Body CreateVitalRequest createVitalRequest);

    @DELETE("v2/vitals/{vitalId}")
    Call<Void> delete(@Path("vitalId") String str, @Header("X-Auth-Token") String str2);

    @GET("v2/vitals")
    Call<List<Vital>> get(@Header("x-Auth-Token") String str, @Query("offset") Integer num, @Query("value") Integer num2, @Query("type") String str2, @Query("userId") String str3);

    @GET("v2/vitals/users/vitals")
    Call<List<Vital>> getVitalForUsers(@Header("x-auth-token") String str, @Query("start") Long l, @Query("end") Long l2, @Query("skip") Integer num, @Query("limit") Integer num2, @Query("careTakerId") String str2);

    @GET("v2/vitals/user/{userId}")
    Call<List<Vital>> get_0(@Path("userId") String str, @Header("x-auth-token") String str2, @Query("start") Long l, @Query("end") Long l2, @Query("skip") Integer num, @Query("limit") Integer num2);

    @GET("v2/vitals/{vitalId}")
    Call<Vital> get_1(@Path("vitalId") String str, @Header("X-Auth-Token") String str2);

    @PUT("v2/vitals")
    Call<Vital> update(@Header("X-Auth-Token") String str, @Body CreateVitalRequest createVitalRequest);
}
