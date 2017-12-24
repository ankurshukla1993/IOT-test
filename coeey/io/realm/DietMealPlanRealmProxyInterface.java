package io.realm;

import com.cooey.common.vo.diet.time.BreakFast;
import com.cooey.common.vo.diet.time.Dinner;
import com.cooey.common.vo.diet.time.Lunch;

public interface DietMealPlanRealmProxyInterface {
    BreakFast realmGet$breakFast();

    String realmGet$calories();

    String realmGet$carbs();

    Dinner realmGet$dinner();

    String realmGet$fat();

    String realmGet$id();

    Lunch realmGet$lunch();

    String realmGet$name();

    String realmGet$proteins();

    String realmGet$quantity();

    String realmGet$servingSize();

    String realmGet$type();

    void realmSet$breakFast(BreakFast breakFast);

    void realmSet$calories(String str);

    void realmSet$carbs(String str);

    void realmSet$dinner(Dinner dinner);

    void realmSet$fat(String str);

    void realmSet$id(String str);

    void realmSet$lunch(Lunch lunch);

    void realmSet$name(String str);

    void realmSet$proteins(String str);

    void realmSet$quantity(String str);

    void realmSet$servingSize(String str);

    void realmSet$type(String str);
}
