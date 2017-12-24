package com.cooey.api.client;

import com.cooey.api.client.models.Message;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface MessagesApi {
    @POST("v2/messages")
    Call<Message> createMessage(@Header("X-Auth-Token") String str, @Body Message message);

    @GET("v2/messages/{userId}")
    Call<List<Message>> getMessages(@Path("userId") String str, @Header("X-Auth-Token") String str2);

    @GET("v2/messages/{userId}/from/{fromUserId}")
    Call<List<Message>> getMessages_0(@Header("X-Auth-Token") String str, @Path("userId") String str2, @Path("fromUserId") String str3);
}
