package com.cooey.common.vo.careplan;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.TemplateRealmProxyInterface;
import io.realm.internal.RealmObjectProxy;
import java.io.Serializable;

public class Template extends RealmObject implements Serializable, TemplateRealmProxyInterface {
    private String description;
    private RealmList<UrlIdentifier> urlIdentifiers;

    public String realmGet$description() {
        return this.description;
    }

    public RealmList realmGet$urlIdentifiers() {
        return this.urlIdentifiers;
    }

    public void realmSet$description(String str) {
        this.description = str;
    }

    public void realmSet$urlIdentifiers(RealmList realmList) {
        this.urlIdentifiers = realmList;
    }

    public Template() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
        realmSet$urlIdentifiers(new RealmList());
    }

    public String getDescription() {
        return realmGet$description();
    }

    public void setDescription(String description) {
        realmSet$description(description);
    }

    public RealmList<UrlIdentifier> getUrlIdentifiers() {
        return realmGet$urlIdentifiers();
    }

    public void setUrlIdentifiers(RealmList<UrlIdentifier> urlIdentifiers) {
        realmSet$urlIdentifiers(urlIdentifiers);
    }

    public String toString() {
        return "Template{description='" + realmGet$description() + '\'' + ", urlIdentifiers=" + realmGet$urlIdentifiers() + '}';
    }
}
