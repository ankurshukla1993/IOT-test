package io.realm;

public interface MealPlanRealmProxyInterface {
    String realmGet$calories();

    String realmGet$carbs();

    String realmGet$dietId();

    String realmGet$dietRecomendation();

    String realmGet$fiber();

    String realmGet$name();

    String realmGet$tagId();

    void realmSet$calories(String str);

    void realmSet$carbs(String str);

    void realmSet$dietId(String str);

    void realmSet$dietRecomendation(String str);

    void realmSet$fiber(String str);

    void realmSet$name(String str);

    void realmSet$tagId(String str);
}
