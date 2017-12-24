package com.cooey.api.client;

import com.cooey.api.client.models.BatteryStatusAlertMessage;
import com.cooey.api.client.models.DevicePowerAlertMessage;
import com.cooey.api.client.models.EmergencyAlertMessage;
import com.cooey.api.client.models.GeofencingAlertMessage;
import com.cooey.api.client.models.HazardousMovementAlertMessage;
import com.cooey.api.client.models.LowMovementAlertMessage;
import com.cooey.api.client.models.ShockAlertMessage;
import com.cooey.api.client.models.TemperatureAlertMessage;
import com.cooey.api.client.models.TrackingAlertMessage;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface DeviceEventsApi {
    @GET("v2/devices/{patientId}/alert/emergency")
    Call<List<EmergencyAlertMessage>> getEmergencyAlerts(@Path("patientId") String str, @Header("X-Auth-Token") String str2);

    @GET("v2/devices/{patientId}/battery/fullcharge")
    Call<List<BatteryStatusAlertMessage>> getFullBatteryReadings(@Path("patientId") String str, @Header("X-Auth-Token") String str2);

    @GET("v2/devices/{patientId}/alert/geofence")
    Call<List<GeofencingAlertMessage>> getGeoFenceAlerts(@Path("patientId") String str, @Header("X-Auth-Token") String str2);

    @GET("v2/devices/{patientId}/alert/hazard")
    Call<List<HazardousMovementAlertMessage>> getHazardousAlerts(@Path("patientId") String str, @Header("X-Auth-Token") String str2);

    @GET("v2/devices/{patientId}/battery/lowcharge")
    Call<List<BatteryStatusAlertMessage>> getLowBatteryReadings(@Path("patientId") String str, @Header("X-Auth-Token") String str2);

    @GET("v2/devices/{patientId}/alert/lowmovement")
    Call<List<LowMovementAlertMessage>> getLowMovementAlerts(@Path("patientId") String str, @Header("X-Auth-Token") String str2);

    @GET("v2/devices/{patientId}/power/off")
    Call<List<DevicePowerAlertMessage>> getPowerOffStatus(@Path("patientId") String str, @Header("X-Auth-Token") String str2);

    @GET("v2/devices/{patientId}/power/on")
    Call<List<DevicePowerAlertMessage>> getPowerOnStatus(@Path("patientId") String str, @Header("X-Auth-Token") String str2);

    @GET("v2/devices/{patientId}/alert/shock")
    Call<List<ShockAlertMessage>> getShockAlerts(@Path("patientId") String str, @Header("X-Auth-Token") String str2);

    @GET("v2/devices/{patientId}/alert/temperature")
    Call<List<TemperatureAlertMessage>> getTemperatureAlerts(@Path("patientId") String str, @Header("X-Auth-Token") String str2);

    @GET("v2/devices/{patientId}/alert/tracking")
    Call<List<TrackingAlertMessage>> getTrackingAlerts(@Path("patientId") String str, @Header("X-Auth-Token") String str2);
}
