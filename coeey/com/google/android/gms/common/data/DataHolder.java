package com.google.android.gms.common.data;

import android.content.ContentValues;
import android.database.CharArrayBuffer;
import android.database.CursorIndexOutOfBoundsException;
import android.database.CursorWindow;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.util.Log;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.internal.zzc;
import com.google.android.gms.internal.zzbej;
import com.google.android.gms.internal.zzbem;
import java.io.Closeable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

@KeepName
public final class DataHolder extends zzbej implements Closeable {
    public static final Creator<DataHolder> CREATOR = new zzf();
    private static final zza zzfto = new zze(new String[0], null);
    private boolean mClosed;
    private int zzdzm;
    private final int zzfcq;
    private final String[] zzfth;
    private Bundle zzfti;
    private final CursorWindow[] zzftj;
    private final Bundle zzftk;
    private int[] zzftl;
    int zzftm;
    private boolean zzftn;

    public static class zza {
        private final String[] zzfth;
        private final ArrayList<HashMap<String, Object>> zzftp;
        private final String zzftq;
        private final HashMap<Object, Integer> zzftr;
        private boolean zzfts;
        private String zzftt;

        private zza(String[] strArr, String str) {
            this.zzfth = (String[]) zzbq.checkNotNull(strArr);
            this.zzftp = new ArrayList();
            this.zzftq = str;
            this.zzftr = new HashMap();
            this.zzfts = false;
            this.zzftt = null;
        }

        public zza zza(ContentValues contentValues) {
            zzc.zzu(contentValues);
            HashMap hashMap = new HashMap(contentValues.size());
            for (Entry entry : contentValues.valueSet()) {
                hashMap.put((String) entry.getKey(), entry.getValue());
            }
            return zza(hashMap);
        }

        public zza zza(HashMap<String, Object> hashMap) {
            int i;
            zzc.zzu(hashMap);
            if (this.zzftq == null) {
                i = -1;
            } else {
                Object obj = hashMap.get(this.zzftq);
                if (obj == null) {
                    i = -1;
                } else {
                    Integer num = (Integer) this.zzftr.get(obj);
                    if (num == null) {
                        this.zzftr.put(obj, Integer.valueOf(this.zzftp.size()));
                        i = -1;
                    } else {
                        i = num.intValue();
                    }
                }
            }
            if (i == -1) {
                this.zzftp.add(hashMap);
            } else {
                this.zzftp.remove(i);
                this.zzftp.add(i, hashMap);
            }
            this.zzfts = false;
            return this;
        }

        public final DataHolder zzcb(int i) {
            return new DataHolder(this);
        }
    }

    public static class zzb extends RuntimeException {
        public zzb(String str) {
            super(str);
        }
    }

    DataHolder(int i, String[] strArr, CursorWindow[] cursorWindowArr, int i2, Bundle bundle) {
        this.mClosed = false;
        this.zzftn = true;
        this.zzdzm = i;
        this.zzfth = strArr;
        this.zzftj = cursorWindowArr;
        this.zzfcq = i2;
        this.zzftk = bundle;
    }

    private DataHolder(zza com_google_android_gms_common_data_DataHolder_zza, int i, Bundle bundle) {
        this(com_google_android_gms_common_data_DataHolder_zza.zzfth, zza(com_google_android_gms_common_data_DataHolder_zza, -1), i, null);
    }

    private DataHolder(String[] strArr, CursorWindow[] cursorWindowArr, int i, Bundle bundle) {
        this.mClosed = false;
        this.zzftn = true;
        this.zzdzm = 1;
        this.zzfth = (String[]) zzbq.checkNotNull(strArr);
        this.zzftj = (CursorWindow[]) zzbq.checkNotNull(cursorWindowArr);
        this.zzfcq = i;
        this.zzftk = bundle;
        zzajn();
    }

