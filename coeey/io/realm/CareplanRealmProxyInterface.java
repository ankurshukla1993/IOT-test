package io.realm;

import com.cooey.common.vo.careplan.Assessment;
import com.cooey.common.vo.careplan.Diagnosis;

public interface CareplanRealmProxyInterface {
    Assessment realmGet$assessment();

    long realmGet$assignedOn();

    String realmGet$assignerId();

    long realmGet$beginTime();

    long realmGet$createdOn();

    String realmGet$description();

    Diagnosis realmGet$diagnosis();

    boolean realmGet$enabled();

    long realmGet$endTime();

    String realmGet$evaluation();

    String realmGet$goal();

    String realmGet$history();

    String realmGet$id();

    String realmGet$name();

    int realmGet$numOfDays();

    String realmGet$parentId();

    String realmGet$patientId();

    String realmGet$tenantId();

    long realmGet$updatedOn();

    void realmSet$assessment(Assessment assessment);

    void realmSet$assignedOn(long j);

    void realmSet$assignerId(String str);

    void realmSet$beginTime(long j);

    void realmSet$createdOn(long j);

    void realmSet$description(String str);

    void realmSet$diagnosis(Diagnosis diagnosis);

    void realmSet$enabled(boolean z);

    void realmSet$endTime(long j);

    void realmSet$evaluation(String str);

    void realmSet$goal(String str);

    void realmSet$history(String str);

    void realmSet$id(String str);

    void realmSet$name(String str);

    void realmSet$numOfDays(int i);

    void realmSet$parentId(String str);

    void realmSet$patientId(String str);

    void realmSet$tenantId(String str);

    void realmSet$updatedOn(long j);
}
