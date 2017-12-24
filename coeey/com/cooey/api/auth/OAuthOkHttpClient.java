package com.cooey.api.auth;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request.Builder;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.apache.oltu.oauth2.client.HttpClient;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.OAuthClientResponse;
import org.apache.oltu.oauth2.client.response.OAuthClientResponseFactory;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;

public class OAuthOkHttpClient implements HttpClient {
    private OkHttpClient client;

    public OAuthOkHttpClient() {
        this.client = new OkHttpClient();
    }

    public OAuthOkHttpClient(OkHttpClient client) {
        this.client = client;
    }

    public <T extends OAuthClientResponse> T execute(OAuthClientRequest request, Map<String, String> headers, String requestMethod, Class<T> responseClass) throws OAuthSystemException, OAuthProblemException {
        MediaType mediaType = MediaType.parse("application/json");
        Builder requestBuilder = new Builder().url(request.getLocationUri());
        if (headers != null) {
            for (Entry<String, String> entry : headers.entrySet()) {
                if (((String) entry.getKey()).equalsIgnoreCase("Content-Type")) {
                    mediaType = MediaType.parse((String) entry.getValue());
                } else {
                    requestBuilder.addHeader((String) entry.getKey(), (String) entry.getValue());
                }
            }
        }
        requestBuilder.method(requestMethod, request.getBody() != null ? RequestBody.create(mediaType, request.getBody()) : null);
        try {
            Response response = this.client.newCall(requestBuilder.build()).execute();
            return OAuthClientResponseFactory.createCustomResponse(response.body().string(), response.body().contentType().toString(), response.code(), responseClass);
        } catch (IOException e) {
            throw new OAuthSystemException(e);
        }
    }

    public void shutdown() {
    }
}
