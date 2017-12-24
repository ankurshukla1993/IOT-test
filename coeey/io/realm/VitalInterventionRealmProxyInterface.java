package io.realm;

import com.cooey.common.vo.careplan.Goal;
import com.cooey.common.vo.careplan.VitalFeature;

public interface VitalInterventionRealmProxyInterface {
    Goal realmGet$goal();

    VitalFeature realmGet$vitalFeature();

    void realmSet$goal(Goal goal);

    void realmSet$vitalFeature(VitalFeature vitalFeature);
}
