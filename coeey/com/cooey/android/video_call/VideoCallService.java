package com.cooey.android.video_call;

import com.cooey.api.client.models.VideoCallSession;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface VideoCallService {
    @POST("v2/videoCall/initiateVideoCall")
    Call<VideoCallSession> createVideoCallRequest(@Header("X-Auth-Token") String str, @Body VideoCallSession videoCallSession);
}
