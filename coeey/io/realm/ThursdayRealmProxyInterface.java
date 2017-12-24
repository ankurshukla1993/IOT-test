package io.realm;

import com.cooey.common.vo.diet.time.BreakFast;
import com.cooey.common.vo.diet.time.Dinner;
import com.cooey.common.vo.diet.time.Lunch;

public interface ThursdayRealmProxyInterface {
    BreakFast realmGet$breakFast();

    Dinner realmGet$dinner();

    Lunch realmGet$lunch();

    void realmSet$breakFast(BreakFast breakFast);

    void realmSet$dinner(Dinner dinner);

    void realmSet$lunch(Lunch lunch);
}
