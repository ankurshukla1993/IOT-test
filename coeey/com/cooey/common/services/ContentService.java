package com.cooey.common.services;

import com.cooey.common.vo.Content;
import com.cooey.common.vo.careplan.ContentFeature;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface ContentService {
    @GET("content/{id}")
    Call<ContentFeature> getCareplanContentforUser(@Header("X-Auth_Token") String str, @Path("id") String str2);

    @GET("v2/content")
    @Headers({"Accept: application/json"})
    Call<List<Content>> getContentForUser(@Header("x-Auth-Token") String str);

    @GET("v2/users/{userId}/contents")
    @Headers({"Accept: application/json"})
    Call<List<Content>> getContentForUserId(@Header("x-Auth-Token") String str, @Path("userId") String str2);
}
