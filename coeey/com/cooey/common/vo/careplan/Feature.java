package com.cooey.common.vo.careplan;

import io.realm.FeatureRealmProxyInterface;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.internal.RealmObjectProxy;
import java.io.Serializable;

public class Feature extends RealmObject implements Serializable, FeatureRealmProxyInterface {
    private Alert alert;
    private CarePlanReminder carePlanReminder;
    private long endTime;
    private String frequency;
    @PrimaryKey
    private String id;
    private String name;
    private String period;
    private CommonFeature properties;
    private String repeat;
    private long startTime;
    private String tenantId;
    private String type;
    private String url;

    public Alert realmGet$alert() {
        return this.alert;
    }

    public CarePlanReminder realmGet$carePlanReminder() {
        return this.carePlanReminder;
    }

    public long realmGet$endTime() {
        return this.endTime;
    }

    public String realmGet$frequency() {
        return this.frequency;
    }

    public String realmGet$id() {
        return this.id;
    }

    public String realmGet$name() {
        return this.name;
    }

    public String realmGet$period() {
        return this.period;
    }

    public CommonFeature realmGet$properties() {
        return this.properties;
    }

    public String realmGet$repeat() {
        return this.repeat;
    }

    public long realmGet$startTime() {
        return this.startTime;
    }

    public String realmGet$tenantId() {
        return this.tenantId;
    }

    public String realmGet$type() {
        return this.type;
    }

    public String realmGet$url() {
        return this.url;
    }

    public void realmSet$alert(Alert alert) {
        this.alert = alert;
    }

    public void realmSet$carePlanReminder(CarePlanReminder carePlanReminder) {
        this.carePlanReminder = carePlanReminder;
    }

    public void realmSet$endTime(long j) {
        this.endTime = j;
    }

    public void realmSet$frequency(String str) {
        this.frequency = str;
    }

    public void realmSet$id(String str) {
        this.id = str;
    }

    public void realmSet$name(String str) {
        this.name = str;
    }

    public void realmSet$period(String str) {
        this.period = str;
    }

    public void realmSet$properties(CommonFeature commonFeature) {
        this.properties = commonFeature;
    }

    public void realmSet$repeat(String str) {
        this.repeat = str;
    }

    public void realmSet$startTime(long j) {
        this.startTime = j;
    }

    public void realmSet$tenantId(String str) {
        this.tenantId = str;
    }

    public void realmSet$type(String str) {
        this.type = str;
    }

    public void realmSet$url(String str) {
        this.url = str;
    }

    public Feature() {
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

    public String getPeriod() {
        return realmGet$period();
    }

    public void setPeriod(String period) {
        realmSet$period(period);
    }

    public String getRepeat() {
        return realmGet$repeat();
    }

    public void setRepeat(String repeat) {
        realmSet$repeat(repeat);
    }

    public String getUrl() {
        return realmGet$url();
    }

    public void setUrl(String url) {
        realmSet$url(url);
    }

    public String getFrequency() {
        return realmGet$frequency();
    }

    public void setFrequency(FeatureFrequency frequency) {
        realmSet$frequency(frequency.toString());
    }

    public long getStartTime() {
        return realmGet$startTime();
    }

    public void setStartTime(long startTime) {
        realmSet$startTime(startTime);
    }

    public long getEndTime() {
        return realmGet$endTime();
    }

    public void setEndTime(long endTime) {
        realmSet$endTime(endTime);
    }

    public String getName() {
        return realmGet$name();
    }

    public void setName(String name) {
        realmSet$name(name);
    }

    public void setType(String type) {
        realmSet$type(type);
    }

    public void setProperties(CommonFeature properties) {
        realmSet$properties(properties);
    }

    public Alert getAlert() {
        return realmGet$alert();
    }

    public void setAlert(Alert alert) {
        realmSet$alert(alert);
    }

    public void setFrequency(String frequency) {
        realmSet$frequency(frequency);
    }

    public String getId() {
        return realmGet$id();
    }

    public void setId(String id) {
        realmSet$id(id);
    }

    public String getType() {
        return realmGet$type();
    }

    public void setType(FeatureType type) {
        realmSet$type(type.toString());
    }

    public CommonFeature getProperties() {
        return realmGet$properties();
    }

    public CarePlanReminder getCarePlanReminder() {
        return realmGet$carePlanReminder();
    }

    public void setCarePlanReminder(CarePlanReminder carePlanReminder) {
        realmSet$carePlanReminder(carePlanReminder);
    }
}
