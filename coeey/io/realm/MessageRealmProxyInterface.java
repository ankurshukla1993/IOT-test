package io.realm;

public interface MessageRealmProxyInterface {
    String realmGet$from();

    String realmGet$id();

    String realmGet$message();

    int realmGet$messageType();

    String realmGet$parameters();

    long realmGet$timestamp();

    void realmSet$from(String str);

    void realmSet$id(String str);

    void realmSet$message(String str);

    void realmSet$messageType(int i);

    void realmSet$parameters(String str);

    void realmSet$timestamp(long j);
}
