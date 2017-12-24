package com.jakewharton.retrofit2.adapter.rxjava2;

import humanize.util.Constants;
import retrofit2.Response;

public final class HttpException extends Exception {
    private final int code;
    private final String message;
    private final transient Response<?> response;

    private static String getMessage(Response<?> response) {
        if (response != null) {
            return "HTTP " + response.code() + Constants.SPACE + response.message();
        }
        throw new NullPointerException("response == null");
    }

    public HttpException(Response<?> response) {
        super(getMessage(response));
        this.code = response.code();
        this.message = response.message();
        this.response = response;
    }

    public int code() {
        return this.code;
    }

    public String message() {
        return this.message;
    }

    public Response<?> response() {
        return this.response;
    }
}
