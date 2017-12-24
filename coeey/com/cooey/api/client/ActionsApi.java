package com.cooey.api.client;

import com.cooey.api.client.models.ActionItem;
import com.cooey.api.client.models.SummaryResponse;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ActionsApi {
    @GET("v2/actions/caretakers/{caretakerId}")
    Call<List<ActionItem>> getActionsForCaretaker(@Path("caretakerId") String str, @Header("X-Auth-Token") String str2, @Query("type") String str3, @Query("beginTime") Long l, @Query("endTime") Long l2);

    @GET("v2/actions/patients/{patientId}")
    Call<List<ActionItem>> getActionsForPatient(@Path("patientId") String str, @Header("X-Auth-Token") String str2, @Query("type") String str3, @Query("beginTime") Long l, @Query("endTime") Long l2);

    @GET("v2/actions/actionLogs/{caretakerId}")
    Call<List<ActionItem>> getCompletedActionItems(@Path("caretakerId") String str, @Header("X-Auth-Token") String str2);

    @GET("v2/actions/actioncompleted/{patientId}")
    Call<List<ActionItem>> getCompletedActionItemsCare(@Path("patientId") String str, @Header("X-Auth-Token") String str2);

    @GET("v2/actions/guardian/{patientId}")
    Call<List<SummaryResponse>> getSummaryForPatient(@Path("patientId") String str, @Header("X-Auth-Token") String str2);

    @PUT("v2/actions")
    Call<ActionItem> updateActionItem(@Body ActionItem actionItem);
}
