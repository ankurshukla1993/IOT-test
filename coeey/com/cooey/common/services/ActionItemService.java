package com.cooey.common.services;

import com.cooey.common.vo.ActionItem;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ActionItemService {
    @GET("v2/actions")
    @Headers({"Accept: application/json"})
    Call<List<ActionItem>> getActionItems();

    @GET("v2/actions/caretakers/{caretakerId}")
    @Headers({"Accept: application/json"})
    Call<List<ActionItem>> getActionItemsForCareTaker(@Header("X-Auth-Token") String str, @Path("caretakerId") String str2, @Query("beginTime") long j, @Query("endTime") long j2);

    @GET("v2/actions/patients/{patientId}")
    @Headers({"Accept: application/json"})
    Call<List<ActionItem>> getActionItemsForPatients(@Header("X-Auth-Token") String str, @Path("patientId") String str2);

    @GET("v2/actions/patients/{patientId}")
    @Headers({"Accept: application/json"})
    Call<List<ActionItem>> getActionItemsForPatients(@Header("X-Auth-Token") String str, @Path("patientId") String str2, @Query("beginTime") long j, @Query("endTime") long j2);

    @PUT("v2/actions")
    @Headers({"Accept: application/json"})
    Call<ActionItem> isCompletedActionItem(@Body ActionItem actionItem);
}
