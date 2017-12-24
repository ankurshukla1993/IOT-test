package com.cooey.common.vo.careplan;

import io.realm.AssessmentRealmProxyInterface;
import io.realm.RealmObject;
import io.realm.internal.RealmObjectProxy;
import java.io.Serializable;

public class Assessment extends RealmObject implements Serializable, AssessmentRealmProxyInterface {
    private String objective;
    private String subjective;

    public String realmGet$objective() {
        return this.objective;
    }

    public String realmGet$subjective() {
        return this.subjective;
    }

    public void realmSet$objective(String str) {
        this.objective = str;
    }

    public void realmSet$subjective(String str) {
        this.subjective = str;
    }

    public Assessment() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
    }

    public String getSubjective() {
        return realmGet$subjective();
    }

    public void setSubjective(String subjective) {
        realmSet$subjective(subjective);
    }

    public String getObjective() {
        return realmGet$objective();
    }

    public void setObjective(String objective) {
        realmSet$objective(objective);
    }
}
