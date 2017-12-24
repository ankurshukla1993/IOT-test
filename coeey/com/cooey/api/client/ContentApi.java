package com.cooey.api.client;

import com.cooey.api.client.models.ContentTemplate;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ContentApi {
    @POST("v2/content")
    @Headers({"Content-Type:application/json"})
    Call<ContentTemplate> create(@Header("x-Auth-Token") String str, @Body ContentTemplate contentTemplate);

    @GET("v2/content/{contentId}")
    Call<ContentTemplate> get(@Path("contentId") String str, @Header("x-Auth-Token") String str2);

    @GET("v2/content")
    Call<List<ContentTemplate>> getAll(@Header("x-Auth-Token") String str, @Query("type") String str2);

    @PUT("v2/content")
    @Headers({"Content-Type:application/json"})
    Call<ContentTemplate> update(@Header("x-Auth-Token") String str, @Body ContentTemplate contentTemplate);
}
