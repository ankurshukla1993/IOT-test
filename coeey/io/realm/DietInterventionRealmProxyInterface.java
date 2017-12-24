package io.realm;

import com.cooey.common.vo.careplan.DietFeature;
import com.cooey.common.vo.careplan.Goal;

public interface DietInterventionRealmProxyInterface {
    DietFeature realmGet$dietFeature();

    Goal realmGet$goal();

    void realmSet$dietFeature(DietFeature dietFeature);

    void realmSet$goal(Goal goal);
}
