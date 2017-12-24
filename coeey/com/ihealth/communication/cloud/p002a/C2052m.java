package com.ihealth.communication.cloud.p002a;

import android.content.Context;
import android.content.SharedPreferences;
import com.ihealth.communication.utils.Log;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class C2052m {
    public static SSLSocketFactory m395a() {
        TrustManager[] trustManagerArr = new X509TrustManager[]{new C2053n()};
        try {
            SSLContext instance = SSLContext.getInstance("TLS");
            instance.init(new KeyManager[0], trustManagerArr, new SecureRandom());
            return instance.getSocketFactory();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return (SSLSocketFactory) SSLSocketFactory.getDefault();
        } catch (KeyManagementException e2) {
            e2.printStackTrace();
            return (SSLSocketFactory) SSLSocketFactory.getDefault();
        }
    }

    public static SSLSocketFactory m396a(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("IHCertificateFileInfo", 0);
        String string = sharedPreferences.getString("cert_path", "idscertificate.p12");
        String string2 = sharedPreferences.getString("cert_password", "ELPWfWdA");
        try {
            KeyStore instance = KeyStore.getInstance("PKCS12");
            instance.load(new FileInputStream(string), string2.toCharArray());
            SSLContext instance2 = SSLContext.getInstance("TLS");
            KeyManagerFactory instance3 = KeyManagerFactory.getInstance("X509");
            instance3.init(instance, string2.toCharArray());
            instance2.init(instance3.getKeyManagers(), null, null);
            return instance2.getSocketFactory();
        } catch (FileNotFoundException e) {
            Log.i("SSLCustomSocketFactory", "fnf" + e.getMessage());
            return C2052m.m395a();
        } catch (Throwable th) {
            Log.i("SSLCustomSocketFactory", "throwable" + th.getMessage());
            return (SSLSocketFactory) SSLSocketFactory.getDefault();
        }
    }
}
