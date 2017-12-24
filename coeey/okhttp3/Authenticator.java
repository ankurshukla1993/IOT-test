package okhttp3;

import java.io.IOException;

public interface Authenticator {
    public static final Authenticator NONE = new C24701();

    static class C24701 implements Authenticator {
        C24701() {
        }

        public Request authenticate(Route route, Response response) {
            return null;
        }
    }

    Request authenticate(Route route, Response response) throws IOException;
}
