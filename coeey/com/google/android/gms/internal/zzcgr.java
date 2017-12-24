package com.google.android.gms.internal;

import android.support.annotation.WorkerThread;
import com.google.android.gms.common.internal.zzbq;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import java.util.Map.Entry;
import org.joda.time.DateTimeConstants;

@WorkerThread
final class zzcgr implements Runnable {
    private final String mPackageName;
    private final URL zzbwa;
    private final byte[] zzgdd;
    private final zzcgp zzizp;
    private final Map<String, String> zzizq;
    private /* synthetic */ zzcgn zzizr;

    public zzcgr(zzcgn com_google_android_gms_internal_zzcgn, String str, URL url, byte[] bArr, Map<String, String> map, zzcgp com_google_android_gms_internal_zzcgp) {
        this.zzizr = com_google_android_gms_internal_zzcgn;
        zzbq.zzgh(str);
        zzbq.checkNotNull(url);
        zzbq.checkNotNull(com_google_android_gms_internal_zzcgp);
        this.zzbwa = url;
        this.zzgdd = bArr;
        this.zzizp = com_google_android_gms_internal_zzcgp;
        this.mPackageName = str;
        this.zzizq = map;
    }

    public final void run() {
        OutputStream outputStream;
        Throwable e;
        Map map;
        int i;
        HttpURLConnection httpURLConnection;
        OutputStream outputStream2;
        Throwable th;
        int i2 = 0;
        this.zzizr.zzavx();
        HttpURLConnection httpURLConnection2;
        Map map2;
        try {
            URLConnection openConnection = this.zzbwa.openConnection();
            if (openConnection instanceof HttpURLConnection) {
                httpURLConnection2 = (HttpURLConnection) openConnection;
                httpURLConnection2.setDefaultUseCaches(false);
                httpURLConnection2.setConnectTimeout(DateTimeConstants.MILLIS_PER_MINUTE);
                httpURLConnection2.setReadTimeout(61000);
                httpURLConnection2.setInstanceFollowRedirects(false);
                httpURLConnection2.setDoInput(true);
                try {
                    if (this.zzizq != null) {
                        for (Entry entry : this.zzizq.entrySet()) {
                            httpURLConnection2.addRequestProperty((String) entry.getKey(), (String) entry.getValue());
                        }
                    }
                    if (this.zzgdd != null) {
                        byte[] zzp = this.zzizr.zzawi().zzp(this.zzgdd);
                        this.zzizr.zzawm().zzayx().zzj("Uploading data. size", Integer.valueOf(zzp.length));
                        httpURLConnection2.setDoOutput(true);
                        httpURLConnection2.addRequestProperty("Content-Encoding", HttpRequest.ENCODING_GZIP);
                        httpURLConnection2.setFixedLengthStreamingMode(zzp.length);
                        httpURLConnection2.connect();
                        outputStream = httpURLConnection2.getOutputStream();
                        try {
                            outputStream.write(zzp);
                            outputStream.close();
                        } catch (IOException e2) {
                            e = e2;
                            map = null;
                            i = 0;
                            OutputStream outputStream3 = outputStream;
                            httpURLConnection = httpURLConnection2;
                            outputStream2 = outputStream3;
                            if (outputStream2 != null) {
                                try {
                                    outputStream2.close();
                                } catch (IOException e3) {
                                    this.zzizr.zzawm().zzayr().zze("Error closing HTTP compressed POST connection output stream. appId", zzcgj.zzje(this.mPackageName), e3);
                                }
                            }
                            if (httpURLConnection != null) {
                                httpURLConnection.disconnect();
                            }
                            this.zzizr.zzawl().zzg(new zzcgq(this.mPackageName, this.zzizp, i, e, null, map));
                        } catch (Throwable th2) {
                            th = th2;
                            map2 = null;
                            if (outputStream != null) {
                                try {
                                    outputStream.close();
                                } catch (IOException e4) {
                                    this.zzizr.zzawm().zzayr().zze("Error closing HTTP compressed POST connection output stream. appId", zzcgj.zzje(this.mPackageName), e4);
                                }
                            }
                            if (httpURLConnection2 != null) {
                                httpURLConnection2.disconnect();
                            }
                            this.zzizr.zzawl().zzg(new zzcgq(this.mPackageName, this.zzizp, i2, null, null, map2));
                            throw th;
                        }
                    }
                    i2 = httpURLConnection2.getResponseCode();
                    map2 = httpURLConnection2.getHeaderFields();
                } catch (IOException e5) {
                    e = e5;
                    map = null;
                    i = i2;
                    httpURLConnection = httpURLConnection2;
                    outputStream2 = null;
                    if (outputStream2 != null) {
                        outputStream2.close();
                    }
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    this.zzizr.zzawl().zzg(new zzcgq(this.mPackageName, this.zzizp, i, e, null, map));
                } catch (Throwable th3) {
                    th = th3;
                    map2 = null;
                    outputStream = null;
                    if (outputStream != null) {
                        outputStream.close();
                    }
                    if (httpURLConnection2 != null) {
                        httpURLConnection2.disconnect();
                    }
                    this.zzizr.zzawl().zzg(new zzcgq(this.mPackageName, this.zzizp, i2, null, null, map2));
                    throw th;
                }
                try {
                    byte[] zza = zzcgn.zzc(httpURLConnection2);
                    if (httpURLConnection2 != null) {
                        httpURLConnection2.disconnect();
                    }
                    this.zzizr.zzawl().zzg(new zzcgq(this.mPackageName, this.zzizp, i2, null, zza, map2));
                    return;
                } catch (IOException e6) {
                    e = e6;
                    map = map2;
                    i = i2;
                    httpURLConnection = httpURLConnection2;
                    outputStream2 = null;
                    if (outputStream2 != null) {
                        outputStream2.close();
                    }
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    this.zzizr.zzawl().zzg(new zzcgq(this.mPackageName, this.zzizp, i, e, null, map));
                } catch (Throwable th32) {
                    th = th32;
                    outputStream = null;
                    if (outputStream != null) {
                        outputStream.close();
                    }
                    if (httpURLConnection2 != null) {
                        httpURLConnection2.disconnect();
                    }
                    this.zzizr.zzawl().zzg(new zzcgq(this.mPackageName, this.zzizp, i2, null, null, map2));
                    throw th;
                }
            }
            throw new IOException("Failed to obtain HTTP connection");
        } catch (IOException e7) {
            e = e7;
            map = null;
            i = 0;
            outputStream2 = null;
            httpURLConnection = null;
            if (outputStream2 != null) {
                outputStream2.close();
            }
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            this.zzizr.zzawl().zzg(new zzcgq(this.mPackageName, this.zzizp, i, e, null, map));
        } catch (Throwable th4) {
            th = th4;
            map2 = null;
            outputStream = null;
            httpURLConnection2 = null;
            if (outputStream != null) {
                outputStream.close();
            }
            if (httpURLConnection2 != null) {
                httpURLConnection2.disconnect();
            }
            this.zzizr.zzawl().zzg(new zzcgq(this.mPackageName, this.zzizp, i2, null, null, map2));
            throw th;
        }
    }
}
