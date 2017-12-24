package com.cooey.api.client;

import com.cooey.api.client.models.CaretakerEvent;
import com.cooey.api.client.models.Event;
import com.cooey.api.client.models.EventRescheduleRequestVO;
import com.cooey.api.client.models.User;
import com.cooey.api.client.models.UserEventRelation;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface EventsApi {
    @POST("v2/events")
    Call<Event> create(@Header("X-Auth-Token") String str, @Body Event event);

    @POST("v2/events/users")
    Call<List<UserEventRelation>> createEvent(@Body List<UserEventRelation> list);

    @GET("v2/events/users/{userId}")
    Call<List<Event>> getAll(@Path("userId") String str);

    @GET("v2/events/caretakers/{caretakerId}")
    Call<List<CaretakerEvent>> getCaretakerDietTemplates(@Path("caretakerId") String str, @Query("start") Long l, @Query("end") Long l2);

    @GET("v2/events/caretakers/{caretakerId}/latest")
    Call<List<CaretakerEvent>> getCaretakerDietTemplateslatest(@Path("caretakerId") String str, @Query("start") Long l, @Query("end") Long l2);

    @GET("v2/events/{eventId}/users")
    Call<List<User>> getUsers(@Path("eventId") String str);

    @POST("v2/events/guardian/changeEvent")
    Call<Void> modifyEventForPatient(@Body EventRescheduleRequestVO eventRescheduleRequestVO);

    @GET("v2/events/{userId}")
    @Headers({"Content-Type:application/json"})
    Call<List<CaretakerEvent>> read(@Path("userId") String str, @Header("X-Auth-Token") String str2, @Query("start") Long l, @Query("end") Long l2);

    @GET("v2/events/{userId}/latest")
    @Headers({"Content-Type:application/json"})
    Call<List<Event>> read_0(@Path("userId") String str, @Header("X-Auth-Token") String str2, @Query("limit") Integer num, @Query("offset") Integer num2);

    @PUT("v2/events")
    Call<Event> update(@Header("X-Auth-Token") String str, @Body Event event);
}
