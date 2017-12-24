package com.cooey.common.vo.careplan;

import io.realm.DietInterventionRealmProxyInterface;
import io.realm.RealmObject;
import io.realm.internal.RealmObjectProxy;

public class DietIntervention extends RealmObject implements DietInterventionRealmProxyInterface {
    private DietFeature dietFeature;
    private Goal goal;

    public DietFeature realmGet$dietFeature() {
        return this.dietFeature;
    }

    public Goal realmGet$goal() {
        return this.goal;
    }

    public void realmSet$dietFeature(DietFeature dietFeature) {
        this.dietFeature = dietFeature;
    }

    public void realmSet$goal(Goal goal) {
        this.goal = goal;
    }

    public DietIntervention() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
    }

    public DietFeature getDietFeature() {
        return realmGet$dietFeature();
    }

    public void setDietFeature(DietFeature dietFeature) {
        realmSet$dietFeature(dietFeature);
    }

    public Goal getGoal() {
        return realmGet$goal();
    }

    public void setGoal(Goal goal) {
        realmSet$goal(goal);
    }
}
