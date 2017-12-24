package com.cooey.common.vo;

import android.os.Parcel;
import android.os.Parcelable;
import com.cooey.common.converter.RealmListParcelConverter;
import org.parceler.IdentityCollection;
import org.parceler.ParcelWrapper;
import org.parceler.ParcelerRuntimeException;

public class Medicine$$Parcelable implements Parcelable, ParcelWrapper<Medicine> {
    public static final Medicine$$Parcelable$Creator$$0 CREATOR = new Medicine$$Parcelable$Creator$$0();
    private Medicine medicine$$0;

    public Medicine$$Parcelable(Medicine medicine$$2) {
        this.medicine$$0 = medicine$$2;
    }

    public void writeToParcel(Parcel parcel$$0, int flags) {
        write(this.medicine$$0, parcel$$0, flags, new IdentityCollection());
    }

    public static void write(Medicine medicine$$1, Parcel parcel$$1, int flags$$0, IdentityCollection identityMap$$0) {
        int identity$$0 = identityMap$$0.getKey(medicine$$1);
        if (identity$$0 != -1) {
            parcel$$1.writeInt(identity$$0);
            return;
        }
        parcel$$1.writeInt(identityMap$$0.put(medicine$$1));
        new RealmListParcelConverter().toParcel(medicine$$1.realmGet$reminders(), parcel$$1);
        parcel$$1.writeString(medicine$$1.getNotes());
        parcel$$1.writeString(medicine$$1.getMedicineId());
        parcel$$1.writeString(medicine$$1.getDescription());
        parcel$$1.writeString(medicine$$1.getExternalId());
        parcel$$1.writeString(medicine$$1.getActive());
        parcel$$1.writeString(medicine$$1.getUpdatedOn());
        parcel$$1.writeString(medicine$$1.getUserId());
        parcel$$1.writeString(medicine$$1.getCreatedOn());
        parcel$$1.writeLong(medicine$$1.getAddedOn());
        parcel$$1.writeInt(medicine$$1.isArchived() ? 1 : 0);
        parcel$$1.writeString(medicine$$1.getName());
        parcel$$1.writeString(medicine$$1.getTenantId());
        parcel$$1.writeString(medicine$$1.getId());
        parcel$$1.writeLong(medicine$$1.getToBeTakenTill());
        parcel$$1.writeString(medicine$$1.getApplicationId());
    }

    public int describeContents() {
        return 0;
    }

    public Medicine getParcel() {
        return this.medicine$$0;
    }

    public static Medicine read(Parcel parcel$$3, IdentityCollection identityMap$$1) {
        boolean z = true;
        int identity$$1 = parcel$$3.readInt();
        if (!identityMap$$1.containsKey(identity$$1)) {
            int reservation$$0 = identityMap$$1.reserve();
            Medicine medicine$$3 = new Medicine();
            identityMap$$1.put(reservation$$0, medicine$$3);
            medicine$$3.setReminders(new RealmListParcelConverter().fromParcel(parcel$$3));
            medicine$$3.setNotes(parcel$$3.readString());
            medicine$$3.setMedicineId(parcel$$3.readString());
            medicine$$3.setDescription(parcel$$3.readString());
            medicine$$3.setExternalId(parcel$$3.readString());
            medicine$$3.setActive(parcel$$3.readString());
            medicine$$3.setUpdatedOn(parcel$$3.readString());
            medicine$$3.setUserId(parcel$$3.readString());
            medicine$$3.setCreatedOn(parcel$$3.readString());
            medicine$$3.setAddedOn(parcel$$3.readLong());
            if (parcel$$3.readInt() != 1) {
                z = false;
            }
            medicine$$3.setArchived(z);
            medicine$$3.setName(parcel$$3.readString());
            medicine$$3.setTenantId(parcel$$3.readString());
            medicine$$3.setId(parcel$$3.readString());
            medicine$$3.setToBeTakenTill(parcel$$3.readLong());
            medicine$$3.setApplicationId(parcel$$3.readString());
            return medicine$$3;
        } else if (!identityMap$$1.isReserved(identity$$1)) {
            return (Medicine) identityMap$$1.get(identity$$1);
        } else {
            throw new ParcelerRuntimeException("An instance loop was detected whild building Parcelable and deseralization cannot continue.  This error is most likely due to using @ParcelConstructor or @ParcelFactory.");
        }
    }
}
