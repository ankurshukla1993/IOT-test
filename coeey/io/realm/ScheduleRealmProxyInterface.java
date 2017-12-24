package io.realm;

import com.cooey.common.vo.Timings;

public interface ScheduleRealmProxyInterface {
    long realmGet$beginTime();

    long realmGet$endTime();

    int realmGet$numOfDays();

    Timings realmGet$timings();

    void realmSet$beginTime(long j);

    void realmSet$endTime(long j);

    void realmSet$numOfDays(int i);

    void realmSet$timings(Timings timings);
}
