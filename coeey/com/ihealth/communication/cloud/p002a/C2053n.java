package com.ihealth.communication.cloud.p002a;

import java.security.cert.X509Certificate;
import javax.net.ssl.X509TrustManager;

final class C2053n implements X509TrustManager {
    C2053n() {
    }

    public void checkClientTrusted(X509Certificate[] chain, String authType) {
    }

    public void checkServerTrusted(X509Certificate[] chain, String authType) {
    }

    public X509Certificate[] getAcceptedIssuers() {
        return null;
    }
}
