package com.cooey.common.services;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface MediaService {
    @POST("v2/media/upload/{fileName}")
    Call<String> uploadFile(@Path("fileName") String str, @Body RequestBody requestBody);
}
