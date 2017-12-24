package com.ihealth.communication.cloud.p002a;

import com.ihealth.communication.utils.Log;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Map.Entry;
import kotlin.text.Typography;
import org.joda.time.DateTimeConstants;

public class C2049j {
    private boolean f537a = true;
    private HttpURLConnection f538b = null;

    public String m381a(String str, Map map, String str2) {
        Throwable th;
        InputStream inputStream = null;
        String str3 = "";
        str3 = "";
        InputStream inputStream2 = null;
        OutputStream outputStream;
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
            this.f538b = (HttpURLConnection) new URL(str).openConnection();
            this.f538b.setConnectTimeout(DateTimeConstants.MILLIS_PER_MINUTE);
            this.f538b.setReadTimeout(DateTimeConstants.MILLIS_PER_MINUTE);
            this.f538b.setRequestMethod(HttpRequest.METHOD_POST);
            this.f538b.setDoInput(true);
            this.f538b.setDoOutput(true);
            str3 = stringBuffer.toString();
            if (this.f537a) {
                Log.i("HttpPost", "发送参数   " + str + " \n" + stringBuffer.toString());
            }
            byte[] bytes = str3.getBytes();
            this.f538b.setRequestProperty("Content-Type", HttpRequest.CONTENT_TYPE_FORM);
            this.f538b.setRequestProperty("Content-Length", String.valueOf(bytes.length));
            this.f538b.connect();
            outputStream = this.f538b.getOutputStream();
            try {
                outputStream.write(bytes);
                outputStream.flush();
                if (this.f538b.getResponseCode() != 200) {
                    str3 = "";
                    if (outputStream != null) {
                        try {
                            outputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (inputStream != null) {
                        inputStream2.close();
                    }
                    if (this.f538b != null) {
                        this.f538b.disconnect();
                    }
                } else {
                    inputStream = this.f538b.getInputStream();
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
                    str3 = URLDecoder.decode(new String(byteArrayOutputStream.toByteArray()), str2);
                    if (outputStream != null) {
                        try {
                            outputStream.close();
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                    }
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    if (this.f538b != null) {
                        this.f538b.disconnect();
                    }
                    if (this.f537a) {
                        Log.i("HttpPost", "请求返回-->" + str3);
                    }
                }
                return str3;
            } catch (Throwable th2) {
                th = th2;
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (IOException e22) {
                        e22.printStackTrace();
                        if (this.f538b != null) {
                            this.f538b.disconnect();
                        }
                        throw th;
                    }
                }
                if (inputStream != null) {
                    inputStream.close();
                }
                if (this.f538b != null) {
                    this.f538b.disconnect();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            outputStream = inputStream;
            if (outputStream != null) {
                outputStream.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
            if (this.f538b != null) {
                this.f538b.disconnect();
            }
            throw th;
        }
    }
}
