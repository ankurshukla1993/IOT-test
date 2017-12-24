package com.cooey.common.vo;

import android.os.Parcel;
import android.os.Parcelable;
import org.parceler.IdentityCollection;
import org.parceler.ParcelWrapper;
import org.parceler.ParcelerRuntimeException;

public class RealmBoolean$$Parcelable implements Parcelable, ParcelWrapper<RealmBoolean> {
    public static final RealmBoolean$$Parcelable$Creator$$1 CREATOR = new RealmBoolean$$Parcelable$Creator$$1();
    private RealmBoolean realmBoolean$$0;

    public RealmBoolean$$Parcelable(RealmBoolean realmBoolean$$2) {
        this.realmBoolean$$0 = realmBoolean$$2;
    }

    public void writeToParcel(Parcel parcel$$0, int flags) {
        write(this.realmBoolean$$0, parcel$$0, flags, new IdentityCollection());
    }

    public static void write(RealmBoolean realmBoolean$$1, Parcel parcel$$1, int flags$$0, IdentityCollection identityMap$$0) {
        int identity$$0 = identityMap$$0.getKey(realmBoolean$$1);
        if (identity$$0 != -1) {
            parcel$$1.writeInt(identity$$0);
            return;
        }
        parcel$$1.writeInt(identityMap$$0.put(realmBoolean$$1));
        parcel$$1.writeInt(realmBoolean$$1.getValue() ? 1 : 0);
    }

    public int describeContents() {
        return 0;
    }

    public RealmBoolean getParcel() {
        return this.realmBoolean$$0;
    }

    public static RealmBoolean read(Parcel parcel$$3, IdentityCollection identityMap$$1) {
        boolean z = true;
        int identity$$1 = parcel$$3.readInt();
        if (!identityMap$$1.containsKey(identity$$1)) {
            int reservation$$0 = identityMap$$1.reserve();
            RealmBoolean realmBoolean$$3 = new RealmBoolean();
            identityMap$$1.put(reservation$$0, realmBoolean$$3);
            if (parcel$$3.readInt() != 1) {
                z = false;
            }
            realmBoolean$$3.setValue(z);
            return realmBoolean$$3;
        } else if (!identityMap$$1.isReserved(identity$$1)) {
            return (RealmBoolean) identityMap$$1.get(identity$$1);
        } else {
            throw new ParcelerRuntimeException("An instance loop was detected whild building Parcelable and deseralization cannot continue.  This error is most likely due to using @ParcelConstructor or @ParcelFactory.");
        }
    }
}
