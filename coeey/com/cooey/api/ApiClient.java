package com.cooey.api;

import com.cooey.api.auth.ApiKeyAuth;
import com.cooey.api.auth.HttpBasicAuth;
import com.cooey.api.auth.OAuth;
import com.cooey.api.auth.OAuth.AccessTokenListener;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.LinkedHashMap;
import java.util.Map;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest.AuthenticationRequestBuilder;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest.TokenRequestBuilder;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import retrofit2.Retrofit.Builder;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ApiClient {
    private Builder adapterBuilder;
    private Map<String, Interceptor> apiAuthorizations;
    private OkHttpClient.Builder okBuilder;

    public ApiClient() {
        this.apiAuthorizations = new LinkedHashMap();
        createDefaultAdapter();
    }

    public ApiClient(String[] authNames) {
        this();
    }

    public ApiClient(String authName) {
        this(new String[]{authName});
    }

    public ApiClient(String authName, String apiKey) {
        this(authName);
        setApiKey(apiKey);
    }

    public ApiClient(String authName, String username, String password) {
        this(authName);
        setCredentials(username, password);
    }

    public ApiClient(String authName, String clientId, String secret, String username, String password) {
        this(authName);
        getTokenEndPoint().setClientId(clientId).setClientSecret(secret).setUsername(username).setPassword(password);
    }

    public void createDefaultAdapter() {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").registerTypeAdapter(DateTime.class, new DateTimeTypeAdapter()).registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter()).setLenient().create();
        this.okBuilder = new OkHttpClient.Builder();
        String baseUrl = "http://dev.cooey.co.in/ehealth";
        if (!baseUrl.endsWith("/")) {
            baseUrl = baseUrl + "/";
        }
        this.adapterBuilder = new Builder().baseUrl(baseUrl).addConverterFactory(ScalarsConverterFactory.create()).addConverterFactory(GsonCustomConverterFactory.create(gson));
    }

    public <S> S createService(Class<S> serviceClass) {
        return this.adapterBuilder.client(this.okBuilder.build()).build().create(serviceClass);
    }

    private void setApiKey(String apiKey) {
        for (Interceptor apiAuthorization : this.apiAuthorizations.values()) {
            if (apiAuthorization instanceof ApiKeyAuth) {
                ((ApiKeyAuth) apiAuthorization).setApiKey(apiKey);
                return;
            }
        }
    }

    private void setCredentials(String username, String password) {
        for (Interceptor apiAuthorization : this.apiAuthorizations.values()) {
            if (apiAuthorization instanceof HttpBasicAuth) {
                ((HttpBasicAuth) apiAuthorization).setCredentials(username, password);
                return;
            } else if (apiAuthorization instanceof OAuth) {
                ((OAuth) apiAuthorization).getTokenRequestBuilder().setUsername(username).setPassword(password);
                return;
            }
        }
    }

    public TokenRequestBuilder getTokenEndPoint() {
        for (Interceptor apiAuthorization : this.apiAuthorizations.values()) {
            if (apiAuthorization instanceof OAuth) {
                return ((OAuth) apiAuthorization).getTokenRequestBuilder();
            }
        }
        return null;
    }

    public AuthenticationRequestBuilder getAuthorizationEndPoint() {
        for (Interceptor apiAuthorization : this.apiAuthorizations.values()) {
            if (apiAuthorization instanceof OAuth) {
                return ((OAuth) apiAuthorization).getAuthenticationRequestBuilder();
            }
        }
        return null;
    }

    public void setAccessToken(String accessToken) {
        for (Interceptor apiAuthorization : this.apiAuthorizations.values()) {
            if (apiAuthorization instanceof OAuth) {
                ((OAuth) apiAuthorization).setAccessToken(accessToken);
                return;
            }
        }
    }

    public void configureAuthorizationFlow(String clientId, String clientSecret, String redirectURI) {
        for (Interceptor apiAuthorization : this.apiAuthorizations.values()) {
            if (apiAuthorization instanceof OAuth) {
                OAuth oauth = (OAuth) apiAuthorization;
                oauth.getTokenRequestBuilder().setClientId(clientId).setClientSecret(clientSecret).setRedirectURI(redirectURI);
                oauth.getAuthenticationRequestBuilder().setClientId(clientId).setRedirectURI(redirectURI);
                return;
            }
        }
    }

    public void registerAccessTokenListener(AccessTokenListener accessTokenListener) {
        for (Interceptor apiAuthorization : this.apiAuthorizations.values()) {
            if (apiAuthorization instanceof OAuth) {
                ((OAuth) apiAuthorization).registerAccessTokenListener(accessTokenListener);
                return;
            }
        }
    }

    public void addAuthorization(String authName, Interceptor authorization) {
        if (this.apiAuthorizations.containsKey(authName)) {
            throw new RuntimeException("auth name \"" + authName + "\" already in api authorizations");
        }
        this.apiAuthorizations.put(authName, authorization);
        this.okBuilder.addInterceptor(authorization);
    }

    public Map<String, Interceptor> getApiAuthorizations() {
        return this.apiAuthorizations;
    }

    public void setApiAuthorizations(Map<String, Interceptor> apiAuthorizations) {
        this.apiAuthorizations = apiAuthorizations;
    }

    public Builder getAdapterBuilder() {
        return this.adapterBuilder;
    }

    public void setAdapterBuilder(Builder adapterBuilder) {
        this.adapterBuilder = adapterBuilder;
    }

    public OkHttpClient.Builder getOkBuilder() {
        return this.okBuilder;
    }

    public void addAuthsToOkBuilder(OkHttpClient.Builder okBuilder) {
        for (Interceptor apiAuthorization : this.apiAuthorizations.values()) {
            okBuilder.addInterceptor(apiAuthorization);
        }
    }

    public void configureFromOkclient(OkHttpClient okClient) {
        this.okBuilder = okClient.newBuilder();
        addAuthsToOkBuilder(this.okBuilder);
    }
}
