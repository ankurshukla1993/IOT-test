package com.google.android.gms.common.data;

import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.data.DataHolder.zza;
import com.google.android.gms.internal.zzben;

public class zzd<T extends zzben> extends AbstractDataBuffer<T> {
    private static final String[] zzftf = new String[]{"data"};
    private final Creator<T> zzftg;

    public zzd(DataHolder dataHolder, Creator<T> creator) {
        super(dataHolder);
        this.zzftg = creator;
    }

    public static <T extends zzben> void zza(zza com_google_android_gms_common_data_DataHolder_zza, T t) {
        Parcel obtain = Parcel.obtain();
        t.writeToParcel(obtain, 0);
        ContentValues contentValues = new ContentValues();
        contentValues.put("data", obtain.marshall());
        com_google_android_gms_common_data_DataHolder_zza.zza(contentValues);
        obtain.recycle();
    }

    public static zza zzajm() {
        return DataHolder.zzb(zzftf);
    }

    public /* synthetic */ Object get(int i) {
        return zzby(i);
    }

    public T zzby(int i) {
        byte[] zzg = this.zzfnz.zzg("data", i, this.zzfnz.zzbz(i));
        Parcel obtain = Parcel.obtain();
        obtain.unmarshall(zzg, 0, zzg.length);
        obtain.setDataPosition(0);
        zzben com_google_android_gms_internal_zzben = (zzben) this.zzftg.createFromParcel(obtain);
        obtain.recycle();
        return com_google_android_gms_internal_zzben;
    }
}
