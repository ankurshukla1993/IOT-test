package com.google.android.gms.location.places;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.common.internal.zzbi;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbej;
import com.google.android.gms.internal.zzbem;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import java.util.Arrays;

public class PlaceReport extends zzbej implements ReflectedParcelable {
    public static final Creator<PlaceReport> CREATOR = new zzl();
    private final String mTag;
    private final String zzdoe;
    private int zzdzm;
    private final String zzikm;

    PlaceReport(int i, String str, String str2, String str3) {
        this.zzdzm = i;
        this.zzikm = str;
        this.mTag = str2;
        this.zzdoe = str3;
    }

    public static PlaceReport create(String str, String str2) {
        boolean z = false;
        String str3 = "unknown";
        zzbq.checkNotNull(str);
        zzbq.zzgh(str2);
        zzbq.zzgh(str3);
        boolean z2 = true;
        switch (str3.hashCode()) {
            case -1436706272:
                if (str3.equals("inferredGeofencing")) {
                    z2 = true;
                    break;
                }
                break;
            case -1194968642:
                if (str3.equals("userReported")) {
                    z2 = true;
                    break;
                }
                break;
            case -284840886:
                if (str3.equals("unknown")) {
                    z2 = false;
                    break;
                }
                break;
            case -262743844:
                if (str3.equals("inferredReverseGeocoding")) {
                    z2 = true;
                    break;
                }
                break;
            case 1164924125:
                if (str3.equals("inferredSnappedToRoad")) {
                    z2 = true;
                    break;
                }
                break;
            case 1287171955:
                if (str3.equals("inferredRadioSignals")) {
                    z2 = true;
                    break;
                }
                break;
        }
        switch (z2) {
            case false:
            case true:
            case true:
            case true:
            case true:
            case true:
                z = true;
                break;
        }
        zzbq.checkArgument(z, "Invalid source");
        return new PlaceReport(1, str, str2, str3);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof PlaceReport)) {
            return false;
        }
        PlaceReport placeReport = (PlaceReport) obj;
        return zzbg.equal(this.zzikm, placeReport.zzikm) && zzbg.equal(this.mTag, placeReport.mTag) && zzbg.equal(this.zzdoe, placeReport.zzdoe);
    }

    public String getPlaceId() {
        return this.zzikm;
    }

    public String getTag() {
        return this.mTag;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.zzikm, this.mTag, this.zzdoe});
    }

    public String toString() {
        zzbi zzw = zzbg.zzw(this);
        zzw.zzg("placeId", this.zzikm);
        zzw.zzg(JobStorage.COLUMN_TAG, this.mTag);
        if (!"unknown".equals(this.zzdoe)) {
            zzw.zzg(Param.SOURCE, this.zzdoe);
        }
        return zzw.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbem.zze(parcel);
        zzbem.zzc(parcel, 1, this.zzdzm);
        zzbem.zza(parcel, 2, getPlaceId(), false);
        zzbem.zza(parcel, 3, getTag(), false);
        zzbem.zza(parcel, 4, this.zzdoe, false);
        zzbem.zzai(parcel, zze);
    }
}
