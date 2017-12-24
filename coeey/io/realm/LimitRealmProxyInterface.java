package io.realm;

public interface LimitRealmProxyInterface {
    String realmGet$higherLimit();

    String realmGet$limitType();

    String realmGet$lowerLimit();

    void realmSet$higherLimit(String str);

    void realmSet$limitType(String str);

    void realmSet$lowerLimit(String str);
}
