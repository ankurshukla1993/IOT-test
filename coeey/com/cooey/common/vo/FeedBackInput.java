package com.cooey.common.vo;

import io.realm.FeedBackInputRealmProxyInterface;
import io.realm.RealmObject;
import io.realm.internal.RealmObjectProxy;

public class FeedBackInput extends RealmObject implements FeedBackInputRealmProxyInterface {
    public boolean caretakerAlert;
    public boolean guardianAlert;
    public String label;
    public boolean patientAlert;
    public String type;
    public String value;

    public boolean realmGet$caretakerAlert() {
        return this.caretakerAlert;
    }

    public boolean realmGet$guardianAlert() {
        return this.guardianAlert;
    }

    public String realmGet$label() {
        return this.label;
    }

    public boolean realmGet$patientAlert() {
        return this.patientAlert;
    }

    public String realmGet$type() {
        return this.type;
    }

    public String realmGet$value() {
        return this.value;
    }

    public void realmSet$caretakerAlert(boolean z) {
        this.caretakerAlert = z;
    }

    public void realmSet$guardianAlert(boolean z) {
        this.guardianAlert = z;
    }

    public void realmSet$label(String str) {
        this.label = str;
    }

    public void realmSet$patientAlert(boolean z) {
        this.patientAlert = z;
    }

    public void realmSet$type(String str) {
        this.type = str;
    }

    public void realmSet$value(String str) {
        this.value = str;
    }

    public FeedBackInput() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
    }

    public String getLabel() {
        return realmGet$label();
    }

    public void setLabel(String label) {
        realmSet$label(label);
    }

    public String getType() {
        return realmGet$type();
    }

    public void setType(String type) {
        realmSet$type(type);
    }

    public String getValue() {
        return realmGet$value();
    }

    public void setValue(String value) {
        realmSet$value(value);
    }

    public boolean isCaretakerAlert() {
        return realmGet$caretakerAlert();
    }

    public void setCaretakerAlert(boolean caretakerAlert) {
        realmSet$caretakerAlert(caretakerAlert);
    }

    public boolean isGuardianAlert() {
        return realmGet$guardianAlert();
    }

    public void setGuardianAlert(boolean guardianAlert) {
        realmSet$guardianAlert(guardianAlert);
    }

    public boolean isPatientAlert() {
        return realmGet$patientAlert();
    }

    public void setPatientAlert(boolean patientAlert) {
        realmSet$patientAlert(patientAlert);
    }
}
