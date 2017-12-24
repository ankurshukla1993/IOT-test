package com.cooey.api.auth;

import com.google.android.gms.actions.SearchIntents;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import okhttp3.Interceptor;
import okhttp3.Interceptor.Chain;
import okhttp3.Request;
import okhttp3.Response;

public class ApiKeyAuth implements Interceptor {
    private String apiKey;
    private final String location;
    private final String paramName;

    public ApiKeyAuth(String location, String paramName) {
        this.location = location;
        this.paramName = paramName;
    }

    public String getLocation() {
        return this.location;
    }

    public String getParamName() {
        return this.paramName;
    }

    public String getApiKey() {
        return this.apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (SearchIntents.EXTRA_QUERY.equals(this.location)) {
            String newQuery = request.url().uri().getQuery();
            String paramValue = this.paramName + "=" + this.apiKey;
            if (newQuery == null) {
                newQuery = paramValue;
            } else {
                newQuery = newQuery + "&" + paramValue;
            }
            try {
                request = request.newBuilder().url(new URI(request.url().uri().getScheme(), request.url().uri().getAuthority(), request.url().uri().getPath(), newQuery, request.url().uri().getFragment()).toURL()).build();
            } catch (URISyntaxException e) {
                throw new IOException(e);
            }
        } else if ("header".equals(this.location)) {
            request = request.newBuilder().addHeader(this.paramName, this.apiKey).build();
        }
        return chain.proceed(request);
    }
}
