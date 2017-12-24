package com.cooey.api.client;

import com.cooey.api.client.models.Device;
import com.cooey.api.client.models.DeviceAlertSettings;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface DevicesApi {
    @POST("v2/users/devices/settings")
    @Headers({"Content-Type:application/json"})
    Call<DeviceAlertSettings> createSettings(@Header("X-Auth-Token") String str, @Body DeviceAlertSettings deviceAlertSettings);

    @GET("v2/users/devices")
    @Headers({"Content-Type:application/json"})
    Call<Void> get(@Header("X-Auth-Token") String str, @Query("limit") Integer num, @Query("offset") Integer num2);

    @GET("v2/users/devices/{deviceId}")
    @Headers({"Content-Type:application/json"})
    Call<Device> getByDeviceId(@Path("deviceId") String str, @Header("X-Auth-Token") String str2);

    @GET("v2/users/devices/user/{patientId}")
    @Headers({"Content-Type:application/json"})
    Call<Device> getByPatientId(@Path("patientId") String str, @Header("X-Auth-Token") String str2);

    @GET("v2/users/devices/settings")
    @Headers({"Content-Type:application/json"})
    Call<DeviceAlertSettings> getSettings(@Header("X-Auth-Token") String str, @Query("patientId") String str2);

    @GET("v2/users/devices/unique/{deviceId}/{mobileNumber}")
    @Headers({"Content-Type:application/json"})
    Call<Device> getUniqueDeviceId(@Path("deviceId") String str, @Path("mobileNumber") String str2, @Header("X-Auth-Token") String str3);

    @POST("v2/users/devices")
    @Headers({"Content-Type:application/json"})
    Call<Device> post(@Header("X-Auth-Token") String str, @Body Device device);

    @PUT("v2/users/devices")
    @Headers({"Content-Type:application/json"})
    Call<Device> update(@Header("X-Auth-Token") String str, @Body Device device);

    @PUT("v2/users/devices/settings")
    @Headers({"Content-Type:application/json"})
    Call<DeviceAlertSettings> updateSettings(@Header("X-Auth-Token") String str, @Body DeviceAlertSettings deviceAlertSettings);
}
