package com.cooey.common.vo.careplan;

import io.realm.GoalRealmProxyInterface;
import io.realm.RealmObject;
import io.realm.internal.RealmObjectProxy;

public class Goal extends RealmObject implements GoalRealmProxyInterface {
    private CarePlanReminder carePlanReminder;
    private Instructions instructions;

    public CarePlanReminder realmGet$carePlanReminder() {
        return this.carePlanReminder;
    }

    public Instructions realmGet$instructions() {
        return this.instructions;
    }

    public void realmSet$carePlanReminder(CarePlanReminder carePlanReminder) {
        this.carePlanReminder = carePlanReminder;
    }

    public void realmSet$instructions(Instructions instructions) {
        this.instructions = instructions;
    }

    public Goal() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
    }

    public Instructions getInstructions() {
        return realmGet$instructions();
    }

    public void setInstructions(Instructions instructions) {
        realmSet$instructions(instructions);
    }

    public CarePlanReminder getCarePlanReminder() {
        return realmGet$carePlanReminder();
    }

    public void setCarePlanReminder(CarePlanReminder carePlanReminder) {
        realmSet$carePlanReminder(carePlanReminder);
    }
}
