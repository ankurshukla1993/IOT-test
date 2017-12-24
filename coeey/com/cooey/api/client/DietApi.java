package com.cooey.api.client;

import com.cooey.api.client.models.CaretakerDietTemplate;
import com.cooey.api.client.models.DietTemplate;
import com.cooey.api.client.models.Food;
import com.cooey.api.client.models.MealTags;
import com.cooey.api.client.models.User;
import com.cooey.api.client.models.UserDietRelation;
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

public interface DietApi {
    @POST("v2/diet/tenants/{tenantId}/templates")
    @Headers({"Content-Type:application/json"})
    Call<DietTemplate> create(@Path("tenantId") String str, @Header("X-Auth-Token") String str2, @Body DietTemplate dietTemplate);

    @POST("v2/diet/dietTag")
    @Headers({"Content-Type:application/json"})
    Call<MealTags> createTag(@Header("X-Auth-Token") String str, @Body MealTags mealTags);

    @DELETE("v2/diet/template/{id}")
    @Headers({"Content-Type:application/json"})
    Call<Void> deleteDiet(@Path("id") String str);

    @GET("v2/diet/tenants/{tenantId}/templates")
    @Headers({"Content-Type:application/json"})
    Call<List<DietTemplate>> get(@Path("tenantId") String str, @Header("X-Auth-Token") String str2, @Query("type") String str3, @Query("start") Long l, @Query("end") Long l2);

    @GET("v2/diet/assignedDiet/{dietId}")
    @Headers({"Content-Type:application/json"})
    Call<List<User>> getAssignedPatient(@Path("dietId") String str, @Header("X-Auth-Token") String str2);

    @GET("v2/diet/templaltes/caretakers/{caretakerId}")
    @Headers({"Content-Type:application/json"})
    Call<List<CaretakerDietTemplate>> getCaretakerTemplates(@Path("caretakerId") String str, @Header("X-Auth-Token") String str2, @Query("start") Long l, @Query("end") Long l2);

    @GET("v2/diet/template/{id}")
    @Headers({"Content-Type:application/json"})
    Call<DietTemplate> getDiet(@Path("id") String str, @Header("X-Auth-Token") String str2);

    @POST("v2/diet/templates")
    @Headers({"Content-Type:application/json"})
    Call<List<UserDietRelation>> getDietRelation(@Query("userId") String str, @Query("type") String str2, @Body List<UserDietRelation> list);

    @GET("v2/diet/owners/{ownerId}/templates")
    @Headers({"Content-Type:application/json"})
    Call<List<DietTemplate>> getOwner(@Path("ownerId") String str, @Header("X-Auth-Token") String str2, @Query("start") Long l, @Query("end") Long l2);

    @GET("v2/diet/dietTag")
    @Headers({"Content-Type:application/json"})
    Call<List<MealTags>> getTag(@Header("X-Auth-Token") String str);

    @GET("v2/diet/dietTag/{id}")
    @Headers({"Content-Type:application/json"})
    Call<MealTags> getTagById(@Path("id") String str, @Header("X-Auth-Token") String str2);

    @GET("v2/diet/search/{query}")
    @Headers({"Content-Type:application/json"})
    Call<List<Food>> searchDiet(@Path("query") String str, @Header("X-Auth-Token") String str2);

    @GET("v2/diet/templates/search")
    @Headers({"Content-Type:application/json"})
    Call<List<DietTemplate>> searchDiet_0(@Header("X-Auth-Token") String str, @Query("query") String str2, @Query("start") Long l, @Query("end") Long l2);

    @DELETE("v2/diet/unassignedDiet/{dietId}/{patientId}")
    @Headers({"Content-Type:application/json"})
    Call<Void> unAssignedPatient(@Path("dietId") String str, @Path("patientId") String str2, @Header("X-Auth-Token") String str3);

    @PUT("v2/diet/tenants/{tenantId}/templates")
    @Headers({"Content-Type:application/json"})
    Call<DietTemplate> updateDiet(@Path("tenantId") String str, @Header("X-Auth-Token") String str2, @Body DietTemplate dietTemplate);

    @PUT("v2/diet/dietTag")
    @Headers({"Content-Type:application/json"})
    Call<MealTags> updateTag(@Header("X-Auth-Token") String str, @Body MealTags mealTags);
}
