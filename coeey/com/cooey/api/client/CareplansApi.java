package com.cooey.api.client;

import com.cooey.api.client.models.AssignCareplanRequest;
import com.cooey.api.client.models.Careplan;
import com.cooey.api.client.models.CareplanBlueprint;
import com.cooey.api.client.models.Intervention;
import com.cooey.api.client.models.InterventionBlueprint;
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

public interface CareplansApi {
    @POST("v2/careplans/assignements")
    @Headers({"Content-Type:application/json"})
    Call<Careplan> assignCareplan(@Header("X-Auth-Token") String str, @Body AssignCareplanRequest assignCareplanRequest);

    @POST("v2/careplans/blueprints")
    @Headers({"Content-Type:application/json"})
    Call<CareplanBlueprint> createCareplan(@Header("X-Auth-Token") String str, @Body CareplanBlueprint careplanBlueprint);

    @POST("v2/careplans/intervention-blueprints")
    @Headers({"Content-Type:application/json"})
    Call<InterventionBlueprint> createInterventionBlueprint(@Header("X-Auth-Token") String str, @Body InterventionBlueprint interventionBlueprint);

    @GET("v2/careplans/blueprints/{careplanBlueprintId}")
    @Headers({"Content-Type:application/json"})
    Call<CareplanBlueprint> getCareplanBlueprint(@Path("careplanBlueprintId") String str, @Header("X-Auth-Token") String str2);

    @GET("v2/careplans/patient/{patientId}")
    @Headers({"Content-Type:application/json"})
    Call<Careplan> getCareplanForPatient(@Path("patientId") String str, @Header("X-Auth-Token") String str2);

    @GET("v2/careplans/blueprints")
    @Headers({"Content-Type:application/json"})
    Call<Void> getCareplans(@Header("X-Auth-Token") String str, @Query("tenantId") String str2, @Query("offset") Integer num, @Query("limit") Integer num2);

    @GET("v2/careplans/blueprints/caretaker/{caretakerId}")
    @Headers({"Content-Type:application/json"})
    Call<List<CareplanBlueprint>> getCareplansForCaretaker(@Path("caretakerId") String str, @Header("X-Auth-Token") String str2);

    @GET("v2/careplans/intervention-blueprints")
    @Headers({"Content-Type:application/json"})
    Call<List<InterventionBlueprint>> getInterventionBlueprints(@Header("X-Auth-Token") String str);

    @GET("v2/careplans/intervention-blueprints/{careplanBlueprintId}")
    @Headers({"Content-Type:application/json"})
    Call<List<InterventionBlueprint>> getInterventionBlueprintsForCareplanBlueprint(@Path("careplanBlueprintId") String str, @Header("X-Auth-Token") String str2);

    @GET("v2/careplans/{careplanId}/interventions")
    @Headers({"Content-Type:application/json"})
    Call<List<Intervention>> getInterventionsForCareplan(@Path("careplanId") String str, @Header("X-Auth-Token") String str2);

    @PUT("v2/careplans/blueprints")
    @Headers({"Content-Type:application/json"})
    Call<CareplanBlueprint> updateCareplanBlueprint(@Header("X-Auth-Token") String str, @Body CareplanBlueprint careplanBlueprint);

    @PUT("v2/careplans/careplan")
    @Headers({"Content-Type:application/json"})
    Call<Careplan> updateIntervention(@Header("X-Auth-Token") String str, @Body Careplan careplan);

    @PUT("v2/careplans/intervention-blueprints")
    @Headers({"Content-Type:application/json"})
    Call<InterventionBlueprint> updateInterventionBlueprint(@Header("X-Auth-Token") String str, @Body InterventionBlueprint interventionBlueprint);
}
