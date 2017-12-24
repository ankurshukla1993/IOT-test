package com.facebook.react.modules.network;

import android.os.Build.VERSION;
import com.facebook.common.logging.FLog;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import okhttp3.ConnectionSpec;
import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
import okhttp3.TlsVersion;

public class OkHttpClientProvider {
    @Nullable
    private static OkHttpClient sClient;

    public static OkHttpClient getOkHttpClient() {
        if (sClient == null) {
            sClient = createClient();
        }
        return sClient;
    }

    public static void replaceOkHttpClient(OkHttpClient client) {
        sClient = client;
    }

    private static OkHttpClient createClient() {
        return enableTls12OnPreLollipop(new Builder().connectTimeout(0, TimeUnit.MILLISECONDS).readTimeout(0, TimeUnit.MILLISECONDS).writeTimeout(0, TimeUnit.MILLISECONDS).cookieJar(new ReactCookieJarContainer())).build();
    }

    public static Builder enableTls12OnPreLollipop(Builder client) {
        if (VERSION.SDK_INT >= 16 && VERSION.SDK_INT <= 19) {
            try {
                client.sslSocketFactory(new TLSSocketFactory());
                ConnectionSpec cs = new ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS).tlsVersions(new TlsVersion[]{TlsVersion.TLS_1_2}).build();
                List<ConnectionSpec> specs = new ArrayList();
                specs.add(cs);
                specs.add(ConnectionSpec.COMPATIBLE_TLS);
                specs.add(ConnectionSpec.CLEARTEXT);
                client.connectionSpecs(specs);
            } catch (Exception exc) {
                FLog.e("OkHttpClientProvider", "Error while enabling TLS 1.2", exc);
            }
        }
        return client;
    }
}
