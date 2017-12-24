package com.cooey.android.users.old.retrofit;

import com.cooey.common.config.PartnerConfig;
import com.cooey.common.config.VitalConfigResponse;
import com.cooey.common.vo.Activity;
import com.cooey.common.vo.CallOutDevice;
import com.cooey.common.vo.Channel;
import com.cooey.common.vo.DietTemplate;
import com.cooey.common.vo.Guardian;
import com.cooey.common.vo.MedicalContact;
import com.cooey.common.vo.Medicine;
import com.cooey.common.vo.MedicineSearch;
import com.cooey.common.vo.User;
import com.cooey.common.vo.Vital;
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

public interface MediaService {
    @POST("v2/medicines")
    @Headers({"Accept: application/json"})
    Call<Medicine> addMedicineReminder(@Header("X-Auth-Token") String str, @Body Medicine medicine);

    @POST("v2/vitals")
    @Headers({"Accept: application/json"})
    Call<Vital> addVitalForPatient(@Header("X-Auth-Token") String str, @Body Vital vital);

    Call<DietTemplate> assignDietTemplateToPatients(@Body DietTemplate dietTemplate);

    @POST("v2/channels")
    @Headers({"Accept: application/json"})
    Call<Channel> createChannel(@Body Channel channel);

    @POST("v2/users")
    @Headers({"Accept: application/json"})
    Call<User> createPatientProfile(@Header("X-Auth-Token") String str, @Body User user);

    @GET("v2/events/caretakers/{caretakerId}/latest")
    @Headers({"Accept: application/json"})
    Call<List<Activity>> getActivities(@Path("caretakerId") String str);

    @GET("v2/devices/{patientId}/alert/emergency")
    @Headers({"Accept: application/json"})
    Call<List<CallOutDevice>> getDeviceDetails(@Header("X-Auth-Token") String str, @Path("patientId") String str2);

    @GET("v2/diet/templaltes/caretakers/{caretakerId}")
    @Headers({"Accept: application/json"})
    Call<List<DietTemplate>> getDietTemplate(@Header("X-Auth-Token") String str, @Path("caretakerId") String str2);

    @GET("v2/events/{userId}")
    @Headers({"Accept: application/json"})
    Call<List<Activity>> getEventsForPatient(@Header("X-Auth-Token") String str, @Path("userId") String str2);

    @GET("v2/users/{userId}/guardians")
    @Headers({"Accept: application/json"})
    Call<List<Guardian>> getGuardiansForPatient(@Header("X-Auth-Token") String str, @Path("userId") String str2, @Query("limit") int i, @Query("offset") int i2);

    @GET("v2/vitals/users/vitals")
    @Headers({"Accept: application/json"})
    Call<List<Vital>> getLastVitalsForUser(@Header("x-auth-token") String str, @Query("limit") int i, @Query("careTakerId") String str2);

    @GET("v2/medicines")
    @Headers({"Accept: application/json"})
    Call<List<Medicine>> getMedicineReminderForPatient(@Header("X-Auth-Token") String str, @Query("userId") String str2);

    @GET("v2/users/{userId}/contacts")
    @Headers({"Accept: application/json"})
    Call<List<MedicalContact>> getMeicalContactsForUser(@Header("X-Auth-Token") String str, @Path("userId") String str2, @Query("type") String str3);

    @GET("v2/users/{emailId}/recovery")
    @Headers({"Accept: application/json"})
    Call<User> getPasswordForUser(@Path("emailId") String str);

    @GET("v2/tenants/settings")
    @Headers({"Accept: application/json"})
    Call<PartnerConfig> getPatnerConfig(@Header("X-Auth-Token") String str);

    @GET("v2/users/{userId}")
    @Headers({"Accept: application/json"})
    Call<User> getUserByUserId(@Header("X-Auth-Token") String str, @Path("userId") String str2);

    @GET("v2/users")
    @Headers({"Accept: application/json"})
    Call<List<User>> getUsersForCareTaker(@Header("X-Auth-Token") String str, @Query("offset") int i, @Query("limit") int i2, @Query("caretakerId") String str2);

    @GET("v2/tenants/{tenantId}/configuration/ANDROID")
    @Headers({"Content-Type: application/json"})
    Call<VitalConfigResponse> getVitalConfigResponse(@Header("X-Auth-Token") String str, @Path("tenantId") String str2, @Query("appType") String str3);

    @GET("v2/vitals/{vitalId}")
    @Headers({"Accept: application/json"})
    Call<Vital> getVitalFromVitalId(@Path("vitalId") String str);

    @GET("v2/users/{userId}")
    Call<Vital> getVitals(@Header("X-Auth-Token") String str, @Path("userId") String str2);

    @GET("v2/vitals")
    @Headers({"Accept: application/json"})
    Call<List<Vital>> getVitalsForPatient(@Header("X-Auth-Token") String str, @Query("offset") int i, @Query("value") int i2, @Query("userId") String str2);

    @GET("v2/users/{userId}/wards")
    @Headers({"Accept: application/json"})
    Call<List<User>> getWardsForCaretaker(@Header("X-Auth-Token") String str, @Path("userId") String str2);

    @GET("v2/medicines/search")
    @Headers({"Accept: application/json"})
    Call<List<MedicineSearch>> medicineSearch(@Header("X-Auth-Token") String str, @Query("query") String str2);

    @PUT("v2/users")
    @Headers({"Accept: application/json"})
    Call<User> updateUser(@Body User user);

    @PUT("v2/users")
    @Headers({"Accept: application/json"})
    Call<User> updateVitalLimits(@Header("X-Auth-Token") String str, @Body User user);

    @GET("v2/users/search")
    @Headers({"Accept: application/json"})
    Call<List<User>> usersList(@Header("X-Auth-Token") String str, @Query("query") String str2, @Query("type") String str3, @Query("caretakerId") String str4);
}
