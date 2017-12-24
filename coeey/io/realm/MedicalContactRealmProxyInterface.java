package io.realm;

import com.cooey.common.vo.RealmString;

public interface MedicalContactRealmProxyInterface {
    String realmGet$email();

    String realmGet$id();

    RealmList<RealmString> realmGet$mobileNumbers();

    String realmGet$name();

    String realmGet$tenantId();

    String realmGet$type();

    String realmGet$userId();

    void realmSet$email(String str);

    void realmSet$id(String str);

    void realmSet$mobileNumbers(RealmList<RealmString> realmList);

    void realmSet$name(String str);

    void realmSet$tenantId(String str);

    void realmSet$type(String str);

    void realmSet$userId(String str);
}
