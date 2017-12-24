package com.google.android.gms.common.data;

import android.database.CharArrayBuffer;
import android.net.Uri;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.common.internal.zzbq;
import java.util.Arrays;

public class zzc {
    protected final DataHolder zzfnz;
    protected int zzftd;
    private int zzfte;

    public zzc(DataHolder dataHolder, int i) {
        this.zzfnz = (DataHolder) zzbq.checkNotNull(dataHolder);
        zzbx(i);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof zzc)) {
            return false;
        }
        zzc com_google_android_gms_common_data_zzc = (zzc) obj;
        return zzbg.equal(Integer.valueOf(com_google_android_gms_common_data_zzc.zzftd), Integer.valueOf(this.zzftd)) && zzbg.equal(Integer.valueOf(com_google_android_gms_common_data_zzc.zzfte), Integer.valueOf(this.zzfte)) && com_google_android_gms_common_data_zzc.zzfnz == this.zzfnz;
    }

    protected final boolean getBoolean(String str) {
        return this.zzfnz.zze(str, this.zzftd, this.zzfte);
    }

    protected final byte[] getByteArray(String str) {
        return this.zzfnz.zzg(str, this.zzftd, this.zzfte);
    }

    protected final float getFloat(String str) {
        return this.zzfnz.zzf(str, this.zzftd, this.zzfte);
    }

    protected final int getInteger(String str) {
        return this.zzfnz.zzc(str, this.zzftd, this.zzfte);
    }

    protected final long getLong(String str) {
        return this.zzfnz.zzb(str, this.zzftd, this.zzfte);
    }

    protected final String getString(String str) {
        return this.zzfnz.zzd(str, this.zzftd, this.zzfte);
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.zzftd), Integer.valueOf(this.zzfte), this.zzfnz});
    }

    public boolean isDataValid() {
        return !this.zzfnz.isClosed();
    }

    protected final void zza(String str, CharArrayBuffer charArrayBuffer) {
        this.zzfnz.zza(str, this.zzftd, this.zzfte, charArrayBuffer);
    }

    protected final void zzbx(int i) {
        boolean z = i >= 0 && i < this.zzfnz.zzftm;
        zzbq.checkState(z);
        this.zzftd = i;
        this.zzfte = this.zzfnz.zzbz(this.zzftd);
    }

    public final boolean zzfv(String str) {
        return this.zzfnz.zzfv(str);
    }

    protected final Uri zzfw(String str) {
        String zzd = this.zzfnz.zzd(str, this.zzftd, this.zzfte);
        return zzd == null ? null : Uri.parse(zzd);
    }

    protected final boolean zzfx(String str) {
        return this.zzfnz.zzh(str, this.zzftd, this.zzfte);
    }
}
