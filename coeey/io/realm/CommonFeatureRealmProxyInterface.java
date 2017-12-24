package io.realm;

import com.cooey.common.vo.Vital;
import com.cooey.common.vo.careplan.Limits;

public interface CommonFeatureRealmProxyInterface {
    String realmGet$id();

    Limits realmGet$limits();

    String realmGet$name();

    String realmGet$parameters();

    String realmGet$tenantId();

    String realmGet$type();

    Vital realmGet$vitalParameters();

    void realmSet$id(String str);

    void realmSet$limits(Limits limits);

    void realmSet$name(String str);

    void realmSet$parameters(String str);

    void realmSet$tenantId(String str);

    void realmSet$type(String str);

    void realmSet$vitalParameters(Vital vital);
}
