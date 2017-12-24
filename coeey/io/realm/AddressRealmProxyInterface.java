package io.realm;

public interface AddressRealmProxyInterface {
    String realmGet$city();

    String realmGet$line1();

    String realmGet$line2();

    String realmGet$state();

    void realmSet$city(String str);

    void realmSet$line1(String str);

    void realmSet$line2(String str);

    void realmSet$state(String str);
}
