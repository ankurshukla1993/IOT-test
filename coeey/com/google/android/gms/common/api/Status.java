package com.google.android.gms.common.api;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.IntentSender.SendIntentException;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.internal.zzbej;
import com.google.android.gms.internal.zzbem;
import java.util.Arrays;

public final class Status extends zzbej implements Result, ReflectedParcelable {
    public static final Creator<Status> CREATOR = new zzg();
    public static final Status zzfko = new Status(0);
    public static final Status zzfkp = new Status(14);
    public static final Status zzfkq = new Status(8);
    public static final Status zzfkr = new Status(15);
    public static final Status zzfks = new Status(16);
    public static final Status zzfkt = new Status(17);
    private static Status zzfku = new Status(18);
    private int zzdzm;
    @Nullable
    private final PendingIntent zzebp;
    private final int zzfcq;
    @Nullable
    private final String zzfhz;

    public Status(int i) {
        this(i, null);
    }

    Status(int i, int i2, @Nullable String str, @Nullable PendingIntent pendingIntent) {
        this.zzdzm = i;
        this.zzfcq = i2;
        this.zzfhz = str;
        this.zzebp = pendingIntent;
    }

    public Status(int i, @Nullable String str) {
        this(1, i, str, null);
    }

    public Status(int i, @Nullable String str, @Nullable PendingIntent pendingIntent) {
        this(1, i, str, pendingIntent);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof Status)) {
            return false;
        }
        Status status = (Status) obj;
        return this.zzdzm == status.zzdzm && this.zzfcq == status.zzfcq && zzbg.equal(this.zzfhz, status.zzfhz) && zzbg.equal(this.zzebp, status.zzebp);
    }

    public final PendingIntent getResolution() {
        return this.zzebp;
    }

    public final Status getStatus() {
        return this;
    }

    public final int getStatusCode() {
        return this.zzfcq;
    }

    @Nullable
    public final String getStatusMessage() {
        return this.zzfhz;
    }

    public final boolean hasResolution() {
        return this.zzebp != null;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.zzdzm), Integer.valueOf(this.zzfcq), this.zzfhz, this.zzebp});
    }

    public final boolean isCanceled() {
        return this.zzfcq == 16;
    }

    public final boolean isInterrupted() {
        return this.zzfcq == 14;
    }

    public final boolean isSuccess() {
        return this.zzfcq <= 0;
    }

    public final void startResolutionForResult(Activity activity, int i) throws SendIntentException {
        if (hasResolution()) {
            activity.startIntentSenderForResult(this.zzebp.getIntentSender(), i, null, 0, 0, 0);
        }
    }

    public final String toString() {
        return zzbg.zzw(this).zzg("statusCode", zzagk()).zzg("resolution", this.zzebp).toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbem.zze(parcel);
        zzbem.zzc(parcel, 1, getStatusCode());
        zzbem.zza(parcel, 2, getStatusMessage(), false);
        zzbem.zza(parcel, 3, this.zzebp, i, false);
        zzbem.zzc(parcel, 1000, this.zzdzm);
        zzbem.zzai(parcel, zze);
    }

    public final String zzagk() {
        return this.zzfhz != null ? this.zzfhz : CommonStatusCodes.getStatusCodeString(this.zzfcq);
    }
}
