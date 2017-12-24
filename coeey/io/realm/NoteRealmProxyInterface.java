package io.realm;

public interface NoteRealmProxyInterface {
    boolean realmGet$active();

    String realmGet$applicationId();

    boolean realmGet$archived();

    String realmGet$content();

    long realmGet$createdOn();

    String realmGet$id();

    String realmGet$tenantId();

    String realmGet$type();

    long realmGet$updatedOn();

    String realmGet$userId();

    void realmSet$active(boolean z);

    void realmSet$applicationId(String str);

    void realmSet$archived(boolean z);

    void realmSet$content(String str);

    void realmSet$createdOn(long j);

    void realmSet$id(String str);

    void realmSet$tenantId(String str);

    void realmSet$type(String str);

    void realmSet$updatedOn(long j);

    void realmSet$userId(String str);
}
