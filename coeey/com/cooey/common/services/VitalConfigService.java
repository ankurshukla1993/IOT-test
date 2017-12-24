package com.cooey.common.services;

import com.cooey.common.config.VitalConfigResponse;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface VitalConfigService {
    @GET("v2/tenants/{tenantId}/configuration/ANDROID")
    @Headers({"Content-Type: application/json"})
    Call<VitalConfigResponse> getVitalConfigResponse(@Header("X-Auth-Token") String str, @Path("tenantId") String str2, @Query("appType") String str3);

    @GET("v2/tenants/{tenantId}/configuration/ANDROID")
    @Headers({"Content-Type: application/json"})
    Observable<VitalConfigResponse> getVitalConfigResponseWithObservable(@Header("X-Auth-Token") String str, @Path("tenantId") String str2, @Query("appType") String str3);
}
