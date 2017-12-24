package com.google.android.gms.common.images;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.internal.zzbej;
import com.google.android.gms.internal.zzbem;
import java.util.Arrays;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

public final class WebImage extends zzbej {
    public static final Creator<WebImage> CREATOR = new zze();
    private final int zzakw;
    private final int zzakx;
    private int zzdzm;
    private final Uri zzexl;

    WebImage(int i, Uri uri, int i2, int i3) {
        this.zzdzm = i;
        this.zzexl = uri;
        this.zzakw = i2;
        this.zzakx = i3;
    }

    public WebImage(Uri uri) throws IllegalArgumentException {
        this(uri, 0, 0);
    }

    public WebImage(Uri uri, int i, int i2) throws IllegalArgumentException {
        this(1, uri, i, i2);
        if (uri == null) {
            throw new IllegalArgumentException("url cannot be null");
        } else if (i < 0 || i2 < 0) {
            throw new IllegalArgumentException("width and height must not be negative");
        }
    }

    public WebImage(JSONObject jSONObject) throws IllegalArgumentException {
        this(zzy(jSONObject), jSONObject.optInt("width", 0), jSONObject.optInt("height", 0));
    }

    private static Uri zzy(JSONObject jSONObject) {
        Uri uri = null;
        if (jSONObject.has("url")) {
            try {
                uri = Uri.parse(jSONObject.getString("url"));
            } catch (JSONException e) {
            }
        }
        return uri;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof WebImage)) {
            return false;
        }
        WebImage webImage = (WebImage) obj;
        return zzbg.equal(this.zzexl, webImage.zzexl) && this.zzakw == webImage.zzakw && this.zzakx == webImage.zzakx;
    }

    public final int getHeight() {
        return this.zzakx;
    }

    public final Uri getUrl() {
        return this.zzexl;
    }

    public final int getWidth() {
        return this.zzakw;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.zzexl, Integer.valueOf(this.zzakw), Integer.valueOf(this.zzakx)});
    }

    public final JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("url", this.zzexl.toString());
            jSONObject.put("width", this.zzakw);
            jSONObject.put("height", this.zzakx);
        } catch (JSONException e) {
        }
        return jSONObject;
    }

    public final String toString() {
        return String.format(Locale.US, "Image %dx%d %s", new Object[]{Integer.valueOf(this.zzakw), Integer.valueOf(this.zzakx), this.zzexl.toString()});
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbem.zze(parcel);
        zzbem.zzc(parcel, 1, this.zzdzm);
        zzbem.zza(parcel, 2, getUrl(), i, false);
        zzbem.zzc(parcel, 3, getWidth());
        zzbem.zzc(parcel, 4, getHeight());
        zzbem.zzai(parcel, zze);
    }
}
