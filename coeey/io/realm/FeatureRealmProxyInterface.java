package io.realm;

import com.cooey.common.vo.careplan.Alert;
import com.cooey.common.vo.careplan.CarePlanReminder;
import com.cooey.common.vo.careplan.CommonFeature;

public interface FeatureRealmProxyInterface {
    Alert realmGet$alert();

    CarePlanReminder realmGet$carePlanReminder();

    long realmGet$endTime();

    String realmGet$frequency();

    String realmGet$id();

    String realmGet$name();

    String realmGet$period();

    CommonFeature realmGet$properties();

    String realmGet$repeat();

    long realmGet$startTime();

    String realmGet$tenantId();

    String realmGet$type();

    String realmGet$url();

    void realmSet$alert(Alert alert);

    void realmSet$carePlanReminder(CarePlanReminder carePlanReminder);

    void realmSet$endTime(long j);

    void realmSet$frequency(String str);

    void realmSet$id(String str);

    void realmSet$name(String str);

    void realmSet$period(String str);

    void realmSet$properties(CommonFeature commonFeature);

    void realmSet$repeat(String str);

    void realmSet$startTime(long j);

    void realmSet$tenantId(String str);

    void realmSet$type(String str);

    void realmSet$url(String str);
}
