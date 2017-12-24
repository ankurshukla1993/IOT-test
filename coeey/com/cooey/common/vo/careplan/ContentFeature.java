package com.cooey.common.vo.careplan;

import io.realm.ContentFeatureRealmProxyInterface;
import io.realm.RealmObject;
import io.realm.internal.RealmObjectProxy;
import java.io.Serializable;

public class ContentFeature extends RealmObject implements Serializable, ContentFeatureRealmProxyInterface {
    private String id;
    private Template template;
    private String tenantId;
    private String type;

    public String realmGet$id() {
        return this.id;
    }

    public Template realmGet$template() {
        return this.template;
    }

    public String realmGet$tenantId() {
        return this.tenantId;
    }

    public String realmGet$type() {
        return this.type;
    }

    public void realmSet$id(String str) {
        this.id = str;
    }

    public void realmSet$template(Template template) {
        this.template = template;
    }

    public void realmSet$tenantId(String str) {
        this.tenantId = str;
    }

    public void realmSet$type(String str) {
        this.type = str;
    }

    public ContentFeature() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
    }

    public String getType() {
        return realmGet$type();
    }

    public void setType(String type) {
        realmSet$type(type);
    }

    public String getId() {
        return realmGet$id();
    }

    public void setId(String id) {
        realmSet$id(id);
    }

    public String getTenantId() {
        return realmGet$tenantId();
    }

    public void setTenantId(String tenantId) {
        realmSet$tenantId(tenantId);
    }

    public Template getTemplate() {
        return realmGet$template();
    }

    public void setTemplate(Template template) {
        realmSet$template(template);
    }

    public String toString() {
        return "ContentFeature{id='" + realmGet$id() + '\'' + ", type='" + realmGet$type() + '\'' + ", tenantId='" + realmGet$tenantId() + '\'' + ", template=" + realmGet$template() + '}';
    }
}
