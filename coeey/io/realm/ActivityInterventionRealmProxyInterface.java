package io.realm;

import com.cooey.common.vo.careplan.ActivityFeature;
import com.cooey.common.vo.careplan.Goal;

public interface ActivityInterventionRealmProxyInterface {
    ActivityFeature realmGet$activityFeature();

    Goal realmGet$goal();

    void realmSet$activityFeature(ActivityFeature activityFeature);

    void realmSet$goal(Goal goal);
}
