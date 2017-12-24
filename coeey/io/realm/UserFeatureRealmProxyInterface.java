package io.realm;

import com.cooey.common.vo.RealmBoolean;

public interface UserFeatureRealmProxyInterface {
    RealmBoolean realmGet$realmBoolean();

    String realmGet$settingName();

    void realmSet$realmBoolean(RealmBoolean realmBoolean);

    void realmSet$settingName(String str);
}
