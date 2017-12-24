package com.cooey.common.services;

import com.cooey.common.vo.Action;
import com.cooey.common.vo.CareplanSummary;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface ActionSummaryService {
    @GET("v2/actions/actioncompleted/{patientId}")
    Call<List<Action>> getActionCompleted(@Header("X-Auth-Token") String str, @Path("patientId") String str2);

    @GET("v2/actions/guardian/{patientId}")
    Call<List<CareplanSummary>> getActionSummary(@Header("X-Auth-Token") String str, @Path("patientId") String str2);
}
