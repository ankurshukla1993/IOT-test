package io.realm;

import com.cooey.common.vo.RealmBoolean;

public interface ReminderRealmProxyInterface {
    RealmList<RealmBoolean> realmGet$activeDays();

    String realmGet$id();

    String realmGet$timeOfDay();

    void realmSet$activeDays(RealmList<RealmBoolean> realmList);

    void realmSet$id(String str);

    void realmSet$timeOfDay(String str);
}
