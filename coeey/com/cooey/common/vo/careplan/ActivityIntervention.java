package com.cooey.common.vo.careplan;

import io.realm.ActivityInterventionRealmProxyInterface;
import io.realm.RealmObject;
import io.realm.internal.RealmObjectProxy;

public class ActivityIntervention extends RealmObject implements ActivityInterventionRealmProxyInterface {
    private ActivityFeature activityFeature;
    private Goal goal;

    public ActivityFeature realmGet$activityFeature() {
        return this.activityFeature;
    }

    public Goal realmGet$goal() {
        return this.goal;
    }

    public void realmSet$activityFeature(ActivityFeature activityFeature) {
        this.activityFeature = activityFeature;
    }

    public void realmSet$goal(Goal goal) {
        this.goal = goal;
    }

    public ActivityIntervention() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
    }

    public ActivityFeature getActivityFeature() {
        return realmGet$activityFeature();
    }

    public void setActivityFeature(ActivityFeature activityFeature) {
        realmSet$activityFeature(activityFeature);
    }

    public Goal getGoal() {
        return realmGet$goal();
    }

    public void setGoal(Goal goal) {
        realmSet$goal(goal);
    }
}
