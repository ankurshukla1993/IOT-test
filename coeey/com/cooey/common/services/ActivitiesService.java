package com.cooey.common.services;

import com.cooey.common.vo.Activity;
import com.cooey.common.vo.ActivityResponse;
import com.cooey.common.vo.ChangeEvent;
import com.cooey.common.vo.FreeSlot;
import io.reactivex.Observable;
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

public interface ActivitiesService {
    @POST("v2/events")
    @Headers({"Accept: application/json"})
    Call<Activity> addEventsActivity(@Header("X-Auth-Token") String str, @Body Activity activity);

    @PUT("v2/events")
    Observable<Activity> editActivityById(@Header("X-Auth-Token") String str, @Body Activity activity);

    @GET("v2/events/schedules/caretakers/{caretakerId}")
    @Headers({"Accept: application/json"})
    Call<List<FreeSlot>> freeSlotsOfCaretaker(@Header("X-Auth-Token") String str, @Path("caretakerId") String str2, @Query("start") long j, @Query("end") long j2);

    @GET("v2/events/{eventId}")
    @Headers({"Accept: application/json"})
    Observable<Activity> getActivitById(@Header("X-Auth-Token") String str, @Path("eventId") String str2);

    @GET("v2/events/{userId}")
    @Headers({"Accept: application/json"})
    Call<List<ActivityResponse>> getActivities(@Header("X-Auth-Token") String str, @Path("userId") String str2);

    @GET("v2/events/{eventId}")
    Observable<List<Activity>> getActivitiesById(@Header("X-Auth-Token") String str, @Path("eventId") String str2);

    @GET("v2/events/caretakers/{caretakerId}/latest")
    @Headers({"Accept: application/json"})
    Call<List<ActivityResponse>> getActivitiesLatest(@Path("caretakerId") String str);

    @GET("v2/events/caretaker/{userId}")
    @Headers({"Accept: application/json"})
    Call<List<ActivityResponse>> getActivitiesOfUser(@Header("X-Auth-Token") String str, @Path("userId") String str2);

    @GET("v2/events/{userId}")
    @Headers({"Accept: application/json"})
    Observable<List<Activity>> getActivitiesWithObservable(@Header("X-Auth-Token") String str, @Path("userId") String str2);

    @GET("v2/events/{eventId}")
    @Headers({"Accept: application/json"})
    Call<Activity> getActivity(@Header("X-Auth-Token") String str, @Body String str2);

    @GET("v2/events/{eventId}")
    Call<List<Activity>> getActivityById(@Header("X-Auth-Token") String str, @Path("eventId") String str2);

    @GET("v2/events/user/{caretakerId}")
    Call<List<Activity>> getCalendarEvents(@Header("X-Auth-Token") String str, @Path("caretakerId") String str2, @Query("start") String str3, @Query("end") String str4);

    @POST("v2/events/guardian/changeEvent")
    @Headers({"Content-Type: application/json"})
    Call<Void> postEventChangeRequest(@Header("X-Auth-Token") String str, @Body ChangeEvent changeEvent);
}
