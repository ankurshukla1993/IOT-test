package com.cooey.common.vo;

import android.os.Parcel;
import android.os.Parcelable;
import com.cooey.common.converter.RealmListParcelConverter;
import org.parceler.IdentityCollection;
import org.parceler.ParcelWrapper;
import org.parceler.ParcelerRuntimeException;

public class Reminder$$Parcelable implements Parcelable, ParcelWrapper<Reminder> {
    public static final Reminder$$Parcelable$Creator$$2 CREATOR = new Reminder$$Parcelable$Creator$$2();
    private Reminder reminder$$0;

    public Reminder$$Parcelable(Reminder reminder$$2) {
        this.reminder$$0 = reminder$$2;
    }

    public void writeToParcel(Parcel parcel$$0, int flags) {
        write(this.reminder$$0, parcel$$0, flags, new IdentityCollection());
    }

    public static void write(Reminder reminder$$1, Parcel parcel$$1, int flags$$0, IdentityCollection identityMap$$0) {
        int identity$$0 = identityMap$$0.getKey(reminder$$1);
        if (identity$$0 != -1) {
            parcel$$1.writeInt(identity$$0);
            return;
        }
        parcel$$1.writeInt(identityMap$$0.put(reminder$$1));
        new RealmListParcelConverter().toParcel(reminder$$1.realmGet$activeDays(), parcel$$1);
        parcel$$1.writeInt(reminder$$1.getJobId());
        parcel$$1.writeString(reminder$$1.getItemId());
        parcel$$1.writeString(reminder$$1.getId());
        parcel$$1.writeString(reminder$$1.getTimeOfDay());
    }

    public int describeContents() {
        return 0;
    }

    public Reminder getParcel() {
        return this.reminder$$0;
    }

    public static Reminder read(Parcel parcel$$3, IdentityCollection identityMap$$1) {
        int identity$$1 = parcel$$3.readInt();
        if (!identityMap$$1.containsKey(identity$$1)) {
            int reservation$$0 = identityMap$$1.reserve();
            Reminder reminder$$3 = new Reminder();
            identityMap$$1.put(reservation$$0, reminder$$3);
            reminder$$3.setActiveDays(new RealmListParcelConverter().fromParcel(parcel$$3));
            reminder$$3.setJobId(parcel$$3.readInt());
            reminder$$3.setItemId(parcel$$3.readString());
            reminder$$3.setId(parcel$$3.readString());
            reminder$$3.setTimeOfDay(parcel$$3.readString());
            return reminder$$3;
        } else if (!identityMap$$1.isReserved(identity$$1)) {
            return (Reminder) identityMap$$1.get(identity$$1);
        } else {
            throw new ParcelerRuntimeException("An instance loop was detected whild building Parcelable and deseralization cannot continue.  This error is most likely due to using @ParcelConstructor or @ParcelFactory.");
        }
    }
}
