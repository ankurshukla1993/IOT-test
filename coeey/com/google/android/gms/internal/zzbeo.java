package com.google.android.gms.internal;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzbq;
import java.util.ArrayList;

public final class zzbeo {
    public static <T extends zzben> T zza(Intent intent, String str, Creator<T> creator) {
        byte[] byteArrayExtra = intent.getByteArrayExtra(str);
        return byteArrayExtra == null ? null : zza(byteArrayExtra, creator);
    }

    public static <T extends zzben> T zza(byte[] bArr, Creator<T> creator) {
        zzbq.checkNotNull(creator);
        Parcel obtain = Parcel.obtain();
        obtain.unmarshall(bArr, 0, bArr.length);
        obtain.setDataPosition(0);
        zzben com_google_android_gms_internal_zzben = (zzben) creator.createFromParcel(obtain);
        obtain.recycle();
        return com_google_android_gms_internal_zzben;
    }

    public static <T extends zzben> void zza(T t, Intent intent, String str) {
        intent.putExtra(str, zza(t));
    }

    public static <T extends zzben> byte[] zza(T t) {
        Parcel obtain = Parcel.obtain();
        t.writeToParcel(obtain, 0);
        byte[] marshall = obtain.marshall();
        obtain.recycle();
        return marshall;
    }

    public static <T extends zzben> ArrayList<T> zzb(Intent intent, String str, Creator<T> creator) {
        ArrayList arrayList = (ArrayList) intent.getSerializableExtra(str);
        if (arrayList == null) {
            return null;
        }
        ArrayList<T> arrayList2 = new ArrayList(arrayList.size());
        arrayList = arrayList;
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            Object obj = arrayList.get(i);
            i++;
            arrayList2.add(zza((byte[]) obj, creator));
        }
        return arrayList2;
    }
}
