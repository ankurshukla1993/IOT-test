package com.google.firebase.iid;

import android.os.IBinder;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.iid.MessengerCompat;
import com.google.android.gms.iid.zzi;

final class zzr {
    private static String zznuo = zzi.class.getName();
    final Messenger zzicr;
    final MessengerCompat zznup;

    zzr(IBinder iBinder) throws RemoteException {
        String interfaceDescriptor = iBinder.getInterfaceDescriptor();
        if ("android.os.IMessenger".equals(interfaceDescriptor)) {
            this.zzicr = new Messenger(iBinder);
            this.zznup = null;
        } else if (zznuo.equals(interfaceDescriptor)) {
            this.zznup = new MessengerCompat(iBinder);
            this.zzicr = null;
        } else {
            String str = "MessengerIpcClient";
            String str2 = "Invalid interface descriptor: ";
            interfaceDescriptor = String.valueOf(interfaceDescriptor);
            Log.w(str, interfaceDescriptor.length() != 0 ? str2.concat(interfaceDescriptor) : new String(str2));
            throw new RemoteException();
        }
    }
}
