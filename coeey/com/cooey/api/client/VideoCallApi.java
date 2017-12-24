package com.cooey.api.client;

import com.cooey.api.client.models.VideoCallSession;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface VideoCallApi {
    @POST("v2/videoCall/initiateVideoCall")
    @Headers({"Content-Type:application/json"})
    Call<VideoCallSession> initiateVideoCall(@Header("x-Auth-token") String str, @Body VideoCallSession videoCallSession);
}
