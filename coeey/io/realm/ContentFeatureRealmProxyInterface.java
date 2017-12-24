package io.realm;

import com.cooey.common.vo.careplan.Template;

public interface ContentFeatureRealmProxyInterface {
    String realmGet$id();

    Template realmGet$template();

    String realmGet$tenantId();

    String realmGet$type();

    void realmSet$id(String str);

    void realmSet$template(Template template);

    void realmSet$tenantId(String str);

    void realmSet$type(String str);
}