    private static CursorWindow[] zza(zza com_google_android_gms_common_data_DataHolder_zza, int i) {
        int i2 = 0;
        if (com_google_android_gms_common_data_DataHolder_zza.zzfth.length == 0) {
            return new CursorWindow[0];
        }
        List zzb = com_google_android_gms_common_data_DataHolder_zza.zzftp;
        int size = zzb.size();
        CursorWindow cursorWindow = new CursorWindow(false);
        ArrayList arrayList = new ArrayList();
        arrayList.add(cursorWindow);
        cursorWindow.setNumColumns(com_google_android_gms_common_data_DataHolder_zza.zzfth.length);
        int i3 = 0;
        int i4 = 0;
        while (i3 < size) {
            try {
                int i5;
                int i6;
                CursorWindow cursorWindow2;
                if (!cursorWindow.allocRow()) {
                    Log.d("DataHolder", "Allocating additional cursor window for large data set (row " + i3 + ")");
                    cursorWindow = new CursorWindow(false);
                    cursorWindow.setStartPosition(i3);
                    cursorWindow.setNumColumns(com_google_android_gms_common_data_DataHolder_zza.zzfth.length);
                    arrayList.add(cursorWindow);
                    if (!cursorWindow.allocRow()) {
                        Log.e("DataHolder", "Unable to allocate row to hold data.");
                        arrayList.remove(cursorWindow);
                        return (CursorWindow[]) arrayList.toArray(new CursorWindow[arrayList.size()]);
                    }
                }
                Map map = (Map) zzb.get(i3);
                boolean z = true;
                for (int i7 = 0; i7 < com_google_android_gms_common_data_DataHolder_zza.zzfth.length && z; i7++) {
                    String str = com_google_android_gms_common_data_DataHolder_zza.zzfth[i7];
                    Object obj = map.get(str);
                    if (obj == null) {
                        z = cursorWindow.putNull(i3, i7);
                    } else if (obj instanceof String) {
                        z = cursorWindow.putString((String) obj, i3, i7);
                    } else if (obj instanceof Long) {
                        z = cursorWindow.putLong(((Long) obj).longValue(), i3, i7);
                    } else if (obj instanceof Integer) {
                        z = cursorWindow.putLong((long) ((Integer) obj).intValue(), i3, i7);
                    } else if (obj instanceof Boolean) {
                        z = cursorWindow.putLong(((Boolean) obj).booleanValue() ? 1 : 0, i3, i7);
                    } else if (obj instanceof byte[]) {
                        z = cursorWindow.putBlob((byte[]) obj, i3, i7);
                    } else if (obj instanceof Double) {
                        z = cursorWindow.putDouble(((Double) obj).doubleValue(), i3, i7);
                    } else if (obj instanceof Float) {
                        z = cursorWindow.putDouble((double) ((Float) obj).floatValue(), i3, i7);
                    } else {
                        String valueOf = String.valueOf(obj);
                        throw new IllegalArgumentException(new StringBuilder((String.valueOf(str).length() + 32) + String.valueOf(valueOf).length()).append("Unsupported object for column ").append(str).append(": ").append(valueOf).toString());
                    }
                }
                if (z) {
                    i5 = i3;
                    i6 = 0;
                    cursorWindow2 = cursorWindow;
                } else if (i4 != 0) {
                    throw new zzb("Could not add the value to a new CursorWindow. The size of value may be larger than what a CursorWindow can handle.");
                } else {
                    Log.d("DataHolder", "Couldn't populate window data for row " + i3 + " - allocating new window.");
                    cursorWindow.freeLastRow();
                    CursorWindow cursorWindow3 = new CursorWindow(false);
                    cursorWindow3.setStartPosition(i3);
                    cursorWindow3.setNumColumns(com_google_android_gms_common_data_DataHolder_zza.zzfth.length);
                    arrayList.add(cursorWindow3);
                    i5 = i3 - 1;
                    cursorWindow2 = cursorWindow3;
                    i6 = 1;
                }
                i4 = i6;
                cursorWindow = cursorWindow2;
                i3 = i5 + 1;
            } catch (RuntimeException e) {
                RuntimeException runtimeException = e;
                i3 = arrayList.size();
                while (i2 < i3) {
                    ((CursorWindow) arrayList.get(i2)).close();
                    i2++;
                }
                throw runtimeException;
            }
        }
        return (CursorWindow[]) arrayList.toArray(new CursorWindow[arrayList.size()]);
    }

    public static zza zzb(String[] strArr) {
        return new zza(strArr);
    }

    public static DataHolder zzca(int i) {
        return new DataHolder(zzfto, i, null);
    }

