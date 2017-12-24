package io.realm;

import com.cooey.common.vo.Schedule;

public interface InterventionRealmProxyInterface {
    long realmGet$assignedOn();

    boolean realmGet$caretakerNotificationEnabled();

    long realmGet$createdOn();

    boolean realmGet$guaridanNotificationEnabled();

    String realmGet$id();

    String realmGet$name();

    String realmGet$ownerId();

    String realmGet$parameters();

    String realmGet$parentId();

    String realmGet$patientId();

    boolean realmGet$patientNotificationEnabled();

    String realmGet$permissions();

    Schedule realmGet$schedule();

    String realmGet$tenantId();

    String realmGet$type();

    long realmGet$updatedOn();

    void realmSet$assignedOn(long j);

    void realmSet$caretakerNotificationEnabled(boolean z);

    void realmSet$createdOn(long j);

    void realmSet$guaridanNotificationEnabled(boolean z);

    void realmSet$id(String str);

    void realmSet$name(String str);

    void realmSet$ownerId(String str);

    void realmSet$parameters(String str);

    void realmSet$parentId(String str);

    void realmSet$patientId(String str);

    void realmSet$patientNotificationEnabled(boolean z);

    void realmSet$permissions(String str);

    void realmSet$schedule(Schedule schedule);

    void realmSet$tenantId(String str);

    void realmSet$type(String str);

    void realmSet$updatedOn(long j);
}
