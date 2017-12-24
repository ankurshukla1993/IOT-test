package com.cooey.common.services;

import com.cooey.common.vo.Channel;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ChannelsService {
    @POST("v2/channels")
    @Headers({"Accept:application/json"})
    Call<Channel> create(@Body Channel channel);
}
