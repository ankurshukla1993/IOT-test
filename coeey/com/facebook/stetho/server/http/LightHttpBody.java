package com.facebook.stetho.server.http;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

public abstract class LightHttpBody {
    public abstract int contentLength();

    public abstract String contentType();

    public abstract void writeTo(OutputStream outputStream) throws IOException;

    public static LightHttpBody create(String body, String contentType) {
        try {
            return create(body.getBytes("UTF-8"), contentType);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public static LightHttpBody create(final byte[] body, final String contentType) {
        return new LightHttpBody() {
            public String contentType() {
                return contentType;
            }

            public int contentLength() {
                return body.length;
            }

            public void writeTo(OutputStream output) throws IOException {
                output.write(body);
            }
        };
    }
}
