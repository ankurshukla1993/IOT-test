package io.realm;

import com.cooey.common.vo.careplan.ContentFeature;
import com.cooey.common.vo.careplan.Goal;

public interface ContentInterventionRealmProxyInterface {
    ContentFeature realmGet$contentFeature();

    Goal realmGet$goal();

    void realmSet$contentFeature(ContentFeature contentFeature);

    void realmSet$goal(Goal goal);
}
