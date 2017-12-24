package com.cooey.common.realm_store;

import android.content.Context;
import com.cooey.common.vo.Height;
import com.cooey.common.vo.ProtoRealm;
import com.cooey.common.vo.User;
import com.cooey.common.vo.UserSettings;
import com.facebook.share.internal.ShareConstants;
import io.realm.Realm;
import io.realm.Realm.Transaction;
import io.realm.RealmResults;
import java.util.ArrayList;
import java.util.List;

public class UserStore {
    private Realm realm;

    public UserStore(Context context) {
        this.realm = ProtoRealm.getInstance(context).getRealm();
    }

    public static UserStore getInstance(Context context) {
        return new UserStore(context);
    }

    public void writeToUser(final User user) {
        try {
            this.realm.executeTransaction(new Transaction() {
                public void execute(Realm realm1) {
                    realm1.copyToRealmOrUpdate(user);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writeToUsers(final List<User> users) {
        try {
            this.realm.executeTransaction(new Transaction() {
                public void execute(Realm realm1) {
                    realm1.copyToRealmOrUpdate(users);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public RealmResults<User> getAllUsers() {
        return this.realm.where(User.class).findAll();
    }

    public RealmResults<User> getAllUsersAsync() {
        return this.realm.where(User.class).findAllAsync();
    }

    public ArrayList<User> getAllUsersArrayList() {
        return new ArrayList(this.realm.where(User.class).findAll());
    }

    public void close() {
        this.realm.close();
    }

    public User getUser(String id) {
        return (User) this.realm.where(User.class).equalTo(ShareConstants.WEB_DIALOG_PARAM_ID, id).findFirst();
    }

    public void updateUserSettingsForUser(UserSettings userSettings, String patientId) {
        User user = (User) this.realm.where(User.class).equalTo(ShareConstants.WEB_DIALOG_PARAM_ID, patientId).findFirst();
        this.realm.beginTransaction();
        user.setUserSettings(userSettings);
        this.realm.commitTransaction();
    }

    public void updateUser(final Height height, final String dateOfBirth, String patientId) {
        final User user = (User) this.realm.where(User.class).equalTo(ShareConstants.WEB_DIALOG_PARAM_ID, patientId).findFirst();
        this.realm.executeTransaction(new Transaction() {
            public void execute(Realm realm) {
                user.setHeight((Height) realm.copyToRealm(height));
                user.setDateOfBirth(dateOfBirth);
                realm.copyToRealmOrUpdate(user);
            }
        });
    }
}
