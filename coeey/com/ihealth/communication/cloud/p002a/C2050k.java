package com.ihealth.communication.cloud.p002a;

import android.content.Context;
import com.ihealth.communication.utils.Log;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Map.Entry;
import javax.net.ssl.HttpsURLConnection;
import kotlin.text.Typography;
import org.joda.time.DateTimeConstants;

public class C2050k {
    private HttpsURLConnection f539a = null;
    private Context f540b = null;

    public C2050k(Context context) {
        this.f540b = context;
    }

    public String m382a(String str, Map map, String str2) {
        OutputStream outputStream;
        Throwable th;
        InputStream inputStream = null;
        String str3 = "";
        try {
            StringBuffer stringBuffer = new StringBuffer();
            if (!(map == null || map.isEmpty())) {
                for (Entry entry : map.entrySet()) {
                    stringBuffer.append((String) entry.getKey()).append('=');
                    stringBuffer.append(URLEncoder.encode((String) entry.getValue(), str2));
                    stringBuffer.append(Typography.amp);
                }
                stringBuffer.deleteCharAt(stringBuffer.length() - 1);
            }
            this.f539a = (HttpsURLConnection) new URL(str).openConnection();
            if (this.f539a instanceof HttpsURLConnection) {
                if (this.f540b != null) {
                    this.f539a.setSSLSocketFactory(C2052m.m396a(this.f540b));
                } else {
                    this.f539a.setSSLSocketFactory(C2052m.m395a());
                }
            }
            this.f539a.setConnectTimeout(DateTimeConstants.MILLIS_PER_MINUTE);
            this.f539a.setReadTimeout(DateTimeConstants.MILLIS_PER_MINUTE);
            this.f539a.setRequestMethod(HttpRequest.METHOD_POST);
            this.f539a.setDoInput(true);
            this.f539a.setDoOutput(true);
            str3 = stringBuffer.toString();
            Log.v("HttpsPost", "Post   " + str + " \n" + stringBuffer.toString());
            byte[] bytes = str3.getBytes();
            this.f539a.setRequestProperty("Content-Type", HttpRequest.CONTENT_TYPE_FORM);
            this.f539a.setRequestProperty("Content-Length", String.valueOf(bytes.length));
            outputStream = this.f539a.getOutputStream();
            try {
                outputStream.write(bytes);
                outputStream.flush();
                inputStream = this.f539a.getInputStream();
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                byte[] bArr = new byte[1024];
                while (true) {
                    int read = inputStream.read(bArr);
                    if (read == -1) {
                        break;
                    }
                    byteArrayOutputStream.write(bArr, 0, read);
                }
                byteArrayOutputStream.close();
                String str4 = new String(byteArrayOutputStream.toByteArray(), str2);
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (inputStream != null) {
                    inputStream.close();
                }
                if (this.f539a != null) {
                    this.f539a.disconnect();
                }
                Log.v("HttpsPost", "Post result -->" + str4);
                return str4;
            } catch (Throwable th2) {
                th = th2;
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                        if (this.f539a != null) {
                            this.f539a.disconnect();
                        }
                        throw th;
                    }
                }
                if (inputStream != null) {
                    inputStream.close();
                }
                if (this.f539a != null) {
                    this.f539a.disconnect();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            outputStream = null;
            if (outputStream != null) {
                outputStream.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
            if (this.f539a != null) {
                this.f539a.disconnect();
            }
            throw th;
        }
    }
}
