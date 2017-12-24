package com.cooey.common.services;

import com.cooey.common.services.requests.CreateGuardianRequest;
import com.cooey.common.services.requests.CreateUserRequest;
import com.cooey.common.vo.ChangePassword;
import com.cooey.common.vo.Guardian;
import com.cooey.common.vo.MedicalContact;
import com.cooey.common.vo.User;
import java.util.List;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UsersService {
    @PUT("v2/users/changePassword")
    @Headers({"Content-Type:application/json"})
    Call<ResponseBody> changePasswordForUser(@Header("X-Auth-Token") String str, @Body ChangePassword changePassword);

    @POST("v2/users")
    Call<User> create(@Header("X-Auth-Token") String str, @Body CreateUserRequest createUserRequest);

    @POST("v2/users/{userId}/guardians}")
    Call<Guardian> createContact(@Path("userId") String str, @Header("X-Auth-Token") String str2, @Body CreateGuardianRequest createGuardianRequest);

    @GET("v2/users")
    Call<List<User>> get(@Header("X-Auth-Token") String str, @Query("offset") Integer num, @Query("limit") Integer num2, @Query("caretakerId") String str2);

    @GET("v2/users/{userId}/caretakers")
    @Headers({"Content-Type:application/json"})
    Call<List<User>> getCaretakers(@Header("X-Auth-Token") String str, @Path("userId") String str2);

    @GET("v2/users/{userId}/guardians")
    Call<List<User>> getContacts(@Path("userId") String str, @Header("X-Auth-Token") String str2, @Query("offset") Integer num, @Query("limit") Integer num2);

    @GET("v2/users/{userId}/dependents")
    @Headers({"Accept:application/json"})
    Call<List<User>> getDependents(@Header("X-Auth-Token") String str, @Path("userId") String str2);

    @GET("v2/users/{userId}/contacts")
    @Headers({"Accept: application/json"})
    Call<List<MedicalContact>> getMedicalContactsForUser(@Header("X-Auth-Token") String str, @Path("userId") String str2, @Query("type") String str3);

    @GET("v2/users/{userId}")
    @Headers({"Accept:application/json"})
    Call<User> get_0(@Path("userId") String str, @Header("X-Auth-Token") String str2);

    @GET("v2/users/search")
    Call<List<User>> search(@Query("query") String str, @Query("type") String str2, @Query("caretakerId") String str3);

    @PUT("v2/users")
    Call<User> update(@Body User user);

    @POST("v2/users/{userId}/profilePic")
    @Headers({"Content-Type:application/json"})
    Call<ResponseBody> uploadProfilePic(@Header("X-Auth-Token") String str, @Path("userId") String str2, @Body User user);
}
