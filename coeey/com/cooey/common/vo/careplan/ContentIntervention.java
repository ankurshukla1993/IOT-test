package com.cooey.common.vo.careplan;

import io.realm.ContentInterventionRealmProxyInterface;
import io.realm.RealmObject;
import io.realm.internal.RealmObjectProxy;

public class ContentIntervention extends RealmObject implements ContentInterventionRealmProxyInterface {
    private ContentFeature contentFeature;
    private Goal goal;

    public ContentFeature realmGet$contentFeature() {
        return this.contentFeature;
    }

    public Goal realmGet$goal() {
        return this.goal;
    }

    public void realmSet$contentFeature(ContentFeature contentFeature) {
        this.contentFeature = contentFeature;
    }

    public void realmSet$goal(Goal goal) {
        this.goal = goal;
    }

    public ContentIntervention() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
    }

    public ContentFeature getContentFeature() {
        return realmGet$contentFeature();
    }

    public void setContentFeature(ContentFeature contentFeature) {
        realmSet$contentFeature(contentFeature);
    }

    public Goal getGoal() {
        return realmGet$goal();
    }

    public void setGoal(Goal goal) {
        realmSet$goal(goal);
    }
}
