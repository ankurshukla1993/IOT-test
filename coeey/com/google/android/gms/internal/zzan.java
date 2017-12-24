package com.google.android.gms.internal;

import com.facebook.common.util.UriUtil;
import com.ihealth.communication.manager.iHealthDevicesManager.ScanDevice;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.net.ssl.SSLSocketFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ProtocolVersion;
import org.apache.http.StatusLine;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.message.BasicStatusLine;

public final class zzan implements zzam {
    private final zzao zzcb;
    private final SSLSocketFactory zzcc;

    public zzan() {
        this(null);
    }

    private zzan(zzao com_google_android_gms_internal_zzao) {
        this(null, null);
    }

    private zzan(zzao com_google_android_gms_internal_zzao, SSLSocketFactory sSLSocketFactory) {
        this.zzcb = null;
        this.zzcc = null;
    }

    private static HttpEntity zza(HttpURLConnection httpURLConnection) {
        InputStream inputStream;
        HttpEntity basicHttpEntity = new BasicHttpEntity();
        try {
            inputStream = httpURLConnection.getInputStream();
        } catch (IOException e) {
            inputStream = httpURLConnection.getErrorStream();
        }
        basicHttpEntity.setContent(inputStream);
        basicHttpEntity.setContentLength((long) httpURLConnection.getContentLength());
        basicHttpEntity.setContentEncoding(httpURLConnection.getContentEncoding());
        basicHttpEntity.setContentType(httpURLConnection.getContentType());
        return basicHttpEntity;
    }

    private static void zza(HttpURLConnection httpURLConnection, zzp<?> com_google_android_gms_internal_zzp_) throws IOException, zza {
        byte[] zzg = com_google_android_gms_internal_zzp_.zzg();
        if (zzg != null) {
            httpURLConnection.setDoOutput(true);
            httpURLConnection.addRequestProperty("Content-Type", zzp.zzf());
            DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
            dataOutputStream.write(zzg);
            dataOutputStream.close();
        }
    }

    public final HttpResponse zza(zzp<?> com_google_android_gms_internal_zzp_, Map<String, String> map) throws IOException, zza {
        String zzg;
        String url = com_google_android_gms_internal_zzp_.getUrl();
        HashMap hashMap = new HashMap();
        hashMap.putAll(com_google_android_gms_internal_zzp_.getHeaders());
        hashMap.putAll(map);
        if (this.zzcb != null) {
            zzg = this.zzcb.zzg(url);
            if (zzg == null) {
                String str = "URL blocked by rewriter: ";
                zzg = String.valueOf(url);
                throw new IOException(zzg.length() != 0 ? str.concat(zzg) : new String(str));
            }
        }
        zzg = url;
        URL url2 = new URL(zzg);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url2.openConnection();
        httpURLConnection.setInstanceFollowRedirects(HttpURLConnection.getFollowRedirects());
        int zzi = com_google_android_gms_internal_zzp_.zzi();
        httpURLConnection.setConnectTimeout(zzi);
        httpURLConnection.setReadTimeout(zzi);
        httpURLConnection.setUseCaches(false);
        httpURLConnection.setDoInput(true);
        UriUtil.HTTPS_SCHEME.equals(url2.getProtocol());
        for (String url3 : hashMap.keySet()) {
            httpURLConnection.addRequestProperty(url3, (String) hashMap.get(url3));
        }
        switch (com_google_android_gms_internal_zzp_.getMethod()) {
            case -1:
                break;
            case 0:
                httpURLConnection.setRequestMethod(HttpRequest.METHOD_GET);
                break;
            case 1:
                httpURLConnection.setRequestMethod(HttpRequest.METHOD_POST);
                zza(httpURLConnection, (zzp) com_google_android_gms_internal_zzp_);
                break;
            case 2:
                httpURLConnection.setRequestMethod(HttpRequest.METHOD_PUT);
                zza(httpURLConnection, (zzp) com_google_android_gms_internal_zzp_);
                break;
            case 3:
                httpURLConnection.setRequestMethod(HttpRequest.METHOD_DELETE);
                break;
            case 4:
                httpURLConnection.setRequestMethod(HttpRequest.METHOD_HEAD);
                break;
            case 5:
                httpURLConnection.setRequestMethod(HttpRequest.METHOD_OPTIONS);
                break;
            case 6:
                httpURLConnection.setRequestMethod(HttpRequest.METHOD_TRACE);
                break;
            case 7:
                httpURLConnection.setRequestMethod("PATCH");
                zza(httpURLConnection, (zzp) com_google_android_gms_internal_zzp_);
                break;
            default:
                throw new IllegalStateException("Unknown method type.");
        }
        ProtocolVersion protocolVersion = new ProtocolVersion("HTTP", 1, 1);
        if (httpURLConnection.getResponseCode() == -1) {
            throw new IOException("Could not retrieve response code from HttpUrlConnection.");
        }
        StatusLine basicStatusLine = new BasicStatusLine(protocolVersion, httpURLConnection.getResponseCode(), httpURLConnection.getResponseMessage());
        HttpResponse basicHttpResponse = new BasicHttpResponse(basicStatusLine);
        int method = com_google_android_gms_internal_zzp_.getMethod();
        zzi = basicStatusLine.getStatusCode();
        boolean z = (method == 4 || ((100 <= zzi && zzi < 200) || zzi == ScanDevice.LINK_USB || zzi == 304)) ? false : true;
        if (z) {
            basicHttpResponse.setEntity(zza(httpURLConnection));
        }
        for (Entry entry : httpURLConnection.getHeaderFields().entrySet()) {
            if (entry.getKey() != null) {
                basicHttpResponse.addHeader(new BasicHeader((String) entry.getKey(), (String) ((List) entry.getValue()).get(0)));
            }
        }
        return basicHttpResponse;
    }
}
