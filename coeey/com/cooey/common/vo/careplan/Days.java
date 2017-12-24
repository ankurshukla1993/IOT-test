package com.cooey.common.vo.careplan;

import io.realm.DaysRealmProxyInterface;
import io.realm.RealmObject;
import io.realm.internal.RealmObjectProxy;

public class Days extends RealmObject implements DaysRealmProxyInterface {
    private boolean friday;
    private boolean monday;
    private boolean saturday;
    private boolean sunday;
    private boolean thursday;
    private boolean tuesday;
    private boolean wednesday;

    public boolean realmGet$friday() {
        return this.friday;
    }

    public boolean realmGet$monday() {
        return this.monday;
    }

    public boolean realmGet$saturday() {
        return this.saturday;
    }

    public boolean realmGet$sunday() {
        return this.sunday;
    }

    public boolean realmGet$thursday() {
        return this.thursday;
    }

    public boolean realmGet$tuesday() {
        return this.tuesday;
    }

    public boolean realmGet$wednesday() {
        return this.wednesday;
    }

    public void realmSet$friday(boolean z) {
        this.friday = z;
    }

    public void realmSet$monday(boolean z) {
        this.monday = z;
    }

    public void realmSet$saturday(boolean z) {
        this.saturday = z;
    }

    public void realmSet$sunday(boolean z) {
        this.sunday = z;
    }

    public void realmSet$thursday(boolean z) {
        this.thursday = z;
    }

    public void realmSet$tuesday(boolean z) {
        this.tuesday = z;
    }

    public void realmSet$wednesday(boolean z) {
        this.wednesday = z;
    }

    public Days() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
    }

    public boolean isMonday() {
        return realmGet$monday();
    }

    public void setMonday(boolean monday) {
        realmSet$monday(monday);
    }

    public boolean isTuesday() {
        return realmGet$tuesday();
    }

    public void setTuesday(boolean tuesday) {
        realmSet$tuesday(tuesday);
    }

    public boolean isWednesday() {
        return realmGet$wednesday();
    }

    public void setWednesday(boolean wednesday) {
        realmSet$wednesday(wednesday);
    }

    public boolean isThursday() {
        return realmGet$thursday();
    }

    public void setThursday(boolean thursday) {
        realmSet$thursday(thursday);
    }

    public boolean isFriday() {
        return realmGet$friday();
    }

    public void setFriday(boolean friday) {
        realmSet$friday(friday);
    }

    public boolean isSaturday() {
        return realmGet$saturday();
    }

    public void setSaturday(boolean saturday) {
        realmSet$saturday(saturday);
    }

    public boolean isSunday() {
        return realmGet$sunday();
    }

    public void setSunday(boolean sunday) {
        realmSet$sunday(sunday);
    }
}
