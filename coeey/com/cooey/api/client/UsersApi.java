package com.cooey.api.client;

import com.cooey.api.client.models.Caretaker;
import com.cooey.api.client.models.Changepwd;
import com.cooey.api.client.models.Contact;
import com.cooey.api.client.models.CreateGuardianRequest;
import com.cooey.api.client.models.CreateUserRequest;
import com.cooey.api.client.models.Guardian;
import com.cooey.api.client.models.ObservationSymptoms;
import com.cooey.api.client.models.Permissions;
import com.cooey.api.client.models.UpdateUserRequest;
import com.cooey.api.client.models.User;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UsersApi {
    @POST("v2/users/chngpwd")
    Call<Void> chngpwd(@Header("X-Auth-Token") String str, @Body Changepwd changepwd);

    @POST("v2/users")
    @Headers({"Content-Type:application/json"})
    Call<User> create(@Header("X-Auth-Token") String str, @Body CreateUserRequest createUserRequest);

    @POST("v2/users/{userId}/caretakers")
    Call<Caretaker> createCaretaker(@Path("userId") String str, @Header("X-Auth-Token") String str2, @Body Caretaker caretaker);

    @POST("v2/users/{userId}/contacts")
    Call<List<Contact>> createContact(@Path("userId") String str, @Header("X-Auth-Token") String str2, @Body List<Contact> list);

    @POST("v2/users/{userId}/guardians")
    Call<Guardian> createGuardian(@Path("userId") String str, @Header("X-Auth-Token") String str2, @Body CreateGuardianRequest createGuardianRequest);

    @POST("v2/users/{userId}/permissions")
    Call<Permissions> createPermissions(@Path("userId") String str, @Header("X-Auth-Token") String str2, @Body Permissions permissions);

    @POST("v2/users/symptoms")
    Call<ObservationSymptoms> createSymptoms(@Header("X-Auth-Token") String str, @Body ObservationSymptoms observationSymptoms);

    @GET("v2/users")
    Call<Void> get(@Header("X-Auth-Token") String str, @Query("type") String str2, @Query("offset") Integer num, @Query("limit") Integer num2, @Query("caretakerId") String str3);

    @GET("v2/users/{userId}/caretakers")
    Call<List<User>> getCaretakers(@Path("userId") String str, @Header("X-Auth-Token") String str2);

    @GET("v2/users/{userId}/contacts")
    Call<List<Contact>> getContacts(@Path("userId") String str, @Header("X-Auth-Token") String str2, @Query("type") String str3);

    @GET("v2/users/{userId}/guardians")
    Call<List<User>> getContacts_0(@Path("userId") String str, @Header("X-Auth-Token") String str2, @Query("offset") Integer num, @Query("limit") Integer num2);

    @GET("v2/users/{userId}/dependents")
    Call<List<User>> getDependents(@Path("userId") String str, @Header("X-Auth-Token") String str2);

    @GET("v2/users/{userId}/permissions")
    @Headers({"Content-Type:application/json"})
    Call<Permissions> getPermissions(@Path("userId") String str, @Header("X-Auth-Token") String str2);

    @GET("v2/users/symptoms/{userId}")
    Call<ObservationSymptoms> getSymptoms(@Path("userId") String str, @Header("X-Auth-Token") String str2);

    @GET("v2/users/unique/{emailId}")
    Call<User> getUniqueEmail(@Path("emailId") String str, @Header("X-Auth-Token") String str2);

    @GET("v2/users/{userId}/wards")
    @Headers({"Content-Type:application/json"})
    Call<List<User>> getWards(@Path("userId") String str);

    @GET("v2/users/{userId}")
    Call<User> get_0(@Path("userId") String str, @Header("X-Auth-Token") String str2);

    @DELETE("v2/users/{userId}/removeCareTaker")
    Call<Void> removeCareTaker(@Path("userId") String str, @Header("X-Auth-Token") String str2);

    @DELETE("v2/users/{userId}/contacts/{contactId}")
    Call<String> removeContacts(@Path("userId") String str, @Path("contactId") String str2, @Header("X-Auth-Token") String str3);

    @GET("v2/users/search")
    Call<List<User>> search(@Header("X-Auth-Token") String str, @Query("query") String str2, @Query("authkey") String str3, @Query("type") String str4, @Query("caretakerId") String str5);

    @GET("v2/users/{emaiId}/recovery")
    Call<User> sendPassword(@Path("emaiId") String str, @Header("X-Auth-Token") String str2);

    @DELETE("v2/users/{userId}/caretakers/{caretakerId}")
    Call<Void> unAssignCaretaker(@Path("userId") String str, @Path("caretakerId") String str2, @Header("X-Auth-Token") String str3);

    @PUT("v2/users")
    @Headers({"Content-Type:application/json"})
    Call<User> update(@Header("X-Auth-Token") String str, @Body UpdateUserRequest updateUserRequest);

    @PUT("v2/users/{userId}/contacts")
    Call<List<Contact>> updateContacts(@Path("userId") String str, @Header("X-Auth-Token") String str2, @Body List<Contact> list);

    @PUT("v2/users/{userId}/permissions")
    Call<Permissions> updatePermissions(@Path("userId") String str, @Header("X-Auth-Token") String str2, @Body Permissions permissions);

    @PUT("v2/users/symptoms")
    Call<ObservationSymptoms> updateSymptoms(@Body ObservationSymptoms observationSymptoms);

    @POST("v2/users/{userId}/profilePic")
    @Headers({"Content-Type:application/json"})
    Call<Void> uploadProfilePhoto(@Path("userId") String str, @Body User user);
}
