package com.cooey.common.generators;

import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient.Builder;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ServiceGenerator implements IServiceGenerator {
    private final Retrofit retrofit;

    public ServiceGenerator(String rootAPIUrl) {
        Builder client = new Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(Level.BODY);
        client.addInterceptor(loggingInterceptor);
        this.retrofit = new Retrofit.Builder().baseUrl(rootAPIUrl).addConverterFactory(ScalarsConverterFactory.create()).addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create())).client(client.build()).build();
    }

    public <T> T create(Class<T> service) {
        return this.retrofit.create(service);
    }
}
