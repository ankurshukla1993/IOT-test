package io.realm;

import com.cooey.common.vo.Limit;

public interface UserSettingsRealmProxyInterface {
    String realmGet$defaultNotificationModeValue();

    boolean realmGet$eventNotificationEnabled();

    Boolean realmGet$notificationEnabled();

    boolean realmGet$vitalLimitNotificationEnabled();

    RealmList<Limit> realmGet$vitalLimits();

    boolean realmGet$vitalNotificationEnabled();

    void realmSet$defaultNotificationModeValue(String str);

    void realmSet$eventNotificationEnabled(boolean z);

    void realmSet$notificationEnabled(Boolean bool);

    void realmSet$vitalLimitNotificationEnabled(boolean z);

    void realmSet$vitalLimits(RealmList<Limit> realmList);

    void realmSet$vitalNotificationEnabled(boolean z);
}
