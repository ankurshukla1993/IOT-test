package com.google.android.gms.internal;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.TreeMap;
import org.apache.http.Header;
import org.apache.http.HttpEntity;

public class zzad implements zzk {
    private static boolean DEBUG = zzab.DEBUG;
    private zzam zzbm;
    private zzae zzbn;

    public zzad(zzam com_google_android_gms_internal_zzam) {
        this(com_google_android_gms_internal_zzam, new zzae(4096));
    }

    private zzad(zzam com_google_android_gms_internal_zzam, zzae com_google_android_gms_internal_zzae) {
        this.zzbm = com_google_android_gms_internal_zzam;
        this.zzbn = com_google_android_gms_internal_zzae;
    }

    private static Map<String, String> zza(Header[] headerArr) {
        Map<String, String> treeMap = new TreeMap(String.CASE_INSENSITIVE_ORDER);
        for (int i = 0; i < headerArr.length; i++) {
            treeMap.put(headerArr[i].getName(), headerArr[i].getValue());
        }
        return treeMap;
    }

    private static void zza(String str, zzp<?> com_google_android_gms_internal_zzp_, zzaa com_google_android_gms_internal_zzaa) throws zzaa {
        zzx zzj = com_google_android_gms_internal_zzp_.zzj();
        int zzi = com_google_android_gms_internal_zzp_.zzi();
        try {
            zzj.zza(com_google_android_gms_internal_zzaa);
            com_google_android_gms_internal_zzp_.zzb(String.format("%s-retry [timeout=%s]", new Object[]{str, Integer.valueOf(zzi)}));
        } catch (zzaa e) {
            com_google_android_gms_internal_zzp_.zzb(String.format("%s-timeout-giveup [timeout=%s]", new Object[]{str, Integer.valueOf(zzi)}));
            throw e;
        }
    }

