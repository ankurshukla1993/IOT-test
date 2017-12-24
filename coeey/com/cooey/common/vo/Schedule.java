package com.cooey.common.vo;

import io.realm.RealmObject;
import io.realm.ScheduleRealmProxyInterface;
import io.realm.internal.RealmObjectProxy;

public class Schedule extends RealmObject implements ScheduleRealmProxyInterface {
    private long beginTime;
    private long endTime;
    private int numOfDays;
    private Timings timings;

    public long realmGet$beginTime() {
        return this.beginTime;
    }

    public long realmGet$endTime() {
        return this.endTime;
    }

    public int realmGet$numOfDays() {
        return this.numOfDays;
    }

    public Timings realmGet$timings() {
        return this.timings;
    }

    public void realmSet$beginTime(long j) {
        this.beginTime = j;
    }

    public void realmSet$endTime(long j) {
        this.endTime = j;
    }

    public void realmSet$numOfDays(int i) {
        this.numOfDays = i;
    }

    public void realmSet$timings(Timings timings) {
        this.timings = timings;
    }

    public Schedule() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
    }

    public void setNumOfDays(int numOfDays) {
        realmSet$numOfDays(numOfDays);
    }

    public int getNumOfDays() {
        return realmGet$numOfDays();
    }

    public void setTimings(Timings timings) {
        realmSet$timings(timings);
    }

    public Timings getTimings() {
        return realmGet$timings();
    }

    public void setBeginTime(long beginTime) {
        realmSet$beginTime(beginTime);
    }

    public long getBeginTime() {
        return realmGet$beginTime();
    }

    public void setEndTime(long endTime) {
        realmSet$endTime(endTime);
    }

    public long getEndTime() {
        return realmGet$endTime();
    }
}
