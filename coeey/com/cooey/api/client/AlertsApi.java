package com.cooey.api.client;

import com.cooey.api.client.models.Alert;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface AlertsApi {
    @GET("v2/alerts/{alertId}")
    Call<Alert> getAlert(@Path("alertId") String str, @Header("X-Auth-Token") String str2);

    @GET("v2/alerts/notifier/{notifierId}")
    Call<List<Alert>> getAlertForNotifier(@Path("notifierId") String str, @Header("X-Auth-Token") String str2);

    @GET("v2/alerts/tenant/{tenantId}")
    Call<List<Alert>> getAlerts(@Path("tenantId") String str, @Header("X-Auth-Token") String str2);

    @PUT("v2/alerts")
    @Headers({"Content-Type:application/json"})
    Call<Alert> updateAlert(@Header("X-Auth-Token") String str, @Body Alert alert);
}
