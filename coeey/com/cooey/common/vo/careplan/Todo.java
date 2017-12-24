package com.cooey.common.vo.careplan;

import io.realm.RealmObject;
import io.realm.TodoRealmProxyInterface;
import io.realm.internal.RealmObjectProxy;

public class Todo extends RealmObject implements TodoRealmProxyInterface {
    private CarePlanReminder carePlanReminder;
    private String note;

    public CarePlanReminder realmGet$carePlanReminder() {
        return this.carePlanReminder;
    }

    public String realmGet$note() {
        return this.note;
    }

    public void realmSet$carePlanReminder(CarePlanReminder carePlanReminder) {
        this.carePlanReminder = carePlanReminder;
    }

    public void realmSet$note(String str) {
        this.note = str;
    }

    public Todo() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
    }

    public String getNote() {
        return realmGet$note();
    }

    public void setNote(String note) {
        realmSet$note(note);
    }

    public CarePlanReminder getCarePlanReminder() {
        return realmGet$carePlanReminder();
    }

    public void setCarePlanReminder(CarePlanReminder carePlanReminder) {
        realmSet$carePlanReminder(carePlanReminder);
    }
}
