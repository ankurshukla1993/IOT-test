package io.realm;

import com.cooey.common.vo.careplan.Limits;

public interface VitalFeatureRealmProxyInterface {
    String realmGet$id();

    Limits realmGet$limits();

    String realmGet$tenantId();

    String realmGet$type();

    void realmSet$id(String str);

    void realmSet$limits(Limits limits);

    void realmSet$tenantId(String str);

    void realmSet$type(String str);
}
