package com.cooey.api.auth;

import java.io.IOException;
import java.util.Map.Entry;
import okhttp3.Interceptor;
import okhttp3.Interceptor.Chain;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.Response;
import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.request.OAuthBearerClientRequest;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest.AuthenticationRequestBuilder;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest.TokenRequestBuilder;
import org.apache.oltu.oauth2.client.response.OAuthJSONAccessTokenResponse;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.types.GrantType;
import org.apache.oltu.oauth2.common.token.BasicOAuthToken;

public class OAuth implements Interceptor {
    private volatile String accessToken;
    private AccessTokenListener accessTokenListener;
    private AuthenticationRequestBuilder authenticationRequestBuilder;
    private OAuthClient oauthClient;
    private TokenRequestBuilder tokenRequestBuilder;

    public interface AccessTokenListener {
        void notify(BasicOAuthToken basicOAuthToken);
    }

    public OAuth(OkHttpClient client, TokenRequestBuilder requestBuilder) {
        this.oauthClient = new OAuthClient(new OAuthOkHttpClient(client));
        this.tokenRequestBuilder = requestBuilder;
    }

    public OAuth(TokenRequestBuilder requestBuilder) {
        this(new OkHttpClient(), requestBuilder);
    }

    public OAuth(OAuthFlow flow, String authorizationUrl, String tokenUrl, String scopes) {
        this(OAuthClientRequest.tokenLocation(tokenUrl).setScope(scopes));
        setFlow(flow);
        this.authenticationRequestBuilder = OAuthClientRequest.authorizationLocation(authorizationUrl);
    }

    public void setFlow(OAuthFlow flow) {
        switch (flow) {
            case accessCode:
            case implicit:
                this.tokenRequestBuilder.setGrantType(GrantType.AUTHORIZATION_CODE);
                return;
            case password:
                this.tokenRequestBuilder.setGrantType(GrantType.PASSWORD);
                return;
            case application:
                this.tokenRequestBuilder.setGrantType(GrantType.CLIENT_CREDENTIALS);
                return;
            default:
                return;
        }
    }

    public Response intercept(Chain chain) throws IOException {
        int i = 1;
        Request request = chain.request();
        if (request.header("Authorization") != null) {
            return chain.proceed(request);
        }
        if (getAccessToken() == null) {
            updateAccessToken(null);
        }
        if (getAccessToken() == null) {
            return chain.proceed(chain.request());
        }
        Builder rb = request.newBuilder();
        String requestAccessToken = new String(getAccessToken());
        try {
            OAuthClientRequest oAuthRequest = new OAuthBearerClientRequest(request.url().toString()).setAccessToken(requestAccessToken).buildHeaderMessage();
            for (Entry<String, String> header : oAuthRequest.getHeaders().entrySet()) {
                rb.addHeader((String) header.getKey(), (String) header.getValue());
            }
            rb.url(oAuthRequest.getLocationUri());
            Response response = chain.proceed(rb.build());
            if (response == null) {
                return response;
            }
            int i2;
            if (response.code() == 401) {
                i2 = 1;
            } else {
                i2 = 0;
            }
            if (response.code() != 403) {
                i = 0;
            }
            if ((i2 | i) == 0 || !updateAccessToken(requestAccessToken)) {
                return response;
            }
            return intercept(chain);
        } catch (OAuthSystemException e) {
            throw new IOException(e);
        }
    }

    public synchronized boolean updateAccessToken(String requestAccessToken) throws IOException {
        boolean z = true;
        synchronized (this) {
            if (getAccessToken() == null || getAccessToken().equals(requestAccessToken)) {
                try {
                    OAuthJSONAccessTokenResponse accessTokenResponse = this.oauthClient.accessToken(this.tokenRequestBuilder.buildBodyMessage());
                    if (accessTokenResponse == null || accessTokenResponse.getAccessToken() == null) {
                        z = false;
                    } else {
                        boolean z2;
                        setAccessToken(accessTokenResponse.getAccessToken());
                        if (this.accessTokenListener != null) {
                            this.accessTokenListener.notify((BasicOAuthToken) accessTokenResponse.getOAuthToken());
                        }
                        if (getAccessToken().equals(requestAccessToken)) {
                            z2 = false;
                        } else {
                            z2 = true;
                        }
                        z = z2;
                    }
                } catch (OAuthSystemException e) {
                    throw new IOException(e);
                } catch (OAuthProblemException e2) {
                    throw new IOException(e2);
                }
            }
        }
        return z;
    }

    public void registerAccessTokenListener(AccessTokenListener accessTokenListener) {
        this.accessTokenListener = accessTokenListener;
    }

    public synchronized String getAccessToken() {
        return this.accessToken;
    }

    public synchronized void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public TokenRequestBuilder getTokenRequestBuilder() {
        return this.tokenRequestBuilder;
    }

    public void setTokenRequestBuilder(TokenRequestBuilder tokenRequestBuilder) {
        this.tokenRequestBuilder = tokenRequestBuilder;
    }

    public AuthenticationRequestBuilder getAuthenticationRequestBuilder() {
        return this.authenticationRequestBuilder;
    }

    public void setAuthenticationRequestBuilder(AuthenticationRequestBuilder authenticationRequestBuilder) {
        this.authenticationRequestBuilder = authenticationRequestBuilder;
    }
}
