package com.google.android.gms.iid;

import android.os.Build.VERSION;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.RemoteException;
import com.google.android.gms.common.internal.ReflectedParcelable;

public class MessengerCompat implements ReflectedParcelable {
    public static final Creator<MessengerCompat> CREATOR = new zzk();
    private Messenger zzicr;
    private zzi zzics;

    public MessengerCompat(IBinder iBinder) {
        if (VERSION.SDK_INT >= 21) {
            this.zzicr = new Messenger(iBinder);
            return;
        }
        zzi com_google_android_gms_iid_zzi;
        if (iBinder == null) {
            com_google_android_gms_iid_zzi = null;
        } else {
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.iid.IMessengerCompat");
            com_google_android_gms_iid_zzi = queryLocalInterface instanceof zzi ? (zzi) queryLocalInterface : new zzj(iBinder);
        }
        this.zzics = com_google_android_gms_iid_zzi;
    }

    private final IBinder getBinder() {
        return this.zzicr != null ? this.zzicr.getBinder() : this.zzics.asBinder();
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        boolean z = false;
        if (obj != null) {
            try {
                z = getBinder().equals(((MessengerCompat) obj).getBinder());
            } catch (ClassCastException e) {
            }
        }
        return z;
    }

    public int hashCode() {
        return getBinder().hashCode();
    }

    public final void send(Message message) throws RemoteException {
        if (this.zzicr != null) {
            this.zzicr.send(message);
        } else {
            this.zzics.send(message);
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        if (this.zzicr != null) {
            parcel.writeStrongBinder(this.zzicr.getBinder());
        } else {
            parcel.writeStrongBinder(this.zzics.asBinder());
        }
    }
}
