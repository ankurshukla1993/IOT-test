package com.cooey.common.services;

import com.cooey.common.services.requests.CreateSessionRequest;
import com.cooey.common.vo.Session;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface SessionsService {
    @POST("v2/sessions")
    @Headers({"Accept: application/json"})
    Call<Session> create(@Body CreateSessionRequest createSessionRequest);

    @GET("v2/sessions")
    Call<Session> get(@Header("X-Auth-Token") String str);

    @POST("v2/sessions/{externalId}")
    @Headers({"Accept: application/json", "Content-Type:application/json"})
    Call<Session> getSessionForExternalId(@Path("externalId") String str);

    @PUT("v2/sessions")
    Call<Session> update(@Header("X-Auth-Token") String str);
}
