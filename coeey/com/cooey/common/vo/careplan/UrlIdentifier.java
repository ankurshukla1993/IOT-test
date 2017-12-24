package com.cooey.common.vo.careplan;

import io.realm.RealmObject;
import io.realm.UrlIdentifierRealmProxyInterface;
import io.realm.internal.RealmObjectProxy;
import java.io.Serializable;

public class UrlIdentifier extends RealmObject implements Serializable, UrlIdentifierRealmProxyInterface {
    public String url;

    public String realmGet$url() {
        return this.url;
    }

    public void realmSet$url(String str) {
        this.url = str;
    }

    public UrlIdentifier() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
    }

    public String getUrl() {
        return realmGet$url();
    }

    public void setUrl(String url) {
        realmSet$url(url);
    }
}
