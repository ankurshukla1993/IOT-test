package com.cooey.api.client;

import com.cooey.api.client.models.Artifact;
import com.cooey.api.client.models.DeviceAlertSettings;
import com.cooey.api.client.models.Role;
import com.cooey.api.client.models.Shift;
import com.cooey.api.client.models.Tax;
import com.cooey.api.client.models.Tenant;
import com.cooey.api.client.models.TenantConfig;
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

public interface TenantsApi {
    @POST("v2/tenants")
    Call<Tenant> create(@Body Tenant tenant);

    @POST("v2/tenants/{tenantId}/artifacts")
    Call<Artifact> createArtifact(@Path("tenantId") String str, @Header("X-Auth-Token") String str2, @Body Artifact artifact);

    @POST("v2/tenants/{tenantId}/shifts")
    Call<Shift> createShift(@Path("tenantId") String str, @Header("X-Auth-Token") String str2, @Body Shift shift);

    @POST("v2/tenants/{tenantId}/taxes")
    Call<Tax> createTax(@Path("tenantId") String str, @Header("X-Auth-Token") String str2, @Body Tax tax);

    @POST("v2/tenants/roles")
    Call<Role> create_0(@Header("X-Auth-Token") String str, @Body Role role);

    @DELETE("v2/tenants/{tenantId}/shifts/{shiftId}")
    Call<Void> deleteShift(@Path("tenantId") String str, @Path("shiftId") String str2);

    @GET("v2/tenants/{tenantId}/taxes")
    Call<List<Tax>> getAllTax(@Path("tenantId") String str, @Header("X-Auth-Token") String str2);

    @GET("v2/tenants/{tenantId}/configuration/{platForm}")
    @Headers({"Content-Type:application/json"})
    Call<Void> getAppSettings(@Path("tenantId") String str, @Path("platForm") String str2, @Header("X-Auth-Token") String str3, @Query("appType") String str4);

    @GET("v2/tenants/{tenantId}/artifacts/{artifactId}")
    Call<Artifact> getArtifact(@Path("tenantId") String str, @Path("artifactId") String str2, @Header("X-Auth-Token") String str3);

    @GET("v2/tenants/devices/{tenantId}")
    @Headers({"Content-Type:application/json"})
    Call<DeviceAlertSettings> getDeviceSettings(@Path("tenantId") String str, @Header("X-Auth-Token") String str2);

    @GET("v2/tenants/roles")
    Call<List<Role>> getRoles(@Header("X-Auth-Token") String str);

    @GET("v2/tenants/roles/{roleId}")
    Call<Role> getRolesById(@Path("roleId") String str, @Header("X-Auth-Token") String str2);

    @GET("v2/tenants/settings")
    @Headers({"Content-Type:application/json"})
    Call<TenantConfig> getSettings(@Header("X-Auth-Token") String str);

    @GET("v2/tenants/{tenantName}/settings")
    @Headers({"Content-Type:application/json"})
    Call<TenantConfig> getSettings_0(@Path("tenantName") String str, @Header("X-Auth-Token") String str2);

    @GET("v2/tenants/{tenantId}/shifts")
    Call<List<Shift>> getShiftsForTenant(@Path("tenantId") String str, @Header("X-Auth-Token") String str2);

    @GET("v2/tenants/{tenantId}/taxes/{taxId}")
    Call<Tax> getTaxById(@Path("tenantId") String str, @Path("taxId") String str2, @Header("X-Auth-Token") String str3);

    @GET("v2/tenants/{tenantId}/artifacts")
    Call<List<Artifact>> getTenantArtifacts(@Path("tenantId") String str, @Header("X-Auth-Token") String str2);

    @PUT("v2/tenants/{tenantName}/settings")
    @Headers({"Content-Type:application/json"})
    Call<TenantConfig> getTenantConfig(@Path("tenantName") String str, @Header("X-Auth-Token") String str2, @Body TenantConfig tenantConfig);

    @GET("v2/tenants")
    Call<List<Tenant>> getTenants(@Header("X-Auth-Token") String str);

    @POST("v2/tenants/devices/{tenantId}")
    @Headers({"Content-Type:application/json"})
    Call<Boolean> postDeviceSettings(@Path("tenantId") String str, @Header("X-Auth-Token") String str2, @Body DeviceAlertSettings deviceAlertSettings);

    @POST("v2/tenants/settings")
    @Headers({"Content-Type:application/json"})
    Call<TenantConfig> setSettings(@Header("X-Auth-Token") String str, @Body TenantConfig tenantConfig);

    @PUT("v2/tenants/roles")
    Call<Role> update(@Header("X-Auth-Token") String str, @Body Role role);

    @PUT("v2/tenants/settings")
    @Headers({"Content-Type:application/json"})
    Call<TenantConfig> updateSettings(@Header("X-Auth-Token") String str, @Body TenantConfig tenantConfig);

    @PUT("v2/tenants/{tenantId}/taxes")
    Call<Tax> updateTax(@Path("tenantId") String str, @Header("X-Auth-Token") String str2, @Body Tax tax);
}
