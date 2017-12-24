package io.realm;

public interface AlertRealmProxyInterface {
    String realmGet$alertType();

    boolean realmGet$call();

    boolean realmGet$email();

    boolean realmGet$notification();

    boolean realmGet$sms();

    void realmSet$alertType(String str);

    void realmSet$call(boolean z);

    void realmSet$email(boolean z);

    void realmSet$notification(boolean z);

    void realmSet$sms(boolean z);
}
