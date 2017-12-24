package com.squareup.picasso;

public interface Picasso$RequestTransformer {
    public static final Picasso$RequestTransformer IDENTITY = new C23191();

    static class C23191 implements Picasso$RequestTransformer {
        C23191() {
        }

        public Request transformRequest(Request request) {
            return request;
        }
    }

    Request transformRequest(Request request);
}
