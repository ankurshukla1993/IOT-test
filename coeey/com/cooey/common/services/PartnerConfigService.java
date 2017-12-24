package com.cooey.common.services;

import com.cooey.common.vo.SettingsConfig;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PartnerConfigService {
    @GET("v2/tenants/{tenantId}/configuration/ANDROID")
    @Headers({"Accept:application/json"})
    Call<SettingsConfig> getPartnerConfig(@Header("X-Auth-Token") String str, @Path("tenantId") String str2, @Query("appType") String str3);

    @GET("v2/tenants/settings")
    @Headers({"Accept:application/json"})
    Call<SettingsConfig> getPartnerConfig2(@Header("X-Auth-Token") String str);
}
