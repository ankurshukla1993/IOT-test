package com.cooey.common.services;

import com.cooey.common.vo.Assignment;
import com.cooey.common.vo.CareplanBlueprint;
import com.cooey.common.vo.CareplanSummary;
import com.cooey.common.vo.InterventionBlueprint;
import com.cooey.common.vo.careplan.Careplan;
import com.cooey.common.vo.careplan.Intervention;
import io.reactivex.Observable;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface CareplanService {
    @POST("v2/careplans/assignements")
    Call<Assignment> assignCareplanBluePrint(@Header("X-Auth-Token") String str, @Body Assignment assignment);

    @GET("v2/careplans/patient/{patientId}")
    @Headers({"Accept:application/json"})
    Call<Careplan> getCareplan(@Header("X-Auth-Token") String str, @Path("patientId") String str2);

    @GET("v2/careplans/patient/{patientId}")
    Observable<Careplan> getCareplanForPatient(@Header("X-Auth-Token") String str, @Path("patientId") String str2);

    @GET("v2/careplans/{careplanId}/interventions")
    @Headers({"Accept:application/json"})
    Call<List<Intervention>> getCareplanInterventions(@Header("X-Auth-Token") String str, @Path("careplanId") String str2);

    @GET("v2/careplans/stats/patients/{patientId}")
    @Headers({"Accept:application/json"})
    Call<List<CareplanSummary>> getCareplanSummary(@Header("X-Auth-Token") String str, @Path("patientId") String str2);

    @GET("v2/careplans/patient/{patientId}")
    @Headers({"Accept:application/json"})
    Observable<Careplan> getCareplanWithObservable(@Header("X-Auth-Token") String str, @Path("patientId") String str2);

    @GET("v2/careplans/blueprints/caretaker/{caretakerId}")
    Call<List<CareplanBlueprint>> getCareplansList(@Header("X-Auth-Token") String str, @Path("caretakerId") String str2);

    @GET("v2/careplans/{careplanId}/interventions")
    Observable<List<Intervention>> getInterventionForPatient(@Header("X-Auth-Token") String str, @Path("careplanId") String str2);

    @GET("v2/careplans/intervention-blueprints/{careplanBlueprintId}")
    Call<List<InterventionBlueprint>> getInterventionFromCareplanBlueprint(@Header("X-Auth-Token") String str, @Path("careplanBlueprintId") String str2);
}
