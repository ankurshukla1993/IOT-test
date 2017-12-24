package com.google.android.gms.auth.api.proxy;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.util.Patterns;
import com.google.android.gms.common.annotation.KeepForSdkWithMembers;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbej;
import com.google.android.gms.internal.zzbem;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

@KeepForSdkWithMembers
public class ProxyRequest extends zzbej {
    public static final Creator<ProxyRequest> CREATOR = new zza();
    public static final int HTTP_METHOD_DELETE = 3;
    public static final int HTTP_METHOD_GET = 0;
    public static final int HTTP_METHOD_HEAD = 4;
    public static final int HTTP_METHOD_OPTIONS = 5;
    public static final int HTTP_METHOD_PATCH = 7;
    public static final int HTTP_METHOD_POST = 1;
    public static final int HTTP_METHOD_PUT = 2;
    public static final int HTTP_METHOD_TRACE = 6;
    public static final int LAST_CODE = 7;
    public static final int VERSION_CODE = 2;
    public final byte[] body;
    public final int httpMethod;
    public final long timeoutMillis;
    public final String url;
    private int versionCode;
    private Bundle zzedk;

    @KeepForSdkWithMembers
    public static class Builder {
        private long zzctq = 3000;
        private String zzedl;
        private int zzedm = ProxyRequest.HTTP_METHOD_GET;
        private byte[] zzedn = null;
        private Bundle zzedo = new Bundle();

        public Builder(String str) {
            zzbq.zzgh(str);
            if (Patterns.WEB_URL.matcher(str).matches()) {
                this.zzedl = str;
                return;
            }
            throw new IllegalArgumentException(new StringBuilder(String.valueOf(str).length() + 51).append("The supplied url [ ").append(str).append("] is not match Patterns.WEB_URL!").toString());
        }

        public ProxyRequest build() {
            if (this.zzedn == null) {
                this.zzedn = new byte[0];
            }
            return new ProxyRequest(2, this.zzedl, this.zzedm, this.zzctq, this.zzedn, this.zzedo);
        }

        public Builder putHeader(String str, String str2) {
            zzbq.zzh(str, "Header name cannot be null or empty!");
            Bundle bundle = this.zzedo;
            if (str2 == null) {
                str2 = "";
            }
            bundle.putString(str, str2);
            return this;
        }

        public Builder setBody(byte[] bArr) {
            this.zzedn = bArr;
            return this;
        }

        public Builder setHttpMethod(int i) {
            boolean z = i >= 0 && i <= ProxyRequest.LAST_CODE;
            zzbq.checkArgument(z, "Unrecognized http method code.");
            this.zzedm = i;
            return this;
        }

        public Builder setTimeoutMillis(long j) {
            zzbq.checkArgument(j >= 0, "The specified timeout must be non-negative.");
            this.zzctq = j;
            return this;
        }
    }

    ProxyRequest(int i, String str, int i2, long j, byte[] bArr, Bundle bundle) {
        this.versionCode = i;
        this.url = str;
        this.httpMethod = i2;
        this.timeoutMillis = j;
        this.body = bArr;
        this.zzedk = bundle;
    }

    public Map<String, String> getHeaderMap() {
        Map linkedHashMap = new LinkedHashMap(this.zzedk.size());
        for (String str : this.zzedk.keySet()) {
            linkedHashMap.put(str, this.zzedk.getString(str));
        }
        return Collections.unmodifiableMap(linkedHashMap);
    }

    public String toString() {
        String str = this.url;
        return new StringBuilder(String.valueOf(str).length() + 42).append("ProxyRequest[ url: ").append(str).append(", method: ").append(this.httpMethod).append(" ]").toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbem.zze(parcel);
        zzbem.zza(parcel, 1, this.url, false);
        zzbem.zzc(parcel, 2, this.httpMethod);
        zzbem.zza(parcel, 3, this.timeoutMillis);
        zzbem.zza(parcel, 4, this.body, false);
        zzbem.zza(parcel, 5, this.zzedk, false);
        zzbem.zzc(parcel, 1000, this.versionCode);
        zzbem.zzai(parcel, zze);
    }
}
