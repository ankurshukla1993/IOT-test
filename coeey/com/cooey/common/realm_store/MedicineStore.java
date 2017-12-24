package com.cooey.common.realm_store;

import android.content.Context;
import com.cooey.common.vo.Medicine;
import com.cooey.common.vo.ProtoRealm;
import com.cooey.common.vo.Reminder;
import com.facebook.share.internal.ShareConstants;
import io.realm.Realm;
import io.realm.Realm.Transaction;
import io.realm.RealmResults;
import java.util.Iterator;
import java.util.UUID;

public class MedicineStore {
    private Realm realm;

    public MedicineStore(Context context) {
        this.realm = ProtoRealm.getInstance(context).getRealm();
    }

    public static MedicineStore getInstance(Context context) {
        return new MedicineStore(context);
    }

    public MedicineStore(Realm realm) {
        this.realm = realm;
    }

    public static MedicineStore getInstance(Realm realm) {
        return new MedicineStore(realm);
    }

    public void writeToMedicine(final Medicine medicineObject) {
        this.realm.executeTransaction(new Transaction() {
            public void execute(Realm realm1) {
                realm1.copyToRealmOrUpdate(medicineObject);
                Iterator it = medicineObject.getReminders().iterator();
                while (it.hasNext()) {
                    Reminder reminder = (Reminder) it.next();
                    reminder.setId(UUID.randomUUID().toString());
                    reminder.setItemId(medicineObject.getId());
                }
            }
        });
    }

    public RealmResults<Medicine> getAllMedicines() {
        return this.realm.where(Medicine.class).findAll();
    }

    public Medicine getMedicineById(String id) {
        return (Medicine) this.realm.where(Medicine.class).equalTo(ShareConstants.WEB_DIALOG_PARAM_ID, id).findFirst();
    }

    public void close() {
        this.realm.close();
    }
}
