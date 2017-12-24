package com.cooey.common.vo;

import io.realm.MedicalContactRealmProxyInterface;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.internal.RealmObjectProxy;

public class MedicalContact extends RealmObject implements MedicalContactRealmProxyInterface {
    private String email;
    @PrimaryKey
    private String id;
    private RealmList<RealmString> mobileNumbers;
    private String name;
    private String tenantId;
    private String type;
    private String userId;

    public String realmGet$email() {
        return this.email;
    }

    public String realmGet$id() {
        return this.id;
    }

    public RealmList realmGet$mobileNumbers() {
        return this.mobileNumbers;
    }

    public String realmGet$name() {
        return this.name;
    }

    public String realmGet$tenantId() {
        return this.tenantId;
    }

    public String realmGet$type() {
        return this.type;
    }

    public String realmGet$userId() {
        return this.userId;
    }

    public void realmSet$email(String str) {
        this.email = str;
    }

    public void realmSet$id(String str) {
        this.id = str;
    }

    public void realmSet$mobileNumbers(RealmList realmList) {
        this.mobileNumbers = realmList;
    }

    public void realmSet$name(String str) {
        this.name = str;
    }

    public void realmSet$tenantId(String str) {
        this.tenantId = str;
    }

    public void realmSet$type(String str) {
        this.type = str;
    }

    public void realmSet$userId(String str) {
        this.userId = str;
    }

    public MedicalContact(String tenantId, String id, String name, RealmList<RealmString> mobileNumbers, String email, String type, String userId) {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
        realmSet$tenantId(tenantId);
        realmSet$id(id);
        realmSet$name(name);
        realmSet$mobileNumbers(mobileNumbers);
        realmSet$email(email);
        realmSet$type(type);
        realmSet$userId(userId);
    }

    public MedicalContact() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
    }

    public String getTenantId() {
        return realmGet$tenantId();
    }

    public void setTenantId(String tenantId) {
        realmSet$tenantId(tenantId);
    }

    public String getId() {
        return realmGet$id();
    }

    public void setId(String id) {
        realmSet$id(id);
    }

    public String getName() {
        return realmGet$name();
    }

    public void setName(String name) {
        realmSet$name(name);
    }

    public RealmList<RealmString> getMobileNumbers() {
        return realmGet$mobileNumbers();
    }

    public void setMobileNumbers(RealmList<RealmString> mobileNumbers) {
        realmSet$mobileNumbers(mobileNumbers);
    }

    public String getEmail() {
        return realmGet$email();
    }

    public void setEmail(String email) {
        realmSet$email(email);
    }

    public String getType() {
        return realmGet$type();
    }

    public void setType(String type) {
        realmSet$type(type);
    }

    public String getUserId() {
        return realmGet$userId();
    }

    public void setUserId(String userId) {
        realmSet$userId(userId);
    }
}
