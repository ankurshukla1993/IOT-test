package com.cooey.api.auth;

import java.io.IOException;
import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.Interceptor.Chain;
import okhttp3.Request;
import okhttp3.Response;

public class HttpBasicAuth implements Interceptor {
    private String password;
    private String username;

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCredentials(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (request.header("Authorization") == null) {
            request = request.newBuilder().addHeader("Authorization", Credentials.basic(this.username, this.password)).build();
        }
        return chain.proceed(request);
    }
}
