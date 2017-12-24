package com.cooey.api.client;

import com.cooey.api.client.models.Group;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GroupsApi {
    @POST("v2/groups")
    Call<Group> create(@Header("X-Auth-Token") String str, @Body Group group);

    @GET("v2/groups/{groupId}")
    Call<Group> getGroup(@Path("groupId") String str, @Header("X-Auth-Token") String str2);

    @GET("v2/groups/unique/{name}")
    Call<Group> getGroupByName(@Path("name") String str, @Header("X-Auth-Token") String str2, @Query("type") String str3);

    @GET("v2/groups/{groupId}/children")
    Call<List<Group>> read(@Path("groupId") String str, @Header("X-Auth-Token") String str2);

    @GET("v2/groups")
    Call<Void> readGroup(@Header("X-Auth-Token") String str, @Query("type") String str2, @Query("offset") Integer num, @Query("limit") Integer num2);

    @PUT("v2/groups")
    Call<Group> update(@Header("X-Auth-Token") String str, @Body Group group);
}
