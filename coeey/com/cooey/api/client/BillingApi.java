package com.cooey.api.client;

import com.cooey.api.client.models.Billing;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface BillingApi {
    @POST("v2/billing")
    Call<Billing> createInvoice(@Header("X-Auth-Token") String str, @Body Billing billing);

    @GET("v2/billing/{billId}")
    @Headers({"Content-Type:application/json"})
    Call<Void> generateInvoice(@Path("billId") String str);

    @GET("v2/billing")
    Call<List<Billing>> getAll(@Header("X-Auth-Token") String str);

    @PUT("v2/billing")
    Call<Billing> updateInvoice(@Header("X-Auth-Token") String str, @Body Billing billing);
}
