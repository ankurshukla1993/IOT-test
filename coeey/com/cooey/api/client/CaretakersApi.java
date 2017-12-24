package com.cooey.api.client;

import com.cooey.api.client.models.Caretaker;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CaretakersApi {
    @GET("v2/caretakers")
    Call<List<Caretaker>> getAll(@Header("X-Auth-Token") String str, @Query("caretakerId") String str2);

    @GET("v2/caretakers/{caretakerId}/users/{userId}")
    @Headers({"Content-Type:application/json"})
    Call<Caretaker> getByUserId(@Path("caretakerId") String str, @Path("userId") String str2, @Header("X-Auth-Token") String str3);

    @PUT("v2/caretakers")
    @Headers({"Content-Type:application/json"})
    Call<Caretaker> update(@Header("X-Auth-Token") String str, @Body Caretaker caretaker);
}
