package io.realm;

import com.cooey.common.vo.RealmString;

public interface AllergyRealmProxyInterface {
    String realmGet$levelValue();

    String realmGet$name();

    RealmList<RealmString> realmGet$symptoms();

    void realmSet$levelValue(String str);

    void realmSet$name(String str);

    void realmSet$symptoms(RealmList<RealmString> realmList);
}
