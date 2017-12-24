package com.cooey.common.vo.careplan;

import com.cooey.common.vo.Vital;
import io.realm.CommonFeatureRealmProxyInterface;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.internal.RealmObjectProxy;
import java.io.Serializable;

public class CommonFeature extends RealmObject implements Serializable, CommonFeatureRealmProxyInterface {
    @PrimaryKey
    private String id;
    private Limits limits;
    private String name;
    private String parameters;
    private String tenantId;
    private String type;
    private Vital vitalParameters;

    public String realmGet$id() {
        return this.id;
    }

    public Limits realmGet$limits() {
        return this.limits;
    }

    public String realmGet$name() {
        return this.name;
    }

    public String realmGet$parameters() {
        return this.parameters;
    }

    public String realmGet$tenantId() {
        return this.tenantId;
    }

    public String realmGet$type() {
        return this.type;
    }

    public Vital realmGet$vitalParameters() {
        return this.vitalParameters;
    }

    public void realmSet$id(String str) {
        this.id = str;
    }

    public void realmSet$limits(Limits limits) {
        this.limits = limits;
    }

    public void realmSet$name(String str) {
        this.name = str;
    }

    public void realmSet$parameters(String str) {
        this.parameters = str;
    }

    public void realmSet$tenantId(String str) {
        this.tenantId = str;
    }

    public void realmSet$type(String str) {
        this.type = str;
    }

    public void realmSet$vitalParameters(Vital vital) {
        this.vitalParameters = vital;
    }

    public CommonFeature() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
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

    public String getType() {
        return realmGet$type();
    }

    public void setType(String type) {
        realmSet$type(type);
    }

    public Limits getLimits() {
        return realmGet$limits();
    }

    public void setLimits(Limits limits) {
        realmSet$limits(limits);
    }

    public String getName() {
        return realmGet$name();
    }

    public void setName(String name) {
        realmSet$name(name);
    }

    public String getParameters() {
        return realmGet$parameters();
    }

    public void setParameters(String parameters) {
        realmSet$parameters(parameters);
    }

    public Vital getVitalParameters() {
        return realmGet$vitalParameters();
    }

    public void setVitalParameters(Vital vitalParameters) {
        realmSet$vitalParameters(vitalParameters);
    }

    public String toString() {
        return "CommonFeature{id='" + realmGet$id() + '\'' + ", tenantId='" + realmGet$tenantId() + '\'' + ", type='" + realmGet$type() + '\'' + ", limits=" + realmGet$limits() + ", name='" + realmGet$name() + '\'' + ", parameters='" + realmGet$parameters() + '\'' + ", vitalParameters=" + realmGet$vitalParameters() + '}';
    }
}
