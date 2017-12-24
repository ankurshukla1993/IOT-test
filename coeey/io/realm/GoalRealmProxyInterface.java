package io.realm;

import com.cooey.common.vo.careplan.CarePlanReminder;
import com.cooey.common.vo.careplan.Instructions;

public interface GoalRealmProxyInterface {
    CarePlanReminder realmGet$carePlanReminder();

    Instructions realmGet$instructions();

    void realmSet$carePlanReminder(CarePlanReminder carePlanReminder);

    void realmSet$instructions(Instructions instructions);
}
