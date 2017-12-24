package com.cooey.common.vo.careplan;

import io.realm.AlertRealmProxyInterface;
import io.realm.RealmObject;
import io.realm.internal.RealmObjectProxy;

public class Alert extends RealmObject implements AlertRealmProxyInterface {
    private String alertType;
    private boolean call;
    private boolean email;
    private boolean notification;
    private boolean sms;

    public String realmGet$alertType() {
        return this.alertType;
    }

    public boolean realmGet$call() {
        return this.call;
    }

    public boolean realmGet$email() {
        return this.email;
    }

    public boolean realmGet$notification() {
        return this.notification;
    }

    public boolean realmGet$sms() {
        return this.sms;
    }

    public void realmSet$alertType(String str) {
        this.alertType = str;
    }

    public void realmSet$call(boolean z) {
        this.call = z;
    }

    public void realmSet$email(boolean z) {
        this.email = z;
    }

    public void realmSet$notification(boolean z) {
        this.notification = z;
    }

    public void realmSet$sms(boolean z) {
        this.sms = z;
    }

    public Alert() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
    }

    public String getAlertType() {
        return realmGet$alertType();
    }

    public void setAlertType(AlertType alertType) {
        realmSet$alertType(alertType.toString());
    }

    public boolean isCall() {
        return realmGet$call();
    }

    public void setCall(boolean call) {
        realmSet$call(call);
    }

    public boolean isEmail() {
        return realmGet$email();
    }

    public void setEmail(boolean email) {
        realmSet$email(email);
    }

    public boolean isSms() {
        return realmGet$sms();
    }

    public void setSms(boolean sms) {
        realmSet$sms(sms);
    }

    public boolean isNotification() {
        return realmGet$notification();
    }

    public void setNotification(boolean notification) {
        realmSet$notification(notification);
    }

    public String toString() {
        return "Alert{alertType='" + realmGet$alertType() + '\'' + ", call=" + realmGet$call() + ", email=" + realmGet$email() + ", sms=" + realmGet$sms() + ", notification=" + realmGet$notification() + '}';
    }
}
