package io.realm;

import com.cooey.common.vo.careplan.CarePlanReminder;

public interface TodoRealmProxyInterface {
    CarePlanReminder realmGet$carePlanReminder();

    String realmGet$note();

    void realmSet$carePlanReminder(CarePlanReminder carePlanReminder);

    void realmSet$note(String str);
}
