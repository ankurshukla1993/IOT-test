package io.realm;

import com.cooey.common.vo.MealPlan;
import com.cooey.common.vo.diet.weekdays.Friday;
import com.cooey.common.vo.diet.weekdays.Monday;
import com.cooey.common.vo.diet.weekdays.Saturday;
import com.cooey.common.vo.diet.weekdays.Thursday;
import com.cooey.common.vo.diet.weekdays.Tuesday;
import com.cooey.common.vo.diet.weekdays.Wednesday;

public interface DietTemplateRealmProxyInterface {
    String realmGet$dietTitle();

    Friday realmGet$friday();

    long realmGet$fromDate();

    String realmGet$id();

    RealmList<MealPlan> realmGet$mealPlan();

    Monday realmGet$monday();

    String realmGet$ownerId();

    String realmGet$ownerType();

    Saturday realmGet$saturday();

    Thursday realmGet$thursday();

    long realmGet$toDate();

    Tuesday realmGet$tuesday();

    Wednesday realmGet$wednesday();

    void realmSet$dietTitle(String str);

    void realmSet$friday(Friday friday);

    void realmSet$fromDate(long j);

    void realmSet$id(String str);

    void realmSet$mealPlan(RealmList<MealPlan> realmList);

    void realmSet$monday(Monday monday);

    void realmSet$ownerId(String str);

    void realmSet$ownerType(String str);

    void realmSet$saturday(Saturday saturday);

    void realmSet$thursday(Thursday thursday);

    void realmSet$toDate(long j);

    void realmSet$tuesday(Tuesday tuesday);

    void realmSet$wednesday(Wednesday wednesday);
}
