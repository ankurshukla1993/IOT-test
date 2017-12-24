package com.cooey.api.client;

import com.cooey.api.client.models.PropertyStates;
import com.cooey.api.client.models.VitalTemplatesConfig;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface VitalTemplateApi {
    @GET("v2/vitalTemplate/{id}")
    Call<VitalTemplatesConfig> get(@Path("id") String str, @Header("x-Auth-Token") String str2);

    @GET("v2/vitalTemplate")
    Call<List<VitalTemplatesConfig>> getAll(@Header("x-Auth-Token") String str);

    @GET("v2/vitalTemplate/globalList")
    Call<List<VitalTemplatesConfig>> getGlobalList(@Header("x-Auth-Token") String str);

    @GET("v2/vitalTemplate/propertyState")
    Call<List<PropertyStates>> getState(@Header("x-Auth-Token") String str);

    @POST("v2/vitalTemplate")
    @Headers({"Content-Type:application/json"})
    Call<VitalTemplatesConfig> post(@Header("x-Auth-Token") String str, @Body VitalTemplatesConfig vitalTemplatesConfig);

    @PUT("v2/vitalTemplate")
    @Headers({"Content-Type:application/json"})
    Call<VitalTemplatesConfig> update(@Header("x-Auth-Token") String str, @Body VitalTemplatesConfig vitalTemplatesConfig);
}
