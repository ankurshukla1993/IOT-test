package com.cooey.api.client;

import com.cooey.api.client.models.CreateSessionRequest;
import com.cooey.api.client.models.Session;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface SessionsApi {
    @POST("v2/sessions")
    @Headers({"Content-Type:application/json"})
    Call<Session> create(@Body CreateSessionRequest createSessionRequest);

    @GET("v2/sessions")
    @Headers({"Content-Type:application/json"})
    Call<Session> get(@Header("X-Auth-Token") String str);

    @PUT("v2/sessions")
    @Headers({"Content-Type:application/json"})
    Call<Session> update(@Header("X-Auth-Token") String str);
}
