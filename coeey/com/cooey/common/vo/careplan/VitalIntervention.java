package com.cooey.common.vo.careplan;

import io.realm.RealmObject;
import io.realm.VitalInterventionRealmProxyInterface;
import io.realm.internal.RealmObjectProxy;

public class VitalIntervention extends RealmObject implements VitalInterventionRealmProxyInterface {
    private Goal goal;
    private VitalFeature vitalFeature;

    public Goal realmGet$goal() {
        return this.goal;
    }

    public VitalFeature realmGet$vitalFeature() {
        return this.vitalFeature;
    }

    public void realmSet$goal(Goal goal) {
        this.goal = goal;
    }

    public void realmSet$vitalFeature(VitalFeature vitalFeature) {
        this.vitalFeature = vitalFeature;
    }

    public VitalIntervention() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
    }

    public VitalFeature getVitalFeature() {
        return realmGet$vitalFeature();
    }

    public void setVitalFeature(VitalFeature vitalFeature) {
        realmSet$vitalFeature(vitalFeature);
    }

    public Goal getGoal() {
        return realmGet$goal();
    }

    public void setGoal(Goal goal) {
        realmSet$goal(goal);
    }
}
