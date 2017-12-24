package com.cooey.api.client;

import com.cooey.api.client.models.Product;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ProductsApi {
    @POST("v2/products")
    Call<Product> create(@Header("X-Auth-Token") String str, @Body Product product);

    @GET("v2/products/{productId}")
    Call<Product> getProductById(@Path("productId") String str, @Header("X-Auth-Token") String str2);

    @GET("v2/products")
    Call<List<Product>> getTenants(@Header("X-Auth-Token") String str);

    @GET("v2/products/search")
    Call<List<Product>> searchProduct(@Header("X-Auth-Token") String str, @Query("query") String str2, @Query("authKey") String str3);

    @PUT("v2/products")
    Call<Product> update(@Header("X-Auth-Token") String str, @Body Product product);
}