    private final byte[] zza(HttpEntity httpEntity) throws IOException, zzy {
        zzap com_google_android_gms_internal_zzap = new zzap(this.zzbn, (int) httpEntity.getContentLength());
        byte[] bArr = null;
        try {
            InputStream content = httpEntity.getContent();
            if (content == null) {
                throw new zzy();
            }
            bArr = this.zzbn.zzb(1024);
            while (true) {
                int read = content.read(bArr);
                if (read == -1) {
                    break;
                }
                com_google_android_gms_internal_zzap.write(bArr, 0, read);
            }
            byte[] toByteArray = com_google_android_gms_internal_zzap.toByteArray();
            return toByteArray;
        } finally {
            try {
                httpEntity.consumeContent();
            } catch (IOException e) {
                zzab.zza("Error occurred when calling consumingContent", new Object[0]);
            }
            this.zzbn.zza(bArr);
            com_google_android_gms_internal_zzap.close();
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.android.gms.internal.zzn zza(com.google.android.gms.internal.zzp<?> r19) throws com.google.android.gms.internal.zzaa {
        /*
        r18 = this;
        r16 = android.os.SystemClock.elapsedRealtime();
    L_0x0004:
        r3 = 0;
        r14 = 0;
        r6 = java.util.Collections.emptyMap();
        r2 = new java.util.HashMap;	 Catch:{ SocketTimeoutException -> 0x00e9, ConnectTimeoutException -> 0x010d, MalformedURLException -> 0x011c, IOException -> 0x013e }
        r2.<init>();	 Catch:{ SocketTimeoutException -> 0x00e9, ConnectTimeoutException -> 0x010d, MalformedURLException -> 0x011c, IOException -> 0x013e }
        r4 = r19.zze();	 Catch:{ SocketTimeoutException -> 0x00e9, ConnectTimeoutException -> 0x010d, MalformedURLException -> 0x011c, IOException -> 0x013e }
        if (r4 == 0) goto L_0x0038;
    L_0x0015:
        r5 = r4.zza;	 Catch:{ SocketTimeoutException -> 0x00e9, ConnectTimeoutException -> 0x010d, MalformedURLException -> 0x011c, IOException -> 0x013e }
        if (r5 == 0) goto L_0x0020;
    L_0x0019:
        r5 = "If-None-Match";
        r7 = r4.zza;	 Catch:{ SocketTimeoutException -> 0x00e9, ConnectTimeoutException -> 0x010d, MalformedURLException -> 0x011c, IOException -> 0x013e }
        r2.put(r5, r7);	 Catch:{ SocketTimeoutException -> 0x00e9, ConnectTimeoutException -> 0x010d, MalformedURLException -> 0x011c, IOException -> 0x013e }
    L_0x0020:
        r8 = r4.zzc;	 Catch:{ SocketTimeoutException -> 0x00e9, ConnectTimeoutException -> 0x010d, MalformedURLException -> 0x011c, IOException -> 0x013e }
        r10 = 0;
        r5 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1));
        if (r5 <= 0) goto L_0x0038;
    L_0x0028:
        r5 = new java.util.Date;	 Catch:{ SocketTimeoutException -> 0x00e9, ConnectTimeoutException -> 0x010d, MalformedURLException -> 0x011c, IOException -> 0x013e }
        r8 = r4.zzc;	 Catch:{ SocketTimeoutException -> 0x00e9, ConnectTimeoutException -> 0x010d, MalformedURLException -> 0x011c, IOException -> 0x013e }
        r5.<init>(r8);	 Catch:{ SocketTimeoutException -> 0x00e9, ConnectTimeoutException -> 0x010d, MalformedURLException -> 0x011c, IOException -> 0x013e }
        r4 = "If-Modified-Since";
        r5 = org.apache.http.impl.cookie.DateUtils.formatDate(r5);	 Catch:{ SocketTimeoutException -> 0x00e9, ConnectTimeoutException -> 0x010d, MalformedURLException -> 0x011c, IOException -> 0x013e }
        r2.put(r4, r5);	 Catch:{ SocketTimeoutException -> 0x00e9, ConnectTimeoutException -> 0x010d, MalformedURLException -> 0x011c, IOException -> 0x013e }
    L_0x0038:
        r0 = r18;
        r4 = r0.zzbm;	 Catch:{ SocketTimeoutException -> 0x00e9, ConnectTimeoutException -> 0x010d, MalformedURLException -> 0x011c, IOException -> 0x013e }
        r0 = r19;
        r15 = r4.zza(r0, r2);	 Catch:{ SocketTimeoutException -> 0x00e9, ConnectTimeoutException -> 0x010d, MalformedURLException -> 0x011c, IOException -> 0x013e }
        r3 = r15.getStatusLine();	 Catch:{ SocketTimeoutException -> 0x00e9, ConnectTimeoutException -> 0x010d, MalformedURLException -> 0x011c, IOException -> 0x01ba }
        r4 = r3.getStatusCode();	 Catch:{ SocketTimeoutException -> 0x00e9, ConnectTimeoutException -> 0x010d, MalformedURLException -> 0x011c, IOException -> 0x01ba }
        r2 = r15.getAllHeaders();	 Catch:{ SocketTimeoutException -> 0x00e9, ConnectTimeoutException -> 0x010d, MalformedURLException -> 0x011c, IOException -> 0x01ba }
        r6 = zza(r2);	 Catch:{ SocketTimeoutException -> 0x00e9, ConnectTimeoutException -> 0x010d, MalformedURLException -> 0x011c, IOException -> 0x01ba }
        r2 = 304; // 0x130 float:4.26E-43 double:1.5E-321;
        if (r4 != r2) goto L_0x0085;
    L_0x0056:
        r2 = r19.zze();	 Catch:{ SocketTimeoutException -> 0x00e9, ConnectTimeoutException -> 0x010d, MalformedURLException -> 0x011c, IOException -> 0x01ba }
        if (r2 != 0) goto L_0x006c;
    L_0x005c:
        r3 = new com.google.android.gms.internal.zzn;	 Catch:{ SocketTimeoutException -> 0x00e9, ConnectTimeoutException -> 0x010d, MalformedURLException -> 0x011c, IOException -> 0x01ba }
        r4 = 304; // 0x130 float:4.26E-43 double:1.5E-321;
        r5 = 0;
        r7 = 1;
        r8 = android.os.SystemClock.elapsedRealtime();	 Catch:{ SocketTimeoutException -> 0x00e9, ConnectTimeoutException -> 0x010d, MalformedURLException -> 0x011c, IOException -> 0x01ba }
        r8 = r8 - r16;
        r3.<init>(r4, r5, r6, r7, r8);	 Catch:{ SocketTimeoutException -> 0x00e9, ConnectTimeoutException -> 0x010d, MalformedURLException -> 0x011c, IOException -> 0x01ba }
    L_0x006b:
        return r3;
    L_0x006c:
        r3 = r2.zzf;	 Catch:{ SocketTimeoutException -> 0x00e9, ConnectTimeoutException -> 0x010d, MalformedURLException -> 0x011c, IOException -> 0x01ba }
        r3.putAll(r6);	 Catch:{ SocketTimeoutException -> 0x00e9, ConnectTimeoutException -> 0x010d, MalformedURLException -> 0x011c, IOException -> 0x01ba }
        r7 = new com.google.android.gms.internal.zzn;	 Catch:{ SocketTimeoutException -> 0x00e9, ConnectTimeoutException -> 0x010d, MalformedURLException -> 0x011c, IOException -> 0x01ba }
        r8 = 304; // 0x130 float:4.26E-43 double:1.5E-321;
        r9 = r2.data;	 Catch:{ SocketTimeoutException -> 0x00e9, ConnectTimeoutException -> 0x010d, MalformedURLException -> 0x011c, IOException -> 0x01ba }
        r10 = r2.zzf;	 Catch:{ SocketTimeoutException -> 0x00e9, ConnectTimeoutException -> 0x010d, MalformedURLException -> 0x011c, IOException -> 0x01ba }
        r11 = 1;
        r2 = android.os.SystemClock.elapsedRealtime();	 Catch:{ SocketTimeoutException -> 0x00e9, ConnectTimeoutException -> 0x010d, MalformedURLException -> 0x011c, IOException -> 0x01ba }
        r12 = r2 - r16;
        r7.<init>(r8, r9, r10, r11, r12);	 Catch:{ SocketTimeoutException -> 0x00e9, ConnectTimeoutException -> 0x010d, MalformedURLException -> 0x011c, IOException -> 0x01ba }
        r3 = r7;
        goto L_0x006b;
    L_0x0085:
        r2 = r15.getEntity();	 Catch:{ SocketTimeoutException -> 0x00e9, ConnectTimeoutException -> 0x010d, MalformedURLException -> 0x011c, IOException -> 0x01ba }
        if (r2 == 0) goto L_0x00f8;
    L_0x008b:
        r2 = r15.getEntity();	 Catch:{ SocketTimeoutException -> 0x00e9, ConnectTimeoutException -> 0x010d, MalformedURLException -> 0x011c, IOException -> 0x01ba }
        r0 = r18;
        r5 = r0.zza(r2);	 Catch:{ SocketTimeoutException -> 0x00e9, ConnectTimeoutException -> 0x010d, MalformedURLException -> 0x011c, IOException -> 0x01ba }
    L_0x0095:
        r8 = android.os.SystemClock.elapsedRealtime();	 Catch:{ SocketTimeoutException -> 0x00e9, ConnectTimeoutException -> 0x010d, MalformedURLException -> 0x011c, IOException -> 0x01be }
        r8 = r8 - r16;
        r2 = DEBUG;	 Catch:{ SocketTimeoutException -> 0x00e9, ConnectTimeoutException -> 0x010d, MalformedURLException -> 0x011c, IOException -> 0x01be }
        if (r2 != 0) goto L_0x00a5;
    L_0x009f:
        r10 = 3000; // 0xbb8 float:4.204E-42 double:1.482E-320;
        r2 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1));
        if (r2 <= 0) goto L_0x00db;
    L_0x00a5:
        r7 = "HTTP response for request=<%s> [lifetime=%d], [size=%s], [rc=%d], [retryCount=%s]";
        r2 = 5;
        r10 = new java.lang.Object[r2];	 Catch:{ SocketTimeoutException -> 0x00e9, ConnectTimeoutException -> 0x010d, MalformedURLException -> 0x011c, IOException -> 0x01be }
        r2 = 0;
        r10[r2] = r19;	 Catch:{ SocketTimeoutException -> 0x00e9, ConnectTimeoutException -> 0x010d, MalformedURLException -> 0x011c, IOException -> 0x01be }
        r2 = 1;
        r8 = java.lang.Long.valueOf(r8);	 Catch:{ SocketTimeoutException -> 0x00e9, ConnectTimeoutException -> 0x010d, MalformedURLException -> 0x011c, IOException -> 0x01be }
        r10[r2] = r8;	 Catch:{ SocketTimeoutException -> 0x00e9, ConnectTimeoutException -> 0x010d, MalformedURLException -> 0x011c, IOException -> 0x01be }
        r8 = 2;
        if (r5 == 0) goto L_0x00fc;
    L_0x00b7:
        r2 = r5.length;	 Catch:{ SocketTimeoutException -> 0x00e9, ConnectTimeoutException -> 0x010d, MalformedURLException -> 0x011c, IOException -> 0x01be }
        r2 = java.lang.Integer.valueOf(r2);	 Catch:{ SocketTimeoutException -> 0x00e9, ConnectTimeoutException -> 0x010d, MalformedURLException -> 0x011c, IOException -> 0x01be }
    L_0x00bc:
        r10[r8] = r2;	 Catch:{ SocketTimeoutException -> 0x00e9, ConnectTimeoutException -> 0x010d, MalformedURLException -> 0x011c, IOException -> 0x01be }
        r2 = 3;
        r3 = r3.getStatusCode();	 Catch:{ SocketTimeoutException -> 0x00e9, ConnectTimeoutException -> 0x010d, MalformedURLException -> 0x011c, IOException -> 0x01be }
        r3 = java.lang.Integer.valueOf(r3);	 Catch:{ SocketTimeoutException -> 0x00e9, ConnectTimeoutException -> 0x010d, MalformedURLException -> 0x011c, IOException -> 0x01be }
        r10[r2] = r3;	 Catch:{ SocketTimeoutException -> 0x00e9, ConnectTimeoutException -> 0x010d, MalformedURLException -> 0x011c, IOException -> 0x01be }
        r2 = 4;
        r3 = r19.zzj();	 Catch:{ SocketTimeoutException -> 0x00e9, ConnectTimeoutException -> 0x010d, MalformedURLException -> 0x011c, IOException -> 0x01be }
        r3 = r3.zzb();	 Catch:{ SocketTimeoutException -> 0x00e9, ConnectTimeoutException -> 0x010d, MalformedURLException -> 0x011c, IOException -> 0x01be }
        r3 = java.lang.Integer.valueOf(r3);	 Catch:{ SocketTimeoutException -> 0x00e9, ConnectTimeoutException -> 0x010d, MalformedURLException -> 0x011c, IOException -> 0x01be }
        r10[r2] = r3;	 Catch:{ SocketTimeoutException -> 0x00e9, ConnectTimeoutException -> 0x010d, MalformedURLException -> 0x011c, IOException -> 0x01be }
        com.google.android.gms.internal.zzab.zzb(r7, r10);	 Catch:{ SocketTimeoutException -> 0x00e9, ConnectTimeoutException -> 0x010d, MalformedURLException -> 0x011c, IOException -> 0x01be }
    L_0x00db:
        r2 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r4 < r2) goto L_0x00e3;
    L_0x00df:
        r2 = 299; // 0x12b float:4.19E-43 double:1.477E-321;
        if (r4 <= r2) goto L_0x00ff;
    L_0x00e3:
        r2 = new java.io.IOException;	 Catch:{ SocketTimeoutException -> 0x00e9, ConnectTimeoutException -> 0x010d, MalformedURLException -> 0x011c, IOException -> 0x01be }
        r2.<init>();	 Catch:{ SocketTimeoutException -> 0x00e9, ConnectTimeoutException -> 0x010d, MalformedURLException -> 0x011c, IOException -> 0x01be }
        throw r2;	 Catch:{ SocketTimeoutException -> 0x00e9, ConnectTimeoutException -> 0x010d, MalformedURLException -> 0x011c, IOException -> 0x01be }
    L_0x00e9:
        r2 = move-exception;
        r2 = "socket";
        r3 = new com.google.android.gms.internal.zzz;
        r3.<init>();
        r0 = r19;
        zza(r2, r0, r3);
        goto L_0x0004;
    L_0x00f8:
        r2 = 0;
        r5 = new byte[r2];	 Catch:{ SocketTimeoutException -> 0x00e9, ConnectTimeoutException -> 0x010d, MalformedURLException -> 0x011c, IOException -> 0x01ba }
        goto L_0x0095;
    L_0x00fc:
        r2 = "null";
        goto L_0x00bc;
    L_0x00ff:
        r3 = new com.google.android.gms.internal.zzn;	 Catch:{ SocketTimeoutException -> 0x00e9, ConnectTimeoutException -> 0x010d, MalformedURLException -> 0x011c, IOException -> 0x01be }
        r7 = 0;
        r8 = android.os.SystemClock.elapsedRealtime();	 Catch:{ SocketTimeoutException -> 0x00e9, ConnectTimeoutException -> 0x010d, MalformedURLException -> 0x011c, IOException -> 0x01be }
        r8 = r8 - r16;
        r3.<init>(r4, r5, r6, r7, r8);	 Catch:{ SocketTimeoutException -> 0x00e9, ConnectTimeoutException -> 0x010d, MalformedURLException -> 0x011c, IOException -> 0x01be }
        goto L_0x006b;
    L_0x010d:
        r2 = move-exception;
        r2 = "connection";
        r3 = new com.google.android.gms.internal.zzz;
        r3.<init>();
        r0 = r19;
        zza(r2, r0, r3);
        goto L_0x0004;
    L_0x011c:
        r2 = move-exception;
        r3 = r2;
        r4 = new java.lang.RuntimeException;
        r5 = "Bad URL ";
        r2 = r19.getUrl();
        r2 = java.lang.String.valueOf(r2);
        r6 = r2.length();
        if (r6 == 0) goto L_0x0138;
    L_0x0130:
        r2 = r5.concat(r2);
    L_0x0134:
        r4.<init>(r2, r3);
        throw r4;
    L_0x0138:
        r2 = new java.lang.String;
        r2.<init>(r5);
        goto L_0x0134;
    L_0x013e:
        r2 = move-exception;
        r5 = r14;
    L_0x0140:
        if (r3 == 0) goto L_0x0184;
    L_0x0142:
        r2 = r3.getStatusLine();
        r4 = r2.getStatusCode();
        r2 = "Unexpected response code %d for %s";
        r3 = 2;
        r3 = new java.lang.Object[r3];
        r7 = 0;
        r8 = java.lang.Integer.valueOf(r4);
        r3[r7] = r8;
        r7 = 1;
        r8 = r19.getUrl();
        r3[r7] = r8;
        com.google.android.gms.internal.zzab.zzc(r2, r3);
        if (r5 == 0) goto L_0x01ac;
    L_0x0162:
        r3 = new com.google.android.gms.internal.zzn;
        r7 = 0;
        r8 = android.os.SystemClock.elapsedRealtime();
        r8 = r8 - r16;
        r3.<init>(r4, r5, r6, r7, r8);
        r2 = 401; // 0x191 float:5.62E-43 double:1.98E-321;
        if (r4 == r2) goto L_0x0176;
    L_0x0172:
        r2 = 403; // 0x193 float:5.65E-43 double:1.99E-321;
        if (r4 != r2) goto L_0x018a;
    L_0x0176:
        r2 = "auth";
        r4 = new com.google.android.gms.internal.zza;
        r4.<init>(r3);
        r0 = r19;
        zza(r2, r0, r4);
        goto L_0x0004;
    L_0x0184:
        r3 = new com.google.android.gms.internal.zzo;
        r3.<init>(r2);
        throw r3;
    L_0x018a:
        r2 = 400; // 0x190 float:5.6E-43 double:1.976E-321;
        if (r4 < r2) goto L_0x0198;
    L_0x018e:
        r2 = 499; // 0x1f3 float:6.99E-43 double:2.465E-321;
        if (r4 > r2) goto L_0x0198;
    L_0x0192:
        r2 = new com.google.android.gms.internal.zzf;
        r2.<init>(r3);
        throw r2;
    L_0x0198:
        r2 = 500; // 0x1f4 float:7.0E-43 double:2.47E-321;
        if (r4 < r2) goto L_0x01a6;
    L_0x019c:
        r2 = 599; // 0x257 float:8.4E-43 double:2.96E-321;
        if (r4 > r2) goto L_0x01a6;
    L_0x01a0:
        r2 = new com.google.android.gms.internal.zzy;
        r2.<init>(r3);
        throw r2;
    L_0x01a6:
        r2 = new com.google.android.gms.internal.zzy;
        r2.<init>(r3);
        throw r2;
    L_0x01ac:
        r2 = "network";
        r3 = new com.google.android.gms.internal.zzm;
        r3.<init>();
        r0 = r19;
        zza(r2, r0, r3);
        goto L_0x0004;
    L_0x01ba:
        r2 = move-exception;
        r5 = r14;
        r3 = r15;
        goto L_0x0140;
    L_0x01be:
        r2 = move-exception;
        r3 = r15;
        goto L_0x0140;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzad.zza(com.google.android.gms.internal.zzp):com.google.android.gms.internal.zzn");
    }
}
