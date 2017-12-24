package com.cooey.api.client;

import com.cooey.api.client.models.InputStream;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface MediaApi {
    @GET("v2/media/{mediaId}")
    Call<Void> getMedia(@Path("mediaId") String str);

    @POST("v2/media/upload/{fileName}")
    @Headers({"Content-Type:application/octet-stream"})
    Call<String> uploadFile(@Path("fileName") String str, @Body InputStream inputStream);
}
