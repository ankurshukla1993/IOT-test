package io.realm;

import com.cooey.common.vo.careplan.UrlIdentifier;

public interface TemplateRealmProxyInterface {
    String realmGet$description();

    RealmList<UrlIdentifier> realmGet$urlIdentifiers();

    void realmSet$description(String str);

    void realmSet$urlIdentifiers(RealmList<UrlIdentifier> realmList);
}
