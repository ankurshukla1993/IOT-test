package com.google.android.gms.dynamic;

import android.content.Intent;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.internal.zzee;
import com.google.android.gms.internal.zzef;

public abstract class zzl extends zzee implements zzk {
    public zzl() {
        attachInterface(this, "com.google.android.gms.dynamic.IFragmentWrapper");
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        IObjectWrapper iObjectWrapper = null;
        if (zza(i, parcel, parcel2, i2)) {
            return true;
        }
        IInterface zzapl;
        int id;
        boolean retainInstance;
        IBinder readStrongBinder;
        switch (i) {
            case 2:
                zzapl = zzapl();
                parcel2.writeNoException();
                zzef.zza(parcel2, zzapl);
                break;
            case 3:
                Parcelable arguments = getArguments();
                parcel2.writeNoException();
                zzef.zzb(parcel2, arguments);
                break;
            case 4:
                id = getId();
                parcel2.writeNoException();
                parcel2.writeInt(id);
                break;
            case 5:
                zzapl = zzapm();
                parcel2.writeNoException();
                zzef.zza(parcel2, zzapl);
                break;
            case 6:
                zzapl = zzapn();
                parcel2.writeNoException();
                zzef.zza(parcel2, zzapl);
                break;
            case 7:
                retainInstance = getRetainInstance();
                parcel2.writeNoException();
                zzef.zza(parcel2, retainInstance);
                break;
            case 8:
                String tag = getTag();
                parcel2.writeNoException();
                parcel2.writeString(tag);
                break;
            case 9:
                zzapl = zzapo();
                parcel2.writeNoException();
                zzef.zza(parcel2, zzapl);
                break;
            case 10:
                id = getTargetRequestCode();
                parcel2.writeNoException();
                parcel2.writeInt(id);
                break;
            case 11:
                retainInstance = getUserVisibleHint();
                parcel2.writeNoException();
                zzef.zza(parcel2, retainInstance);
                break;
            case 12:
                zzapl = getView();
                parcel2.writeNoException();
                zzef.zza(parcel2, zzapl);
                break;
            case 13:
                retainInstance = isAdded();
                parcel2.writeNoException();
                zzef.zza(parcel2, retainInstance);
                break;
            case 14:
                retainInstance = isDetached();
                parcel2.writeNoException();
                zzef.zza(parcel2, retainInstance);
                break;
            case 15:
                retainInstance = isHidden();
                parcel2.writeNoException();
                zzef.zza(parcel2, retainInstance);
                break;
            case 16:
                retainInstance = isInLayout();
                parcel2.writeNoException();
                zzef.zza(parcel2, retainInstance);
                break;
            case 17:
                retainInstance = isRemoving();
                parcel2.writeNoException();
                zzef.zza(parcel2, retainInstance);
                break;
            case 18:
                retainInstance = isResumed();
                parcel2.writeNoException();
                zzef.zza(parcel2, retainInstance);
                break;
            case 19:
                retainInstance = isVisible();
                parcel2.writeNoException();
                zzef.zza(parcel2, retainInstance);
                break;
            case 20:
                readStrongBinder = parcel.readStrongBinder();
                if (readStrongBinder != null) {
                    zzapl = readStrongBinder.queryLocalInterface("com.google.android.gms.dynamic.IObjectWrapper");
                    iObjectWrapper = zzapl instanceof IObjectWrapper ? (IObjectWrapper) zzapl : new zzm(readStrongBinder);
                }
                zzv(iObjectWrapper);
                parcel2.writeNoException();
                break;
            case 21:
                setHasOptionsMenu(zzef.zza(parcel));
                parcel2.writeNoException();
                break;
            case 22:
                setMenuVisibility(zzef.zza(parcel));
                parcel2.writeNoException();
                break;
            case 23:
                setRetainInstance(zzef.zza(parcel));
                parcel2.writeNoException();
                break;
            case 24:
                setUserVisibleHint(zzef.zza(parcel));
                parcel2.writeNoException();
                break;
            case 25:
                startActivity((Intent) zzef.zza(parcel, Intent.CREATOR));
                parcel2.writeNoException();
                break;
            case 26:
                startActivityForResult((Intent) zzef.zza(parcel, Intent.CREATOR), parcel.readInt());
                parcel2.writeNoException();
                break;
            case 27:
                readStrongBinder = parcel.readStrongBinder();
                if (readStrongBinder != null) {
                    zzapl = readStrongBinder.queryLocalInterface("com.google.android.gms.dynamic.IObjectWrapper");
                    iObjectWrapper = zzapl instanceof IObjectWrapper ? (IObjectWrapper) zzapl : new zzm(readStrongBinder);
                }
                zzw(iObjectWrapper);
                parcel2.writeNoException();
                break;
            default:
                return false;
        }
        return true;
    }
}
