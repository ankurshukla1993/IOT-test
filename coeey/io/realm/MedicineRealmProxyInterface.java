package io.realm;

import com.cooey.common.vo.Reminder;

public interface MedicineRealmProxyInterface {
    String realmGet$active();

    long realmGet$addedOn();

    String realmGet$applicationId();

    boolean realmGet$archived();

    String realmGet$createdOn();

    String realmGet$description();

    String realmGet$externalId();

    String realmGet$id();

    String realmGet$medicineId();

    String realmGet$name();

    String realmGet$notes();

    RealmList<Reminder> realmGet$reminders();

    String realmGet$tenantId();

    long realmGet$toBeTakenTill();

    String realmGet$updatedOn();

    String realmGet$userId();

    void realmSet$active(String str);

    void realmSet$addedOn(long j);

    void realmSet$applicationId(String str);

    void realmSet$archived(boolean z);

    void realmSet$createdOn(String str);

    void realmSet$description(String str);

    void realmSet$externalId(String str);

    void realmSet$id(String str);

    void realmSet$medicineId(String str);

    void realmSet$name(String str);

    void realmSet$notes(String str);

    void realmSet$reminders(RealmList<Reminder> realmList);

    void realmSet$tenantId(String str);

    void realmSet$toBeTakenTill(long j);

    void realmSet$updatedOn(String str);

    void realmSet$userId(String str);
}
