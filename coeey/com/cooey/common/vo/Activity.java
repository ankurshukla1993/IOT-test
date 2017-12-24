package com.cooey.common.vo;

import android.annotation.SuppressLint;
import com.alamkanak.weekview.WeekViewEvent;
import io.realm.ActivityRealmProxyInterface;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.internal.RealmObjectProxy;
import java.io.Serializable;
import java.util.Calendar;

public class Activity extends RealmObject implements Serializable, ActivityRealmProxyInterface {
    private String category;
    private String enableNotification;
    private String end;
    @PrimaryKey
    private String id;
    private String name;
    private String notificationBefore;
    private String patientId;
    private String start;
    private String userId;

    public String realmGet$category() {
        return this.category;
    }

    public String realmGet$enableNotification() {
        return this.enableNotification;
    }

    public String realmGet$end() {
        return this.end;
    }

    public String realmGet$id() {
        return this.id;
    }

    public String realmGet$name() {
        return this.name;
    }

    public String realmGet$notificationBefore() {
        return this.notificationBefore;
    }

    public String realmGet$patientId() {
        return this.patientId;
    }

    public String realmGet$start() {
        return this.start;
    }

    public String realmGet$userId() {
        return this.userId;
    }

    public void realmSet$category(String str) {
        this.category = str;
    }

    public void realmSet$enableNotification(String str) {
        this.enableNotification = str;
    }

    public void realmSet$end(String str) {
        this.end = str;
    }

    public void realmSet$id(String str) {
        this.id = str;
    }

    public void realmSet$name(String str) {
        this.name = str;
    }

    public void realmSet$notificationBefore(String str) {
        this.notificationBefore = str;
    }

    public void realmSet$patientId(String str) {
        this.patientId = str;
    }

    public void realmSet$start(String str) {
        this.start = str;
    }

    public void realmSet$userId(String str) {
        this.userId = str;
    }

    public Activity(String id, String name, String start, String end, String enableNotification, String patientId, String category) {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
        realmSet$id(id);
        realmSet$name(name);
        realmSet$start(start);
        realmSet$end(end);
        realmSet$enableNotification(enableNotification);
        realmSet$patientId(patientId);
        realmSet$category(category);
    }

    public String getName() {
        return realmGet$name();
    }

    public void setName(String name) {
        realmSet$name(name);
    }

    public Activity() {
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

    public String getStart() {
        return realmGet$start();
    }

    public void setStart(String start) {
        realmSet$start(start);
    }

    public String getEnd() {
        return realmGet$end();
    }

    public void setEnd(String end) {
        realmSet$end(end);
    }

    public String getEnableNotification() {
        return realmGet$enableNotification();
    }

    public void setEnableNotification(String enableNotification) {
        realmSet$enableNotification(enableNotification);
    }

    public String getPatientId() {
        return realmGet$patientId();
    }

    public void setPatientId(String patientId) {
        realmSet$patientId(patientId);
    }

    public String getCategory() {
        return realmGet$category();
    }

    public void setCategory(String category) {
        realmSet$category(category);
    }

    public String getUserId() {
        return realmGet$userId();
    }

    public void setUserId(String userId) {
        realmSet$userId(userId);
    }

    @SuppressLint({"SimpleDateFormat"})
    public WeekViewEvent toWeekViewEvent() {
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTimeInMillis(Long.parseLong(getStart()));
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTimeInMillis(Long.parseLong(getEnd()));
        Calendar now = Calendar.getInstance();
        Calendar startTime = (Calendar) now.clone();
        startTime.setTimeInMillis(startCalendar.getTime().getTime());
        startTime.set(1, startCalendar.get(1));
        startTime.set(2, startCalendar.get(2));
        startTime.set(5, startCalendar.get(5));
        Calendar endTime = (Calendar) now.clone();
        endTime.setTimeInMillis(endCalendar.getTime().getTime());
        endTime.set(1, endCalendar.get(1));
        endTime.set(2, endCalendar.get(2));
        endTime.set(5, endCalendar.get(5));
        WeekViewEvent weekViewEvent = new WeekViewEvent();
        weekViewEvent.setName(getName());
        weekViewEvent.setStartTime(startTime);
        weekViewEvent.setEndTime(endTime);
        return weekViewEvent;
    }
}
