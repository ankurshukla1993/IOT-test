package com.cooey.common.realm_store;

import android.content.Context;
import com.cooey.common.vo.DietTemplate;
import com.cooey.common.vo.ProtoRealm;
import com.cooey.common.vo.diet.weekdays.MealTemplate;
import io.realm.Realm;
import io.realm.Realm.Transaction;
import io.realm.RealmResults;
import java.util.Calendar;

public class DietTemplateStore {
    private Realm realm;

    public DietTemplateStore(Context context) {
        this.realm = ProtoRealm.getInstance(context).getRealm();
    }

    public static DietTemplateStore getInstance(Context context) {
        return new DietTemplateStore(context);
    }

    public void writeToDietTemplate(final DietTemplate dietTemplate) {
        this.realm.executeTransaction(new Transaction() {
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(dietTemplate);
            }
        });
    }

    public void writeToDietTemplateAndTimeline(final DietTemplate dietTemplate) {
        this.realm.executeTransaction(new Transaction() {
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(dietTemplate);
            }
        });
    }

    public RealmResults<DietTemplate> getAllDietTemplate() {
        return this.realm.where(DietTemplate.class).findAll();
    }

    public DietTemplate getFirstDietTemplate() {
        if (this.realm.where(DietTemplate.class).findFirst() != null) {
            return (DietTemplate) this.realm.where(DietTemplate.class).findFirst();
        }
        return null;
    }

    public MealTemplate getMealTemplateForToday() {
        int day = Calendar.getInstance().get(7);
        return null;
    }

    public void close() {
        this.realm.close();
    }
}
