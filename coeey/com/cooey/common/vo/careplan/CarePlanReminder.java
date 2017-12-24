package com.cooey.common.vo.careplan;

import io.realm.CarePlanReminderRealmProxyInterface;
import io.realm.RealmObject;
import io.realm.internal.RealmObjectProxy;
import java.io.Serializable;

public class CarePlanReminder extends RealmObject implements Serializable, CarePlanReminderRealmProxyInterface {
    private int date;
    private Days days;
    private long endTime;
    private int hour;
    private int minutes;
    private long startTime;

    public int realmGet$date() {
        return this.date;
    }

    public Days realmGet$days() {
        return this.days;
    }

    public long realmGet$endTime() {
        return this.endTime;
    }

    public int realmGet$hour() {
        return this.hour;
    }

    public int realmGet$minutes() {
        return this.minutes;
    }

    public long realmGet$startTime() {
        return this.startTime;
    }

    public void realmSet$date(int i) {
        this.date = i;
    }

    public void realmSet$days(Days days) {
        this.days = days;
    }

    public void realmSet$endTime(long j) {
        this.endTime = j;
    }

    public void realmSet$hour(int i) {
        this.hour = i;
    }

    public void realmSet$minutes(int i) {
        this.minutes = i;
    }

    public void realmSet$startTime(long j) {
        this.startTime = j;
    }

    public CarePlanReminder() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
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

    public Days getDays() {
        return realmGet$days();
    }

    public void setDays(Days days) {
        realmSet$days(days);
    }

    public int getHour() {
        return realmGet$hour();
    }

    public void setHour(int hour) {
        realmSet$hour(hour);
    }

    public int getMinutes() {
        return realmGet$minutes();
    }

    public void setMinutes(int minutes) {
        realmSet$minutes(minutes);
    }

    public int getDate() {
        return realmGet$date();
    }

    public void setDate(int date) {
        realmSet$date(date);
    }
}