    private final void zzh(String str, int i) {
        if (this.zzfti == null || !this.zzfti.containsKey(str)) {
            String str2 = "No such column: ";
            String valueOf = String.valueOf(str);
            throw new IllegalArgumentException(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
        } else if (isClosed()) {
            throw new IllegalArgumentException("Buffer is closed.");
        } else if (i < 0 || i >= this.zzftm) {
            throw new CursorIndexOutOfBoundsException(i, this.zzftm);
        }
    }

    public final void close() {
        synchronized (this) {
            if (!this.mClosed) {
                this.mClosed = true;
                for (CursorWindow close : this.zzftj) {
                    close.close();
                }
            }
        }
    }

    protected final void finalize() throws Throwable {
        try {
            if (this.zzftn && this.zzftj.length > 0 && !isClosed()) {
                close();
                String obj = toString();
                Log.e("DataBuffer", new StringBuilder(String.valueOf(obj).length() + 178).append("Internal data leak within a DataBuffer object detected!  Be sure to explicitly call release() on all DataBuffer extending objects when you are done with them. (internal object: ").append(obj).append(")").toString());
            }
            super.finalize();
        } catch (Throwable th) {
            super.finalize();
        }
    }

    public final int getCount() {
        return this.zzftm;
    }

    public final int getStatusCode() {
        return this.zzfcq;
    }

    public final boolean isClosed() {
        boolean z;
        synchronized (this) {
            z = this.mClosed;
        }
        return z;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbem.zze(parcel);
        zzbem.zza(parcel, 1, this.zzfth, false);
        zzbem.zza(parcel, 2, this.zzftj, i, false);
        zzbem.zzc(parcel, 3, this.zzfcq);
        zzbem.zza(parcel, 4, this.zzftk, false);
        zzbem.zzc(parcel, 1000, this.zzdzm);
        zzbem.zzai(parcel, zze);
        if ((i & 1) != 0) {
            close();
        }
    }

    public final void zza(String str, int i, int i2, CharArrayBuffer charArrayBuffer) {
        zzh(str, i);
        this.zzftj[i2].copyStringToBuffer(i, this.zzfti.getInt(str), charArrayBuffer);
    }

    public final Bundle zzafx() {
        return this.zzftk;
    }

    public final void zzajn() {
        int i;
        int i2 = 0;
        this.zzfti = new Bundle();
        for (i = 0; i < this.zzfth.length; i++) {
            this.zzfti.putInt(this.zzfth[i], i);
        }
        this.zzftl = new int[this.zzftj.length];
        i = 0;
        while (i2 < this.zzftj.length) {
            this.zzftl[i2] = i;
            i += this.zzftj[i2].getNumRows() - (i - this.zzftj[i2].getStartPosition());
            i2++;
        }
        this.zzftm = i;
    }

    public final long zzb(String str, int i, int i2) {
        zzh(str, i);
        return this.zzftj[i2].getLong(i, this.zzfti.getInt(str));
    }

    public final int zzbz(int i) {
        int i2 = 0;
        boolean z = i >= 0 && i < this.zzftm;
        zzbq.checkState(z);
        while (i2 < this.zzftl.length) {
            if (i < this.zzftl[i2]) {
                i2--;
                break;
            }
            i2++;
        }
        return i2 == this.zzftl.length ? i2 - 1 : i2;
    }

    public final int zzc(String str, int i, int i2) {
        zzh(str, i);
        return this.zzftj[i2].getInt(i, this.zzfti.getInt(str));
    }

    public final String zzd(String str, int i, int i2) {
        zzh(str, i);
        return this.zzftj[i2].getString(i, this.zzfti.getInt(str));
    }

    public final boolean zze(String str, int i, int i2) {
        zzh(str, i);
        return Long.valueOf(this.zzftj[i2].getLong(i, this.zzfti.getInt(str))).longValue() == 1;
    }

    public final float zzf(String str, int i, int i2) {
        zzh(str, i);
        return this.zzftj[i2].getFloat(i, this.zzfti.getInt(str));
    }

    public final boolean zzfv(String str) {
        return this.zzfti.containsKey(str);
    }

    public final byte[] zzg(String str, int i, int i2) {
        zzh(str, i);
        return this.zzftj[i2].getBlob(i, this.zzfti.getInt(str));
    }

    public final boolean zzh(String str, int i, int i2) {
        zzh(str, i);
        return this.zzftj[i2].isNull(i, this.zzfti.getInt(str));
    }
}
