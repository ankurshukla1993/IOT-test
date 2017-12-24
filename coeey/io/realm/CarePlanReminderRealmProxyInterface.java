package io.realm;

import com.cooey.common.vo.careplan.Days;

public interface CarePlanReminderRealmProxyInterface {
    int realmGet$date();

    Days realmGet$days();

    long realmGet$endTime();

    int realmGet$hour();

    int realmGet$minutes();

    long realmGet$startTime();

    void realmSet$date(int i);

    void realmSet$days(Days days);

    void realmSet$endTime(long j);

    void realmSet$hour(int i);

    void realmSet$minutes(int i);

    void realmSet$startTime(long j);
}
