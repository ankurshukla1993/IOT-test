package com.cooey.common.realm_store;

import android.content.Context;
import com.cooey.common.vo.Guardian;
import com.cooey.common.vo.ProtoRealm;
import com.cooey.common.vo.UserSettings;
import com.facebook.share.internal.ShareConstants;
import io.realm.Realm;
import io.realm.Realm.Transaction;
import io.realm.RealmResults;
import java.util.ArrayList;
import java.util.List;

public class GuardianStore {
    private Realm realm;

    public GuardianStore(Context context) {
        this.realm = ProtoRealm.getInstance(context).getRealm();
    }

    public static GuardianStore getInstance(Context context) {
        return new GuardianStore(context);
    }

    public void writeToGuardian(final Guardian guardian) {
        this.realm.executeTransaction(new Transaction() {
            public void execute(Realm realm1) {
                realm1.copyToRealmOrUpdate(guardian);
            }
        });
    }

    public void writeToGuardian(final List<Guardian> guardianList) {
        this.realm.executeTransaction(new Transaction() {
            public void execute(Realm realm1) {
                realm1.copyToRealmOrUpdate(guardianList);
            }
        });
    }

    public RealmResults<Guardian> getAllGuardians() {
        return this.realm.where(Guardian.class).findAll();
    }

    public ArrayList<Guardian> getAllGuardianListByUserId(String id) {
        return new ArrayList(this.realm.where(Guardian.class).equalTo("patientId", id).findAll());
    }

    public ArrayList<Guardian> getAllGuardiansArrayList() {
        return new ArrayList(this.realm.where(Guardian.class).findAll());
    }

    public void close() {
        this.realm.close();
    }

    public Guardian getGuardian(String id) {
        return (Guardian) this.realm.where(Guardian.class).equalTo(ShareConstants.WEB_DIALOG_PARAM_ID, id).findFirst();
    }

    public void updateGuardianSettingsForGuardian(UserSettings userSettings, String patientId) {
        Guardian guardian = (Guardian) this.realm.where(Guardian.class).equalTo(ShareConstants.WEB_DIALOG_PARAM_ID, patientId).findFirst();
        this.realm.beginTransaction();
        guardian.setUserSettings(userSettings);
        this.realm.commitTransaction();
    }
}
