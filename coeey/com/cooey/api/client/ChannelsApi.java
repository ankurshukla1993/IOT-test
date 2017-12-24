package com.cooey.api.client;

import com.cooey.api.client.models.Channel;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ChannelsApi {
    @POST("v2/channels")
    Call<Channel> createChannel(@Body Channel channel);

    @GET("v2/channels")
    Call<String> test();
}
