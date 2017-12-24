package com.RNFetchBlob;

import android.support.v4.app.NotificationCompat;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule.RCTDeviceEventEmitter;
import io.fabric.sdk.android.services.common.CommonUtils;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;

public class RNFetchBlobUtils {

    static class C05521 implements X509TrustManager {
        C05521() {
        }

        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }

    static class C05532 implements HostnameVerifier {
        C05532() {
        }

        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    }

    public static String getMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance(CommonUtils.MD5_INSTANCE);
            md.update(input.getBytes());
            byte[] digest = md.digest();
            StringBuffer sb = new StringBuffer();
            int length = digest.length;
            for (int i = 0; i < length; i++) {
                sb.append(String.format("%02x", new Object[]{Integer.valueOf(digest[i] & 255)}));
            }
            return sb.toString();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } catch (Throwable th) {
            return null;
        }
    }

    public static void emitWarningEvent(String data) {
        WritableMap args = Arguments.createMap();
        args.putString(NotificationCompat.CATEGORY_EVENT, "warn");
        args.putString("detail", data);
        ((RCTDeviceEventEmitter) RNFetchBlob.RCTContext.getJSModule(RCTDeviceEventEmitter.class)).emit(RNFetchBlobConst.EVENT_MESSAGE, args);
    }

    public static Builder getUnsafeOkHttpClient(OkHttpClient client) {
        try {
            TrustManager[] trustAllCerts = new TrustManager[]{new C05521()};
            SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new SecureRandom());
            SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
            Builder builder = client.newBuilder();
            builder.sslSocketFactory(sslSocketFactory);
            builder.hostnameVerifier(new C05532());
            return builder;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
