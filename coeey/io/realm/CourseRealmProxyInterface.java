package io.realm;

public interface CourseRealmProxyInterface {
    String realmGet$assignedBy();

    boolean realmGet$completed();

    String realmGet$description();

    long realmGet$endTime();

    String realmGet$id();

    String realmGet$name();

    long realmGet$startTime();

    String realmGet$tenantId();

    void realmSet$assignedBy(String str);

    void realmSet$completed(boolean z);

    void realmSet$description(String str);

    void realmSet$endTime(long j);

    void realmSet$id(String str);

    void realmSet$name(String str);

    void realmSet$startTime(long j);

    void realmSet$tenantId(String str);
}
